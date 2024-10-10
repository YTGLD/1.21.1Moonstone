package com.moonstone.moonstonemod.item.plague.medicine.med;

import com.moonstone.moonstonemod.init.moonstoneitem.extend.medIC;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.medicinebox;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class masticatory extends medIC {
    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable(""));
            pTooltipComponents.add(Component.translatable("item.masticatory.tool.string").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable(""));
            pTooltipComponents.add(Component.translatable("item.masticatory.tool.string.1").withStyle(ChatFormatting.RED));
        } else {
            pTooltipComponents.add(Component.translatable(""));
            pTooltipComponents.add(Component.literal("SHIFT").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
        }

        pTooltipComponents.add(Component.translatable(""));
        pTooltipComponents.add(Component.translatable(""+ medicinebox.do_apple).withStyle(ChatFormatting.RED));
        pTooltipComponents.add(Component.translatable("item.masticatory.tool.string.2").withStyle(ChatFormatting.RED));

    }

}

