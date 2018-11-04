package com.animania.client.render.props;

import com.animania.Animania;
import com.animania.common.entities.props.EntityWagon;
import com.leviathanstudio.craftstudio.client.model.ModelCraftStudio;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderWagon extends Render<EntityWagon> 
{
	public static final Factory FACTORY = new Factory();
	private ModelCraftStudio modelWagon = new ModelCraftStudio(Animania.MODID, "model_wagon", 256, 128);
	private int lastTexture = 1;
	private static final ResourceLocation wagonTextures1 = new ResourceLocation("animania:textures/entity/props/wagon.png");
	private static final ResourceLocation wagonTextures2 = new ResourceLocation("animania:textures/entity/props/wagon2.png");
	private static final ResourceLocation wagonTextures3 = new ResourceLocation("animania:textures/entity/props/wagon3.png");

	public RenderWagon(RenderManager manager) {
		super(manager);
		this.shadowSize = 1.5F;
	}

	public void doRender(EntityWagon entity, double x, double y, double z, float entityYaw, float partialTicks)
	{
		GlStateManager.pushMatrix();
		this.setupTranslation(x, y, z);
		this.setupRotation(entity, entityYaw, partialTicks);
		this.bindEntityTexture(entity);
		this.modelWagon.render(entity, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);



		GlStateManager.popMatrix();
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	} 


	public void setupRotation(EntityWagon wagon, float yaw, float partialticks)
	{
		GlStateManager.rotate(180.0F - yaw, 0.0F, 0.5F, 0.0F);
		double yPulling = wagon.puller == null ? wagon.posY : wagon.puller.posY;
		double yCart = wagon.posY;

		float difference = (float) (yPulling-yCart);
		GlStateManager.rotate(15 * difference, 1.0f, 0, 0);
		GlStateManager.scale(-1.0F, -1.0F, 1.0F);
	}


	public void setupTranslation(double p_188309_1_, double p_188309_3_, double p_188309_5_)
	{
		GlStateManager.translate((float)p_188309_1_, (float)p_188309_3_ + 1.6F, (float)p_188309_5_);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityWagon entity) {
		int blinkTimer = entity.blinkTimer;
		
		if (entity.world.getWorldTime() % 24000 < 13000) {
			lastTexture = 1;
			return this.getWagonTextures1(entity);
		} else if (blinkTimer == 15) {
			int bob = Animania.RANDOM.nextInt(3);
			if (bob == 0) {
				lastTexture = 1;
				return this.getWagonTextures1(entity);
			} else if (bob == 2) {
				lastTexture = 2;
				return this.getWagonTextures2(entity);
			} else {
				lastTexture = 3;
				return this.getWagonTextures3(entity);
			}
		} else {
			if (lastTexture == 1) {
				return this.getWagonTextures1(entity);
			} else if (lastTexture == 2) {
				return this.getWagonTextures2(entity);
			} else {
				return this.getWagonTextures3(entity);
			}
		}

	}
	protected ResourceLocation getWagonTextures1(EntityWagon entity) {
		return RenderWagon.wagonTextures1;
	}

	protected ResourceLocation getWagonTextures2(EntityWagon entity) {
		return RenderWagon.wagonTextures2;
	}

	protected ResourceLocation getWagonTextures3(EntityWagon entity) {
		return RenderWagon.wagonTextures3;
	}

	public static class Factory<T extends EntityWagon> implements IRenderFactory<T>
	{
		@Override
		public Render<? super T> createRenderFor(RenderManager manager) {
			return new RenderWagon(manager);
		}
	}




}