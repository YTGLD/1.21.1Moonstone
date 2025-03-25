package com.moonstone.moonstonemod.item.man;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.contents.ManBundleContents;
import com.moonstone.moonstonemod.init.items.Drugs;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.AttReg;
import com.moonstone.moonstonemod.init.moonstoneitem.DataReg;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Map;

public class skin_dna extends ManDNA {
    public static final String molt = "moltSize";

    public skin_dna() {
        super(new Properties().stacksTo(1).rarity(Rarity.RARE).component(DataReg.man, ManBundleContents.EMPTY));
    }
    //加4护甲
    @Override
    public @Nullable List<Item> getDrug() {
        return List.of(
                Drugs.scale.get(),//鳞片化:增加2点护甲值，获得护甲值25%基于护甲的韧性值
                Drugs.stone_skin.get(),//硬质皮肤:增加20%护甲值但是减少15%挖掘速度
                Drugs.molt.get()//蜕皮再生：受到伤害后5秒内，临时减少20%护甲值和增加40%治疗（不可叠加）
        );
    }

    public static void hurt_of_skin_dna(LivingIncomingDamageEvent event){
        if (event.getEntity() instanceof Player player){
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (stack.getItem() instanceof health_dna) {
                            ManBundleContents manBundleContents = stack.get(DataReg.man);
                            if (manBundleContents != null) {
                                manBundleContents.items().forEach((itemStack -> {
                                    if (itemStack.is(Drugs.molt)) {
                                        @Nullable CompoundTag tah = itemStack.get(DataReg.tag);
                                        if (tah != null) {
                                            tah.putInt(molt,100);
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

    public Multimap<Holder<Attribute>, AttributeModifier> getA(SlotContext slotContext, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier>modifierMultimap = HashMultimap.create();

        if (stack.is(Items.skin_dna.get())) {
            ManBundleContents manBundleContents = stack.get(DataReg.man);
            if (manBundleContents != null) {
                manBundleContents.items().forEach((itemStack -> {
                    if (itemStack.is(Drugs.scale)) {
                        modifierMultimap.put(Attributes.ARMOR,
                                new AttributeModifier(ResourceLocation.withDefaultNamespace("scale"+this.getDescriptionId()),
                                        2, AttributeModifier.Operation.ADD_VALUE));
                        float t  = slotContext.entity().getArmorValue() / 4f;
                        modifierMultimap.put(Attributes.ARMOR_TOUGHNESS,
                                new AttributeModifier(ResourceLocation.withDefaultNamespace("scale"+this.getDescriptionId()),
                                        t, AttributeModifier.Operation.ADD_VALUE));

                    }
                }));
            }
        }
        if (stack.is(Items.skin_dna.get())) {
            ManBundleContents manBundleContents = stack.get(DataReg.man);
            if (manBundleContents != null) {
                manBundleContents.items().forEach((itemStack -> {
                    if (itemStack.is(Drugs.stone_skin)) {
                        modifierMultimap.put(Attributes.ARMOR,
                                new AttributeModifier(ResourceLocation.withDefaultNamespace("stone_skin"+this.getDescriptionId()),
                                        0.2f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
                        modifierMultimap.put(AttReg.cit,
                                new AttributeModifier(ResourceLocation.withDefaultNamespace("stone_skin"+this.getDescriptionId()),
                                        -0.15f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

                    }
                }));
            }
        }
        if (stack.is(Items.skin_dna.get())) {
            ManBundleContents manBundleContents = stack.get(DataReg.man);
            if (manBundleContents != null) {
                manBundleContents.items().forEach((itemStack -> {
                    if (itemStack.is(Drugs.molt)) {
                        modifierMultimap.put(Attributes.ARMOR,
                                new AttributeModifier(ResourceLocation.withDefaultNamespace("molt"+this.getDescriptionId()),
                                        -0.2f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
                        modifierMultimap.put(AttReg.heal,
                                new AttributeModifier(ResourceLocation.withDefaultNamespace("molt"+this.getDescriptionId()),
                                        0.4f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

                    }
                }));
            }
        }
        return modifierMultimap;
    }
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        slotContext.entity().getAttributes().addTransientAttributeModifiers(getA(slotContext,stack));

        ManBundleContents manBundleContents = stack.get(DataReg.man);
        if (manBundleContents != null) {
            manBundleContents.items().forEach((itemStack -> {
                if (itemStack.is(Drugs.molt)) {
                    @Nullable CompoundTag tah = itemStack.get(DataReg.tag);
                    if (tah != null) {
                        if (tah.getInt(molt) > 0) {
                            tah.putInt(molt,tah.getInt(molt)-1);
                        }
                    }
                }
            }));
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        slotContext.entity().getAttributes().removeAttributeModifiers(getA(slotContext,stack));
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier>modifierMultimap = HashMultimap.create();

        modifierMultimap.put(Attributes.ARMOR,
                new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()),
                        4, AttributeModifier.Operation.ADD_VALUE));

        return modifierMultimap;
    }

}
