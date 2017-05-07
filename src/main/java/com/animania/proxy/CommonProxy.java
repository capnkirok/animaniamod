package com.animania.proxy;

import com.animania.common.AnimaniaAchievements;
import com.animania.common.events.UpdateHandler;
import com.animania.common.handler.EventsHandler;

public class CommonProxy {

	public void preInit() {
		// EntityHandler.preInit();

		// EVENTS
		EventsHandler.preInit();
		UpdateHandler.init();
		AnimaniaAchievements.init();
	}

	public void init() {
	}

}