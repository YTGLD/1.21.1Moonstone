package com.moonstone.moonstonemod.item.ectoplasm;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.moonstoneitem.IBattery;
import com.moonstone.moonstonemod.moonstoneitem.ectoplasm;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;

public class ectoplasmbattery extends ectoplasm implements IBattery {


    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = HashMultimap.create();
        modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()), 10, AttributeModifier.Operation.ADD_VALUE));
        modifierMultimap.put(Attributes.ARMOR, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()), 4, AttributeModifier.Operation.ADD_VALUE));
        modifierMultimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()), 0.05, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        modifierMultimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()), 0.05, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        return modifierMultimap;
    }
}


