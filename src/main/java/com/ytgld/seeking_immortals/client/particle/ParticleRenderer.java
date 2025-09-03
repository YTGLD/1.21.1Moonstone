package com.ytgld.seeking_immortals.client.particle;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.ytgld.seeking_immortals.SeekingImmortalsMod;
import com.ytgld.seeking_immortals.renderer.MRender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import org.jetbrains.annotations.NotNull;

@EventBusSubscriber(value = Dist.CLIENT, bus = EventBusSubscriber.Bus.GAME, modid = SeekingImmortalsMod.MODID)
public final class ParticleRenderer {
    @SubscribeEvent
    public static void RenderLevelStageEvent(RenderLevelStageEvent event) {
        if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_PARTICLES) {
            var camPos = event.getCamera().getPosition();

            PoseStack poseStack = event.getPoseStack();
            poseStack.pushPose();



            RenderType renderType = MRender.light;
            VertexConsumer consumer = Minecraft.getInstance().renderBuffers().bufferSource().getBuffer(renderType);


            Minecraft.getInstance().particleEngine.iterateParticles(particle -> {
                if (particle instanceof blood blood) {

                    float live = blood.getLifetime();
                    poseStack.pushPose();
                    var offset = particle.getPos().subtract(camPos);
                    poseStack.translate(offset.x, offset.y, offset.z);

                    renderSphere1(poseStack,consumer,240,live,1);

                    poseStack.popPose();
                }
            });
            Minecraft.getInstance().renderBuffers().bufferSource().endBatch(renderType);

            poseStack.popPose();
        }

    }
    public static void renderSphere1(@NotNull PoseStack matrices, @NotNull VertexConsumer vertexConsumer, int light, float s,float a) {
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

                vertexConsumer.addVertex(matrices.last().pose(), x0, y0, z0).setColor(1.0f, 0.25f, 0.75f, a).setOverlay(OverlayTexture.NO_OVERLAY).setUv(0, 0).setUv2(light, light).setNormal(1, 0, 0);
                vertexConsumer.addVertex(matrices.last().pose(), x1, y1, z1).setColor(1.0f, 0.25f, 0.75f, a).setOverlay(OverlayTexture.NO_OVERLAY).setUv(0, 0).setUv2(light, light).setNormal(1, 0, 0);
                vertexConsumer.addVertex(matrices.last().pose(), x2, y2, z2).setColor(1.0f, 0.25f, 0.75f, a).setOverlay(OverlayTexture.NO_OVERLAY).setUv(0, 0).setUv2(light, light).setNormal(1, 0, 0);
                vertexConsumer.addVertex(matrices.last().pose(), x3, y3, z3).setColor(1.0f, 0.25f, 0.75f, a).setOverlay(OverlayTexture.NO_OVERLAY).setUv(0, 0).setUv2(light, light).setNormal(1, 0, 0);
            }
        }
    }
    private ParticleRenderer() {

    }
}