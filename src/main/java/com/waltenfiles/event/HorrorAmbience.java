package com.waltenfiles.event;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;

/** Sons de batimento distantes na floresta (taiga/dark forest) durante a noite. */
public class HorrorAmbience {
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END || event.side != LogicalSide.SERVER) {
            return;
        }
        Player player = event.player;
        if (player.tickCount % 200 != 0) {
            return;
        }
        Level level = player.level();
        RandomSource random = level.getRandom();
        if (random.nextInt(3) != 0) {
            return;
        }
        long t = level.getDayTime() % 24000L;
        boolean night = t >= 13000L && t < 23000L;
        var biome = level.getBiome(player.blockPosition());
        boolean forest = biome.is(BiomeTags.IS_TAIGA) || biome.is(Biomes.DARK_FOREST);
        if (night && forest) {
            double x = player.getX() + (random.nextDouble() - 0.5D) * 24.0D;
            double z = player.getZ() + (random.nextDouble() - 0.5D) * 24.0D;
            level.playSound(null, x, player.getY(), z, SoundEvents.WARDEN_HEARTBEAT, SoundSource.AMBIENT, 0.8F, 0.7F);
        }
    }
}
