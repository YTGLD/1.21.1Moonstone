package com.moonstone.tbl.client.event;

import net.neoforged.neoforge.common.NeoForge;
import com.moonstone.tbl.client.handler.ShaderHandler;

public class ClientEvents {

	public static void init() {
		NeoForge.EVENT_BUS.addListener(ShaderHandler::onRenderWeather);
	}


}
