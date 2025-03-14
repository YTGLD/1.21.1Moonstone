package com.moonstone.moonstonemod.item.nightmare;

import com.moonstone.moonstonemod.init.moonstoneitem.extend.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class nightmaremoai extends nightmare implements Nightmare{
    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        pTooltipComponents.add(Component.translatable(""));
        if (Screen.hasShiftDown()){
            pTooltipComponents.add(Component.translatable("item.nightmaremoai.tool.string").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable(""));
        }else {
            pTooltipComponents.add(Component.translatable("· [SHIFT]").withStyle(ChatFormatting.DARK_RED));
        }
        pTooltipComponents.add(Component.translatable(""));
        pTooltipComponents.add(Component.translatable("item.nightmaremoai.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmaremoai.tool.string.2").withStyle(ChatFormatting.DARK_RED));
    }
}
