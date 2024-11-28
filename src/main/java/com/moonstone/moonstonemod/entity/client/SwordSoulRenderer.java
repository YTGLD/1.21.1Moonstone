package com.moonstone.moonstonemod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.client.renderer.MRender;
import com.moonstone.moonstonemod.entity.zombie.sword_soul;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class SwordSoulRenderer  extends EntityRenderer<sword_soul> {
    public SwordSoulRenderer(EntityRendererProvider.Context p_173917_) {
        super(p_173917_);
    }

    @Override
    public void render(sword_soul entity, float p_114486_, float p_114487_, PoseStack poseStack, MultiBufferSource bufferSource, int p_114490_) {
        MedicineboxUi(poseStack,bufferSource,240,entity);
        setT(poseStack, entity,bufferSource);
        for (int i = 0; i < 1; i++) {
            poseStack.pushPose();
            poseStack.scale(1.75f, 1.75f, 1.75f);
            float ss = i / 33f;
            float as = (float) Math.sin(entity.tickCount / 60f);
            as = Math.abs(as); // 确保是非负值
            as = 0.5f + (as * 0.5f); // 将范围调整到 0.5 到 1 之间


            poseStack.scale(as,as,as);
            renderSphere1(entity,poseStack, bufferSource, 240, ss, 0 + (i * 5f), 255f-(i*5f), 245 / 255f, 0.25F, 6);

            poseStack.popPose();
        }

        super.render(entity, p_114486_, p_114487_, poseStack, bufferSource, p_114490_);
    }
    private void setT(PoseStack matrices,
                      sword_soul entity,
                      MultiBufferSource vertexConsumers)
    {
        matrices.pushPose();

        for (int i = 1; i < entity.getTrailPositions().size(); i++){
            Vec3 prevPos = entity.getTrailPositions().get(i - 1);
            Vec3 currPos = entity.getTrailPositions().get(i);
            Vec3 adjustedPrevPos = new Vec3(prevPos.x - entity.getX(), prevPos.y - entity.getY(), prevPos.z - entity.getZ());
            Vec3 adjustedCurrPos = new Vec3(currPos.x - entity.getX(), currPos.y - entity.getY(), currPos.z - entity.getZ());

            float alpha = (float)(i) / (float)(entity.getTrailPositions().size());

            Handler.renderBlood(matrices, vertexConsumers, adjustedPrevPos, adjustedCurrPos, alpha, MRender.lightning(),0.5f);
        }
        matrices.popPose();
    }
    public void MedicineboxUi(@NotNull PoseStack matrices,
                         @NotNull MultiBufferSource vertexConsumers,
                         int light,
                         @NotNull Entity entity) {

        matrices.pushPose();

        matrices.mulPose(Axis.XP.rotationDegrees(entity.tickCount));
        matrices.mulPose(Axis.YP.rotationDegrees(entity.tickCount));
        matrices.mulPose(Axis.ZP.rotationDegrees(entity.tickCount));

        matrices.scale(2, 2, 2);

        Minecraft.getInstance().getItemRenderer().renderStatic(
                Blocks.ZOMBIE_HEAD.asItem().getDefaultInstance(),
                ItemDisplayContext.GROUND,
                light,
                OverlayTexture.NO_OVERLAY,
                matrices,
                vertexConsumers,
                entity.level(),
                0);

        matrices.popPose();

    }
    public void renderSphere1(Entity entity,@NotNull PoseStack matrices, @NotNull MultiBufferSource vertexConsumers, int light, float s,float r,float g,float b,float a,int size) {
        int stacks = size + 6; // 垂直方向的分割数
        int slices = size + 6; // 水平方向的分割数
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

                vertexConsumer.addVertex(matrices.last().pose(), x0, y0, z0).setColor(r, g, b, a).setOverlay(OverlayTexture.NO_OVERLAY).setUv(0, 0).setUv2(light, light).setNormal(1, 0, 0);
                vertexConsumer.addVertex(matrices.last().pose(), x1, y1, z1).setColor(r, g, b, a).setOverlay(OverlayTexture.NO_OVERLAY).setUv(0, 0).setUv2(light, light).setNormal(1, 0, 0);
                vertexConsumer.addVertex(matrices.last().pose(), x2, y2, z2).setColor(r, g, b, a).setOverlay(OverlayTexture.NO_OVERLAY).setUv(0, 0).setUv2(light, light).setNormal(1, 0, 0);
                vertexConsumer.addVertex(matrices.last().pose(), x3, y3, z3).setColor(r, g, b, a).setOverlay(OverlayTexture.NO_OVERLAY).setUv(0, 0).setUv2(light, light).setNormal(1, 0, 0);
            }
        }
    }
    @Override
    public ResourceLocation getTextureLocation(sword_soul p_114482_) {
        return ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,"textures/entity/flysword.png");
    }
}




