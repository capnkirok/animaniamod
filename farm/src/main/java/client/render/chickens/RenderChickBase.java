package client.render.chickens;

import com.animania.addons.farm.client.model.chicken.ModelChick;
import com.animania.client.render.layer.LayerBlinking;
import com.animania.common.handler.BlockHandler;
import com.mojang.blaze3d.platform.GlStateManager;
import common.entity.chickens.EntityAnimaniaChicken;
import common.entity.chickens.EntityChickBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Dist.CLIENT)
public class RenderChickBase<T extends EntityChickBase> extends RenderLiving<T>
{
	public static final Factory FACTORY = new Factory();

	LayerBlinking blinkingLayer;

	public RenderChickBase(RenderManager rm)
	{
		super(rm, new ModelChick(), 0.2F);
		this.addLayer(this.blinkingLayer = new LayerBlinking(this, new ResourceLocation("animania:textures/entity/chickens/chick_blink.png"), 0));
	}

	@Override
	protected float handleRotationFloat(T livingBase, float partialTicks)
	{
		float f = livingBase.oFlap + (livingBase.wingRotation - livingBase.oFlap) * partialTicks;
		float f1 = livingBase.oFlapSpeed + (livingBase.destPos - livingBase.oFlapSpeed) * partialTicks;
		return (MathHelper.sin(f) + 1.0F) * f1;
	}

	@Override
	protected void preRenderCallback(T LivingEntity, float f)
	{
		this.preRenderScale(LivingEntity, f);
		this.blinkingLayer.setColors(LivingEntity.lidCol, LivingEntity.lidCol);
	}

	protected void preRenderScale(T entity, float f)
	{

		float age = entity.getEntityAge();
		GL11.glScalef(1.0F + age / entity.getSizeDividend(), 1.0F + age / entity.getSizeDividend(), 1.0F + age / entity.getSizeDividend());

		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();

		BlockPos pos = new BlockPos(x, y, z);

		Block blockchk = entity.level.getBlockState(pos).getBlock();

		EntityAnimaniaChicken entityChk = entity;

		if (blockchk == BlockHandler.blockNest || entityChk.getSleeping())
		{
			this.shadowSize = 0;
			GlStateManager.translate(-.25F, 0.1F, -.25F);
		}
		else
		{
			this.shadowSize = 0.2F;
			entityChk.setSleeping(false);
		}

	}

	@Override
	protected ResourceLocation getEntityTexture(T entity)
	{
		return entity.getResourceLocation();
	}

	static class Factory<T extends EntityChickBase> implements IRenderFactory<T>
	{
		@Override
		public Render<? super T> createRenderFor(RenderManager manager)
		{
			return new RenderChickBase(manager);
		}
	}

}