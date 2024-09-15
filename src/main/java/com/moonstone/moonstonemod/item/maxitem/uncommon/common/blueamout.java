package com.moonstone.moonstonemod.item.maxitem.uncommon.common;

import com.moonstone.moonstonemod.moonstoneitem.CommonItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class blueamout extends CommonItem {


    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        pTooltipComponents.add(Component.translatable("item.blueamout.tool.string").withStyle(ChatFormatting.GOLD));
        pTooltipComponents.add(Component.translatable("item.blueamout.tool.string.1").withStyle(ChatFormatting.GOLD));

    }
}
