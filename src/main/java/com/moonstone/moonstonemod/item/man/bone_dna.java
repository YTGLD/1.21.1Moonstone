package com.moonstone.moonstonemod.item.man;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.contents.ManBundleContents;
import com.moonstone.moonstonemod.init.items.Drugs;
import com.moonstone.moonstonemod.init.moonstoneitem.DataReg;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class bone_dna extends skin_dna{

    @Override
    public @Nullable List<Item> getDrug() {
        return List.of(
                Drugs.scale.get(),//鳞片化:增加2点护甲值，获得护甲值25%基于护甲的韧性值
                Drugs.stone_skin.get(),//硬质皮肤:增加20%护甲值但是减少15%挖掘速度
                Drugs.molt.get(),//蜕皮再生：受到伤害后5秒内，临时减少20%护甲值和增加40%治疗（不可叠加）

                Drugs.bone_spur.get(),//反伤20%，反伤有50%概率受到额外25%伤害
                Drugs.hollow.get(),//增加100%摔落伤害和25%攻速
                Drugs.proliferative_bone.get()//增加25%护甲和韧性，减少20%速度

        );
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        slotContext.entity().getAttributes().removeAttributeModifiers(hollow(stack));
        slotContext.entity().getAttributes().removeAttributeModifiers(proliferative_bone(stack));
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        slotContext.entity().getAttributes().addTransientAttributeModifiers(hollow(stack));
        slotContext.entity().getAttributes().addTransientAttributeModifiers(proliferative_bone(stack));
    }
    public Multimap<Holder<Attribute>, AttributeModifier> proliferative_bone(ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier>modifierMultimap = HashMultimap.create();

        ManBundleContents manBundleContents = stack.get(DataReg.man);
        if (manBundleContents != null) {
            manBundleContents.items().forEach((itemStack -> {
                if (itemStack.is(Drugs.proliferative_bone)) {
                    modifierMultimap.put(Attributes.ARMOR,
                            new AttributeModifier(ResourceLocation.withDefaultNamespace("proliferative_bone"+this.getDescriptionId()),
                                    0.25f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
                    modifierMultimap.put(Attributes.ARMOR_TOUGHNESS,
                            new AttributeModifier(ResourceLocation.withDefaultNamespace("proliferative_bone"+this.getDescriptionId()),
                                    0.25f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
                    modifierMultimap.put(Attributes.MOVEMENT_SPEED,
                            new AttributeModifier(ResourceLocation.withDefaultNamespace("proliferative_bone"+this.getDescriptionId()),
                                    -0.2F, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
                }
            }));
        }

        return modifierMultimap;
    }
    public Multimap<Holder<Attribute>, AttributeModifier> hollow(ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier>modifierMultimap = HashMultimap.create();

        ManBundleContents manBundleContents = stack.get(DataReg.man);
        if (manBundleContents != null) {
            manBundleContents.items().forEach((itemStack -> {
                if (itemStack.is(Drugs.hollow)) {
                    modifierMultimap.put(Attributes.ATTACK_SPEED,
                            new AttributeModifier(ResourceLocation.withDefaultNamespace("hollow"+this.getDescriptionId()),
                                    0.25f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
                }
            }));
        }

        return modifierMultimap;
    }
    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap =  super.getAttributeModifiers(slotContext, id, stack);

        modifierMultimap.put(Attributes.ARMOR,
                new AttributeModifier(ResourceLocation.withDefaultNamespace("bone_dna"+this.getDescriptionId()),
                        0.15f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        modifierMultimap.put(Attributes.ARMOR_TOUGHNESS,
                new AttributeModifier(ResourceLocation.withDefaultNamespace("bone_dna"+this.getDescriptionId()),
                        0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));


        return modifierMultimap;
    }

    public static void hollow(LivingIncomingDamageEvent event){
        if (event.getEntity() instanceof Player player){
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (stack.getItem() instanceof bone_dna) {
                            ManBundleContents manBundleContents = stack.get(DataReg.man);
                            if (manBundleContents != null) {
                                manBundleContents.items().forEach((itemStack -> {
                                    if (itemStack.is(Drugs.hollow)) {
                                        if (event.getSource().is(DamageTypes.FALL)) {
                                            event.setAmount(event.getAmount()*2);
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
    public static void bone_spur(LivingIncomingDamageEvent event){
        if (event.getEntity() instanceof Player player){
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (stack.getItem() instanceof bone_dna) {
                            ManBundleContents manBundleContents = stack.get(DataReg.man);
                            if (manBundleContents != null) {
                                manBundleContents.items().forEach((itemStack -> {
                                    if (itemStack.is(Drugs.bone_spur)) {
                                        if (event.getSource().getEntity() instanceof LivingEntity living) {
                                            living.hurt(living.damageSources().dryOut(),event.getAmount()*0.2f);

                                            if (new Random().nextInt(100) < 25) {
                                                event.setAmount(event.getAmount()*1.5f);
                                            }

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
