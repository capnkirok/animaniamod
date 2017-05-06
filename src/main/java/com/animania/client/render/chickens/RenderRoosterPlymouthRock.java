package com.animania.client.render.chickens;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.animania.client.models.ModelRooster;
import com.animania.common.entities.chickens.EntityHenPlymouthRock;
import com.animania.common.entities.chickens.EntityRoosterPlymouthRock;


@SideOnly(Side.CLIENT)
public class RenderRoosterPlymouthRock extends RenderLiving<EntityRoosterPlymouthRock>
{

	public RenderRoosterPlymouthRock(RenderManager rm)
	{
		super(rm, new ModelRooster(), 0.32F);
	}

	protected float handleRotationFloat(EntityRoosterPlymouthRock livingBase, float partialTicks)
	{
		float f = livingBase.oFlap + (livingBase.wingRotation - livingBase.oFlap) * partialTicks;
		float f1 = livingBase.oFlapSpeed + (livingBase.destPos - livingBase.oFlapSpeed) * partialTicks;
		return (MathHelper.sin(f) + 1.0F) * f1;
	}
	
	@Override
    protected void preRenderCallback(EntityRoosterPlymouthRock entityliving, float f)
    {
        preRenderScale((EntityRoosterPlymouthRock)entityliving, f);
    }

	protected void preRenderScale(EntityRoosterPlymouthRock entity, float f)
    {
        GL11.glScalef(1F, 1F, 1F); 
    }
	

	@Override
	protected ResourceLocation getEntityTexture(EntityRoosterPlymouthRock entity) {
		int blinkTimer = entity.blinkTimer;

		if (blinkTimer < 5 && blinkTimer >= 0) {
			return entity.getResourceLocationBlink();
		} else {
			return entity.getResourceLocation();
		}
	}

	

}
