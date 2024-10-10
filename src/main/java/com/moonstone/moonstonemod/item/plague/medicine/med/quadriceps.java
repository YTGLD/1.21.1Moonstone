package com.moonstone.moonstonemod.item.plague.medicine.med;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Config;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.medIC;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.medicinebox;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.Holder;
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

public class  quadriceps extends medIC {



    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            if (player.isSprinting()) {
                player.getAttributes().addTransientAttributeModifiers(aaa(player, stack));
            }else {
                player.getAttributes().removeAttributeModifiers(aaa(player, stack));
            }
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            player.getAttributes().removeAttributeModifiers(aaa(player, stack));
        }
    }

    public Multimap<Holder<Attribute>, AttributeModifier> aaa(Player player , ItemStack stack){
        Multimap<Holder<Attribute>, AttributeModifier>linkedHashMultimap = HashMultimap.create();
        linkedHashMultimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()), Config.SERVER.quadriceps_speed.get(), AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        linkedHashMultimap.put(Attributes.SAFE_FALL_DISTANCE, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()),3, AttributeModifier.Operation.ADD_VALUE));
        return linkedHashMultimap;
    }


    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable(""));
            pTooltipComponents.add(Component.translatable("item.quadriceps.tool.string").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable(""));
            pTooltipComponents.add(Component.translatable("item.quadriceps.tool.string.1").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.quadriceps.tool.string.2").withStyle(ChatFormatting.RED));
        } else {
            pTooltipComponents.add(Component.literal("SHIFT").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
        }
        pTooltipComponents.add(Component.translatable(""));
        pTooltipComponents.add(Component.translatable(""+ medicinebox.do_jump).withStyle(ChatFormatting.RED));
        pTooltipComponents.add(Component.translatable("item.quadriceps.tool.string.3").withStyle(ChatFormatting.RED));

    }

}
