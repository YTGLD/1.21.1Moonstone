package com.moonstone.moonstonemod.item.blood;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.init.moonstoneitem.AttReg;
import com.moonstone.moonstonemod.init.moonstoneitem.DataReg;
import com.moonstone.moonstonemod.init.moonstoneitem.i.Blood;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
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

import java.text.DecimalFormat;
import java.util.List;

public class killer  extends Item implements ICurioItem, Blood {
    public killer() {
        super(new Properties().stacksTo(1).durability(1000000000).rarity(Rarity.UNCOMMON));



    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (stack.get(DataReg.tag)==null){
            stack.set(DataReg.tag,new CompoundTag());
        }
        if (slotContext.entity() instanceof Player player){

            float lv = player.getHealth() / player.getMaxHealth();

            lv *= 100;
            int now = (int) (100 -(lv));
            if (stack.get(DataReg.tag)==null){
                stack.set(DataReg.tag,new CompoundTag());
            }
            if (stack.get(DataReg.tag)!=null){
                stack.get(DataReg.tag).putInt(uDead,now);
            }

            player.getAttributes().addTransientAttributeModifiers(ad(stack));
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            player.getAttributes().removeAttributeModifiers(ad(stack));
        }
    }


    public Multimap<Holder<Attribute>, AttributeModifier> ad(ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> modifiers = HashMultimap.create();

        if (stack.get(DataReg.tag)!=null) {
            int lvl = stack.get(DataReg.tag).getInt(uDead);
            float heal = 1.25f / 100f * lvl;
            float speed = 0.8f / 100f * lvl;
            float damage = 0.75f / 100f * lvl;
            float attSpeed = 0.5f / 100f * lvl;
            float armor = 0.35f / 100f * lvl;



            modifiers.put(AttReg.heal,new AttributeModifier(ResourceLocation.parse(MoonStoneMod.MODID +this.getDescriptionId()),
                    heal, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
            modifiers.put(Attributes.MOVEMENT_SPEED,new AttributeModifier(ResourceLocation.parse(MoonStoneMod.MODID+this.getDescriptionId()),
                    speed, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
            modifiers.put(Attributes.ATTACK_DAMAGE,new AttributeModifier(ResourceLocation.parse(MoonStoneMod.MODID+this.getDescriptionId()),
                    damage, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
            modifiers.put(Attributes.ATTACK_SPEED,new AttributeModifier(ResourceLocation.parse(MoonStoneMod.MODID+this.getDescriptionId()),
                    attSpeed, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
            modifiers.put(Attributes.ARMOR,new AttributeModifier(ResourceLocation.parse(MoonStoneMod.MODID+this.getDescriptionId()),
                    armor, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));



        }
        return modifiers;
    }
    public static final String  uDead = "undead";

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type) {
        tooltip.add(Component.literal(""));
        tooltip.add(Component.translatable("item.killer.tool.string.1").withStyle(ChatFormatting.RED));
        tooltip.add(Component.translatable("item.killer.tool.string.2").withStyle(ChatFormatting.RED));
        tooltip.add(Component.translatable("item.killer.tool.string.3").withStyle(ChatFormatting.RED));
        tooltip.add(Component.translatable("item.killer.tool.string.4").withStyle(ChatFormatting.RED));
        tooltip.add(Component.translatable("item.killer.tool.string.5").withStyle(ChatFormatting.RED));
        tooltip.add(Component.translatable("item.killer.tool.string.6").withStyle(ChatFormatting.RED));
        tooltip.add(Component.literal(""));
        if (stack.get(DataReg.tag)!=null) {
            int lvl = stack.get(DataReg.tag).getInt(uDead);
            float heal = 1.25f / 100f * lvl;
            float speed = 0.8f / 100f * lvl;
            float damage = 0.75f / 100f * lvl;
            float attSpeed = 0.5f / 100f * lvl;
            float armor = 0.35f / 100f * lvl;
            tooltip.add(Component.translatable("item.killer.tool.string.7").withStyle(ChatFormatting.DARK_RED));
            DecimalFormat df = new DecimalFormat("#.###");

            tooltip.add(Component.translatable("item.killer.tool.string.8").append(df.format(heal * 100) +"%").withStyle(ChatFormatting.DARK_RED));
            tooltip.add(Component.translatable("item.killer.tool.string.9").append(df.format(speed * 100) +"%").withStyle(ChatFormatting.DARK_RED));
            tooltip.add(Component.translatable("item.killer.tool.string.10").append(df.format(damage * 100) +"%").withStyle(ChatFormatting.DARK_RED));
            tooltip.add(Component.translatable("item.killer.tool.string.11").append(df.format(attSpeed * 100) +"%").withStyle(ChatFormatting.DARK_RED));
            tooltip.add(Component.translatable("item.killer.tool.string.12").append(df.format(armor * 100) +"%").withStyle(ChatFormatting.DARK_RED));
        }
        tooltip.add(Component.literal(""));
    }
}

