package com.moonstone.moonstonemod.item.maxitem.rage;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.storage.loot.LootContext;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

public class rage_orb extends Item implements ICurioItem,RAGE{
    public rage_orb() {
        super(new Properties().stacksTo(1).rarity(Rarity.EPIC));
    }
    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        pTooltipComponents.add(Component.translatable("item.rage_orb.tool.string").withStyle(ChatFormatting.GOLD));
        pTooltipComponents.add(Component.translatable("item.rage_orb.tool.string.2").withStyle(ChatFormatting.GOLD));
        pTooltipComponents.add(Component.translatable("item.rage_orb.tool.string.3").withStyle(ChatFormatting.GOLD));
        pTooltipComponents.add(Component.translatable("item.rage_orb.tool.string.4").withStyle(ChatFormatting.GOLD));
    }

    @Override
    public int getLootingLevel(SlotContext slotContext, @Nullable LootContext lootContext, ItemStack stack) {
        return 1;
    }

    @Override
    public int getFortuneLevel(SlotContext slotContext, LootContext lootContext, ItemStack stack) {
        return 1;
    }
}

