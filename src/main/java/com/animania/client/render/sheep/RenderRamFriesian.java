package com.animania.client.render.sheep;

import org.lwjgl.opengl.GL11;

import com.animania.client.models.sheep.ModelFriesianSheep;
import com.animania.common.entities.sheep.EntityAnimaniaSheep;
import com.animania.common.entities.sheep.EntityRamFriesian;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderRamFriesian<T extends EntityRamFriesian> extends RenderLiving<T>
{
	public static final Factory FACTORY = new Factory();
	private static final String modid = "animania", SheepBaseDir = "textures/entity/sheep/";

	private static final ResourceLocation[] SHEEP_TEXTURES = new ResourceLocation[] { new ResourceLocation(RenderRamFriesian.modid, RenderRamFriesian.SheepBaseDir + "sheep_friesian_" + "black_ram.png"), new ResourceLocation(RenderRamFriesian.modid, RenderRamFriesian.SheepBaseDir + "sheep_friesian_" + "white_ram.png"), new ResourceLocation(RenderRamFriesian.modid, RenderRamFriesian.SheepBaseDir + "sheep_friesian_" + "brown_ram.png") };

	private static final ResourceLocation[] SHEEP_TEXTURES_BLINK = new ResourceLocation[] { new ResourceLocation(RenderRamFriesian.modid, RenderRamFriesian.SheepBaseDir + "sheep_friesian_" + "black_ram_blink.png"), new ResourceLocation(RenderRamFriesian.modid, RenderRamFriesian.SheepBaseDir + "sheep_friesian_" + "white_ram_blink.png"), new ResourceLocation(RenderRamFriesian.modid, RenderRamFriesian.SheepBaseDir + "sheep_friesian_" + "brown_ram_blink.png") };

	private static final ResourceLocation[] SHEEP_TEXTURES_SHEARED = new ResourceLocation[] { new ResourceLocation(RenderRamFriesian.modid, RenderRamFriesian.SheepBaseDir + "sheep_friesian_" + "black_ram_sheared.png"), new ResourceLocation(RenderRamFriesian.modid, RenderRamFriesian.SheepBaseDir + "sheep_friesian_" + "white_ram_sheared.png"), new ResourceLocation(RenderRamFriesian.modid, RenderRamFriesian.SheepBaseDir + "sheep_friesian_" + "brown_ram_sheared.png") };

	private static final ResourceLocation[] SHEEP_TEXTURES_SHEARED_BLINK = new ResourceLocation[] { new ResourceLocation(RenderRamFriesian.modid, RenderRamFriesian.SheepBaseDir + "sheep_friesian_" + "black_ram_sheared_blink.png"), new ResourceLocation(RenderRamFriesian.modid, RenderRamFriesian.SheepBaseDir + "sheep_friesian_" + "white_ram_sheared_blink.png"), new ResourceLocation(RenderRamFriesian.modid, RenderRamFriesian.SheepBaseDir + "sheep_friesian_" + "brown_ram_sheared_blink.png") };

	private static ModelFriesianSheep model = new ModelFriesianSheep();
	
	public RenderRamFriesian(RenderManager rm)
	{
		super(rm, model, 0.5F);
	}

	protected void preRenderScale(EntityRamFriesian entity, float f)
	{
		GL11.glScalef(0.65F, 0.65F, 0.65F);
		GL11.glTranslatef(0f, 0f, -0.5f);
		boolean isSleeping = false;
		EntityAnimaniaSheep entitySheep = (EntityAnimaniaSheep) entity;
		if (entitySheep.getSleeping())
		{
			isSleeping = true;
		}

		if (isSleeping)
		{
			this.shadowSize = 0;
			float sleepTimer = entitySheep.getSleepTimer();
			if (sleepTimer > -0.55F)
			{
				sleepTimer = sleepTimer - 0.01F;
			}
			entity.setSleepTimer(sleepTimer);

			GlStateManager.translate(-0.25F, entity.height - 1.05F - sleepTimer, -0.25F);
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
		int blinkTimer = entity.blinkTimer;
		long currentTime = entity.world.getWorldTime() % 23999;
		boolean isSleeping = false;

		EntityAnimaniaSheep entitySheep = (EntityAnimaniaSheep) entity;
		isSleeping = entitySheep.getSleeping();
		float sleepTimer = entitySheep.getSleepTimer();

		if (entity.posX == -1 && entity.posY == -1 && entity.posZ == -1)
		{
			return SHEEP_TEXTURES[0];
		}
		else
		{
			if (!entity.getSheared())
			{
				if (isSleeping && sleepTimer <= -0.55F && currentTime < 23250)
				{
					return this.SHEEP_TEXTURES_BLINK[entity.getColorNumber()];
				}
				else if (blinkTimer < 7 && blinkTimer >= 0)
				{
					return this.SHEEP_TEXTURES_BLINK[entity.getColorNumber()];
				}
				else
				{
					return this.SHEEP_TEXTURES[entity.getColorNumber()];
				}
			}
			else
			{
				if (isSleeping && sleepTimer <= -0.55F && currentTime < 23250)
				{
					return this.SHEEP_TEXTURES_SHEARED_BLINK[entity.getColorNumber()];
				}
				else if (blinkTimer < 7 && blinkTimer >= 0)
				{
					return this.SHEEP_TEXTURES_SHEARED_BLINK[entity.getColorNumber()];
				}
				else
				{
					return this.SHEEP_TEXTURES_SHEARED[entity.getColorNumber()];
				}
			}
		}

	}

	@Override
	protected void preRenderCallback(T entityliving, float f)
	{
		this.preRenderScale(entityliving, f);
		
		if (entityliving.hasCustomName() && "jeb_".equals(entityliving.getCustomNameTag()) && entityliving.isDyeable())
		{
			int i1 = 25;
			int i = entityliving.ticksExisted / 25 + entityliving.getEntityId();
			int j = EnumDyeColor.values().length;
			int k = i % j;
			int l = (i + 1) % j;
			float q = ((float) (entityliving.ticksExisted % 25) + f) / 25.0F;
			float[] afloat1 = EntitySheep.getDyeRgb(EnumDyeColor.byMetadata(k));
			float[] afloat2 = EntitySheep.getDyeRgb(EnumDyeColor.byMetadata(l));
			model.setWoolColor(afloat1[0] * (1.0F - q) + afloat2[0] * q, afloat1[1] * (1.0F - q) + afloat2[1] * q, afloat1[2] * (1.0F - q) + afloat2[2] * q);
		
		}
		else
		{
			float[] rgb = entityliving.getDyeColor().getColorComponentValues();
			model.setWoolColor(rgb[0], rgb[1], rgb[2]);
		}

	}

	static class Factory<T extends EntityRamFriesian> implements IRenderFactory<T>
	{
		@Override
		public Render<? super T> createRenderFor(RenderManager manager)
		{
			return new RenderRamFriesian(manager);
		}

	}
}
