package com.moonstone.moonstonemod.mixin;

import com.moonstone.moonstonemod.init.Enchants;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public abstract class EnchantmentMixin {

    @Inject(at = @At("HEAD"), method = "getFullname", cancellable = true)
    private static void getFullname(Holder<Enchantment> enchantment, int level, CallbackInfoReturnable<Component> cir){
        if (Enchants.isMoonEnchant(enchantment,Enchants.Terror)){
            cir.setReturnValue(Component.translatable("enchantment.moonstone.terror").append(" "+level).withStyle(ChatFormatting.RED));
        }
        if (Enchants.isMoonEnchant(enchantment,Enchants.malice)){
            cir.setReturnValue(Component.translatable("enchantment.moonstone.malice").append(" "+level).withStyle(ChatFormatting.RED));
        }
        if (Enchants.isMoonEnchant(enchantment,Enchants.threat)){
            cir.setReturnValue(Component.translatable("enchantment.moonstone.threat").append(" "+level).withStyle(ChatFormatting.RED));
        }
    }
}
