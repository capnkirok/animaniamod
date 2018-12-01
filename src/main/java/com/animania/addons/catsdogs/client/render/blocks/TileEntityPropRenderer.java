package com.animania.addons.catsdogs.client.render.blocks;

import java.util.HashMap;
import java.util.Map;

import com.animania.Animania;
import com.animania.addons.catsdogs.common.tileentity.TileEntityProp;
import com.animania.addons.catsdogs.common.tileentity.TileEntityProp.PropType;
import com.leviathanstudio.craftstudio.client.model.ModelCraftStudio;
import com.leviathanstudio.craftstudio.common.animation.simpleImpl.CSTileEntitySpecialRenderer;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityPropRenderer extends TileEntitySpecialRenderer<TileEntityProp>
{

	private static Map<PropType, ModelCraftStudio> models = new HashMap<PropType, ModelCraftStudio>();
	private static Map<PropType, ResourceLocation> textures = new HashMap<PropType, ResourceLocation>();

	static
	{
		for (PropType t : PropType.values())
		{
			String name = t.block;
			int height = 0;
			int width = 0;

			switch (t)
			{
			case CAT_BED_2:
			case CAT_BED_1:
				height = 32;
				width = 64;
				break;
			case CAT_TOWER:
			case DOG_PILLOW:
				height = 128;
				width = 128;
				break;
			case LITTER_BOX:
			case DOG_HOUSE:
				height = 64;
				width = 64;
				break;
			}

			ResourceLocation texture = new ResourceLocation("animania:textures/entity/tileentities/" + name + ".png");
			ModelCraftStudio model = new ModelCraftStudio(Animania.MODID, "model_" + name, height, width);

			models.put(t, model);
			textures.put(t, texture);
		}
	}

	public static TileEntityPropRenderer instance;

	@Override
	public void render(TileEntityProp te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
	{
		EnumFacing enumfacing = EnumFacing.getFront(te.getBlockMetadata() & 7);

		ModelCraftStudio model = models.get(te.type);
		ResourceLocation texture = textures.get(te.type);

		GlStateManager.pushMatrix();
		GlStateManager.translate(x + 0.5, y + 1.5D, z + 0.5);
		GlStateManager.multMatrix(CSTileEntitySpecialRenderer.ROTATION_CORRECTOR);
		GlStateManager.rotate(enumfacing.getHorizontalAngle(), 0, 1, 0);
		this.bindTexture(texture);
		model.render();
		GlStateManager.popMatrix();
	}

	@Override
	public void setRendererDispatcher(TileEntityRendererDispatcher rendererDispatcherIn)
	{
		super.setRendererDispatcher(rendererDispatcherIn);
		TileEntityPropRenderer.instance = this;
	}
}