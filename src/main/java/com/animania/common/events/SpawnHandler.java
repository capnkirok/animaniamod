package com.animania.common.events;

import java.util.List;

import com.animania.Animania;
import com.animania.common.entities.amphibians.EntityAmphibian;
import com.animania.common.entities.chickens.ChickenLeghorn.EntityHenLeghorn;
import com.animania.common.entities.chickens.ChickenLeghorn.EntityRoosterLeghorn;
import com.animania.common.entities.chickens.ChickenOrpington.EntityHenOrpington;
import com.animania.common.entities.chickens.ChickenOrpington.EntityRoosterOrpington;
import com.animania.common.entities.chickens.ChickenPlymouthRock.EntityHenPlymouthRock;
import com.animania.common.entities.chickens.ChickenPlymouthRock.EntityRoosterPlymouthRock;
import com.animania.common.entities.chickens.ChickenRhodeIslandRed.EntityHenRhodeIslandRed;
import com.animania.common.entities.chickens.ChickenRhodeIslandRed.EntityRoosterRhodeIslandRed;
import com.animania.common.entities.chickens.ChickenWyandotte.EntityHenWyandotte;
import com.animania.common.entities.chickens.ChickenWyandotte.EntityRoosterWyandotte;
import com.animania.common.entities.chickens.EntityAnimaniaChicken;
import com.animania.common.entities.cows.CowAngus.EntityBullAngus;
import com.animania.common.entities.cows.CowAngus.EntityCowAngus;
import com.animania.common.entities.cows.CowFriesian.EntityBullFriesian;
import com.animania.common.entities.cows.CowFriesian.EntityCowFriesian;
import com.animania.common.entities.cows.CowHereford.EntityBullHereford;
import com.animania.common.entities.cows.CowHereford.EntityCowHereford;
import com.animania.common.entities.cows.CowHighland.EntityBullHighland;
import com.animania.common.entities.cows.CowHighland.EntityCowHighland;
import com.animania.common.entities.cows.CowHolstein.EntityBullHolstein;
import com.animania.common.entities.cows.CowHolstein.EntityCowHolstein;
import com.animania.common.entities.cows.CowLonghorn.EntityBullLonghorn;
import com.animania.common.entities.cows.CowLonghorn.EntityCowLonghorn;
import com.animania.common.entities.cows.CowMooshroom.EntityBullMooshroom;
import com.animania.common.entities.cows.CowMooshroom.EntityCowMooshroom;
import com.animania.common.entities.cows.EntityAnimaniaCow;
import com.animania.common.entities.goats.EntityAnimaniaGoat;
import com.animania.common.entities.horses.EntityAnimaniaHorse;
import com.animania.common.entities.pigs.EntityAnimaniaPig;
import com.animania.common.entities.pigs.PigDuroc.EntityHogDuroc;
import com.animania.common.entities.pigs.PigDuroc.EntitySowDuroc;
import com.animania.common.entities.pigs.PigHampshire.EntityHogHampshire;
import com.animania.common.entities.pigs.PigHampshire.EntitySowHampshire;
import com.animania.common.entities.pigs.PigLargeBlack.EntityHogLargeBlack;
import com.animania.common.entities.pigs.PigLargeBlack.EntitySowLargeBlack;
import com.animania.common.entities.pigs.PigOldSpot.EntityHogOldSpot;
import com.animania.common.entities.pigs.PigOldSpot.EntitySowOldSpot;
import com.animania.common.entities.pigs.PigYorkshire.EntityHogYorkshire;
import com.animania.common.entities.pigs.PigYorkshire.EntitySowYorkshire;
import com.animania.common.entities.rodents.EntityFerretGrey;
import com.animania.common.entities.rodents.EntityFerretWhite;
import com.animania.common.entities.rodents.EntityHamster;
import com.animania.common.entities.rodents.EntityHedgehog;
import com.animania.common.entities.rodents.EntityHedgehogAlbino;
import com.animania.common.entities.rodents.rabbits.EntityAnimaniaRabbit;
import com.animania.common.entities.rodents.rabbits.RabbitChinchilla.EntityRabbitBuckChinchilla;
import com.animania.common.entities.rodents.rabbits.RabbitChinchilla.EntityRabbitDoeChinchilla;
import com.animania.common.entities.rodents.rabbits.RabbitCottonail.EntityRabbitBuckCottontail;
import com.animania.common.entities.rodents.rabbits.RabbitCottonail.EntityRabbitDoeCottontail;
import com.animania.common.entities.rodents.rabbits.RabbitHavana.EntityRabbitBuckHavana;
import com.animania.common.entities.rodents.rabbits.RabbitHavana.EntityRabbitDoeHavana;
import com.animania.common.entities.rodents.rabbits.RabbitJack.EntityRabbitBuckJack;
import com.animania.common.entities.rodents.rabbits.RabbitJack.EntityRabbitDoeJack;
import com.animania.common.entities.sheep.EntityAnimaniaSheep;
import com.animania.common.entities.sheep.SheepDorset.EntityEweDorset;
import com.animania.common.entities.sheep.SheepDorset.EntityRamDorset;
import com.animania.common.entities.sheep.SheepFriesian.EntityEweFriesian;
import com.animania.common.entities.sheep.SheepFriesian.EntityRamFriesian;
import com.animania.common.entities.sheep.SheepMerino.EntityEweMerino;
import com.animania.common.entities.sheep.SheepMerino.EntityRamMerino;
import com.animania.common.entities.sheep.SheepSuffolk.EntityEweSuffolk;
import com.animania.common.entities.sheep.SheepSuffolk.EntityRamSuffolk;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.config.AnimaniaConfig;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.entity.living.LivingSpawnEvent.CheckSpawn;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class SpawnHandler {

	@SubscribeEvent
	public void removeSpawns(CheckSpawn event)
	{
		BlockPos pos = new BlockPos(event.getX(), event.getY(), event.getZ()); 
		World worldIn = event.getWorld();
		Biome biome = event.getWorld().getBiome(pos);
		int chooser = 0;

		if (AnimaniaConfig.gameRules.replaceVanillaCows && (event.getEntity().getClass().equals(EntityCow.class) || event.getEntity().getClass().equals(EntityMooshroom.class)) && !worldIn.isRemote)
		{
			if (!event.getEntity().hasCustomName())
			{
				event.setResult(Result.DENY);
			}

			List<EntityLivingBase> otheranimals = AnimaniaHelper.getEntitiesInRange(EntityLivingBase.class, 100, event.getEntity().world, pos);
			if (otheranimals.size() <= 2) 
			{
				chooser = Animania.RANDOM.nextInt(2) + 1;

				if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.cowHolsteinBiomeTypes))) {
					if (chooser == 1) {
						EntityCowHolstein entity = new EntityCowHolstein(worldIn);
						entity.setPosition(event.getX(),event.getY()+1, event.getZ());
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 2)
					{
						EntityBullHolstein entity = new EntityBullHolstein(worldIn);
						entity.setPosition(event.getX(),event.getY()+1, event.getZ());
						worldIn.spawnEntity(entity);
					}

				}
				else if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.cowLonghornBiomeTypes))) {
					if (chooser == 1) {
						EntityCowLonghorn entity = new EntityCowLonghorn(worldIn);
						entity.setPosition(event.getX(),event.getY()+1, event.getZ());
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 2)
					{
						EntityBullLonghorn entity = new EntityBullLonghorn(worldIn);
						entity.setPosition(event.getX(),event.getY()+1, event.getZ());
						worldIn.spawnEntity(entity);
					}

				}
				else if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.cowHerefordBiomeTypes))) {
					if (chooser == 1) {
						EntityCowHereford entity = new EntityCowHereford(worldIn);
						entity.setPosition(event.getX(),event.getY()+1, event.getZ());
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 2)
					{
						EntityBullHereford entity = new EntityBullHereford(worldIn);
						entity.setPosition(event.getX(),event.getY()+1, event.getZ());
						worldIn.spawnEntity(entity);
					}

				}
				else if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.cowHighlandBiomeTypes))) {
					if (chooser == 1) {
						EntityCowHighland entity = new EntityCowHighland(worldIn);
						entity.setPosition(event.getX(),event.getY()+1, event.getZ());
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 2)
					{
						EntityBullHighland entity = new EntityBullHighland(worldIn);
						entity.setPosition(event.getX(),event.getY()+1, event.getZ());
						worldIn.spawnEntity(entity);
					}

				}
				else if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.cowAngusBiomeTypes))) {
					if (chooser == 1) {
						EntityCowAngus entity = new EntityCowAngus(worldIn);
						entity.setPosition(event.getX(),event.getY()+1, event.getZ());
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 2)
					{
						EntityBullAngus entity = new EntityBullAngus(worldIn);
						entity.setPosition(event.getX(),event.getY()+1, event.getZ());
						worldIn.spawnEntity(entity);
					}

				}
				else if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.cowMooshroomBiomeTypes))) {

					if (chooser == 1) {
						EntityCowMooshroom entity = new EntityCowMooshroom(worldIn);
						entity.setPosition(event.getX(),event.getY()+1, event.getZ());
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 2)
					{
						EntityBullMooshroom entity = new EntityBullMooshroom(worldIn);
						entity.setPosition(event.getX(),event.getY()+1, event.getZ());
						worldIn.spawnEntity(entity);
					}

				}
				else if (chooser == 1)
				{
					EntityCowFriesian entity = new EntityCowFriesian(worldIn);
					entity.setPosition(event.getX(),event.getY()+1, event.getZ());
					worldIn.spawnEntity(entity);
				}
				else if (chooser == 2)
				{
					EntityBullFriesian entity = new EntityBullFriesian(worldIn);
					entity.setPosition(event.getX(),event.getY()+1, event.getZ());
					worldIn.spawnEntity(entity);
				}
			}

		}
		else if (AnimaniaConfig.gameRules.replaceVanillaPigs && event.getEntity().getClass().equals(EntityPig.class) && !worldIn.isRemote)
		{

			if (!event.getEntity().hasCustomName())
			{
				event.setResult(Result.DENY);
			}

			chooser = Animania.RANDOM.nextInt(2) + 1;
			List<EntityLivingBase> otheranimals = AnimaniaHelper.getEntitiesInRange(EntityLivingBase.class, 100, event.getEntity().world, pos);
			if (otheranimals.size() <= 2) 
			{
				chooser = Animania.RANDOM.nextInt(2) + 1;

				if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.pigOldSpotBiomeTypes))) {

					if (Animania.RANDOM.nextBoolean()) {
						if (chooser == 1) {

							EntitySowOldSpot entity = new EntitySowOldSpot(worldIn);
							entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
							worldIn.spawnEntity(entity);
						}
						else if (chooser == 2)
						{
							EntityHogOldSpot entity = new EntityHogOldSpot(worldIn);
							entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
							worldIn.spawnEntity(entity);
						}

					}
					else
					{

						if (chooser == 1)
						{
							EntitySowHampshire entity = new EntitySowHampshire(worldIn);
							entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
							worldIn.spawnEntity(entity);
						}
						else if (chooser == 2)
						{
							EntityHogHampshire entity = new EntityHogHampshire(worldIn);
							entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
							worldIn.spawnEntity(entity);
						}

					}
				}
				else if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.pigDurocBiomeTypes))) {
					if (chooser == 1) {
						EntitySowDuroc entity = new EntitySowDuroc(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 2)
					{
						EntityHogDuroc entity = new EntityHogDuroc(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}

				}
				else if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.pigLargeBlackBiomeTypes))) {
					if (chooser == 1) {
						EntitySowLargeBlack entity = new EntitySowLargeBlack(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 2)
					{
						EntityHogLargeBlack entity = new EntityHogLargeBlack(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
				}
				else if (chooser == 1)
				{
					EntitySowYorkshire entity = new EntitySowYorkshire(worldIn);
					entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
					worldIn.spawnEntity(entity);
				}
				else if (chooser == 2)
				{
					EntityHogYorkshire entity = new EntityHogYorkshire(worldIn);
					entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
					worldIn.spawnEntity(entity);
				}
			}

		}

		else if (AnimaniaConfig.gameRules.replaceVanillaChickens && event.getEntity().getClass().equals(EntityChicken.class) && !worldIn.isRemote)
		{

			if (!event.getEntity().hasCustomName())
			{
				event.setResult(Result.DENY);
			}

			chooser = Animania.RANDOM.nextInt(2) + 1;

			List<EntityLivingBase> otheranimals = AnimaniaHelper.getEntitiesInRange(EntityLivingBase.class, 100, event.getEntity().world, pos);
			if (otheranimals.size() <= 2) 
			{
				chooser = Animania.RANDOM.nextInt(3);

				if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.chickenOrpingtonBiomeTypes))) {
					if (chooser == 1) {
						EntityHenOrpington entity = new EntityHenOrpington(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 2)
					{
						EntityRoosterOrpington entity = new EntityRoosterOrpington(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}

				}
				else if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.chickenPlymouthRockBiomeTypes))) {
					if (chooser == 1) {
						EntityHenPlymouthRock entity = new EntityHenPlymouthRock(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 2)
					{
						EntityRoosterPlymouthRock entity = new EntityRoosterPlymouthRock(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}

				}
				else if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.chickenRhodeIslandRedBiomeTypes))) {
					if (chooser == 1) {
						EntityHenRhodeIslandRed entity = new EntityHenRhodeIslandRed(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 2)
					{
						EntityRoosterRhodeIslandRed entity = new EntityRoosterRhodeIslandRed(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}

				}
				else if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.chickenWyandotteBiomeTypes))) {
					if (chooser == 1) {
						EntityHenWyandotte entity = new EntityHenWyandotte(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 2)
					{
						EntityRoosterWyandotte entity = new EntityRoosterWyandotte(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}

				}
				else if (chooser == 1)
				{
					EntityHenLeghorn entity = new EntityHenLeghorn(worldIn);
					entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
					worldIn.spawnEntity(entity);
				}
				else if (chooser == 2)
				{
					EntityRoosterLeghorn entity = new EntityRoosterLeghorn(worldIn);
					entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
					worldIn.spawnEntity(entity);
				}
			}

		}

		else if (AnimaniaConfig.gameRules.replaceVanillaSheep && event.getEntity().getClass().equals(EntitySheep.class) && !worldIn.isRemote)
		{

			if (!event.getEntity().hasCustomName())
			{
				event.setResult(Result.DENY);
			}

			chooser = Animania.RANDOM.nextInt(2) + 1;
			List<EntityLivingBase> otheranimals = AnimaniaHelper.getEntitiesInRange(EntityLivingBase.class, 100, event.getEntity().world, pos);

			if (otheranimals.size() <= 2) 
			{
				chooser = Animania.RANDOM.nextInt(3);

				if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.sheepDorsetBiomeTypes))) {
					int chooser2 = Animania.RANDOM.nextInt(2);
					if (Animania.RANDOM.nextBoolean())
					{
						if (chooser == 1)
						{
							EntityEweDorset entity = new EntityEweDorset(worldIn);
							entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
							worldIn.spawnEntity(entity);
						}
						else if (chooser == 2)
						{
							EntityRamDorset entity = new EntityRamDorset(worldIn);
							entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
							worldIn.spawnEntity(entity);
						}

					}
					else
					{

						if (chooser == 1)
						{
							EntityEweFriesian entity = new EntityEweFriesian(worldIn);
							entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
							worldIn.spawnEntity(entity);
						}
						else if (chooser == 2)
						{
							EntityRamFriesian entity = new EntityRamFriesian(worldIn);
							entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
							worldIn.spawnEntity(entity);
						}

					}
				}

				else if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.sheepSuffolkBiomeTypes))) {
					if (chooser == 1) {
						EntityEweSuffolk entity = new EntityEweSuffolk(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
					else if (chooser ==2)
					{
						EntityRamSuffolk entity = new EntityRamSuffolk(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}

				}
				else if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.sheepDorsetBiomeTypes))) {
					if (chooser == 1) {
						EntityEweDorset entity = new EntityEweDorset(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 2)
					{
						EntityRamDorset entity = new EntityRamDorset(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}

				}
				else if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.sheepMerinoBiomeTypes))) {
					if (chooser == 1) {
						EntityEweMerino entity = new EntityEweMerino(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 2)
					{
						EntityRamMerino entity = new EntityRamMerino(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}

				}
				else if (chooser == 1)
				{
					EntityEweFriesian entity = new EntityEweFriesian(worldIn);
					entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
					worldIn.spawnEntity(entity);
				}
				else if (chooser == 2)
				{
					EntityRamFriesian entity = new EntityRamFriesian(worldIn);
					entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
					worldIn.spawnEntity(entity);
				}
			}


		}
		else if (AnimaniaConfig.gameRules.replaceVanillaRabbits && event.getEntity().getClass().equals(EntityRabbit.class) && !worldIn.isRemote)
		{

			if (!event.getEntity().hasCustomName())
			{
				event.setResult(Result.DENY);
			}

			chooser = Animania.RANDOM.nextInt(2) + 1;

			List<EntityLivingBase> otheranimals = AnimaniaHelper.getEntitiesInRange(EntityLivingBase.class, 100, event.getEntity().world, pos);
			if (otheranimals.size() <= 2) 
			{
				chooser = Animania.RANDOM.nextInt(3);

				if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.rabbitCottontailBiomeTypes))) {
					if (chooser == 1) {
						EntityRabbitBuckCottontail entity = new EntityRabbitBuckCottontail(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 2)
					{
						EntityRabbitDoeCottontail entity = new EntityRabbitDoeCottontail(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}

				}
				else if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.rabbitJackBiomeTypes)) || AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.rabbitJackBiomeTypes)[1])) {
					if (chooser == 1) {
						EntityRabbitBuckJack entity = new EntityRabbitBuckJack(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 2)
					{
						EntityRabbitDoeJack entity = new EntityRabbitDoeJack(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}

				}
				else if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.rabbitHavanaBiomeTypes))) {
					if (chooser == 1) {
						EntityRabbitBuckHavana entity = new EntityRabbitBuckHavana(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 2)
					{
						EntityRabbitDoeHavana entity = new EntityRabbitDoeHavana(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}

				}
				else if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.rabbitChinchillaBiomeTypes))) {
					if (chooser == 1) {
						EntityRabbitDoeChinchilla entity = new EntityRabbitDoeChinchilla(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 2)
					{
						EntityRabbitBuckChinchilla entity = new EntityRabbitBuckChinchilla(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}

				}
				else if (chooser == 1)
				{
					EntityRabbitBuckCottontail entity = new EntityRabbitBuckCottontail(worldIn);
					entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
					worldIn.spawnEntity(entity);
				}
				else if (chooser == 2)
				{
					EntityRabbitDoeCottontail entity = new EntityRabbitDoeCottontail(worldIn);
					entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
					worldIn.spawnEntity(entity);
				}
			}



		}
		else if (AnimaniaConfig.gameRules.replaceVanillaHorses && event.getEntity().getClass().equals(EntityHorse.class) && !worldIn.isRemote)
		{

			if (!event.getEntity().hasCustomName())
			{
				event.setResult(Result.DENY);
			}

		}
		else if (AnimaniaConfig.spawn.spawnAnimaniaHorses && event.getEntity().getClass().equals(EntityHorse.class) && !worldIn.isRemote)
		{

			boolean toggler = Animania.RANDOM.nextBoolean();
			if (!event.getEntity().hasCustomName() && toggler)
			{
				event.setResult(Result.DENY);
			}

		}
		else if (!AnimaniaConfig.spawn.spawnFreshWaterSquids && event.getEntity().getClass().equals(EntitySquid.class) && !worldIn.isRemote)
		{

			if (!AnimaniaHelper.hasBiomeType(biome, Type.OCEAN))
			{
				if (!event.getEntity().hasCustomName())
				{
					event.setResult(Result.DENY);
				}
			}

		} 


	}

	@SubscribeEvent
	public void limitAnimaniaSpawns(CheckSpawn event)
	{
		BlockPos pos = new BlockPos(event.getX(),event.getY()+1, event.getZ());
		World worldIn = event.getWorld();
		Biome biome = event.getWorld().getBiome(pos);


		if (AnimaniaConfig.spawn.spawnAnimaniaCows && (event.getEntity() instanceof EntityAnimaniaCow && !worldIn.isRemote))
		{
			List<EntityAnimaniaCow> others = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaCow.class, 100, event.getEntity().world, pos);
			if (others.size() > AnimaniaConfig.spawn.spawnLimitCows)
			{
				event.setResult(Result.DENY);
				//System.out.println("Cow Denied Existence!");
			}
		}
		else if (AnimaniaConfig.spawn.spawnAnimaniaPigs && (event.getEntity() instanceof EntityAnimaniaPig && !worldIn.isRemote))
		{
			List<EntityAnimaniaPig> others = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaPig.class, 100, event.getEntity().world, pos);
			if (others.size() > AnimaniaConfig.spawn.spawnLimitPigs)
			{
				event.setResult(Result.DENY);
				//System.out.println("Pig Denied Existence!");
			}
		}
		else if (AnimaniaConfig.spawn.spawnAnimaniaChickens && (event.getEntity() instanceof EntityAnimaniaChicken && !worldIn.isRemote))
		{
			List<EntityAnimaniaChicken> others = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaChicken.class, 100, event.getEntity().world, pos);
			if (others.size() > AnimaniaConfig.spawn.spawnLimitChickens)
			{
				event.setResult(Result.DENY);
				//System.out.println("Chicken Denied Existence!");
			}
		}
		else if (AnimaniaConfig.spawn.spawnAnimaniaSheep && (event.getEntity() instanceof EntityAnimaniaSheep && !worldIn.isRemote))
		{
			List<EntityAnimaniaSheep> others = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaSheep.class, 100, event.getEntity().world, pos);
			if (others.size() > AnimaniaConfig.spawn.spawnLimitSheep)
			{
				event.setResult(Result.DENY);
				//System.out.println("Sheep Denied Existence!");
			}
		}
		else if (AnimaniaConfig.spawn.spawnAnimaniaGoats && (event.getEntity() instanceof EntityAnimaniaGoat && !worldIn.isRemote))
		{
			List<EntityAnimaniaGoat> others = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaGoat.class, 100, event.getEntity().world, pos);
			if (others.size() > AnimaniaConfig.spawn.spawnLimitGoats)
			{
				event.setResult(Result.DENY);
				//System.out.println("Goats Denied Existence!");
			}
		}
		else if (AnimaniaConfig.spawn.spawnAnimaniaRabbits && (event.getEntity() instanceof EntityAnimaniaRabbit && !worldIn.isRemote))
		{
			List<EntityAnimaniaRabbit> others = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaRabbit.class, 100, event.getEntity().world, pos);
			if (others.size() > AnimaniaConfig.spawn.spawnLimitRabbits)
			{
				event.setResult(Result.DENY);
				//System.out.println("Rabbit Denied Existence!");
			}
		}
		else if (AnimaniaConfig.spawn.spawnAnimaniaHorses && (event.getEntity() instanceof EntityAnimaniaHorse && !worldIn.isRemote))
		{
			List<EntityAnimaniaHorse> others = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaHorse.class, 100, event.getEntity().world, pos);
			if (others.size() > AnimaniaConfig.spawn.spawnLimitHorses)
			{
				event.setResult(Result.DENY);
				//System.out.println("Horse Denied Existence!");
			}
		}
		else if (AnimaniaConfig.spawn.spawnAnimaniaHorses && (event.getEntity() instanceof EntityAnimaniaHorse && !worldIn.isRemote))
		{
			List<EntityAnimaniaHorse> others = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaHorse.class, 100, event.getEntity().world, pos);
			if (others.size() > AnimaniaConfig.spawn.spawnLimitHorses)
			{
				event.setResult(Result.DENY);
				//System.out.println("Horse Denied Existence!");
			}
		}
		else if (AnimaniaConfig.spawn.spawnAnimaniaAmphibians && (event.getEntity() instanceof EntityAmphibian && !worldIn.isRemote))
		{
			List<EntityAmphibian> others = AnimaniaHelper.getEntitiesInRange(EntityAmphibian.class, 100, event.getEntity().world, pos);
			if (others.size() > AnimaniaConfig.spawn.spawnLimitAmphibians)
			{
				event.setResult(Result.DENY);
				//System.out.println("Amphibian Denied Existence!");
			}
		}
		else if (AnimaniaConfig.spawn.spawnAnimaniaAmphibians && (event.getEntity() instanceof EntityAmphibian && !worldIn.isRemote))
		{
			List<EntityAmphibian> others = AnimaniaHelper.getEntitiesInRange(EntityAmphibian.class, 100, event.getEntity().world, pos);
			if (others.size() > AnimaniaConfig.spawn.spawnLimitAmphibians)
			{
				event.setResult(Result.DENY);
				//System.out.println("Amphibian Denied Existence!");
			}
		}
		else if (AnimaniaConfig.spawn.spawnAnimaniaRodents && (event.getEntity() instanceof EntityHamster && !worldIn.isRemote))
		{
			List<EntityHamster> others = AnimaniaHelper.getEntitiesInRange(EntityHamster.class, 100, event.getEntity().world, pos);
			if (others.size() > AnimaniaConfig.spawn.spawnLimitHamsters)
			{
				event.setResult(Result.DENY);
				//System.out.println("Hamster Denied Existence!");
			}
		}
		else if (AnimaniaConfig.spawn.spawnAnimaniaRodents && (event.getEntity() instanceof EntityFerretGrey && !worldIn.isRemote))
		{
			List<EntityFerretGrey> others = AnimaniaHelper.getEntitiesInRange(EntityFerretGrey.class, 100, event.getEntity().world, pos);
			if (others.size() > AnimaniaConfig.spawn.spawnLimitFerrets)
			{
				event.setResult(Result.DENY);
				//System.out.println("Ferret Denied Existence!");
			}
		}
		else if (AnimaniaConfig.spawn.spawnAnimaniaRodents && (event.getEntity() instanceof EntityFerretWhite && !worldIn.isRemote))
		{
			List<EntityFerretWhite> others = AnimaniaHelper.getEntitiesInRange(EntityFerretWhite.class, 100, event.getEntity().world, pos);
			if (others.size() > AnimaniaConfig.spawn.spawnLimitFerrets)
			{
				event.setResult(Result.DENY);
				//System.out.println("Ferret Denied Existence!");
			}
		}
		else if (AnimaniaConfig.spawn.spawnAnimaniaRodents && (event.getEntity() instanceof EntityHedgehog && !worldIn.isRemote))
		{
			List<EntityHedgehog> others = AnimaniaHelper.getEntitiesInRange(EntityHedgehog.class, 100, event.getEntity().world, pos);
			if (others.size() > AnimaniaConfig.spawn.spawnLimitFerrets)
			{
				event.setResult(Result.DENY);
				//System.out.println("Hedgehog Denied Existence!");
			}
		}
		else if (AnimaniaConfig.spawn.spawnAnimaniaRodents && (event.getEntity() instanceof EntityHedgehogAlbino && !worldIn.isRemote))
		{
			List<EntityHedgehogAlbino> others = AnimaniaHelper.getEntitiesInRange(EntityHedgehogAlbino.class, 100, event.getEntity().world, pos);
			if (others.size() > AnimaniaConfig.spawn.spawnLimitFerrets)
			{
				event.setResult(Result.DENY);
				//System.out.println("Hedgehog Denied Existence!");
			}
		}

	}

}


