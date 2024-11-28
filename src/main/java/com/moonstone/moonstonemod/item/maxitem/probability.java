package com.moonstone.moonstonemod.item.maxitem;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.CommonItem;
import com.moonstone.moonstonemod.init.moonstoneitem.i.Die;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

import java.util.List;

public class probability extends CommonItem implements Die {
    public static void att(LivingIncomingDamageEvent event){
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.probability.get())) {
                int luck = (int) player.getLuck();
                float damage = Mth.nextFloat(RandomSource.create(), 0.1f + (luck / 100f), 2);

                event.setAmount(event.getAmount() * damage);

            }
        }
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.probability.get())) {
                int luck = (int) player.getLuck();
                float damage = Mth.nextFloat(RandomSource.create(), 0.25f + (luck / 100f), 2);

                event.setAmount(event.getAmount() * damage);

            }
        }
    }
    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        pTooltipComponents.add(Component.translatable("item.probability.tool.string.1").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFF8B658B))));
        pTooltipComponents.add(Component.translatable("item.probability.tool.string.2").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFF8B658B))));
        pTooltipComponents.add(Component.translatable("item.probability.tool.string.3").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFF8B658B))));
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("item.probability.tool.string.4").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFF483D8B))));
        pTooltipComponents.add(Component.translatable("item.probability.tool.string.5").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFF8B658B))));
//        pTooltipComponents.add(Component.translatable("item.probability.tool.string.6").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFF8B658B))));
    }

}

