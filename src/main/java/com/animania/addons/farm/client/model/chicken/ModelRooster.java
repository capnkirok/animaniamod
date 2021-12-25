package com.animania.addons.farm.client.model.chicken;

import com.animania.addons.farm.common.entity.chickens.ChickenLeghorn.EntityRoosterLeghorn;
import com.animania.addons.farm.common.entity.chickens.ChickenOrpington.EntityRoosterOrpington;
import com.animania.addons.farm.common.entity.chickens.ChickenPlymouthRock.EntityRoosterPlymouthRock;
import com.animania.addons.farm.common.entity.chickens.ChickenRhodeIslandRed.EntityRoosterRhodeIslandRed;
import com.animania.addons.farm.common.entity.chickens.ChickenWyandotte.EntityRoosterWyandotte;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.entity.LivingEntity;

public class ModelRooster extends ModelBase
{
	private float lastLimb;
	ModelRenderer RootNode;
	ModelRenderer Body1;
	ModelRenderer Wing1;
	ModelRenderer Tail1;
	ModelRenderer Tail2;
	ModelRenderer Feather1;
	ModelRenderer Feather2;
	ModelRenderer Feather3;
	ModelRenderer Wing2;
	ModelRenderer Leg2Pivot;
	ModelRenderer leg2Top;
	ModelRenderer leg2;
	ModelRenderer Foot2;
	ModelRenderer Foot2b;
	ModelRenderer Leg1Pivot;
	ModelRenderer leg1Top;
	ModelRenderer leg1;
	ModelRenderer Foot1;
	ModelRenderer Foot1b;
	ModelRenderer Neck;
	ModelRenderer Neck2;
	ModelRenderer Head;
	ModelRenderer Crest;
	ModelRenderer CrestBottom;
	ModelRenderer BeakBottom;
	ModelRenderer BeakTop;

	public ModelRooster()
	{
		this(0.0f);
	}

	public ModelRooster(float par1)
	{
		this.RootNode = new ModelRenderer(this, 16, 16);
		this.RootNode.setTextureSize(64, 32);
		this.RootNode.addBox(0F, 0F, 0F, 0, 0, 0);
		this.RootNode.setRotationPoint(0F, 15F, 0F);

		this.Body1 = new ModelRenderer(this, 0, 7);
		this.Body1.setTextureSize(64, 32);
		this.Body1.addBox(-3F, -3F, -3F, 6, 6, 6);
		this.Body1.setRotationPoint(0F, 17.99997F, 2F);

		this.Wing1 = new ModelRenderer(this, 30, 6);
		this.Wing1.setTextureSize(64, 32);
		this.Wing1.addBox(-0.5F, -1.5F, 0F, 1, 3, 4);
		this.Wing1.setRotationPoint(-3.5F, 17.3922F, -1.446827F);

		this.Tail1 = new ModelRenderer(this, 36, 20);
		this.Tail1.setTextureSize(64, 32);
		this.Tail1.addBox(-2.5F, -2F, -2F, 5, 4, 4);
		this.Tail1.setRotationPoint(0F, 17.62293F, 5.620475F);

		this.Tail2 = new ModelRenderer(this, 25, 14);
		this.Tail2.setTextureSize(64, 32);
		this.Tail2.addBox(-2F, -1F, -1.5F, 4, 2, 3);
		// Tail2.setRotationPoint( 0F, 16.11382F, 7.850402F );
		this.Tail2.setRotationPoint(0F, -1.50911F, 2.23F);

		this.Feather1 = new ModelRenderer(this, 45, 8);
		this.Feather1.setTextureSize(64, 32);
		this.Feather1.addBox(0F, -2.5F, -3F, 0, 5, 6);
		this.Feather1.setRotationPoint(-1.5F, 12.69338F, 8.745152F);

		this.Feather2 = new ModelRenderer(this, 45, 8);
		this.Feather2.setTextureSize(64, 32);
		this.Feather2.addBox(0F, -2.5F, -3F, 0, 5, 6);
		this.Feather2.setRotationPoint(1.5F, 12.69338F, 8.745152F);

		this.Feather3 = new ModelRenderer(this, 44, 7);
		this.Feather3.setTextureSize(64, 32);
		this.Feather3.addBox(0F, -3F, -3.5F, 0, 6, 7);
		this.Feather3.setRotationPoint(0F, 11.3252F, 9.103053F);

		this.Wing2 = new ModelRenderer(this, 41, 6);
		this.Wing2.setTextureSize(64, 32);
		this.Wing2.addBox(-0.5F, -1.5F, 0F, 1, 3, 4);
		this.Wing2.setRotationPoint(3.5F, 17.3922F, -1.446827F);

		this.Leg2Pivot = new ModelRenderer(this, 16, 16);
		this.Leg2Pivot.setTextureSize(64, 32);
		this.Leg2Pivot.addBox(0F, 0F, 0F, 0, 0, 0);
		this.Leg2Pivot.setRotationPoint(1.5F, 19.9968F, 1.749448F);

		this.leg2Top = new ModelRenderer(this, 39, 0);
		this.leg2Top.setTextureSize(64, 32);
		this.leg2Top.addBox(-1F, -1F, -1F, 2, 2, 2);
		// leg2Top.setRotationPoint( 1.5F, 21.29997F, 1.75F );
		this.leg2Top.setRotationPoint(0F, 1.30317F, 0F);

		this.leg2 = new ModelRenderer(this, 35, 0);
		this.leg2.setTextureSize(64, 32);
		this.leg2.addBox(-0.5F, -2F, -0.5F, 1, 2, 1);
		// leg2.setRotationPoint( 1.5F, 24.09997F, 0.9999997F );
		this.leg2.setRotationPoint(0F, 4.13197F, -.749448F);

		this.Foot2 = new ModelRenderer(this, 25, 0);
		this.Foot2.setTextureSize(64, 32);
		this.Foot2.addBox(-0.5F, -0.5F, -1.5F, 1, 1, 3);
		// Foot2.setRotationPoint( 0.5000001F, 23.99997F, 2.927502E-07F );
		this.Foot2.setRotationPoint(-1F, 4.0032F, 2.927502E-07F - 1.75F);

		this.Foot2b = new ModelRenderer(this, 25, 0);
		this.Foot2b.setTextureSize(64, 32);
		this.Foot2b.addBox(-0.5F, -0.5F, -1.5F, 1, 1, 3);
		// Foot2b.setRotationPoint( 2.5F, 23.99997F, -1.678391E-07F );
		this.Foot2b.setRotationPoint(1F, 4.0032F, -1.678391E-07F - 1.75F);

		this.Leg1Pivot = new ModelRenderer(this, 16, 16);
		this.Leg1Pivot.setTextureSize(64, 32);
		this.Leg1Pivot.addBox(0F, 0F, 0F, 0, 0, 0);
		this.Leg1Pivot.setRotationPoint(-1.5F, 19.9968F, 1.749448F);

		this.leg1Top = new ModelRenderer(this, 39, 0);
		this.leg1Top.setTextureSize(64, 32);
		this.leg1Top.addBox(-1F, -1F, -1F, 2, 2, 2);
		// leg1Top.setRotationPoint( -1.5F, 21.29997F, 1.75F );
		this.leg1Top.setRotationPoint(0F, 1.30317F, 0F);

		this.leg1 = new ModelRenderer(this, 35, 0);
		this.leg1.setTextureSize(64, 32);
		this.leg1.addBox(-0.5F, -2F, -0.5F, 1, 2, 1);
		// leg1.setRotationPoint( -1.5F, 24.09997F, 0.9999997F );
		this.leg1.setRotationPoint(0F, 4.13197F, -.749448F);

		this.Foot1 = new ModelRenderer(this, 25, 0);
		this.Foot1.setTextureSize(64, 32);
		this.Foot1.addBox(-0.5F, -0.5F, -1.5F, 1, 1, 3);
		// Foot1.setRotationPoint( -2.5F, 23.99997F, -2.747192E-07F );
		this.Foot1.setRotationPoint(-1F, 4.0032F, -2.747192E-07F - 1.75F); // was
																			// 1

		this.Foot1b = new ModelRenderer(this, 25, 0);
		this.Foot1b.setTextureSize(64, 32);
		this.Foot1b.addBox(-0.5F, -0.5F, -1.5F, 1, 1, 3);
		// Foot1b.setRotationPoint( -0.5000001F, 23.99997F, -2.747192E-07F );
		this.Foot1b.setRotationPoint(1F, 4.0032F, -2.747192E-07F - 1.75F);

		this.Neck = new ModelRenderer(this, 3, 19);
		this.Neck.setTextureSize(64, 32);
		this.Neck.addBox(-2.5F, -2.5F, -2F, 5, 5, 4);
		this.Neck.setRotationPoint(0F, 16.8998F, -1F);

		this.Neck2 = new ModelRenderer(this, 21, 21);
		this.Neck2.setTextureSize(64, 32);
		this.Neck2.addBox(-2F, -2F, -1.5F, 4, 4, 3);
		// Neck2.setRotationPoint( 0F, 15.40154F, -3.01686F );
		this.Neck2.setRotationPoint(0F, -1.88264F, -2.01686F);

		this.Head = new ModelRenderer(this, 0, 0);
		this.Head.setTextureSize(64, 32);
		this.Head.addBox(-1.5F, -2F, -1.5F, 3, 4, 3);
		// Head.setRotationPoint( 0F, 12.87489F, -4.212082F );
		this.Head.setRotationPoint(0F, -4.025F, -3.212082F);

		this.Crest = new ModelRenderer(this, 19, 3);
		this.Crest.setTextureSize(64, 32);
		this.Crest.addBox(0F, -1.5F, -2F, 0, 3, 4);
		// Crest.setRotationPoint( 0F, 10.3283F, -5.265868F );
		this.Crest.setRotationPoint(0F, -6.57F, -4.265868F);

		this.CrestBottom = new ModelRenderer(this, 22, 6);
		this.CrestBottom.setTextureSize(64, 32);
		this.CrestBottom.addBox(0F, -1F, -0.5F, 0, 2, 1);
		// CrestBottom.setRotationPoint( 0F, 13.94243F, -6.078995F );
		this.CrestBottom.setRotationPoint(0F, -2.955F, -5.078995F);

		this.BeakBottom = new ModelRenderer(this, 19, 0);
		this.BeakBottom.setTextureSize(64, 32);
		this.BeakBottom.addBox(-0.5F, -0.5F, -1F, 1, 1, 2);
		// BeakBottom.setRotationPoint( 0F, 12.92235F, -6.227087F );
		this.BeakBottom.setRotationPoint(0F, -3.97745F, -5.227087F);

		this.BeakTop = new ModelRenderer(this, 19, 0);
		this.BeakTop.setTextureSize(64, 32);
		this.BeakTop.addBox(-0.5F, -0.5F, -1F, 1, 1, 2);
		// BeakTop.setRotationPoint( 0F, 12.63394F, -6.097404F );
		this.BeakTop.setRotationPoint(0F, -4.26586F, -5.097404F);

		this.Neck.addChild(this.Neck2);
		this.Neck.addChild(this.Head);
		this.Neck.addChild(this.BeakTop);
		this.Neck.addChild(this.BeakBottom);
		this.Neck.addChild(this.Crest);
		this.Neck.addChild(this.CrestBottom);

		this.Leg1Pivot.addChild(this.leg1Top);
		this.Leg1Pivot.addChild(this.leg1);
		this.Leg1Pivot.addChild(this.Foot1);
		this.Leg1Pivot.addChild(this.Foot1b);

		this.Leg2Pivot.addChild(this.leg2Top);
		this.Leg2Pivot.addChild(this.leg2);
		this.Leg2Pivot.addChild(this.Foot2);
		this.Leg2Pivot.addChild(this.Foot2b);

		this.Tail1.addChild(this.Tail2);

	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{

		this.Neck.render(scale);
		this.Body1.render(scale);
		this.Leg1Pivot.render(scale);
		this.Leg2Pivot.render(scale);
		this.Wing1.render(scale);
		this.Wing2.render(scale);
		this.Feather1.render(scale);
		this.Feather2.render(scale);
		this.Feather3.render(scale);
		this.Tail1.render(scale);

		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
	}

	@Override
	public void setLivingAnimations(LivingEntity LivingEntityIn, float limbSwingAmount, float ageInTicks, float partialTickTime)
	{

		super.setLivingAnimations(LivingEntityIn, limbSwingAmount, ageInTicks, partialTickTime);

		if (limbSwingAmount > this.lastLimb)
			this.Neck.rotateAngleX = Math.abs(30F / (float) Math.PI * 1.4F * limbSwingAmount);
		else
		{
			// do nothing
		}

	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
	{

		this.Body1.rotateAngleX = -0.1745329F;
		this.Crest.rotateAngleX = 0.3490659F;
		this.Tail1.rotateAngleX = 0.2144478F;
		this.Tail2.rotateAngleX = 0.5295422F;
		this.Feather1.rotateAngleX = 0.5097123F;
		this.Feather1.rotateAngleY = -0.3010362F;
		this.Feather1.rotateAngleZ = -0.1503443F;
		this.Feather2.rotateAngleX = 0.5097123F;
		this.Feather2.rotateAngleY = 0.3010362F;
		this.Feather2.rotateAngleZ = 0.1503443F;
		this.Feather3.rotateAngleX = 0.5295422F;
		this.Feather3.rotateAngleY = 0F;
		this.Feather3.rotateAngleZ = 0F;
		this.leg2Top.rotateAngleX = 0.2617994F;
		this.leg2.rotateAngleX = -0.2617995F;
		this.Foot2.rotateAngleX = -1.555994E-08F;
		this.Foot2b.rotateAngleX = -4.214685E-08F;
		this.leg1Top.rotateAngleX = 0.2617994F;
		this.leg1.rotateAngleX = -0.2617995F;
		this.Foot1.rotateAngleX = -1.555994E-08F;
		this.Foot1b.rotateAngleX = -4.214685E-08F;
		this.Neck.rotateAngleX = -0.4742105F;
		this.Neck2.rotateAngleX = -0.7360098F;
		this.Head.rotateAngleX = 0.05872217F;
		this.Crest.rotateAngleX = 0.3490659F;
		this.CrestBottom.rotateAngleX = 5.213158E-08F;
		this.BeakBottom.rotateAngleX = 0.05872219F;
		this.BeakTop.rotateAngleX = 0.3169494F;

		long currentTime = entityIn.level.getLevelTime();

		if (currentTime > 23250 || currentTime < 500)
		{

			int crowDuration = 0;

			if ((entityIn instanceof EntityRoosterOrpington rooster) || (entityIn instanceof EntityRoosterPlymouthRock rooster) || (entityIn instanceof EntityRoosterRhodeIslandRed rooster) || (entityIn instanceof EntityRoosterWyandotte rooster))
			{
				crowDuration = rooster.getCrowDuration();
			}
			else if (entityIn instanceof EntityRoosterLeghorn rooster)
			{
				crowDuration = rooster.getCrowDuration();
			}

			if (crowDuration > 0)
			{

				if (crowDuration < 10)
					this.Neck.rotateAngleX = 0F - (float) (crowDuration * .005);
				else if (crowDuration >= 40)
					this.Neck.rotateAngleX = -0.5742105F + (float) (crowDuration * .005);
				else
					this.Neck.rotateAngleX = -0.5742105F;

			}
			else
			{
				this.Neck.rotateAngleX = Math.abs(headPitch / (180F / (float) Math.PI) * 1.4F * limbSwingAmount);
				this.Neck.rotateAngleX = Math.abs(netHeadYaw / (180F / (float) Math.PI));
			}

		}
		else
		{
			this.Neck.rotateAngleX = Math.abs(headPitch / (180F / (float) Math.PI) * 1.4F * limbSwingAmount);
			this.Neck.rotateAngleX = Math.abs(netHeadYaw / (180F / (float) Math.PI));
		}

		this.Body1.rotateAngleX = (float) Math.PI / 2F;

		this.Leg1Pivot.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.Leg2Pivot.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;

		this.Wing1.rotateAngleZ = ageInTicks;
		this.Wing2.rotateAngleZ = -ageInTicks;
	}

}
