package com.animania.proxy;

public class ClientProxy extends CommonProxy {

	@Override
	public void preInit() {
		super.preInit();
		// RenderHandler.preInit();
	}

	@Override
	public void init() {
		super.init();
		// AnimaniaTextures.registerTextures();
	}

}