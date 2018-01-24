package com.animania.client.render.cows;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.animania.client.models.ModelBull;
import com.animania.common.entities.cows.EntityBullHolstein;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBullHolstein<T extends EntityBullHolstein> extends RenderLiving<T>
{
    public static final Factory           FACTORY          = new Factory();

    private static final ResourceLocation cowTextures      = new ResourceLocation("animania:textures/entity/cows/bull_holstein.png");
    private static final ResourceLocation cowTexturesBlink = new ResourceLocation("animania:textures/entity/cows/bull_holstein_blink.png");
    private static final ResourceLocation purpTextures     	= new ResourceLocation("animania:textures/entity/cows/bull_purplicious.png");
	private static final ResourceLocation purpTexturesBlink = new ResourceLocation("animania:textures/entity/cows/bull_purplicious.png");
    Random                                rand             = new Random();

    public RenderBullHolstein(RenderManager rm) {
        super(rm, new ModelBull(), 0.5F);
    }

    protected void preRenderScale(T entity, float f) {
        GL11.glScalef(1.3F, 1.3F, 1.3F); // TODO make dynamic
    }

    @Override
    protected void preRenderCallback(T entityliving, float f) {
        this.preRenderScale(entityliving, f);
    }

    protected ResourceLocation getCowTextures(T par1EntityCow) {

		if (par1EntityCow.getCustomNameTag().equals("Purp")) {
			return RenderBullHolstein.purpTextures; 
		} else {
			return RenderBullHolstein.cowTextures;
		}
	}

	protected ResourceLocation getCowTexturesBlink(T par1EntityCow) {
		if (par1EntityCow.getCustomNameTag().equals("Purp")) {
			return RenderBullHolstein.purpTexturesBlink; 
		} else {
			return RenderBullHolstein.cowTexturesBlink;
		}
	}

    @Override
    protected ResourceLocation getEntityTexture(T entity) {
        int blinkTimer = entity.blinkTimer;

        if (blinkTimer < 7 && blinkTimer >= 0)
            return this.getCowTexturesBlink(entity);
        else
            return this.getCowTextures(entity);
    }

    static class Factory<T extends EntityBullHolstein> implements IRenderFactory<T>
    {
        @Override
        public Render<? super T> createRenderFor(RenderManager manager) {
            return new RenderBullHolstein(manager);
        }
    }
}
