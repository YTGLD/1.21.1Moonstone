package com.moonstone.moonstonemod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.client.CircleCubeBoom;
import com.moonstone.moonstonemod.entity.zombie.test_e;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class NigBoomRender extends EntityRenderer<test_e> {
    public NigBoomRender(EntityRendererProvider.Context p_173917_) {
        super(p_173917_);
    }

    @Override
    public void render(test_e entity, float p_114486_, float p_114487_, PoseStack poseStack, MultiBufferSource bufferSource, int p_114490_) {
        new CircleCubeBoom(poseStack, bufferSource, 240, entity);


        super.render(entity, p_114486_, p_114487_, poseStack, bufferSource, p_114490_);
    }



    @Override
    public ResourceLocation getTextureLocation(test_e p_114482_) {
        return ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,"textures/entity/flysword.png");
    }
}


