package com.moonstone.moonstonemod.item.nanodoom.buyme;

import com.moonstone.moonstonemod.init.moonstoneitem.extend.Perhaps;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class wind_and_rain extends Perhaps {
    public static final String wind = "WindAndRain";


    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        pTooltipComponents.add(Component.translatable(""));
        pTooltipComponents.add(Component.translatable("item.wind_and_rain.tool.string").withStyle(ChatFormatting.GOLD));
        pTooltipComponents.add(Component.translatable("item.wind_and_rain.tool.string.1").withStyle(ChatFormatting.GOLD));
        pTooltipComponents.add(Component.translatable("item.wind_and_rain.tool.string.2").withStyle(ChatFormatting.GOLD));
        pTooltipComponents.add(Component.translatable("item.wind_and_rain.tool.string.3").withStyle(ChatFormatting.GOLD));
        pTooltipComponents.add(Component.translatable(""));
        pTooltipComponents.add(Component.translatable("item.wind_and_rain.tool.string.4").withStyle(ChatFormatting.RED));
    }
}
