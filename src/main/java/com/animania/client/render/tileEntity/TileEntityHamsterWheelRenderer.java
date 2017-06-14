package com.animania.client.render.tileEntity;

import com.animania.Animania;
import com.animania.common.entities.rodents.EntityHamster;
import com.animania.common.tileentities.TileEntityHamsterWheel;
import com.leviathanstudio.craftstudio.client.model.ModelCraftStudio;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;

public class TileEntityHamsterWheelRenderer<T extends TileEntityHamsterWheel> extends TileEntitySpecialRenderer<T> {
	private ModelCraftStudio modelWheel = new ModelCraftStudio(Animania.MODID, "model_hamster_wheel", 64, 32);
	private ModelCraftStudio modelHamster = new ModelCraftStudio(Animania.MODID, "hamster", 64, 32);
	
    @Override
    public void renderTileEntityAt(T te, double x, double y, double z, float partialTicks, int destroyStage) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x + 0.5D, y + 1.5D, z + 0.5D);
        GlStateManager.rotate(180F, 0, 0, 1);
        GlStateManager.rotate(180F, 0, 1, 0);
        this.bindTexture(new ResourceLocation(Animania.MODID, "textures/blocks/hamster_wheel.png"));
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
}
