package com.animania.client.models.goats;

import com.animania.common.entities.goats.EntityAnimaniaGoat;
import com.animania.common.entities.goats.GoatAngora.EntityBuckAngora;
import com.animania.config.AnimaniaConfig;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

public class ModelBuckAngora extends ModelBase
{	
	private float headRotationAngleX;
	public ModelRenderer HeadNode = new ModelRenderer(this, 0, 0);

	ModelRenderer Body;
	ModelRenderer Butt;
	ModelRenderer Tail;
	ModelRenderer BackLeg_L;
	ModelRenderer BackLegWool_L;
	ModelRenderer Wool1;
	ModelRenderer BackLeg_R;
	ModelRenderer BackLegWool_R;
	ModelRenderer FrontLeg_L;
	ModelRenderer FrontLegWool_L;
	ModelRenderer Reproductive1;
	ModelRenderer Reproductive2;
	ModelRenderer Neck;
	ModelRenderer Head;
	ModelRenderer Ear_R;
	ModelRenderer Ear2_R;
	ModelRenderer Ear_L;
	ModelRenderer Ear2_L;
	ModelRenderer Bud_R;
	ModelRenderer Horn1_R;
	ModelRenderer Horn2_R;
	ModelRenderer Horn3_R;
	ModelRenderer Mouth;
	ModelRenderer Beard;
	ModelRenderer Nose;
	ModelRenderer Snout1;
	ModelRenderer Snout2;
	ModelRenderer Snout3;
	ModelRenderer Bud_L;
	ModelRenderer Horn1_L;
	ModelRenderer Horn2_L;
	ModelRenderer Horn3_L;
	ModelRenderer WoolHead;
	ModelRenderer WoolNeck;
	ModelRenderer Wool2;
	ModelRenderer FrontLeg_R;
	ModelRenderer FrontLegWool_R;

	public ModelBuckAngora()
	{
		this(0.0f);
	}

	public ModelBuckAngora(float par1)
	{

		Body = new ModelRenderer( this, 81, 5 );
		Body.setTextureSize( 128, 256 );
		Body.addBox( -5F, -6.5F, -6.5F, 10, 13, 13);
		Body.setRotationPoint( 0F, 8F, 2F );
		Butt = new ModelRenderer( this, 71, 40 );
		Butt.setTextureSize( 128, 256 );
		Butt.addBox( -4.5F, -6F, 0F, 9, 12, 18);
		Butt.setRotationPoint( 0F, 8.435779F, 6.980974F );
		Tail = new ModelRenderer( this, 38, 10 );
		Tail.setTextureSize( 128, 256 );
		Tail.addBox( -1.5F, -5F, -1.5F, 3, 8, 3);
		Tail.setRotationPoint( 0F, 3.104151F, 24.88555F );
		BackLeg_L = new ModelRenderer( this, 47, 28 );
		BackLeg_L.setTextureSize( 128, 256 );
		BackLeg_L.addBox( -1.5F, -3F, -2.5F, 3, 18, 5);
		BackLeg_L.setRotationPoint( 5F, 9.213954F, 18.9974F );
		BackLegWool_L = new ModelRenderer( this, 19, 208 );
		BackLegWool_L.setTextureSize( 128, 256 );
		BackLegWool_L.addBox( -2F, 0F, -3F, 4, 12, 6);
		BackLegWool_L.setRotationPoint( 5F, 5.714552F, 18.93275F );
		Wool1 = new ModelRenderer( this, 55, 179 );
		Wool1.setTextureSize( 128, 256 );
		Wool1.addBox( -6F, -6.5F, 1F, 12, 13, 16);
		Wool1.setRotationPoint( 0F, 8.610089F, 8.973363F );
		BackLeg_R = new ModelRenderer( this, 47, 28 );
		BackLeg_R.setTextureSize( 128, 256 );
		BackLeg_R.addBox( -1.5F, -3F, -2.5F, 3, 18, 5);
		BackLeg_R.setRotationPoint( -5F, 9.213954F, 18.9974F );
		BackLegWool_R = new ModelRenderer( this, 19, 208 );
		BackLegWool_R.setTextureSize( 128, 256 );
		BackLegWool_R.addBox( -2F, 0F, -3F, 4, 12, 6);
		BackLegWool_R.setRotationPoint( -5F, 5.714552F, 18.93275F );
		FrontLeg_L = new ModelRenderer( this, 62, 4 );
		FrontLeg_L.setTextureSize( 128, 256 );
		FrontLeg_L.addBox( -1.5F, -2F, -2F, 3, 16, 4);
		FrontLeg_L.setRotationPoint( 4F, 10.72712F, -1.250051F );
		FrontLegWool_L = new ModelRenderer( this, 51, 220 );
		FrontLegWool_L.setTextureSize( 128, 256 );
		FrontLegWool_L.addBox( -2.5F, 1F, -2.5F, 5, 10, 5);
		FrontLegWool_L.setRotationPoint( 4.5F, 6.727118F, -1.250051F );
		Wool2 = new ModelRenderer( this, 54, 134 );
		Wool2.setTextureSize( 128, 256 );
		Wool2.addBox( -6.5F, -7F, 0F, 13, 14, 18);
		Wool2.setRotationPoint( 0F, 7.38991F, -4.973363F );
		FrontLeg_R = new ModelRenderer( this, 62, 4 );
		FrontLeg_R.setTextureSize( 128, 256 );
		FrontLeg_R.addBox( -1.5F, -2F, -2F, 3, 16, 4);
		FrontLeg_R.setRotationPoint( -4F, 10.72712F, -1.250051F );
		FrontLegWool_R = new ModelRenderer( this, 51, 220 );
		FrontLegWool_R.setTextureSize( 128, 256 );
		FrontLegWool_R.addBox( -2.5F, 1F, -2.5F, 5, 10, 5);
		FrontLegWool_R.setRotationPoint( -4.5F, 6.727116F, -1.250051F );
		
		Reproductive1 = new ModelRenderer( this, 22, 38 );
		Reproductive1.setTextureSize( 128, 256 );
		Reproductive1.addBox( -1F, -2F, -2F, 2, 4, 4);
		Reproductive1.setRotationPoint( 0F, 13.20496F, 15.5981F );
		Reproductive2 = new ModelRenderer( this, 10, 37 );
		Reproductive2.setTextureSize( 128, 256 );
		Reproductive2.addBox( -1.5F, -2F, -1F, 3, 4, 2);
		Reproductive2.setRotationPoint( 0F, 13.05019F, 17.56459F );
		
		HeadNode = new ModelRenderer(this, 85, 78);
		HeadNode.setTextureSize(128, 256);
		HeadNode.addBox(0F, 0F, 0F, 0, 0, 0);
		HeadNode.setRotationPoint(0F, 7.738533F, -0.9885842F);
		
		Neck = new ModelRenderer(this, 85, 78);
		Neck.setTextureSize(128, 256);
		Neck.addBox(-2F, -4F, -14.5F, 4, 8, 15);
		Neck.setRotationPoint(0F, 0F, 0F);
		
		Head = new ModelRenderer(this, 9, 60);
		Head.setTextureSize(128, 256);
		Head.addBox( -3.5F, -3.5F, -7.5F, 7, 7, 7);
        Head.setRotationPoint( 0F, -2.993793F + - 7.738533F, -5.669091F + 0.9885842F );
		
		Ear_R = new ModelRenderer( this, 60, 84 );
		Ear_R.setTextureSize( 128, 256 );
		Ear_R.addBox( -4F, -1F, -0.5F, 4, 2, 1);
		Ear_R.setRotationPoint( -2.5F, -4.096622F - 7.738533F, -9.101841F + 0.9885842F);
		Ear2_R = new ModelRenderer( this, 49, 80 );
		Ear2_R.setTextureSize( 128, 256 );
		Ear2_R.addBox( -2.5F, -0.5F, -0.5F, 1, 1, 1);
		Ear2_R.setRotationPoint( -4.761655F, -4.824657F - 7.738533F, -9.879581F + 0.9885842F);
		Ear_L = new ModelRenderer( this, 60, 84 );
		Ear_L.setTextureSize( 128, 256 );
		Ear_L.addBox( 0F, -1F, -0.5F, 4, 2, 1);
		Ear_L.setRotationPoint( 2.5F, -4.096622F - 7.738533F, -9.101841F + 0.9885842F );
		Ear2_L = new ModelRenderer( this, 49, 80 );
		Ear2_L.setTextureSize( 128, 256 );
		Ear2_L.addBox( 1.5F, -0.5F, -0.5F, 1, 1, 1);
		Ear2_L.setRotationPoint( 4.761655F, -4.824657F - 7.738533F, -9.879581F + 0.9885842F );
		Bud_R = new ModelRenderer( this, 38, 64 );
		Bud_R.setTextureSize( 128, 256 );
		Bud_R.addBox( -1.5F, -0.5F, -1.5F, 3, 1, 3);
		Bud_R.setRotationPoint( -1.8F, -6.101549F - 7.738533F, -8.632511F + 0.9885842F );
		Horn1_R = new ModelRenderer( this, 38, 71 );
		Horn1_R.setTextureSize( 128, 256 );
		Horn1_R.addBox( -1F, -7.5F, -1F, 2, 7, 2);
		Horn1_R.setRotationPoint( -1.691772F, -5.616201F - 7.738533F, -8.545192F + 0.9885842F );
		Horn2_R = new ModelRenderer( this, 38, 71 );
		Horn2_R.setTextureSize( 128, 256 );
		Horn2_R.addBox( -1F, -7.4F, 1.7F, 2, 7, 2);
		Horn2_R.setRotationPoint( -6.685526F, -9.676292F - 7.738533F, -7.587014F + 0.9885842F );
		Horn3_R = new ModelRenderer( this, 38, 71 );
		Horn3_R.setTextureSize( 128, 256 );
		Horn3_R.addBox( -1F, -6F, -1F, 2, 6, 2);
		Horn3_R.setRotationPoint( -12.27423F, -9.172562F - 7.738533F, -3.402195F + 0.9885842F );
		Mouth = new ModelRenderer( this, 8, 111 );
		Mouth.setTextureSize( 128, 256 );
		Mouth.addBox( -1.5F, -0.5F, -5.5F, 3, 1, 6);
		Mouth.setRotationPoint( 0F, 1.437237F - 7.738533F, -10.64664F + 0.9885842F );
		Beard = new ModelRenderer( this, 31, 111 );
		Beard.setTextureSize( 128, 256 );
		Beard.addBox( -1F, -2F, 0F, 2, 8, 4);
		Beard.setRotationPoint( 0F, 3.543264F - 7.738533F, -14.72607F + 0.9885842F );
		Snout3 = new ModelRenderer( this, 9, 86 );
		Snout3.setTextureSize( 128, 256 );
		Snout3.addBox( -1.5F, 0F, -5F, 3, 2, 5);
		Snout3.setRotationPoint( 0F, -2.624887F - 7.738533F, -13.36171F + 0.9885842F);
		Nose = new ModelRenderer( this, 40, 91 );
		Nose.setTextureSize( 128, 256 );
		Nose.addBox( -1F, 0F, 0F, 2, 2, 2);
		Nose.setRotationPoint( 0F, 0.4948158F - 7.738533F, -17.93254F + 0.9885842F);
		Snout1 = new ModelRenderer( this, 11, 76 );
		Snout1.setTextureSize( 128, 256 );
		Snout1.addBox( -1.5F, 0F, -5.5F, 3, 2, 6);
		Snout1.setRotationPoint( -0.01F, -3.827187F - 7.738533F, -13.70373F + 0.9885842F );
		Snout2 = new ModelRenderer( this, 10, 96 );
		Snout2.setTextureSize( 128, 256 );
		Snout2.addBox( -2F, -0.5F, -5F, 4, 3, 6);
		Snout2.setRotationPoint( 0F, -1.029425F - 7.738533F, -12.76844F + 0.9885842F );
		Bud_L = new ModelRenderer( this, 38, 64 );
		Bud_L.setTextureSize( 128, 256 );
		Bud_L.addBox( -1.5F, -0.5F, -1.5F, 3, 1, 3);
		Bud_L.setRotationPoint( 1.8F, -6.101549F - 7.738533F, -8.632511F + 0.9885842F );
		Horn1_L = new ModelRenderer( this, 38, 71 );
		Horn1_L.setTextureSize( 128, 256 );
		Horn1_L.addBox( -1F, -7.5F, -1F, 2, 7, 2);
		Horn1_L.setRotationPoint( 1.691772F, -5.616201F - 7.738533F, -8.545192F + 0.9885842F );
		Horn2_L = new ModelRenderer( this, 38, 71 );
		Horn2_L.setTextureSize( 128, 256 );
		Horn2_L.addBox( -1F, -7.4F, 1.7F, 2, 7, 2);
		Horn2_L.setRotationPoint( 6.685524F, -9.676292F - 7.738533F, -7.587016F + 0.9885842F );
		Horn3_L = new ModelRenderer( this, 38, 71 );
		Horn3_L.setTextureSize( 128, 256 );
		Horn3_L.addBox( -1F, -6F, -1F, 2, 6, 2);
		Horn3_L.setRotationPoint( 12.27422F, -9.172562F - 7.738533F, -3.402196F + 0.9885842F );
		WoolHead = new ModelRenderer( this, 9, 181 );
		WoolHead.setTextureSize( 128, 256 );
		WoolHead.addBox( -4F, -4F, -6.5F, 8, 8, 5);
		WoolHead.setRotationPoint( 0F, -3.814644F - 7.738533F, -2.783574F + 0.9885842F);
		WoolNeck = new ModelRenderer( this, 5, 141 );
		WoolNeck.setTextureSize( 128, 256 );
		WoolNeck.addBox( -3F, -4.5F, -14.5F, 6, 9, 15);
		WoolNeck.setRotationPoint( 0F, 7.738533F - 7.738533F, -0.9885848F + 0.9885842F );
		
		

		this.HeadNode.addChild(Head);
		this.HeadNode.addChild(Neck);
		this.HeadNode.addChild(Ear_R);
		this.HeadNode.addChild(Ear2_R);
		this.HeadNode.addChild(Ear_L);
		this.HeadNode.addChild(Ear2_L);
		this.HeadNode.addChild(Bud_R);
		this.HeadNode.addChild(Bud_L);
		this.HeadNode.addChild(Horn1_R);
		this.HeadNode.addChild(Horn2_R);
		this.HeadNode.addChild(Horn3_R);
		this.HeadNode.addChild(Horn1_L);
		this.HeadNode.addChild(Horn2_L);
		this.HeadNode.addChild(Horn3_L);
		this.HeadNode.addChild(Mouth);
		this.HeadNode.addChild(Beard);
		this.HeadNode.addChild(Nose);
		this.HeadNode.addChild(Snout1);
		this.HeadNode.addChild(Snout2);
		this.HeadNode.addChild(Snout3);
		this.HeadNode.addChild(WoolHead);
		this.HeadNode.addChild(WoolNeck);


	}

	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		Body.rotateAngleX = -0.08726646F;
		Butt.rotateAngleX = 0.01847221F;
		Tail.rotateAngleX = -0.6475495F;
		BackLeg_L.rotateAngleX = 0.01847221F;
		BackLegWool_L.rotateAngleX = 0.01847221F;
		Wool1.rotateAngleX = 0.01847221F;
		BackLeg_R.rotateAngleX = 0.01847222F;
		BackLegWool_R.rotateAngleX = 0.01847221F;
		Reproductive1.rotateAngleX = 0.07853982F;
		Reproductive2.rotateAngleX = 0.07853982F;
		Neck.rotateAngleX = -0.9705301F;
		Head.rotateAngleX = 0.2771511F;
		Ear_R.rotateAngleX = 0.3975939F;
		Ear_R.rotateAngleY = -0.2030238F;
		Ear_R.rotateAngleZ = 0.321354F;
		Ear2_R.rotateAngleX = 0.397594F;
		Ear2_R.rotateAngleY = -0.2030239F;
		Ear2_R.rotateAngleZ = 0.3213542F;
		Ear_L.rotateAngleX = 0.3975939F;
		Ear_L.rotateAngleY = 0.2030238F;
		Ear_L.rotateAngleZ = -0.321354F;
		Ear2_L.rotateAngleX = 0.397594F;
		Ear2_L.rotateAngleY = 0.2030239F;
		Ear2_L.rotateAngleZ = -0.3213542F;
		Bud_R.rotateAngleX = 0.1493225F;
		Bud_R.rotateAngleY = -0.1260779F;
		Bud_R.rotateAngleZ = -0.09751795F;
		Mouth.rotateAngleX = 0.2771511F;
		Beard.rotateAngleX = 0.1083812F;
		Snout3.rotateAngleX = 0.6541421F;
		Nose.rotateAngleX = 0.4028149F;
		Snout1.rotateAngleX = 1.100248F;
		Snout2.rotateAngleX = 0.4028149F;
		Bud_L.rotateAngleX = 0.1493225F;
		Bud_L.rotateAngleY = 0.1260779F;
		Bud_L.rotateAngleZ = 0.09751797F;
		
		Horn1_R.rotateAngleX = -0.7281986F;
		Horn1_R.rotateAngleY = -0.4957607F;
		Horn1_R.rotateAngleZ = -0.6505846F;
		
		Horn2_R.rotateAngleX = -.308694F;
		Horn2_R.rotateAngleY = -.19F;
		Horn2_R.rotateAngleZ = -1.523367F;
		
		Horn3_R.rotateAngleX = -1.085297F;
		Horn3_R.rotateAngleY = -0.5934884F;
		Horn3_R.rotateAngleZ = -0.3137805F;
		
		Horn1_L.rotateAngleX = -0.7281986F;
	    Horn1_L.rotateAngleY = 0.4957606F;
	    Horn1_L.rotateAngleZ = 0.6505845F;
		
	    Horn2_L.rotateAngleX = -.308693F;
		Horn2_L.rotateAngleY = .19F;
		Horn2_L.rotateAngleZ = 1.523367F;
		
		Horn3_L.rotateAngleX = -1.085297F;
		Horn3_L.rotateAngleY = 0.5934881F;
		Horn3_L.rotateAngleZ = 0.3137808F;
		
		WoolHead.rotateAngleX = 0.2771511F;
		WoolNeck.rotateAngleX = -0.9705304F;
		Wool2.rotateAngleX = -0.06927392F;

		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

		boolean isSleeping = false;
		EntityAnimaniaGoat ech = (EntityAnimaniaGoat) entityIn;
		if (ech.getSleeping()) {
			isSleeping = true;
		}
		float sleepTimer = ech.getSleepTimer();

		if (isSleeping) {

			this.FrontLeg_L.rotateAngleX = sleepTimer * -1.8F;
			this.FrontLeg_L.render(scale * .95F);
			this.FrontLegWool_L.rotateAngleX = sleepTimer * -1.8F;
			this.FrontLegWool_L.render(scale * .95F);
			
			this.FrontLeg_R.rotateAngleX = sleepTimer * -1.8F;
			this.FrontLeg_R.render(scale * .97F);
			this.FrontLegWool_R.rotateAngleX = sleepTimer * -1.8F;
			this.FrontLegWool_R.render(scale * .97F);
			
			this.BackLeg_L.rotateAngleX = sleepTimer * 1.7F;
			this.BackLeg_L.render(scale * .97F);
			this.BackLegWool_L.rotateAngleX = sleepTimer * 1.7F;
			this.BackLegWool_L.render(scale * .97F);
			
			this.BackLeg_R.rotateAngleX = sleepTimer * 1.75F;
			this.BackLeg_R.render(scale * .95F);
			this.BackLegWool_R.rotateAngleX = sleepTimer * 1.75F;
			this.BackLegWool_R.render(scale * .95F);
			
			this.HeadNode.rotateAngleY = sleepTimer * -2.8F;

			if (sleepTimer > -.28) {
				this.Body.rotateAngleX = - (sleepTimer/3);
			} else {
				this.Body.rotateAngleX = + (sleepTimer/3);
			}

		} else {

			this.BackLeg_L.rotateAngleZ = 0;
			this.BackLeg_L.render(scale);
			this.BackLeg_R.rotateAngleZ = 0;
			this.BackLeg_R.render(scale);
			this.FrontLeg_L.rotateAngleZ = 0;
			this.FrontLeg_L.render(scale);
			this.FrontLeg_R.rotateAngleZ = 0;
			this.FrontLeg_R.render(scale);
			
			this.BackLegWool_L.rotateAngleZ = 0;
			this.BackLegWool_R.render(scale);
			this.FrontLegWool_L.rotateAngleZ = 0;
			this.FrontLegWool_R.render(scale);
			this.BackLegWool_L.rotateAngleZ = 0;
			this.BackLegWool_R.render(scale);
			this.FrontLegWool_L.rotateAngleZ = 0;
			this.FrontLegWool_R.render(scale);
			
			this.HeadNode.rotateAngleY = 0F;
			this.Body.rotateAngleX = 0F;

		}
		
		
		Body.render(scale);
		Butt.render(scale);
		Tail.render(scale);
		if (AnimaniaConfig.gameRules.showParts) {
			Reproductive1.render(scale);
			Reproductive2.render(scale);
		}
		Wool1.render(scale);
		Wool2.render(scale);
		HeadNode.render(scale);

	}

	@Override
	public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float p_78086_2_, float p_78086_3_, float partialTickTime)
	{
		super.setLivingAnimations(entitylivingbaseIn, p_78086_2_, p_78086_3_, partialTickTime);

		this.HeadNode.rotationPointY = + 7.75F + ((EntityBuckAngora)entitylivingbaseIn).getHeadAnchorPointY(partialTickTime) * 6.0F;
		this.headRotationAngleX = ((EntityBuckAngora)entitylivingbaseIn).getHeadAngleX(partialTickTime);

	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
	{
		float f6 = 180F / (float) Math.PI;

		
		
		if (entity instanceof EntityBuckAngora) {

			EntityBuckAngora eb = (EntityBuckAngora) entity;
			if (eb.getFighting() && eb.getRivalUniqueId() != null) {
				this.HeadNode.rotateAngleX = 0.687F;
			}
			else {
				this.HeadNode.rotateAngleX = par5 / (180F / (float) Math.PI);
				this.HeadNode.rotateAngleY = par4 / (180F / (float) Math.PI);
				this.HeadNode.rotateAngleX = this.headRotationAngleX;
			}

		}
		else {
			this.HeadNode.rotateAngleX = par5 / (180F / (float) Math.PI);
			this.HeadNode.rotateAngleY = par4 / (180F / (float) Math.PI);
			this.HeadNode.rotateAngleX = this.headRotationAngleX;
		}
		
		boolean isSleeping = false;

		EntityAnimaniaGoat ech = (EntityAnimaniaGoat) entity;
		if (ech.getSleeping()) {
			isSleeping = true;
		}

		if (!isSleeping) {
			this.Tail.rotateAngleY = MathHelper.sin(par3 * 3.141593F * 0.05F) * MathHelper.sin(par3 * 3.141593F * .03F * 0.05F) * 0.15F * 3.141593F;
		} else {
			this.Tail.rotateAngleY = MathHelper.sin(1 * 3.141593F * 0.05F) * MathHelper.sin(1 * 3.141593F * .03F * 0.05F) * 0.15F * 3.141593F;
		}
		
		this.BackLeg_L.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.BackLegWool_L.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.BackLeg_R.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
		this.BackLegWool_R.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
		this.FrontLeg_L.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
		this.FrontLegWool_L.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
		this.FrontLeg_R.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.FrontLegWool_R.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
	}

}
