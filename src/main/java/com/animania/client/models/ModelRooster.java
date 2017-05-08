package com.animania.client.models;

import com.animania.common.entities.chickens.EntityRoosterLeghorn;
import com.animania.common.entities.chickens.EntityRoosterOrpington;
import com.animania.common.entities.chickens.EntityRoosterPlymouthRock;
import com.animania.common.entities.chickens.EntityRoosterRhodeIslandRed;
import com.animania.common.entities.chickens.EntityRoosterWyandotte;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

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
		this( 0.0f );
	}

	public ModelRooster( float par1 )
	{
		RootNode = new ModelRenderer( this, 16, 16 );
		RootNode.setTextureSize( 64, 32 );
		RootNode.addBox( 0F, 0F, 0F, 0, 0, 0);
		RootNode.setRotationPoint( 0F, 15F, 0F );

		Body1 = new ModelRenderer( this, 0, 7 );
		Body1.setTextureSize( 64, 32 );
		Body1.addBox( -3F, -3F, -3F, 6, 6, 6);
		Body1.setRotationPoint( 0F, 17.99997F, 2F );

		Wing1 = new ModelRenderer( this, 30, 6 );
		Wing1.setTextureSize( 64, 32 );
		Wing1.addBox( -0.5F, -1.5F, 0F, 1, 3, 4);
		Wing1.setRotationPoint( -3.5F, 17.3922F, -1.446827F );

		Tail1 = new ModelRenderer( this, 36, 20 );
		Tail1.setTextureSize( 64, 32 );
		Tail1.addBox( -2.5F, -2F, -2F, 5, 4, 4);
		Tail1.setRotationPoint( 0F, 17.62293F, 5.620475F );

		Tail2 = new ModelRenderer( this, 25, 14 );
		Tail2.setTextureSize( 64, 32 );
		Tail2.addBox( -2F, -1F, -1.5F, 4, 2, 3);
		//Tail2.setRotationPoint( 0F, 16.11382F, 7.850402F );
		Tail2.setRotationPoint( 0F, -1.50911F, 2.23F );

		Feather1 = new ModelRenderer( this, 45, 8 );
		Feather1.setTextureSize( 64, 32 );
		Feather1.addBox( 0F, -2.5F, -3F, 0, 5, 6);
		Feather1.setRotationPoint( -1.5F, 12.69338F, 8.745152F );

		Feather2 = new ModelRenderer( this, 45, 8 );
		Feather2.setTextureSize( 64, 32 );
		Feather2.addBox( 0F, -2.5F, -3F, 0, 5, 6);
		Feather2.setRotationPoint( 1.5F, 12.69338F, 8.745152F );

		Feather3 = new ModelRenderer( this, 44, 7 );
		Feather3.setTextureSize( 64, 32 );
		Feather3.addBox( 0F, -3F, -3.5F, 0, 6, 7);
		Feather3.setRotationPoint( 0F, 11.3252F, 9.103053F );

		Wing2 = new ModelRenderer( this, 41, 6 );
		Wing2.setTextureSize( 64, 32 );
		Wing2.addBox( -0.5F, -1.5F, 0F, 1, 3, 4);
		Wing2.setRotationPoint( 3.5F, 17.3922F, -1.446827F );

		Leg2Pivot = new ModelRenderer( this, 16, 16 );
		Leg2Pivot.setTextureSize( 64, 32 );
		Leg2Pivot.addBox( 0F, 0F, 0F, 0, 0, 0);
		Leg2Pivot.setRotationPoint( 1.5F, 19.9968F, 1.749448F );

		leg2Top = new ModelRenderer( this, 39, 0 );
		leg2Top.setTextureSize( 64, 32 );
		leg2Top.addBox( -1F, -1F, -1F, 2, 2, 2);
		//leg2Top.setRotationPoint( 1.5F, 21.29997F, 1.75F );
		leg2Top.setRotationPoint( 0F, 1.30317F, 0F );

		leg2 = new ModelRenderer( this, 35, 0 );
		leg2.setTextureSize( 64, 32 );
		leg2.addBox( -0.5F, -2F, -0.5F, 1, 2, 1);
		//leg2.setRotationPoint( 1.5F, 24.09997F, 0.9999997F );
		leg2.setRotationPoint( 0F, 4.13197F, -.749448F );

		Foot2 = new ModelRenderer( this, 25, 0 );
		Foot2.setTextureSize( 64, 32 );
		Foot2.addBox( -0.5F, -0.5F, -1.5F, 1, 1, 3);
		//Foot2.setRotationPoint( 0.5000001F, 23.99997F, 2.927502E-07F );
		Foot2.setRotationPoint( -1F, 4.0032F, 2.927502E-07F-1.75F );

		Foot2b = new ModelRenderer( this, 25, 0 );
		Foot2b.setTextureSize( 64, 32 );
		Foot2b.addBox( -0.5F, -0.5F, -1.5F, 1, 1, 3);
		//Foot2b.setRotationPoint( 2.5F, 23.99997F, -1.678391E-07F );
		Foot2b.setRotationPoint( 1F, 4.0032F, -1.678391E-07F-1.75F );

		Leg1Pivot = new ModelRenderer( this, 16, 16 );
		Leg1Pivot.setTextureSize( 64, 32 );
		Leg1Pivot.addBox( 0F, 0F, 0F, 0, 0, 0);
		Leg1Pivot.setRotationPoint( -1.5F, 19.9968F, 1.749448F );

		leg1Top = new ModelRenderer( this, 39, 0 );
		leg1Top.setTextureSize( 64, 32 );
		leg1Top.addBox( -1F, -1F, -1F, 2, 2, 2);
		//leg1Top.setRotationPoint( -1.5F, 21.29997F, 1.75F );
		leg1Top.setRotationPoint( 0F, 1.30317F, 0F );

		leg1 = new ModelRenderer( this, 35, 0 );
		leg1.setTextureSize( 64, 32 );
		leg1.addBox( -0.5F, -2F, -0.5F, 1, 2, 1);
		//leg1.setRotationPoint( -1.5F, 24.09997F, 0.9999997F );
		leg1.setRotationPoint( 0F, 4.13197F, -.749448F );

		Foot1 = new ModelRenderer( this, 25, 0 );
		Foot1.setTextureSize( 64, 32 );
		Foot1.addBox( -0.5F, -0.5F, -1.5F, 1, 1, 3);
		//Foot1.setRotationPoint( -2.5F, 23.99997F, -2.747192E-07F );
		Foot1.setRotationPoint( -1F, 4.0032F, -2.747192E-07F - 1.75F ); //was 1

		Foot1b = new ModelRenderer( this, 25, 0 );
		Foot1b.setTextureSize( 64, 32 );
		Foot1b.addBox( -0.5F, -0.5F, -1.5F, 1, 1, 3);
		//Foot1b.setRotationPoint( -0.5000001F, 23.99997F, -2.747192E-07F );
		Foot1b.setRotationPoint( 1F, 4.0032F, -2.747192E-07F - 1.75F);

		Neck = new ModelRenderer( this, 3, 19 );
		Neck.setTextureSize( 64, 32 );
		Neck.addBox( -2.5F, -2.5F, -2F, 5, 5, 4);
		Neck.setRotationPoint( 0F, 16.8998F, -1F );

		Neck2 = new ModelRenderer( this, 21, 21 );
		Neck2.setTextureSize( 64, 32 );
		Neck2.addBox( -2F, -2F, -1.5F, 4, 4, 3);
		//Neck2.setRotationPoint( 0F, 15.40154F, -3.01686F );
		Neck2.setRotationPoint( 0F, -1.88264F, -2.01686F );

		Head = new ModelRenderer( this, 0, 0 );
		Head.setTextureSize( 64, 32 );
		Head.addBox( -1.5F, -2F, -1.5F, 3, 4, 3);
		//Head.setRotationPoint( 0F, 12.87489F, -4.212082F );
		Head.setRotationPoint( 0F, -4.025F, -3.212082F );

		Crest = new ModelRenderer( this, 19, 3 );
		Crest.setTextureSize( 64, 32 );
		Crest.addBox( 0F, -1.5F, -2F, 0, 3, 4);
		//Crest.setRotationPoint( 0F, 10.3283F, -5.265868F );
		Crest.setRotationPoint( 0F, -6.57F, -4.265868F );

		CrestBottom = new ModelRenderer( this, 22, 6 );
		CrestBottom.setTextureSize( 64, 32 );
		CrestBottom.addBox( 0F, -1F, -0.5F, 0, 2, 1);
		//CrestBottom.setRotationPoint( 0F, 13.94243F, -6.078995F );
		CrestBottom.setRotationPoint( 0F, -2.955F, -5.078995F );

		BeakBottom = new ModelRenderer( this, 19, 0 );
		BeakBottom.setTextureSize( 64, 32 );
		BeakBottom.addBox( -0.5F, -0.5F, -1F, 1, 1, 2);
		//BeakBottom.setRotationPoint( 0F, 12.92235F, -6.227087F );
		BeakBottom.setRotationPoint( 0F, -3.97745F, -5.227087F );

		BeakTop = new ModelRenderer( this, 19, 0 );
		BeakTop.setTextureSize( 64, 32 );
		BeakTop.addBox( -0.5F, -0.5F, -1F, 1, 1, 2);
		//BeakTop.setRotationPoint( 0F, 12.63394F, -6.097404F );
		BeakTop.setRotationPoint( 0F, -4.26586F, -5.097404F );

		this.Neck.addChild(Neck2);
		this.Neck.addChild(Head);
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
	public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwingAmount, float ageInTicks, float partialTickTime)
	{

		super.setLivingAnimations(entitylivingbaseIn, limbSwingAmount, ageInTicks, partialTickTime);
		
		if (limbSwingAmount > lastLimb) {
			this.Neck.rotateAngleX = Math.abs(((30F / (float)Math.PI)) * 1.4F * limbSwingAmount);
		}  else {
			//do nothing
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
		
		long currentTime = entityIn.world.getWorldTime();

		
		
		if ((currentTime > 23250 || currentTime < 500)) {

			int crowDuration = 0;

			if (entityIn instanceof EntityRoosterOrpington) {
				EntityRoosterOrpington rooster = (EntityRoosterOrpington) entityIn;
				crowDuration = rooster.getCrowDuration();
			} else if (entityIn instanceof EntityRoosterPlymouthRock) {
				EntityRoosterPlymouthRock rooster = (EntityRoosterPlymouthRock) entityIn;
				crowDuration = rooster.getCrowDuration();
			} else if (entityIn instanceof EntityRoosterRhodeIslandRed) {
				EntityRoosterRhodeIslandRed rooster = (EntityRoosterRhodeIslandRed) entityIn;
				crowDuration = rooster.getCrowDuration();
			} else if (entityIn instanceof EntityRoosterWyandotte) {
				EntityRoosterWyandotte rooster = (EntityRoosterWyandotte) entityIn;
				crowDuration = rooster.getCrowDuration();
			} else if (entityIn instanceof EntityRoosterLeghorn) {
				EntityRoosterLeghorn rooster = (EntityRoosterLeghorn) entityIn;
				crowDuration = rooster.getCrowDuration();
			}

			if (crowDuration > 0) {

				if (crowDuration < 10) {
					this.Neck.rotateAngleX = 0F - (float)(crowDuration * .005);
				} else if (crowDuration >= 40) {
					this.Neck.rotateAngleX = -0.5742105F + (float)(crowDuration * .005);
				} else {
					this.Neck.rotateAngleX = -0.5742105F;
				}

			} else {
				this.Neck.rotateAngleX = Math.abs((headPitch / (180F / (float)Math.PI)) * 1.4F * limbSwingAmount);
				this.Neck.rotateAngleX = Math.abs(netHeadYaw / (180F / (float)Math.PI));
			}

		} else {
			this.Neck.rotateAngleX = Math.abs((headPitch / (180F / (float)Math.PI)) * 1.4F * limbSwingAmount);
			this.Neck.rotateAngleX = Math.abs(netHeadYaw / (180F / (float)Math.PI));
		}
		
		this.Body1.rotateAngleX = ((float)Math.PI / 2F);

		this.Leg1Pivot.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.Leg2Pivot.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;

		this.Wing1.rotateAngleZ = ageInTicks;
		this.Wing2.rotateAngleZ = -ageInTicks;
	}

}
