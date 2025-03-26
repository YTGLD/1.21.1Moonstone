package com.moonstone.moonstonemod.event;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.entity.extend.MoonTamableAnimal;
import com.moonstone.moonstonemod.event.mevent.AttackBloodEvent;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.AttReg;
import com.moonstone.moonstonemod.item.man.chemical_compound;
import com.moonstone.moonstonemod.item.man.muscle_conversion;
import com.moonstone.moonstonemod.item.man.phosphate_bond;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

public class ZombieEvent {
    @SubscribeEvent
    public  void LivingIncomingDamageEvent(LivingIncomingDamageEvent event){
        muscle_conversion.zombieAttack(event);
        phosphate_bond.damage5(event,20);
        chemical_compound.healDamage(event,10,12);
    }
    @SubscribeEvent
    public  void LivingHealEvent(LivingHealEvent event){
        chemical_compound.healZombie(event,15,12);
    }
    @SubscribeEvent
    public  void LivingDeathEvent(LivingDeathEvent event){
        phosphate_bond.dieZombie(event,10);
        chemical_compound.zombieDie(event,5);
    }
}
