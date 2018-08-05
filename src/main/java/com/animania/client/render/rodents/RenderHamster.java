package com.animania.client.render.rodents;

import org.lwjgl.opengl.GL11;

import com.animania.client.models.ModelHamster;
import com.animania.common.entities.rodents.EntityHamster;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderHamster<T extends EntityHamster> extends RenderLiving<T>
{
	public static final Factory FACTORY = new Factory();
	private static final String             modid          = "animania", hamsterBaseDir = "textures/entity/rodents/";
	private static final ResourceLocation[] HAMSTER_TEXTURES = new ResourceLocation[] { 
			new ResourceLocation(RenderHamster.modid, RenderHamster.hamsterBaseDir + "hamster_" + "black.png"), 
			new ResourceLocation(RenderHamster.modid, RenderHamster.hamsterBaseDir +"hamster_" + "brown.png"), 
			new ResourceLocation(RenderHamster.modid, RenderHamster.hamsterBaseDir +"hamster_" + "darkbrown.png"), 
			new ResourceLocation(RenderHamster.modid, RenderHamster.hamsterBaseDir +"hamster_" + "darkgray.png"), 
			new ResourceLocation(RenderHamster.modid, RenderHamster.hamsterBaseDir +"hamster_" + "gray.png"), 
			new ResourceLocation(RenderHamster.modid, RenderHamster.hamsterBaseDir +"hamster_" + "plum.png"), 
			new ResourceLocation(RenderHamster.modid, RenderHamster.hamsterBaseDir +"hamster_" + "tarou.png"), 
			new ResourceLocation(RenderHamster.modid, RenderHamster.hamsterBaseDir +"hamster_" + "white.png"), 
			new ResourceLocation(RenderHamster.modid, RenderHamster.hamsterBaseDir +"hamster_" + "gold.png")}; 

	private static final ResourceLocation[] HAMSTER_TEXTURES_BLINK = new ResourceLocation[] { 
			new ResourceLocation(RenderHamster.modid, RenderHamster.hamsterBaseDir + "hamster_" + "black_blink.png"), 
			new ResourceLocation(RenderHamster.modid, RenderHamster.hamsterBaseDir +"hamster_" + "brown_blink.png"), 
			new ResourceLocation(RenderHamster.modid, RenderHamster.hamsterBaseDir +"hamster_" + "darkbrown_blink.png"), 
			new ResourceLocation(RenderHamster.modid, RenderHamster.hamsterBaseDir +"hamster_" + "darkgray_blink.png"), 
			new ResourceLocation(RenderHamster.modid, RenderHamster.hamsterBaseDir +"hamster_" + "gray_blink.png"), 
			new ResourceLocation(RenderHamster.modid, RenderHamster.hamsterBaseDir +"hamster_" + "plum_blink.png"), 
			new ResourceLocation(RenderHamster.modid, RenderHamster.hamsterBaseDir +"hamster_" + "tarou_blink.png"), 
			new ResourceLocation(RenderHamster.modid, RenderHamster.hamsterBaseDir +"hamster_" + "white_blink.png"), 
			new ResourceLocation(RenderHamster.modid, RenderHamster.hamsterBaseDir +"hamster_" + "gold_blink.png")}; 


	private float               scale;
	protected ModelHamster      modelHamsterMain;

	public RenderHamster(RenderManager rm) {
		super(rm, new ModelHamster(), 0.25F);
		this.modelHamsterMain = new ModelHamster();
		this.scale = 0.5F;
		this.shadowSize = 0.15F;

	}

	protected void preRenderScale(EntityHamster entityliving, float f) {
		GL11.glScalef(this.scale * .8F, this.scale * .8F, this.scale * .8F);

		if (entityliving.isRiding()) {
			if (entityliving.getRidingEntity() instanceof EntityPlayerSP) {

				EntityPlayer player = (EntityPlayer) entityliving.getRidingEntity();
				entityliving.rotationYaw = player.rotationYaw;

				if (player.isSneaking()) {
					GlStateManager.translate(-0.85F, entityliving.height - .07F, -0.1F);

				} else {
					GlStateManager.translate(-0.85F, entityliving.height - .17F, -0.1F);
				}
			}
		} else if (entityliving.getSleeping()) {
			GlStateManager.translate(0F, 0.15F, 0F); 
			GlStateManager.rotate(20.0F, 0.0F, 0.0F, 1.0F);
		}
	}


	@Override
	protected void preRenderCallback(EntityHamster entityliving, float f) {
		preRenderScale((EntityHamster)entityliving, f);
	}

	@Override
	protected ResourceLocation getEntityTexture(T entity) {
		int blinkTimer = entity.blinkTimer;
		boolean isSleeping = false;

		EntityHamster entityChk = (EntityHamster) entity;
		isSleeping = entityChk.getSleeping();
		float sleepTimer = entityChk.getSleepTimer();

		if (isSleeping) {
			return RenderHamster.HAMSTER_TEXTURES_BLINK[entity.getColorNumber()];
		} else if (blinkTimer < 7 && blinkTimer >= 0) {
			return RenderHamster.HAMSTER_TEXTURES_BLINK[entity.getColorNumber()];
		} else {
			return RenderHamster.HAMSTER_TEXTURES[entity.getColorNumber()];
		}
	}

	static class Factory<T extends EntityHamster> implements IRenderFactory<T>
	{
		@Override
		public Render<? super T> createRenderFor(RenderManager manager) {
			return new RenderHamster(manager);
		}
	}
}


