package com.moonstone.moonstonemod.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.moonstone.moonstonemod.entity.zombie.sword_soul;
import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.*;
import net.minecraft.util.FastColor;
import net.minecraft.world.TickRateManager;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(LevelRenderer.class)
public abstract class LevelRendererMixin {
    @Shadow @Nullable private ClientLevel level;

    @Shadow @Final private Minecraft minecraft;

    @Shadow @Nullable private PostChain entityEffect;

    @Shadow @Final private RenderBuffers renderBuffers;

    @Shadow protected abstract void renderEntity(Entity entity, double camX, double camY, double camZ, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource);

    @Inject(at = @At("HEAD"), method = "renderLevel")
    public void moonstone$getUseDuration(DeltaTracker deltaTracker, boolean renderBlockOutline, Camera camera, GameRenderer gameRenderer, LightTexture lightTexture, Matrix4f frustumMatrix, Matrix4f projectionMatrix, CallbackInfo ci) {
        TickRateManager tickratemanager = null;
        if (this.minecraft.level != null) {
            tickratemanager = this.minecraft.level.tickRateManager();
        }
        if (camera!=null) {;
            Vec3 vec3 = camera.getPosition();
            double d0 = vec3.x();
            double d1 = vec3.y();
            double d2 = vec3.z();
            if (level != null) {
                for (Entity entity : level.entitiesForRendering()) {
                    MultiBufferSource multibuffersource;
                    if (entity instanceof sword_soul) {
                        OutlineBufferSource outlinebuffersource = renderBuffers.outlineBufferSource();
                        multibuffersource = outlinebuffersource;
                        int i = entity.getTeamColor();
                        outlinebuffersource.setColor(FastColor.ARGB32.red(i), FastColor.ARGB32.green(i), FastColor.ARGB32.blue(i), 255);

                        float f2 = 0;
                        if (tickratemanager != null) {
                            f2 = deltaTracker.getGameTimeDeltaPartialTick(!tickratemanager.isEntityFrozen(entity));
                        }
                        renderEntity(entity, d0, d1, d2, f2, new PoseStack(), multibuffersource);
                        if (entityEffect != null) {
                            entityEffect.process(deltaTracker.getGameTimeDeltaTicks());
                        }
                        minecraft.getMainRenderTarget().bindWrite(false);
                    }

                }
            }
        }
    }
}
