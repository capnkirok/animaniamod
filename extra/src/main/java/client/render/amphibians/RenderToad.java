package client.render.amphibians;

import com.animania.addons.extra.client.model.amphibians.ModelToad;
import com.mojang.blaze3d.platform.GlStateManager;
import common.entity.amphibians.EntityToad;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Dist.CLIENT)
public class RenderToad<T extends EntityToad> extends RenderLiving<T>
{
	public static final Factory FACTORY = new Factory();

	// Need to move in main class
	private static final String modid = "animania", toadBaseDir = "textures/entity/amphibians/toads/";
	private static final ResourceLocation TOAD_TEXTURE = new ResourceLocation(RenderToad.modid, RenderToad.toadBaseDir + "toad.png");

	public RenderToad(RenderManager rendermanagerIn)
	{
		super(rendermanagerIn, new ModelToad(), 0.05F);
	}

	/**
	 * Allows the render to do state modifications necessary before the model is
	 * rendered.
	 */
	@Override
	protected void preRenderCallback(T entityIn, float partialTickTime)
	{
		GlStateManager.scale(0.32D, 0.32D, 0.32D);
		float f1 = 1.2F;
		float f2 = (entityIn.prevSquishFactor + (entityIn.squishFactor - entityIn.prevSquishFactor) * partialTickTime) / (f1 * 0.5F + 1.0F);
		float f3 = 1.0F / (f2 + 1.0F);
		GL11.glScalef(f3 * f1, 1.0F / f3 * f1, f3 * f1);
	}

	@Override
	protected ResourceLocation getEntityTexture(T entity)
	{
		return RenderToad.TOAD_TEXTURE;
	}

	public static class Factory<T extends EntityToad> implements IRenderFactory<T>
	{
		@Override
		public Render<? super T> createRenderFor(RenderManager manager)
		{
			return new RenderToad(manager);
		}
	}
}
