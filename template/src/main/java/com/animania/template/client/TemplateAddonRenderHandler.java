package com.animania.template.client;

import com.animania.Animania;
import net.minecraft.client.renderer.BlockEntity.BlockEntitySpecialRenderer;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TemplateAddonRenderHandler
{

	/**
	 * Render Entities <br>
	 * Render Items
	 */
	public static void preInit()
	{

	}

	/**
	 * Render TileEntities
	 */
	public static void init()
	{

	}

	@SideOnly(Dist.CLIENT)
	private static void register(Item item)
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}

	@SideOnly(Dist.CLIENT)
	private static void register(Item item, String name, int meta)
	{
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Animania.MODID + ":" + name, "inventory"));
	}

	@SideOnly(Dist.CLIENT)

	private static void registerColored(Item item, String name)
	{
		for (int meta = 0; meta < 16; meta++)
			ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Animania.MODID + ":" + name + "_" + EnumDyeColor.byDyeDamage(meta).getName(), "inventory"));
	}

	@SideOnly(Dist.CLIENT)
	private static <T extends Entity> void registerEntityRender(Class<T> entityClass, IRenderFactory<? super T> renderFactory)
	{
		RenderingRegistry.registerEntityRenderingHandler(entityClass, renderFactory);
	}

	@SideOnly(Dist.CLIENT)
	private static <T extends BlockEntity> void registerBlockEntityRender(Class<T> BlockEntityClass, BlockEntitySpecialRenderer<? super T> specialRenderer)
	{
		ClientRegistry.bindBlockEntitySpecialRenderer(BlockEntityClass, specialRenderer);
	}

}
