package com.animania.render.chickens;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

import com.animania.entities.chickens.EntityRoosterLeghorn;
import com.animania.models.ModelRooster;


@SideOnly(Side.CLIENT)
public class RenderRoosterLeghorn extends RenderLiving<EntityRoosterLeghorn>
{

	public RenderRoosterLeghorn(RenderManager rm)
	{
		super(rm, new ModelRooster(), 0.32F);
	}

	protected float handleRotationFloat(EntityRoosterLeghorn livingBase, float partialTicks)
	{
		float f = livingBase.oFlap + (livingBase.wingRotation - livingBase.oFlap) * partialTicks;
		float f1 = livingBase.oFlapSpeed + (livingBase.destPos - livingBase.oFlapSpeed) * partialTicks;
		return (MathHelper.sin(f) + 1.0F) * f1;
	}
	
	@Override
    protected void preRenderCallback(EntityRoosterLeghorn entityliving, float f)
    {
        preRenderScale((EntityRoosterLeghorn)entityliving, f);
    }

	protected void preRenderScale(EntityRoosterLeghorn entity, float f)
    {
        GL11.glScalef(1.0F, 1.0F, 1.0F); 
    }
	

	@Override
	protected ResourceLocation getEntityTexture(EntityRoosterLeghorn entity) {
		int blinkTimer = entity.blinkTimer;

		if (blinkTimer < 5 && blinkTimer >= 0) {
			return entity.getResourceLocationBlink();
		} else {
			return entity.getResourceLocation();
		}
	}

	

}
