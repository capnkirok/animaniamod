package com.animania.client.render.rabbits;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.animania.client.models.rabbits.ModelChinchilla;
import com.animania.common.entities.chickens.EntityAnimaniaChicken;
import com.animania.common.entities.rodents.rabbits.EntityAnimaniaRabbit;
import com.animania.common.entities.rodents.rabbits.EntityRabbitBuckChinchilla;
import com.animania.common.handler.BlockHandler;

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
public class RenderBuckChinchilla<T extends EntityRabbitBuckChinchilla> extends RenderLiving<T>
{
	public static final Factory           FACTORY          = new Factory();
	private static final ResourceLocation rabbitTextures      = new ResourceLocation("animania:textures/entity/rabbits/rabbit_chinchilla.png");
	private static final ResourceLocation rabbitTexturesBlink = new ResourceLocation("animania:textures/entity/rabbits/rabbit_chinchilla_blink.png");
	Random                                rand             = new Random();

	public RenderBuckChinchilla(RenderManager rm) {
		super(rm, new ModelChinchilla(), 0.25F);
	}

	protected ResourceLocation getRabbitTextures(T par1EntityCow) {
		return RenderBuckChinchilla.rabbitTextures;
	}

	protected ResourceLocation getRabbitTexturesBlink(T par1EntityCow) {
		return RenderBuckChinchilla.rabbitTexturesBlink;
	}

	protected void preRenderScale(EntityRabbitBuckChinchilla entity, float f) {
		GL11.glScalef(0.57F, 0.57F, 0.57F);
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
			return this.rabbitTexturesBlink;
		} else if (blinkTimer < 7 && blinkTimer >= 0) {
			return this.rabbitTexturesBlink;
		} else {
			return this.rabbitTextures;
		}
	}
	
	static class Factory<T extends EntityRabbitBuckChinchilla> implements IRenderFactory<T>
	{
		@Override
		public Render<? super T> createRenderFor(RenderManager manager) {
			return new RenderBuckChinchilla(manager);
		}

	}
}
