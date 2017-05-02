package com.animania;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

import com.animania.entities.chickens.EntityChickLeghorn;
import com.animania.entities.chickens.EntityChickOrpington;
import com.animania.entities.chickens.EntityChickPlymouthRock;
import com.animania.entities.chickens.EntityChickRhodeIslandRed;
import com.animania.entities.chickens.EntityChickWyandotte;
import com.animania.entities.chickens.EntityHenLeghorn;
import com.animania.entities.chickens.EntityHenOrpington;
import com.animania.entities.chickens.EntityHenPlymouthRock;
import com.animania.entities.chickens.EntityHenRhodeIslandRed;
import com.animania.entities.chickens.EntityHenWyandotte;
import com.animania.entities.chickens.EntityRoosterLeghorn;
import com.animania.entities.chickens.EntityRoosterOrpington;
import com.animania.entities.chickens.EntityRoosterPlymouthRock;
import com.animania.entities.chickens.EntityRoosterRhodeIslandRed;
import com.animania.entities.chickens.EntityRoosterWyandotte;
import com.animania.entities.cows.EntityBullAngus;
import com.animania.entities.cows.EntityBullFriesian;
import com.animania.entities.cows.EntityBullHereford;
import com.animania.entities.cows.EntityBullHolstein;
import com.animania.entities.cows.EntityBullLonghorn;
import com.animania.entities.cows.EntityCalfAngus;
import com.animania.entities.cows.EntityCalfFriesian;
import com.animania.entities.cows.EntityCalfHereford;
import com.animania.entities.cows.EntityCalfHolstein;
import com.animania.entities.cows.EntityCalfLonghorn;
import com.animania.entities.cows.EntityCowAngus;
import com.animania.entities.cows.EntityCowFriesian;
import com.animania.entities.cows.EntityCowHereford;
import com.animania.entities.cows.EntityCowHolstein;
import com.animania.entities.cows.EntityCowLonghorn;
import com.animania.entities.horses.EntityStallionDraftHorse;
import com.animania.entities.peacocks.EntityPeachickBlue;
import com.animania.entities.peacocks.EntityPeachickWhite;
import com.animania.entities.peacocks.EntityPeacockBlue;
import com.animania.entities.peacocks.EntityPeacockWhite;
import com.animania.entities.peacocks.EntityPeafowlBlue;
import com.animania.entities.peacocks.EntityPeafowlWhite;
import com.animania.entities.pigs.EntityHogDuroc;
import com.animania.entities.pigs.EntityHogHampshire;
import com.animania.entities.pigs.EntityHogLargeBlack;
import com.animania.entities.pigs.EntityHogLargeWhite;
import com.animania.entities.pigs.EntityHogOldSpot;
import com.animania.entities.pigs.EntityHogYorkshire;
import com.animania.entities.pigs.EntityPigletDuroc;
import com.animania.entities.pigs.EntityPigletHampshire;
import com.animania.entities.pigs.EntityPigletLargeBlack;
import com.animania.entities.pigs.EntityPigletLargeWhite;
import com.animania.entities.pigs.EntityPigletOldSpot;
import com.animania.entities.pigs.EntityPigletYorkshire;
import com.animania.entities.pigs.EntitySowDuroc;
import com.animania.entities.pigs.EntitySowHampshire;
import com.animania.entities.pigs.EntitySowLargeBlack;
import com.animania.entities.pigs.EntitySowLargeWhite;
import com.animania.entities.pigs.EntitySowOldSpot;
import com.animania.entities.pigs.EntitySowYorkshire;
import com.animania.entities.rodents.EntityFerretGrey;
import com.animania.entities.rodents.EntityFerretWhite;
import com.animania.entities.rodents.EntityHamster;
import com.animania.entities.rodents.EntityHedgehog;
import com.animania.entities.rodents.EntityHedgehogAlbino;
import com.animania.render.chickens.RenderChickLeghorn;
import com.animania.render.chickens.RenderChickOrpington;
import com.animania.render.chickens.RenderChickPlymouthRock;
import com.animania.render.chickens.RenderChickRhodeIslandRed;
import com.animania.render.chickens.RenderChickWyandotte;
import com.animania.render.chickens.RenderHenLeghorn;
import com.animania.render.chickens.RenderHenOrpington;
import com.animania.render.chickens.RenderHenPlymouthRock;
import com.animania.render.chickens.RenderHenRhodeIslandRed;
import com.animania.render.chickens.RenderHenWyandotte;
import com.animania.render.chickens.RenderRoosterLeghorn;
import com.animania.render.chickens.RenderRoosterOrpington;
import com.animania.render.chickens.RenderRoosterPlymouthRock;
import com.animania.render.chickens.RenderRoosterRhodeIslandRed;
import com.animania.render.chickens.RenderRoosterWyandotte;
import com.animania.render.cows.RenderBullAngus;
import com.animania.render.cows.RenderBullFriesian;
import com.animania.render.cows.RenderBullHereford;
import com.animania.render.cows.RenderBullHolstein;
import com.animania.render.cows.RenderBullLonghorn;
import com.animania.render.cows.RenderCalfAngus;
import com.animania.render.cows.RenderCalfFriesian;
import com.animania.render.cows.RenderCalfHereford;
import com.animania.render.cows.RenderCalfHolstein;
import com.animania.render.cows.RenderCalfLonghorn;
import com.animania.render.cows.RenderCowAngus;
import com.animania.render.cows.RenderCowFriesian;
import com.animania.render.cows.RenderCowHereford;
import com.animania.render.cows.RenderCowHolstein;
import com.animania.render.cows.RenderCowLonghorn;
import com.animania.render.horses.RenderStallionDraftHorse;
import com.animania.render.peacocks.RenderPeachickBlue;
import com.animania.render.peacocks.RenderPeachickWhite;
import com.animania.render.peacocks.RenderPeacockBlue;
import com.animania.render.peacocks.RenderPeacockWhite;
import com.animania.render.peacocks.RenderPeafowlBlue;
import com.animania.render.peacocks.RenderPeafowlWhite;
import com.animania.render.pigs.RenderHogDuroc;
import com.animania.render.pigs.RenderHogHampshire;
import com.animania.render.pigs.RenderHogLargeBlack;
import com.animania.render.pigs.RenderHogLargeWhite;
import com.animania.render.pigs.RenderHogOldSpot;
import com.animania.render.pigs.RenderHogYorkshire;
import com.animania.render.pigs.RenderPigletDuroc;
import com.animania.render.pigs.RenderPigletHampshire;
import com.animania.render.pigs.RenderPigletLargeBlack;
import com.animania.render.pigs.RenderPigletLargeWhite;
import com.animania.render.pigs.RenderPigletOldSpot;
import com.animania.render.pigs.RenderPigletYorkshire;
import com.animania.render.pigs.RenderSowDuroc;
import com.animania.render.pigs.RenderSowHampshire;
import com.animania.render.pigs.RenderSowLargeBlack;
import com.animania.render.pigs.RenderSowLargeWhite;
import com.animania.render.pigs.RenderSowOldSpot;
import com.animania.render.pigs.RenderSowYorkshire;
import com.animania.render.rodents.RenderFerretGrey;
import com.animania.render.rodents.RenderFerretWhite;
import com.animania.render.rodents.RenderHamster;
import com.animania.render.rodents.RenderHedgehog;
import com.animania.render.rodents.RenderHedgehogAlbino;
import com.animania.tileentities.TileEntityNest;
import com.animania.tileentities.TileEntityNestRenderer;
import com.animania.tileentities.TileEntityTrough;
import com.animania.tileentities.TileEntityTroughRenderer;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenderers() {
		
		RenderManager rm = Minecraft.getMinecraft().getRenderManager();
		
		//TileEntities
		
		TileEntitySpecialRenderer<TileEntityTrough> mcrt = new TileEntityTroughRenderer();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTrough.class, mcrt);
		
		TileEntitySpecialRenderer<TileEntityNest> mcrn = new TileEntityNestRenderer();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNest.class, mcrn);
		
		//Cows
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
		
		//Rodents
		RenderingRegistry.registerEntityRenderingHandler(EntityHamster.class, new RenderHamster(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityHedgehog.class, new RenderHedgehog(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityHedgehogAlbino.class, new RenderHedgehogAlbino(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityFerretGrey.class, new RenderFerretGrey(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityFerretWhite.class, new RenderFerretWhite(rm));
		
		//Chickens
		RenderingRegistry.registerEntityRenderingHandler(EntityHenPlymouthRock.class, new RenderHenPlymouthRock(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityRoosterPlymouthRock.class, new RenderRoosterPlymouthRock(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityChickPlymouthRock.class, new RenderChickPlymouthRock(rm));
		
		RenderingRegistry.registerEntityRenderingHandler(EntityHenLeghorn.class, new RenderHenLeghorn(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityRoosterLeghorn.class, new RenderRoosterLeghorn(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityChickLeghorn.class, new RenderChickLeghorn(rm));
		
		RenderingRegistry.registerEntityRenderingHandler(EntityHenOrpington.class, new RenderHenOrpington(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityRoosterOrpington.class, new RenderRoosterOrpington(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityChickOrpington.class, new RenderChickOrpington(rm));
		
		RenderingRegistry.registerEntityRenderingHandler(EntityHenWyandotte.class, new RenderHenWyandotte(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityRoosterWyandotte.class, new RenderRoosterWyandotte(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityChickWyandotte.class, new RenderChickWyandotte(rm));
		
		RenderingRegistry.registerEntityRenderingHandler(EntityHenRhodeIslandRed.class, new RenderHenRhodeIslandRed(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityRoosterRhodeIslandRed.class, new RenderRoosterRhodeIslandRed(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityChickRhodeIslandRed.class, new RenderChickRhodeIslandRed(rm));
		
		//Peacocks
		RenderingRegistry.registerEntityRenderingHandler(EntityPeacockBlue.class, new RenderPeacockBlue(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityPeafowlBlue.class, new RenderPeafowlBlue(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityPeachickBlue.class, new RenderPeachickBlue(rm));
		
		RenderingRegistry.registerEntityRenderingHandler(EntityPeacockWhite.class, new RenderPeacockWhite(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityPeafowlWhite.class, new RenderPeafowlWhite(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntityPeachickWhite.class, new RenderPeachickWhite(rm));
		
		//Pigs
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
		
		//Horses
		RenderingRegistry.registerEntityRenderingHandler(EntityStallionDraftHorse.class, new RenderStallionDraftHorse(rm));
		
	}
	
	@Override
	public void registerTextures() {
		AnimaniaTextures.registerTextures();
	}
	
	
}