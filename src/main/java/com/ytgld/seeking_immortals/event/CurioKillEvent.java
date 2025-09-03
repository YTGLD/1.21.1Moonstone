package com.ytgld.seeking_immortals.event;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.Event;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

public class CurioKillEvent extends Event {
    private final LivingEntity living ;
    private final Player scr ;
    private final ItemStack stack ;
    private final LivingDeathEvent event ;
    public CurioKillEvent(LivingEntity living, Player scr, ItemStack stack, LivingDeathEvent event){
        this.living = living;
        this.scr = scr;
        this.stack = stack;
        this.event = event;
    }

    public ItemStack getStack() {
        return stack;
    }

    public LivingDeathEvent getEvent() {
        return event;
    }

    public LivingEntity getLiving() {
        return living;
    }

    public Player getScr() {
        return scr;
    }
}


