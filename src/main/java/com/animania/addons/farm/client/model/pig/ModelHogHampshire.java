package com.animania.addons.farm.client.model.pig;

import com.animania.addons.farm.common.entity.pigs.PigHampshire.EntityHogHampshire;
import com.animania.config.AnimaniaConfig;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Dist.CLIENT)
public class ModelHogHampshire extends ModelBase
{
    private float headRotationAngleX;
    ModelRenderer Leg1;
    ModelRenderer Leg2;
    ModelRenderer Leg3;
    ModelRenderer Leg4;
    ModelRenderer Body;
    ModelRenderer Snout;
    ModelRenderer Head;
    ModelRenderer Ear1;
    ModelRenderer Ear1a;
    ModelRenderer Ear1b;
    ModelRenderer Ear2;
    ModelRenderer Ear2a;
    ModelRenderer Ear2b;
    ModelRenderer Tail1;
    ModelRenderer Tail1a;
    ModelRenderer Tail1b;
    ModelRenderer Tail1c;
    ModelRenderer Tail1d;
    ModelRenderer Nipple1;
    ModelRenderer Nipple2;
    ModelRenderer Nipple3;
    ModelRenderer Nipple4;
    ModelRenderer Nipple5;
    ModelRenderer Nipple6;
    ModelRenderer BlockA;
    ModelRenderer BlockB;

    public ModelHogHampshire() {
        this(0.0f);
    }

    public ModelHogHampshire(float par1) {
        this.Leg1 = new ModelRenderer(this, 0, 36);
        this.Leg1.setTextureSize(64, 64);
        this.Leg1.addBox(-2F, 0F, -2F, 4, 6, 4);
        this.Leg1.setRotationPoint(3F, 18F, -5F);
        this.Leg2 = new ModelRenderer(this, 0, 36);
        this.Leg2.setTextureSize(64, 64);
        this.Leg2.addBox(-2F, 0F, -2F, 4, 6, 4);
        this.Leg2.setRotationPoint(-3F, 18F, -5F);
        this.Leg3 = new ModelRenderer(this, 0, 16);
        this.Leg3.setTextureSize(64, 64);
        this.Leg3.addBox(-2F, 0F, -2F, 4, 6, 4);
        this.Leg3.setRotationPoint(3F, 18F, 7F);
        this.Leg4 = new ModelRenderer(this, 0, 16);
        this.Leg4.setTextureSize(64, 64);
        this.Leg4.addBox(-2F, 0F, -2F, 4, 6, 4);
        this.Leg4.setRotationPoint(-3F, 18F, 7F);

        this.Body = new ModelRenderer(this, 28, 8);
        this.Body.setTextureSize(64, 64);
        this.Body.addBox(-5F, -10F, -7F, 10, 16, 8);
        this.Body.setRotationPoint(0F, 11F, 2F);

        this.Nipple1 = new ModelRenderer(this, 17, 22);
        this.Nipple1.setTextureSize(64, 64);
        this.Nipple1.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
        this.Nipple1.setRotationPoint(-1.5F, 18.25F, 3.5F);
        this.Nipple2 = new ModelRenderer(this, 17, 22);
        this.Nipple2.setTextureSize(64, 64);
        this.Nipple2.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
        this.Nipple2.setRotationPoint(1.5F, 18.25F, 3.5F);
        this.Nipple3 = new ModelRenderer(this, 17, 24);
        this.Nipple3.setTextureSize(64, 64);
        this.Nipple3.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
        this.Nipple3.setRotationPoint(-1.5F, 18.25F, 0.4999999F);
        this.Nipple4 = new ModelRenderer(this, 17, 24);
        this.Nipple4.setTextureSize(64, 64);
        this.Nipple4.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
        this.Nipple4.setRotationPoint(1.5F, 18.25F, 0.4999997F);
        this.Nipple5 = new ModelRenderer(this, 17, 26);
        this.Nipple5.setTextureSize(64, 64);
        this.Nipple5.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
        this.Nipple5.setRotationPoint(-1.5F, 18.25F, -2.5F);
        this.Nipple6 = new ModelRenderer(this, 17, 26);
        this.Nipple6.setTextureSize(64, 64);
        this.Nipple6.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
        this.Nipple6.setRotationPoint(1.5F, 18.25F, -2.5F);

        this.BlockA = new ModelRenderer(this, 38, 0);
        this.BlockA.setTextureSize(64, 64);
        this.BlockA.addBox(-1F, -1F, -1.5F, 2, 2, 3);
        this.BlockA.setRotationPoint(0F, 18F, 3F);
        this.BlockB = new ModelRenderer(this, 44, 1);
        this.BlockB.setTextureSize(64, 64);
        this.BlockB.addBox(-1F, -1F, -1F, 2, 2, 2);
        this.BlockB.setRotationPoint(0F, 19F, 5F);

        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setTextureSize(64, 64);
        this.Head.addBox(-4F, -4F, -8F, 8, 8, 8);
        this.Head.setRotationPoint(0F, 11F, -6F); // was -6F

        this.Snout = new ModelRenderer(this, 16, 16);
        this.Snout.setTextureSize(64, 64);
        this.Snout.addBox(-2F, 0F, -9F, 4, 3, 1);
        // Snout.setRotationPoint( 0F, 12F, -6F );
        this.Snout.setRotationPoint(0F, 0F, 0F);

        this.Ear1 = new ModelRenderer(this, 3, 28);
        this.Ear1.setTextureSize(64, 64);
        this.Ear1.addBox(-1.5F, -2.5F, -0.5F, 3, 3, 1);
        // Ear1.setRotationPoint( 3F, 9.25F, -11F );
        this.Ear1.setRotationPoint(3F, -2.75F, -5F);

        this.Ear1a = new ModelRenderer(this, 2, 27);
        this.Ear1a.setTextureSize(64, 64);
        this.Ear1a.addBox(-1.5F, -0.5F, -1F, 3, 1, 2);
        // Ear1a.setRotationPoint( 4.850319F, 6.900764F, -11.55445F );
        this.Ear1a.setRotationPoint(4.850319F, 6.900764F - 12F, -11.55445F + 6F);

        this.Ear1b = new ModelRenderer(this, 3, 28);
        this.Ear1b.setTextureSize(64, 64);
        this.Ear1b.addBox(-1F, -0.5F, -0.5F, 2, 1, 1);
        // Ear1b.setRotationPoint( 4.013302F, 7.511297F, -11.97475F );
        this.Ear1b.setRotationPoint(4.013302F, 7.511297F - 12f, -11.97475F + 6F);

        this.Ear2 = new ModelRenderer(this, 3, 28);
        this.Ear2.setTextureSize(64, 64);
        this.Ear2.addBox(-1.5F, -2.5F, -0.5F, 3, 3, 1);
        // Ear2.setRotationPoint( -3F, 9.25F, -11F );
        this.Ear2.setRotationPoint(-3F, 9.25F - 12F, -11F + 6F);

        this.Ear2a = new ModelRenderer(this, 2, 27);
        this.Ear2a.setTextureSize(64, 64);
        this.Ear2a.addBox(-1.5F, -0.5F, -1F, 3, 1, 2);
        // Ear2a.setRotationPoint( -4.850319F, 6.900764F, -11.55445F );
        this.Ear2a.setRotationPoint(-4.850319F, 6.900764F - 12F, -11.55445F + 6F);

        this.Ear2b = new ModelRenderer(this, 3, 28);
        this.Ear2b.setTextureSize(64, 64);
        this.Ear2b.addBox(-1F, -0.5F, -0.5F, 2, 1, 1);
        // Ear2b.setRotationPoint( -4.013302F, 7.511297F, -11.97475F );
        this.Ear2b.setRotationPoint(-4.013302F, 7.511297F - 12F, -11.97475F + 6F);

        this.Tail1 = new ModelRenderer(this, 26, 0);
        this.Tail1.setTextureSize(64, 64);
        this.Tail1.addBox(-2.5F, -0.5F, -0.5F, 3, 1, 1);
        this.Tail1.setRotationPoint(1.5F, 11.5F, 8.499999F);

        this.Tail1a = new ModelRenderer(this, 25, 0);
        this.Tail1a.setTextureSize(64, 64);
        this.Tail1a.addBox(-0.5F, -0.5F, -2.5F, 1, 1, 3);
        this.Tail1a.setRotationPoint(-1.408867F - 1.5F, 12.49008F - 9.5F, 9.247147F - 9.299999F);

        this.Tail1b = new ModelRenderer(this, 26, 0);
        this.Tail1b.setTextureSize(64, 64);
        this.Tail1b.addBox(0F, -0.5F, -0.5F, 2, 1, 1);
        this.Tail1b.setRotationPoint(-0.8336565F - 1.5F, 15.46033F - 11.5F, 9.558231F - 9.299999F);

        this.Tail1c = new ModelRenderer(this, 26, 0);
        this.Tail1c.setTextureSize(64, 64);
        this.Tail1c.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
        this.Tail1c.setRotationPoint(1.635806F - 1.5F, 14.38999F - 11.5F, 9.479872F - 9.299999F);

        this.Tail1d = new ModelRenderer(this, 26, 0);
        this.Tail1d.setTextureSize(64, 64);
        this.Tail1d.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
        this.Tail1d.setRotationPoint(0.6080551F - 1.5F, 13.43201F - 11.5F, 9.318629F - 9.299999F);

        this.Head.addChild(this.Snout);
        this.Head.addChild(this.Ear1);
        this.Head.addChild(this.Ear1a);
        this.Head.addChild(this.Ear1b);
        this.Head.addChild(this.Ear2);
        this.Head.addChild(this.Ear2a);
        this.Head.addChild(this.Ear2b);

        this.Tail1.addChild(this.Tail1a);
        this.Tail1.addChild(this.Tail1b);
        this.Tail1.addChild(this.Tail1c);
        this.Tail1.addChild(this.Tail1d);

    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {

        this.Leg1.render(scale);
        this.Leg2.render(scale);
        this.Leg3.render(scale);
        this.Leg4.render(scale);
        this.Body.render(scale);

        this.Nipple1.render(scale);
        this.Nipple2.render(scale);
        this.Nipple3.render(scale);
        this.Nipple4.render(scale);
        this.Nipple5.render(scale);
        this.Nipple6.render(scale);

        if (AnimaniaConfig.gameRules.showParts) {
            this.BlockA.render(scale);
            this.BlockB.render(scale);
        }

        this.Head.render(scale);
        this.Tail1.render(scale);

        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

    }

    @Override
    public void setLivingAnimations(LivingEntity LivingEntityIn, float p_78086_2_, float p_78086_3_, float partialTickTime) {

        super.setLivingAnimations(LivingEntityIn, p_78086_2_, p_78086_3_, partialTickTime);

        if (LivingEntityIn instanceof EntityHogHampshire) {
        	EntityHogHampshire entityHogHampshire = (EntityHogHampshire) LivingEntityIn;
            this.Head.rotationPointY = 11.0F + entityHogHampshire.getHeadAnchorPointY(partialTickTime) * 5.5F; // number
                                                                                                                                        // should
                                                                                                                                        // match
                                                                                                                                        // model
                                                                                                                                        // Y
                                                                                                                                        // point
            this.headRotationAngleX = entityHogHampshire.getHeadAngleX(partialTickTime);
        }

    }

    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {

        this.Ear1.rotateAngleX = -0.2617994F;
        this.Ear1.rotateAngleY = 0.3490658F;
        this.Ear1.rotateAngleZ = 0.6981317F;
        this.Ear1a.rotateAngleX = -0.2617994F;
        this.Ear1a.rotateAngleY = 0.3490658F;
        this.Ear1a.rotateAngleZ = 0.6981317F;
        this.Ear1b.rotateAngleX = -0.2617994F;
        this.Ear1b.rotateAngleY = 0.3490658F;
        this.Ear1b.rotateAngleZ = 0.6981317F;
        this.Ear2.rotateAngleX = -0.2617994F;
        this.Ear2.rotateAngleY = -0.3490658F;
        this.Ear2.rotateAngleZ = -0.6981317F;
        this.Ear2a.rotateAngleX = -0.2617994F;
        this.Ear2a.rotateAngleY = -0.3490658F;
        this.Ear2a.rotateAngleZ = -0.6981317F;
        this.Ear2b.rotateAngleX = -0.2617994F;
        this.Ear2b.rotateAngleY = -0.3490658F;
        this.Ear2b.rotateAngleZ = -0.6981317F;
        this.Tail1.rotateAngleX = 0.1409582F;
        this.Tail1.rotateAngleY = 0.2046205F;
        this.Tail1.rotateAngleZ = 1.882951E-10F;
        this.Tail1a.rotateAngleX = 1.429837F;
        this.Tail1a.rotateAngleY = -2.936972F;
        this.Tail1a.rotateAngleZ = -3.141593F;

        this.Nipple1.rotateAngleX = 1.570796F;
        this.Nipple2.rotateAngleX = 1.570796F;
        this.Nipple3.rotateAngleX = 1.570796F;
        this.Nipple4.rotateAngleX = 1.570796F;
        this.Nipple5.rotateAngleX = 1.570796F;
        this.Nipple6.rotateAngleX = 1.570796F;

        if (AnimaniaConfig.gameRules.showParts) {
            this.BlockA.rotateAngleX = 0.2617994F;
            this.BlockB.rotateAngleX = 0F;
        }

        float f6 = 180F / (float) Math.PI;
        this.Head.rotateAngleX = par5 / (180F / (float) Math.PI);
        this.Head.rotateAngleY = par4 / (180F / (float) Math.PI);
        this.Head.rotateAngleX = this.headRotationAngleX;
        this.Body.rotateAngleX = (float) Math.PI / 2F;

        this.Leg1.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
        this.Leg2.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
        this.Leg3.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
        this.Leg4.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
    }

}
