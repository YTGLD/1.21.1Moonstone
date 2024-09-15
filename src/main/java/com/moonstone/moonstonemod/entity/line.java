package com.moonstone.moonstonemod.entity;

import com.moonstone.moonstonemod.entity.zombie.cell_zombie;
import com.moonstone.moonstonemod.init.EntityTs;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class line  extends TamableAnimal {

    public line(EntityType<? extends line> p_27412_, Level p_27413_) {
        super(p_27412_, p_27413_);
    }

    public void tick() {
        super.tick();
        trailPositions.add(new Vec3(this.getX(), this.getY(), this.getZ()));

        if (trailPositions.size() > 50) {
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
    public void die(@NotNull DamageSource p_21809_) {

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
        line wolf = EntityTs.line.get().create(p_146743_);
        if (wolf != null) {
            UUID uuid = this.getOwnerUUID();
            if (uuid != null) {
                wolf.setOwnerUUID(uuid);
                wolf.setTame(true, true);
            }
        }
        return wolf;
    }
}




