package com.moonstone.moonstonemod.item.nanodoom;

import com.moonstone.moonstonemod.event.TextEvt;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.Doom;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class doomeye extends Doom implements TextEvt.Twelve{

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        pTooltipComponents.add(Component.translatable("item.doomeye.tool.string").withStyle(ChatFormatting.GOLD));
        pTooltipComponents.add(Component.translatable(""));
        pTooltipComponents.add(Component.translatable("item.doomeye.tool.string.1").withStyle(ChatFormatting.GOLD));

    }
}
