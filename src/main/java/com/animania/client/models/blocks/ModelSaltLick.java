package com.animania.client.models.blocks;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.model.ModelRenderer;

public class ModelSaltLick extends ModelBase
{
	ModelRenderer Block;

	public ModelSaltLick()
	{
		this(0.0f);
	}

	public ModelSaltLick(float par1)
	{
		this.Block = new ModelRenderer(this, 6, 6);
		this.Block.setTextureSize(64, 32);
		this.Block.addBox(-5F, -5F, -5F, 10, 10, 10);
		this.Block.setRotationPoint(0F, 19F, 0F);
	}

	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		this.Block.rotateAngleX = 0F;
		this.Block.rotateAngleY = 0F;
		this.Block.rotateAngleZ = 0F;
		this.Block.render(par7);

	}

}
