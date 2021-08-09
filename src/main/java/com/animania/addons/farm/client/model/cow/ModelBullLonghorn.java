package com.animania.addons.farm.client.model.cow;

import com.animania.addons.farm.common.entity.cows.CowHighland.EntityBullHighland;
import com.animania.addons.farm.common.entity.cows.CowLonghorn.EntityBullLonghorn;
import com.animania.addons.farm.common.entity.cows.EntityAnimaniaCow;
import com.animania.config.AnimaniaConfig;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;

public class ModelBullLonghorn extends ModelBase
{
	private float        headRotationAngleX;
	public ModelRenderer Head          = new ModelRenderer(this, 0, 0);
	ModelRenderer        Body;
	ModelRenderer        BodyHump;
	ModelRenderer        BodyHump2;
	ModelRenderer        sac;
	ModelRenderer        penis;
	ModelRenderer        TailTop;
	ModelRenderer        Tail;
	ModelRenderer        TailHair1;
	ModelRenderer        TailHair2;
	ModelRenderer        HeadTop;
	ModelRenderer        Snout;
	ModelRenderer        EarL;
	ModelRenderer        EarLa;
	ModelRenderer        EarR;
	ModelRenderer        EarRa;
	ModelRenderer        Ring;
	ModelRenderer        Horn1;
	ModelRenderer        Horn1A;
	ModelRenderer        Horn1B;
	ModelRenderer        Horn1C;
	ModelRenderer        Horn2;
	ModelRenderer        Horn2A;
	ModelRenderer        Horn2B;
	ModelRenderer        Horn2C;
	ModelRenderer        Leg0;
	ModelRenderer        Leg1;
	ModelRenderer        Leg2;
	ModelRenderer        Leg3;

	protected float      field_78145_g = 8.0F;
	protected float      field_78151_h = 4.0F;

	public ModelBullLonghorn() {
		this.Body = new ModelRenderer(this, 18, 4);
		this.Body.setTextureSize(128, 64);
		this.Body.addBox(-6F, -5F, -5F, 12, 10, 10);
		this.Body.setRotationPoint(0F, 8F, 4F);
		this.BodyHump = new ModelRenderer(this, 62, 9);
		this.BodyHump.setTextureSize(128, 64);
		this.BodyHump.addBox(-7F, -5.02F, -6.5F, 14, 10, 13);
		this.BodyHump.setRotationPoint(0F, 8.01F, -5F);
		this.BodyHump2 = new ModelRenderer(this, 71, 34);
		this.BodyHump2.setTextureSize(128, 64);
		this.BodyHump2.addBox(-5.5F, -3.02F, -5F, 11, 6, 10);
		this.BodyHump2.setRotationPoint(0F, 7.200001F, 1F);

		this.sac = new ModelRenderer(this, 64, 5);
		this.sac.setTextureSize(128, 64);
		this.sac.addBox(-1.5F, -1.5F, -1F, 3, 3, 2);
		this.sac.setRotationPoint(0F, 14F, 6.983971F);
		this.penis = new ModelRenderer(this, 52, 0);
		this.penis.setTextureSize(128, 64);
		this.penis.addBox(-1F, -2.5F, -2F, 2, 5, 4);
		this.penis.setRotationPoint(-3.059797E-07F, 12.5F, 4.983971F);

		this.TailTop = new ModelRenderer(this, 32, 49);
		this.TailTop.setTextureSize(128, 64);
		this.TailTop.addBox(-1F, -0.5F, 0F, 2, 1, 1);
		this.TailTop.setRotationPoint(0F, 4.1F, 8.999999F);

		this.Tail = new ModelRenderer(this, 27, 51);
		this.Tail.setTextureSize(128, 64);
		this.Tail.addBox(-0.5F, -0.5F, -5.7F, 1, 1, 6);
		this.Tail.setRotationPoint(0F, 4F, 9F);

		this.TailHair1 = new ModelRenderer(this, 23, 52);
		this.TailHair1.setTextureSize(128, 64);
		this.TailHair1.addBox(-1F, 0F, -4F, 2, 0, 3);
		this.TailHair1.setRotationPoint(0F, -.1F, -4.64188F);

		this.TailHair2 = new ModelRenderer(this, 23, 52);
		this.TailHair2.setTextureSize(128, 64);
		this.TailHair2.addBox(-1F, 0F, -4F, 2, 0, 3);
		this.TailHair2.setRotationPoint(2.010928E-07F, -.1F, -4.64188F);
	
		this.Leg0 = new ModelRenderer(this, 0, 32);
		this.Leg0.setTextureSize(128, 64);
		this.Leg0.addBox(-2F, 0F, -2F, 4, 12, 4);
		this.Leg0.setRotationPoint(-4F, 13F, -7F);
		this.Leg1 = new ModelRenderer(this, 24, 32);
		this.Leg1.setTextureSize(128, 64);
		this.Leg1.addBox(-2F, 0F, -2F, 4, 12, 4);
		this.Leg1.setRotationPoint(4F, 13F, -7F);
		this.Leg2 = new ModelRenderer(this, 40, 32);
		this.Leg2.setTextureSize(128, 64);
		this.Leg2.addBox(-2F, 0F, -2F, 4, 12, 4);
		this.Leg2.setRotationPoint(4F, 13F, 7F);
		this.Leg3 = new ModelRenderer(this, 0, 16);
		this.Leg3.setTextureSize(128, 64);
		this.Leg3.addBox(-2F, 0F, -2F, 4, 12, 4);
		this.Leg3.setRotationPoint(-4F, 13F, 7F);

		this.Head = new ModelRenderer(this, 0, 0);
		this.Head.setTextureSize(128, 64);
		this.Head.addBox(-4F, -4F, -3F, 8, 8, 6);
		this.Head.setRotationPoint(0F, 5F, -13F); 

		this.Snout = new ModelRenderer(this, 49, 50);
		this.Snout.setTextureSize(128, 64);
		this.Snout.addBox(-2F, -2F, -1.5F, 4, 4, 3);
		this.Snout.setRotationPoint(0F, 3F, -2.5F); 

		this.EarL = new ModelRenderer(this, 39, 53);
		this.EarL.setTextureSize(128, 64);
		this.EarL.addBox(-1.5F, -1F, -0.5F, 3, 2, 1);
		this.EarL.setRotationPoint(-5.5F, -1.5F, 1F);

		this.EarLa = new ModelRenderer(this, 45, 50);
		this.EarLa.setTextureSize(128, 64);
		this.EarLa.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.EarLa.setRotationPoint(-4.5F, -.04F, 0.69718F); 

		this.EarR = new ModelRenderer(this, 39, 53);
		this.EarR.setTextureSize(128, 64);
		this.EarR.addBox(-1.5F, -1F, -0.5F, 3, 2, 1);
		this.EarR.setRotationPoint(5.5F, -1.5F, 1F); 

		this.EarRa = new ModelRenderer(this, 41, 50);
		this.EarRa.setTextureSize(128, 64);
		this.EarRa.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.EarRa.setRotationPoint(4.5F, -.04F, 0.69718F); 

		this.Ring = new ModelRenderer(this, 65, 53);
		this.Ring.setTextureSize(128, 64);
		this.Ring.addBox(-2F, -2F, -.1F, 4, 4, 0);
		this.Ring.setRotationPoint(0.0F, 3F, -4.1F);

		this.HeadTop = new ModelRenderer(this, 5, 5);
		this.HeadTop.setTextureSize(128, 64);
		this.HeadTop.addBox(-3F, -1.5F, -1.5F, 6, 3, 3);
		this.HeadTop.setRotationPoint(0F, -4F, 0F);

		this.Horn1 = new ModelRenderer(this, 22, 0);
		this.Horn1.setTextureSize(128, 64);
		this.Horn1.addBox(0F, -1F, -1F, 6, 2, 2);
		this.Horn1.setRotationPoint(3F, -4F, -0F);

		this.Horn1A = new ModelRenderer(this, 38, 0);
		this.Horn1A.setTextureSize(128, 64);
		this.Horn1A.addBox(-1F, -1F, -1F, 2, 2, 2);
		this.Horn1A.setRotationPoint(9.964239F, -4.87F, 0F);

		this.Horn1B = new ModelRenderer(this, 46, 0);
		this.Horn1B.setTextureSize(128, 64);
		this.Horn1B.addBox(-0.5F, -1F, -1F, 1, 2, 2);
		this.Horn1B.setRotationPoint(11.40985F, -5.9427986F, 0F);

		this.Horn1C = new ModelRenderer(this, 52, 1);
		this.Horn1C.setTextureSize(128, 64);
		this.Horn1C.addBox(-0.5F, -1F, -0.5F, 1, 2, 1);
		this.Horn1C.setRotationPoint(12.35614F, -6.993765F, 0F);

		this.Horn2 = new ModelRenderer(this, 22, 0);
		this.Horn2.setTextureSize(128, 64);
		this.Horn2.addBox(0F, -1F, -1F, 6, 2, 2);
		this.Horn2.setRotationPoint(-9F, -4F, 0F); 

		this.Horn2A = new ModelRenderer(this, 38, 0);
		this.Horn2A.setTextureSize(128, 64);
		this.Horn2A.addBox(-1F, -1F, -1F, 2, 2, 2);
		this.Horn2A.setRotationPoint(-9.964239F, -4.87F, 0F);

		this.Horn2B = new ModelRenderer(this, 46, 0);
		this.Horn2B.setTextureSize(128, 64);
		this.Horn2B.addBox(-0.5F, -1F, -1F, 1, 2, 2);
		this.Horn2B.setRotationPoint(-11.40985F, -5.9427986F, 0F);

		this.Horn2C = new ModelRenderer(this, 52, 1);
		this.Horn2C.setTextureSize(128, 64);
		this.Horn2C.addBox(-0.5F, -1F, -0.5F, 1, 2, 1);
		this.Horn2C.setRotationPoint(-12.35614F, -6.993765F, 0F);

		this.Head.addChild(this.Snout);
		this.Head.addChild(this.EarL);
		this.Head.addChild(this.EarLa);
		this.Head.addChild(this.EarR);
		this.Head.addChild(this.EarRa);
		// this.Head.addChild(this.Ring);
		this.Head.addChild(this.HeadTop);
		this.Head.addChild(this.Horn1);
		this.Head.addChild(this.Horn1A);
		this.Head.addChild(this.Horn1B);
		this.Head.addChild(this.Horn1C);
		this.Head.addChild(this.Horn2);
		this.Head.addChild(this.Horn2A);
		this.Head.addChild(this.Horn2B);
		this.Head.addChild(this.Horn2C);

		this.Tail.addChild(this.TailHair1);
		this.Tail.addChild(this.TailHair2);

	}

	@Override
	public void render(Entity entity, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float scale) {

		// Body
		this.Body.rotateAngleX = 1.570796F;
		this.BodyHump.rotateAngleX = 1.570796F;
		this.BodyHump2.rotateAngleX = 1.308997F;
		this.sac.rotateAngleX = -3.821371E-15F;
		this.sac.rotateAngleY = -3.141593F;
		this.sac.rotateAngleZ = -3.141593F;
		this.penis.rotateAngleX = 1.457546F;
		this.penis.rotateAngleY = 3.141593F;
		this.penis.rotateAngleZ = 3.141593F;
		this.EarL.rotateAngleX = 0.2032792F;
		this.EarLa.rotateAngleX = 0.2032792F;
		this.EarR.rotateAngleX = 0.2032792F;
		this.EarRa.rotateAngleX = 0.2032792F;

		// Tail
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
			this.Head.rotateAngleY = sleepTimer * 2.8F;

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
		this.BodyHump.render(scale);
		this.BodyHump2.render(scale);
		if (AnimaniaConfig.gameRules.showParts) {
			this.sac.render(scale);
			this.penis.render(scale);
		}
		
		this.Leg0.render(scale);
		this.Leg1.render(scale);
		this.Leg2.render(scale);
		this.Leg3.render(scale);

		this.TailTop.render(scale);
		this.Tail.render(scale);

	}

	@Override
	public void setLivingAnimations(LivingEntity LivingEntityIn, float p_78086_2_, float p_78086_3_, float partialTickTime) {
		super.setLivingAnimations(LivingEntityIn, p_78086_2_, p_78086_3_, partialTickTime);

		if (LivingEntityIn instanceof EntityBullLonghorn) {
			EntityBullLonghorn entityBullLonghorn = (EntityBullLonghorn) LivingEntityIn;
			this.Head.rotationPointY = 6.0F + entityBullLonghorn.getHeadAnchorPointY(partialTickTime) * 9.0F;
			this.headRotationAngleX = entityBullLonghorn.getHeadAngleX(partialTickTime);
		} else if (LivingEntityIn instanceof EntityBullHighland) {
			EntityBullHighland entityBullHighland = (EntityBullHighland) LivingEntityIn;
			this.Head.rotationPointY = 6.0F + entityBullHighland.getHeadAnchorPointY(partialTickTime) * 9.0F;
			this.headRotationAngleX = entityBullHighland.getHeadAngleX(partialTickTime);
		}
	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {
		float f6 = 180F / (float) Math.PI;
		this.Body.rotateAngleX = (float) Math.PI / 2F;

		if (entity instanceof EntityBullLonghorn) {

			EntityBullLonghorn eb = (EntityBullLonghorn) entity;
			if (eb.getFighting()) {
				this.Head.rotationPointY = 14.0F + eb.getHeadAnchorPointY(1) * 9.0F;
				this.headRotationAngleX = eb.getHeadAngleX(-3);
			}
			else {
				this.Head.rotateAngleX = par5 / (180F / (float) Math.PI);
				this.Head.rotateAngleY = par4 / (180F / (float) Math.PI);
				this.Head.rotateAngleX = this.headRotationAngleX;
			}
		} else if (entity instanceof EntityBullHighland) {

			EntityBullHighland eb = (EntityBullHighland) entity;
			if (eb.getFighting()) {
				this.Head.rotationPointY = 14.0F + eb.getHeadAnchorPointY(1) * 9.0F;
				this.headRotationAngleX = eb.getHeadAngleX(-3);
			}
			else {
				this.Head.rotateAngleX = par5 / (180F / (float) Math.PI);
				this.Head.rotateAngleY = par4 / (180F / (float) Math.PI);
				this.Head.rotateAngleX = this.headRotationAngleX;
			}

		}

		else {
			this.Head.rotateAngleX = par5 / (180F / (float) Math.PI);
			this.Head.rotateAngleY = par4 / (180F / (float) Math.PI);
			this.Head.rotateAngleX = this.headRotationAngleX;
		}

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
