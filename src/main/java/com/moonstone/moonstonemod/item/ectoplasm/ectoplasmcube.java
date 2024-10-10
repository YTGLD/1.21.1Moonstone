package com.moonstone.moonstonemod.item.ectoplasm;

import com.moonstone.moonstonemod.init.moonstoneitem.i.IEctoplasm;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class ectoplasmcube extends Item implements IEctoplasm {
    public ectoplasmcube() {
        super(new Properties().stacksTo(64).rarity(Rarity.EPIC).food(
                new FoodProperties.Builder().alwaysEdible().nutrition(10).saturationModifier(1.0f).build()));
    }

}

