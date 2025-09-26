package com.moonstone.tbl.client.shader;

import com.moonstone.tbl.client.shader.postprocessing.WorldShader;
import com.ytgld.seeking_immortals.ClientConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraft.server.packs.resources.ResourceProvider;

import javax.annotation.Nullable;

public class ShaderHelper implements ResourceManagerReloadListener {

	public static final ShaderHelper INSTANCE = new ShaderHelper();
    @Nullable
	private WorldShader worldShader = null;

    @Nullable
	public WorldShader getWorldShader() {
		return this.worldShader;
	}
	public boolean canUseShaders() {
		return true;
	}
	public boolean isWorldShaderActive() {
		if (ClientConfig.CLIENT_CONFIG.Shader.get()){
			return true;
		}else {
			//return true;
			return this.canUseShaders() && this.worldShader != null;
		}
	}
	public void initShaders(ResourceProvider resourceProvider) {
		if(this.canUseShaders()) {
			try {
				if(this.worldShader == null) {
					this.worldShader = new WorldShader(Minecraft.getInstance().getTextureManager(), resourceProvider, Minecraft.getInstance().getMainRenderTarget());
					this.worldShader.resize(Minecraft.getInstance().getWindow().getWidth(), Minecraft.getInstance().getWindow().getHeight());
				}
			} catch(Exception ex) {
                ex.printStackTrace();
			}
		}
	}

	public void require() {
    }

	@Override
	public void onResourceManagerReload(ResourceManager resourceManager) {
	}
}
