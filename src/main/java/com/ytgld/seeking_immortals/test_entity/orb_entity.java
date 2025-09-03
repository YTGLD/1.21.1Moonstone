package com.ytgld.seeking_immortals.test_entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
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
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class orb_entity extends ThrowableItemProjectile {
    public int r = 255;
    public int g = 0;
    public int b = 255;
    public boolean lColor = false;

    public orb_entity(EntityType<? extends orb_entity> p_21803_, Level p_21804_) {
        super(p_21803_, p_21804_);
        this.setNoGravity(true);
        this.noPhysics = true;
    }

    public boolean canSee = true;
    public int live = 50;
    @Override
    protected void checkSupportingBlock(boolean onGround, @Nullable Vec3 movement) {

    }

    private final List<Vec3> trailPositions = new ArrayList<>();

    @Override
    public void tick() {
        this.setNoGravity(true);
        if (!lColor){
            r = Mth.nextInt(RandomSource.create(),100,255);
            g = Mth.nextInt(RandomSource.create(),0,25);
            b = Mth.nextInt(RandomSource.create(),175,255);

            lColor = true;
        }
        if (canSee) {
            trailPositions.add(new Vec3(this.getX(), this.getY(), this.getZ()));
        }
        if (!trailPositions.isEmpty()) {
            if (trailPositions.size() > 20||!canSee) {
                trailPositions.removeFirst();
            }
        }
        if (canSee) {
            if (this.getOwner() != null) {
                if (this.tickCount > 6) {
                    Vec3 targetPos = this.getOwner().position().add(0, 0, 0); // 将 Y 坐标增加 heightOffset

                    Vec3 currentPos = this.position();
                    Vec3 direction = targetPos.subtract(currentPos).normalize();

                    Vec3 currentDirection = this.getDeltaMovement().normalize();

                    double angle = Math.acos(currentDirection.dot(direction)) * (180.0 / Math.PI);

                    if (angle > 7.5) {
                        double angleLimit = Math.toRadians(7.5); // 将5度转为弧度

                        Vec3 limitedDirection = currentDirection.scale(Math.cos(angleLimit)) // 计算缩放因子
                                .add(direction.normalize().scale(Math.sin(angleLimit))); // 根据目标方向进行调整

                        this.setDeltaMovement(limitedDirection.x * 0.3f, limitedDirection.y * 0.3f, limitedDirection.z * 0.3f);
                    } else {
                        this.setDeltaMovement(direction.x * 0.3f, direction.y * 0.3f, direction.z * 0.3f);
                    }
                }
            }
        }else {
            this.setDeltaMovement(0,0,0);
        }
        if (this.tickCount > 120) {
            canSee = false;
            live--;
        }else if (!canSee){
            live--;
        }

        if (live<=0) {
            this.discard();
        }
        if (canSee) {
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
                    if (this.tickCount > 15) {
                        living.hurt(living.damageSources().magic(), 4);
                        living.invulnerableTime = 0;
                        living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 1));
                        living.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 1));
                        living.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 1));
                        this.level().addParticle(ParticleTypes.SONIC_BOOM, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
                        canSee = false;
                    }
                }
            }
            super.tick();
        }
    }


    @Override
    public void playerTouch(Player player) {
        if (canSee) {
            this.level().addParticle(ParticleTypes.SONIC_BOOM, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
            canSee = false;
        }
        if (live<= 0) {
            this.discard();
        }
    }
    public float getDistanceToGround() {
        // 获取实体的位置
        Vec3 position = this.position();
        BlockPos blockPos = new BlockPos((int) position.x, (int) position.y, (int) position.z);

        // 获取该位置下方的最近非空气方块位置
        BlockPos groundPos = blockPos.below();
        while (groundPos.getY() > -100 && this.level().getBlockState(groundPos).isAir()) {
            groundPos = groundPos.below();
        }
        Vec3 groundCenter = new Vec3(groundPos.getX() + 0.5, groundPos.getY() + 0.5, groundPos.getZ() + 0.5);
        return (float) position.distanceTo(groundCenter);
    }

    public List<Vec3> getTrailPositions() {
        return trailPositions;
    }

    @Override
    protected @NotNull Item getDefaultItem() {
        return Items.IRON_SWORD;
    }
    }
