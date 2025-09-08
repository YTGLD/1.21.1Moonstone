package com.moonstone.tbl.client.event;

import com.moonstone.tbl.client.handler.ShaderHandler;
import com.moonstone.tbl.client.shader.ShaderHelper;
import net.neoforged.neoforge.client.event.RegisterShadersEvent;

public class MoonstoneBLShaders {

	static void registerShaders(RegisterShadersEvent event) {
		ShaderHandler.loadWorldShader(event.getResourceProvider());
		ShaderHelper.INSTANCE.initShaders(event.getResourceProvider());
	}
}
