package com.moonstone.moonstonemod.item.nightmare;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.DataReg;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.moonstoneitem.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
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
import java.util.UUID;

public class nightmareeye extends nightmare {
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
       CompoundTag tag = stack.get(DataReg.tag);
        if (tag != null){
            tag.putString("TestTag", "TestTag");
            if (slotContext.entity() instanceof Player player) {
                player.getAttributes().addTransientAttributeModifiers(un_un_pla(player, stack));
            }
        }
    }
    @Override
    public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            if (player.isCreative()){
                return true;
            }
        }
        return false;
    }
    public Multimap<Holder<Attribute>, AttributeModifier> un_un_pla(Player player, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = HashMultimap.create();
        UUID uuid = UUID.fromString("83f9fb4e-5c3f-3b02-a19a-70f2fa258dfa");


        float s = 0.25f;
        if (Handler.hascurio(player, com.moonstone.moonstonemod.init.Items.nightmarecharm.get())){
            s -= 0.15f;
        }
        if (Handler.hascurio(player, com.moonstone.moonstonemod.init.Items.nightmareanchor.get())){
            s -= 0.05f;
        }
        if (Handler.hascurio(player, com.moonstone.moonstonemod.init.Items.nightmarerotten.get())){
            float a = BuiltInRegistries.ATTRIBUTE.size();
            a /= 100;
            a *= 1.1F;
            s += a;
        }

        if (Handler.hascurio(player, Items.nightmare_orb.get())){
            if (player.getHealth()<= player.getMaxHealth() / 3) {
                s = -s;
            }
        }
        if (Handler.hascurio(player,Items.nightmare_head.get())) {
           CompoundTag tag = stack.get(DataReg.tag);
            if (tag != null){
                if (tag != null) {
                    float d = tag.getInt(nightmare_head.die);
                    d /= 100;
                    float ds = d;
                    ds = 1 - ds;
                    s = s * (1 - ds);
                }
            }else {
                stack.set(DataReg.tag,new CompoundTag());
            }
        }

        modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()), -s, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        modifierMultimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()), -s, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        modifierMultimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()), -s, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        modifierMultimap.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()), -s, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        modifierMultimap.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()), -s, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()), -s, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));return modifierMultimap;
    }
    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        pTooltipComponents.add(Component.translatable(""));
        pTooltipComponents.add(Component.translatable("item.nightmareeye.tool.string").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable(""));
        pTooltipComponents.add(Component.translatable("item.nightmareeye.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmareeye.tool.string.2").withStyle(ChatFormatting.DARK_RED));


    }
}
