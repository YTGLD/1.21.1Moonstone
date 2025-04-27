package com.moonstone.moonstonemod;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;
public class ConfigClient {
    public static final ConfigClient Client;
    public static final ModConfigSpec fc;
    static {
        final Pair<ConfigClient, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(ConfigClient::new);
        Client = specPair.getLeft();
        fc = specPair.getRight();
    }
    public ConfigClient(ModConfigSpec.Builder BUILDER){

        BUILDER.push("client");

        Shader = BUILDER
                .comment("关闭后，将关闭模组内的后处理渲染器")
                .define("RenderBackEnds", true);


        light = BUILDER
                .comment("关闭后，将关闭模组内的动态光照")
                .define("light_", false);




        BUILDER.pop();

        BUILDER.build();
    }
    public   ModConfigSpec.BooleanValue light;

    public   ModConfigSpec.BooleanValue Shader ;


}
