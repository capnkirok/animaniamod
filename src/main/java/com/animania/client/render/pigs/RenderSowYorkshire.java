package com.animania.client.render.pigs;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.animania.client.models.ModelSow;
import com.animania.client.render.pigs.layers.LayerMudSowYorkshire;
import com.animania.common.entities.pigs.EntitySowYorkshire;
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
public class RenderSowYorkshire<T extends EntitySowYorkshire> extends RenderLiving<T>
{
    public static final Factory           FACTORY            = new Factory();

    private static final ResourceLocation PIG_TEXTURES       = new ResourceLocation("animania:textures/entity/pigs/sow_yorkshire.png");
    private static final ResourceLocation PIG_TEXTURES_BLINK = new ResourceLocation("animania:textures/entity/pigs/sow_yorkshire_blink.png");

    public RenderSowYorkshire(RenderManager rm) {
        super(rm, new ModelSow(), 0.5F);
        this.addLayer(new LayerMudSowYorkshire(this));
    }

    protected void preRenderScale(T entity, float f) {

        GL11.glScalef(1.0F, 1.0F, 1.0F);

        double x = entity.posX;
        double y = entity.posY;
        double z = entity.posZ;

        BlockPos pos = new BlockPos(x, y, z);
        Random rand = new Random();

        Block blockchk = entity.world.getBlockState(pos).getBlock();

        if (blockchk == BlockHandler.blockMud && !entity.getMuddy()) {
            GlStateManager.translate(0.0F, entity.height - 1.45F, 0.0F);
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

            if (!entity.hasPath()) {
                GlStateManager.translate(0.0F, entity.height - 1.45F, 0.0F);
                GlStateManager.rotate(86.0F, 0.0F, 0.0F, 1.0F);
            }

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
            return RenderSowYorkshire.PIG_TEXTURES_BLINK;
        else
            return RenderSowYorkshire.PIG_TEXTURES;
    }

    static class Factory<T extends EntitySowYorkshire> implements IRenderFactory<T>
    {
        @Override
        public Render<? super T> createRenderFor(RenderManager manager) {
            return new RenderSowYorkshire(manager);
        }
    }
}