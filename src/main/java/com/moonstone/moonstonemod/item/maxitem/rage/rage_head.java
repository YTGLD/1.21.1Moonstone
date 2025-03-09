package com.moonstone.moonstonemod.item.maxitem.rage;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.Effects;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

public class rage_head extends Item implements ICurioItem,RAGE{
    public rage_head() {
        super(new Properties().stacksTo(1).rarity(Rarity.EPIC));
    }
    public static void useBow(LivingEntityUseItemEvent.Start event){
        if (Handler.hascurio(event.getEntity(), Items.rage_head.get())){
            if (event.getItem().getUseAnimation() == UseAnim.BOW) {
                event.setDuration(event.getDuration()-8);
            }
        }
    }
    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        pTooltipComponents.add(Component.translatable("item.rage_head.tool.string").withStyle(ChatFormatting.GOLD));
        pTooltipComponents.add(Component.translatable("item.rage_head.tool.string.1").withStyle(ChatFormatting.GOLD));
    }
}
