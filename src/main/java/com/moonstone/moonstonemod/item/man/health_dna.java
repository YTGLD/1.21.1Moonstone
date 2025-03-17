package com.moonstone.moonstonemod.item.man;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.contents.ManBundleContents;
import com.moonstone.moonstonemod.init.items.Drugs;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.DataReg;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class health_dna extends ManDNA {

    public health_dna() {
        super(new Properties().stacksTo(1).rarity(Rarity.RARE).component(DataReg.man, ManBundleContents.EMPTY));
    }

    @Override
    public @Nullable List<Item> getDrug() {
        return List.of(
                Drugs.stem_cell_proliferation.get(),//干细胞激增：每损失10%生命值，加5%治疗
                Drugs.lymphatic.get(),//淋巴因子释放：每存在1个负面状态，治疗加5%
                Drugs.abnormal.get(),//反常态病变：正面药水状态时长减少50%，任何效果状态结束时恢复6点生命值
                Drugs.catalyst_for_life.get()//异变催化剂：增加10点生命值
        );
    }

    public  static void abnormal(LivingEntity entity){
        if (entity instanceof Player player) {
            if (Handler.hascurio(player, Items.health_dna.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.health_dna.get())) {
                                ManBundleContents manBundleContents = stack.get(DataReg.man);
                                if (manBundleContents != null) {
                                    manBundleContents.items().forEach((itemStack -> {
                                        if (itemStack.is(Drugs.abnormal)) {
                                            player.heal(6);
                                        }
                                    }));
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    public  static void lymphatic(LivingHealEvent event){
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.health_dna.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.health_dna.get())) {
                                ManBundleContents manBundleContents = stack.get(DataReg.man);
                                if (manBundleContents != null) {
                                    manBundleContents.items().forEach((itemStack -> {
                                        if (itemStack.is(Drugs.lymphatic)) {
                                            List<Integer> integersHealth = new ArrayList<>();
                                            for (MobEffectInstance effect : player.getActiveEffects()) {
                                                if (effect != null
                                                        && !effect.getEffect().value().isBeneficial()) {
                                                    integersHealth.add(1);
                                                }
                                            }
                                            float heal = 0;
                                            for (int ignored : integersHealth){
                                                heal+=5;
                                            }
                                            heal/=100;

                                            event.setAmount(event.getAmount()*(1+heal));
                                        }
                                    }));
                                }
                            }
                        }
                    }
                });
            }
        }
    }
    public  static void stem_cell_proliferation(LivingHealEvent event){
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.health_dna.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.health_dna.get())) {
                                ManBundleContents manBundleContents = stack.get(DataReg.man);
                                if (manBundleContents != null) {
                                    manBundleContents.items().forEach((itemStack -> {
                                        if (itemStack.is(Drugs.stem_cell_proliferation)) {

                                            float lv = player.getHealth() / player.getMaxHealth();

                                            lv *= 100;
                                            float now = (100 -(lv));
                                            now/=100f;
                                            now*=0.5f;
                                            event.setAmount(event.getAmount()*(1+now));

                                        }
                                    }));
                                }
                            }
                        }
                    }
                });
            }
        }
    }
    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier>modifierMultimap = HashMultimap.create();
        modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()), 8, AttributeModifier.Operation.ADD_VALUE));
        CuriosApi.getCuriosInventory(slotContext.entity()).ifPresent(handler -> {
            Map<String, ICurioStacksHandler> curios = handler.getCurios();
            for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                ICurioStacksHandler stacksHandler = entry.getValue();
                IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                for (int i = 0; i < stacksHandler.getSlots(); i++) {
                    ItemStack stackInSlot = stackHandler.getStackInSlot(i);
                    if (stackInSlot.is(Items.health_dna.get())) {
                        ManBundleContents manBundleContents = stackInSlot.get(DataReg.man);
                        if (manBundleContents != null) {
                            manBundleContents.items().forEach((itemStack -> {
                                if (itemStack.is(Drugs.catalyst_for_life)) {
                                    modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(ResourceLocation.withDefaultNamespace("heathgene"+this.getDescriptionId()), 10, AttributeModifier.Operation.ADD_VALUE));
                                }
                            }));
                        }
                    }
                }
            }
        });
        return modifierMultimap;
    }
    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        pTooltipComponents.add(Component.translatable("item.health_dna.tool.string").withStyle(ChatFormatting.GOLD));
        ManBundleContents manBundleContents = pStack.get(DataReg.man);
        if (manBundleContents != null) {
            manBundleContents.items().forEach((itemStack -> {
                if (!itemStack.isEmpty()) {
                    ResourceLocation resourceLocation= BuiltInRegistries.ITEM.getKey(itemStack.getItem());
                    String s = resourceLocation.toString().replace(":",".");
                    pTooltipComponents.add(Component.translatable("item."+s).withStyle(ChatFormatting.GOLD));
                }
            }));
        }

        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }

}
