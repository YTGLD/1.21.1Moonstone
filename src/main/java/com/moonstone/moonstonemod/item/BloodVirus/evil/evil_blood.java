package com.moonstone.moonstonemod.item.BloodVirus.evil;

import com.moonstone.moonstonemod.entity.bloodvruis.blood_orb_owner;
import com.moonstone.moonstonemod.init.EntityTs;
import com.moonstone.moonstonemod.init.moonstoneitem.i.Blood;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

public class evil_blood extends Item implements ICurioItem, Blood {
    private final String vb = "BloodEvil";

    public evil_blood() {
        super(new Properties().stacksTo(1).rarity(Rarity.UNCOMMON));
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            player.getTags().remove(vb);
        }
    }
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            if (!player.getTags().contains(vb)){
                blood_orb_owner owner_blood = new blood_orb_owner(EntityTs.blood_orb_owner.get(),player.level());
                owner_blood.setOwnerUUID(player.getUUID());
                owner_blood.setPos(player.position());
                player.level().addFreshEntity(owner_blood);
                player.getTags().add(vb);
            }
        }
    }



    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("item.evil_blood.tool.string").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.literal(""));
            pTooltipComponents.add(Component.translatable("item.blood_candle.tool.string.1").withStyle(ChatFormatting.RED));
        } else {
            pTooltipComponents.add(Component.literal("Shift").withStyle(ChatFormatting.DARK_RED));
        }
    }
}


