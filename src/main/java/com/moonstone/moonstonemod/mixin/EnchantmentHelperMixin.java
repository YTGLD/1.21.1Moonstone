package com.moonstone.moonstonemod.mixin;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.items.Items;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Consumer;

@Mixin(EnchantmentHelper.class)
class EnchantmentHelperMixin {
    @Inject(at = @At("RETURN"), method = "modifyDamage", cancellable = true)
    private static void modifyDamage(ServerLevel level, ItemStack tool,
                                     Entity entity, DamageSource damageSource,
                                     float damage, CallbackInfoReturnable<Float> cir) {

        if (damageSource.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.rage_orb.get())){
                cir.setReturnValue(cir.getReturnValue()*1.4f);
            }
        }

    }
    @Inject(at = @At("RETURN"), method = "modifyKnockback", cancellable = true)
    private static void modifyKnockback(ServerLevel level, ItemStack tool, Entity entity,
                                        DamageSource damageSource, float knockback, CallbackInfoReturnable<Float> cir) {
        if (damageSource.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.rage_orb.get())){
                cir.setReturnValue(cir.getReturnValue()*0.2f);
            }
        }
    }
    @Inject(at = @At("RETURN"), method = "processProjectileCount", cancellable = true)
    private static void processProjectileCount(ServerLevel level, ItemStack tool, Entity entity, int projectileCount, CallbackInfoReturnable<Integer> cir) {
        if (entity instanceof Player player){
            if (Handler.hascurio(player, Items.rage_head.get())){
                cir.setReturnValue(cir.getReturnValue()+2);
            }
        }
    }
}
