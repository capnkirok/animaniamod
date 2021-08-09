package com.animania.addons.extra.client.render.tileentity;

import com.animania.Animania;
import com.animania.addons.extra.common.entity.rodents.EntityHamster;
import com.animania.addons.extra.common.tileentity.TileEntityHamsterWheel;
import com.leviathanstudio.craftstudio.client.model.ModelCraftStudio;
import com.leviathanstudio.craftstudio.common.animation.simpleImpl.CSTileEntitySpecialRenderer;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.GlStateManager.DestFactor;
import com.mojang.blaze3d.platform.GlStateManager.SourceFactor;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Dist.CLIENT)
public class TileEntityHamsterWheelRenderer extends TileEntitySpecialRenderer<TileEntityHamsterWheel>
{
	private static final ResourceLocation WHEEL_TEXTURE = new ResourceLocation("animania:textures/entity/tileentities/hamster_wheel.png");

	private static final ResourceLocation HAMSTER_TEXTURES = new ResourceLocation("animania:textures/entity/rodents/hamster_tarou.png");
	
	private ModelCraftStudio modelWheel = new ModelCraftStudio(Animania.MODID, "model_hamster_wheel", 64, 32);
	private ModelCraftStudio modelHamster = new ModelCraftStudio(Animania.MODID, "hamster", 64, 32);

	public static TileEntityHamsterWheelRenderer  instance;
		
	@Override
	public void render(TileEntityHamsterWheel te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		EnumFacing enumfacing = EnumFacing.getFront(te.getBlockMetadata() & 7);
		
		GlStateManager.pushMatrix();
        GlStateManager.translate(x + 0.5D, y + 1.5D, z + 0.5D);
        GlStateManager.multMatrix(CSTileEntitySpecialRenderer.ROTATION_CORRECTOR);
        GlStateManager.rotate(enumfacing.getHorizontalAngle(), 0, 1, 0);
        GlStateManager.enableAlpha();
		GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
        this.bindTexture(WHEEL_TEXTURE);
        this.modelWheel.render(te);
        EntityHamster hamster = te.getHamster();
        if (hamster != null){
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
	public void setRendererDispatcher(TileEntityRendererDispatcher rendererDispatcherIn) {
		super.setRendererDispatcher(rendererDispatcherIn);
		TileEntityHamsterWheelRenderer.instance = this;
	}
}