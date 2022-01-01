package client.render.cows;

import com.animania.addons.farm.client.model.cow.ModelCowHereford;
import com.animania.addons.farm.common.entity.cows.CowHereford.CowEntityHereford;
import com.animania.client.render.layer.LayerBlinking;
import com.mojang.blaze3d.platform.GlStateManager;
import common.entity.cows.EntityAnimaniaCow;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Dist.CLIENT)
public class RenderCowHereford<T extends CowEntityHereford> extends RenderLiving<T>
{
	public static final Factory FACTORY = new Factory();

	private static final ResourceLocation cowTextures = new ResourceLocation("animania:textures/entity/cows/cow_hereford.png");
	private static final ResourceLocation cowTexturesBlink = new ResourceLocation("animania:textures/entity/cows/cow_blink.png");

	public RenderCowHereford(RenderManager rm)
	{
		super(rm, new ModelCowHereford(), 0.5F);
		addLayer(new LayerBlinking(this, cowTexturesBlink, 0xDEDEDE));
	}

	@Override
	protected void preRenderCallback(T LivingEntity, float f)
	{
		this.preRenderScale(LivingEntity, f);
	}

	protected ResourceLocation getCowTextures(T par1CowEntity)
	{
		return RenderCowHereford.cowTextures;
	}

	protected ResourceLocation getCowTexturesBlink(T par1CowEntity)
	{
		return RenderCowHereford.cowTexturesBlink;
	}

	protected void preRenderScale(T entity, float f)
	{
		GL11.glScalef(1.34F, 1.34F, 1.34F);

		EntityAnimaniaCow CowEntity = entity;
		if (CowEntity.getSleeping())
		{
			float sleepTimer = CowEntity.getSleepTimer();
			if (sleepTimer > -0.55F)
			{
				sleepTimer = sleepTimer - 0.01F;
			}
			entity.setSleepTimer(sleepTimer);

			GlStateManager.translate(-0.25F, entity.height - 1.85F - sleepTimer, -0.25F);
			GlStateManager.rotate(6.0F, 0.0F, 0.0F, 1.0F);
		}
		else
		{
			CowEntity.setSleeping(false);
			CowEntity.setSleepTimer(0F);
		}

	}

	@Override
	protected ResourceLocation getEntityTexture(T entity)
	{
		return this.getCowTextures(entity);

	}

	static class Factory<T extends CowEntityHereford> implements IRenderFactory<T>
	{
		@Override
		public Render<? super T> createRenderFor(RenderManager manager)
		{
			return new RenderCowHereford(manager);
		}
	}
}
