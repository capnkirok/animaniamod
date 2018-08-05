package com.animania.client.render.rabbits;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.animania.client.models.rabbits.ModelLop;
import com.animania.client.render.rabbits.RenderBuckLop.Factory;
import com.animania.common.entities.rodents.rabbits.EntityAnimaniaRabbit;
import com.animania.common.entities.rodents.rabbits.EntityRabbitBuckLop;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBuckLop<T extends EntityRabbitBuckLop> extends RenderLiving<T>
{
	public static final Factory             FACTORY        = new Factory();
	private static final String             modid          = "animania", rabbitBaseDir = "textures/entity/rabbits/";

	private static final ResourceLocation[] RABBIT_TEXTURES = new ResourceLocation[] { 
			new ResourceLocation(RenderBuckLop.modid, RenderBuckLop.rabbitBaseDir + "rabbit_lop_" + "black.png"), 
			new ResourceLocation(RenderBuckLop.modid, RenderBuckLop.rabbitBaseDir +"rabbit_lop_" + "brown.png"), 
			new ResourceLocation(RenderBuckLop.modid, RenderBuckLop.rabbitBaseDir +"rabbit_lop_" + "golden.png"), 
			new ResourceLocation(RenderBuckLop.modid, RenderBuckLop.rabbitBaseDir +"rabbit_lop_" + "olive.png"), 
			new ResourceLocation(RenderBuckLop.modid, RenderBuckLop.rabbitBaseDir +"rabbit_lop_" + "patch_black.png"), 
			new ResourceLocation(RenderBuckLop.modid, RenderBuckLop.rabbitBaseDir +"rabbit_lop_" + "patch_brown.png"), 
			new ResourceLocation(RenderBuckLop.modid, RenderBuckLop.rabbitBaseDir +"rabbit_lop_" + "patch_grey.png")}; 

	private static final ResourceLocation[] RABBIT_TEXTURES_BLINK = new ResourceLocation[] { 
			new ResourceLocation(RenderBuckLop.modid, RenderBuckLop.rabbitBaseDir + "rabbit_lop_" + "black_blink.png"), 
			new ResourceLocation(RenderBuckLop.modid, RenderBuckLop.rabbitBaseDir +"rabbit_lop_" + "brown_blink.png"), 
			new ResourceLocation(RenderBuckLop.modid, RenderBuckLop.rabbitBaseDir +"rabbit_lop_" + "golden_blink.png"), 
			new ResourceLocation(RenderBuckLop.modid, RenderBuckLop.rabbitBaseDir +"rabbit_lop_" + "olive_blink.png"), 
			new ResourceLocation(RenderBuckLop.modid, RenderBuckLop.rabbitBaseDir +"rabbit_lop_" + "patch_black_blink.png"), 
			new ResourceLocation(RenderBuckLop.modid, RenderBuckLop.rabbitBaseDir +"rabbit_lop_" + "patch_brown_blink.png"), 
			new ResourceLocation(RenderBuckLop.modid, RenderBuckLop.rabbitBaseDir +"rabbit_lop_" + "patch_grey_blink.png")}; 
	public RenderBuckLop(RenderManager rm) {
		super(rm, new ModelLop(), 0.25F);
	}

	protected void preRenderScale(EntityRabbitBuckLop entity, float f) {
		GL11.glScalef(0.47F, 0.47F, 0.47F);
		GL11.glTranslatef(0f, 0f, -0.5f);
		double x = entity.posX;
		double y = entity.posY;
		double z = entity.posZ;
		BlockPos pos = new BlockPos(x, y, z);
		Block blockchk = entity.world.getBlockState(pos).getBlock();
		boolean isSleeping = false;
		EntityAnimaniaRabbit entityChk = (EntityAnimaniaRabbit) entity;
		if (entityChk.getSleeping()) {
			isSleeping = true;
		}
		
		if (isSleeping ) {
			this.shadowSize = 0;
			GlStateManager.translate(-.25F, 0.25F, -.25F); 
		} else {
			this.shadowSize = 0.25F;
			entityChk.setSleeping(false);
		}
	}

	@Override
	protected void preRenderCallback(T entityliving, float f) {
		this.preRenderScale(entityliving, f);
	}

	@Override
	protected ResourceLocation getEntityTexture(T entity) {
		int blinkTimer = entity.blinkTimer;
		
		boolean isSleeping = false;

		EntityAnimaniaRabbit entityChk = (EntityAnimaniaRabbit) entity;
		isSleeping = entityChk.getSleeping();
		float sleepTimer = entityChk.getSleepTimer();

		if (isSleeping) {
			return this.RABBIT_TEXTURES_BLINK[entity.getColorNumber()];
		} else if (blinkTimer < 7 && blinkTimer >= 0) {
			return this.RABBIT_TEXTURES_BLINK[entity.getColorNumber()];
		} else {
			return this.RABBIT_TEXTURES[entity.getColorNumber()];
		}
	}
	
	static class Factory<T extends EntityRabbitBuckLop> implements IRenderFactory<T>
	{
		@Override
		public Render<? super T> createRenderFor(RenderManager manager) {
			return new RenderBuckLop(manager);
		}

	}
}
