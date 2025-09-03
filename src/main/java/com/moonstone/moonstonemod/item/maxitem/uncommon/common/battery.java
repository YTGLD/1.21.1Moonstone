package com.moonstone.moonstonemod.item.maxitem.uncommon.common;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.seeking_immortals.Config;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.CommonItem;
import com.moonstone.moonstonemod.init.moonstoneitem.i.IBattery;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

public class battery extends CommonItem implements IBattery {

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        slotContext.entity().getAttributes().addTransientAttributeModifiers(getAttributeModifiers(stack));
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        slotContext.entity().getAttributes().removeAttributeModifiers(getAttributeModifiers(stack));
    }

    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = HashMultimap.create();
        modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()),  0.05, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        modifierMultimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()), 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        modifierMultimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()), Config.SERVER.battery_speed.get(), AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        return modifierMultimap;
    }


}
