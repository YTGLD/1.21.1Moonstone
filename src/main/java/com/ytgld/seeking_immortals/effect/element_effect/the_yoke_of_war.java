package com.ytgld.seeking_immortals.effect.element_effect;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class the_yoke_of_war  extends MobEffect {
    public the_yoke_of_war() {
        super(MobEffectCategory.NEUTRAL, 0xffff00ff);
        this.addAttributeModifier(Attributes.ATTACK_DAMAGE, ResourceLocation.parse("9ba8005a-2f55-38dd-9454-3d3b1d67bff1"),0.1f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED, ResourceLocation.parse("9ba8005a-2f55-38dd-9454-3d3b1d67bff1"),0.05f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);


        this.addAttributeModifier(Attributes.ARMOR, ResourceLocation.parse("9ba8005a-2f55-38dd-9454-3d3b1d67bff1"),-0.1f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        this.addAttributeModifier(Attributes.MAX_HEALTH, ResourceLocation.parse("9ba8005a-2f55-38dd-9454-3d3b1d67bff1"),-0.05f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);

    }
}
