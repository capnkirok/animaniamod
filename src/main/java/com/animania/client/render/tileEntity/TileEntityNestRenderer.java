package com.animania.client.render.tileEntity;

import javax.annotation.Nullable;

import com.animania.client.models.ModelNest;
import com.animania.common.tileentities.TileEntityNest;
import com.mojang.authlib.GameProfile;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityNestRenderer extends TileEntitySpecialRenderer<TileEntityNest>
{
	private static final ResourceLocation NEST_TEXTURE = new ResourceLocation("animania:textures/entity/tileentities/block_nest_white.png");
	private static final ResourceLocation NEST_TEXTURE2 = new ResourceLocation("animania:textures/entity/tileentities/block_nest_blue.png");
	public static TileEntityNestRenderer  instance;
	private final ModelNest               nest         = new ModelNest();

	@Override
	public void renderTileEntityAt(TileEntityNest te, double x, double y, double z, float partialTicks, int destroyStage) {
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		this.renderNest(te, (float) x, (float) y, (float) z, destroyStage, partialTicks);
		GlStateManager.popMatrix();
	}

	@Override
	public void setRendererDispatcher(TileEntityRendererDispatcher rendererDispatcherIn) {
		super.setRendererDispatcher(rendererDispatcherIn);
		TileEntityNestRenderer.instance = this;
	}

	public void renderNest(TileEntityNest te, float x, float y, float z, int destroyStage, float animateTicks) {
		ModelBase modelbase = this.nest;

		if (destroyStage >= 0) {
			this.bindTexture(TileEntitySpecialRenderer.DESTROY_STAGES[destroyStage]);
			GlStateManager.matrixMode(5890);
			GlStateManager.pushMatrix();
			GlStateManager.scale(4.0F, 2.0F, 1.0F);
			GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
			GlStateManager.matrixMode(5888);
		}
		
		this.bindTexture(TileEntityNestRenderer.NEST_TEXTURE);
		
		GlStateManager.pushMatrix();
		GlStateManager.scale(-1.0F, -1.0F, 1.0F);
		GlStateManager.enableAlpha();

		modelbase.render((Entity) null, animateTicks, 0.0F, 0.0F, 0, 0.0F, 0.0625F);
		
		if(te != null && !te.itemHandler.getStackInSlot(0).isEmpty())
			this.nest.renderEggs(0.0625F, te.itemHandler.getStackInSlot(0).getCount(), te.getNestContent());
		
		GlStateManager.popMatrix();

		if (destroyStage >= 0) {
			GlStateManager.matrixMode(5890);
			GlStateManager.popMatrix();
			GlStateManager.matrixMode(5888);
		}
	}
}