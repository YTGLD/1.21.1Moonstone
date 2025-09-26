package com.moonstone.tbl.client.shader.postprocessing;

import com.google.gson.JsonSyntaxException;
import com.mojang.blaze3d.pipeline.RenderTarget;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceProvider;
import com.moonstone.tbl.common.MoonstoneTBL;

import java.io.IOException;

public class DiffBlitDepth extends PostChain implements AutoCloseable {

	public RenderTarget BeforeTarget;
	public RenderTarget AfterTarget;
	public RenderTarget Output;
	public RenderTarget Base;

	public DiffBlitDepth(TextureManager textureManager, ResourceProvider resourceProvider, RenderTarget screenTarget) throws IOException, JsonSyntaxException {
		super(textureManager, resourceProvider, screenTarget, ResourceLocation.fromNamespaceAndPath(MoonstoneTBL.ID, "shaders/post/diffblitdepth.json"));

		this.BeforeTarget = this.getTempTarget("beforeDepth");
		this.AfterTarget = this.getTempTarget("afterDepth");
		this.Output = this.getTempTarget("output");
		this.Base = this.getTempTarget("base");
	}
}