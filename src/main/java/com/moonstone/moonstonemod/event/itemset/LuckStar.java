package com.moonstone.moonstonemod.event.itemset;

import com.moonstone.moonstonemod.Config;
import com.moonstone.moonstonemod.Handler;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingExperienceDropEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

public class LuckStar {
    public static void experienceDropEvent(LivingExperienceDropEvent event){
        if (Handler.hasItemInList(event.getAttackingPlayer(), Config.SERVER.listEctoplasm.get())){
            event.setDroppedExperience(event.getDroppedExperience()*2);
        }
    }
    public static void hurtEvent(LivingIncomingDamageEvent event){
        if (event.getEntity() instanceof Player player) {
            if (Handler.hasItemInList(player, Config.SERVER.listEctoplasm.get())) {
                event.setAmount(event.getAmount()*1.25f);
            }
        }
    }
}
