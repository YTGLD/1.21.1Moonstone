package com.moonstone.moonstonemod.item.maulice;

import com.moonstone.moonstonemod.moonstoneitem.MLS;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class mring extends MLS {

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        pTooltipComponents.add(Component.translatable("item.mring.tool.string").withStyle(ChatFormatting.DARK_GREEN));
        pTooltipComponents.add(Component.translatable("item.mring.tool.string.1").withStyle(ChatFormatting.DARK_GREEN));

    }

}


