package com.moonstone.moonstonemod.item.plague.medicine.med;

import com.moonstone.moonstonemod.moonstoneitem.extend.medIC;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class polyphagia extends medIC {
    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        pTooltipComponents.add(Component.translatable("item.polyphagia.tool.string").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("item.polyphagia.tool.string.1").withStyle(ChatFormatting.RED));
        } else {
            pTooltipComponents.add(Component.translatable(""));
            pTooltipComponents.add(Component.literal("SHIFT").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
        }
    }

}

