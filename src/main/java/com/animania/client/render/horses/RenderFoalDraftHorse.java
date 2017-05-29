package com.animania.client.render.horses;

import org.lwjgl.opengl.GL11;

import com.animania.client.models.ModelDraftHorseFoal;
import com.animania.common.entities.horses.EntityFoalDraftHorse;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@SideOnly(Side.CLIENT)
public class RenderFoalDraftHorse<T extends EntityFoalDraftHorse> extends RenderLiving<T>
{

	public static final Factory             FACTORY        = new Factory();
	private static final ResourceLocation horseTextures = new ResourceLocation("animania:textures/entity/horses/draft_horse_grey.png");

	public RenderFoalDraftHorse(RenderManager rm)
	{
		super(rm, new ModelDraftHorseFoal(), 0.5F);
	}

	protected void preRenderScale(EntityFoalDraftHorse entity, float f)
	{

		float age = entity.getEntityAge();

		GL11.glScalef(0.4F + age, 0.4F + age, 0.4F + age); 


	}

	@Override
	protected void preRenderCallback(EntityFoalDraftHorse entityliving, float f)
	{
		preRenderScale((EntityFoalDraftHorse)entityliving, f);
	}

	protected ResourceLocation getHorseTextures(EntityFoalDraftHorse par1Entity)
	{
		return horseTextures;
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityFoalDraftHorse entity) {

		int blinkTimer = entity.blinkTimer;

		if (blinkTimer < 5 && blinkTimer >= 0) {
			return entity.getResourceLocationBlink();
		} else {
			return entity.getResourceLocation();
		}


	}
	
	static class Factory<T extends EntityFoalDraftHorse> implements IRenderFactory<T>
    {
        @Override
        public Render<? super T> createRenderFor(RenderManager manager) {
            return new RenderFoalDraftHorse(manager);
        }
    }
}
