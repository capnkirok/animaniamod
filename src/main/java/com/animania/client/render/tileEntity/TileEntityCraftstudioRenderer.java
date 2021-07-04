package com.animania.client.render.tileEntity;

import com.animania.Animania;
import com.leviathanstudio.craftstudio.client.model.ModelCraftStudio;
import com.leviathanstudio.craftstudio.common.animation.simpleImpl.CSTileEntitySpecialRenderer;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Dist.CLIENT)
public class TileEntityCraftstudioRenderer<T extends TileEntity> extends TileEntitySpecialRenderer<T>
{
	private final ResourceLocation BLOCK_TEXTURE;

	private final ModelCraftStudio BLOCK_MODEL;

	public static TileEntityCraftstudioRenderer instance; 

	private double offsetX,offsetY,offsetZ;
	
	public TileEntityCraftstudioRenderer(String name, int texHeight, int texWidth, double offsetX, double offsetY, double offsetZ)
	{
		BLOCK_TEXTURE = new ResourceLocation("animania:textures/entity/tileentities/" + name + ".png");
		BLOCK_MODEL = new ModelCraftStudio(Animania.MODID, "model_" + name, texHeight, texWidth);
		
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.offsetZ = offsetZ;
	}
	
	public TileEntityCraftstudioRenderer(String name, int texHeight, int texWidth)
	{
		this(name, texHeight, texWidth, 0, 0, 0);
	}

	@Override
	public void render(T te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
	{
		EnumFacing enumfacing = EnumFacing.getFront(te.getBlockMetadata() & 7);

		GlStateManager.pushMatrix();
		GlStateManager.translate(x + 0.5D + this.offsetX, y + 1.5D + this.offsetY, z + 0.5D + this.offsetZ);
		GlStateManager.multMatrix(CSTileEntitySpecialRenderer.ROTATION_CORRECTOR);
		GlStateManager.rotate(enumfacing.getHorizontalAngle(), 0, 1, 0);
		this.bindTexture(BLOCK_TEXTURE);
		this.BLOCK_MODEL.render();
		GlStateManager.popMatrix();
	}

	@Override
	public void setRendererDispatcher(TileEntityRendererDispatcher rendererDispatcherIn)
	{
		super.setRendererDispatcher(rendererDispatcherIn);
		TileEntityCraftstudioRenderer.instance = this;
	}
}