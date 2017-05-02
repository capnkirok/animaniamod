package com.animania.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;

public class ModelBall extends ModelBase {
private ModelRendererBall modelBall;

	public ModelBall() {
		textureWidth = 64;
		textureHeight = 32;
		modelBall = new ModelRendererBall(this, 0, 0);
		
	}
    @Override
    public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        super.render(par1Entity, par2, par3, par4, par5, par6, par7);
        modelBall.color = (int) par2;
        modelBall.render(par7);
    }
}
