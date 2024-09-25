package com.moonstone.moonstonemod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.client.renderer.MRender;
import com.moonstone.moonstonemod.entity.zombie.blood_zombie_fly;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import org.jetbrains.annotations.NotNull;

public class BloodSwordRenderer <T extends ThrowableItemProjectile> extends EntityRenderer<T> {
    public BloodSwordRenderer(EntityRendererProvider.Context p_173917_) {
        super(p_173917_);
    }

    public void render(T pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.pushPose();

        renderSphere1(pPoseStack,pBuffer,240,pEntity);

        pPoseStack.popPose();
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
    @Override
    public @NotNull ResourceLocation getTextureLocation(T p_114482_) {
        return ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID, "textures/entity/flysword.png");
    }
    public void renderSphere1(@NotNull PoseStack matrices, @NotNull MultiBufferSource vertexConsumers, int light, Entity entity) {
        if (entity instanceof blood_zombie_fly zombieFly) {
            float radius = 0.2f; // 球体的半径
            int stacks = 20; // 垂直方向的分割数
            int slices = 20; // 水平方向的分割数
            float a = zombieFly.age;
            if (a>20f){
                a=20f;
            }
            a/=2f;

            matrices.scale(a/10f,a/10f,a/10f);
            VertexConsumer vertexConsumer = vertexConsumers.getBuffer(MRender.gateways());
            for (int i = 0; i < stacks; ++i) {
                float phi0 = (float) Math.PI * ((i + 0) / (float) stacks);
                float phi1 = (float) Math.PI * ((i + 1) / (float) stacks);

                for (int j = 0; j < slices; ++j) {
                    float theta0 = (float) (2 * Math.PI) * ((j + 0) / (float) slices);
                    float theta1 = (float) (2 * Math.PI) * ((j + 1) / (float) slices);

                    float x0 = radius * (float) Math.sin(phi0) * (float) Math.cos(theta0);
                    float y0 = radius * (float) Math.cos(phi0);
                    float z0 = radius * (float) Math.sin(phi0) * (float) Math.sin(theta0);

                    float x1 = radius * (float) Math.sin(phi0) * (float) Math.cos(theta1);
                    float y1 = radius * (float) Math.cos(phi0);
                    float z1 = radius * (float) Math.sin(phi0) * (float) Math.sin(theta1);

                    float x2 = radius * (float) Math.sin(phi1) * (float) Math.cos(theta1);
                    float y2 = radius * (float) Math.cos(phi1);
                    float z2 = radius * (float) Math.sin(phi1) * (float) Math.sin(theta1);

                    float x3 = radius * (float) Math.sin(phi1) * (float) Math.cos(theta0);
                    float y3 = radius * (float) Math.cos(phi1);
                    float z3 = radius * (float) Math.sin(phi1) * (float) Math.sin(theta0);

                    vertexConsumer.addVertex(matrices.last().pose(), x0, y0, z0).setColor(1.0f, 1.0f, 1.0f, 1.0f).setOverlay(OverlayTexture.NO_OVERLAY).setUv(0, 0).setUv2(light, light).setNormal(1, 0, 0);
                    vertexConsumer.addVertex(matrices.last().pose(), x1, y1, z1).setColor(1.0f, 1.0f, 1.0f, 1.0f).setOverlay(OverlayTexture.NO_OVERLAY).setUv(0, 0).setUv2(light, light).setNormal(1, 0, 0);
                    vertexConsumer.addVertex(matrices.last().pose(), x2, y2, z2).setColor(1.0f, 1.0f, 1.0f, 1.0f).setOverlay(OverlayTexture.NO_OVERLAY).setUv(0, 0).setUv2(light, light).setNormal(1, 0, 0);
                    vertexConsumer.addVertex(matrices.last().pose(), x3, y3, z3).setColor(1.0f, 1.0f, 1.0f, 1.0f).setOverlay(OverlayTexture.NO_OVERLAY).setUv(0, 0).setUv2(light, light).setNormal(1, 0, 0);
                }
            }
        }
    }
    public void vertex(
            PoseStack.Pose pPose,
            VertexConsumer pConsumer,
            int pX,
            int pY,
            int pZ,
            float pU,
            float pV,
            int pNormalX,
            int pNormalY,
            int pNormalZ,
            int pPackedLight
    ) {
        pConsumer.addVertex(pPose, (float)pX, (float)pY, (float)pZ)
                .setColor(-1)
                .setUv(pU, pV)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(pPackedLight)
                .setNormal(pPose, (float)pNormalX, (float)pNormalZ, (float)pNormalY);
    }
}

