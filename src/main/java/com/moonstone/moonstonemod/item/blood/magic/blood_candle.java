package com.moonstone.moonstonemod.item.blood.magic;

import com.moonstone.moonstonemod.entity.owner_blood;
import com.moonstone.moonstonemod.init.EntityTs;
import com.moonstone.moonstonemod.moonstoneitem.Blood;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.BundleContents;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

public class blood_candle extends Item implements ICurioItem, Blood {

    public blood_candle() {
        super(new Properties().stacksTo(1).component(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY).rarity(Rarity.UNCOMMON));
    }


    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            player.getTags().remove("HasBlood");
        }
    }
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            if (!player.getTags().contains("HasBlood")){
                owner_blood owner_blood = new owner_blood(EntityTs.owner_blood.get(),player.level());
                owner_blood.setOwnerUUID(player.getUUID());
                owner_blood.setPos(player.position());
                player.level().addFreshEntity(owner_blood);
                player.getTags().add("HasBlood");
            }
        }
    }



    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("item.blood_candle.tool.string").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.literal(""));

            pTooltipComponents.add(Component.translatable("item.blood_candle.tool.string.1").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.blood_candle.tool.string.2").withStyle(ChatFormatting.RED));

        } else {
            pTooltipComponents.add(Component.literal("Shift").withStyle(ChatFormatting.DARK_RED));
        }
    }
}

