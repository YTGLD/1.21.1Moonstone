package com.moonstone.tbl.client.event;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.moonstone.moonstonemod.MoonStoneMod;
import net.minecraft.client.renderer.ShaderInstance;
import net.neoforged.neoforge.client.event.RegisterShadersEvent;
import com.moonstone.tbl.client.handler.ShaderHandler;
import com.moonstone.tbl.client.shader.ShaderHelper;
import com.moonstone.tbl.common.TBL;

import java.io.IOException;

public class BetweenlandsShaders {

	public static ShaderInstance PRIMORDIAL_SHIELD;

	static void registerShaders(RegisterShadersEvent event) {
		try {
			event.registerShader(new ShaderInstance(event.getResourceProvider(), TBL.prefix("primordial_shield/primordial_shield"), DefaultVertexFormat.POSITION_TEX_COLOR), instance -> {
				PRIMORDIAL_SHIELD = instance;
			});
		} catch (IOException e) {
			MoonStoneMod.LOGGER.error("Failed to register Betweenlands shaders. Reason: ", e);
		}
		ShaderHandler.loadWorldShader(event.getResourceProvider());
		ShaderHelper.INSTANCE.initShaders(event.getResourceProvider());
	}
}
