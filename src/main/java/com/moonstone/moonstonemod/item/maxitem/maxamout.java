package com.moonstone.moonstonemod.item.maxitem;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.event.old.TextEvt;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.UnCommonItem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.common.NeoForgeMod;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class maxamout extends UnCommonItem implements TextEvt.Twelve{
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            if (player.hasEffect(MobEffects.DIG_SLOWDOWN)){
                player.removeEffect(MobEffects.DIG_SLOWDOWN);
            }
        }
        if (slotContext.entity() instanceof Player player) {
        }
    }


    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            player.getAttributes().addTransientAttributeModifiers(swim(player, stack));
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            player.getAttributes().removeAttributeModifiers(swim(player, stack));
        }
        if (slotContext.entity() instanceof Player player) {
        }
    }
    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        if (Screen.hasShiftDown()) {

            pTooltipComponents.add(Component.translatable(""));
            pTooltipComponents.add(Component.translatable("item.maxamout.tool.string").withStyle(ChatFormatting.GOLD));
            pTooltipComponents.add(Component.translatable("item.maxamout.tool.string.1").withStyle(ChatFormatting.GOLD));
            pTooltipComponents.add(Component.translatable(""));
            pTooltipComponents.add(Component.translatable("item.maxamout.tool.string.2").withStyle(ChatFormatting.GOLD));
            pTooltipComponents.add(Component.translatable("item.maxamout.tool.string.3").withStyle(ChatFormatting.GOLD));
            pTooltipComponents.add(Component.translatable(""));
            pTooltipComponents.add(Component.translatable("item.maxamout.tool.string.6").withStyle(ChatFormatting.GOLD));
            pTooltipComponents.add(Component.translatable("item.maxamout.tool.string.7").withStyle(ChatFormatting.GOLD));
            pTooltipComponents.add(Component.translatable("item.maxamout.tool.string.8").withStyle(ChatFormatting.GOLD));
        }else {
            pTooltipComponents.add(Component.translatable("SHIFT").withStyle(ChatFormatting.GOLD,ChatFormatting.BOLD));


        }
    }
    public Multimap<Holder<Attribute>, AttributeModifier> swim(Player player, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = HashMultimap.create();
        modifierMultimap.put(NeoForgeMod.SWIM_SPEED, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()), 0.75, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        return modifierMultimap;
    }
}


