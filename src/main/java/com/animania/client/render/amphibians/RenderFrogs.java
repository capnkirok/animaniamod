package com.animania.client.render.amphibians;

import com.animania.client.models.ModelFrog;
import com.animania.common.entities.amphibians.EntityFrogs;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderFrogs<T extends EntityFrogs> extends RenderLiving<T> {// RenderPlayer
	public static final Factory FACTORY = new Factory();

	// Need to move in main class
	private static final String modid = "animania", frogsBaseDir = "textures/entity/amphibians/frogs/";
	private static final ResourceLocation[] FROGS_TEXTURES = new ResourceLocation[] {
			new ResourceLocation(modid, frogsBaseDir + "default_frog.png"),
			new ResourceLocation(modid, frogsBaseDir + "green_frog.png"),
			new ResourceLocation(modid, frogsBaseDir + "red_tree_frog.png"),
			new ResourceLocation(modid, frogsBaseDir + "blue_tree_frog.png"),
			new ResourceLocation(modid, frogsBaseDir + "yellow_tree_frog.png") };

	public RenderFrogs(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelFrog(), 0.05F);
	}

	/**
	 * Allows the render to do state modifications necessary before the model is
	 * rendered.
	 */
	@Override
	protected void preRenderCallback(T entityIn, float partialTickTime) {
		GlStateManager.scale(0.5D, 0.5D, 0.5D);
	}

	@Override
	protected ResourceLocation getEntityTexture(T entity) {
		switch (entity.getFrogsType()) {
		case 0:
		default:
			return FROGS_TEXTURES[0];
		case 1:
			return FROGS_TEXTURES[1];
		case 2:
			return FROGS_TEXTURES[2];
		case 3:
			return FROGS_TEXTURES[3];
		case 4:
			return FROGS_TEXTURES[4];
		case 5:
			return FROGS_TEXTURES[5];
		}
	}

	public static class Factory<T extends EntityFrogs> implements IRenderFactory<T> {
		@Override
		public Render<? super T> createRenderFor(RenderManager manager) {
			return new RenderFrogs(manager);
		}
	}
}
