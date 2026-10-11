package com.waltenfiles;

import com.waltenfiles.event.HorrorAmbience;
import com.waltenfiles.registry.ModBlocks;
import com.waltenfiles.registry.ModEntities;
import com.waltenfiles.registry.ModItems;
import com.waltenfiles.registry.ModTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(WaltenFilesMod.MODID)
public class WaltenFilesMod {
    public static final String MODID = "waltenfiles";

    public WaltenFilesMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModBlocks.BLOCKS.register(bus);
        ModItems.ITEMS.register(bus);
        ModEntities.ENTITIES.register(bus);
        ModTabs.TABS.register(bus);
        bus.addListener(ModEntities::registerAttributes);
        bus.addListener(ModEntities::registerSpawnPlacements);
        MinecraftForge.EVENT_BUS.register(new HorrorAmbience());
    }
}
