package com.waltenfiles.registry;

import com.waltenfiles.WaltenFilesMod;
import com.waltenfiles.entity.MobProfile;
import com.waltenfiles.entity.WaltenMob;
import java.util.List;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, WaltenFilesMod.MODID);

    // ---- Personagens (mobs) ----
    public static final RegistryObject<EntityType<WaltenMob>> JACK = mob("jack_walten",
            new MobProfile().onHit(() -> MobEffects.WEAKNESS, 100, 0), 0.7F, 2.1F);
    public static final RegistryObject<EntityType<WaltenMob>> ROSEMARY = mob("rosemary_walten",
            new MobProfile().onHit(() -> MobEffects.MOVEMENT_SLOWDOWN, 80, 1), 0.6F, 1.95F);
    public static final RegistryObject<EntityType<WaltenMob>> SOPHIE = mob("sophie_walten",
            new MobProfile().blinkBehindTarget().onHit(() -> MobEffects.MOVEMENT_SLOWDOWN, 60, 0), 0.5F, 1.65F);
    public static final RegistryObject<EntityType<WaltenMob>> EDD = mob("edd_walten",
            new MobProfile().nightOnly().onHit(() -> MobEffects.CONFUSION, 120, 0), 0.6F, 1.9F);
    public static final RegistryObject<EntityType<WaltenMob>> MOLLY = mob("molly_walten",
            new MobProfile().nightOnly().onHit(() -> MobEffects.CONFUSION, 120, 0), 0.55F, 1.8F);
    public static final RegistryObject<EntityType<WaltenMob>> FELIX = mob("felix_kraken",
            new MobProfile().freezeWhenWatched().onHit(() -> MobEffects.BLINDNESS, 80, 0), 0.6F, 1.95F);
    public static final RegistryObject<EntityType<WaltenMob>> SHADOW_MAN = mob("shadow_man",
            new MobProfile().blinkBehindTarget().vanishWhenWatched().onHit(() -> MobEffects.DARKNESS, 200, 0), 0.6F, 2.5F);

    // ---- Animatronicos (so acordam de noite) ----
    public static final RegistryObject<EntityType<WaltenMob>> BON = mob("bon",
            new MobProfile().nightOnly().machine(), 0.7F, 2.0F);
    public static final RegistryObject<EntityType<WaltenMob>> SHA = mob("sha",
            new MobProfile().nightOnly().machine(), 0.7F, 2.0F);
    public static final RegistryObject<EntityType<WaltenMob>> BOOZOO = mob("boozoo",
            new MobProfile().nightOnly().machine(), 0.7F, 2.0F);
    public static final RegistryObject<EntityType<WaltenMob>> BANNY = mob("banny",
            new MobProfile().nightOnly().machine().freezeWhenWatched(), 0.7F, 2.0F);

    private static RegistryObject<EntityType<WaltenMob>> mob(String name, MobProfile profile, float width, float height) {
        return ENTITIES.register(name, () -> EntityType.Builder
                .<WaltenMob>of((type, level) -> new WaltenMob(type, level, profile), MobCategory.MONSTER)
                .sized(width, height)
                .clientTrackingRange(10)
                .build(WaltenFilesMod.MODID + ":" + name));
    }

    private static AttributeSupplier.Builder attrs(double health, double speed, double damage, double knockbackResist) {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, health)
                .add(Attributes.MOVEMENT_SPEED, speed)
                .add(Attributes.ATTACK_DAMAGE, damage)
                .add(Attributes.FOLLOW_RANGE, 40.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, knockbackResist);
    }

    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(JACK.get(), attrs(60, 0.23, 9, 0.6).build());
        event.put(ROSEMARY.get(), attrs(40, 0.30, 6, 0.2).build());
        event.put(SOPHIE.get(), attrs(24, 0.34, 4, 0.0).build());
        event.put(EDD.get(), attrs(30, 0.32, 5, 0.1).build());
        event.put(MOLLY.get(), attrs(24, 0.30, 4, 0.1).build());
        event.put(FELIX.get(), attrs(45, 0.28, 7, 0.3).build());
        event.put(SHADOW_MAN.get(), attrs(80, 0.30, 10, 0.5).build());
        event.put(BON.get(), attrs(50, 0.25, 8, 0.4).build());
        event.put(SHA.get(), attrs(40, 0.27, 7, 0.3).build());
        event.put(BOOZOO.get(), attrs(45, 0.26, 7, 0.3).build());
        event.put(BANNY.get(), attrs(55, 0.24, 8, 0.4).build());
    }

    /** Mobs que tambem aparecem naturalmente na floresta a noite (via biome modifier). */
    public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {
        for (RegistryObject<EntityType<WaltenMob>> type : List.of(SHADOW_MAN, FELIX, EDD, MOLLY)) {
            event.register(type.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        }
    }
}
