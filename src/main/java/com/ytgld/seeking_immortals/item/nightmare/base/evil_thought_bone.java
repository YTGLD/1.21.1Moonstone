package com.ytgld.seeking_immortals.item.nightmare.base;

import com.ytgld.seeking_immortals.Handler;
import com.ytgld.seeking_immortals.event.CurioDropEvent;
import com.ytgld.seeking_immortals.init.Items;
import com.ytgld.seeking_immortals.item.nightmare.extend.SuperNightmare;
import com.ytgld.seeking_immortals.item.nightmare.extend.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;

import java.util.Collection;
import java.util.List;

public class evil_thought_bone extends nightmare implements SuperNightmare {
    public static void CurioDropEvent(CurioDropEvent curioHurtEvent){
        Player player = curioHurtEvent.getPlayer() ;
        ItemStack stack = curioHurtEvent.getStack() ;
        LivingDropsEvent event = curioHurtEvent.getEvent() ;
        if (Handler.hascurio(player, Items.evil_thought_bone.get())) {
            if (stack.is(Items.evil_thought_bone.get())&&stack.getItem() instanceof evil_thought_bone evilThoughtBone) {
                float lvl = 2;
                Collection<ItemEntity> drop = event.getDrops();
                for (ItemEntity entity : drop){
                    ItemStack i_stack = entity.getItem();
                    if ((i_stack.getMaxStackSize() != 1)){
                        i_stack.setCount((int) (i_stack.getCount() * lvl));
                        entity.setItem(i_stack);
                    }
                }
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        tooltipComponents.add(Component.literal(""));
        tooltipComponents.add(Component.translatable("item.evil_thought_bone.tool.string").withStyle(ChatFormatting.DARK_RED));
        tooltipComponents.add(Component.literal(""));
    }
}


