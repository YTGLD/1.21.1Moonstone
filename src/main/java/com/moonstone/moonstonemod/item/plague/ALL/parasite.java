package com.moonstone.moonstonemod.item.plague.ALL;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.init.AttReg;
import com.moonstone.moonstonemod.init.DataReg;
import com.moonstone.moonstonemod.moonstoneitem.extend.medIC;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

public class parasite extends medIC implements ICurioItem {
    public String lvl_parasite = "lvl";
    public String sizeLevel = "sizeLevel";
    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
       CompoundTag tag = stack.get(DataReg.tag);
        if (tag != null){
            tag.putString("ytgld", "ytgld");
        }else {
            stack.set(DataReg.tag,new CompoundTag());
        }
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext .entity() instanceof Player player){
           CompoundTag tag = stack.get(DataReg.tag);
            player.getAttributes().addTransientAttributeModifiers(Head(stack));
            if (tag != null){
                {
                    if (player.getFoodData().getFoodLevel() < 7) {
                        if (tag.getInt(sizeLevel) > 400) {
                            player.getFoodData().setFoodLevel(player.getFoodData().getFoodLevel() + 1);
                            tag.putInt(sizeLevel, tag.getInt(sizeLevel) - 20);
                        }
                    }

                    if (player.getFoodData().getFoodLevel() < player.getFoodData().getFoodLevel() / 2) {
                        if (!player.level().isClientSide && player.tickCount % 30 == 0) {
                            player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 60, 0));
                        }
                    }
                    player.getAttributes().addTransientAttributeModifiers(AttributeModifiers(player, stack));
                    if (!player.level().isClientSide && player.tickCount % 30 == 0) {
                        player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 120, 0));
                        if (tag.getInt(sizeLevel) >= 300 && tag.getInt(sizeLevel) < 600) {
                            tag.putInt(lvl_parasite, 1);
                        }
                        if (tag.getInt(sizeLevel) >= 600 && tag.getInt(sizeLevel) < 900) {
                            tag.putInt(lvl_parasite, 2);
                        }
                        if (tag.getInt(sizeLevel) >= 900) {
                            tag.putInt(lvl_parasite, 3);
                        }
                    }
                }
            }else {
                stack.set(DataReg.tag,new CompoundTag());
            }
        }
    }
    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (slotContext .entity() instanceof Player player){
            player.getAttributes().removeAttributeModifiers(AttributeModifiers(player,stack));
            player.getAttributes().removeAttributeModifiers(Head(stack));
        }
    }
    private Multimap<Holder<Attribute>, AttributeModifier> Head(ItemStack stack){
        Multimap<Holder<Attribute>, AttributeModifier> multimap = HashMultimap.create();
        CompoundTag tag = stack.get(DataReg.tag);
        float s = 0;
        if (tag != null) {
            if (tag.getInt(sizeLevel) > 900){
                s=0.4f;
            }
        }


        multimap.put(AttReg.alL_attack, new AttributeModifier(
                ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()),
                s,
                AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

        return multimap;
    }
    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);

        CompoundTag tag = pStack.get(DataReg.tag);
        if (tag != null){
            if (Screen.hasShiftDown()) {
                pTooltipComponents.add(Component.translatable("item.parasite.tool.string").withStyle(ChatFormatting.RED));
                pTooltipComponents.add(Component.translatable("item.parasite.tool.string.1").withStyle(ChatFormatting.RED));
                pTooltipComponents.add(Component.translatable(""));
                pTooltipComponents.add(Component.translatable("item.parasite.tool.string.2").withStyle(ChatFormatting.RED));
                pTooltipComponents.add(Component.translatable("item.parasite.tool.string.3").withStyle(ChatFormatting.RED));
                pTooltipComponents.add(Component.translatable(""));
                pTooltipComponents.add(Component.translatable("item.parasite.tool.string.4").withStyle(ChatFormatting.RED));
                pTooltipComponents.add(Component.translatable(""));
                pTooltipComponents.add(Component.translatable("item.parasite.tool.string.5").withStyle(ChatFormatting.RED));


                if (tag.getInt(lvl_parasite) == 0) {//在0级时的寄生虫
                    pTooltipComponents.add(Component.translatable("无属性").withStyle(ChatFormatting.DARK_RED));
                } else if (tag.getInt(lvl_parasite) >= 1 && tag.getInt(lvl_parasite) < 2) {//在1级时的寄生虫
                    pTooltipComponents.add(Component.translatable(" +1 伤害").withStyle(ChatFormatting.RED));
                    pTooltipComponents.add(Component.translatable(" +2 生命上限").withStyle(ChatFormatting.RED));
                    pTooltipComponents.add(Component.translatable(" +4 护甲").withStyle(ChatFormatting.RED));
                    pTooltipComponents.add(Component.translatable(""));
                    pTooltipComponents.add(Component.translatable(" -1 食物的饥饿值").withStyle(ChatFormatting.RED));
                } else if (tag.getInt(lvl_parasite) >= 2 && tag.getInt(lvl_parasite) < 3) {//在2级时的寄生虫
                    pTooltipComponents.add(Component.translatable(" +2 伤害").withStyle(ChatFormatting.RED));
                    pTooltipComponents.add(Component.translatable(" +4 生命上限").withStyle(ChatFormatting.RED));
                    pTooltipComponents.add(Component.translatable(" +6 护甲").withStyle(ChatFormatting.RED));
                    pTooltipComponents.add(Component.translatable(""));
                    pTooltipComponents.add(Component.translatable(" -1 食物的饱和度").withStyle(ChatFormatting.RED));
                } else if (tag.getInt(lvl_parasite) >= 3) {//在3级时的寄生虫
                    pTooltipComponents.add(Component.translatable(" 在饥饿值大于60%时，攻击附加40%伤害").withStyle(ChatFormatting.RED));
                    pTooltipComponents.add(Component.translatable(""));
                    pTooltipComponents.add(Component.translatable(" -1 食物的饥饿值").withStyle(ChatFormatting.RED));
                    pTooltipComponents.add(Component.translatable(" -1 食物的饱和度").withStyle(ChatFormatting.RED));
                    pTooltipComponents.add(Component.translatable(""));
                    pTooltipComponents.add(Component.translatable(" +2 伤害").withStyle(ChatFormatting.RED));
                    pTooltipComponents.add(Component.translatable(" +4 生命上限").withStyle(ChatFormatting.RED));
                    pTooltipComponents.add(Component.translatable(" +6 护甲").withStyle(ChatFormatting.RED));

                }

            } else {
                pTooltipComponents.add(Component.translatable("按下SHIFT查看").withStyle(ChatFormatting.DARK_RED));
            }
            pTooltipComponents.add(Component.translatable(""));
            pTooltipComponents.add(Component.translatable("目前等级：" + tag.getInt(lvl_parasite)).withStyle(ChatFormatting.DARK_RED));
            pTooltipComponents.add(Component.translatable("目前点数：" + tag.getInt(sizeLevel)).withStyle(ChatFormatting.DARK_RED));
        }
    }
    public Multimap<Holder<Attribute>, AttributeModifier> AttributeModifiers(Player player, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = HashMultimap.create();
       CompoundTag tag = stack.get(DataReg.tag);
        if (tag != null){
            int si = 0;
            if (tag.getInt(lvl_parasite) >= 1) {
                si = 1;
            }
            if (tag.getInt(lvl_parasite) >= 2) {
                si *= 2;
            }

            modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()), si, AttributeModifier.Operation.ADD_VALUE));
            modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()), si * 2, AttributeModifier.Operation.ADD_VALUE));
            modifierMultimap.put(Attributes.ARMOR, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()), si * 3, AttributeModifier.Operation.ADD_VALUE));

        }
        return modifierMultimap;
    }
}
