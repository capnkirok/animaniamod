package client.render.horses;

import com.animania.addons.farm.client.model.horse.ModelDraftHorseFoal;
import com.animania.addons.farm.common.entity.horses.HorseDraft.EntityFoalDraftHorse;
import com.animania.client.render.layer.LayerBlinking;
import com.mojang.blaze3d.platform.GlStateManager;
import common.entity.horses.EntityAnimaniaHorse;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Dist.CLIENT)
public class RenderFoalDraftHorse<T extends EntityFoalDraftHorse> extends RenderLiving<T>
{

	public static final Factory FACTORY = new Factory();
	private static final String modid = "animania", horseBaseDir = "textures/entity/horses/";

	private static final ResourceLocation[] HORSE_TEXTURES = { new ResourceLocation(RenderFoalDraftHorse.modid, RenderFoalDraftHorse.horseBaseDir + "draft_horse_" + "black.png"), new ResourceLocation(RenderFoalDraftHorse.modid, RenderFoalDraftHorse.horseBaseDir + "draft_horse_" + "bw1.png"), new ResourceLocation(RenderFoalDraftHorse.modid, RenderFoalDraftHorse.horseBaseDir + "draft_horse_" + "bw2.png"), new ResourceLocation(RenderFoalDraftHorse.modid, RenderFoalDraftHorse.horseBaseDir + "draft_horse_" + "grey.png"), new ResourceLocation(RenderFoalDraftHorse.modid, RenderFoalDraftHorse.horseBaseDir + "draft_horse_" + "red.png"), new ResourceLocation(RenderFoalDraftHorse.modid, RenderFoalDraftHorse.horseBaseDir + "draft_horse_" + "white.png") };
	private static ResourceLocation BLINK = new ResourceLocation("animania:textures/entity/horses/horse_blink.png");

	private static final int[] BLINK_COLORS = { 0x1B1B1B, 0x181818, 0x171717, 0x797979, 0x8F3514, 0xC1C1C1 };

	private LayerBlinking blinkingLayer;

	public RenderFoalDraftHorse(RenderManager rm)
	{
		super(rm, new ModelDraftHorseFoal(), 0.5F);
		this.addLayer(this.blinkingLayer = new LayerBlinking(this, BLINK, 0));
	}

	protected void preRenderScale(EntityFoalDraftHorse entity, float f)
	{

		float age = entity.getEntityAge();
		GL11.glScalef(0.4F + age / entity.getSizeDividend(), 0.4F + age / entity.getSizeDividend(), 0.4F + age / entity.getSizeDividend());

		EntityAnimaniaHorse HorseEntity = entity;
		if (HorseEntity.getSleeping())
		{
			this.shadowSize = 0F;
			float sleepTimer = HorseEntity.getSleepTimer();
			if (sleepTimer > -0.55F)
			{
				sleepTimer = sleepTimer - 0.01F;
			}
			entity.setSleepTimer(sleepTimer);

			GlStateManager.translate(-0.25F, entity.height - 1.25F - sleepTimer, -0.25F);
			GlStateManager.rotate(6.0F, 0.0F, 0.0F, 1.0F);
		}
		else
		{
			this.shadowSize = 0.8F;
			HorseEntity.setSleeping(false);
			HorseEntity.setSleepTimer(0F);
		}

	}

	@Override
	protected ResourceLocation getEntityTexture(EntityFoalDraftHorse entity)
	{
		if (entity.getX() == -1 && entity.getY() == -1 && entity.getZ() == -1)
		{
			return HORSE_TEXTURES[0];
		}

		return HORSE_TEXTURES[entity.getColorNumber()];
	}

	@Override
	protected void preRenderCallback(EntityFoalDraftHorse LivingEntity, float f)
	{
		this.preRenderScale(LivingEntity, f);
		this.blinkingLayer.setColors(BLINK_COLORS[LivingEntity.getColorNumber()], BLINK_COLORS[LivingEntity.getColorNumber()]);
	}

	static class Factory<T extends EntityFoalDraftHorse> implements IRenderFactory<T>
	{
		@Override
		public Render<? super T> createRenderFor(RenderManager manager)
		{
			return new RenderFoalDraftHorse(manager);
		}
	}
}
