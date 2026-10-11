package com.waltenfiles.entity;

import java.util.function.Supplier;
import net.minecraft.world.effect.MobEffect;

/** Descreve o comportamento de cada personagem. Edite aqui para ajustar o terror. */
public final class MobProfile {
    /** So se move/ataca de noite (animatronicos e aparicoes). De dia fica parado como estatua. */
    public boolean nightOnly;
    /** Congela quando algum jogador esta olhando para ele. */
    public boolean freezeWhenWatched;
    /** Teleporta para as costas do alvo de tempos em tempos. */
    public boolean blinkBehindTarget;
    /** Desaparece se o jogador encarar por ~3 segundos. */
    public boolean vanishWhenWatched;
    /** Usa sons metalicos (animatronico). */
    public boolean machine;
    public Supplier<MobEffect> hitEffect;
    public int hitEffectTicks;
    public int hitEffectAmplifier;

    public MobProfile nightOnly() { this.nightOnly = true; return this; }
    public MobProfile freezeWhenWatched() { this.freezeWhenWatched = true; return this; }
    public MobProfile blinkBehindTarget() { this.blinkBehindTarget = true; return this; }
    public MobProfile vanishWhenWatched() { this.vanishWhenWatched = true; return this; }
    public MobProfile machine() { this.machine = true; return this; }

    public MobProfile onHit(Supplier<MobEffect> effect, int ticks, int amplifier) {
        this.hitEffect = effect;
        this.hitEffectTicks = ticks;
        this.hitEffectAmplifier = amplifier;
        return this;
    }
}
