package com.animania.client.handler;

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
import com.animania.client.render.horses.RenderFoalDraftHorse;
import com.animania.client.render.horses.RenderMareDraftHorse;
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
import com.animania.client.render.tileEntity.TileEntityHamsterWheelRenderer;
import com.animania.client.render.tileEntity.TileEntityNestRenderer;
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
import com.animania.common.entities.horses.EntityFoalDraftHorse;
import com.animania.common.entities.horses.EntityMareDraftHorse;
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
import com.animania.common.tileentities.TileEntityHamsterWheel;
import com.animania.common.tileentities.TileEntityNest;
import com.animania.common.tileentities.TileEntityTrough;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderHandler
{

    public static void preInit() {
        renderEntitiesFactory();
    }

    public static void init() {
        renderTileEntity();
    }

    static void renderEntitiesFactory() {
        // Frogs
        RenderingRegistry.registerEntityRenderingHandler(EntityFrogs.class, RenderFrogs.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityDartFrogs.class, RenderDartFrogs.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityToad.class, RenderToad.FACTORY);

        // Chickens
        RenderingRegistry.registerEntityRenderingHandler(EntityHenPlymouthRock.class, RenderHenPlymouthRock.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityRoosterPlymouthRock.class, RenderRoosterPlymouthRock.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityChickPlymouthRock.class, RenderChickPlymouthRock.FACTORY);

        RenderingRegistry.registerEntityRenderingHandler(EntityHenLeghorn.class, RenderHenLeghorn.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityRoosterLeghorn.class, RenderRoosterLeghorn.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityChickLeghorn.class, RenderChickLeghorn.FACTORY);

        RenderingRegistry.registerEntityRenderingHandler(EntityHenOrpington.class, RenderHenOrpington.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityRoosterOrpington.class, RenderRoosterOrpington.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityChickOrpington.class, RenderChickOrpington.FACTORY);

        RenderingRegistry.registerEntityRenderingHandler(EntityHenWyandotte.class, RenderHenWyandotte.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityRoosterWyandotte.class, RenderRoosterWyandotte.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityChickWyandotte.class, RenderChickWyandotte.FACTORY);

        RenderingRegistry.registerEntityRenderingHandler(EntityHenRhodeIslandRed.class, RenderHenRhodeIslandRed.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityRoosterRhodeIslandRed.class, RenderRoosterRhodeIslandRed.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityChickRhodeIslandRed.class, RenderChickRhodeIslandRed.FACTORY);

        // Cows
        RenderingRegistry.registerEntityRenderingHandler(EntityCowHolstein.class, RenderCowHolstein.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityBullHolstein.class, RenderBullHolstein.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityCalfHolstein.class, RenderCalfHolstein.FACTORY);

        RenderingRegistry.registerEntityRenderingHandler(EntityCowFriesian.class, RenderCowFriesian.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityBullFriesian.class, RenderBullFriesian.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityCalfFriesian.class, RenderCalfFriesian.FACTORY);

        RenderingRegistry.registerEntityRenderingHandler(EntityCowAngus.class, RenderCowAngus.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityBullAngus.class, RenderBullAngus.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityCalfAngus.class, RenderCalfAngus.FACTORY);

        RenderingRegistry.registerEntityRenderingHandler(EntityCowLonghorn.class, RenderCowLonghorn.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityBullLonghorn.class, RenderBullLonghorn.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityCalfLonghorn.class, RenderCalfLonghorn.FACTORY);

        RenderingRegistry.registerEntityRenderingHandler(EntityCowHereford.class, RenderCowHereford.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityBullHereford.class, RenderBullHereford.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityCalfHereford.class, RenderCalfHereford.FACTORY);

        // Pigs
        RenderingRegistry.registerEntityRenderingHandler(EntitySowYorkshire.class, RenderSowYorkshire.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityHogYorkshire.class, RenderHogYorkshire.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityPigletYorkshire.class, RenderPigletYorkshire.FACTORY);

        RenderingRegistry.registerEntityRenderingHandler(EntitySowOldSpot.class, RenderSowOldSpot.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityHogOldSpot.class, RenderHogOldSpot.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityPigletOldSpot.class, RenderPigletOldSpot.FACTORY);

        RenderingRegistry.registerEntityRenderingHandler(EntitySowLargeBlack.class, RenderSowLargeBlack.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityHogLargeBlack.class, RenderHogLargeBlack.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityPigletLargeBlack.class, RenderPigletLargeBlack.FACTORY);

        RenderingRegistry.registerEntityRenderingHandler(EntitySowLargeWhite.class, RenderSowLargeWhite.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityHogLargeWhite.class, RenderHogLargeWhite.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityPigletLargeWhite.class, RenderPigletLargeWhite.FACTORY);

        RenderingRegistry.registerEntityRenderingHandler(EntitySowDuroc.class, RenderSowDuroc.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityHogDuroc.class, RenderHogDuroc.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityPigletDuroc.class, RenderPigletDuroc.FACTORY);

        RenderingRegistry.registerEntityRenderingHandler(EntitySowHampshire.class, RenderSowHampshire.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityHogHampshire.class, RenderHogHampshire.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityPigletHampshire.class, RenderPigletHampshire.FACTORY);

        // Rodents
        RenderingRegistry.registerEntityRenderingHandler(EntityHamster.class, RenderHamster.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityHedgehog.class, RenderHedgehog.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityHedgehogAlbino.class, RenderHedgehogAlbino.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityFerretGrey.class, RenderFerretGrey.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityFerretWhite.class, RenderFerretWhite.FACTORY);

        // Peacocks
        RenderingRegistry.registerEntityRenderingHandler(EntityPeacockBlue.class, RenderPeacockBlue.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityPeafowlBlue.class, RenderPeafowlBlue.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityPeachickBlue.class, RenderPeachickBlue.FACTORY);

        RenderingRegistry.registerEntityRenderingHandler(EntityPeacockWhite.class, RenderPeacockWhite.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityPeafowlWhite.class, RenderPeafowlWhite.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityPeachickWhite.class, RenderPeachickWhite.FACTORY);

        // Horses
        RenderingRegistry.registerEntityRenderingHandler(EntityStallionDraftHorse.class, RenderStallionDraftHorse.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityMareDraftHorse.class, RenderMareDraftHorse.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityFoalDraftHorse.class, RenderFoalDraftHorse.FACTORY);

    }

    static void renderTileEntity() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTrough.class, new TileEntityTroughRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNest.class, new TileEntityNestRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHamsterWheel.class, new TileEntityHamsterWheelRenderer());
    }
}
