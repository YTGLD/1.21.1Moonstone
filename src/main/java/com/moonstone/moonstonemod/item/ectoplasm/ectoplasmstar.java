package com.moonstone.moonstonemod.item.ectoplasm;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.ectoplasm;
import net.minecraft.ChatFormatting;
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

public class ectoplasmstar extends ectoplasm {

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            if (!player.level().isClientSide) {
                slotContext.entity().getAttributes().addTransientAttributeModifiers(att());
                slotContext.entity().getAttributes().addTransientAttributeModifiers(att2(player));
            }
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            slotContext.entity().getAttributes().removeAttributeModifiers(att());
            slotContext.entity().getAttributes().removeAttributeModifiers(att2(player));
        }
    }

    public Multimap<Holder<Attribute>, AttributeModifier> att(){
        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = HashMultimap.create();
        int s = 20;
        modifierMultimap.put(Attributes.LUCK, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()), s, AttributeModifier.Operation.ADD_VALUE));
        return modifierMultimap;
    }
    public Multimap<Holder<Attribute>, AttributeModifier> att2(Player player){
        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = HashMultimap.create();
        int max = 100;
        float s = player.getLuck();
        if (s>max){
            s=max;
        }
        s /= 100;
        modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()), s/2, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        modifierMultimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()), s, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        return modifierMultimap;
    }
    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        pTooltipComponents.add(Component.translatable("item.ectoplasmstar.tool.string").withStyle(ChatFormatting.GOLD));
        pTooltipComponents.add(Component.translatable(""));
        pTooltipComponents.add(Component.translatable("item.ectoplasmstar.tool.string.1").withStyle(ChatFormatting.GOLD));
        pTooltipComponents.add(Component.translatable(""));
    }
}


