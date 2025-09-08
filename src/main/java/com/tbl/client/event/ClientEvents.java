package com.tbl.client.event;

import net.neoforged.neoforge.common.NeoForge;
import com.tbl.client.handler.ShaderHandler;

public class ClientEvents {

	public static void init() {
		NeoForge.EVENT_BUS.addListener(ShaderHandler::onRenderWorldLast);
		NeoForge.EVENT_BUS.addListener(ShaderHandler::onRenderWeather);
	}


}
