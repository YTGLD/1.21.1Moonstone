package com.moonstone.moonstonemod.item.nightmare.super_nightmare;

import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.init.moonstoneitem.AttReg;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.nightmare;
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
    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> attributeModifiers = super.getAttributeModifiers(slotContext, id, stack);
        attributeModifiers.put(Attributes.LUCK, new AttributeModifier(id, 10, AttributeModifier.Operation.ADD_VALUE));
        attributeModifiers.put(AttReg.alL_attack, new AttributeModifier(id, 0.2, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        attributeModifiers.put(AttReg.heal, new AttributeModifier(id, 0.5, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

        return attributeModifiers;

    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.nightmare_base_start_egg.tool.string").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_start_egg.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_start_egg.tool.string.2").withStyle(ChatFormatting.DARK_RED));
    }
}


