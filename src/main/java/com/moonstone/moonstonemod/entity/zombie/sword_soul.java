package com.moonstone.moonstonemod.entity.zombie;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.init.EntityTs;
import com.moonstone.moonstonemod.init.Items;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.SpawnUtil;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
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
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.moonstone.moonstonemod.event.AllEvent.*;

public class sword_soul  extends TamableAnimal {
    public sword_soul(EntityType<? extends sword_soul> p_21803_, Level p_21804_) {
        super(p_21803_, p_21804_);
        this.setNoGravity(true);
        CuriosApi.getCuriosInventory(this).ifPresent(handler -> handler.getStacksHandler("ncrdna").ifPresent(stacks -> {
            stacks.addPermanentModifier(new AttributeModifier(ResourceLocation.withDefaultNamespace("ncrdna"+"sword_soul"), 2, AttributeModifier.Operation.ADD_VALUE));
        }));
    }
    @Override
    public void die(@NotNull DamageSource p_21809_) {
        if (this.getOwner()!=null&&getOwner() instanceof ServerPlayer player){
            this.setPos(this.getOwner().position());
            CuriosApi.getCuriosInventory(this).ifPresent((handler)->{
                Map<String, ICurioStacksHandler> curios = handler.getCurios();

                for (String id : curios.keySet()) {
                    drop(curios.get(id), player);
                }
            });
        }
    }

    public static void drop(ICurioStacksHandler stacksHandler, ServerPlayer serverPlayer) {

        for (int i = 0; i < stacksHandler.getSlots(); i++) {
            ItemStack stack1 = stacksHandler.getStacks().getStackInSlot(i);
            stacksHandler.getStacks().setStackInSlot(i, ItemStack.EMPTY);

            ItemStack stack2 = stacksHandler.getCosmeticStacks().getStackInSlot(i);
            stacksHandler.getCosmeticStacks().setStackInSlot(i, ItemStack.EMPTY);

            if (!stack1.isEmpty()) {
                serverPlayer.drop(stack1, true, false);
            }

            if (!stack2.isEmpty()) {
                serverPlayer.drop(stack2, true, false);
            }
        }
    }
    @SubscribeEvent
    public static void evil(LivingDeathEvent event){
        if ((event.getEntity() instanceof LivingEntity player)) {
            if (Handler.hascurio(player,Items.cell_boom.get())){
                player.level().explode(null,player.getX(),player.getY(),player.getZ(),5.5f,true , Level.ExplosionInteraction.MOB);
            }
        }
        if (event.getSource().getEntity() instanceof LivingEntity player){
            if (Handler.hascurio(player, Items.giant.get())){

                if (!Handler.hascurio(player,Items.giant_nightmare.get())) {
                    if (player.level() instanceof ServerLevel p_222881_) {
                        if (Mth.nextInt(RandomSource.create(), 1, 5) == 1) {

                            Handler.trySpawnMob(player, EntityTs.cell_giant.get(), MobSpawnType.TRIGGERED, p_222881_, new BlockPos((int) event.getEntity().getX(), (int) event.getEntity().getY(), (int) event.getEntity().getZ()), 10, 2, 3, SpawnUtil.Strategy.ON_TOP_OF_COLLIDER);
                            player.level().playSound(null, player.blockPosition(), SoundEvents.WARDEN_EMERGE, SoundSource.NEUTRAL, 1.0F, 1.0F);

                            if (Handler.hascurio(player, Items.mother_cell.get())) {
                                if (Mth.nextInt(RandomSource.create(), 1, 2) == 1) {
                                    Handler.trySpawnMob(player, EntityTs.cell_giant.get(), MobSpawnType.TRIGGERED, p_222881_, new BlockPos((int) event.getEntity().getX(), (int) event.getEntity().getY(), (int) event.getEntity().getZ()), 10, 2, 3, SpawnUtil.Strategy.ON_TOP_OF_COLLIDER);
                                }
                                for (int i = 0; i < 2; i++) {
                                    cell_zombie cell_zombie = new cell_zombie(EntityTs.cell_zombie.get(), player.level());
                                    cell_zombie.setOwnerUUID(player.getUUID());
                                    cell_zombie.setPos(player.position());
                                    player.level().addFreshEntity(cell_zombie);
                                }
                            }

                        }
                    }
                }else {
                    if (player.level() instanceof ServerLevel p_222881_) {
                        if (Mth.nextInt(RandomSource.create(), 1, 2) == 1) {
                            Handler.trySpawnMob(player, EntityTs.nightmare_giant.get(), MobSpawnType.TRIGGERED, p_222881_, new BlockPos((int) event.getEntity().getX(), (int) event.getEntity().getY(), (int) event.getEntity().getZ()), 10, 2, 3, SpawnUtil.Strategy.ON_TOP_OF_COLLIDER);

                            player.hurt(player.damageSources().dryOut(), player.getHealth() / 2);
                            player.level().playSound(null, player.blockPosition(), SoundEvents.WARDEN_EMERGE, SoundSource.NEUTRAL, 1.0F, 1.0F);

                            if (Handler.hascurio(player, Items.subspace_cell.get())) {
                                for (int i = 0; i < 3; i++) {
                                    Handler.trySpawnMob(player, EntityTs.cell_giant.get(), MobSpawnType.TRIGGERED, p_222881_, new BlockPos((int) event.getEntity().getX(), (int) event.getEntity().getY(), (int) event.getEntity().getZ()), 10, 2, 3, SpawnUtil.Strategy.ON_TOP_OF_COLLIDER);
                                }
                            }
                        }
                    }
                }
            }
            if (Handler.hascurio(player, Items.cell.get())){
                if (Mth.nextInt(RandomSource.create(),1, 2) ==1 ){
                    cell_zombie z = new cell_zombie(EntityTs.cell_zombie.get(), player.level());
                    z.teleportTo(event.getEntity().getX(),event.getEntity().getY(), event.getEntity().getZ());
                    z.setOwnerUUID(player.getUUID());

                    if (Handler.hascurio(player,Items.adrenaline.get())){
                        z.addTag(DamageCell);
                    }
                    if (Handler.hascurio(player,Items.cell_mummy.get())){
                        z.addTag(muMMY);
                    }
                    if (Handler.hascurio(player,Items.cell_boom.get())){
                        z.addTag(boom);
                    }
                    if (Handler.hascurio(player,Items.cell_calcification.get())){
                        z.addTag(calcification);
                    }
                    if (Handler.hascurio(player,Items.cell_blood.get())){
                        z.addTag(cb_blood);
                    }
                    player.level().addFreshEntity(z);
                }
            }
        }
    }
    private final List<Vec3> trailPositions = new ArrayList<>();
    public List<Vec3> getTrailPositions() {
        return trailPositions;
    }
    @Override
    public void tick() {
        super.tick();
        this.setNoGravity(true);

        LivingEntity target = getTarget(); // 获取目标
        Vec3 currentPos = this.position();

        if ( target != null) {
            Vec3 targetPos = target.position().add(0, 0.5, 0);
            Vec3 direction = targetPos.subtract(currentPos).normalize();
            this.setDeltaMovement(direction.x * (0.075f + 0.5), direction.y * (0.075f + 0.5), direction.z * (0.075f + 0.5));
        }


        if (this.getOwner() != null) {
            if (this.getOwner() instanceof Player player){
                if (!Handler.hascurio(player, Items.necora_baby.get())){
                    this.setPos(player.position());
                    playRemoveOneSound(this);
                    this.kill();
                }
            }
        }
        trailPositions.add(new Vec3(this.getX(), this.getY(), this.getZ()));

        if (trailPositions.size() > 10) {
            trailPositions.removeFirst();
        }


        Vec3 playerPos = this.position().add(0, 0.75, 0);
        int range = 20;
        List<Mob> entities = this.level().getEntitiesOfClass(Mob.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
        for (Mob mob : entities) {
            if (this.getTarget() == null) {
                ResourceLocation entity = BuiltInRegistries.ENTITY_TYPE.getKey(mob.getType());
                if (!entity.getNamespace().equals(MoonStoneMod.MODID)) {
                    this.setTarget(mob);
                }
            }
        }
        if (this.getTarget() != null) {
            ResourceLocation entity = BuiltInRegistries.ENTITY_TYPE.getKey(this.getTarget().getType());
            if (entity.getNamespace().equals(MoonStoneMod.MODID)) {
                this.setTarget(null);
            }
            if (!this.getTarget().isAlive()) {
                this.setTarget(null);
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
        if (this.getTarget()!=null){
            if (this.tickCount % 20 == 0) {

            }
        }
    }
    private void playRemoveOneSound(Entity p_186343_) {
        p_186343_.playSound(SoundEvents.RESPAWN_ANCHOR_DEPLETE.value(), 1, 1);
    }
    @Override
    public boolean isFood(ItemStack pStack) {
        return false;
    }
    public boolean isPushable() {
        return false;
    }

    protected void doPush(Entity p_27415_) {
    }
    protected void pushEntities() {
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.0D, 30, 6));
        this.goalSelector.addGoal(7, new BreedGoal(this, 1.0D));
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());

        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, Monster.class, false));
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        sword_soul wolf = EntityTs.sword_soul.get().create(serverLevel);
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
