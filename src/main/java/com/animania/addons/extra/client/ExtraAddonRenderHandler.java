package com.animania.addons.extra.client;

import com.animania.Animania;
import com.animania.addons.extra.client.render.amphibians.RenderDartFrogs;
import com.animania.addons.extra.client.render.amphibians.RenderFrogs;
import com.animania.addons.extra.client.render.amphibians.RenderToad;
import com.animania.addons.extra.client.render.peafowl.RenderPeachickBase;
import com.animania.addons.extra.client.render.peafowl.RenderPeacockBase;
import com.animania.addons.extra.client.render.peafowl.RenderPeafowlBase;
import com.animania.addons.extra.client.render.rabbits.RenderBuckChinchilla;
import com.animania.addons.extra.client.render.rabbits.RenderBuckCottontail;
import com.animania.addons.extra.client.render.rabbits.RenderBuckDutch;
import com.animania.addons.extra.client.render.rabbits.RenderBuckHavana;
import com.animania.addons.extra.client.render.rabbits.RenderBuckJack;
import com.animania.addons.extra.client.render.rabbits.RenderBuckLop;
import com.animania.addons.extra.client.render.rabbits.RenderBuckNewZealand;
import com.animania.addons.extra.client.render.rabbits.RenderBuckRex;
import com.animania.addons.extra.client.render.rabbits.RenderDoeChinchilla;
import com.animania.addons.extra.client.render.rabbits.RenderDoeCottontail;
import com.animania.addons.extra.client.render.rabbits.RenderDoeDutch;
import com.animania.addons.extra.client.render.rabbits.RenderDoeHavana;
import com.animania.addons.extra.client.render.rabbits.RenderDoeJack;
import com.animania.addons.extra.client.render.rabbits.RenderDoeLop;
import com.animania.addons.extra.client.render.rabbits.RenderDoeNewZealand;
import com.animania.addons.extra.client.render.rabbits.RenderDoeRex;
import com.animania.addons.extra.client.render.rabbits.RenderKitChinchilla;
import com.animania.addons.extra.client.render.rabbits.RenderKitCottontail;
import com.animania.addons.extra.client.render.rabbits.RenderKitDutch;
import com.animania.addons.extra.client.render.rabbits.RenderKitHavana;
import com.animania.addons.extra.client.render.rabbits.RenderKitJack;
import com.animania.addons.extra.client.render.rabbits.RenderKitLop;
import com.animania.addons.extra.client.render.rabbits.RenderKitNewZealand;
import com.animania.addons.extra.client.render.rabbits.RenderKitRex;
import com.animania.addons.extra.client.render.rodents.RenderFerretGrey;
import com.animania.addons.extra.client.render.rodents.RenderFerretWhite;
import com.animania.addons.extra.client.render.rodents.RenderHamster;
import com.animania.addons.extra.client.render.rodents.RenderHedgehog;
import com.animania.addons.extra.client.render.rodents.RenderHedgehogAlbino;
import com.animania.addons.extra.client.render.tileentity.TileEntityHamsterWheelRenderer;
import com.animania.addons.extra.common.entity.amphibians.EntityDartFrogs;
import com.animania.addons.extra.common.entity.amphibians.EntityFrogs;
import com.animania.addons.extra.common.entity.amphibians.EntityToad;
import com.animania.addons.extra.common.entity.peafowl.PeafowlBlue.EntityPeachickBlue;
import com.animania.addons.extra.common.entity.peafowl.PeafowlBlue.EntityPeacockBlue;
import com.animania.addons.extra.common.entity.peafowl.PeafowlBlue.EntityPeafowlBlue;
import com.animania.addons.extra.common.entity.peafowl.PeafowlCharcoal.EntityPeachickCharcoal;
import com.animania.addons.extra.common.entity.peafowl.PeafowlCharcoal.EntityPeacockCharcoal;
import com.animania.addons.extra.common.entity.peafowl.PeafowlCharcoal.EntityPeafowlCharcoal;
import com.animania.addons.extra.common.entity.peafowl.PeafowlOpal.EntityPeachickOpal;
import com.animania.addons.extra.common.entity.peafowl.PeafowlOpal.EntityPeacockOpal;
import com.animania.addons.extra.common.entity.peafowl.PeafowlOpal.EntityPeafowlOpal;
import com.animania.addons.extra.common.entity.peafowl.PeafowlPeach.EntityPeachickPeach;
import com.animania.addons.extra.common.entity.peafowl.PeafowlPeach.EntityPeacockPeach;
import com.animania.addons.extra.common.entity.peafowl.PeafowlPeach.EntityPeafowlPeach;
import com.animania.addons.extra.common.entity.peafowl.PeafowlPurple.EntityPeachickPurple;
import com.animania.addons.extra.common.entity.peafowl.PeafowlPurple.EntityPeacockPurple;
import com.animania.addons.extra.common.entity.peafowl.PeafowlPurple.EntityPeafowlPurple;
import com.animania.addons.extra.common.entity.peafowl.PeafowlTaupe.EntityPeachickTaupe;
import com.animania.addons.extra.common.entity.peafowl.PeafowlTaupe.EntityPeacockTaupe;
import com.animania.addons.extra.common.entity.peafowl.PeafowlTaupe.EntityPeafowlTaupe;
import com.animania.addons.extra.common.entity.peafowl.PeafowlWhite.EntityPeachickWhite;
import com.animania.addons.extra.common.entity.peafowl.PeafowlWhite.EntityPeacockWhite;
import com.animania.addons.extra.common.entity.peafowl.PeafowlWhite.EntityPeafowlWhite;
import com.animania.addons.extra.common.entity.rodents.EntityFerretGrey;
import com.animania.addons.extra.common.entity.rodents.EntityFerretWhite;
import com.animania.addons.extra.common.entity.rodents.EntityHamster;
import com.animania.addons.extra.common.entity.rodents.EntityHedgehog;
import com.animania.addons.extra.common.entity.rodents.EntityHedgehogAlbino;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitChinchilla.EntityRabbitBuckChinchilla;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitChinchilla.EntityRabbitDoeChinchilla;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitChinchilla.EntityRabbitKitChinchilla;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitCottonail.EntityRabbitBuckCottontail;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitCottonail.EntityRabbitDoeCottontail;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitCottonail.EntityRabbitKitCottontail;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitDutch.EntityRabbitBuckDutch;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitDutch.EntityRabbitDoeDutch;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitDutch.EntityRabbitKitDutch;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitHavana.EntityRabbitBuckHavana;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitHavana.EntityRabbitDoeHavana;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitHavana.EntityRabbitKitHavana;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitJack.EntityRabbitBuckJack;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitJack.EntityRabbitDoeJack;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitJack.EntityRabbitKitJack;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitLop.EntityRabbitBuckLop;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitLop.EntityRabbitDoeLop;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitLop.EntityRabbitKitLop;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitNewZealand.EntityRabbitBuckNewZealand;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitNewZealand.EntityRabbitDoeNewZealand;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitNewZealand.EntityRabbitKitNewZealand;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitRex.EntityRabbitBuckRex;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitRex.EntityRabbitDoeRex;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitRex.EntityRabbitKitRex;
import com.animania.addons.extra.common.handler.ExtraAddonBlockHandler;
import com.animania.addons.extra.common.handler.ExtraAddonItemHandler;
import com.animania.addons.extra.common.tileentity.TileEntityHamsterWheel;
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
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ExtraAddonRenderHandler
{

	/**
	 * Render Entities <br>
	 * Render Items
	 */
	public static void preInit()
	{
		// Items
		register(ExtraAddonItemHandler.hamsterFood);
		register(ExtraAddonItemHandler.peacockEggBlue);
		register(ExtraAddonItemHandler.peacockEggWhite);
		register(ExtraAddonItemHandler.hamsterBallClear);
		register(ExtraAddonItemHandler.peacockFeatherBlue);
		register(ExtraAddonItemHandler.peacockFeatherWhite);
		register(ExtraAddonItemHandler.peacockFeatherCharcoal);
		register(ExtraAddonItemHandler.peacockFeatherOpal);
		register(ExtraAddonItemHandler.peacockFeatherPeach);
		register(ExtraAddonItemHandler.peacockFeatherPurple);
		register(ExtraAddonItemHandler.peacockFeatherTaupe);
		registerColored(ExtraAddonItemHandler.hamsterBallColored, "hamster_ball");

		// Frogs
		register(ExtraAddonItemHandler.rawFrogLegs);
		register(ExtraAddonItemHandler.cookedFrogLegs);

		// Goats
		register(ExtraAddonItemHandler.rawPeacock);
		register(ExtraAddonItemHandler.cookedPeacock);
		register(ExtraAddonItemHandler.rawPrimePeacock);
		register(ExtraAddonItemHandler.cookedPrimePeacock);

		// Rabbit
		register(ExtraAddonItemHandler.rawPrimeRabbit);
		register(ExtraAddonItemHandler.cookedPrimeRabbit);

		register(ExtraAddonItemHandler.entityeggrandompeacock);
		register(ExtraAddonItemHandler.entityeggdartfrog);
		register(ExtraAddonItemHandler.entityeggrandomrabbit);

		register(Item.getItemFromBlock(ExtraAddonBlockHandler.blockHamsterWheel));

		// Rabbits
		RenderingRegistry.registerEntityRenderingHandler(EntityRabbitBuckCottontail.class, RenderBuckCottontail.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityRabbitDoeCottontail.class, RenderDoeCottontail.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityRabbitKitCottontail.class, RenderKitCottontail.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityRabbitBuckChinchilla.class, RenderBuckChinchilla.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityRabbitDoeChinchilla.class, RenderDoeChinchilla.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityRabbitKitChinchilla.class, RenderKitChinchilla.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityRabbitBuckDutch.class, RenderBuckDutch.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityRabbitDoeDutch.class, RenderDoeDutch.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityRabbitKitDutch.class, RenderKitDutch.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityRabbitBuckHavana.class, RenderBuckHavana.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityRabbitDoeHavana.class, RenderDoeHavana.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityRabbitKitHavana.class, RenderKitHavana.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityRabbitBuckJack.class, RenderBuckJack.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityRabbitDoeJack.class, RenderDoeJack.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityRabbitKitJack.class, RenderKitJack.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityRabbitBuckNewZealand.class, RenderBuckNewZealand.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityRabbitDoeNewZealand.class, RenderDoeNewZealand.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityRabbitKitNewZealand.class, RenderKitNewZealand.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityRabbitBuckRex.class, RenderBuckRex.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityRabbitDoeRex.class, RenderDoeRex.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityRabbitKitRex.class, RenderKitRex.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityRabbitBuckLop.class, RenderBuckLop.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityRabbitDoeLop.class, RenderDoeLop.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityRabbitKitLop.class, RenderKitLop.FACTORY);

		// Frogs
		RenderingRegistry.registerEntityRenderingHandler(EntityFrogs.class, RenderFrogs.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityDartFrogs.class, RenderDartFrogs.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityToad.class, RenderToad.FACTORY);

		// Rodents
		RenderingRegistry.registerEntityRenderingHandler(EntityHamster.class, RenderHamster.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityHedgehog.class, RenderHedgehog.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityHedgehogAlbino.class, RenderHedgehogAlbino.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityFerretGrey.class, RenderFerretGrey.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityFerretWhite.class, RenderFerretWhite.FACTORY);

		// Peacocks
		RenderingRegistry.registerEntityRenderingHandler(EntityPeacockBlue.class, RenderPeacockBase.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeafowlBlue.class, RenderPeafowlBase.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeachickBlue.class, RenderPeachickBase.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityPeacockWhite.class, RenderPeacockBase.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeafowlWhite.class, RenderPeafowlBase.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeachickWhite.class, RenderPeachickBase.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityPeacockCharcoal.class, RenderPeacockBase.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeafowlCharcoal.class, RenderPeafowlBase.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeachickCharcoal.class, RenderPeachickBase.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityPeacockOpal.class, RenderPeacockBase.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeafowlOpal.class, RenderPeafowlBase.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeachickOpal.class, RenderPeachickBase.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityPeacockPeach.class, RenderPeacockBase.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeafowlPeach.class, RenderPeafowlBase.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeachickPeach.class, RenderPeachickBase.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityPeacockPurple.class, RenderPeacockBase.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeafowlPurple.class, RenderPeafowlBase.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeachickPurple.class, RenderPeachickBase.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityPeacockTaupe.class, RenderPeacockBase.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeafowlTaupe.class, RenderPeafowlBase.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeachickTaupe.class, RenderPeachickBase.FACTORY);
	}

	@CraftStudioLoader
	public static void registerCraftStudioAssets()
	{
		CSRegistryHelper csRegistry = new CSRegistryHelper(Animania.MODID);

		csRegistry.register(EnumResourceType.MODEL, EnumRenderType.BLOCK, "model_hamster_wheel");
		csRegistry.register(EnumResourceType.MODEL, EnumRenderType.ENTITY, "hamster");

		csRegistry.register(EnumResourceType.ANIM, EnumRenderType.BLOCK, "anim_hamster_wheel");
		csRegistry.register(EnumResourceType.ANIM, EnumRenderType.ENTITY, "hamster_run");

	}

	/**
	 * Render TileEntities
	 */
	public static void init()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHamsterWheel.class, new TileEntityHamsterWheelRenderer());
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
