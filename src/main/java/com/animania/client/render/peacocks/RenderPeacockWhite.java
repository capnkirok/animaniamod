package com.animania.client.render.peacocks;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

import com.animania.client.models.ModelPeacock;
import com.animania.common.entities.peacocks.EntityPeacockWhite;


@SideOnly(Side.CLIENT)
public class RenderPeacockWhite extends RenderLiving<EntityPeacockWhite>
{

	public RenderPeacockWhite(RenderManager rm)
	{
		super(rm, new ModelPeacock(), 0.32F);
	}

	protected float handleRotationFloat(EntityPeacockWhite livingBase, float partialTicks)
	{
		float f = livingBase.oFlap + (livingBase.wingRotation - livingBase.oFlap) * partialTicks;
		float f1 = livingBase.oFlapSpeed + (livingBase.destPos - livingBase.oFlapSpeed) * partialTicks;
		return (MathHelper.sin(f) + 1.0F) * f1;
	}
	
	@Override
    protected void preRenderCallback(EntityPeacockWhite entityliving, float f)
    {
        preRenderScale((EntityPeacockWhite)entityliving, f);
    }

	protected void preRenderScale(EntityPeacockWhite entity, float f)
    {
        GL11.glScalef(1.0F, 1.0F, 1.0F); 
    }
	

	@Override
	protected ResourceLocation getEntityTexture(EntityPeacockWhite entity) {
		int blinkTimer = entity.blinkTimer;

		if (blinkTimer < 5 && blinkTimer >= 0) {
			return entity.getResourceLocationBlink();
		} else {
			return entity.getResourceLocation();
		}
	}

}