package com.moonstone.moonstonemod.mixin.t;

import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LightTexture;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import com.moonstone.tbl.client.handler.ShaderHandler;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
	@Inject(method = "resize", at = @At("TAIL"))
	private void resizeBuffers(int width, int height, CallbackInfo ci) {
		ShaderHandler.resize(width, height);
	}
}
