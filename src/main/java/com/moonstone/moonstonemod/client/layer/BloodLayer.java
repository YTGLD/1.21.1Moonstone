package com.moonstone.moonstonemod.client.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.moonstone.moonstonemod.entity.attack_blood;
import com.moonstone.moonstonemod.init.moonstoneitem.EntityTs;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.StuckInBodyLayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class BloodLayer <T extends LivingEntity, M extends PlayerModel<T>> extends StuckInBodyLayer<T, M> {
    private final EntityRenderDispatcher dispatcher;

    public BloodLayer(EntityRendererProvider.Context context, LivingEntityRenderer<T, M> renderer) {
        super(renderer);
        this.dispatcher = context.getEntityRenderDispatcher();
    }

    @Override
    protected int numStuck(T entity) {
        if (entity instanceof IBloodSize size){
            return size.getSize();
        }
        return 0;
    }

    @Override
    protected void renderStuckItem(
            PoseStack poseStack, MultiBufferSource buffer, int packedLight, Entity entity, float x, float y, float z, float partialTick
    ) {
        if (entity instanceof IBloodSize size && size.getSize() > 0) {
            attack_blood arrow = new attack_blood(EntityTs.attack_blood.get(), entity.level());

            poseStack.pushPose();
            this.dispatcher.render(arrow, 0.0, 0.0, 0.0,
                    0.0F, partialTick, poseStack, buffer,
                    packedLight);
            poseStack.popPose();

        }
    }
}

