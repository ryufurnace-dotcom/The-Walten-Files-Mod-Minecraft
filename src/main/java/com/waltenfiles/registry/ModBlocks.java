package com.waltenfiles.registry;

import com.waltenfiles.WaltenFilesMod;
import com.waltenfiles.block.RetroTvBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, WaltenFilesMod.MODID);

    public static final RegistryObject<Block> TV_RETRO = BLOCKS.register("tv_retro",
            () -> new RetroTvBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BROWN)
                    .strength(1.5F)
                    .sound(SoundType.METAL)
                    .noOcclusion()
                    .lightLevel(state -> 3)));
}
