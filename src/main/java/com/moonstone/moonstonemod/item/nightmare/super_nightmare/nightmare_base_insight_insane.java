package com.moonstone.moonstonemod.item.nightmare.super_nightmare;

import com.moonstone.moonstonemod.Config;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

import java.util.List;

public class nightmare_base_insight_insane extends nightmare implements SuperNightmare {
    public static void LivingDeathEvents(LivingDeathEvent event){
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.nightmare_base_insight_insane.get())) {
                player.heal(event.getEntity().getMaxHealth()/10);
                player.getCooldowns().addCooldown(Items.nightmare_base_insight_insane.get(),200);
            }
        }
    }
    public static void damage(LivingIncomingDamageEvent event){
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.nightmare_base_insight_insane.get())) {
                if (player.getCooldowns().isOnCooldown(Items.nightmare_base_insight_insane.get())){
                    event.setAmount(event.getAmount()*(1+( Config.SERVER.nightmare_base_insight_insane.get()/100f)));
                    player.getCooldowns().addCooldown(Items.nightmare_base_insight_insane.get(),0);
                }
            }
        }
    }
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.nightmare_base_insight_insane.tool.string").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_insight_insane.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_insight_insane.tool.string.2").withStyle(ChatFormatting.DARK_RED));
    }
}



