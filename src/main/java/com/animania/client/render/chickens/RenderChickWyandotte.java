package com.animania.client.render.chickens;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.animania.client.models.ModelChick;
import com.animania.common.entities.chickens.EntityChickWyandotte;

@SideOnly(Side.CLIENT)
public class RenderChickWyandotte extends RenderLiving<EntityChickWyandotte>
{
	public RenderChickWyandotte(RenderManager rm)
	{
		super(rm, new ModelChick(), 0.2F);
	}

	protected float handleRotationFloat(EntityChickWyandotte livingBase, float partialTicks)
	{
		float f = livingBase.oFlap + (livingBase.wingRotation - livingBase.oFlap) * partialTicks;
		float f1 = livingBase.oFlapSpeed + (livingBase.destPos - livingBase.oFlapSpeed) * partialTicks;
		return (MathHelper.sin(f) + 1.0F) * f1;
	}
	
	@Override
	protected void preRenderCallback(EntityChickWyandotte entityliving, float f)
	{
		preRenderScale((EntityChickWyandotte)entityliving, f);
	}

	protected void preRenderScale(EntityChickWyandotte entity, float f)
	{
		
		float age = entity.getEntityAge();
		
		GL11.glScalef(1.0F + age, 1.0F + age, 1.0F + age); 
		

	}

	@Override
	protected ResourceLocation getEntityTexture(EntityChickWyandotte entity) {
		
		int blinkTimer = entity.blinkTimer;

		if (blinkTimer < 5 && blinkTimer >= 0) {
			return entity.getResourceLocationBlink();
		} else {
			return entity.getResourceLocation();
		}
		
	}
	
	

}
