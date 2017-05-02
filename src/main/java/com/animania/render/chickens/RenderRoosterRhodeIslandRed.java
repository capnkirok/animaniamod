package com.animania.render.chickens;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

import com.animania.entities.chickens.EntityRoosterRhodeIslandRed;
import com.animania.models.ModelRooster;


@SideOnly(Side.CLIENT)
public class RenderRoosterRhodeIslandRed extends RenderLiving<EntityRoosterRhodeIslandRed>
{

	public RenderRoosterRhodeIslandRed(RenderManager rm)
	{
		super(rm, new ModelRooster(), 0.32F);
	}

	protected float handleRotationFloat(EntityRoosterRhodeIslandRed livingBase, float partialTicks)
	{
		float f = livingBase.oFlap + (livingBase.wingRotation - livingBase.oFlap) * partialTicks;
		float f1 = livingBase.oFlapSpeed + (livingBase.destPos - livingBase.oFlapSpeed) * partialTicks;
		return (MathHelper.sin(f) + 1.0F) * f1;
	}
	
	@Override
    protected void preRenderCallback(EntityRoosterRhodeIslandRed entityliving, float f)
    {
        preRenderScale((EntityRoosterRhodeIslandRed)entityliving, f);
    }

	protected void preRenderScale(EntityRoosterRhodeIslandRed entity, float f)
    {
        GL11.glScalef(1.08F, 1.08F, 1.08F); 
    }
	

	@Override
	protected ResourceLocation getEntityTexture(EntityRoosterRhodeIslandRed entity) {
		int blinkTimer = entity.blinkTimer;

		if (blinkTimer < 5 && blinkTimer >= 0) {
			return entity.getResourceLocationBlink();
		} else {
			return entity.getResourceLocation();
		}
	}

	

}
