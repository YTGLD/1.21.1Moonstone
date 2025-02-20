package com.moonstone.moonstonemod.item.maxitem.book;

import com.moonstone.moonstonemod.init.moonstoneitem.extend.BookSkill;
import com.moonstone.moonstonemod.init.moonstoneitem.i.Blood;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class the_blood_book  extends BookSkill implements Blood {


    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        tooltipComponents.add(Component.translatable("item.the_blood_book.tool.string").withStyle(ChatFormatting.RED));
        tooltipComponents.add(Component.translatable("item.the_blood_book.tool.string.1").withStyle(ChatFormatting.RED));
        tooltipComponents.add(Component.translatable("item.the_blood_book.tool.string.3").withStyle(ChatFormatting.RED));
        tooltipComponents.add(Component.translatable("item.the_blood_book.tool.string.4").withStyle(ChatFormatting.RED));
        tooltipComponents.add(Component.literal(""));
        tooltipComponents.add(Component.translatable("item.the_blood_book.tool.string.5").withStyle(ChatFormatting.RED));
        tooltipComponents.add(Component.literal(""));
        tooltipComponents.add(Component.translatable("item.the_blood_book.tool.string.6").withStyle(ChatFormatting.RED));
        tooltipComponents.add(Component.literal(""));
    }
}
