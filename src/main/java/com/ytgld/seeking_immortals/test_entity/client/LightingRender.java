package com.ytgld.seeking_immortals.test_entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.ytgld.seeking_immortals.SeekingImmortalsMod;
import com.ytgld.seeking_immortals.renderer.MRender;
import com.ytgld.seeking_immortals.renderer.MoonPost;
import com.ytgld.seeking_immortals.renderer.light.Light;
import com.ytgld.seeking_immortals.test_entity.lighting;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public class LightingRender <T extends lighting> extends net.minecraft.client.renderer.entity.EntityRenderer<T> {
    public LightingRender(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(T t) {
        return null;
    }

    @Override
    public boolean shouldRender(T livingEntity, Frustum camera, double camX, double camY, double camZ) {
        return true;
    }

    @Override
    public void render(T p_entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        MoonPost.renderEffectForNextTick(SeekingImmortalsMod.POST);
        double x = Mth.lerp(partialTick, p_entity.xOld, p_entity.getX());
        double y = Mth.lerp(partialTick, p_entity.yOld, p_entity.getY());
        double z = Mth.lerp(partialTick, p_entity.zOld, p_entity.getZ());
        poseStack.pushPose();
        poseStack.translate(p_entity.getX()-x, p_entity.getY()-y,p_entity.getZ() -z);
        renderSphere1(poseStack,bufferSource.getBuffer(MRender.light),240,p_entity.tickCount / 5f, 1 - p_entity.tickCount / 20f);
        renderBeaconBeam(poseStack,bufferSource,partialTick,p_entity.tickCount / 20f,p_entity.level().getGameTime(),1 - p_entity.tickCount / 20,10, Light.ARGB.color(255,255,66,200),(1 - p_entity.tickCount / 20f)/5f,(1 - p_entity.tickCount / 20f)/5f);
        poseStack.popPose();

    }
    public static void renderBeaconBeam(PoseStack poseStack, MultiBufferSource bufferSource, float partialTick, float textureScale, long gameTime, int yOffset, int height, int color, float beamRadius, float glowRadius) {
        int i = yOffset + height;
        poseStack.pushPose();
        poseStack.translate(0.5, 0.0, 0.5);
        float f = (float)Math.floorMod(gameTime, 40) + partialTick;
        float f1 = height < 0 ? f : -f;
        float f2 = Mth.frac(f1 * 0.2F - (float)Mth.floor(f1 * 0.1F));
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(f * 2.25F - 45.0F));
        float f3 = 0.0F;
        float f5 = 0.0F;
        float f6 = -beamRadius;
        float f12;
        float f13;
        poseStack.popPose();
        f3 = -glowRadius;
        float f4 = -glowRadius;
        f5 = -glowRadius;
        f6 = -glowRadius;
        f12 = -1.0F + f2;
        f13 = (float)height * textureScale + f12;
        renderPart(poseStack, bufferSource.getBuffer(MRender.endBloodOutline), FastColor.ARGB32.color(255, color), yOffset, i, f3, f4, glowRadius, f5, f6, glowRadius, glowRadius, glowRadius, 0.0F, 1.0F, f13, f12);
        poseStack.popPose();
    }

    private static void renderPart(PoseStack poseStack, VertexConsumer consumer, int color, int minY, int maxY, float x1, float z1, float x2, float z2, float x3, float z3, float x4, float z4, float minU, float maxU, float minV, float maxV) {
        PoseStack.Pose posestack$pose = poseStack.last();
        renderQuad(posestack$pose, consumer, color, minY, maxY, x1, z1, x2, z2, minU, maxU, minV, maxV);
        renderQuad(posestack$pose, consumer, color, minY, maxY, x4, z4, x3, z3, minU, maxU, minV, maxV);
        renderQuad(posestack$pose, consumer, color, minY, maxY, x2, z2, x4, z4, minU, maxU, minV, maxV);
        renderQuad(posestack$pose, consumer, color, minY, maxY, x3, z3, x1, z1, minU, maxU, minV, maxV);
    }

    private static void renderQuad(PoseStack.Pose pose, VertexConsumer consumer, int color, int minY, int maxY, float minX, float minZ, float maxX, float maxZ, float minU, float maxU, float minV, float maxV) {
        addVertex(pose, consumer, color, maxY, minX, minZ, maxU, minV);
        addVertex(pose, consumer, color, minY, minX, minZ, maxU, maxV);
        addVertex(pose, consumer, color, minY, maxX, maxZ, minU, maxV);
        addVertex(pose, consumer, color, maxY, maxX, maxZ, minU, minV);
    }
    private static void addVertex(PoseStack.Pose pose, VertexConsumer consumer, int color, int y, float x, float z, float u, float v) {
        consumer.addVertex(pose, x, (float)y, z).setColor(color).setUv(u, v).setOverlay(OverlayTexture.NO_OVERLAY).setLight(15728880).setNormal(pose, 0.0F, 1.0F, 0.0F);
    }

    public void renderSphere1(@NotNull PoseStack matrices, @NotNull VertexConsumer vertexConsumer, int light, float s,float a   ) {
        int stacks = 20;
        int slices = 20;
        for (int i = 0; i < stacks; ++i) {
            float phi0 = (float) Math.PI * ((i + 0) / (float) stacks);
            float phi1 = (float) Math.PI * ((i + 1) / (float) stacks);

            for (int j = 0; j < slices; ++j) {
                float theta0 = (float) (2 * Math.PI) * ((j + 0) / (float) slices);
                float theta1 = (float) (2 * Math.PI) * ((j + 1) / (float) slices);

                float x0 = s * (float) Math.sin(phi0) * (float) Math.cos(theta0);
                float y0 = s * (float) Math.cos(phi0);
                float z0 = s * (float) Math.sin(phi0) * (float) Math.sin(theta0);

                float x1 = s * (float) Math.sin(phi0) * (float) Math.cos(theta1);
                float y1 = s * (float) Math.cos(phi0);
                float z1 = s * (float) Math.sin(phi0) * (float) Math.sin(theta1);

                float x2 = s * (float) Math.sin(phi1) * (float) Math.cos(theta1);
                float y2 = s * (float) Math.cos(phi1);
                float z2 = s * (float) Math.sin(phi1) * (float) Math.sin(theta1);

                float x3 = s * (float) Math.sin(phi1) * (float) Math.cos(theta0);
                float y3 = s * (float) Math.cos(phi1);
                float z3 = s * (float) Math.sin(phi1) * (float) Math.sin(theta0);

                vertexConsumer.addVertex(matrices.last().pose(), x0, y0, z0).setColor(1.0f, 0.25f, 0.80f, a).setOverlay(OverlayTexture.NO_OVERLAY).setUv(0, 0).setUv2(light, light).setNormal(1, 0, 0);
                vertexConsumer.addVertex(matrices.last().pose(), x1, y1, z1).setColor(1.0f, 0.25f, 0.80f, a).setOverlay(OverlayTexture.NO_OVERLAY).setUv(0, 0).setUv2(light, light).setNormal(1, 0, 0);
                vertexConsumer.addVertex(matrices.last().pose(), x2, y2, z2).setColor(1.0f, 0.25f, 0.80f, a).setOverlay(OverlayTexture.NO_OVERLAY).setUv(0, 0).setUv2(light, light).setNormal(1, 0, 0);
                vertexConsumer.addVertex(matrices.last().pose(), x3, y3, z3).setColor(1.0f, 0.25f, 0.80f, a).setOverlay(OverlayTexture.NO_OVERLAY).setUv(0, 0).setUv2(light, light).setNormal(1, 0, 0);
            }
        }
    }
}


