package com.moonstone.moonstonemod.item.blood.magic;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.entity.blood;
import com.moonstone.moonstonemod.entity.rage_blood;
import com.moonstone.moonstonemod.init.EntityTs;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.i.Blood;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

public class rage_blood_head extends Item implements ICurioItem, Blood {
    public rage_blood_head() {
        super(new Properties().stacksTo(1).durability(1000000000).rarity(Rarity.UNCOMMON));
    }

    public static void hurt(LivingIncomingDamageEvent event){
        if (event.getSource().getEntity() instanceof rage_blood ){
            if (Handler.hascurio(event.getEntity(), Items.rage_blood_head.get())){
                event.setAmount(0);
            }
        }
    }
    public static void Did(LivingDeathEvent event){
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.rage_blood_head.get())){
                {
                    rage_blood blood = new rage_blood(EntityTs.rage_blood.get(), player.level());
                    blood.setDeltaMovement(Mth.nextDouble(RandomSource.create(), 0.1, 0.11), Mth.nextDouble(RandomSource.create(), 0.095, 0.1), Mth.nextDouble(RandomSource.create(), 0.099, 0.1));
                    blood.setOwnerUUID(player.getUUID());
                    blood.setPos(event.getEntity().getX(), event.getEntity().getY() + 1.5f, event.getEntity().getZ());

                    player.level().addFreshEntity(blood);
                }
                {

                    rage_blood blood = new rage_blood(EntityTs.rage_blood.get(),player.level());
                    blood.setDeltaMovement(Mth.nextDouble(RandomSource.create(),-0.12,0.1211),Mth.nextDouble(RandomSource.create(),0.0925,0.121),Mth.nextDouble(RandomSource.create(),-0.0992,0.112));
                    blood.setOwnerUUID(player.getUUID());
                    blood.setPos(event.getEntity().getX(),event.getEntity().getY()+1.5f,  event.getEntity().getZ());

                    player.level().addFreshEntity(blood);
                }
                player.playSound(SoundEvents.GENERIC_EXPLODE.value(),0.25F,0.25F);

            }
        }
    }


    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("item.rage_blood_head.tool.string").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.rage_blood_head.tool.string.1").withStyle(ChatFormatting.RED));
        } else {
            pTooltipComponents.add(Component.literal("Shift").withStyle(ChatFormatting.DARK_RED));
        }
    }
}

