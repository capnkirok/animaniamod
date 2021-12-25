package com.animania.addons.extra.client.model.rodents;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.resources.ResourceLocation;

public class ModelRendererBall extends ModelPart
{

	public static final ResourceLocation resource = new ResourceLocation("animania:textures/entity/rodents/hamster_ball.png");

	ModelPart Shape1;
	ModelPart Shape2;
	ModelPart Shape3;
	ModelPart Shape4;
	ModelPart Shape5;
	ModelPart Shape6;
	ModelPart Shape7;
	ModelPart Shape8;
	ModelPart Shape9;
	ModelPart Shape10;
	ModelPart Shape11;
	ModelPart Shape12;
	ModelPart Shape13;
	ModelPart Shape14;
	ModelPart Shape15;
	ModelPart Shape16;

	public int rotation = 0;
	public int color = 0;

	public ModelRendererBall(ModelBase base, int par2, int par3)
	{
		super(base, par2, par3);
		this.textureWidth = 64;
		this.textureHeight = 32;

		this.Shape1 = new ModelRenderer(base, 0, 0);
		this.Shape1.addBox(-5.001F, 4.001F, -5.002F, 10, 1, 10);
		this.Shape1.setRotationPoint(0F, 17F, 0F);
		this.Shape1.setTextureSize(64, 32);
		this.Shape1.mirror = true;
		this.setRotation(this.Shape1, 0F, 0F, 0F);
		this.Shape2 = new ModelRenderer(base, 0, 0);
		this.Shape2.addBox(-5.002F, -6.002F, -5.003F, 10, 1, 10);
		this.Shape2.setRotationPoint(0F, 18F, 0F);
		this.Shape2.setTextureSize(64, 32);
		this.Shape2.mirror = true;
		this.setRotation(this.Shape2, 0F, 0F, 0F);
		this.Shape3 = new ModelRenderer(base, 0, 0);
		this.Shape3.addBox(-5.003F, -5.003F, -5.004F, 1, 8, 10);
		this.Shape3.setRotationPoint(0F, 18F, 0F);
		this.Shape3.setTextureSize(64, 32);
		this.Shape3.mirror = true;
		this.setRotation(this.Shape3, 0F, 0F, 0F);
		this.Shape4 = new ModelRenderer(base, 0, 0);
		this.Shape4.addBox(4.01F, -5.004F, -5.005F, 1, 8, 10);
		this.Shape4.setRotationPoint(0F, 18F, 0F);
		this.Shape4.setTextureSize(64, 32);
		this.Shape4.mirror = true;
		this.setRotation(this.Shape4, 0F, 0F, 0F);
		this.Shape5 = new ModelRenderer(base, 0, 0);
		this.Shape5.addBox(-4.02F, -5.005F, -6.006F, 8, 8, 2);
		this.Shape5.setRotationPoint(0F, 18F, 0F);
		this.Shape5.setTextureSize(64, 32);
		this.Shape5.mirror = true;
		this.setRotation(this.Shape5, 0F, 0F, 0F);
		this.Shape6 = new ModelRenderer(base, 0, 0);
		this.Shape6.addBox(-3.01F, -4.002F, 6.007F, 6, 6, 1);
		this.Shape6.setRotationPoint(0F, 18F, 0F);
		this.Shape6.setTextureSize(64, 32);
		this.Shape6.mirror = true;
		this.setRotation(this.Shape6, 0F, 0F, 0F);
		this.Shape7 = new ModelRenderer(base, 0, 0);
		this.Shape7.addBox(5.01F, -5.006F, -4.003F, 1, 8, 8);
		this.Shape7.setRotationPoint(0F, 18F, 0F);
		this.Shape7.setTextureSize(64, 32);
		this.Shape7.mirror = true;
		this.setRotation(this.Shape7, 0F, 0F, 0F);
		this.Shape8 = new ModelRenderer(base, 0, 0);
		this.Shape8.addBox(-6.01F, -5.007F, -4.005F, 1, 8, 8);
		this.Shape8.setRotationPoint(0F, 18F, 0F);
		this.Shape8.setTextureSize(64, 32);
		this.Shape8.mirror = true;
		this.setRotation(this.Shape8, 0F, 0F, 0F);
		this.Shape9 = new ModelRenderer(base, 0, 0);
		this.Shape9.addBox(-4.01F, -7.004F, -4.009F, 8, 1, 8);
		this.Shape9.setRotationPoint(0F, 18.008F, 0F);
		this.Shape9.setTextureSize(64, 32);
		this.Shape9.mirror = true;
		this.setRotation(this.Shape9, 0F, 0F, 0F);
		this.Shape10 = new ModelRenderer(base, 0, 0);
		this.Shape10.addBox(-4.01F, 5.009F, -4.010F, 8, 1, 8);
		this.Shape10.setRotationPoint(0F, 17F, 0F);
		this.Shape10.setTextureSize(64, 32);
		this.Shape10.mirror = true;
		this.setRotation(this.Shape10, 0F, 0F, 0F);
		this.Shape11 = new ModelRenderer(base, 0, 0);
		this.Shape11.addBox(-3.01F, -4.01F, -7.011F, 6, 6, 1);
		this.Shape11.setRotationPoint(0F, 18F, 0F);
		this.Shape11.setTextureSize(64, 32);
		this.Shape11.mirror = true;
		this.setRotation(this.Shape11, 0F, 0F, 0F);
		this.Shape12 = new ModelRenderer(base, 0, 0);
		this.Shape12.addBox(-7.01F, -4.002F, -3.012F, 1, 6, 6);
		this.Shape12.setRotationPoint(0F, 18F, 0F);
		this.Shape12.setTextureSize(64, 32);
		this.Shape12.mirror = true;
		this.setRotation(this.Shape12, 0F, 0F, 0F);
		this.Shape13 = new ModelRenderer(base, 0, 0);
		this.Shape13.addBox(-4.01F, -5.011F, 4.013F, 8, 8, 2);
		this.Shape13.setRotationPoint(0F, 18F, 0F);
		this.Shape13.setTextureSize(64, 32);
		this.Shape13.mirror = true;
		this.setRotation(this.Shape13, 0F, 0F, 0F);
		this.Shape14 = new ModelRenderer(base, 0, 0);
		this.Shape14.addBox(6.01F, -4.012F, -3.014F, 1, 6, 6);
		this.Shape14.setRotationPoint(0F, 18F, 0F);
		this.Shape14.setTextureSize(64, 32);
		this.Shape14.mirror = true;
		this.setRotation(this.Shape14, 0F, 0F, 0F);
		this.Shape15 = new ModelRenderer(base, 0, 0);
		this.Shape15.addBox(-3.02F, -8.002F, -3.015F, 6, 1, 6);
		this.Shape15.setRotationPoint(0F, 18F, 0F);
		this.Shape15.setTextureSize(64, 32);
		this.Shape15.mirror = true;
		this.setRotation(this.Shape15, 0F, 0F, 0F);
		this.Shape16 = new ModelRenderer(base, 0, 0);
		this.Shape16.addBox(-3.03F, 6.002F, -3.016F, 6, 1, 6);
		this.Shape16.setRotationPoint(0F, 17F, 0F);
		this.Shape16.setTextureSize(64, 32);
		this.Shape16.mirror = true;
		this.setRotation(this.Shape16, 0F, 0F, 0F);
	}

	@Override
	public void render(float f5)
	{
		super.render(f5);
		GL11.glEnable(GL11.GL_NORMALIZE);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		Minecraft.getMinecraft().renderEngine.bindTexture(ModelRendererBall.resource);

		switch (this.color)
		{
		case 0:
			GL11.glColor3f(0.1F, 0.1F, 0.1F); // Black
			break;
		case 1:
			GL11.glColor3f(1.0F, 0.0F, 0.0F); // Red
			break;
		case 2:
			GL11.glColor3f(0.4F, 0.49F, 0.2F); // Green
			break;
		case 3:
			GL11.glColor3f(0.6F, 0.49F, 0.2F); // Brown
			break;
		case 4:
			GL11.glColor3f(0.2F, 0.5F, 1.0F); // Blue
			break;
		case 5:
			GL11.glColor3f(0.5F, 0.25F, 0.7F); // Purple
			break;
		case 6:
			GL11.glColor3f(0.28F, 0.50F, 0.6F); // Cyan
			break;
		case 7:
			GL11.glColor3f(0.6F, 0.6F, 0.6F); // Light Gray
			break;
		case 8:
			GL11.glColor3f(0.3F, 0.3F, 0.3F); // Gray
			break;
		case 9:
			GL11.glColor3f(0.95F, 0.50F, 0.65F); // Pink
			break;
		case 10:
			GL11.glColor3f(0.5F, 0.8F, 0.01F); // Lime
			break;
		case 11:
			GL11.glColor3f(1.0F, 1.0F, 0.0F); // Yellow
			break;
		case 12:
			GL11.glColor3f(0.4F, 0.6F, 0.847F); // Light Blue
			break;
		case 13:
			GL11.glColor3f(0.7F, 0.3F, 0.85F); // Magenta
			break;
		case 14:
			GL11.glColor3f(0.85F, 0.5F, 0.2F); // Orange
			break;
		case 15:
			GL11.glColor3f(1.0F, 1.0F, 1.0F); // White
			break;
		default:
			break;
		}
		GL11.glPushMatrix();
		GL11.glTranslatef(0, 1, 0);
		GL11.glRotatef(this.rotation * 20, 1, 0, 0);
		GL11.glTranslatef(-.1f, -1.9f, 0);
		GL11.glScalef(1.7f, 1.7f, 1.7f);
		this.Shape1.render(f5);
		this.Shape2.render(f5);
		this.Shape3.render(f5);
		this.Shape4.render(f5);
		this.Shape5.render(f5);
		this.Shape6.render(f5);
		this.Shape7.render(f5);
		this.Shape8.render(f5);
		this.Shape9.render(f5);
		this.Shape10.render(f5);
		this.Shape11.render(f5);
		this.Shape12.render(f5);
		this.Shape13.render(f5);
		this.Shape14.render(f5);
		this.Shape15.render(f5);
		this.Shape16.render(f5);
		GL11.glPopMatrix();

		GL11.glColor3f(1.0F, 1.0F, 1.0F); // Clear Colors

	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
