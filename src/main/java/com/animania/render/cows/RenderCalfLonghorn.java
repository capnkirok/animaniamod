package com.animania.render.cows;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.animania.entities.cows.EntityCalfHolstein;
import com.animania.entities.cows.EntityCalfLonghorn;
import com.animania.entities.cows.EntityCowHereford;
import com.animania.models.ModelCalfLonghorn;

@SideOnly(Side.CLIENT)
public class RenderCalfLonghorn extends RenderLiving<EntityCalfLonghorn>
{
    private static final ResourceLocation cowTextures = new ResourceLocation("animania:textures/entity/cows/calf_longhorn.png");
    private static final ResourceLocation cowTexturesBlink = new ResourceLocation("animania:textures/entity/cows/calf_longhorn_blink.png");
    Random rand = new Random();
    
    public RenderCalfLonghorn(RenderManager rm)
   	{
   		super(rm, new ModelCalfLonghorn(), 0.5F);
   	}

    protected ResourceLocation getCowTextures(EntityCalfLonghorn par1EntityCow)
    {
        return cowTextures;
    }
    
    protected ResourceLocation getCowTexturesBlink(EntityCalfLonghorn par1EntityCow)
    {
        return cowTexturesBlink;
    }

    @Override
	protected void preRenderCallback(EntityCalfLonghorn entityliving, float f)
	{
		preRenderScale((EntityCalfLonghorn)entityliving, f);
	}

	protected void preRenderScale(EntityCalfLonghorn entity, float f)
	{
		float age = entity.getEntityAge();
		GL11.glScalef(1.0F + age, 1.0F + age, 1.0F + age); 
	}
    
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
	protected ResourceLocation getEntityTexture(EntityCalfLonghorn par1Entity)
	{
		EntityCalfLonghorn entity = (EntityCalfLonghorn)par1Entity;

		int blinkTimer = entity.blinkTimer;

		if (blinkTimer < 7 && blinkTimer >= 0) {
			return this.getCowTexturesBlink(entity);
		} else {
			return this.getCowTextures(entity);
		}

	}
}
