package com.moonstone.moonstonemod.moonstoneitem.extend;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.moonstoneitem.Iplague;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.CustomData;
import org.checkerframework.checker.units.qual.C;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class medIC extends Item implements Iplague, ICurioItem {
    public medIC() {
        super(new Properties().stacksTo(1).rarity(Rarity.UNCOMMON));
    }
 /*
    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            if (Handler.hascurio(player, stack.getItem())){
                return false;
            }
        }
        return true;
    }

     */


    @NotNull
    @Override
    public ICurio.DropRule getDropRule(SlotContext slotContext, DamageSource source, int lootingLevel, boolean recentlyHit, ItemStack stack) {
        return ICurio.DropRule.ALWAYS_KEEP;
    }
}
