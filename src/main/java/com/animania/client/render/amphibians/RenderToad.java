package com.animania.client.render.amphibians;

import com.animania.client.models.ModelToad;
import com.animania.common.entities.amphibians.EntityToad;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderToad<T extends EntityToad> extends RenderLiving<T> {
	public static final Factory FACTORY = new Factory();

	// Need to move in main class
	private static final String modid = "animania", toadBaseDir = "textures/entity/amphibians/toads/";
	private static final ResourceLocation TOAD_TEXTURE = new ResourceLocation(modid, toadBaseDir + "toad.png");

	public RenderToad(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelToad(), 0.05F);
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
		return TOAD_TEXTURE;
	}

	public static class Factory<T extends EntityToad> implements IRenderFactory<T> {
		@Override
		public Render<? super T> createRenderFor(RenderManager manager) {
			return new RenderToad(manager);
		}
	}
}
