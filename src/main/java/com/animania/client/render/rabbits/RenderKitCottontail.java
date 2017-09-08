package com.animania.client.render.rabbits;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.animania.client.models.rabbits.ModelCottontail;
import com.animania.common.entities.rodents.rabbits.EntityRabbitKitCottontail;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderKitCottontail<T extends EntityRabbitKitCottontail> extends RenderLiving<T>
{
	public static final Factory           FACTORY          = new Factory();
	private static final ResourceLocation rabbitTextures      = new ResourceLocation("animania:textures/entity/rabbits/rabbit_cottontail.png");
	private static final ResourceLocation rabbitTexturesBlink = new ResourceLocation("animania:textures/entity/rabbits/rabbit_cottontail_blink.png");
	Random                                rand             = new Random();

	public RenderKitCottontail(RenderManager rm) {
		super(rm, new ModelCottontail(), 0.25F);
	}

	protected ResourceLocation getRabbitTextures(T par1EntityCow) {
		return RenderKitCottontail.rabbitTextures;
	}

	protected ResourceLocation getRabbitTexturesBlink(T par1EntityCow) {
		return RenderKitCottontail.rabbitTexturesBlink;
	}

	protected void preRenderScale(EntityRabbitKitCottontail entity, float f) {
		float age = entity.getEntityAge();
		GL11.glScalef(0.26F + age, 0.26F + age, 0.26F + age); 
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

	static class Factory<T extends EntityRabbitKitCottontail> implements IRenderFactory<T>
	{
		@Override
		public Render<? super T> createRenderFor(RenderManager manager) {
			return new RenderKitCottontail(manager);
		}

	}
}
