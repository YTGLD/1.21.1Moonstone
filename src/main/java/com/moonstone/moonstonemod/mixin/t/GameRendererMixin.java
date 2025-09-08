package com.moonstone.moonstonemod.mixin.t;

import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import com.moonstone.tbl.client.handler.ShaderHandler;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
	@Inject(method = "resize", at = @At("TAIL"), remap = false)
	private void resizeBuffers(int width, int height, CallbackInfo ci) {
		ShaderHandler.resize(width, height);
	}
}
