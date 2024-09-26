package com.moonstone.moonstonemod.item.blood.magic;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.entity.blood;
import com.moonstone.moonstonemod.entity.owner_blood;
import com.moonstone.moonstonemod.init.DataReg;
import com.moonstone.moonstonemod.init.EntityTs;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.moonstoneitem.Blood;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class blood_candle extends Item implements ICurioItem, Blood {

    public blood_candle() {
        super(new Properties().stacksTo(1).component(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY).rarity(Rarity.UNCOMMON));
    }


    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (stack.get(DataReg.tag) != null) {
            stack.get(DataReg.tag).putBoolean("HasBlood", false);
        }
    }
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            if (stack.get(DataReg.tag) != null) {
                if (!stack.get(DataReg.tag).getBoolean("HasBlood")) {
                    owner_blood owner_blood = new owner_blood(EntityTs.owner_blood.get(),player.level());
                    owner_blood.setOwnerUUID(player.getUUID());
                    owner_blood.setPos(player.position());
                    player.level().addFreshEntity(owner_blood);
                    stack.get(DataReg.tag).putBoolean("HasBlood",true);
                }
            } else stack.set(DataReg.tag, new CompoundTag());
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

