package com.moonstone.moonstonemod.item.maxitem.uncommon;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.event.TextEvt;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.UnCommonItem;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class evilcandle extends UnCommonItem implements TextEvt.Twelve{
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        slotContext.entity().clearFire();
        slotContext.entity().getAttributes().addTransientAttributeModifiers(getAttributeModifiers(slotContext));
    }


    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);

        pTooltipComponents.add(Component.translatable("item.evilcandle.tool.string").withStyle(ChatFormatting.GOLD));
        pTooltipComponents.add(Component.translatable(""));
        pTooltipComponents.add(Component.translatable("item.evilcandle.tool.string.1").withStyle(ChatFormatting.GOLD));
        pTooltipComponents.add(Component.translatable("item.evilcandle.tool.string.2").withStyle(ChatFormatting.GOLD));

    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        slotContext.entity().getAttributes().removeAttributeModifiers(getAttributeModifiers(slotContext));
    }

    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext) {
        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = HashMultimap.create();
        float a  = 0;
        if (slotContext.entity().isInLava()){
            a = 0.5f;
        }
        modifierMultimap.put(Attributes.JUMP_STRENGTH, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()),  a, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
         return modifierMultimap;
    }

}
