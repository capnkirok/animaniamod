package com.animania.client.render.cows;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.animania.client.models.ModelCalf;
import com.animania.common.entities.cows.EntityCalfHolstein;

@SideOnly(Side.CLIENT)
public class RenderCalfHolstein extends RenderLiving<EntityCalfHolstein>
{
    private static final ResourceLocation cowTextures = new ResourceLocation("animania:textures/entity/cows/calf_holstein.png");
    private static final ResourceLocation cowTexturesBlink = new ResourceLocation("animania:textures/entity/cows/calf_holstein_blink.png");
    Random rand = new Random();
    
    public RenderCalfHolstein(RenderManager rm)
   	{
   		super(rm, new ModelCalf(), 0.5F);
   	}

    protected ResourceLocation getCowTextures(EntityCalfHolstein par1EntityCow)
    {
        return cowTextures;
    }

    protected ResourceLocation getCowTexturesBlink(EntityCalfHolstein par1EntityCow)
    {
        return cowTexturesBlink;
    }
    
    @Override
	protected void preRenderCallback(EntityCalfHolstein entityliving, float f)
	{
		preRenderScale(entityliving, f);
	}

	protected void preRenderScale(EntityCalfHolstein entity, float f)
	{
		
		float age = entity.getEntityAge();
		
		GL11.glScalef(1.0F + age, 1.0F + age, 1.0F + age); 
	}
	
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
	@Override
	protected ResourceLocation getEntityTexture(EntityCalfHolstein par1Entity)
	{
		EntityCalfHolstein entity = par1Entity;

		int blinkTimer = entity.blinkTimer;

		if (blinkTimer < 7 && blinkTimer >= 0) {
			return this.getCowTexturesBlink(entity);
		} else {
			return this.getCowTextures(entity);
		}

	}
}
