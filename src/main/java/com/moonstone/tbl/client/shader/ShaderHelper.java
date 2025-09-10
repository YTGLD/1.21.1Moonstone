package com.moonstone.tbl.client.shader;

import com.mojang.blaze3d.systems.RenderSystem;
import com.ytgld.seeking_immortals.ClientConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraft.server.packs.resources.ResourceProvider;
import org.lwjgl.opengl.*;
import com.moonstone.tbl.client.shader.postprocessing.Tonemapper;
import com.moonstone.tbl.client.shader.postprocessing.WorldShader;

import javax.annotation.Nullable;

public class ShaderHelper implements ResourceManagerReloadListener {

	public static final ShaderHelper INSTANCE = new ShaderHelper();

	private boolean checked = false;
	private boolean shadersSupported = false;
	private boolean gl30Supported = false;
	private boolean arbFloatBufferSupported = false;

	@Nullable
	private Exception shaderError = null;
	@Nullable
	private WorldShader worldShader = null;
	@Nullable
	private Tonemapper toneMappingShader = null;
	private boolean shadersUpdated = false;
	private boolean required = false;

	/**
	 * The minumum amount of required texture units for the shaders to work properly
	 */
	public static final int MIN_REQUIRED_TEX_UNITS = 6;

	@Nullable
	public WorldShader getWorldShader() {
		return this.worldShader;
	}

	/**
	 * Returns whether shaders are supported and enabled
	 * @return
	 */
	public boolean canUseShaders() {
		return true;
	}

	/**
	 * Returns whether the world shader is active
	 * @return
	 */
	public boolean isWorldShaderActive() {
		if (ClientConfig.CLIENT_CONFIG.Shader.get()){
			return true;
		}else {
			//return true;
			return this.canUseShaders() && this.worldShader != null;
		}
	}

	/**
	 * initializes the main shader if necessary
	 */
	public void initShaders(ResourceProvider resourceProvider) {
		if(this.canUseShaders()) {
			try {
				if(this.worldShader == null) {
					this.worldShader = new WorldShader(Minecraft.getInstance().getTextureManager(), resourceProvider, Minecraft.getInstance().getMainRenderTarget());
					this.worldShader.resize(Minecraft.getInstance().getWindow().getWidth(), Minecraft.getInstance().getWindow().getHeight());
				}
			} catch(Exception ex) {
				this.shaderError = ex;
				ex.printStackTrace();
			}
		}
	}

	/**
	 * Updates the main shader
	 */
	public void updateShaders(float partialTicks) {
		if(this.canUseShaders()) {
			try {
				if(this.isRequired()) {
					//this.worldShader.updateDepthBuffer();
					//this.worldShader.updateMatrices();
					this.worldShader.updateTextures(partialTicks);

					this.shadersUpdated = true;
				}
			} catch(Exception ex) {
				this.shaderError = ex;
				ex.printStackTrace();
			}
		}
	}

	/**
	 * Deletes the main shader
	 */
	public void deleteShaders() {
		this.shaderError = null;

		//if(this.worldShader != null)
		//	this.worldShader.delete();
		//this.worldShader = null;

		//if(this.blitBuffer != null)
		//	this.blitBuffer.delete();
		//this.blitBuffer = null;

		if(this.toneMappingShader != null)
			this.toneMappingShader.delete();
		this.toneMappingShader = null;
	}

	/**
	 * Enables the shaders to be used in the next/current render tick.
	 * The shaders are always rendered in the BL dimension, but if something in another
	 * dimension requires the shaders this must be called every render tick
	 */
	public void require() {
		this.required = true;
	}

	private boolean isRequired() {
		if(this.required) {
			return true;
		}
		Minecraft mc = Minecraft.getInstance();
		if(mc.player != null) {
//			IPortalCapability cap = mc.player.getCapability(CapabilityRegistry.CAPABILITY_PORTAL, null);
//			if (cap != null && cap.isInPortal()) {
//				return true;
//			}
		}
		return mc.level != null;
	}

	@Override
	public void onResourceManagerReload(ResourceManager resourceManager) {
		this.deleteShaders();
	}
}
