package com.ytgld.seeking_immortals.event;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.Event;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

public class CurioAttackEvent extends Event {
    private final Player player ;
    private final ItemStack stack ;
    private final LivingIncomingDamageEvent event ;
    public CurioAttackEvent(Player player, ItemStack stack, LivingIncomingDamageEvent event){
        this.player = player;
        this.stack = stack;
        this.event = event;
    }

    public Player getPlayer() {
        return player;
    }

    public ItemStack getStack() {
        return stack;
    }

    public LivingIncomingDamageEvent getEvent() {
        return event;
    }
}
