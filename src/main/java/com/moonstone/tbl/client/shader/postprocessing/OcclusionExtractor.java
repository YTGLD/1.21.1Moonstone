package com.moonstone.tbl.client.shader.postprocessing;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.resources.ResourceLocation;
import com.moonstone.tbl.common.TBL;

public class OcclusionExtractor extends PostProcessingEffect<OcclusionExtractor> {
	private int worldDepth = -1;
	private int clipPlaneDepth = -1;

	private int worldDepthFBOUniformID = -1;
	private int clipPlaneDepthFBOUniformID = -1;

	public void setDepthTextures(int worldDepth, int clipPlaneDepth) {
		this.worldDepth = worldDepth;
		this.clipPlaneDepth = clipPlaneDepth;
	}

	@Override
	protected ResourceLocation[] getShaders() {
		return new ResourceLocation[] {TBL.prefix("shaders/postprocessing/occlusionextractor/occlusionextractor.vsh"), TBL.prefix("shaders/postprocessing/occlusionextractor/occlusionextractor.fsh")};
	}

	@Override
	protected boolean initEffect() {
		this.worldDepthFBOUniformID = GlStateManager._glGetUniformLocation(this.getShaderProgram(), "s_world_depth");
		this.clipPlaneDepthFBOUniformID = GlStateManager._glGetUniformLocation(this.getShaderProgram(), "s_clipPlane_depth");
		return true;
	}

	@Override
	protected void uploadUniforms(float partialTicks) {
		//Override diffuse sampler
		this.uploadSampler(this.worldDepthFBOUniformID, this.worldDepth, 0);
		this.uploadSampler(this.clipPlaneDepthFBOUniformID, this.clipPlaneDepth, 1);
	}
}
