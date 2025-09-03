package com.ytgld.seeking_immortals.item.nightmare.super_nightmare;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.seeking_immortals.Config;
import com.ytgld.seeking_immortals.Handler;
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
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.storage.loot.LootContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.*;

public class nightmare_base_fool extends nightmare implements SuperNightmare ,  AllTip, MainNightmare {
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


    @Override
    public int getLootingLevel(SlotContext slotContext, @Nullable LootContext lootContext, ItemStack stack) {
        return 2;
    }

    @Override
    public int getFortuneLevel(SlotContext slotContext, LootContext lootContext, ItemStack stack) {
        return 2;
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        slotContext.entity().getAttributes().addTransientAttributeModifiers(gets(slotContext));
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        slotContext.entity().getAttributes().removeAttributeModifiers(gets(slotContext));
    }

    public Multimap<Holder<Attribute>, AttributeModifier> gets(SlotContext slotContext) {
        Multimap<Holder<Attribute>, AttributeModifier> linkedHashMultimap = HashMultimap.create();
        LivingEntity living = slotContext.entity();

        {
            List<Integer> integersATTACK_DAMAGE = new ArrayList<>();
            CuriosApi.getCuriosInventory(living).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (!stack.isEmpty()) {
                            integersATTACK_DAMAGE.add(1);
                        }
                    }
                }
            });
            float dam = 0;
            for (int ignored : integersATTACK_DAMAGE) {
                dam++;
            }
            int j = 5;
            if (Handler.hascurio(living, Items.nightmare_base_fool_soul.get())) {
                j += 9;
            }
            dam -= j;
            if (dam < 0) {
                dam = 0;
            }
            dam /= 100f;
            dam *= 2;

            dam = -dam;
            if (dam <= -Config.SERVER.nightmare_base_fool.getAsDouble()) {
                dam = (float) -Config.SERVER.nightmare_base_fool.getAsDouble();
            }
            linkedHashMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()), dam, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        }
        {
            List<Integer> integersHealth = new ArrayList<>();
            CuriosApi.getCuriosInventory(living).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (!stack.isEmpty()) {
                            integersHealth.add(1);
                        }
                    }
                }
            });
            float health = 0;
            for (int ignored : integersHealth) {
                health++;
            }
            int j = 2;
            if (Handler.hascurio(living, Items.nightmare_base_fool_soul.get())) {
                j += 7;
            }
            health -= j;
            if (health < 0) {
                health = 0;
            }
            health /= 100f;
            health *= 1;
            health = -health;
            if (health <= -Config.SERVER.nightmare_base_fool.getAsDouble()) {
                health = (float) -Config.SERVER.nightmare_base_fool.getAsDouble();
            }
            linkedHashMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()), health, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        }


        return linkedHashMultimap;
    }


    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.nightmare_base_fool.tool.string").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_fool.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_fool.tool.string.2").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_black_eye.tool.string.1").withStyle(ChatFormatting.RED));
        pTooltipComponents.add(Component.translatable("item.moonstone.nightmare_base_fool_betray").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.moonstone.nightmare_base_fool_bone").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.moonstone.nightmare_base_fool_soul").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.moonstone.apple").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.literal(""));

        pTooltipComponents.add(Component.translatable("item.nightmareeye.tool.string.2").withStyle(ChatFormatting.DARK_RED));

    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> linkedHashMultimap = com.google.common.collect.LinkedHashMultimap.create();
        CuriosApi
                .addSlotModifier(linkedHashMultimap, "nightmare", ResourceLocation.parse("nightmare_base_fool" + "add_slot"
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
        map.put(1,"你似乎得到了两级的时运加成");
        map.put(2,"你似乎得到了两级的抢夺加成");
        return map;
    }

    @Override
    public Map<Integer, String> element(ItemStack stack) {
        Map<Integer,String> map = new HashMap<>();
        map.put(1,"你似乎得到了2级的时运加成");
        map.put(2,"你似乎得到了2级的抢夺加成");
        return map;
    }
}

