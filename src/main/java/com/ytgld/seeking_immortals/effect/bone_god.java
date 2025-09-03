package com.ytgld.seeking_immortals.effect;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class bone_god extends MobEffect {
    public bone_god() {
        super(MobEffectCategory.HARMFUL, 0xffff0000);
        this.addAttributeModifier(Attributes.ATTACK_DAMAGE,ResourceLocation.parse("0e2ef6d7-b626-37a3-b614-05b4e8649bce"),
                -0.025f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);

    }
}

