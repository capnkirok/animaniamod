package com.animania.proxy;

import com.animania.common.AnimaniaAchievements;
import com.animania.common.events.UpdateHandler;
import com.animania.common.handler.BlockHandler;
import com.animania.common.handler.DamageSourceHandler;
import com.animania.common.handler.DictionaryHandler;
import com.animania.common.handler.EntityHandler;
import com.animania.common.handler.EventsHandler;
import com.animania.common.handler.ItemHandler;
import com.animania.common.handler.RecipeHandler;
import com.animania.common.handler.TileEntityHandler;

public class CommonProxy {

	public void preInit() {
		EntityHandler.preInit();
		ItemHandler.preInit();
		BlockHandler.preInit();
		TileEntityHandler.preInit();
		DamageSourceHandler.preInit();

		// EVENTS
		EventsHandler.preInit();
		UpdateHandler.init();
		AnimaniaAchievements.init();
	}

	public void init() {
		DictionaryHandler.init();
		RecipeHandler.init();
	}

}