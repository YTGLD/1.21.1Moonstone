package com.moonstone.moonstonemod.event;

import com.moonstone.moonstonemod.init.AttReg;
import com.moonstone.moonstonemod.init.DataReg;
import com.moonstone.moonstonemod.init.Effects;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.i.IBattery;
import com.moonstone.moonstonemod.item.BloodVirus.dna.bat_cell;
import com.moonstone.moonstonemod.item.TheNecora.bnabush.giant_boom_cell;
import com.moonstone.moonstonemod.item.blood.*;
import com.moonstone.moonstonemod.item.blood.magic.blood_magic_box;
import com.moonstone.moonstonemod.item.blood.magic.blood_sun;
import com.moonstone.moonstonemod.item.deceased_contract;
import com.moonstone.moonstonemod.item.maxitem.god_lead;
import com.moonstone.moonstonemod.item.maxitem.malice_die;
import com.moonstone.moonstonemod.item.nightmare.nightmare_head;
import com.moonstone.moonstonemod.item.nightmare.nightmare_heart;
import com.moonstone.moonstonemod.item.nightmare.nightmare_orb;
import com.moonstone.moonstonemod.item.plague.ALL.dna;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.CriticalHitEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

public class NewEvent {
    @SubscribeEvent
    public  void doBreak(LivingEntityUseItemEvent.Start event){
        dna.doBreak(event);
    }
    @SubscribeEvent
    public void LivingHealEvent(LivingHealEvent event) {
        nightmare_orb.nightmare_orb_heal(event);
        nightmare_head.LivingHealEvent(event);

        if (event.getEntity() instanceof LivingEntity living){
            if (living.getAttribute(AttReg.heal)!=null){
                float attack = (float) living.getAttribute(AttReg.heal).getValue();
                event.setAmount(event.getAmount()*(attack));
            }
        }

    }
    @SubscribeEvent
    public void BatteryName(ItemTooltipEvent event){
        ItemStack stack = event.getItemStack();
        if (stack.get(DataReg.tag) !=null){
            if (stack.get(DataReg.tag).getBoolean("ALLBattery")){
                event.getToolTip().add(Component.translatable("item.moonstone.battery").withStyle(ChatFormatting.GOLD));
            }
        }
        if (stack.getItem() instanceof IBattery){
            event.getToolTip().add(Component.translatable("item.moonstone.tooltip.battery").withStyle(ChatFormatting.GOLD));

        }
    }
    @SubscribeEvent
    public void LivingHurtEvent(LivingIncomingDamageEvent event){
        nightmare_heart.NigH(event);
        nightmare_head.headHurt(event);
        bat_cell.Bat(event);
        max_sword.hurt(event);
        max_eye.A(event);
        max_blood_eye.Att(event);
        blood_amout.Hurt(event);
        giant_boom_cell.Boom(event);
        deceased_contract.attack(event);
        malice_die.att(event);
        god_lead.hurtS(event);
        dna.hur(event);
        if (event.getSource().getEntity() instanceof LivingEntity living){
            if (living.getAttribute(AttReg.alL_attack)!=null){
                float attack = (float) living.getAttribute(AttReg.alL_attack).getValue();
                event.setAmount(event.getAmount()*(attack));
            }
        }

    }
    @SubscribeEvent
    public void soulbattery(CriticalHitEvent event) {
        if (event.getEntity() instanceof Player living){
            if (living.getAttribute(AttReg.cit)!=null){
                float attack = (float) living.getAttribute(AttReg.cit).getValue();
                event.setDamageMultiplier(event.getDamageMultiplier()*(attack));
            }
        }

    }
    @SubscribeEvent
    public void EntityInteract(PlayerInteractEvent.RightClickItem event){
        max_blood_cube.RightClickItem(event);
    }
    @SubscribeEvent
    public  void SwordEventLivingEntityUseItemEvent(LivingEntityUseItemEvent.Stop event){


    }
    @SubscribeEvent
    public void LivingHealEvent(LivingDeathEvent event) {
        nightmare_heart.Nig(event);
        nightmare_head.LivingDeathEvent(event);
        max_eye.Die(event);
        blood_snake.Die(event);
        max_sword.die(event);
        the_prison_of_sin.LivingDeathEvent(event);
        blood_magic_box.Did(event);
        deceased_contract.Did(event);
        blood_sun.Did(event);
        dna.dieD(event);
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
