package com.moonstone.moonstonemod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.moonstone.moonstonemod.entity.bolt;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import org.joml.Matrix4f;

public class BoltRenderer extends EntityRenderer<bolt> {
    public BoltRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    public void render(bolt entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        float[] afloat = new float[8];
        float[] afloat1 = new float[8];
        float f = 0.0F;
        float f1 = 0.0F;
        RandomSource randomsource = RandomSource.create(entity.seed);

        for(int i = 7; i >= 0; --i) {
            afloat[i] = f;
            afloat1[i] = f1;
            f += (float)(randomsource.nextInt(11) - 5);
            f1 += (float)(randomsource.nextInt(11) - 5);
        }

        VertexConsumer vertexconsumer = buffer.getBuffer(RenderType.lightning());
        Matrix4f matrix4f = poseStack.last().pose();
        poseStack.scale(0.025f,0.025f,0.025f);
        poseStack.mulPose(Axis.XP.rotationDegrees(entity.seed));
        poseStack.mulPose(Axis.YP.rotationDegrees(entity.seed));
        poseStack.mulPose(Axis.ZP.rotationDegrees(entity.seed));




        for(int j = 0; j < 4; ++j) {
            RandomSource randomsource1 = RandomSource.create(entity.seed);

            for(int k = 0; k < 3; ++k) {
                int l = 7;
                int i1 = 0;
                if (k > 0) {
                    l = 7 - k;
                }

                if (k > 0) {
                    i1 = l - 2;
                }

                float f2 = afloat[l] - f;
                float f3 = afloat1[l] - f1;

                for(int j1 = l; j1 >= i1; --j1) {
                    float f4 = f2;
                    float f5 = f3;
                    if (k == 0) {
                        f2 += (float)(randomsource1.nextInt(11) - 5);
                        f3 += (float)(randomsource1.nextInt(11) - 5);
                    } else {
                        f2 += (float)(randomsource1.nextInt(31) - 15);
                        f3 += (float)(randomsource1.nextInt(31) - 15);
                    }
                    float f10 = 0.1F + (float)j * 0.2F;
                    if (k == 0) {
                        f10 *= (float)j1 * 0.1F + 1.0F;
                    }

                    float f11 = 0.1F + (float)j * 0.2F;
                    if (k == 0) {
                        f11 *= ((float)j1 - 1.0F) * 0.1F + 1.0F;
                    }
                    float a  =255;
                    a-=entity.timeB;
                    if (a>10){
                        a-= entity.timeB*4;
                    }
                    if (a<0){
                        a=0;
                    }
                    a/=255;
                    quad(matrix4f, vertexconsumer, f2, f3, j1, f4, f5, 106/255f ,90/255f ,205/255f, f10, f11, false, false, true, false,a);
                    quad(matrix4f, vertexconsumer, f2, f3, j1, f4, f5, 106/255f ,90/255f ,205/255f, f10, f11, true, false, true, true,a);
                    quad(matrix4f, vertexconsumer, f2, f3, j1, f4, f5, 106/255f ,90/255f ,205/255f, f10, f11, true, true, false, true,a);
                    quad(matrix4f, vertexconsumer, f2, f3, j1, f4, f5, 106/255f ,90/255f ,205/255f, f10, f11, false, true, false, false,a);
                }
            }
        }

    }

    private static void quad(Matrix4f matrix, VertexConsumer consumer, float x1, float z1, int index, float x2, float z2, float red, float green, float blue, float p_115283_, float p_115284_, boolean p_115285_, boolean p_115286_, boolean p_115287_, boolean p_115288_,float a  ) {
        consumer.addVertex(matrix, x1 + (p_115285_ ? p_115284_ : -p_115284_), (float)(index * 16), z1 + (p_115286_ ? p_115284_ : -p_115284_)).setColor(red, green, blue, a);
        consumer.addVertex(matrix, x2 + (p_115285_ ? p_115283_ : -p_115283_), (float)((index + 1) * 16), z2 + (p_115286_ ? p_115283_ : -p_115283_)).setColor(red, green, blue, a);
        consumer.addVertex(matrix, x2 + (p_115287_ ? p_115283_ : -p_115283_), (float)((index + 1) * 16), z2 + (p_115288_ ? p_115283_ : -p_115283_)).setColor(red, green, blue, a);
        consumer.addVertex(matrix, x1 + (p_115287_ ? p_115284_ : -p_115284_), (float)(index * 16), z1 + (p_115288_ ? p_115284_ : -p_115284_)).setColor(red, green, blue, a);
    }

    public ResourceLocation getTextureLocation(bolt entity) {
        return TextureAtlas.LOCATION_BLOCKS;
    }
}

