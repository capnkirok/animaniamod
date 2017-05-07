package com.animania.client.render.peacocks;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

import com.animania.client.models.ModelPeachick;
import com.animania.common.entities.peacocks.EntityPeachickBlue;


@SideOnly(Side.CLIENT)
public class RenderPeachickBlue extends RenderLiving<EntityPeachickBlue>
{

	public RenderPeachickBlue(RenderManager rm)
	{
		super(rm, new ModelPeachick(), 0.15F);
	}

	@Override
	protected float handleRotationFloat(EntityPeachickBlue livingBase, float partialTicks)
	{
		float f = livingBase.oFlap + (livingBase.wingRotation - livingBase.oFlap) * partialTicks;
		float f1 = livingBase.oFlapSpeed + (livingBase.destPos - livingBase.oFlapSpeed) * partialTicks;
		return (MathHelper.sin(f) + 1.0F) * f1;
	}


	@Override
	protected void preRenderCallback(EntityPeachickBlue entityliving, float f)
	{
		preRenderScale(entityliving, f);
	}

	protected void preRenderScale(EntityPeachickBlue entity, float f)
	{
		float age = entity.getEntityAge();
		GL11.glScalef(0.3F + age, 0.3F + age, 0.3F + age); 

	}

	@Override
	protected ResourceLocation getEntityTexture(EntityPeachickBlue entity) {
		int blinkTimer = entity.blinkTimer;

		if (blinkTimer < 5 && blinkTimer >= 0) {
			return entity.getResourceLocationBlink();
		} else {
			return entity.getResourceLocation();
		}
	}

}