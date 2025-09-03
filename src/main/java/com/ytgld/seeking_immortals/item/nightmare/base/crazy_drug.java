package com.ytgld.seeking_immortals.item.nightmare.base;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.seeking_immortals.Handler;
import com.ytgld.seeking_immortals.SeekingImmortalsMod;
import com.ytgld.seeking_immortals.init.AttReg;
import com.ytgld.seeking_immortals.init.DataReg;
import com.ytgld.seeking_immortals.init.Items;
import com.ytgld.seeking_immortals.item.nightmare.extend.nightmare;
import com.ytgld.seeking_immortals.item.nightmare.tip.Terror;
import com.ytgld.seeking_immortals.renderer.light.Light;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

/**
 * 疯狂长生不死药：
 * <p>
 * <p>
 * 	刺鼻不死药的进化版本
 * <p>
 * <p>
 * 	药瓶装满时能喝10次
 * <p>
 * 	每佩戴1个罪孽都会增加容量
 * <p>
 * <p>
 * 	按下R键饮用瓶中的药水
 * <p>
 * 	增加伤害，护甲，速度，治疗，攻速和最大生命值
 * <p>
 * <p>
 * 	同时恢复10%生命值
 * <p>
 * 	但是每次饮用都会减少瓶中药水洁净度
 * <p>
 * 	每次都会减少属性加成且无下限
 * <p>
 * 	灵药洁净度随时间缓慢恢复
 * <p>
 * <p>

 * 	喝下的药水次数越多，属性加成越好
 * <p>
 * 	目前属性加成：50%
 * <p>
 * 	目前每次减少的洁净度：10%
 * <p>
 * 	目前持续时间：400
 * <p>
 * <p>
 * 	最多：100%
 * <p>
 * <p>
 * 获取：获得一瓶满加成的刺鼻不死药
 */
public class crazy_drug extends nightmare {

    public  static final String drugClear = "drugClear";
    public static final String effectCooldown = "effectCooldown";
    public  static final String drugSize = "drugSize";
    public  static final String drugTime = "drugTime";
    public  static final String drugStronger = "drugStronger";

    //每次减少的标签
    public  static final String drugWeakness = "drugWeakness";
    public static void crazy_drugAddEffect(Player player, ItemStack stack){
        if (Handler.hascurio(player, Items.crazy_drug.get())) {
            if (stack.is(Items.crazy_drug.get())){
                if (stack.getItem() instanceof crazy_drug drug) {
                    CompoundTag compoundTag = stack.get(DataReg.tag);
                    if (compoundTag != null) {
                        if (compoundTag.getInt(drugSize) > 0) {
                            if (compoundTag.getInt(effectCooldown) <= 0) {
                                //加经验值，不超过最大值（100）
                                if (compoundTag.getInt(terrorName) < drug.maxLevel(stack)) {
                                    compoundTag.putInt(terrorName, compoundTag.getInt(terrorName) + 1);
                                }
                                //减少洁净度
                                compoundTag.putInt(drugWeakness, compoundTag.getInt(drugWeakness) + 5);
                                float stronger = 50*(1+(drug.nowLevel(stack))/100f);
                                float weakness = compoundTag.getInt(drugWeakness);
                                weakness /= (1 + (drug.nowLevel(stack)/100f));
                                stronger -= weakness;

                                //最终的奖励
                                compoundTag.putFloat(drugStronger, stronger/100f);
                                compoundTag.putInt(drugTime, (int) (400 * (1 + (drug.nowLevel(stack)/100f))));

                                player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.WITCH_DRINK, SoundSource.NEUTRAL, 1F, 1F);
                                player.heal(player.getMaxHealth() * 0.1f);

                                //减少数量
                                compoundTag.putInt(drugSize, compoundTag.getInt(drugSize) - 1);
                                compoundTag.putInt(effectCooldown, 2);
                            }
                        }
                    }
                }
            }
        }
    }
    public static final String giveDrug= "giveDrug";
    public static void give(ItemStack stack,Player player){
        CompoundTag compoundTag = stack.get(DataReg.tag);
        if (compoundTag != null) {
            if (!compoundTag.getBoolean(giveDrug)){
                player.addItem(new ItemStack(Items.crazy_drug.get()));
                compoundTag.putBoolean(giveDrug,true);
            }
        }
    }
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        super.curioTick(slotContext, stack);
        if (slotContext.entity() instanceof Player player) {
            if (!player.level().isClientSide) {
                CompoundTag compoundTag = stack.get(DataReg.tag);
                player.getAttributes().addTransientAttributeModifiers(ad(stack,this));
                //携带药水的数量
                int value = (int) player.getAttributeValue(AttReg.effectNumber);
                if (compoundTag != null) {
                    if (compoundTag.getInt(effectCooldown) > 0) {
                        compoundTag.putInt(effectCooldown,compoundTag.getInt(effectCooldown)-1);
                    }

                    if (!compoundTag.getBoolean(drugClear)){
                        compoundTag.putInt(drugWeakness, 0);
                        compoundTag.putInt(drugSize, value);
                        compoundTag.putBoolean(drugClear,true);
                    }
                    if (compoundTag.getInt(drugTime) > 0) {
                        compoundTag.putInt(drugTime,compoundTag.getInt(drugTime)-1);
                    }
                    if (!player.getCooldowns().isOnCooldown(Items.crazy_drug.get())){
                        if (compoundTag.getInt(drugWeakness)>0) {
                            compoundTag.putInt(drugWeakness, compoundTag.getInt(drugWeakness) - 7);
                        }
                        if (compoundTag.getInt(drugSize)<value) {
                            compoundTag.putInt(drugSize, compoundTag.getInt(drugSize) + 2);
                        }
                        player.getCooldowns().addCooldown(Items.crazy_drug.get(), (int) (350/(1+(this.nowLevel(stack))/100f)));
                    }
                }
            }
        }
    }
    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            if (!player.level().isClientSide){
                player.getAttributes().removeAttributeModifiers(ad(stack,this));
            }
        }
    }
    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> linkedHashMultimap = com.google.common.collect.LinkedHashMultimap.create();
        linkedHashMultimap.put(AttReg.effectNumber, new AttributeModifier(id, 9, AttributeModifier.Operation.ADD_VALUE));
        return linkedHashMultimap;
    }
    public Multimap<Holder<Attribute>, AttributeModifier> ad(ItemStack stack, Terror terror) {
        Multimap<Holder<Attribute>, AttributeModifier> modifiers = HashMultimap.create();
        CompoundTag compoundTag = stack.get(DataReg.tag);
        if (compoundTag!=null) {

            float s =0;
            if (compoundTag.getInt(drugTime) > 0) {
                s = compoundTag.getFloat(drugStronger);
            }
            modifiers.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ResourceLocation.parse(SeekingImmortalsMod.MODID + this.getDescriptionId()),
                    s, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
            modifiers.put(Attributes.ARMOR, new AttributeModifier(ResourceLocation.parse(SeekingImmortalsMod.MODID + this.getDescriptionId()),
                    s, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
            modifiers.put(Attributes.ATTACK_SPEED, new AttributeModifier(ResourceLocation.parse(SeekingImmortalsMod.MODID + this.getDescriptionId()),
                    s, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
            modifiers.put(Attributes.MAX_HEALTH, new AttributeModifier(ResourceLocation.parse(SeekingImmortalsMod.MODID + this.getDescriptionId()),
                    s, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
            modifiers.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(ResourceLocation.parse(SeekingImmortalsMod.MODID + this.getDescriptionId()),
                    s, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
            modifiers.put(AttReg.heal, new AttributeModifier(ResourceLocation.parse(SeekingImmortalsMod.MODID + this.getDescriptionId()),
                    s, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

        }
        return modifiers;
    }

    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        return !Handler.hascurio(slotContext.entity(), Items.biochemistry.get());
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("item.crazy_drug.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        tooltipComponents.add(Component.translatable("item.crazy_drug.tool.string.2").withStyle(ChatFormatting.DARK_RED));
        tooltipComponents.add(Component.literal(""));
        tooltipComponents.add(Component.translatable("item.crazy_drug.tool.string.3").withStyle(ChatFormatting.DARK_RED));
        tooltipComponents.add(Component.translatable("item.crazy_drug.tool.string.4").withStyle(ChatFormatting.DARK_RED));
        tooltipComponents.add(Component.literal(""));
        tooltipComponents.add(Component.translatable("item.crazy_drug.tool.string.5").withStyle(ChatFormatting.DARK_RED));
        tooltipComponents.add(Component.translatable("item.crazy_drug.tool.string.6").withStyle(ChatFormatting.DARK_RED));
        tooltipComponents.add(Component.translatable("item.crazy_drug.tool.string.7").withStyle(ChatFormatting.DARK_RED));
        tooltipComponents.add(Component.translatable("item.crazy_drug.tool.string.8").withStyle(ChatFormatting.DARK_RED));
        tooltipComponents.add(Component.literal(""));
        tooltipComponents.add(Component.translatable("item.crazy_drug.tool.string.9").withStyle(ChatFormatting.GOLD));
        tooltipComponents.add(Component.literal(""));
        tooltipComponents.add(Component.translatable("item.crazy_drug.tool.string.10")
                .append(String.format("%.2f",50*(1+(this.nowLevel(stack))/100f))).withStyle(ChatFormatting.GOLD));
        tooltipComponents.add(Component.translatable("item.crazy_drug.tool.string.11")
                .append(String.format("%.2f",5/(1+(this.nowLevel(stack))/100f))).withStyle(ChatFormatting.GOLD));
        tooltipComponents.add(Component.translatable("item.crazy_drug.tool.string.12")
                .append(String.format("%.2f",400*(1+(this.nowLevel(stack))/100f))).withStyle(ChatFormatting.GOLD));
        tooltipComponents.add(Component.translatable("item.crazy_drug.tool.string.14")
                .append(String.format("%.2f",350/(1+(this.nowLevel(stack))/100f))).withStyle(ChatFormatting.GOLD));

        CompoundTag compoundTag =stack.get(DataReg.tag);
        if (compoundTag!=null) {
            tooltipComponents.add((Component.translatable("item.crazy_drug.tool.string.13")).append(String.valueOf(compoundTag.getInt(drugSize))).withStyle(ChatFormatting.DARK_RED));

            float stronger = 50 * (1+(this.nowLevel(stack))/100f);
            float weak = compoundTag.getFloat(drugWeakness);
            weak /= (1 + (this.nowLevel(stack)/100f));
            stronger -= weak;

            tooltipComponents.add(Component.translatable("item.crazy_drug.tool.string.15")
                    .append(String.format("%.2f",stronger)).append("%").withStyle(ChatFormatting.GOLD));
        }else {
            tooltipComponents.add((Component.translatable("item.crazy_drug.tool.string.13")).append(String.valueOf(0)).withStyle(ChatFormatting.DARK_RED));
        }
    }

    @Override
    public ResourceLocation image(@Nullable LivingEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(SeekingImmortalsMod.MODID, "textures/gui/tooltip/small_fire.png");
    }

    @Override
    public int color(ItemStack stack) {
        return Light.ARGB.color(255,0,255,25);
    }
    @Override
    public int nowLevel(ItemStack stack) {
        CompoundTag compoundTag = stack.get(DataReg.tag);
        if (compoundTag != null) {
            return compoundTag.getInt(terrorName);
        }
        return 0;
    }
    /**
     *
     * @param stack 输入物品
     * @return 最大值是1+1（100%+100%）也就是2倍
     */
    @Override
    public int maxLevel(ItemStack stack) {
        return 100;
    }
}
