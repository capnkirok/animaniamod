package client.render.pigs;

import client.render.pigs.layers.LayerMudHogYorkshire;
import com.animania.Animania;
import com.animania.addons.farm.client.model.pig.ModelHog;
import com.animania.addons.farm.common.entity.pigs.PigYorkshire.EntityHogYorkshire;
import com.animania.client.render.layer.LayerBlinking;
import com.animania.common.handler.BlockHandler;
import com.mojang.blaze3d.platform.GlStateManager;
import common.entity.pigs.EntityAnimaniaPig;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Dist.CLIENT)
public class RenderHogYorkshire<T extends EntityHogYorkshire> extends RenderLiving<T>
{
	public static final Factory FACTORY = new Factory();

	private static final ResourceLocation PIG_TEXTURES = new ResourceLocation("animania:textures/entity/pigs/hog_yorkshire.png");
	private static final ResourceLocation PIG_TEXTURES_BLINK = new ResourceLocation("animania:textures/entity/pigs/pig_blink.png");

	public RenderHogYorkshire(RenderManager rm)
	{
		super(rm, new ModelHog(), 0.5F);
		this.addLayer(new LayerMudHogYorkshire(this));
		this.addLayer(new LayerBlinking(this, PIG_TEXTURES_BLINK, 0xE07F7D));
	}

	protected void preRenderScale(T entity, float f)
	{
		GL11.glScalef(1.10F, 1.10F, 1.10F);

		EntityAnimaniaPig entityChk = entity;

		if (entityChk.getSleeping())
		{
			this.shadowSize = 0;
			float sleepTimer = entityChk.getSleepTimer();
			if (entityChk.getRandom().nextInt(2) < 1 && sleepTimer > -0.55F)
			{
				sleepTimer = sleepTimer - 0.01F;
			}
			entity.setSleepTimer(sleepTimer);

			GlStateManager.translate(0.0F, entity.height - 1.25F, 0.0F);
			GlStateManager.rotate(86.0F, 0.0F, 0.0F, 1.0F);
		}
		else
		{
			entityChk.setSleeping(false);
			entityChk.setSleepTimer(0F);

			double x = entity.getX();
			double y = entity.getY();
			double z = entity.getZ();

			BlockPos pos = new BlockPos(x, y, z);

			Block blockchk = entity.level.getBlockState(pos).getBlock();
			Block blockchk2 = entity.level.getBlockState(pos).getBlock();
			boolean mudBlock = false;
			if (blockchk == BlockHandler.blockMud || blockchk.getUnlocalizedName().contains("tile.mud") || blockchk2.getUnlocalizedName().contains("tile.mud"))
			{
				mudBlock = true;
			}

			if (mudBlock && !entity.getMuddy())
			{
				this.shadowSize = 0;
				GlStateManager.translate(0.0F, entity.height - 1.45F, 0.0F);
				GlStateManager.rotate(86.0F, 0.0F, 0.0F, 1.0F);
				entity.setMuddy(true);
				entity.setMudTimer(1.0F);
				entity.setSplashTimer(1.0F);
			}
			else if (entity.isWet() && entity.getMuddy() && !mudBlock)
			{
				this.shadowSize = 0.5F;
				entity.setMuddy(false);
				entity.setMudTimer(0.0F);
				entity.setSplashTimer(0.0F);
			}
			else if (mudBlock)
			{
				Float splashTimer = entity.getSplashTimer();
				GlStateManager.translate(0.0F, entity.height - 1.45F, 0.0F);
				GlStateManager.rotate(86.0F, 0.0F, 0.0F, 1.0F);

				splashTimer = splashTimer - 0.045F;
				entity.setSplashTimer(splashTimer);
				if (splashTimer <= 0.0F)
				{
					entity.setMuddy(true);
					entity.setMudTimer(1.0F);
				}

			}
			else if (entity.getMudTimer() > 0)
			{
				this.shadowSize = 0.5F;
				entity.setMuddy(false);
				float mudTimer = entity.getMudTimer();
				if (Animania.RANDOM.nextInt(3) < 1)
				{
					mudTimer = mudTimer - 0.0025F;
					entity.setMudTimer(mudTimer);
				}
			}
		}

	}

	@Override
	protected void preRenderCallback(T LivingEntity, float f)
	{
		this.preRenderScale(LivingEntity, f);
	}

	@Override
	protected ResourceLocation getEntityTexture(T entity)
	{
		return RenderHogYorkshire.PIG_TEXTURES;
	}

	static class Factory<T extends EntityHogYorkshire> implements IRenderFactory<T>
	{
		@Override
		public Render<? super T> createRenderFor(RenderManager manager)
		{
			return new RenderHogYorkshire(manager);
		}
	}

}