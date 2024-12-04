package com.moonstone.moonstonemod.event.itemset;

import com.moonstone.moonstonemod.Config;
import com.moonstone.moonstonemod.Handler;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.CriticalHitEvent;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public class LifeManSuper {
    public static void healEvent(LivingHealEvent event){
        if (Handler.hasItemInList(event.getEntity(), Config.SERVER.listLifeManSuper.get())){
            event.setAmount(event.getAmount() * 1.07f);
        }
    }
    public static void attackEvent(LivingIncomingDamageEvent event){
        if (event.getSource().getEntity()!=null
                &&event.getSource().getEntity() instanceof Player player) {

            if (Handler.hasItemInList(player, Config.SERVER.listLifeManSuper.get())) {
                event.setAmount(event.getAmount()*1.07f);
            }
        }
    }
    public static void criticalHitEvent(CriticalHitEvent event){
        Player player = event.getEntity();

        if (Handler.hasItemInList(player, Config.SERVER.listLifeManSuper.get())) {
            event.setDamageMultiplier(event.getDamageMultiplier() * 1.07f);
        }
    }
    public static void health(Player player, CallbackInfoReturnable<Float> cir){
        if (Handler.hasItemInList(player, Config.SERVER.listLifeManSuper.get())){
            cir.setReturnValue(cir.getReturnValue()* 1.07f);
        }
    }
    public static void speed (Player player, CallbackInfoReturnable<Float> cir){
        if (Handler.hasItemInList(player, Config.SERVER.listLifeManSuper.get())){
            cir.setReturnValue(cir.getReturnValue()* 1.07f);
        }
    }
}
