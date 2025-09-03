package com.ytgld.seeking_immortals.event;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.Event;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

public class CurioDeathAtMeEvent extends Event {
    private final Player player ;
    private final LivingEntity scr ;
    private final ItemStack stack ;
    private final LivingDeathEvent event ;
    public CurioDeathAtMeEvent(Player player, LivingEntity scr, ItemStack stack, LivingDeathEvent event){
        this.player = player;
        this.scr = scr;
        this.stack = stack;
        this.event = event;
    }

    public Player getPlayer() {
        return player;
    }

    public ItemStack getStack() {
        return stack;
    }

    public LivingDeathEvent getEvent() {
        return event;
    }

    public LivingEntity getScr() {
        return scr;
    }
}

