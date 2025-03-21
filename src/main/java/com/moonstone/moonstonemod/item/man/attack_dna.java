package com.moonstone.moonstonemod.item.man;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.contents.ManBundleContents;
import com.moonstone.moonstonemod.init.items.Drugs;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.DataReg;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.CriticalHitEvent;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class attack_dna extends run_dna {

    public attack_dna() {
    }

    @Override
    public @Nullable List<Item> getDrug() {
        return List.of(
                Drugs.reverse_correction.get(),//逆修正强化：普通攻击有15%的概率造成暴击
                Drugs.self_correction.get(),//自修正强化：暴击有概率造成额外25%的伤害
                Drugs.abnormal_muscles.get(),//反常肌肉强化：疾跑时伤害增加30%
                Drugs.abnormal_endurance.get(),//反常耐力：增加20%攻击伤害，减少20%最大生命值
                Drugs.protein.get(),//蛋白质
                Drugs.hydrolysis.get(),//水解
                Drugs.cp_energy.get(),//cp能力
                Drugs.phosphorylation.get()//异常磷酸化
        );
    }
    public static void abnormal_muscles(LivingIncomingDamageEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.attack_dna.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.attack_dna.get())) {
                                ManBundleContents manBundleContents = stack.get(DataReg.man);
                                if (manBundleContents != null) {
                                    manBundleContents.items().forEach((itemStack -> {
                                        if (itemStack.is(Drugs.abnormal_muscles)) {
                                            if (player.isSprinting()) {
                                                event.setAmount(event.getAmount()*1.3f);

                                            }
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
    public static void reverse_correction(LivingIncomingDamageEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.attack_dna.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.attack_dna.get())) {
                                ManBundleContents manBundleContents = stack.get(DataReg.man);
                                if (manBundleContents != null) {
                                    Random random = new Random();
                                    manBundleContents.items().forEach((itemStack -> {
                                        if (itemStack.is(Drugs.reverse_correction)) {
                                            if (random.nextInt(100)<15) {
                                                event.setAmount(event.getAmount()*1.35f);

                                            }
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
    public static void self_correction(CriticalHitEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.attack_dna.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.attack_dna.get())) {
                                ManBundleContents manBundleContents = stack.get(DataReg.man);
                                if (manBundleContents != null) {
                                    Random random = new Random();
                                    manBundleContents.items().forEach((itemStack -> {
                                        if (itemStack.is(Drugs.self_correction)) {
                                            if (random.nextInt(100)<25) {
                                                event.setDamageMultiplier(event.getDamageMultiplier()*1.25f);

                                            }
                                        }
                                    }));;
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
        modifierMultimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()), -0.2, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()), 4, AttributeModifier.Operation.ADD_VALUE));


        CuriosApi.getCuriosInventory(slotContext.entity()).ifPresent(handler -> {
            Map<String, ICurioStacksHandler> curios = handler.getCurios();
            for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                ICurioStacksHandler stacksHandler = entry.getValue();
                IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                for (int i = 0; i < stacksHandler.getSlots(); i++) {
                    ItemStack stackInSlot = stackHandler.getStackInSlot(i);
                    if (stackInSlot.is(Items.attack_dna.get())) {
                        ManBundleContents manBundleContents = stackInSlot.get(DataReg.man);
                        if (manBundleContents != null) {
                            manBundleContents.items().forEach((itemStack -> {
                                if (itemStack.is(Drugs.abnormal_endurance)) {
                                    modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()), -0.2, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
                                    modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()), 0.2, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

                                }
                            }));
                        }
                    }
                }
            }
        });
        return modifierMultimap;
    }
}
