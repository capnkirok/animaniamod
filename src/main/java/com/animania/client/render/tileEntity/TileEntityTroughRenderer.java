package com.animania.client.render.tileEntity;

import javax.annotation.Nullable;

import com.animania.client.models.ModelTrough;
import com.animania.common.tileentities.TileEntityTrough;
import com.mojang.authlib.GameProfile;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityTroughRenderer extends TileEntitySpecialRenderer<TileEntityTrough>
{
    private static final ResourceLocation  TROUGH_EMPTY_TEXTURE = new ResourceLocation("animania:textures/entity/tileentities/block_trough.png");
    public static TileEntityTroughRenderer instance;
    private final ModelTrough              trough               = new ModelTrough();

    @Override
    public void renderTileEntityAt(TileEntityTrough te, double x, double y, double z, float partialTicks, int destroyStage) {
        EnumFacing enumfacing = EnumFacing.getFront(te.getBlockMetadata() & 7);
        float f = te.getAnimationProgress(partialTicks);
        this.renderTrough((float) x, (float) y, (float) z, enumfacing, te.getTroughRotation() * 360 / 16.0F, te.getTroughType(),
                te.getPlayerProfile(), destroyStage, f);
    }

    @Override
    public void setRendererDispatcher(TileEntityRendererDispatcher rendererDispatcherIn) {
        super.setRendererDispatcher(rendererDispatcherIn);
        TileEntityTroughRenderer.instance = this;
    }

    public void renderTrough(float x, float y, float z, EnumFacing facing, float p_188190_5_, int troughType, @Nullable GameProfile profile,
            int destroyStage, float animateTicks) {
        ModelBase modelbase = this.trough;

        if (destroyStage >= 0) {
            this.bindTexture(TileEntitySpecialRenderer.DESTROY_STAGES[destroyStage]);
            GlStateManager.matrixMode(5890);
            GlStateManager.pushMatrix();
            GlStateManager.scale(4.0F, 2.0F, 1.0F);
            GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
            GlStateManager.matrixMode(5888);
        }
        else
            switch (troughType) {
                case 0:
                default:
                    this.bindTexture(TileEntityTroughRenderer.TROUGH_EMPTY_TEXTURE);
                    animateTicks = 0.0F;
                    break;
                case 1:
                    this.bindTexture(TileEntityTroughRenderer.TROUGH_EMPTY_TEXTURE);
                    animateTicks = 1.0F;
                    break;
                case 2:
                    this.bindTexture(TileEntityTroughRenderer.TROUGH_EMPTY_TEXTURE);
                    animateTicks = 2.0F;
                    break;
                case 3:
                    this.bindTexture(TileEntityTroughRenderer.TROUGH_EMPTY_TEXTURE);
                    animateTicks = 3.0F;
                    break;
                case 4:
                    this.bindTexture(TileEntityTroughRenderer.TROUGH_EMPTY_TEXTURE);
                    animateTicks = 4.0F;
                    break;
                case 5:
                    this.bindTexture(TileEntityTroughRenderer.TROUGH_EMPTY_TEXTURE);
                    animateTicks = 5.0F;
                    break;
                case 6:
                    this.bindTexture(TileEntityTroughRenderer.TROUGH_EMPTY_TEXTURE);
                    animateTicks = 6.0F;
                    break;
                case 7:
                    this.bindTexture(TileEntityTroughRenderer.TROUGH_EMPTY_TEXTURE);
                    animateTicks = 7.0F;
                    break;
                case 8:
                    this.bindTexture(TileEntityTroughRenderer.TROUGH_EMPTY_TEXTURE);
                    animateTicks = 8.0F;
                    break;
                case 9:
                    this.bindTexture(TileEntityTroughRenderer.TROUGH_EMPTY_TEXTURE);
                    animateTicks = 9.0F;
                    break;

            }

        GlStateManager.pushMatrix();
        GlStateManager.disableCull();
        Float rot = 0.0F;

        if (facing == EnumFacing.UP)
            GlStateManager.translate(x, y, z);
        else
            switch (facing) {

                case NORTH:
                    GlStateManager.translate(x + 1.5, y + 1.5F, z + .5);
                    break;
                case SOUTH:
                    GlStateManager.translate(x - .5, y + 1.5F, z + .5);
                    GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
                    break;
                case WEST:
                    GlStateManager.translate(x + .5, y + 1.5F, z - 0.5);
                    GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
                    break;
                case EAST:
                default:
                    GlStateManager.translate(x + .5, y + 1.5F, z + 1.5);
                    GlStateManager.rotate(270.0F, 0.0F, 1.0F, 0.0F);

            }

        float f = 0.0625F;
        GlStateManager.enableRescaleNormal();
        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
        GlStateManager.enableAlpha();

        modelbase.render((Entity) null, animateTicks, 0.0F, 0.0F, 0, 0.0F, 0.0625F);
        GlStateManager.popMatrix();

        if (destroyStage >= 0) {
            GlStateManager.matrixMode(5890);
            GlStateManager.popMatrix();
            GlStateManager.matrixMode(5888);
        }
    }
}