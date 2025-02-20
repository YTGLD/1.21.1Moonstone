package com.moonstone.moonstonemod.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.moonstone.moonstonemod.ConfigClient;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.client.renderer.MRender;
import com.moonstone.moonstonemod.client.renderer.MoonPost;
import com.moonstone.moonstonemod.init.moonstoneitem.DataReg;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.item.blood.max_sword;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.Map;

public class Sword {
    public Sword(@NotNull PoseStack matrices,
                 @NotNull MultiBufferSource vertexConsumers,
                 int light,
                 @NotNull Entity entity) {
        if (ConfigClient.Client.Shader.get()) {
            MoonPost.renderEffectForNextTick(MoonStoneMod.POST);
        }
        if (entity instanceof LivingEntity living) {
            if (Handler.hascurio(living,Items.max_sword.get())) {
                CuriosApi.getCuriosInventory(living).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (!stack.isEmpty()) {
                                if (stack.is(Items.max_sword.get())) {
                                    if (stack.get(DataReg.tag) != null) {
                                        if (stack.get(DataReg.tag).getInt(max_sword.MaxSword)>0){
                                            matrices.pushPose();
                                            matrices.mulPose(Axis.YN.rotation((float) living.tickCount/20));
                                            matrices.translate(0,0.07,0.7*1.5);
                                            renderSphere1(matrices,vertexConsumers,light,0.17f);
                                            matrices.popPose();

                                        }
                                        if (stack.get(DataReg.tag).getInt(max_sword.MaxSword)>1){
                                            matrices.pushPose();
                                            matrices.mulPose(Axis.YN.rotation((float) living.tickCount/22));
                                            matrices.translate(0,0,0.5f*1.5);
                                            renderSphere1(matrices,vertexConsumers,light,0.14f);
                                            matrices.popPose();
                                        }
                                        if (stack.get(DataReg.tag).getInt(max_sword.MaxSword)>2){
                                            matrices.pushPose();
                                            matrices.mulPose(Axis.YN.rotation((float) living.tickCount/10));
                                            matrices.translate(0,0.2,0.45*1.5);
                                            renderSphere1(matrices,vertexConsumers,light,0.1f);
                                            matrices.popPose();
                                        }
                                        if (stack.get(DataReg.tag).getInt(max_sword.MaxSword)>3){
                                            matrices.pushPose();
                                            matrices.mulPose(Axis.YN.rotation((float) living.tickCount/21));
                                            matrices.translate(0,0.22,0.23*1.5);
                                            renderSphere1(matrices,vertexConsumers,light,0.075f);
                                            matrices.popPose();
                                        }
                                        if (stack.get(DataReg.tag).getInt(max_sword.MaxSword)>4){
                                            matrices.pushPose();
                                            matrices.mulPose(Axis.YN.rotation((float) living.tickCount/8));
                                            matrices.translate(0,0.25,0.55*1.5);
                                            renderSphere1(matrices,vertexConsumers,light,0.12f);
                                            matrices.popPose();


                                        }
                                        if (stack.get(DataReg.tag).getInt(max_sword.MaxSword)>5){
                                            matrices.pushPose();
                                            matrices.mulPose(Axis.YN.rotation((float) living.tickCount/6));
                                            matrices.translate(0,-0.05,0.58*1.5);
                                            renderSphere1(matrices,vertexConsumers,light,0.1f);
                                            matrices.popPose();
                                        }
                                        if (stack.get(DataReg.tag).getInt(max_sword.MaxSword)>6){
                                            matrices.pushPose();
                                            matrices.mulPose(Axis.YN.rotation((float) living.tickCount/15));
                                            matrices.translate(0,0.11,0.6*1.5);
                                            renderSphere1(matrices,vertexConsumers,light,0.15f);
                                            matrices.popPose();
                                        }
                                        if (stack.get(DataReg.tag).getInt(max_sword.MaxSword)>7){
                                            matrices.pushPose();
                                            matrices.mulPose(Axis.YN.rotation((float) living.tickCount/20));
                                            matrices.translate(0,0.11,0.4*1.5);
                                            renderSphere1(matrices,vertexConsumers,light,0.22f);
                                            matrices.popPose();
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
            }

        }
    }
    public void renderSphere1(@NotNull PoseStack matrices, @NotNull MultiBufferSource vertexConsumers, int light, float s) {
        {
            float radius = s; // 球体的半径
            int stacks = 20; // 垂直方向的分割数
            int slices = 20; // 水平方向的分割数
            VertexConsumer vertexConsumer = vertexConsumers.getBuffer(MRender.Snake());
            for (int i = 0; i < stacks; ++i) {
                float phi0 = (float) Math.PI * ((i + 0) / (float) stacks);
                float phi1 = (float) Math.PI * ((i + 1) / (float) stacks);

                for (int j = 0; j < slices; ++j) {
                    float theta0 = (float) (2 * Math.PI) * ((j + 0) / (float) slices);
                    float theta1 = (float) (2 * Math.PI) * ((j + 1) / (float) slices);

                    float x0 = radius * (float) Math.sin(phi0) * (float) Math.cos(theta0);
                    float y0 = radius * (float) Math.cos(phi0);
                    float z0 = radius * (float) Math.sin(phi0) * (float) Math.sin(theta0);

                    float x1 = radius * (float) Math.sin(phi0) * (float) Math.cos(theta1);
                    float y1 = radius * (float) Math.cos(phi0);
                    float z1 = radius * (float) Math.sin(phi0) * (float) Math.sin(theta1);

                    float x2 = radius * (float) Math.sin(phi1) * (float) Math.cos(theta1);
                    float y2 = radius * (float) Math.cos(phi1);
                    float z2 = radius * (float) Math.sin(phi1) * (float) Math.sin(theta1);

                    float x3 = radius * (float) Math.sin(phi1) * (float) Math.cos(theta0);
                    float y3 = radius * (float) Math.cos(phi1);
                    float z3 = radius * (float) Math.sin(phi1) * (float) Math.sin(theta0);

                    vertexConsumer.addVertex(matrices.last().pose(), x0, y0, z0).setColor(1.0f, 1.0f, 1.0f, 1.0f).setOverlay(OverlayTexture.NO_OVERLAY).setUv(0, 0).setUv2(light, light).setNormal(1, 0, 0);
                    vertexConsumer.addVertex(matrices.last().pose(), x1, y1, z1).setColor(1.0f, 1.0f, 1.0f, 1.0f).setOverlay(OverlayTexture.NO_OVERLAY).setUv(0, 0).setUv2(light, light).setNormal(1, 0, 0);
                    vertexConsumer.addVertex(matrices.last().pose(), x2, y2, z2).setColor(1.0f, 1.0f, 1.0f, 1.0f).setOverlay(OverlayTexture.NO_OVERLAY).setUv(0, 0).setUv2(light, light).setNormal(1, 0, 0);
                    vertexConsumer.addVertex(matrices.last().pose(), x3, y3, z3).setColor(1.0f, 1.0f, 1.0f, 1.0f).setOverlay(OverlayTexture.NO_OVERLAY).setUv(0, 0).setUv2(light, light).setNormal(1, 0, 0);
                }
            }
        }
        {
            float radius = s; // 球体的半径
            int stacks = 20; // 垂直方向的分割数
            int slices = 20; // 水平方向的分割数
            VertexConsumer vertexConsumer = vertexConsumers.getBuffer(MRender.gateways());
            for (int i = 0; i < stacks; ++i) {
                float phi0 = (float) Math.PI * ((i + 0) / (float) stacks);
                float phi1 = (float) Math.PI * ((i + 1) / (float) stacks);

                for (int j = 0; j < slices; ++j) {
                    float theta0 = (float) (2 * Math.PI) * ((j + 0) / (float) slices);
                    float theta1 = (float) (2 * Math.PI) * ((j + 1) / (float) slices);

                    float x0 = radius * (float) Math.sin(phi0) * (float) Math.cos(theta0);
                    float y0 = radius * (float) Math.cos(phi0);
                    float z0 = radius * (float) Math.sin(phi0) * (float) Math.sin(theta0);

                    float x1 = radius * (float) Math.sin(phi0) * (float) Math.cos(theta1);
                    float y1 = radius * (float) Math.cos(phi0);
                    float z1 = radius * (float) Math.sin(phi0) * (float) Math.sin(theta1);

                    float x2 = radius * (float) Math.sin(phi1) * (float) Math.cos(theta1);
                    float y2 = radius * (float) Math.cos(phi1);
                    float z2 = radius * (float) Math.sin(phi1) * (float) Math.sin(theta1);

                    float x3 = radius * (float) Math.sin(phi1) * (float) Math.cos(theta0);
                    float y3 = radius * (float) Math.cos(phi1);
                    float z3 = radius * (float) Math.sin(phi1) * (float) Math.sin(theta0);

                    vertexConsumer.addVertex(matrices.last().pose(), x0, y0, z0).setColor(1.0f, 1.0f, 1.0f, 1.0f).setOverlay(OverlayTexture.NO_OVERLAY).setUv(0, 0).setUv2(light, light).setNormal(1, 0, 0);
                    vertexConsumer.addVertex(matrices.last().pose(), x1, y1, z1).setColor(1.0f, 1.0f, 1.0f, 1.0f).setOverlay(OverlayTexture.NO_OVERLAY).setUv(0, 0).setUv2(light, light).setNormal(1, 0, 0);
                    vertexConsumer.addVertex(matrices.last().pose(), x2, y2, z2).setColor(1.0f, 1.0f, 1.0f, 1.0f).setOverlay(OverlayTexture.NO_OVERLAY).setUv(0, 0).setUv2(light, light).setNormal(1, 0, 0);
                    vertexConsumer.addVertex(matrices.last().pose(), x3, y3, z3).setColor(1.0f, 1.0f, 1.0f, 1.0f).setOverlay(OverlayTexture.NO_OVERLAY).setUv(0, 0).setUv2(light, light).setNormal(1, 0, 0);
                }
            }
        }
    }
}
