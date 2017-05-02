package com.animania.render.rodents;


import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

import com.animania.entities.cows.EntityCalfHereford;
import com.animania.entities.rodents.EntityHedgehog;
import com.animania.entities.rodents.EntityHedgehogAlbino;
import com.animania.models.ModelHedgehog;


@SideOnly(Side.CLIENT)
public class RenderHedgehogAlbino extends RenderLiving<EntityHedgehogAlbino>
{
	private static final ResourceLocation HEDGEHOG_TEXTURES = new ResourceLocation("animania:textures/entity/rodents/hedgehog_white.png");
	private static final ResourceLocation HEDGEHOG_TEXTURES_BLINK = new ResourceLocation("animania:textures/entity/rodents/hedgehog_white_blink.png");
	private static final ResourceLocation SONIC_TEXTURES = new ResourceLocation("animania:textures/entity/rodents/hedgehog_sonic.png");
	private static final ResourceLocation SANIC_TEXTURES = new ResourceLocation("animania:textures/entity/rodents/hedgehog_sanic.png");

	public RenderHedgehogAlbino(RenderManager rm)
	{
		super(rm, new ModelHedgehog(), 0.3F);
	}

	protected void preRenderScale(EntityHedgehogAlbino entity, float f)
	{
		GL11.glScalef(0.7F, 0.7F, 0.7F); 

		if (entity.getCustomNameTag().equals("Sanic")) {
			GL11.glRotatef(20, -1, 0, 1);
			GL11.glScalef(1.2F, 1.7F, 1.6F); 
		}

	}

	@Override
	protected void preRenderCallback(EntityHedgehogAlbino entityliving, float f)
	{
		preRenderScale((EntityHedgehogAlbino)entityliving, f);
	}

	protected ResourceLocation getEntityTexture(EntityHedgehogAlbino entity)
	{
		
		if (entity.getCustomNameTag().equals("Sonic")) {
			return SONIC_TEXTURES;
		} else if (entity.getCustomNameTag().equals("Sanic")) {
			return SANIC_TEXTURES;
		} else {
			int blinkTimer = entity.blinkTimer;
			if (blinkTimer < 5 && blinkTimer >= 0) {
				return HEDGEHOG_TEXTURES_BLINK;
			} else {
				return HEDGEHOG_TEXTURES;
			}
		}
	}

	public ModelHedgehog getMainModel()
	{
		return (ModelHedgehog)super.getMainModel();
	}
}