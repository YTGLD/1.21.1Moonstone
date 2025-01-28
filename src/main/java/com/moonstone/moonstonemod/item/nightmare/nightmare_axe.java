package com.moonstone.moonstonemod.item.nightmare;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.entity.axe;
import com.moonstone.moonstonemod.entity.nightmare.nightmare_entity;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.EntityTs;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

import java.util.List;

public class nightmare_axe extends nightmare {
    public static void Nig(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.nightmare_axe.get())) {
                if (!player.getCooldowns().isOnCooldown(Items.nightmare_axe.get())) {
                    axe e = new axe(EntityTs.axe.get(), event.getEntity().level());

                    e.setPos(new Vec3(event.getEntity().getX(), event.getEntity().getY() +15, event.getEntity().getZ()));
                    e.setOwner(player);

                    event.getEntity().level().addFreshEntity(e);

                    player.getCooldowns().addCooldown(Items.nightmare_axe.get(), 100);

                }
            }
        }
    }
    public static void heals(LivingHealEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.nightmare_axe.get())) {
                Vec3 playerPos = player.position();
                float range =12;
                List<axe> entities =
                        player.level().getEntitiesOfClass(axe.class,
                                new AABB(playerPos.x - range,
                                        playerPos.y - range,
                                        playerPos.z - range,
                                        playerPos.x + range,
                                        playerPos.y + range,
                                        playerPos.z + range));
                float a8 = entities.size();
                a8/=4;
                event.setAmount(event.getAmount()*(1+a8));
            }
        }
    }
    public static void att(LivingIncomingDamageEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.nightmare_axe.get())) {
                Vec3 playerPos = player.position();
                float range =12;
                List<axe> entities =
                        player.level().getEntitiesOfClass(axe.class,
                                new AABB(playerPos.x - range,
                                        playerPos.y - range,
                                        playerPos.z - range,
                                        playerPos.x + range,
                                        playerPos.y + range,
                                        playerPos.z + range));
                float a8 = entities.size();
                a8/=4;
                event.setAmount(event.getAmount()*(1+a8));
            }
        }
    }
    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        pTooltipComponents.add(Component.translatable("item.nightmare_axe.tool.string").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_axe.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_axe.tool.string.2").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_axe.tool.string.3").withStyle(ChatFormatting.DARK_RED));

    }
}
