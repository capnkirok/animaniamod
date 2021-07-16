package com.animania.addons.farm.client.render.horses;

import org.lwjgl.opengl.GL11;

import com.animania.addons.farm.client.model.horse.ModelDraftHorseStallion;
import com.animania.addons.farm.common.entity.horses.EntityAnimaniaHorse;
import com.animania.addons.farm.common.entity.horses.HorseDraft.EntityStallionDraftHorse;
import com.animania.client.render.layer.LayerBlinking;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Dist.CLIENT)
public class RenderStallionDraftHorse<T extends EntityStallionDraftHorse> extends RenderLiving<T>
{
	public static final Factory FACTORY = new Factory();
	private static final String modid = "animania", horseBaseDir = "textures/entity/horses/";

	private static final ResourceLocation[] HORSE_TEXTURES = new ResourceLocation[] { new ResourceLocation(RenderStallionDraftHorse.modid, RenderStallionDraftHorse.horseBaseDir + "draft_horse_" + "black.png"), new ResourceLocation(RenderStallionDraftHorse.modid, RenderStallionDraftHorse.horseBaseDir + "draft_horse_" + "bw1.png"), new ResourceLocation(RenderStallionDraftHorse.modid, RenderStallionDraftHorse.horseBaseDir + "draft_horse_" + "bw2.png"), new ResourceLocation(RenderStallionDraftHorse.modid, RenderStallionDraftHorse.horseBaseDir + "draft_horse_" + "grey.png"), new ResourceLocation(RenderStallionDraftHorse.modid, RenderStallionDraftHorse.horseBaseDir + "draft_horse_" + "red.png"), new ResourceLocation(RenderStallionDraftHorse.modid, RenderStallionDraftHorse.horseBaseDir + "draft_horse_" + "white.png") };

	private static ResourceLocation BLINK = new ResourceLocation("animania:textures/entity/horses/horse_blink.png");

	private static final int[] BLINK_COLORS = new int[] { 0x1B1B1B, 0x181818, 0x171717, 0x797979, 0x8F3514, 0xC1C1C1 };

	private LayerBlinking blinkingLayer;

	public RenderStallionDraftHorse(RenderManager rm)
	{
		super(rm, new ModelDraftHorseStallion(), 0.8F);
		this.addLayer(blinkingLayer = new LayerBlinking(this, BLINK, 0));
	}

	protected void preRenderScale(EntityStallionDraftHorse entity, float f)
	{
		GL11.glScalef(0.85F, 0.85F, 0.85F);
		EntityAnimaniaHorse HorseEntity = entity;
		if (HorseEntity.getSleeping())
		{
			this.shadowSize = 0F;
			float sleepTimer = HorseEntity.getSleepTimer();
			if (sleepTimer > -0.55F)
			{
				sleepTimer = sleepTimer - 0.01F;
			}
			entity.setSleepTimer(sleepTimer);

			GlStateManager.translate(-0.25F, entity.height - 1.95F - sleepTimer, -0.25F);
			GlStateManager.rotate(6.0F, 0.0F, 0.0F, 1.0F);
		} else
		{
			this.shadowSize = 0.8F;
		}

	}

	@Override
	protected ResourceLocation getEntityTexture(EntityStallionDraftHorse entity)
	{
		if (entity.getX() == -1 && entity.getY() == -1 && entity.getZ() == -1)
		{
			return RenderStallionDraftHorse.HORSE_TEXTURES[0];
		}

		return RenderStallionDraftHorse.HORSE_TEXTURES[entity.getColorNumber()];
	}

	@Override
	protected void preRenderCallback(EntityStallionDraftHorse LivingEntity, float f)
	{
		preRenderScale(LivingEntity, f);
		blinkingLayer.setColors(BLINK_COLORS[LivingEntity.getColorNumber()], BLINK_COLORS[LivingEntity.getColorNumber()]);
	}

	static class Factory<T extends EntityStallionDraftHorse> implements IRenderFactory<T>
	{
		@Override
		public Render<? super T> createRenderFor(RenderManager manager)
		{
			return new RenderStallionDraftHorse(manager);
		}
	}
}
