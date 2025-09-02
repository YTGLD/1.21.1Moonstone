package com.moonstone.moonstonemod.client.particle;

import com.moonstone.moonstonemod.entity.attack_blood;
import com.moonstone.moonstonemod.entity.owner_blood;
import com.moonstone.moonstonemod.init.moonstoneitem.Effects;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class blood extends TextureSheetParticle {
    public blood(ClientLevel level, double x, double y, double z, float movementX, float movementY, float movementZ) {
        super(level, x, y, z, movementX, movementY, movementZ);
        this.lifetime = 500;
        this.alpha = 0;
    }
    @Override
    protected int getLightColor(float p_107249_) {
        return 240;
    }

    @Override
    public @NotNull ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }
    private final List<Vec3> trailPositions = new ArrayList<>();

    public void tick() {

        trailPositions.add(new Vec3(this.x, this.y, this.z));

        if (trailPositions.size() > 33) {
            trailPositions.removeFirst();
        }

        Vec3 playerPos = this.getPos();
        float range = 16;

        List<owner_blood> entities =
                this.level.getEntitiesOfClass(owner_blood.class,
                        new AABB(playerPos.x - range,
                                playerPos.y - range,
                                playerPos.z - range,
                                playerPos.x + range,
                                playerPos.y + range,
                                playerPos.z + range));

        for (owner_blood ownerBlood : entities) {
            if (ownerBlood.getTarget() instanceof LivingEntity living){
                if (living.isAlive()) {
                    setv(living);
                }else {
                    setv(ownerBlood);
                }
            }else {
                setv(ownerBlood);
            }
        }
        this.alpha = 0;
        lifetime--;
        if (lifetime<=0){
            this.remove();
        }
        super.tick();
    }
   public void setv(Entity e    ){
       Vec3 targetPos = e.position().add(0, 0, 0); // 将 Y 坐标增加 heightOffset

       Vec3 currentPos = this.getPos();
       Vec3 direction = targetPos.subtract(currentPos).normalize();

       Vec3 currentDirection = new Vec3(this.xd, this.yd, this.zd).normalize();

       double angle = Math.acos(currentDirection.dot(direction)) * (180.0 / Math.PI);

       if (angle > 10) {
           double angleLimit = Math.toRadians(10); // 将5度转为弧度

           Vec3 limitedDirection = currentDirection.scale(Math.cos(angleLimit)) // 计算缩放因子
                   .add(direction.normalize().scale(Math.sin(angleLimit))); // 根据目标方向进行调整

           this.setParticleSpeed(limitedDirection.x * 0.5f, limitedDirection.y * 0.5f, limitedDirection.z * 0.5f);
       } else {
           this.setParticleSpeed(direction.x * 0.5f, direction.y * 0.5f, direction.z * 0.5f);
       }
   }
    public List<Vec3> getTrailPositions() {
        return trailPositions;
    }
    @OnlyIn(Dist.CLIENT)
    public record Provider(SpriteSet sprite) implements ParticleProvider<SimpleParticleType> {
        public Provider(SpriteSet sprite) {
            this.sprite = sprite;
        }


        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            blood particle = new blood(level, x, y, z, (float) xSpeed, (float) ySpeed, (float) zSpeed);
            particle.pickSprite(this.sprite);
            return particle;
        }
        public SpriteSet sprite() {
            return this.sprite;
        }
    }
}

