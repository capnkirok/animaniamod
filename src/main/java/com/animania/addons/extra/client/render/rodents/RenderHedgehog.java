package com.animania.addons.extra.client.render.rodents;

import org.lwjgl.opengl.GL11;

import com.animania.addons.extra.client.model.rodents.ModelHedgehog;
import com.animania.addons.extra.common.entity.rodents.EntityHedgehog;
import com.animania.addons.extra.common.entity.rodents.EntityHedgehogBase;
import com.animania.client.render.layer.LayerBlinking;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.entity.PlayerEntitySP;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Dist.CLIENT)
public class RenderHedgehog<T extends EntityHedgehog> extends RenderLiving<T>
{
	public static final Factory FACTORY = new Factory();

	private static final ResourceLocation HEDGEHOG_TEXTURES = new ResourceLocation("animania:textures/entity/rodents/hedgehog.png");
	private static final ResourceLocation HEDGEpig_blink = new ResourceLocation("animania:textures/entity/rodents/hedgehog_blink.png");
	private static final ResourceLocation SONIC_TEXTURES = new ResourceLocation("animania:textures/entity/rodents/hedgehog_sonic.png");
	private static final ResourceLocation SANIC_TEXTURES = new ResourceLocation("animania:textures/entity/rodents/hedgehog_sanic.png");

	private LayerBlinking blinking;

	public RenderHedgehog(RenderManager rm)
	{
		super(rm, new ModelHedgehog(), 0.2F);
		this.addLayer(this.blinking = new LayerBlinking(this, HEDGEpig_blink, 0xD3CDAB));
	}

	protected void preRenderScale(T entity, float f)
	{
		if (entity.isPassenger())
		{

			if (entity.getRidingEntity() instanceof PlayerEntitySP)
			{
				GL11.glScalef(0.4F, 0.4F, 0.4F);
				PlayerEntity player = (PlayerEntity) entity.getRidingEntity();
				entity.rotationYaw = player.rotationYaw;
				if (player.isSneaking())
					GlStateManager.translate(-1.0F, entity.height - .1F, .1F);
				else
					GlStateManager.translate(-1.0F, entity.height - .2F, .1F);
			}

		}
		else
		{
			GL11.glScalef(0.6F, 0.6F, 0.6F);

			EntityHedgehogBase entityChk = entity;
			if (entity.getCustomNameTag().equals("Sanic"))
			{
				GL11.glRotatef(20, -1, 0, 1);
				GL11.glScalef(1.2F, 1.7F, 1.6F);
			}
			else if (entityChk.getSleeping() || entity.isSitting())
			{
				this.shadowSize = 0;
				GlStateManager.translate(0F, 0.15F, 0F);
			}
		}

	}

	@Override
	protected void preRenderCallback(T LivingEntity, float f)
	{
		this.preRenderScale(LivingEntity, f);
	}

	@Override
	protected ResourceLocation getEntityTexture(T entity)
	{
		if (entity.getCustomNameTag().equals("Sonic"))
		{
			this.blinking.setColors(0xD8AA82, 0xD8AA82);
			return RenderHedgehog.SONIC_TEXTURES;
		}
		else if (entity.getCustomNameTag().equals("Sanic"))
		{
			this.blinking.setColors(0xFFFFFF, 0xFFFFFF);
			return RenderHedgehog.SANIC_TEXTURES;
		}

		this.blinking.setColors(0xD3CDAB, 0xD3CDAB);
		return RenderHedgehog.HEDGEHOG_TEXTURES;
	}

	@Override
	public ModelHedgehog getMainModel()
	{
		return (ModelHedgehog) super.getMainModel();
	}

	static class Factory<T extends EntityHedgehog> implements IRenderFactory<T>
	{
		@Override
		public Render<? super T> createRenderFor(RenderManager manager)
		{
			return new RenderHedgehog(manager);
		}
	}
}