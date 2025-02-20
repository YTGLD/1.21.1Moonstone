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
                .comment("Do you want to enable the post rendering system")
                .define("RenderBackEnds", true);




        BUILDER.pop();

        BUILDER.build();
    }


    public   ModConfigSpec.BooleanValue Shader ;


}
