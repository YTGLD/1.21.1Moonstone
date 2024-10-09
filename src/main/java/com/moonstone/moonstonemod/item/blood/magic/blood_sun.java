package com.moonstone.moonstonemod.item.blood.magic;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.entity.blood;
import com.moonstone.moonstonemod.entity.sun;
import com.moonstone.moonstonemod.init.EntityTs;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.moonstoneitem.Blood;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

public class blood_sun extends Item implements ICurioItem, Blood {
    public blood_sun() {
        super(new Properties().stacksTo(1).durability(1000000000).rarity(Rarity.UNCOMMON));
    }

    public static void Did(LivingDeathEvent event){
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.blood_sun.get())){

                if (!player.getCooldowns().isOnCooldown(Items.blood_sun.get())){
                    sun blood = new sun(EntityTs.sun.get(),player.level());

                    blood.setOwner(player);

                    blood.setPos(event.getEntity().getX(),event.getEntity().getY()+1.5f,  event.getEntity().getZ());

                    player.level().addFreshEntity(blood);

                    player.getCooldowns().addCooldown(Items.blood_sun.get(),600);
                }

            }
        }
    }


    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("item.blood_sun.tool.string").withStyle(ChatFormatting.RED));
        } else {
            pTooltipComponents.add(Component.literal("Shift").withStyle(ChatFormatting.DARK_RED));
        }
    }
}
