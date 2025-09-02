package com.moonstone.moonstonemod.item.blood;

import com.moonstone.moonstonemod.init.moonstoneitem.DataReg;
import com.moonstone.moonstonemod.init.moonstoneitem.i.Blood;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;

import java.text.DecimalFormat;
import java.util.List;

public class like_the_book extends Item implements Blood {
    public like_the_book() {
        super(new Properties().stacksTo(1).rarity(Rarity.UNCOMMON));
    }
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type) {
        tooltip.add(Component.literal(""));
        tooltip.add(Component.translatable("item.like_the_book.tool.string").withStyle(ChatFormatting.RED));
        tooltip.add(Component.translatable("item.like_the_book.tool.string.1").withStyle(ChatFormatting.RED));
        tooltip.add(Component.literal(""));
    }
}