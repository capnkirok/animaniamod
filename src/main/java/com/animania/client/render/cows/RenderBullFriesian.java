package com.animania.client.render.cows;

import java.util.Random;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

import com.animania.client.models.ModelBull;
import com.animania.common.entities.cows.EntityBullFriesian;
import com.animania.common.entities.cows.EntityCowHereford;

@SideOnly(Side.CLIENT)
public class RenderBullFriesian extends RenderLiving
{
    private static final ResourceLocation cowTextures = new ResourceLocation("animania:textures/entity/cows/bull_friesian.png");
    private static final ResourceLocation cowTexturesBlink = new ResourceLocation("animania:textures/entity/cows/bull_friesian_blink.png");
    Random rand = new Random();

    public RenderBullFriesian(RenderManager rm)
   	{
   		super(rm, new ModelBull(), 0.5F);
   	}
    
    protected void preRenderScale(EntityBullFriesian entity, float f)
    {
        GL11.glScalef(1.3F, 1.3F, 1.3F); //TODO make dynamic
    }

	@Override
    protected void preRenderCallback(EntityLivingBase entityliving, float f)
    {
        preRenderScale((EntityBullFriesian)entityliving, f);
    }

    protected ResourceLocation getCowTextures(EntityBullFriesian par1EntityCow)
    {
        return cowTextures;
    }
    
    protected ResourceLocation getCowTexturesBlink(EntityBullFriesian par1EntityCow)
	{
		return cowTexturesBlink;
	}

    protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
		EntityBullFriesian entity = (EntityBullFriesian)par1Entity;

		int blinkTimer = entity.blinkTimer;

		if (blinkTimer < 7 && blinkTimer >= 0) {
			return this.getCowTexturesBlink(entity);
		} else {
			return this.getCowTextures(entity);
		}

	}
}
