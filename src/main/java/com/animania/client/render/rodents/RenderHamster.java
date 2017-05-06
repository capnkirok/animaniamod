package com.animania.client.render.rodents;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.animania.client.models.ModelHamster;
import com.animania.common.entities.cows.EntityBullHereford;
import com.animania.common.entities.pigs.EntityHogOldSpot;
import com.animania.common.entities.rodents.EntityHamster;

public class RenderHamster extends RenderLiving<EntityHamster>
{
	private float scale;
	protected ModelHamster modelHamsterMain;


	public RenderHamster(RenderManager rm)
	{
		super(rm, new ModelHamster(), 0.25F);
		modelHamsterMain = new ModelHamster(); //TODO Check
		scale = 0.5F;
		shadowSize = 0.15F;
	}

	protected void preRenderScale(EntityHamster entityhamster, float f)
	{
		GL11.glScalef(scale, scale, scale);
	}

	@Override
	protected void preRenderCallback(EntityHamster entityliving, float f)
	{
		preRenderScale((EntityHamster)entityliving, f);
	}
	
	@Override
    protected ResourceLocation getEntityTexture(EntityHamster entity) {
  	
		EntityHamster entityHamster = (EntityHamster) entity;
    	
		int blinkTimer = entity.blinkTimer;

		if (blinkTimer < 5 && blinkTimer >= 0) {
			return entityHamster.getResourceLocationBlink();
		} else {
			return entityHamster.getResourceLocation();
		}
		
    }
}

	/*
	@Override
    protected void preRenderCallback(EntityLivingBase entityliving, float f)
    {
        preRenderScale((EntityHamster)entityliving, f);
    }
	*/

	/*
	@Override
    protected void renderEquippedItems(EntityLivingBase entityliving, float f)
    {
        ItemStack itemstack = entityliving.getHeldItem();
        if(itemstack != ItemStack.EMPTY)
        {
            GL11.glPushMatrix();
            modelHamsterMain.hamsterLegFrontRight.postRender(0.0625F);
            GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);
            if(itemstack.itemID < 256 && RenderBlocks.renderItemIn3d(Block.blocksList[itemstack.itemID].getRenderType()))
            {
                float f1 = 0.5F;
                GL11.glTranslatef(0.0F, 0.01875F, -0.3125F);
                f1 *= 0.75F;
                GL11.glRotatef(20F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(f1, -f1, f1);
            } else
            if(Item.itemsList[itemstack.itemID].isFull3D())
            {
                float f2 = 0.625F;
                GL11.glTranslatef(0.0F, 0.01875F, 0.0F);
                GL11.glScalef(f2, -f2, f2);
                GL11.glRotatef(-100F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45F, 0.0F, 1.0F, 0.0F);
            } else
            {
                float f3 = 0.375F;
                GL11.glTranslatef(0.25F, 0.01875F, -0.1875F);
                GL11.glScalef(f3, f3, f3);
                GL11.glRotatef(60F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(-90F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(20F, 0.0F, 0.0F, 1.0F);
            }
            renderManager.itemRenderer.renderItem(entityliving, itemstack, 0);
            GL11.glPopMatrix();
        }
    }


	public void doRender(EntityHamster entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
		EntityHamster entityHamster = (EntityHamster) entity;

    	if (entityHamster.isChild()) {
    		y -= 0.45D;
    	}

    	super.doRender((EntityHamster)entityHamster, x, y, z, entityYaw, partialTicks);
    }



    


}
 */
