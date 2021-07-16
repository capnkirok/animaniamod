package com.animania.addons.farm.client.model.cow;

import com.animania.addons.farm.common.entity.cows.CowAngus.EntityCalfAngus;
import com.animania.addons.farm.common.entity.cows.CowFriesian.EntityCalfFriesian;
import com.animania.addons.farm.common.entity.cows.CowHereford.EntityCalfHereford;
import com.animania.addons.farm.common.entity.cows.CowHighland.EntityCalfHighland;
import com.animania.addons.farm.common.entity.cows.CowHolstein.EntityCalfHolstein;
import com.animania.addons.farm.common.entity.cows.CowJersey.EntityCalfJersey;
import com.animania.addons.farm.common.entity.cows.CowMooshroom.EntityCalfMooshroom;
import com.animania.addons.farm.common.entity.cows.EntityAnimaniaCow;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Dist.CLIENT)
public class ModelCalf extends ModelBase
{
    private float        headRotationAngleX;
    public ModelRenderer Head          = new ModelRenderer(this, 0, 0);
    ModelRenderer        Body;
    ModelRenderer        Horn1;
    ModelRenderer        Horn2;
    ModelRenderer        Snout;
    ModelRenderer        EarL;
    ModelRenderer        EarR;
    ModelRenderer        TailTop;
    ModelRenderer        Tail;
    ModelRenderer        TailHair1;
    ModelRenderer        TailHair2;
    ModelRenderer        Leg0;
    ModelRenderer        Leg1;
    ModelRenderer        Leg2;
    ModelRenderer        Leg3;
    protected float      field_78145_g = 8.0F;
    protected float      field_78151_h = 4.0F;

    public ModelCalf() {
        this.Body = new ModelRenderer(this, 29, 9);
        this.Body.setTextureSize(64, 64);
        this.Body.addBox(-3F, -4.5F, -2.5F, 6, 9, 5);
        this.Body.setRotationPoint(0F, 13.5F, 0F);

        this.Head = new ModelRenderer(this, 3, 4);
        this.Head.setTextureSize(64, 64);
        this.Head.addBox(-3.01F, -3F, -2F, 6, 6, 4);
        this.Head.setRotationPoint(1.907349E-06F, 11.5F, -6.5F); // Was 11.5

        this.EarL = new ModelRenderer(this, 6, 48);
        this.EarL.setTextureSize(64, 64);
        this.EarL.addBox(-1F, -1F, -0.5F, 2, 2, 1);
        this.EarL.setRotationPoint(-4F, -1F, 0F);

        this.EarR = new ModelRenderer(this, 0, 48);
        this.EarR.setTextureSize(64, 64);
        this.EarR.addBox(-1F, -1F, -0.5F, 2, 2, 1);
        this.EarR.setRotationPoint(4F, -1F, 0F);

        this.Snout = new ModelRenderer(this, 50, 51);
        this.Snout.setTextureSize(64, 64);
        this.Snout.addBox(-2F, -1.5F, -1F, 4, 3, 2);
        this.Snout.setRotationPoint(0F, 2F, -1.5F);

        this.Horn1 = new ModelRenderer(this, 22, 0);
        this.Horn1.setTextureSize(64, 64);
        this.Horn1.addBox(-0.5F, -1F, -0.5F, 1, 2, 1);
        this.Horn1.setRotationPoint(-2F, -3.5F, 0F);

        this.Horn2 = new ModelRenderer(this, 22, 0);
        this.Horn2.setTextureSize(64, 64);
        this.Horn2.addBox(-0.5F, -1F, -0.5F, 1, 2, 1);
        this.Horn2.setRotationPoint(2F, -3.5F, 0F);

        this.TailTop = new ModelRenderer(this, 32, 49);
        this.TailTop.setTextureSize(64, 64);
        this.TailTop.addBox(-0.5F, -0.5F, 0F, 1, 1, 1);
        this.TailTop.setRotationPoint(0F, 12.1F, 4.5F);

        this.Tail = new ModelRenderer(this, 28, 15);
        this.Tail.setTextureSize(64, 64);
        this.Tail.addBox(-0.5F, -0.5F, -2F, 1, 1, 2);
        this.Tail.setRotationPoint(0F, 12F, 4.5F);

        this.TailHair1 = new ModelRenderer(this, 23, 52);
        this.TailHair1.setTextureSize(64, 64);
        this.TailHair1.addBox(-1F, 0F, -3.05F, 2, 0, 3);
        this.TailHair1.setRotationPoint(0F, 0.25F, -1.3F);

        this.TailHair2 = new ModelRenderer(this, 23, 52);
        this.TailHair2.setTextureSize(64, 64);
        this.TailHair2.addBox(-1F, 0F, -2.5F, 2, 0, 3);
        this.TailHair2.setRotationPoint(-1.769535E-16F, 0F, -2.547969F);

        this.Leg0 = new ModelRenderer(this, 6, 34);
        this.Leg0.setTextureSize(64, 64);
        this.Leg0.addBox(-1F, 1F, -1F, 2, 8, 2);
        this.Leg0.setRotationPoint(-2F, 15F, -3.5F);
        this.Leg1 = new ModelRenderer(this, 16, 34);
        this.Leg1.setTextureSize(64, 64);
        this.Leg1.addBox(-1F, 1F, -1F, 2, 8, 2);
        this.Leg1.setRotationPoint(2F, 15F, -3.5F);
        this.Leg2 = new ModelRenderer(this, 16, 34);
        this.Leg2.setTextureSize(64, 64);
        this.Leg2.addBox(-1F, 1F, -1F, 2, 8, 2);
        this.Leg2.setRotationPoint(-2F, 15F, 3.5F);
        this.Leg3 = new ModelRenderer(this, 12, 34);
        this.Leg3.setTextureSize(64, 64);
        this.Leg3.addBox(-1F, 1F, -1F, 2, 8, 2);
        this.Leg3.setRotationPoint(2F, 15F, 3.5F);

        this.Head.addChild(this.Horn1);
        this.Head.addChild(this.Horn2);
       
        this.Head.addChild(this.Snout);
        this.Head.addChild(this.EarL);
        this.Head.addChild(this.EarR);
        this.Tail.addChild(this.TailHair1);
        this.Tail.addChild(this.TailHair2);

    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    @Override
    public void render(Entity entity, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float scale) {

        this.Tail.rotateAngleX = -2F;
        this.Tail.rotateAngleY = -3.141593F;
        this.Tail.rotateAngleZ = -3.141593F;
        this.TailHair1.rotateAngleZ = -2.280276F;
        this.TailHair2.rotateAngleZ = 2.432113F;

        this.setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, scale, entity);

        boolean isSleeping = false;
		EntityAnimaniaCow ech = (EntityAnimaniaCow) entity;
		if (ech.getSleeping()) {
			isSleeping = true;
		}
		
		float sleepTimer = ech.getSleepTimer();

		if (isSleeping) {

			this.Leg0.rotateAngleX = sleepTimer * -1.8F;
			this.Leg0.render(scale * .95F);
			this.Leg1.rotateAngleX = sleepTimer * -1.8F;
			this.Leg1.render(scale * .97F);
			this.Leg2.rotateAngleX = sleepTimer * 1.7F;
			this.Leg2.render(scale * .97F);
			this.Leg3.rotateAngleX = sleepTimer * 1.75F;
			this.Leg3.render(scale * .95F);
			this.Head.rotateAngleY = sleepTimer * + 2.8F;

			if (sleepTimer > -.28) {
				this.Body.rotateAngleX = (float) Math.PI / 2F - (sleepTimer/3);
			} else {
				this.Body.rotateAngleX = (float) Math.PI / 2F + (sleepTimer/3);
			}

		} else {

			this.Leg0.rotateAngleZ = 0;
			this.Leg0.render(scale);
			this.Leg1.rotateAngleZ = 0;
			this.Leg1.render(scale);
			this.Leg2.rotateAngleZ = 0;
			this.Leg2.render(scale);
			this.Leg3.rotateAngleZ = 0;
			this.Leg3.render(scale);
			this.Head.rotateAngleY = 0F;
			this.Body.rotateAngleX = (float) Math.PI / 2F;

		}
        
        this.Head.render(scale);
        this.Body.render(scale);
        this.TailTop.render(scale);
        this.Tail.render(scale);

    }

    @Override
    public void setLivingAnimations(LivingEntity LivingEntityIn, float p_78086_2_, float p_78086_3_, float partialTickTime) {

        super.setLivingAnimations(LivingEntityIn, p_78086_2_, p_78086_3_, partialTickTime);

        if (LivingEntityIn instanceof EntityCalfHolstein) {
        	EntityCalfHolstein entityCalfHolstein = (EntityCalfHolstein) LivingEntityIn;
            this.Head.rotationPointY = 10.0F + entityCalfHolstein.getHeadAnchorPointY(partialTickTime) * 6.0F;
            this.headRotationAngleX = entityCalfHolstein.getHeadAngleX(partialTickTime);
        }
        else if (LivingEntityIn instanceof EntityCalfFriesian) {
        	EntityCalfFriesian entityCalfFriesian = (EntityCalfFriesian) LivingEntityIn;
            this.Head.rotationPointY = 10.0F + entityCalfFriesian.getHeadAnchorPointY(partialTickTime) * 6.0F;
            this.headRotationAngleX = entityCalfFriesian.getHeadAngleX(partialTickTime);
        }
        else if (LivingEntityIn instanceof EntityCalfHereford) {
        	EntityCalfHereford entityCalfHereford = (EntityCalfHereford) LivingEntityIn;
            this.Head.rotationPointY = 10.0F + entityCalfHereford.getHeadAnchorPointY(partialTickTime) * 6.0F;
            this.headRotationAngleX = entityCalfHereford.getHeadAngleX(partialTickTime);
        }
        else if (LivingEntityIn instanceof EntityCalfHighland) {
        	EntityCalfHighland entityCalfHighland = (EntityCalfHighland) LivingEntityIn;
            this.Head.rotationPointY = 10.0F + entityCalfHighland.getHeadAnchorPointY(partialTickTime) * 6.0F;
            this.headRotationAngleX = entityCalfHighland.getHeadAngleX(partialTickTime);
        }
        else if (LivingEntityIn instanceof EntityCalfMooshroom) {
        	EntityCalfMooshroom entityCalfMooshrom = (EntityCalfMooshroom) LivingEntityIn;
            this.Head.rotationPointY = 10.0F + entityCalfMooshrom.getHeadAnchorPointY(partialTickTime) * 6.0F;
            this.headRotationAngleX = entityCalfMooshrom.getHeadAngleX(partialTickTime);
        }
        else if (LivingEntityIn instanceof EntityCalfJersey) {
        	EntityCalfJersey entityCalfJersey = (EntityCalfJersey) LivingEntityIn;
            this.Head.rotationPointY = 10.0F + entityCalfJersey.getHeadAnchorPointY(partialTickTime) * 6.0F;
            this.headRotationAngleX = entityCalfJersey.getHeadAngleX(partialTickTime);
        }
        else if (LivingEntityIn instanceof EntityCalfAngus) {
        	EntityCalfAngus entityCalfAngus = (EntityCalfAngus) LivingEntityIn;
            this.Head.rotationPointY = 10.0F + entityCalfAngus.getHeadAnchorPointY(partialTickTime) * 6.0F;
            this.headRotationAngleX = entityCalfAngus.getHeadAngleX(partialTickTime);
        }
    }

    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {
       
        //this.Head.rotateAngleX = par5 / (180F / (float) Math.PI);
       // this.Head.rotateAngleY = par4 / (180F / (float) Math.PI);
        //this.Head.rotateAngleX = this.headRotationAngleX;
       // this.Body.rotateAngleX = (float) Math.PI / 2F;

        EntityAnimaniaCow ech = (EntityAnimaniaCow) entity;
		if (!ech.getSleeping()) {
			this.TailTop.rotateAngleX = (float) Math.PI / 2F;
			this.Tail.rotateAngleY = MathHelper.sin(par3 * 3.141593F * 0.05F) * MathHelper.sin(par3 * 3.141593F * .03F * 0.05F) * 0.15F * 3.141593F;
		} else {
			this.Tail.rotateAngleY = MathHelper.sin(1 * 3.141593F * 0.05F) * MathHelper.sin(1 * 3.141593F * .03F * 0.05F) * 0.15F * 3.141593F;
		}

        this.Leg0.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
        this.Leg1.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
        this.Leg2.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
        this.Leg3.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
    }
}
