package com.animania.client.render.BlockEntity;

import com.animania.Animania;
import com.leviathanstudio.craftstudio.client.model.ModelCraftStudio;
import com.leviathanstudio.craftstudio.common.animation.simpleImpl.CSBlockEntitySpecialRenderer;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.renderer.BlockEntity.BlockEntityRendererDispatcher;
import net.minecraft.client.renderer.BlockEntity.BlockEntitySpecialRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Dist.CLIENT)
public class BlockEntityCraftstudioRenderer<T extends BlockEntity> extends BlockEntitySpecialRenderer<T>
{
	private final ResourceLocation BLOCK_TEXTURE;

	private final ModelCraftStudio BLOCK_MODEL;

	public static BlockEntityCraftstudioRenderer instance;

	private double offsetX, offsetY, offsetZ;

	public BlockEntityCraftstudioRenderer(String name, int texHeight, int texWidth, double offsetX, double offsetY, double offsetZ)
	{
		this.BLOCK_TEXTURE = new ResourceLocation("animania:textures/entity/tileentities/" + name + ".png");
		this.BLOCK_MODEL = new ModelCraftStudio(Animania.MODID, "model_" + name, texHeight, texWidth);

		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.offsetZ = offsetZ;
	}

	public BlockEntityCraftstudioRenderer(String name, int texHeight, int texWidth)
	{
		this(name, texHeight, texWidth, 0, 0, 0);
	}

	@Override
	public void render(T te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
	{
		Direction enumfacing = Direction.getFront(te.getBlockMetadata() & 7);

		GlStateManager.pushMatrix();
		GlStateManager.translate(x + 0.5D + this.offsetX, y + 1.5D + this.offsetY, z + 0.5D + this.offsetZ);
		GlStateManager.multMatrix(CSBlockEntitySpecialRenderer.ROTATION_CORRECTOR);
		GlStateManager.rotate(enumfacing.getHorizontalAngle(), 0, 1, 0);
		this.bindTexture(this.BLOCK_TEXTURE);
		this.BLOCK_MODEL.render();
		GlStateManager.popMatrix();
	}

	@Override
	public void setRendererDispatcher(BlockEntityRendererDispatcher rendererDispatcherIn)
	{
		super.setRendererDispatcher(rendererDispatcherIn);
		BlockEntityCraftstudioRenderer.instance = this;
	}
}