package client.render.rabbits;

import com.animania.addons.extra.client.model.rabbits.ModelJack;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitJack.RabbitEntityKitJack;
import com.animania.client.render.layer.LayerBlinking;
import com.mojang.blaze3d.platform.GlStateManager;
import common.entity.rodents.rabbits.EntityAnimaniaRabbit;
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
public class RenderKitJack<T extends RabbitEntityKitJack> extends RenderLiving<T>
{
	public static final Factory FACTORY = new Factory();
	private static final ResourceLocation rabbitTextures = new ResourceLocation("animania:textures/entity/rabbits/rabbit_jack.png");
	private static final ResourceLocation rabbitTexturesBlink = new ResourceLocation("animania:textures/entity/rabbits/rabbit_blink.png");

	public RenderKitJack(RenderManager rm)
	{
		super(rm, new ModelJack(), 0.25F);
		this.addLayer(new LayerBlinking(this, rabbitTexturesBlink, 0x938375));
	}

	protected void preRenderScale(RabbitEntityKitJack entity, float f)
	{
		float age = entity.getEntityAge();
		GL11.glScalef(0.32F + age / entity.getSizeDividend(), 0.32F + age / entity.getSizeDividend(), 0.32F + age / entity.getSizeDividend());
		GL11.glTranslatef(0f, 0f, -0.5f);

		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();
		BlockPos pos = new BlockPos(x, y, z);
		Block blockchk = entity.level.getBlockState(pos).getBlock();
		EntityAnimaniaRabbit entityChk = entity;
		if (entityChk.getSleeping())
		{
			this.shadowSize = 0;
			GlStateManager.translate(-.25F, 0.1F, -.25F);
		}
		else
		{
			this.shadowSize = 0.25F;
			entityChk.setSleeping(false);
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
		return RenderKitJack.rabbitTextures;
	}

	static class Factory<T extends RabbitEntityKitJack> implements IRenderFactory<T>
	{
		@Override
		public Render<? super T> createRenderFor(RenderManager manager)
		{
			return new RenderKitJack(manager);
		}

	}
}