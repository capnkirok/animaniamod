package com.animania.proxy;

import com.animania.Animania;
import com.animania.client.AnimaniaTextures;
import com.animania.client.handler.RenderHandler;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import scala.tools.nsc.Global;

public class ClientProxy extends CommonProxy {

	@Override
	public void preInit() {
		super.preInit();
		RenderHandler.preInit();
		AnimaniaTextures.registerTextures();

	}

	@Override
	public void init() {
		super.init();
		RenderHandler.init();
	}
	
	
	@Override
	public void registerFluidBlockRendering(Block block, String name) {
		name = name.toLowerCase();
		super.registerFluidBlockRendering(block, name);
		FluidStateMapper mapper = new FluidStateMapper(name);
		
		Item item = Item.getItemFromBlock(block);
		ModelBakery.registerItemVariants(item);
		ModelLoader.setCustomMeshDefinition(item, mapper);

		ModelLoader.setCustomStateMapper(block, mapper);
	}
	
	
	public static class FluidStateMapper extends StateMapperBase implements ItemMeshDefinition {
		public final ModelResourceLocation location;

		public FluidStateMapper(String name) {
			location = new ModelResourceLocation(Animania.MODID + ":fluids", name);
		}

		protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
			return location;
		}

		public ModelResourceLocation getModelLocation(ItemStack stack) {
			return location;
		}
	}

}