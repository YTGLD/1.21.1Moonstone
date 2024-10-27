package com.moonstone.moonstonemod.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.moonstone.moonstonemod.entity.owner_blood;
import com.moonstone.moonstonemod.init.EntityTs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.client.renderer.RenderBuffers;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

@Mixin(LevelRenderer.class)
public abstract class LevelRendererMixin {
    @Shadow @Nullable
    private ClientLevel level;

    @Shadow @Final private Minecraft minecraft;

    @Shadow @Nullable private PostChain entityEffect;

    @Shadow @Final private RenderBuffers renderBuffers;

    @Shadow protected abstract void renderEntity(Entity entity, double camX, double camY, double camZ, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource);
    @Inject(method = "getLightColor(Lnet/minecraft/world/level/BlockAndTintGetter;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/BlockPos;)I",
            at = @At(value = "RETURN"), cancellable = true)
    private static void renderLevel(BlockAndTintGetter levels, BlockState state, BlockPos zeroPos, CallbackInfoReturnable<Integer> cir) {

        if (Minecraft.getInstance().level!=null) {

            for (int i = 0; i < Minecraft.getInstance().level.getEntityCount(); i++) {
                Entity entity = Minecraft.getInstance().level.getEntity(i);
                if (entity != null && entity.getType() == (EntityTs.owner_blood.get())) {
                    BlockPos playerPos = entity.blockPosition();

                    int size = 0;

                    int playerX = playerPos.getX();
                    int playerY = playerPos.getY();
                    int playerZ = playerPos.getZ();

                    int zeroX = zeroPos.getX();
                    int zeroY = zeroPos.getY();
                    int zeroZ = zeroPos.getZ();

                    double distance = Math.sqrt(Math.pow(playerX - zeroX, 2) + Math.pow(playerY - zeroY, 2) + Math.pow(playerZ - zeroZ, 2));


                    if (distance <= 14) {
                        size = (int) (14 - distance); // 越接近中心，size越大
                    }
                    int is = levels.getBrightness(LightLayer.SKY, zeroPos);
                    int j =  levels.getBrightness(LightLayer.BLOCK, zeroPos);
                    int k = size;
                    if (j < k) {
                        j = k;
                    }

                    cir.setReturnValue(is << 20 | j << 4);
                }

            }
        }
    }
}
