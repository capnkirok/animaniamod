package com.animania.client.render.cows;

import java.util.Random;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

import com.animania.client.models.ModelCowLonghorn;
import com.animania.common.entities.cows.EntityCowLonghorn;

@SideOnly(Side.CLIENT)
public class RenderCowLonghorn extends RenderLiving
{
    private static final ResourceLocation cowTextures = new ResourceLocation("animania:textures/entity/cows/cow_longhorn.png");
    private static final ResourceLocation cowTexturesBlink = new ResourceLocation("animania:textures/entity/cows/cow_longhorn_blink.png");
    Random rand = new Random();
    
    public RenderCowLonghorn(RenderManager rm)
   	{
   		super(rm, new ModelCowLonghorn(), 0.5F);
   	}
   
    protected void preRenderScale(EntityCowLonghorn entity, float f)
    {
        GL11.glScalef(1.44F, 1.44F, 1.44F); 
    }

	@Override
    protected void preRenderCallback(EntityLivingBase entityliving, float f)
    {
        preRenderScale((EntityCowLonghorn)entityliving, f);
    }
	
    protected ResourceLocation getCowTextures(EntityCowLonghorn par1EntityCow)
    {
        return cowTextures;
    }
    
    protected ResourceLocation getCowTexturesBlink(EntityCowLonghorn par1EntityCow)
    {
        return cowTexturesBlink;
    }

    @Override
	protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
		EntityCowLonghorn entity = (EntityCowLonghorn)par1Entity;

		int blinkTimer = entity.blinkTimer;

		if (blinkTimer < 7 && blinkTimer >= 0) {
			return this.getCowTexturesBlink(entity);
		} else {
			return this.getCowTextures(entity);
		}

	}
}
