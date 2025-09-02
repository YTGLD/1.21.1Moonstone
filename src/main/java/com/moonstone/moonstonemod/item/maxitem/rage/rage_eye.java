package com.moonstone.moonstonemod.item.maxitem.rage;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Config;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.DataReg;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

public class rage_eye extends Item implements ICurioItem,RAGE{
    public rage_eye() {
        super(new Properties().stacksTo(1).rarity(Rarity.EPIC));
    }
    public static final String health= "RageHealth";
    public static final String damage= "RageDamage";
    public static final String armor= "RageArmor";

    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        double b  = Config.SERVER.rage_eye.get();
        float a  = (float) b;
        double c  =  Config.SERVER.rage_eye_copy.get();
        float copy  = (float) c;
        if (!Handler.hascurio(player, Items.rage_eye.get())) {

            if (stack.get(DataReg.tag) == null) {
                stack.set(DataReg.tag, new CompoundTag());
            }
            if (!(entity instanceof Player)) {
                if (stack.get(DataReg.tag) != null) {
                    if (entity instanceof LivingEntity living) {
                        if (living.getAttribute(Attributes.MAX_HEALTH) != null) {
                            if (living.getAttribute(Attributes.MAX_HEALTH).getBaseValue() * copy < player.getAttributeValue(Attributes.MAX_HEALTH) * a) {
                                stack.get(DataReg.tag).putFloat(health, (float) living.getAttribute(Attributes.MAX_HEALTH).getBaseValue() * copy);
                            } else {
                                stack.get(DataReg.tag).putFloat(health, (float) player.getAttributeValue(Attributes.MAX_HEALTH) * a);
                            }
                        }
                        if (living.getAttribute(Attributes.ATTACK_DAMAGE) != null) {
                            if (living.getAttribute(Attributes.ATTACK_DAMAGE).getBaseValue() * copy < player.getAttributeValue(Attributes.ATTACK_DAMAGE) * a) {
                                stack.get(DataReg.tag).putFloat(damage, (float) living.getAttribute(Attributes.ATTACK_DAMAGE).getBaseValue() * copy);
                            } else {
                                stack.get(DataReg.tag).putFloat(damage, (float) player.getAttributeValue(Attributes.ATTACK_DAMAGE) * a);

                            }
                        }
                        if (living.getAttribute(Attributes.ARMOR) != null) {
                            if (living.getAttribute(Attributes.ARMOR).getBaseValue() * copy < player.getAttributeValue(Attributes.ARMOR) * a) {
                                stack.get(DataReg.tag).putFloat(armor, (float) living.getAttribute(Attributes.ARMOR).getBaseValue() * copy);
                            } else {
                                stack.get(DataReg.tag).putFloat(armor, (float) player.getAttributeValue(Attributes.ARMOR) * a);
                            }
                        }
                    }
                }
            }
        }

        return super.onLeftClickEntity(stack, player, entity);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("item.rage_eye.tool.string").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
        tooltipComponents.add(Component.literal(""));
        tooltipComponents.add(Component.translatable("item.rage_eye.tool.string.4").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));


    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = HashMultimap.create();
        if (stack.get(DataReg.tag)!=null) {
            modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(id, stack.get(DataReg.tag).getFloat(health), AttributeModifier.Operation.ADD_VALUE));
            modifierMultimap.put(Attributes.ARMOR, new AttributeModifier(id, stack.get(DataReg.tag).getFloat(armor), AttributeModifier.Operation.ADD_VALUE));
            modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(id, stack.get(DataReg.tag).getFloat(damage), AttributeModifier.Operation.ADD_VALUE));

        }
       return modifierMultimap;
    }
}

