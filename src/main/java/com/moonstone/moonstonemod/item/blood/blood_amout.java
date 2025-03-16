package com.moonstone.moonstonemod.item.blood;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.entity.line;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.EntityTs;
import com.moonstone.moonstonemod.init.moonstoneitem.i.Blood;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

public class blood_amout extends Item implements ICurioItem, Blood {
    public blood_amout() {
        super(new Properties().stacksTo(1).durability(1000000000).rarity(Rarity.UNCOMMON));
    }
    public static void Hurt(LivingIncomingDamageEvent event){
        if (event.getEntity() instanceof Player player){
            if (event.getSource().getEntity()!=null&& Handler.hascurio(player, Items.blood_amout.get())){
                if (!player.getCooldowns().isOnCooldown( Items.blood_amout.get())) {
                    line line = new line(EntityTs.line.get(), player.level());
                    line.setPos(player.position());
                    line.setOwnerUUID(player.getUUID());
                    player.level().addFreshEntity(line);
                    player.getCooldowns().addCooldown( Items.blood_amout.get(),10);
                }
            }
        }
    }

    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("item.blood_amout.tool.string").withStyle(ChatFormatting.RED));
        } else {
            pTooltipComponents.add(Component.literal("Shift").withStyle(ChatFormatting.DARK_RED));
        }
    }
}
