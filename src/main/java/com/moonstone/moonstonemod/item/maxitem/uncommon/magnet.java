package com.moonstone.moonstonemod.item.maxitem.uncommon;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

public class magnet extends Item implements ICurioItem {
    public magnet() {
        super(new Properties().stacksTo(1).rarity(Rarity.EPIC));
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        Vec3 playerPos = slotContext.entity().position().add(0, 0.75, 0);
        float range = 8;
        List<ItemEntity> itemEntities =
                slotContext.entity().level().getEntitiesOfClass(ItemEntity.class,
                        new AABB(playerPos.x - range,
                                playerPos.y - range,
                                playerPos.z - range,
                                playerPos.x + range,
                                playerPos.y + range,
                                playerPos.z + range));
        for (ItemEntity item : itemEntities) {
            if (item.tickCount > 35) {
                Vec3 direction = playerPos.subtract(item.position());
                direction = direction.normalize().scale(0.1);
                item.setDeltaMovement(item.getDeltaMovement().add(direction));
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        pTooltipComponents.add(Component.translatable("item.magnet.tool.string").withStyle(ChatFormatting.GOLD));
    }
}
