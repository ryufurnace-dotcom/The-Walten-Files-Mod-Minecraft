package com.waltenfiles.client;

import net.minecraft.client.model.AbstractZombieModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.monster.Monster;

/** Modelo humanoide com bracos levantados quando o mob esta agressivo (igual ao zumbi, mas para qualquer Monster). */
public class WaltenModel<T extends Monster> extends AbstractZombieModel<T> {
    public WaltenModel(ModelPart part) {
        super(part);
    }

    @Override
    public boolean isAggressive(T entity) {
        return entity.isAggressive();
    }
}
