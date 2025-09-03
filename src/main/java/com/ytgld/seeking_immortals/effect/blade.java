package com.ytgld.seeking_immortals.effect;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class blade  extends MobEffect {
    public blade() {
        super(MobEffectCategory.HARMFUL, 0xffff0000);
        this.addAttributeModifier(Attributes.ATTACK_DAMAGE, ResourceLocation.parse("c35c79fa-c14d-3173-a5f4-409e5a0c65eb"),0.05f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
    }
}

