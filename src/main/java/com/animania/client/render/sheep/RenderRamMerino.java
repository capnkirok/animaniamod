package com.animania.client.render.sheep;

import org.lwjgl.opengl.GL11;

import com.animania.client.models.sheep.ModelMerinoRam;
import com.animania.common.entities.sheep.EntityRamMerino;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderRamMerino<T extends EntityRamMerino> extends RenderLiving<T>
{
	public static final Factory           FACTORY          = new Factory();
	private static final String             modid          = "animania", SheepBaseDir = "textures/entity/sheep/";

	private static final ResourceLocation[] SHEEP_TEXTURES = new ResourceLocation[] { 
			new ResourceLocation(RenderRamMerino.modid, RenderRamMerino.SheepBaseDir +"sheep_merino_" + "white_ram.png"), 
			new ResourceLocation(RenderRamMerino.modid, RenderRamMerino.SheepBaseDir +"sheep_merino_" + "brown_ram.png")}; 

	private static final ResourceLocation[] SHEEP_TEXTURES_BLINK = new ResourceLocation[] { 
			new ResourceLocation(RenderRamMerino.modid, RenderRamMerino.SheepBaseDir +"sheep_merino_" + "white_ram_blink.png"), 
			new ResourceLocation(RenderRamMerino.modid, RenderRamMerino.SheepBaseDir +"sheep_merino_" + "brown_ram_blink.png")}; 

	private static final ResourceLocation[] SHEEP_TEXTURES_SHEARED = new ResourceLocation[] { 
			new ResourceLocation(RenderRamMerino.modid, RenderRamMerino.SheepBaseDir +"sheep_merino_" + "white_ram_sheared.png"), 
			new ResourceLocation(RenderRamMerino.modid, RenderRamMerino.SheepBaseDir +"sheep_merino_" + "brown_ram_sheared.png")}; 

	private static final ResourceLocation[] SHEEP_TEXTURES_SHEARED_BLINK = new ResourceLocation[] { 
			new ResourceLocation(RenderRamMerino.modid, RenderRamMerino.SheepBaseDir +"sheep_merino_" + "white_ram_sheared_blink.png"), 
			new ResourceLocation(RenderRamMerino.modid, RenderRamMerino.SheepBaseDir +"sheep_merino_" + "brown_ram_sheared_blink.png")}; 

	public RenderRamMerino(RenderManager rm) {
		super(rm, new ModelMerinoRam(), 0.5F);
	}

	protected void preRenderScale(EntityRamMerino entity, float f) {
		GL11.glScalef(0.56F, 0.56F, 0.56F);
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
				return RenderRamMerino.SHEEP_TEXTURES_BLINK[entity.getColorNumber()];
			} else {
				return RenderRamMerino.SHEEP_TEXTURES[entity.getColorNumber()];
			}
		} else {
			if (blinkTimer < 7 && blinkTimer >= 0) {
				return RenderRamMerino.SHEEP_TEXTURES_SHEARED_BLINK[entity.getColorNumber()];
			} else {
				return RenderRamMerino.SHEEP_TEXTURES_SHEARED[entity.getColorNumber()];
			}
		}
	}

	static class Factory<T extends EntityRamMerino> implements IRenderFactory<T>
	{
		@Override
		public Render<? super T> createRenderFor(RenderManager manager) {
			return new RenderRamMerino(manager);
		}

	}
}
