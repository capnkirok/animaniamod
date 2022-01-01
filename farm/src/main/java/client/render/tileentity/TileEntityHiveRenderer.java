package client.render.tileentity;

import com.animania.Animania;
import com.animania.addons.farm.common.tileentity.BlockEntityHive;
import com.leviathanstudio.craftstudio.client.model.ModelCraftStudio;
import com.leviathanstudio.craftstudio.common.animation.simpleImpl.CSBlockEntitySpecialRenderer;
import com.mojang.blaze3d.platform.GlStateManager;
import common.handler.FarmAddonBlockHandler;
import net.minecraft.client.renderer.BlockEntity.BlockEntityRendererDispatcher;
import net.minecraft.client.renderer.BlockEntity.BlockEntitySpecialRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Dist.CLIENT)
public class BlockEntityHiveRenderer extends BlockEntitySpecialRenderer<BlockEntityHive>
{
	private static final ResourceLocation HIVE_TEXTURE = new ResourceLocation("animania:textures/entity/props/bee_hive.png");

	private ModelCraftStudio modelBeeHive = new ModelCraftStudio(Animania.MODID, "model_bee_hive", 128, 64);
	private ModelCraftStudio modelWildHive = new ModelCraftStudio(Animania.MODID, "model_wild_hive", 128, 64);

	public static BlockEntityHiveRenderer instance;

	@Override
	public void render(BlockEntityHive te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
	{

		Direction enumfacing = Direction.getFront(te.getBlockMetadata() & 7);
		GlStateManager.pushMatrix();
		this.bindTexture(HIVE_TEXTURE);
		if (te.getHiveType() == FarmAddonBlockHandler.blockHive)
		{
			GlStateManager.translate(x + 0.5D, y + 1.5D, z + 0.5D);
			GlStateManager.multMatrix(CSBlockEntitySpecialRenderer.ROTATION_CORRECTOR);
			GlStateManager.rotate(enumfacing.getHorizontalAngle(), 0, 1, 0);
			this.modelBeeHive.render(te);
		}
		else
		{
			if (enumfacing == Direction.NORTH)
			{
				GlStateManager.translate(x + 0.5D, y + 1D, z + 0.75D);
			}
			else if (enumfacing == Direction.SOUTH)
			{
				GlStateManager.translate(x + 0.5D, y + 1D, z + 0.25D);
			}
			else if (enumfacing == Direction.EAST || enumfacing != Direction.WEST)
			{
				GlStateManager.translate(x + 0.25D, y + 1D, z + 0.5D);
			}
			else
			{
				GlStateManager.translate(x + 0.75D, y + 1D, z + 0.5D);
			}
			GlStateManager.multMatrix(CSBlockEntitySpecialRenderer.ROTATION_CORRECTOR);
			GlStateManager.rotate(enumfacing.getHorizontalAngle(), 0, 1, 0);
			this.modelWildHive.render(te);
		}
		GlStateManager.popMatrix();
	}

	@Override
	public void setRendererDispatcher(BlockEntityRendererDispatcher rendererDispatcherIn)
	{
		super.setRendererDispatcher(rendererDispatcherIn);
		BlockEntityHiveRenderer.instance = this;
	}
}