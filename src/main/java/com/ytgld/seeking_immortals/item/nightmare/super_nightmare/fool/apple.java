package com.ytgld.seeking_immortals.item.nightmare.super_nightmare.fool;

import com.ytgld.seeking_immortals.Handler;
import com.ytgld.seeking_immortals.init.Items;
import com.ytgld.seeking_immortals.item.nightmare.extend.SuperNightmare;
import com.ytgld.seeking_immortals.item.nightmare.extend.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

import java.util.List;

public class apple extends nightmare implements SuperNightmare {
    public static void damage(LivingIncomingDamageEvent event){
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.apple.get())) {
                event.setAmount(10);
            }
        }
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.apple.get())) {
                event.setAmount(2);
            }
        }
    }
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.apple.tool.string").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.apple.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.apple.tool.string.2").withStyle(ChatFormatting.DARK_RED));

    }

}
