package com.animania.client.render.sheep;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.animania.client.models.sheep.ModeAdultFriesian;
import com.animania.client.render.sheep.RenderEweFriesian.Factory;
import com.animania.common.entities.sheep.EntityRamFriesian;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderRamFriesian<T extends EntityRamFriesian> extends RenderLiving<T>
{
	public static final Factory           FACTORY          = new Factory();
	private static final ResourceLocation sheepTextures      = new ResourceLocation("animania:textures/entity/sheep/sheep_friesian_ram.png");
	private static final ResourceLocation sheepTexturesBlink = new ResourceLocation("animania:textures/entity/sheep/sheep_friesian_ram_blink.png"); 
	private static final ResourceLocation sheepTexturesSheared = new ResourceLocation("animania:textures/entity/sheep/sheep_friesian_ram_sheared.png");
	private static final ResourceLocation sheepTexturesShearedBlink = new ResourceLocation("animania:textures/entity/sheep/sheep_friesian_ram_sheared_blink.png");

	Random                                rand             = new Random();

	public RenderRamFriesian(RenderManager rm) {
		super(rm, new ModeAdultFriesian(), 0.5F);
	}

	protected ResourceLocation getsheepTextures(T entity) {
		if (entity.getSheared()) {
			return RenderRamFriesian.sheepTexturesSheared;
		} else {
			return RenderRamFriesian.sheepTextures;
		}
	}

	protected ResourceLocation getsheepTexturesBlink(T entity) {
		if (entity.getSheared()) {
			return RenderRamFriesian.sheepTexturesShearedBlink;
		} else {
			return RenderRamFriesian.sheepTexturesBlink;
		}
	}

	protected void preRenderScale(EntityRamFriesian entity, float f) {
		GL11.glScalef(0.64F, 0.64F, 0.64F);
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
			return this.getsheepTexturesBlink(entity);
		else
			return this.getsheepTextures(entity);
	}

	static class Factory<T extends EntityRamFriesian> implements IRenderFactory<T>
	{
		@Override
		public Render<? super T> createRenderFor(RenderManager manager) {
			return new RenderRamFriesian(manager);
		}

	}
}
