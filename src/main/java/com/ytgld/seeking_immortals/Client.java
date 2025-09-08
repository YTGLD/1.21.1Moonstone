package com.ytgld.seeking_immortals;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import org.jetbrains.annotations.ApiStatus;
import com.moonstone.tbl.client.event.ClientRegistrationEvents;

@ApiStatus.Internal
@Mod(value = SeekingImmortalsMod.MODID, dist = Dist.CLIENT)
public class Client {
    public Client(IEventBus modEventBus, ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
        {
            ClientRegistrationEvents.initClient(modEventBus);
        }
    }
}
