package com.moonstone.moonstonemod.entity.zombie;

import com.google.common.annotations.VisibleForTesting;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.Dynamic;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.entity.ai.AIgiant;
import com.moonstone.moonstonemod.entity.nightmare.AInightmare;
import com.moonstone.moonstonemod.entity.nightmare.SonicBoom;
import com.moonstone.moonstonemod.entity.nightmare.nightmare_giant;
import com.moonstone.moonstonemod.init.EntityTs;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.protocol.game.DebugPackets;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Unit;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.*;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.warden.AngerLevel;
import net.minecraft.world.entity.monster.warden.AngerManagement;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.vibrations.VibrationSystem;
import net.minecraft.world.level.pathfinder.Node;
import net.minecraft.world.level.pathfinder.PathFinder;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Contract;
import org.slf4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class cell_giant extends TamableAnimal implements OwnableEntity {

    private static final Logger LOGGER = LogUtils.getLogger();
    private static final EntityDataAccessor<Integer> CLIENT_ANGER_LEVEL = SynchedEntityData.defineId(cell_giant.class, EntityDataSerializers.INT);

    private int tendrilAnimation;
    private int tendrilAnimationO;
    private int heartAnimation;
    private int heartAnimationO;
    public AnimationState roarAnimationState = new AnimationState();
    public AnimationState sniffAnimationState = new AnimationState();
    public AnimationState emergeAnimationState = new AnimationState();
    public AnimationState diggingAnimationState = new AnimationState();
    public AnimationState attackAnimationState = new AnimationState();
    public AnimationState sonicBoomAnimationState = new AnimationState();
    private VibrationSystem.Data vibrationData;
    AngerManagement angerManagement = new AngerManagement(this::canTargetEntity, Collections.emptyList());

    public cell_giant(EntityType<? extends cell_giant> p_219350_, Level p_219351_) {
        super(p_219350_, p_219351_);
        this.vibrationData = new VibrationSystem.Data();
        this.xpReward = 5;
        this.getNavigation().setCanFloat(true);
        this.setPathfindingMalus(PathType.UNPASSABLE_RAIL, 0.0F);
        this.setPathfindingMalus(PathType.DAMAGE_OTHER, 8.0F);
        this.setPathfindingMalus(PathType.POWDER_SNOW, 8.0F);
        this.setPathfindingMalus(PathType.LAVA, 8.0F);
        this.setPathfindingMalus(PathType.DAMAGE_FIRE, 0.0F);
        this.setPathfindingMalus(PathType.DANGER_FIRE, 0.0F);
    }
    @org.jetbrains.annotations.Nullable
    @Override
    public LivingEntity getOwner() {
        return super.getOwner();
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket(ServerEntity p_352154_) {
        return new ClientboundAddEntityPacket(this, p_352154_, this.hasPose(Pose.EMERGING) ? 1 : 0);
    }

    @Override
    public void die(DamageSource p_21809_) {

    }

    @Override
    protected void registerGoals() {

        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F));
        this.goalSelector.addGoal(7, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(6, new NonTameRandomTargetGoal<>(this, Turtle.class, false, Turtle.BABY_ON_LAND_SELECTOR));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, Monster.class, false));

    }
    public void recreateFromPacket(ClientboundAddEntityPacket p_219420_) {
        super.recreateFromPacket(p_219420_);
        if (p_219420_.getData() == 1) {
            this.setPose(Pose.EMERGING);
        }

    }

    public boolean checkSpawnObstruction(LevelReader p_219398_) {
        return super.checkSpawnObstruction(p_219398_) && p_219398_.noCollision(this, this.getType().getDimensions().makeBoundingBox(this.position()));
    }

    public float getWalkTargetValue(BlockPos p_219410_, LevelReader p_219411_) {
        return 0.0F;
    }


    boolean isDiggingOrEmerging() {
        return this.hasPose(Pose.DIGGING) || this.hasPose(Pose.EMERGING);
    }

    protected boolean canRide(Entity p_219462_) {
        return false;
    }

    public boolean canDisableShield() {
        return true;
    }

    protected float nextStep() {
        return this.moveDist + 0.55F;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 500.0D).add(Attributes.MOVEMENT_SPEED, (double)0.3F).add(Attributes.KNOCKBACK_RESISTANCE, 1.0D).add(Attributes.ATTACK_KNOCKBACK, 1.5D).add(Attributes.ATTACK_DAMAGE, 30.0D);
    }

    public boolean dampensVibrations() {
        return true;
    }

    protected float getSoundVolume() {
        return 4.0F;
    }

    @javax.annotation.Nullable
    protected SoundEvent getAmbientSound() {
        return !this.hasPose(Pose.ROARING) && !this.isDiggingOrEmerging() ? this.getAngerLevel().getAmbientSound() : null;
    }

    protected SoundEvent getHurtSound(DamageSource p_219440_) {
        return SoundEvents.ZOMBIE_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ZOMBIE_DEATH;
    }

    protected void playStepSound(BlockPos p_219431_, BlockState p_219432_) {
        this.playSound(SoundEvents.ZOMBIE_STEP, 10.0F, 1.0F);
    }

    public boolean doHurtTarget(Entity p_219472_) {
        this.level().broadcastEntityEvent(this, (byte)4);
        com.moonstone.moonstonemod.entity.nightmare.SonicBoom.setCooldown(this, 40);
        return super.doHurtTarget(p_219472_);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
        super.defineSynchedData(pBuilder);
        pBuilder.define(CLIENT_ANGER_LEVEL, 0);
    }

    public int getClientAngerLevel() {
        return this.entityData.get(CLIENT_ANGER_LEVEL);
    }

    private void syncClientAngerLevel() {
        this.entityData.set(CLIENT_ANGER_LEVEL, this.getActiveAnger());
    }

    public int time = 0;

    public void tick() {
        time++;

        if (time > 1200){
            this.discard();
        }
        if (this.getOwner()!= null) {
            if (this.getOwner().getLastHurtByMob()!= null) {
                if (!this.getOwner().getLastHurtByMob().is(this)) {
                    this.setAttackTarget(this.getOwner().getLastHurtByMob());
                }
            }
            if (this.getOwner().getLastAttacker()!= null) {
                if (!this.getOwner().getLastAttacker().is(this)) {
                    this.setAttackTarget(this.getOwner().getLastAttacker());
                }

            }
            if (this.getOwner().getLastHurtMob()!= null) {
                if (!this.getOwner().getLastHurtMob().is(this)) {
                    this.setAttackTarget(this.getOwner().getLastHurtMob());
                }

            }
        }
        Level level = this.level();
        if (level instanceof ServerLevel) {
            if (this.isPersistenceRequired() || this.requiresCustomPersistence()) {
                AInightmare.setDigCooldown(this);
            }
        }

        super.tick();

        Vec3 playerPos = this.position().add(0, 0.75, 0);
        int range = 10;
        List<Mob> entities = this.level().getEntitiesOfClass(Mob.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
        for (Mob mob : entities) {
            if (!this.getBrain().getMemory(MemoryModuleType.ATTACK_TARGET).isPresent()) {
                ResourceLocation entity = BuiltInRegistries.ENTITY_TYPE.getKey(mob.getType());
                if (!entity.getNamespace().equals(MoonStoneMod.MODID)) {
                    this.setAttackTarget(mob);
                }
            }
        }
        if (this.getBrain().getMemory(MemoryModuleType.ATTACK_TARGET).isPresent()) {
            if (!this.getBrain().getMemory(MemoryModuleType.ATTACK_TARGET).get().isAlive()) {
                this.setAttackTarget(null);
            }
        }
        if (this.getOwner()!= null) {
            if (this.getOwner().getLastHurtByMob()!= null) {
                if (!this.getOwner().getLastHurtByMob().is(this)) {
                    this.setTarget(this.getOwner().getLastHurtByMob());
                }
            }
            if (this.getOwner().getLastAttacker()!= null) {
                if (!this.getOwner().getLastAttacker().is(this)) {
                    this.setTarget(this.getOwner().getLastAttacker());
                }

            }
            if (this.getOwner().getLastHurtMob()!= null) {
                if (!this.getOwner().getLastHurtMob().is(this)) {
                    this.setTarget(this.getOwner().getLastHurtMob());
                }

            }
        }
        if (this.level().isClientSide()) {
            if (this.tickCount % this.getHeartBeatDelay() == 0) {
                this.heartAnimation = 10;

            }

            this.tendrilAnimationO = this.tendrilAnimation;
            if (this.tendrilAnimation > 0) {
                --this.tendrilAnimation;
            }

            this.heartAnimationO = this.heartAnimation;
            if (this.heartAnimation > 0) {
                --this.heartAnimation;
            }

            switch (this.getPose()) {
                case EMERGING:
                    this.clientDiggingParticles(this.emergeAnimationState);
                    break;
                case DIGGING:
                    this.clientDiggingParticles(this.diggingAnimationState);
            }
        }

    }

    protected void customServerAiStep() {
        ServerLevel serverlevel = (ServerLevel)this.level();
        serverlevel.getProfiler().push("nightmare_giantBrain");
        this.getBrain().tick(serverlevel, this);
        this.level().getProfiler().pop();
        super.customServerAiStep();

        if (this.tickCount % 20 == 0) {
            this.angerManagement.tick(serverlevel, this::canTargetEntity);
            this.syncClientAngerLevel();
        }
    }

    public void handleEntityEvent(byte p_219360_) {
        if (p_219360_ == 4) {
            this.roarAnimationState.stop();
            this.attackAnimationState.start(this.tickCount);
        } else if (p_219360_ == 61) {
            this.tendrilAnimation = 10;
        } else if (p_219360_ == 62) {
            this.sonicBoomAnimationState.start(this.tickCount);
        } else {
            super.handleEntityEvent(p_219360_);
        }

    }

    private int getHeartBeatDelay() {
        float f = (float)this.getClientAngerLevel() / (float)AngerLevel.ANGRY.getMinimumAnger();
        return 40 - Mth.floor(Mth.clamp(f, 0.0F, 1.0F) * 30.0F);
    }

    public float getTendrilAnimation(float p_219468_) {
        return Mth.lerp(p_219468_, (float)this.tendrilAnimationO, (float)this.tendrilAnimation) / 10.0F;
    }

    public float getHeartAnimation(float p_219470_) {
        return Mth.lerp(p_219470_, (float)this.heartAnimationO, (float)this.heartAnimation) / 10.0F;
    }

    private void clientDiggingParticles(AnimationState p_219384_) {
        if ((float)p_219384_.getAccumulatedTime() < 4500.0F) {
            RandomSource randomsource = this.getRandom();
            BlockState blockstate = this.getBlockStateOn();
            if (blockstate.getRenderShape() != RenderShape.INVISIBLE) {
                for(int i = 0; i < 30; ++i) {
                    double d0 = this.getX() + (double)Mth.randomBetween(randomsource, -0.7F, 0.7F);
                    double d1 = this.getY();
                    double d2 = this.getZ() + (double)Mth.randomBetween(randomsource, -0.7F, 0.7F);
                    this.level().addParticle(new BlockParticleOption(ParticleTypes.BLOCK, blockstate), d0, d1, d2, 0.0D, 0.0D, 0.0D);
                }
            }
        }

    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> p_219422_) {
        if (DATA_POSE.equals(p_219422_)) {
            switch (this.getPose()) {
                case EMERGING:
                    this.emergeAnimationState.start(this.tickCount);
                    break;
                case DIGGING:
                    this.diggingAnimationState.start(this.tickCount);
                    break;
                case ROARING:
                    this.roarAnimationState.start(this.tickCount);
                    break;
                case SNIFFING:
                    this.sniffAnimationState.start(this.tickCount);
            }
        }

        super.onSyncedDataUpdated(p_219422_);
    }

    public boolean ignoreExplosion() {
        return this.isDiggingOrEmerging();
    }

    protected Brain<?> makeBrain(Dynamic<?> p_219406_) {
        return AIgiant.makeBrain(this, p_219406_);
    }

    public Brain<cell_giant> getBrain() {
        return (Brain<cell_giant>)super.getBrain();
    }

    protected void sendDebugPackets() {
        super.sendDebugPackets();
        DebugPackets.sendEntityBrain(this);
    }


    @Contract("null->false")
    public boolean canTargetEntity(@javax.annotation.Nullable Entity p_219386_) {
        if (p_219386_ instanceof LivingEntity livingentity) {
            if (this.getOwner()!= null) {
                if (!livingentity.is(this.getOwner())) {
                    if (this.level() == p_219386_.level() && EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(p_219386_) && !this.isAlliedTo(p_219386_) && livingentity.getType() != EntityType.ARMOR_STAND && livingentity.getType() != EntityTs.nightmare_giant.get() && !livingentity.isInvulnerable() && !livingentity.isDeadOrDying() && this.level().getWorldBorder().isWithinBounds(livingentity.getBoundingBox())) {
                        return true;
                    }
                }
            }
        }

        return false;
    }


    public void addAdditionalSaveData(CompoundTag p_219434_) {
        super.addAdditionalSaveData(p_219434_);
        AngerManagement.codec(this::canTargetEntity).encodeStart(NbtOps.INSTANCE, this.angerManagement).resultOrPartial(LOGGER::error).ifPresent((p_219437_) -> {
            p_219434_.put("anger", p_219437_);
        });
        VibrationSystem.Data.CODEC.encodeStart(NbtOps.INSTANCE, this.vibrationData).resultOrPartial(LOGGER::error).ifPresent((p_219418_) -> {
            p_219434_.put("listener", p_219418_);
        });
    }

    public void readAdditionalSaveData(CompoundTag p_219415_) {
        super.readAdditionalSaveData(p_219415_);
        if (p_219415_.contains("anger")) {
            AngerManagement.codec(this::canTargetEntity).parse(new Dynamic<>(NbtOps.INSTANCE, p_219415_.get("anger"))).resultOrPartial(LOGGER::error).ifPresent((p_219394_) -> {
                this.angerManagement = p_219394_;
            });
            this.syncClientAngerLevel();
        }

        if (p_219415_.contains("listener", 10)) {
            VibrationSystem.Data.CODEC.parse(new Dynamic<>(NbtOps.INSTANCE, p_219415_.getCompound("listener"))).resultOrPartial(LOGGER::error).ifPresent((p_281093_) -> {
                this.vibrationData = p_281093_;
            });
        }

    }

    private void playListeningSound() {
        if (!this.hasPose(Pose.ROARING)) {
            this.playSound(this.getAngerLevel().getListeningSound(), 10.0F, this.getVoicePitch());
        }

    }

    public AngerLevel getAngerLevel() {
        return AngerLevel.byAnger(this.getActiveAnger());
    }

    private int getActiveAnger() {
        return this.angerManagement.getActiveAnger(this.getTarget());
    }

    public void clearAnger(Entity p_219429_) {
        this.angerManagement.clearAnger(p_219429_);
    }

    public void increaseAngerAt(@javax.annotation.Nullable Entity p_219442_) {
        this.increaseAngerAt(p_219442_, 35, true);
    }
    @VisibleForTesting
    public void increaseAngerAt(@javax.annotation.Nullable Entity p_219388_, int p_219389_, boolean p_219390_) {
        if (!this.isNoAi() && this.canTargetEntity(p_219388_)) {
            AInightmare.setDigCooldown(this);
            boolean flag = !(this.getBrain().getMemory(MemoryModuleType.ATTACK_TARGET).orElse(null) instanceof Player);
            int i = this.angerManagement.increaseAnger(p_219388_, p_219389_);
            if (p_219388_ instanceof Player && flag && AngerLevel.byAnger(i).isAngry()) {
                this.getBrain().eraseMemory(MemoryModuleType.ATTACK_TARGET);
            }

            if (p_219390_) {
                this.playListeningSound();
            }
        }

    }
    public Optional<LivingEntity> getEntityAngryAt() {
        return this.getAngerLevel().isAngry() ? this.angerManagement.getActiveEntity() : Optional.empty();
    }

    @javax.annotation.Nullable
    public LivingEntity getTarget() {
        return this.getBrain().getMemory(MemoryModuleType.ATTACK_TARGET).orElse((LivingEntity)null);
    }

    public boolean removeWhenFarAway(double p_219457_) {
        return false;
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return false;
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        nightmare_giant wolf = EntityTs.nightmare_giant.get().create(p_146743_);
        if (wolf != null) {
            UUID uuid = this.getOwnerUUID();
            if (uuid != null) {
                wolf.setOwnerUUID(uuid);

            }
        }
        return wolf;
    }

    public boolean hurt(DamageSource p_219381_, float p_219382_) {
        boolean flag = super.hurt(p_219381_, p_219382_);
        if (!this.level().isClientSide && !this.isNoAi() && !this.isDiggingOrEmerging()) {
            Entity entity = p_219381_.getEntity();
            this.increaseAngerAt(entity, AngerLevel.ANGRY.getMinimumAnger() + 20, false);
            if (this.brain.getMemory(MemoryModuleType.ATTACK_TARGET).isEmpty() && entity instanceof LivingEntity) {
                LivingEntity livingentity = (LivingEntity)entity;
                if (!p_219381_.isDirect() || this.closerThan(livingentity, 5.0D)) {

                    if (this.getOwner()!= null &&!livingentity.is(this.getOwner())) {
                        this.setAttackTarget(livingentity);
                    }
                }
            }
        }

        return flag;
    }

    public void setAttackTarget(LivingEntity p_219460_) {
        this.getBrain().eraseMemory(MemoryModuleType.ROAR_TARGET);
        this.getBrain().setMemory(MemoryModuleType.ATTACK_TARGET, p_219460_);
        this.getBrain().eraseMemory(MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE);

        SonicBoom.setCooldown(this, 20);
    }



    public boolean isPushable() {
        return !this.isDiggingOrEmerging() && super.isPushable();
    }

    protected void doPush(Entity p_219353_) {
        if (!this.isNoAi() && !this.getBrain().hasMemoryValue(MemoryModuleType.TOUCH_COOLDOWN)) {
            this.getBrain().setMemoryWithExpiry(MemoryModuleType.TOUCH_COOLDOWN, Unit.INSTANCE, 20L);
            this.increaseAngerAt(p_219353_);
        }

        super.doPush(p_219353_);
    }

    @VisibleForTesting
    public AngerManagement getAngerManagement() {
        return this.angerManagement;
    }

    protected PathNavigation createNavigation(Level p_219396_) {
        return new GroundPathNavigation(this, p_219396_) {
            protected PathFinder createPathFinder(int p_219479_) {
                this.nodeEvaluator = new WalkNodeEvaluator();
                this.nodeEvaluator.setCanPassDoors(true);
                return new PathFinder(this.nodeEvaluator, p_219479_) {
                    protected float distance(Node p_219486_, Node p_219487_) {
                        return p_219486_.distanceToXZ(p_219487_);
                    }
                };
            }
        };
    }

}