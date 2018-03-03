package com.animania.client.models;

import org.lwjgl.opengl.GL11;

import com.animania.common.entities.cows.EntityCowHighland;
import com.animania.common.entities.cows.EntityCowLonghorn;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelCowLonghorn extends ModelBase
{
    private float        headRotationAngleX;
    public ModelRenderer head          = new ModelRenderer(this, 0, 0);
    ModelRenderer        body;
    ModelRenderer        udder;
    ModelRenderer        udderA;
    ModelRenderer        udderC;
    ModelRenderer        udderB;
    ModelRenderer        udderD;
    ModelRenderer        Horn1;
    ModelRenderer        Horn1A;
    ModelRenderer        Horn1B;
    ModelRenderer        Horn1C;
    ModelRenderer        Horn2;
    ModelRenderer        Horn2A;
    ModelRenderer        Horn2B;
    ModelRenderer        Horn2C;
    ModelRenderer        Snout;
    ModelRenderer        EarL;
    ModelRenderer        EarLa;
    ModelRenderer        EarR;
    ModelRenderer        EarRa;
    ModelRenderer        TailTop;
    ModelRenderer        Tail;
    ModelRenderer        TailHair1;
    ModelRenderer        TailHair2;
    ModelRenderer        leg1;
    ModelRenderer        leg2;
    ModelRenderer        leg3;
    ModelRenderer        leg4;
    protected float      field_78145_g = 8.0F;
    protected float      field_78151_h = 4.0F;

    public ModelCowLonghorn() {
        this.head.setTextureSize(64, 64);
        this.head.addBox(-4F, -4F, -3F, 8, 8, 6);
        this.head.setRotationPoint(0F, 5F, -12F);

        this.body = new ModelRenderer(this, 18, 4);
        this.body.setTextureSize(64, 64);
        this.body.addBox(-6F, -9F, -5F, 12, 18, 10);
        this.body.setRotationPoint(0F, 8F, 0F);

        this.udder = new ModelRenderer(this, 52, 0);
        this.udder.setTextureSize(64, 64);
        this.udder.addBox(-2F, -3F, -1F, 4, 6, 2);
        this.udder.setRotationPoint(0F, 14F, 5.5F);
        this.udderA = new ModelRenderer(this, 54, 9);
        this.udderA.setTextureSize(64, 64);
        this.udderA.addBox(-0.5F, -0.5F, -1F, 1, 1, 2);
        this.udderA.setRotationPoint(-1.5F, 15F, 4F);
        this.udderC = new ModelRenderer(this, 54, 9);
        this.udderC.setTextureSize(64, 64);
        this.udderC.addBox(-0.5F, -0.5F, -1F, 1, 1, 2);
        this.udderC.setRotationPoint(1.5F, 15F, 7F);
        this.udderB = new ModelRenderer(this, 54, 9);
        this.udderB.setTextureSize(64, 64);
        this.udderB.addBox(-0.5F, -0.5F, -1F, 1, 1, 2);
        this.udderB.setRotationPoint(-1.5F, 15F, 7F);
        this.udderD = new ModelRenderer(this, 54, 9);
        this.udderD.setTextureSize(64, 64);
        this.udderD.addBox(-0.5F, -0.5F, -1F, 1, 1, 2);
        this.udderD.setRotationPoint(1.5F, 15F, 4F);

        this.leg1 = new ModelRenderer(this, 0, 32);
        this.leg1.setTextureSize(64, 64);
        this.leg1.addBox(-2F, 0F, -2F, 4, 12, 4);
        this.leg1.setRotationPoint(-4F, 13F, -7F);
        this.leg2 = new ModelRenderer(this, 24, 32);
        this.leg2.setTextureSize(64, 64);
        this.leg2.addBox(-2F, 0F, -2F, 4, 12, 4);
        this.leg2.setRotationPoint(4F, 13F, -7F);
        this.leg3 = new ModelRenderer(this, 40, 32);
        this.leg3.setTextureSize(64, 64);
        this.leg3.addBox(-2F, 0F, -2F, 4, 12, 4);
        this.leg3.setRotationPoint(4F, 13F, 7F);
        this.leg4 = new ModelRenderer(this, 0, 16);
        this.leg4.setTextureSize(64, 64);
        this.leg4.addBox(-2F, 0F, -2F, 4, 12, 4);
        this.leg4.setRotationPoint(-4F, 13F, 7F);

        this.Horn1 = new ModelRenderer(this, 1, 59);
        this.Horn1.setTextureSize(64, 64);
        this.Horn1.addBox(-2.5F, -1F, -1F, 5, 2, 2);
        this.Horn1.setRotationPoint(6F, -2.5F, 0F);

        this.Horn1A = new ModelRenderer(this, 17, 59);
        this.Horn1A.setTextureSize(64, 64);
        this.Horn1A.addBox(-1F, -1F, -1F, 2, 2, 2);
        this.Horn1A.setRotationPoint(9.5F, -3.5F, 0F);

        this.Horn1B = new ModelRenderer(this, 25, 59);
        this.Horn1B.setTextureSize(64, 64);
        this.Horn1B.addBox(-0.5F, -1F, -1F, 1, 2, 2);
        this.Horn1B.setRotationPoint(11F, -4.5F, 0F);

        this.Horn1C = new ModelRenderer(this, 26, 60);
        this.Horn1C.setTextureSize(64, 64);
        this.Horn1C.addBox(-0.5F, -1F, -0.5F, 1, 2, 1);
        this.Horn1C.setRotationPoint(12F, -5.5F, 0F);

        this.Horn2 = new ModelRenderer(this, 1, 59);
        this.Horn2.setTextureSize(64, 64);
        this.Horn2.addBox(-2.5F, -1F, -1F, 5, 2, 2);
        this.Horn2.setRotationPoint(-6F, -2.5F, 0F);

        this.Horn2A = new ModelRenderer(this, 17, 59);
        this.Horn2A.setTextureSize(64, 64);
        this.Horn2A.addBox(-1F, -1F, -1F, 2, 2, 2);
        this.Horn2A.setRotationPoint(-9.5F, -3.5F, 0F);

        this.Horn2B = new ModelRenderer(this, 25, 59);
        this.Horn2B.setTextureSize(64, 64);
        this.Horn2B.addBox(-0.5F, -1F, -1F, 1, 2, 2);
        this.Horn2B.setRotationPoint(-11F, -4.5F, 0F);

        this.Horn2C = new ModelRenderer(this, 26, 60);
        this.Horn2C.setTextureSize(64, 64);
        this.Horn2C.addBox(-0.5F, -1F, -0.5F, 1, 2, 1);
        this.Horn2C.setRotationPoint(-12F, -5.5F, 0F);

        this.Snout = new ModelRenderer(this, 49, 50);
        this.Snout.setTextureSize(64, 64);
        this.Snout.addBox(-2F, -2F, -1.5F, 4, 4, 3);
        this.Snout.setRotationPoint(0F, 3F, -2.5F);
        this.EarL = new ModelRenderer(this, 39, 53);
        this.EarL.setTextureSize(64, 64);
        this.EarL.addBox(-1.5F, -1F, -0.5F, 3, 2, 1);
        this.EarL.setRotationPoint(-5.5F, -1.5F, 1F);
        this.EarLa = new ModelRenderer(this, 45, 50);
        this.EarLa.setTextureSize(64, 64);
        this.EarLa.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
        this.EarLa.setRotationPoint(-4.5F, -.04F, 0.69718F);

        this.EarR = new ModelRenderer(this, 39, 53);
        this.EarR.setTextureSize(64, 64);
        this.EarR.addBox(-1.5F, -1F, -0.5F, 3, 2, 1);
        // EarR.setRotationPoint(5.5F, 3.5F, -13F);
        // head.setRotationPoint(0F, 5F, -12F);
        this.EarR.setRotationPoint(5.5F, -1.5F, 1F);
        this.EarRa = new ModelRenderer(this, 41, 50);
        this.EarRa.setTextureSize(64, 64);
        this.EarRa.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
        // head.setRotationPoint(0F, 5F, -12F);
        // EarRa.setRotationPoint(4.5F, 4.969114F, -12.69718F);
        this.EarRa.setRotationPoint(4.5F, -.04F, 0.69718F);

        this.TailTop = new ModelRenderer(this, 32, 49);
        this.TailTop.setTextureSize(64, 64);
        this.TailTop.addBox(-1F, -0.5F, 0F, 2, 1, 1);
        this.TailTop.setRotationPoint(0F, 4.0F, 9F);
        // TailTop.setRotationPoint(0F, 0F, 0F);
        this.Tail = new ModelRenderer(this, 27, 51);
        this.Tail.setTextureSize(64, 64);
        this.Tail.addBox(-0.5F, -1.0F, -5.7F, 1, 1, 6);
        this.Tail.setRotationPoint(0F, 4F, 9F);
        this.TailHair1 = new ModelRenderer(this, 23, 52);
        this.TailHair1.setTextureSize(64, 64);
        this.TailHair1.addBox(-1F, 0.0F, -4F, 2, 0, 3);
        this.TailHair1.setRotationPoint(0F, -.5F, -4.64188F);
        // Tail.setRotationPoint(0F, 4F, 9F);
        // TailHair1.setRotationPoint(0F, 8.189776F, 10.64188F);

        this.TailHair2 = new ModelRenderer(this, 23, 52);
        this.TailHair2.setTextureSize(64, 64);
        this.TailHair2.addBox(-1F, 0.0F, -4F, 2, 0, 3);
        this.TailHair2.setRotationPoint(2.010928E-07F, -.5F, -4.64188F);
        // TailHair2.setRotationPoint(2.010928E-07F, 8.189775F, 10.64188F);
        // TailHair2.setRotationPoint(2.010928E-07F, 4.189775F, 1.64188F);

        this.head.addChild(this.Horn1);
        this.head.addChild(this.Horn1A);
        this.head.addChild(this.Horn1B);
        this.head.addChild(this.Horn1C);
        this.head.addChild(this.Horn2);
        this.head.addChild(this.Horn2A);
        this.head.addChild(this.Horn2B);
        this.head.addChild(this.Horn2C);
        this.head.addChild(this.Snout);
        this.head.addChild(this.EarL);
        this.head.addChild(this.EarLa);
        this.head.addChild(this.EarR);
        this.head.addChild(this.EarRa);

        // this.Tail.addChild(this.TailTop);
        this.Tail.addChild(this.TailHair1);
        this.Tail.addChild(this.TailHair2);

    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    @Override
    public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_,
            float p_78088_7_) {

        this.Tail.rotateAngleX = -2F;
        this.Tail.rotateAngleY = -3.141593F;
        this.Tail.rotateAngleZ = -3.141593F;
        this.TailHair1.rotateAngleZ = -2.280276F;
        this.TailHair2.rotateAngleZ = 2.432113F;

        this.setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);

        if (this.isChild) {
            float f6 = 2.0F;
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0F, this.field_78145_g * p_78088_7_, this.field_78151_h * p_78088_7_);
            this.head.render(p_78088_7_);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
            GL11.glTranslatef(0.0F, 24.0F * p_78088_7_, 0.0F);
            this.body.render(p_78088_7_);
            this.leg1.render(p_78088_7_);
            this.leg2.render(p_78088_7_);
            this.leg3.render(p_78088_7_);
            this.leg4.render(p_78088_7_);
            GL11.glPopMatrix();
            // TODO add other child stuff
        }
        else {
            this.head.render(p_78088_7_);
            this.body.render(p_78088_7_);
            this.leg1.render(p_78088_7_);
            this.leg2.render(p_78088_7_);
            this.leg3.render(p_78088_7_);
            this.leg4.render(p_78088_7_);
            this.udder.render(p_78088_7_);
            this.udderA.render(p_78088_7_);
            this.udderC.render(p_78088_7_);
            this.udderD.render(p_78088_7_);
            this.udderB.render(p_78088_7_);

            this.TailTop.render(p_78088_7_);
            this.Tail.render(p_78088_7_);

        }
    }

    @Override
    public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float p_78086_2_, float p_78086_3_, float partialTickTime) {

        super.setLivingAnimations(entitylivingbaseIn, p_78086_2_, p_78086_3_, partialTickTime);

        if (entitylivingbaseIn instanceof EntityCowLonghorn) {
            this.head.rotationPointY = 6.0F + ((EntityCowLonghorn) entitylivingbaseIn).getHeadRotationPointY(partialTickTime) * 9.0F;
            this.headRotationAngleX = ((EntityCowLonghorn) entitylivingbaseIn).getHeadRotationAngleX(partialTickTime);
        } else  if (entitylivingbaseIn instanceof EntityCowHighland) {
            this.head.rotationPointY = 6.0F + ((EntityCowHighland) entitylivingbaseIn).getHeadRotationPointY(partialTickTime) * 9.0F;
            this.headRotationAngleX = ((EntityCowHighland) entitylivingbaseIn).getHeadRotationAngleX(partialTickTime);
        }
    }

    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {
        float f6 = 180F / (float) Math.PI;
        this.head.rotateAngleX = par5 / (180F / (float) Math.PI);
        this.head.rotateAngleY = par4 / (180F / (float) Math.PI);
        this.head.rotateAngleX = this.headRotationAngleX;
        this.body.rotateAngleX = (float) Math.PI / 2F;

        this.udder.rotateAngleX = (float) Math.PI / 2F;
        this.udderA.rotateAngleX = (float) Math.PI / 2F;
        this.udderC.rotateAngleX = (float) Math.PI / 2F;
        this.udderD.rotateAngleX = (float) Math.PI / 2F;
        this.udderB.rotateAngleX = (float) Math.PI / 2F;

        this.TailTop.rotateAngleX = (float) Math.PI / 2F;
        this.Tail.rotateAngleY = MathHelper.sin(par3 * 3.141593F * 0.05F) * MathHelper.sin(par3 * 3.141593F * .03F * 0.05F) * 0.15F * 3.141593F;

        this.leg1.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
        this.leg2.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
        this.leg3.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
        this.leg4.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
    }
}
