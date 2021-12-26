package com.animania.addons.catsdogs.client.render.blocks;

import net.minecraft.world.level.Level;

import java.util.HashMap;
import java.util.Map;

import com.animania.Animania;
import com.animania.addons.catsdogs.common.BlockEntity.BlockEntityProp;
import com.animania.addons.catsdogs.common.BlockEntity.BlockEntityProp.PropType;
import com.leviathanstudio.craftstudio.client.model.ModelCraftStudio;
import com.leviathanstudio.craftstudio.common.animation.simpleImpl.CSBlockEntitySpecialRenderer;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.renderer.BlockEntity.BlockEntityRendererDispatcher;
import net.minecraft.client.renderer.BlockEntity.BlockEntitySpecialRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Dist.CLIENT)
public class BlockEntityPropRenderer extends BlockEntitySpecialRenderer<BlockEntityProp>
{

	private static Map<PropType, ModelCraftStudio> models = new HashMap<PropType, ModelCraftStudio>();
	private static Map<PropType, ResourceLocation> textures = new HashMap<>();

	static
	{
		for (PropType t : PropType.values())
		{
			String name = t.block;
			int height = 0;
			int width;

			width = switch (t)
			{
			case CAT_BED_2, CAT_BED_1 ->
			{
				height = 32;
				yield 64;
			}
			case CAT_TOWER, DOG_PILLOW ->
			{
				height = 128;
				yield 128;
			}
			case LITTER_BOX, DOG_HOUSE ->
			{
				height = 64;
				yield 64;
			}
			};

			ResourceLocation texture = new ResourceLocation("animania:textures/entity/tileentities/" + name + ".png");
			ModelCraftStudio model = new ModelCraftStudio(Animania.MODID, "model_" + name, height, width);

			models.put(t, model);
			textures.put(t, texture);
		}
	}

	public static BlockEntityPropRenderer instance;

	@Override
	public void render(BlockEntityProp te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
	{
		Direction enumfacing = Direction.getFront(te.getBlockMetadata() & 7);

		ModelCraftStudio model = models.get(te.type);
		ResourceLocation texture = textures.get(te.type);

		GlStateManager.pushMatrix();
		GlStateManager.translate(x + 0.5, y + 1.5D, z + 0.5);
		GlStateManager.multMatrix(CSBlockEntitySpecialRenderer.ROTATION_CORRECTOR);
		GlStateManager.rotate(enumfacing.getHorizontalAngle(), 0, 1, 0);
		this.bindTexture(texture);
		model.render();
		GlStateManager.popMatrix();
	}

	@Override
	public void setRendererDispatcher(BlockEntityRendererDispatcher rendererDispatcherIn)
	{
		super.setRendererDispatcher(rendererDispatcherIn);
		BlockEntityPropRenderer.instance = this;
	}
}