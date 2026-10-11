package com.waltenfiles.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.waltenfiles.WaltenFilesMod;
import com.waltenfiles.entity.WaltenMob;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;

/** Renderer humanoide (modelo de zumbi, bracos levantados quando agressivo) com textura propria. */
public class WaltenRenderer extends HumanoidMobRenderer<WaltenMob, ZombieModel<WaltenMob>> {
    private final ResourceLocation texture;
    private final float scale;

    public WaltenRenderer(EntityRendererProvider.Context context, String textureName, float scale) {
        super(context, new ZombieModel<>(context.bakeLayer(ModelLayers.ZOMBIE)), 0.5F * scale);
        this.texture = new ResourceLocation(WaltenFilesMod.MODID, "textures/entity/" + textureName + ".png");
        this.scale = scale;
    }

    @Override
    public ResourceLocation getTextureLocation(WaltenMob entity) {
        return texture;
    }

    @Override
    protected void scale(WaltenMob entity, PoseStack poseStack, float partialTick) {
        poseStack.scale(scale, scale, scale);
    }
}
