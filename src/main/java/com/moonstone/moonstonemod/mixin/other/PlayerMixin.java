package com.moonstone.moonstonemod.mixin.other;

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

    @Inject(at = @At("RETURN"), method = "getSpeed", cancellable = true)
    private void getSpeed(CallbackInfoReturnable<Float> cir) {
        if ((Player) (Object) this instanceof Player player) {
            run_dna.protein(cir, player);
            run_dna.phosphorylation(cir,player );

        }
    }
}
