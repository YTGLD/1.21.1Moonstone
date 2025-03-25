package com.moonstone.moonstonemod.entity;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.entity.zombie.cell_zombie;
import com.moonstone.moonstonemod.event.old.AllEvent;
import com.moonstone.moonstonemod.init.moonstoneitem.EntityTs;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class nightmare_giant_to extends ytgld {

    public nightmare_giant_to(EntityType<? extends nightmare_giant_to> p_219350_, Level p_219351_) {
        super(p_219350_, p_219351_);
    }

    @Override
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
                                    if (this.getType() == EntityTs.nightmare_giant_to.get()) {
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
        super.tick();

    }

    protected SoundEvent getHurtSound(DamageSource p_219440_) {
        return SoundEvents.ZOMBIE_HURT;
    }
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ZOMBIE_DEATH;
    }
}
