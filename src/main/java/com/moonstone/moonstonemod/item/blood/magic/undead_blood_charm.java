package com.moonstone.moonstonemod.item.blood.magic;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.entity.rage_blood;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.i.Blood;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Targeting;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

public class undead_blood_charm extends Item implements ICurioItem, Blood {
    public undead_blood_charm() {
        super(new Properties().stacksTo(1).durability(1000000000).rarity(Rarity.UNCOMMON));
    }

    public static void LivingIncomingDamageEvent(LivingIncomingDamageEvent event){
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.undead_blood_charm.get())){
                event.setAmount(event.getAmount()*1.4f);
            }
        }
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.undead_blood_charm.get())) {
                if (event.getEntity().isInvertedHealAndHarm()) {
                    event.setAmount(event.getAmount()*1.5f);
                }
            }
        }
    }
    public static void LivingHealEvent(LivingHealEvent event){
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.undead_blood_charm.get())){
                event.setAmount(event.getAmount()*1.5f);



                LivingEntity entity = player.getLastHurtByMob();
                if (entity != null) {
                    entity.invulnerableTime = 0;
                    entity.hurt(entity.damageSources().dryOut(),event.getAmount()*1.5f);
                    entity.level().levelEvent(2001, new BlockPos((int) entity.getX(), (int) (entity.getY() + 1), (int) entity.getZ()), Block.getId(Blocks.RED_WOOL.defaultBlockState()));
                }



                Vec3 playerPos = player.position();
                float range = 8;
                List<LivingEntity> entities =
                        player.level().getEntitiesOfClass(LivingEntity.class,
                                new AABB(playerPos.x - range,
                                        playerPos.y - range,
                                        playerPos.z - range,
                                        playerPos.x + range,
                                        playerPos.y + range,
                                        playerPos.z + range));
                for (LivingEntity living : entities) {
                    if (living instanceof Targeting targeting){
                        if (targeting.getTarget()!=null&&targeting.getTarget().is(player)){
                            living.invulnerableTime = 0;
                            living.hurt(living.damageSources().dryOut(),event.getAmount()*0.5f);
                            living.level().levelEvent(2001, new BlockPos((int) living.getX(), (int) (living.getY() + 1), (int) living.getZ()), Block.getId(Blocks.RED_WOOL.defaultBlockState()));
                            break;
                        }
                    }
                }
            }
        }
    }
    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        pTooltipComponents.add(Component.translatable("item.undead_blood_charm.tool.string.1").withStyle(ChatFormatting.RED));
        pTooltipComponents.add(Component.translatable("item.undead_blood_charm.tool.string.2").withStyle(ChatFormatting.RED));
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("item.undead_blood_charm.tool.string.3").withStyle(ChatFormatting.RED));
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("item.undead_blood_charm.tool.string.4").withStyle(ChatFormatting.RED));
        pTooltipComponents.add(Component.translatable("item.undead_blood_charm.tool.string.5").withStyle(ChatFormatting.RED));
    }


}




