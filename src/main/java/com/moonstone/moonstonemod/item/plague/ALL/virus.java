package com.moonstone.moonstonemod.item.plague.ALL;

import com.moonstone.moonstonemod.init.DataReg;
import com.moonstone.moonstonemod.moonstoneitem.extend.medIC;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

public class virus extends medIC implements ICurioItem {

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
       CompoundTag tag = stack.get(DataReg.tag);
        if (tag != null){
            tag.putString("ytgld", "ytgld");
        }else {
            stack.set(DataReg.tag,new CompoundTag());
        }
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);

        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("item.virus.tool.string").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.virus.tool.string.1").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.virus.tool.string.2").withStyle(ChatFormatting.RED));
        } else {
            pTooltipComponents.add(Component.translatable("Shift").withStyle(ChatFormatting.DARK_RED));
        }
        pTooltipComponents.add(Component.translatable(""));

    }
}