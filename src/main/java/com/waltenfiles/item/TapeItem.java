package com.waltenfiles.item;

import com.waltenfiles.lore.TapeLore;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class TapeItem extends Item {
    private final int tapeId;

    public TapeItem(int tapeId, Properties properties) {
        super(properties);
        this.tapeId = tapeId;
    }

    public int getTapeId() {
        return tapeId;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.literal(TapeLore.title(tapeId)).withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.translatable("tooltip.waltenfiles.fita_dica").withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
    }
}
