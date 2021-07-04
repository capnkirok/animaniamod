package com.animania.addons.farm.client.render.sheep;

import org.lwjgl.opengl.GL11;

import com.animania.addons.farm.client.model.sheep.ModelFriesianSheep;
import com.animania.addons.farm.common.entity.sheep.EntityAnimaniaSheep;
import com.animania.addons.farm.common.entity.sheep.SheepFriesian.EntityLambFriesian;
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
public class RenderLambFriesian<T extends EntityLambFriesian> extends RenderLiving<T>
{
	public static final Factory FACTORY = new Factory();
	private static final String modid = "animania", SheepBaseDir = "textures/entity/sheep/";

	private static final ResourceLocation[] SHEEP_TEXTURES = new ResourceLocation[] { new ResourceLocation(RenderLambFriesian.modid, RenderLambFriesian.SheepBaseDir + "sheep_friesian_" + "black.png"), new ResourceLocation(RenderLambFriesian.modid, RenderLambFriesian.SheepBaseDir + "sheep_friesian_" + "white.png"), new ResourceLocation(RenderLambFriesian.modid, RenderLambFriesian.SheepBaseDir + "sheep_friesian_" + "brown.png") };

	private static final ResourceLocation SHEEP_TEXTURE_BLINK = new ResourceLocation("animania:textures/entity/sheep/sheep_blink.png");
	private static final ResourceLocation[] SHEEP_TEXTURES_SHEARED = new ResourceLocation[] { new ResourceLocation(RenderLambFriesian.modid, RenderLambFriesian.SheepBaseDir + "sheep_friesian_" + "black_sheared.png"), new ResourceLocation(RenderLambFriesian.modid, RenderLambFriesian.SheepBaseDir + "sheep_friesian_" + "white_sheared.png"), new ResourceLocation(RenderLambFriesian.modid, RenderLambFriesian.SheepBaseDir + "sheep_friesian_" + "brown_sheared.png") };

	private static int[] EYE_COLORS = new int[] { 0x282828, 0xEDEDED, 0x282828 };

	private LayerBlinking blinking;

	private static ModelFriesianSheep model = new ModelFriesianSheep();

	public RenderLambFriesian(RenderManager rm)
	{
		super(rm, model, 0.5F);
		this.addLayer(blinking = new LayerBlinking(this, SHEEP_TEXTURE_BLINK, 0));
	}

	protected void preRenderScale(EntityLambFriesian entity, float f)
	{
		float age = entity.getEntityAge();
		GL11.glScalef(0.33F + (age / entity.getSizeDividend()), 0.33F + (age / entity.getSizeDividend()), 0.33F + (age / entity.getSizeDividend()));
		GL11.glTranslatef(0f, 0f, -0.5f);
		EntityAnimaniaSheep entitySheep = (EntityAnimaniaSheep) entity;
		if (entitySheep.getSleeping())
		{
			this.shadowSize = 0;
			float sleepTimer = entitySheep.getSleepTimer();
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
			entitySheep.setSleeping(false);
			entitySheep.setSleepTimer(0F);
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(T entity)
	{
		if (entity.posX == -1 && entity.posY == -1 && entity.posZ == -1)
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

	static class Factory<T extends EntityLambFriesian> implements IRenderFactory<T>
	{
		@Override
		public Render<? super T> createRenderFor(RenderManager manager)
		{
			return new RenderLambFriesian(manager);
		}

	}
}
