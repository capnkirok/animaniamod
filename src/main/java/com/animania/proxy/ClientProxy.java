package com.animania.proxy;

import java.io.File;
import java.util.List;

import com.animania.Animania;
import com.animania.addons.AddonResourcePack;
import com.animania.addons.AnimaniaAddon;
import com.animania.client.AnimaniaTextures;
import com.animania.client.handler.RenderHandler;
import com.animania.common.handler.AddonHandler;
import com.animania.common.items.ItemEntityEgg;
import com.animania.manual.gui.GuiManual;
import com.animania.manual.resources.ManualResourceLoader;
import com.leviathanstudio.craftstudio.client.registry.CSRegistryHelper;
import com.leviathanstudio.craftstudio.client.registry.CraftStudioLoader;
import com.leviathanstudio.craftstudio.client.util.EnumRenderType;
import com.leviathanstudio.craftstudio.client.util.EnumResourceType;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.SimpleReloadableResourceManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

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

	@CraftStudioLoader
	public static void registerCraftStudioAssets()
	{
		CSRegistryHelper csRegistry = new CSRegistryHelper(Animania.MODID);

		csRegistry.register(EnumResourceType.MODEL, EnumRenderType.BLOCK, "model_hamster_wheel");
		csRegistry.register(EnumResourceType.MODEL, EnumRenderType.BLOCK, "model_bee_hive");
		csRegistry.register(EnumResourceType.MODEL, EnumRenderType.BLOCK, "model_wild_hive");
		csRegistry.register(EnumResourceType.MODEL, EnumRenderType.ENTITY, "hamster");
		csRegistry.register(EnumResourceType.MODEL, EnumRenderType.ENTITY, "model_wagon");
		csRegistry.register(EnumResourceType.MODEL, EnumRenderType.ENTITY, "model_cart");
		csRegistry.register(EnumResourceType.MODEL, EnumRenderType.ENTITY, "model_cart_chest");
		csRegistry.register(EnumResourceType.MODEL, EnumRenderType.ENTITY, "model_tiller");
		
		csRegistry.register(EnumResourceType.ANIM, EnumRenderType.BLOCK, "anim_hamster_wheel");
		csRegistry.register(EnumResourceType.ANIM, EnumRenderType.BLOCK, "anim_bees");
		csRegistry.register(EnumResourceType.ANIM, EnumRenderType.BLOCK, "anim_bees_wild");
		csRegistry.register(EnumResourceType.ANIM, EnumRenderType.ENTITY, "hamster_run");
		csRegistry.register(EnumResourceType.ANIM, EnumRenderType.ENTITY, "anim_cart");
		csRegistry.register(EnumResourceType.ANIM, EnumRenderType.ENTITY, "anim_cart_chest");
		csRegistry.register(EnumResourceType.ANIM, EnumRenderType.ENTITY, "anim_wagon");
		csRegistry.register(EnumResourceType.ANIM, EnumRenderType.ENTITY, "anim_tiller");
		
	}
	
	public void openManualGui(ItemStack stack)
	{
		GuiManual manual = GuiManual.getInstance(stack);
		Minecraft.getMinecraft().displayGuiScreen(manual);
	}

	//Sleep
	public void Sleep(EntityPlayer entityplayer) {

		long currentTime = 0;
		int factorTime = 0;

		for (int j = 0; j < entityplayer.world.getMinecraftServer().getServer().worlds.length; ++j)
		{
			currentTime = entityplayer.world.getMinecraftServer().getServer().worlds[j].getWorldTime() %24000;
			factorTime = 24000 - (int)currentTime;
			entityplayer.world.getMinecraftServer().getServer().worlds[j].setWorldTime(entityplayer.world.getMinecraftServer().getServer().worlds[j].getWorldTime() + factorTime) ;
		}

	}
	
	@Override
	public void addAddonResourcePack(AnimaniaAddon addon)
	{
		List<IResourcePack> packs = ReflectionHelper.getPrivateValue(Minecraft.class, Minecraft.getMinecraft(), "field_110449_ao", "defaultResourcePacks");
		File animania = Loader.instance().activeModContainer().getSource();
		
		IResourcePack pack = null;
		if(animania.isDirectory())
			pack = new AddonResourcePack.Folder(addon);
		else 
			pack = new AddonResourcePack.Jar(addon);
		
		packs.add(pack);	
		IResourceManager res = Minecraft.getMinecraft().getResourceManager();
		if(res instanceof SimpleReloadableResourceManager)
		{
			((SimpleReloadableResourceManager) res).reloadResourcePack(pack);
			Minecraft.getMinecraft().getLanguageManager().onResourceManagerReload(res);
		}
	}

}