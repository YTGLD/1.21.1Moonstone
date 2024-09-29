package com.moonstone.moonstonemod.entity.bloodvruis;

import com.moonstone.moonstonemod.init.EntityTs;
import com.moonstone.moonstonemod.init.Items;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class blood_orb_attack extends ThrowableItemProjectile {

    private final List<Vec3> trailPositions = new ArrayList<>();

    public blood_orb_attack(EntityType<? extends blood_orb_attack> entityType, Level level) {
        super(entityType, level);
        this.setNoGravity(true);

    }


    public List<Vec3> getTrailPositions() {
        return trailPositions;
    }

    @Override
    protected @NotNull Item getDefaultItem() {
        return Items.blood.get();
    }

    @Override
    public @NotNull ItemStack getItem() {
        return Items.blood.get().getDefaultInstance();
    }

    @Override
    public void tick() {
        super.tick();
        float xRot = this.getXRot();
        float yRot = this.getYRot();
        float radX = (float) Math.toRadians(xRot);
        float radY = (float) Math.toRadians(yRot);
        float moveX = (float) (Math.sin(radY) * Math.cos(radX));
        float moveY = (float) Math.sin(radX);
        float moveZ = (float) (Math.cos(radY) * Math.cos(radX));

        float speed = 0.123F;
        this.setPos(this.position().add(moveX * speed, moveY * speed, moveZ * speed));


        if (this.tickCount > 200) {
            this.discard();
        }

        if (this.tickCount % 10 == 0) {
            blood_orb_small attack_blood = new blood_orb_small(EntityTs.blood_orb_small.get(), this.level());
            attack_blood.setPos(this.position());
            attack_blood.setOwner(this.getOwner());
            playRemoveOneSound(this);
            this.level().addFreshEntity(attack_blood);

        }

        trailPositions.add(new Vec3(this.getX(), this.getY(), this.getZ()));

        if (trailPositions.size() > 20) {
            trailPositions.removeFirst();
        }

        this.setNoGravity(true);
    }
    private void playRemoveOneSound(Entity p_186343_) {
        p_186343_.playSound(SoundEvents.RESPAWN_ANCHOR_DEPLETE.value(), 0.2f, 0.2f);
    }
}


