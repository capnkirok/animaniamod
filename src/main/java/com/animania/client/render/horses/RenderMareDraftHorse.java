package com.animania.client.render.horses;

import org.lwjgl.opengl.GL11;

import com.animania.client.models.ModelDraftHorseMare;
import com.animania.client.render.amphibians.RenderDartFrogs.Factory;
import com.animania.common.entities.horses.EntityMareDraftHorse;
import com.animania.common.entities.horses.EntityStallionDraftHorse;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@SideOnly(Side.CLIENT)
public class RenderMareDraftHorse<T extends EntityMareDraftHorse> extends RenderLiving<T>
{

	public static final Factory             FACTORY        = new Factory();
	private static final String             modid          = "animania", horseBaseDir = "textures/entity/horses/";

	private static final ResourceLocation[] HORSE_TEXTURES = new ResourceLocation[] { 
			new ResourceLocation(RenderMareDraftHorse.modid, RenderMareDraftHorse.horseBaseDir + "draft_horse_" + "black.png"), 
			new ResourceLocation(RenderMareDraftHorse.modid, RenderMareDraftHorse.horseBaseDir +"draft_horse_" + "bw1.png"), 
			new ResourceLocation(RenderMareDraftHorse.modid, RenderMareDraftHorse.horseBaseDir +"draft_horse_" + "bw2.png"), 
			new ResourceLocation(RenderMareDraftHorse.modid, RenderMareDraftHorse.horseBaseDir +"draft_horse_" + "grey.png"), 
			new ResourceLocation(RenderMareDraftHorse.modid, RenderMareDraftHorse.horseBaseDir +"draft_horse_" + "red.png"), 
			new ResourceLocation(RenderMareDraftHorse.modid, RenderMareDraftHorse.horseBaseDir +"draft_horse_" + "white.png")}; 

	private static final ResourceLocation[] HORSE_TEXTURES_BLINK = new ResourceLocation[] { 
			new ResourceLocation(RenderMareDraftHorse.modid, RenderMareDraftHorse.horseBaseDir + "draft_horse_" + "black_blink.png"), 
			new ResourceLocation(RenderMareDraftHorse.modid, RenderMareDraftHorse.horseBaseDir +"draft_horse_" + "bw1_blink.png"), 
			new ResourceLocation(RenderMareDraftHorse.modid, RenderMareDraftHorse.horseBaseDir +"draft_horse_" + "bw2_blink.png"), 
			new ResourceLocation(RenderMareDraftHorse.modid, RenderMareDraftHorse.horseBaseDir +"draft_horse_" + "grey_blink.png"), 
			new ResourceLocation(RenderMareDraftHorse.modid, RenderMareDraftHorse.horseBaseDir +"draft_horse_" + "red_blink.png"), 
			new ResourceLocation(RenderMareDraftHorse.modid, RenderMareDraftHorse.horseBaseDir +"draft_horse_" + "white_blink.png")}; 

	public RenderMareDraftHorse(RenderManager rm)
	{
		super(rm, new ModelDraftHorseMare(), 0.8F);
	}

	protected void preRenderScale(EntityMareDraftHorse entity, float f)
	{
		GL11.glScalef(0.72F, 0.72F, 0.72F); 
	}

	@Override
	protected void preRenderCallback(EntityMareDraftHorse entityliving, float f)
	{
		preRenderScale((EntityMareDraftHorse)entityliving, f);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityMareDraftHorse entity) {

		int blinkTimer = entity.blinkTimer;

		if (blinkTimer < 5 && blinkTimer >= 0)
			return RenderMareDraftHorse.HORSE_TEXTURES_BLINK[entity.getColorNumber()];
		else
			return RenderMareDraftHorse.HORSE_TEXTURES[entity.getColorNumber()];



	}

	static class Factory<T extends EntityMareDraftHorse> implements IRenderFactory<T>
	{
		@Override
		public Render<? super T> createRenderFor(RenderManager manager) {
			return new RenderMareDraftHorse(manager);
		}
	}
}
