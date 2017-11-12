package com.animania.client.render.props;

import com.animania.Animania;
import com.animania.common.entities.props.EntityCart;
import com.google.common.primitives.SignedBytes;
import com.leviathanstudio.craftstudio.client.model.ModelCraftStudio;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderEntityItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderCart extends Render<EntityCart>
{
	public static final Factory FACTORY = new Factory();
	private ModelCraftStudio modelCart = new ModelCraftStudio(Animania.MODID, "model_cart", 128, 128);
	private ModelCraftStudio modelCartChest = new ModelCraftStudio(Animania.MODID, "model_cart_chest", 128, 128);
	private RenderEntityItem itemRenderer;
	private static EntityItem customitem = new EntityItem(null);
	private int rotSkip;

	public RenderCart(RenderManager manager) {
		super(manager);
		this.shadowSize = 1.0F;
		this.itemRenderer = null;
	}

	public void doRender(EntityCart entity, double x, double y, double z, float entityYaw, float partialTicks)
	{

		if (entity.getHasChest()) {
			GlStateManager.pushMatrix();
			this.setupTranslation(x, y, z);
			this.setupRotation(entity, entityYaw, partialTicks);
			this.bindEntityTexture(entity);
			this.modelCartChest.render(entity, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
			GlStateManager.popMatrix();
			super.doRender(entity, x, y, z, entityYaw, partialTicks);
		} else {
			GlStateManager.pushMatrix();
			this.setupTranslation(x, y, z);
			this.setupRotation(entity, entityYaw, partialTicks);
			this.bindEntityTexture(entity);
			this.modelCart.render(entity, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
			GlStateManager.popMatrix();
			super.doRender(entity, x, y, z, entityYaw, partialTicks);
		}


	}

	public void setupRotation(EntityCart cart, float yaw, float partialticks)
	{
		GlStateManager.rotate(180.0F - yaw, 0.0F, 0.5F, 0.0F);
		double yPulling = cart.puller == null ? cart.posY : cart.puller.posY;
		double yCart = cart.posY;

		float difference = (float) (yPulling-yCart);
		GlStateManager.rotate(25 * difference, 1.0f, 0, 0);
		GlStateManager.scale(-1.0F, -1.0F, 1.0F);
	}

	public void setupTranslation(double p_188309_1_, double p_188309_3_, double p_188309_5_)
	{
		GlStateManager.translate((float)p_188309_1_, (float)p_188309_3_ + 1.5F, (float)p_188309_5_);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityCart entity) {
		if (entity.getHasChest()) {
			return new ResourceLocation(Animania.MODID, "textures/entity/props/cart_chest.png");
		} else {
			return new ResourceLocation(Animania.MODID, "textures/entity/props/cart.png");
		}
	}

	public static class Factory<T extends EntityCart> implements IRenderFactory<T>
	{
		@Override
		public Render<? super T> createRenderFor(RenderManager manager) {

			return new RenderCart(manager);
		}


	}

	protected void renderCartContents(EntityCart entityCart, float partialTicks, IBlockState iblockstate)
	{
		GlStateManager.pushMatrix();
		Minecraft.getMinecraft().getBlockRendererDispatcher().renderBlockBrightness(iblockstate, entityCart.getBrightness());
		GlStateManager.popMatrix();
	}

}