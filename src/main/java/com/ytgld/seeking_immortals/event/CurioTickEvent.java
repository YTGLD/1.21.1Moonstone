package com.ytgld.seeking_immortals.event;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.Event;

public class CurioTickEvent  extends Event {
    private final Player player ;
    private final ItemStack stack ;
    public CurioTickEvent(Player player, ItemStack stack){
        this.player = player;
        this.stack = stack;
    }

    public Player getPlayer() {
        return player;
    }

    public ItemStack getStack() {
        return stack;
    }
}

