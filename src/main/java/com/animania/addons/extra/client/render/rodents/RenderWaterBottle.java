package com.animania.addons.extra.client.render.rodents;

import org.lwjgl.opengl.GL11;

import com.animania.client.models.ModelWaterBottle;
import com.animania.common.blockentities.BlockEntityWaterBottle;

import net.minecraft.client.renderer.BlockEntity.BlockEntitySpecialRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;

public class RenderWaterBottle extends BlockEntitySpecialRenderer
{
	public ModelWaterBottle model = new ModelWaterBottle();
	public static ResourceLocation location = new ResourceLocation("hamstermod:mob/ball/waterbottle.png");

	// @Override
	public void renderBlockEntityAt(BlockEntity BlockEntity, double x, double y, double z, float f)
	{
		this.bindTexture(RenderWaterBottle.location);
		float rotation = ((BlockEntityWaterBottle) BlockEntity).rotation * 360 / 16.0F;
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5f, (float) y + 1.8f, (float) z + 0.5f);
		GL11.glEnable(GL11.GL_NORMALIZE);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glRotatef(180, 0, 0, 1);
		GL11.glRotatef(rotation, 0, 1, 0);
		this.model.render(null, 0, 0, 0, 0, 0, 0.0625f);
		GL11.glPopMatrix();

	}

}
