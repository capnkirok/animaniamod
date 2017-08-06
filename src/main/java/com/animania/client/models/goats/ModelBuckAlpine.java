package com.animania.client.models.goats;

import com.animania.common.entities.cows.EntityBullAngus;
import com.animania.common.entities.cows.EntityBullFriesian;
import com.animania.common.entities.cows.EntityBullHolstein;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelQuadruped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

public class ModelBuckAlpine extends ModelBase
{	
	ModelRenderer Body;
	ModelRenderer Butt;
	ModelRenderer Tail;
	ModelRenderer BackLeg_L;
	ModelRenderer BackLeg_R;
	ModelRenderer FrontLeg_L;
	ModelRenderer FrontLeg_R;
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
	ModelRenderer Snout3;
	ModelRenderer Nose;
	ModelRenderer Snout1;
	ModelRenderer Snout2;
	ModelRenderer Bud_L;
	ModelRenderer Horn1_L;
	ModelRenderer Horn2_L;
	ModelRenderer Horn3_L;

	public ModelBuckAlpine()
	{
		this(0.0f);
	}

	public ModelBuckAlpine(float par1)
	{
		Body = new ModelRenderer(this, 81, 5);
		Body.setTextureSize(128, 128);
		Body.addBox(-5F, -6.5F, -6.5F, 10, 13, 13);
		Body.setRotationPoint(0F, 8F, 2F);
		Butt = new ModelRenderer(this, 71, 40);
		Butt.setTextureSize(128, 128);
		Butt.addBox(-4.5F, -6F, 0F, 9, 12, 18);
		Butt.setRotationPoint(0F, 8.435779F, 6.980974F);
		Tail = new ModelRenderer(this, 38, 10);
		Tail.setTextureSize(128, 128);
		Tail.addBox(-1.5F, -5F, -1.5F, 3, 8, 3);
		Tail.setRotationPoint(0F, 3.104151F, 24.88555F);
		BackLeg_L = new ModelRenderer(this, 47, 28);
		BackLeg_L.setTextureSize(128, 128);
		BackLeg_L.addBox(-1.5F, -3F, -2.5F, 3, 18, 5);
		BackLeg_L.setRotationPoint(5F, 9.213954F, 18.9974F);
		BackLeg_R = new ModelRenderer(this, 47, 28);
		BackLeg_R.setTextureSize(128, 128);
		BackLeg_R.addBox(-1.5F, -3F, -2.5F, 3, 18, 5);
		BackLeg_R.setRotationPoint(-5F, 9.213954F, 18.9974F);
		FrontLeg_L = new ModelRenderer(this, 62, 4);
		FrontLeg_L.setTextureSize(128, 128);
		FrontLeg_L.addBox(-1.5F, -2F, -2F, 3, 16, 4);
		FrontLeg_L.setRotationPoint(4F, 10.72712F, -1.250051F);
		FrontLeg_R = new ModelRenderer(this, 62, 4);
		FrontLeg_R.setTextureSize(128, 128);
		FrontLeg_R.addBox(-1.5F, -2F, -2F, 3, 16, 4);
		FrontLeg_R.setRotationPoint(-4F, 10.72712F, -1.250051F);
		Reproductive1 = new ModelRenderer(this, 22, 38);
		Reproductive1.setTextureSize(128, 128);
		Reproductive1.addBox(-1F, -2F, -2F, 2, 4, 4);
		Reproductive1.setRotationPoint(0F, 13.29212F, 16.5943F);
		Reproductive2 = new ModelRenderer(this, 10, 37);
		Reproductive2.setTextureSize(128, 128);
		Reproductive2.addBox(-1.5F, -2F, -1F, 3, 4, 2);
		Reproductive2.setRotationPoint(0F, 13.46643F, 18.58669F);
		Neck = new ModelRenderer(this, 85, 78);
		Neck.setTextureSize(128, 128);
		Neck.addBox(-2F, -4F, -14.5F, 4, 8, 15);
		Neck.setRotationPoint(0F, 7.738533F, -0.9885842F);
		Head = new ModelRenderer(this, 9, 60);
		Head.setTextureSize(128, 128);
		Head.addBox(-3F, -3.5F, -7.5F, 6, 7, 7);
		Head.setRotationPoint(0F, -3.019106F, -5.760714F);
		Ear_R = new ModelRenderer(this, 60, 84);
		Ear_R.setTextureSize(128, 128);
		Ear_R.addBox(-4F, -1F, -0.5F, 4, 2, 1);
		Ear_R.setRotationPoint(-2.5F, -4.118599F, -9.194533F);
		Ear2_R = new ModelRenderer(this, 49, 80);
		Ear2_R.setTextureSize(128, 128);
		Ear2_R.addBox(-2.5F, -0.5F, -0.5F, 1, 1, 1);
		Ear2_R.setRotationPoint(-4.761655F, -4.845879F, -9.97298F);
		Ear_L = new ModelRenderer(this, 60, 84);
		Ear_L.setTextureSize(128, 128);
		Ear_L.addBox(0F, -1F, -0.5F, 4, 2, 1);
		Ear_L.setRotationPoint(2.5F, -4.118599F, -9.194533F);
		Ear2_L = new ModelRenderer(this, 49, 80);
		Ear2_L.setTextureSize(128, 128);
		Ear2_L.addBox(1.5F, -0.5F, -0.5F, 1, 1, 1);
		Ear2_L.setRotationPoint(4.761655F, -4.845879F, -9.97298F);
		Bud_R = new ModelRenderer(this, 38, 64);
		Bud_R.setTextureSize(128, 128);
		Bud_R.addBox(-1.5F, -0.5F, -1.5F, 3, 1, 3);
		Bud_R.setRotationPoint(-1.8F, -6.123981F, -8.727151F);
		Horn1_R = new ModelRenderer(this, 38, 71);
		Horn1_R.setTextureSize(128, 128);
		Horn1_R.addBox(-1F, -7.5F, -1F, 2, 7, 2);
		Horn1_R.setRotationPoint(-1.691772F, -5.63872F, -8.63936F);
		Horn2_R = new ModelRenderer(this, 38, 71);
		Horn2_R.setTextureSize(128, 128);
		Horn2_R.addBox(-1F, -7.5F, -1F, 2, 7, 2);
		Horn2_R.setRotationPoint(-3.712005F, -10.69292F, -5.073734F);
		Horn3_R = new ModelRenderer(this, 38, 71);
		Horn3_R.setTextureSize(128, 128);
		Horn3_R.addBox(-1F, -6F, -1F, 2, 6, 2);
		Horn3_R.setRotationPoint(-7.09127F, -10.93122F, 1.051931F);
		Mouth = new ModelRenderer(this, 8, 111);
		Mouth.setTextureSize(128, 128);
		Mouth.addBox(-1.5F, -0.5F, -5.5F, 3, 1, 6);
		Mouth.setRotationPoint(0F, 1.416758F, -10.73395F);
		Beard = new ModelRenderer(this, 31, 111);
		Beard.setTextureSize(128, 128);
		Beard.addBox(-1F, -2F, 0F, 2, 8, 4);
		Beard.setRotationPoint(0F, 3.526743F, -14.81134F);
		Snout3 = new ModelRenderer(this, 9, 86);
		Snout3.setTextureSize(128, 128);
		Snout3.addBox(-1.5F, 0F, -5F, 3, 2, 5);
		Snout3.setRotationPoint(0F, -2.642731F, -13.45297F);
		Nose = new ModelRenderer(this, 40, 91);
		Nose.setTextureSize(128, 128);
		Nose.addBox(-1F, 0F, 0F, 2, 2, 2);
		Nose.setRotationPoint(0F, 0.481411F, -18.02077F);
		Snout1 = new ModelRenderer(this, 11, 76);
		Snout1.setTextureSize(128, 128);
		Snout1.addBox(-1.5F, 0F, -5.5F, 3, 2, 6);
		Snout1.setRotationPoint(-0.01F, -3.844696F, -13.79616F);
		Snout2 = new ModelRenderer(this, 10, 96);
		Snout2.setTextureSize(128, 128);
		Snout2.addBox(-2F, -0.5F, -5F, 4, 3, 6);
		Snout2.setRotationPoint(0F, -1.047844F, -12.85815F);
		Bud_L = new ModelRenderer(this, 38, 64);
		Bud_L.setTextureSize(128, 128);
		Bud_L.addBox(-1.5F, -0.5F, -1.5F, 3, 1, 3);
		Bud_L.setRotationPoint(1.8F, -6.123981F, -8.727151F);
		Horn1_L = new ModelRenderer(this, 38, 71);
		Horn1_L.setTextureSize(128, 128);
		Horn1_L.addBox(-1F, -7.5F, -1F, 2, 7, 2);
		Horn1_L.setRotationPoint(1.691772F, -5.63872F, -8.63936F);
		Horn2_L = new ModelRenderer(this, 38, 71);
		Horn2_L.setTextureSize(128, 128);
		Horn2_L.addBox(-1F, -7.5F, -1F, 2, 7, 2);
		Horn2_L.setRotationPoint(3.712005F, -10.69292F, -5.073734F);
		Horn3_L = new ModelRenderer(this, 38, 71);
		Horn3_L.setTextureSize(128, 128);
		Horn3_L.addBox(-1F, -6F, -1F, 2, 6, 2);
		Horn3_L.setRotationPoint(7.09127F, -10.93122F, 1.051931F);
		
		
		this.Head.addChild(Ear_R);
		this.Head.addChild(Ear2_R);
		this.Head.addChild(Ear_L);
		this.Head.addChild(Ear2_L);
		this.Head.addChild(Bud_R);
		this.Head.addChild(Bud_L);
		this.Head.addChild(Horn1_R);
		this.Head.addChild(Horn2_R);
		this.Head.addChild(Horn3_R);
		this.Head.addChild(Horn1_L);
		this.Head.addChild(Horn2_L);
		this.Head.addChild(Horn3_L);
		this.Head.addChild(Mouth);
		this.Head.addChild(Beard);
		this.Head.addChild(Snout1);
		this.Head.addChild(Snout2);
		this.Head.addChild(Snout3);

	}

	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		
		Body.rotateAngleX = -0.08726646F;
		Butt.rotateAngleX = 0.01847221F;
		Tail.rotateAngleX = -0.6475495F;
		Reproductive1.rotateAngleX = 0.07853982F;
		Reproductive2.rotateAngleX = 0.07853982F;
		Neck.rotateAngleX = -0.9392135F;
		Ear_R.rotateAngleX = 0.398545F;
		Ear_R.rotateAngleY = -0.2031062F;
		Ear_R.rotateAngleZ = 0.3211416F;
		Ear2_R.rotateAngleX = 0.398545F;
		Ear2_R.rotateAngleY = -0.2031062F;
		Ear2_R.rotateAngleZ = 0.3211418F;
		Ear_L.rotateAngleX = 0.398545F;
		Ear_L.rotateAngleY = 0.2031062F;
		Ear_L.rotateAngleZ = -0.3211416F;
		Ear2_L.rotateAngleX = 0.398545F;
		Ear2_L.rotateAngleY = 0.2031062F;
		Ear2_L.rotateAngleZ = -0.3211418F;
		Bud_R.rotateAngleX = 0.1502857F;
		Bud_R.rotateAngleY = -0.1260964F;
		Bud_R.rotateAngleZ = -0.09764144F;
		Horn1_R.rotateAngleX = -0.7273443F;
		Horn1_R.rotateAngleY = -0.4953492F;
		Horn1_R.rotateAngleZ = -0.01269654F;
		Horn2_R.rotateAngleX = -1.390043F;
		Horn2_R.rotateAngleY = -1.887417F;
		Horn2_R.rotateAngleZ = 1.380272F;
		Horn3_R.rotateAngleX = -0.8089435F;
		Horn3_R.rotateAngleY = 3.05143F;
		Horn3_R.rotateAngleZ = 2.665597F;
		Mouth.rotateAngleX = 0.278122F;
		Mouth.rotateAngleY = 0F;
		Mouth.rotateAngleZ = 0F;
		Beard.rotateAngleX = 0.1093521F;
		Beard.rotateAngleY = 0F;
		Beard.rotateAngleZ = 0F;
		Snout3.rotateAngleX = 0.6551131F;
		Snout3.rotateAngleY = 0F;
		Snout3.rotateAngleZ = 0F;
		Nose.rotateAngleX = 0.4037857F;
		Nose.rotateAngleY = 0F;
		Nose.rotateAngleZ = 0F;
		Snout1.rotateAngleX = 1.101219F;
		Snout1.rotateAngleY = 0F;
		Snout1.rotateAngleZ = 0F;
		Snout2.rotateAngleX = 0.4037857F;
		Snout2.rotateAngleY = 0F;
		Snout2.rotateAngleZ = 0F;
		Bud_L.rotateAngleX = 0.1502857F;
		Bud_L.rotateAngleY = 0.1260964F;
		Bud_L.rotateAngleZ = 0.09764142F;
		Horn1_L.rotateAngleX = -0.7273443F;
		Horn1_L.rotateAngleY = 0.4953492F;
		Horn1_L.rotateAngleZ = 0.01269654F;
		Horn2_L.rotateAngleX = -1.390043F;
		Horn2_L.rotateAngleY = 1.887417F;
		Horn2_L.rotateAngleZ = -1.380272F;
		Horn3_L.rotateAngleX = -0.8089435F;
		Horn3_L.rotateAngleY = -3.05143F;
		Horn3_L.rotateAngleZ = -2.665597F;
		
		Body.renderWithRotation(scale);
		Butt.renderWithRotation(scale);
		Tail.renderWithRotation(scale);
		BackLeg_L.renderWithRotation(scale);	
		BackLeg_R.renderWithRotation(scale);
		FrontLeg_L.renderWithRotation(scale);
		FrontLeg_R.renderWithRotation(scale);  
		Reproductive1.renderWithRotation(scale);
		Reproductive2.renderWithRotation(scale);
		Head.renderWithRotation(scale);

//		Neck.renderWithRotation(scale);
//		Ear_R.renderWithRotation(scale);
//		Ear2_R.renderWithRotation(scale);
//		Ear_L.renderWithRotation(scale);
//		Ear2_L.renderWithRotation(scale);
//		Bud_R.renderWithRotation(scale);
//		Horn1_R.renderWithRotation(scale);
//		Horn2_R.renderWithRotation(scale);
//		Horn3_R.renderWithRotation(scale);
//		Mouth.renderWithRotation(scale);
//		Beard.renderWithRotation(scale);
//		Snout3.renderWithRotation(scale);
//		Nose.renderWithRotation(scale);
//		Snout1.renderWithRotation(scale);
//		Snout2.renderWithRotation(scale);
//		Bud_L.renderWithRotation(scale);
//		Horn1_L.renderWithRotation(scale);
//		Horn2_L.renderWithRotation(scale);
//		Horn3_L.renderWithRotation(scale);

		


	}

	@Override
	public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float p_78086_2_, float p_78086_3_, float partialTickTime)
	{
		super.setLivingAnimations(entitylivingbaseIn, p_78086_2_, p_78086_3_, partialTickTime);
	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity scale)
	{
		float f6 = 180F / (float) Math.PI;

		this.Body.rotateAngleX = (float) Math.PI / 2F;

		this.Head.rotateAngleX = par5 / (180F / (float) Math.PI);
		this.Head.rotateAngleY = par4 / (180F / (float) Math.PI);
		// this.Head.rotateAngleX = this.headRotationAngleX;

		this.Tail.rotateAngleX = (float) Math.PI / 2F;
		this.Tail.rotateAngleY = MathHelper.sin(par3 * 3.141593F * 0.05F) * MathHelper.sin(par3 * 3.141593F * .03F * 0.05F) * 0.15F * 3.141593F;

		this.BackLeg_L.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.BackLeg_R.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
		this.FrontLeg_L.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
		this.FrontLeg_R.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
	}

}
