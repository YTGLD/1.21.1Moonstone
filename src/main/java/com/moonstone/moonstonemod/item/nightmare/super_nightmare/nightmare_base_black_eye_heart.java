package com.moonstone.moonstonemod.item.nightmare.super_nightmare;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class nightmare_base_black_eye_heart  extends nightmare {
      public static void heal(LivingHealEvent event){
        if (event.getEntity() instanceof LivingEntity living) {
            Vec3 playerPos = living.position().add(0, 0.75, 0);
            float range = 8;
            List<Player> entities =
                    living.level().getEntitiesOfClass(Player.class,
                            new AABB(playerPos.x - range,
                                    playerPos.y - range,
                                    playerPos.z - range,
                                    playerPos.x + range,
                                    playerPos.y + range,
                                    playerPos.z + range));
            for (Player player : entities){
                if (!living.is(player)) {
                    if (Handler.hascurio(player, Items.nightmare_base_black_eye_heart.get())) {
                        player.heal(event.getAmount());
                        event.setAmount(0);
                    }
                }
            }
        }
    }
    public static void hurt(LivingIncomingDamageEvent event){
        if (event.getEntity() instanceof LivingEntity living) {
            Vec3 playerPos = living.position().add(0, 0.75, 0);
            float range = 8;
            List<Player> entities =
                    living.level().getEntitiesOfClass(Player.class,
                            new AABB(playerPos.x - range,
                                    playerPos.y - range,
                                    playerPos.z - range,
                                    playerPos.x + range,
                                    playerPos.y + range,
                                    playerPos.z + range));
            for (Player player : entities){
                if (!living.is(player)) {
                    if (Handler.hascurio(player, Items.nightmare_base_black_eye_heart.get())) {
                        event.setAmount(event.getAmount() * 1.25f);
                    }
                }
            }
        }
    }
    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.nightmare_base_black_eye_heart.tool.string").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_black_eye_heart.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_black_eye_heart.tool.string.2").withStyle(ChatFormatting.DARK_RED));
    }
}
