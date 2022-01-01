package client.render.props;

import com.animania.Animania;
import com.leviathanstudio.craftstudio.client.model.ModelCraftStudio;
import com.mojang.blaze3d.platform.GlStateManager;
import common.entity.pullables.EntityTiller;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderTiller extends Render<EntityTiller>
{
	public static final Factory FACTORY = new Factory();
	private ModelCraftStudio modeltiller = new ModelCraftStudio(Animania.MODID, "model_tiller", 128, 64);

	public RenderTiller(RenderManager manager)
	{
		super(manager);
		this.shadowSize = 1.0F;
	}

	public void doRender(EntityTiller entity, double x, double y, double z, float entityYaw, float partialTicks)
	{
		GlStateManager.pushMatrix();
		this.setupTranslation(x, y, z);
		this.setupRotation(entity, entityYaw, partialTicks);
		this.bindEntityTexture(entity);
		this.modeltiller.render(entity, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GlStateManager.popMatrix();
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	public void setupRotation(EntityTiller tiller, float yaw, float partialticks)
	{
		GlStateManager.rotate(180.0F - yaw, 0.0F, 0.5F, 0.0F);
		double yPulling = tiller.puller == null ? tiller.getY() : tiller.puller.getY();
		double ytiller = tiller.getY();

		float difference = (float) (yPulling - ytiller);
		GlStateManager.rotate(25 * difference, 1.0f, 0, 0);
		GlStateManager.scale(-1.0F, -1.0F, 1.0F);
	}

	public void setupTranslation(double p_188309_1_, double p_188309_3_, double p_188309_5_)
	{
		GlStateManager.translate((float) p_188309_1_, (float) p_188309_3_ + 1.5F, (float) p_188309_5_);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityTiller entity)
	{

		return new ResourceLocation(Animania.MODID, "textures/entity/props/tiller.png");

	}

	public static class Factory<T extends EntityTiller> implements IRenderFactory<T>
	{
		@Override
		public Render<? super T> createRenderFor(RenderManager manager)
		{
			return new RenderTiller(manager);
		}
	}

}