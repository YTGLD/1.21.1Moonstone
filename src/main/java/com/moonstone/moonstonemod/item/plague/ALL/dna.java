package com.moonstone.moonstonemod.item.plague.ALL;

import com.moonstone.moonstonemod.init.DataReg;
import com.moonstone.moonstonemod.moonstoneitem.Iplague;
import com.moonstone.moonstonemod.moonstoneitem.extend.medIC;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class dna extends medIC implements ICurioItem , Iplague {

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
       CompoundTag tag = stack.get(DataReg.tag);
        if (tag != null){
            tag.putString("ytgld", "ytgld");
        }else {
            stack.set(DataReg.tag,new CompoundTag());
        }
    }
}