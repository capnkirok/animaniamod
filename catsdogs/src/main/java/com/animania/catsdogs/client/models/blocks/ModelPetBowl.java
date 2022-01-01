package com.animania.catsdogs.client.models.blocks;

import com.animania.client.models.IColoredModel;
import com.animania.client.models.render.ModelRendererColored;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.Entity;

public class ModelPetBowl extends ModelPart.Cube implements IColoredModel
{
	ModelPart Root_Node;
	ModelPart Side1;
	ModelPart Side2;
	ModelPart Side3;
	ModelPart Side4;
	ModelPart Side5;
	ModelPart Side6;
	ModelPart Side7;
	ModelPart Side8;
	ModelPart Bottom_;
	ModelPart Bottom_2;
	ModelPart Bottom_3;
	ModelPart Bottom_4;
	ModelRendererColored Kibble;
	ModelRendererColored Kibble2;
	ModelRendererColored Kibble3;
	ModelRendererColored Kibble4;
	ModelRendererColored Kibble21;
	ModelRendererColored Kibble31;
	ModelRendererColored Kibble1;
	ModelRendererColored Kibble22;
	ModelRendererColored Kibble32;
	ModelRendererColored Kibble41;
	ModelRendererColored Kibble23;
	ModelRendererColored Kibble33;
	ModelRendererColored Kibble5;
	ModelRendererColored Kibble24;
	ModelRendererColored Kibble34;
	ModelRendererColored Kibble42;
	ModelRendererColored Kibble25;
	ModelRendererColored Kibble35;
	ModelRendererColored Kibble6;
	ModelRendererColored Kibble26;
	ModelRendererColored Kibble36;
	ModelRendererColored Kibble43;
	ModelRendererColored Kibble27;
	ModelRendererColored Kibble37;
	ModelRendererColored Kibble44;
	ModelRendererColored Kibble28;
	ModelRendererColored Kibble38;
	ModelRendererColored Kibble7;
	ModelRendererColored Kibble29;
	ModelRendererColored Kibble39;
	ModelRendererColored Kibble45;
	ModelRendererColored Kibble210;
	ModelRendererColored Kibble310;
	ModelRendererColored Kibble311;
	ModelRendererColored Kibble312;
	ModelRendererColored Kibble313;
	ModelRendererColored Kibble8;
	ModelRendererColored Kibble211;
	ModelRendererColored Kibble314;
	ModelRendererColored Kibble46;
	ModelRendererColored Kibble212;
	ModelRendererColored Kibble315;
	ModelRendererColored Kibble9;
	ModelRendererColored Kibble213;
	ModelRendererColored Kibble316;
	ModelRendererColored Kibble47;
	ModelRendererColored Kibble214;
	ModelRendererColored Kibble317;
	ModelRendererColored Kibble10;
	ModelRendererColored Kibble215;
	ModelRendererColored Kibble318;
	ModelRendererColored Kibble48;
	ModelRendererColored Kibble216;
	ModelRendererColored Kibble319;
	ModelRendererColored Kibble11;
	ModelRendererColored Kibble217;
	ModelRendererColored Kibble320;
	ModelRendererColored Kibble49;
	ModelRendererColored Kibble218;
	ModelRendererColored Kibble321;
	ModelRendererColored Kibble12;
	ModelRendererColored Kibble219;
	ModelRendererColored Kibble322;
	ModelRendererColored Kibble410;
	ModelRendererColored Kibble220;
	ModelRendererColored Kibble323;
	ModelRendererColored Kibble411;
	ModelRendererColored Kibble221;
	ModelRendererColored Kibble324;
	ModelRendererColored Kibble325;
	ModelRendererColored Kibble326;

	public ModelPetBowl()
	{
		this(0.0f);
	}

	public ModelPetBowl(float par1)
	{
		this.Root_Node = new ModelRenderer(this, 16, 16);
		this.Root_Node.setTextureSize(64, 32);
		this.Root_Node.addBox(0F, 0F, 0F, 0, 0, 0);
		this.Root_Node.setRotationPoint(0F, 24F, 0F);
		this.Side1 = new ModelRenderer(this, 12, 12);
		this.Side1.setTextureSize(64, 32);
		this.Side1.addBox(4F, -2F, -2F, 1, 4, 4);
		this.Side1.setRotationPoint(0F, 21.5F, 0F);
		this.Side2 = new ModelRenderer(this, 12, 12);
		this.Side2.setTextureSize(64, 32);
		this.Side2.addBox(-1F, -2F, 0F, 1, 4, 4);
		this.Side2.setRotationPoint(-1.999999F, 21.5F, 5F);
		this.Side3 = new ModelRenderer(this, 12, 12);
		this.Side3.setTextureSize(64, 32);
		this.Side3.addBox(-1F, -2F, 0F, 1, 4, 4);
		this.Side3.setRotationPoint(-4.828425F, 21.5F, 2.171573F);
		this.Side4 = new ModelRenderer(this, 12, 12);
		this.Side4.setTextureSize(64, 32);
		this.Side4.addBox(-1F, -2F, 0F, 1, 4, 4);
		this.Side4.setRotationPoint(-4.828426F, 21.5F, -1.828427F);
		this.Side5 = new ModelRenderer(this, 12, 12);
		this.Side5.setTextureSize(64, 32);
		this.Side5.addBox(-1F, -2F, 0F, 1, 4, 4);
		this.Side5.setRotationPoint(-1.999998F, 21.5F, -4.656853F);
		this.Side6 = new ModelRenderer(this, 12, 12);
		this.Side6.setTextureSize(64, 32);
		this.Side6.addBox(-1F, -2F, 0F, 1, 4, 4);
		this.Side6.setRotationPoint(2.000001F, 21.5F, -4.656853F);
		this.Side7 = new ModelRenderer(this, 12, 12);
		this.Side7.setTextureSize(64, 32);
		this.Side7.addBox(-1F, -2F, 0F, 1, 4, 4);
		this.Side7.setRotationPoint(4.828427F, 21.5F, -1.828425F);
		this.Side8 = new ModelRenderer(this, 12, 12);
		this.Side8.setTextureSize(64, 32);
		this.Side8.addBox(-1F, -2F, 0F, 1, 4, 4);
		this.Side8.setRotationPoint(4.828425F, 21.5F, 2.171574F);
		this.Bottom_ = new ModelRenderer(this, 12, 6);
		this.Bottom_.setTextureSize(64, 32);
		this.Bottom_.addBox(-4F, -0.5F, -2F, 8, 1, 4);
		this.Bottom_.setRotationPoint(0F, 23F, 0F);
		this.Bottom_2 = new ModelRenderer(this, 12, 6);
		this.Bottom_2.setTextureSize(64, 32);
		this.Bottom_2.addBox(-4F, -0.5F, -2F, 8, 1, 4);
		this.Bottom_2.setRotationPoint(0F, 23F, 0F);
		this.Bottom_3 = new ModelRenderer(this, 12, 6);
		this.Bottom_3.setTextureSize(64, 32);
		this.Bottom_3.addBox(-4F, -0.5F, -2F, 8, 1, 4);
		this.Bottom_3.setRotationPoint(0F, 23F, 0F);
		this.Bottom_4 = new ModelRenderer(this, 12, 6);
		this.Bottom_4.setTextureSize(64, 32);
		this.Bottom_4.addBox(-4F, -0.5F, -2F, 8, 1, 4);
		this.Bottom_4.setRotationPoint(0F, 23F, 0F);
		this.Kibble = new ModelRendererColored(this, 18, 2);
		this.Kibble.setTextureSize(64, 32);
		this.Kibble.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble.setRotationPoint(-0.5F, 21.5F, 2.5F);
		this.Kibble2 = new ModelRendererColored(this, 18, 2);
		this.Kibble2.setTextureSize(64, 32);
		this.Kibble2.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble2.setRotationPoint(0.5F, 21F, 1.5F);
		this.Kibble3 = new ModelRendererColored(this, 18, 2);
		this.Kibble3.setTextureSize(64, 32);
		this.Kibble3.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble3.setRotationPoint(-1.267418F, 20.5F, 1.9F);
		this.Kibble4 = new ModelRendererColored(this, 18, 2);
		this.Kibble4.setTextureSize(64, 32);
		this.Kibble4.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble4.setRotationPoint(-0.5F, 21.5F, 1.5F);
		this.Kibble21 = new ModelRendererColored(this, 18, 2);
		this.Kibble21.setTextureSize(64, 32);
		this.Kibble21.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble21.setRotationPoint(-1.912066F, 21F, 1.577905F);
		this.Kibble31 = new ModelRendererColored(this, 18, 2);
		this.Kibble31.setTextureSize(64, 32);
		this.Kibble31.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble31.setRotationPoint(-0.3285331F, 20.5F, 2.458921F);
		this.Kibble1 = new ModelRendererColored(this, 18, 2);
		this.Kibble1.setTextureSize(64, 32);
		this.Kibble1.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble1.setRotationPoint(1.982582F, 21.5F, 1.5F);
		this.Kibble22 = new ModelRendererColored(this, 18, 2);
		this.Kibble22.setTextureSize(64, 32);
		this.Kibble22.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble22.setRotationPoint(1.067586F, 21F, 0.4216765F);
		this.Kibble32 = new ModelRendererColored(this, 18, 2);
		this.Kibble32.setTextureSize(64, 32);
		this.Kibble32.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble32.setRotationPoint(1.321916F, 20.5F, 2.215857F);
		this.Kibble41 = new ModelRendererColored(this, 18, 2);
		this.Kibble41.setTextureSize(64, 32);
		this.Kibble41.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble41.setRotationPoint(0.9859222F, 21.5F, 1.418336F);
		this.Kibble23 = new ModelRendererColored(this, 18, 2);
		this.Kibble23.setTextureSize(64, 32);
		this.Kibble23.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble23.setRotationPoint(0.8106487F, 21F, 2.68691F);
		this.Kibble33 = new ModelRendererColored(this, 18, 2);
		this.Kibble33.setTextureSize(64, 32);
		this.Kibble33.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble33.setRotationPoint(1.955643F, 20.5F, 1.325751F);
		this.Kibble5 = new ModelRendererColored(this, 18, 2);
		this.Kibble5.setTextureSize(64, 32);
		this.Kibble5.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble5.setRotationPoint(-0.5F, 21.5F, 0.1000004F);
		this.Kibble24 = new ModelRendererColored(this, 18, 2);
		this.Kibble24.setTextureSize(64, 32);
		this.Kibble24.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble24.setRotationPoint(-1.235981F, 20.99866F, 1.307058F);
		this.Kibble34 = new ModelRendererColored(this, 18, 2);
		this.Kibble34.setTextureSize(64, 32);
		this.Kibble34.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble34.setRotationPoint(0.5035561F, 20.70565F, 0.6575029F);
		this.Kibble42 = new ModelRendererColored(this, 18, 2);
		this.Kibble42.setTextureSize(64, 32);
		this.Kibble42.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble42.setRotationPoint(-0.3424319F, 21.6307F, 1.078821F);
		this.Kibble25 = new ModelRendererColored(this, 18, 2);
		this.Kibble25.setTextureSize(64, 32);
		this.Kibble25.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble25.setRotationPoint(1.101661F, 21.32954F, 0.8069769F);
		this.Kibble35 = new ModelRendererColored(this, 18, 2);
		this.Kibble35.setTextureSize(64, 32);
		this.Kibble35.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble35.setRotationPoint(-0.4992056F, 20.49984F, 0.2753914F);
		this.Kibble6 = new ModelRendererColored(this, 18, 2);
		this.Kibble6.setTextureSize(64, 32);
		this.Kibble6.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble6.setRotationPoint(-2.761048F, 21.27966F, 1.515037F);
		this.Kibble26 = new ModelRendererColored(this, 18, 2);
		this.Kibble26.setTextureSize(64, 32);
		this.Kibble26.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble26.setRotationPoint(-1.619033F, 21.05934F, 2.462274F);
		this.Kibble36 = new ModelRendererColored(this, 18, 2);
		this.Kibble36.setTextureSize(64, 32);
		this.Kibble36.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble36.setRotationPoint(-2.06883F, 20.29823F, 0.8033095F);
		this.Kibble43 = new ModelRendererColored(this, 18, 2);
		this.Kibble43.setTextureSize(64, 32);
		this.Kibble43.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble43.setRotationPoint(-1.7772F, 21.43127F, 1.419847F);
		this.Kibble27 = new ModelRendererColored(this, 18, 2);
		this.Kibble27.setTextureSize(64, 32);
		this.Kibble27.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble27.setRotationPoint(-1.882572F, 20.76118F, 0.08198526F);
		this.Kibble37 = new ModelRendererColored(this, 18, 2);
		this.Kibble37.setTextureSize(64, 32);
		this.Kibble37.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble37.setRotationPoint(-2.545976F, 20.32496F, 1.785915F);
		this.Kibble44 = new ModelRendererColored(this, 18, 2);
		this.Kibble44.setTextureSize(64, 32);
		this.Kibble44.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble44.setRotationPoint(-0.342432F, 21.6307F, 1.078821F);
		this.Kibble28 = new ModelRendererColored(this, 18, 2);
		this.Kibble28.setTextureSize(64, 32);
		this.Kibble28.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble28.setRotationPoint(-0.6491588F, 21.22169F, 2.48901F);
		this.Kibble38 = new ModelRendererColored(this, 18, 2);
		this.Kibble38.setTextureSize(64, 32);
		this.Kibble38.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble38.setRotationPoint(0.7681076F, 20.8202F, 1.321139F);
		this.Kibble7 = new ModelRendererColored(this, 18, 2);
		this.Kibble7.setTextureSize(64, 32);
		this.Kibble7.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble7.setRotationPoint(-0.5F, 21.5F, 0.1000004F);
		this.Kibble29 = new ModelRendererColored(this, 18, 2);
		this.Kibble29.setTextureSize(64, 32);
		this.Kibble29.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble29.setRotationPoint(0.003598869F, 21F, -1.221509F);
		this.Kibble39 = new ModelRendererColored(this, 18, 2);
		this.Kibble39.setTextureSize(64, 32);
		this.Kibble39.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble39.setRotationPoint(-1.445684F, 20.5F, -0.1336928F);
		this.Kibble45 = new ModelRendererColored(this, 18, 2);
		this.Kibble45.setTextureSize(64, 32);
		this.Kibble45.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble45.setRotationPoint(-0.9089555F, 21.5F, -0.8125539F);
		this.Kibble210 = new ModelRendererColored(this, 18, 2);
		this.Kibble210.setTextureSize(64, 32);
		this.Kibble210.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble210.setRotationPoint(-2.165683F, 21F, -0.1639889F);
		this.Kibble310 = new ModelRendererColored(this, 18, 2);
		this.Kibble310.setTextureSize(64, 32);
		this.Kibble310.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble310.setRotationPoint(-0.3578667F, 20.35F, -0.006823928F);
		this.Kibble311 = new ModelRendererColored(this, 18, 2);
		this.Kibble311.setTextureSize(64, 32);
		this.Kibble311.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble311.setRotationPoint(-0.1387583F, 21.5F, 0.06311534F);
		this.Kibble312 = new ModelRendererColored(this, 18, 2);
		this.Kibble312.setTextureSize(64, 32);
		this.Kibble312.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble312.setRotationPoint(0.6518521F, 21.5F, -2.413765F);
		this.Kibble313 = new ModelRendererColored(this, 18, 2);
		this.Kibble313.setTextureSize(64, 32);
		this.Kibble313.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble313.setRotationPoint(-1.25344F, 21.5F, -3.021926F);
		this.Kibble8 = new ModelRendererColored(this, 18, 2);
		this.Kibble8.setTextureSize(64, 32);
		this.Kibble8.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble8.setRotationPoint(1.356536F, 21.5F, -1.827819F);
		this.Kibble211 = new ModelRendererColored(this, 18, 2);
		this.Kibble211.setTextureSize(64, 32);
		this.Kibble211.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble211.setRotationPoint(0.08056542F, 21F, -2.437655F);
		this.Kibble314 = new ModelRendererColored(this, 18, 2);
		this.Kibble314.setTextureSize(64, 32);
		this.Kibble314.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble314.setRotationPoint(1.046395F, 20.5F, -0.9043784F);
		this.Kibble46 = new ModelRendererColored(this, 18, 2);
		this.Kibble46.setTextureSize(64, 32);
		this.Kibble46.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble46.setRotationPoint(0.4136325F, 21.5F, -1.494752F);
		this.Kibble212 = new ModelRendererColored(this, 18, 2);
		this.Kibble212.setTextureSize(64, 32);
		this.Kibble212.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble212.setRotationPoint(0.9574023F, 21F, -0.1892587F);
		this.Kibble315 = new ModelRendererColored(this, 18, 2);
		this.Kibble315.setTextureSize(64, 32);
		this.Kibble315.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble315.setRotationPoint(1.260692F, 20.5F, -1.975814F);
		this.Kibble9 = new ModelRendererColored(this, 18, 2);
		this.Kibble9.setTextureSize(64, 32);
		this.Kibble9.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble9.setRotationPoint(-1.481493F, 21.5F, -2.09013F);
		this.Kibble213 = new ModelRendererColored(this, 18, 2);
		this.Kibble213.setTextureSize(64, 32);
		this.Kibble213.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble213.setRotationPoint(-1.659482F, 20.99866F, -0.6876404F);
		this.Kibble316 = new ModelRendererColored(this, 18, 2);
		this.Kibble316.setTextureSize(64, 32);
		this.Kibble316.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble316.setRotationPoint(-0.3376997F, 20.70565F, -1.991788F);
		this.Kibble47 = new ModelRendererColored(this, 18, 2);
		this.Kibble47.setTextureSize(64, 32);
		this.Kibble47.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble47.setRotationPoint(-0.9374095F, 21.6307F, -1.261341F);
		this.Kibble214 = new ModelRendererColored(this, 18, 2);
		this.Kibble214.setTextureSize(64, 32);
		this.Kibble214.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble214.setRotationPoint(0.2692313F, 21.32954F, -2.099983F);
		this.Kibble317 = new ModelRendererColored(this, 18, 2);
		this.Kibble317.setTextureSize(64, 32);
		this.Kibble317.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble317.setRotationPoint(-1.409041F, 20.49984F, -1.930401F);
		this.Kibble10 = new ModelRendererColored(this, 18, 2);
		this.Kibble10.setTextureSize(64, 32);
		this.Kibble10.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble10.setRotationPoint(-2.966135F, 21.27966F, 0.1258362F);
		this.Kibble215 = new ModelRendererColored(this, 18, 2);
		this.Kibble215.setTextureSize(64, 32);
		this.Kibble215.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble215.setRotationPoint(-1.536606F, 21.05934F, 0.5232083F);
		this.Kibble318 = new ModelRendererColored(this, 18, 2);
		this.Kibble318.setTextureSize(64, 32);
		this.Kibble318.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble318.setRotationPoint(-2.625513F, 20.29823F, -0.8067404F);
		this.Kibble48 = new ModelRendererColored(this, 18, 2);
		this.Kibble48.setTextureSize(64, 32);
		this.Kibble48.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble48.setRotationPoint(-2.107249F, 21.43127F, -0.3633795F);
		this.Kibble216 = new ModelRendererColored(this, 18, 2);
		this.Kibble216.setTextureSize(64, 32);
		this.Kibble216.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble216.setRotationPoint(-2.750532F, 20.76118F, -1.541159F);
		this.Kibble319 = new ModelRendererColored(this, 18, 2);
		this.Kibble319.setTextureSize(64, 32);
		this.Kibble319.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble319.setRotationPoint(-2.659093F, 20.32496F, 0.2850727F);
		this.Kibble11 = new ModelRendererColored(this, 18, 2);
		this.Kibble11.setTextureSize(64, 32);
		this.Kibble11.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble11.setRotationPoint(1.5F, 21.5F, 0.1000004F);
		this.Kibble217 = new ModelRendererColored(this, 18, 2);
		this.Kibble217.setTextureSize(64, 32);
		this.Kibble217.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble217.setRotationPoint(1.583739F, 21F, 1.511733F);
		this.Kibble320 = new ModelRendererColored(this, 18, 2);
		this.Kibble320.setTextureSize(64, 32);
		this.Kibble320.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble320.setRotationPoint(2.080258F, 21.5F, -2.059467F);
		this.Kibble49 = new ModelRendererColored(this, 18, 2);
		this.Kibble49.setTextureSize(64, 32);
		this.Kibble49.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble49.setRotationPoint(2.247736F, 21.5F, 0.7639972F);
		this.Kibble218 = new ModelRendererColored(this, 18, 2);
		this.Kibble218.setTextureSize(64, 32);
		this.Kibble218.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble218.setRotationPoint(3.12709F, 21F, -0.3435835F);
		this.Kibble321 = new ModelRendererColored(this, 18, 2);
		this.Kibble321.setTextureSize(64, 32);
		this.Kibble321.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble321.setRotationPoint(1.416863F, 20.5F, 0.2554886F);
		this.Kibble12 = new ModelRendererColored(this, 18, 2);
		this.Kibble12.setTextureSize(64, 32);
		this.Kibble12.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble12.setRotationPoint(0.5993088F, 21.5F, 2.620312F);
		this.Kibble219 = new ModelRendererColored(this, 18, 2);
		this.Kibble219.setTextureSize(64, 32);
		this.Kibble219.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble219.setRotationPoint(2.013164F, 21F, 2.65214F);
		this.Kibble322 = new ModelRendererColored(this, 18, 2);
		this.Kibble322.setTextureSize(64, 32);
		this.Kibble322.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble322.setRotationPoint(0.502718F, 20.5F, 1.650982F);
		this.Kibble410 = new ModelRendererColored(this, 18, 2);
		this.Kibble410.setTextureSize(64, 32);
		this.Kibble410.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble410.setRotationPoint(1.322151F, 21.5F, 1.929298F);
		this.Kibble220 = new ModelRendererColored(this, 18, 2);
		this.Kibble220.setTextureSize(64, 32);
		this.Kibble220.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble220.setRotationPoint(0.2900807F, 21F, 0.9624316F);
		this.Kibble323 = new ModelRendererColored(this, 18, 2);
		this.Kibble323.setTextureSize(64, 32);
		this.Kibble323.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble323.setRotationPoint(0.7474885F, 20.5F, 2.715869F);
		this.Kibble411 = new ModelRendererColored(this, 18, 2);
		this.Kibble411.setTextureSize(64, 32);
		this.Kibble411.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble411.setRotationPoint(2.247736F, 21.5F, 0.7639973F);
		this.Kibble221 = new ModelRendererColored(this, 18, 2);
		this.Kibble221.setTextureSize(64, 32);
		this.Kibble221.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble221.setRotationPoint(2.802393F, 21F, 2.064903F);
		this.Kibble324 = new ModelRendererColored(this, 18, 2);
		this.Kibble324.setTextureSize(64, 32);
		this.Kibble324.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble324.setRotationPoint(3.090747F, 20.5F, 0.2758757F);
		this.Kibble325 = new ModelRendererColored(this, 18, 2);
		this.Kibble325.setTextureSize(64, 32);
		this.Kibble325.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble325.setRotationPoint(2.163996F, 21.5F, -0.6477347F);
		this.Kibble326 = new ModelRendererColored(this, 18, 2);
		this.Kibble326.setTextureSize(64, 32);
		this.Kibble326.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Kibble326.setRotationPoint(0.6685259F, 21.5F, -1.975729F);

	}

	@Override
	public void setColor(float r, float g, float b)
	{
		this.Kibble.setColor(r, g, b);
		this.Kibble2.setColor(r, g, b);
		this.Kibble3.setColor(r, g, b);
		this.Kibble4.setColor(r, g, b);
		this.Kibble21.setColor(r, g, b);
		this.Kibble31.setColor(r, g, b);
		this.Kibble1.setColor(r, g, b);
		this.Kibble22.setColor(r, g, b);
		this.Kibble32.setColor(r, g, b);
		this.Kibble41.setColor(r, g, b);
		this.Kibble23.setColor(r, g, b);
		this.Kibble33.setColor(r, g, b);
		this.Kibble5.setColor(r, g, b);
		this.Kibble24.setColor(r, g, b);
		this.Kibble34.setColor(r, g, b);
		this.Kibble42.setColor(r, g, b);
		this.Kibble25.setColor(r, g, b);
		this.Kibble35.setColor(r, g, b);
		this.Kibble6.setColor(r, g, b);
		this.Kibble26.setColor(r, g, b);
		this.Kibble36.setColor(r, g, b);
		this.Kibble43.setColor(r, g, b);
		this.Kibble27.setColor(r, g, b);
		this.Kibble37.setColor(r, g, b);
		this.Kibble44.setColor(r, g, b);
		this.Kibble28.setColor(r, g, b);
		this.Kibble38.setColor(r, g, b);
		this.Kibble7.setColor(r, g, b);
		this.Kibble29.setColor(r, g, b);
		this.Kibble39.setColor(r, g, b);
		this.Kibble45.setColor(r, g, b);
		this.Kibble210.setColor(r, g, b);
		this.Kibble310.setColor(r, g, b);
		this.Kibble311.setColor(r, g, b);
		this.Kibble312.setColor(r, g, b);
		this.Kibble313.setColor(r, g, b);
		this.Kibble8.setColor(r, g, b);
		this.Kibble211.setColor(r, g, b);
		this.Kibble314.setColor(r, g, b);
		this.Kibble46.setColor(r, g, b);
		this.Kibble212.setColor(r, g, b);
		this.Kibble315.setColor(r, g, b);
		this.Kibble9.setColor(r, g, b);
		this.Kibble213.setColor(r, g, b);
		this.Kibble316.setColor(r, g, b);
		this.Kibble47.setColor(r, g, b);
		this.Kibble214.setColor(r, g, b);
		this.Kibble317.setColor(r, g, b);
		this.Kibble10.setColor(r, g, b);
		this.Kibble215.setColor(r, g, b);
		this.Kibble318.setColor(r, g, b);
		this.Kibble48.setColor(r, g, b);
		this.Kibble216.setColor(r, g, b);
		this.Kibble319.setColor(r, g, b);
		this.Kibble11.setColor(r, g, b);
		this.Kibble217.setColor(r, g, b);
		this.Kibble320.setColor(r, g, b);
		this.Kibble49.setColor(r, g, b);
		this.Kibble218.setColor(r, g, b);
		this.Kibble321.setColor(r, g, b);
		this.Kibble12.setColor(r, g, b);
		this.Kibble219.setColor(r, g, b);
		this.Kibble322.setColor(r, g, b);
		this.Kibble410.setColor(r, g, b);
		this.Kibble220.setColor(r, g, b);
		this.Kibble323.setColor(r, g, b);
		this.Kibble411.setColor(r, g, b);
		this.Kibble221.setColor(r, g, b);
		this.Kibble324.setColor(r, g, b);
		this.Kibble325.setColor(r, g, b);
		this.Kibble326.setColor(r, g, b);
	}

	public void renderFood(float scale)
	{
		this.Kibble.xRot = 0F;
		this.Kibble.yRot = 0F;
		this.Kibble.zRot = 0F;
		this.Kibble.renderWithRotation(scale);

		this.Kibble2.xRot = -0.1574999F;
		this.Kibble2.yRot = -0.4672038F;
		this.Kibble2.zRot = -0.3014539F;
		this.Kibble2.renderWithRotation(scale);

		this.Kibble3.xRot = 0.3970294F;
		this.Kibble3.yRot = 0.3625107F;
		this.Kibble3.zRot = 0.775726F;
		this.Kibble3.renderWithRotation(scale);

		this.Kibble4.xRot = 0F;
		this.Kibble4.yRot = 2.41131F;
		this.Kibble4.zRot = 0F;
		this.Kibble4.renderWithRotation(scale);

		this.Kibble21.xRot = -0.1574999F;
		this.Kibble21.yRot = 1.944106F;
		this.Kibble21.zRot = -0.3014538F;
		this.Kibble21.renderWithRotation(scale);

		this.Kibble31.xRot = 0.3970294F;
		this.Kibble31.yRot = 2.773821F;
		this.Kibble31.zRot = 0.775726F;
		this.Kibble31.renderWithRotation(scale);

		this.Kibble1.xRot = 0F;
		this.Kibble1.yRot = 1.489042F;
		this.Kibble1.zRot = 0F;
		this.Kibble1.renderWithRotation(scale);

		this.Kibble22.xRot = -0.1574999F;
		this.Kibble22.yRot = 1.021838F;
		this.Kibble22.zRot = -0.3014539F;
		this.Kibble22.renderWithRotation(scale);

		this.Kibble32.xRot = 0.3970294F;
		this.Kibble32.yRot = 1.851552F;
		this.Kibble32.zRot = 0.7757261F;
		this.Kibble32.renderWithRotation(scale);

		this.Kibble41.xRot = 0F;
		this.Kibble41.yRot = -2.382834F;
		this.Kibble41.zRot = 0F;
		this.Kibble41.renderWithRotation(scale);

		this.Kibble23.xRot = -0.1574999F;
		this.Kibble23.yRot = -2.850037F;
		this.Kibble23.zRot = -0.3014538F;
		this.Kibble23.renderWithRotation(scale);

		this.Kibble33.xRot = 0.3970294F;
		this.Kibble33.yRot = -2.020323F;
		this.Kibble33.zRot = 0.775726F;
		this.Kibble33.renderWithRotation(scale);

		this.Kibble5.xRot = 0.1310762F;
		this.Kibble5.yRot = -2.981984F;
		this.Kibble5.zRot = -0.1431112F;
		this.Kibble5.renderWithRotation(scale);

		this.Kibble24.xRot = -0.101737F;
		this.Kibble24.yRot = 2.867677F;
		this.Kibble24.zRot = -0.4886197F;
		this.Kibble24.renderWithRotation(scale);

		this.Kibble34.xRot = 0.5685233F;
		this.Kibble34.yRot = -2.657655F;
		this.Kibble34.zRot = 0.6736612F;
		this.Kibble34.renderWithRotation(scale);

		this.Kibble42.xRot = -0.003045561F;
		this.Kibble42.yRot = -0.5615618F;
		this.Kibble42.zRot = 0.1937396F;
		this.Kibble42.renderWithRotation(scale);

		this.Kibble25.xRot = -0.07101944F;
		this.Kibble25.yRot = -1.048444F;
		this.Kibble25.zRot = -0.1268627F;
		this.Kibble25.renderWithRotation(scale);

		this.Kibble35.xRot = -5.963936F;
		this.Kibble35.yRot = -0.1320712F;
		this.Kibble35.zRot = 0.9653189F;
		this.Kibble35.renderWithRotation(scale);

		this.Kibble6.xRot = 0.1521878F;
		this.Kibble6.yRot = -1.474344F;
		this.Kibble6.zRot = 0.1203963F;
		this.Kibble6.renderWithRotation(scale);

		this.Kibble26.xRot = 0.03257764F;
		this.Kibble26.yRot = -1.953105F;
		this.Kibble26.zRot = -0.2637101F;
		this.Kibble26.renderWithRotation(scale);

		this.Kibble36.xRot = 0.4908113F;
		this.Kibble36.yRot = -1.040031F;
		this.Kibble36.zRot = 0.9636469F;
		this.Kibble36.renderWithRotation(scale);

		this.Kibble43.xRot = -0.1933369F;
		this.Kibble43.yRot = 0.9265222F;
		this.Kibble43.zRot = 0.01292846F;
		this.Kibble43.renderWithRotation(scale);

		this.Kibble27.xRot = -0.3232937F;
		this.Kibble27.yRot = 0.4358495F;
		this.Kibble27.zRot = -0.1980595F;
		this.Kibble27.renderWithRotation(scale);

		this.Kibble37.xRot = 0.2111879F;
		this.Kibble37.yRot = 1.272965F;
		this.Kibble37.zRot = 0.7181429F;
		this.Kibble37.renderWithRotation(scale);

		this.Kibble44.xRot = 0.1715521F;
		this.Kibble44.yRot = -2.636588F;
		this.Kibble44.zRot = -0.09052449F;
		this.Kibble44.renderWithRotation(scale);

		this.Kibble28.xRot = -0.04301889F;
		this.Kibble28.yRot = -3.080209F;
		this.Kibble28.zRot = -0.4586606F;
		this.Kibble28.renderWithRotation(scale);

		this.Kibble38.xRot = 0.5892963F;
		this.Kibble38.yRot = -2.279304F;
		this.Kibble38.zRot = 0.7483609F;
		this.Kibble38.renderWithRotation(scale);

		this.Kibble7.xRot = 0F;
		this.Kibble7.yRot = 0.4213091F;
		this.Kibble7.zRot = 0F;
		this.Kibble7.renderWithRotation(scale);

		this.Kibble29.xRot = -0.1574999F;
		this.Kibble29.yRot = -0.04589463F;
		this.Kibble29.zRot = -0.3014538F;
		this.Kibble29.renderWithRotation(scale);

		this.Kibble39.xRot = 0.3970294F;
		this.Kibble39.yRot = 0.7838198F;
		this.Kibble39.zRot = 0.775726F;
		this.Kibble39.renderWithRotation(scale);

		this.Kibble45.xRot = 0F;
		this.Kibble45.yRot = 2.832619F;
		this.Kibble45.zRot = 0F;
		this.Kibble45.renderWithRotation(scale);

		this.Kibble210.xRot = -0.1574999F;
		this.Kibble210.yRot = 2.365415F;
		this.Kibble210.zRot = -0.3014538F;
		this.Kibble210.renderWithRotation(scale);

		this.Kibble310.xRot = 0.3970294F;
		this.Kibble310.yRot = -3.088056F;
		this.Kibble310.zRot = 0.775726F;
		this.Kibble310.renderWithRotation(scale);

		this.Kibble311.xRot = 0.3970294F;
		this.Kibble311.yRot = -3.088056F;
		this.Kibble311.zRot = 0.775726F;
		this.Kibble311.renderWithRotation(scale);

		this.Kibble312.xRot = 0.3970294F;
		this.Kibble312.yRot = -3.088056F;
		this.Kibble312.zRot = 0.775726F;
		this.Kibble312.renderWithRotation(scale);

		this.Kibble313.xRot = 0.3970294F;
		this.Kibble313.yRot = -3.088056F;
		this.Kibble313.zRot = 0.775726F;
		this.Kibble313.renderWithRotation(scale);

		this.Kibble8.xRot = 0F;
		this.Kibble8.yRot = 1.910351F;
		this.Kibble8.zRot = 0F;
		this.Kibble8.renderWithRotation(scale);

		this.Kibble211.xRot = -0.1574999F;
		this.Kibble211.yRot = 1.443147F;
		this.Kibble211.zRot = -0.3014538F;
		this.Kibble211.renderWithRotation(scale);

		this.Kibble314.xRot = 0.3970294F;
		this.Kibble314.yRot = 2.272861F;
		this.Kibble314.zRot = 0.775726F;
		this.Kibble314.renderWithRotation(scale);

		this.Kibble46.xRot = 0F;
		this.Kibble46.yRot = -1.961524F;
		this.Kibble46.zRot = 0F;
		this.Kibble46.renderWithRotation(scale);

		this.Kibble212.xRot = -0.1574999F;
		this.Kibble212.yRot = -2.428728F;
		this.Kibble212.zRot = -0.3014538F;
		this.Kibble212.renderWithRotation(scale);

		this.Kibble315.xRot = 0.3970293F;
		this.Kibble315.yRot = -1.599014F;
		this.Kibble315.zRot = 0.775726F;
		this.Kibble315.renderWithRotation(scale);

		this.Kibble9.xRot = 0.1310762F;
		this.Kibble9.yRot = -2.560675F;
		this.Kibble9.zRot = -0.1431112F;
		this.Kibble9.renderWithRotation(scale);

		this.Kibble213.xRot = -0.101737F;
		this.Kibble213.yRot = -2.994199F;
		this.Kibble213.zRot = -0.4886197F;
		this.Kibble213.renderWithRotation(scale);

		this.Kibble316.xRot = 0.5685233F;
		this.Kibble316.yRot = -2.236345F;
		this.Kibble316.zRot = 0.6736611F;
		this.Kibble316.renderWithRotation(scale);

		this.Kibble47.xRot = -0.003045562F;
		this.Kibble47.yRot = -0.1402526F;
		this.Kibble47.zRot = 0.1937396F;
		this.Kibble47.renderWithRotation(scale);

		this.Kibble214.xRot = -0.07101945F;
		this.Kibble214.yRot = -0.6271345F;
		this.Kibble214.zRot = -0.1268627F;
		this.Kibble214.renderWithRotation(scale);

		this.Kibble317.xRot = 0.3192494F;
		this.Kibble317.yRot = 0.2892379F;
		this.Kibble317.zRot = 0.9653188F;
		this.Kibble317.renderWithRotation(scale);

		this.Kibble10.xRot = 0.1521878F;
		this.Kibble10.yRot = -1.053035F;
		this.Kibble10.zRot = 0.1203963F;
		this.Kibble10.renderWithRotation(scale);

		this.Kibble215.xRot = 0.03257762F;
		this.Kibble215.yRot = -1.531796F;
		this.Kibble215.zRot = -0.2637101F;
		this.Kibble215.renderWithRotation(scale);

		this.Kibble318.xRot = 0.4908113F;
		this.Kibble318.yRot = -0.6187222F;
		this.Kibble318.zRot = 0.9636468F;
		this.Kibble318.renderWithRotation(scale);

		this.Kibble48.xRot = 6.089849F;
		this.Kibble48.yRot = 1.347831F;
		this.Kibble48.zRot = 0.01292845F;
		this.Kibble48.renderWithRotation(scale);

		this.Kibble216.xRot = -0.3232938F;
		this.Kibble216.yRot = 0.8571585F;
		this.Kibble216.zRot = -0.1980595F;
		this.Kibble216.renderWithRotation(scale);

		this.Kibble319.xRot = 0.211188F;
		this.Kibble319.yRot = 1.694274F;
		this.Kibble319.zRot = 0.718143F;
		this.Kibble319.renderWithRotation(scale);

		this.Kibble11.xRot = 0F;
		this.Kibble11.yRot = -2.296948F;
		this.Kibble11.zRot = 0F;
		this.Kibble11.renderWithRotation(scale);

		this.Kibble217.xRot = -0.1574999F;
		this.Kibble217.yRot = -2.764152F;
		this.Kibble217.zRot = -0.3014539F;
		this.Kibble217.renderWithRotation(scale);

		this.Kibble320.xRot = 0.3970294F;
		this.Kibble320.yRot = -1.934437F;
		this.Kibble320.zRot = 0.7757261F;
		this.Kibble320.renderWithRotation(scale);

		this.Kibble49.xRot = -6.283185F;
		this.Kibble49.yRot = 0.1143622F;
		this.Kibble49.zRot = 0F;
		this.Kibble49.renderWithRotation(scale);

		this.Kibble218.xRot = -0.1574999F;
		this.Kibble218.yRot = -0.3528416F;
		this.Kibble218.zRot = -0.3014538F;
		this.Kibble218.renderWithRotation(scale);

		this.Kibble321.xRot = 0.3970294F;
		this.Kibble321.yRot = 0.4768728F;
		this.Kibble321.zRot = 0.775726F;
		this.Kibble321.renderWithRotation(scale);

		this.Kibble12.xRot = 0F;
		this.Kibble12.yRot = -0.807906F;
		this.Kibble12.zRot = 0F;
		this.Kibble12.renderWithRotation(scale);

		this.Kibble219.xRot = -0.1574999F;
		this.Kibble219.yRot = -1.27511F;
		this.Kibble219.zRot = -0.3014539F;
		this.Kibble219.renderWithRotation(scale);

		this.Kibble322.xRot = 0.3970294F;
		this.Kibble322.yRot = -0.4453953F;
		this.Kibble322.zRot = 0.775726F;
		this.Kibble322.renderWithRotation(scale);

		this.Kibble410.xRot = 0F;
		this.Kibble410.yRot = 1.603404F;
		this.Kibble410.zRot = 0F;
		this.Kibble410.renderWithRotation(scale);

		this.Kibble220.xRot = -0.1574999F;
		this.Kibble220.yRot = 1.1362F;
		this.Kibble220.zRot = -0.3014538F;
		this.Kibble220.renderWithRotation(scale);

		this.Kibble323.xRot = 0.3970294F;
		this.Kibble323.yRot = 1.965915F;
		this.Kibble323.zRot = 0.775726F;
		this.Kibble323.renderWithRotation(scale);

		this.Kibble411.xRot = -6.283185F;
		this.Kibble411.yRot = -1.95317F;
		this.Kibble411.zRot = -1.241958E-08F;
		this.Kibble411.renderWithRotation(scale);

		this.Kibble221.xRot = -0.1574999F;
		this.Kibble221.yRot = -2.420374F;
		this.Kibble221.zRot = -0.3014539F;
		this.Kibble221.renderWithRotation(scale);

		this.Kibble324.xRot = 0.3970294F;
		this.Kibble324.yRot = -1.590659F;
		this.Kibble324.zRot = 0.775726F;
		this.Kibble324.renderWithRotation(scale);

		this.Kibble325.xRot = 0.3970294F;
		this.Kibble325.yRot = -1.934437F;
		this.Kibble325.zRot = 0.7757261F;
		this.Kibble325.renderWithRotation(scale);

		this.Kibble326.xRot = 0.3970294F;
		this.Kibble326.yRot = -1.934437F;
		this.Kibble326.zRot = 0.7757261F;
		this.Kibble326.renderWithRotation(scale);

	}

	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		this.Root_Node.xRot = 0F;
		this.Root_Node.yRot = 0F;
		this.Root_Node.zRot = 0F;
		this.Root_Node.renderWithRotation(par7);

		this.Side1.xRot = 0F;
		this.Side1.yRot = -1.570796F;
		this.Side1.zRot = 0F;
		this.Side1.renderWithRotation(par7);

		this.Side2.xRot = 0F;
		this.Side2.yRot = -2.356194F;
		this.Side2.zRot = 0F;
		this.Side2.renderWithRotation(par7);

		this.Side3.xRot = 0F;
		this.Side3.yRot = -3.141593F;
		this.Side3.zRot = 0F;
		this.Side3.renderWithRotation(par7);

		this.Side4.xRot = 0F;
		this.Side4.yRot = 2.356194F;
		this.Side4.zRot = 0F;
		this.Side4.renderWithRotation(par7);

		this.Side5.xRot = -6.283185F;
		this.Side5.yRot = 1.570796F;
		this.Side5.zRot = 0F;
		this.Side5.renderWithRotation(par7);

		this.Side6.xRot = 0F;
		this.Side6.yRot = 0.7853978F;
		this.Side6.zRot = 0F;
		this.Side6.renderWithRotation(par7);

		this.Side7.xRot = 0F;
		this.Side7.yRot = -2.753375E-07F;
		this.Side7.zRot = 0F;
		this.Side7.renderWithRotation(par7);

		this.Side8.xRot = 0F;
		this.Side8.yRot = -0.7853984F;
		this.Side8.zRot = 0F;
		this.Side8.renderWithRotation(par7);

		this.Bottom_.xRot = 0F;
		this.Bottom_.yRot = 0F;
		this.Bottom_.zRot = 0F;
		this.Bottom_.renderWithRotation(par7);

		this.Bottom_2.xRot = 0F;
		this.Bottom_2.yRot = -1.570796F;
		this.Bottom_2.zRot = 0F;
		this.Bottom_2.renderWithRotation(par7);

		this.Bottom_3.xRot = 0F;
		this.Bottom_3.yRot = -0.7853982F;
		this.Bottom_3.zRot = 0F;
		this.Bottom_3.renderWithRotation(par7);

		this.Bottom_4.xRot = 0F;
		this.Bottom_4.yRot = 0.7853982F;
		this.Bottom_4.zRot = 0F;
		this.Bottom_4.renderWithRotation(par7);
	}
}
