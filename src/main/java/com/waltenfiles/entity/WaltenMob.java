package com.waltenfiles.entity;

import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

/** Mob generico de The Walten Files. O comportamento vem do MobProfile. */
public class WaltenMob extends Monster {
    private final MobProfile profile;
    private int blinkCooldown = 100;
    private int watchedTicks = 0;

    public WaltenMob(EntityType<? extends WaltenMob> type, Level level, MobProfile profile) {
        super(type, level);
        this.profile = profile;
        this.xpReward = 10;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 12.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide) {
            return;
        }

        boolean active = !profile.nightOnly || isNightTime();
        boolean watchSensitive = profile.freezeWhenWatched || profile.vanishWhenWatched;
        boolean watched = watchSensitive && isWatchedByPlayer();
        boolean frozen = !active || (profile.freezeWhenWatched && watched);

        if (this.isNoAi() != frozen) {
            this.setNoAi(frozen);
        }
        if (!active && this.getTarget() != null) {
            this.setTarget(null);
        }

        if (profile.vanishWhenWatched) {
            if (watched) {
                watchedTicks++;
                if (watchedTicks > 60) {
                    this.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, 0.5F);
                    this.discard();
                    return;
                }
            } else if (watchedTicks > 0) {
                watchedTicks--;
            }
        }

        if (profile.blinkBehindTarget && !frozen) {
            if (blinkCooldown > 0) {
                blinkCooldown--;
            } else {
                LivingEntity target = this.getTarget();
                if (target != null && this.distanceToSqr(target) > 16.0D) {
                    blinkBehind(target);
                }
                blinkCooldown = 80 + this.random.nextInt(80);
            }
        }
    }

    private void blinkBehind(LivingEntity target) {
        Vec3 back = target.getLookAngle().multiply(1.0D, 0.0D, 1.0D);
        if (back.lengthSqr() < 1.0E-4D) {
            return;
        }
        back = back.normalize().scale(-2.5D);
        if (this.randomTeleport(target.getX() + back.x, target.getY(), target.getZ() + back.z, true)) {
            this.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, 0.6F);
        }
    }

    private boolean isNightTime() {
        long t = this.level().getDayTime() % 24000L;
        return t >= 13000L && t < 23000L;
    }

    /** Parecido com a checagem do Enderman: o jogador esta olhando na direcao do mob? */
    private boolean isWatchedByPlayer() {
        for (Player p : this.level().players()) {
            if (p.isSpectator() || this.distanceToSqr(p) > 32.0D * 32.0D) {
                continue;
            }
            Vec3 look = p.getViewVector(1.0F).normalize();
            Vec3 toMob = new Vec3(this.getX() - p.getX(), this.getEyeY() - p.getEyeY(), this.getZ() - p.getZ());
            double dist = toMob.length();
            if (dist < 0.001D) {
                return true;
            }
            toMob = toMob.normalize();
            if (look.dot(toMob) > 1.0D - 0.03D / dist && p.hasLineOfSight(this)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        boolean hit = super.doHurtTarget(target);
        if (hit && profile.hitEffect != null && target instanceof LivingEntity living) {
            living.addEffect(new MobEffectInstance(profile.hitEffect.get(), profile.hitEffectTicks, profile.hitEffectAmplifier), this);
        }
        return hit;
    }

    @Override
    public boolean removeWhenFarAway(double distanceSqr) {
        return !profile.machine && super.removeWhenFarAway(distanceSqr);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        if (profile.machine) {
            return null;
        }
        return profile.vanishWhenWatched ? SoundEvents.ENDERMAN_AMBIENT : SoundEvents.ZOMBIE_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return profile.machine ? SoundEvents.IRON_GOLEM_HURT : SoundEvents.ZOMBIE_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return profile.machine ? SoundEvents.IRON_GOLEM_DEATH : SoundEvents.ZOMBIE_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(profile.machine ? SoundEvents.IRON_GOLEM_STEP : SoundEvents.ZOMBIE_STEP, 0.15F, 1.0F);
    }
}
