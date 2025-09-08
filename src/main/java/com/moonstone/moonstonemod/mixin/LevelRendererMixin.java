package com.moonstone.moonstonemod.mixin;

import com.all.ILevelRender;
import com.mojang.blaze3d.vertex.PoseStack;
import com.moonstone.moonstonemod.client.renderer.MoonPost;
import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.PostChain;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(LevelRenderer.class)
public abstract class LevelRendererMixin implements ILevelRender {
    @Shadow @Nullable private PostChain transparencyChain;

    @Override
    public PostChain moonstone1_21_1$transparencyChain() {
        return transparencyChain;
    }
}
