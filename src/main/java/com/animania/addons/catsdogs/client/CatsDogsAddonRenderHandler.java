package com.animania.addons.catsdogs.client;

import com.animania.Animania;
import com.animania.client.render.amphibians.RenderDartFrogs;
import com.animania.client.render.amphibians.RenderFrogs;
import com.animania.client.render.amphibians.RenderToad;
import com.animania.client.render.chickens.RenderChickLeghorn;
import com.animania.client.render.chickens.RenderChickOrpington;
import com.animania.client.render.chickens.RenderChickPlymouthRock;
import com.animania.client.render.chickens.RenderChickRhodeIslandRed;
import com.animania.client.render.chickens.RenderChickWyandotte;
import com.animania.client.render.chickens.RenderHenLeghorn;
import com.animania.client.render.chickens.RenderHenOrpington;
import com.animania.client.render.chickens.RenderHenPlymouthRock;
import com.animania.client.render.chickens.RenderHenRhodeIslandRed;
import com.animania.client.render.chickens.RenderHenWyandotte;
import com.animania.client.render.chickens.RenderRoosterLeghorn;
import com.animania.client.render.chickens.RenderRoosterOrpington;
import com.animania.client.render.chickens.RenderRoosterPlymouthRock;
import com.animania.client.render.chickens.RenderRoosterRhodeIslandRed;
import com.animania.client.render.chickens.RenderRoosterWyandotte;
import com.animania.client.render.cows.RenderBullAngus;
import com.animania.client.render.cows.RenderBullFriesian;
import com.animania.client.render.cows.RenderBullHereford;
import com.animania.client.render.cows.RenderBullHighland;
import com.animania.client.render.cows.RenderBullHolstein;
import com.animania.client.render.cows.RenderBullJersey;
import com.animania.client.render.cows.RenderBullLonghorn;
import com.animania.client.render.cows.RenderBullMooshroom;
import com.animania.client.render.cows.RenderCalfAngus;
import com.animania.client.render.cows.RenderCalfFriesian;
import com.animania.client.render.cows.RenderCalfHereford;
import com.animania.client.render.cows.RenderCalfHighland;
import com.animania.client.render.cows.RenderCalfHolstein;
import com.animania.client.render.cows.RenderCalfJersey;
import com.animania.client.render.cows.RenderCalfLonghorn;
import com.animania.client.render.cows.RenderCalfMooshroom;
import com.animania.client.render.cows.RenderCowAngus;
import com.animania.client.render.cows.RenderCowFriesian;
import com.animania.client.render.cows.RenderCowHereford;
import com.animania.client.render.cows.RenderCowHighland;
import com.animania.client.render.cows.RenderCowHolstein;
import com.animania.client.render.cows.RenderCowJersey;
import com.animania.client.render.cows.RenderCowLonghorn;
import com.animania.client.render.cows.RenderCowMooshroom;
import com.animania.client.render.goats.RenderBuckAlpine;
import com.animania.client.render.goats.RenderBuckAngora;
import com.animania.client.render.goats.RenderBuckFainting;
import com.animania.client.render.goats.RenderBuckKiko;
import com.animania.client.render.goats.RenderBuckKinder;
import com.animania.client.render.goats.RenderBuckNigerianDwarf;
import com.animania.client.render.goats.RenderBuckPygmy;
import com.animania.client.render.goats.RenderDoeAlpine;
import com.animania.client.render.goats.RenderDoeAngora;
import com.animania.client.render.goats.RenderDoeFainting;
import com.animania.client.render.goats.RenderDoeKiko;
import com.animania.client.render.goats.RenderDoeKinder;
import com.animania.client.render.goats.RenderDoeNigerianDwarf;
import com.animania.client.render.goats.RenderDoePygmy;
import com.animania.client.render.goats.RenderKidAlpine;
import com.animania.client.render.goats.RenderKidAngora;
import com.animania.client.render.goats.RenderKidFainting;
import com.animania.client.render.goats.RenderKidKiko;
import com.animania.client.render.goats.RenderKidKinder;
import com.animania.client.render.goats.RenderKidNigerianDwarf;
import com.animania.client.render.goats.RenderKidPygmy;
import com.animania.client.render.horses.RenderFoalDraftHorse;
import com.animania.client.render.horses.RenderMareDraftHorse;
import com.animania.client.render.horses.RenderStallionDraftHorse;
import com.animania.client.render.peacocks.RenderPeachickBlue;
import com.animania.client.render.peacocks.RenderPeachickCharcoal;
import com.animania.client.render.peacocks.RenderPeachickOpal;
import com.animania.client.render.peacocks.RenderPeachickPeach;
import com.animania.client.render.peacocks.RenderPeachickPurple;
import com.animania.client.render.peacocks.RenderPeachickTaupe;
import com.animania.client.render.peacocks.RenderPeachickWhite;
import com.animania.client.render.peacocks.RenderPeacockBlue;
import com.animania.client.render.peacocks.RenderPeacockCharcoal;
import com.animania.client.render.peacocks.RenderPeacockOpal;
import com.animania.client.render.peacocks.RenderPeacockPeach;
import com.animania.client.render.peacocks.RenderPeacockPurple;
import com.animania.client.render.peacocks.RenderPeacockTaupe;
import com.animania.client.render.peacocks.RenderPeacockWhite;
import com.animania.client.render.peacocks.RenderPeafowlBlue;
import com.animania.client.render.peacocks.RenderPeafowlCharcoal;
import com.animania.client.render.peacocks.RenderPeafowlOpal;
import com.animania.client.render.peacocks.RenderPeafowlPeach;
import com.animania.client.render.peacocks.RenderPeafowlPurple;
import com.animania.client.render.peacocks.RenderPeafowlTaupe;
import com.animania.client.render.peacocks.RenderPeafowlWhite;
import com.animania.client.render.pigs.RenderHogDuroc;
import com.animania.client.render.pigs.RenderHogHampshire;
import com.animania.client.render.pigs.RenderHogLargeBlack;
import com.animania.client.render.pigs.RenderHogLargeWhite;
import com.animania.client.render.pigs.RenderHogOldSpot;
import com.animania.client.render.pigs.RenderHogYorkshire;
import com.animania.client.render.pigs.RenderPigletDuroc;
import com.animania.client.render.pigs.RenderPigletHampshire;
import com.animania.client.render.pigs.RenderPigletLargeBlack;
import com.animania.client.render.pigs.RenderPigletLargeWhite;
import com.animania.client.render.pigs.RenderPigletOldSpot;
import com.animania.client.render.pigs.RenderPigletYorkshire;
import com.animania.client.render.pigs.RenderSowDuroc;
import com.animania.client.render.pigs.RenderSowHampshire;
import com.animania.client.render.pigs.RenderSowLargeBlack;
import com.animania.client.render.pigs.RenderSowLargeWhite;
import com.animania.client.render.pigs.RenderSowOldSpot;
import com.animania.client.render.pigs.RenderSowYorkshire;
import com.animania.client.render.props.RenderCart;
import com.animania.client.render.props.RenderWagon;
import com.animania.client.render.rabbits.RenderBuckChinchilla;
import com.animania.client.render.rabbits.RenderBuckCottontail;
import com.animania.client.render.rabbits.RenderBuckDutch;
import com.animania.client.render.rabbits.RenderBuckHavana;
import com.animania.client.render.rabbits.RenderBuckJack;
import com.animania.client.render.rabbits.RenderBuckLop;
import com.animania.client.render.rabbits.RenderBuckNewZealand;
import com.animania.client.render.rabbits.RenderBuckRex;
import com.animania.client.render.rabbits.RenderDoeChinchilla;
import com.animania.client.render.rabbits.RenderDoeCottontail;
import com.animania.client.render.rabbits.RenderDoeDutch;
import com.animania.client.render.rabbits.RenderDoeHavana;
import com.animania.client.render.rabbits.RenderDoeJack;
import com.animania.client.render.rabbits.RenderDoeLop;
import com.animania.client.render.rabbits.RenderDoeNewZealand;
import com.animania.client.render.rabbits.RenderDoeRex;
import com.animania.client.render.rabbits.RenderKitChinchilla;
import com.animania.client.render.rabbits.RenderKitCottontail;
import com.animania.client.render.rabbits.RenderKitDutch;
import com.animania.client.render.rabbits.RenderKitHavana;
import com.animania.client.render.rabbits.RenderKitJack;
import com.animania.client.render.rabbits.RenderKitLop;
import com.animania.client.render.rabbits.RenderKitNewZealand;
import com.animania.client.render.rabbits.RenderKitRex;
import com.animania.client.render.rodents.RenderFerretGrey;
import com.animania.client.render.rodents.RenderFerretWhite;
import com.animania.client.render.rodents.RenderHamster;
import com.animania.client.render.rodents.RenderHedgehog;
import com.animania.client.render.rodents.RenderHedgehogAlbino;
import com.animania.client.render.sheep.RenderEweDorper;
import com.animania.client.render.sheep.RenderEweDorset;
import com.animania.client.render.sheep.RenderEweFriesian;
import com.animania.client.render.sheep.RenderEweJacob;
import com.animania.client.render.sheep.RenderEweMerino;
import com.animania.client.render.sheep.RenderEweSuffolk;
import com.animania.client.render.sheep.RenderLambDorper;
import com.animania.client.render.sheep.RenderLambDorset;
import com.animania.client.render.sheep.RenderLambFriesian;
import com.animania.client.render.sheep.RenderLambJacob;
import com.animania.client.render.sheep.RenderLambMerino;
import com.animania.client.render.sheep.RenderLambSuffolk;
import com.animania.client.render.sheep.RenderRamDorper;
import com.animania.client.render.sheep.RenderRamDorset;
import com.animania.client.render.sheep.RenderRamFriesian;
import com.animania.client.render.sheep.RenderRamJacob;
import com.animania.client.render.sheep.RenderRamMerino;
import com.animania.client.render.sheep.RenderRamSuffolk;
import com.animania.client.render.tileEntity.TileEntityHamsterWheelRenderer;
import com.animania.client.render.tileEntity.TileEntityNestRenderer;
import com.animania.client.render.tileEntity.TileEntitySaltLickRenderer;
import com.animania.client.render.tileEntity.TileEntityTroughRenderer;
import com.animania.common.entities.amphibians.EntityDartFrogs;
import com.animania.common.entities.amphibians.EntityFrogs;
import com.animania.common.entities.amphibians.EntityToad;
import com.animania.common.entities.chickens.EntityChickLeghorn;
import com.animania.common.entities.chickens.EntityChickOrpington;
import com.animania.common.entities.chickens.EntityChickPlymouthRock;
import com.animania.common.entities.chickens.EntityChickRhodeIslandRed;
import com.animania.common.entities.chickens.EntityChickWyandotte;
import com.animania.common.entities.chickens.EntityHenLeghorn;
import com.animania.common.entities.chickens.EntityHenOrpington;
import com.animania.common.entities.chickens.EntityHenPlymouthRock;
import com.animania.common.entities.chickens.EntityHenRhodeIslandRed;
import com.animania.common.entities.chickens.EntityHenWyandotte;
import com.animania.common.entities.chickens.EntityRoosterLeghorn;
import com.animania.common.entities.chickens.EntityRoosterOrpington;
import com.animania.common.entities.chickens.EntityRoosterPlymouthRock;
import com.animania.common.entities.chickens.EntityRoosterRhodeIslandRed;
import com.animania.common.entities.chickens.EntityRoosterWyandotte;
import com.animania.common.entities.cows.EntityBullAngus;
import com.animania.common.entities.cows.EntityBullFriesian;
import com.animania.common.entities.cows.EntityBullHereford;
import com.animania.common.entities.cows.EntityBullHighland;
import com.animania.common.entities.cows.EntityBullHolstein;
import com.animania.common.entities.cows.EntityBullJersey;
import com.animania.common.entities.cows.EntityBullLonghorn;
import com.animania.common.entities.cows.EntityBullMooshroom;
import com.animania.common.entities.cows.EntityCalfAngus;
import com.animania.common.entities.cows.EntityCalfFriesian;
import com.animania.common.entities.cows.EntityCalfHereford;
import com.animania.common.entities.cows.EntityCalfHighland;
import com.animania.common.entities.cows.EntityCalfHolstein;
import com.animania.common.entities.cows.EntityCalfJersey;
import com.animania.common.entities.cows.EntityCalfLonghorn;
import com.animania.common.entities.cows.EntityCalfMooshroom;
import com.animania.common.entities.cows.EntityCowAngus;
import com.animania.common.entities.cows.EntityCowFriesian;
import com.animania.common.entities.cows.EntityCowHereford;
import com.animania.common.entities.cows.EntityCowHighland;
import com.animania.common.entities.cows.EntityCowHolstein;
import com.animania.common.entities.cows.EntityCowJersey;
import com.animania.common.entities.cows.EntityCowLonghorn;
import com.animania.common.entities.cows.EntityCowMooshroom;
import com.animania.common.entities.goats.EntityBuckAlpine;
import com.animania.common.entities.goats.EntityBuckAngora;
import com.animania.common.entities.goats.EntityBuckFainting;
import com.animania.common.entities.goats.EntityBuckKiko;
import com.animania.common.entities.goats.EntityBuckKinder;
import com.animania.common.entities.goats.EntityBuckNigerianDwarf;
import com.animania.common.entities.goats.EntityBuckPygmy;
import com.animania.common.entities.goats.EntityDoeAlpine;
import com.animania.common.entities.goats.EntityDoeAngora;
import com.animania.common.entities.goats.EntityDoeFainting;
import com.animania.common.entities.goats.EntityDoeKiko;
import com.animania.common.entities.goats.EntityDoeKinder;
import com.animania.common.entities.goats.EntityDoeNigerianDwarf;
import com.animania.common.entities.goats.EntityDoePygmy;
import com.animania.common.entities.goats.EntityKidAlpine;
import com.animania.common.entities.goats.EntityKidAngora;
import com.animania.common.entities.goats.EntityKidFainting;
import com.animania.common.entities.goats.EntityKidKiko;
import com.animania.common.entities.goats.EntityKidKinder;
import com.animania.common.entities.goats.EntityKidNigerianDwarf;
import com.animania.common.entities.goats.EntityKidPygmy;
import com.animania.common.entities.horses.EntityFoalDraftHorse;
import com.animania.common.entities.horses.EntityMareDraftHorse;
import com.animania.common.entities.horses.EntityStallionDraftHorse;
import com.animania.common.entities.peacocks.EntityPeachickBlue;
import com.animania.common.entities.peacocks.EntityPeachickCharcoal;
import com.animania.common.entities.peacocks.EntityPeachickOpal;
import com.animania.common.entities.peacocks.EntityPeachickPeach;
import com.animania.common.entities.peacocks.EntityPeachickPurple;
import com.animania.common.entities.peacocks.EntityPeachickTaupe;
import com.animania.common.entities.peacocks.EntityPeachickWhite;
import com.animania.common.entities.peacocks.EntityPeacockBlue;
import com.animania.common.entities.peacocks.EntityPeacockCharcoal;
import com.animania.common.entities.peacocks.EntityPeacockOpal;
import com.animania.common.entities.peacocks.EntityPeacockPeach;
import com.animania.common.entities.peacocks.EntityPeacockPurple;
import com.animania.common.entities.peacocks.EntityPeacockTaupe;
import com.animania.common.entities.peacocks.EntityPeacockWhite;
import com.animania.common.entities.peacocks.EntityPeafowlBlue;
import com.animania.common.entities.peacocks.EntityPeafowlCharcoal;
import com.animania.common.entities.peacocks.EntityPeafowlOpal;
import com.animania.common.entities.peacocks.EntityPeafowlPeach;
import com.animania.common.entities.peacocks.EntityPeafowlPurple;
import com.animania.common.entities.peacocks.EntityPeafowlTaupe;
import com.animania.common.entities.peacocks.EntityPeafowlWhite;
import com.animania.common.entities.pigs.EntityHogDuroc;
import com.animania.common.entities.pigs.EntityHogHampshire;
import com.animania.common.entities.pigs.EntityHogLargeBlack;
import com.animania.common.entities.pigs.EntityHogLargeWhite;
import com.animania.common.entities.pigs.EntityHogOldSpot;
import com.animania.common.entities.pigs.EntityHogYorkshire;
import com.animania.common.entities.pigs.EntityPigletDuroc;
import com.animania.common.entities.pigs.EntityPigletHampshire;
import com.animania.common.entities.pigs.EntityPigletLargeBlack;
import com.animania.common.entities.pigs.EntityPigletLargeWhite;
import com.animania.common.entities.pigs.EntityPigletOldSpot;
import com.animania.common.entities.pigs.EntityPigletYorkshire;
import com.animania.common.entities.pigs.EntitySowDuroc;
import com.animania.common.entities.pigs.EntitySowHampshire;
import com.animania.common.entities.pigs.EntitySowLargeBlack;
import com.animania.common.entities.pigs.EntitySowLargeWhite;
import com.animania.common.entities.pigs.EntitySowOldSpot;
import com.animania.common.entities.pigs.EntitySowYorkshire;
import com.animania.common.entities.props.EntityCart;
import com.animania.common.entities.props.EntityWagon;
import com.animania.common.entities.rodents.EntityFerretGrey;
import com.animania.common.entities.rodents.EntityFerretWhite;
import com.animania.common.entities.rodents.EntityHamster;
import com.animania.common.entities.rodents.EntityHedgehog;
import com.animania.common.entities.rodents.EntityHedgehogAlbino;
import com.animania.common.entities.rodents.rabbits.EntityRabbitBuckChinchilla;
import com.animania.common.entities.rodents.rabbits.EntityRabbitBuckCottontail;
import com.animania.common.entities.rodents.rabbits.EntityRabbitBuckDutch;
import com.animania.common.entities.rodents.rabbits.EntityRabbitBuckHavana;
import com.animania.common.entities.rodents.rabbits.EntityRabbitBuckJack;
import com.animania.common.entities.rodents.rabbits.EntityRabbitBuckLop;
import com.animania.common.entities.rodents.rabbits.EntityRabbitBuckNewZealand;
import com.animania.common.entities.rodents.rabbits.EntityRabbitBuckRex;
import com.animania.common.entities.rodents.rabbits.EntityRabbitDoeChinchilla;
import com.animania.common.entities.rodents.rabbits.EntityRabbitDoeCottontail;
import com.animania.common.entities.rodents.rabbits.EntityRabbitDoeDutch;
import com.animania.common.entities.rodents.rabbits.EntityRabbitDoeHavana;
import com.animania.common.entities.rodents.rabbits.EntityRabbitDoeJack;
import com.animania.common.entities.rodents.rabbits.EntityRabbitDoeLop;
import com.animania.common.entities.rodents.rabbits.EntityRabbitDoeNewZealand;
import com.animania.common.entities.rodents.rabbits.EntityRabbitDoeRex;
import com.animania.common.entities.rodents.rabbits.EntityRabbitKitChinchilla;
import com.animania.common.entities.rodents.rabbits.EntityRabbitKitCottontail;
import com.animania.common.entities.rodents.rabbits.EntityRabbitKitDutch;
import com.animania.common.entities.rodents.rabbits.EntityRabbitKitHavana;
import com.animania.common.entities.rodents.rabbits.EntityRabbitKitJack;
import com.animania.common.entities.rodents.rabbits.EntityRabbitKitLop;
import com.animania.common.entities.rodents.rabbits.EntityRabbitKitNewZealand;
import com.animania.common.entities.rodents.rabbits.EntityRabbitKitRex;
import com.animania.common.entities.sheep.EntityEweDorper;
import com.animania.common.entities.sheep.EntityEweDorset;
import com.animania.common.entities.sheep.EntityEweFriesian;
import com.animania.common.entities.sheep.EntityEweJacob;
import com.animania.common.entities.sheep.EntityEweMerino;
import com.animania.common.entities.sheep.EntityEweSuffolk;
import com.animania.common.entities.sheep.EntityLambDorper;
import com.animania.common.entities.sheep.EntityLambDorset;
import com.animania.common.entities.sheep.EntityLambFriesian;
import com.animania.common.entities.sheep.EntityLambJacob;
import com.animania.common.entities.sheep.EntityLambMerino;
import com.animania.common.entities.sheep.EntityLambSuffolk;
import com.animania.common.entities.sheep.EntityRamDorper;
import com.animania.common.entities.sheep.EntityRamDorset;
import com.animania.common.entities.sheep.EntityRamFriesian;
import com.animania.common.entities.sheep.EntityRamJacob;
import com.animania.common.entities.sheep.EntityRamMerino;
import com.animania.common.entities.sheep.EntityRamSuffolk;
import com.animania.common.tileentities.TileEntityHamsterWheel;
import com.animania.common.tileentities.TileEntityNest;
import com.animania.common.tileentities.TileEntitySaltLick;
import com.animania.common.tileentities.TileEntityTrough;

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

public class CatsDogsAddonRenderHandler
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
