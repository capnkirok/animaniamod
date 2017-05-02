package com.animania.render.cows;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.animania.entities.cows.EntityCalfAngus;
import com.animania.entities.cows.EntityCalfFriesian;
import com.animania.entities.cows.EntityCowHereford;
import com.animania.models.ModelCalf;

@SideOnly(Side.CLIENT)
public class RenderCalfFriesian extends RenderLiving<EntityCalfFriesian>
{
    private static final ResourceLocation cowTextures = new ResourceLocation("animania:textures/entity/cows/calf_friesian.png");
    private static final ResourceLocation cowTexturesBlink = new ResourceLocation("animania:textures/entity/cows/calf_friesian_blink.png");
    Random rand = new Random();
    
    public RenderCalfFriesian(RenderManager rm)
   	{
   		super(rm, new ModelCalf(), 0.5F);
   	}

    protected ResourceLocation getCowTextures(EntityCalfFriesian par1EntityCow)
    {
        return cowTextures;
    }
    
    protected ResourceLocation getCowTexturesBlink(EntityCalfFriesian par1EntityCow)
    {
        return cowTexturesBlink;
    }
    
    @Override
	protected void preRenderCallback(EntityCalfFriesian entityliving, float f)
	{
		preRenderScale((EntityCalfFriesian)entityliving, f);
	}

	protected void preRenderScale(EntityCalfFriesian entity, float f)
	{
		
		float age = entity.getEntityAge();
		
		GL11.glScalef(1.0F + age, 1.0F + age, 1.0F + age); 
		

	}

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
	protected ResourceLocation getEntityTexture(EntityCalfFriesian par1Entity)
	{
		EntityCalfFriesian entity = (EntityCalfFriesian)par1Entity;

		int blinkTimer = entity.blinkTimer;

		if (blinkTimer < 7 && blinkTimer >= 0) {
			return this.getCowTexturesBlink(entity);
		} else {
			return this.getCowTextures(entity);
		}

	}
}
