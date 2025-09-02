package com.moonstone.moonstonemod.event;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.entity.extend.MoonTamableAnimal;
import com.moonstone.moonstonemod.entity.nightmare.nightmare_giant;
import com.moonstone.moonstonemod.entity.ytgld;
import com.moonstone.moonstonemod.event.mevent.AttackBloodEvent;
import com.moonstone.moonstonemod.init.moonstoneitem.AttReg;
import com.moonstone.moonstonemod.init.moonstoneitem.EntityTs;
import com.moonstone.moonstonemod.item.man.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

import java.util.List;

public class ZombieEvent {
    @SubscribeEvent
    public  void LivingIncomingDamageEvent(LivingIncomingDamageEvent event){
        muscle_conversion.zombieAttack(event);
        chemical_compound.zombieDie(event,5);
        phosphate_bond.damage5(event,6);
        chemical_compound.healDamage(event,20,12);
    }
    @SubscribeEvent
    public  void LivingHealEvent(LivingHealEvent event){
        phosphate_bond.healZombie(event);
        chemical_compound.healZombie(event,25,12);
        white_blood_cells_are_abruptly_reduced.dieZombie(event,15);
    }
    @SubscribeEvent
    public  void LivingDeathEvent(LivingDeathEvent event){
        phosphate_bond.dieZombie(event,10);
        skin_glucose_fermentation.dieZombie(event,50);
    }
    @SubscribeEvent
    public  void LivingDeathEvent(LivingEntityUseItemEvent.Finish event){
        if (event.getEntity() instanceof Player player) {
            if (event.getItem().is(Items.OMINOUS_BOTTLE)){
                if (Handler.hascurio(player, com.moonstone.moonstonemod.init.items.Items.immortal.get())) {
                    Vec3 playerPos = player.position().add(0, 0.75, 0);
                    float range = 20;
                    List<nightmare_giant> entities =
                            player.level().getEntitiesOfClass(nightmare_giant.class,
                                    new AABB(playerPos.x - range,
                                            playerPos.y - range,
                                            playerPos.z - range,
                                            playerPos.x + range,
                                            playerPos.y + range,
                                            playerPos.z + range));

                    for (nightmare_giant nightmareGiant : entities) {
                        ytgld y = new ytgld(EntityTs.ytgld.get(), nightmareGiant.level());
                        y.setOwnerUUID(nightmareGiant.getOwnerUUID());
                        y.setPos(nightmareGiant.position());
                        nightmareGiant.level().addFreshEntity(y);

                        nightmareGiant.discard();
                        break;
                    }
                }
            }
        }
    }
}
