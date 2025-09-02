package com.moonstone.moonstonemod.client.particle;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

public class popr  extends TextureSheetParticle {
    public popr(ClientLevel level, double x, double y, double z, float movementX, float movementY, float movementZ) {
        super(level, x, y, z, movementX/3f, movementY, movementZ/3f);
        this.lifetime = 200;
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
    public int size = 5;
    public float alp = 255;
     float radius = 0.5f;
    public void tick() {

        this.alpha = 0;
        lifetime--;
        alp -= 51 / 10f / 255f;
        radius+=0.05f;
        if (lifetime <= 0) {
            this.remove();
        }
        super.tick();
    }

    public void renderBlack(@NotNull PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, float x, float y, float z
            , popr entity) {
        poseStack.mulPose(Axis.XP.rotationDegrees((float) (-entity.lifetime)));
        poseStack.mulPose(Axis.YP.rotationDegrees((float) (-entity.lifetime)));

        for (int i = 0; i < size; i++) {
            float vAngle1 = (float) (Math.PI * i / size);
            float vAngle2 = (float) (Math.PI * (i + 1) / size);

            for (int j = 0; j < size; j++) {
                float hAngle1 = (float) (2 * Math.PI * j / size);
                float hAngle2 = (float) (2 * Math.PI * (j + 1) / size);

                float x1 = (float) (x + radius * Math.sin(vAngle1) * Math.cos(hAngle1));
                float y1 = (float) (y + radius * Math.cos(vAngle1));
                float z1 = (float) (z + radius * Math.sin(vAngle1) * Math.sin(hAngle1));

                float x2 = (float) (x + radius * Math.sin(vAngle1) * Math.cos(hAngle2));
                float y2 = (float) (y + radius * Math.cos(vAngle1));
                float z2 = (float) (z + radius * Math.sin(vAngle1) * Math.sin(hAngle2));

                float x3 = (float) (x + radius * Math.sin(vAngle2) * Math.cos(hAngle1));
                float y3 = (float) (y + radius * Math.cos(vAngle2));
                float z3 = (float) (z + radius * Math.sin(vAngle2) * Math.sin(hAngle1));

                float x4 = (float) (x + radius * Math.sin(vAngle2) * Math.cos(hAngle2));
                float y4 = (float) (y + radius * Math.cos(vAngle2));
                float z4 = (float) (z + radius * Math.sin(vAngle2) * Math.sin(hAngle2));

                // 绘制两个三角形
                vertexConsumer.addVertex(poseStack.last().pose(), x1, y1, z1).setColor(0, 0, 0, alp).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x2, y2, z2).setColor(0, 0, 0, alp).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x3, y3, z3).setColor(0, 0, 0, alp).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);

                vertexConsumer.addVertex(poseStack.last().pose(), x2, y2, z2).setColor(0, 0, 0, alp).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x3, y3, z3).setColor(0, 0, 0, alp).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x4, y4, z4).setColor(0, 0, 0, alp).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    public record Provider(SpriteSet sprite) implements ParticleProvider<SimpleParticleType> {
        public Provider(SpriteSet sprite) {
            this.sprite = sprite;
        }


        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            popr particle = new popr(level, x, y, z, (float) xSpeed/3f, (float) ySpeed, (float) zSpeed/3f);
            particle.pickSprite(this.sprite);
            return particle;
        }
        public SpriteSet sprite() {
            return this.sprite;
        }
    }
}

