package com.moonstone.moonstonemod.entity.nightmare;

import com.google.common.annotations.VisibleForTesting;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.Dynamic;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.entity.extend.MoonTamableAnimal;
import com.moonstone.moonstonemod.entity.zombie.cell_zombie;
import com.moonstone.moonstonemod.event.old.AllEvent;
import com.moonstone.moonstonemod.init.moonstoneitem.EntityTs;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
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
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.GameEventTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Unit;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NonTameRandomTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.warden.AngerLevel;
import net.minecraft.world.entity.monster.warden.AngerManagement;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.DynamicGameEventListener;
import net.minecraft.world.level.gameevent.EntityPositionSource;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.PositionSource;
import net.minecraft.world.level.gameevent.vibrations.VibrationSystem;
import net.minecraft.world.level.pathfinder.Node;
import net.minecraft.world.level.pathfinder.PathFinder;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Contract;
import org.slf4j.Logger;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.BiConsumer;

public class nightmare_giant extends MoonTamableAnimal implements OwnableEntity,VibrationSystem {

    private static final Logger LOGGER = LogUtils.getLogger();
    private static final EntityDataAccessor<Integer> CLIENT_ANGER_LEVEL = SynchedEntityData.defineId(nightmare_giant.class, EntityDataSerializers.INT);

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
    private final DynamicGameEventListener<VibrationSystem.Listener> dynamicGameEventListener;
    private final VibrationSystem.User vibrationUser;
    private VibrationSystem.Data vibrationData;
    AngerManagement angerManagement = new AngerManagement(this::canTargetEntity, Collections.emptyList());

    public nightmare_giant(EntityType<? extends nightmare_giant> p_219350_, Level p_219351_) {
        super(p_219350_, p_219351_);


        this.vibrationUser = new VibrationUser();
        this.vibrationData = new VibrationSystem.Data();
        this.dynamicGameEventListener = new DynamicGameEventListener<>(new VibrationSystem.Listener(this));
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
        this.targetSelector.addGoal(7, new com.moonstone.moonstonemod.entity.nightmare.NearestAttackableTargetGoal<>(this, Villager.class, false));
        this.targetSelector.addGoal(7, new com.moonstone.moonstonemod.entity.nightmare.NearestAttackableTargetGoal<>(this, Zombie.class, false));
        this.targetSelector.addGoal(7, new com.moonstone.moonstonemod.entity.nightmare.NearestAttackableTargetGoal<>(this, Spider.class, false));
        this.targetSelector.addGoal(7, new com.moonstone.moonstonemod.entity.nightmare.NearestAttackableTargetGoal<>(this, Skeleton.class, false));
        this.targetSelector.addGoal(7, new com.moonstone.moonstonemod.entity.nightmare.NearestAttackableTargetGoal<>(this, Creeper.class, false));
        this.targetSelector.addGoal(7, new com.moonstone.moonstonemod.entity.nightmare.NearestAttackableTargetGoal<>(this, EnderMan.class, false));
        this.targetSelector.addGoal(7, new com.moonstone.moonstonemod.entity.nightmare.NearestAttackableTargetGoal<>(this, Monster.class, false));
        this.targetSelector.addGoal(7, new net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal<>(this, Monster.class, false));

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
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 150).add(Attributes.MOVEMENT_SPEED, (double)0.3F).add(Attributes.KNOCKBACK_RESISTANCE, 1.0D).add(Attributes.ATTACK_KNOCKBACK, 1.5D).add(Attributes.ATTACK_DAMAGE, 15);
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
        SonicBoom.setCooldown(this, 40);
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
    @Override
    public void die(DamageSource p_21809_) {
        if (this.getTags().contains(Handler.Giant_Boom)){
            this.level().explode(this, this.getX(), this.getY(), this.getZ(), 12.5f, true, Level.ExplosionInteraction.NONE);
        }
    }


    int sZombieTime = 0;
    public void tick() {
        time++;
        if (!this.getTags().contains(Handler.Giant_Time)) {
            time += 3;
        }else {
            time+=2;
        }
        if (sZombieTime>0){
            sZombieTime--;
        }
        if (time > 3600){
            this.discard();
        }
        if (this.getBrain().getMemory(MemoryModuleType.ATTACK_TARGET).isPresent()) {
            ResourceLocation entity = BuiltInRegistries.ENTITY_TYPE.getKey(this.getBrain().getMemory(MemoryModuleType.ATTACK_TARGET).get().getType());
            if (entity.getNamespace().equals(MoonStoneMod.MODID)) {
                this.setAttackTarget(null);
            }
        }

        {
            Vec3 playerPos = this.position().add(0, 1.25, 0);
            float range = 16;
            List<cell_zombie> entities =
                    this.level().getEntitiesOfClass(cell_zombie.class,
                            new AABB(playerPos.x - range,
                                    playerPos.y - range,
                                    playerPos.z - range,
                                    playerPos.x + range,
                                    playerPos.y + range,
                                    playerPos.z + range));

            for (Entity c : entities) {
                if (c instanceof cell_zombie cellZombie) {
                    if (this.tickCount % 20 == 1) {
                        this.heal(entities.size());
                    }
                }
            }
        }
        Vec3 playerPos = this.position().add(0, 0.75, 0);
        int range = 10;
        List<Mob> entities = this.level().getEntitiesOfClass(Mob.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
        for (Mob mob : entities) {
            if (!this.getBrain().getMemory(MemoryModuleType.ATTACK_TARGET).isPresent()) {
                ResourceLocation entity = BuiltInRegistries.ENTITY_TYPE.getKey(mob.getType());
                if (!entity.getNamespace().equals(MoonStoneMod.MODID)) {
                    if (this.getBrain().getMemory(MemoryModuleType.ATTACK_TARGET).isEmpty()) {
                        if (sZombieTime<=0){
                            if (this.getOwner()!=null) {
                                for (int i = 0; i < 2; i++) {
                                    if (this.getType() == EntityTs.nightmare_giant.get()) {
                                        cell_zombie cellZombie = new cell_zombie(EntityTs.cell_zombie.get(), this.level());
                                        cellZombie.teleportTo(this.getX(), this.getY(), this.getZ());
                                        cellZombie.setOwnerUUID(this.getOwnerUUID());
                                        cellZombie.addTag(AllEvent.DamageCell);
                                        cellZombie.addTag("hasNig");
                                        this.level().playSound(null, this.getOnPos(), SoundEvents.TRIAL_SPAWNER_OMINOUS_ACTIVATE, SoundSource.AMBIENT, 10, 10);
                                        this.level().addFreshEntity(cellZombie);
                                        sZombieTime = 300;
                                    }
                                }
                            }
                        }
                        mob.addEffect(new MobEffectInstance(MobEffects.GLOWING,200,0));
                        mob.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,200,2));
                        mob.addEffect(new MobEffectInstance(MobEffects.WEAKNESS,200,2));
                        mob.addEffect(new MobEffectInstance(MobEffects.BLINDNESS,200,2));

                        this.setAttackTarget(mob);
                    }
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
                if (!this.getOwner().getLastHurtByMob().is(this)&&!BuiltInRegistries.ENTITY_TYPE.getKey(this.getOwner().getLastHurtByMob().getType()).getNamespace().equals(MoonStoneMod.MODID)) {
                    this.setAttackTarget(this.getOwner().getLastHurtByMob());
                }
            }
            if (this.getOwner().getLastAttacker()!= null) {
                if (!this.getOwner().getLastAttacker().is(this)&&!BuiltInRegistries.ENTITY_TYPE.getKey(this.getOwner().getLastAttacker().getType()).getNamespace().equals(MoonStoneMod.MODID)) {
                    this.setAttackTarget(this.getOwner().getLastAttacker());
                }

            }
            if (this.getOwner().getLastHurtMob()!= null) {
                if (!this.getOwner().getLastHurtMob().is(this)&&!BuiltInRegistries.ENTITY_TYPE.getKey(this.getOwner().getLastHurtMob().getType()).getNamespace().equals(MoonStoneMod.MODID)) {
                    this.setAttackTarget(this.getOwner().getLastHurtMob());
                }

            }
        }
        Level level = this.level();
        if (level instanceof ServerLevel serverlevel) {
            VibrationSystem.Ticker.tick(serverlevel, this.vibrationData, this.vibrationUser);
            if (this.isPersistenceRequired() || this.requiresCustomPersistence()) {
                AInightmare.setDigCooldown(this);
            }
        }

        super.tick();
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

        AInightmare.updateActivity(this);
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
        return AInightmare.makeBrain(this, p_219406_);
    }

    public Brain<nightmare_giant> getBrain() {
        return (Brain<nightmare_giant>)super.getBrain();
    }

    protected void sendDebugPackets() {
        super.sendDebugPackets();
        DebugPackets.sendEntityBrain(this);
    }

    public void updateDynamicGameEventListener(BiConsumer<DynamicGameEventListener<?>, ServerLevel> p_219413_) {
        Level level = this.level();
        if (level instanceof ServerLevel serverlevel) {
            p_219413_.accept(this.dynamicGameEventListener, serverlevel);
        }

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
    public void increaseAngerAt(@Nullable Entity p_219388_, int p_219389_, boolean p_219390_) {
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

    public VibrationSystem.Data getVibrationData() {
        return this.vibrationData;
    }

    public VibrationSystem.User getVibrationUser() {
        return this.vibrationUser;
    }
    class VibrationUser implements VibrationSystem.User {
        private static final int GAME_EVENT_LISTENER_RANGE = 16;
        private final PositionSource positionSource = new EntityPositionSource(nightmare_giant.this, nightmare_giant.this.getEyeHeight());

        @Override
        public int getListenerRadius() {
            return 16;
        }

        @Override
        public PositionSource getPositionSource() {
            return this.positionSource;
        }

        @Override
        public TagKey<GameEvent> getListenableEvents() {
            return GameEventTags.WARDEN_CAN_LISTEN;
        }

        @Override
        public boolean canTriggerAvoidVibration() {
            return true;
        }

        @Override
        public boolean canReceiveVibration(ServerLevel p_282574_, BlockPos p_282323_, Holder<GameEvent> p_316784_, GameEvent.Context p_282515_) {
            if (!nightmare_giant.this.isNoAi()
                    && !nightmare_giant.this.isDeadOrDying()
                    && !nightmare_giant.this.getBrain().hasMemoryValue(MemoryModuleType.VIBRATION_COOLDOWN)
                    && !nightmare_giant.this.isDiggingOrEmerging()
                    && p_282574_.getWorldBorder().isWithinBounds(p_282323_)) {
                if (p_282515_.sourceEntity() instanceof LivingEntity livingentity && !nightmare_giant.this.canTargetEntity(livingentity)) {
                    return false;
                }

                return true;
            } else {
                return false;
            }
        }

        @Override
        public void onReceiveVibration(
                ServerLevel p_281325_, BlockPos p_282386_, Holder<GameEvent> p_316139_, @Nullable Entity p_281438_, @Nullable Entity p_282582_, float p_283699_
        ) {
            if (!nightmare_giant.this.isDeadOrDying()) {
                nightmare_giant.this.brain.setMemoryWithExpiry(MemoryModuleType.VIBRATION_COOLDOWN, Unit.INSTANCE, 40L);
                p_281325_.broadcastEntityEvent(nightmare_giant.this, (byte)61);
                nightmare_giant.this.playSound(SoundEvents.WARDEN_TENDRIL_CLICKS, 5.0F, nightmare_giant.this.getVoicePitch());
                BlockPos blockpos = p_282386_;
                if (p_282582_ != null) {
                    if (nightmare_giant.this.closerThan(p_282582_, 30.0)) {
                        if (nightmare_giant.this.getBrain().hasMemoryValue(MemoryModuleType.RECENT_PROJECTILE)) {
                            if (nightmare_giant.this.canTargetEntity(p_282582_)) {
                                blockpos = p_282582_.blockPosition();
                            }

                            nightmare_giant.this.increaseAngerAt(p_282582_);
                        } else {
                            nightmare_giant.this.increaseAngerAt(p_282582_, 10, true);
                        }
                    }

                    nightmare_giant.this.getBrain().setMemoryWithExpiry(MemoryModuleType.RECENT_PROJECTILE, Unit.INSTANCE, 100L);
                } else {
                    nightmare_giant.this.increaseAngerAt(p_281438_);
                }

                if (!nightmare_giant.this.getAngerLevel().isAngry()) {
                    Optional<LivingEntity> optional = nightmare_giant.this.angerManagement.getActiveEntity();
                    if (p_282582_ != null || optional.isEmpty() || optional.get() == p_281438_) {
                        AInightmare.setDisturbanceLocation(nightmare_giant.this, blockpos);
                    }
                }
            }
        }
    }
}