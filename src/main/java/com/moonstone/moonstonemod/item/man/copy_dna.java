package com.moonstone.moonstonemod.item.man;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.contents.ManBundleContents;
import com.moonstone.moonstonemod.init.items.Drugs;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.AttReg;
import com.moonstone.moonstonemod.init.moonstoneitem.DataReg;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Map;

public class copy_dna extends ManDNA {

    public copy_dna() {
        super(new Properties().stacksTo(1).rarity(Rarity.RARE).component(DataReg.man, ManBundleContents.EMPTY));
    }

    @Override
    public @Nullable List<Item> getDrug() {
        return List.of(
                Drugs.lymphadenopathy.get(),//淋巴结肿大：减少66%所有药水效果持续时间，任何效果结束时恢复5%最大生命值
                Drugs.connective_tissue.get(),//结缔组织增生：减少20%弹射物伤害，受到弹射物伤害时恢复2点生命值
                Drugs.paralysis.get(),//免疫瘫痪：增加10点生命值，减少10%治疗
                Drugs.formative_cell.get()//形成细胞激增：生命值低于50%时，增加40%治疗
        );
    }

    public  static void connective_tissue(LivingIncomingDamageEvent event){
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.copy_dna.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.copy_dna.get())) {
                                ManBundleContents manBundleContents = stack.get(DataReg.man);
                                if (manBundleContents != null) {
                                    manBundleContents.items().forEach((itemStack -> {
                                        if (itemStack.is(Drugs.connective_tissue)) {
                                            if (event.getSource().is(DamageTypes.MOB_PROJECTILE)){
                                                event.setAmount(event.getAmount()*0.8f);
                                                player.heal(2);
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

    public  static void formative_cell(LivingHealEvent event){
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.copy_dna.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.copy_dna.get())) {
                                ManBundleContents manBundleContents = stack.get(DataReg.man);
                                if (manBundleContents != null) {
                                    manBundleContents.items().forEach((itemStack -> {
                                        if (itemStack.is(Drugs.formative_cell)) {
                                            if (player.getHealth()<=player.getMaxHealth()*0.5f){
                                                event.setAmount(event.getAmount()*1.4f);
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


    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier>modifierMultimap = HashMultimap.create();
        modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()), 10, AttributeModifier.Operation.ADD_VALUE));
        modifierMultimap.put(AttReg.heal, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()), -0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));


        CuriosApi.getCuriosInventory(slotContext.entity()).ifPresent(handler -> {
            Map<String, ICurioStacksHandler> curios = handler.getCurios();
            for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                ICurioStacksHandler stacksHandler = entry.getValue();
                IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                for (int i = 0; i < stacksHandler.getSlots(); i++) {
                    ItemStack stackInSlot = stackHandler.getStackInSlot(i);
                    if (stackInSlot.is(Items.copy_dna.get())) {
                        ManBundleContents manBundleContents = stackInSlot.get(DataReg.man);
                        if (manBundleContents != null) {
                            manBundleContents.items().forEach((itemStack -> {
                                if (itemStack.is(Drugs.paralysis)) {
                                    modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(ResourceLocation.withDefaultNamespace("paralysis"+this.getDescriptionId()), 10, AttributeModifier.Operation.ADD_VALUE));
                                    modifierMultimap.put(AttReg.heal, new AttributeModifier(ResourceLocation.withDefaultNamespace("paralysis"+this.getDescriptionId()), -0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
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

        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }

}
