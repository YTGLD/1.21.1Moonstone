package com.ytgld.seeking_immortals.item.nightmare.tip;

import net.minecraft.world.item.ItemStack;

public interface ILevel {
    float maxLevel_float(ItemStack stack);
    float nowLevel_float(ItemStack stack);
}
