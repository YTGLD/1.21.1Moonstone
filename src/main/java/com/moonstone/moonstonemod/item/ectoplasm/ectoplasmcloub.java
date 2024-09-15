package com.moonstone.moonstonemod.item.ectoplasm;

import com.moonstone.moonstonemod.moonstoneitem.IEctoplasm;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class ectoplasmcloub extends Item implements IEctoplasm {
    public ectoplasmcloub() {
        super(new Properties().stacksTo(64).rarity(Rarity.RARE).food(
                new FoodProperties.Builder().alwaysEdible().nutrition(8).saturationModifier(0.8f).build()));
    }
}
