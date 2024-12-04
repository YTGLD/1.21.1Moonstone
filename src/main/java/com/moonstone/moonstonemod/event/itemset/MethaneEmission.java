package com.moonstone.moonstonemod.event.itemset;

import com.moonstone.moonstonemod.Config;
import com.moonstone.moonstonemod.Handler;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public class MethaneEmission {
    public static void LivingHealEvent(LivingDeathEvent event){
        if (Handler.hasItemInList(event.getEntity(), Config.SERVER.listMethaneEmission.get())){
            event.getEntity().level().explode(null, event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), 9f, false, Level.ExplosionInteraction.NONE);
        }
    }
    public static void health(Player player, CallbackInfoReturnable<Float> cir){
        if (Handler.hasItemInList(player, Config.SERVER.listMethaneEmission.get())){
            cir.setReturnValue(cir.getReturnValue()* 0.5f);
        }
    }
}
