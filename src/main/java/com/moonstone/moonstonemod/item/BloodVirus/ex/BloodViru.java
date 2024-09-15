package com.moonstone.moonstonemod.item.BloodVirus.ex;

import com.moonstone.moonstonemod.init.DataReg;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.CustomData;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class BloodViru extends Item implements ICurioItem {
    public BloodViru() {
        super(new Properties().stacksTo(1).rarity(Rarity.UNCOMMON).component(DataComponents.CUSTOM_DATA,CustomData.EMPTY));
    }
    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
       CompoundTag tag = stack.get(DataReg.tag);
        if (tag != null){
            tag.putString("ytgld", "ytgld");
        }else {
            stack.set(DataReg.tag,new CompoundTag());
        }
    }

    /*
    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        return !Handler.hascurio(slotContext.entity(),stack.getItem());
    }


     */
    @NotNull
    @Override
    public ICurio.DropRule getDropRule(SlotContext slotContext, DamageSource source, int lootingLevel, boolean recentlyHit, ItemStack stack) {
        return ICurio.DropRule.ALWAYS_KEEP;
    }
}
