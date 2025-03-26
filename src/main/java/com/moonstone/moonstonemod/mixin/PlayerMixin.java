package com.moonstone.moonstonemod.mixin;

import com.moonstone.moonstonemod.init.moonstoneitem.AttReg;
import com.moonstone.moonstonemod.item.man.run_dna;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public class PlayerMixin {
    @Inject(at = @At("RETURN"), method = "createAttributes", cancellable = true)
    private static void createAttributes(CallbackInfoReturnable<AttributeSupplier.Builder> cir){
        cir.setReturnValue(cir.getReturnValue()
                .add(AttReg.alL_attack,1)
                .add(AttReg.cit,1)
                .add(AttReg.heal,1)
                .add(AttReg.hurt,1)
                .add(AttReg.dig,1)
                .add(AttReg.owner_blood_time,1)
                .add(AttReg.owner_blood_speed,1)
                .add(AttReg.owner_blood_damage,1)
                .add(AttReg.owner_blood_attack_speed,1)
                .add(AttReg.zombie_attack_damage,1)
        );
    }

    @Inject(at = @At("RETURN"), method = "getSpeed", cancellable = true)
    private void getSpeed(CallbackInfoReturnable<Float> cir) {
        if ((Player) (Object) this instanceof Player player) {
            run_dna.protein(cir, player);
            run_dna.phosphorylation(cir,player );

        }
    }
}
