package com.moonstone.tbl.client.shader.postprocessing;

import com.all.IPostChain;
import com.google.gson.JsonSyntaxException;
import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.shaders.Uniform;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceProvider;
import com.moonstone.tbl.common.MoonstoneTBL;

import javax.annotation.Nullable;
import java.io.IOException;

public class Warp extends PostChain implements AutoCloseable {
	public RenderTarget BaseTexture;
	public RenderTarget gasTextureTarget;
	public Warp(TextureManager textureManager, ResourceProvider resourceProvider, RenderTarget screenTarget) throws IOException, JsonSyntaxException {
		super(textureManager, resourceProvider, screenTarget, ResourceLocation.fromNamespaceAndPath(MoonstoneTBL.ID, "shaders/post/warp.json"));
		this.BaseTexture = this.getTempTarget("input");
		this.BaseTexture.setClearColor(1,1,1,1);
		this.BaseTexture.clear(false);
		this.gasTextureTarget = this.getTempTarget("output");
	}

}