package com.animania.render.rodents;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

import com.animania.Animania;
import com.animania.models.ModelBall;
import com.animania.models.ModelRendererBall;
import com.animania.models.ModelWaterBottle;
/*
public class RenderHamsterInv implements IItemRenderer{

private ModelWaterBottle modelWaterBottle = new ModelWaterBottle();
private ModelBall modelHamsterBall = new ModelBall();
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		GL11.glEnable(GL11.GL_NORMALIZE);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
		if (item.getItem() == Animania.waterBottle){
			Minecraft.getMinecraft().renderEngine.bindTexture(RenderWaterBottle.location);
			GL11.glTranslatef(0.5f, 1.9f, 0);
			GL11.glRotatef(180, 0, 0, 1);
			GL11.glScalef(1.8f, 1.8f, 1.8f);
			modelWaterBottle.render(null, 0, 0, 0, 0, 0.0F, 0.0625F);
		}
		
		if (item.getItem() == Animania.hamsterBall){
			Minecraft.getMinecraft().renderEngine.bindTexture(ModelRendererBall.resource);
			GL11.glTranslatef(0, 0.8f, 0);
			GL11.glRotatef(180, 0, 0, 1);
			GL11.glScalef(0.9f, 0.9f, 0.9f);
			float[] col = EntitySheep.fleeceColorTable[item.getItemDamage()];
			modelHamsterBall.render(null, item.getItemDamage(), 0, 0, 0, 0.0F, 0.0625F);
		}
	}

}
*/
