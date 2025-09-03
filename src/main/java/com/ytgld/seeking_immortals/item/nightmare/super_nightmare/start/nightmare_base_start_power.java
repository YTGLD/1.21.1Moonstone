package com.ytgld.seeking_immortals.item.nightmare.super_nightmare.start;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.seeking_immortals.Handler;
import com.ytgld.seeking_immortals.item.nightmare.extend.SuperNightmare;
import com.ytgld.seeking_immortals.item.nightmare.extend.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import top.theillusivec4.curios.api.SlotContext;

import java.util.ArrayList;
import java.util.List;

public class nightmare_base_start_power extends nightmare implements SuperNightmare {
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (Handler.hascurio(slotContext.entity(), this)) {
            slotContext.entity().getAttributes().addTransientAttributeModifiers(gets(slotContext));
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        slotContext.entity().getAttributes().removeAttributeModifiers(gets(slotContext));
    }

    public Multimap<Holder<Attribute>, AttributeModifier> gets(SlotContext slotContext) {
        Multimap<Holder<Attribute>, AttributeModifier> linkedHashMultimap = HashMultimap.create();
        LivingEntity living = slotContext.entity();
        List<Integer> integersHealth = new ArrayList<>();
        for (MobEffectInstance effect : living.getActiveEffects()) {
            if (effect != null
                    && effect.getEffect().value().isBeneficial()) {
                integersHealth.add(1);
            }
        }
        float att = 0;
        for (int i : integersHealth) {
            att += 2;
        }
        att /= 100;

        for (Holder<Attribute> attribute : BuiltInRegistries.ATTRIBUTE.asHolderIdMap()) {
            linkedHashMultimap.put(attribute, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()), att, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        }

        return linkedHashMultimap;
    }


    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.nightmare_base_start_power.tool.string").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_start_power.tool.string.1").withStyle(ChatFormatting.DARK_RED));
    }
}


