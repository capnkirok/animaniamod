package com.animania.render.rodents;


import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

import com.animania.entities.rodents.EntityFerretWhite;
import com.animania.models.ModelFerret;


@SideOnly(Side.CLIENT)
public class RenderFerretWhite extends RenderLiving<EntityFerretWhite>
{
	private static final ResourceLocation FERRET_TEXTURES = new ResourceLocation("animania:textures/entity/rodents/ferret_white.png");
	private static final ResourceLocation FERRET_TEXTURES_BLINK = new ResourceLocation("animania:textures/entity/rodents/ferret_white_blink.png");

	public RenderFerretWhite(RenderManager rm)
	{
		super(rm, new ModelFerret(), 0.2F);
	}

	protected void preRenderScale(EntityFerretWhite entity, float f)
	{
		GL11.glScalef(0.5F, 0.5F, 0.5F); 


	}

	@Override
	protected void preRenderCallback(EntityFerretWhite entityliving, float f)
	{
		preRenderScale((EntityFerretWhite)entityliving, f);
	}

	protected ResourceLocation getEntityTexture(EntityFerretWhite entity)
	{
		int blinkTimer = entity.blinkTimer;
		if (blinkTimer < 5 && blinkTimer >= 0) {
			return FERRET_TEXTURES_BLINK;
		} else {
			return FERRET_TEXTURES;
		}

	}

	public ModelFerret getMainModel()
	{
		return (ModelFerret)super.getMainModel();
	}
}