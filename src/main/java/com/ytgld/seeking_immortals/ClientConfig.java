package com.ytgld.seeking_immortals;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class ClientConfig {
    public static final ModConfigSpec CLIENT;
    public static final ClientCfg CLIENT_CONFIG;
    static {
        ModConfigSpec.Builder CLIENT_BUILDER = new ModConfigSpec.Builder();
        CLIENT_CONFIG = new ClientCfg(CLIENT_BUILDER);
        CLIENT = CLIENT_BUILDER.build();
    }

    public static class ClientCfg {
        public final ModConfigSpec.BooleanValue Shader;

        public ClientCfg(ModConfigSpec.Builder builder) {
            builder.push("shader");
            Shader = builder.define("使用模组的自定义渲染处理器", true);
            builder.pop();
        }
    }

    @SubscribeEvent
    public static void onLoad(final ModConfigEvent.Loading configEvent) {
    }

    @SubscribeEvent
    public static void onReload(final ModConfigEvent.Reloading configEvent) {
    }
}