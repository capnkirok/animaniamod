package com.animania.common.events;

import java.util.List;
import java.util.Set;

import com.animania.Animania;
import com.animania.common.entities.amphibians.EntityAmphibian;
import com.animania.common.entities.chickens.EntityAnimaniaChicken;
import com.animania.common.entities.chickens.EntityHenBase;
import com.animania.common.entities.chickens.EntityHenLeghorn;
import com.animania.common.entities.chickens.EntityHenOrpington;
import com.animania.common.entities.chickens.EntityHenPlymouthRock;
import com.animania.common.entities.chickens.EntityHenRhodeIslandRed;
import com.animania.common.entities.chickens.EntityHenWyandotte;
import com.animania.common.entities.chickens.EntityRoosterBase;
import com.animania.common.entities.chickens.EntityRoosterLeghorn;
import com.animania.common.entities.chickens.EntityRoosterOrpington;
import com.animania.common.entities.chickens.EntityRoosterPlymouthRock;
import com.animania.common.entities.chickens.EntityRoosterRhodeIslandRed;
import com.animania.common.entities.chickens.EntityRoosterWyandotte;
import com.animania.common.entities.cows.EntityAnimaniaCow;
import com.animania.common.entities.cows.EntityBullAngus;
import com.animania.common.entities.cows.EntityBullBase;
import com.animania.common.entities.cows.EntityBullFriesian;
import com.animania.common.entities.cows.EntityBullHereford;
import com.animania.common.entities.cows.EntityBullHighland;
import com.animania.common.entities.cows.EntityBullHolstein;
import com.animania.common.entities.cows.EntityBullLonghorn;
import com.animania.common.entities.cows.EntityBullMooshroom;
import com.animania.common.entities.cows.EntityCowAngus;
import com.animania.common.entities.cows.EntityCowBase;
import com.animania.common.entities.cows.EntityCowFriesian;
import com.animania.common.entities.cows.EntityCowHereford;
import com.animania.common.entities.cows.EntityCowHighland;
import com.animania.common.entities.cows.EntityCowHolstein;
import com.animania.common.entities.cows.EntityCowLonghorn;
import com.animania.common.entities.cows.EntityCowMooshroom;
import com.animania.common.entities.goats.EntityAnimaniaGoat;
import com.animania.common.entities.goats.EntityBuckBase;
import com.animania.common.entities.goats.EntityDoeBase;
import com.animania.common.entities.horses.EntityAnimaniaHorse;
import com.animania.common.entities.horses.EntityMareDraftHorse;
import com.animania.common.entities.horses.EntityStallionDraftHorse;
import com.animania.common.entities.peacocks.EntityAnimaniaPeacock;
import com.animania.common.entities.pigs.EntityAnimaniaPig;
import com.animania.common.entities.pigs.EntityHogBase;
import com.animania.common.entities.pigs.EntityHogDuroc;
import com.animania.common.entities.pigs.EntityHogHampshire;
import com.animania.common.entities.pigs.EntityHogLargeBlack;
import com.animania.common.entities.pigs.EntityHogOldSpot;
import com.animania.common.entities.pigs.EntityHogYorkshire;
import com.animania.common.entities.pigs.EntityPigletYorkshire;
import com.animania.common.entities.pigs.EntitySowBase;
import com.animania.common.entities.pigs.EntitySowDuroc;
import com.animania.common.entities.pigs.EntitySowHampshire;
import com.animania.common.entities.pigs.EntitySowLargeBlack;
import com.animania.common.entities.pigs.EntitySowOldSpot;
import com.animania.common.entities.pigs.EntitySowYorkshire;
import com.animania.common.entities.rodents.EntityFerretBase;
import com.animania.common.entities.rodents.EntityHamster;
import com.animania.common.entities.rodents.EntityHedgehogBase;
import com.animania.common.entities.rodents.rabbits.EntityAnimaniaRabbit;
import com.animania.common.entities.rodents.rabbits.EntityRabbitBuckBase;
import com.animania.common.entities.rodents.rabbits.EntityRabbitBuckChinchilla;
import com.animania.common.entities.rodents.rabbits.EntityRabbitBuckCottontail;
import com.animania.common.entities.rodents.rabbits.EntityRabbitBuckHavana;
import com.animania.common.entities.rodents.rabbits.EntityRabbitBuckJack;
import com.animania.common.entities.rodents.rabbits.EntityRabbitDoeBase;
import com.animania.common.entities.rodents.rabbits.EntityRabbitDoeChinchilla;
import com.animania.common.entities.rodents.rabbits.EntityRabbitDoeCottontail;
import com.animania.common.entities.rodents.rabbits.EntityRabbitDoeHavana;
import com.animania.common.entities.rodents.rabbits.EntityRabbitDoeJack;
import com.animania.common.entities.sheep.EntityAnimaniaSheep;
import com.animania.common.entities.sheep.EntityEweBase;
import com.animania.common.entities.sheep.EntityEweDorset;
import com.animania.common.entities.sheep.EntityEweFriesian;
import com.animania.common.entities.sheep.EntityEweMerino;
import com.animania.common.entities.sheep.EntityEweSuffolk;
import com.animania.common.entities.sheep.EntityRamBase;
import com.animania.common.entities.sheep.EntityRamDorset;
import com.animania.common.entities.sheep.EntityRamFriesian;
import com.animania.common.entities.sheep.EntityRamMerino;
import com.animania.common.entities.sheep.EntityRamSuffolk;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.config.AnimaniaConfig;
import com.google.common.collect.Lists;

import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent.CheckSpawn;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventReplaceSpawnAnimals

{
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onEntitySpawn(EntityJoinWorldEvent event)
	{

		BlockPos pos = new BlockPos(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
		World worldIn = event.getWorld();
		Biome biome = event.getWorld().getBiome(pos);
		int chooser = 0;

		// Remove + replace vanilla animals that make it past the spawn list
		if (AnimaniaConfig.gameRules.replaceVanillaCows && (event.getEntity().getClass().equals(EntityCow.class) || event.getEntity().getClass().equals(EntityMooshroom.class)) && !worldIn.isRemote)
		{
			if (!event.getEntity().hasCustomName())
			{
				event.setCanceled(true);
			}

			List<EntityAnimaniaCow> others = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaCow.class, 32, event.getEntity().world, pos);
			if (others.size() < 2)
			{
				chooser = Animania.RANDOM.nextInt(3);
				
				if (BiomeDictionary.hasType(biome, AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.cowHolsteinBiomeTypes)[0])) {
					if (chooser == 1) {
						EntityCowHolstein entity = new EntityCowHolstein(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 2)
					{
						EntityBullHolstein entity = new EntityBullHolstein(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}

				}
				else if (BiomeDictionary.hasType(biome, AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.cowLonghornBiomeTypes)[0])) {
					if (chooser == 1) {
						EntityCowLonghorn entity = new EntityCowLonghorn(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 2)
					{
						EntityBullLonghorn entity = new EntityBullLonghorn(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}

				}
				else if (BiomeDictionary.hasType(biome, AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.cowHerefordBiomeTypes)[0])) {
					if (chooser == 1) {
						EntityCowHereford entity = new EntityCowHereford(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 2)
					{
						EntityBullHereford entity = new EntityBullHereford(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}

				}
				else if (BiomeDictionary.hasType(biome, AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.cowHighlandBiomeTypes)[0])) {
					if (chooser == 1) {
						EntityCowHighland entity = new EntityCowHighland(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 2)
					{
						EntityBullHighland entity = new EntityBullHighland(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}

				}
				else if (BiomeDictionary.hasType(biome, AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.cowAngusBiomeTypes)[0])) {
					if (chooser == 1) {
						EntityCowAngus entity = new EntityCowAngus(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 2)
					{
						EntityBullAngus entity = new EntityBullAngus(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}

				}
				else if (BiomeDictionary.hasType(biome, AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.cowMooshroomBiomeTypes)[0])) {
					
					if (chooser == 1) {
						EntityCowMooshroom entity = new EntityCowMooshroom(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 2)
					{
						EntityBullMooshroom entity = new EntityBullMooshroom(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}

				}
				else if (chooser == 1)
				{
					EntityCowFriesian entity = new EntityCowFriesian(worldIn);
					entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
					worldIn.spawnEntity(entity);
				}
				else if (chooser == 2)
				{
					EntityBullFriesian entity = new EntityBullFriesian(worldIn);
					entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
					worldIn.spawnEntity(entity);
				}
			}

		}
		else if (AnimaniaConfig.gameRules.replaceVanillaPigs && event.getEntity().getClass().equals(EntityPig.class) && !worldIn.isRemote)
		{

			if (!event.getEntity().hasCustomName())
			{
				event.setCanceled(true);
			}

			List<EntityAnimaniaPig> others = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaPig.class, 32, event.getEntity().world, pos);
			if (others.size() < 2)
			{
				chooser = Animania.RANDOM.nextInt(3);

				if (BiomeDictionary.hasType(biome, AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.pigOldSpotBiomeTypes)[0])) {
          
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
				else if (BiomeDictionary.hasType(biome, AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.pigDurocBiomeTypes)[0])) {
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
				else if (BiomeDictionary.hasType(biome, AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.pigLargeBlackBiomeTypes)[0])) {
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
				event.setCanceled(true);
			}

			List<EntityAnimaniaChicken> others = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaChicken.class, 32, event.getEntity().world, pos);
			if (others.size() < 2)
			{
				chooser = Animania.RANDOM.nextInt(3);

				if (BiomeDictionary.hasType(biome, AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.chickenOrpingtonBiomeTypes)[0])) {
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
				else if (BiomeDictionary.hasType(biome, AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.chickenPlymouthRockBiomeTypes)[0])) {
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
				else if (BiomeDictionary.hasType(biome, AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.chickenRhodeIslandRedBiomeTypes)[0])) {
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
				else if (BiomeDictionary.hasType(biome, AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.chickenWyandotteBiomeTypes)[0])) {
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
				event.setCanceled(true);
			}

			List<EntityAnimaniaSheep> others = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaSheep.class, 32, event.getEntity().world, pos);
			if (others.size() < 2)
			{
				chooser = Animania.RANDOM.nextInt(3);

				if (BiomeDictionary.hasType(biome, AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.sheepDorsetBiomeTypes)[0])) {
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

				else if (BiomeDictionary.hasType(biome, AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.sheepSuffolkBiomeTypes)[0])) {
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
				else if (BiomeDictionary.hasType(biome, AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.sheepDorsetBiomeTypes)[0])) {
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
				else if (BiomeDictionary.hasType(biome, AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.sheepMerinoBiomeTypes)[0])) {
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
				event.setCanceled(true);
			}

			List<EntityAnimaniaSheep> others = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaSheep.class, 32, event.getEntity().world, pos);
			if (others.size() < 2)
			{
				chooser = Animania.RANDOM.nextInt(3);

				if (BiomeDictionary.hasType(biome, AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.rabbitCottontailBiomeTypes)[0])) {
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
				else if (BiomeDictionary.hasType(biome, AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.rabbitJackBiomeTypes)[0]) || BiomeDictionary.hasType(biome, AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.rabbitJackBiomeTypes)[1])) {
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
				else if (BiomeDictionary.hasType(biome, AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.rabbitHavanaBiomeTypes)[0])) {
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
				else if (BiomeDictionary.hasType(biome, AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.rabbitChinchillaBiomeTypes)[0])) {
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
				event.setCanceled(true);
			}

		}
		else if (!AnimaniaConfig.spawn.spawnFreshWaterSquids && event.getEntity().getClass().equals(EntitySquid.class) && !worldIn.isRemote)
		{

			if (!BiomeDictionary.hasType(biome, Type.OCEAN))
			{
				if (!event.getEntity().hasCustomName())
				{
					event.setCanceled(true);
				}
			}

			// Mob Riding
		}
		else if (event.getEntity().getClass().equals(EntityZombie.class) && AnimaniaConfig.gameRules.allowMobRiding)
		{

			if (worldIn.rand.nextFloat() < 0.05D)
			{
				if (worldIn.getClosestPlayerToEntity(event.getEntity(), 10) != null && !worldIn.isRemote && event.getEntity().getPosition().getY() > 60)
				{
					EntityZombie ez = (EntityZombie) event.getEntity();
					EntityHenLeghorn entitychicken1 = new EntityHenLeghorn(worldIn);
					entitychicken1.setLocationAndAngles(ez.posX, ez.posY, ez.posZ, ez.rotationYaw, 0.0F);
					entitychicken1.setChickenJockey(true);
					worldIn.spawnEntity(entitychicken1);
					ez.setChild(true);
					ez.startRiding(entitychicken1);

					// System.out.println("zombie");
				}

			}

		}
		else if (event.getEntity().getClass().equals(EntityPigZombie.class) && AnimaniaConfig.gameRules.allowMobRiding)
		{

			if (worldIn.rand.nextFloat() < 0.05D)
			{
				if (worldIn.getClosestPlayerToEntity(event.getEntity(), 10) != null && !worldIn.isRemote && event.getEntity().getPosition().getY() > 60)
				{
					EntityPigZombie ez = (EntityPigZombie) event.getEntity();
					EntityPigletYorkshire ep = new EntityPigletYorkshire(worldIn);
					ep.setLocationAndAngles(ez.posX, ez.posY, ez.posZ, ez.rotationYaw, 0.0F);
					worldIn.spawnEntity(ep);
					ez.setChild(true);
					ez.startRiding(ep);

					// System.out.println("pig zombie");
				}

			}

		}
		else if (event.getEntity().getClass().equals(EntitySkeleton.class) && AnimaniaConfig.gameRules.allowMobRiding)
		{

			if (worldIn.rand.nextFloat() < 0.05D)
			{
				if (worldIn.getClosestPlayerToEntity(event.getEntity(), 10) != null && !worldIn.isRemote && event.getEntity().getPosition().getY() > 60)
				{
					EntitySkeleton ez = (EntitySkeleton) event.getEntity();
					EntityBullFriesian ef = new EntityBullFriesian(worldIn);
					ef.setLocationAndAngles(ez.posX, ez.posY, ez.posZ, ez.rotationYaw, 0.0F);
					worldIn.spawnEntity(ef);
					ez.startRiding(ef);
					ef.updatePassenger(ez);

					// System.out.println("skelly");
				}
			}
		}
	}

	@SubscribeEvent
	public void trySpawn(CheckSpawn event)
	{
		BlockPos pos = new BlockPos(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
		World worldIn = event.getWorld();
		Biome biome = event.getWorld().getBiome(pos);
		int chooser = 0;

		// SPAWN LIMIT ENFORCER

		// Amphibians
		if (event.getEntity() instanceof EntityAmphibian && !worldIn.isRemote)
		{
			List list = worldIn.loadedEntityList;

			int amphibianCount = 0;
			int num = 0;
			for (int i = 0; i < list.size(); i++)
			{
				if (list.get(i) instanceof EntityAmphibian)
				{
					num++;
				}
			}
			amphibianCount = num;

			EntityAmphibian checkTamed = (EntityAmphibian) event.getEntity();
			if (amphibianCount >= AnimaniaConfig.spawn.spawnLimitAmphibians && worldIn.getClosestPlayerToEntity(event.getEntity(), 3) == null)
			{
				if (!checkTamed.hasCustomName() && checkTamed.getAge() == 0)
				{
					event.setResult(Result.DENY);
				}
			}

			if (!AnimaniaConfig.spawn.spawnAnimaniaAmphibians)
			{
				event.setResult(Result.DENY);
			}

			// Cows
		}
		else if ((event.getEntity() instanceof EntityCowBase || event.getEntity() instanceof EntityBullBase) && !worldIn.isRemote)
		{
			List list = worldIn.loadedEntityList;

			int cowCount = 0;
			int num = 0;
			for (int i = 0; i < list.size(); i++)
			{
				if (list.get(i) instanceof EntityAnimaniaCow)
				{
					num++;
				}
			}
			cowCount = num;

			// System.out.println("Animania Cow count: " + cowCount);

			EntityAnimaniaCow checkTamed = (EntityAnimaniaCow) event.getEntity();

			if (cowCount >= AnimaniaConfig.spawn.spawnLimitCows && worldIn.getClosestPlayerToEntity(event.getEntity(), 3) == null)
			{
				if (!checkTamed.hasCustomName() && checkTamed.getAge() == 0)
				{
					event.setResult(Result.DENY);
					event.getEntity().setDead();
				}
			}

			if (!AnimaniaConfig.spawn.spawnAnimaniaCows)
			{
				event.setResult(Result.DENY);
			}

			// Pigs
		}
		else if ((event.getEntity() instanceof EntityHogBase || event.getEntity() instanceof EntitySowBase) && !worldIn.isRemote)
		{
			List list = worldIn.loadedEntityList;

			int pigCount = 0;
			int num = 0;
			for (int i = 0; i < list.size(); i++)
			{
				if (list.get(i) instanceof EntityAnimaniaPig)
				{
					num++;
				}
			}
			pigCount = num;

			// System.out.println("Animania Pig count: " + pigCount);

			EntityAnimaniaPig checkTamed = (EntityAnimaniaPig) event.getEntity();
			if (pigCount >= AnimaniaConfig.spawn.spawnLimitPigs && worldIn.getClosestPlayerToEntity(event.getEntity(), 3) == null)
			{
				if (!checkTamed.hasCustomName() && checkTamed.getAge() == 0)
				{
					event.setResult(Result.DENY);
				}
			}

			if (!AnimaniaConfig.spawn.spawnAnimaniaPigs)
			{
				event.setResult(Result.DENY);
			}

			// Sheep
		}
		else if ((event.getEntity() instanceof EntityRamBase || event.getEntity() instanceof EntityEweBase) && !worldIn.isRemote)
		{

			List list = worldIn.loadedEntityList;

			int sheepCount = 0;
			int num = 0;
			for (int i = 0; i < list.size(); i++)
			{
				if (list.get(i) instanceof EntityAnimaniaSheep)
				{
					num++;
				}
			}
			sheepCount = num;
			// System.out.println("Animania Sheep count: " + sheepCount);

			EntityAnimaniaSheep checkTamed = (EntityAnimaniaSheep) event.getEntity();
			if (sheepCount >= AnimaniaConfig.spawn.spawnLimitSheep && worldIn.getClosestPlayerToEntity(event.getEntity(), 3) == null)
			{
				if (!checkTamed.hasCustomName() && checkTamed.getAge() == 0)
				{
					event.setResult(Result.DENY);
				}
			}

			if (!AnimaniaConfig.spawn.spawnAnimaniaSheep)
			{
				event.setResult(Result.DENY);
			}

			// Chickens
		}
		else if ((event.getEntity() instanceof EntityRoosterBase || event.getEntity() instanceof EntityHenBase) && !worldIn.isRemote)
		{
			List list = worldIn.loadedEntityList;
			int chickenCount = 0;
			int num = 0;
			for (int i = 0; i < list.size(); i++)
			{
				if (list.get(i) instanceof EntityAnimaniaChicken)
				{
					num++;
				}
			}
			chickenCount = num;

			// System.out.println("Chicken count: " + chickenCount);

			EntityAnimaniaChicken checkTamed = (EntityAnimaniaChicken) event.getEntity();
			if (chickenCount >= AnimaniaConfig.spawn.spawnLimitChickens && worldIn.getClosestPlayerToEntity(event.getEntity(), 3) == null)
			{
				if (!checkTamed.hasCustomName() && checkTamed.getAge() == 0)
				{
					event.setResult(Result.DENY);
				}
			}

			if (!AnimaniaConfig.spawn.spawnAnimaniaChickens)
			{
				event.setResult(Result.DENY);
			}

			// Rabbits
		}
		else if ((event.getEntity() instanceof EntityRabbitBuckBase || event.getEntity() instanceof EntityRabbitDoeBase) && !worldIn.isRemote)
		{

			List list = worldIn.loadedEntityList;

			int rabbitCount = 0;
			int num = 0;
			for (int i = 0; i < list.size(); i++)
			{
				if (list.get(i) instanceof EntityAnimaniaRabbit)
				{
					num++;
				}
			}
			rabbitCount = num;
			// System.out.println("Animania Rabbit count: " + rabbitCount);

			EntityAnimaniaRabbit checkTamed = (EntityAnimaniaRabbit) event.getEntity();
			if (rabbitCount >= AnimaniaConfig.spawn.spawnLimitRabbits && worldIn.getClosestPlayerToEntity(event.getEntity(), 3) == null)
			{
				if (!checkTamed.hasCustomName() && checkTamed.getAge() == 0)
				{
					event.setResult(Result.DENY);
				}
			}

			if (!AnimaniaConfig.spawn.spawnAnimaniaRabbits)
			{
				event.setResult(Result.DENY);
			}

			// Horses
		}
		else if ((event.getEntity() instanceof EntityStallionDraftHorse || event.getEntity() instanceof EntityMareDraftHorse) && !worldIn.isRemote)
		{
			List list = worldIn.loadedEntityList;
			int horseCount = 0;
			int num = 0;
			for (int i = 0; i < list.size(); i++)
			{
				if (list.get(i) instanceof EntityAnimaniaHorse)
				{
					num++;
				}
			}

			horseCount = num;
			// System.out.println("Horse Count: " + horseCount);

			EntityAnimaniaHorse checkTamed = (EntityAnimaniaHorse) event.getEntity();
			if (horseCount >= AnimaniaConfig.spawn.spawnLimitHorses && worldIn.getClosestPlayerToEntity(event.getEntity(), 3) == null)
			{
				if (!checkTamed.hasCustomName() && checkTamed.getAge() == 0)
				{
					event.setResult(Result.DENY);
				}
			}

			if (!AnimaniaConfig.spawn.spawnAnimaniaHorses)
			{
				event.setResult(Result.DENY);
			}

			// Peacocks
		}
		else if (event.getEntity() instanceof EntityAnimaniaPeacock && !worldIn.isRemote)
		{
			List list = worldIn.loadedEntityList;

			int peacockCount = 0;
			int num = 0;
			for (int i = 0; i < list.size(); i++)
			{
				if (list.get(i) instanceof EntityAnimaniaPeacock)
				{
					num++;
				}
			}
			peacockCount = num;

			// System.out.println("Peacock count: " + peacockCount);

			EntityAnimaniaPeacock checkTamed = (EntityAnimaniaPeacock) event.getEntity();
			if (peacockCount >= AnimaniaConfig.spawn.spawnLimitPeacocks && worldIn.getClosestPlayerToEntity(event.getEntity(), 3) == null)
			{
				if (!checkTamed.hasCustomName() && checkTamed.getAge() == 0)
				{
					event.setResult(Result.DENY);
				}
			}

			if (!AnimaniaConfig.spawn.spawnAnimaniaPeacocks)
			{
				event.setResult(Result.DENY);
			}

			// Goats
		}
		else if ((event.getEntity() instanceof EntityBuckBase || event.getEntity() instanceof EntityDoeBase) && !worldIn.isRemote)
		{
			List list = worldIn.loadedEntityList;

			int goatCount = 0;
			int num = 0;
			for (int i = 0; i < list.size(); i++)
			{
				if (list.get(i) instanceof EntityAnimaniaGoat)
				{
					num++;
				}
			}
			goatCount = num;

			// System.out.println("Goat count: " + goatCount);

			EntityAnimaniaGoat checkTamed = (EntityAnimaniaGoat) event.getEntity();
			if (goatCount >= AnimaniaConfig.spawn.spawnLimitGoats && worldIn.getClosestPlayerToEntity(event.getEntity(), 3) == null)
			{
				if (!checkTamed.hasCustomName() && checkTamed.getAge() == 0)
				{
					event.setResult(Result.DENY);

				}
			}

			if (!AnimaniaConfig.spawn.spawnAnimaniaGoats)
			{
				event.setResult(Result.DENY);
			}

			// Hamsters
		}
		else if (event.getEntity() instanceof EntityHamster && !worldIn.isRemote)
		{

			List list = worldIn.loadedEntityList;

			int hamsterCount = 0;
			int num = 0;
			for (int i = 0; i < list.size(); i++)
			{
				if (list.get(i) instanceof EntityHamster)
				{
					num++;
				}
			}
			hamsterCount = num;

			// System.out.println("Hamster count: " + hamsterCount);

			if (hamsterCount >= AnimaniaConfig.spawn.spawnLimitHamsters && worldIn.getClosestPlayerToEntity(event.getEntity(), 3) == null)
				if (!event.getEntity().hasCustomName())
				{
					EntityHamster checkTamed = (EntityHamster) event.getEntity();
					if (!checkTamed.isInLove() && checkTamed.isTamed())
					{
						event.setResult(Result.DENY);
					}
				}

			if (!AnimaniaConfig.spawn.spawnAnimaniaRodents)
			{
				event.setResult(Result.DENY);
			}

			// Ferrets
		}
		else if (event.getEntity() instanceof EntityFerretBase && !worldIn.isRemote)
		{

			List list = worldIn.loadedEntityList;

			int ferretCount = 0;
			int num = 0;
			for (int i = 0; i < list.size(); i++)
			{
				if (list.get(i) instanceof EntityFerretBase)
				{
					num++;
				}
			}
			ferretCount = num;

			// System.out.println("Ferret count: " + ferretCount);

			EntityFerretBase checkTamed = (EntityFerretBase) event.getEntity();
			if (ferretCount >= AnimaniaConfig.spawn.spawnLimitFerrets && worldIn.getClosestPlayerToEntity(event.getEntity(), 3) == null)
			{
				if (!checkTamed.hasCustomName() && !checkTamed.getAge())
				{
					event.setResult(Result.DENY);
				}
			}

			if (!AnimaniaConfig.spawn.spawnAnimaniaRodents)
			{
				event.setResult(Result.DENY);
			}

			// Hedgehogs
		}
		else if (event.getEntity() instanceof EntityHedgehogBase && !worldIn.isRemote)
		{

			List list = worldIn.loadedEntityList;

			int hedgehogCount = 0;
			int num = 0;
			for (int i = 0; i < list.size(); i++)
			{
				if (list.get(i) instanceof EntityHedgehogBase)
				{
					num++;
				}
			}
			hedgehogCount = num;
			// System.out.println("Hedgehog count: " + hedgehogCount);

			EntityHedgehogBase checkTamed = (EntityHedgehogBase) event.getEntity();
			if (hedgehogCount >= AnimaniaConfig.spawn.spawnLimitHedgehogs && worldIn.getClosestPlayerToEntity(event.getEntity(), 3) == null)
			{
				if (!checkTamed.hasCustomName() && !checkTamed.getAge())
				{
					event.setResult(Result.DENY);
				}
			}

			if (!AnimaniaConfig.spawn.spawnAnimaniaRodents)
			{
				event.setResult(Result.DENY);
			}

		}
	}
	
	private static Biome[] getBiomes(BiomeDictionary.Type type)
	{
		List<Biome> criteriaMet = Lists.newArrayList();
		for (Biome b : Biome.REGISTRY)
		{
			Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(b);
			if (types.contains(type))
			{
				criteriaMet.add(b);
			}
			
		}

		if (BiomeDictionary.getBiomes(type).isEmpty()) {
			System.out.println(type.getName() + I18n.translateToLocal("text.error.invalidbiometype"));
		}
		
		return criteriaMet.toArray(new Biome[criteriaMet.size()]);
	}

}