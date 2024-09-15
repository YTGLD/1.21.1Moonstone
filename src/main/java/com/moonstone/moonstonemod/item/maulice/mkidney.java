package com.moonstone.moonstonemod.item.maulice;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.init.DataReg;
import com.moonstone.moonstonemod.moonstoneitem.MLS;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class mkidney extends MLS {
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
       CompoundTag tag = stack.get(DataReg.tag);
        if (tag != null){
            if (!tag.getBoolean("brain")) {
                tag.putBoolean("mkidney", true);
            }
        }else {
            stack.set(DataReg.tag,new CompoundTag());
        }
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> linkedHashMultimap = HashMultimap.create();
        CuriosApi
                .addSlotModifier(linkedHashMultimap, "charm", ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()), 1, AttributeModifier.Operation.ADD_VALUE);
        return linkedHashMultimap;
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);

        pTooltipComponents.add(Component.translatable("item.mkidney.tool.string").withStyle(ChatFormatting.DARK_GREEN));
        pTooltipComponents.add(Component.translatable("item.mkidney.tool.string.1").withStyle(ChatFormatting.DARK_GREEN));
        pTooltipComponents.add(Component.translatable("item.mkidney.tool.string.2").withStyle(ChatFormatting.DARK_GREEN));
        pTooltipComponents.add(Component.translatable("item.mkidney.tool.string.3").withStyle(ChatFormatting.DARK_GREEN));
        pTooltipComponents.add(Component.translatable("item.mkidney.tool.string.4").withStyle(ChatFormatting.DARK_GREEN));

    }
}
