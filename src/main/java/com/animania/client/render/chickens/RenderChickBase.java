package com.animania.client.render.chickens;

import org.lwjgl.opengl.GL11;

import com.animania.client.models.ModelChick;
import com.animania.client.render.layer.LayerBlinking;
import com.animania.common.entities.chickens.EntityAnimaniaChicken;
import com.animania.common.entities.chickens.EntityChickBase;
import com.animania.common.handler.BlockHandler;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderChickBase<T extends EntityChickBase> extends RenderLiving<T>
{
	public static final Factory FACTORY = new Factory();

	LayerBlinking blinkingLayer;
	
	public RenderChickBase(RenderManager rm)
	{
		super(rm, new ModelChick(), 0.2F);
		this.addLayer(blinkingLayer = new LayerBlinking(this, new ResourceLocation("animania:textures/entity/chickens/chick_blink.png"), 0));
	}

		
	@Override
	protected float handleRotationFloat(T livingBase, float partialTicks)
	{
		float f = livingBase.oFlap + (livingBase.wingRotation - livingBase.oFlap) * partialTicks;
		float f1 = livingBase.oFlapSpeed + (livingBase.destPos - livingBase.oFlapSpeed) * partialTicks;
		return (MathHelper.sin(f) + 1.0F) * f1;
	}

	@Override
	protected void preRenderCallback(T entityliving, float f)
	{
		this.preRenderScale(entityliving, f);
		blinkingLayer.setColors(entityliving.lidCol, entityliving.lidCol);
	}

	protected void preRenderScale(T entity, float f)
	{

		float age = entity.getEntityAge();
		GL11.glScalef(1.0F + (age / entity.getSizeDividend()), 1.0F + (age / entity.getSizeDividend()), 1.0F + (age / entity.getSizeDividend())); 

		double x = entity.posX;
		double y = entity.posY;
		double z = entity.posZ;

		BlockPos pos = new BlockPos(x, y, z);

		Block blockchk = entity.world.getBlockState(pos).getBlock();

		EntityAnimaniaChicken entityChk = (EntityAnimaniaChicken) entity;

		if (blockchk == BlockHandler.blockNest || entityChk.getSleeping())
		{
			this.shadowSize = 0;
			GlStateManager.translate(-.25F, 0.1F, -.25F);
		}
		else
		{
			this.shadowSize = 0.2F;
			entityChk.setSleeping(false);
		}

	}

	@Override
	protected ResourceLocation getEntityTexture(T entity)
	{
		return entity.getResourceLocation();
	}

	static class Factory<T extends EntityChickBase> implements IRenderFactory<T>
	{
		@Override
		public Render<? super T> createRenderFor(RenderManager manager)
		{
			return new RenderChickBase(manager);
		}
	}

}