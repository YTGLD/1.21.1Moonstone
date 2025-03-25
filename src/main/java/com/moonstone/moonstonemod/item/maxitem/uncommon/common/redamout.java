package com.moonstone.moonstonemod.item.maxitem.uncommon.common;

import com.moonstone.moonstonemod.event.old.TextEvt;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.CommonItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class redamout extends CommonItem implements TextEvt.Twelve{



    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        pTooltipComponents.add(Component.translatable("item.redamout.tool.string").withStyle(ChatFormatting.GOLD));
        pTooltipComponents.add(Component.translatable("item.redamout.tool.string.1").withStyle(ChatFormatting.GOLD));
    }
}
