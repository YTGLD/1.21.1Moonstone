package com.moonstone.moonstonemod.item.nightmare;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.entity.nightmare.nightmare_entity;
import com.moonstone.moonstonemod.init.moonstoneitem.EntityTs;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

import java.util.List;

public class nightmare_heart extends nightmare {
    public static void NigH(LivingIncomingDamageEvent event){
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player,Items.nightmare_heart.get())){
                if (event.getSource().getEntity()!= null&& event.getSource().getEntity() instanceof nightmare_entity){
                    event.setAmount(0);
                }
            }
        }
    }
    public static void Nig(LivingDeathEvent event) {
        if (event.getSource().getDirectEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.nightmare_heart.get())) {
                if (!player.getCooldowns().isOnCooldown(Items.nightmare_heart.get())) {
                    nightmare_entity e = new nightmare_entity(EntityTs.nightmare_entity.get(), event.getEntity().level());
                    e.setPos(new Vec3(event.getEntity().getX(), event.getEntity().getY() - 1, event.getEntity().getZ()));

                    e.setNoAi(true);
                    e.setNoGravity(true);
                    e.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 12000, 0, false, false));


                    event.getEntity().level().addFreshEntity(e);

                    player.getCooldowns().addCooldown(Items.nightmare_heart.get(), 50);

                }
            }
        }

    }


    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        pTooltipComponents.add(Component.translatable("item.nightmare_heart.tool.string").withStyle(ChatFormatting.DARK_RED));
    }
}
