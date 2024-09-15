package com.moonstone.moonstonemod.client.particle;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class red extends TextureSheetParticle {
    public red(ClientLevel level, double x, double y, double z, float movementX, float movementY, float movementZ) {
        super(level, x, y, z, movementX, movementY, movementZ);
        this.lifetime = 450;
        this.quadSize *= 0.8f;
    }
    @Override
    protected int getLightColor(float p_107249_) {
        return 240;
    }
//
//    @Override
//    public void render(VertexConsumer buffer, Camera camera, float partialTicks) {
//        super.render(buffer, camera, partialTicks);
//
//        Vec3 vec3 = camera.getPosition();
//
//        for (int i = 1; i < trailPositions.size(); i++){
//            Vec3 prevPos = trailPositions.get(i - 1);
//            Vec3 currPos = trailPositions.get(i);
//            Vec3 adjustedPrevPos = new Vec3(prevPos.x - vec3.x, prevPos.y -vec3.y, prevPos.z - vec3.z);
//            Vec3 adjustedCurrPos = new Vec3(currPos.x - vec3.x, currPos.y -vec3.y, currPos.z - vec3.z);
//            renderLine(buffer, adjustedPrevPos, adjustedCurrPos, 1);
//        }
//    }
//    private final List<Vec3> trailPositions = new ArrayList<>();
//
//    public static void renderLine(VertexConsumer vertexConsumer, Vec3 start, Vec3 end, float a) {
//        // 设置线条的起点
//        vertexConsumer.addVertex((float) start.x, (float) start.y, (float) start.z)
//                .setColor(1,1,1,a)
//                .setUv(0,0)
//                .setUv2(240,240)
//                .setNormal(1,0,0);
//
//        // 设置线条的终点
//        vertexConsumer.addVertex((float) end.x, (float) end.y, (float) end.z)
//                .setColor(1,1,1,a)
//                .setUv(0,0)
//                .setUv2(240,240)
//                .setNormal(1,0,0);
//    }
    @Override
    public @NotNull ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }
    public void tick() {

        this.alpha -= 0.01f;
        if (alpha<=0){
            this.remove();
        }
        super.tick();
//
//        trailPositions.add(new Vec3(this.getPos().x, this.getPos().y, this.getPos().z));
//
//        if (trailPositions.size()>50){
//            trailPositions.removeFirst();
//        }
    }
    @OnlyIn(Dist.CLIENT)
    public record Provider(SpriteSet sprite) implements ParticleProvider<SimpleParticleType> {
        public Provider(SpriteSet sprite) {
            this.sprite = sprite;
        }


        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            red particle = new red(level, x, y, z, 0, 0, 0);
            particle.pickSprite(this.sprite);
            return particle;
        }
        public SpriteSet sprite() {
            return this.sprite;
        }
    }
}
