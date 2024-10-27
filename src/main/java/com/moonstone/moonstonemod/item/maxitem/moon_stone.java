package com.moonstone.moonstonemod.item.maxitem;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.CommonItem;
import com.moonstone.moonstonemod.init.moonstoneitem.i.Die;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

import java.util.List;

public class moon_stone extends CommonItem implements Die {

    public static void LivingIncomingDamageEvent(LivingIncomingDamageEvent event){
        if (event.getSource().getEntity() instanceof Player living) {
            if  (Handler.hascurio(living, Items.moon_stone.get())) {
                if (!living.getCooldowns().isOnCooldown(Items.moon_stone.get())) {
                    LivingDeathEvent deathEvent = new LivingDeathEvent(event.getEntity(), event.getSource());
                    NeoForge.EVENT_BUS.post(deathEvent);

                    living.getCooldowns().addCooldown(Items.moon_stone.get(),400);
                }
            }
        }
    }
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("item.moon_stone.tool.string.1").withStyle(ChatFormatting.GOLD));
    }

}
