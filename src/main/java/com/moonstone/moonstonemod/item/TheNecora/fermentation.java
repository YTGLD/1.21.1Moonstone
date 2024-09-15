package com.moonstone.moonstonemod.item.TheNecora;

import com.moonstone.moonstonemod.moonstoneitem.extend.TheNecoraIC;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class fermentation extends TheNecoraIC {
    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable(""));
            pTooltipComponents.add(Component.translatable("item.fermentation.tool.string").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.fermentation.tool.string.1").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.fermentation.tool.string.2").withStyle(ChatFormatting.RED));
        }else {
            pTooltipComponents.add(Component.translatable(""));
            pTooltipComponents.add(Component.translatable("-[SHIFT]").withStyle(ChatFormatting.DARK_RED));
            pTooltipComponents.add(Component.translatable("item.fermentation.tool.string.3").withStyle(ChatFormatting.RED));
        }
    }
}



