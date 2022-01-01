package client.render.layer;

import client.render.cows.RenderBullMooshroom;
import com.animania.addons.farm.common.entity.cows.CowMooshroom.EntityBullMooshroom;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Dist.CLIENT)
public class LayerBullMooshroomMushroom implements LayerRenderer<EntityBullMooshroom>
{
	private final RenderBullMooshroom mooshroomRenderer;

	public LayerBullMooshroomMushroom(RenderBullMooshroom mooshroomRendererIn)
	{
		this.mooshroomRenderer = mooshroomRendererIn;
	}

	public void doRenderLayer(EntityBullMooshroom LivingEntityIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		if (!LivingEntityIn.isChild() && !LivingEntityIn.isInvisible())
		{
			BlockRendererDispatcher blockrendererdispatcher = Minecraft.getInstance().getBlockRendererDispatcher();
			this.mooshroomRenderer.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
			GlStateManager.enableCull();
			GlStateManager.cullFace(GlStateManager.CullFace.FRONT);
			GlStateManager.pushMatrix();
			GlStateManager.scale(1.0F, -1.0F, 1.0F);
			GlStateManager.translate(0.2F, 0.15F, 0.5F);
			GlStateManager.rotate(42.0F, 0.0F, 1.0F, 0.0F);
			GlStateManager.pushMatrix();
			GlStateManager.translate(-0.5F, -0.5F, 0.5F);
			blockrendererdispatcher.renderBlockBrightness(Blocks.RED_MUSHROOM.defaultBlockState(), 1.0F);
			GlStateManager.popMatrix();
			GlStateManager.pushMatrix();
			GlStateManager.translate(0.1F, 0.0F, -0.6F);
			GlStateManager.rotate(42.0F, 0.0F, 1.0F, 0.0F);
			GlStateManager.translate(-0.5F, -0.5F, 0.5F);
			blockrendererdispatcher.renderBlockBrightness(Blocks.RED_MUSHROOM.defaultBlockState(), 1.0F);
			GlStateManager.popMatrix();
			GlStateManager.popMatrix();
			GlStateManager.pushMatrix();
			// this.mooshroomRenderer.getMainModel().head.postRender(0.0625F);
			GlStateManager.scale(1.0F, -1.0F, 1.0F);
			GlStateManager.translate(0.0F, 0.3F, -0.2F);
			GlStateManager.rotate(12.0F, 0.0F, 1.0F, 0.0F);
			GlStateManager.translate(-0.5F, -0.5F, 0.5F);
			blockrendererdispatcher.renderBlockBrightness(Blocks.RED_MUSHROOM.defaultBlockState(), 1.0F);
			GlStateManager.popMatrix();
			GlStateManager.cullFace(GlStateManager.CullFace.BACK);
			GlStateManager.disableCull();
		}
	}

	public boolean shouldCombineTextures()
	{
		return true;
	}
}