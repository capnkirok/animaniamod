package com.animania.addons.extra.client.model.tileentity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelHamsterWheel extends ModelBase
{
	ModelRenderer Base1;
    ModelRenderer Base2;
    ModelRenderer axel1;
    ModelRenderer Base3;
    ModelRenderer Base4;
    ModelRenderer axel12;
    ModelRenderer Base5;
    ModelRenderer Wheel1;
    ModelRenderer Wheel2;
    ModelRenderer Wheel3;
    ModelRenderer Wheel4;
    ModelRenderer Wheel5;
    ModelRenderer Wheel6;
    ModelRenderer Wheel7;
    ModelRenderer Wheel8;
    ModelRenderer Stick;
    ModelRenderer Stick2;
    ModelRenderer axel1b;
    
    public ModelHamsterWheel() {
        this(0.0f);
    }

    public ModelHamsterWheel(float par1) {
    	Base1 = new ModelRenderer( this, 12, 1 );
        Base1.setTextureSize( 64, 32 );
        Base1.addBox( -1F, 0F, -0.5F, 1, 13, 1);
        Base1.setRotationPoint( 6F, 24F, 6F );
       
        Base2 = new ModelRenderer( this, 12, 1 );
        Base2.setTextureSize( 64, 32 );
        Base2.addBox( -0.5F, -0.5F, -0.5F, 1, 13, 1);
        Base2.setRotationPoint( -2.99795E-07F, 13F, 6F );
        
        axel1 = new ModelRenderer( this, 12, 1 );
        axel1.setTextureSize( 64, 32 );
        axel1.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
        axel1.setRotationPoint( -2.99795E-07F, 13F, 5F );
        
        Base3 = new ModelRenderer( this, 12, 1 );
        Base3.setTextureSize( 64, 32 );
        Base3.addBox( -1F, 0F, -0.5F, 1, 13, 1);
        Base3.setRotationPoint( 5.999999F, 24F, -6F );
        
        Base4 = new ModelRenderer( this, 12, 1 );
        Base4.setTextureSize( 64, 32 );
        Base4.addBox( -0.5F, -0.5F, -0.5F, 1, 13, 1);
        Base4.setRotationPoint( -3.023637E-06F, 13F, -6F );
        
        axel12 = new ModelRenderer( this, 12, 1 );
        axel12.setTextureSize( 64, 32 );
        axel12.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
        axel12.setRotationPoint( -4.87138E-06F, 13F, -5F );
        
        Base5 = new ModelRenderer( this, 4, 13 );
        Base5.setTextureSize( 64, 32 );
        Base5.addBox( -0.5F, -0.5F, -6F, 1, 1, 12);
        Base5.setRotationPoint( -6.5F, 23.5F, 0F );
        
        Wheel1 = new ModelRenderer( this, 19, 2 );
        Wheel1.setTextureSize( 64, 32 );
        Wheel1.addBox( -3F, -0.5F, -5F, 6, 1, 10);
        Wheel1.setRotationPoint( -3.234292E-06F, 6.500002F, 4.768372E-07F );
        
        Wheel2 = new ModelRenderer( this, 19, 2 );
        Wheel2.setTextureSize( 64, 32 );
        Wheel2.addBox( 0F, 0F, -5F, 6, 1, 10);
        Wheel2.setRotationPoint( 2.999997F, 6F, 4.768372E-07F );
        
        Wheel3 = new ModelRenderer( this, 19, 2 );
        Wheel3.setTextureSize( 64, 32 );
        Wheel3.addBox( 0F, 0F, -5F, 6, 1, 10);
        Wheel3.setRotationPoint( 7.242638F, 10.24264F, 4.768372E-07F );
        
        Wheel4 = new ModelRenderer( this, 19, 2 );
        Wheel4.setTextureSize( 64, 32 );
        Wheel4.addBox( 0F, 0F, -5F, 6, 1, 10);
        Wheel4.setRotationPoint( 7.242639F, 16.24264F, 4.768372E-07F );
        
        Wheel5 = new ModelRenderer( this, 19, 2 );
        Wheel5.setTextureSize( 64, 32 );
        Wheel5.addBox( 0F, 0F, -5F, 6, 1, 10);
        Wheel5.setRotationPoint( 2.999999F, 20.48528F, 4.768372E-07F );
        
        Wheel6 = new ModelRenderer( this, 19, 2 );
        Wheel6.setTextureSize( 64, 32 );
        Wheel6.addBox( 0F, 0F, -5F, 6, 1, 10);
        Wheel6.setRotationPoint( -3.000001F, 20.48528F, 1.218644E-06F );
        
        Wheel7 = new ModelRenderer( this, 19, 2 );
        Wheel7.setTextureSize( 64, 32 );
        Wheel7.addBox( 0F, 0F, -5F, 6, 1, 10);
        Wheel7.setRotationPoint( -7.242642F, 16.24264F, 2.267717E-06F );
        
        Wheel8 = new ModelRenderer( this, 19, 2 );
        Wheel8.setTextureSize( 64, 32 );
        Wheel8.addBox( 0F, 0F, -5F, 6, 1, 10);
        Wheel8.setRotationPoint( -7.242642F, 10.24264F, 3.009524E-06F );
        
        Stick = new ModelRenderer( this, 13, 2 );
        Stick.setTextureSize( 64, 32 );
        Stick.addBox( -0.5F, -6.5F, 0F, 1, 13, 0);
        Stick.setRotationPoint( -1.748819E-06F, 13F, 4.75F );
        
        Stick2 = new ModelRenderer( this, 13, 2 );
        Stick2.setTextureSize( 64, 32 );
        Stick2.addBox( -0.5F, -6.5F, 0F, 1, 13, 0);
        Stick2.setRotationPoint( -2.454983E-06F, 13F, -4.75F );
       
        axel1b = new ModelRenderer( this, 0, 0 );
        axel1b.setTextureSize( 64, 32 );
        axel1b.addBox( -0.5F, -0.5F, -1F, 1, 1, 2);
        axel1b.setRotationPoint( -2.99795E-07F, 13F, 7F );

    }

    @Override
    public void render(Entity entityIn, float nestType, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {

    	
       this.Base1.render(scale);
       this.Base2.render(scale);
       this.axel1.render(scale);
       this.Base3.render(scale);
       this.Base4.render(scale);
       this.axel12.render(scale);
       this.Wheel1.render(scale);
       this.Wheel2.render(scale);
       this.Wheel3.render(scale);
       this.Wheel4.render(scale);
       this.Wheel5.render(scale);
       this.Wheel6.render(scale);
       this.Wheel7.render(scale);
       this.Wheel8.render(scale);
       this.Stick.render(scale);
       this.Stick2.render(scale);
       this.axel1b.render(scale);
       
        
        this.setRotationAngles(0.0F, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
      
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {

    	Base1.rotateAngleZ = 1.570796F;
        Base2.rotateAngleZ = -0.5235988F;
        axel1.rotateAngleZ = -0.5235988F;
        Base3.rotateAngleZ = 1.570796F;
        Base4.rotateAngleZ = -0.523599F;
        axel12.rotateAngleZ = -0.523599F;
        Wheel1.rotateAngleZ = -1.685874E-07F;
        Wheel2.rotateAngleZ = 0.7853982F;
        Wheel3.rotateAngleZ = 1.570797F;
        Wheel4.rotateAngleZ = 2.356194F;
        Wheel5.rotateAngleX = -1.236345E-07F;
        Wheel5.rotateAngleY = 1.236345E-07F;
        Wheel5.rotateAngleZ = -3.141593F;
        Wheel6.rotateAngleX = -1.236345E-07F;
        Wheel6.rotateAngleY = 1.236345E-07F;
        Wheel6.rotateAngleZ = -2.356194F;
        Wheel7.rotateAngleX = -1.236345E-07F;
        Wheel7.rotateAngleY = 1.236344E-07F;
        Wheel7.rotateAngleZ = -1.570796F;
        Wheel8.rotateAngleX = -1.236345E-07F;
        Wheel8.rotateAngleY = 1.236344E-07F;
        Wheel8.rotateAngleZ = -0.7853976F;
        Stick.rotateAngleZ = -1.685874E-07F;
        Stick2.rotateAngleZ = -1.685874E-07F;
        axel1b.rotateAngleZ = -0.5235988F;

    }

}
