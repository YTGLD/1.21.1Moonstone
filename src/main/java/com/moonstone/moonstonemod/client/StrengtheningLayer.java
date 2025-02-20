package com.moonstone.moonstonemod.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.moonstone.moonstonemod.ConfigClient;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.client.renderer.MRender;
import com.moonstone.moonstonemod.client.renderer.MoonPost;
import com.moonstone.moonstonemod.entity.bloodvruis.test_blood;
import com.moonstone.moonstonemod.entity.nightmare.nightmare_giant;
import com.moonstone.moonstonemod.entity.zombie.blood_zombie;
import com.moonstone.moonstonemod.entity.zombie.blood_zombie_boom;
import com.moonstone.moonstonemod.entity.zombie.red_entity;
import com.moonstone.moonstonemod.init.items.Items;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;

public class StrengtheningLayer<T extends LivingEntity, M extends EntityModel<T>> extends  RenderLayer {
    public StrengtheningLayer(RenderLayerParent<T, M> p_117346_) {
        //noinspection unchecked
        super(p_117346_);
    }
    /**
        matrices.translate( 左右 , 上下 , 前后 );
     **/
    @Override
    public void render(@NotNull PoseStack matrices,
                       @NotNull MultiBufferSource vertexConsumers,
                       int light,
                       @NotNull Entity entity,
                       float limbAngle, float limbDistance,
                       float tickDelta, float animationProgress,
                       float headYaw, float headPitch) {
        new orb(matrices, vertexConsumers, light, entity);

        new Sword(matrices, vertexConsumers, light, entity);

        if (entity instanceof blood_zombie_boom){
            matrices.pushPose();
            matrices.scale(0.5f,0.5f,0.5f);
            new BloodBoom(matrices, vertexConsumers, light, entity);
            matrices.popPose();
        }
        if (entity instanceof test_blood){
            matrices.scale(0.33f,0.33f,0.33f);
            new CircleCubeBoom(matrices, vertexConsumers, light, entity);
        }
        if (entity instanceof nightmare_giant) {
            if (ConfigClient.Client.Shader.get()) {
                MoonPost.renderEffectForNextTick(MoonStoneMod.POST_Blood);
            }
            matrices.pushPose();
            matrices.translate(0, 0.175, -0.15);
            renderSphere1(matrices, vertexConsumers, 240, 0.4f);
            matrices.popPose();
        }
        if (entity instanceof Player player) {
            if (Handler.hascurio(player, Items.nightmare_head.get())) {
                matrices.pushPose();
                Nig(matrices, vertexConsumers, light, entity, limbAngle, limbDistance, tickDelta, animationProgress, headYaw, headPitch);
                matrices.popPose();
                matrices.pushPose();
                Nig3(matrices, vertexConsumers, light, entity, limbAngle, limbDistance, tickDelta, animationProgress, headYaw, headPitch);
                matrices.popPose();
                matrices.pushPose();
                renderCircle3(matrices, vertexConsumers, light, 0.0F, 0.0F, 0.0F, 0.2F, entity);
                matrices.popPose();
                matrices.pushPose();
                renderCircle4(matrices, vertexConsumers, light, 0.0F, 0.0F, 0.0F, 0.2F, entity);
                matrices.popPose();
                matrices.pushPose();
                renderCircle6(matrices, vertexConsumers, light, 0.0F, 0.0F, 0.0F, 0.2F, entity);
                matrices.popPose();
                matrices.pushPose();
                renderCircle5(matrices, vertexConsumers, light, 0.0F, 0.0F, 0.0F, 0.2F, entity);
                matrices.popPose();
                matrices.pushPose();
                renderCircle7(matrices, vertexConsumers, light, 0.0F, 0.0F, 0.0F, 0.2F, entity);
                matrices.popPose();
                matrices.pushPose();
                renderCircle8(matrices, vertexConsumers, light, 0.0F, 0.0F, 0.0F, 0.2F, entity);
                matrices.popPose();
            }
        }
        if (entity instanceof Player player) {
            if (Handler.hascurio(player,Items.nightmare_cube.get())){
                if (!player.getCooldowns().isOnCooldown(Items.nightmare_cube.get())){
                    new BlackCube(matrices, vertexConsumers, light, entity);
                }
            }
        }
        if (entity instanceof red_entity) {
            new CircleCube(matrices, vertexConsumers, light, entity);
        }


        if (entity instanceof blood_zombie){
            new BloodBoom(matrices, vertexConsumers, light, entity);
        }
    }
    public void renderSphere1(@NotNull PoseStack matrices, @NotNull MultiBufferSource vertexConsumers, int light, float s) {
        {
            int stacks = 20; // 垂直方向的分割数
            int slices = 20; // 水平方向的分割数
            VertexConsumer vertexConsumer = vertexConsumers.getBuffer(MRender.ging());
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
            VertexConsumer vertexConsumer = vertexConsumers.getBuffer(MRender.Snake_p_blood);
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
    public void Nig(@NotNull PoseStack matrices,
                          @NotNull MultiBufferSource vertexConsumers,
                          int light,
                          @NotNull Entity entity,
                          float limbAngle, float limbDistance,
                          float tickDelta, float animationProgress,
                          float headYaw, float headPitch) {

        EntityRenderer<? super LivingEntity> render = Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(entity);
        if (render instanceof LivingEntityRenderer) {
            this.renderCircle(matrices,vertexConsumers,light,0,0,entity);
        }

    }

    public void Nig3(@NotNull PoseStack matrices,
                     @NotNull MultiBufferSource vertexConsumers,
                     int light,
                     @NotNull Entity entity,
                     float limbAngle, float limbDistance,
                     float tickDelta, float animationProgress,
                     float headYaw, float headPitch) {

        EntityRenderer<? super LivingEntity> render = Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(entity);
        if (render instanceof LivingEntityRenderer) {
            this.renderCircle2(matrices,vertexConsumers,light,0,0,entity);
        }
    }
    public void renderCircle(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, float x, float y,Entity entity) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.lines());
        int segments = 64; // 圆的段数
        float radius = 0.5F; // 圆的半径
        poseStack.translate(0 ,-1 ,0);
        poseStack.mulPose(Axis.XP.rotationDegrees((float) (entity.tickCount)));
        poseStack.mulPose(Axis.YP.rotationDegrees((float) (entity.tickCount * 1.289)));

        for (int i = 0; i < segments; i++) {
            float angle1 = (float) (2 * Math.PI * i / segments);
            float angle2 = (float) (2 * Math.PI * (i + 1) / segments);

            float x1 = (float) (x + radius * Math.cos(angle1));
            float y1 = (float) (y + radius * Math.sin(angle1));
            float x2 = (float) (x + radius * Math.cos(angle2));
            float y2 = (float) (y + radius * Math.sin(angle2));

            vertexConsumer.addVertex(poseStack.last().pose(), x1, y1, 0).setColor(0.8F, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
            vertexConsumer.addVertex(poseStack.last().pose(), x2, y2, 0).setColor(0.8F, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
        }
    }
    public void black_hold(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, float x, float y,Entity entity,float rgb) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(MRender.t());
        int segments = 64; // 圆的段数
        float radius = 1.5f; // 圆的半径
        // poseStack.mulPose(Axis.XP.rotationDegrees((float) (entity.tickCount)));
        poseStack.mulPose(Axis.YP.rotationDegrees((float) (entity.tickCount * 1.289)));

        for (int i = 0; i < segments; i++) {
            float angle1 = (float) (2 * Math.PI * i / segments);
            float angle2 = (float) (2 * Math.PI * (i + 1) / segments);

            float x1 = (float) (x + radius * Math.cos(angle1));
            float y1 = (float) (y + radius * Math.sin(angle1));
            float x2 = (float) (x + radius * Math.cos(angle2));
            float y2 = (float) (y + radius * Math.sin(angle2));

            vertexConsumer.addVertex(poseStack.last().pose(), x1, y1, 0).setColor(1, 0, 0, rgb).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
            vertexConsumer.addVertex(poseStack.last().pose(), x2, y2, 0).setColor(1, 0, 0, rgb).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
        }
    }
    public void renderCircle2(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, float x, float y,Entity entity) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.lines());
        int segments = 64; // 圆的段数
        float radius = 0.25F; // 圆的半径
        poseStack.translate(0 ,-1 ,0);
        poseStack.mulPose(Axis.XP.rotationDegrees((float) (-entity.tickCount * 1.3)));
        poseStack.mulPose(Axis.YP.rotationDegrees((float) (-entity.tickCount * 2.289)));

        for (int i = 0; i < segments; i++) {
            float angle1 = (float) (2 * Math.PI * i / segments);
            float angle2 = (float) (2 * Math.PI * (i + 1) / segments);

            float x1 = (float) (x + radius * Math.cos(angle1));
            float y1 = (float) (y + radius * Math.sin(angle1));
            float x2 = (float) (x + radius * Math.cos(angle2));
            float y2 = (float) (y + radius * Math.sin(angle2));

            vertexConsumer.addVertex(poseStack.last().pose(), x1, y1, 0).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
            vertexConsumer.addVertex(poseStack.last().pose(), x2, y2, 0).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
        }
    }
    public void renderCircle3(@NotNull PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, float x, float y, float z, float radius , Entity entity) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(MRender.ging());
        int verticalSegments = 16; // 垂直段数
        int horizontalSegments = 16; // 水平段数

        poseStack.translate(0 ,-1 ,0);
        for (int i = 0; i < verticalSegments; i++) {
            float vAngle1 = (float) (Math.PI * i / verticalSegments);
            float vAngle2 = (float) (Math.PI * (i + 1) / verticalSegments);

            for (int j = 0; j < horizontalSegments; j++) {
                float hAngle1 = (float) (2 * Math.PI * j / horizontalSegments);
                float hAngle2 = (float) (2 * Math.PI * (j + 1) / horizontalSegments);

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
                vertexConsumer.addVertex(poseStack.last().pose(), x1, y1, z1).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x2, y2, z2).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x3, y3, z3).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);

                vertexConsumer.addVertex(poseStack.last().pose(), x2, y2, z2).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x3, y3, z3).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x4, y4, z4).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
            }
        }
    }
    public void renderCircle6(@NotNull PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, float x, float y, float z, float radius , Entity entity) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(MRender.ging());
        int verticalSegments = 4; // 垂直段数
        int horizontalSegments = 4; // 水平段数

        poseStack.translate(0 ,-1 ,0);
        for (int i = 0; i < verticalSegments; i++) {
            float vAngle1 = (float) (Math.PI * i / verticalSegments);
            float vAngle2 = (float) (Math.PI * (i + 1) / verticalSegments);

            for (int j = 0; j < horizontalSegments; j++) {
                float hAngle1 = (float) (2 * Math.PI * j / horizontalSegments);
                float hAngle2 = (float) (2 * Math.PI * (j + 1) / horizontalSegments);

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
                vertexConsumer.addVertex(poseStack.last().pose(), x1, y1, z1).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x2, y2, z2).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x3, y3, z3).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);

                vertexConsumer.addVertex(poseStack.last().pose(), x2, y2, z2).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x3, y3, z3).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x4, y4, z4).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
            }
        }
    }
    public void renderCircle5(@NotNull PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, float x, float y, float z, float radius , Entity entity) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(MRender.ging());
        int verticalSegments = 8; // 垂直段数
        int horizontalSegments = 8; // 水平段数

        poseStack.translate(0 ,-1 ,0);
        for (int i = 0; i < verticalSegments; i++) {
            float vAngle1 = (float) (Math.PI * i / verticalSegments);
            float vAngle2 = (float) (Math.PI * (i + 1) / verticalSegments);

            for (int j = 0; j < horizontalSegments; j++) {
                float hAngle1 = (float) (2 * Math.PI * j / horizontalSegments);
                float hAngle2 = (float) (2 * Math.PI * (j + 1) / horizontalSegments);

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
                vertexConsumer.addVertex(poseStack.last().pose(), x1, y1, z1).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x2, y2, z2).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x3, y3, z3).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);

                vertexConsumer.addVertex(poseStack.last().pose(), x2, y2, z2).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x3, y3, z3).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x4, y4, z4).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
            }
        }
    }
    public void renderCircle4(@NotNull PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, float x, float y, float z, float radius , Entity entity) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(MRender.ging());
        int verticalSegments = 22; // 垂直段数
        int horizontalSegments = 22; // 水平段数

        poseStack.translate(0 ,-1 ,0);
        for (int i = 0; i < verticalSegments; i++) {
            float vAngle1 = (float) (Math.PI * i / verticalSegments);
            float vAngle2 = (float) (Math.PI * (i + 1) / verticalSegments);

            for (int j = 0; j < horizontalSegments; j++) {
                float hAngle1 = (float) (2 * Math.PI * j / horizontalSegments);
                float hAngle2 = (float) (2 * Math.PI * (j + 1) / horizontalSegments);

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
                vertexConsumer.addVertex(poseStack.last().pose(), x1, y1, z1).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x2, y2, z2).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x3, y3, z3).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);

                vertexConsumer.addVertex(poseStack.last().pose(), x2, y2, z2).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x3, y3, z3).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x4, y4, z4).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
            }
        }
    }
    public void renderCircle7(@NotNull PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, float x, float y, float z, float radius , Entity entity) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(MRender.ging());
        int verticalSegments = 24; // 垂直段数
        int horizontalSegments = 24; // 水平段数

        poseStack.translate(0 ,-1 ,0);
        for (int i = 0; i < verticalSegments; i++) {
            float vAngle1 = (float) (Math.PI * i / verticalSegments);
            float vAngle2 = (float) (Math.PI * (i + 1) / verticalSegments);

            for (int j = 0; j < horizontalSegments; j++) {
                float hAngle1 = (float) (2 * Math.PI * j / horizontalSegments);
                float hAngle2 = (float) (2 * Math.PI * (j + 1) / horizontalSegments);

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
                vertexConsumer.addVertex(poseStack.last().pose(), x1, y1, z1).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x2, y2, z2).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x3, y3, z3).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);

                vertexConsumer.addVertex(poseStack.last().pose(), x2, y2, z2).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x3, y3, z3).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x4, y4, z4).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
            }
        }
    }
    public void renderCircle8(@NotNull PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, float x, float y, float z, float radius , Entity entity) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(MRender.ging());
        int verticalSegments = 2; // 垂直段数
        int horizontalSegments = 2; // 水平段数

        poseStack.translate(0 ,-1 ,0);
        for (int i = 0; i < verticalSegments; i++) {
            float vAngle1 = (float) (Math.PI * i / verticalSegments);
            float vAngle2 = (float) (Math.PI * (i + 1) / verticalSegments);

            for (int j = 0; j < horizontalSegments; j++) {
                float hAngle1 = (float) (2 * Math.PI * j / horizontalSegments);
                float hAngle2 = (float) (2 * Math.PI * (j + 1) / horizontalSegments);

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
                vertexConsumer.addVertex(poseStack.last().pose(), x1, y1, z1).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x2, y2, z2).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x3, y3, z3).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);

                vertexConsumer.addVertex(poseStack.last().pose(), x2, y2, z2).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x3, y3, z3).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x4, y4, z4).setColor(1, 0.3F, 0.5F, 1.0f).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
            }
        }
    }
}
