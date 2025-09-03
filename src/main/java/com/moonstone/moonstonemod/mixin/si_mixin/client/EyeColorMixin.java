package com.moonstone.moonstonemod.mixin.si_mixin.client;

import com.ytgld.seeking_immortals.Handler;
import com.ytgld.seeking_immortals.SeekingImmortalsMod;
import com.ytgld.seeking_immortals.init.Items;
import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceProvider;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;

@Mixin(GameRenderer.class)
public class EyeColorMixin {
    @Shadow @Final
    Minecraft minecraft;
    @Shadow @Final private Camera mainCamera;
    @Unique
    private PostChain seekingImmortals$blurEffect;
    @Inject(at = @At("RETURN"), method = "close")
    public void close(CallbackInfo ci){
        if (this.seekingImmortals$blurEffect != null) {
            this.seekingImmortals$blurEffect.close();
        }
    }
    @Inject(at = @At("RETURN"), method = "loadBlurEffect")
    public void loadBlurEffect(ResourceProvider resourceProvider, CallbackInfo ci){
        if (this.seekingImmortals$blurEffect != null) {
            this.seekingImmortals$blurEffect.close();
        }
        try {
            this.seekingImmortals$blurEffect = new PostChain(this.minecraft.getTextureManager(), resourceProvider, this.minecraft.getMainRenderTarget(),
                    ResourceLocation.fromNamespaceAndPath(SeekingImmortalsMod.MODID,"shaders/post/blur.json")
            );
            this.seekingImmortals$blurEffect.resize(this.minecraft.getWindow().getWidth(), this.minecraft.getWindow().getHeight());
        } catch (IOException ignored) {

        }
    }
    @Inject(at = @At("RETURN"), method = "resize")
    public void resize(int width, int height, CallbackInfo ci){
        if (this.seekingImmortals$blurEffect != null) {
            this.seekingImmortals$blurEffect.resize(width, height);
        }

    }
    @Inject(at = @At("RETURN"), method = "render")
    public void init(DeltaTracker deltaTracker, boolean renderLevel, CallbackInfo ci) {
        if (mainCamera.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.muddy_jewels.get())) {
                if (this.seekingImmortals$blurEffect != null) {
                    this.seekingImmortals$blurEffect.process(deltaTracker.getGameTimeDeltaTicks());
                }
            }
        }
    }
}
