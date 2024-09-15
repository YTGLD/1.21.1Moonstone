package com.moonstone.moonstonemod.item.maxitem.uncommon;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Config;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.init.DataReg;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.moonstoneitem.UnCommonItem;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.common.NeoForgeMod;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class plague extends UnCommonItem {
    public static final String DoPlague="Plague";
    public static final String CursePlague="CursePlague";
    public static final String YanJIu="YanJIu";
    public static final String FanYanJIu="FanYanJIu";

    public static final String YanJIuBoolean="YanJIuBoolean";


    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
       CompoundTag tag = stack.get(DataReg.tag);
        if (tag != null){
           tag.putBoolean(DoPlague,true);
            if (slotContext.entity().tickCount%20 == 0) {
                if (tag.getFloat(FanYanJIu) < 100) {
                   tag.putFloat(FanYanJIu, (float) (tag.getFloat(FanYanJIu) + Config.plague_pain.get()));
                }
            }

            if (tag.getBoolean(YanJIuBoolean)){
               tag.remove(CursePlague);
            }

            if (tag.getFloat(YanJIu)>=100){
               tag.putBoolean(YanJIuBoolean,true);
            }else {
               tag.putBoolean(YanJIuBoolean,false);
            }
        }else {
            stack.set(DataReg.tag,new CompoundTag());
        }
    }


    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        slotContext.entity().getAttributes().addTransientAttributeModifiers(this.APlague(stack));

    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        slotContext.entity().getAttributes().removeAttributeModifiers(this.APlague(stack));
        slotContext.entity().getAttributes().removeAttributeModifiers(this.APlague(newStack));

    }

    @Override
    public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
        return Handler.hascurio(slotContext.entity(), Items.nightmareeye.get());
    }

    public Multimap<Holder<Attribute>, AttributeModifier> APlague(ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = HashMultimap.create();
       CompoundTag tag = stack.get(DataReg.tag);
        if (tag != null){
            if (tag.getBoolean(YanJIuBoolean)) {
                modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(ResourceLocation.withDefaultNamespace(MoonStoneMod.MODID+"plague"), 0.5, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
                modifierMultimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(ResourceLocation.withDefaultNamespace(MoonStoneMod.MODID+"plague"), 0.25, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
                modifierMultimap.put(Attributes.ARMOR, new AttributeModifier(ResourceLocation.withDefaultNamespace(MoonStoneMod.MODID+"plague"), 0.66, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
                modifierMultimap.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(ResourceLocation.withDefaultNamespace(MoonStoneMod.MODID+"plague"), 0.9, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
                modifierMultimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(ResourceLocation.withDefaultNamespace(MoonStoneMod.MODID+"plague"), 0.7, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
                modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ResourceLocation.withDefaultNamespace(MoonStoneMod.MODID+"plague"), 0.7, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
                modifierMultimap.put(NeoForgeMod.SWIM_SPEED, new AttributeModifier(ResourceLocation.withDefaultNamespace(MoonStoneMod.MODID+"plague"), 0.7, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
            } else {
                float c = tag.getFloat(plague.CursePlague);
                c = -c;
                c /= 3;
                c /= 100;

                c *= Config.plague_effect.get();
                modifierMultimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(ResourceLocation.withDefaultNamespace(MoonStoneMod.MODID+"plague"), c, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
                modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ResourceLocation.withDefaultNamespace(MoonStoneMod.MODID+"plague"), c, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

                float s = tag.getFloat(FanYanJIu) / 100 / 3 / 100;
                s = -s;
                s *= Config.plague_effect.get();

                modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(ResourceLocation.withDefaultNamespace(MoonStoneMod.MODID+"plague"), s, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
                modifierMultimap.put(Attributes.ARMOR, new AttributeModifier(ResourceLocation.withDefaultNamespace(MoonStoneMod.MODID+"plague"), s, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
                modifierMultimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(ResourceLocation.withDefaultNamespace(MoonStoneMod.MODID+"plague"), s, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
            }
        }
        return modifierMultimap;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext pContext, List<Component> p_150751_, TooltipFlag pTooltipFlag) {
        p_150751_.add(Component.translatable("item.plague.tool.string.un").withStyle(ChatFormatting.RED));
       CompoundTag tag = stack.get(DataReg.tag);
        if (tag != null){
            if (!tag.getBoolean(YanJIuBoolean)) {
                //研究进度
                p_150751_.add(Component.translatable("item.plague.tool.string").append("" + tag.getFloat(FanYanJIu)).append("%").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
                //反研究进度.
                p_150751_.add(Component.translatable("item.plague.tool.string.1").append("" + tag.getFloat(YanJIu)).append("%").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
                //病毒进度.
                p_150751_.add(Component.translatable("item.plague.tool.string.2").append("" + tag.getFloat(CursePlague)).append("%").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
                p_150751_.add(Component.translatable(""));

                p_150751_.add(Component.translatable("item.plague.tool.string.3").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
                p_150751_.add(Component.translatable("item.plague.tool.string.4").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
                p_150751_.add(Component.translatable("item.plague.tool.string.5").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
                p_150751_.add(Component.translatable(""));
                p_150751_.add(Component.translatable("item.plague.tool.string.6").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
            } else {
                p_150751_.add(Component.translatable("item.plague.tool.string.8").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
                p_150751_.add(Component.translatable("item.plague.tool.string.9").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
                p_150751_.add(Component.translatable("item.plague.tool.string.10").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
                p_150751_.add(Component.translatable("item.plague.tool.string.11").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
                p_150751_.add(Component.translatable("item.plague.tool.string.12").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
                p_150751_.add(Component.translatable("item.plague.tool.string.13").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
                p_150751_.add(Component.translatable("item.plague.tool.string.14").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));

                p_150751_.add(Component.translatable("item.plague.tool.string.15").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
                p_150751_.add(Component.translatable("item.plague.tool.string.16").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
                p_150751_.add(Component.translatable("item.plague.tool.string.17").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
                p_150751_.add(Component.translatable("item.plague.tool.string.18").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
            }
        }
    }
}
