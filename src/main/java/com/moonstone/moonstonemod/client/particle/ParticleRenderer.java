package com.moonstone.moonstonemod.client.particle;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.client.renderer.MRender;
import com.moonstone.moonstonemod.client.renderer.MoonPost;
import com.moonstone.moonstonemod.entity.attack_blood;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import net.neoforged.neoforge.internal.versions.neoforge.NeoForgeVersion;

import static com.moonstone.moonstonemod.MoonStoneMod.Client.renderPoseStack;

@EventBusSubscriber(value = Dist.CLIENT, bus = EventBusSubscriber.Bus.GAME, modid = MoonStoneMod.MODID)
public final class ParticleRenderer {
    @SubscribeEvent
    public static void onRenderLevelStage(RenderLevelStageEvent event) {
        if (event.getStage() != RenderLevelStageEvent.Stage.AFTER_PARTICLES) {
            return;
        }
        var camPos = event.getCamera().getPosition();

        PoseStack poseStack = event.getPoseStack();
        poseStack.pushPose();



        RenderType renderType = MRender.LIGHTNING;
        VertexConsumer consumer = Minecraft.getInstance().renderBuffers().bufferSource().getBuffer(renderType);


        Minecraft.getInstance().particleEngine.iterateParticles(particle -> {
            if (particle instanceof blood blood) {

                poseStack.pushPose();
                var offset = particle.getPos().subtract(camPos);
                poseStack.translate(offset.x, offset.y, offset.z);
                setT(poseStack,blood,consumer);
               poseStack.popPose();
            }
            if (particle instanceof blue blue) {
                poseStack.pushPose();
                var offset = particle.getPos().subtract(camPos);
                poseStack.translate(offset.x, offset.y, offset.z);
                blue.setBlue(poseStack,blue,consumer);
                poseStack.popPose();
            }
        });
        Minecraft.getInstance().renderBuffers().bufferSource().endBatch(renderType);

        poseStack.popPose();
    }
    @SubscribeEvent
    public static void line(RenderLevelStageEvent event) {
        if (event.getStage() != RenderLevelStageEvent.Stage.AFTER_PARTICLES) {
            return;
        }
        var camPos = event.getCamera().getPosition();

        PoseStack poseStack = event.getPoseStack();
        poseStack.pushPose();


        RenderType renderType = MRender.lines();
        VertexConsumer consumer = Minecraft.getInstance().renderBuffers().bufferSource().getBuffer(renderType);


        Minecraft.getInstance().particleEngine.iterateParticles(particle -> {
            if (particle instanceof popr black) {
                poseStack.pushPose();
                var offset = particle.getPos().subtract(camPos);
                poseStack.translate(offset.x, offset.y, offset.z);
                black.renderBlack(poseStack, consumer, 240, 0, 0, 0, black);
                poseStack.popPose();
            }
        });
        Minecraft.getInstance().renderBuffers().bufferSource().endBatch(renderType);

        poseStack.popPose();
    }
    private  static void setT(PoseStack matrices,
                      blood entity,
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

    public static void renderBlood(PoseStack poseStack, VertexConsumer vertexConsumer, Vec3 start, Vec3 end, float a, RenderType renderType, float r) {
        int segmentCount = 8; // 圆柱横向细分数

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
    private static void addSquare(VertexConsumer vertexConsumer, PoseStack poseStack, Vec3 up1, Vec3 up2, Vec3 down1, Vec3 down2, float alpha) {
        // 添加四个顶点来绘制一个矩形
        vertexConsumer.addVertex(poseStack.last().pose(), (float) up1.x, (float) up1.y, (float) up1.z)
                .setColor(255, 0, 0, (int) (alpha * 255))
                .setUv2(240, 240)
                .setNormal(0, 0, 1);

        vertexConsumer.addVertex(poseStack.last().pose(), (float) down1.x, (float) down1.y, (float) down1.z)
                .setColor(255, 0, 0, (int) (alpha * 255))
                .setUv2(240, 240)
                .setNormal(0, 0, 1);

        vertexConsumer.addVertex(poseStack.last().pose(), (float) down2.x, (float) down2.y, (float) down2.z)
                .setColor(255, 0, 0, (int) (alpha * 255))
                .setUv2(240, 240)
                .setNormal(0, 0, 1);

        vertexConsumer.addVertex(poseStack.last().pose(), (float) up2.x, (float) up2.y, (float) up2.z)
                .setColor(255, 0, 0, (int) (alpha * 255))
                .setUv2(240, 240)
                .setNormal(0, 0, 1);
    }
    public ParticleRenderer() {

    }
}