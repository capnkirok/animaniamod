package com.animania.models;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class ModelRendererBall extends ModelRenderer {
	
	public static final ResourceLocation resource = new ResourceLocation("animania:entity/hamsters/hamster_ball.png");

	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape5;
	ModelRenderer Shape6;
	ModelRenderer Shape7;
	ModelRenderer Shape8;
	ModelRenderer Shape9;
	ModelRenderer Shape10;
	ModelRenderer Shape11;
	ModelRenderer Shape12;
	ModelRenderer Shape13;
	ModelRenderer Shape14;
	ModelRenderer Shape15;
	ModelRenderer Shape16;
	
	public int rotation = 0;
	public int color = 0;

	public ModelRendererBall(ModelBase base, int par2, int par3) {
		super(base, par2, par3);
		textureWidth = 64;
		textureHeight = 32;

		Shape1 = new ModelRenderer(base, 0, 0);
		Shape1.addBox(-5F, 4F, -5F, 10, 1, 10);
		Shape1.setRotationPoint(0F, 17F, 0F);
		Shape1.setTextureSize(64, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(base, 0, 0);
		Shape2.addBox(-5F, -6F, -5F, 10, 1, 10);
		Shape2.setRotationPoint(0F, 18F, 0F);
		Shape2.setTextureSize(64, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, 0F);
		Shape3 = new ModelRenderer(base, 0, 0);
		Shape3.addBox(-5F, -5F, -5F, 1, 8, 10);
		Shape3.setRotationPoint(0F, 18F, 0F);
		Shape3.setTextureSize(64, 32);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0F);
		Shape4 = new ModelRenderer(base, 0, 0);
		Shape4.addBox(4F, -5F, -5F, 1, 8, 10);
		Shape4.setRotationPoint(0F, 18F, 0F);
		Shape4.setTextureSize(64, 32);
		Shape4.mirror = true;
		setRotation(Shape4, 0F, 0F, 0F);
		Shape5 = new ModelRenderer(base, 0, 0);
		Shape5.addBox(-4F, -5F, -6F, 8, 8, 2);
		Shape5.setRotationPoint(0F, 18F, 0F);
		Shape5.setTextureSize(64, 32);
		Shape5.mirror = true;
		setRotation(Shape5, 0F, 0F, 0F);
		Shape6 = new ModelRenderer(base, 0, 0);
		Shape6.addBox(-3F, -4F, 6F, 6, 6, 1);
		Shape6.setRotationPoint(0F, 18F, 0F);
		Shape6.setTextureSize(64, 32);
		Shape6.mirror = true;
		setRotation(Shape6, 0F, 0F, 0F);
		Shape7 = new ModelRenderer(base, 0, 0);
		Shape7.addBox(5F, -5F, -4F, 1, 8, 8);
		Shape7.setRotationPoint(0F, 18F, 0F);
		Shape7.setTextureSize(64, 32);
		Shape7.mirror = true;
		setRotation(Shape7, 0F, 0F, 0F);
		Shape8 = new ModelRenderer(base, 0, 0);
		Shape8.addBox(-6F, -5F, -4F, 1, 8, 8);
		Shape8.setRotationPoint(0F, 18F, 0F);
		Shape8.setTextureSize(64, 32);
		Shape8.mirror = true;
		setRotation(Shape8, 0F, 0F, 0F);
		Shape9 = new ModelRenderer(base, 0, 0);
		Shape9.addBox(-4F, -7F, -4F, 8, 1, 8);
		Shape9.setRotationPoint(0F, 18F, 0F);
		Shape9.setTextureSize(64, 32);
		Shape9.mirror = true;
		setRotation(Shape9, 0F, 0F, 0F);
		Shape10 = new ModelRenderer(base, 0, 0);
		Shape10.addBox(-4F, 5F, -4F, 8, 1, 8);
		Shape10.setRotationPoint(0F, 17F, 0F);
		Shape10.setTextureSize(64, 32);
		Shape10.mirror = true;
		setRotation(Shape10, 0F, 0F, 0F);
		Shape11 = new ModelRenderer(base, 0, 0);
		Shape11.addBox(-3F, -4F, -7F, 6, 6, 1);
		Shape11.setRotationPoint(0F, 18F, 0F);
		Shape11.setTextureSize(64, 32);
		Shape11.mirror = true;
		setRotation(Shape11, 0F, 0F, 0F);
		Shape12 = new ModelRenderer(base, 0, 0);
		Shape12.addBox(-7F, -4F, -3F, 1, 6, 6);
		Shape12.setRotationPoint(0F, 18F, 0F);
		Shape12.setTextureSize(64, 32);
		Shape12.mirror = true;
		setRotation(Shape12, 0F, 0F, 0F);
		Shape13 = new ModelRenderer(base, 0, 0);
		Shape13.addBox(-4F, -5F, 4F, 8, 8, 2);
		Shape13.setRotationPoint(0F, 18F, 0F);
		Shape13.setTextureSize(64, 32);
		Shape13.mirror = true;
		setRotation(Shape13, 0F, 0F, 0F);
		Shape14 = new ModelRenderer(base, 0, 0);
		Shape14.addBox(6F, -4F, -3F, 1, 6, 6);
		Shape14.setRotationPoint(0F, 18F, 0F);
		Shape14.setTextureSize(64, 32);
		Shape14.mirror = true;
		setRotation(Shape14, 0F, 0F, 0F);
		Shape15 = new ModelRenderer(base, 0, 0);
		Shape15.addBox(-3F, -8F, -3F, 6, 1, 6);
		Shape15.setRotationPoint(0F, 18F, 0F);
		Shape15.setTextureSize(64, 32);
		Shape15.mirror = true;
		setRotation(Shape15, 0F, 0F, 0F);
		Shape16 = new ModelRenderer(base, 0, 0);
		Shape16.addBox(-3F, 6F, -3F, 6, 1, 6);
		Shape16.setRotationPoint(0F, 17F, 0F);
		Shape16.setTextureSize(64, 32);
		Shape16.mirror = true;
		setRotation(Shape16, 0F, 0F, 0F);
	}

	@Override
	public void render(float f5) {
		super.render(f5);
		GL11.glEnable(GL11.GL_NORMALIZE);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		Minecraft.getMinecraft().renderEngine.bindTexture(resource);
		Random rand = new Random();
		EnumDyeColor col = EntitySheep.getRandomSheepColor(rand);
		//TODO GL11.glColor3f(col[0], col[1], col[2]);
		
		GL11.glPushMatrix();
		GL11.glTranslatef(0, 1, 0);
		GL11.glRotatef(rotation * 20, 1, 0, 0);
		GL11.glTranslatef(0, -1.9f, 0);
		GL11.glScalef(1.7f, 1.7f, 1.7f);
		Shape1.render(f5);
		Shape2.render(f5);
		Shape3.render(f5);
		Shape4.render(f5);
		Shape5.render(f5);
		Shape6.render(f5);
		Shape7.render(f5);
		Shape8.render(f5);
		Shape9.render(f5);
		Shape10.render(f5);
		Shape11.render(f5);
		Shape12.render(f5);
		Shape13.render(f5);
		Shape14.render(f5);
		Shape15.render(f5);
		Shape16.render(f5);
		GL11.glPopMatrix();
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
