package com.animania.render.cows;

import java.util.Random;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

import com.animania.entities.cows.EntityCowAngus;
import com.animania.entities.cows.EntityCowHereford;
import com.animania.models.ModelCowAngus;

@SideOnly(Side.CLIENT)
public class RenderCowAngus extends RenderLiving
{
    private static final ResourceLocation cowTextures = new ResourceLocation("animania:textures/entity/cows/cow_angus.png");
    private static final ResourceLocation cowTexturesBlink = new ResourceLocation("animania:textures/entity/cows/cow_angus_blink.png");
    Random rand = new Random();
    
    public RenderCowAngus(RenderManager rm)
   	{
   		super(rm, new ModelCowAngus(), 0.75F);
   	}
   
    protected void preRenderScale(EntityCowAngus entity, float f)
    {
        GL11.glScalef(1.34F, 1.34F, 1.34F); 
    }

	@Override
    protected void preRenderCallback(EntityLivingBase entityliving, float f)
    {
        preRenderScale((EntityCowAngus)entityliving, f);
    }
	
    protected ResourceLocation getCowTextures(EntityCowAngus par1EntityCow)
    {
        return cowTextures;
    }
    
    protected ResourceLocation getCowTexturesBlink(EntityCowAngus par1EntityCow)
    {
        return cowTexturesBlink;
    }

    protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
		EntityCowAngus entity = (EntityCowAngus)par1Entity;

		int blinkTimer = entity.blinkTimer;

		if (blinkTimer < 7 && blinkTimer >= 0) {
			return this.getCowTexturesBlink(entity);
		} else {
			return this.getCowTextures(entity);
		}

	}
}
