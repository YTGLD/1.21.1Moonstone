package com.ytgld.seeking_immortals.test_entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class lighting extends ThrowableItemProjectile {
    public lighting(EntityType<? extends lighting> entityType, Level level) {
        super(entityType, level);
    }
    @Override
    public void tick(){
        this.setNoGravity(true);


        if (this.tickCount == 1) {
            Vec3 playerPos = this.position();
            float range = 2;
            List<LivingEntity> entities =
                    this.level().getEntitiesOfClass(LivingEntity.class,
                            new AABB(playerPos.x - range,
                                    playerPos.y - range,
                                    playerPos.z - range,
                                    playerPos.x + range,
                                    playerPos.y + range,
                                    playerPos.z + range));
            for (LivingEntity living : entities) {
                if (this.getOwner() != null && !living.is(this.getOwner())) {
                    if (this.getOwner() instanceof Player player) {
                        living.invulnerableTime = 0;
                        living.hurt(living.damageSources().magic(), 3 + player.getMaxHealth() * 0.1f);
                    }
                }
            }
        }else if (this.tickCount > 20){
            this.discard();
        }
    }

    protected @NotNull Item getDefaultItem() {
        return Items.IRON_SWORD;
    }

}
