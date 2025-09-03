package com.ytgld.seeking_immortals.item.nightmare.super_nightmare;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.seeking_immortals.Config;
import com.ytgld.seeking_immortals.Handler;
import com.ytgld.seeking_immortals.init.AttReg;
import com.ytgld.seeking_immortals.init.Items;
import com.ytgld.seeking_immortals.item.nightmare.extend.MainNightmare;
import com.ytgld.seeking_immortals.item.nightmare.extend.SuperNightmare;
import com.ytgld.seeking_immortals.item.nightmare.extend.nightmare;
import com.ytgld.seeking_immortals.item.nightmare.tip.AllTip;
import com.ytgld.seeking_immortals.item.nightmare.tip.ToolTip;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class nightmare_base_start extends nightmare implements SuperNightmare,  AllTip, MainNightmare {
    @Override
    public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            if (CuriosApi.getCuriosInventory(player).isPresent()
                    && CuriosApi.getCuriosInventory(player).get().isEquipped(Items.immortal.get())){
                return true;
            }
            if (player.isCreative()){
                return true;
            }
        }
        return false;
    }

    public static void damage(LivingIncomingDamageEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.nightmare_base_start.get())) {
                if (!player.getCooldowns().isOnCooldown(Items.nightmare_base_start.get())){
                    if (player.getHealth()>10) {
                        player.setHealth(player.getHealth() - 1);
                        player.getCooldowns().addCooldown(Items.nightmare_base_start.get(),10);
                    }
                }

            }
        }
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (Handler.hascurio(slotContext.entity(), this))
            slotContext.entity().getAttributes().addTransientAttributeModifiers(gets(slotContext));
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        slotContext.entity().getAttributes().removeAttributeModifiers(gets(slotContext));
    }

    public Multimap<Holder<Attribute>, AttributeModifier> gets(SlotContext slotContext) {
        Multimap<Holder<Attribute>, AttributeModifier> linkedHashMultimap = HashMultimap.create();
        linkedHashMultimap.put(Attributes.ARMOR, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage" +
                this.getDescriptionId()), -Config.SERVER.nightmare_base_start.getAsInt()/100f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

        linkedHashMultimap.put(AttReg.heal, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()), 0.5f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        return linkedHashMultimap;
    }


    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.nightmare_base_start.tool.string").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_start.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_start.tool.string.2").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_black_eye.tool.string.1").withStyle(ChatFormatting.RED));
        pTooltipComponents.add(Component.translatable("item.moonstone.nightmare_base_start_power").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.moonstone.nightmare_base_start_pod").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.moonstone.nightmare_base_start_egg").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.literal(""));

        pTooltipComponents.add(Component.translatable("item.nightmareeye.tool.string.2").withStyle(ChatFormatting.DARK_RED));

    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> linkedHashMultimap = com.google.common.collect.LinkedHashMultimap.create();
        CuriosApi
                .addSlotModifier(linkedHashMultimap, "nightmare", ResourceLocation.parse("nightmare_base_start" + "add_slot"
                ), 3, AttributeModifier.Operation.ADD_VALUE);
        return linkedHashMultimap;
    }

    @Override
    public @NotNull Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        return Optional.of(new ToolTip(this,stack));
    }

    @Override
    public Map<Integer, String> tooltip() {
        Map<Integer,String> map = new HashMap<>();
        map.put(1,"你被赐予了异常的恢复能力");
        map.put(2,"增加百分之五十治疗");
        return map;
    }

    @Override
    public Map<Integer, String> element(ItemStack stack) {
        Map<Integer,String> map = new HashMap<>();
        map.put(1,"你被赐予了异常的恢复能力");
        map.put(2,"增加百分之五十治疗");
        return map;
    }
}


