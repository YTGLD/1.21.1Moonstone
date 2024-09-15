package com.moonstone.moonstonemod.item.nanodoom;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.moonstoneitem.Doom;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class wind extends Doom {

    private float abc ;

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            player.getAttributes().addTransientAttributeModifiers(this.Head());
            if (player.isSprinting()){
                if (abc < 0.75) {
                    abc += 0.02f;
                }
            }else if (abc > 0){
                abc -= 0.015f;
            }
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        slotContext.entity().getAttributes().removeAttributeModifiers(this.Head());
    }

    private Multimap<Holder<Attribute>, AttributeModifier> Head(){
        Multimap<Holder<Attribute>, AttributeModifier> multimap = HashMultimap.create();


        multimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(
                ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()),
                abc,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

        return multimap;
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        pTooltipComponents.add(Component.translatable("item.wind.tool.string").withStyle(ChatFormatting.GOLD));

    }
}

