package com.moonstone.moonstonemod.event.itemset;

import com.moonstone.moonstonemod.Config;
import com.moonstone.moonstonemod.Handler;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.CriticalHitEvent;

public class BatteryMan {

    public static void healEvent(LivingHealEvent event){
        if (Handler.hasItemInList(event.getEntity(), Config.SERVER.listBattery.get())){
            event.setAmount(event.getAmount() * 0.7f);
        }
    }
    public static void hurtEvent(LivingIncomingDamageEvent event){
        if (event.getEntity() instanceof Player player) {
            if (Handler.hasItemInList(player, Config.SERVER.listBattery.get())) {
                event.setAmount(event.getAmount()*0.95f);
            }
        }
    }
    public static void attackEvent(LivingIncomingDamageEvent event){
        if (event.getSource().getEntity()!=null
                &&event.getSource().getEntity() instanceof Player player) {

            if (Handler.hasItemInList(player, Config.SERVER.listBattery.get())) {
                event.setAmount(event.getAmount()*1.1f);
            }
        }
    }
    public static void criticalHitEvent(CriticalHitEvent event){
        Player player = event.getEntity();

        if (Handler.hasItemInList(player, Config.SERVER.listBattery.get())) {
            event.setDamageMultiplier(event.getDamageMultiplier() * 1.1f);
        }
    }
}
