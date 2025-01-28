package com.moonstone.moonstonemod.mixin;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.items.Items;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow
    public abstract Vec3 position();

    @Inject(at = @At("RETURN"), method = "isInvulnerableTo", cancellable = true)
    public void mhead(DamageSource p_20122_, CallbackInfoReturnable<Boolean> cir) {
        if ((Entity) (Object) this instanceof Player player) {
            if (Handler.hascurio(player, Items.nightmare_base_redemption_degenerate.get())) {
                if (p_20122_.is(DamageTypes.MAGIC) ||
                        p_20122_.is(DamageTypes.FALL) ||
                        p_20122_.is(DamageTypes.ON_FIRE) ||
                        p_20122_.is(DamageTypes.LAVA) ||
                        p_20122_.is(DamageTypes.IN_FIRE)) {
                    cir.setReturnValue(true);
                }
            }
        }

        if (p_20122_.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.nine_sword_book.get())) {
                if (!BuiltInRegistries.ITEM.getKey(player.getMainHandItem().getItem().asItem()).getPath().contains("sword")) {
                    cir.setReturnValue(true);
                }
                if (!(player.getMainHandItem().getItem() instanceof SwordItem)) {
                    cir.setReturnValue(true);
                }
            }
            if (Handler.hascurio(player, Items.mhead.get())) {
                cir.setReturnValue(true);
            }
            if (Handler.hascurio(player, Items.the_blood_book.get())) {
                if (!(player.getMainHandItem().isEmpty())) {
                    cir.setReturnValue(true);
                }
            }
        }
    }
}
