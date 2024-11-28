package com.moonstone.moonstonemod.mixin;

import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.init.moonstoneitem.DataReg;
import com.moonstone.moonstonemod.item.nanodoom.buyme.wind_and_rain;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public class ItemStackMixin {

    @Inject(at = @At("HEAD"), method = "getUseDuration", cancellable = true)
    public void moonstone$getUseDuration(LivingEntity entity, CallbackInfoReturnable<Integer> cir) {
        ItemStack stack = (ItemStack) (Object) this;
        CompoundTag tag = stack.get(DataReg.tag);
        if (tag != null) {
            if (tag.getBoolean(wind_and_rain.wind)) {
                cir.setReturnValue(72000);
            }
        }
    }
    @Inject(at = @At("HEAD"), method = "getUseAnimation", cancellable = true)
    public void moon$getUseAnimation(CallbackInfoReturnable<UseAnim> cir) {
        ItemStack stack = (ItemStack) (Object) this;
        CompoundTag tag = stack.get(DataReg.tag);
        if (tag != null) {
            if (tag.getBoolean(wind_and_rain.wind)) {
                cir.setReturnValue(UseAnim.BOW);
            }
        }
    }
    @Inject(at = @At("HEAD"), method = "hasFoil", cancellable = true)
    public void hasFoil(CallbackInfoReturnable<Boolean> cir) {
        ItemStack stack = (ItemStack) (Object) this;
        if (BuiltInRegistries.ITEM.getKey(stack.getItem()).getNamespace().equals(MoonStoneMod.MODID)){
            cir.setReturnValue(false);
        }
    }
}

