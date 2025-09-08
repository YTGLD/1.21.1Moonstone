package com.moonstone.moonstonemod.mixin.t;

import com.mojang.blaze3d.vertex.PoseStack;
import com.moonstone.tbl.client.handler.ShaderHandler;
import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.renderer.*;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

import static com.moonstone.tbl.client.handler.ShaderHandler.*;

@Mixin(LevelRenderer.class)
public abstract class LevelRendererMixin {

	@Shadow @Nullable private PostChain transparencyChain;

	// Copy main render target depth to WorldShader depth buffer before depth is cleared
	@Inject(method = "renderDebug", at = @At("HEAD"), remap = false)
	public void renderDebugHook(PoseStack poseStack, MultiBufferSource buffer, Camera camera, CallbackInfo ci) {
		onPreRenderDebug(poseStack, buffer, camera);
	}

	@Inject(method = "renderLevel", at = @At("RETURN"), remap = false)
	public void renderLevel(DeltaTracker deltaTracker, boolean renderBlockOutline, Camera camera, GameRenderer gameRenderer, LightTexture lightTexture, Matrix4f frustumMatrix, Matrix4f projectionMatrix, CallbackInfo ci) {
		float f = deltaTracker.getGameTimeDeltaPartialTick(false);
		ShaderHandler.renderWorldShader(f);
	}

	@Inject(method = "renderSectionLayer", at = @At("HEAD"), remap = false)
	public void translucentPatcherStart(RenderType renderType, double x, double y, double z, Matrix4f frustrumMatrix, Matrix4f projectionMatrix, CallbackInfo ci) {
		// Translucent RenderType only, Fast & Fancy only
		if (renderType != RenderType.translucent() || this.transparencyChain != null) return;
		onPreTranslucentBatch();
	}

	@Inject(method = "renderSectionLayer", at = @At("RETURN"), remap = false)
	public void translucentPatcherEnd(RenderType renderType, double x, double y, double z, Matrix4f frustrumMatrix, Matrix4f projectionMatrix, CallbackInfo ci) {
		if (renderType != RenderType.translucent() || this.transparencyChain != null) return;
		onPostTranslucentBatch();
	}
}
