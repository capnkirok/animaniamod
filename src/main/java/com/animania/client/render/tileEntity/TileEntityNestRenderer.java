package com.animania.client.render.tileEntity;

import java.util.Map;
import java.util.UUID;

import javax.annotation.Nullable;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelDragonHead;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.animania.client.models.ModelNest;
import com.animania.common.tileentities.TileEntityNest;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;

@SideOnly(Side.CLIENT)
public class TileEntityNestRenderer extends TileEntitySpecialRenderer<TileEntityNest>
{
	private static final ResourceLocation NEST_TEXTURE = new ResourceLocation("animania:textures/entity/tileentities/block_nest_white.png");
	public static TileEntityNestRenderer instance;
	private final ModelNest nest = new ModelNest();

	public void renderTileEntityAt(TileEntityNest te, double x, double y, double z, float partialTicks, int destroyStage)
	{
		EnumFacing enumfacing = EnumFacing.getFront(te.getBlockMetadata() & 7);
		GlStateManager.pushMatrix();
		GlStateManager.translate((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
		float f = te.getAnimationProgress(partialTicks);
		this.renderNest((float)x, (float)y, (float)z, enumfacing, (float)(1 * 360) / 16.0F, te.getNestType(), te.getPlayerProfile(), destroyStage, f);
		GlStateManager.popMatrix();
	}

	public void setRendererDispatcher(TileEntityRendererDispatcher rendererDispatcherIn)
	{
		super.setRendererDispatcher(rendererDispatcherIn);
		instance = this;
	}

	public void renderNest(float x, float y, float z, EnumFacing facing, float p_188190_5_, int nestType, @Nullable GameProfile profile, int destroyStage, float animateTicks)
	{
		ModelBase modelbase = this.nest;

		if (destroyStage >= 0)
		{
			this.bindTexture(DESTROY_STAGES[destroyStage]);
			GlStateManager.matrixMode(5890);
			GlStateManager.pushMatrix();
			GlStateManager.scale(4.0F, 2.0F, 1.0F);
			GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
			GlStateManager.matrixMode(5888);
		}
		else
		{
			switch (nestType)
			{
			case 0:
			default:
				this.bindTexture(NEST_TEXTURE);
				animateTicks = 0.0F;
				break;
			case 1:
				this.bindTexture(NEST_TEXTURE);
				animateTicks = 1.0F;
				break;
			case 2:
				this.bindTexture(NEST_TEXTURE);
				animateTicks = 2.0F;
				break;
			case 3:
				this.bindTexture(NEST_TEXTURE);
				animateTicks = 3.0F;
				break;
			case 4:
				this.bindTexture(NEST_TEXTURE);
				animateTicks = 4.0F;
				break;
			case 5:
				this.bindTexture(NEST_TEXTURE);
				animateTicks = 5.0F;
				break;
			case 6:
				this.bindTexture(NEST_TEXTURE);
				animateTicks = 6.0F;
				break;
			case 7:
				this.bindTexture(NEST_TEXTURE);
				animateTicks = 7.0F;
				break;
			case 8:
				this.bindTexture(NEST_TEXTURE);
				animateTicks = 8.0F;
				break;
			case 9:
				this.bindTexture(NEST_TEXTURE);
				animateTicks = 9.0F;
				break;
			case 10:
				this.bindTexture(NEST_TEXTURE);
				animateTicks = 10.0F;
				break;
			case 11:
				this.bindTexture(NEST_TEXTURE);
				animateTicks = 11.0F;
				break;
			case 12:
				this.bindTexture(NEST_TEXTURE);
				animateTicks = 12.0F;
				break;
			case 13:
				this.bindTexture(NEST_TEXTURE);
				animateTicks = 13.0F;
				break;
			case 14:
				this.bindTexture(NEST_TEXTURE);
				animateTicks = 14.0F;
				break;
			case 15:
				this.bindTexture(NEST_TEXTURE);
				animateTicks = 15.0F;
				break;
			}

		}

		GlStateManager.pushMatrix();
		GlStateManager.scale(-1.0F, -1.0F, 1.0F);
		GlStateManager.enableAlpha();

		modelbase.render((Entity)null, animateTicks, 0.0F, 0.0F, 0, 0.0F, 0.0625F);
		GlStateManager.popMatrix();

		if (destroyStage >= 0)
		{
			GlStateManager.matrixMode(5890);
			GlStateManager.popMatrix();
			GlStateManager.matrixMode(5888);
		}
	}
}