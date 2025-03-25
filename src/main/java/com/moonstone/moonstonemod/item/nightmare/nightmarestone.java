package com.moonstone.moonstonemod.item.nightmare;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.event.old.AllEvent;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import top.theillusivec4.curios.api.SlotContext;

import java.util.Collection;
import java.util.List;

public class nightmarestone extends nightmare implements Nightmare{

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            if (Handler.hascurio(player, Items.nightmareeye.get())) {
                aDouble = AllEvent.EffectInstance(player);
                if (!player.level().isClientSide && player.tickCount % 20 == 0) {
                    player.getAttributes().addTransientAttributeModifiers(un_un_pla(player));
                    Collection<MobEffectInstance> collection = player.getActiveEffects();
                    for (MobEffectInstance mobEffectInstance : collection) {
                        MobEffect mobEffect = mobEffectInstance.getEffect().value();
                        if (!mobEffect.isBeneficial()) {
                            int lvl = mobEffectInstance.getAmplifier();
                            int time = mobEffectInstance.getDuration();
                            Holder<MobEffect> effect = mobEffectInstance.getEffect().getDelegate();

                            player.addEffect(new MobEffectInstance(effect, time + 10, lvl));

                        }
                    }
                }
            }
        }
    }
    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        pTooltipComponents.add(Component.translatable(""));

        if (Screen.hasShiftDown()){
            pTooltipComponents.add(Component.translatable("item.nightmarestone.tool.string").withStyle(ChatFormatting.DARK_RED));
            pTooltipComponents.add(Component.translatable("item.nightmarestone.tool.string.1").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.nightmarestone.tool.string.2").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.nightmarestone.tool.string.3").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable(""));
            pTooltipComponents.add(Component.translatable("item.nightmarestone.tool.string.7").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable(""));
            pTooltipComponents.add(Component.translatable("item.nightmarestone.tool.string.8").withStyle(ChatFormatting.RED));

        }else {
            pTooltipComponents.add(Component.translatable("· [SHIFT]").withStyle(ChatFormatting.DARK_RED));
        }
        pTooltipComponents.add(Component.translatable(""));
        pTooltipComponents.add(Component.translatable("item.nightmarestone.tool.string.9").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmarestone.tool.string.10").withStyle(ChatFormatting.DARK_RED));
    }
    //c98b3962-3ea9-47f8-820d-134ce2691af0
    public Multimap<Holder<Attribute>, AttributeModifier> un_un_pla(Player player) {
        Multimap<Holder<Attribute>, AttributeModifier>modifierMultimap = HashMultimap.create();


        modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()),AllEvent. EffectInstance(player)/25, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        modifierMultimap.put(Attributes.ARMOR, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()), AllEvent. EffectInstance(player)/25, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        modifierMultimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()), AllEvent. EffectInstance(player)/25, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

        return modifierMultimap;
    }
    public static double aDouble;
}
