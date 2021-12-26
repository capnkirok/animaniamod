package com.animania.client.render.BlockEntity;

import com.animania.client.models.blocks.ModelSaltLick;
import com.animania.common.tileentities.BlockEntitySaltLick;
import com.animania.config.AnimaniaConfig;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.renderer.BlockEntity.BlockEntityRendererDispatcher;
import net.minecraft.client.renderer.BlockEntity.BlockEntitySpecialRenderer;
import net.minecraft.resources.ResourceLocation;

public class BlockEntitySaltLickRenderer extends BlockEntitySpecialRenderer<BlockEntitySaltLick>
{
	private static final ResourceLocation TEXTURE = new ResourceLocation("animania:textures/entity/tileentities/salt_lick.png");
	public static BlockEntitySaltLickRenderer instance;

	private final ModelSaltLick saltLick = new ModelSaltLick();

	@Override
	public void setRendererDispatcher(BlockEntityRendererDispatcher rendererDispatcherIn)
	{
		super.setRendererDispatcher(rendererDispatcherIn);
		BlockEntitySaltLickRenderer.instance = this;
	}

	@Override
	public void render(BlockEntitySaltLick te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
	{
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x + 0.5F, (float) y, (float) z + 0.5F);

		this.bindTexture(TEXTURE);

		GlStateManager.pushMatrix();
		GlStateManager.scale(-1.0F, -1.0F, 1.0F);
		GlStateManager.enableAlpha();

		float amountLeft = (float) te.usesLeft / (float) AnimaniaConfig.careAndFeeding.saltLickMaxUses;

		GlStateManager.scale(1f, amountLeft, 1f);
		GlStateManager.translate(0, -1.5, 0);
		this.saltLick.render((Entity) null, partialTicks, 0.0F, 0.0F, 0, 0.0F, 0.0625F);
		GlStateManager.popMatrix();

		GlStateManager.popMatrix();

	}

}
