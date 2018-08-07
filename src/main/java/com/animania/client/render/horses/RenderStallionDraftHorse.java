package com.animania.client.render.horses;

import org.lwjgl.opengl.GL11;

import com.animania.client.models.ModelDraftHorseStallion;
import com.animania.common.entities.horses.EntityAnimaniaHorse;
import com.animania.common.entities.horses.EntityStallionDraftHorse;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@SideOnly(Side.CLIENT)
public class RenderStallionDraftHorse<T extends EntityStallionDraftHorse> extends RenderLiving<T>
{
	public static final Factory             FACTORY        = new Factory();
	private static final String             modid          = "animania", horseBaseDir = "textures/entity/horses/";

	private static final ResourceLocation[] HORSE_TEXTURES = new ResourceLocation[] { 
			new ResourceLocation(RenderStallionDraftHorse.modid, RenderStallionDraftHorse.horseBaseDir + "draft_horse_" + "black.png"), 
			new ResourceLocation(RenderStallionDraftHorse.modid, RenderStallionDraftHorse.horseBaseDir +"draft_horse_" + "bw1.png"), 
			new ResourceLocation(RenderStallionDraftHorse.modid, RenderStallionDraftHorse.horseBaseDir +"draft_horse_" + "bw2.png"), 
			new ResourceLocation(RenderStallionDraftHorse.modid, RenderStallionDraftHorse.horseBaseDir +"draft_horse_" + "grey.png"), 
			new ResourceLocation(RenderStallionDraftHorse.modid, RenderStallionDraftHorse.horseBaseDir +"draft_horse_" + "red.png"), 
			new ResourceLocation(RenderStallionDraftHorse.modid, RenderStallionDraftHorse.horseBaseDir +"draft_horse_" + "white.png")}; 

	private static final ResourceLocation[] HORSE_TEXTURES_BLINK = new ResourceLocation[] { 
			new ResourceLocation(RenderStallionDraftHorse.modid, RenderStallionDraftHorse.horseBaseDir + "draft_horse_" + "black_blink.png"), 
			new ResourceLocation(RenderStallionDraftHorse.modid, RenderStallionDraftHorse.horseBaseDir +"draft_horse_" + "bw1_blink.png"), 
			new ResourceLocation(RenderStallionDraftHorse.modid, RenderStallionDraftHorse.horseBaseDir +"draft_horse_" + "bw2_blink.png"), 
			new ResourceLocation(RenderStallionDraftHorse.modid, RenderStallionDraftHorse.horseBaseDir +"draft_horse_" + "grey_blink.png"), 
			new ResourceLocation(RenderStallionDraftHorse.modid, RenderStallionDraftHorse.horseBaseDir +"draft_horse_" + "red_blink.png"), 
			new ResourceLocation(RenderStallionDraftHorse.modid, RenderStallionDraftHorse.horseBaseDir +"draft_horse_" + "white_blink.png")}; 


	public RenderStallionDraftHorse(RenderManager rm)
	{
		super(rm, new ModelDraftHorseStallion(), 0.8F);
	}

	protected void preRenderScale(EntityStallionDraftHorse entity, float f)
	{
		GL11.glScalef(0.85F, 0.85F, 0.85F); 
		boolean isSleeping = false;
		EntityAnimaniaHorse entityHorse = (EntityAnimaniaHorse) entity;
		if (entityHorse.getSleeping()) {
			isSleeping = true;
		}

		if (isSleeping) {
			this.shadowSize = 0F;
			float sleepTimer = entityHorse.getSleepTimer();
			if (sleepTimer > - 0.55F) {
				sleepTimer = sleepTimer - 0.01F;
			}
			entity.setSleepTimer(sleepTimer);

			GlStateManager.translate(-0.25F, entity.height - 1.95F - sleepTimer, -0.25F);
			GlStateManager.rotate(6.0F, 0.0F, 0.0F, 1.0F);
		} else {
			this.shadowSize = 0.8F;
			entityHorse.setSleeping(false);
			entityHorse.setSleepTimer(0F);
		}

	}

	@Override
	protected ResourceLocation getEntityTexture(EntityStallionDraftHorse entity) {
		int blinkTimer = entity.blinkTimer;
		long currentTime = entity.world.getWorldTime() % 23999;
		boolean isSleeping = false;

		EntityAnimaniaHorse entityHorse = (EntityAnimaniaHorse) entity;
		isSleeping = entityHorse.getSleeping();
		float sleepTimer = entityHorse.getSleepTimer();

		if (isSleeping && sleepTimer <= -0.55F && currentTime < 23250) {
			return RenderStallionDraftHorse.HORSE_TEXTURES_BLINK[entity.getColorNumber()];
		} else if (blinkTimer < 7 && blinkTimer >= 0) {
			return RenderStallionDraftHorse.HORSE_TEXTURES_BLINK[entity.getColorNumber()];
		} else {
			return RenderStallionDraftHorse.HORSE_TEXTURES[entity.getColorNumber()];
		}

	}


	@Override
	protected void preRenderCallback(EntityStallionDraftHorse entityliving, float f)
	{
		preRenderScale((EntityStallionDraftHorse)entityliving, f);
	}

	static class Factory<T extends EntityStallionDraftHorse> implements IRenderFactory<T>
	{
		@Override
		public Render<? super T> createRenderFor(RenderManager manager) {
			return new RenderStallionDraftHorse(manager);
		}
	}
}
