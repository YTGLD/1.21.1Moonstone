package com.moonstone.moonstonemod.item.blood.magic;

import com.moonstone.moonstonemod.moonstoneitem.Blood;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

public class the_blood_book  extends Item implements ICurioItem, Blood {


    public the_blood_book() {
        super(new Properties().stacksTo(1).rarity(Rarity.UNCOMMON));
    }


}
