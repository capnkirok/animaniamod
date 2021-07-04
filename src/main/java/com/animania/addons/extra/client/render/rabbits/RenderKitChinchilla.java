package com.animania.addons.extra.client.render.rabbits;

import org.lwjgl.opengl.GL11;

import com.animania.addons.extra.client.model.rabbits.ModelChinchilla;
import com.animania.addons.extra.common.entity.rodents.rabbits.EntityAnimaniaRabbit;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitChinchilla.EntityRabbitKitChinchilla;
import com.animania.client.render.layer.LayerBlinking;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Dist.CLIENT)
public class RenderKitChinchilla<T extends EntityRabbitKitChinchilla> extends RenderLiving<T>
{
	public static final Factory FACTORY = new Factory();
	private static final ResourceLocation rabbitTextures = new ResourceLocation("animania:textures/entity/rabbits/rabbit_chinchilla.png");
	private static final ResourceLocation rabbitTexturesBlink = new ResourceLocation("animania:textures/entity/rabbits/rabbit_blink.png");

	public RenderKitChinchilla(RenderManager rm)
	{
		super(rm, new ModelChinchilla(), 0.25F);
		this.addLayer(new LayerBlinking(this, rabbitTexturesBlink, 0x9E9E9E));
	}

	protected void preRenderScale(EntityRabbitKitChinchilla entity, float f)
	{
		float age = entity.getEntityAge();
		GL11.glScalef(0.33F + (age / entity.getSizeDividend()), 0.33F + (age / entity.getSizeDividend()), 0.33F + (age / entity.getSizeDividend()));
		GL11.glTranslatef(0f, 0f, -0.5f);

		double x = entity.posX;
		double y = entity.posY;
		double z = entity.posZ;
		BlockPos pos = new BlockPos(x, y, z);
		Block blockchk = entity.world.getBlockState(pos).getBlock();
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
	}

	@Override
	protected ResourceLocation getEntityTexture(T entity)
	{
		return this.rabbitTextures;
	}

	static class Factory<T extends EntityRabbitKitChinchilla> implements IRenderFactory<T>
	{
		@Override
		public Render<? super T> createRenderFor(RenderManager manager)
		{
			return new RenderKitChinchilla(manager);
		}

	}
}
