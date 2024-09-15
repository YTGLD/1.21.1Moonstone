package com.moonstone.moonstonemod.item.amout;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.init.DataReg;
import com.moonstone.moonstonemod.moonstoneitem.CommonItem;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class ectoplasmstone extends CommonItem {
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            player.getAttributes().addTransientAttributeModifiers(aaa(stack));
            final int bbb = Mth.nextInt(RandomSource.create(), -5, 10);

            if (stack.get(DataReg.tag) != null) {
                if (stack.get(DataReg.tag).getInt("double_stone_moonstone_soul") == 0) {
                    stack.get(DataReg.tag).putInt("double_stone_moonstone_soul", bbb);
                }
            } else {
                stack.set(DataReg.tag, new CompoundTag());
            }
        }

    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            player.getAttributes().removeAttributeModifiers(aaa(stack));
        }
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        if (pStack.get(DataReg.tag) != null) {
            pTooltipComponents.add(Component.translatable("effect.minecraft.health_boost" ).append(String.valueOf(pStack.get(DataReg.tag).getInt("double_stone_moonstone_soul"))).withStyle(ChatFormatting.GOLD));
        }
    }

    public Multimap<Holder<Attribute>, AttributeModifier> aaa(ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = HashMultimap.create();
        if (stack.get(DataReg.tag) != null) {
            modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()), stack.get(DataReg.tag).getInt("double_stone_moonstone_soul"), AttributeModifier.Operation.ADD_VALUE));
        }
        return modifierMultimap;
    }
}

