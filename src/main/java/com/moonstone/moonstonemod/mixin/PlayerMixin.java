package com.moonstone.moonstonemod.mixin;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.event.NewEvent;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.AttReg;
import com.moonstone.moonstonemod.init.moonstoneitem.DataReg;
import com.moonstone.moonstonemod.item.man.run_dna;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.objectweb.asm.Handle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.Map;

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
