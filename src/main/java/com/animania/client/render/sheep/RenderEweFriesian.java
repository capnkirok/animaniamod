package com.animania.client.render.sheep;

import org.lwjgl.opengl.GL11;

import com.animania.client.models.sheep.ModelFriesianSheep;
import com.animania.common.entities.sheep.EntityEweFriesian;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderEweFriesian<T extends EntityEweFriesian> extends RenderLiving<T>
{
	public static final Factory           FACTORY          = new Factory();
	private static final String             modid          = "animania", SheepBaseDir = "textures/entity/sheep/";

	private static final ResourceLocation[] SHEEP_TEXTURES = new ResourceLocation[] { 
			new ResourceLocation(RenderEweFriesian.modid, RenderEweFriesian.SheepBaseDir + "sheep_friesian_" + "black.png"), 
			new ResourceLocation(RenderEweFriesian.modid, RenderEweFriesian.SheepBaseDir +"sheep_friesian_" + "white.png"), 
			new ResourceLocation(RenderEweFriesian.modid, RenderEweFriesian.SheepBaseDir +"sheep_friesian_" + "brown.png")}; 

	private static final ResourceLocation[] SHEEP_TEXTURES_BLINK = new ResourceLocation[] { 
			new ResourceLocation(RenderEweFriesian.modid, RenderEweFriesian.SheepBaseDir + "sheep_friesian_" + "black_blink.png"), 
			new ResourceLocation(RenderEweFriesian.modid, RenderEweFriesian.SheepBaseDir +"sheep_friesian_" + "white_blink.png"), 
			new ResourceLocation(RenderEweFriesian.modid, RenderEweFriesian.SheepBaseDir +"sheep_friesian_" + "brown_blink.png")}; 

	private static final ResourceLocation[] SHEEP_TEXTURES_SHEARED = new ResourceLocation[] { 
			new ResourceLocation(RenderEweFriesian.modid, RenderEweFriesian.SheepBaseDir + "sheep_friesian_" + "black_sheared.png"), 
			new ResourceLocation(RenderEweFriesian.modid, RenderEweFriesian.SheepBaseDir +"sheep_friesian_" + "white_sheared.png"), 
			new ResourceLocation(RenderEweFriesian.modid, RenderEweFriesian.SheepBaseDir +"sheep_friesian_" + "brown_sheared.png")}; 

	private static final ResourceLocation[] SHEEP_TEXTURES_SHEARED_BLINK = new ResourceLocation[] { 
			new ResourceLocation(RenderEweFriesian.modid, RenderEweFriesian.SheepBaseDir + "sheep_friesian_" + "black_sheared_blink.png"), 
			new ResourceLocation(RenderEweFriesian.modid, RenderEweFriesian.SheepBaseDir +"sheep_friesian_" + "white_sheared_blink.png"), 
			new ResourceLocation(RenderEweFriesian.modid, RenderEweFriesian.SheepBaseDir +"sheep_friesian_" + "brown_sheared_blink.png")}; 


	public RenderEweFriesian(RenderManager rm) {
		super(rm, new ModelFriesianSheep(), 0.5F);
	}

	protected void preRenderScale(EntityEweFriesian entity, float f) {
		GL11.glScalef(0.58F, 0.58F, 0.58F);
		GL11.glTranslatef(0f, 0f, -0.5f);
	}

	@Override
	protected void preRenderCallback(T entityliving, float f) {
		this.preRenderScale(entityliving, f);
	}

	@Override
	protected ResourceLocation getEntityTexture(T entity) {
		int blinkTimer = entity.blinkTimer;

		if (!entity.getSheared()) {
			if (blinkTimer < 7 && blinkTimer >= 0) {
				return RenderEweFriesian.SHEEP_TEXTURES_BLINK[entity.getColorNumber()];
			} else {
				return RenderEweFriesian.SHEEP_TEXTURES[entity.getColorNumber()];
			}
		} else {
			if (blinkTimer < 7 && blinkTimer >= 0) {
				return RenderEweFriesian.SHEEP_TEXTURES_SHEARED_BLINK[entity.getColorNumber()];
			} else {
				return RenderEweFriesian.SHEEP_TEXTURES_SHEARED[entity.getColorNumber()];
			}
		}
	}

	static class Factory<T extends EntityEweFriesian> implements IRenderFactory<T>
	{
		@Override
		public Render<? super T> createRenderFor(RenderManager manager) {
			return new RenderEweFriesian(manager);
		}

	}
}
