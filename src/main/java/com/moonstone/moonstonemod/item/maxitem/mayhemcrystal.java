package com.moonstone.moonstonemod.item.maxitem;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.AttReg;
import com.moonstone.moonstonemod.init.moonstoneitem.DataReg;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.UnCommonItem;
import com.moonstone.moonstonemod.init.moonstoneitem.i.Iwar;
import com.moonstone.moonstonemod.item.nightmare.super_nightmare.nightmare_base_stone_meet;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

public class mayhemcrystal extends UnCommonItem  implements Iwar {


    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier>modifierMultimap = HashMultimap.create();
        float s  = 0.3f;
        if (Handler.hascurio(slotContext.entity(), Items.nightmare_base_stone_meet.get())){
            s*=1.5f;
        }
        modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(id, s, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        return modifierMultimap;
    }
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            if (Handler.hascurio(player, Items.nightmare_base_stone_meet.get())){
                if (stack.get(DataReg.tag) != null) {
                    stack.get(DataReg.tag).putBoolean(nightmare_base_stone_meet.curse,true);
                }else {
                    stack.set(DataReg.tag,new CompoundTag());
                }
            }
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            if (!Handler.hascurio(player, Items.nightmare_base_stone_meet.get())){
                if (stack.get(DataReg.tag) != null) {
                    stack.get(DataReg.tag).putBoolean(nightmare_base_stone_meet.curse,false);
                }else {
                    stack.set(DataReg.tag,new CompoundTag());
                }
            }
        }
    }
}

