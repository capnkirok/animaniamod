package com.animania.client.handler;

import com.animania.client.render.amphibians.RenderFrogs;
import com.animania.client.render.amphibians.RenderToad;
import com.animania.client.render.amphibians.RenderTreeFrogs;
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
import com.animania.client.render.cows.RenderBullHolstein;
import com.animania.client.render.cows.RenderBullLonghorn;
import com.animania.client.render.cows.RenderCalfAngus;
import com.animania.client.render.cows.RenderCalfFriesian;
import com.animania.client.render.cows.RenderCalfHereford;
import com.animania.client.render.cows.RenderCalfHolstein;
import com.animania.client.render.cows.RenderCalfLonghorn;
import com.animania.client.render.cows.RenderCowAngus;
import com.animania.client.render.cows.RenderCowFriesian;
import com.animania.client.render.cows.RenderCowHereford;
import com.animania.client.render.cows.RenderCowHolstein;
import com.animania.client.render.cows.RenderCowLonghorn;
import com.animania.client.render.horses.RenderStallionDraftHorse;
import com.animania.client.render.peacocks.RenderPeachickBlue;
import com.animania.client.render.peacocks.RenderPeachickWhite;
import com.animania.client.render.peacocks.RenderPeacockBlue;
import com.animania.client.render.peacocks.RenderPeacockWhite;
import com.animania.client.render.peacocks.RenderPeafowlBlue;
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
import com.animania.client.render.rodents.RenderFerretGrey;
import com.animania.client.render.rodents.RenderFerretWhite;
import com.animania.client.render.rodents.RenderHamster;
import com.animania.client.render.rodents.RenderHedgehog;
import com.animania.client.render.rodents.RenderHedgehogAlbino;
import com.animania.client.render.tileEntity.TileEntityNestRenderer;
import com.animania.client.render.tileEntity.TileEntityTroughRenderer;
import com.animania.common.entities.amphibians.EntityFrogs;
import com.animania.common.entities.amphibians.EntityToad;
import com.animania.common.entities.amphibians.EntityTreeFrogs;
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
import com.animania.common.entities.cows.EntityBullHolstein;
import com.animania.common.entities.cows.EntityBullLonghorn;
import com.animania.common.entities.cows.EntityCalfAngus;
import com.animania.common.entities.cows.EntityCalfFriesian;
import com.animania.common.entities.cows.EntityCalfHereford;
import com.animania.common.entities.cows.EntityCalfHolstein;
import com.animania.common.entities.cows.EntityCalfLonghorn;
import com.animania.common.entities.cows.EntityCowAngus;
import com.animania.common.entities.cows.EntityCowFriesian;
import com.animania.common.entities.cows.EntityCowHereford;
import com.animania.common.entities.cows.EntityCowHolstein;
import com.animania.common.entities.cows.EntityCowLonghorn;
import com.animania.common.entities.horses.EntityStallionDraftHorse;
import com.animania.common.entities.peacocks.EntityPeachickBlue;
import com.animania.common.entities.peacocks.EntityPeachickWhite;
import com.animania.common.entities.peacocks.EntityPeacockBlue;
import com.animania.common.entities.peacocks.EntityPeacockWhite;
import com.animania.common.entities.peacocks.EntityPeafowlBlue;
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
import com.animania.common.entities.rodents.EntityFerretGrey;
import com.animania.common.entities.rodents.EntityFerretWhite;
import com.animania.common.entities.rodents.EntityHamster;
import com.animania.common.entities.rodents.EntityHedgehog;
import com.animania.common.entities.rodents.EntityHedgehogAlbino;
import com.animania.common.tileentities.TileEntityNest;
import com.animania.common.tileentities.TileEntityTrough;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderHandler {

	// TODO Need to convert all deprecated uses of
	// 'registerEntityRenderingHandler' to use Factory system
	public static void preInit() {
		renderEntitiesFactory();
	}

	public static void init() {
		renderTileEntity();
		renderEntities();
	}

	static void renderEntities() {
		RenderManager rm = Minecraft.getMinecraft().getRenderManager();

		// Cows
		RenderingRegistry.registerEntityRenderingHandler(EntityCowHolstein.class, new RenderCowHolstein(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityBullHolstein.class, new RenderBullHolstein(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityCalfHolstein.class, new RenderCalfHolstein(rm));

		RenderingRegistry.registerEntityRenderingHandler(EntityCowFriesian.class, new RenderCowFriesian(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityBullFriesian.class, new RenderBullFriesian(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityCalfFriesian.class, new RenderCalfFriesian(rm));

		RenderingRegistry.registerEntityRenderingHandler(EntityCowAngus.class, new RenderCowAngus(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityBullAngus.class, new RenderBullAngus(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityCalfAngus.class, new RenderCalfAngus(rm));

		RenderingRegistry.registerEntityRenderingHandler(EntityCowLonghorn.class, new RenderCowLonghorn(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityBullLonghorn.class, new RenderBullLonghorn(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityCalfLonghorn.class, new RenderCalfLonghorn(rm));

		RenderingRegistry.registerEntityRenderingHandler(EntityCowHereford.class, new RenderCowHereford(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityBullHereford.class, new RenderBullHereford(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityCalfHereford.class, new RenderCalfHereford(rm));

		// Rodents
		RenderingRegistry.registerEntityRenderingHandler(EntityHamster.class, new RenderHamster(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityHedgehog.class, new RenderHedgehog(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityHedgehogAlbino.class, new RenderHedgehogAlbino(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityFerretGrey.class, new RenderFerretGrey(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityFerretWhite.class, new RenderFerretWhite(rm));

		// Chickens
		RenderingRegistry.registerEntityRenderingHandler(EntityHenPlymouthRock.class, new RenderHenPlymouthRock(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityRoosterPlymouthRock.class,
				new RenderRoosterPlymouthRock(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityChickPlymouthRock.class,
				new RenderChickPlymouthRock(rm));

		RenderingRegistry.registerEntityRenderingHandler(EntityHenLeghorn.class, new RenderHenLeghorn(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityRoosterLeghorn.class, new RenderRoosterLeghorn(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityChickLeghorn.class, new RenderChickLeghorn(rm));

		RenderingRegistry.registerEntityRenderingHandler(EntityHenOrpington.class, new RenderHenOrpington(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityRoosterOrpington.class, new RenderRoosterOrpington(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityChickOrpington.class, new RenderChickOrpington(rm));

		RenderingRegistry.registerEntityRenderingHandler(EntityHenWyandotte.class, new RenderHenWyandotte(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityRoosterWyandotte.class, new RenderRoosterWyandotte(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityChickWyandotte.class, new RenderChickWyandotte(rm));

		RenderingRegistry.registerEntityRenderingHandler(EntityHenRhodeIslandRed.class,
				new RenderHenRhodeIslandRed(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityRoosterRhodeIslandRed.class,
				new RenderRoosterRhodeIslandRed(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityChickRhodeIslandRed.class,
				new RenderChickRhodeIslandRed(rm));

		// Peacocks
		RenderingRegistry.registerEntityRenderingHandler(EntityPeacockBlue.class, new RenderPeacockBlue(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityPeafowlBlue.class, new RenderPeafowlBlue(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityPeachickBlue.class, new RenderPeachickBlue(rm));

		RenderingRegistry.registerEntityRenderingHandler(EntityPeacockWhite.class, new RenderPeacockWhite(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityPeafowlWhite.class, new RenderPeafowlWhite(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityPeachickWhite.class, new RenderPeachickWhite(rm));

		// Pigs
		RenderingRegistry.registerEntityRenderingHandler(EntitySowYorkshire.class, new RenderSowYorkshire(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityHogYorkshire.class, new RenderHogYorkshire(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityPigletYorkshire.class, new RenderPigletYorkshire(rm));

		RenderingRegistry.registerEntityRenderingHandler(EntitySowOldSpot.class, new RenderSowOldSpot(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityHogOldSpot.class, new RenderHogOldSpot(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityPigletOldSpot.class, new RenderPigletOldSpot(rm));

		RenderingRegistry.registerEntityRenderingHandler(EntitySowLargeBlack.class, new RenderSowLargeBlack(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityHogLargeBlack.class, new RenderHogLargeBlack(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityPigletLargeBlack.class, new RenderPigletLargeBlack(rm));

		RenderingRegistry.registerEntityRenderingHandler(EntitySowLargeWhite.class, new RenderSowLargeWhite(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityHogLargeWhite.class, new RenderHogLargeWhite(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityPigletLargeWhite.class, new RenderPigletLargeWhite(rm));

		RenderingRegistry.registerEntityRenderingHandler(EntitySowDuroc.class, new RenderSowDuroc(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityHogDuroc.class, new RenderHogDuroc(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityPigletDuroc.class, new RenderPigletDuroc(rm));

		RenderingRegistry.registerEntityRenderingHandler(EntitySowHampshire.class, new RenderSowHampshire(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityHogHampshire.class, new RenderHogHampshire(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityPigletHampshire.class, new RenderPigletHampshire(rm));

		// Horses
		RenderingRegistry.registerEntityRenderingHandler(EntityStallionDraftHorse.class,
				new RenderStallionDraftHorse(rm));

	}

	static void renderEntitiesFactory() {
		RenderingRegistry.registerEntityRenderingHandler(EntityFrogs.class, RenderFrogs.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityTreeFrogs.class, RenderTreeFrogs.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityToad.class, RenderToad.FACTORY);
	}

	static void renderTileEntity() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTrough.class, new TileEntityTroughRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNest.class, new TileEntityNestRenderer());
	}
}
