package com.animania.addons.extra.client.model.peafowl;

import com.animania.addons.extra.common.entity.peafowl.EntityAnimaniaPeacock;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.entity.LivingEntity;

public class ModelPeacock extends ModelBase
{
	private float lastLimb;
	ModelRenderer RootNode;
	ModelRenderer Neck;
	ModelRenderer Neck2;
	ModelRenderer Neck3;
	ModelRenderer Head;
	ModelRenderer Crest;
	ModelRenderer BeakBottom;
	ModelRenderer BeakTop;
	ModelRenderer Body1;
	ModelRenderer leg1Top;
	ModelRenderer leg1top2;
	ModelRenderer leg1bottom;
	ModelRenderer Foot1;
	ModelRenderer Foot1b;
	ModelRenderer leg2Top;
	ModelRenderer leg2top2;
	ModelRenderer leg2bottom;
	ModelRenderer Foot2;
	ModelRenderer Foot2b;
	ModelRenderer Tail1;
	ModelRenderer Tail2;
	ModelRenderer FanNodeA;
	ModelRenderer FeatherA;
	ModelRenderer FeatherA1;
	ModelRenderer FeatherA1b;
	ModelRenderer FeatherA2;
	ModelRenderer FeatherA2b;
	ModelRenderer FeatherA3;
	ModelRenderer FeatherA3b;
	ModelRenderer FeatherA4;
	ModelRenderer FeatherA4b;
	ModelRenderer FeatherA5;
	ModelRenderer FeatherA5b;
	ModelRenderer FeatherA6;
	ModelRenderer FeatherA6b;
	ModelRenderer FeatherA7;
	ModelRenderer FeatherA7b;
	ModelRenderer FeatherA8;
	ModelRenderer FeatherA8b;
	ModelRenderer FanNodeB;
	ModelRenderer FeatherB;
	ModelRenderer FeatherB1;
	ModelRenderer FeatherB1b;
	ModelRenderer FeatherB2;
	ModelRenderer FeatherB2b;
	ModelRenderer FeatherB3;
	ModelRenderer FeatherB3b;
	ModelRenderer FeatherB4;
	ModelRenderer FeatherB4b;
	ModelRenderer FeatherB5;
	ModelRenderer FeatherB5b;
	ModelRenderer FeatherB6;
	ModelRenderer FeatherB6b;
	ModelRenderer FeatherB7;
	ModelRenderer FeatherB7b;
	ModelRenderer FeatherB8;
	ModelRenderer FeatherB8b;
	ModelRenderer FanNodeC;
	ModelRenderer FeatherB11;
	ModelRenderer FeatherB1b1;
	ModelRenderer FeatherB21;
	ModelRenderer FeatherB2b1;
	ModelRenderer FeatherB31;
	ModelRenderer FeatherB3b1;
	ModelRenderer FeatherB41;
	ModelRenderer FeatherB4b1;
	ModelRenderer FanNodeD;
	ModelRenderer FeatherD;
	ModelRenderer FeatherD1;
	ModelRenderer FeatherD1b;
	ModelRenderer FeatherD2;
	ModelRenderer FeatherD2b;
	ModelRenderer Wing1;
	ModelRenderer Wing1a;
	ModelRenderer Wing1b;
	ModelRenderer Wing2;
	ModelRenderer Wing2a;
	ModelRenderer Wing2b;

	public ModelPeacock()
	{
		this(0.0f);
	}

	public ModelPeacock(float par1)
	{
		this.RootNode = new ModelRenderer(this, 16, 16);
		this.RootNode.setTextureSize(128, 64);
		this.RootNode.addBox(0F, 0F, 0F, 0, 0, 0);
		this.RootNode.setRotationPoint(0F, 10.5F, 0F);

		this.Neck = new ModelRenderer(this, 3, 19);
		this.Neck.setTextureSize(128, 64);
		this.Neck.addBox(-2.5F, -2.5F, -2F, 5, 5, 4);
		this.Neck.setRotationPoint(0F, 12.3998F, -1F);

		this.Neck2 = new ModelRenderer(this, 21, 21);
		this.Neck2.setTextureSize(128, 64);
		this.Neck2.addBox(-2F, -2F, -1.5F, 4, 4, 3);
		this.Neck2.setRotationPoint(0F, 10.90154F - 12.3998F, -3.01686F + 1F);

		this.Neck3 = new ModelRenderer(this, 3, 28);
		this.Neck3.setTextureSize(128, 64);
		this.Neck3.addBox(-1.51F, -1.5F, -6F, 3, 3, 6);
		this.Neck3.setRotationPoint(0F, 10.5F - 12.3998F, -3.75F + 1F);

		this.Head = new ModelRenderer(this, 0, 0);
		this.Head.setTextureSize(128, 64);
		this.Head.addBox(-1.5F, -2.5F, -2F, 3, 3, 4);
		this.Head.setRotationPoint(0F, 4.5F - 12.3998F, -5F + 1F);

		this.Crest = new ModelRenderer(this, 33, 43);
		this.Crest.setTextureSize(128, 64);
		this.Crest.addBox(0F, -2.5F, -3.5F, 0, 5, 7);
		this.Crest.setRotationPoint(0F, -0.04393768F - 12.3998F, -4.59361F + 1F);

		this.BeakBottom = new ModelRenderer(this, 19, 0);
		this.BeakBottom.setTextureSize(128, 64);
		this.BeakBottom.addBox(-0.5F, -0.5F, -1F, 1, 1, 2);
		this.BeakBottom.setRotationPoint(0F, 3.862326F - 12.3998F, -8.057199F + 1F);

		this.BeakTop = new ModelRenderer(this, 19, 0);
		this.BeakTop.setTextureSize(128, 64);
		this.BeakTop.addBox(-0.5F, -0.5F, -1F, 1, 1, 2);
		this.BeakTop.setRotationPoint(0F, 3.560648F - 12.3998F, -7.962383F + 1F);

		this.Body1 = new ModelRenderer(this, 0, 7);
		this.Body1.setTextureSize(128, 64);
		this.Body1.addBox(-3F, -3F, -3F, 6, 6, 6);
		this.Body1.setRotationPoint(0F, 13.5F, 2F);

		this.leg1Top = new ModelRenderer(this, 39, 0);
		this.leg1Top.setTextureSize(128, 64);
		this.leg1Top.addBox(-1F, -1F, -1F, 2, 2, 2);
		this.leg1Top.setRotationPoint(-1.5F, 16.79997F, 1.75F);

		this.leg1top2 = new ModelRenderer(this, 35, 0);
		this.leg1top2.setTextureSize(128, 64);
		this.leg1top2.addBox(-0.5F, -2.5F, -0.5F, 1, 3, 1);
		this.leg1top2.setRotationPoint(-1.5F + 1.5F, 19.75261F - 16.79997F, 2.602251F - 1.75F);

		this.leg1bottom = new ModelRenderer(this, 35, 0);
		this.leg1bottom.setTextureSize(128, 64);
		this.leg1bottom.addBox(-0.5F, -3F, -0.5F, 1, 4, 1);
		this.leg1bottom.setRotationPoint(-1.5F + 1.5F, 22.95F - 16.79997F, 2.2F - 1.75F);

		this.Foot1 = new ModelRenderer(this, 25, 0);
		this.Foot1.setTextureSize(128, 64);
		this.Foot1.addBox(-0.5F, -0.5F, -1.5F, 1, 1, 3);
		this.Foot1.setRotationPoint(-2.25F + 1.5F, 23.95172F - 16.79997F, 1.200874F - 1.75F);

		this.Foot1b = new ModelRenderer(this, 25, 0);
		this.Foot1b.setTextureSize(128, 64);
		this.Foot1b.addBox(-0.5F, -0.5F, -1.5F, 1, 1, 3);
		this.Foot1b.setRotationPoint(-0.75F + 1.5F, 23.95172F - 16.79997F, 1.200874F - 1.75F);

		this.leg2Top = new ModelRenderer(this, 39, 0);
		this.leg2Top.setTextureSize(128, 64);
		this.leg2Top.addBox(-1F, -1F, -1F, 2, 2, 2);
		this.leg2Top.setRotationPoint(1.5F, 16.79997F, 1.75F);

		this.leg2top2 = new ModelRenderer(this, 35, 0);
		this.leg2top2.setTextureSize(128, 64);
		this.leg2top2.addBox(-0.5F, -3F, -0.5F, 1, 3, 1);
		this.leg2top2.setRotationPoint(1.5F - 1.5F, 20.23324F - 16.79997F, 2.74007F - 1.75F);

		this.leg2bottom = new ModelRenderer(this, 35, 0);
		this.leg2bottom.setTextureSize(128, 64);
		this.leg2bottom.addBox(-0.5F, -3F, -0.5F, 1, 4, 1);
		this.leg2bottom.setRotationPoint(1.5F - 1.5F, 22.95F - 16.79997F, 2.2F - 1.75F);

		this.Foot2 = new ModelRenderer(this, 25, 0);
		this.Foot2.setTextureSize(128, 64);
		this.Foot2.addBox(-0.5F, -0.5F, -1.5F, 1, 1, 3);
		this.Foot2.setRotationPoint(0.75F - 1.5F, 23.95172F - 16.79997F, 1.200874F - 1.75F);

		this.Foot2b = new ModelRenderer(this, 25, 0);
		this.Foot2b.setTextureSize(128, 64);
		this.Foot2b.addBox(-0.5F, -0.5F, -1.5F, 1, 1, 3);
		this.Foot2b.setRotationPoint(2.25F - 1.5F, 23.95172F - 16.79997F, 1.200874F - 1.75F);

		this.Tail1 = new ModelRenderer(this, 36, 20);
		this.Tail1.setTextureSize(128, 64);
		this.Tail1.addBox(-2.5F, -2F, -2F, 5, 4, 4);
		this.Tail1.setRotationPoint(0F, 14.10777F, 5.446827F);

		this.Tail2 = new ModelRenderer(this, 25, 14);
		this.Tail2.setTextureSize(128, 64);
		this.Tail2.addBox(-2F, -1F, -1.5F, 4, 2, 3);
		this.Tail2.setRotationPoint(0F, 14.25801F - 14.10777F, 8.135215F - 5.446827F);

		this.FanNodeA = new ModelRenderer(this, 6, 13);
		this.FanNodeA.setTextureSize(128, 64);
		this.FanNodeA.addBox(0F, 0F, 0F, 0, 0, 0);
		this.FanNodeA.setRotationPoint(0F, 9 + 10.89287F, 10 + 4.49056F);

		this.FeatherA = new ModelRenderer(this, 63, 0);
		this.FeatherA.setTextureSize(128, 64);
		this.FeatherA.addBox(-6.5F, -42.5F, 0F, 13, 64, 0);
		this.FeatherA.setRotationPoint(0F, 10.89287F - 10.89287F, 4.49056F - 4.49056F);

		this.FeatherA1 = new ModelRenderer(this, 63, 0);
		this.FeatherA1.setTextureSize(128, 64);
		this.FeatherA1.addBox(-6.5F, -42.5F, 0F, 13, 64, 0);
		this.FeatherA1.setRotationPoint(4F, 10.89287F - 10.89287F, 4.49056F - 4.49056F);

		this.FeatherA1b = new ModelRenderer(this, 63, 0);
		this.FeatherA1b.setTextureSize(128, 64);
		this.FeatherA1b.addBox(-6.5F, -42.5F, 0F, 13, 64, 0);
		this.FeatherA1b.setRotationPoint(-4F, 10.89287F - 10.89287F, 4.49056F - 4.49056F);

		this.FeatherA2 = new ModelRenderer(this, 63, 0);
		this.FeatherA2.setTextureSize(128, 64);
		this.FeatherA2.addBox(-6.5F, -42.5F, 0F, 13, 64, 0);
		this.FeatherA2.setRotationPoint(8F, 10.89287F - 8.89287F, 4.49056F - 4.49056F);

		this.FeatherA2b = new ModelRenderer(this, 63, 0);
		this.FeatherA2b.setTextureSize(128, 64);
		this.FeatherA2b.addBox(-6.5F, -42.5F, 0F, 13, 64, 0);
		this.FeatherA2b.setRotationPoint(-8F, 10.89287F - 8.89287F, 4.49056F - 4.49056F);

		this.FeatherA3 = new ModelRenderer(this, 63, 0);
		this.FeatherA3.setTextureSize(128, 64);
		this.FeatherA3.addBox(-6.5F, -42.5F, 0F, 13, 64, 0);
		this.FeatherA3.setRotationPoint(12F, 10.89287F - 6.89287F, 4.49056F - 4.49056F);

		this.FeatherA3b = new ModelRenderer(this, 63, 0);
		this.FeatherA3b.setTextureSize(128, 64);
		this.FeatherA3b.addBox(-6.5F, -42.5F, 0F, 13, 64, 0);
		this.FeatherA3b.setRotationPoint(-12F, 10.89287F - 6.89287F, 4.49056F - 4.49056F);

		this.FeatherA4 = new ModelRenderer(this, 63, 0);
		this.FeatherA4.setTextureSize(128, 64);
		this.FeatherA4.addBox(-6.5F, -42.5F, 0F, 13, 64, 0);
		this.FeatherA4.setRotationPoint(16F, 10.89287F - 4.89287F, 4.49056F - 4.49056F);

		this.FeatherA4b = new ModelRenderer(this, 63, 0);
		this.FeatherA4b.setTextureSize(128, 64);
		this.FeatherA4b.addBox(-6.5F, -42.5F, 0F, 13, 64, 0);
		this.FeatherA4b.setRotationPoint(-16F, 10.89287F - 4.89287F, 4.49056F - 4.49056F);

		this.FeatherA5 = new ModelRenderer(this, 63, 0);
		this.FeatherA5.setTextureSize(128, 64);
		this.FeatherA5.addBox(-6.5F, -42.5F, 0F, 13, 64, 0);
		this.FeatherA5.setRotationPoint(18F, 10.89287F - 2.89287F, 4.49056F - 4.49056F);

		this.FeatherA5b = new ModelRenderer(this, 63, 0);
		this.FeatherA5b.setTextureSize(128, 64);
		this.FeatherA5b.addBox(-6.5F, -42.5F, 0F, 13, 64, 0);
		this.FeatherA5b.setRotationPoint(-18F, 10.89287F - 2.89287F, 4.49056F - 4.49056F);

		this.FeatherA6 = new ModelRenderer(this, 63, 0);
		this.FeatherA6.setTextureSize(128, 64);
		this.FeatherA6.addBox(-6.5F, -42.5F, 0F, 13, 64, 0);
		// FeatherA6.setRotationPoint( -2.473759E-06F, 10.89288F - 10.89287F,
		// 4.49056F - 4.49056F );
		this.FeatherA6.setRotationPoint(20F, 10.89288F - 0.39287F, 4.49056F - 4.49056F);

		this.FeatherA6b = new ModelRenderer(this, 63, 0);
		this.FeatherA6b.setTextureSize(128, 64);
		this.FeatherA6b.addBox(-6.5F, -42.5F, 0F, 13, 64, 0);
		// FeatherA6b.setRotationPoint( -2.473759E-06F, 10.89288F - 10.89287F,
		// 4.49056F - 4.49056F );
		this.FeatherA6b.setRotationPoint(-20F, 10.89288F - 0.39287F, 4.49056F - 4.49056F);

		this.FeatherA7 = new ModelRenderer(this, 63, 0);
		this.FeatherA7.setTextureSize(128, 64);
		this.FeatherA7.addBox(-6.5F, -42.5F, 0F, 13, 64, 0);
		// FeatherA7.setRotationPoint( -2.473759E-06F, 10.89288F - 10.89287F,
		// 4.49056F - 4.49056F );
		this.FeatherA7.setRotationPoint(22F, 10.89288F + 3.89287F, 4.49056F - 4.49056F);

		this.FeatherA7b = new ModelRenderer(this, 63, 0);
		this.FeatherA7b.setTextureSize(128, 64);
		this.FeatherA7b.addBox(-6.5F, -42.5F, 0F, 13, 64, 0);
		// FeatherA7b.setRotationPoint( -2.473759E-06F, 10.89288F - 10.89287F,
		// 4.49056F - 4.49056F );
		this.FeatherA7b.setRotationPoint(-22F, 10.89288F + 3.89287F, 4.49056F - 4.49056F);

		this.FeatherA8 = new ModelRenderer(this, 63, 0);
		this.FeatherA8.setTextureSize(128, 64);
		this.FeatherA8.addBox(-6.5F, -42.5F, 0F, 13, 64, 0);
		// FeatherA8.setRotationPoint( -2.473759E-06F, 10.89288F - 10.89287F,
		// 4.49056F - 4.49056F );
		this.FeatherA8.setRotationPoint(22F, 10.89288F + 6.89287F, 4.49056F - 4.49056F);

		this.FeatherA8b = new ModelRenderer(this, 63, 0);
		this.FeatherA8b.setTextureSize(128, 64);
		this.FeatherA8b.addBox(-6.5F, -42.5F, 0F, 13, 64, 0);
		// FeatherA8b.setRotationPoint( -2.473759E-06F, 10.89288F - 10.89287F,
		// 4.49056F - 4.49056F );
		this.FeatherA8b.setRotationPoint(-22F, 10.89288F + 6.89287F, 4.49056F - 4.49056F);

		this.FanNodeB = new ModelRenderer(this, 6, 13);
		this.FanNodeB.setTextureSize(128, 64);
		this.FanNodeB.addBox(0F, 0F, 0F, 0, 0, 0);
		this.FanNodeB.setRotationPoint(0F, 15 + 8.74961F, 6 + 4.853049F);

		this.FeatherB = new ModelRenderer(this, 63, 0);
		this.FeatherB.setTextureSize(128, 64);
		this.FeatherB.addBox(-6.5F, -35F, 0F, 13, 52, 0);
		this.FeatherB.setRotationPoint(0F, 11.17086F - 8.74961F, 3.293798F - 3.853049F);

		this.FeatherB1 = new ModelRenderer(this, 63, 0);
		this.FeatherB1.setTextureSize(128, 64);
		this.FeatherB1.addBox(-6.5F, -35F, 0F, 13, 52, 0);
		this.FeatherB1.setRotationPoint(4F, 11.16443F - 8.74961F, 3.206001F - 3.853049F);

		this.FeatherB1b = new ModelRenderer(this, 63, 0);
		this.FeatherB1b.setTextureSize(128, 64);
		this.FeatherB1b.addBox(-6.5F, -35F, 0F, 13, 52, 0);
		this.FeatherB1b.setRotationPoint(-4F, 11.16443F - 8.74961F, 3.206001F - 3.853049F);

		this.FeatherB2 = new ModelRenderer(this, 63, 0);
		this.FeatherB2.setTextureSize(128, 64);
		this.FeatherB2.addBox(-6.5F, -35F, 0F, 13, 52, 0);
		this.FeatherB2.setRotationPoint(8F, 11.16443F - 6.74961F, 3.206001F - 3.853049F);

		this.FeatherB2b = new ModelRenderer(this, 63, 0);
		this.FeatherB2b.setTextureSize(128, 64);
		this.FeatherB2b.addBox(-6.5F, -35F, 0F, 13, 52, 0);
		this.FeatherB2b.setRotationPoint(-8F, 11.16443F - 6.74961F, 3.206001F - 3.853049F);

		this.FeatherB3 = new ModelRenderer(this, 63, 0);
		this.FeatherB3.setTextureSize(128, 64);
		this.FeatherB3.addBox(-6.5F, -35F, 0F, 13, 52, 0);
		this.FeatherB3.setRotationPoint(12F, 11.16443F - 4.74961F, 3.206001F - 3.853049F);

		this.FeatherB3b = new ModelRenderer(this, 63, 0);
		this.FeatherB3b.setTextureSize(128, 64);
		this.FeatherB3b.addBox(-6.5F, -35F, 0F, 13, 52, 0);
		this.FeatherB3b.setRotationPoint(-12F, 11.16443F - 4.74961F, 3.206001F - 3.853049F);

		this.FeatherB4 = new ModelRenderer(this, 63, 0);
		this.FeatherB4.setTextureSize(128, 64);
		this.FeatherB4.addBox(-6.5F, -35F, 0F, 13, 52, 0);
		this.FeatherB4.setRotationPoint(16F, 11.16443F - 2.74961F, 3.206001F - 3.853049F);

		this.FeatherB4b = new ModelRenderer(this, 63, 0);
		this.FeatherB4b.setTextureSize(128, 64);
		this.FeatherB4b.addBox(-6.5F, -35F, 0F, 13, 52, 0);
		this.FeatherB4b.setRotationPoint(-16F, 11.16443F - 2.74961F, 3.206001F - 3.853049F);

		this.FeatherB5 = new ModelRenderer(this, 63, 0);
		this.FeatherB5.setTextureSize(128, 64);
		this.FeatherB5.addBox(-6.5F, -35F, 0F, 13, 52, 0);
		this.FeatherB5.setRotationPoint(18F, 11.16443F + 1.24961F, 3.206001F - 3.853049F);

		this.FeatherB5b = new ModelRenderer(this, 63, 0);
		this.FeatherB5b.setTextureSize(128, 64);
		this.FeatherB5b.addBox(-6.5F, -35F, 0F, 13, 52, 0);
		this.FeatherB5b.setRotationPoint(-18F, 11.16443F + 1.24961F, 3.206001F - 3.853049F);

		this.FeatherB6 = new ModelRenderer(this, 63, 0);
		this.FeatherB6.setTextureSize(128, 64);
		this.FeatherB6.addBox(-6.5F, -35F, 0F, 13, 52, 0);
		this.FeatherB6.setRotationPoint(20F, 11.16443F + 4.24961F, 3.206F - 3.853049F);

		this.FeatherB6b = new ModelRenderer(this, 63, 0);
		this.FeatherB6b.setTextureSize(128, 64);
		this.FeatherB6b.addBox(-6.5F, -35F, 0F, 13, 52, 0);
		this.FeatherB6b.setRotationPoint(-20F, 11.16443F + 4.24961F, 3.206F - 3.853049F);

		this.FeatherB7 = new ModelRenderer(this, 63, 0);
		this.FeatherB7.setTextureSize(128, 64);
		this.FeatherB7.addBox(-6.5F, -35F, 0F, 13, 52, 0);
		this.FeatherB7.setRotationPoint(22F, 11.16443F + 7.24961F, 3.206F - 3.853049F);

		this.FeatherB7b = new ModelRenderer(this, 63, 0);
		this.FeatherB7b.setTextureSize(128, 64);
		this.FeatherB7b.addBox(-6.5F, -35F, 0F, 13, 52, 0);
		this.FeatherB7b.setRotationPoint(-22F, 11.16443F + 7.24961F, 3.206F - 3.853049F);

		this.FeatherB8 = new ModelRenderer(this, 63, 0);
		this.FeatherB8.setTextureSize(128, 64);
		this.FeatherB8.addBox(-6.5F, -35F, 0F, 13, 52, 0);
		this.FeatherB8.setRotationPoint(22F, 11.16443F + 10.24961F, 3.206F - 3.853049F);

		this.FeatherB8b = new ModelRenderer(this, 63, 0);
		this.FeatherB8b.setTextureSize(128, 64);
		this.FeatherB8b.addBox(-6.5F, -35F, 0F, 13, 52, 0);
		this.FeatherB8b.setRotationPoint(-22F, 11.16443F + 10.24961F, 3.206F - 3.853049F);

		// FANNODEC
		this.FanNodeC = new ModelRenderer(this, 6, 13);
		this.FanNodeC.setTextureSize(128, 64);
		this.FanNodeC.addBox(0F, 3F, 0F, 0, 0, 0);
		this.FanNodeC.setRotationPoint(0.13F, 16 + 10.575961F, 2 + 1.868241F);

		this.FeatherB11 = new ModelRenderer(this, 63, 0);
		this.FeatherB11.setTextureSize(128, 64);
		this.FeatherB11.addBox(-6.5F, -27F, 0F, 13, 40, 0);
		this.FeatherB11.setRotationPoint(0.003027502F, 11.51945F - 8.575961F, 2.069598F - 2.868241F);

		this.FeatherB1b1 = new ModelRenderer(this, 63, 0);
		this.FeatherB1b1.setTextureSize(128, 64);
		this.FeatherB1b1.addBox(-6.5F, -27F, 0F, 13, 40, 0);
		this.FeatherB1b1.setRotationPoint(0.003027502F, 11.51945F - 8.575961F, 2.069598F - 2.868241F);

		this.FeatherB21 = new ModelRenderer(this, 63, 0);
		this.FeatherB21.setTextureSize(128, 64);
		this.FeatherB21.addBox(-6.5F, -27F, 0F, 13, 40, 0);
		this.FeatherB21.setRotationPoint(4.003027502F, 11.51945F - 6.575961F, 2.069598F - 2.868241F);

		this.FeatherB2b1 = new ModelRenderer(this, 63, 0);
		this.FeatherB2b1.setTextureSize(128, 64);
		this.FeatherB2b1.addBox(-6.5F, -27F, 0F, 13, 40, 0);
		this.FeatherB2b1.setRotationPoint(-4.003027502F, 11.51945F - 6.575961F, 2.069598F - 2.868241F);

		this.FeatherB31 = new ModelRenderer(this, 63, 0);
		this.FeatherB31.setTextureSize(128, 64);
		this.FeatherB31.addBox(-6.5F, -27F, 0F, 13, 40, 0);
		this.FeatherB31.setRotationPoint(8.003027502F, 11.51945F - 4.575961F, 2.069598F - 2.868241F);

		this.FeatherB3b1 = new ModelRenderer(this, 63, 0);
		this.FeatherB3b1.setTextureSize(128, 64);
		this.FeatherB3b1.addBox(-6.5F, -27F, 0F, 13, 40, 0);
		this.FeatherB3b1.setRotationPoint(-8.003027502F, 11.51945F - 4.575961F, 2.069598F - 2.868241F);

		this.FeatherB41 = new ModelRenderer(this, 63, 0);
		this.FeatherB41.setTextureSize(128, 64);
		this.FeatherB41.addBox(-6.5F, -27F, 0F, 13, 40, 0);
		this.FeatherB41.setRotationPoint(12.003025029F, 11.51945F - 2.575961F, 2.069597F - 2.868241F);

		this.FeatherB4b1 = new ModelRenderer(this, 63, 0);
		this.FeatherB4b1.setTextureSize(128, 64);
		this.FeatherB4b1.addBox(-6.5F, -27F, 0F, 13, 40, 0);
		this.FeatherB4b1.setRotationPoint(-12.003025029F, 11.51945F - 2.575961F, 2.069597F - 2.868241F);

		this.FanNodeD = new ModelRenderer(this, 31, 31);
		this.FanNodeD.setTextureSize(128, 64);
		this.FanNodeD.addBox(0F, -4.5F, 0F, 0, 9, 0);
		this.FanNodeD.setRotationPoint(0F, 8 + 16.473945F, 4 + .202189F);

		this.FeatherD = new ModelRenderer(this, 31, 31);
		this.FeatherD.setTextureSize(128, 64);
		this.FeatherD.addBox(-6.5F, -8.5F, 0F, 13, 13, 0);
		this.FeatherD.setRotationPoint(0F, 9.473946F - 9.473945F, 2.202188F - 2.202189F);

		this.FeatherD1 = new ModelRenderer(this, 31, 31);
		this.FeatherD1.setTextureSize(128, 64);
		this.FeatherD1.addBox(-6.5F, -8.5F, 0F, 13, 13, 0);
		this.FeatherD1.setRotationPoint(0F, 9.473945F - 9.473945F, 2.202189F - 2.202189F);

		this.FeatherD1b = new ModelRenderer(this, 31, 31);
		this.FeatherD1b.setTextureSize(128, 64);
		this.FeatherD1b.addBox(-6.5F, -8.5F, 0F, 13, 13, 0);
		this.FeatherD1b.setRotationPoint(0F, 9.473945F - 9.473945F, 2.202189F - 2.202189F);

		this.FeatherD2 = new ModelRenderer(this, 31, 31);
		this.FeatherD2.setTextureSize(128, 64);
		this.FeatherD2.addBox(-6.5F, -8.5F, 0F, 13, 13, 0);
		this.FeatherD2.setRotationPoint(0F, 9.473945F - 9.473945F, 2.202189F - 2.202189F);

		this.FeatherD2b = new ModelRenderer(this, 31, 31);
		this.FeatherD2b.setTextureSize(128, 64);
		this.FeatherD2b.addBox(-6.5F, -8.5F, 0F, 13, 13, 0);
		this.FeatherD2b.setRotationPoint(0F, 9.473945F - 9.473945F, 2.202189F - 2.202189F);

		this.Wing1 = new ModelRenderer(this, 92, 2);
		this.Wing1.setTextureSize(128, 64);
		this.Wing1.addBox(-0.5F, -1.5F, -1.5F, 1, 3, 7);
		this.Wing1.setRotationPoint(-3.5F, 12.6603F, 0.1172081F);

		this.Wing1a = new ModelRenderer(this, 96, 2);
		this.Wing1a.setTextureSize(128, 64);
		this.Wing1a.addBox(-0.5F, -1F, 1F, 1, 2, 2);
		this.Wing1a.setRotationPoint(-3.5F + 3.51F, 13.96746F - 13.6603F, 4.423172F - 0.1172081F);

		this.Wing1b = new ModelRenderer(this, 93, 2);
		this.Wing1b.setTextureSize(128, 64);
		this.Wing1b.addBox(-0.5F, -0.5F, -1F, 1, 1, 6);
		this.Wing1b.setRotationPoint(-3.5F + 3.52F, 14.57406F - 12.6603F, -0.4637537F - 0.1172081F);

		this.Wing2 = new ModelRenderer(this, 92, 2);
		this.Wing2.setTextureSize(128, 64);
		this.Wing2.addBox(-0.5F, -1.5F, -1.5F, 1, 3, 7);
		this.Wing2.setRotationPoint(3.5F, 12.6603F, 0.1172086F);

		this.Wing2a = new ModelRenderer(this, 96, 2);
		this.Wing2a.setTextureSize(128, 64);
		this.Wing2a.addBox(-0.5F, -1F, 1F, 1, 2, 2);
		this.Wing2a.setRotationPoint(3.5F - 3.51F, 13.96746F - 13.6603F, 4.423172F - 0.1172086F);

		this.Wing2b = new ModelRenderer(this, 93, 2);
		this.Wing2b.setTextureSize(128, 64);
		this.Wing2b.addBox(-0.5F, -0.5F, -1F, 1, 1, 6);
		this.Wing2b.setRotationPoint(3.5F - 3.52F, 14.57406F - 12.6603F, -0.4637532F - 0.1172086F);

		this.Neck.addChild(this.Neck2);
		this.Neck.addChild(this.Neck3);
		this.Neck.addChild(this.Head);
		this.Neck.addChild(this.BeakTop);
		this.Neck.addChild(this.BeakBottom);
		this.Neck.addChild(this.Crest);

		this.leg1Top.addChild(this.leg1top2);
		this.leg1Top.addChild(this.leg1bottom);
		this.leg1Top.addChild(this.Foot1);
		this.leg1Top.addChild(this.Foot1b);

		this.leg2Top.addChild(this.leg2top2);
		this.leg2Top.addChild(this.leg2bottom);
		this.leg2Top.addChild(this.Foot2);
		this.leg2Top.addChild(this.Foot2b);

		this.Wing1.addChild(this.Wing1a);
		this.Wing1.addChild(this.Wing1b);

		this.Wing2.addChild(this.Wing2a);
		this.Wing2.addChild(this.Wing2b);

		this.FanNodeA.addChild(this.FeatherA);
		this.FanNodeA.addChild(this.FeatherA1);
		this.FanNodeA.addChild(this.FeatherA1b);
		this.FanNodeA.addChild(this.FeatherA2);
		this.FanNodeA.addChild(this.FeatherA2b);
		this.FanNodeA.addChild(this.FeatherA3);
		this.FanNodeA.addChild(this.FeatherA3b);
		this.FanNodeA.addChild(this.FeatherA4);
		this.FanNodeA.addChild(this.FeatherA4b);
		this.FanNodeA.addChild(this.FeatherA5);
		this.FanNodeA.addChild(this.FeatherA5b);
		this.FanNodeA.addChild(this.FeatherA6);
		this.FanNodeA.addChild(this.FeatherA6b);
		this.FanNodeA.addChild(this.FeatherA7);
		this.FanNodeA.addChild(this.FeatherA7b);
		this.FanNodeA.addChild(this.FeatherA8);
		this.FanNodeA.addChild(this.FeatherA8b);

		this.FanNodeB.addChild(this.FeatherB);
		this.FanNodeB.addChild(this.FeatherB1);
		this.FanNodeB.addChild(this.FeatherB1b);
		this.FanNodeB.addChild(this.FeatherB2);
		this.FanNodeB.addChild(this.FeatherB2b);
		this.FanNodeB.addChild(this.FeatherB3);
		this.FanNodeB.addChild(this.FeatherB3b);
		this.FanNodeB.addChild(this.FeatherB4);
		this.FanNodeB.addChild(this.FeatherB4b);
		this.FanNodeB.addChild(this.FeatherB5);
		this.FanNodeB.addChild(this.FeatherB5b);
		this.FanNodeB.addChild(this.FeatherB6);
		this.FanNodeB.addChild(this.FeatherB6b);
		this.FanNodeB.addChild(this.FeatherB7);
		this.FanNodeB.addChild(this.FeatherB7b);
		this.FanNodeB.addChild(this.FeatherB8);
		this.FanNodeB.addChild(this.FeatherB8b);

		this.FanNodeC.addChild(this.FeatherB11);
		this.FanNodeC.addChild(this.FeatherB1b1);
		this.FanNodeC.addChild(this.FeatherB21);
		this.FanNodeC.addChild(this.FeatherB2b1);
		this.FanNodeC.addChild(this.FeatherB31);
		this.FanNodeC.addChild(this.FeatherB3b1);
		this.FanNodeC.addChild(this.FeatherB41);
		this.FanNodeC.addChild(this.FeatherB4b1);

		this.FanNodeD.addChild(this.FeatherD);
		this.FanNodeD.addChild(this.FeatherD1b);
		this.FanNodeD.addChild(this.FeatherD2);
		this.FanNodeD.addChild(this.FeatherD2b);

		this.Tail1.addChild(this.Tail2);

	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{

		this.Neck.render(scale);
		this.Body1.render(scale);
		this.leg1Top.render(scale);
		this.leg2Top.render(scale);
		this.Wing1.render(scale);
		this.Wing2.render(scale);
		this.Tail1.render(scale);

		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

		EntityAnimaniaPeacock ech = (EntityAnimaniaPeacock) entityIn;
		if (ech.getSleeping())
		{
			this.FanNodeA.rotateAngleX = -1.5F;
			this.FanNodeB.rotateAngleX = -1.5F;
			this.FanNodeC.rotateAngleX = -1.5F;
			this.FanNodeD.rotateAngleX = -1.5F;
		}

		this.FanNodeA.render(scale / 3);
		this.FanNodeB.render(scale / 3);
		this.FanNodeC.render(scale / 3);
		this.FanNodeD.render(scale / 3);

	}

	@Override
	public void setLivingAnimations(LivingEntity entity, float limbSwingAmount, float ageInTicks, float partialTickTime)
	{

		super.setLivingAnimations(entity, limbSwingAmount, ageInTicks, partialTickTime);

	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
	{

		this.Neck.rotateAngleX = -0.539251F;
		this.Neck2.rotateAngleX = -0.9490896F;
		this.Neck3.rotateAngleX = -1.376694F;
		this.Head.rotateAngleX = 0.07594994F;
		this.Crest.rotateAngleX = -0.1465151F;
		this.BeakBottom.rotateAngleX = 0.07594992F;
		this.BeakTop.rotateAngleX = 0.3341772F;
		this.Body1.rotateAngleX = -0.1745329F;
		this.leg1Top.rotateAngleX = 0.2792527F;
		this.leg1top2.rotateAngleX = 0.2792527F;
		this.leg1bottom.rotateAngleX = -0.1745331F;
		this.Foot1.rotateAngleX = -5.789743E-08F;
		this.leg2Top.rotateAngleX = 0.2792527F;
		this.leg2top2.rotateAngleX = 0.2792527F;
		this.leg2bottom.rotateAngleX = -0.1745331F;
		this.Foot2.rotateAngleX = -5.789743E-08F;
		this.Tail1.rotateAngleX = -0.4363323F;
		this.Tail2.rotateAngleX = -0.121238F;

		this.FanNodeA.rotateAngleX = -0.2617994F;
		this.FeatherA.rotateAngleX = -0.2617994F;
		this.FeatherA1.rotateAngleX = -0.2792528F;
		this.FeatherA1.rotateAngleY = -5.792813E-09F;
		this.FeatherA1.rotateAngleZ = 0.2268928F;
		this.FeatherA1b.rotateAngleX = -0.2792527F;
		this.FeatherA1b.rotateAngleY = 3.986776E-09F;
		this.FeatherA1b.rotateAngleZ = -0.2268928F;
		this.FeatherA2.rotateAngleX = -0.296706F;
		this.FeatherA2.rotateAngleY = -6.077558E-09F;
		this.FeatherA2.rotateAngleZ = 0.4537856F;
		this.FeatherA2b.rotateAngleX = -0.296706F;
		this.FeatherA2b.rotateAngleY = 1.589279E-09F;
		this.FeatherA2b.rotateAngleZ = -0.4537856F;
		this.FeatherA3.rotateAngleX = -0.3141594F;
		this.FeatherA3.rotateAngleY = -3.567901E-08F;
		this.FeatherA3.rotateAngleZ = 0.6806786F;
		this.FeatherA3b.rotateAngleX = -0.3141594F;
		this.FeatherA3b.rotateAngleY = 1.490632E-08F;
		this.FeatherA3b.rotateAngleZ = -0.6806785F;
		this.FeatherA4.rotateAngleX = -0.3316126F;
		this.FeatherA4.rotateAngleY = -1.525341E-08F;
		this.FeatherA4.rotateAngleZ = 0.9075713F;
		this.FeatherA4b.rotateAngleX = -0.3316126F;
		this.FeatherA4b.rotateAngleY = 1.525341E-08F;
		this.FeatherA4b.rotateAngleZ = -0.9075713F;
		this.FeatherA5.rotateAngleX = -0.3490659F;
		this.FeatherA5.rotateAngleY = -4.286015E-08F;
		this.FeatherA5.rotateAngleZ = 1.134464F;
		this.FeatherA5b.rotateAngleX = -0.349066F;
		this.FeatherA5b.rotateAngleY = 2.922648E-08F;
		this.FeatherA5b.rotateAngleZ = -1.134464F;
		this.FeatherA6.rotateAngleX = -0.3563616F;
		this.FeatherA6.rotateAngleY = -0.01687157F;
		this.FeatherA6.rotateAngleZ = 1.365743F;
		this.FeatherA6b.rotateAngleX = -0.3563616F;
		this.FeatherA6b.rotateAngleY = -0.01687157F;
		this.FeatherA6b.rotateAngleZ = -1.356971F;
		this.FeatherA7.rotateAngleX = -0.3635876F;
		this.FeatherA7.rotateAngleY = -0.03381116F;
		this.FeatherA7.rotateAngleZ = 1.597045F;
		this.FeatherA7b.rotateAngleX = -0.3635877F;
		this.FeatherA7b.rotateAngleY = -0.0338112F;
		this.FeatherA7b.rotateAngleZ = -1.579455F;
		this.FeatherA8.rotateAngleX = -0.3707415F;
		this.FeatherA8.rotateAngleY = -0.05100469F;
		this.FeatherA8.rotateAngleZ = 1.828417F;
		this.FeatherA8b.rotateAngleX = -0.3707415F;
		this.FeatherA8b.rotateAngleY = -0.05100469F;
		this.FeatherA8b.rotateAngleZ = -1.801868F;
		this.FanNodeB.rotateAngleX = -0.2617994F;
		this.FeatherB.rotateAngleX = -0.2617994F;
		this.FeatherB1.rotateAngleX = -0.2792527F;
		this.FeatherB1.rotateAngleY = -5.112545E-10F;
		this.FeatherB1.rotateAngleZ = 0.2268928F;
		this.FeatherB1b.rotateAngleX = -0.2792527F;
		this.FeatherB1b.rotateAngleY = -3.301778E-09F;
		this.FeatherB1b.rotateAngleZ = -0.2268928F;
		this.FeatherB2.rotateAngleX = -0.296706F;
		this.FeatherB2.rotateAngleY = -3.057893E-09F;
		this.FeatherB2.rotateAngleZ = 0.4537857F;
		this.FeatherB2b.rotateAngleX = -0.296706F;
		this.FeatherB2b.rotateAngleY = 1.056584E-08F;
		this.FeatherB2b.rotateAngleZ = -0.4537857F;
		this.FeatherB3.rotateAngleX = -0.3141594F;
		this.FeatherB3.rotateAngleY = -2.389782E-08F;
		this.FeatherB3.rotateAngleZ = 0.6806788F;
		this.FeatherB3b.rotateAngleX = -0.3141594F;
		this.FeatherB3b.rotateAngleY = 9.560558E-09F;
		this.FeatherB3b.rotateAngleZ = -0.6806785F;
		this.FeatherB4.rotateAngleX = -0.3316126F;
		this.FeatherB4.rotateAngleY = -1.282882E-09F;
		this.FeatherB4.rotateAngleZ = 0.9075713F;
		this.FeatherB4b.rotateAngleX = -0.3316126F;
		this.FeatherB4b.rotateAngleY = 1.282882E-09F;
		this.FeatherB4b.rotateAngleZ = -0.9075713F;
		this.FeatherB5.rotateAngleX = -0.3490659F;
		this.FeatherB5.rotateAngleY = -5.144992E-09F;
		this.FeatherB5.rotateAngleZ = 1.134465F;
		this.FeatherB5b.rotateAngleX = -0.3490659F;
		this.FeatherB5b.rotateAngleY = 2.968926E-08F;
		this.FeatherB5b.rotateAngleZ = -1.134464F;
		this.FeatherB6.rotateAngleX = -0.3563616F;
		this.FeatherB6.rotateAngleY = -0.01687154F;
		this.FeatherB6.rotateAngleZ = 1.365743F;
		this.FeatherB6b.rotateAngleX = -0.3563617F;
		this.FeatherB6b.rotateAngleY = -0.01687153F;
		this.FeatherB6b.rotateAngleZ = -1.356971F;
		this.FeatherB7.rotateAngleX = -0.3635876F;
		this.FeatherB7.rotateAngleY = -0.03381118F;
		this.FeatherB7.rotateAngleZ = 1.597045F;
		this.FeatherB7b.rotateAngleX = -0.3635877F;
		this.FeatherB7b.rotateAngleY = -0.03381117F;
		this.FeatherB7b.rotateAngleZ = -1.579455F;
		this.FeatherB8.rotateAngleX = -0.3707415F;
		this.FeatherB8.rotateAngleY = -0.05100475F;
		this.FeatherB8.rotateAngleZ = 1.828418F;
		this.FeatherB8b.rotateAngleX = -0.3707414F;
		this.FeatherB8b.rotateAngleY = -0.05100471F;
		this.FeatherB8b.rotateAngleZ = -1.801867F;
		this.FanNodeC.rotateAngleX = -0.2617994F;
		this.FanNodeC.rotateAngleY = 3.139456E-09F;
		this.FanNodeC.rotateAngleZ = -7.534808E-08F;
		this.FeatherB11.rotateAngleX = -0.2792527F;
		this.FeatherB11.rotateAngleY = -6.7773E-09F;
		this.FeatherB11.rotateAngleZ = -0.2268928F;
		this.FeatherB1b1.rotateAngleX = -0.2792527F;
		this.FeatherB1b1.rotateAngleY = 1.632293E-09F;
		this.FeatherB1b1.rotateAngleZ = 0.2268928F;
		this.FeatherB21.rotateAngleX = -0.3141594F;
		this.FeatherB21.rotateAngleY = -3.313954E-08F;
		this.FeatherB21.rotateAngleZ = 0.6806789F;
		this.FeatherB2b1.rotateAngleX = -0.3141593F;
		this.FeatherB2b1.rotateAngleY = 1.610589E-08F;
		this.FeatherB2b1.rotateAngleZ = -0.6806785F;
		this.FeatherB31.rotateAngleX = -0.3490659F;
		this.FeatherB31.rotateAngleY = 5.208576E-09F;
		this.FeatherB31.rotateAngleZ = 1.134464F;
		this.FeatherB3b1.rotateAngleX = -0.3490659F;
		this.FeatherB3b1.rotateAngleY = 1.29077E-08F;
		this.FeatherB3b1.rotateAngleZ = -1.134464F;
		this.FeatherB41.rotateAngleX = -0.3635876F;
		this.FeatherB41.rotateAngleY = -0.03381122F;
		this.FeatherB41.rotateAngleZ = 1.597045F;
		this.FeatherB4b1.rotateAngleX = -0.3635877F;
		this.FeatherB4b1.rotateAngleY = -0.0338112F;
		this.FeatherB4b1.rotateAngleZ = -1.579455F;
		this.FanNodeD.rotateAngleX = -0.2617995F;
		this.FeatherD.rotateAngleX = -0.2617994F;
		this.FeatherD1.rotateAngleX = -0.296706F;
		this.FeatherD1.rotateAngleY = 3.409307E-09F;
		this.FeatherD1.rotateAngleZ = 0.7853982F;
		this.FeatherD1b.rotateAngleX = -0.296706F;
		this.FeatherD1b.rotateAngleY = -5.56468E-10F;
		this.FeatherD1b.rotateAngleZ = -0.7853984F;
		this.FeatherD2.rotateAngleX = -0.3316126F;
		this.FeatherD2.rotateAngleZ = 1.570797F;
		this.FeatherD2b.rotateAngleX = -0.3316126F;
		this.FeatherD2b.rotateAngleZ = -1.570797F;

		this.Wing1.rotateAngleX = -0.2947292F;
		this.Wing2.rotateAngleX = -0.2947292F;

		this.Neck.rotateAngleX = Math.abs(headPitch / (180F / (float) Math.PI) * 1.4F * limbSwingAmount);
		this.Neck.rotateAngleX = Math.abs(netHeadYaw / (180F / (float) Math.PI));

		this.Body1.rotateAngleX = (float) Math.PI / 2F;
		this.leg1Top.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.leg2Top.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;

		this.Wing1.rotateAngleZ = ageInTicks;
		this.Wing2.rotateAngleZ = -ageInTicks;
	}

}
