package com.animania.addons.extra.client.render.BlockEntity;

import com.animania.Animania;
import com.animania.addons.extra.common.entity.rodents.EntityHamster;
import com.animania.addons.extra.common.blockentity.BlockEntityHamsterWheel;
import com.leviathanstudio.craftstudio.client.model.ModelCraftStudio;
import com.leviathanstudio.craftstudio.common.animation.simpleImpl.CSBlockEntitySpecialRenderer;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.GlStateManager.DestFactor;
import com.mojang.blaze3d.platform.GlStateManager.SourceFactor;

import net.minecraft.client.renderer.BlockEntity.BlockEntityRendererDispatcher;
import net.minecraft.client.renderer.BlockEntity.BlockEntitySpecialRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Dist.CLIENT)
public class BlockEntityHamsterWheelRenderer extends BlockEntitySpecialRenderer<BlockEntityHamsterWheel>
{
	private static final ResourceLocation WHEEL_TEXTURE = new ResourceLocation("animania:textures/entity/tileentities/hamster_wheel.png");

	private static final ResourceLocation HAMSTER_TEXTURES = new ResourceLocation("animania:textures/entity/rodents/hamster_tarou.png");

	private ModelCraftStudio modelWheel = new ModelCraftStudio(Animania.MODID, "model_hamster_wheel", 64, 32);
	private ModelCraftStudio modelHamster = new ModelCraftStudio(Animania.MODID, "hamster", 64, 32);

	public static BlockEntityHamsterWheelRenderer instance;

	@Override
	public void render(BlockEntityHamsterWheel te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
	{
		Direction enumfacing = Direction.getFront(te.getBlockMetadata() & 7);

		GlStateManager.pushMatrix();
		GlStateManager.translate(x + 0.5D, y + 1.5D, z + 0.5D);
		GlStateManager.multMatrix(CSBlockEntitySpecialRenderer.ROTATION_CORRECTOR);
		GlStateManager.rotate(enumfacing.getHorizontalAngle(), 0, 1, 0);
		GlStateManager.enableAlpha();
		GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
		this.bindTexture(WHEEL_TEXTURE);
		this.modelWheel.render(te);
		EntityHamster hamster = te.getHamster();
		if (hamster != null)
		{
			if (hamster.getResourceLocation() == null)
				hamster.setResourceLoc();
			GlStateManager.pushMatrix();
			GlStateManager.scale(0.5, 0.5, 0.5);
			GlStateManager.translate(0, 0.9, 0);
			GlStateManager.rotate(-90, 0, 1, 0);
			this.bindTexture(hamster.getResourceLocation());
			this.modelHamster.render(te);
			GlStateManager.popMatrix();
		}
		GlStateManager.popMatrix();
	}

	@Override
	public void setRendererDispatcher(BlockEntityRendererDispatcher rendererDispatcherIn)
	{
		super.setRendererDispatcher(rendererDispatcherIn);
		BlockEntityHamsterWheelRenderer.instance = this;
	}
}