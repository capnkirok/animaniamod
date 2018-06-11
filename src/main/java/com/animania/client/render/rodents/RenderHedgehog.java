package com.animania.client.render.rodents;

import org.lwjgl.opengl.GL11;

import com.animania.client.models.ModelHedgehog;
import com.animania.common.entities.rodents.EntityHedgehog;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderHedgehog<T extends EntityHedgehog> extends RenderLiving<T>
{
    public static final Factory           FACTORY                 = new Factory();

    private static final ResourceLocation HEDGEHOG_TEXTURES       = new ResourceLocation("animania:textures/entity/rodents/hedgehog.png");
    private static final ResourceLocation HEDGEHOG_TEXTURES_BLINK = new ResourceLocation("animania:textures/entity/rodents/hedgehog_blink.png");
    private static final ResourceLocation SONIC_TEXTURES          = new ResourceLocation("animania:textures/entity/rodents/hedgehog_sonic.png");
    private static final ResourceLocation SANIC_TEXTURES          = new ResourceLocation("animania:textures/entity/rodents/hedgehog_sanic.png");

    public RenderHedgehog(RenderManager rm) {
        super(rm, new ModelHedgehog(), 0.3F);
    }

    protected void preRenderScale(T entity, float f) {
        if (entity.isRiding()) {

            if (entity.getRidingEntity() instanceof EntityPlayerSP) {
                GL11.glScalef(0.4F, 0.4F, 0.4F);
                EntityPlayer player = (EntityPlayer) entity.getRidingEntity();
                entity.rotationYaw = player.rotationYaw;
                if (player.isSneaking())
                    GlStateManager.translate(-1.0F, entity.height - .1F, .1F);
                else
                    GlStateManager.translate(-1.0F, entity.height - .2F, .1F);
            }

        }
        else {
            GL11.glScalef(0.6F, 0.6F, 0.6F);
            
            if (entity.isHedgehogSitting()) {
            	GlStateManager.translate(0F, +.1F, .0F);
            }

            if (entity.getCustomNameTag().equals("Sanic")) {
                GL11.glRotatef(20, -1, 0, 1);
                GL11.glScalef(1.2F, 1.7F, 1.6F);
            }
        }

    }

    @Override
    protected void preRenderCallback(T entityliving, float f) {
        this.preRenderScale(entityliving, f);
    }

    @Override
    protected ResourceLocation getEntityTexture(T entity) {
        if (entity.getCustomNameTag().equals("Sonic"))
            return RenderHedgehog.SONIC_TEXTURES;
        else if (entity.getCustomNameTag().equals("Sanic"))
            return RenderHedgehog.SANIC_TEXTURES;
        else {
            int blinkTimer = entity.blinkTimer;
            if (blinkTimer < 5 && blinkTimer >= 0)
                return RenderHedgehog.HEDGEHOG_TEXTURES_BLINK;
            else
                return RenderHedgehog.HEDGEHOG_TEXTURES;
        }
    }

    @Override
    public ModelHedgehog getMainModel() {
        return (ModelHedgehog) super.getMainModel();
    }

    static class Factory<T extends EntityHedgehog> implements IRenderFactory<T>
    {
        @Override
        public Render<? super T> createRenderFor(RenderManager manager) {
            return new RenderHedgehog(manager);
        }
    }
}