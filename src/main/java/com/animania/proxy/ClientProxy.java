package com.animania.proxy;

import java.io.File;
import java.util.List;

import com.animania.Animania;
import com.animania.addons.AddonResourcePack;
import com.animania.api.addons.AnimaniaAddon;
import com.animania.client.AnimaniaTextures;
import com.animania.client.handler.RenderHandler;
import com.animania.common.handler.AddonHandler;
import com.animania.common.items.ItemEntityEgg;
import com.animania.manual.gui.GuiManual;
import com.animania.manual.resources.ManualResourceLoader;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiErrorScreen;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.SimpleReloadableResourceManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.CustomModLoadingErrorDisplayException;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.MultipleModsErrored;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ClientProxy extends CommonProxy
{

	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		super.preInit(event);
		RenderHandler.preInit();
		AnimaniaTextures.registerTextures();

		AddonHandler.preInitClient();
	}

	@Override
	public void init(FMLInitializationEvent event)
	{
		super.init(event);
		RenderHandler.init();

		for (Item item : ForgeRegistries.ITEMS.getValues())
		{
			if (item instanceof ItemEntityEgg)
			{
				FMLClientHandler.instance().getClient().getItemColors().registerItemColorHandler(new ItemEntityEgg.Color(), item);
			}
		}

		AddonHandler.initClient();
	}

	@Override
	public void postInit(FMLPostInitializationEvent event)
	{
		super.postInit(event);
		ManualResourceLoader.loadResources();
	}

	@Override
	public void registerFluidBlockRendering(Block block, String name)
	{
		name = name.toLowerCase();
		super.registerFluidBlockRendering(block, name);
		FluidStateMapper mapper = new FluidStateMapper(name);

		Item item = Item.getItemFromBlock(block);
		ModelBakery.registerItemVariants(item);
		ModelLoader.setCustomMeshDefinition(item, mapper);

		ModelLoader.setCustomStateMapper(block, mapper);
	}

	@Override
	public void reloadManual()
	{
		ManualResourceLoader.loadResources();
	}

	public static class FluidStateMapper extends StateMapperBase implements ItemMeshDefinition
	{
		public final ModelResourceLocation location;

		public FluidStateMapper(String name)
		{
			this.location = new ModelResourceLocation(Animania.MODID + ":fluids", name);
		}

		@Override
		protected ModelResourceLocation getModelResourceLocation(IBlockState state)
		{
			return this.location;
		}

		@Override
		public ModelResourceLocation getModelLocation(ItemStack stack)
		{
			return this.location;
		}
	}

	@Override
	public void openManualGui(ItemStack stack)
	{
		GuiManual manual = GuiManual.getInstance(stack);
		Minecraft.getMinecraft().displayGuiScreen(manual);
	}

	// Sleep
	@Override
	public void Sleep(PlayerEntity PlayerEntity)
	{

		long currentTime = 0;
		int factorTime = 0;

		for (int j = 0; j < Playerentity.level.getMinecraftServer().getServer().worlds.length; ++j)
		{
			currentTime = Playerentity.level.getMinecraftServer().getServer().worlds[j].getWorldTime() % 24000;
			factorTime = 24000 - (int) currentTime;
			Playerentity.level.getMinecraftServer().getServer().worlds[j].setWorldTime(Playerentity.level.getMinecraftServer().getServer().worlds[j].getWorldTime() + factorTime);
		}

	}

	@Override
	public void addAddonResourcePack(AnimaniaAddon addon)
	{
		List<IResourcePack> packs = ObfuscationReflectionHelper.getPrivateValue(Minecraft.class, Minecraft.getMinecraft(), "field_110449_ao");
		File animania = Loader.instance().activeModContainer().getSource();

		IResourcePack pack = null;
		if (animania.isDirectory())
			pack = new AddonResourcePack.Folder(addon);
		else
			pack = new AddonResourcePack.Jar(addon);

		packs.add(pack);
		IResourceManager res = Minecraft.getMinecraft().getResourceManager();
		if (res instanceof SimpleReloadableResourceManager)
		{
			((SimpleReloadableResourceManager) res).reloadResourcePack(pack);
			Minecraft.getMinecraft().getLanguageManager().onResourceManagerReload(res);
		}
	}

	@Override
	public void throwCustomModLoadingErrorDisplayException(MultipleModsErrored errors)
	{
		CustomModLoadingErrorDisplayException customEx = new CustomModLoadingErrorDisplayException("Addon Loading Errors", errors) {

			private GuiScreen screen;

			@Override
			public void initGui(GuiErrorScreen errorScreen, FontRenderer fontRenderer)
			{
				screen = errors.createGui();
				screen.setWorldAndResolution(Minecraft.getMinecraft(), errorScreen.width, errorScreen.height);
			}

			@Override
			public void drawScreen(GuiErrorScreen errorScreen, FontRenderer fontRenderer, int mouseRelX, int mouseRelY, float tickTime)
			{
				screen.drawScreen(mouseRelX, mouseRelY, tickTime);
			}

		};

		throw customEx;
	}

}