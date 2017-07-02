package com.animania.client.render.rodents;

import org.lwjgl.opengl.GL11;

import com.animania.client.models.ModelHamster;
import com.animania.client.render.amphibians.RenderFrogs;
import com.animania.common.entities.rodents.EntityHamster;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderHamster<T extends EntityHamster> extends RenderLiving<T>
{
	public static final Factory FACTORY = new Factory();
	private static final String             modid          = "animania", hamsterBaseDir = "textures/entity/rodents/";
	private static final ResourceLocation[] HAMSTER_TEXTURES = new ResourceLocation[] { 
			new ResourceLocation(RenderHamster.modid, RenderHamster.hamsterBaseDir + "hamster_" + "black.png"), 
			new ResourceLocation(RenderHamster.modid, RenderHamster.hamsterBaseDir +"hamster_" + "brown.png"), 
			new ResourceLocation(RenderHamster.modid, RenderHamster.hamsterBaseDir +"hamster_" + "darkbrown.png"), 
			new ResourceLocation(RenderHamster.modid, RenderHamster.hamsterBaseDir +"hamster_" + "darkgray.png"), 
			new ResourceLocation(RenderHamster.modid, RenderHamster.hamsterBaseDir +"hamster_" + "gray.png"), 
			new ResourceLocation(RenderHamster.modid, RenderHamster.hamsterBaseDir +"hamster_" + "plum.png"), 
			new ResourceLocation(RenderHamster.modid, RenderHamster.hamsterBaseDir +"hamster_" + "tarou.png"), 
			new ResourceLocation(RenderHamster.modid, RenderHamster.hamsterBaseDir +"hamster_" + "white.png"), 
			new ResourceLocation(RenderHamster.modid, RenderHamster.hamsterBaseDir +"hamster_" + "gold.png")}; 
	
	private static final ResourceLocation[] HAMSTER_TEXTURES_BLINK = new ResourceLocation[] { 
			new ResourceLocation(RenderHamster.modid, RenderHamster.hamsterBaseDir + "hamster_" + "black_blink.png"), 
			new ResourceLocation(RenderHamster.modid, RenderHamster.hamsterBaseDir +"hamster_" + "brown_blink.png"), 
			new ResourceLocation(RenderHamster.modid, RenderHamster.hamsterBaseDir +"hamster_" + "darkbrown_blink.png"), 
			new ResourceLocation(RenderHamster.modid, RenderHamster.hamsterBaseDir +"hamster_" + "darkgray_blink.png"), 
			new ResourceLocation(RenderHamster.modid, RenderHamster.hamsterBaseDir +"hamster_" + "gray_blink.png"), 
			new ResourceLocation(RenderHamster.modid, RenderHamster.hamsterBaseDir +"hamster_" + "plum_blink.png"), 
			new ResourceLocation(RenderHamster.modid, RenderHamster.hamsterBaseDir +"hamster_" + "tarou_blink.png"), 
			new ResourceLocation(RenderHamster.modid, RenderHamster.hamsterBaseDir +"hamster_" + "white_blink.png"), 
			new ResourceLocation(RenderHamster.modid, RenderHamster.hamsterBaseDir +"hamster_" + "gold_blink.png")}; 


	private float               scale;
	protected ModelHamster      modelHamsterMain;

	public RenderHamster(RenderManager rm) {
		super(rm, new ModelHamster(), 0.25F);
		this.modelHamsterMain = new ModelHamster();
		this.scale = 0.5F;
		this.shadowSize = 0.15F;
	}

	protected void preRenderScale(T entity, float f) {
		GL11.glScalef(this.scale * .8F, this.scale * .8F, this.scale * .8F);

		if (entity.isRiding())
			if (entity.getRidingEntity() instanceof EntityPlayerSP) {

				EntityPlayer player = (EntityPlayer) entity.getRidingEntity();
				entity.rotationYaw = player.rotationYaw;

				if (player.isSneaking())
					GlStateManager.translate(-0.85F, entity.height - .07F, -0.1F);
				else
					GlStateManager.translate(-0.85F, entity.height - .17F, -0.1F);

			}
	}

	@Override
	protected void preRenderCallback(T entityliving, float f) {
		this.preRenderScale(entityliving, f);
	}

	@Override
	protected ResourceLocation getEntityTexture(T entity) {

		int blinkTimer = entity.blinkTimer;

		if (blinkTimer < 5 && blinkTimer >= 0)
			return RenderHamster.HAMSTER_TEXTURES_BLINK[entity.getColorNumber()];
		else
			return RenderHamster.HAMSTER_TEXTURES[entity.getColorNumber()];


	}

	static class Factory<T extends EntityHamster> implements IRenderFactory<T>
	{
		@Override
		public Render<? super T> createRenderFor(RenderManager manager) {
			return new RenderHamster(manager);
		}
	}
}

/*
 * @Override protected void preRenderCallback(EntityLivingBase entityliving,
 * float f) { preRenderScale((EntityHamster)entityliving, f); }
 */

/*
 * @Override protected void renderEquippedItems(EntityLivingBase entityliving,
 * float f) { ItemStack itemstack = entityliving.getHeldItem(); if(itemstack !=
 * ItemStack.EMPTY) { GL11.glPushMatrix();
 * modelHamsterMain.hamsterLegFrontRight.postRender(0.0625F);
 * GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F); if(itemstack.itemID < 256 &&
 * RenderBlocks.renderItemIn3d(Block.blocksList[itemstack.itemID].getRenderType(
 * ))) { float f1 = 0.5F; GL11.glTranslatef(0.0F, 0.01875F, -0.3125F); f1 *=
 * 0.75F; GL11.glRotatef(20F, 1.0F, 0.0F, 0.0F); GL11.glRotatef(45F, 0.0F, 1.0F,
 * 0.0F); GL11.glScalef(f1, -f1, f1); } else
 * if(Item.itemsList[itemstack.itemID].isFull3D()) { float f2 = 0.625F;
 * GL11.glTranslatef(0.0F, 0.01875F, 0.0F); GL11.glScalef(f2, -f2, f2);
 * GL11.glRotatef(-100F, 1.0F, 0.0F, 0.0F); GL11.glRotatef(45F, 0.0F, 1.0F,
 * 0.0F); } else { float f3 = 0.375F; GL11.glTranslatef(0.25F, 0.01875F,
 * -0.1875F); GL11.glScalef(f3, f3, f3); GL11.glRotatef(60F, 0.0F, 0.0F, 1.0F);
 * GL11.glRotatef(-90F, 1.0F, 0.0F, 0.0F); GL11.glRotatef(20F, 0.0F, 0.0F,
 * 1.0F); } renderManager.itemRenderer.renderItem(entityliving, itemstack, 0);
 * GL11.glPopMatrix(); } }
 *
 *
 * public void doRender(EntityHamster entity, double x, double y, double z,
 * float entityYaw, float partialTicks) { EntityHamster entityHamster =
 * (EntityHamster) entity;
 *
 * if (entityHamster.isChild()) { y -= 0.45D; }
 *
 * super.doRender((EntityHamster)entityHamster, x, y, z, entityYaw,
 * partialTicks); }
 *
 *
 *
 *
 *
 *
 * }
 */
