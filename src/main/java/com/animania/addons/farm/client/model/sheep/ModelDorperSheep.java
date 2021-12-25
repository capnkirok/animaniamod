package com.animania.addons.farm.client.model.sheep;

import com.animania.addons.farm.common.entity.sheep.EntityAnimaniaSheep;
import com.animania.addons.farm.common.entity.sheep.SheepDorper.EntityEweDorper;
import com.animania.addons.farm.common.entity.sheep.SheepDorper.EntityLambDorper;
import com.animania.addons.farm.common.entity.sheep.SheepDorper.EntityRamDorper;
import com.animania.client.models.IColoredModel;
import com.animania.client.models.render.ModelRendererColored;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.entity.LivingEntity;

public class ModelDorperSheep extends ModelBase implements IColoredModel
{
	private float headRotationAngleX;
	public ModelRenderer HeadNode = new ModelRenderer(this, 0, 0);

	ModelRenderer Body;
	ModelRenderer RightFrontLeg;
	ModelRenderer Hips;
	ModelRenderer RightBackLeg;
	ModelRenderer Tail;
	ModelRendererColored WoolBody1;
	ModelRendererColored WoolBody2;
	ModelRendererColored WoolHips;
	ModelRenderer LeftBackLeg;
	ModelRenderer Neck;
	ModelRenderer Head;
	ModelRenderer UpperJaw;
	ModelRenderer Nose;
	ModelRenderer UpperJawDetail1;
	ModelRenderer UpperJawDetail2;
	ModelRenderer LowerJaw;
	ModelRenderer LeftEar1;
	ModelRenderer LeftEar2;
	ModelRenderer RightEar1;
	ModelRenderer RightEar2;
	ModelRendererColored NeckWool;
	ModelRendererColored NeckWool2;
	ModelRenderer LeftFrontLeg;

	public ModelDorperSheep()
	{
		this(0.0f);
	}

	public ModelDorperSheep(float par1)
	{

		this.Body = new ModelRenderer(this, 0, 39);
		this.Body.setTextureSize(128, 128);
		this.Body.addBox(-3.5F, -5.5F, -13.5F, 7, 13, 17);
		this.Body.setRotationPoint(0F, 7F, 0F + 7F);
		this.RightFrontLeg = new ModelRenderer(this, 107, 4);
		this.RightFrontLeg.setTextureSize(128, 128);
		this.RightFrontLeg.addBox(-1.5F, 0F, -1.5F, 3, 16, 3);
		this.RightFrontLeg.setRotationPoint(-4F, 9.149483F, -10.08116F + 7F);
		this.Hips = new ModelRenderer(this, 56, 25);
		this.Hips.setTextureSize(128, 128);
		this.Hips.addBox(-4F, -1F, 0F, 8, 12, 11);
		this.Hips.setRotationPoint(0F, 2.675018F, 1.651946F + 7F);
		this.RightBackLeg = new ModelRenderer(this, 107, 26);
		this.RightBackLeg.setTextureSize(128, 128);
		this.RightBackLeg.addBox(-1.5F, -0.5F, -1.5F, 3, 18, 3);
		this.RightBackLeg.setRotationPoint(-4.5F, 7.543364F, 9.238067F + 7F);
		this.Tail = new ModelRenderer(this, 0, 0);
		this.Tail.setTextureSize(128, 128);
		this.Tail.addBox(-1.5F, -1F, -2F, 3, 7, 3);
		this.Tail.setRotationPoint(0F, 3.352909F, 12.66545F + 7F);
		this.WoolBody1 = new ModelRendererColored(this, 40, 100);
		this.WoolBody1.setTextureSize(128, 128);
		this.WoolBody1.addBox(-4.5F, -1.9F, -6F, 9, 8, 12);
		this.WoolBody1.setRotationPoint(0F, 3.205133F, -0.06906392F + 7F);
		this.WoolBody2 = new ModelRendererColored(this, 1, 104);
		this.WoolBody2.setTextureSize(128, 128);
		this.WoolBody2.addBox(-5F, -2.5F, -5F, 10, 7, 8);
		this.WoolBody2.setRotationPoint(0F, 2.858269F, -8.846455F + 7F);
		this.WoolHips = new ModelRendererColored(this, 85, 104);
		this.WoolHips.setTextureSize(128, 128);
		this.WoolHips.addBox(-5.5F, -2.5F, -6F, 11, 7, 8);
		this.WoolHips.setRotationPoint(0F, 3.509068F, 11.16795F + 7F);
		this.LeftBackLeg = new ModelRenderer(this, 107, 26);
		this.LeftBackLeg.setTextureSize(128, 128);
		this.LeftBackLeg.addBox(-1.5F, -0.5F, -1.5F, 3, 18, 3);
		this.LeftBackLeg.setRotationPoint(4.5F, 7.543364F, 9.238067F + 7F);
		this.LeftFrontLeg = new ModelRenderer(this, 107, 4);
		this.LeftFrontLeg.setTextureSize(128, 128);
		this.LeftFrontLeg.addBox(-1.5F, 0F, -1.5F, 3, 16, 3);
		this.LeftFrontLeg.setRotationPoint(4F, 9.149483F, -10.08116F + 7F);

		this.HeadNode = new ModelRenderer(this, 0, 19);
		this.HeadNode.setTextureSize(128, 128);
		this.HeadNode.addBox(0F, 0F, 0F, 0, 0, 0);
		this.HeadNode.setRotationPoint(0F, 5.110041F, -11.14083F + 7F);

		this.Neck = new ModelRenderer(this, 0, 18);
		this.Neck.setTextureSize(128, 128);
		this.Neck.addBox(-2.5F, -1.5F, -10.5F, 5, 7, 11);
		this.Neck.setRotationPoint(0F, 0F, 0F);

		this.Head = new ModelRenderer(this, 32, 25);
		this.Head.setTextureSize(128, 128);
		this.Head.addBox(-3F, -2F, -4F, 6, 7, 5);
		this.Head.setRotationPoint(0F, -0.022192F - 5.110041F, -19.57529F + 11.14083F);

		this.UpperJaw = new ModelRenderer(this, 32, 39);
		this.UpperJaw.setTextureSize(128, 128);
		this.UpperJaw.addBox(-2F, 0F, -5F, 4, 3, 5);
		this.UpperJaw.setRotationPoint(0F, 2.052254F - 5.110041F, -23.13853F + 11.14083F);
		this.Nose = new ModelRenderer(this, 0, 36);
		this.Nose.setTextureSize(128, 128);
		this.Nose.addBox(0F, 0F, 0F, 2, 2, 2);
		this.Nose.setRotationPoint(-1F, 3.780466F - 5.110041F, -28.36378F + 11.14083F);
		this.UpperJawDetail1 = new ModelRenderer(this, 0, 10);
		this.UpperJawDetail1.setTextureSize(128, 128);
		this.UpperJawDetail1.addBox(-1.5F, 0F, -5F, 3, 2, 5);
		this.UpperJawDetail1.setRotationPoint(0F, -0.6369667F - 5.110041F, -23.91834F + 11.14083F);
		this.UpperJawDetail2 = new ModelRenderer(this, 14, 0);
		this.UpperJawDetail2.setTextureSize(128, 128);
		this.UpperJawDetail2.addBox(-1.5F, 0F, -5F, 3, 2, 5);
		this.UpperJawDetail2.setRotationPoint(0F, 0.3641491F - 5.110041F, -23.90201F + 11.14083F);
		this.LowerJaw = new ModelRenderer(this, 12, 8);
		this.LowerJaw.setTextureSize(128, 128);
		this.LowerJaw.addBox(-1.5F, 0F, -5F, 3, 1, 5);
		this.LowerJaw.setRotationPoint(0F, 4.670414F - 5.110041F, -21.75461F + 11.14083F);
		this.LeftEar1 = new ModelRenderer(this, 20, 15);
		this.LeftEar1.setTextureSize(128, 128);
		this.LeftEar1.addBox(0F, -1F, -1F, 4, 2, 1);
		this.LeftEar1.setRotationPoint(2.5F, -0.2142792F - 5.110041F, -19.63099F + 11.14083F);
		this.LeftEar2 = new ModelRenderer(this, 0, 26);
		this.LeftEar2.setTextureSize(128, 128);
		this.LeftEar2.addBox(0F, -0.5F, -1F, 1, 1, 1);
		this.LeftEar2.setRotationPoint(6.166575F, -0.6877365F - 5.110041F, -20.50967F + 11.14083F);
		this.RightEar1 = new ModelRenderer(this, 20, 15);
		this.RightEar1.setTextureSize(128, 128);
		this.RightEar1.addBox(-4F, -1F, -1F, 4, 2, 1);
		this.RightEar1.setRotationPoint(-2.5F, -0.2142792F - 5.110041F, -19.63099F + 11.14083F);
		this.RightEar2 = new ModelRenderer(this, 0, 26);
		this.RightEar2.setTextureSize(128, 128);
		this.RightEar2.addBox(-1F, -0.5F, -1F, 1, 1, 1);
		this.RightEar2.setRotationPoint(-6.166575F, -0.6877346F - 5.110041F, -20.50966F + 11.14083F);
		this.NeckWool = new ModelRendererColored(this, 59, 69);
		this.NeckWool.setTextureSize(128, 128);
		this.NeckWool.addBox(-3.5F, -2.5F, -5F, 7, 10, 10);
		this.NeckWool.setRotationPoint(0F, 2.858284F - 5.110041F, -13.18523F + 11.14083F);
		this.NeckWool2 = new ModelRendererColored(this, 30, 75);
		this.NeckWool2.setTextureSize(128, 128);
		this.NeckWool2.addBox(-4F, -2.5F, -2F, 8, 11, 4);
		this.NeckWool2.setRotationPoint(0F, 0.06732941F - 5.110041F, -17.128F + 11.14083F);

		this.HeadNode.addChild(this.Head);
		this.HeadNode.addChild(this.Neck);
		this.HeadNode.addChild(this.UpperJaw);
		this.HeadNode.addChild(this.Nose);
		this.HeadNode.addChild(this.UpperJawDetail1);
		this.HeadNode.addChild(this.UpperJawDetail2);
		this.HeadNode.addChild(this.LowerJaw);
		this.HeadNode.addChild(this.LeftEar1);
		this.HeadNode.addChild(this.LeftEar2);
		this.HeadNode.addChild(this.RightEar1);
		this.HeadNode.addChild(this.RightEar2);
		this.HeadNode.addChild(this.NeckWool);
		this.HeadNode.addChild(this.NeckWool2);

	}

	@Override
	public void setWoolColor(float r, float g, float b)
	{
		this.WoolBody1.setColor(r, g, b);
		this.WoolBody2.setColor(r, g, b);
		this.WoolHips.setColor(r, g, b);
	}

	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		this.Body.rotateAngleX = -0.03490658F;
		this.RightFrontLeg.rotateAngleX = -0.009773841F;
		this.Hips.rotateAngleX = 0.0174533F;
		this.RightBackLeg.rotateAngleX = -0.01396263F;
		this.Tail.rotateAngleX = 0.6251218F;
		this.WoolBody1.rotateAngleX = -0.01679522F;
		this.WoolBody2.rotateAngleX = -0.03490658F;
		this.WoolHips.rotateAngleX = -0.03490658F;
		this.LeftBackLeg.rotateAngleX = -0.01396263F;
		this.Neck.rotateAngleX = -0.6684765F;
		this.Head.rotateAngleX = 0.2822346F;
		this.UpperJaw.rotateAngleX = 0.3557643F;
		this.Nose.rotateAngleX = 0.4814279F;
		this.UpperJawDetail1.rotateAngleX = 0.8936448F;
		this.UpperJawDetail2.rotateAngleX = 0.6360343F;
		this.LowerJaw.rotateAngleX = 0.3450664F;
		this.LeftEar1.rotateAngleX = 0.2500451F;
		this.LeftEar1.rotateAngleY = 0.2031327F;
		this.LeftEar1.rotateAngleZ = -0.1289499F;
		this.LeftEar2.rotateAngleX = 0.2696677F;
		this.LeftEar2.rotateAngleY = 0.3971406F;
		this.LeftEar2.rotateAngleZ = -0.07895216F;
		this.RightEar1.rotateAngleX = 0.2500452F;
		this.RightEar1.rotateAngleY = -0.2031327F;
		this.RightEar1.rotateAngleZ = 0.1289499F;
		this.RightEar2.rotateAngleX = 0.2696678F;
		this.RightEar2.rotateAngleY = -0.3971407F;
		this.RightEar2.rotateAngleZ = 0.07895218F;
		this.NeckWool.rotateAngleX = -0.5530767F;
		this.NeckWool2.rotateAngleX = -0.19262F;
		this.LeftFrontLeg.rotateAngleX = -0.009773842F;

		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

		boolean isSleeping = false;
		EntityAnimaniaSheep ech = (EntityAnimaniaSheep) entityIn;
		if (ech.getSleeping())
		{
			isSleeping = true;
		}
		float sleepTimer = ech.getSleepTimer();

		if (isSleeping)
		{

			this.LeftFrontLeg.rotateAngleX = sleepTimer * -1.8F;
			this.LeftFrontLeg.render(scale * .95F);
			this.RightFrontLeg.rotateAngleX = sleepTimer * -1.8F;
			this.RightFrontLeg.render(scale * .97F);
			this.LeftBackLeg.rotateAngleX = sleepTimer * 1.7F;
			this.LeftBackLeg.render(scale * .97F);
			this.RightBackLeg.rotateAngleX = sleepTimer * 1.75F;
			this.RightBackLeg.render(scale * .95F);
			this.HeadNode.rotateAngleY = sleepTimer * -2.8F;

			if (sleepTimer > -.28)
			{
				this.Body.rotateAngleX = -(sleepTimer / 3);
			}
			else
			{
				this.Body.rotateAngleX = +(sleepTimer / 3);
			}

		}
		else
		{

			this.LeftBackLeg.rotateAngleZ = 0;
			this.LeftBackLeg.render(scale);
			this.RightBackLeg.rotateAngleZ = 0;
			this.RightBackLeg.render(scale);
			this.LeftFrontLeg.rotateAngleZ = 0;
			this.LeftFrontLeg.render(scale);
			this.RightFrontLeg.rotateAngleZ = 0;
			this.RightFrontLeg.render(scale);
			this.HeadNode.rotateAngleY = 0F;
			this.Body.rotateAngleX = 0F;

		}

		this.Body.render(scale);
		this.HeadNode.render(scale);
		this.Hips.render(scale);
		this.Tail.render(scale);
		this.WoolHips.render(scale);
		this.WoolBody1.render(scale);
		this.WoolBody2.render(scale);

	}

	@Override
	public void setLivingAnimations(LivingEntity entity, float p_78086_2_, float p_78086_3_, float partialTickTime)
	{
		super.setLivingAnimations(entity, p_78086_2_, p_78086_3_, partialTickTime);

		if (entity instanceof EntityRamDorper entitiyRamDorper)
		{
			this.HeadNode.rotationPointY = 4F + entitiyRamDorper.getHeadAnchorPointY(partialTickTime) * 4.0F;
			this.headRotationAngleX = entitiyRamDorper.getHeadAngleX(partialTickTime);
		}
		else if (entity instanceof EntityEweDorper entityEveDorper)
		{
			this.HeadNode.rotationPointY = 4F + entityEveDorper.getHeadAnchorPointY(partialTickTime) * 4.0F;
			this.headRotationAngleX = entityEveDorper.getHeadAngleX(partialTickTime);
		}
		else if (entity instanceof EntityLambDorper entityLambDorper)
		{
			this.HeadNode.rotationPointY = 4F + entityLambDorper.getHeadAnchorPointY(partialTickTime) * 4.0F;
			this.headRotationAngleX = entityLambDorper.getHeadAngleX(partialTickTime);
		}
	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
	{
		float f6 = 180F / (float) Math.PI;

		if ((entity instanceof EntityRamDorper eb) && (eb.getFighting() && eb.getRivalUniqueId() != null))
		{
			this.HeadNode.rotateAngleX = 0.687F;
		}
		else
		{
			this.HeadNode.rotateAngleX = par5 / (180F / (float) Math.PI);
			this.HeadNode.rotateAngleY = par4 / (180F / (float) Math.PI);
			this.HeadNode.rotateAngleX = this.headRotationAngleX;
		}

		boolean isSleeping = false;
		EntityAnimaniaSheep ech = (EntityAnimaniaSheep) entity;
		if (ech.getSleeping())
		{
			isSleeping = true;
		}
		if (!isSleeping)
		{
			this.Tail.rotateAngleY = MathHelper.sin(par3 * 3.141593F * 0.05F) * MathHelper.sin(par3 * 3.141593F * .03F * 0.05F) * 0.15F * 3.141593F;
		}
		else
		{
			this.Tail.rotateAngleY = MathHelper.sin(1 * 3.141593F * 0.05F) * MathHelper.sin(1 * 3.141593F * .03F * 0.05F) * 0.15F * 3.141593F;
		}

		this.LeftBackLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.2F * par2; // Left
																						// Back
		this.RightBackLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;// Right
																										// Back
		this.LeftFrontLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;// Left
																										// Front
		this.RightFrontLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;// Right
																						// Front

	}

}
