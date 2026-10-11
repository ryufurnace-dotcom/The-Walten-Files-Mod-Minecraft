package com.waltenfiles.client;

import com.waltenfiles.WaltenFilesMod;
import com.waltenfiles.entity.WaltenMob;
import com.waltenfiles.registry.ModEntities;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = WaltenFilesMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        reg(event, ModEntities.JACK, "jack_walten", 1.1F);
        reg(event, ModEntities.ROSEMARY, "rosemary_walten", 1.0F);
        reg(event, ModEntities.SOPHIE, "sophie_walten", 0.85F);
        reg(event, ModEntities.EDD, "edd_walten", 0.97F);
        reg(event, ModEntities.MOLLY, "molly_walten", 0.92F);
        reg(event, ModEntities.FELIX, "felix_kraken", 1.0F);
        reg(event, ModEntities.SHADOW_MAN, "shadow_man", 1.28F);
        reg(event, ModEntities.BON, "bon", 1.03F);
        reg(event, ModEntities.SHA, "sha", 1.03F);
        reg(event, ModEntities.BOOZOO, "boozoo", 1.03F);
        reg(event, ModEntities.BANNY, "banny", 1.03F);
    }

    private static void reg(EntityRenderersEvent.RegisterRenderers event,
                            RegistryObject<EntityType<WaltenMob>> type, String texture, float scale) {
        event.registerEntityRenderer(type.get(), ctx -> new WaltenRenderer(ctx, texture, scale));
    }
}
