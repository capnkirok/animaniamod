package com.animania.client.render.tileEntity;

import com.animania.client.models.ModelHamster;
import com.animania.client.models.ModelHamsterWheel;
import com.animania.common.entities.rodents.EntityHamster;
import com.animania.common.tileentities.TileEntityHamsterWheel;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityHamsterWheelRenderer extends TileEntitySpecialRenderer<TileEntityHamsterWheel>
{
	private static final ResourceLocation WHEEL_TEXTURE = new ResourceLocation("animania:textures/entity/tileentities/hamster_wheel.png");
	private static final ResourceLocation WHEEL_TEXTURE2 = new ResourceLocation("animania:textures/entity/tileentities/hamster_wheel2.png");
	private static final ResourceLocation WHEEL_TEXTURE3 = new ResourceLocation("animania:textures/entity/tileentities/hamster_wheel3.png");

	private static final ResourceLocation HAMSTER_TEXTURES = new ResourceLocation("animania:textures/entity/rodents/hamster_tarou.png");

	public static TileEntityHamsterWheelRenderer  instance;
	private final ModelHamsterWheel               wheel         = new ModelHamsterWheel();
	private static final ModelHamster model = new ModelHamster();
	private static EntityHamster entity;

	@Override
	public void renderTileEntityAt(TileEntityHamsterWheel te, double x, double y, double z, float partialTicks, int destroyStage) {
		EnumFacing enumfacing = EnumFacing.getFront(te.getBlockMetadata() & 7);
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		float f = 0;
		boolean isRunning = te.isRunning();
		this.renderWheel((float) x, (float) y, (float) z, enumfacing, 1 * 360 / 16.0F, te.rotateTimer, f, isRunning);
		GlStateManager.popMatrix();


		if (te.isRunning()) {

			//if(state.getBlock() != ModBlocks.cowJar) { // I don't know. But it seems for some reason the renderer gets called for minecraft:air in certain cases.
			//	return;
			//}
			if(entity == null && te.hasWorld()) {
				entity = new EntityHamster(te.getWorld());
				entity.setScaleForAge(false);
				entity.setHamsterStanding(true);
			}
			bindTexture(HAMSTER_TEXTURES);
			if(entity != null) {
				GlStateManager.pushMatrix();
				GlStateManager.translate(x + 0.5, y + 0.8, z + 0.5);
				//GlStateManager.rotate(RenderUtils.getFacingAngle(state), 0f, 1f, 0f);
				GlStateManager.rotate(180f, 0f, 0f, 1f);
				GlStateManager.rotate(270f, 0f, 1f, 0f);
				GlStateManager.scale(0.042, 0.042, 0.042);
				model.render(entity, 0f, 0f, 0f, 0f, 0f, 1f);
				GlStateManager.popMatrix();
			}
		}

	}

	@Override
	public void setRendererDispatcher(TileEntityRendererDispatcher rendererDispatcherIn) {
		super.setRendererDispatcher(rendererDispatcherIn);
		TileEntityHamsterWheelRenderer.instance = this;
	}

	public void renderWheel(float x, float y, float z, EnumFacing facing, float p_188190_5_, int stage, float animateTicks, boolean isRunning) {
		ModelBase modelbase = this.wheel;

		if (isRunning) {
			if (stage == 0) {
				this.bindTexture(TileEntityHamsterWheelRenderer.WHEEL_TEXTURE);
			} else if (stage == 1) {
				this.bindTexture(TileEntityHamsterWheelRenderer.WHEEL_TEXTURE2);
			} else if (stage == 2) {
				this.bindTexture(TileEntityHamsterWheelRenderer.WHEEL_TEXTURE3);
			}
		} else {
			this.bindTexture(TileEntityHamsterWheelRenderer.WHEEL_TEXTURE);
		}
		animateTicks = 0.0F;

		GlStateManager.pushMatrix();
		GlStateManager.scale(-1.0F, -1.0F, 1.0F);
		GlStateManager.enableAlpha();

		modelbase.render((Entity) null, animateTicks, 0.0F, 0.0F, 0, 0.0F, 0.0625F);
		GlStateManager.popMatrix();



	}
}