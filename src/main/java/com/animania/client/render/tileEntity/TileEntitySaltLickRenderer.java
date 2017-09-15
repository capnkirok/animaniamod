package com.animania.client.render.tileEntity;

import com.animania.client.models.ModelNest;
import com.animania.client.models.blocks.ModelSaltLick;
import com.animania.common.tileentities.TileEntityNest;
import com.animania.common.tileentities.TileEntitySaltLick;
import com.animania.config.AnimaniaConfig;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

public class TileEntitySaltLickRenderer extends TileEntitySpecialRenderer<TileEntitySaltLick>
{
	private static final ResourceLocation TEXTURE = new ResourceLocation("animania:textures/entity/tileentities/salt_lick.png");
	public static TileEntitySaltLickRenderer  instance;

	private final ModelSaltLick saltLick = new ModelSaltLick();

	@Override
	public void setRendererDispatcher(TileEntityRendererDispatcher rendererDispatcherIn) {
		super.setRendererDispatcher(rendererDispatcherIn);
		TileEntitySaltLickRenderer.instance = this;
	}
	
	@Override
	public void render(TileEntitySaltLick te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
	{
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x + 0.5F, (float) y, (float) z + 0.5F);

		this.bindTexture(TEXTURE);

		GlStateManager.pushMatrix();
		GlStateManager.scale(-1.0F, -1.0F, 1.0F);
		GlStateManager.enableAlpha();

		float amountLeft = (float)te.usesLeft / (float)AnimaniaConfig.careAndFeeding.saltLickMaxUses;
		
		GlStateManager.scale(1f, amountLeft, 1f);
		GlStateManager.translate(0, -1.5, 0);
		saltLick.render((Entity) null, partialTicks, 0.0F, 0.0F, 0, 0.0F, 0.0625F);
		GlStateManager.popMatrix();

		GlStateManager.popMatrix();
		
	}

}
