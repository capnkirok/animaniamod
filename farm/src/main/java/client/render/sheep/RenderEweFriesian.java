package client.render.sheep;

import com.animania.addons.farm.client.model.sheep.ModelFriesianSheep;
import com.animania.addons.farm.common.entity.sheep.SheepFriesian.EntityEweFriesian;
import com.animania.client.render.layer.LayerBlinking;
import com.mojang.blaze3d.platform.GlStateManager;
import common.entity.sheep.EntityAnimaniaSheep;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Dist.CLIENT)
public class RenderEweFriesian<T extends EntityEweFriesian> extends RenderLiving<T>
{
	public static final Factory FACTORY = new Factory();
	private static final String modid = "animania", SheepBaseDir = "textures/entity/sheep/";

	private static final ResourceLocation[] SHEEP_TEXTURES = { new ResourceLocation(RenderEweFriesian.modid, RenderEweFriesian.SheepBaseDir + "sheep_friesian_" + "black.png"), new ResourceLocation(RenderEweFriesian.modid, RenderEweFriesian.SheepBaseDir + "sheep_friesian_" + "white.png"), new ResourceLocation(RenderEweFriesian.modid, RenderEweFriesian.SheepBaseDir + "sheep_friesian_" + "brown.png") };

	private static final ResourceLocation SHEEP_TEXTURE_BLINK = new ResourceLocation("animania:textures/entity/sheep/sheep_blink.png");
	private static final ResourceLocation[] SHEEP_TEXTURES_SHEARED = { new ResourceLocation(RenderEweFriesian.modid, RenderEweFriesian.SheepBaseDir + "sheep_friesian_" + "black_sheared.png"), new ResourceLocation(RenderEweFriesian.modid, RenderEweFriesian.SheepBaseDir + "sheep_friesian_" + "white_sheared.png"), new ResourceLocation(RenderEweFriesian.modid, RenderEweFriesian.SheepBaseDir + "sheep_friesian_" + "brown_sheared.png") };

	private static int[] EYE_COLORS = { 0x282828, 0xEDEDED, 0x282828 };

	private LayerBlinking blinking;

	private static ModelFriesianSheep model = new ModelFriesianSheep();

	public RenderEweFriesian(RenderManager rm)
	{
		super(rm, model, 0.5F);
		this.addLayer(this.blinking = new LayerBlinking(this, SHEEP_TEXTURE_BLINK, 0));
	}

	protected void preRenderScale(EntityEweFriesian entity, float f)
	{
		GL11.glScalef(0.61F, 0.61F, 0.61F);
		GL11.glTranslatef(0f, 0f, -0.5f);
		EntityAnimaniaSheep SheepEntity = entity;
		if (SheepEntity.getSleeping())
		{
			this.shadowSize = 0;
			float sleepTimer = SheepEntity.getSleepTimer();
			if (sleepTimer > -0.55F)
			{
				sleepTimer = sleepTimer - 0.0125F;
			}
			entity.setSleepTimer(sleepTimer);

			GlStateManager.translate(-0.25F, entity.height - 1.05F - sleepTimer, -0.25F);
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
			return RenderEweFriesian.SHEEP_TEXTURES[entity.getColorNumber()];
		}
		else
		{
			return RenderEweFriesian.SHEEP_TEXTURES_SHEARED[entity.getColorNumber()];
		}
	}

	@Override
	protected void preRenderCallback(T LivingEntity, float partial)
	{
		this.preRenderScale(LivingEntity, partial);
		this.blinking.setColors(EYE_COLORS[LivingEntity.getColorNumber()], EYE_COLORS[LivingEntity.getColorNumber()]);
		if (LivingEntity.hasCustomName() && "jeb_".equals(LivingEntity.getCustomNameTag()) && LivingEntity.isDyeable())
		{
			int i1 = 25;
			int i = LivingEntity.ticksExisted / 25 + LivingEntity.getEntityId();
			int j = EnumDyeColor.values().length;
			int k = i % j;
			int l = (i + 1) % j;
			float f = ((float) (LivingEntity.ticksExisted % 25) + partial) / 25.0F;
			float[] afloat1 = SheepEntity.getDyeRgb(EnumDyeColor.byMetadata(k));
			float[] afloat2 = SheepEntity.getDyeRgb(EnumDyeColor.byMetadata(l));
			model.setWoolColor(afloat1[0] * (1.0F - f) + afloat2[0] * f, afloat1[1] * (1.0F - f) + afloat2[1] * f, afloat1[2] * (1.0F - f) + afloat2[2] * f);

		}
		else
		{
			float[] rgb = LivingEntity.getDyeColor().getColorComponentValues();
			model.setWoolColor(rgb[0], rgb[1], rgb[2]);
		}

	}

	static class Factory<T extends EntityEweFriesian> implements IRenderFactory<T>
	{
		@Override
		public Render<? super T> createRenderFor(RenderManager manager)
		{
			return new RenderEweFriesian(manager);
		}

	}
}
