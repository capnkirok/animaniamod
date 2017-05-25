package com.animania.client.render.pigs;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.animania.client.models.ModelPiglet;
import com.animania.client.render.pigs.layers.LayerMudPigletOldSpot;
import com.animania.common.entities.pigs.EntityPigletOldSpot;
import com.animania.common.handler.BlockHandler;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPigletOldSpot<T extends EntityPigletOldSpot> extends RenderLiving<T>
{
    public static final Factory           FACTORY            = new Factory();

    private static final ResourceLocation PIG_TEXTURES       = new ResourceLocation("animania:textures/entity/pigs/piglet_old_spot.png");
    private static final ResourceLocation PIG_TEXTURES_BLINK = new ResourceLocation("animania:textures/entity/pigs/piglet_old_spot_blink.png");

    public RenderPigletOldSpot(RenderManager rm) {
        super(rm, new ModelPiglet(), 0.3F);
        this.addLayer(new LayerMudPigletOldSpot(this));
    }

    protected void preRenderScale(T entity, float f) {
        float age = entity.getEntityAge();
        GL11.glScalef(1.1F + age, 1.1F + age, 1.1F + age);

        double x = entity.posX;
        double y = entity.posY;
        double z = entity.posZ;

        BlockPos pos = new BlockPos(x, y, z);
        Random rand = new Random();

        Block blockchk = entity.world.getBlockState(pos).getBlock();

        if (blockchk == BlockHandler.blockMud && !entity.getMuddy()) {
            GlStateManager.translate(0.0F, entity.height - .50F + age * .1F, 0.0F);
            GlStateManager.rotate(86.0F, 0.0F, 0.0F, 1.0F);
            entity.setMuddy(true);
            entity.setMudTimer(1.0F);
            entity.setSplashTimer(1.0F);
        }
        else if (entity.isWet() && entity.getMuddy()) {
            entity.setMuddy(false);
            entity.setMudTimer(0.0F);
            entity.setSplashTimer(0.0F);
        }
        else if (blockchk == BlockHandler.blockMud) {
            Float splashTimer = entity.getSplashTimer();
            GlStateManager.translate(0.0F, entity.height - .50F + age * .1F, 0.0F);
            GlStateManager.rotate(86.0F, 0.0F, 0.0F, 1.0F);

            splashTimer = splashTimer - 0.045F;
            entity.setSplashTimer(splashTimer);
            if (splashTimer <= 0.0F) {
                entity.setMuddy(true);
                entity.setMudTimer(1.0F);
            }

        }
        else if (entity.getMudTimer() > 0) {
            entity.setMuddy(false);
            float mudTimer = entity.getMudTimer();
            if (rand.nextInt(3) < 1) {
                mudTimer = mudTimer - 0.0025F;
                entity.setMudTimer(mudTimer);
            }
        }

    }

    @Override
    protected void preRenderCallback(T entityliving, float f) {
        this.preRenderScale(entityliving, f);
    }

    @Override
    protected ResourceLocation getEntityTexture(T entity) {

        int blinkTimer = entity.blinkTimer;

        if (blinkTimer < 7 && blinkTimer >= 0)
            return RenderPigletOldSpot.PIG_TEXTURES_BLINK;
        else
            return RenderPigletOldSpot.PIG_TEXTURES;
    }

    static class Factory<T extends EntityPigletOldSpot> implements IRenderFactory<T>
    {
        @Override
        public Render<? super T> createRenderFor(RenderManager manager) {
            return new RenderPigletOldSpot(manager);
        }
    }
}