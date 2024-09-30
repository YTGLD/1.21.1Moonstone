package com.moonstone.moonstonemod.item.BloodVirus;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.event.AllEvent;
import com.moonstone.moonstonemod.init.AttReg;
import com.moonstone.moonstonemod.init.DataReg;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.item.BloodVirus.ex.BloodViru;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class ragegene  extends BloodViru {
    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("item.ragegene.tool.string").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.ragegene.tool.string.1").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.ragegene.tool.string.2").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable(""));
            pTooltipComponents.add(Component.translatable("item.ragegene.tool.string.3").withStyle(ChatFormatting.RED));

        } else {
            pTooltipComponents.add(Component.translatable("Shift").withStyle(ChatFormatting.DARK_RED));
        }
    }
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            player.getAttributes().addTransientAttributeModifiers(this.Head(stack));
            CompoundTag tag = stack.get(DataReg.tag);

            if (tag == null) {
                stack.set(DataReg.tag,new CompoundTag());
            }

        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player)
            slotContext.entity().getAttributes().removeAttributeModifiers(this.Head(stack));
    }

    private Multimap<Holder<Attribute>, AttributeModifier> Head(ItemStack stack){
        Multimap<Holder<Attribute>, AttributeModifier> multimap = HashMultimap.create();
        CompoundTag tag = stack.get(DataReg.tag);
        float s = 0;
        if (tag != null) {
            s=((float) tag.getInt(AllEvent.rage) / 100);
        }


        multimap.put(AttReg.alL_attack, new AttributeModifier(
                ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()),
                s,
                AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

        return multimap;
    }
}
