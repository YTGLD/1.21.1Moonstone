package com.moonstone.moonstonemod.item.maxitem;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.entity.the_sword;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.EntityTs;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.CommonItem;
import com.moonstone.moonstonemod.init.moonstoneitem.i.Die;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class god_sword extends CommonItem implements Die {
    public static final String name = "HasTheSword";

    public boolean overrideOtherStackedOnMe(ItemStack me, ItemStack Other, Slot p_150744_, ClickAction p_150745_, Player p_150746_, SlotAccess p_150747_) {
        if (p_150745_ == ClickAction.SECONDARY && p_150744_.allowModification(p_150746_)) {
            if (Other.isEmpty()){
                if (p_150746_.getTags().contains(name)){
                    p_150746_.getTags().remove(name);

                    p_150746_.getCooldowns().addCooldown(me.getItem(),20);

                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        if (Handler.hascurio(slotContext.entity(), Items.blood_candle.get())) {
            return false;
        }

        return true;

    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            player.getTags().remove(name);
        }
    }
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {

            if (!player.getTags().contains(name)&&!player.getCooldowns().isOnCooldown(this)){
                the_sword the_sword = new the_sword(EntityTs.the_sword.get(),player.level());
                the_sword.setOwnerUUID(player.getUUID());
                the_sword.setPos(player.position());
                player.level().addFreshEntity(the_sword);
                player.getTags().add(name);
            }
        }
    }



    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("item.god_sword.tool.string").withStyle(ChatFormatting.GOLD));
            pTooltipComponents.add(Component.literal(""));
            pTooltipComponents.add(Component.translatable("item.god_sword.tool.string.2").withStyle(ChatFormatting.GOLD));
            pTooltipComponents.add(Component.translatable("item.god_sword.tool.string.3").withStyle(ChatFormatting.GOLD));
            pTooltipComponents.add(Component.literal(""));
            pTooltipComponents.add(Component.translatable("item.god_sword.tool.string.4").withStyle(ChatFormatting.GOLD));
            pTooltipComponents.add(Component.literal(""));
            pTooltipComponents.add(Component.translatable("item.god_sword.tool.string.5").withStyle(ChatFormatting.GOLD));
            pTooltipComponents.add(Component.translatable("item.god_sword.tool.string.6").withStyle(ChatFormatting.GOLD));
            pTooltipComponents.add(Component.literal(""));
            pTooltipComponents.add(Component.translatable("item.god_sword.tool.string.1").withStyle(ChatFormatting.GOLD));

        } else {
            pTooltipComponents.add(Component.literal("Shift").withStyle(ChatFormatting.YELLOW));
        }
    }
}


