package com.moonstone.moonstonemod.item;

import com.moonstone.moonstonemod.init.moonstoneitem.DataReg;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.UnCommonItem;
import com.moonstone.moonstonemod.init.moonstoneitem.i.Die;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class universe extends UnCommonItem implements Die {
    public static final String doAsUniverse = "doAsUniverse";
    public static final int universeSize = 5;
    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        pTooltipComponents.add(Component.translatable("item.universe.tool.string").withStyle(ChatFormatting.GOLD));
        pTooltipComponents.add(Component.translatable("item.universe.tool.string.1").withStyle(ChatFormatting.GOLD));
        pTooltipComponents.add(Component.translatable("item.universe.tool.string.2").withStyle(ChatFormatting.GOLD));
        pTooltipComponents.add(Component.translatable("item.universe.tool.string.3").withStyle(ChatFormatting.GOLD));
        pTooltipComponents.add(Component.literal(""));
        if (pStack.get(DataReg.tag)!=null){
            for (String string : pStack.get(DataReg.tag).getAllKeys()){
                String modifiedString = string.replace(':', '.')
                        .replace(doAsUniverse,"")
                        .replace("item.moonstone.universe","");

                pTooltipComponents.add(Component.translatable(modifiedString).withStyle(ChatFormatting.GOLD));
            }
        }else {
            pTooltipComponents.add(Component.translatable("item.universe.tool.null").withStyle(ChatFormatting.RED));
        }
    }
}
