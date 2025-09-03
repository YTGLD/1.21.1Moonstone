package com.ytgld.seeking_immortals.event.key;

import com.ytgld.seeking_immortals.init.Keys;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

public class ClientEvent {
    @SubscribeEvent
    public void onClientTick(ClientTickEvent.Post evt) {
        if (Keys.KEY_MAPPING_LAZY_R.consumeClick()) {
            PacketDistributor.sendToServer(new UseCurio(ItemStack.EMPTY));
        }
    }

}
