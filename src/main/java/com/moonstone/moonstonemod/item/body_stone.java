package com.moonstone.moonstonemod.item;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.CommonItem;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.TheNecoraIC;
import com.moonstone.moonstonemod.init.moonstoneitem.i.Die;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class body_stone  extends CommonItem implements Die {

    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        if (Screen.hasShiftDown()){
            pTooltipComponents.add(Component.translatable("item.deceased_contract.tool.string").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFEEE9BF))));
        } else {
            pTooltipComponents.add(Component.translatable("key.keyboard.left.shift").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFEEE9BF))));
        }
    }
    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier>  multimap = HashMultimap.create();
        CuriosApi
                .addSlotModifier(multimap, "charm", ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()), 2, AttributeModifier.Operation.ADD_VALUE);

        return multimap;
    }
}