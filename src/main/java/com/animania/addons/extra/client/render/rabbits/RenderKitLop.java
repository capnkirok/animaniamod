package com.animania.addons.extra.client.render.rabbits;

import org.lwjgl.opengl.GL11;

import com.animania.addons.extra.client.model.rabbits.ModelLop;
import com.animania.addons.extra.common.entity.rodents.rabbits.EntityAnimaniaRabbit;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitLop.RabbitEntityKitLop;
import com.animania.client.render.layer.LayerBlinking;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Dist.CLIENT)
public class RenderKitLop<T extends RabbitEntityKitLop> extends RenderLiving<T>
{
	public static final Factory FACTORY = new Factory();
	private static final String modid = "animania", rabbitBaseDir = "textures/entity/rabbits/";

	private static final ResourceLocation[] RABBIT_TEXTURES = new ResourceLocation[] { new ResourceLocation(RenderKitLop.modid, RenderKitLop.rabbitBaseDir + "rabbit_lop_" + "black.png"), new ResourceLocation(RenderKitLop.modid, RenderKitLop.rabbitBaseDir + "rabbit_lop_" + "brown.png"), new ResourceLocation(RenderKitLop.modid, RenderKitLop.rabbitBaseDir + "rabbit_lop_" + "golden.png"), new ResourceLocation(RenderKitLop.modid, RenderKitLop.rabbitBaseDir + "rabbit_lop_" + "olive.png"), new ResourceLocation(RenderKitLop.modid, RenderKitLop.rabbitBaseDir + "rabbit_lop_" + "patch_black.png"), new ResourceLocation(RenderKitLop.modid, RenderKitLop.rabbitBaseDir + "rabbit_lop_" + "patch_brown.png"), new ResourceLocation(RenderKitLop.modid, RenderKitLop.rabbitBaseDir + "rabbit_lop_" + "patch_grey.png") };

private static int[] EYE_COLORS = new int[]{0x404040, 0x816D60, 0xD0A675, 0x7F6C5B, 0xF6F4F4, 0xF6F4F4, 0xF6F4F4};
	
	private static final ResourceLocation rabbitTexturesBlink = new ResourceLocation("animania:textures/entity/rabbits/rabbit_blink.png");

	private LayerBlinking blinkingLayer;
	
	public RenderKitLop(RenderManager rm)
	{
		super(rm, new ModelLop(), 0.25F);
		this.addLayer(blinkingLayer = new LayerBlinking(this, rabbitTexturesBlink, 0));
	}

	protected void preRenderScale(RabbitEntityKitLop entity, float f)
	{
		float age = entity.getEntityAge();
		GL11.glScalef(0.23F + (age / entity.getSizeDividend()), 0.23F + (age / entity.getSizeDividend()), 0.23F + (age / entity.getSizeDividend()));
		GL11.glTranslatef(0f, 0f, -0.5f);

		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();
		BlockPos pos = new BlockPos(x, y, z);
		Block blockchk = entity.level.getBlockState(pos).getBlock();
		EntityAnimaniaRabbit entityChk = (EntityAnimaniaRabbit) entity;
		if (entityChk.getSleeping())
		{
			this.shadowSize = 0;
			GlStateManager.translate(-.25F, 0.1F, -.25F);
		}
		else
		{
			this.shadowSize = 0.25F;
			entityChk.setSleeping(false);
		}
	}

	@Override
	protected void preRenderCallback(T LivingEntity, float f)
	{
		this.preRenderScale(LivingEntity, f);
		this.blinkingLayer.setColors(EYE_COLORS[LivingEntity.getColorNumber()], EYE_COLORS[LivingEntity.getColorNumber()]);
	}

	@Override
	protected ResourceLocation getEntityTexture(T entity)
	{
		if (entity.getX() == -1 && entity.getY() == -1 && entity.getZ() == -1)
		{
			return RABBIT_TEXTURES[0];
		}

		return this.RABBIT_TEXTURES[entity.getColorNumber()];
	}

	static class Factory<T extends RabbitEntityKitLop> implements IRenderFactory<T>
	{
		@Override
		public Render<? super T> createRenderFor(RenderManager manager)
		{
			return new RenderKitLop(manager);
		}

	}
}
