package com.animania.addons.extra.client.render.rodents;

import org.lwjgl.opengl.GL11;

import com.animania.addons.extra.client.model.rodents.ModelHamster;
import com.animania.addons.extra.common.entity.rodents.EntityHamster;
import com.animania.client.render.layer.LayerBlinking;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.entity.PlayerEntitySP;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderHamster<T extends EntityHamster> extends RenderLiving<T>
{
	public static final Factory FACTORY = new Factory();
	private static final String modid = "animania", hamsterBaseDir = "textures/entity/rodents/";
	private static final ResourceLocation[] HAMSTER_TEXTURES = new ResourceLocation[] { new ResourceLocation(RenderHamster.modid, RenderHamster.hamsterBaseDir + "hamster_" + "black.png"), new ResourceLocation(RenderHamster.modid, RenderHamster.hamsterBaseDir + "hamster_" + "brown.png"), new ResourceLocation(RenderHamster.modid, RenderHamster.hamsterBaseDir + "hamster_" + "darkbrown.png"), new ResourceLocation(RenderHamster.modid, RenderHamster.hamsterBaseDir + "hamster_" + "darkgray.png"), new ResourceLocation(RenderHamster.modid, RenderHamster.hamsterBaseDir + "hamster_" + "gray.png"), new ResourceLocation(RenderHamster.modid, RenderHamster.hamsterBaseDir + "hamster_" + "plum.png"), new ResourceLocation(RenderHamster.modid, RenderHamster.hamsterBaseDir + "hamster_" + "tarou.png"), new ResourceLocation(RenderHamster.modid, RenderHamster.hamsterBaseDir + "hamster_" + "white.png"), new ResourceLocation(RenderHamster.modid, RenderHamster.hamsterBaseDir + "hamster_" + "gold.png") };

	private static final int[] EYE_COLORS = new int[] {0xDEDEDE, 0xDB703B, 0xEBCFC2, 0x97918F, 0xA8A8A8, 0xAF797D, 0xFFD7AD, 0xEFEFEF, 0xD39013};

	private LayerBlinking blinking;

	private float scale;
	protected ModelHamster modelHamsterMain;

	public RenderHamster(RenderManager rm)
	{
		super(rm, new ModelHamster(), 0.25F);
		this.modelHamsterMain = new ModelHamster();
		this.scale = 0.5F;
		this.shadowSize = 0.15F;
		this.addLayer(blinking = new LayerBlinking(this, new ResourceLocation("animania:textures/entity/rodents/hamster_blink.png"), 0));
	}

	protected void preRenderScale(EntityHamster LivingEntity, float f)
	{
		GL11.glScalef(this.scale * .8F, this.scale * .8F, this.scale * .8F);

		if (LivingEntity.isPassenger())
		{
			if (LivingEntity.getRidingEntity() instanceof PlayerEntitySP)
			{

				PlayerEntity player = (PlayerEntity) LivingEntity.getRidingEntity();
				LivingEntity.rotationYaw = player.rotationYaw;

				if (player.isSneaking())
				{
					GlStateManager.translate(-0.85F, LivingEntity.height - .07F, -0.1F);

				}
				else
				{
					GlStateManager.translate(-0.85F, LivingEntity.height - .17F, -0.1F);
				}
			}
		}
		else if (LivingEntity.getSleeping())
		{
			GlStateManager.translate(0F, 0.15F, 0F);
			GlStateManager.rotate(20.0F, 0.0F, 0.0F, 1.0F);
		}
	}

	@Override
	protected void preRenderCallback(EntityHamster LivingEntity, float f)
	{
		preRenderScale((EntityHamster) LivingEntity, f);
		this.blinking.setColors(EYE_COLORS[LivingEntity.getColorNumber()], EYE_COLORS[LivingEntity.getColorNumber()]);
	}

	@Override
	protected ResourceLocation getEntityTexture(T entity)
	{
		if (entity.getX() == -1 && entity.getY() == -1 && entity.getZ() == -1)
		{
			return HAMSTER_TEXTURES[0];
		}

		return RenderHamster.HAMSTER_TEXTURES[entity.getColorNumber()];
	}

	static class Factory<T extends EntityHamster> implements IRenderFactory<T>
	{
		@Override
		public Render<? super T> createRenderFor(RenderManager manager)
		{
			return new RenderHamster(manager);
		}
	}
}
