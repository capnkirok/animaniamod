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

import com.animania.entities.cows.EntityBullHereford;
import com.animania.entities.cows.EntityCowHereford;
import com.animania.models.ModelBullHereford;

@SideOnly(Side.CLIENT)
public class RenderBullHereford extends RenderLiving
{
    private static final ResourceLocation cowTextures = new ResourceLocation("animania:textures/entity/cows/bull_hereford.png");
    private static final ResourceLocation cowTexturesBlink = new ResourceLocation("animania:textures/entity/cows/bull_hereford_blink.png");
    Random rand = new Random();

    public RenderBullHereford(RenderManager rm)
   	{
   		super(rm, new ModelBullHereford(), 0.5F);
   	}
    
    protected ResourceLocation getCowTextures(EntityBullHereford par1EntityCow)
    {
        return cowTextures;
    }
    
    protected ResourceLocation getCowTexturesBlink(EntityBullHereford par1EntityCow)
	{
		return cowTexturesBlink;
	}
    
    protected void preRenderScale(EntityBullHereford entity, float f)
    {
        GL11.glScalef(1.4F, 1.4F, 1.4F); //TODO make dynamic
    }

	@Override
    protected void preRenderCallback(EntityLivingBase entityliving, float f)
    {
        preRenderScale((EntityBullHereford)entityliving, f);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
	protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
		EntityBullHereford entity = (EntityBullHereford)par1Entity;

		int blinkTimer = entity.blinkTimer;

		if (blinkTimer < 7 && blinkTimer >= 0) {
			return this.getCowTexturesBlink(entity);
		} else {
			return this.getCowTextures(entity);
		}

	}
}
