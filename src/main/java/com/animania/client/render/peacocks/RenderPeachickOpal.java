package com.animania.client.render.peacocks;

import org.lwjgl.opengl.GL11;

import com.animania.client.models.ModelPeachick;
import com.animania.common.entities.peacocks.EntityAnimaniaPeacock;
import com.animania.common.entities.peacocks.EntityPeachickOpal;
import com.animania.common.handler.BlockHandler;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPeachickOpal<T extends EntityPeachickOpal> extends RenderLiving<T>
{
    public static final Factory FACTORY = new Factory();

    public RenderPeachickOpal(RenderManager rm) {
        super(rm, new ModelPeachick(), 0.15F);
    }

    @Override
    protected float handleRotationFloat(T livingBase, float partialTicks) {
        float f = livingBase.oFlap + (livingBase.wingRotation - livingBase.oFlap) * partialTicks;
        float f1 = livingBase.oFlapSpeed + (livingBase.destPos - livingBase.oFlapSpeed) * partialTicks;
        return (MathHelper.sin(f) + 1.0F) * f1;
    }

    @Override
    protected void preRenderCallback(T entityliving, float f) {
        this.preRenderScale(entityliving, f);
    }

    protected void preRenderScale(T entity, float f) {
        float age = entity.getEntityAge();
        GL11.glScalef(0.3F + age, 0.3F + age, 0.3F + age);

        double x = entity.posX;
		double y = entity.posY;
		double z = entity.posZ;

		BlockPos pos = new BlockPos(x, y, z);

		Block blockchk = entity.world.getBlockState(pos).getBlock();
        boolean isSleeping = false;
		EntityAnimaniaPeacock entityChk = (EntityAnimaniaPeacock) entity;
		if (entityChk.getSleeping()) {
			isSleeping = true;
		}
		if (blockchk == BlockHandler.blockNest || isSleeping ) {
			isSleeping = true;
		}
		
		if (isSleeping ) {
			GlStateManager.translate(-0.25F, 0.35F, -0.25F);
			this.shadowSize = 0;
		} else {
			this.shadowSize = 0.3F;
			entityChk.setSleeping(false);
		}

	}

	@Override
	protected ResourceLocation getEntityTexture(T entity) {
		int blinkTimer = entity.blinkTimer;
		long currentTime = entity.world.getWorldTime() % 23999;
		boolean isSleeping = false;

		EntityAnimaniaPeacock entityChk = (EntityAnimaniaPeacock) entity;
		isSleeping = entityChk.getSleeping();

		if (isSleeping && currentTime < 23250) {
			return entity.getResourceLocationBlink();
		} else if (blinkTimer < 5 && blinkTimer >= 0) {
			return entity.getResourceLocationBlink();
		} else {
			return entity.getResourceLocation();
		}

	}

    static class Factory<T extends EntityPeachickOpal> implements IRenderFactory<T>
    {
        @Override
        public Render<? super T> createRenderFor(RenderManager manager) {
            return new RenderPeachickOpal(manager);
        }
    }

}