package com.animania.client.models.goats;

import com.animania.common.entities.goats.EntityDoeNigerianDwarf;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

public class ModelDoeNigerianDwarf extends ModelBase
{	
	private float headRotationAngleX;
	public ModelRenderer HeadNode = new ModelRenderer(this, 0, 0);

	ModelRenderer Body;
	ModelRenderer Butt;
	ModelRenderer Tail;
	ModelRenderer BackLeg_L;
	ModelRenderer BackLeg_R;
	ModelRenderer FrontLeg_L;
	ModelRenderer FrontLeg_R;
	ModelRenderer Udder;
	ModelRenderer Udder1;
	ModelRenderer Udder2;
	ModelRenderer Udder3;
	ModelRenderer Udder4;
	ModelRenderer Neck;
	ModelRenderer Head;
	ModelRenderer Ear_R;
	ModelRenderer Ear2_R;
	ModelRenderer Ear_L;
	ModelRenderer Ear2_L;
	ModelRenderer Bud_R;
	ModelRenderer Mouth;
	ModelRenderer Snout3;
	ModelRenderer Nose;
	ModelRenderer Snout1;
	ModelRenderer Snout2;
	ModelRenderer Bud_L;

	public ModelDoeNigerianDwarf()
	{
		this(0.0f);
	}

	public ModelDoeNigerianDwarf(float par1)
	{
		Body = new ModelRenderer( this, 81, 5 );
		Body.setTextureSize( 128, 128 );
		Body.addBox( -5F, -6.5F, -6.5F, 10, 13, 13);
		Body.setRotationPoint( 0F, 9F, 2F );
		Butt = new ModelRenderer( this, 71, 40 );
		Butt.setTextureSize( 128, 128 );
		Butt.addBox( -4.5F, -6F, 0F, 9, 12, 18);
		Butt.setRotationPoint( 0F, 9.435779F, 6.980974F );
		Tail = new ModelRenderer( this, 38, 10 );
		Tail.setTextureSize( 128, 128 );
		Tail.addBox( -1.5F, -3.5F, -1.5F, 3, 5, 3);
		Tail.setRotationPoint( 0F, 5.103979F, 24.90402F );
		BackLeg_L = new ModelRenderer( this, 47, 28 );
		BackLeg_L.setTextureSize( 128, 128 );
		BackLeg_L.addBox( -1.5F, -2F, -2.5F, 3, 16, 5);
		BackLeg_L.setRotationPoint( 5F, 10.21395F, 18.9974F );
		BackLeg_R = new ModelRenderer( this, 47, 28 );
		BackLeg_R.setTextureSize( 128, 128 );
		BackLeg_R.addBox( -1.5F, -2F, -2.5F, 3, 16, 5);
		BackLeg_R.setRotationPoint( -5F, 10.21395F, 18.9974F );
		FrontLeg_L = new ModelRenderer( this, 62, 4 );
		FrontLeg_L.setTextureSize( 128, 128 );
		FrontLeg_L.addBox( -1.5F, -1F, -2F, 3, 14, 4);
		FrontLeg_L.setRotationPoint( 4F, 11.72712F, -1.250051F );
		FrontLeg_R = new ModelRenderer( this, 62, 4 );
		FrontLeg_R.setTextureSize( 128, 128 );
		FrontLeg_R.addBox( -1.5F, -1F, -2F, 3, 14, 4);
		FrontLeg_R.setRotationPoint( -4F, 11.72712F, -1.250051F );
		
		HeadNode = new ModelRenderer(this, 85, 78);
		HeadNode.setTextureSize(128, 128);
		HeadNode.addBox(0F, 0F, 0F, 0, 0, 0);
		HeadNode.setRotationPoint(0F, 8.651377F, -1.984779F);

		Neck = new ModelRenderer(this, 85, 78);
		Neck.setTextureSize(128, 128);
		Neck.addBox( -2.5F, -4.5F, -14.5F, 5, 9, 15);
		Neck.setRotationPoint(0F, 0F, 0F);

		Head = new ModelRenderer( this, 9, 60 );
		Head.setTextureSize( 128, 128 );
		Head.addBox( -3F, -3.5F, -7.5F, 6, 7, 7);
		Head.setRotationPoint( 0F, -2.08095F - 8.651377F, -6.665287F + 1.984779F);

		Ear_R = new ModelRenderer( this, 60, 84 );
		Ear_R.setTextureSize( 128, 128 );
		Ear_R.addBox( -4F, -1F, -0.5F, 4, 2, 1);
		Ear_R.setRotationPoint( -2.5F, -3.183779F - 8.651377F, -10.09804F  + 1.984779F);
		Ear2_R = new ModelRenderer( this, 49, 80 );
		Ear2_R.setTextureSize( 128, 128 );
		Ear2_R.addBox( -2.5F, -0.5F, -0.5F, 1, 1, 1);
		Ear2_R.setRotationPoint( -4.761655F, -3.911814F - 8.651377F, -10.87578F + 1.984779F );
		Ear_L = new ModelRenderer( this, 60, 84 );
		Ear_L.setTextureSize( 128, 128 );
		Ear_L.addBox( 0F, -1F, -0.5F, 4, 2, 1);
		Ear_L.setRotationPoint( 2.5F, -3.183779F - 8.651377F, -10.09804F + 1.984779F );
		Ear2_L = new ModelRenderer( this, 49, 80 );
		Ear2_L.setTextureSize( 128, 128 );
		Ear2_L.addBox( 1.5F, -0.5F, -0.5F, 1, 1, 1);
		Ear2_L.setRotationPoint( 4.761655F, -3.911814F - 8.651377F, -10.87578F + 1.984779F );
		Bud_R = new ModelRenderer( this, 38, 64 );
		Bud_R.setTextureSize( 128, 128 );
		Bud_R.addBox( -1.5F, -0.5F, -1.5F, 3, 1, 3);
		Bud_R.setRotationPoint( -1.8F, -5.188705F - 8.651377F, -9.628707F  + 1.984779F);
		Mouth = new ModelRenderer( this, 8, 111 );
		Mouth.setTextureSize( 128, 128 );
		Mouth.addBox( -1.5F, -0.5F, -5.5F, 3, 1, 6);
		Mouth.setRotationPoint( 0F, 2.35008F - 8.651377F, -11.64283F + 1.984779F );
		Snout3 = new ModelRenderer( this, 9, 86 );
		Snout3.setTextureSize( 128, 128 );
		Snout3.addBox( -1.5F, 0F, -5F, 3, 2, 5);
		Snout3.setRotationPoint( 0F, -1.712044F - 8.651377F, -14.35791F + 1.984779F );
		Nose = new ModelRenderer( this, 40, 91 );
		Nose.setTextureSize( 128, 128 );
		Nose.addBox( -1F, 0F, 0F, 2, 2, 2);
		Nose.setRotationPoint( 0F, 1.40766F - 8.651377F, -18.92873F + 1.984779F );
		Snout1 = new ModelRenderer( this, 11, 76 );
		Snout1.setTextureSize( 128, 128 );
		Snout1.addBox( -1.5F, 0F, -5.5F, 3, 2, 6);
		Snout1.setRotationPoint( -0.01F, -2.914343F - 8.651377F, -14.69993F + 1.984779F );
		Snout2 = new ModelRenderer( this, 10, 96 );
		Snout2.setTextureSize( 128, 128 );
		Snout2.addBox( -2F, -0.5F, -5F, 4, 3, 6);
		Snout2.setRotationPoint( 0F, -0.116581F - 8.651377F, -13.76463F + 1.984779F );
		Bud_L = new ModelRenderer( this, 38, 64 );
		Bud_L.setTextureSize( 128, 128 );
		Bud_L.addBox( -1.5F, -0.5F, -1.5F, 3, 1, 3);
		Bud_L.setRotationPoint( 1.8F, -5.188705F - 8.651377F, -9.628707F  + 1.984779F);
		Udder = new ModelRenderer( this, 4, 11 );
		Udder.setTextureSize( 128, 128 );
		Udder.addBox( -3F, -2F, -3F, 6, 4, 6);
		Udder.setRotationPoint( 0F, 16.36405F, 17.24187F );
		Udder1 = new ModelRenderer( this, 9, 16 );
		Udder1.setTextureSize( 128, 128 );
		Udder1.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Udder1.setRotationPoint( 2F, 18.86405F, 15.24187F );
		Udder2 = new ModelRenderer( this, 9, 16 );
		Udder2.setTextureSize( 128, 128 );
		Udder2.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Udder2.setRotationPoint( -2F, 18.86405F, 15.24187F );
		Udder3 = new ModelRenderer( this, 9, 16 );
		Udder3.setTextureSize( 128, 128 );
		Udder3.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Udder3.setRotationPoint( -2F, 18.86405F, 19.24187F );
		Udder4 = new ModelRenderer( this, 9, 16 );
		Udder4.setTextureSize( 128, 128 );
		Udder4.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Udder4.setRotationPoint( 2F, 18.86405F, 19.24187F );

		this.HeadNode.addChild(Head);
		this.HeadNode.addChild(Neck);
		this.HeadNode.addChild(Ear_R);
		this.HeadNode.addChild(Ear2_R);
		this.HeadNode.addChild(Ear_L);
		this.HeadNode.addChild(Ear2_L);
		this.HeadNode.addChild(Bud_R);
		this.HeadNode.addChild(Bud_L);
		this.HeadNode.addChild(Mouth);
		this.HeadNode.addChild(Snout1);
		this.HeadNode.addChild(Snout2);
		this.HeadNode.addChild(Snout3);

	}

	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		Butt.rotateAngleX = 0.01847221F;
		Tail.rotateAngleX = -0.8720122F;
		BackLeg_L.rotateAngleX = 0.01847221F;
		BackLeg_R.rotateAngleX = 0.01847221F;
		Udder1.rotateAngleX = 1.297424E-08F;
		Udder2.rotateAngleX = 1.020954E-08F;
		Udder3.rotateAngleX = 7.444835E-09F;
		Udder4.rotateAngleX = 4.680133E-09F;
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
		Snout3.rotateAngleX = 0.6541421F;
		Nose.rotateAngleX = 0.4028149F;
		Snout1.rotateAngleX = 1.100248F;
		Snout2.rotateAngleX = 0.4028149F;
		Bud_L.rotateAngleX = 0.1493225F;
		Bud_L.rotateAngleY = 0.1260779F;
		Bud_L.rotateAngleZ = 0.09751797F;

		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

		Body.render(scale);
		Butt.render(scale);
		Tail.render(scale);
		BackLeg_L.render(scale);	
		BackLeg_R.render(scale);
		FrontLeg_L.render(scale);
		FrontLeg_R.render(scale);  
		HeadNode.render(scale);
		Udder.render(scale);
		Udder1.render(scale);
		Udder2.render(scale);
		Udder3.render(scale);
		Udder4.render(scale);

	}

	@Override
	public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float p_78086_2_, float p_78086_3_, float partialTickTime)
	{
		super.setLivingAnimations(entitylivingbaseIn, p_78086_2_, p_78086_3_, partialTickTime);

		this.HeadNode.rotationPointY = + 7.75F + ((EntityDoeNigerianDwarf)entitylivingbaseIn).getHeadRotationPointY(partialTickTime) * 6.0F;
		this.headRotationAngleX = ((EntityDoeNigerianDwarf)entitylivingbaseIn).getHeadRotationAngleX(partialTickTime);

	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity scale)
	{
		float f6 = 180F / (float) Math.PI;

		//this.Body.rotateAngleX = (float) Math.PI / 2F;

		this.HeadNode.rotateAngleX = par5 / (180F / (float) Math.PI);
		this.HeadNode.rotateAngleY = par4 / (180F / (float) Math.PI);
		this.HeadNode.rotateAngleX = this.headRotationAngleX;

		//this.Tail.rotateAngleX = (float) Math.PI / 2F;
		this.Tail.rotateAngleY = MathHelper.sin(par3 * 3.141593F * 0.05F) * MathHelper.sin(par3 * 3.141593F * .03F * 0.05F) * 0.15F * 3.141593F;

		this.BackLeg_L.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.BackLeg_R.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
		this.FrontLeg_L.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
		this.FrontLeg_R.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
	}

}
