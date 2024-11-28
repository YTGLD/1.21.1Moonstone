package com.moonstone.moonstonemod.item;

import com.moonstone.moonstonemod.entity.zombie.sword_soul;
import com.moonstone.moonstonemod.init.moonstoneitem.DataReg;
import com.moonstone.moonstonemod.init.moonstoneitem.EntityTs;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.TheNecoraIC;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class necora_baby extends TheNecoraIC {

    public static final String necora  = "necora_baby";

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (stack.get(DataReg.tag)!=null) {
            stack.get(DataReg.tag).remove(necora);
        }



    }
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (stack.get(DataReg.tag)!=null) {
            if (!stack.get(DataReg.tag).getBoolean(necora)){
                sword_soul sword_soul = new sword_soul(EntityTs.sword_soul.get(),slotContext.entity().level());
                sword_soul.setOwnerUUID(slotContext.entity().getUUID());
                sword_soul.setPos(slotContext.entity().position());

                slotContext.entity().level().addFreshEntity(sword_soul);

                stack.get(DataReg.tag).putBoolean(necora,true);
            }
        }else stack.set(DataReg.tag,new CompoundTag());
    }
    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("item.necora_baby.tool.string").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.ITALIC));
            pTooltipComponents.add(Component.translatable(""));
            pTooltipComponents.add(Component.translatable("item.necora_baby.tool.string.1").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable(""));
            pTooltipComponents.add(Component.translatable("item.necora_baby.tool.string.2").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.necora_baby.tool.string.3").withStyle(ChatFormatting.RED));
        }else {
            pTooltipComponents.add(Component.translatable(""));
            pTooltipComponents.add(Component.literal("-[SHIFT]").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.BOLD));
        }
    }
}
