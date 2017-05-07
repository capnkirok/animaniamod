package com.animania.client.render.chickens;

import org.lwjgl.opengl.GL11;

import com.animania.client.models.ModelHen;
import com.animania.common.entities.chickens.EntityHenOrpington;
import com.animania.common.handler.BlockHandler;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderHenOrpington extends RenderLiving<EntityHenOrpington> {

	public RenderHenOrpington(RenderManager rm) {
		super(rm, new ModelHen(), 0.3F);
	}

	@Override
	protected float handleRotationFloat(EntityHenOrpington livingBase, float partialTicks) {
		float f = livingBase.oFlap + (livingBase.wingRotation - livingBase.oFlap) * partialTicks;
		float f1 = livingBase.oFlapSpeed + (livingBase.destPos - livingBase.oFlapSpeed) * partialTicks;
		return (MathHelper.sin(f) + 1.0F) * f1;
	}

	@Override
	protected void preRenderCallback(EntityHenOrpington entityliving, float f) {
		preRenderScale(entityliving, f);
	}

	protected void preRenderScale(EntityHenOrpington entity, float f) {
		GL11.glScalef(1.08F, 1.08F, 1.08F);

		double x = entity.posX;
		double y = entity.posY;
		double z = entity.posZ;

		BlockPos pos = new BlockPos(x, y, z);

		Block blockchk = entity.world.getBlockState(pos).getBlock();

		if (blockchk == BlockHandler.blockNest) {
			GlStateManager.translate(-0.25F, 0.35F, -0.25F);
		}

	}

	@Override
	protected ResourceLocation getEntityTexture(EntityHenOrpington entity) {
		int blinkTimer = entity.blinkTimer;

		if (blinkTimer < 5 && blinkTimer >= 0) {
			return entity.getResourceLocationBlink();
		} else {
			return entity.getResourceLocation();
		}
	}

}
