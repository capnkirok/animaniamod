package com.animania.addons.catsdogs.client.models.blocks;

import com.animania.client.models.IColoredModel;
import com.animania.client.models.render.ModelRendererColored;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPetBowl extends ModelBase implements IColoredModel
{
	ModelRenderer Root_Node;
	ModelRenderer Side1;
	ModelRenderer Side2;
	ModelRenderer Side3;
	ModelRenderer Side4;
	ModelRenderer Side5;
	ModelRenderer Side6;
	ModelRenderer Side7;
	ModelRenderer Side8;
	ModelRenderer Bottom_;
	ModelRenderer Bottom_2;
	ModelRenderer Bottom_3;
	ModelRenderer Bottom_4;
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
		Root_Node = new ModelRenderer(this, 16, 16);
		Root_Node.setTextureSize(64, 32);
		Root_Node.addBox(0F, 0F, 0F, 0, 0, 0);
		Root_Node.setRotationPoint(0F, 24F, 0F);
		Side1 = new ModelRenderer(this, 12, 12);
		Side1.setTextureSize(64, 32);
		Side1.addBox(4F, -2F, -2F, 1, 4, 4);
		Side1.setRotationPoint(0F, 21.5F, 0F);
		Side2 = new ModelRenderer(this, 12, 12);
		Side2.setTextureSize(64, 32);
		Side2.addBox(-1F, -2F, 0F, 1, 4, 4);
		Side2.setRotationPoint(-1.999999F, 21.5F, 5F);
		Side3 = new ModelRenderer(this, 12, 12);
		Side3.setTextureSize(64, 32);
		Side3.addBox(-1F, -2F, 0F, 1, 4, 4);
		Side3.setRotationPoint(-4.828425F, 21.5F, 2.171573F);
		Side4 = new ModelRenderer(this, 12, 12);
		Side4.setTextureSize(64, 32);
		Side4.addBox(-1F, -2F, 0F, 1, 4, 4);
		Side4.setRotationPoint(-4.828426F, 21.5F, -1.828427F);
		Side5 = new ModelRenderer(this, 12, 12);
		Side5.setTextureSize(64, 32);
		Side5.addBox(-1F, -2F, 0F, 1, 4, 4);
		Side5.setRotationPoint(-1.999998F, 21.5F, -4.656853F);
		Side6 = new ModelRenderer(this, 12, 12);
		Side6.setTextureSize(64, 32);
		Side6.addBox(-1F, -2F, 0F, 1, 4, 4);
		Side6.setRotationPoint(2.000001F, 21.5F, -4.656853F);
		Side7 = new ModelRenderer(this, 12, 12);
		Side7.setTextureSize(64, 32);
		Side7.addBox(-1F, -2F, 0F, 1, 4, 4);
		Side7.setRotationPoint(4.828427F, 21.5F, -1.828425F);
		Side8 = new ModelRenderer(this, 12, 12);
		Side8.setTextureSize(64, 32);
		Side8.addBox(-1F, -2F, 0F, 1, 4, 4);
		Side8.setRotationPoint(4.828425F, 21.5F, 2.171574F);
		Bottom_ = new ModelRenderer(this, 12, 6);
		Bottom_.setTextureSize(64, 32);
		Bottom_.addBox(-4F, -0.5F, -2F, 8, 1, 4);
		Bottom_.setRotationPoint(0F, 23F, 0F);
		Bottom_2 = new ModelRenderer(this, 12, 6);
		Bottom_2.setTextureSize(64, 32);
		Bottom_2.addBox(-4F, -0.5F, -2F, 8, 1, 4);
		Bottom_2.setRotationPoint(0F, 23F, 0F);
		Bottom_3 = new ModelRenderer(this, 12, 6);
		Bottom_3.setTextureSize(64, 32);
		Bottom_3.addBox(-4F, -0.5F, -2F, 8, 1, 4);
		Bottom_3.setRotationPoint(0F, 23F, 0F);
		Bottom_4 = new ModelRenderer(this, 12, 6);
		Bottom_4.setTextureSize(64, 32);
		Bottom_4.addBox(-4F, -0.5F, -2F, 8, 1, 4);
		Bottom_4.setRotationPoint(0F, 23F, 0F);
		Kibble = new ModelRendererColored(this, 18, 2);
		Kibble.setTextureSize(64, 32);
		Kibble.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble.setRotationPoint(-0.5F, 21.5F, 2.5F);
		Kibble2 = new ModelRendererColored(this, 18, 2);
		Kibble2.setTextureSize(64, 32);
		Kibble2.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble2.setRotationPoint(0.5F, 21F, 1.5F);
		Kibble3 = new ModelRendererColored(this, 18, 2);
		Kibble3.setTextureSize(64, 32);
		Kibble3.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble3.setRotationPoint(-1.267418F, 20.5F, 1.9F);
		Kibble4 = new ModelRendererColored(this, 18, 2);
		Kibble4.setTextureSize(64, 32);
		Kibble4.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble4.setRotationPoint(-0.5F, 21.5F, 1.5F);
		Kibble21 = new ModelRendererColored(this, 18, 2);
		Kibble21.setTextureSize(64, 32);
		Kibble21.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble21.setRotationPoint(-1.912066F, 21F, 1.577905F);
		Kibble31 = new ModelRendererColored(this, 18, 2);
		Kibble31.setTextureSize(64, 32);
		Kibble31.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble31.setRotationPoint(-0.3285331F, 20.5F, 2.458921F);
		Kibble1 = new ModelRendererColored(this, 18, 2);
		Kibble1.setTextureSize(64, 32);
		Kibble1.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble1.setRotationPoint(1.982582F, 21.5F, 1.5F);
		Kibble22 = new ModelRendererColored(this, 18, 2);
		Kibble22.setTextureSize(64, 32);
		Kibble22.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble22.setRotationPoint(1.067586F, 21F, 0.4216765F);
		Kibble32 = new ModelRendererColored(this, 18, 2);
		Kibble32.setTextureSize(64, 32);
		Kibble32.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble32.setRotationPoint(1.321916F, 20.5F, 2.215857F);
		Kibble41 = new ModelRendererColored(this, 18, 2);
		Kibble41.setTextureSize(64, 32);
		Kibble41.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble41.setRotationPoint(0.9859222F, 21.5F, 1.418336F);
		Kibble23 = new ModelRendererColored(this, 18, 2);
		Kibble23.setTextureSize(64, 32);
		Kibble23.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble23.setRotationPoint(0.8106487F, 21F, 2.68691F);
		Kibble33 = new ModelRendererColored(this, 18, 2);
		Kibble33.setTextureSize(64, 32);
		Kibble33.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble33.setRotationPoint(1.955643F, 20.5F, 1.325751F);
		Kibble5 = new ModelRendererColored(this, 18, 2);
		Kibble5.setTextureSize(64, 32);
		Kibble5.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble5.setRotationPoint(-0.5F, 21.5F, 0.1000004F);
		Kibble24 = new ModelRendererColored(this, 18, 2);
		Kibble24.setTextureSize(64, 32);
		Kibble24.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble24.setRotationPoint(-1.235981F, 20.99866F, 1.307058F);
		Kibble34 = new ModelRendererColored(this, 18, 2);
		Kibble34.setTextureSize(64, 32);
		Kibble34.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble34.setRotationPoint(0.5035561F, 20.70565F, 0.6575029F);
		Kibble42 = new ModelRendererColored(this, 18, 2);
		Kibble42.setTextureSize(64, 32);
		Kibble42.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble42.setRotationPoint(-0.3424319F, 21.6307F, 1.078821F);
		Kibble25 = new ModelRendererColored(this, 18, 2);
		Kibble25.setTextureSize(64, 32);
		Kibble25.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble25.setRotationPoint(1.101661F, 21.32954F, 0.8069769F);
		Kibble35 = new ModelRendererColored(this, 18, 2);
		Kibble35.setTextureSize(64, 32);
		Kibble35.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble35.setRotationPoint(-0.4992056F, 20.49984F, 0.2753914F);
		Kibble6 = new ModelRendererColored(this, 18, 2);
		Kibble6.setTextureSize(64, 32);
		Kibble6.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble6.setRotationPoint(-2.761048F, 21.27966F, 1.515037F);
		Kibble26 = new ModelRendererColored(this, 18, 2);
		Kibble26.setTextureSize(64, 32);
		Kibble26.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble26.setRotationPoint(-1.619033F, 21.05934F, 2.462274F);
		Kibble36 = new ModelRendererColored(this, 18, 2);
		Kibble36.setTextureSize(64, 32);
		Kibble36.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble36.setRotationPoint(-2.06883F, 20.29823F, 0.8033095F);
		Kibble43 = new ModelRendererColored(this, 18, 2);
		Kibble43.setTextureSize(64, 32);
		Kibble43.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble43.setRotationPoint(-1.7772F, 21.43127F, 1.419847F);
		Kibble27 = new ModelRendererColored(this, 18, 2);
		Kibble27.setTextureSize(64, 32);
		Kibble27.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble27.setRotationPoint(-1.882572F, 20.76118F, 0.08198526F);
		Kibble37 = new ModelRendererColored(this, 18, 2);
		Kibble37.setTextureSize(64, 32);
		Kibble37.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble37.setRotationPoint(-2.545976F, 20.32496F, 1.785915F);
		Kibble44 = new ModelRendererColored(this, 18, 2);
		Kibble44.setTextureSize(64, 32);
		Kibble44.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble44.setRotationPoint(-0.342432F, 21.6307F, 1.078821F);
		Kibble28 = new ModelRendererColored(this, 18, 2);
		Kibble28.setTextureSize(64, 32);
		Kibble28.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble28.setRotationPoint(-0.6491588F, 21.22169F, 2.48901F);
		Kibble38 = new ModelRendererColored(this, 18, 2);
		Kibble38.setTextureSize(64, 32);
		Kibble38.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble38.setRotationPoint(0.7681076F, 20.8202F, 1.321139F);
		Kibble7 = new ModelRendererColored(this, 18, 2);
		Kibble7.setTextureSize(64, 32);
		Kibble7.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble7.setRotationPoint(-0.5F, 21.5F, 0.1000004F);
		Kibble29 = new ModelRendererColored(this, 18, 2);
		Kibble29.setTextureSize(64, 32);
		Kibble29.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble29.setRotationPoint(0.003598869F, 21F, -1.221509F);
		Kibble39 = new ModelRendererColored(this, 18, 2);
		Kibble39.setTextureSize(64, 32);
		Kibble39.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble39.setRotationPoint(-1.445684F, 20.5F, -0.1336928F);
		Kibble45 = new ModelRendererColored(this, 18, 2);
		Kibble45.setTextureSize(64, 32);
		Kibble45.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble45.setRotationPoint(-0.9089555F, 21.5F, -0.8125539F);
		Kibble210 = new ModelRendererColored(this, 18, 2);
		Kibble210.setTextureSize(64, 32);
		Kibble210.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble210.setRotationPoint(-2.165683F, 21F, -0.1639889F);
		Kibble310 = new ModelRendererColored(this, 18, 2);
		Kibble310.setTextureSize(64, 32);
		Kibble310.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble310.setRotationPoint(-0.3578667F, 20.35F, -0.006823928F);
		Kibble311 = new ModelRendererColored(this, 18, 2);
		Kibble311.setTextureSize(64, 32);
		Kibble311.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble311.setRotationPoint(-0.1387583F, 21.5F, 0.06311534F);
		Kibble312 = new ModelRendererColored(this, 18, 2);
		Kibble312.setTextureSize(64, 32);
		Kibble312.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble312.setRotationPoint(0.6518521F, 21.5F, -2.413765F);
		Kibble313 = new ModelRendererColored(this, 18, 2);
		Kibble313.setTextureSize(64, 32);
		Kibble313.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble313.setRotationPoint(-1.25344F, 21.5F, -3.021926F);
		Kibble8 = new ModelRendererColored(this, 18, 2);
		Kibble8.setTextureSize(64, 32);
		Kibble8.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble8.setRotationPoint(1.356536F, 21.5F, -1.827819F);
		Kibble211 = new ModelRendererColored(this, 18, 2);
		Kibble211.setTextureSize(64, 32);
		Kibble211.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble211.setRotationPoint(0.08056542F, 21F, -2.437655F);
		Kibble314 = new ModelRendererColored(this, 18, 2);
		Kibble314.setTextureSize(64, 32);
		Kibble314.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble314.setRotationPoint(1.046395F, 20.5F, -0.9043784F);
		Kibble46 = new ModelRendererColored(this, 18, 2);
		Kibble46.setTextureSize(64, 32);
		Kibble46.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble46.setRotationPoint(0.4136325F, 21.5F, -1.494752F);
		Kibble212 = new ModelRendererColored(this, 18, 2);
		Kibble212.setTextureSize(64, 32);
		Kibble212.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble212.setRotationPoint(0.9574023F, 21F, -0.1892587F);
		Kibble315 = new ModelRendererColored(this, 18, 2);
		Kibble315.setTextureSize(64, 32);
		Kibble315.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble315.setRotationPoint(1.260692F, 20.5F, -1.975814F);
		Kibble9 = new ModelRendererColored(this, 18, 2);
		Kibble9.setTextureSize(64, 32);
		Kibble9.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble9.setRotationPoint(-1.481493F, 21.5F, -2.09013F);
		Kibble213 = new ModelRendererColored(this, 18, 2);
		Kibble213.setTextureSize(64, 32);
		Kibble213.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble213.setRotationPoint(-1.659482F, 20.99866F, -0.6876404F);
		Kibble316 = new ModelRendererColored(this, 18, 2);
		Kibble316.setTextureSize(64, 32);
		Kibble316.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble316.setRotationPoint(-0.3376997F, 20.70565F, -1.991788F);
		Kibble47 = new ModelRendererColored(this, 18, 2);
		Kibble47.setTextureSize(64, 32);
		Kibble47.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble47.setRotationPoint(-0.9374095F, 21.6307F, -1.261341F);
		Kibble214 = new ModelRendererColored(this, 18, 2);
		Kibble214.setTextureSize(64, 32);
		Kibble214.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble214.setRotationPoint(0.2692313F, 21.32954F, -2.099983F);
		Kibble317 = new ModelRendererColored(this, 18, 2);
		Kibble317.setTextureSize(64, 32);
		Kibble317.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble317.setRotationPoint(-1.409041F, 20.49984F, -1.930401F);
		Kibble10 = new ModelRendererColored(this, 18, 2);
		Kibble10.setTextureSize(64, 32);
		Kibble10.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble10.setRotationPoint(-2.966135F, 21.27966F, 0.1258362F);
		Kibble215 = new ModelRendererColored(this, 18, 2);
		Kibble215.setTextureSize(64, 32);
		Kibble215.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble215.setRotationPoint(-1.536606F, 21.05934F, 0.5232083F);
		Kibble318 = new ModelRendererColored(this, 18, 2);
		Kibble318.setTextureSize(64, 32);
		Kibble318.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble318.setRotationPoint(-2.625513F, 20.29823F, -0.8067404F);
		Kibble48 = new ModelRendererColored(this, 18, 2);
		Kibble48.setTextureSize(64, 32);
		Kibble48.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble48.setRotationPoint(-2.107249F, 21.43127F, -0.3633795F);
		Kibble216 = new ModelRendererColored(this, 18, 2);
		Kibble216.setTextureSize(64, 32);
		Kibble216.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble216.setRotationPoint(-2.750532F, 20.76118F, -1.541159F);
		Kibble319 = new ModelRendererColored(this, 18, 2);
		Kibble319.setTextureSize(64, 32);
		Kibble319.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble319.setRotationPoint(-2.659093F, 20.32496F, 0.2850727F);
		Kibble11 = new ModelRendererColored(this, 18, 2);
		Kibble11.setTextureSize(64, 32);
		Kibble11.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble11.setRotationPoint(1.5F, 21.5F, 0.1000004F);
		Kibble217 = new ModelRendererColored(this, 18, 2);
		Kibble217.setTextureSize(64, 32);
		Kibble217.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble217.setRotationPoint(1.583739F, 21F, 1.511733F);
		Kibble320 = new ModelRendererColored(this, 18, 2);
		Kibble320.setTextureSize(64, 32);
		Kibble320.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble320.setRotationPoint(2.080258F, 21.5F, -2.059467F);
		Kibble49 = new ModelRendererColored(this, 18, 2);
		Kibble49.setTextureSize(64, 32);
		Kibble49.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble49.setRotationPoint(2.247736F, 21.5F, 0.7639972F);
		Kibble218 = new ModelRendererColored(this, 18, 2);
		Kibble218.setTextureSize(64, 32);
		Kibble218.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble218.setRotationPoint(3.12709F, 21F, -0.3435835F);
		Kibble321 = new ModelRendererColored(this, 18, 2);
		Kibble321.setTextureSize(64, 32);
		Kibble321.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble321.setRotationPoint(1.416863F, 20.5F, 0.2554886F);
		Kibble12 = new ModelRendererColored(this, 18, 2);
		Kibble12.setTextureSize(64, 32);
		Kibble12.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble12.setRotationPoint(0.5993088F, 21.5F, 2.620312F);
		Kibble219 = new ModelRendererColored(this, 18, 2);
		Kibble219.setTextureSize(64, 32);
		Kibble219.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble219.setRotationPoint(2.013164F, 21F, 2.65214F);
		Kibble322 = new ModelRendererColored(this, 18, 2);
		Kibble322.setTextureSize(64, 32);
		Kibble322.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble322.setRotationPoint(0.502718F, 20.5F, 1.650982F);
		Kibble410 = new ModelRendererColored(this, 18, 2);
		Kibble410.setTextureSize(64, 32);
		Kibble410.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble410.setRotationPoint(1.322151F, 21.5F, 1.929298F);
		Kibble220 = new ModelRendererColored(this, 18, 2);
		Kibble220.setTextureSize(64, 32);
		Kibble220.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble220.setRotationPoint(0.2900807F, 21F, 0.9624316F);
		Kibble323 = new ModelRendererColored(this, 18, 2);
		Kibble323.setTextureSize(64, 32);
		Kibble323.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble323.setRotationPoint(0.7474885F, 20.5F, 2.715869F);
		Kibble411 = new ModelRendererColored(this, 18, 2);
		Kibble411.setTextureSize(64, 32);
		Kibble411.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble411.setRotationPoint(2.247736F, 21.5F, 0.7639973F);
		Kibble221 = new ModelRendererColored(this, 18, 2);
		Kibble221.setTextureSize(64, 32);
		Kibble221.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble221.setRotationPoint(2.802393F, 21F, 2.064903F);
		Kibble324 = new ModelRendererColored(this, 18, 2);
		Kibble324.setTextureSize(64, 32);
		Kibble324.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble324.setRotationPoint(3.090747F, 20.5F, 0.2758757F);
		Kibble325 = new ModelRendererColored(this, 18, 2);
		Kibble325.setTextureSize(64, 32);
		Kibble325.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble325.setRotationPoint(2.163996F, 21.5F, -0.6477347F);
		Kibble326 = new ModelRendererColored(this, 18, 2);
		Kibble326.setTextureSize(64, 32);
		Kibble326.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		Kibble326.setRotationPoint(0.6685259F, 21.5F, -1.975729F);

	}

	@Override
	public void setColor(float r, float g, float b)
	{
		Kibble.setColor(r, g, b);
		Kibble2.setColor(r, g, b);
		Kibble3.setColor(r, g, b);
		Kibble4.setColor(r, g, b);
		Kibble21.setColor(r, g, b);
		Kibble31.setColor(r, g, b);
		Kibble1.setColor(r, g, b);
		Kibble22.setColor(r, g, b);
		Kibble32.setColor(r, g, b);
		Kibble41.setColor(r, g, b);
		Kibble23.setColor(r, g, b);
		Kibble33.setColor(r, g, b);
		Kibble5.setColor(r, g, b);
		Kibble24.setColor(r, g, b);
		Kibble34.setColor(r, g, b);
		Kibble42.setColor(r, g, b);
		Kibble25.setColor(r, g, b);
		Kibble35.setColor(r, g, b);
		Kibble6.setColor(r, g, b);
		Kibble26.setColor(r, g, b);
		Kibble36.setColor(r, g, b);
		Kibble43.setColor(r, g, b);
		Kibble27.setColor(r, g, b);
		Kibble37.setColor(r, g, b);
		Kibble44.setColor(r, g, b);
		Kibble28.setColor(r, g, b);
		Kibble38.setColor(r, g, b);
		Kibble7.setColor(r, g, b);
		Kibble29.setColor(r, g, b);
		Kibble39.setColor(r, g, b);
		Kibble45.setColor(r, g, b);
		Kibble210.setColor(r, g, b);
		Kibble310.setColor(r, g, b);
		Kibble311.setColor(r, g, b);
		Kibble312.setColor(r, g, b);
		Kibble313.setColor(r, g, b);
		Kibble8.setColor(r, g, b);
		Kibble211.setColor(r, g, b);
		Kibble314.setColor(r, g, b);
		Kibble46.setColor(r, g, b);
		Kibble212.setColor(r, g, b);
		Kibble315.setColor(r, g, b);
		Kibble9.setColor(r, g, b);
		Kibble213.setColor(r, g, b);
		Kibble316.setColor(r, g, b);
		Kibble47.setColor(r, g, b);
		Kibble214.setColor(r, g, b);
		Kibble317.setColor(r, g, b);
		Kibble10.setColor(r, g, b);
		Kibble215.setColor(r, g, b);
		Kibble318.setColor(r, g, b);
		Kibble48.setColor(r, g, b);
		Kibble216.setColor(r, g, b);
		Kibble319.setColor(r, g, b);
		Kibble11.setColor(r, g, b);
		Kibble217.setColor(r, g, b);
		Kibble320.setColor(r, g, b);
		Kibble49.setColor(r, g, b);
		Kibble218.setColor(r, g, b);
		Kibble321.setColor(r, g, b);
		Kibble12.setColor(r, g, b);
		Kibble219.setColor(r, g, b);
		Kibble322.setColor(r, g, b);
		Kibble410.setColor(r, g, b);
		Kibble220.setColor(r, g, b);
		Kibble323.setColor(r, g, b);
		Kibble411.setColor(r, g, b);
		Kibble221.setColor(r, g, b);
		Kibble324.setColor(r, g, b);
		Kibble325.setColor(r, g, b);
		Kibble326.setColor(r, g, b);
	}

	public void renderFood(float scale)
	{
		Kibble.rotateAngleX = 0F;
		Kibble.rotateAngleY = 0F;
		Kibble.rotateAngleZ = 0F;
		Kibble.renderWithRotation(scale);

		Kibble2.rotateAngleX = -0.1574999F;
		Kibble2.rotateAngleY = -0.4672038F;
		Kibble2.rotateAngleZ = -0.3014539F;
		Kibble2.renderWithRotation(scale);

		Kibble3.rotateAngleX = 0.3970294F;
		Kibble3.rotateAngleY = 0.3625107F;
		Kibble3.rotateAngleZ = 0.775726F;
		Kibble3.renderWithRotation(scale);

		Kibble4.rotateAngleX = 0F;
		Kibble4.rotateAngleY = 2.41131F;
		Kibble4.rotateAngleZ = 0F;
		Kibble4.renderWithRotation(scale);

		Kibble21.rotateAngleX = -0.1574999F;
		Kibble21.rotateAngleY = 1.944106F;
		Kibble21.rotateAngleZ = -0.3014538F;
		Kibble21.renderWithRotation(scale);

		Kibble31.rotateAngleX = 0.3970294F;
		Kibble31.rotateAngleY = 2.773821F;
		Kibble31.rotateAngleZ = 0.775726F;
		Kibble31.renderWithRotation(scale);

		Kibble1.rotateAngleX = 0F;
		Kibble1.rotateAngleY = 1.489042F;
		Kibble1.rotateAngleZ = 0F;
		Kibble1.renderWithRotation(scale);

		Kibble22.rotateAngleX = -0.1574999F;
		Kibble22.rotateAngleY = 1.021838F;
		Kibble22.rotateAngleZ = -0.3014539F;
		Kibble22.renderWithRotation(scale);

		Kibble32.rotateAngleX = 0.3970294F;
		Kibble32.rotateAngleY = 1.851552F;
		Kibble32.rotateAngleZ = 0.7757261F;
		Kibble32.renderWithRotation(scale);

		Kibble41.rotateAngleX = 0F;
		Kibble41.rotateAngleY = -2.382834F;
		Kibble41.rotateAngleZ = 0F;
		Kibble41.renderWithRotation(scale);

		Kibble23.rotateAngleX = -0.1574999F;
		Kibble23.rotateAngleY = -2.850037F;
		Kibble23.rotateAngleZ = -0.3014538F;
		Kibble23.renderWithRotation(scale);

		Kibble33.rotateAngleX = 0.3970294F;
		Kibble33.rotateAngleY = -2.020323F;
		Kibble33.rotateAngleZ = 0.775726F;
		Kibble33.renderWithRotation(scale);

		Kibble5.rotateAngleX = 0.1310762F;
		Kibble5.rotateAngleY = -2.981984F;
		Kibble5.rotateAngleZ = -0.1431112F;
		Kibble5.renderWithRotation(scale);

		Kibble24.rotateAngleX = -0.101737F;
		Kibble24.rotateAngleY = 2.867677F;
		Kibble24.rotateAngleZ = -0.4886197F;
		Kibble24.renderWithRotation(scale);

		Kibble34.rotateAngleX = 0.5685233F;
		Kibble34.rotateAngleY = -2.657655F;
		Kibble34.rotateAngleZ = 0.6736612F;
		Kibble34.renderWithRotation(scale);

		Kibble42.rotateAngleX = -0.003045561F;
		Kibble42.rotateAngleY = -0.5615618F;
		Kibble42.rotateAngleZ = 0.1937396F;
		Kibble42.renderWithRotation(scale);

		Kibble25.rotateAngleX = -0.07101944F;
		Kibble25.rotateAngleY = -1.048444F;
		Kibble25.rotateAngleZ = -0.1268627F;
		Kibble25.renderWithRotation(scale);

		Kibble35.rotateAngleX = -5.963936F;
		Kibble35.rotateAngleY = -0.1320712F;
		Kibble35.rotateAngleZ = 0.9653189F;
		Kibble35.renderWithRotation(scale);

		Kibble6.rotateAngleX = 0.1521878F;
		Kibble6.rotateAngleY = -1.474344F;
		Kibble6.rotateAngleZ = 0.1203963F;
		Kibble6.renderWithRotation(scale);

		Kibble26.rotateAngleX = 0.03257764F;
		Kibble26.rotateAngleY = -1.953105F;
		Kibble26.rotateAngleZ = -0.2637101F;
		Kibble26.renderWithRotation(scale);

		Kibble36.rotateAngleX = 0.4908113F;
		Kibble36.rotateAngleY = -1.040031F;
		Kibble36.rotateAngleZ = 0.9636469F;
		Kibble36.renderWithRotation(scale);

		Kibble43.rotateAngleX = -0.1933369F;
		Kibble43.rotateAngleY = 0.9265222F;
		Kibble43.rotateAngleZ = 0.01292846F;
		Kibble43.renderWithRotation(scale);

		Kibble27.rotateAngleX = -0.3232937F;
		Kibble27.rotateAngleY = 0.4358495F;
		Kibble27.rotateAngleZ = -0.1980595F;
		Kibble27.renderWithRotation(scale);

		Kibble37.rotateAngleX = 0.2111879F;
		Kibble37.rotateAngleY = 1.272965F;
		Kibble37.rotateAngleZ = 0.7181429F;
		Kibble37.renderWithRotation(scale);

		Kibble44.rotateAngleX = 0.1715521F;
		Kibble44.rotateAngleY = -2.636588F;
		Kibble44.rotateAngleZ = -0.09052449F;
		Kibble44.renderWithRotation(scale);

		Kibble28.rotateAngleX = -0.04301889F;
		Kibble28.rotateAngleY = -3.080209F;
		Kibble28.rotateAngleZ = -0.4586606F;
		Kibble28.renderWithRotation(scale);

		Kibble38.rotateAngleX = 0.5892963F;
		Kibble38.rotateAngleY = -2.279304F;
		Kibble38.rotateAngleZ = 0.7483609F;
		Kibble38.renderWithRotation(scale);

		Kibble7.rotateAngleX = 0F;
		Kibble7.rotateAngleY = 0.4213091F;
		Kibble7.rotateAngleZ = 0F;
		Kibble7.renderWithRotation(scale);

		Kibble29.rotateAngleX = -0.1574999F;
		Kibble29.rotateAngleY = -0.04589463F;
		Kibble29.rotateAngleZ = -0.3014538F;
		Kibble29.renderWithRotation(scale);

		Kibble39.rotateAngleX = 0.3970294F;
		Kibble39.rotateAngleY = 0.7838198F;
		Kibble39.rotateAngleZ = 0.775726F;
		Kibble39.renderWithRotation(scale);

		Kibble45.rotateAngleX = 0F;
		Kibble45.rotateAngleY = 2.832619F;
		Kibble45.rotateAngleZ = 0F;
		Kibble45.renderWithRotation(scale);

		Kibble210.rotateAngleX = -0.1574999F;
		Kibble210.rotateAngleY = 2.365415F;
		Kibble210.rotateAngleZ = -0.3014538F;
		Kibble210.renderWithRotation(scale);

		Kibble310.rotateAngleX = 0.3970294F;
		Kibble310.rotateAngleY = -3.088056F;
		Kibble310.rotateAngleZ = 0.775726F;
		Kibble310.renderWithRotation(scale);

		Kibble311.rotateAngleX = 0.3970294F;
		Kibble311.rotateAngleY = -3.088056F;
		Kibble311.rotateAngleZ = 0.775726F;
		Kibble311.renderWithRotation(scale);

		Kibble312.rotateAngleX = 0.3970294F;
		Kibble312.rotateAngleY = -3.088056F;
		Kibble312.rotateAngleZ = 0.775726F;
		Kibble312.renderWithRotation(scale);

		Kibble313.rotateAngleX = 0.3970294F;
		Kibble313.rotateAngleY = -3.088056F;
		Kibble313.rotateAngleZ = 0.775726F;
		Kibble313.renderWithRotation(scale);

		Kibble8.rotateAngleX = 0F;
		Kibble8.rotateAngleY = 1.910351F;
		Kibble8.rotateAngleZ = 0F;
		Kibble8.renderWithRotation(scale);

		Kibble211.rotateAngleX = -0.1574999F;
		Kibble211.rotateAngleY = 1.443147F;
		Kibble211.rotateAngleZ = -0.3014538F;
		Kibble211.renderWithRotation(scale);

		Kibble314.rotateAngleX = 0.3970294F;
		Kibble314.rotateAngleY = 2.272861F;
		Kibble314.rotateAngleZ = 0.775726F;
		Kibble314.renderWithRotation(scale);

		Kibble46.rotateAngleX = 0F;
		Kibble46.rotateAngleY = -1.961524F;
		Kibble46.rotateAngleZ = 0F;
		Kibble46.renderWithRotation(scale);

		Kibble212.rotateAngleX = -0.1574999F;
		Kibble212.rotateAngleY = -2.428728F;
		Kibble212.rotateAngleZ = -0.3014538F;
		Kibble212.renderWithRotation(scale);

		Kibble315.rotateAngleX = 0.3970293F;
		Kibble315.rotateAngleY = -1.599014F;
		Kibble315.rotateAngleZ = 0.775726F;
		Kibble315.renderWithRotation(scale);

		Kibble9.rotateAngleX = 0.1310762F;
		Kibble9.rotateAngleY = -2.560675F;
		Kibble9.rotateAngleZ = -0.1431112F;
		Kibble9.renderWithRotation(scale);

		Kibble213.rotateAngleX = -0.101737F;
		Kibble213.rotateAngleY = -2.994199F;
		Kibble213.rotateAngleZ = -0.4886197F;
		Kibble213.renderWithRotation(scale);

		Kibble316.rotateAngleX = 0.5685233F;
		Kibble316.rotateAngleY = -2.236345F;
		Kibble316.rotateAngleZ = 0.6736611F;
		Kibble316.renderWithRotation(scale);

		Kibble47.rotateAngleX = -0.003045562F;
		Kibble47.rotateAngleY = -0.1402526F;
		Kibble47.rotateAngleZ = 0.1937396F;
		Kibble47.renderWithRotation(scale);

		Kibble214.rotateAngleX = -0.07101945F;
		Kibble214.rotateAngleY = -0.6271345F;
		Kibble214.rotateAngleZ = -0.1268627F;
		Kibble214.renderWithRotation(scale);

		Kibble317.rotateAngleX = 0.3192494F;
		Kibble317.rotateAngleY = 0.2892379F;
		Kibble317.rotateAngleZ = 0.9653188F;
		Kibble317.renderWithRotation(scale);

		Kibble10.rotateAngleX = 0.1521878F;
		Kibble10.rotateAngleY = -1.053035F;
		Kibble10.rotateAngleZ = 0.1203963F;
		Kibble10.renderWithRotation(scale);

		Kibble215.rotateAngleX = 0.03257762F;
		Kibble215.rotateAngleY = -1.531796F;
		Kibble215.rotateAngleZ = -0.2637101F;
		Kibble215.renderWithRotation(scale);

		Kibble318.rotateAngleX = 0.4908113F;
		Kibble318.rotateAngleY = -0.6187222F;
		Kibble318.rotateAngleZ = 0.9636468F;
		Kibble318.renderWithRotation(scale);

		Kibble48.rotateAngleX = 6.089849F;
		Kibble48.rotateAngleY = 1.347831F;
		Kibble48.rotateAngleZ = 0.01292845F;
		Kibble48.renderWithRotation(scale);

		Kibble216.rotateAngleX = -0.3232938F;
		Kibble216.rotateAngleY = 0.8571585F;
		Kibble216.rotateAngleZ = -0.1980595F;
		Kibble216.renderWithRotation(scale);

		Kibble319.rotateAngleX = 0.211188F;
		Kibble319.rotateAngleY = 1.694274F;
		Kibble319.rotateAngleZ = 0.718143F;
		Kibble319.renderWithRotation(scale);

		Kibble11.rotateAngleX = 0F;
		Kibble11.rotateAngleY = -2.296948F;
		Kibble11.rotateAngleZ = 0F;
		Kibble11.renderWithRotation(scale);

		Kibble217.rotateAngleX = -0.1574999F;
		Kibble217.rotateAngleY = -2.764152F;
		Kibble217.rotateAngleZ = -0.3014539F;
		Kibble217.renderWithRotation(scale);

		Kibble320.rotateAngleX = 0.3970294F;
		Kibble320.rotateAngleY = -1.934437F;
		Kibble320.rotateAngleZ = 0.7757261F;
		Kibble320.renderWithRotation(scale);

		Kibble49.rotateAngleX = -6.283185F;
		Kibble49.rotateAngleY = 0.1143622F;
		Kibble49.rotateAngleZ = 0F;
		Kibble49.renderWithRotation(scale);

		Kibble218.rotateAngleX = -0.1574999F;
		Kibble218.rotateAngleY = -0.3528416F;
		Kibble218.rotateAngleZ = -0.3014538F;
		Kibble218.renderWithRotation(scale);

		Kibble321.rotateAngleX = 0.3970294F;
		Kibble321.rotateAngleY = 0.4768728F;
		Kibble321.rotateAngleZ = 0.775726F;
		Kibble321.renderWithRotation(scale);

		Kibble12.rotateAngleX = 0F;
		Kibble12.rotateAngleY = -0.807906F;
		Kibble12.rotateAngleZ = 0F;
		Kibble12.renderWithRotation(scale);

		Kibble219.rotateAngleX = -0.1574999F;
		Kibble219.rotateAngleY = -1.27511F;
		Kibble219.rotateAngleZ = -0.3014539F;
		Kibble219.renderWithRotation(scale);

		Kibble322.rotateAngleX = 0.3970294F;
		Kibble322.rotateAngleY = -0.4453953F;
		Kibble322.rotateAngleZ = 0.775726F;
		Kibble322.renderWithRotation(scale);

		Kibble410.rotateAngleX = 0F;
		Kibble410.rotateAngleY = 1.603404F;
		Kibble410.rotateAngleZ = 0F;
		Kibble410.renderWithRotation(scale);

		Kibble220.rotateAngleX = -0.1574999F;
		Kibble220.rotateAngleY = 1.1362F;
		Kibble220.rotateAngleZ = -0.3014538F;
		Kibble220.renderWithRotation(scale);

		Kibble323.rotateAngleX = 0.3970294F;
		Kibble323.rotateAngleY = 1.965915F;
		Kibble323.rotateAngleZ = 0.775726F;
		Kibble323.renderWithRotation(scale);

		Kibble411.rotateAngleX = -6.283185F;
		Kibble411.rotateAngleY = -1.95317F;
		Kibble411.rotateAngleZ = -1.241958E-08F;
		Kibble411.renderWithRotation(scale);

		Kibble221.rotateAngleX = -0.1574999F;
		Kibble221.rotateAngleY = -2.420374F;
		Kibble221.rotateAngleZ = -0.3014539F;
		Kibble221.renderWithRotation(scale);

		Kibble324.rotateAngleX = 0.3970294F;
		Kibble324.rotateAngleY = -1.590659F;
		Kibble324.rotateAngleZ = 0.775726F;
		Kibble324.renderWithRotation(scale);

		Kibble325.rotateAngleX = 0.3970294F;
		Kibble325.rotateAngleY = -1.934437F;
		Kibble325.rotateAngleZ = 0.7757261F;
		Kibble325.renderWithRotation(scale);

		Kibble326.rotateAngleX = 0.3970294F;
		Kibble326.rotateAngleY = -1.934437F;
		Kibble326.rotateAngleZ = 0.7757261F;
		Kibble326.renderWithRotation(scale);

	}

	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		Root_Node.rotateAngleX = 0F;
		Root_Node.rotateAngleY = 0F;
		Root_Node.rotateAngleZ = 0F;
		Root_Node.renderWithRotation(par7);

		Side1.rotateAngleX = 0F;
		Side1.rotateAngleY = -1.570796F;
		Side1.rotateAngleZ = 0F;
		Side1.renderWithRotation(par7);

		Side2.rotateAngleX = 0F;
		Side2.rotateAngleY = -2.356194F;
		Side2.rotateAngleZ = 0F;
		Side2.renderWithRotation(par7);

		Side3.rotateAngleX = 0F;
		Side3.rotateAngleY = -3.141593F;
		Side3.rotateAngleZ = 0F;
		Side3.renderWithRotation(par7);

		Side4.rotateAngleX = 0F;
		Side4.rotateAngleY = 2.356194F;
		Side4.rotateAngleZ = 0F;
		Side4.renderWithRotation(par7);

		Side5.rotateAngleX = -6.283185F;
		Side5.rotateAngleY = 1.570796F;
		Side5.rotateAngleZ = 0F;
		Side5.renderWithRotation(par7);

		Side6.rotateAngleX = 0F;
		Side6.rotateAngleY = 0.7853978F;
		Side6.rotateAngleZ = 0F;
		Side6.renderWithRotation(par7);

		Side7.rotateAngleX = 0F;
		Side7.rotateAngleY = -2.753375E-07F;
		Side7.rotateAngleZ = 0F;
		Side7.renderWithRotation(par7);

		Side8.rotateAngleX = 0F;
		Side8.rotateAngleY = -0.7853984F;
		Side8.rotateAngleZ = 0F;
		Side8.renderWithRotation(par7);

		Bottom_.rotateAngleX = 0F;
		Bottom_.rotateAngleY = 0F;
		Bottom_.rotateAngleZ = 0F;
		Bottom_.renderWithRotation(par7);

		Bottom_2.rotateAngleX = 0F;
		Bottom_2.rotateAngleY = -1.570796F;
		Bottom_2.rotateAngleZ = 0F;
		Bottom_2.renderWithRotation(par7);

		Bottom_3.rotateAngleX = 0F;
		Bottom_3.rotateAngleY = -0.7853982F;
		Bottom_3.rotateAngleZ = 0F;
		Bottom_3.renderWithRotation(par7);

		Bottom_4.rotateAngleX = 0F;
		Bottom_4.rotateAngleY = 0.7853982F;
		Bottom_4.rotateAngleZ = 0F;
		Bottom_4.renderWithRotation(par7);
	}

}
