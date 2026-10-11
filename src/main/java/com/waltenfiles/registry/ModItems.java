package com.waltenfiles.registry;

import com.waltenfiles.WaltenFilesMod;
import com.waltenfiles.entity.WaltenMob;
import com.waltenfiles.item.TapeItem;
import com.waltenfiles.lore.TapeLore;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, WaltenFilesMod.MODID);

    public static final RegistryObject<Item> TV_RETRO = ITEMS.register("tv_retro",
            () -> new BlockItem(ModBlocks.TV_RETRO.get(), new Item.Properties()));

    public static final List<RegistryObject<Item>> FITAS = new ArrayList<>();

    static {
        for (int i = 1; i <= TapeLore.COUNT; i++) {
            final int id = i;
            FITAS.add(ITEMS.register("fita_cassete_" + i,
                    () -> new TapeItem(id, new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON))));
        }
    }

    // ---- Ovos de spawn (criativo) ----
    public static final RegistryObject<Item> JACK_EGG = egg("jack_walten", ModEntities.JACK, 0x26262B, 0x8A1C1C);
    public static final RegistryObject<Item> ROSEMARY_EGG = egg("rosemary_walten", ModEntities.ROSEMARY, 0x6E1E2D, 0xC8A99A);
    public static final RegistryObject<Item> SOPHIE_EGG = egg("sophie_walten", ModEntities.SOPHIE, 0x7896C8, 0xCDB4A5);
    public static final RegistryObject<Item> EDD_EGG = egg("edd_walten", ModEntities.EDD, 0x325F46, 0x96AAA8);
    public static final RegistryObject<Item> MOLLY_EGG = egg("molly_walten", ModEntities.MOLLY, 0xBE7896, 0x9BACAA);
    public static final RegistryObject<Item> FELIX_EGG = egg("felix_kraken", ModEntities.FELIX, 0x464649, 0x969640);
    public static final RegistryObject<Item> SHADOW_EGG = egg("shadow_man", ModEntities.SHADOW_MAN, 0x050507, 0xFFFFFF);
    public static final RegistryObject<Item> BON_EGG = egg("bon", ModEntities.BON, 0x4664D2, 0xFF1E1E);
    public static final RegistryObject<Item> SHA_EGG = egg("sha", ModEntities.SHA, 0xE6E6EB, 0xDC96BE);
    public static final RegistryObject<Item> BOOZOO_EGG = egg("boozoo", ModEntities.BOOZOO, 0x965AC8, 0xF0C83C);
    public static final RegistryObject<Item> BANNY_EGG = egg("banny", ModEntities.BANNY, 0xEBA0BE, 0xFFFFFF);

    private static RegistryObject<Item> egg(String name, RegistryObject<EntityType<WaltenMob>> type, int background, int highlight) {
        return ITEMS.register(name + "_spawn_egg",
                () -> new ForgeSpawnEggItem(type, background, highlight, new Item.Properties()));
    }
}
