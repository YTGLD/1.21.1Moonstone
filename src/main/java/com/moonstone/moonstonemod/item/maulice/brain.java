package com.moonstone.moonstonemod.item.maulice;

import com.moonstone.moonstonemod.init.DataReg;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.MLS;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class brain extends MLS {
    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
       CompoundTag tag = stack.get(DataReg.tag);
        if (tag != null){
            if (!tag.getBoolean("brain")) {
                tag.putBoolean("brain", true);
            }
        }else {
            stack.set(DataReg.tag,new CompoundTag());
        }
    }
    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        pTooltipComponents.add(Component.translatable("item.brain.tool.string").withStyle(ChatFormatting.DARK_GREEN));
    }
    public static final String brain ="brain_brain";
}
