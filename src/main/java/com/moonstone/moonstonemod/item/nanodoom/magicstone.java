package com.moonstone.moonstonemod.item.nanodoom;

import com.moonstone.moonstonemod.init.moonstoneitem.DataReg;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.Doom;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.List;

public class magicstone extends Doom {
    public final String magic = "Magic";


    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            final int damage = Mth.nextInt(RandomSource.create(), -10, 20);
            final int kok = Mth.nextInt(RandomSource.create(), -20, 50);
            final int regs = Mth.nextInt(RandomSource.create(), -2, 7);

            if (stack.get(DataReg.tag)!=null) {
                if (!stack.get(DataReg.tag).getBoolean(magic)) {
                    stack.get(DataReg.tag).putInt("damage", damage);
                    stack.get(DataReg.tag).putInt("kok", kok);
                    stack.get(DataReg.tag).putInt("regs", regs);

                    stack.get(DataReg.tag).putBoolean(magic, true);

                }
            }else {
                stack.set(DataReg.tag,new CompoundTag());
            }
        }
    }

     @NotNull
    @Override
    public ICurio.DropRule getDropRule(SlotContext slotContext, DamageSource source, int lootingLevel, boolean recentlyHit, ItemStack stack) {
        return ICurio.DropRule.ALWAYS_KEEP;
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        if (pStack.get(DataReg.tag)!=null) {
            pTooltipComponents.add(Component.translatable("effect.minecraft.strength").append(pStack.get(DataReg.tag).getInt("damage") + "%").withStyle(ChatFormatting.GOLD));
            pTooltipComponents.add(Component.translatable("effect.minecraft.resistance").append(pStack.get(DataReg.tag).getInt("regs") + "%").withStyle(ChatFormatting.GOLD));
            pTooltipComponents.add(Component.translatable("attribute.name.generic.knockback_resistance").append(pStack.get(DataReg.tag).getInt("kok") + "%").withStyle(ChatFormatting.GOLD));
        }
    }
}
