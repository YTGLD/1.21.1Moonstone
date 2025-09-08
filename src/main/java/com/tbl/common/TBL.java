package com.tbl.common;

import com.moonstone.moonstonemod.MoonStoneMod;
import net.minecraft.resources.ResourceLocation;

import java.util.Locale;

public class TBL {
    public static final String ID = MoonStoneMod.MODID;
    public static ResourceLocation prefix(String name) {
        return ResourceLocation.fromNamespaceAndPath("moonstone", name.toLowerCase(Locale.ROOT));
    }
}
