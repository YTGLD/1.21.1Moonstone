package com.moonstone.moonstonemod.item.nightmare;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.Effects;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.i.INightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import java.util.List;

public class nightmarewater extends Item implements INightmare {

    public nightmarewater() {
        super(new Properties().stacksTo(1).rarity(Rarity.RARE).food(
                new FoodProperties.Builder().alwaysEdible().nutrition(0).saturationModifier(0.0f).build()));
    }
    @Override
    public UseAnim getUseAnimation(ItemStack p_41452_) {
        return UseAnim.DRINK;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack s, Level level, LivingEntity living){
        living.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 300, 0));
        living.addEffect(new MobEffectInstance(MobEffects.POISON, 300, 0));
        living.addEffect(new MobEffectInstance(MobEffects.UNLUCK, 300, 0));
        living.addEffect(new MobEffectInstance(MobEffects.GLOWING, 300, 0));
        living.addEffect(new MobEffectInstance(MobEffects.HUNGER, 300, 2));
        living.addEffect(new MobEffectInstance(Effects.blood, 300, 0));
        living.addEffect(new MobEffectInstance(Effects.rage, 300, 0));
        living.addEffect(new MobEffectInstance(Effects.rage, 300, 0));
        living.addEffect(new MobEffectInstance(Effects.blood, 300, 0));
        living.addEffect(new MobEffectInstance(MobEffects.WIND_CHARGED, 300, 0));
        living.addEffect(new MobEffectInstance(MobEffects.WEAVING, 300, 0));
        living.addEffect(new MobEffectInstance(MobEffects.OOZING, 300, 0));
        living.addEffect(new MobEffectInstance(MobEffects.INFESTED, 300, 0));
        if (Handler.hascurio(living, Items.nightmareeye.get())) {
            living.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 1200, 2));
            living.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1200, 1));
            living.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 1200, 2));
            living.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1200, 1));
            living.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1200, 0));
            living.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 1200, 2));
            living.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 1200, 0));
            living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1200, 1));
        }else {
            living.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 300, 0));
            living.addEffect(new MobEffectInstance(MobEffects.POISON, 300, 0));
            living.addEffect(new MobEffectInstance(MobEffects.UNLUCK, 300, 0));
            living.addEffect(new MobEffectInstance(MobEffects.GLOWING, 300, 0));
            living.addEffect(new MobEffectInstance(MobEffects.HUNGER, 300, 2));
        }


        return this.getDefaultInstance();
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);

        pTooltipComponents.add(Component.translatable("item.nightmarewater.tool.string").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmarewater.tool.string.1").withStyle(ChatFormatting.DARK_RED));


    }
}



