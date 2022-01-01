package client.render.rabbits;

import com.animania.addons.extra.client.model.rabbits.ModelLop;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitLop.RabbitEntityDoeLop;
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
public class RenderDoeLop<T extends RabbitEntityDoeLop> extends RenderLiving<T>
{
	public static final Factory FACTORY = new Factory();
	private static final String modid = "animania", rabbitBaseDir = "textures/entity/rabbits/";
	private static final ResourceLocation[] RABBIT_TEXTURES = { new ResourceLocation(RenderDoeLop.modid, RenderDoeLop.rabbitBaseDir + "rabbit_lop_" + "black.png"), new ResourceLocation(RenderDoeLop.modid, RenderDoeLop.rabbitBaseDir + "rabbit_lop_" + "brown.png"), new ResourceLocation(RenderDoeLop.modid, RenderDoeLop.rabbitBaseDir + "rabbit_lop_" + "golden.png"), new ResourceLocation(RenderDoeLop.modid, RenderDoeLop.rabbitBaseDir + "rabbit_lop_" + "olive.png"), new ResourceLocation(RenderDoeLop.modid, RenderDoeLop.rabbitBaseDir + "rabbit_lop_" + "patch_black.png"), new ResourceLocation(RenderDoeLop.modid, RenderDoeLop.rabbitBaseDir + "rabbit_lop_" + "patch_brown.png"), new ResourceLocation(RenderDoeLop.modid, RenderDoeLop.rabbitBaseDir + "rabbit_lop_" + "patch_grey.png") };
	private static int[] EYE_COLORS = { 0x404040, 0x816D60, 0xD0A675, 0x7F6C5B, 0xF6F4F4, 0xF6F4F4, 0xF6F4F4 };
	private static final ResourceLocation rabbitTexturesBlink = new ResourceLocation("animania:textures/entity/rabbits/rabbit_blink.png");
	private static final ResourceLocation killerRabbitTextures = new ResourceLocation("animania:textures/entity/rabbits/rabbit_killer.png");
	private LayerBlinking blinkingLayer;

	public RenderDoeLop(RenderManager rm)
	{
		super(rm, new ModelLop(), 0.25F);
		this.addLayer(this.blinkingLayer = new LayerBlinking(this, rabbitTexturesBlink, 0));
	}

	protected void preRenderScale(RabbitEntityDoeLop entity, float f)
	{
		if (entity.getCustomNameTag().equals("Killer"))
		{
			GlStateManager.scale(0.7D, 0.7D, 0.7D);
		}
		else
		{
			GL11.glScalef(0.51F, 0.51F, 0.51F);
		}
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
			GlStateManager.translate(-.25F, 0.25F, -.25F);
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
		this.blinkingLayer.setColors(EYE_COLORS[LivingEntity.getColorNumber()], EYE_COLORS[LivingEntity.getColorNumber()]);
	}

	@Override
	protected ResourceLocation getEntityTexture(T entity)
	{
		if (entity.getCustomNameTag().trim().equals("Killer"))
		{
			return RenderDoeLop.killerRabbitTextures;
		}
		else
		{
			if (entity.getX() == -1 && entity.getY() == -1 && entity.getZ() == -1)
			{
				return RABBIT_TEXTURES[0];
			}

			return RenderDoeLop.RABBIT_TEXTURES[entity.getColorNumber()];
		}
	}

	static class Factory<T extends RabbitEntityDoeLop> implements IRenderFactory<T>
	{
		@Override
		public Render<? super T> createRenderFor(RenderManager manager)
		{
			return new RenderDoeLop(manager);
		}

	}
}
