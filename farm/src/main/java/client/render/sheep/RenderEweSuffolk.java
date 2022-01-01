package client.render.sheep;

import com.animania.addons.farm.client.model.sheep.ModelSuffolkEwe;
import com.animania.addons.farm.common.entity.sheep.SheepSuffolk.EntityEweSuffolk;
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
public class RenderEweSuffolk<T extends EntityEweSuffolk> extends RenderLiving<T>
{
	public static final Factory FACTORY = new Factory();
	private static final String modid = "animania", SheepBaseDir = "textures/entity/sheep/";

	private static final ResourceLocation[] SHEEP_TEXTURES = { new ResourceLocation(RenderEweSuffolk.modid, RenderEweSuffolk.SheepBaseDir + "sheep_suffolk_" + "white_ewe.png"), new ResourceLocation(RenderEweSuffolk.modid, RenderEweSuffolk.SheepBaseDir + "sheep_suffolk_" + "brown_ewe.png") };

	private static final ResourceLocation SHEEP_TEXTURE_BLINK = new ResourceLocation("animania:textures/entity/sheep/sheep_blink.png");
	private static final ResourceLocation[] SHEEP_TEXTURES_SHEARED = { new ResourceLocation(RenderEweSuffolk.modid, RenderEweSuffolk.SheepBaseDir + "sheep_suffolk_" + "white_ewe_sheared.png"), new ResourceLocation(RenderEweSuffolk.modid, RenderEweSuffolk.SheepBaseDir + "sheep_suffolk_" + "brown_ewe_sheared.png") };

	public RenderEweSuffolk(RenderManager rm)
	{
		super(rm, new ModelSuffolkEwe(), 0.5F);
		this.addLayer(new LayerBlinking(this, SHEEP_TEXTURE_BLINK, 0x1D1D1D));
	}

	protected void preRenderScale(EntityEweSuffolk entity, float f)
	{
		GL11.glScalef(0.64F, 0.64F, 0.64F);
		GL11.glTranslatef(0f, 0f, -0.5f);
		EntityAnimaniaSheep SheepEntity = entity;
		if (SheepEntity.getSleeping())
		{
			this.shadowSize = 0;
			float sleepTimer = SheepEntity.getSleepTimer();
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
			return RenderEweSuffolk.SHEEP_TEXTURES[entity.getColorNumber()];
		}
		else
		{
			return RenderEweSuffolk.SHEEP_TEXTURES_SHEARED[entity.getColorNumber()];
		}
	}

	@Override
	protected void preRenderCallback(T LivingEntity, float f)
	{
		this.preRenderScale(LivingEntity, f);
		if (LivingEntity.hasCustomName() && "jeb_".equals(LivingEntity.getCustomNameTag()) && LivingEntity.isDyeable())
		{
			int i1 = 25;
			int i = LivingEntity.ticksExisted / 25 + LivingEntity.getEntityId();
			int j = EnumDyeColor.values().length;
			int k = i % j;
			int l = (i + 1) % j;
			float q = ((float) (LivingEntity.ticksExisted % 25) + f) / 25.0F;
			float[] afloat1 = SheepEntity.getDyeRgb(EnumDyeColor.byMetadata(k));
			float[] afloat2 = SheepEntity.getDyeRgb(EnumDyeColor.byMetadata(l));
			((ModelSuffolkEwe) this.mainModel).setWoolColor(afloat1[0] * (1.0F - q) + afloat2[0] * q, afloat1[1] * (1.0F - q) + afloat2[1] * q, afloat1[2] * (1.0F - q) + afloat2[2] * q);
		}
		else
		{
			float[] rgb = LivingEntity.getDyeColor().getColorComponentValues();
			((ModelSuffolkEwe) this.mainModel).setWoolColor(rgb[0], rgb[1], rgb[2]);
		}
	}

	static class Factory<T extends EntityEweSuffolk> implements IRenderFactory<T>
	{
		@Override
		public Render<? super T> createRenderFor(RenderManager manager)
		{
			return new RenderEweSuffolk(manager);
		}

	}
}
