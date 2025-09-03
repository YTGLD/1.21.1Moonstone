package com.moonstone.moonstonemod.mixin.si_mixin.client;

import com.ytgld.seeking_immortals.renderer.MoonPost;
import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.culling.Frustum;
import org.joml.Matrix4f;
import org.joml.Vector3d;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(LevelRenderer.class)
public abstract class LevelRendererMixin {
    @Shadow
    @Final
    private Minecraft minecraft;

    @Shadow @Nullable private Frustum capturedFrustum;

    @Shadow @Final private Vector3d frustumPos;

    @Shadow private Frustum cullingFrustum;

    @Inject(method = "initOutline()V",
            at = @At("TAIL"))
    private void initOutline(CallbackInfo ci) {
        MoonPost.onInitializeOutline(minecraft);
    }

    @Inject(method = "resize(II)V",
            at = @At("TAIL"))
    private void resize(int x, int y, CallbackInfo ci) {
        MoonPost.resize(x, y);
    }


    @Inject(method = "renderLevel",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/RenderBuffers;bufferSource()Lnet/minecraft/client/renderer/MultiBufferSource$BufferSource;"))
    private void renderLevel1(DeltaTracker deltaTracker, boolean renderBlockOutline, Camera camera, GameRenderer gameRenderer, LightTexture lightTexture, Matrix4f frustumMatrix, Matrix4f projectionMatrix, CallbackInfo ci) {
        MoonPost.clearAndBindWrite(this.minecraft.getMainRenderTarget());
    }

    @Inject(method = "renderLevel(Lnet/minecraft/client/DeltaTracker;ZLnet/minecraft/client/Camera;Lnet/minecraft/client/renderer/GameRenderer;Lnet/minecraft/client/renderer/LightTexture;Lorg/joml/Matrix4f;Lorg/joml/Matrix4f;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/OutlineBufferSource;endOutlineBatch()V"))
    private void renderLevel2(DeltaTracker deltaTracker, boolean renderBlockOutline, Camera camera, GameRenderer gameRenderer, LightTexture lightTexture, Matrix4f frustumMatrix, Matrix4f projectionMatrix, CallbackInfo ci) {
        MoonPost.processEffects(deltaTracker, this.minecraft.getMainRenderTarget());
    }

    @Inject(method = "renderLevel",
            at = @At(
                    value = "TAIL"
            ))
    private void renderLevel3(DeltaTracker deltaTracker, boolean renderBlockOutline, Camera camera, GameRenderer gameRenderer, LightTexture lightTexture, Matrix4f frustumMatrix, Matrix4f projectionMatrix, CallbackInfo ci) {
        MoonPost.blitEffects(minecraft);
    }
}
