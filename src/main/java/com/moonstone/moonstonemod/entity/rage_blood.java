package com.moonstone.moonstonemod.entity;

import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.init.moonstoneitem.EntityTs;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class rage_blood extends TamableAnimal {

    private LivingEntity target;

    public rage_blood(EntityType<? extends rage_blood> p_27412_, Level p_27413_) {
        super(p_27412_, p_27413_);
    }
    @Override
    public float getXRot() {
        return 0;
    }

    @Override
    public float getYRot() {
        return 0;
    }
    private int cloudTime = 0;

    private int time  = 0;
    public void tick() {
        super.tick();
        time++;
        if (this.time > 200) {

            this.level().explode(this, this.getX(), this.getY(), this.getZ(), 2.25f, false, Level.ExplosionInteraction.NONE);
            this.discard();
        }
        if (cloudTime > 0){
            cloudTime--;
        }
        Vec3 playerPos = this.position().add(0, 0.75, 0);
        int range = 1;
        List<LivingEntity> entities = this.level().getEntitiesOfClass(LivingEntity.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));

        if (this.getOwner()!=null
                && this.getOwner() instanceof Player player) {
            for (LivingEntity living : entities) {
                if (this.target != null
                        && living.is(this.target)
                        && player.getAttribute(Attributes.ATTACK_DAMAGE)!=null)
                {
                    if (cloudTime <= 0 ) {
                        cloudTime = 7;
                        float dam = (float) player.getAttribute(Attributes.ATTACK_DAMAGE).getValue();
                        dam *= 0.33f;

                        this.target.invulnerableTime = 0;
                        this.target.hurt(living.damageSources().mobAttack(this), dam);
                    }
                }
            }
        }



        if (target == null || !target.isAlive()) {
            findNewTarget();
        }

        if (target != null) {
            Vec3 targetPos = target.position().add(0, 0.5, 0);
            Vec3 currentPos = this.position();
            Vec3 direction = targetPos.subtract(currentPos).normalize();

            // 获取当前运动方向
            Vec3 currentDirection = this.getDeltaMovement().normalize();

            // 计算目标方向与当前方向之间的夹角
            double angle = Math.acos(currentDirection.dot(direction)) * (180.0 / Math.PI);

            // 如果夹角超过10度，则限制方向
            if (angle > 9) {
                // 计算旋转后的新方向
                double angleLimit = Math.toRadians(9); // 将10度转为弧度

                // 根据正弦法则计算限制后的方向
                Vec3 limitedDirection = currentDirection.scale(Math.cos(angleLimit)) // 计算缩放因子
                        .add(direction.normalize().scale(Math.sin(angleLimit))); // 根据目标方向进行调整

                this.setDeltaMovement(limitedDirection.x * 0.454f, limitedDirection.y * 0.454f, limitedDirection.z * 0.454f);
            } else {
                this.setDeltaMovement(direction.x * 0.454f, direction.y * 0.454f, direction.z * 0.454f);
            }
        }
        trailPositions.add(new Vec3(this.getX(), this.getY(), this.getZ()));

        if (trailPositions.size() > 8) {
            trailPositions.removeFirst();
        }

        this.setNoGravity(true);
        this.setXRot(0);
        this.setYRot(0);
    }
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean hurt(DamageSource p_30386_, float p_30387_) {
        return false;
    }


    protected void doPush(Entity p_27415_) {
    }

    protected void pushEntities() {
    }


    @Override
    public void setItemSlot(EquipmentSlot p_21036_, ItemStack p_21037_) {

    }

    @Override
    public void setItemInHand(InteractionHand p_21009_, ItemStack p_21010_) {

    }

    private final List<Vec3> trailPositions = new ArrayList<>();

    public List<Vec3> getTrailPositions() {
        return trailPositions;
    }

    @Override
    public boolean isFood(ItemStack itemStack) {
        return false;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        rage_blood wolf = EntityTs.rage_blood.get().create(p_146743_);
        if (wolf != null) {
            UUID uuid = this.getOwnerUUID();
            if (uuid != null) {
                wolf.setOwnerUUID(uuid);
                wolf.setTame(true, true);
            }
        }
        return wolf;
    }
    private void findNewTarget() {
        AABB searchBox = this.getBoundingBox().inflate(16);
        List<LivingEntity> entities = this.level().getEntitiesOfClass(LivingEntity.class, searchBox);
        double closestDistance = Double.MAX_VALUE;
        LivingEntity closestEntity = null;


        for (LivingEntity entity : entities) {
            ResourceLocation name = BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType());
            if (this.getOwner() != null) {
                if (!name.getNamespace().equals(MoonStoneMod.MODID) && !(entity.is(this.getOwner()))) {
                    double distance = this.distanceToSqr(entity);
                    if (distance < closestDistance) {
                        closestDistance = distance;
                        closestEntity = entity;
                    }
                }
            }
        }

        this.target = closestEntity;
    }
}

