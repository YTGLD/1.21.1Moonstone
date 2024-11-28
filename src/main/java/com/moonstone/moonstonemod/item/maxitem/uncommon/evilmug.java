package com.moonstone.moonstonemod.item.maxitem.uncommon;

import com.moonstone.moonstonemod.init.moonstoneitem.DataReg;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import java.util.List;

public class evilmug extends Item {
    public static String blood = "blood";

    public evilmug() {
        super(new Properties().stacksTo(1).rarity(Rarity.UNCOMMON).food(
                new FoodProperties.Builder().alwaysEdible().nutrition(1).saturationModifier(0.2f).build()));
    }


    @Override
    public UseAnim getUseAnimation(ItemStack p_41452_) {
        return UseAnim.DRINK;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        pPlayer.startUsingItem(pUsedHand);
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack s, Level level, LivingEntity living) {

        CompoundTag tag = s.get(DataReg.tag.get());
        if (tag != null) {
            {

                if (tag.getInt(blood) > 0) {
                    living.heal(10);
                    if (tag.getInt(blood) >= 10) {
                        tag.putInt(blood, tag.getInt(blood) - 10);
                    } else {
                        tag.putInt(blood, tag.getInt(blood) - tag.getInt(blood));
                    }
                } else  {
                    living.hurt(living.damageSources().magic(), 10);
                    tag.putInt(blood, tag.getInt(blood) + 10);
                }
            }
        }else {
            s.set(DataReg.tag,new CompoundTag());
        }
        return s;
    }
    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        CompoundTag tag = pStack.get(DataReg.tag.get());
        if (tag != null) {
            {
                pTooltipComponents.add(Component.translatable(String.valueOf(tag)).withStyle(ChatFormatting.RED).withStyle(ChatFormatting.ITALIC));
                pTooltipComponents.add(Component.translatable(""));

                if (tag != null) {
                    if (tag.getInt(blood) <= 0) {
                        pTooltipComponents.add(Component.translatable("item.evilmug.tool.string").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.ITALIC));
                        pTooltipComponents.add(Component.translatable("item.evilmug.tool.string.1").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.ITALIC));
                        pTooltipComponents.add(Component.translatable(""));
                        pTooltipComponents.add(Component.translatable("item.evilmug.tool.string.2").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.ITALIC));

                    } else {
                        pTooltipComponents.add(Component.translatable("item.evilmug.tool.string.3").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.ITALIC));
                    }
                    pTooltipComponents.add(Component.translatable("Blood : " + tag.getInt(blood)).withStyle(ChatFormatting.RED).withStyle(ChatFormatting.ITALIC));

                }

            }
        }
    }
}

