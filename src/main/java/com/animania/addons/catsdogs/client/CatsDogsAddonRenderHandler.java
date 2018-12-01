package com.animania.addons.catsdogs.client;

import com.animania.Animania;
import com.animania.addons.catsdogs.client.models.cats.ModelCatAmericanShorthair;
import com.animania.addons.catsdogs.client.models.cats.ModelCatAsiatic;
import com.animania.addons.catsdogs.client.models.cats.ModelCatExotic;
import com.animania.addons.catsdogs.client.models.cats.ModelCatOcelot;
import com.animania.addons.catsdogs.client.models.cats.ModelCatRagdoll;
import com.animania.addons.catsdogs.client.models.cats.ModelCatSiamese;
import com.animania.addons.catsdogs.client.models.cats.ModelCatTabby;
import com.animania.addons.catsdogs.client.render.blocks.TileEntityPetBowlRenderer;
import com.animania.addons.catsdogs.client.render.blocks.TileEntityPropRenderer;
import com.animania.addons.catsdogs.client.render.cats.RenderCatGeneric;
import com.animania.addons.catsdogs.common.entity.cats.EntityKittenAmericanShorthair;
import com.animania.addons.catsdogs.common.entity.cats.EntityKittenAsiatic;
import com.animania.addons.catsdogs.common.entity.cats.EntityKittenExotic;
import com.animania.addons.catsdogs.common.entity.cats.EntityKittenNorwegian;
import com.animania.addons.catsdogs.common.entity.cats.EntityKittenOcelot;
import com.animania.addons.catsdogs.common.entity.cats.EntityKittenRagdoll;
import com.animania.addons.catsdogs.common.entity.cats.EntityKittenSiamese;
import com.animania.addons.catsdogs.common.entity.cats.EntityKittenTabby;
import com.animania.addons.catsdogs.common.entity.cats.EntityQueenAmericanShorthair;
import com.animania.addons.catsdogs.common.entity.cats.EntityQueenAsiatic;
import com.animania.addons.catsdogs.common.entity.cats.EntityQueenExotic;
import com.animania.addons.catsdogs.common.entity.cats.EntityQueenNorwegian;
import com.animania.addons.catsdogs.common.entity.cats.EntityQueenOcelot;
import com.animania.addons.catsdogs.common.entity.cats.EntityQueenRagdoll;
import com.animania.addons.catsdogs.common.entity.cats.EntityQueenSiamese;
import com.animania.addons.catsdogs.common.entity.cats.EntityQueenTabby;
import com.animania.addons.catsdogs.common.entity.cats.EntityTomAmericanShorthair;
import com.animania.addons.catsdogs.common.entity.cats.EntityTomAsiatic;
import com.animania.addons.catsdogs.common.entity.cats.EntityTomExotic;
import com.animania.addons.catsdogs.common.entity.cats.EntityTomNorwegian;
import com.animania.addons.catsdogs.common.entity.cats.EntityTomOcelot;
import com.animania.addons.catsdogs.common.entity.cats.EntityTomRagdoll;
import com.animania.addons.catsdogs.common.entity.cats.EntityTomSiamese;
import com.animania.addons.catsdogs.common.entity.cats.EntityTomTabby;
import com.animania.addons.catsdogs.common.tileentity.TileEntityPetBowl;
import com.animania.addons.catsdogs.common.tileentity.TileEntityProp;
import com.leviathanstudio.craftstudio.client.registry.CSRegistryHelper;
import com.leviathanstudio.craftstudio.client.registry.CraftStudioLoader;
import com.leviathanstudio.craftstudio.client.util.EnumRenderType;
import com.leviathanstudio.craftstudio.client.util.EnumResourceType;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CatsDogsAddonRenderHandler
{

	private static final String CATS = "animania:textures/entity/cats/";
	
	
	public static void preInit()
	{
		//registerCraftStudioAssets();
		renderEntitiesFactory();
		
	}

	/**
	 * Render TileEntities
	 */
	public static void init()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPetBowl.class, new TileEntityPetBowlRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityProp.class, new TileEntityPropRenderer());
	}
	
	@CraftStudioLoader
	public static void registerCraftStudioAssets()
	{
		CSRegistryHelper csRegistry = new CSRegistryHelper(Animania.MODID);
		
		csRegistry.register(EnumResourceType.MODEL, EnumRenderType.BLOCK, "model_pet_bowl");
		csRegistry.register(EnumResourceType.MODEL, EnumRenderType.BLOCK, "model_cat_bed_1");
		csRegistry.register(EnumResourceType.MODEL, EnumRenderType.BLOCK, "model_cat_bed_2");
		csRegistry.register(EnumResourceType.MODEL, EnumRenderType.BLOCK, "model_cat_tower");
		csRegistry.register(EnumResourceType.MODEL, EnumRenderType.BLOCK, "model_dog_house");
		csRegistry.register(EnumResourceType.MODEL, EnumRenderType.BLOCK, "model_dog_pillow");
		csRegistry.register(EnumResourceType.MODEL, EnumRenderType.BLOCK, "model_litter_box");

	}
	
	@SideOnly(Side.CLIENT)
	static void renderEntitiesFactory()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityTomRagdoll.class, new RenderCatGeneric.Factory(new ModelCatRagdoll(), r(CATS + "ragdoll.png"), r(CATS + "blink_2.png"), 0x83786D, 0.67f));
		RenderingRegistry.registerEntityRenderingHandler(EntityQueenRagdoll.class, new RenderCatGeneric.Factory(new ModelCatRagdoll(), r(CATS + "ragdoll.png"), r(CATS + "blink_2.png"), 0x83786D, 0.57f));
		RenderingRegistry.registerEntityRenderingHandler(EntityKittenRagdoll.class, new RenderCatGeneric.Factory(new ModelCatRagdoll(), r(CATS + "ragdoll.png"), r(CATS + "blink_2.png"), 0x83786D, 0.37f));
		RenderingRegistry.registerEntityRenderingHandler(EntityTomAmericanShorthair.class, new RenderCatGeneric.Factory(new ModelCatAmericanShorthair(), r(CATS + "american_shorthair.png"), r(CATS + "blink_1.png"), 0x7D7D7D, 0.67f));
		RenderingRegistry.registerEntityRenderingHandler(EntityQueenAmericanShorthair.class, new RenderCatGeneric.Factory(new ModelCatAmericanShorthair(), r(CATS + "american_shorthair.png"), r(CATS + "blink_1.png"), 0x7D7D7D, 0.57f));
		RenderingRegistry.registerEntityRenderingHandler(EntityKittenAmericanShorthair.class, new RenderCatGeneric.Factory(new ModelCatAmericanShorthair(), r(CATS + "american_shorthair.png"), r(CATS + "blink_1.png"), 0x7D7D7D, 0.37f));
		RenderingRegistry.registerEntityRenderingHandler(EntityTomAsiatic.class, new RenderCatGeneric.Factory(new ModelCatAsiatic(), r(CATS + "asiatic.png"), r(CATS + "blink_1.png"), 0x836951, 0.67f));
		RenderingRegistry.registerEntityRenderingHandler(EntityQueenAsiatic.class, new RenderCatGeneric.Factory(new ModelCatAsiatic(), r(CATS + "asiatic.png"), r(CATS + "blink_1.png"), 0x836951, 0.57f));
		RenderingRegistry.registerEntityRenderingHandler(EntityKittenAsiatic.class, new RenderCatGeneric.Factory(new ModelCatAsiatic(), r(CATS + "asiatic.png"), r(CATS + "blink_1.png"), 0x836951, 0.37f));
		RenderingRegistry.registerEntityRenderingHandler(EntityTomExotic.class, new RenderCatGeneric.Factory(new ModelCatExotic(), r(CATS + "exotic.png"), r(CATS + "blink_1.png"), 0xA75823, 0.67f));
		RenderingRegistry.registerEntityRenderingHandler(EntityQueenExotic.class, new RenderCatGeneric.Factory(new ModelCatExotic(), r(CATS + "exotic.png"), r(CATS + "blink_1.png"), 0xA75823, 0.57f));
		RenderingRegistry.registerEntityRenderingHandler(EntityKittenExotic.class, new RenderCatGeneric.Factory(new ModelCatExotic(), r(CATS + "exotic.png"), r(CATS + "blink_1.png"), 0xA75823, 0.37f)); 
		RenderingRegistry.registerEntityRenderingHandler(EntityTomNorwegian.class, new RenderCatGeneric.Factory(new ModelCatRagdoll(), r(CATS + "norwegian.png"), r(CATS + "blink_2.png"), 0x4E3C30, 0.67f));
		RenderingRegistry.registerEntityRenderingHandler(EntityQueenNorwegian.class, new RenderCatGeneric.Factory(new ModelCatRagdoll(), r(CATS + "norwegian.png"), r(CATS + "blink_2.png"), 0x4E3C30, 0.57f));
		RenderingRegistry.registerEntityRenderingHandler(EntityKittenNorwegian.class, new RenderCatGeneric.Factory(new ModelCatRagdoll(), r(CATS + "norwegian.png"), r(CATS + "blink_2.png"), 0x4E3C30, 0.37f)); 
		RenderingRegistry.registerEntityRenderingHandler(EntityTomOcelot.class, new RenderCatGeneric.Factory(new ModelCatOcelot(), r(CATS + "ocelot.png"), r(CATS + "blink_1.png"), 0xA47947, 0.67f));
		RenderingRegistry.registerEntityRenderingHandler(EntityQueenOcelot.class, new RenderCatGeneric.Factory(new ModelCatOcelot(), r(CATS + "ocelot.png"), r(CATS + "blink_1.png"), 0xA47947, 0.57f));
		RenderingRegistry.registerEntityRenderingHandler(EntityKittenOcelot.class, new RenderCatGeneric.Factory(new ModelCatOcelot(), r(CATS + "ocelot.png"), r(CATS + "blink_1.png"), 0xA47947, 0.37f)); 
		RenderingRegistry.registerEntityRenderingHandler(EntityTomSiamese.class, new RenderCatGeneric.Factory(new ModelCatSiamese(), r(CATS + "siamese.png"), r(CATS + "blink_1.png"), 0x271D1B, 0.67f));
		RenderingRegistry.registerEntityRenderingHandler(EntityQueenSiamese.class, new RenderCatGeneric.Factory(new ModelCatSiamese(), r(CATS + "siamese.png"), r(CATS + "blink_1.png"), 0x271D1B, 0.57f));
		RenderingRegistry.registerEntityRenderingHandler(EntityKittenSiamese.class, new RenderCatGeneric.Factory(new ModelCatSiamese(), r(CATS + "siamese.png"), r(CATS + "blink_1.png"), 0x271D1B, 0.37f)); 
		RenderingRegistry.registerEntityRenderingHandler(EntityTomTabby.class, new RenderCatGeneric.Factory(new ModelCatTabby(), r(CATS + "tabby.png"), r(CATS + "blink_1.png"), 0x594336, 0.67f));
		RenderingRegistry.registerEntityRenderingHandler(EntityQueenTabby.class, new RenderCatGeneric.Factory(new ModelCatTabby(), r(CATS + "tabby.png"), r(CATS + "blink_1.png"), 0x594336, 0.57f));
		RenderingRegistry.registerEntityRenderingHandler(EntityKittenTabby.class, new RenderCatGeneric.Factory(new ModelCatTabby(), r(CATS + "tabby.png"), r(CATS + "blink_1.png"), 0x594336, 0.37f)); 
	
	}

	@SideOnly(Side.CLIENT)
	private static void register(Item item)
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}

	@SideOnly(Side.CLIENT)
	private static void register(Item item, String name, int meta)
	{
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Animania.MODID + ":" + name, "inventory"));
	}

	@SideOnly(Side.CLIENT)

	private static void registerColored(Item item, String name)
	{
		for (int meta = 0; meta < 16; meta++)
			ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Animania.MODID + ":" + name + "_" + EnumDyeColor.byDyeDamage(meta).getName(), "inventory"));
	}
	
	@SideOnly(Side.CLIENT)
    private static <T extends Entity> void registerEntityRender(Class<T> entityClass, IRenderFactory<? super T> renderFactory)
	{
		RenderingRegistry.registerEntityRenderingHandler(entityClass, renderFactory);
	}

	@SideOnly(Side.CLIENT)
	private static <T extends TileEntity> void registerTileEntityRender(Class<T> tileEntityClass, TileEntitySpecialRenderer<? super T> specialRenderer)
	{
		 ClientRegistry.bindTileEntitySpecialRenderer(tileEntityClass, specialRenderer);
	}
	
	private static ResourceLocation r(String s)
	{
		return new ResourceLocation(s);
	}

}
