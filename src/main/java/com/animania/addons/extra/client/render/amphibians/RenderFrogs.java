package com.animania.addons.extra.client.render.amphibians;

import java.util.Calendar;

import org.lwjgl.opengl.GL11;

import com.animania.addons.extra.client.model.amphibians.ModelFrog;
import com.animania.addons.extra.common.entity.amphibians.EntityFrogs;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Dist.CLIENT)
public class RenderFrogs<T extends EntityFrogs> extends RenderLiving<T>
{// RenderPlayer
	public static final Factory FACTORY = new Factory();

	// Need to move in main class
	private static final String modid = "animania", frogsBaseDir = "textures/entity/amphibians/frogs/";
	private static final ResourceLocation PEPE_TEXTURE = new ResourceLocation("animania:textures/entity/amphibians/frogs/pepe_frog.png");
	private static final ResourceLocation[] FROGS_TEXTURES = new ResourceLocation[] { new ResourceLocation(RenderFrogs.modid, RenderFrogs.frogsBaseDir + "default_frog.png"), new ResourceLocation(RenderFrogs.modid, RenderFrogs.frogsBaseDir + "green_frog.png") };
	private static final ResourceLocation WEDNESDAY_TEXTURE = new ResourceLocation("animania:textures/misc/entities/frog_white.png");

	public RenderFrogs(RenderManager rendermanagerIn)
	{
		super(rendermanagerIn, new ModelFrog(), 0.05F);
	}

	/**
	 * Allows the render to do state modifications necessary before the model is
	 * rendered.
	 */
	@Override
	protected void preRenderCallback(T entityIn, float partialTickTime)
	{

		if (entityIn.getCustomNameTag().equals("Pepe"))
		{
			GlStateManager.scale(0.5D, 0.5D, 0.5D);
		}
		else
		{
			GlStateManager.scale(0.3D, 0.3D, 0.3D);
		}

		float f1 = 1.2F;
		float f2 = (entityIn.prevSquishFactor + (entityIn.squishFactor - entityIn.prevSquishFactor) * partialTickTime) / (f1 * 0.5F + 1.0F);
		float f3 = 1.0F / (f2 + 1.0F);
		GL11.glScalef(f3 * f1, 1.0F / f3 * f1, f3 * f1);

	}

	@Override
	protected ResourceLocation getEntityTexture(T entity)
	{

		if (entity.posX == -1 && entity.posY == -1 && entity.posZ == -1)
		{
			return FROGS_TEXTURES[0];
		}
		else
		{
			if (entity.getCustomNameTag().equals("Pepe"))
				return RenderFrogs.PEPE_TEXTURE;
			else if(entity.getCustomNameTag().equalsIgnoreCase("me_irl") && Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY)
				return WEDNESDAY_TEXTURE;
			else
				switch (entity.getFrogsType())
				{
				case 0:
				default:
					return RenderFrogs.FROGS_TEXTURES[0];
				case 1:
					return RenderFrogs.FROGS_TEXTURES[1];
				}
		}
	}

	public static class Factory<T extends EntityFrogs> implements IRenderFactory<T>
	{
		@Override
		public Render<? super T> createRenderFor(RenderManager manager)
		{
			return new RenderFrogs(manager);
		}
	}
}
