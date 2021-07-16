package com.animania.addons.farm.client.render.sheep;

import org.lwjgl.opengl.GL11;

import com.animania.addons.farm.client.model.sheep.ModelDorsetEwe;
import com.animania.addons.farm.common.entity.sheep.EntityAnimaniaSheep;
import com.animania.addons.farm.common.entity.sheep.SheepDorset.EntityLambDorset;
import com.animania.client.render.layer.LayerBlinking;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Dist.CLIENT)
public class RenderLambDorset<T extends EntityLambDorset> extends RenderLiving<T>
{
	public static final Factory FACTORY = new Factory();
	private static final String modid = "animania", SheepBaseDir = "textures/entity/sheep/";

	private static final ResourceLocation[] SHEEP_TEXTURES = new ResourceLocation[] { new ResourceLocation(RenderLambDorset.modid, RenderLambDorset.SheepBaseDir + "sheep_dorset_" + "white_ewe.png"), new ResourceLocation(RenderLambDorset.modid, RenderLambDorset.SheepBaseDir + "sheep_dorset_" + "brown_ewe.png") };

	private static final ResourceLocation SHEEP_TEXTURE_BLINK = new ResourceLocation("animania:textures/entity/sheep/sheep_blink.png");
	private static final ResourceLocation[] SHEEP_TEXTURES_SHEARED = new ResourceLocation[] { new ResourceLocation(RenderLambDorset.modid, RenderLambDorset.SheepBaseDir + "sheep_dorset_" + "white_ewe_sheared.png"), new ResourceLocation(RenderLambDorset.modid, RenderLambDorset.SheepBaseDir + "sheep_dorset_" + "brown_ewe_sheared.png") };

	private static int[] EYE_COLORS = new int[] { 0xEDEDED, 0x1D1D1D };

	private LayerBlinking blinking;

	public RenderLambDorset(RenderManager rm)
	{
		super(rm, new ModelDorsetEwe(), 0.5F);
		this.addLayer(blinking = new LayerBlinking(this, SHEEP_TEXTURE_BLINK, 0));
	}

	protected void preRenderScale(EntityLambDorset entity, float f)
	{
		float age = entity.getEntityAge();
		GL11.glScalef(0.30F + (age / entity.getSizeDividend()), 0.30F + (age / entity.getSizeDividend()), 0.30F + (age / entity.getSizeDividend()));
		GL11.glTranslatef(0f, 0f, -0.5f);
		EntityAnimaniaSheep SheepEntity = (EntityAnimaniaSheep) entity;
		if (SheepEntity.getSleeping())
		{
			this.shadowSize = 0;
			float sleepTimer = SheepEntity.getSleepTimer();
			if (sleepTimer > -0.55F)
			{
				sleepTimer = sleepTimer - 0.01F;
			}
			entity.setSleepTimer(sleepTimer);

			GlStateManager.translate(-0.25F, entity.height - .45F - sleepTimer, -0.25F);
			GlStateManager.rotate(6.0F, 0.0F, 0.0F, 1.0F);
		}
		else
		{
			this.shadowSize = 0.5F;
			SheepEntity.setSleeping(false);
			SheepEntity.setSleepTimer(0F);
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(T entity)
	{
		if (entity.getX() == -1 && entity.getY() == -1 && entity.getZ() == -1)
		{
			return SHEEP_TEXTURES[0];
		}

		if (!entity.getSheared())
		{
			return this.SHEEP_TEXTURES[entity.getColorNumber()];
		}
		else
		{
			return this.SHEEP_TEXTURES_SHEARED[entity.getColorNumber()];
		}
	}

	@Override
	protected void preRenderCallback(T LivingEntity, float f)
	{
		this.preRenderScale(LivingEntity, f);
		this.blinking.setColors(EYE_COLORS[LivingEntity.getColorNumber()], EYE_COLORS[LivingEntity.getColorNumber()]);
	}

	static class Factory<T extends EntityLambDorset> implements IRenderFactory<T>
	{
		@Override
		public Render<? super T> createRenderFor(RenderManager manager)
		{
			return new RenderLambDorset(manager);
		}

	}
}
