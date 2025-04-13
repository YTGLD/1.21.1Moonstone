package com.moonstone.moonstonemod.entity;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.AttReg;
import com.moonstone.moonstonemod.init.moonstoneitem.EntityTs;
import com.moonstone.moonstonemod.item.maxitem.god_sword;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class the_sword extends TamableAnimal {
    public static final EntityDataAccessor<Integer> ARMOR  =  SynchedEntityData.defineId(the_sword.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> ATTACK_TIME  =  SynchedEntityData.defineId(the_sword.class, EntityDataSerializers.INT);
    private LivingEntity target;

    @Nullable
    @Override
    public LivingEntity getTarget() {
        return target;
    }

    public the_sword(EntityType<? extends the_sword> p_21803_, Level p_21804_) {
        super(p_21803_, p_21804_);
        this.setNoGravity(true);
        for (int i = 0; i < trailPositions.length; i++) {
            trailPositions[i][0] = Vec3.ZERO;
            trailPositions[i][1] = Vec3.ZERO;
        }
    }
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 100)
                .add(Attributes.ARMOR, 10);
    }

    @Override
    public boolean isInvulnerable() {
        return true;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
        super.defineSynchedData(pBuilder);
        pBuilder.define(ARMOR, 0);
        pBuilder.define(ATTACK_TIME, 0);
    }
    @Override
    public void die(@NotNull DamageSource p_21809_) {

    }
    public static final int max = 32;
    private int trailPointer = -1;

    private final Vec3[][] trailPositions = new Vec3[max][2];

    public Vec3 getTrailPosition(int pointer, float partialTick) {
        if (this.isRemoved()) {
            partialTick = 1.0F;
        }

        int i = (this.trailPointer - pointer) & max-1;
        int j = (this.trailPointer - pointer - 1) & max-1;

        Vec3 d0 = this.trailPositions[j][0];
        Vec3 d1 = this.trailPositions[i][0].subtract(d0);
        return d0.add(d1.scale(partialTick));
    }

    public Vec3 getHelmetPosition() {
        return this.position();
    }

    public void tickVisual() {
        Vec3 blue = getHelmetPosition();
        this.trailPointer = (this.trailPointer + 1) % this.trailPositions.length;
        this.trailPositions[this.trailPointer][0] = blue;
    }

    public boolean hasTrail() {
        return trailPointer != -1;
    }


    @Override
    public void tick() {
        super.tick();

        this.setNoGravity(true);
        if (entityData.get(ARMOR)<=0){
            this.invulnerableTime = 10;
            this.heal(100);
        }
        if (this.tickCount % 100 == 1) {
            if (entityData.get(ARMOR)<13) {
                this.entityData.set(ARMOR, entityData.get(ARMOR) + 1);
            }
        }
        if (this.tickCount % 20 == 1) {
            findNewTarget();
        }


        LivingEntity owner = getOwner(); // 获取主人

        if (target == null) {

        }else {
            if (this.tickCount%10==0){
                if (target.isAlive()&&this.getOwner()!=null&&this.getOwner()instanceof Player player){
                    target.hurt(target.damageSources().playerAttack(player), (float) (this.getOwner().getAttributeValue(Attributes.ATTACK_DAMAGE)/10f)*this.entityData.get(ARMOR)/150F*this.entityData.get(ATTACK_TIME)/6);
                }
            }
            if (this.tickCount%2==0) {
                if (target.isAlive()) {
                    if (this.entityData.get(ATTACK_TIME) < 300) {
                        this.entityData.set(ATTACK_TIME, this.entityData.get(ATTACK_TIME) + 1);
                    }
                } else {
                    if (this.entityData.get(ATTACK_TIME) > 0) {
                        this.entityData.set(ATTACK_TIME, this.entityData.get(ATTACK_TIME) - 2);
                    }
                }
            }
        }
        Vec3 currentPos = this.position();

        if (owner != null){
            if (this.tickCount%20==1) {
                owner.heal(owner.getMaxHealth() * this.entityData.get(ARMOR) / 100f);
            }
            double desiredDistance = 2; // 设置想要保持的距离
            Vec3 targetPos = owner.position().add(0, 3, 0); // 获取玩家位置并抬高

            Vec3 forward = owner.getLookAngle(); // 获取玩家的朝向向量
            Vec3 direction = forward.scale(-1).normalize(); // 计算背后的方向（逆向）

            Vec3 newTargetPos = targetPos.add(direction.scale(desiredDistance)); // 计算新的目标位置

            this.setDeltaMovement(newTargetPos.subtract(currentPos).normalize().scale(0.15f)); // 设置对象的运动速度
        }

        if (this.getOwner() != null) {
            if (this.getOwner() instanceof Player player){
                Vec3 playerPos = player.position();
                int range = 20;
                List<Mob> entities = player.level().getEntitiesOfClass(Mob.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));

                if (!Handler.hascurio(player, Items.the_god_sword.get())){
                    this.discard();
                }
                if (player.getCooldowns().isOnCooldown(Items.the_god_sword.get())){
                    this.discard();
                }
            }
        }
        if (level().isClientSide) {
            tickVisual();
        }
        if (this.getOwner() instanceof Player player){
            if (!Handler.hascurio(player, Items.the_god_sword.get())){
                this.discard();
            }
            if (!player.getTags().contains(god_sword.name)){
                this.discard();
            }
            if (player.getCooldowns().isOnCooldown(Items.the_god_sword.get())){
                this.discard();
            }
        }
    }
    private void playRemoveOneSound(Entity p_186343_) {
        p_186343_.playSound(SoundEvents.GLASS_BREAK, 1, 1 + p_186343_.level().getRandom().nextFloat() * 0.4F);
    }
    @Override
    public boolean isFood(ItemStack pStack) {
        return false;
    }
    public boolean isPushable() {
        return false;
    }


    @Override
    public boolean hurt(DamageSource source, float amount) {
        playRemoveOneSound(this);
        if (entityData.get(ARMOR) > 1) {
            if (this.getOwner() instanceof Player player) {
                if (!player.level().isClientSide) {
                    player.heal(amount * 2);

                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200,  2));
                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200,  2));
                    player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 200,  2));
                    player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 200,  2));
                    player.addEffect(new MobEffectInstance(MobEffects.CONDUIT_POWER, 200,  2));
                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200,  2));
                    player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 200,  2));


                    if (source.getEntity() instanceof LivingEntity living) {
                        if (!living.is(player)) {
                            living.hurt(living.damageSources().dryOut(), amount * 1.5F);
                            living.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 500, (int) 2));
                            living.addEffect(new MobEffectInstance(MobEffects.POISON, 500, (int) 2));
                            living.addEffect(new MobEffectInstance(MobEffects.WITHER, 500, (int) 2));
                            living.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 500, (int) 2));
                            living.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 500, (int) 2));
                            living.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 500, (int) 2));
                            living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 500, (int) 2));

                        }
                    }
                }

            }

            entityData.set(ARMOR, entityData.get(ARMOR) - 1);
        }else {
            return false;
        }

        return true;
    }

    protected void doPush(Entity p_27415_) {
    }
    protected void pushEntities() {
    }

    private void findNewTarget() {
        AABB searchBox = this.getBoundingBox().inflate(16);
        List<LivingEntity> entities = this.level().getEntitiesOfClass(LivingEntity.class, searchBox);
        double closestDistance = Double.MAX_VALUE;
        LivingEntity closestEntity = null;


        for (LivingEntity entity : entities) {
            ResourceLocation name = BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType());
            if (this.getOwner() != null) {
                if (!(entity instanceof OwnableEntity tamableAnimal
                        && tamableAnimal.getOwner() != null
                        && tamableAnimal.getOwner().equals(this.getOwner()))) {
                    if (!name.getNamespace().equals(MoonStoneMod.MODID) && !(entity.is(this.getOwner()))) {
                        double distance = this.distanceToSqr(entity);
                        if (distance < closestDistance) {
                            closestDistance = distance;
                            closestEntity = entity;
                        }
                    }
                }
            }
        }

        this.target = closestEntity;
    }
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F));
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        the_sword wolf = EntityTs.the_sword.get().create(serverLevel);
        if (wolf != null) {
            UUID uuid = this.getOwnerUUID();
            if (uuid != null) {
                wolf.setOwnerUUID(uuid);
                wolf.setTame(true,true);
            }
        }
        return wolf;
    }
}

