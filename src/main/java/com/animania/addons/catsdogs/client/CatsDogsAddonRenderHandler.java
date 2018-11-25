package com.animania.addons.catsdogs.client;

import com.animania.Animania;
import com.animania.addons.catsdogs.client.models.cats.ModelCatAmericanShorthair;
import com.animania.addons.catsdogs.client.models.cats.ModelCatAsiatic;
import com.animania.addons.catsdogs.client.models.cats.ModelCatExotic;
import com.animania.addons.catsdogs.client.models.cats.ModelCatOcelot;
import com.animania.addons.catsdogs.client.models.cats.ModelCatRagdoll;
import com.animania.addons.catsdogs.client.models.cats.ModelCatSiamese;
import com.animania.addons.catsdogs.client.models.cats.ModelCatTabby;
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
import com.leviathanstudio.craftstudio.client.registry.CraftStudioLoader;

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

	private static ResourceLocation BLINK_CATS = new ResourceLocation("animania:textures/entity/cats/blink.png");
	
	
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
		
	}
	
	@CraftStudioLoader
	public static void registerCraftStudioAssets()
	{
		//CSRegistryHelper csRegistry = new CSRegistryHelper(Animania.MODID);
		//csRegistry.register(EnumResourceType.MODEL, EnumRenderType.ENTITY, "model_ragdoll");
		
	}
	
	@SideOnly(Side.CLIENT)
	static void renderEntitiesFactory()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityTomRagdoll.class, new RenderCatGeneric.Factory(new ModelCatRagdoll(), new ResourceLocation("animania:textures/entity/cats/ragdoll.png"), BLINK_CATS, 0x83786D, 0.67f));
		RenderingRegistry.registerEntityRenderingHandler(EntityQueenRagdoll.class, new RenderCatGeneric.Factory(new ModelCatRagdoll(), new ResourceLocation("animania:textures/entity/cats/ragdoll.png"), BLINK_CATS, 0x83786D, 0.57f));
		RenderingRegistry.registerEntityRenderingHandler(EntityKittenRagdoll.class, new RenderCatGeneric.Factory(new ModelCatRagdoll(), new ResourceLocation("animania:textures/entity/cats/ragdoll.png"), BLINK_CATS, 0x83786D, 0.37f));
		RenderingRegistry.registerEntityRenderingHandler(EntityTomAmericanShorthair.class, new RenderCatGeneric.Factory(new ModelCatAmericanShorthair(), new ResourceLocation("animania:textures/entity/cats/american_shorthair.png"), BLINK_CATS, 0x83786D, 0.67f));
		RenderingRegistry.registerEntityRenderingHandler(EntityQueenAmericanShorthair.class, new RenderCatGeneric.Factory(new ModelCatAmericanShorthair(), new ResourceLocation("animania:textures/entity/cats/american_shorthair.png"), BLINK_CATS, 0x83786D, 0.57f));
		RenderingRegistry.registerEntityRenderingHandler(EntityKittenAmericanShorthair.class, new RenderCatGeneric.Factory(new ModelCatAmericanShorthair(), new ResourceLocation("animania:textures/entity/cats/american_shorthair.png"), BLINK_CATS, 0x83786D, 0.37f));
		RenderingRegistry.registerEntityRenderingHandler(EntityTomAsiatic.class, new RenderCatGeneric.Factory(new ModelCatAsiatic(), new ResourceLocation("animania:textures/entity/cats/asiatic.png"), BLINK_CATS, 0x83786D, 0.67f));
		RenderingRegistry.registerEntityRenderingHandler(EntityQueenAsiatic.class, new RenderCatGeneric.Factory(new ModelCatAsiatic(), new ResourceLocation("animania:textures/entity/cats/asiatic.png"), BLINK_CATS, 0x83786D, 0.57f));
		RenderingRegistry.registerEntityRenderingHandler(EntityKittenAsiatic.class, new RenderCatGeneric.Factory(new ModelCatAsiatic(), new ResourceLocation("animania:textures/entity/cats/asiatic.png"), BLINK_CATS, 0x83786D, 0.37f));
		RenderingRegistry.registerEntityRenderingHandler(EntityTomExotic.class, new RenderCatGeneric.Factory(new ModelCatExotic(), new ResourceLocation("animania:textures/entity/cats/exotic.png"), BLINK_CATS, 0x83786D, 0.67f));
		RenderingRegistry.registerEntityRenderingHandler(EntityQueenExotic.class, new RenderCatGeneric.Factory(new ModelCatExotic(), new ResourceLocation("animania:textures/entity/cats/exotic.png"), BLINK_CATS, 0x83786D, 0.57f));
		RenderingRegistry.registerEntityRenderingHandler(EntityKittenExotic.class, new RenderCatGeneric.Factory(new ModelCatExotic(), new ResourceLocation("animania:textures/entity/cats/exotic.png"), BLINK_CATS, 0x83786D, 0.37f)); 
		RenderingRegistry.registerEntityRenderingHandler(EntityTomNorwegian.class, new RenderCatGeneric.Factory(new ModelCatRagdoll(), new ResourceLocation("animania:textures/entity/cats/norwegian.png"), BLINK_CATS, 0x83786D, 0.67f));
		RenderingRegistry.registerEntityRenderingHandler(EntityQueenNorwegian.class, new RenderCatGeneric.Factory(new ModelCatRagdoll(), new ResourceLocation("animania:textures/entity/cats/norwegian.png"), BLINK_CATS, 0x83786D, 0.57f));
		RenderingRegistry.registerEntityRenderingHandler(EntityKittenNorwegian.class, new RenderCatGeneric.Factory(new ModelCatRagdoll(), new ResourceLocation("animania:textures/entity/cats/norwegian.png"), BLINK_CATS, 0x83786D, 0.37f)); 
		RenderingRegistry.registerEntityRenderingHandler(EntityTomOcelot.class, new RenderCatGeneric.Factory(new ModelCatOcelot(), new ResourceLocation("animania:textures/entity/cats/ocelot.png"), BLINK_CATS, 0x83786D, 0.67f));
		RenderingRegistry.registerEntityRenderingHandler(EntityQueenOcelot.class, new RenderCatGeneric.Factory(new ModelCatOcelot(), new ResourceLocation("animania:textures/entity/cats/ocelot.png"), BLINK_CATS, 0x83786D, 0.57f));
		RenderingRegistry.registerEntityRenderingHandler(EntityKittenOcelot.class, new RenderCatGeneric.Factory(new ModelCatOcelot(), new ResourceLocation("animania:textures/entity/cats/ocelot.png"), BLINK_CATS, 0x83786D, 0.37f)); 
		RenderingRegistry.registerEntityRenderingHandler(EntityTomSiamese.class, new RenderCatGeneric.Factory(new ModelCatSiamese(), new ResourceLocation("animania:textures/entity/cats/siamese.png"), BLINK_CATS, 0x83786D, 0.67f));
		RenderingRegistry.registerEntityRenderingHandler(EntityQueenSiamese.class, new RenderCatGeneric.Factory(new ModelCatSiamese(), new ResourceLocation("animania:textures/entity/cats/siamese.png"), BLINK_CATS, 0x83786D, 0.57f));
		RenderingRegistry.registerEntityRenderingHandler(EntityKittenSiamese.class, new RenderCatGeneric.Factory(new ModelCatSiamese(), new ResourceLocation("animania:textures/entity/cats/siamese.png"), BLINK_CATS, 0x83786D, 0.37f)); 
		RenderingRegistry.registerEntityRenderingHandler(EntityTomTabby.class, new RenderCatGeneric.Factory(new ModelCatTabby(), new ResourceLocation("animania:textures/entity/cats/tabby.png"), BLINK_CATS, 0x83786D, 0.67f));
		RenderingRegistry.registerEntityRenderingHandler(EntityQueenTabby.class, new RenderCatGeneric.Factory(new ModelCatTabby(), new ResourceLocation("animania:textures/entity/cats/tabby.png"), BLINK_CATS, 0x83786D, 0.57f));
		RenderingRegistry.registerEntityRenderingHandler(EntityKittenTabby.class, new RenderCatGeneric.Factory(new ModelCatTabby(), new ResourceLocation("animania:textures/entity/cats/tabby.png"), BLINK_CATS, 0x83786D, 0.37f)); 
	
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

}
