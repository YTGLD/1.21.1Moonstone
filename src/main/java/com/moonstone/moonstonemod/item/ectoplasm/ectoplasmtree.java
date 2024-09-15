package com.moonstone.moonstonemod.item.ectoplasm;

import com.moonstone.moonstonemod.moonstoneitem.ectoplasm;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class ectoplasmtree extends ectoplasm {
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity().tickCount % 20 == 0){
            slotContext.entity().addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 400, 0, false,false));
        }
    }
    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        pTooltipComponents.add(Component.translatable("effect.minecraft.fire_resistance").withStyle(ChatFormatting.GOLD));
    }

}
