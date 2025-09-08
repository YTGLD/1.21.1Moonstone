package com.moonstone.moonstonemod.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.client.renderer.MRender;
import com.ytgld.seeking_immortals.event.old.NewEvent;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class SwordRenderLayer <T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T, M> {
    private final ModelPart part;

    public SwordRenderLayer(RenderLayerParent<T, M> renderer, ModelPart modelPart) {
        super(renderer);
        this.part = modelPart;
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, T player, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(-110));
        float pos = (float) Math.sin(NewEvent.time/200);
        poseStack.scale(1+pos/10,1+pos/11,1+pos/15);
        poseStack.translate(0,pos/5,0);

        VertexConsumer vertexConsumer = buffer.getBuffer(MRender.entityCutoutNoCull1(ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,"textures/model/use_sword_model.png")));
        part.render(poseStack, vertexConsumer, 255, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();
    }
}
