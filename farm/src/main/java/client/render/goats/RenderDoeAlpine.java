package client.render.goats;

import com.animania.addons.farm.client.model.goats.ModelDoeAlpine;
import com.animania.addons.farm.common.entity.goats.GoatAlpine.EntityDoeAlpine;
import com.animania.client.render.layer.LayerBlinking;
import com.mojang.blaze3d.platform.GlStateManager;
import common.entity.goats.EntityAnimaniaGoat;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Dist.CLIENT)
public class RenderDoeAlpine<T extends EntityDoeAlpine> extends RenderLiving<T>
{
	public static final Factory FACTORY = new Factory();
	private static final ResourceLocation goatTextures = new ResourceLocation("animania:textures/entity/goats/doe_alpine.png");
	private static final ResourceLocation goatTexturesBlink = new ResourceLocation("animania:textures/entity/goats/goats_blink.png");

	public RenderDoeAlpine(RenderManager rm)
	{
		super(rm, new ModelDoeAlpine(), 0.5F);
		this.addLayer(new LayerBlinking(this, goatTexturesBlink, 0x83786D));
	}

	protected ResourceLocation getGoatTextures(T par1Entity)
	{
		return RenderDoeAlpine.goatTextures;
	}

	protected ResourceLocation getGoatTexturesBlink(T par1Entity)
	{
		return RenderDoeAlpine.goatTexturesBlink;
	}

	protected void preRenderScale(EntityDoeAlpine entity, float f)
	{
		GL11.glScalef(0.6F, 0.6F, 0.6F);
		GL11.glTranslatef(0f, 0f, -0.5f);
		EntityAnimaniaGoat entityGoat = entity;
		if (entityGoat.getSleeping())
		{
			this.shadowSize = 0;
			float sleepTimer = entityGoat.getSleepTimer();
			if (sleepTimer > -0.55F)
			{
				sleepTimer = sleepTimer - 0.01F;
			}
			entity.setSleepTimer(sleepTimer);

			GlStateManager.translate(-0.25F, entity.height - 1.45F - sleepTimer, -0.25F);
			GlStateManager.rotate(6.0F, 0.0F, 0.0F, 1.0F);
		}
		else
		{
			this.shadowSize = 0.5F;
			entityGoat.setSleeping(false);
			entityGoat.setSleepTimer(0F);
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(T entity)
	{
		return this.getGoatTextures(entity);
	}

	@Override
	protected void preRenderCallback(T LivingEntity, float f)
	{
		this.preRenderScale(LivingEntity, f);
	}

	static class Factory<T extends EntityDoeAlpine> implements IRenderFactory<T>
	{
		@Override
		public Render<? super T> createRenderFor(RenderManager manager)
		{
			return new RenderDoeAlpine(manager);
		}

	}
}
