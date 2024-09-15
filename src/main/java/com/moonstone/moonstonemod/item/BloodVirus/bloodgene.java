package com.moonstone.moonstonemod.item.BloodVirus;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.init.DataReg;
import com.moonstone.moonstonemod.item.BloodVirus.ex.BloodViru;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class bloodgene extends BloodViru {


    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        slotContext.entity().getAttributes().addTransientAttributeModifiers(this.getAttributeModifiers( slotContext.entity(),stack));
       CompoundTag tag = stack.get(DataReg.tag);
        if (tag != null){
            tag.putString("ytgld", "ytgld");
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        slotContext.entity().getAttributes().removeAttributeModifiers(this.getAttributeModifiers( slotContext.entity(),stack));

    }

    public String blood = "bloodgene";

    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(LivingEntity player, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = HashMultimap.create();
       CompoundTag tag = stack.get(DataReg.tag);
        if (tag != null){
            tag.putString("ytgld", "ytgld");
            float as= tag.getFloat(blood) / 100;

            UUID uuid = UUID.fromString("0d077092-9045-3af8-b41e-a691f388e76a");
            modifierMultimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()),(-0.5)+as, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

            modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()),(-0.5)+as, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

        }


        return modifierMultimap;
    }
    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("item.bloodgene.tool.string").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.bloodgene.tool.string.1").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable(""));
            pTooltipComponents.add(Component.translatable("item.bloodgene.tool.string.2").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable(""));
            pTooltipComponents.add(Component.translatable("item.bloodgene.tool.string.3").withStyle(ChatFormatting.RED));

        } else {
            pTooltipComponents.add(Component.translatable("按下SHIFT查看").withStyle(ChatFormatting.DARK_RED));


        }
    }
}
