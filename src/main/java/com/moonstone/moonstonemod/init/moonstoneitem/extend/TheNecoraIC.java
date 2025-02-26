package com.moonstone.moonstonemod.init.moonstoneitem.extend;

import com.moonstone.moonstonemod.init.moonstoneitem.DataReg;
import com.moonstone.moonstonemod.init.moonstoneitem.i.Iplague;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class TheNecoraIC extends Item  implements Iplague, ICurioItem {
    public TheNecoraIC() {
        super(new Properties().stacksTo(1).rarity(Rarity.EPIC));
    }

    /*
     @Override
     public boolean canEquip(SlotContext slotContext, ItemStack stack) {
         if (slotContext.entity() instanceof Player player) {
             if (Handler.hascurio(player, stack.getItem())){
                 return false;
             }
         }
         return true;
     }

      */
    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        ICurioItem.super.onEquip(slotContext, prevStack, stack);
        CompoundTag tag = stack.get(DataReg.tag);
        if (tag != null){
            tag.putString("ytgld", "ytgld");
        }else {
            stack.set(DataReg.tag,new CompoundTag());
        }
    }

    @NotNull
    @Override
    public ICurio.DropRule getDropRule(SlotContext slotContext, DamageSource source, boolean recentlyHit,
                               ItemStack stack) {
        return ICurio.DropRule.ALWAYS_KEEP;
    }
}
