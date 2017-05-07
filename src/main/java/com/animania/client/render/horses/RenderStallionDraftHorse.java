package com.animania.client.render.horses;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

import com.animania.client.models.ModelStallionDraftHorse;
import com.animania.common.entities.horses.EntityStallionDraftHorse;


@SideOnly(Side.CLIENT)
public class RenderStallionDraftHorse extends RenderLiving
{
    private static final ResourceLocation horseTextures = new ResourceLocation("animania:textures/entity/horses/draft_horse_black.png");

    public RenderStallionDraftHorse(RenderManager rm)
   	{
   		super(rm, new ModelStallionDraftHorse(), 0.5F);
   	}

    protected void preRenderScale(EntityStallionDraftHorse entity, float f)
    {
        GL11.glScalef(1.0F, 1.0F, 1.0F); 
    }

	@Override
    protected void preRenderCallback(EntityLivingBase entityliving, float f)
    {
        preRenderScale((EntityStallionDraftHorse)entityliving, f);
    }
    
    protected ResourceLocation getHorseTextures(EntityStallionDraftHorse par1EntityCow)
    {
        return horseTextures;
    }
   
    @Override
	protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.getHorseTextures((EntityStallionDraftHorse)par1Entity);
    }
}
