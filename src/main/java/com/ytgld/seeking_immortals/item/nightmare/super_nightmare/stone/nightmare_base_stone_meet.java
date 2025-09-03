package com.ytgld.seeking_immortals.item.nightmare.super_nightmare.stone;

import com.ytgld.seeking_immortals.Handler;
import com.ytgld.seeking_immortals.init.EntityTs;
import com.ytgld.seeking_immortals.init.Items;
import com.ytgld.seeking_immortals.item.nightmare.extend.SuperNightmare;
import com.ytgld.seeking_immortals.item.nightmare.extend.nightmare;
import com.ytgld.seeking_immortals.test_entity.lighting;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.Optional;

public class nightmare_base_stone_meet extends nightmare implements SuperNightmare {
    public static void lookAttack(Player player){
        if (getPlayerLookTarget(player.level(), player) instanceof LivingEntity living){
            if (Handler.hascurio(player,Items.nightmare_base_stone_meet.get())) {
                if (!player.getCooldowns().isOnCooldown(Items.nightmare_base_stone_meet.get())) {
                    lighting lighting = new lighting(EntityTs.lighting.get(), player.level());
                    lighting.setPos(living.position());
                    lighting.setOwner(player);
                    living.level().addFreshEntity(lighting);
                    player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.RESPAWN_ANCHOR_CHARGE, SoundSource.NEUTRAL, 0.25F, 0.25F);
                    player.getCooldowns().addCooldown(Items.nightmare_base_stone_meet.get(), 7);
                }
            }
        }
    }
    public static Entity getPlayerLookTarget(Level level, LivingEntity living) {
        Entity pointedEntity = null;
        double range = 20.0D;
        Vec3 srcVec = living.getEyePosition();
        Vec3 lookVec = living.getViewVector(1.0F);
        Vec3 destVec = srcVec.add(lookVec.x() * range, lookVec.y() * range, lookVec.z() * range);
        float var9 = 1.0F;
        List<Entity> possibleList = level.getEntities(living, living.getBoundingBox().expandTowards(lookVec.x() * range, lookVec.y() * range, lookVec.z() * range).inflate(var9, var9, var9));
        double hitDist = 0;

        for (Entity possibleEntity : possibleList) {

            if (possibleEntity.isPickable()) {
                float borderSize = possibleEntity.getPickRadius();
                AABB collisionBB = possibleEntity.getBoundingBox().inflate(borderSize, borderSize, borderSize);
                Optional<Vec3> interceptPos = collisionBB.clip(srcVec, destVec);

                if (collisionBB.contains(srcVec)) {
                    if (0.0D < hitDist || hitDist == 0.0D) {
                        pointedEntity = possibleEntity;
                        hitDist = 0.0D;
                    }
                } else if (interceptPos.isPresent()) {
                    double possibleDist = srcVec.distanceTo(interceptPos.get());

                    if (possibleDist < hitDist || hitDist == 0.0D) {
                        pointedEntity = possibleEntity;
                        hitDist = possibleDist;
                    }
                }
            }
        }
        return pointedEntity;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        pTooltipComponents.add(Component.translatable("item.nightmare_base_stone_meet.tool.string").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_stone_meet.tool.string.1").withStyle(ChatFormatting.DARK_RED));
    }
}
