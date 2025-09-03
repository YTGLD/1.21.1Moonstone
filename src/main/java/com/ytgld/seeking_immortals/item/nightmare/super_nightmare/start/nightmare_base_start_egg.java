package com.ytgld.seeking_immortals.item.nightmare.super_nightmare.start;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.seeking_immortals.Handler;
import com.ytgld.seeking_immortals.init.AttReg;
import com.ytgld.seeking_immortals.item.nightmare.extend.SuperNightmare;
import com.ytgld.seeking_immortals.item.nightmare.extend.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class nightmare_base_start_egg extends nightmare implements SuperNightmare {
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers() {
        Multimap<Holder<Attribute>, AttributeModifier> attributeModifiers = HashMultimap.create();
        attributeModifiers.put(Attributes.LUCK, new AttributeModifier(ResourceLocation.parse(this.getDescriptionId()), 10, AttributeModifier.Operation.ADD_VALUE));
        attributeModifiers.put(AttReg.alL_attack, new AttributeModifier(ResourceLocation.parse(this.getDescriptionId()), 0.2, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        attributeModifiers.put(AttReg.heal, new AttributeModifier(ResourceLocation.parse(this.getDescriptionId()), 0.5, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

        return attributeModifiers;

    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        if (Handler.hascurio(slotContext.entity(), this)) {
            slotContext.entity().getAttributes().addTransientAttributeModifiers(getAttributeModifiers());
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        slotContext.entity().getAttributes().removeAttributeModifiers(getAttributeModifiers());

    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.nightmare_base_start_egg.tool.string").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_start_egg.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_start_egg.tool.string.2").withStyle(ChatFormatting.DARK_RED));
    }
}


