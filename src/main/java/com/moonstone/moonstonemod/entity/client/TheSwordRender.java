package com.moonstone.moonstonemod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.moonstone.moonstonemod.ConfigClient;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.client.renderer.MRender;
import com.moonstone.moonstonemod.client.renderer.MoonPost;
import com.moonstone.moonstonemod.entity.the_sword;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;

import java.util.List;

import static net.minecraft.client.renderer.texture.OverlayTexture.NO_OVERLAY;

public class TheSwordRender extends EntityRenderer<the_sword> {
    public TheSwordRender(EntityRendererProvider.Context p_173917_) {
        super(p_173917_);
    }


    @Override
    public boolean shouldRender(the_sword livingEntity, Frustum camera, double camX, double camY, double camZ) {
        return true;
    }
    private static void addSquare(VertexConsumer vertexConsumer, PoseStack poseStack, Vec3 up1, Vec3 up2, Vec3 down1, Vec3 down2, float alpha) {
        // 添加四个顶点来绘制一个矩形
        vertexConsumer.addVertex(poseStack.last().pose(), (float) up1.x, (float) up1.y, (float) up1.z)
                .setColor(	135,206,250, (int) (alpha * 255))
                .setUv2(240, 240)
                .setUv(0,0)
                .setNormal(0, 0, 1);

        vertexConsumer.addVertex(poseStack.last().pose(), (float) down1.x, (float) down1.y, (float) down1.z)
                .setColor(	0,0,0, (int) (alpha * 255))
                .setUv2(240, 240)
                .setUv(0,0)
                .setNormal(0, 0, 1);

        vertexConsumer.addVertex(poseStack.last().pose(), (float) down2.x, (float) down2.y, (float) down2.z)
                .setColor(	0,0,0, (int) (alpha * 255))
                .setUv2(240, 240)
                .setUv(0,0)
                .setNormal(0, 0, 1);

        vertexConsumer.addVertex(poseStack.last().pose(), (float) up2.x, (float) up2.y, (float) up2.z)
                .setColor(	135,206,250, (int) (alpha * 255))
                .setUv2(240, 240)
                .setUv(0,0)
                .setNormal(0, 0, 1);
    }
    public static void renderLine(PoseStack poseStack, MultiBufferSource bufferSource, Vec3 start, Vec3 end, float a, RenderType renderType, float radius) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(renderType);

        int segmentCount = 16; // 圆柱横向细分数

        for (int i = 0; i < segmentCount; i++) {
            double angle1 = (2 * Math.PI * i) / segmentCount;
            double angle2 = (2 * Math.PI * (i + 1)) / segmentCount;

            double x1 = Math.cos(angle1) * radius;
            double z1 = Math.sin(angle1) * radius;
            double x2 = Math.cos(angle2) * radius;
            double z2 = Math.sin(angle2) * radius;

            Vec3 up1 = start.add(x1, 0, z1);
            Vec3 up2 = start.add(x2, 0, z2);
            Vec3 down1 = end.add(x1, 0, z1);
            Vec3 down2 = end.add(x2, 0, z2);


            addSquare(vertexConsumer, poseStack, up1, up2, down1, down2, a);
        }
    }
    @Override
    public void render(the_sword entity, float p_114486_, float p_114487_, PoseStack poseStack, MultiBufferSource bufferSource, int p_114490_) {
        super.render(entity, p_114486_, p_114487_, poseStack, bufferSource, p_114490_);
        if (ConfigClient.Client.Shader.get()) {
            MoonPost.renderEffectForNextTick(MoonStoneMod.POST);
        }
        if (entity.getTarget()!=null) {
            float sTime = entity.getEntityData().get(the_sword.ATTACK_TIME)/ 300f;
            Vec3 entityPos = entity.position();
            Vec3 nearbyEntityPos = entity.getTarget().position();

            Vec3 end = nearbyEntityPos.subtract(entityPos);

            renderLine(poseStack, bufferSource, new Vec3(0, 0, 0),
                    end.add(0, 1, 0), sTime , MRender.beacon.apply(ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID, "textures/blue.png"), true),
                    0.1f+sTime/ 10);

            renderLine(poseStack, bufferSource, new Vec3(0, 0, 0),
                    end.add(0, 1, 0), sTime, MRender.LIGHTNING,
                    0.1f+sTime  / 10);
        }
        double x = Mth.lerp(p_114487_, entity.xOld, entity.getX());
        double y = Mth.lerp(p_114487_, entity.yOld, entity.getY());
        double z = Mth.lerp(p_114487_, entity.zOld, entity.getZ());

        int integer = entity.getEntityData().get(the_sword.ARMOR); // 1~12
        {
            poseStack.pushPose();
            poseStack.mulPose(Axis.XN.rotationDegrees(entity.tickCount * 2));
            poseStack.mulPose(Axis.YN.rotationDegrees(entity.tickCount * 2));
            poseStack.mulPose(Axis.ZN.rotationDegrees(entity.tickCount * 2));
            poseStack.translate(-0, 0.25, -0);
            ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
            Item skeletonSkull = Items.SKELETON_SKULL;
            ItemStack skeletonSkullDefaultInstance = skeletonSkull.getDefaultInstance();
            BakedModel model = itemRenderer.getModel(skeletonSkullDefaultInstance, Minecraft.getInstance().level, null, 0);
            itemRenderer.render(skeletonSkullDefaultInstance, ItemDisplayContext.NONE, false, poseStack, bufferSource, Minecraft.getInstance().getEntityRenderDispatcher().getPackedLightCoords(entity, 0.0F), OverlayTexture.NO_OVERLAY, model);
            poseStack.popPose();

            poseStack.pushPose();
            poseStack.mulPose(Axis.XN.rotationDegrees(entity.tickCount * 3.3f));
            poseStack.mulPose(Axis.YN.rotationDegrees(entity.tickCount * 3.3f));
            poseStack.mulPose(Axis.ZN.rotationDegrees(entity.tickCount * 3.3f));

            poseStack.translate(-0.5, -0.5, -0.5);
            renderCubeFace(poseStack, bufferSource, p_114490_, MRender.LIGHTNING, integer - 1,integer/24f);
            renderCubeFace(poseStack, bufferSource, p_114490_, MRender.beacon.apply(ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,
                    "textures/blue.png"),true), integer - 1,integer/96f);
            poseStack.popPose();

            poseStack.pushPose();
            poseStack.scale(2,2,2);
            poseStack.mulPose(Axis.XN.rotationDegrees(-entity.tickCount * 1.3f));
            poseStack.mulPose(Axis.YN.rotationDegrees(-entity.tickCount * 1.3f));
            poseStack.mulPose(Axis.ZN.rotationDegrees(-entity.tickCount * 1.3f));
            poseStack.translate(-0.5, -0.5, -0.5);
            renderCubeFace2(poseStack, bufferSource, p_114490_, MRender.LIGHTNING, integer - 1,integer/36f);
            renderCubeFace2(poseStack, bufferSource, p_114490_, MRender.beacon.apply(ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,
                    "textures/blue.png"),true), integer - 1,integer/128f);
            poseStack.popPose();


        }

        if (entity.hasTrail()) {
            poseStack.pushPose();
            poseStack.translate(-x, -y, -z);
            if (ConfigClient.Client.light.get()) {
                setMatrices(poseStack, bufferSource, entity,integer);
            }
            renderTrail(entity, p_114487_, poseStack, bufferSource, 152/255f, 245/255f, 255/255f, 240);
            poseStack.popPose();
        }
    }

    public void setMatrices(@NotNull PoseStack matrices, @NotNull MultiBufferSource vertexConsumers, @NotNull Entity ownerBlood,int rage) {
        float posAdd = 1.001F;

        BlockPos playerPos = ownerBlood.blockPosition();
        Vec3 playerVec = new Vec3(playerPos.getX(), playerPos.getY(), playerPos.getZ());

        for (int x = -rage; x <= rage; x++) {
            for (int y = -rage; y <= rage; y++) {
                for (int z = -rage; z <= rage; z++) {
                    BlockPos currentPos = playerPos.offset(x, y, z);
                    Vec3 currentVec = new Vec3(currentPos.getX(), currentPos.getY(), currentPos.getZ());
                    BlockState blockState = ownerBlood.level().getBlockState(currentPos);
                    if (!blockState.isEmpty() && blockState.canOcclude()) {
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
                                    vertexConsumers.getBuffer(MRender.LIGHTNING).putBulkData(matrices.last(), quad, new float[]{
                                            1.32f, 1.32f, 1.32f, 1.32f
                                    }, 0, 0, 1, alp, new int[]{240, 240, 240, 240}, OverlayTexture.NO_OVERLAY, true);
                                }
                            }
                        }

                        matrices.popPose();
                    }
                }
            }
        }
    }
    private void renderTrail(the_sword entityIn, float partialTicks, PoseStack poseStack, MultiBufferSource bufferIn, float trailR, float trailG, float trailB, int packedLightIn) {
        int samples = 0;
        int sampleSize = the_sword.max;
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


    public void renderCubeFace(@NotNull PoseStack matrices, @NotNull MultiBufferSource vertexConsumers, int light, RenderType renderType, int face,float a  ) {
        PoseStack.Pose pose = matrices.last();
        Matrix4f matrix4f = pose.pose();
        float x = 0.5F; // 正方形中心的x坐标
        float y = 0.5F; // 正方形中心的y坐标
        float z = 0.5F; // 正方形中心的z坐标
        float size = 0.5F; // 正方形的边长的一半

        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(renderType);

        // 根据不同的面来绘制正方形
        if (face > 0) { // 前面
            vertexConsumer.addVertex(matrix4f, x - size, y - size, z - size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
            vertexConsumer.addVertex(matrix4f, x + size, y - size, z - size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
            vertexConsumer.addVertex(matrix4f, x + size, y + size, z - size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
            vertexConsumer.addVertex(matrix4f, x - size, y + size, z - size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
        }
        if (face > 1) { // 后面
            vertexConsumer.addVertex(matrix4f, x - size, y + size, z + size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
            vertexConsumer.addVertex(matrix4f, x + size, y + size, z + size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
            vertexConsumer.addVertex(matrix4f, x + size, y - size, z + size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
            vertexConsumer.addVertex(matrix4f, x - size, y - size, z + size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
        }
        if (face > 2) { // 左面
            vertexConsumer.addVertex(matrix4f, x - size, y - size, z - size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
            vertexConsumer.addVertex(matrix4f, x - size, y - size, z + size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
            vertexConsumer.addVertex(matrix4f, x - size, y + size, z + size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
            vertexConsumer.addVertex(matrix4f, x - size, y + size, z - size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
        }
        if (face > 3) { // 右面
            vertexConsumer.addVertex(matrix4f, x + size, y - size, z - size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
            vertexConsumer.addVertex(matrix4f, x + size, y - size, z + size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
            vertexConsumer.addVertex(matrix4f, x + size, y + size, z + size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
            vertexConsumer.addVertex(matrix4f, x + size, y + size, z - size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
        }
        if (face > 4) { // 上面
            vertexConsumer.addVertex(matrix4f, x - size, y + size, z - size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
            vertexConsumer.addVertex(matrix4f, x + size, y + size, z - size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
            vertexConsumer.addVertex(matrix4f, x + size, y + size, z + size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
            vertexConsumer.addVertex(matrix4f, x - size, y + size, z + size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
        }
        if (face > 5) { // 下面
            vertexConsumer.addVertex(matrix4f, x - size, y - size, z + size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
            vertexConsumer.addVertex(matrix4f, x + size, y - size, z + size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
            vertexConsumer.addVertex(matrix4f, x + size, y - size, z - size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
            vertexConsumer.addVertex(matrix4f, x - size, y - size, z - size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
        }
    }

    public void renderCubeFace2(@NotNull PoseStack matrices, @NotNull MultiBufferSource vertexConsumers, int light, RenderType renderType, int face,float a  ) {
        PoseStack.Pose pose = matrices.last();
        Matrix4f matrix4f = pose.pose();
        float x = 0.5F; // 正方形中心的x坐标
        float y = 0.5F; // 正方形中心的y坐标
        float z = 0.5F; // 正方形中心的z坐标
        float size = 0.5F; // 正方形的边长的一半

        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(renderType);

        // 根据不同的面来绘制正方形
        if (face > 6) { // 前面
            vertexConsumer.addVertex(matrix4f, x - size, y - size, z - size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
            vertexConsumer.addVertex(matrix4f, x + size, y - size, z - size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
            vertexConsumer.addVertex(matrix4f, x + size, y + size, z - size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
            vertexConsumer.addVertex(matrix4f, x - size, y + size, z - size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
        }
        if (face > 7) { // 后面
            vertexConsumer.addVertex(matrix4f, x - size, y + size, z + size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
            vertexConsumer.addVertex(matrix4f, x + size, y + size, z + size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
            vertexConsumer.addVertex(matrix4f, x + size, y - size, z + size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
            vertexConsumer.addVertex(matrix4f, x - size, y - size, z + size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
        }
        if (face > 8) { // 左面
            vertexConsumer.addVertex(matrix4f, x - size, y - size, z - size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
            vertexConsumer.addVertex(matrix4f, x - size, y - size, z + size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
            vertexConsumer.addVertex(matrix4f, x - size, y + size, z + size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
            vertexConsumer.addVertex(matrix4f, x - size, y + size, z - size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
        }
        if (face > 9) { // 右面
            vertexConsumer.addVertex(matrix4f, x + size, y - size, z - size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
            vertexConsumer.addVertex(matrix4f, x + size, y - size, z + size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
            vertexConsumer.addVertex(matrix4f, x + size, y + size, z + size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
            vertexConsumer.addVertex(matrix4f, x + size, y + size, z - size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
        }
        if (face > 10) { // 上面
            vertexConsumer.addVertex(matrix4f, x - size, y + size, z - size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
            vertexConsumer.addVertex(matrix4f, x + size, y + size, z - size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
            vertexConsumer.addVertex(matrix4f, x + size, y + size, z + size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
            vertexConsumer.addVertex(matrix4f, x - size, y + size, z + size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
        }
        if (face > 11) { // 下面
            vertexConsumer.addVertex(matrix4f, x - size, y - size, z + size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
            vertexConsumer.addVertex(matrix4f, x + size, y - size, z + size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
            vertexConsumer.addVertex(matrix4f, x + size, y - size, z - size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
            vertexConsumer.addVertex(matrix4f, x - size, y - size, z - size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
        }
    }





    @Override
    public ResourceLocation getTextureLocation(the_sword p_114482_) {
        return ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,"textures/entity/flysword.png");
    }
}


