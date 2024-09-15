package com.moonstone.moonstonemod.event;

import com.moonstone.moonstonemod.init.Effects;
import com.moonstone.moonstonemod.item.BloodVirus.dna.bat_cell;
import com.moonstone.moonstonemod.item.blood.max_blood_cube;
import com.moonstone.moonstonemod.item.blood.max_blood_eye;
import com.moonstone.moonstonemod.item.blood.max_eye;
import com.moonstone.moonstonemod.item.blood.max_sword;
import com.moonstone.moonstonemod.item.nightmare.nightmare_head;
import com.moonstone.moonstonemod.item.nightmare.nightmare_heart;
import com.moonstone.moonstonemod.item.nightmare.nightmare_orb;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

public class NewEvent {
    @SubscribeEvent
    public void LivingHealEvent(LivingHealEvent event) {
        nightmare_orb.nightmare_orb_heal(event);
        nightmare_head.LivingHealEvent(event);
    }
    @SubscribeEvent
    public void LivingHurtEvent(LivingIncomingDamageEvent event){
        nightmare_heart.NigH(event);
        nightmare_head.headHurt(event);
        bat_cell.Bat(event);
        max_sword.hurt(event);
        max_eye.A(event);
        max_blood_eye.Att(event);
    }
    @SubscribeEvent
    public void EntityInteract(PlayerInteractEvent.RightClickItem event){
        max_blood_cube.RightClickItem(event);
    }

    @SubscribeEvent
    public void LivingHealEvent(LivingDeathEvent event) {
        nightmare_heart.Nig(event);
        nightmare_head.LivingDeathEvent(event);
        max_eye.Die(event);
        max_sword.die(event);

    }
    @SubscribeEvent
    public void EffectTick(EntityTickEvent.Post event) {
        if (event.getEntity() instanceof LivingEntity living){
            if (living.hasEffect(Effects.blood)&&living.getEffect(Effects.blood)!=null) {
                if (!living.level().isClientSide){
                    if (living.tickCount%20==0){
                        living.hurt(living.damageSources().magic(),living.getEffect(Effects.blood).getAmplifier());
                        living.invulnerableTime = 0;
                    }
                }
            }
            if (living.hasEffect(Effects.rage)&&living.getEffect(Effects.rage)!=null) {
                if (!living.level().isClientSide){
                    if (living.tickCount%10==0){
                        living.hurt(living.damageSources().magic(),living.getEffect(Effects.rage).getAmplifier()+0.5f);
                        living.invulnerableTime = 0;
                    }
                }
            }
        }
    }
}
