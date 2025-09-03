package com.ytgld.seeking_immortals.effect;

import com.ytgld.seeking_immortals.init.AttReg;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class dead extends MobEffect {
    public dead() {
        super(MobEffectCategory.HARMFUL, 0xffff0000);
        this.addAttributeModifier(Attributes.ATTACK_DAMAGE, ResourceLocation.parse("0de21b13-73b3-37ba-84f2-92c78b6712c2"),-0.05f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        this.addAttributeModifier(Attributes.ATTACK_SPEED,ResourceLocation.parse("0de21b13-73b3-37ba-84f2-92c78b6712c2"),-0.05f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED,ResourceLocation.parse("0de21b13-73b3-37ba-84f2-92c78b6712c2"),-0.05f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);

        this.addAttributeModifier(Attributes.ARMOR,ResourceLocation.parse("0de21b13-73b3-37ba-84f2-92c78b6712c2"),-0.2f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        this.addAttributeModifier(AttReg.heal,ResourceLocation.parse("0de21b13-73b3-37ba-84f2-92c78b6712c2"),-0.2f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);

    }
}


