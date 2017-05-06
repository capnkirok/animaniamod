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

import com.animania.client.models.ModelBullAngus;
import com.animania.common.entities.cows.EntityBullAngus;
import com.animania.common.entities.cows.EntityCowHereford;

@SideOnly(Side.CLIENT)
public class RenderBullAngus extends RenderLiving
{
    private static final ResourceLocation cowTextures = new ResourceLocation("animania:textures/entity/cows/bull_angus.png");
    private static final ResourceLocation cowTexturesBlink = new ResourceLocation("animania:textures/entity/cows/bull_angus_blink.png");
    Random rand = new Random();

    public RenderBullAngus(RenderManager rm)
   	{
   		super(rm, new ModelBullAngus(), 0.75F);
   	}
    
    protected ResourceLocation getCowTextures(EntityBullAngus par1EntityCow)
    {
        return cowTextures;
    }
    
    protected ResourceLocation getCowTexturesBlink(EntityBullAngus par1EntityCow)
	{
		return cowTexturesBlink;
	}
    
    protected void preRenderScale(EntityBullAngus entity, float f)
    {
        GL11.glScalef(1.4F, 1.4F, 1.4F); 
    }

	@Override
    protected void preRenderCallback(EntityLivingBase entityliving, float f)
    {
        preRenderScale((EntityBullAngus)entityliving, f);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
	protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
		EntityBullAngus entity = (EntityBullAngus)par1Entity;

		int blinkTimer = entity.blinkTimer;

		if (blinkTimer < 7 && blinkTimer >= 0) {
			return this.getCowTexturesBlink(entity);
		} else {
			return this.getCowTextures(entity);
		}

	}
}
