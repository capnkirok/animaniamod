package com.animania.client.render.horses;

import org.lwjgl.opengl.GL11;

import com.animania.client.models.ModelDraftHorseMare;
import com.animania.client.render.amphibians.RenderDartFrogs.Factory;
import com.animania.common.entities.horses.EntityMareDraftHorse;
import com.animania.common.entities.horses.EntityStallionDraftHorse;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@SideOnly(Side.CLIENT)
public class RenderMareDraftHorse<T extends EntityMareDraftHorse> extends RenderLiving<T>
{

	public static final Factory             FACTORY        = new Factory();
	private static final ResourceLocation horseTextures = new ResourceLocation("animania:textures/entity/horses/draft_horse_grey.png");

	public RenderMareDraftHorse(RenderManager rm)
	{
		super(rm, new ModelDraftHorseMare(), 0.8F);
	}

	protected void preRenderScale(EntityMareDraftHorse entity, float f)
	{
		GL11.glScalef(0.72F, 0.72F, 0.72F); 
	}

	@Override
	protected void preRenderCallback(EntityMareDraftHorse entityliving, float f)
	{
		preRenderScale((EntityMareDraftHorse)entityliving, f);
	}

	protected ResourceLocation getHorseTextures(EntityMareDraftHorse par1Entity)
	{
		return horseTextures;
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityMareDraftHorse entity) {

		int blinkTimer = entity.blinkTimer;

		if (blinkTimer < 5 && blinkTimer >= 0) {
			return entity.getResourceLocationBlink();
		} else {
			return entity.getResourceLocation();
		}


	}
	
	static class Factory<T extends EntityMareDraftHorse> implements IRenderFactory<T>
    {
        @Override
        public Render<? super T> createRenderFor(RenderManager manager) {
            return new RenderMareDraftHorse(manager);
        }
    }
}
