package com.animania.addons.farm.client.render.sheep;

import org.lwjgl.opengl.GL11;

import com.animania.addons.farm.client.model.sheep.ModelSuffolkEwe;
import com.animania.addons.farm.common.entity.sheep.EntityAnimaniaSheep;
import com.animania.addons.farm.common.entity.sheep.SheepSuffolk.EntityLambSuffolk;
import com.animania.client.render.layer.LayerBlinking;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Dist.CLIENT)
public class RenderLambSuffolk<T extends EntityLambSuffolk> extends RenderLiving<T>
{
	public static final Factory FACTORY = new Factory();
	private static final String modid = "animania", SheepBaseDir = "textures/entity/sheep/";

	private static final ResourceLocation[] SHEEP_TEXTURES = { new ResourceLocation(RenderLambSuffolk.modid, RenderLambSuffolk.SheepBaseDir + "sheep_suffolk_" + "white_ewe.png"), new ResourceLocation(RenderLambSuffolk.modid, RenderLambSuffolk.SheepBaseDir + "sheep_suffolk_" + "brown_ewe.png") };

	private static final ResourceLocation SHEEP_TEXTURE_BLINK = new ResourceLocation("animania:textures/entity/sheep/sheep_blink.png");
	private static final ResourceLocation[] SHEEP_TEXTURES_SHEARED = { new ResourceLocation(RenderLambSuffolk.modid, RenderLambSuffolk.SheepBaseDir + "sheep_suffolk_" + "white_ewe_sheared.png"), new ResourceLocation(RenderLambSuffolk.modid, RenderLambSuffolk.SheepBaseDir + "sheep_suffolk_" + "brown_ewe_sheared.png") };

	public RenderLambSuffolk(RenderManager rm)
	{
		super(rm, new ModelSuffolkEwe(), 0.5F);
		this.addLayer(new LayerBlinking(this, SHEEP_TEXTURE_BLINK, 0x1D1D1D));
	}

	protected void preRenderScale(EntityLambSuffolk entity, float f)
	{
		GL11.glScalef(0.32F, 0.32F, 0.32F);
		GL11.glTranslatef(0f, 0f, -0.5f);
		EntityAnimaniaSheep SheepEntity = entity;
		if (SheepEntity.getSleeping())
		{
			this.shadowSize = 0;
			float sleepTimer = SheepEntity.getSleepTimer();
			if (sleepTimer > -0.55F)
			{
				sleepTimer = sleepTimer - 0.01F;
			}
			entity.setSleepTimer(sleepTimer);

			GlStateManager.translate(-0.25F, entity.height - .45F - sleepTimer, -0.25F);
			GlStateManager.rotate(6.0F, 0.0F, 0.0F, 1.0F);
		}
		else
		{
			this.shadowSize = 0.5F;
			SheepEntity.setSleeping(false);
			SheepEntity.setSleepTimer(0F);
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(T entity)
	{
		if (entity.getX() == -1 && entity.getY() == -1 && entity.getZ() == -1)
		{
			return SHEEP_TEXTURES[0];
		}

		if (!entity.getSheared())
		{
			return RenderLambSuffolk.SHEEP_TEXTURES[entity.getColorNumber()];
		}
		else
		{
			return RenderLambSuffolk.SHEEP_TEXTURES_SHEARED[entity.getColorNumber()];
		}
	}

	@Override
	protected void preRenderCallback(T LivingEntity, float f)
	{
		this.preRenderScale(LivingEntity, f);
	}

	static class Factory<T extends EntityLambSuffolk> implements IRenderFactory<T>
	{
		@Override
		public Render<? super T> createRenderFor(RenderManager manager)
		{
			return new RenderLambSuffolk(manager);
		}

	}
}
