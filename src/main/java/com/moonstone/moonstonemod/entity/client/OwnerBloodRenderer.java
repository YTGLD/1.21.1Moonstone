package com.moonstone.moonstonemod.entity.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.moonstone.moonstonemod.ConfigClient;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.client.renderer.MRender;
import com.moonstone.moonstonemod.client.renderer.MoonPost;
import com.moonstone.moonstonemod.entity.owner_blood;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;

import java.util.List;

import static net.minecraft.client.renderer.texture.OverlayTexture.NO_OVERLAY;

public class OwnerBloodRenderer  extends EntityRenderer<owner_blood> {
    public OwnerBloodRenderer(EntityRendererProvider.Context p_173917_) {
        super(p_173917_);
    }

    @Override
    public void render(owner_blood entity, float p_114486_, float p_114487_, PoseStack poseStack, MultiBufferSource bufferSource, int p_114490_) {
        super.render(entity, p_114486_, p_114487_, poseStack, bufferSource, p_114490_);
        if (ConfigClient.Client.Shader.get()) {
            MoonPost.renderEffectForNextTick(MoonStoneMod.POST);
        }
        double x = Mth.lerp(p_114487_, entity.xOld, entity.getX());
        double y = Mth.lerp(p_114487_, entity.yOld, entity.getY());
        double z = Mth.lerp(p_114487_, entity.zOld, entity.getZ());
        if (entity.hasTrail()) {
            poseStack.pushPose();
            poseStack.translate(-x, -y, -z);
            if (ConfigClient.Client.light.get()) {
                setMatrices(poseStack, bufferSource, entity);
            }
            renderTrail(entity, p_114487_, poseStack, bufferSource, 220/255f, 20/255f, 60/255f, 240);
            poseStack.popPose();
        }
        renderSphere1(poseStack,bufferSource,240,0.5f);

    }
    public void setMatrices(@NotNull PoseStack matrices,
                            @NotNull MultiBufferSource vertexConsumers,
                            @NotNull Entity ownerBlood) {
        int rage = 20;
        float posAdd = 1.001F;

        BlockPos playerPos = ownerBlood.blockPosition();
        Vec3 playerVec = new Vec3(playerPos.getX(), playerPos.getY(), playerPos.getZ());

        for (int x = -rage; x <= rage; x++) {
            for (int y = -rage; y <= rage; y++) {
                for (int z = -rage; z <= rage; z++) {
                    BlockPos currentPos = playerPos.offset(x, y, z);
                    Vec3 currentVec = new Vec3(currentPos.getX(), currentPos.getY(), currentPos.getZ());
                    BlockState blockState = ownerBlood.level().getBlockState(currentPos);
                    if (!blockState.isEmpty() && !blockState.is(Blocks.AIR)) {
                        matrices.pushPose();

                        matrices.translate(currentPos.getX() + 0.5, currentPos.getY() + 0.5, currentPos.getZ() + 0.5);

                        matrices.scale(posAdd, posAdd, posAdd);

                        matrices.translate(-(currentPos.getX() + 0.5), -(currentPos.getY() + 0.5), -(currentPos.getZ() + 0.5));

                        matrices.translate(currentPos.getX(), currentPos.getY(), currentPos.getZ());

                        double distance = playerVec.distanceTo(currentVec);

                        float alp = Math.max(0, 1 - (float) distance / rage);

                        BakedModel bakedModel = Minecraft.getInstance().getBlockRenderer().getBlockModel(blockState);
                        for (Direction direction : Direction.values()) {
                            BlockPos offsetPos = currentPos.relative(direction);
                            if (!ownerBlood.level().getBlockState(offsetPos).isSolid()) {
                                List<BakedQuad> quads = bakedModel.getQuads(blockState, direction, RandomSource.create());
                                for (BakedQuad quad : quads) {
                                    if (alp*alp>0) {
                                        vertexConsumers.getBuffer(MRender.lightning()).putBulkData(matrices.last(), quad, new float[]{
                                                1.32f, 1.32f, 1.32f, 1.32f
                                        }, 1, 0, 0, alp * alp, new int[]{240, 240, 240, 240}, OverlayTexture.NO_OVERLAY, true);
                                    }
                                }
                            }
                        }

                        matrices.popPose();
                    }
                }
            }
        }
    }


    private void renderTrail(owner_blood entityIn, float partialTicks, PoseStack poseStack, MultiBufferSource bufferIn, float trailR, float trailG, float trailB, int packedLightIn) {
        int samples = 0;
        int sampleSize = owner_blood.max;
        float as = 0.3f; // 调整高度
        float trailZRot = 0;
        Vec3 topAngleVec = new Vec3(as, as, as).zRot(trailZRot);
        Vec3 bottomAngleVec = new Vec3(-as, -as, as).zRot(trailZRot);
        Vec3 drawFrom = entityIn.getTrailPosition(0, partialTicks);
        VertexConsumer vertexconsumer = bufferIn.getBuffer(MRender.LIGHTNING);

        while (samples < sampleSize - 1) { // 减少一个采样点以避免访问越界
            Vec3 sample = entityIn.getTrailPosition(samples + 1, partialTicks); // 修改这里的指针偏移量
            float u1 = samples / (float) sampleSize;
            float u2 = u1 + 1 / (float) sampleSize;

            // 计算动态透明度
            float alpha1 = 1 - u1; // 从1到0
            float alpha2 = 1 - u2; // 从1到0

            Vec3 draw1 = drawFrom;

            PoseStack.Pose posestack$pose = poseStack.last();
            Matrix4f matrix4f = posestack$pose.pose();

            // 添加四边形的四个顶点
            vertexconsumer.addVertex(matrix4f, (float) draw1.x + (float) bottomAngleVec.x, (float) draw1.y + (float) bottomAngleVec.y, (float) draw1.z + (float) bottomAngleVec.z).setColor(trailR, trailG, trailB, alpha1).setUv(0, 0).setOverlay(NO_OVERLAY).setUv2(packedLightIn,240).setNormal(0.0F, 0.0F, 0.0F);
            vertexconsumer.addVertex(matrix4f, (float) sample.x + (float) bottomAngleVec.x, (float) sample.y + (float) bottomAngleVec.y, (float) sample.z + (float) bottomAngleVec.z).setColor(trailR, trailG, trailB, alpha2).setUv(0, 0).setOverlay(NO_OVERLAY).setUv2(packedLightIn,240).setNormal(0.0F, 0.0F, 0.0F);
            vertexconsumer.addVertex(matrix4f, (float) sample.x + (float) topAngleVec.x, (float) sample.y + (float) topAngleVec.y, (float) sample.z + (float) topAngleVec.z).setColor(trailR, trailG, trailB, alpha2).setUv(0, 0).setOverlay(NO_OVERLAY).setUv2(packedLightIn,240).setNormal(0.0F, 0.0F, 0.0F);
            vertexconsumer.addVertex(matrix4f, (float) draw1.x + (float) topAngleVec.x, (float) draw1.y + (float) topAngleVec.y, (float) draw1.z + (float) topAngleVec.z).setColor(trailR, trailG, trailB, alpha1).setUv(0, 0).setOverlay(NO_OVERLAY).setUv2(packedLightIn,240).setNormal(0.0F, 0.0F, 0.0F);

            samples++;
            drawFrom = sample;
        }
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




