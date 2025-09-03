package com.ytgld.seeking_immortals.item.nightmare.base;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.seeking_immortals.Handler;
import com.ytgld.seeking_immortals.SeekingImmortalsMod;
import com.ytgld.seeking_immortals.event.CurioKillEvent;
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
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
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
 * 刺鼻不死药：
 * <p>
 * <p>
 * 	药瓶装满时能喝3次
 * <p>
 * 	每佩戴1个罪孽都会增加容量
 * <p>
 * <p>
 * 	按下R键喝下瓶中的药水
 * <p>
 * 	来增加治疗，速度，最大生命值和攻击速度
 * <p>
 * <p>
 * 	同时恢复40%生命值
 * <p>
 * <p>
 * 	杀死生物有可能恢复瓶中的药水量
 * <p>
 * <p>
 * 	喝下的药水次数越多，属性加成越好，持续时间越长
 * <p>
 * 	目前属性：20%
 * <p>
 * 	目前持续时间：10秒
 * <p>
 */
public class biochemistry extends nightmare {

    public static final String giveName = "giveNameBiochemistry";
    public static final String giveNameEnd = "giveNameEndBiochemistry";




    //药水数量
    public static final String effectSize ="biochemistryStringEffectSize";
    //持续时间
    public static final String effectTime ="biochemistryStringEffectTime";
    //药水效果
    public static final String effectStronger ="biochemistryStringEffectStronger";
    public static void addEffectForBiochemistry(Player player, ItemStack stack){
        if (Handler.hascurio(player, Items.biochemistry.get())) {
            if (stack.is(Items.biochemistry.get())){
                if (stack.getItem() instanceof biochemistry biochemistry) {
                    CompoundTag compoundTag = stack.get(DataReg.tag);
                    if (compoundTag != null) {
                        //有药水
                        if (compoundTag.getInt(effectSize) > 0) {

                            //增加物品经验值/物品等级
                            if (compoundTag.getInt(terrorName) < biochemistry.maxLevel(stack)) {
                                compoundTag.putInt(terrorName, compoundTag.getInt(terrorName) + 1);
                            }
                            //最大175%
                            //1.75 * 0.2f
                            int lvl = biochemistry.nowLevel(stack);
                            float s = 1 + (lvl / 100f);
                            //最大0.35f
                            compoundTag.putFloat(effectStronger, 0.275F * s);
                            //最大时间现在是350刻*1.75
                            //最大350刻
                            compoundTag.putInt(effectTime, (int) (350 * s));



                            player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.WITCH_DRINK, SoundSource.NEUTRAL, 1F, 1F);
                            player.heal(player.getMaxHealth() * 0.4f);
                            //减少药水数量
                            compoundTag.putInt(effectSize, compoundTag.getInt(effectSize) - 1);
                        }
                    }
                }
            }
        }
    }
    public static void CuriosDie(CurioKillEvent curioKillEvent){
        Player player = curioKillEvent.getScr() ;
        ItemStack stack = curioKillEvent .getStack();
        if (Handler.hascurio(player, Items.biochemistry.get())) {
            CompoundTag compoundTag = stack.get(DataReg.tag);
            if (compoundTag != null){
                int value = (int) player.getAttributeValue(AttReg.effectNumber);
                if (Mth.nextInt(RandomSource.create(),1,100)<=25) {
                    if (compoundTag.getInt(effectSize) < value) {
                        compoundTag.putInt(effectSize, compoundTag.getInt(effectSize) + 1);
                        player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARMOR_EQUIP_GENERIC, SoundSource.NEUTRAL, 1F, 1F);
                    }
                }
            }
        }
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        super.curioTick(slotContext, stack);
        if (slotContext.entity() instanceof Player player) {
            if (!player.level().isClientSide) {
                if (this.nowLevel(stack)>=this.maxLevel(stack)){
                    crazy_drug.give(stack,player);
                }

                CompoundTag compoundTag = stack.get(DataReg.tag);
                player.getAttributes().addTransientAttributeModifiers(ad(stack,this));
                if (compoundTag != null) {
                    int value = (int) player.getAttributeValue(AttReg.effectNumber);


                    if (!player.getCooldowns().isOnCooldown(Items.biochemistry.get())){
                        if (compoundTag.getInt(effectSize) < value) {
                            compoundTag.putInt(effectSize, compoundTag.getInt(effectSize) + 1);
                            player.getCooldowns().addCooldown(Items.biochemistry.get(), 600);
                        }
                    }


                    if (compoundTag.getInt(effectSize) > value) {
                        compoundTag.putInt(effectSize, value);
                    }



                    if (compoundTag.getInt(effectTime) <= 0) {
                        compoundTag.putFloat(effectStronger, 0);
                    } else {
                        compoundTag.putInt(effectTime, compoundTag.getInt(effectTime) - 1);
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
        linkedHashMultimap.put(AttReg.effectNumber, new AttributeModifier(id, 2, AttributeModifier.Operation.ADD_VALUE));
        return linkedHashMultimap;
    }
    public Multimap<Holder<Attribute>, AttributeModifier> ad(ItemStack stack, Terror terror) {
        Multimap<Holder<Attribute>, AttributeModifier> modifiers = HashMultimap.create();
        CompoundTag compoundTag = stack.get(DataReg.tag);
        if (compoundTag!=null) {

            float s =0;
            if (compoundTag.getInt(effectTime) > 0) {
                s = compoundTag.getFloat(effectStronger);
            }
            modifiers.put(Attributes.MAX_HEALTH, new AttributeModifier(ResourceLocation.parse(SeekingImmortalsMod.MODID + this.getDescriptionId()),
                    s, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
            modifiers.put(Attributes.ATTACK_SPEED, new AttributeModifier(ResourceLocation.parse(SeekingImmortalsMod.MODID + this.getDescriptionId()),
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
        return !Handler.hascurio(slotContext.entity(), Items.crazy_drug.get());
    }

    /**
     * 刺鼻不死药：
     * <p>
     * <p>
     * 	药瓶装满时能喝3次
     * <p>
     * 	每佩戴1个罪孽都会增加容量
     * <p>
     * <p>
     * 	按下R键喝下瓶中的药水
     * <p>
     * 	来增加治疗，速度，最大生命值和攻击速度
     * <p>
     * <p>
     * 	同时恢复40%生命值
     * <p>
     * <p>
     * 	杀死生物有可能恢复瓶中的药水量
     * <p>
     * <p>
     * 	喝下的药水次数越多，属性加成越好，持续时间越长
     * <p>
     * <p>
     *
     * 	目前属性：%
     * <p>
     * 	目前持续时间：秒
     * <p>
     */
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("item.biochemistry.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        tooltipComponents.add(Component.translatable("item.biochemistry.tool.string.2").withStyle(ChatFormatting.DARK_RED));
        tooltipComponents.add(Component.literal(""));
        tooltipComponents.add(Component.translatable("item.biochemistry.tool.string.3").withStyle(ChatFormatting.DARK_RED));
        tooltipComponents.add(Component.translatable("item.biochemistry.tool.string.4").withStyle(ChatFormatting.DARK_RED));
        tooltipComponents.add(Component.literal(""));
        tooltipComponents.add(Component.translatable("item.biochemistry.tool.string.5").withStyle(ChatFormatting.DARK_RED));
        tooltipComponents.add(Component.literal(""));
        tooltipComponents.add(Component.translatable("item.biochemistry.tool.string.6").withStyle(ChatFormatting.DARK_RED));
        tooltipComponents.add(Component.translatable("item.biochemistry.tool.string.11").withStyle(ChatFormatting.DARK_RED));
        tooltipComponents.add(Component.literal(""));
        tooltipComponents.add(Component.translatable("item.biochemistry.tool.string.7").withStyle(ChatFormatting.GOLD));
        tooltipComponents.add(Component.literal(""));
        tooltipComponents.add(Component.translatable("item.biochemistry.tool.string.8").append(String.format("%.2f",27.5F * (1+this.nowLevel(stack)/100f))).append("%").withStyle(ChatFormatting.GOLD));
        tooltipComponents.add(Component.translatable("item.biochemistry.tool.string.9").append(String.format("%.2f",350F* (1+this.nowLevel(stack)/100f))).append("t").withStyle(ChatFormatting.GOLD));
        CompoundTag compoundTag =stack.get(DataReg.tag);
        if (compoundTag!=null) {
            tooltipComponents.add((Component.translatable("item.biochemistry.tool.string.10")).append(String.valueOf(compoundTag.getInt(effectSize))).withStyle(ChatFormatting.DARK_RED));
        }else {
            tooltipComponents.add((Component.translatable("item.biochemistry.tool.string.10")).append(String.valueOf(0)).withStyle(ChatFormatting.DARK_RED));
        }
    }
    @Override
    public ResourceLocation image(@Nullable LivingEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(SeekingImmortalsMod.MODID, "textures/gui/tooltip/small_fire.png");
    }
    @Override
    public int nowLevel(ItemStack stack) {
        CompoundTag compoundTag = stack.get(DataReg.tag);
        if (compoundTag != null) {
            return compoundTag.getInt(terrorName);
        }
        return 0;
    }
    @Override
    public int color(ItemStack stack) {
        return Light.ARGB.color(255,255,0,25);
    }

    /**
     *
     * @param stack 输入的物品
     * @return 最大增加的数量，这里是175%（也就是100%+75%）
     */
    @Override
    public int maxLevel(ItemStack stack) {
        return 75;
    }
}
