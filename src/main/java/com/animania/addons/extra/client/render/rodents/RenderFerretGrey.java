package com.animania.addons.extra.client.render.rodents;

import org.lwjgl.opengl.GL11;

import com.animania.addons.extra.client.model.rodents.ModelFerret;
import com.animania.addons.extra.common.entity.rodents.EntityFerretGrey;
import com.animania.client.render.layer.LayerBlinking;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.entity.LocalPlayer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Dist.CLIENT)
public class RenderFerretGrey<T extends EntityFerretGrey> extends RenderLiving<T>
{
	public static final Factory FACTORY = new Factory();

	private static final ResourceLocation FERRET_TEXTURES = new ResourceLocation("animania:textures/entity/rodents/ferret_grey.png");
	private static final ResourceLocation FERRET_TEXTURES_BLINK = new ResourceLocation("animania:textures/entity/rodents/ferret_blink.png");

	public RenderFerretGrey(RenderManager rm)
	{
		super(rm, new ModelFerret(), 0.2F);
		this.addLayer(new LayerBlinking(this, FERRET_TEXTURES_BLINK, 0x58372D));
	}

	protected void preRenderScale(T entity, float f)
	{
		if (entity.isPassenger())
		{

			if (entity.getRidingEntity() instanceof LocalPlayer)
			{
				GL11.glScalef(0.3F, 0.3F, 0.3F);
				Player player = (Player) entity.getRidingEntity();
				entity.rotationYaw = player.rotationYaw;
				if (player.isSneaking())
					GlStateManager.translate(-1.2F, entity.height + .0F, .5F);
				else
					GlStateManager.translate(-1.2F, entity.height - .1F, .5F);
			}

		}
		else if (entity.getSleeping())
		{
			GL11.glScalef(0.5F, 0.5F, 0.5F);
			GlStateManager.translate(0F, 0.20F, 0F);
			GlStateManager.rotate(10.0F, 0.0F, 0.0F, 1.0F);
		}
		else
		{
			GL11.glScalef(0.5F, 0.5F, 0.5F);
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
		return RenderFerretGrey.FERRET_TEXTURES;
	}

	@Override
	public ModelFerret getMainModel()
	{
		return (ModelFerret) super.getMainModel();
	}

	static class Factory<T extends EntityFerretGrey> implements IRenderFactory<T>
	{
		@Override
		public Render<? super T> createRenderFor(RenderManager manager)
		{
			return new RenderFerretGrey(manager);
		}
	}
}