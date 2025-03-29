package com.moonstone.moonstonemod.mixin.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.client.renderer.MRender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.SectionBufferBuilderPack;
import net.minecraft.client.renderer.block.ModelBlockRenderer;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.level.LevelEvent;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ModelBlockRenderer.class)
public class ModelBlockRendererMixin {
    @Shadow @Final private BlockColors blockColors;

    @Inject(method = "putQuadData",
            at = @At(
                    value = "RETURN"
            ))
    private void putQuadData1(BlockAndTintGetter level,
                              BlockState state,
                              BlockPos pos,
                              VertexConsumer consumer,
                              PoseStack.Pose pose,
                              BakedQuad quad,
                              float brightness0,
                              float brightness1,
                              float brightness2,
                              float brightness3,
                              int lightmap0,
                              int lightmap1,
                              int lightmap2,
                              int lightmap3,
                              int packedOverlay,
                              CallbackInfo ci) {
        Player player = Minecraft.getInstance().player;
        if (player != null && !state.isEmpty()) {
            float maxDistance = 10;
            float minAlp = 0;
            int minLight = 0;
            float maxAlp = 1.0f;
            int maxLight = 240;

            BlockPos blockPosPlayer = player.getOnPos();
            float distance = (float) Math.sqrt(pos.distSqr(blockPosPlayer));

            if (distance > maxDistance) {
                return;
            }


            double v = Math.sqrt(maxDistance - distance) / Math.sqrt(maxDistance);
            float alp = (float) (v * (maxAlp - minAlp) + minAlp);
            int light = Math.min((int) (v * (maxLight - minLight) + minLight), maxLight);
            if (alp<=0||light<=0){
                return;
            }

            consumer.putBulkData(pose, quad, new float[]{1.0F, 1.0F, 1.0F, 1.0F}, 1, 0, 0, 0.01f, new int[]{240, 240, 240, 240}, packedOverlay, true);
        }
    }
}

