package com.animania.render.chickens;

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

import com.animania.entities.chickens.EntityHenOrpington;
import com.animania.entities.chickens.EntityRoosterOrpington;
import com.animania.models.ModelRooster;


@SideOnly(Side.CLIENT)
public class RenderRoosterOrpington extends RenderLiving<EntityRoosterOrpington>
{

	public RenderRoosterOrpington(RenderManager rm)
	{
		super(rm, new ModelRooster(), 0.32F);
	}

	protected float handleRotationFloat(EntityRoosterOrpington livingBase, float partialTicks)
	{
		float f = livingBase.oFlap + (livingBase.wingRotation - livingBase.oFlap) * partialTicks;
		float f1 = livingBase.oFlapSpeed + (livingBase.destPos - livingBase.oFlapSpeed) * partialTicks;
		return (MathHelper.sin(f) + 1.0F) * f1;
	}
	
	@Override
    protected void preRenderCallback(EntityRoosterOrpington entityliving, float f)
    {
        preRenderScale((EntityRoosterOrpington)entityliving, f);
    }

	protected void preRenderScale(EntityRoosterOrpington entity, float f)
    {
        GL11.glScalef(1.10F, 1.10F, 1.10F); 
    }
	

	@Override
	protected ResourceLocation getEntityTexture(EntityRoosterOrpington entity) {
		int blinkTimer = entity.blinkTimer;

		if (blinkTimer < 5 && blinkTimer >= 0) {
			return entity.getResourceLocationBlink();
		} else {
			return entity.getResourceLocation();
		}
	}

	

}
