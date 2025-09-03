package com.ytgld.seeking_immortals.item.nightmare.super_nightmare;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.seeking_immortals.Config;
import com.ytgld.seeking_immortals.Handler;
import com.ytgld.seeking_immortals.event.old.AdvancementEvt;
import com.ytgld.seeking_immortals.init.DataReg;
import com.ytgld.seeking_immortals.init.Effects;
import com.ytgld.seeking_immortals.init.Items;
import com.ytgld.seeking_immortals.item.nightmare.extend.MainNightmare;
import com.ytgld.seeking_immortals.item.nightmare.extend.SuperNightmare;
import com.ytgld.seeking_immortals.item.nightmare.extend.nightmare;
import com.ytgld.seeking_immortals.item.nightmare.tip.AllTip;
import com.ytgld.seeking_immortals.item.nightmare.tip.ToolTip;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class nightmare_base_reversal extends nightmare implements SuperNightmare,  AllTip, MainNightmare {


    public static final String att = "Attrib";


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

    public static void LivingDeathEvent(LivingDeathEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.nightmare_base_reversal.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.nightmare_base_reversal.get())) {
                                if (stack.get(DataReg.tag) != null) {
                                    int max = 100 - Config.SERVER.nightmare_base_reversal.getAsInt();

                                    stack.get(DataReg.tag).putInt(att, max);
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (!slotContext.entity().level().isClientSide) {
            if (slotContext.entity().tickCount>=20) {
                slotContext.entity().getAttributes().addTransientAttributeModifiers(geta(stack));
            }else {
                slotContext.entity().addEffect(new MobEffectInstance(Effects.invulnerable,200,0,false,false,false));
            }
        }
        if (slotContext.entity().hasEffect(MobEffects.POISON)) {

            if (slotContext.entity().getHealth() <= 1) {
                if (stack.get(DataReg.tag) != null) {
                    if (!stack.get(DataReg.tag).getBoolean(AdvancementEvt.nightmare_base_reversal_orb)) {
                        if (slotContext.entity() instanceof Player player) {
                            player.addItem(new ItemStack(Items.nightmare_base_reversal_orb.get()));
                        }
                        stack.get(DataReg.tag).putBoolean(AdvancementEvt.nightmare_base_reversal_orb, true);
                    }
                }
                slotContext.entity().kill();
            }
        }
        if (stack.get(DataReg.tag) != null) {
            if (!Handler.hascurio(slotContext.entity(), Items.nightmare_base_reversal_card.get())) {
                if (stack.get(DataReg.tag).getInt(att) >= 4) {
                    if (slotContext.entity() instanceof Player player && !player.getCooldowns().isOnCooldown(stack.getItem())) {
                        stack.get(DataReg.tag).putInt(att, stack.get(DataReg.tag).getInt(att) - 4);
                        player.getCooldowns().addCooldown(stack.getItem(), 10);
                    }
                }
            } else {
                if (stack.get(DataReg.tag).getInt(att) >= -46) {
                    if (slotContext.entity() instanceof Player player && !player.getCooldowns().isOnCooldown(stack.getItem())) {
                        stack.get(DataReg.tag).putInt(att, stack.get(DataReg.tag).getInt(att) - 2);
                        player.getCooldowns().addCooldown(stack.getItem(), 20);
                    }
                }
            }
        } else {
            stack.set(DataReg.tag, new CompoundTag());
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        slotContext.entity().getAttributes().removeAttributeModifiers(geta(stack));
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> linkedHashMultimap = com.google.common.collect.LinkedHashMultimap.create();
        CuriosApi
                .addSlotModifier(linkedHashMultimap, "nightmare", ResourceLocation.parse("nightmare_base_reversal" + "add_slot"
                ), 3, AttributeModifier.Operation.ADD_VALUE);
        return linkedHashMultimap;
    }

    public Multimap<Holder<Attribute>, AttributeModifier> geta(ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> get = HashMultimap.create();

        if (stack.get(DataReg.tag) != null) {
            double as = -stack.get(DataReg.tag).getInt(att);
            as /= 100;
            for (Holder<Attribute> attribute : BuiltInRegistries.ATTRIBUTE.asHolderIdMap()) {
                if (attribute != (Attributes.MAX_HEALTH)) {
                    get.put(attribute, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()), as, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
                }
            }
        }
        return get;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.nightmare_base_reversal.tool.string").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_reversal.tool.string.2").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_reversal.tool.string.3").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_black_eye.tool.string.1").withStyle(ChatFormatting.RED));
        pTooltipComponents.add(Component.translatable("item.moonstone.nightmare_base_reversal_orb").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.moonstone.nightmare_base_reversal_card").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.moonstone.nightmare_base_reversal_mysterious").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.moonstone.candle").withStyle(ChatFormatting.DARK_RED));

        pTooltipComponents.add(Component.literal(""));

        pTooltipComponents.add(Component.translatable("item.nightmareeye.tool.string.2").withStyle(ChatFormatting.DARK_RED));

    }
    @Override
    public @NotNull Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        return Optional.of(new ToolTip(this,stack));
    }

    @Override
    public Map<Integer, String> tooltip() {
        Map<Integer,String> map = new HashMap<>();
        map.put(1,"轮回之路注定坎坷");
        map.put(2,"复活时给予十秒无敌");
        return map;
    }

    @Override
    public Map<Integer, String> element(ItemStack stack) {
        Map<Integer,String> map = new HashMap<>();
        map.put(1,"轮回之路注定坎坷");
        map.put(2,"复活时给予十秒无敌");
        return map;
    }
}

