package com.moonstone.moonstonemod.moonstoneitem;

import com.moonstone.moonstonemod.init.DataReg;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class BloodViru extends Item implements ICurioItem {
    public BloodViru() {
        super(new Properties().stacksTo(1).rarity(Rarity.UNCOMMON));
    }
    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        if (stack.get(DataReg.tag)==null){
            stack.set(DataReg.tag,new CompoundTag());
        }
        if (stack.get(DataReg.tag)!=null){
            stack.get(DataReg.tag).putString("Ytgld","Ytgld");
        }

    }

    @NotNull
    @Override
    public ICurio.DropRule getDropRule(SlotContext slotContext, DamageSource source, int lootingLevel, boolean recentlyHit, ItemStack stack) {
        return ICurio.DropRule.ALWAYS_KEEP;
    }
}
