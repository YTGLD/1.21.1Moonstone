package com.moonstone.moonstonemod.client.particle;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.moonstone.moonstonemod.entity.AtSword;
import com.moonstone.moonstonemod.entity.owner_blood;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class blue extends TextureSheetParticle {
    public blue(ClientLevel level, double x, double y, double z, float movementX, float movementY, float movementZ) {
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

        if (trailPositions.size() > 15) {
            trailPositions.removeFirst();
        }

        Vec3 playerPos = this.getPos();
        float range = 16;

        List<AtSword> entities =
                this.level.getEntitiesOfClass(AtSword.class,
                        new AABB(playerPos.x - range,
                                playerPos.y - range,
                                playerPos.z - range,
                                playerPos.x + range,
                                playerPos.y + range,
                                playerPos.z + range));

        for (AtSword atSword : entities) {
            if (atSword.isNoGravity()) {
                setv(atSword);
            }
        }
        this.alpha = 0;
        lifetime--;
        if (lifetime<=0){
            this.remove();
        }
        super.tick();
    }
    public void setv(Entity e){
        Vec3 targetPos = e.position().add(0, 0, 0); // 将 Y 坐标增加 heightOffset

        Vec3 currentPos = this.getPos();
        Vec3 direction = targetPos.subtract(currentPos).normalize();

        Vec3 currentDirection = new Vec3(this.xd, this.yd, this.zd).normalize();

        double angle = Math.acos(currentDirection.dot(direction)) * (180.0 / Math.PI);

        if (angle > 10) {
            double angleLimit = Math.toRadians(10);

            Vec3 limitedDirection = currentDirection.scale(Math.cos(angleLimit)) // 计算缩放因子
                    .add(direction.normalize().scale(Math.sin(angleLimit))); // 根据目标方向进行调整

            this.setParticleSpeed(limitedDirection.x * 0.5f, limitedDirection.y * 0.5f, limitedDirection.z * 0.5f);
        } else {
            this.setParticleSpeed(direction.x * 0.5f, direction.y * 0.5f, direction.z * 0.5f);
        }
    }
    public void setBlue(PoseStack matrices,
                              blue entity,
                              VertexConsumer vertexConsumers)
    {
        matrices.pushPose();

        for (int i = 1; i < entity.getTrailPositions().size(); i++){
            Vec3 prevPos = entity.getTrailPositions().get(i - 1);
            Vec3 currPos = entity.getTrailPositions().get(i);
            Vec3 adjustedPrevPos = new Vec3(prevPos.x - entity.getPos().x, prevPos.y - entity.getPos().y, prevPos.z - entity.getPos().z);
            Vec3 adjustedCurrPos = new Vec3(currPos.x - entity.getPos().x, currPos.y - entity.getPos().y, currPos.z - entity.getPos().z);

            float alpha = (float)(i) / (float)(entity.getTrailPositions().size());

            renderBlood(matrices, vertexConsumers, adjustedPrevPos, adjustedCurrPos, alpha, RenderType.lightning(),0.1f);
        }
        matrices.popPose();
    }

    public void renderBlood(PoseStack poseStack, VertexConsumer vertexConsumer, Vec3 start, Vec3 end, float a, RenderType renderType, float r) {
        int segmentCount = 16; // 圆柱横向细分数

        for (int i = 0; i < segmentCount; i++) {
            double angle1 = (2 * Math.PI * i) / segmentCount;
            double angle2 = (2 * Math.PI * (i + 1)) / segmentCount;

            double x1 = Math.cos(angle1) * r;
            double z1 = Math.sin(angle1) * r;
            double x2 = Math.cos(angle2) * r;
            double z2 = Math.sin(angle2) * r;

            Vec3 up1 = start.add(x1, 0, z1);
            Vec3 up2 = start.add(x2, 0, z2);
            Vec3 down1 = end.add(x1, 0, z1);
            Vec3 down2 = end.add(x2, 0, z2);


            addSquare(vertexConsumer, poseStack, up1, up2, down1, down2, a);
        }
    }
    private void addSquare(VertexConsumer vertexConsumer, PoseStack poseStack, Vec3 up1, Vec3 up2, Vec3 down1, Vec3 down2, float alpha) {
        // 添加四个顶点来绘制一个矩形
        vertexConsumer.addVertex(poseStack.last().pose(), (float) up1.x, (float) up1.y, (float) up1.z)
                .setColor(	187, 255, 255, (int) (alpha * 255))
                .setUv2(240, 240)
                .setNormal(0, 0, 1);

        vertexConsumer.addVertex(poseStack.last().pose(), (float) down1.x, (float) down1.y, (float) down1.z)
                .setColor(	187, 255, 255, (int) (alpha * 255))
                .setUv2(240, 240)
                .setNormal(0, 0, 1);

        vertexConsumer.addVertex(poseStack.last().pose(), (float) down2.x, (float) down2.y, (float) down2.z)
                .setColor(	187, 255, 255, (int) (alpha * 255))
                .setUv2(240, 240)
                .setNormal(0, 0, 1);

        vertexConsumer.addVertex(poseStack.last().pose(), (float) up2.x, (float) up2.y, (float) up2.z)
                .setColor(	187, 255, 255, (int) (alpha * 255))
                .setUv2(240, 240)
                .setNormal(0, 0, 1);
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
            blue particle = new blue(level, x, y, z, (float) xSpeed, (float) ySpeed, (float) zSpeed);
            particle.pickSprite(this.sprite);
            return particle;
        }
        public SpriteSet sprite() {
            return this.sprite;
        }
    }
}
