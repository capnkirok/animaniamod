package com.animania.client.render.rabbits;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.animania.client.models.rabbits.ModelChinchilla;
import com.animania.common.entities.rodents.rabbits.EntityRabbitBuckChinchilla;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
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
	}

	@Override
	protected void preRenderCallback(T entityliving, float f) {
		this.preRenderScale(entityliving, f);
	}

	@Override
	protected ResourceLocation getEntityTexture(T entity) {
		int blinkTimer = entity.blinkTimer;

		if (blinkTimer < 7 && blinkTimer >= 0)
			return this.getRabbitTexturesBlink(entity);
		else
			return this.getRabbitTextures(entity);
	}

	static class Factory<T extends EntityRabbitBuckChinchilla> implements IRenderFactory<T>
	{
		@Override
		public Render<? super T> createRenderFor(RenderManager manager) {
			return new RenderBuckChinchilla(manager);
		}

	}
}
