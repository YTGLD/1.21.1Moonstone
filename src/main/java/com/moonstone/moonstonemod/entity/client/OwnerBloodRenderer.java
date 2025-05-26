package com.moonstone.moonstonemod.entity.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
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
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.model.IQuadTransformer;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.List;

import static net.minecraft.client.renderer.texture.OverlayTexture.NO_OVERLAY;
import static org.lwjgl.opengl.GL11C.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11C.GL_SRC_ALPHA;

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
        if (ConfigClient.Client.Shader.get()) {
            MoonPost.renderEffectForNextTick(MoonStoneMod.POST_Blood);
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
        int rage = 12;
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
                                    putBulkData(vertexConsumers,matrices.last(), quad, new float[]{
                                            1, 1, 1, 1
                                    }, 1, 0, 0, alp, new int[]{240, 240, 240, 240}, OverlayTexture.NO_OVERLAY, true);
                                }
                            }
                        }

                        matrices.popPose();
                    }
                }
            }
        }
    }

    private void putBulkData(MultiBufferSource vertexConsumers,PoseStack.Pose pose, BakedQuad quad, float[] brightness, float red, float green, float blue, float alpha, int[] lightmap, int packedOverlay, boolean readAlpha) {
        int[] aint = quad.getVertices();
        Vec3i vec3i = quad.getDirection().getNormal();
        Matrix4f matrix4f = pose.pose();
        Vector3f vector3f = pose.transformNormal((float)vec3i.getX(), (float)vec3i.getY(), (float)vec3i.getZ(), new Vector3f());
        int j = aint.length / 8;
        int k = (int)(alpha * 255.0F);
        MemoryStack memorystack = MemoryStack.stackPush();

        try {
            ByteBuffer bytebuffer = memorystack.malloc(DefaultVertexFormat.BLOCK.getVertexSize());
            IntBuffer intbuffer = bytebuffer.asIntBuffer();

            for(int l = 0; l < j; ++l) {
                intbuffer.clear();
                intbuffer.put(aint, l * 8, 8);
                float f = bytebuffer.getFloat(0);
                float f1 = bytebuffer.getFloat(4);
                float f2 = bytebuffer.getFloat(8);
                float f3;
                float f4;
                float f5;
                if (readAlpha) {
                    float f6 = (float)(bytebuffer.get(12) & 255);
                    float f7 = (float)(bytebuffer.get(13) & 255);
                    float f8 = (float)(bytebuffer.get(14) & 255);
                    f3 = f6 * brightness[l] * red;
                    f4 = f7 * brightness[l] * green;
                    f5 = f8 * brightness[l] * blue;
                } else {
                    f3 = brightness[l] * red * 255.0F;
                    f4 = brightness[l] * green * 255.0F;
                    f5 = brightness[l] * blue * 255.0F;
                }

                int vertexAlpha = readAlpha ? (int)(alpha * (float)(bytebuffer.get(15) & 255) / 255.0F * 255.0F) : k;
                int i1 = FastColor.ARGB32.color(vertexAlpha, (int)f3, (int)f4, (int)f5);
                int j1 = this.applyBakedLighting(lightmap[l], bytebuffer);
                float f10 = bytebuffer.getFloat(16);
                float f9 = bytebuffer.getFloat(20);
                Vector3f vector3f1 = matrix4f.transformPosition(f, f1, f2, new Vector3f());
                this.applyBakedNormals(vector3f, bytebuffer, pose.normal());
                vertexConsumers.getBuffer(MRender.LIGHTNING).addVertex(vector3f1.x(), vector3f1.y(), vector3f1.z(), i1, f10, f9, packedOverlay, j1, vector3f.x(), vector3f.y(), vector3f.z());
            }
        } catch (Throwable var35) {
            try {
                memorystack.close();
            } catch (Throwable var34) {
                var35.addSuppressed(var34);
            }

            throw var35;
        }

        memorystack.close();

    }
    private void applyBakedNormals(Vector3f generated, ByteBuffer data, Matrix3f normalTransform) {
        byte nx = data.get(28);
        byte ny = data.get(29);
        byte nz = data.get(30);
        if (nx != 0 || ny != 0 || nz != 0) {
            generated.set((float)nx / 127.0F, (float)ny / 127.0F, (float)nz / 127.0F);
            generated.mul(normalTransform);
        }

    }
    private int applyBakedLighting(int packedLight, ByteBuffer data) {
        int bl = packedLight & '\uffff';
        int sl = packedLight >> 16 & '\uffff';
        int offset = IQuadTransformer.UV2 * 4;
        int blBaked = Short.toUnsignedInt(data.getShort(offset));
        int slBaked = Short.toUnsignedInt(data.getShort(offset + 2));
        bl = Math.max(bl, blBaked);
        sl = Math.max(sl, slBaked);
        return bl | sl << 16;
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




