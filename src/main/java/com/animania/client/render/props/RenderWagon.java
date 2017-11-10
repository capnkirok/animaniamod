package com.animania.client.render.props;

import com.animania.Animania;
import com.animania.common.entities.props.EntityWagon;
import com.leviathanstudio.craftstudio.client.model.ModelCraftStudio;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderWagon extends Render<EntityWagon>
{
    public static final Factory FACTORY = new Factory();
    private ModelCraftStudio modelWagon = new ModelCraftStudio(Animania.MODID, "model_wagon", 256, 128);
    
    public RenderWagon(RenderManager manager) {
        super(manager);
        this.shadowSize = 1.0F;
    }
    
    public void doRender(EntityWagon entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        GlStateManager.pushMatrix();
        this.setupTranslation(x, y, z);
        this.setupRotation(entity, entityYaw, partialTicks);
        this.bindEntityTexture(entity);

        this.modelWagon.render(entity, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
    
    public void setupRotation(EntityWagon p_188311_1_, float p_188311_2_, float p_188311_3_)
    {
        GlStateManager.rotate(180.0F - p_188311_2_, 0.0F, 1.0F, 0.0F);
        
        float f = 0;
        float f1 = 0;
        
        if (f > 0.0F)
        {
            GlStateManager.rotate(MathHelper.sin(f) * f * f1 / 10.0F * 0, 1.0F, 0.0F, 0.0F);
        }

        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
    }

    public void setupTranslation(double p_188309_1_, double p_188309_3_, double p_188309_5_)
    {
        GlStateManager.translate((float)p_188309_1_, (float)p_188309_3_ + 1.6F, (float)p_188309_5_);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityWagon entity) {
        return new ResourceLocation(Animania.MODID, "textures/entity/props/wagon.png");
    }

    public static class Factory<T extends EntityWagon> implements IRenderFactory<T>
    {
        @Override
        public Render<? super T> createRenderFor(RenderManager manager) {
            return new RenderWagon(manager);
        }
    }
	
}