package com.animania.client.render.sheep;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.animania.client.models.sheep.ModelLambFriesian;
import com.animania.common.entities.sheep.EntityLambFriesian;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderLambFriesian<T extends EntityLambFriesian> extends RenderLiving<T>
{
	public static final Factory           FACTORY          = new Factory();
	private static final ResourceLocation sheepTextures      = new ResourceLocation("animania:textures/entity/sheep/sheep_friesian_ram.png");
    private static final ResourceLocation sheepTexturesBlink = new ResourceLocation("animania:textures/entity/sheep/sheep_friesian_ram_blink.png"); 
    private static final ResourceLocation sheepTexturesSheared = new ResourceLocation("animania:textures/entity/sheep/sheep_ram_sheared.png");
	Random                                rand             = new Random();

	public RenderLambFriesian(RenderManager rm) {
		super(rm, new ModelLambFriesian(), 0.5F);
	}

	protected ResourceLocation getsheepTextures(T par1EntityCow) {
		return RenderLambFriesian.sheepTextures;
	}

	protected ResourceLocation getsheepTexturesBlink(T par1EntityCow) {
		return RenderLambFriesian.sheepTexturesBlink;
	}

	protected void preRenderScale(EntityLambFriesian entity, float f) {
		float age = entity.getEntityAge();
		GL11.glScalef(0.35F + age, 0.35F + age, 0.35F + age); 
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

	static class Factory<T extends EntityLambFriesian> implements IRenderFactory<T>
	{
		@Override
		public Render<? super T> createRenderFor(RenderManager manager) {
			return new RenderLambFriesian(manager);
		}

	}
}
