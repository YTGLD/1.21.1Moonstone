package com.ytgld.seeking_immortals.item.nightmare.super_nightmare.stone;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.seeking_immortals.Handler;
import com.ytgld.seeking_immortals.init.Items;
import com.ytgld.seeking_immortals.item.nightmare.extend.SuperNightmare;
import com.ytgld.seeking_immortals.item.nightmare.extend.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class end_bone  extends nightmare implements SuperNightmare {
    public static void hurts(LivingIncomingDamageEvent event){
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.end_bone.get())) {
                if (player.getHealth() >= player.getMaxHealth()){
                    if ( event.getSource().getEntity() instanceof LivingEntity living) {
                        living.hurt(living.damageSources().dryOut(), event.getAmount() * 0.7f);
                    }
                    event.setAmount(event.getAmount()*0.2f);
                }
            }
        }
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (!slotContext.entity().level().isClientSide){
            slotContext.entity().getAttributes().addTransientAttributeModifiers(getAttributeModifiers(slotContext.entity()));
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (!slotContext.entity().level().isClientSide){
            slotContext.entity().getAttributes().removeAttributeModifiers(getAttributeModifiers(slotContext.entity()));
        }
    }
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.end_bone.tool.string").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.end_bone.tool.string.1").withStyle(ChatFormatting.DARK_RED));
    }
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(LivingEntity living) {
        Multimap<Holder<Attribute>, AttributeModifier> attributeModifiers = HashMultimap.create();
        float s = 0;
        if (living.getHealth() >= living.getMaxHealth()){
            s -= 0.5f;
        }else {
            s = 0;
        }
        attributeModifiers.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(ResourceLocation.parse(this.getDescriptionId()),
                s, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

        return attributeModifiers;

    }

}
