package com.animania.client.models;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.ResourceLocation;

public class ModelRendererBall extends ModelRenderer
{

    public static final ResourceLocation resource = new ResourceLocation("animania:entity/hamsters/hamster_ball.png");

    ModelRenderer                        Shape1;
    ModelRenderer                        Shape2;
    ModelRenderer                        Shape3;
    ModelRenderer                        Shape4;
    ModelRenderer                        Shape5;
    ModelRenderer                        Shape6;
    ModelRenderer                        Shape7;
    ModelRenderer                        Shape8;
    ModelRenderer                        Shape9;
    ModelRenderer                        Shape10;
    ModelRenderer                        Shape11;
    ModelRenderer                        Shape12;
    ModelRenderer                        Shape13;
    ModelRenderer                        Shape14;
    ModelRenderer                        Shape15;
    ModelRenderer                        Shape16;

    public int                           rotation = 0;
    public int                           color    = 0;

    public ModelRendererBall(ModelBase base, int par2, int par3) {
        super(base, par2, par3);
        this.textureWidth = 64;
        this.textureHeight = 32;

        this.Shape1 = new ModelRenderer(base, 0, 0);
        this.Shape1.addBox(-5F, 4F, -5F, 10, 1, 10);
        this.Shape1.setRotationPoint(0F, 17F, 0F);
        this.Shape1.setTextureSize(64, 32);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0F, 0F, 0F);
        this.Shape2 = new ModelRenderer(base, 0, 0);
        this.Shape2.addBox(-5F, -6F, -5F, 10, 1, 10);
        this.Shape2.setRotationPoint(0F, 18F, 0F);
        this.Shape2.setTextureSize(64, 32);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0F, 0F, 0F);
        this.Shape3 = new ModelRenderer(base, 0, 0);
        this.Shape3.addBox(-5F, -5F, -5F, 1, 8, 10);
        this.Shape3.setRotationPoint(0F, 18F, 0F);
        this.Shape3.setTextureSize(64, 32);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0F, 0F, 0F);
        this.Shape4 = new ModelRenderer(base, 0, 0);
        this.Shape4.addBox(4F, -5F, -5F, 1, 8, 10);
        this.Shape4.setRotationPoint(0F, 18F, 0F);
        this.Shape4.setTextureSize(64, 32);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, 0F, 0F, 0F);
        this.Shape5 = new ModelRenderer(base, 0, 0);
        this.Shape5.addBox(-4F, -5F, -6F, 8, 8, 2);
        this.Shape5.setRotationPoint(0F, 18F, 0F);
        this.Shape5.setTextureSize(64, 32);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, 0F, 0F, 0F);
        this.Shape6 = new ModelRenderer(base, 0, 0);
        this.Shape6.addBox(-3F, -4F, 6F, 6, 6, 1);
        this.Shape6.setRotationPoint(0F, 18F, 0F);
        this.Shape6.setTextureSize(64, 32);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, 0F, 0F, 0F);
        this.Shape7 = new ModelRenderer(base, 0, 0);
        this.Shape7.addBox(5F, -5F, -4F, 1, 8, 8);
        this.Shape7.setRotationPoint(0F, 18F, 0F);
        this.Shape7.setTextureSize(64, 32);
        this.Shape7.mirror = true;
        this.setRotation(this.Shape7, 0F, 0F, 0F);
        this.Shape8 = new ModelRenderer(base, 0, 0);
        this.Shape8.addBox(-6F, -5F, -4F, 1, 8, 8);
        this.Shape8.setRotationPoint(0F, 18F, 0F);
        this.Shape8.setTextureSize(64, 32);
        this.Shape8.mirror = true;
        this.setRotation(this.Shape8, 0F, 0F, 0F);
        this.Shape9 = new ModelRenderer(base, 0, 0);
        this.Shape9.addBox(-4F, -7F, -4F, 8, 1, 8);
        this.Shape9.setRotationPoint(0F, 18F, 0F);
        this.Shape9.setTextureSize(64, 32);
        this.Shape9.mirror = true;
        this.setRotation(this.Shape9, 0F, 0F, 0F);
        this.Shape10 = new ModelRenderer(base, 0, 0);
        this.Shape10.addBox(-4F, 5F, -4F, 8, 1, 8);
        this.Shape10.setRotationPoint(0F, 17F, 0F);
        this.Shape10.setTextureSize(64, 32);
        this.Shape10.mirror = true;
        this.setRotation(this.Shape10, 0F, 0F, 0F);
        this.Shape11 = new ModelRenderer(base, 0, 0);
        this.Shape11.addBox(-3F, -4F, -7F, 6, 6, 1);
        this.Shape11.setRotationPoint(0F, 18F, 0F);
        this.Shape11.setTextureSize(64, 32);
        this.Shape11.mirror = true;
        this.setRotation(this.Shape11, 0F, 0F, 0F);
        this.Shape12 = new ModelRenderer(base, 0, 0);
        this.Shape12.addBox(-7F, -4F, -3F, 1, 6, 6);
        this.Shape12.setRotationPoint(0F, 18F, 0F);
        this.Shape12.setTextureSize(64, 32);
        this.Shape12.mirror = true;
        this.setRotation(this.Shape12, 0F, 0F, 0F);
        this.Shape13 = new ModelRenderer(base, 0, 0);
        this.Shape13.addBox(-4F, -5F, 4F, 8, 8, 2);
        this.Shape13.setRotationPoint(0F, 18F, 0F);
        this.Shape13.setTextureSize(64, 32);
        this.Shape13.mirror = true;
        this.setRotation(this.Shape13, 0F, 0F, 0F);
        this.Shape14 = new ModelRenderer(base, 0, 0);
        this.Shape14.addBox(6F, -4F, -3F, 1, 6, 6);
        this.Shape14.setRotationPoint(0F, 18F, 0F);
        this.Shape14.setTextureSize(64, 32);
        this.Shape14.mirror = true;
        this.setRotation(this.Shape14, 0F, 0F, 0F);
        this.Shape15 = new ModelRenderer(base, 0, 0);
        this.Shape15.addBox(-3F, -8F, -3F, 6, 1, 6);
        this.Shape15.setRotationPoint(0F, 18F, 0F);
        this.Shape15.setTextureSize(64, 32);
        this.Shape15.mirror = true;
        this.setRotation(this.Shape15, 0F, 0F, 0F);
        this.Shape16 = new ModelRenderer(base, 0, 0);
        this.Shape16.addBox(-3F, 6F, -3F, 6, 1, 6);
        this.Shape16.setRotationPoint(0F, 17F, 0F);
        this.Shape16.setTextureSize(64, 32);
        this.Shape16.mirror = true;
        this.setRotation(this.Shape16, 0F, 0F, 0F);
    }

    @Override
    public void render(float f5) {
        super.render(f5);
        GL11.glEnable(GL11.GL_NORMALIZE);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        Minecraft.getMinecraft().renderEngine.bindTexture(ModelRendererBall.resource);
        Random rand = new Random();
        EnumDyeColor col = EntitySheep.getRandomSheepColor(rand);
        // TODO GL11.glColor3f(col[0], col[1], col[2]);

        GL11.glPushMatrix();
        GL11.glTranslatef(0, 1, 0);
        GL11.glRotatef(this.rotation * 20, 1, 0, 0);
        GL11.glTranslatef(0, -1.9f, 0);
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
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
