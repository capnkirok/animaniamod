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

import com.animania.client.models.ModelBull;
import com.animania.common.entities.cows.EntityBullHolstein;

@SideOnly(Side.CLIENT)
public class RenderBullHolstein extends RenderLiving
{
    private static final ResourceLocation cowTextures = new ResourceLocation("animania:textures/entity/cows/bull_holstein.png");
    private static final ResourceLocation cowTexturesBlink = new ResourceLocation("animania:textures/entity/cows/bull_holstein_blink.png");
    Random rand = new Random();

    public RenderBullHolstein(RenderManager rm)
   	{
   		super(rm, new ModelBull(), 0.5F);
   	}

    protected void preRenderScale(EntityBullHolstein entity, float f)
    {
        GL11.glScalef(1.3F, 1.3F, 1.3F); //TODO make dynamic
    }

	@Override
    protected void preRenderCallback(EntityLivingBase entityliving, float f)
    {
        preRenderScale((EntityBullHolstein)entityliving, f);
    }
    
    protected ResourceLocation getCowTextures(EntityBullHolstein par1EntityCow)
    {
        return cowTextures;
    }
    
    protected ResourceLocation getCowTexturesBlink(EntityBullHolstein par1EntityCow)
	{
		return cowTexturesBlink;
	}
   
    @Override
	protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
		EntityBullHolstein entity = (EntityBullHolstein)par1Entity;

		int blinkTimer = entity.blinkTimer;

		if (blinkTimer < 7 && blinkTimer >= 0) {
			return this.getCowTexturesBlink(entity);
		} else {
			return this.getCowTextures(entity);
		}

	}
}
