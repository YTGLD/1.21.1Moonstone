package com.moonstone.moonstonemod.event.itemset;

import com.moonstone.moonstonemod.Config;
import com.moonstone.moonstonemod.Handler;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

public class EctoplasmLuckStar {
    public static void healEvent(LivingHealEvent event){
        if (Handler.hasItemInList(event.getEntity(), Config.SERVER.listEctoplasmLuckStar.get())){
            event.setAmount(event.getAmount() * 1.2f);
        }
    }
    public static void hurtEvent(LivingIncomingDamageEvent event){
        if (event.getEntity() instanceof Player player) {
            if (Handler.hasItemInList(player, Config.SERVER.listEctoplasmLuckStar.get())) {
                event.setAmount(event.getAmount()*0.95f);
            }
        }
    }
    public static void attackEvent(LivingIncomingDamageEvent event){
        if (event.getSource().getEntity()!=null
                &&event.getSource().getEntity() instanceof Player player) {

            if (Handler.hasItemInList(player, Config.SERVER.listEctoplasmLuckStar.get())) {
                event.setAmount(event.getAmount()*0.8f);
            }
        }
    }
}
