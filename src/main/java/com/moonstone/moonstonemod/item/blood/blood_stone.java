package com.moonstone.moonstonemod.item.blood;

import com.moonstone.moonstonemod.init.moonstoneitem.i.Blood;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class blood_stone extends Item implements ICurioItem, Blood {
    public blood_stone() {
        super(new Properties().stacksTo(1).durability(1000000000).rarity(Rarity.UNCOMMON));
    }

}
