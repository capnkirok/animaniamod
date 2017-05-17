package com.animania.client.render.rodents;


import org.lwjgl.opengl.GL11;

import com.animania.client.models.ModelFerret;
import com.animania.common.entities.rodents.EntityFerretGrey;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@SideOnly(Side.CLIENT)
public class RenderFerretGrey extends RenderLiving<EntityFerretGrey>
{
	private static final ResourceLocation FERRET_TEXTURES = new ResourceLocation("animania:textures/entity/rodents/ferret_grey.png");
	private static final ResourceLocation FERRET_TEXTURES_BLINK = new ResourceLocation("animania:textures/entity/rodents/ferret_grey_blink.png");

	public RenderFerretGrey(RenderManager rm)
	{
		super(rm, new ModelFerret(), 0.2F);
	}

	protected void preRenderScale(EntityFerretGrey entity, float f)
	{
		if (entity.isRiding()) {

			if (entity.getRidingEntity() instanceof EntityPlayerSP) {
				GL11.glScalef(0.3F, 0.3F, 0.3F); 
				EntityPlayer player = (EntityPlayer)entity.getRidingEntity();
				entity.rotationYaw = player.rotationYaw;
				if (player.isSneaking()) {
					GlStateManager.translate(-1.2F, entity.height + .0F, .5F);
				} else {
					GlStateManager.translate(-1.2F, entity.height - .1F, .5F);
				}
			}


		} else {
			GL11.glScalef(0.5F, 0.5F, 0.5F); 
		}
		

	}

	@Override
	protected void preRenderCallback(EntityFerretGrey entityliving, float f)
	{
		preRenderScale(entityliving, f);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityFerretGrey entity)
	{
		int blinkTimer = entity.blinkTimer;
		if (blinkTimer < 5 && blinkTimer >= 0) {
			return FERRET_TEXTURES_BLINK;
		} else {
			return FERRET_TEXTURES;
		}
	}

	@Override
	public ModelFerret getMainModel()
	{
		return (ModelFerret)super.getMainModel();
	}
}