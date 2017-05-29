package com.animania.client.render.horses;

import org.lwjgl.opengl.GL11;

import com.animania.client.models.ModelDraftHorseStallion;
import com.animania.client.render.amphibians.RenderDartFrogs.Factory;
import com.animania.client.render.pigs.RenderHogDuroc;
import com.animania.common.entities.horses.EntityStallionDraftHorse;
import com.animania.common.entities.pigs.EntityHogDuroc;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@SideOnly(Side.CLIENT)
public class RenderStallionDraftHorse<T extends EntityStallionDraftHorse> extends RenderLiving<T>
{
	public static final Factory             FACTORY        = new Factory();
	private static final ResourceLocation horseTextures = new ResourceLocation("animania:textures/entity/horses/draft_horse_grey.png");

	public RenderStallionDraftHorse(RenderManager rm)
	{
		super(rm, new ModelDraftHorseStallion(), 0.8F);
	}

	protected void preRenderScale(EntityStallionDraftHorse entity, float f)
	{
		GL11.glScalef(0.85F, 0.85F, 0.85F); 
	}

	@Override
	protected void preRenderCallback(EntityStallionDraftHorse entityliving, float f)
	{
		preRenderScale((EntityStallionDraftHorse)entityliving, f);
	}

	protected ResourceLocation getHorseTextures(EntityStallionDraftHorse par1Entity)
	{
		return horseTextures;
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityStallionDraftHorse entity) {

		int blinkTimer = entity.blinkTimer;

		if (blinkTimer < 5 && blinkTimer >= 0) {
			return entity.getResourceLocationBlink();
		} else {
			return entity.getResourceLocation();
		}


	}
	
	static class Factory<T extends EntityStallionDraftHorse> implements IRenderFactory<T>
    {
        @Override
        public Render<? super T> createRenderFor(RenderManager manager) {
            return new RenderStallionDraftHorse(manager);
        }
    }
}
