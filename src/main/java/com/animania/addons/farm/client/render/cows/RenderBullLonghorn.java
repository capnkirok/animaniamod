package com.animania.addons.farm.client.render.cows;

import org.lwjgl.opengl.GL11;

import com.animania.addons.farm.common.entity.cows.CowLonghorn.EntityBullLonghorn;
import com.animania.addons.farm.client.model.cow.ModelBullLonghorn;
import com.animania.addons.farm.common.entity.cows.EntityAnimaniaCow;
import com.animania.client.render.layer.LayerBlinking;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Dist.CLIENT)
public class RenderBullLonghorn<T extends EntityBullLonghorn> extends RenderLiving<T>
{
	public static final Factory FACTORY = new Factory();

	private static final ResourceLocation cowTextures = new ResourceLocation("animania:textures/entity/cows/bull_longhorn.png");
	private static final ResourceLocation cowTexturesBlink = new ResourceLocation("animania:textures/entity/cows/bull_blink.png");

	public RenderBullLonghorn(RenderManager rm)
	{
		super(rm, new ModelBullLonghorn(), 0.5F);
		addLayer(new LayerBlinking(this, cowTexturesBlink, 0xDEDEDE));
	}

	protected ResourceLocation getCowTextures(T par1CowEntity)
	{
		return RenderBullLonghorn.cowTextures;
	}

	protected ResourceLocation getCowTexturesBlink(T par1CowEntity)
	{
		return RenderBullLonghorn.cowTexturesBlink;
	}

	protected void preRenderScale(T entity, float f)
	{
		GL11.glScalef(1.5F, 1.5F, 1.5F);
		EntityAnimaniaCow CowEntity = (EntityAnimaniaCow) entity;
		if (CowEntity.getSleeping())
		{
			float sleepTimer = CowEntity.getSleepTimer();
			if (sleepTimer > -0.55F)
			{
				sleepTimer = sleepTimer - 0.01F;
			}
			entity.setSleepTimer(sleepTimer);

			GlStateManager.translate(-0.25F, entity.height - 1.85F - sleepTimer, -0.25F);
			GlStateManager.rotate(6.0F, 0.0F, 0.0F, 1.0F);
		}
		else
		{
			CowEntity.setSleeping(false);
			CowEntity.setSleepTimer(0F);
		}

	}

	@Override
	protected ResourceLocation getEntityTexture(T entity)
	{
		return this.getCowTextures(entity);

	}

	@Override
	protected void preRenderCallback(T LivingEntity, float f)
	{
		this.preRenderScale(LivingEntity, f);
	}

	static class Factory<T extends EntityBullLonghorn> implements IRenderFactory<T>
	{
		@Override
		public Render<? super T> createRenderFor(RenderManager manager)
		{
			return new RenderBullLonghorn(manager);
		}
	}
}
