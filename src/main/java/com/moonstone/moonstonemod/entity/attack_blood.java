package com.moonstone.moonstonemod.entity;

import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.init.Items;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class attack_blood extends ThrowableItemProjectile {
    private LivingEntity target;
    private final List<Vec3> trailPositions = new ArrayList<>();

    public attack_blood(EntityType<? extends attack_blood> entityType, Level level) {
        super(entityType, level);
        this.setNoGravity(true);

    }

    public LivingEntity getTarget() {
        return target;
    }

    public void setTarget(LivingEntity target) {
        this.target = target;
    }

    public List<Vec3> getTrailPositions() {
        return trailPositions;
    }

    @Override
    protected Item getDefaultItem() {
        return Items.blood.get();
    }

    @Override
    public @NotNull ItemStack getItem() {
        return Items.blood.get().getDefaultInstance();
    }
    @Override
    public float getXRot() {
        return 0;
    }

    @Override
    public float getYRot() {
        return 0;
    }

    @Override
    public void tick() {
        super.tick();
        Vec3 playerPos = this.position().add(0, 0.75, 0);
        int range = 1;
        List<Mob> entities = this.level().getEntitiesOfClass(Mob.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
        for (Mob entity : entities){
            if (this.getOwner()!=null) {
                if (!entity.is(this.getOwner())&&this.getOwner() instanceof Player player){
                    ResourceLocation entitys = BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType());
                    if (!entitys.getNamespace().equals(MoonStoneMod.MODID)) {
                        entity.invulnerableTime = 0;
                        entity.hurt(this.getOwner().damageSources().dryOut(), 4 + player.getMaxHealth() / 10);
                        this.discard();
                    }
                }
            }
        }
        if (this.tickCount > 200) {
            this.discard();
        }
        if (target != null) {
            if (!target.isAlive()) {
                target = null;
            }
        }
        float s = this.tickCount / 200f;
        if (target != null) {


            Vec3 targetPos = target.position().add(0, 0.5, 0);
            Vec3 currentPos = this.position();
            Vec3 direction = targetPos.subtract(currentPos).normalize();
            this.setDeltaMovement(direction.x * (0.075f + s), direction.y * (0.075f + s), direction.z * (0.075f + s));
        }

        trailPositions.add(new Vec3(this.getX(), this.getY(), this.getZ()));

        if (trailPositions.size() > 20) {
            trailPositions.removeFirst();
        }

        this.setNoGravity(true);
        this.setYRot(0);
        this.setXRot(0);
    }


}

