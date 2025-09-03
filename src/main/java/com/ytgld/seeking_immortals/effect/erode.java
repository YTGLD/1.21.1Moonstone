package com.ytgld.seeking_immortals.effect;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class erode extends MobEffect {
    public erode() {
        super(MobEffectCategory.HARMFUL, 0xffff00ff);
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED,ResourceLocation.parse("da37c59b-11ef-4c2f-8eb8-ca8cfe9b69c4"),-0.2f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        this.addAttributeModifier(Attributes.JUMP_STRENGTH,ResourceLocation.parse("da37c59b-11ef-4c2f-8eb8-ca8cfe9b69c4"),-0.2f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);

    }
}
