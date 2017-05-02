package com.animania.render.cows;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.animania.entities.cows.EntityCowFriesian;
import com.animania.entities.cows.EntityCowFriesian;
import com.animania.entities.cows.EntityCowHereford;
import com.animania.models.ModelCow;

@SideOnly(Side.CLIENT)
public class RenderCowFriesian extends RenderLiving
{
    private static final ResourceLocation cowTextures = new ResourceLocation("animania:textures/entity/cows/cow_friesian.png");
    private static final ResourceLocation cowTexturesBlink = new ResourceLocation("animania:textures/entity/cows/cow_friesian_blink.png");
    Random rand = new Random();
    
    public RenderCowFriesian(RenderManager rm)
   	{
   		super(rm, new ModelCow(), 0.5F);
   	}

    protected ResourceLocation getCowTextures(EntityCowFriesian par1EntityCow)
    {
        return cowTextures;
    }
    
    protected ResourceLocation getCowTexturesBlink(EntityCowFriesian par1EntityCow)
    {
        return cowTexturesBlink;
    }

    protected void preRenderScale(EntityCowFriesian entity, float f)
    {
        GL11.glScalef(1.24F, 1.24F, 1.24F); 
    }

	@Override
    protected void preRenderCallback(EntityLivingBase entityliving, float f)
    {
        preRenderScale((EntityCowFriesian)entityliving, f);
    }  
    
	protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
		EntityCowFriesian entity = (EntityCowFriesian)par1Entity;

		int blinkTimer = entity.blinkTimer;

		if (blinkTimer < 7 && blinkTimer >= 0) {
			return this.getCowTexturesBlink(entity);
		} else {
			return this.getCowTextures(entity);
		}

	}
}
