package com.moonstone.moonstonemod.item.maxitem;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.entity.SwordOfTwelve;
import com.moonstone.moonstonemod.entity.axe;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.CommonItem;
import com.moonstone.moonstonemod.init.moonstoneitem.i.Die;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

import java.util.List;

public class twelve_sword extends CommonItem implements Die {



    public static void att(LivingIncomingDamageEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.twelve_sword_.get())) {
                Vec3 playerPos = player.position();
                float range =10;
                List<SwordOfTwelve> entities =
                        player.level().getEntitiesOfClass(SwordOfTwelve.class,
                                new AABB(playerPos.x - range,
                                        playerPos.y - range,
                                        playerPos.z - range,
                                        playerPos.x + range,
                                        playerPos.y + range,
                                        playerPos.z + range));
                float a8 = entities.size();
                a8/=10;
                event.setAmount(event.getAmount()*(1+a8));
            }
        }
    }
    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        pTooltipComponents.add(Component.translatable("item.twelve_sword.tool.string.1").withStyle(ChatFormatting.GOLD));
        pTooltipComponents.add(Component.translatable("item.twelve_sword.tool.string.4").withStyle(ChatFormatting.GOLD));
        pTooltipComponents.add(Component.translatable("item.twelve_sword.tool.string.2").withStyle(ChatFormatting.GOLD));
        pTooltipComponents.add(Component.translatable("item.twelve_sword.tool.string.3").withStyle(ChatFormatting.GOLD));
    }

    public static class at_sword extends CommonItem{

    }
    public static class sword extends CommonItem{

    }
}
