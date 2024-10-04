package com.moonstone.moonstonemod.item.nanodoom;

import com.moonstone.moonstonemod.moonstoneitem.INanoBattery;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class nanorobot extends INanoBattery {

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        super.curioTick(slotContext,stack);
        this.setT(340,slotContext.entity() ,stack);
        if (slotContext.entity() instanceof Player player){
            ItemStack ss = player.getItemBySlot(EquipmentSlot.MAINHAND);
            if (!ss.isEmpty()) {
                if (ss.getMaxDamage() != 0) {
                    if (ss.getDamageValue() > 0) {

                        if (!player.getCooldowns().isOnCooldown(stack.getItem())) {
                            ss.setDamageValue(ss.getDamageValue() - 30);
                            player.getCooldowns().addCooldown(stack.getItem(), getT());
                        }
                    }
                }
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        pTooltipComponents.add(Component.translatable("item.nanorobot.tool.string").withStyle(ChatFormatting.GOLD));
        pTooltipComponents.add(Component.translatable("item.nanorobot.tool.string.1").withStyle(ChatFormatting.GOLD));

    }

}
