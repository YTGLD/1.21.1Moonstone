package com.moonstone.moonstonemod.item.maxitem;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.event.TextEvt;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.DataReg;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.UnCommonItem;
import com.moonstone.moonstonemod.item.nightmare.super_nightmare.nightmare_base_stone_meet;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
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
            if (Handler.hascurio(player, Items.nightmare_base_stone_meet.get())){
                if (stack.get(DataReg.tag) != null) {
                    stack.get(DataReg.tag).putBoolean(nightmare_base_stone_meet.curse,true);
                }else {
                    stack.set(DataReg.tag,new CompoundTag());
                }
            }
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
            if (!Handler.hascurio(player, Items.nightmare_base_stone_meet.get())){
                if (stack.get(DataReg.tag) != null) {
                    stack.get(DataReg.tag).putBoolean(nightmare_base_stone_meet.curse,false);
                }else {
                    stack.set(DataReg.tag,new CompoundTag());
                }
            }
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
            pTooltipComponents.add(Component.translatable("item.maxamout.tool.string.4").withStyle(ChatFormatting.GOLD));
            pTooltipComponents.add(Component.translatable("item.maxamout.tool.string.5").withStyle(ChatFormatting.GOLD));
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


