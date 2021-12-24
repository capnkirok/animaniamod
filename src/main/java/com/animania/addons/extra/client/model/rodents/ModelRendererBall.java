package com.animania.addons.extra.client.model.rodents;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.resources.ResourceLocation;

public class ModelRendererBall extends ModelPart
{

    public static final ResourceLocation resource = new ResourceLocation("animania:textures/entity/rodents/hamster_ball.png");

    ModelPart                        Shape1;
    ModelPart                        Shape2;
    ModelPart                        Shape3;
    ModelPart                        Shape4;
    ModelPart                        Shape5;
    ModelPart                        Shape6;
    ModelPart                        Shape7;
    ModelPart                        Shape8;
    ModelPart                        Shape9;
    ModelPart                        Shape10;
    ModelPart                        Shape11;
    ModelPart                        Shape12;
    ModelPart                        Shape13;
    ModelPart                        Shape14;
    ModelPart                        Shape15;
    ModelPart                        Shape16;

    public int                           rotation = 0;
    public int                           color    = 0;

    public ModelRendererBall(ModelBase base, int par2, int par3) {
        super(base, par2, par3);
        this.textureWidth = 64;
        this.textureHeight = 32;

        Shape1 = new ModelRenderer(base, 0, 0);
		Shape1.addBox(-5.001F, 4.001F, -5.002F, 10, 1, 10);
		Shape1.setRotationPoint(0F, 17F, 0F);
		Shape1.setTextureSize(64, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(base, 0, 0);
		Shape2.addBox(-5.002F, -6.002F, -5.003F, 10, 1, 10);
		Shape2.setRotationPoint(0F, 18F, 0F);
		Shape2.setTextureSize(64, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, 0F);
		Shape3 = new ModelRenderer(base, 0, 0);
		Shape3.addBox(-5.003F, -5.003F, -5.004F, 1, 8, 10);
		Shape3.setRotationPoint(0F, 18F, 0F);
		Shape3.setTextureSize(64, 32);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0F);
		Shape4 = new ModelRenderer(base, 0, 0);
		Shape4.addBox(4.01F, -5.004F, -5.005F, 1, 8, 10);
		Shape4.setRotationPoint(0F, 18F, 0F);
		Shape4.setTextureSize(64, 32);
		Shape4.mirror = true;
		setRotation(Shape4, 0F, 0F, 0F);
		Shape5 = new ModelRenderer(base, 0, 0);
		Shape5.addBox(-4.02F, -5.005F, -6.006F, 8, 8, 2);
		Shape5.setRotationPoint(0F, 18F, 0F);
		Shape5.setTextureSize(64, 32);
		Shape5.mirror = true;
		setRotation(Shape5, 0F, 0F, 0F);
		Shape6 = new ModelRenderer(base, 0, 0);
		Shape6.addBox(-3.01F, -4.002F, 6.007F, 6, 6, 1);
		Shape6.setRotationPoint(0F, 18F, 0F);
		Shape6.setTextureSize(64, 32);
		Shape6.mirror = true;
		setRotation(Shape6, 0F, 0F, 0F);
		Shape7 = new ModelRenderer(base, 0, 0);
		Shape7.addBox(5.01F, -5.006F, -4.003F, 1, 8, 8);
		Shape7.setRotationPoint(0F, 18F, 0F);
		Shape7.setTextureSize(64, 32);
		Shape7.mirror = true;
		setRotation(Shape7, 0F, 0F, 0F);
		Shape8 = new ModelRenderer(base, 0, 0);
		Shape8.addBox(-6.01F, -5.007F, -4.005F, 1, 8, 8);
		Shape8.setRotationPoint(0F, 18F, 0F);
		Shape8.setTextureSize(64, 32);
		Shape8.mirror = true;
		setRotation(Shape8, 0F, 0F, 0F);
		Shape9 = new ModelRenderer(base, 0, 0);
		Shape9.addBox(-4.01F, -7.004F, -4.009F, 8, 1, 8);
		Shape9.setRotationPoint(0F, 18.008F, 0F);
		Shape9.setTextureSize(64, 32);
		Shape9.mirror = true;
		setRotation(Shape9, 0F, 0F, 0F);
		Shape10 = new ModelRenderer(base, 0, 0);
		Shape10.addBox(-4.01F, 5.009F, -4.010F, 8, 1, 8);
		Shape10.setRotationPoint(0F, 17F, 0F);
		Shape10.setTextureSize(64, 32);
		Shape10.mirror = true;
		setRotation(Shape10, 0F, 0F, 0F);
		Shape11 = new ModelRenderer(base, 0, 0);
		Shape11.addBox(-3.01F, -4.01F, -7.011F, 6, 6, 1);
		Shape11.setRotationPoint(0F, 18F, 0F);
		Shape11.setTextureSize(64, 32);
		Shape11.mirror = true;
		setRotation(Shape11, 0F, 0F, 0F);
		Shape12 = new ModelRenderer(base, 0, 0);
		Shape12.addBox(-7.01F, -4.002F, -3.012F, 1, 6, 6);
		Shape12.setRotationPoint(0F, 18F, 0F);
		Shape12.setTextureSize(64, 32);
		Shape12.mirror = true;
		setRotation(Shape12, 0F, 0F, 0F);
		Shape13 = new ModelRenderer(base, 0, 0);
		Shape13.addBox(-4.01F, -5.011F, 4.013F, 8, 8, 2);
		Shape13.setRotationPoint(0F, 18F, 0F);
		Shape13.setTextureSize(64, 32);
		Shape13.mirror = true;
		setRotation(Shape13, 0F, 0F, 0F);
		Shape14 = new ModelRenderer(base, 0, 0);
		Shape14.addBox(6.01F, -4.012F, -3.014F, 1, 6, 6);
		Shape14.setRotationPoint(0F, 18F, 0F);
		Shape14.setTextureSize(64, 32);
		Shape14.mirror = true;
		setRotation(Shape14, 0F, 0F, 0F);
		Shape15 = new ModelRenderer(base, 0, 0);
		Shape15.addBox(-3.02F, -8.002F, -3.015F, 6, 1, 6);
		Shape15.setRotationPoint(0F, 18F, 0F);
		Shape15.setTextureSize(64, 32);
		Shape15.mirror = true;
		setRotation(Shape15, 0F, 0F, 0F);
		Shape16 = new ModelRenderer(base, 0, 0);
		Shape16.addBox(-3.03F, 6.002F, -3.016F, 6, 1, 6);
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
        Minecraft.getMinecraft().renderEngine.bindTexture(ModelRendererBall.resource);
       
        if (color == 0) {
			GL11.glColor3f(0.1F, 0.1F, 0.1F); //Black
		} else if (color == 1) {
			GL11.glColor3f(1.0F, 0.0F, 0.0F); //Red
		} else if (color == 2) {
			GL11.glColor3f(0.4F, 0.49F, 0.2F); //Green
		} else if (color == 3) {
			GL11.glColor3f(0.6F, 0.49F, 0.2F); //Brown
		} else if (color == 4) {
			GL11.glColor3f(0.2F, 0.5F, 1.0F); //Blue
		} else if (color == 5) {
			GL11.glColor3f(0.5F, 0.25F, 0.7F); //Purple
		} else if (color == 6) {
			GL11.glColor3f(0.28F, 0.50F, 0.6F); //Cyan
		} else if (color == 7) {
			GL11.glColor3f(0.6F, 0.6F, 0.6F); //Light Gray
		} else if (color == 8) {
			GL11.glColor3f(0.3F, 0.3F, 0.3F); //Gray
		} else if (color == 9) {
			GL11.glColor3f(0.95F, 0.50F, 0.65F); //Pink
		} else if (color == 10) {
			GL11.glColor3f(0.5F, 0.8F, 0.01F); //Lime
		} else if (color == 11) {
			GL11.glColor3f(1.0F, 1.0F, 0.0F); //Yellow
		} else if (color == 12) {
			GL11.glColor3f(0.4F, 0.6F, 0.847F); //Light Blue
		} else if (color == 13) {
			GL11.glColor3f(0.7F, 0.3F, 0.85F); //Magenta
		} else if (color == 14) {
			GL11.glColor3f(0.85F, 0.5F, 0.2F); //Orange
		} else if (color == 15) {
			GL11.glColor3f(1.0F, 1.0F, 1.0F); //White
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
        
        GL11.glColor3f(1.0F, 1.0F, 1.0F); //Clear Colors
        
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
