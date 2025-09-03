package com.moonstone.moonstonemod.mixin.si_mixin;

import com.ytgld.seeking_immortals.Handler;
import com.ytgld.seeking_immortals.init.Items;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow public abstract double getAttributeValue(Holder<Attribute> attribute);

    @Shadow public abstract double getAttributeBaseValue(Holder<Attribute> attribute);

    @Inject(at = @At("RETURN"), method = "getMaxHealth", cancellable = true)
    private void SeekingImmortalscreateAttributes(CallbackInfoReturnable<Float> cir){
        LivingEntity living = (LivingEntity) (Object) this;
        if (Handler.hascurio(living, Items.apple.get())){
            cir.setReturnValue(30f);
        }
        if (Handler.hascurio(living, Items.falling_immortals.get())){
            cir.setReturnValue(30f);
        }
    }
    @Inject(at = @At("RETURN"), method = "canBeAffected", cancellable = true)
    private void canBeAffected(MobEffectInstance effectInstance, CallbackInfoReturnable<Boolean> cir){
        LivingEntity living = (LivingEntity) (Object) this;
        if (Handler.hascurio(living, Items.nightmare_base_black_eye.get())||Handler.hascurio(living, Items.the_divine_fall_ring.get())) {
            if (effectInstance.is(MobEffects.BLINDNESS)||effectInstance.is(MobEffects.DARKNESS)) {
                cir.setReturnValue(false);
            }
        }
        if (Handler.hascurio(living, Items.bone_or_god.get())) {
            if (!effectInstance.getEffect().value().isBeneficial()) {
                cir.setReturnValue(false);
            }
        }
    }
    @Inject(at = @At("RETURN"), method = "getArmorValue", cancellable = true)
    private void getArmorValue(CallbackInfoReturnable<Integer> cir){
        LivingEntity living = (LivingEntity) (Object) this;
        if (Handler.hascurio(living, Items.falling_immortals.get())){
            cir.setReturnValue(10);
        }

    }
    @Inject(at = @At("RETURN"), method = "getAttributeValue", cancellable = true)
    private void getAttributeValue(Holder<Attribute> attribute, CallbackInfoReturnable<Double> cir) {
        LivingEntity living = (LivingEntity) (Object) this;
        if (Handler.hascurio(living, Items.falling_immortals.get())){
            cir.setReturnValue(this.getAttributeBaseValue(attribute));
        }

    }
}
