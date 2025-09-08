package com.moonstone.moonstonemod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.client.renderer.MRender;
import com.moonstone.moonstonemod.client.renderer.MoonPost;
import com.moonstone.moonstonemod.entity.owner_blood;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import com.tbl.client.shader.LightSource;
import com.tbl.client.shader.ShaderHelper;
import com.tbl.client.shader.postprocessing.WorldShader;

import java.awt.event.WindowFocusListener;

public class OwnerBloodRenderer  extends EntityRenderer<owner_blood> {
    public OwnerBloodRenderer(EntityRendererProvider.Context p_173917_) {
        super(p_173917_);
    }

    @Override
    public boolean shouldRender(owner_blood livingEntity, Frustum camera, double camX, double camY, double camZ) {
        return true;
    }

    @Override
    public void render(owner_blood entity, float p_114486_, float p_114487_, PoseStack poseStack, MultiBufferSource bufferSource, int p_114490_) {
        super.render(entity, p_114486_, p_114487_, poseStack, bufferSource, p_114490_);
        if (com.ytgld.seeking_immortals.ClientConfig.CLIENT_CONFIG.Shader.get()) {
            MoonPost.renderEffectForNextTick(MoonStoneMod.POST_Blood);
        }

        if (ShaderHelper.INSTANCE.isWorldShaderActive()) {
            WorldShader shader = ShaderHelper.INSTANCE.getWorldShader();
            ShaderHelper.INSTANCE.require();
            if (shader != null) {
                shader.addLight(new LightSource(entity.getX(), entity.getY(), entity.getZ(), 12, 2.25f, 0.2f, 0.2f));
            }
        }
        double x = Mth.lerp(p_114487_, entity.xOld, entity.getX());
        double y = Mth.lerp(p_114487_, entity.yOld, entity.getY());
        double z = Mth.lerp(p_114487_, entity.zOld, entity.getZ());

        poseStack.pushPose();
        poseStack.translate(entity.getX()-x, entity.getY()-y,entity.getZ() -z);
        setT(poseStack, entity, bufferSource);
        poseStack.popPose();

        renderSphere1(poseStack,bufferSource,240,0.5f);
    }

    private void setT(PoseStack matrices,
                      owner_blood entity,
                      MultiBufferSource vertexConsumers)
    {
        matrices.pushPose();

        for (int i = 1; i < entity.getTrailPositions().size(); i++){
            Vec3 prevPos = entity.getTrailPositions().get(i - 1);
            Vec3 currPos = entity.getTrailPositions().get(i);
            Vec3 adjustedPrevPos = new Vec3(prevPos.x - entity.getX(), prevPos.y - entity.getY(), prevPos.z - entity.getZ());
            Vec3 adjustedCurrPos = new Vec3(currPos.x - entity.getX(), currPos.y - entity.getY(), currPos.z - entity.getZ());

            float alpha = (float)(i) / (float)(entity.getTrailPositions().size());

            Handler.renderBlood(matrices, vertexConsumers, adjustedPrevPos, adjustedCurrPos, alpha, MRender.LIGHTNING,0.35f);
        }
        matrices.popPose();
    }


    public void renderSphere1(@NotNull PoseStack matrices, @NotNull MultiBufferSource vertexConsumers, int light, float s) {
        {
            int stacks = 20; // 垂直方向的分割数
            int slices = 20; // 水平方向的分割数
            VertexConsumer vertexConsumer = vertexConsumers.getBuffer(MRender.gateways());
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

                    vertexConsumer.addVertex(matrices.last().pose(), x0, y0, z0).setColor(1.0f, 1.0f, 1.0f, 1.0f).setOverlay(OverlayTexture.NO_OVERLAY).setUv(0, 0).setUv2(light, light).setNormal(1, 0, 0);
                    vertexConsumer.addVertex(matrices.last().pose(), x1, y1, z1).setColor(1.0f, 1.0f, 1.0f, 1.0f).setOverlay(OverlayTexture.NO_OVERLAY).setUv(0, 0).setUv2(light, light).setNormal(1, 0, 0);
                    vertexConsumer.addVertex(matrices.last().pose(), x2, y2, z2).setColor(1.0f, 1.0f, 1.0f, 1.0f).setOverlay(OverlayTexture.NO_OVERLAY).setUv(0, 0).setUv2(light, light).setNormal(1, 0, 0);
                    vertexConsumer.addVertex(matrices.last().pose(), x3, y3, z3).setColor(1.0f, 1.0f, 1.0f, 1.0f).setOverlay(OverlayTexture.NO_OVERLAY).setUv(0, 0).setUv2(light, light).setNormal(1, 0, 0);
                }
            }
        }
        {
            int stacks = 20; // 垂直方向的分割数
            int slices = 20; // 水平方向的分割数
            VertexConsumer vertexConsumer = vertexConsumers.getBuffer(MRender.Snake());
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

                    vertexConsumer.addVertex(matrices.last().pose(), x0, y0, z0).setColor(1.0f, 1.0f, 1.0f, 1.0f).setOverlay(OverlayTexture.NO_OVERLAY).setUv(0, 0).setUv2(light, light).setNormal(1, 0, 0);
                    vertexConsumer.addVertex(matrices.last().pose(), x1, y1, z1).setColor(1.0f, 1.0f, 1.0f, 1.0f).setOverlay(OverlayTexture.NO_OVERLAY).setUv(0, 0).setUv2(light, light).setNormal(1, 0, 0);
                    vertexConsumer.addVertex(matrices.last().pose(), x2, y2, z2).setColor(1.0f, 1.0f, 1.0f, 1.0f).setOverlay(OverlayTexture.NO_OVERLAY).setUv(0, 0).setUv2(light, light).setNormal(1, 0, 0);
                    vertexConsumer.addVertex(matrices.last().pose(), x3, y3, z3).setColor(1.0f, 1.0f, 1.0f, 1.0f).setOverlay(OverlayTexture.NO_OVERLAY).setUv(0, 0).setUv2(light, light).setNormal(1, 0, 0);
                }
            }
        }
    }
    @Override
    public ResourceLocation getTextureLocation(owner_blood p_114482_) {
        return ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,"textures/entity/flysword.png");
    }
}




