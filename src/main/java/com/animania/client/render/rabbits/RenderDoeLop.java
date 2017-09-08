package com.animania.client.render.rabbits;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.animania.client.models.rabbits.ModelLop;
import com.animania.client.render.rabbits.RenderDoeLop.Factory;
import com.animania.common.entities.rodents.rabbits.EntityRabbitDoeLop;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderDoeLop<T extends EntityRabbitDoeLop> extends RenderLiving<T>
{
	public static final Factory             FACTORY        = new Factory();
	private static final String             modid          = "animania", rabbitBaseDir = "textures/entity/rabbits/";

	private static final ResourceLocation[] RABBIT_TEXTURES = new ResourceLocation[] { 
			new ResourceLocation(RenderDoeLop.modid, RenderDoeLop.rabbitBaseDir + "rabbit_lop_" + "black.png"), 
			new ResourceLocation(RenderDoeLop.modid, RenderDoeLop.rabbitBaseDir +"rabbit_lop_" + "brown.png"), 
			new ResourceLocation(RenderDoeLop.modid, RenderDoeLop.rabbitBaseDir +"rabbit_lop_" + "golden.png"), 
			new ResourceLocation(RenderDoeLop.modid, RenderDoeLop.rabbitBaseDir +"rabbit_lop_" + "olive.png"), 
			new ResourceLocation(RenderDoeLop.modid, RenderDoeLop.rabbitBaseDir +"rabbit_lop_" + "patch_black.png"), 
			new ResourceLocation(RenderDoeLop.modid, RenderDoeLop.rabbitBaseDir +"rabbit_lop_" + "patch_brown.png"), 
			new ResourceLocation(RenderDoeLop.modid, RenderDoeLop.rabbitBaseDir +"rabbit_lop_" + "patch_grey.png")}; 

	private static final ResourceLocation[] RABBIT_TEXTURES_BLINK = new ResourceLocation[] { 
			new ResourceLocation(RenderDoeLop.modid, RenderDoeLop.rabbitBaseDir + "rabbit_lop_" + "black_blink.png"), 
			new ResourceLocation(RenderDoeLop.modid, RenderDoeLop.rabbitBaseDir +"rabbit_lop_" + "brown_blink.png"), 
			new ResourceLocation(RenderDoeLop.modid, RenderDoeLop.rabbitBaseDir +"rabbit_lop_" + "golden_blink.png"), 
			new ResourceLocation(RenderDoeLop.modid, RenderDoeLop.rabbitBaseDir +"rabbit_lop_" + "olive_blink.png"), 
			new ResourceLocation(RenderDoeLop.modid, RenderDoeLop.rabbitBaseDir +"rabbit_lop_" + "patch_black_blink.png"), 
			new ResourceLocation(RenderDoeLop.modid, RenderDoeLop.rabbitBaseDir +"rabbit_lop_" + "patch_brown_blink.png"), 
			new ResourceLocation(RenderDoeLop.modid, RenderDoeLop.rabbitBaseDir +"rabbit_lop_" + "patch_grey_blink.png")}; 

	public RenderDoeLop(RenderManager rm) {
		super(rm, new ModelLop(), 0.25F);
	}
	
	protected void preRenderScale(EntityRabbitDoeLop entity, float f) {
		GL11.glScalef(0.51F, 0.51F, 0.51F);
		GL11.glTranslatef(0f, 0f, -0.5f);
	}

	@Override
	protected void preRenderCallback(T entityliving, float f) {
		this.preRenderScale(entityliving, f);
	}

	@Override
	protected ResourceLocation getEntityTexture(T entity) {
		int blinkTimer = entity.blinkTimer;

		if (blinkTimer < 7 && blinkTimer >= 0)
			return RenderDoeLop.RABBIT_TEXTURES_BLINK[entity.getColorNumber()];
		else
			return RenderDoeLop.RABBIT_TEXTURES[entity.getColorNumber()];

	}

	static class Factory<T extends EntityRabbitDoeLop> implements IRenderFactory<T>
	{
		@Override
		public Render<? super T> createRenderFor(RenderManager manager) {
			return new RenderDoeLop(manager);
		}

	}
}
