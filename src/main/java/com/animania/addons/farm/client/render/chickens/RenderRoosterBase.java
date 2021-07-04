package com.animania.addons.farm.client.render.chickens;

import org.lwjgl.opengl.GL11;

import com.animania.addons.farm.client.model.chicken.ModelRooster;
import com.animania.addons.farm.common.entity.chickens.EntityAnimaniaChicken;
import com.animania.addons.farm.common.entity.chickens.EntityRoosterBase;
import com.animania.client.render.layer.LayerBlinking;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Dist.CLIENT)
public class RenderRoosterBase<T extends EntityRoosterBase> extends RenderLiving<T>
{
	public static final Factory FACTORY = new Factory();

	private LayerBlinking blinkingLayer;
	
	public RenderRoosterBase(RenderManager rm)
	{
		super(rm, new ModelRooster(), 0.32F);
		this.addLayer(blinkingLayer = new LayerBlinking(this, new ResourceLocation("animania:textures/entity/chickens/chicken_blink.png"), 0));
	}

	@Override
	protected float handleRotationFloat(T livingBase, float partialTicks)
	{
		float f = livingBase.oFlap + (livingBase.wingRotation - livingBase.oFlap) * partialTicks;
		float f1 = livingBase.oFlapSpeed + (livingBase.destPos - livingBase.oFlapSpeed) * partialTicks;
		return (MathHelper.sin(f) + 1.0F) * f1;
	}

	@Override
	protected void preRenderCallback(T LivingEntity, float f)
	{
		this.preRenderScale(LivingEntity, f);
		blinkingLayer.setColors(LivingEntity.lidCol, LivingEntity.lidCol);
	}

	protected void preRenderScale(T entity, float f)
	{
		if (entity.getCustomNameTag().equals("Ducktonio")) {
			GL11.glScalef(1.5F, 1.5F, 1.5F);
		} {
			GL11.glScalef(1.0F, 1.0F, 1.0F);
		}

		EntityAnimaniaChicken entityChk = (EntityAnimaniaChicken) entity;
		if (entityChk.getSleeping())
		{
			GlStateManager.translate(-0.25F, 0.35F, -0.25F);
			this.shadowSize = 0;
		}
		else
		{
			this.shadowSize = 0.32F;
			entityChk.setSleeping(false);
		}

	}

	@Override
	protected ResourceLocation getEntityTexture(T entity)
	{
		
		if (entity.getCustomNameTag().equals("Ducktonio")) {
			return new ResourceLocation("animania:textures/entity/chickens/rooster_antonio.png");
		} else {
			return entity.getResourceLocation();
		}
		
		
	}

	static class Factory<T extends EntityRoosterBase> implements IRenderFactory<T>
	{
		@Override
		public Render<? super T> createRenderFor(RenderManager manager)
		{
			return new RenderRoosterBase(manager);
		}
	}

}
