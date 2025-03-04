package com.moonstone.moonstonemod.item;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.entity.coffin_entity;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.EntityTs;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.CommonItem;
import com.moonstone.moonstonemod.init.moonstoneitem.i.Blood;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

public class coffin extends CommonItem implements ICurioItem, Blood {
    public static void coffins(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (event.getEntity().onGround()) {
                if (Handler.hascurio(player, Items.coffin_.get())) {
                    if (!player.getCooldowns().isOnCooldown(Items.coffin_.get())) {
                        coffin_entity e = new coffin_entity(EntityTs.coffin_entity.get(), event.getEntity().level());
                        e.setNoGravity(true);
                        e.setPos(new Vec3(event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ()));
                        e.setOwner(player);

                        event.getEntity().level().addFreshEntity(e);

                        player.getCooldowns().addCooldown(Items.coffin_.get(), 3600);

                    }
                }
            }
        }
    }
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        if (Screen.hasShiftDown()) {
            tooltipComponents.add(Component.translatable("item.moonstone.coffin.tool.string.3").withStyle(ChatFormatting.DARK_RED));
            tooltipComponents.add(Component.literal(""));
        }else {
            tooltipComponents.add(Component.translatable("item.moonstone.coffin.tool.string.1").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.ITALIC));
            tooltipComponents.add(Component.translatable("item.moonstone.coffin.tool.string.2").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.ITALIC));
            tooltipComponents.add(Component.literal(""));
            tooltipComponents.add(Component.translatable("key.keyboard.left.shift").withStyle(ChatFormatting.DARK_RED));

        }
    }
    public static class coffin_item extends coffin{

        @Override
        public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
            super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
            tooltipComponents.add(Component.translatable("item.moonstone.coffin.tool.string"));
        }
    }
}
