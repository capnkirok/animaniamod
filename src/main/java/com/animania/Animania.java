package com.animania;

import com.animania.common.creativeTab.TabAnimaniaEntities;
import com.animania.common.creativeTab.TabAnimaniaResources;
import com.animania.proxy.CommonProxy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Animania.MODID, name = Animania.NAME, version = Animania.VERSION, guiFactory = "com.animania.client.gui.GuiFactoryAnimania")
public class Animania {

	@SidedProxy(clientSide = "com.animania.proxy.ClientProxy", serverSide = "com.animania.proxy.ServerProxy")
	public static CommonProxy proxy;

	// Instance
	@Instance(Animania.MODID)
	public static Animania instance;

	public static final String MODID = "animania";
	public static final String VERSION = "1.0.4.7";
	public static final String NAME = "Animania";

	// Tabs
	public static CreativeTabs TabAnimaniaEggs = new TabAnimaniaEntities(CreativeTabs.getNextID(), "Animania");
	public static CreativeTabs TabAnimaniaResources = new TabAnimaniaResources(CreativeTabs.getNextID(), "Animania");

		//hamsterBall = new ItemHamsterBall().setUnlocalizedName("hamsterBall");
		//GameRegistry.registerItem(hamsterBall, "hamsterBall");

		//DAMAGE
		bullDamage = new DamageSource("bull").setDamageBypassesArmor();

		//ITEMS 
		//Items for Animals
		hamsterFood = new ItemHamsterFood();
		truffle = new ItemTruffle();
		bucketSlop = new ItemBucketSlop();
		carvingKnife = new ItemCarvingKnife(ToolMaterial.IRON);
		cheeseMold = new ItemCheeseMold();
		milkBucketFriesian = new ItemBucketMilk("friesian");
		milkBucketHolstein = new ItemBucketMilk("holstein");
		peacockFeatherBlue = new ItemPeacockFeather("blue");
		peacockFeatherWhite = new ItemPeacockFeather("white");

		//Other foods
		ultimateOmelette = new ItemOmelette("super");
		truffleOmelette = new ItemOmelette("truffle");
		baconOmelette = new ItemOmelette("bacon");
		cheeseOmelette = new ItemOmelette("cheese");
		plainOmelette = new ItemOmelette("plain");
		truffleSoup = new ItemTruffleSoup();
		chocolateTruffle = new ItemChocolateTruffle();

		//ITEMS produced by Animals
		//COW ITEMS
		rawHerefordBeef = new ItemRawHerefordBeef();
		rawLonghornBeef = new ItemRawLonghornBeef();
		rawAngusBeef = new ItemRawAngusBeef();

		rawLonghornSteak = new ItemRawLonghornSteak();
		rawHerefordSteak = new ItemRawHerefordSteak();
		rawAngusSteak = new ItemRawAngusSteak();

		cookedLonghornRoast = new ItemCookedLonghornRoast();
		cookedHerefordRoast = new ItemCookedHerefordRoast();
		cookedAngusRoast = new ItemCookedAngusRoast();

		cookedLonghornSteak = new ItemCookedLonghornSteak();
		cookedHerefordSteak = new ItemCookedHerefordSteak();
		cookedAngusSteak = new ItemCookedAngusSteak();

		rawPrimeBeef = new ItemRawPrimeBeef();
		cookedPrimeBeef = new ItemCookedPrimeBeef();
		rawPrimeSteak = new ItemRawPrimeSteak();
		cookedPrimeSteak = new ItemCookedPrimeSteak();

		//PIG ITEMS
		rawLargeBlackPork = new ItemRawLargeBlackPork();
		rawDurocPork = new ItemRawDurocPork();
		rawOldSpotPork = new ItemRawOldSpotPork();
		rawHampshirePork = new ItemRawHampshirePork();

		rawLargeBlackBacon = new ItemRawLargeBlackBacon();
		rawDurocBacon = new ItemRawDurocBacon();
		rawOldSpotBacon = new ItemRawOldSpotBacon();
		rawHampshireBacon = new ItemRawHampshireBacon();

		cookedLargeBlackRoast = new ItemCookedLargeBlackRoast();
		cookedDurocRoast = new ItemCookedDurocRoast();
		cookedOldSpotRoast = new ItemCookedOldSpotRoast();
		cookedHampshireRoast = new ItemCookedHampshireRoast();

		cookedLargeBlackBacon = new ItemCookedLargeBlackBacon();
		cookedDurocBacon = new ItemCookedDurocBacon();
		cookedOldSpotBacon = new ItemCookedOldSpotBacon();
		cookedHampshireBacon = new ItemCookedHampshireBacon();

		rawPrimePork = new ItemRawPrimePork();
		cookedPrimePork = new ItemCookedPrimePork();
		rawPrimeBacon = new ItemRawPrimeBacon();
		cookedPrimeBacon = new ItemCookedPrimeBacon();

		//CHICKEN ITEMS
		rawOrpingtonChicken = new ItemRawOrpingtonChicken();
		rawPlymouthRockChicken = new ItemRawPlymouthRockChicken();
		rawWyandotteChicken = new ItemRawWyandotteChicken();
		rawRhodeIslandRedChicken = new ItemRawRhodeIslandRedChicken();
		cookedOrpingtonChicken = new ItemCookedOrpingtonChicken();
		cookedPlymouthRockChicken = new ItemCookedPlymouthRockChicken();
		cookedWyandotteChicken = new ItemCookedWyandotteChicken();
		cookedRhodeIslandRedChicken = new ItemCookedRhodeIslandRedChicken();
		rawPrimeChicken = new ItemRawPrimeChicken();
		cookedPrimeChicken = new ItemCookedPrimeChicken();
		brownEgg = new ItemBrownEgg();

		//CHEESE
		cheeseWheelFriesian = new ItemCheeseWheel("friesian");
		cheeseWheelHolstein = new ItemCheeseWheel("holstein");
		cheeseWedgeFriesian = new ItemCheeseWedge("friesian");
		cheeseWedgeHolstein = new ItemCheeseWedge("holstein");

		//Item Entity Eggs
		//COWS
		entityeggcalfholstein = new ItemEntityEgg("calf_holstein");
		entityeggcowholstein = new ItemEntityEgg("cow_holstein");
		entityeggbullholstein = new ItemEntityEgg("bull_holstein");

		entityeggcalffriesian = new ItemEntityEgg("calf_friesian");
		entityeggcowfriesian = new ItemEntityEgg("cow_friesian");
		entityeggbullfriesian = new ItemEntityEgg("bull_friesian");

		entityeggcalfangus = new ItemEntityEgg("calf_angus");
		entityeggcowangus = new ItemEntityEgg("cow_angus");
		entityeggbullangus = new ItemEntityEgg("bull_angus");

		entityeggcalflonghorn = new ItemEntityEgg("calf_longhorn");
		entityeggcowlonghorn = new ItemEntityEgg("cow_longhorn");
		entityeggbulllonghorn = new ItemEntityEgg("bull_longhorn");

		entityeggcalfhereford = new ItemEntityEgg("calf_hereford");
		entityeggcowhereford = new ItemEntityEgg("cow_hereford");
		entityeggbullhereford = new ItemEntityEgg("bull_hereford");

		entityeggrandomcow = new ItemEntityEgg("cow_random");

		//CHICKENS
		entityeggchickplymouth = new ItemEntityEgg("chick_plymouth");
		entityegghenplymouth = new ItemEntityEgg("hen_plymouth");
		entityeggroosterplymouth = new ItemEntityEgg("rooster_plymouth");

		entityeggchickleghorn = new ItemEntityEgg("chick_leghorn");
		entityegghenleghorn = new ItemEntityEgg("hen_leghorn");
		entityeggroosterleghorn = new ItemEntityEgg("rooster_leghorn");

		entityeggchickorpington = new ItemEntityEgg("chick_orpington");
		entityegghenorpington = new ItemEntityEgg("hen_orpington");
		entityeggroosterorpington = new ItemEntityEgg("rooster_orpington");

		entityeggchickred = new ItemEntityEgg("chick_red");
		entityegghenred = new ItemEntityEgg("hen_red");
		entityeggroosterred = new ItemEntityEgg("rooster_red");

		entityeggchickwyandotte = new ItemEntityEgg("chick_wyandotte");
		entityegghenwyandotte = new ItemEntityEgg("hen_wyandotte");
		entityeggroosterwyandotte = new ItemEntityEgg("rooster_wyandotte");

		entityeggrandomchicken = new ItemEntityEgg("chicken_random");

		//PEAFOWL
		entityeggpeachickblue = new ItemEntityEgg("peachick_blue");
		entityeggpeafowlblue = new ItemEntityEgg("peafowl_blue");
		entityeggpeacockblue = new ItemEntityEgg("peacock_blue");

		entityeggpeachickwhite = new ItemEntityEgg("peachick_white");
		entityeggpeafowlwhite = new ItemEntityEgg("peafowl_white");
		entityeggpeacockwhite = new ItemEntityEgg("peacock_white");

		//PIGS
		entityeggpigletyorkshire = new ItemEntityEgg("piglet_yorkshire");
		entityeggsowyorkshire = new ItemEntityEgg("sow_yorkshire");
		entityegghogyorkshire = new ItemEntityEgg("hog_yorkshire");

		entityeggpigletoldspot = new ItemEntityEgg("piglet_oldspot");
		entityeggsowoldspot = new ItemEntityEgg("sow_oldspot");
		entityegghogoldspot = new ItemEntityEgg("hog_oldspot");

		entityeggpigletlargeblack = new ItemEntityEgg("piglet_largeblack");
		entityeggsowlargeblack = new ItemEntityEgg("sow_largeblack");
		entityegghoglargeblack = new ItemEntityEgg("hog_largeblack");

		entityeggpigletlargewhite = new ItemEntityEgg("piglet_largewhite");
		entityeggsowlargewhite = new ItemEntityEgg("sow_largewhite");
		entityegghoglargewhite = new ItemEntityEgg("hog_largewhite");

		entityeggpigletduroc = new ItemEntityEgg("piglet_duroc");
		entityeggsowduroc = new ItemEntityEgg("sow_duroc");
		entityegghogduroc = new ItemEntityEgg("hog_duroc");

		entityeggpiglethampshire = new ItemEntityEgg("piglet_hampshire");
		entityeggsowhampshire = new ItemEntityEgg("sow_hampshire");
		entityegghoghampshire = new ItemEntityEgg("hog_hampshire");

		entityeggrandompig = new ItemEntityEgg("pig_random");

		//RODENTS
		entityegghamster = new ItemEntityEgg("hamster");
		entityeggferretgrey = new ItemEntityEgg("ferret_grey");
		entityeggferretwhite = new ItemEntityEgg("ferret_white");
		entityegghedgehog = new ItemEntityEgg("hedgehog");
		entityegghedgehogalbino = new ItemEntityEgg("hedgehog_albino");

		//HORSES
		//entityeggdrafthorsestallion = new ItemEntityEgg("draft_horse_stallion");
		//entityeggdrafthorsemare = new ItemEntityEgg("draft_horse_mare");
		//entityeggdrafthorsefoal = new ItemEntityEgg("draft_horse_foal");

		//BLOCKS
		blockMud = new BlockMud().setHardness(1.0F).setResistance(1.0F);
		blockTrough = new BlockTrough().setHardness(1.0F).setResistance(1.0F);
		blockInvisiblock = new BlockInvisiblock().setHardness(1.0F).setResistance(1.0F);
		blockNest = new BlockNest().setHardness(1.0F).setResistance(1.0F);
		blockSeeds = new BlockSeeds();

		//ITEMBLOCKS
		itemBlockMud = new ItemBlock(blockMud);
		GameRegistry.register(itemBlockMud.setRegistryName(blockMud.getRegistryName()));

		itemBlockTrough = new ItemBlock(blockTrough);
		GameRegistry.register(itemBlockTrough.setRegistryName(blockTrough.getRegistryName()));

		itemBlockNest = new ItemBlock(blockNest);
		GameRegistry.register(itemBlockNest.setRegistryName(blockNest.getRegistryName()));

		//itemInvisiblock = new ItemBlock(blockInvisiblock);
		//GameRegistry.register(itemInvisiblock.setRegistryName(blockInvisiblock.getRegistryName()));

		//EVENTS
		ModSoundEvents.registerSounds();
		FMLCommonHandler.instance().bus().register(new LoginEventHandler());
		FMLCommonHandler.instance().bus().register(new ItemSeedHandler());
		FMLCommonHandler.instance().bus().register(new EggThrowHandler());
		FMLCommonHandler.instance().bus().register(new EventReplaceSpawnAnimals());
		FMLCommonHandler.instance().bus().register(new ConfigChangeEventHandler());
		UpdateHandler.init();
		AnimaniaAchievements.init();
		
		proxy.registerTextures();


	}

	@EventHandler
	public void load(FMLInitializationEvent event){

		proxy.registerRenderers();

		//Forge Events
		MinecraftForge.EVENT_BUS.register(new EventMudDamageCanceller());
		MinecraftForge.EVENT_BUS.register(new RemoveVanillaSpawns());

		//COWS
		if (Animania.spawnAnimaniaCows) {
			ResourceLocation ch1 = new ResourceLocation("animania:textures/entity/cows/cow_holstein.png");
			EntityRegistry.registerModEntity(ch1, EntityCowHolstein.class, "animania.CowHolstein", Animania.CowHolsteinID, this, 64, 3, true); 

			ResourceLocation ch2 = new ResourceLocation("animania:textures/entity/cows/bull_holstein.png");
			EntityRegistry.registerModEntity(ch2, EntityBullHolstein.class, "animania.BullHolstein", Animania.BullHolsteinID, this, 64, 3, true); 

			ResourceLocation ch3 = new ResourceLocation("animania:textures/entity/cows/calf_holstein.png");
			EntityRegistry.registerModEntity(ch3, EntityCalfHolstein.class, "animania.CalfHolstein", Animania.CalfHolsteinID, this, 64, 3, true); 
			EntityRegistry.addSpawn(EntityCowHolstein.class, Animania.spawnProbabilityCows, 1, Animania.numberCowFamilies, EnumCreatureType.CREATURE, Biomes.FOREST); 

			ResourceLocation cf1 = new ResourceLocation("animania:textures/entity/cows/cow_friesian.png");
			EntityRegistry.registerModEntity(cf1, EntityCowFriesian.class, "animania.CowFriesian", Animania.CowFriesianID, this, 64, 3, true); 

			ResourceLocation cf2 = new ResourceLocation("animania:textures/entity/cows/bull_friesian.png");
			EntityRegistry.registerModEntity(cf2, EntityBullFriesian.class, "animania.BullFriesian", Animania.BullFriesianID, this, 64, 3, true); 

			ResourceLocation cf3 = new ResourceLocation("animania:textures/entity/cows/calf_friesian.png");
			EntityRegistry.registerModEntity(cf3, EntityCalfFriesian.class, "animania.CalfFriesian", Animania.CalfFriesianID, this, 64, 3, true); 
			EntityRegistry.addSpawn(EntityCowFriesian.class, Animania.spawnProbabilityCows, 1, Animania.numberCowFamilies, EnumCreatureType.CREATURE, Biomes.PLAINS); 

			ResourceLocation ca1 = new ResourceLocation("animania:textures/entity/cows/cow_angus.png");
			EntityRegistry.registerModEntity(ca1, EntityCowAngus.class, "animania.CowAngus", Animania.CowAngusID, this, 64, 3, true); 

			ResourceLocation ca2 = new ResourceLocation("animania:textures/entity/cows/bull_angus.png");
			EntityRegistry.registerModEntity(ca2, EntityBullAngus.class, "animania.BullAngus", Animania.BullAngusID, this, 64, 3, true); 

			ResourceLocation ca3 = new ResourceLocation("animania:textures/entity/cows/calf_angus.png");
			EntityRegistry.registerModEntity(ca3, EntityCalfAngus.class, "animania.CalfAngus", Animania.CalfAngusID, this, 64, 3, true); 
			EntityRegistry.addSpawn(EntityCowAngus.class, Animania.spawnProbabilityCows, 1, Animania.numberCowFamilies, EnumCreatureType.CREATURE, Biomes.JUNGLE, Biomes.MESA, Biomes.MUSHROOM_ISLAND); 

			ResourceLocation cl1 = new ResourceLocation("animania:textures/entity/cows/cow_longhorn.png");
			EntityRegistry.registerModEntity(cl1, EntityCowLonghorn.class, "animania.CowLonghorn", Animania.CowLonghornID, this, 64, 3, true); 

			ResourceLocation cl2 = new ResourceLocation("animania:textures/entity/cows/bull_longhorn.png");
			EntityRegistry.registerModEntity(cl2, EntityBullLonghorn.class, "animania.BullLonghorn", Animania.BullLonghornID, this, 64, 3, true); 

			ResourceLocation cl3 = new ResourceLocation("animania:textures/entity/cows/calf_longhorn.png");
			EntityRegistry.registerModEntity(cl3, EntityCalfLonghorn.class, "animania.CalfLonghorn", Animania.CalfLonghornID, this, 64, 3, true); 
			EntityRegistry.addSpawn(EntityCowLonghorn.class, Animania.spawnProbabilityCows, 1, Animania.numberCowFamilies, EnumCreatureType.CREATURE, Biomes.SAVANNA); 

			ResourceLocation che1 = new ResourceLocation("animania:textures/entity/cows/cow_hereford.png");
			EntityRegistry.registerModEntity(che1, EntityCowHereford.class, "animania.CowHereford", Animania.CowHerefordID, this, 64, 3, true); 

			ResourceLocation che2 = new ResourceLocation("animania:textures/entity/cows/bull_hereford.png");
			EntityRegistry.registerModEntity(che2, EntityBullHereford.class, "animania.BullHereford", Animania.BullHerefordID, this, 64, 3, true); 

			ResourceLocation che3 = new ResourceLocation("animania:textures/entity/cows/calf_hereford.png");
			EntityRegistry.registerModEntity(che3, EntityCalfHereford.class, "animania.CalfHereford", Animania.CalfHerefordID, this, 64, 3, true); 
			EntityRegistry.addSpawn(EntityCowHereford.class, Animania.spawnProbabilityCows, 1, Animania.numberCowFamilies, EnumCreatureType.CREATURE, Biomes.FOREST_HILLS); 
		}



		//CHICKENS
		if (Animania.spawnAnimaniaChickens) {
			ResourceLocation chp1 = new ResourceLocation("animania:textures/entity/chickens/hen_specked.png");
			EntityRegistry.registerModEntity(chp1, EntityHenPlymouthRock.class, "animania.HenPlymouthRock", Animania.HenPlymouthRockID, this, 64, 3, true);
			EntityRegistry.addSpawn(EntityHenPlymouthRock.class, Animania.spawnProbabilityHens, 1, Animania.numberChickenFamilies, EnumCreatureType.CREATURE, Biomes.EXTREME_HILLS_WITH_TREES, Biomes.EXTREME_HILLS, Biomes.EXTREME_HILLS_EDGE, Biomes.FOREST_HILLS); 

			ResourceLocation chp2 = new ResourceLocation("animania:textures/entity/chickens/rooster_specked.png");
			EntityRegistry.registerModEntity(chp2, EntityRoosterPlymouthRock.class, "animania.RoosterPlymouthRock", Animania.RoosterPlymouthRockID, this, 64, 3, true); 

			ResourceLocation chp3 = new ResourceLocation("animania:textures/entity/chickens/chick_specked.png");
			EntityRegistry.registerModEntity(chp3, EntityChickPlymouthRock.class, "animania.ChickPlymouthRock", Animania.ChickPlymouthRockID, this, 64, 3, true); 

			ResourceLocation chl1 = new ResourceLocation("animania:textures/entity/chickens/hen_white.png");
			EntityRegistry.registerModEntity(chl1, EntityHenLeghorn.class, "animania.HenLeghorn", Animania.HenLeghornID, this, 64, 3, true); 
			EntityRegistry.addSpawn(EntityHenLeghorn.class, Animania.spawnProbabilityHens, 1, Animania.numberChickenFamilies, EnumCreatureType.CREATURE, Biomes.PLAINS); 

			ResourceLocation chl2 = new ResourceLocation("animania:textures/entity/chickens/rooster_white.png");
			EntityRegistry.registerModEntity(chl2, EntityRoosterLeghorn.class, "animania.RoosterLeghorn", Animania.RoosterLeghornID, this, 64, 3, true); 

			ResourceLocation chl3 = new ResourceLocation("animania:textures/entity/chickens/chick_white.png");
			EntityRegistry.registerModEntity(chl3, EntityChickLeghorn.class, "animania.ChickLeghorn", Animania.ChickLeghornID, this, 64, 3, true); 

			ResourceLocation cho1 = new ResourceLocation("animania:textures/entity/chickens/hen_golden.png");
			EntityRegistry.registerModEntity(cho1, EntityHenOrpington.class, "animania.HenOrpington", Animania.HenOrpingtonID, this, 64, 3, true); 
			EntityRegistry.addSpawn(EntityHenOrpington.class, Animania.spawnProbabilityHens, 1, Animania.numberChickenFamilies, EnumCreatureType.CREATURE, Biomes.JUNGLE, Biomes.SWAMPLAND); 

			ResourceLocation cho2 = new ResourceLocation("animania:textures/entity/chickens/rooster_golden.png");
			EntityRegistry.registerModEntity(cho2, EntityRoosterOrpington.class, "animania.RoosterOrpington", Animania.RoosterOrpingtonID, this, 64, 3, true); 

			ResourceLocation cho3 = new ResourceLocation("animania:textures/entity/chickens/chick_golden.png");
			EntityRegistry.registerModEntity(cho3, EntityChickOrpington.class, "animania.ChickOrpington", Animania.ChickOrpingtonID, this, 64, 3, true); 

			ResourceLocation chw1 = new ResourceLocation("animania:textures/entity/chickens/hen_brown.png");
			EntityRegistry.registerModEntity(chw1, EntityHenWyandotte.class, "animania.HenWyandotte", Animania.HenWyandotteID, this, 64, 3, true); 
			EntityRegistry.addSpawn(EntityHenWyandotte.class, Animania.spawnProbabilityHens, 1, Animania.numberChickenFamilies, EnumCreatureType.CREATURE, Biomes.FOREST); 

			ResourceLocation chw2 = new ResourceLocation("animania:textures/entity/chickens/rooster_brown.png");
			EntityRegistry.registerModEntity(chw2, EntityRoosterWyandotte.class, "animania.RoosterWyandotte", Animania.RoosterWyandotteID, this, 64, 3, true); 

			ResourceLocation chw3 = new ResourceLocation("animania:textures/entity/chickens/chick_brown.png");
			EntityRegistry.registerModEntity(chw3, EntityChickWyandotte.class, "animania.ChickWyandotte", Animania.ChickWyandotteID, this, 64, 3, true); 

			ResourceLocation chr1 = new ResourceLocation("animania:textures/entity/chickens/hen_red.png");
			EntityRegistry.registerModEntity(chr1, EntityHenRhodeIslandRed.class, "animania.HenRhodeIslandRed", Animania.HenRhodeIslandRedID, this, 64, 3, true); 
			EntityRegistry.addSpawn(EntityHenRhodeIslandRed.class, Animania.spawnProbabilityHens, 1, Animania.numberChickenFamilies, EnumCreatureType.CREATURE, Biomes.FOREST); 

			ResourceLocation chr2 = new ResourceLocation("animania:textures/entity/chickens/rooster_red.png");
			EntityRegistry.registerModEntity(chr2, EntityRoosterRhodeIslandRed.class, "animania.RoosterRhodeIslandRed", Animania.RoosterRhodeIslandRedID, this, 64, 3, true); 

			ResourceLocation chr3 = new ResourceLocation("animania:textures/entity/chickens/chick_red.png");
			EntityRegistry.registerModEntity(chr3, EntityChickRhodeIslandRed.class, "animania.ChickRhodeIslandRed", Animania.ChickRhodeIslandRedID, this, 64, 3, true); 

		}


		//PIGS
		if (Animania.spawnAnimaniaPigs) {
			ResourceLocation py1 = new ResourceLocation("animania:textures/entity/pigs/sow_yorkshire.png");
			EntityRegistry.registerModEntity(py1, EntitySowYorkshire.class, "animania.SowYorkshire", Animania.SowYorkshireID, this, 64, 3, true); 

			ResourceLocation py2 = new ResourceLocation("animania:textures/entity/chickens/hog_yorkshire.png");
			EntityRegistry.registerModEntity(py2, EntityHogYorkshire.class, "animania.HogYorkshire", Animania.HogYorkshireID, this, 64, 3, true); 

			ResourceLocation py3 = new ResourceLocation("animania:textures/entity/chickens/piglet_yorkshire.png");
			EntityRegistry.registerModEntity(py3, EntityPigletYorkshire.class, "animania.PigletYorkshire", Animania.PigletYorkshireID, this, 64, 3, true); 
			EntityRegistry.addSpawn(EntitySowYorkshire.class, Animania.spawnProbabilitySows, 1, Animania.numberPigFamilies, EnumCreatureType.CREATURE, Biomes.PLAINS); 

			ResourceLocation pos1 = new ResourceLocation("animania:textures/entity/pigs/sow_old_spot.png");
			EntityRegistry.registerModEntity(pos1, EntitySowOldSpot.class, "animania.SowOldSpot", Animania.SowOldSpotID, this, 64, 3, true); 

			ResourceLocation pos2 = new ResourceLocation("animania:textures/entity/pigs/hog_old_spot.png");
			EntityRegistry.registerModEntity(pos2, EntityHogOldSpot.class, "animania.HogOldSpot", Animania.HogOldSpotID, this, 64, 3, true); 

			ResourceLocation pos3 = new ResourceLocation("animania:textures/entity/pigs/piglet_old_spot.png");
			EntityRegistry.registerModEntity(pos3, EntityPigletOldSpot.class, "animania.PigletOldSpot", Animania.PigletOldSpotID, this, 64, 3, true); 
			EntityRegistry.addSpawn(EntitySowOldSpot.class, Animania.spawnProbabilitySows, 1, Animania.numberPigFamilies, EnumCreatureType.CREATURE, Biomes.FOREST); 

			ResourceLocation plb1 = new ResourceLocation("animania:textures/entity/pigs/sow_large_black.png");
			EntityRegistry.registerModEntity(plb1, EntitySowLargeBlack.class, "animania.SowLargeBlack", Animania.SowLargeBlackID, this, 64, 3, true); 

			ResourceLocation plb2 = new ResourceLocation("animania:textures/entity/pigs/hog_large_black.png");
			EntityRegistry.registerModEntity(plb2, EntityHogLargeBlack.class, "animania.HogLargeBlack", Animania.HogLargeBlackID, this, 64, 3, true); 

			ResourceLocation plb3 = new ResourceLocation("animania:textures/entity/pigs/piglet_large_black.png");
			EntityRegistry.registerModEntity(plb3, EntityPigletLargeBlack.class, "animania.PigletLargeBlack", Animania.PigletLargeBlackID, this, 64, 3, true); 
			EntityRegistry.addSpawn(EntitySowLargeBlack.class, Animania.spawnProbabilitySows, 1, Animania.numberPigFamilies, EnumCreatureType.CREATURE, Biomes.SWAMPLAND, Biomes.BIRCH_FOREST); 

			ResourceLocation plw1 = new ResourceLocation("animania:textures/entity/pigs/sow_large_white.png");
			EntityRegistry.registerModEntity(plw1, EntitySowLargeWhite.class, "animania.SowLargeWhite", Animania.SowLargeWhiteID, this, 64, 3, true); 

			ResourceLocation plw2 = new ResourceLocation("animania:textures/entity/pigs/hog_large_white.png");
			EntityRegistry.registerModEntity(plw2, EntityHogLargeWhite.class, "animania.HogLargeWhite", Animania.HogLargeWhiteID, this, 64, 3, true); 

			ResourceLocation plw3 = new ResourceLocation("animania:textures/entity/pigs/piglet_large_white.png");
			EntityRegistry.registerModEntity(plw3, EntityPigletLargeWhite.class, "animania.PigletLargeWhite", Animania.PigletLargeWhiteID, this, 64, 3, true); 
			EntityRegistry.addSpawn(EntitySowLargeWhite.class, Animania.spawnProbabilitySows, 1, Animania.numberPigFamilies, EnumCreatureType.CREATURE, Biomes.FOREST_HILLS, Biomes.BIRCH_FOREST_HILLS); 

			ResourceLocation pd1 = new ResourceLocation("animania:textures/entity/pigs/sow_duroc.png");
			EntityRegistry.registerModEntity(pd1, EntitySowDuroc.class, "animania.SowDuroc", Animania.SowDurocID, this, 64, 3, true); 

			ResourceLocation pd2 = new ResourceLocation("animania:textures/entity/pigs/hog_duroc.png");
			EntityRegistry.registerModEntity(pd2, EntityHogDuroc.class, "animania.HogDuroc", Animania.HogDurocID, this, 64, 3, true); 

			ResourceLocation pd3 = new ResourceLocation("animania:textures/entity/pigs/piglet_duroc.png");
			EntityRegistry.registerModEntity(pd3, EntityPigletDuroc.class, "animania.PigletDuroc", Animania.PigletDurocID, this, 64, 3, true); 
			EntityRegistry.addSpawn(EntitySowDuroc.class, Animania.spawnProbabilitySows, 1, Animania.numberPigFamilies, EnumCreatureType.CREATURE, Biomes.JUNGLE); 

			ResourceLocation ph1 = new ResourceLocation("animania:textures/entity/pigs/sow_hampshire.png");
			EntityRegistry.registerModEntity(ph1, EntitySowHampshire.class, "animania.SowHampshire", Animania.SowHampshireID, this, 64, 3, true); 

			ResourceLocation ph2 = new ResourceLocation("animania:textures/entity/pigs/hog_hampshire.png");
			EntityRegistry.registerModEntity(ph2, EntityHogHampshire.class, "animania.HogHampshire", Animania.HogHampshireID, this, 64, 3, true); 

			ResourceLocation ph3 = new ResourceLocation("animania:textures/entity/pigs/piglet_hampshire.png");
			EntityRegistry.registerModEntity(ph3, EntityPigletHampshire.class, "animania.PigletHampshire", Animania.PigletHampshireID, this, 64, 3, true); 
			EntityRegistry.addSpawn(EntitySowHampshire.class, Animania.spawnProbabilitySows, 1, Animania.numberPigFamilies, EnumCreatureType.CREATURE,  Biomes.EXTREME_HILLS, Biomes.EXTREME_HILLS_WITH_TREES); 
		}


		//RODENTS
		if (Animania.spawnAnimaniaRodents) {

			ResourceLocation ha1 = new ResourceLocation("animania:textures/entity/pigs/hamster_tarou.png");
			EntityRegistry.registerModEntity(ha1, EntityHamster.class, "animania.Hamster", Animania.HamsterMaleID, this, 64, 3, true); 
			EntityRegistry.addSpawn(EntityHamster.class, Animania.spawnProbabilityHamsters, 1, 2, EnumCreatureType.CREATURE, Biomes.BEACH, Biomes.DESERT, Biomes.DESERT_HILLS); 

			ResourceLocation fe1 = new ResourceLocation("animania:textures/entity/pigs/ferret_grey.png");
			EntityRegistry.registerModEntity(fe1, EntityFerretGrey.class, "animania.FerretGrey", Animania.FerretMaleGreyID, this, 64, 3, true); 

			ResourceLocation fe2 = new ResourceLocation("animania:textures/entity/pigs/ferret_white.png");
			EntityRegistry.registerModEntity(fe2, EntityFerretWhite.class, "animania.FerretWhite", Animania.FerretMaleWhiteID, this, 64, 3, true); 

			EntityRegistry.addSpawn(EntityFerretGrey.class, Animania.spawnProbabilityFerrets, 1, 2, EnumCreatureType.CREATURE, Biomes.SAVANNA);
			EntityRegistry.addSpawn(EntityFerretWhite.class, Animania.spawnProbabilityFerrets, 1, 2, EnumCreatureType.CREATURE, Biomes.SAVANNA);


			ResourceLocation he1 = new ResourceLocation("animania:textures/entity/pigs/hedgehog.png");
			EntityRegistry.registerModEntity(he1, EntityHedgehog.class, "animania.Hedgehog", Animania.HedgehogMaleID, this, 64, 3, true); 
			EntityRegistry.addSpawn(EntityHedgehog.class, Animania.spawnProbabilityHedgehogs, 1, 2, EnumCreatureType.CREATURE, Biomes.FOREST);

			ResourceLocation he2 = new ResourceLocation("animania:textures/entity/pigs/hedgehog_white.png");
			EntityRegistry.registerModEntity(he2, EntityHedgehogAlbino.class, "animania.HedgehogAlbino", Animania.HedgehogMaleAlbinoID, this, 64, 3, true); 
			EntityRegistry.addSpawn(EntityHedgehogAlbino.class, Animania.spawnProbabilityHedgehogs, 1, 2, EnumCreatureType.CREATURE, Biomes.SWAMPLAND);
		}


		//PEACOCKS
		if (Animania.spawnAnimaniaPeacocks) {
			ResourceLocation pb1 = new ResourceLocation("animania:textures/entity/pigs/peacock_blue.png");
			EntityRegistry.registerModEntity(pb1, EntityPeacockBlue.class, "animania.PeacockBlue", Animania.PeacockBlueID, this, 64, 3, true); 

			ResourceLocation pb2 = new ResourceLocation("animania:textures/entity/pigs/peafowl_blue.png");
			EntityRegistry.registerModEntity(pb2, EntityPeafowlBlue.class, "animania.PeafowlBlue", Animania.PeafowlBlueID, this, 64, 3, true); 

			ResourceLocation pb3 = new ResourceLocation("animania:textures/entity/pigs/peachick_blue.png");
			EntityRegistry.registerModEntity(pb3, EntityPeachickBlue.class, "animania.PeachickBlue", Animania.PeachickBlueID, this, 64, 3, true); 

			EntityRegistry.addSpawn(EntityPeacockBlue.class, Animania.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, Biomes.JUNGLE, Biomes.SWAMPLAND); 
			EntityRegistry.addSpawn(EntityPeafowlBlue.class, Animania.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, Biomes.JUNGLE, Biomes.SWAMPLAND);   
			EntityRegistry.addSpawn(EntityPeachickBlue.class, Animania.spawnProbabilityPeacocks/2, 1, 1, EnumCreatureType.CREATURE, Biomes.JUNGLE, Biomes.SWAMPLAND);   

			ResourceLocation pw1 = new ResourceLocation("animania:textures/entity/pigs/peacock_white.png");
			EntityRegistry.registerModEntity(pw1, EntityPeacockWhite.class, "animania.PeacockWhite", Animania.PeacockWhiteID, this, 64, 3, true); 

			ResourceLocation pw2 = new ResourceLocation("animania:textures/entity/pigs/peacock_white.png");
			EntityRegistry.registerModEntity(pw2, EntityPeafowlWhite.class, "animania.PeafowlWhite", Animania.PeafowlWhiteID, this, 64, 3, true); 

			ResourceLocation pw3 = new ResourceLocation("animania:textures/entity/pigs/peacock_white.png");
			EntityRegistry.registerModEntity(pw3, EntityPeachickWhite.class, "animania.PeachickWhite", Animania.PeachickWhiteID, this, 64, 3, true); 

			EntityRegistry.addSpawn(EntityPeacockWhite.class, Animania.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, Biomes.JUNGLE, Biomes.SWAMPLAND); 
			EntityRegistry.addSpawn(EntityPeafowlWhite.class, Animania.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, Biomes.JUNGLE, Biomes.SWAMPLAND); 
			EntityRegistry.addSpawn(EntityPeachickWhite.class, Animania.spawnProbabilityPeacocks/2, 1, 1, EnumCreatureType.CREATURE, Biomes.JUNGLE, Biomes.SWAMPLAND); 
		}



		/*
		//HORSES
		if (Animania.spawnAnimaniaHorses) {
			EntityRegistry.registerModEntity(EntityStallionDraftHorse.class, "animania.StallionDraftHorse", Animania.StallionDraftHorseID, this, 64, 3, true); 
		}
		 */


		GameRegistry.registerTileEntity(TileEntityTrough.class, "TileEntityTrough");
		GameRegistry.registerTileEntity(TileEntityNest.class, "TileEntityNest");

		proxy.registerRenderers();

		//Recipes

		//HAMSTERS


		/*
		GameRegistry.addRecipe(new ItemStack(hamsterBall,1,0), new Object[]{
			"xxx","x x","xxx",'x', Blocks.GLASS,
		});
		GameRegistry.addRecipe(new ItemStack(hamsterBall,1,1), new Object[]{
			"xxx","xdx","xxx",'x', Blocks.GLASS, 'd', new ItemStack(Items.DYE, 1, 14)
		});
		GameRegistry.addRecipe(new ItemStack(hamsterBall,1,2), new Object[]{
			"xxx","xdx","xxx",'x', Blocks.GLASS, 'd', new ItemStack(Items.DYE, 1, 13)
		});
		GameRegistry.addRecipe(new ItemStack(hamsterBall,1,3), new Object[]{
			"xxx","xdx","xxx",'x', Blocks.GLASS, 'd', new ItemStack(Items.DYE, 1, 12)
		});
		GameRegistry.addRecipe(new ItemStack(hamsterBall,1,4), new Object[]{
			"xxx","xdx","xxx",'x', Blocks.GLASS, 'd', new ItemStack(Items.DYE, 1, 11)
		});
		GameRegistry.addRecipe(new ItemStack(hamsterBall,1,5), new Object[]{
			"xxx","xdx","xxx",'x', Blocks.GLASS, 'd', new ItemStack(Items.DYE, 1, 10)
		});
		GameRegistry.addRecipe(new ItemStack(hamsterBall,1,6), new Object[]{
			"xxx","xdx","xxx",'x', Blocks.GLASS, 'd', new ItemStack(Items.DYE, 1, 9)
		});
		GameRegistry.addRecipe(new ItemStack(hamsterBall,1,7), new Object[]{
			"xxx","xdx","xxx",'x', Blocks.GLASS, 'd', new ItemStack(Items.DYE, 1, 8)
		});
		GameRegistry.addRecipe(new ItemStack(hamsterBall,1,8), new Object[]{
			"xxx","xdx","xxx",'x', Blocks.GLASS, 'd', new ItemStack(Items.DYE, 1, 7)
		});
		GameRegistry.addRecipe(new ItemStack(hamsterBall,1,9), new Object[]{
			"xxx","xdx","xxx",'x', Blocks.GLASS, 'd', new ItemStack(Items.DYE, 1, 6)
		});
		GameRegistry.addRecipe(new ItemStack(hamsterBall,1,10), new Object[]{
			"xxx","xdx","xxx",'x', Blocks.GLASS, 'd', new ItemStack(Items.DYE, 1, 5)
		});
		GameRegistry.addRecipe(new ItemStack(hamsterBall,1,11), new Object[]{
			"xxx","xdx","xxx",'x', Blocks.GLASS, 'd', new ItemStack(Items.DYE, 1, 4)
		});
		GameRegistry.addRecipe(new ItemStack(hamsterBall,1,12), new Object[]{
			"xxx","xdx","xxx",'x', Blocks.GLASS, 'd', new ItemStack(Items.DYE, 1, 3)
		});
		GameRegistry.addRecipe(new ItemStack(hamsterBall,1,13), new Object[]{
			"xxx","xdx","xxx",'x', Blocks.GLASS, 'd', new ItemStack(Items.DYE, 1, 2)
		});
		GameRegistry.addRecipe(new ItemStack(hamsterBall,1,14), new Object[]{
			"xxx","xdx","xxx",'x', Blocks.GLASS, 'd', new ItemStack(Items.DYE, 1, 1)
		});
		GameRegistry.addRecipe(new ItemStack(hamsterBall,1,15), new Object[]{
			"xxx","xdx","xxx",'x', Blocks.GLASS, 'd', new ItemStack(Items.DYE, 1, 0)
		});
		/*
		GameRegistry.addRecipe(new ItemStack(WaterBottle,1,0), new Object[]{
			"x","w","i",'x', Blocks.glass, 'w', new ItemStack(Items.potionitem, 1, 0), 'i', Items.iron_ingot
		});
		 */

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(hamsterFood, 3), new Object[] {
			Items.WHEAT_SEEDS, Items.PUMPKIN_SEEDS, Items.MELON_SEEDS
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(hamsterFood, 3), new Object[] {
			"listAllseed", Items.PUMPKIN_SEEDS, Items.MELON_SEEDS
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(hamsterFood, 3), new Object[] {
			Items.WHEAT_SEEDS, "listAllseed", Items.MELON_SEEDS
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(hamsterFood, 3), new Object[] {
			Items.WHEAT_SEEDS, Items.PUMPKIN_SEEDS, "listAllseed"
		}));

		//CARVING KNIFE
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Animania.carvingKnife, 1), new Object[] {
			"   ","ii ","s  ",'i', "ingotIron", 's', Items.STICK
		}));

		//BEEF Recipes
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Animania.rawAngusSteak, 5), new Object[] {
			Animania.rawAngusBeef, new ItemStack(Animania.carvingKnife, 1, OreDictionary.WILDCARD_VALUE)
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Animania.rawHerefordSteak, 4), new Object[] {
			Animania.rawHerefordBeef, new ItemStack(Animania.carvingKnife, 1, OreDictionary.WILDCARD_VALUE)
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Animania.rawLonghornSteak, 4), new Object[] {
			Animania.rawLonghornBeef, new ItemStack(Animania.carvingKnife, 1, OreDictionary.WILDCARD_VALUE)
		}));

		//PORK Recipes
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Animania.rawLargeBlackBacon, 5), new Object[] {
			Animania.rawLargeBlackPork, new ItemStack(Animania.carvingKnife, 1, OreDictionary.WILDCARD_VALUE)
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Animania.rawDurocBacon, 4), new Object[] {
			Animania.rawDurocPork, new ItemStack(Animania.carvingKnife, 1, OreDictionary.WILDCARD_VALUE)
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Animania.rawOldSpotBacon, 4), new Object[] {
			Animania.rawOldSpotPork, new ItemStack(Animania.carvingKnife, 1, OreDictionary.WILDCARD_VALUE)
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Animania.rawHampshireBacon, 4), new Object[] {
			Animania.rawHampshirePork, new ItemStack(Animania.carvingKnife, 1, OreDictionary.WILDCARD_VALUE)
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.LEAD, 1), new Object[] {
			"leather", "leather", "string", "string"
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.NAME_TAG, 1), new Object[] {
			"string", "nuggetGold"
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Animania.truffleSoup, 1), new Object[] {
			Animania.truffle, Animania.truffle, Items.BOWL
		}));


		/*
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Animania.cheeseWheelHolstein, 1), new Object[] {
			Animania.milkBucketHolstein, new ItemStack(Animania.cheeseMold, 1, OreDictionary.WILDCARD_VALUE)
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Animania.cheeseWheelFriesian, 1), new Object[] {
			Animania.milkBucketFriesian, new ItemStack(Animania.cheeseMold, 1, OreDictionary.WILDCARD_VALUE)
		}));
		 */


		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Animania.cheeseOmelette, 1), new Object[] {
			Animania.plainOmelette, "foodCheese"
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Animania.baconOmelette, 1), new Object[] {
			Animania.plainOmelette, Animania.cookedDurocBacon
		}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Animania.baconOmelette, 1), new Object[] {
			Animania.plainOmelette, Animania.cookedHampshireBacon
		}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Animania.baconOmelette, 1), new Object[] {
			Animania.plainOmelette, Animania.cookedLargeBlackBacon
		}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Animania.baconOmelette, 1), new Object[] {
			Animania.plainOmelette, Animania.cookedOldSpotBacon
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Animania.truffleOmelette, 1), new Object[] {
			Animania.plainOmelette, Animania.truffle
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Animania.ultimateOmelette, 1), new Object[] {
			Animania.plainOmelette, Animania.cookedDurocBacon, Animania.truffle, "foodCheese"
		}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Animania.ultimateOmelette, 1), new Object[] {
			Animania.plainOmelette, Animania.cookedHampshireBacon, Animania.truffle, "foodCheese"
		}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Animania.ultimateOmelette, 1), new Object[] {
			Animania.plainOmelette, Animania.cookedLargeBlackBacon, Animania.truffle, "foodCheese"
		}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Animania.ultimateOmelette, 1), new Object[] {
			Animania.plainOmelette, Animania.cookedOldSpotBacon, Animania.truffle, "foodCheese"
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Animania.chocolateTruffle, 1), new Object[] {
			Animania.truffle, new ItemStack(Items.DYE, 1, 3), "listAllsugar"
		}));

		//BLOCKS
		//Nest
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Animania.blockNest, 1), new Object[] {
			"wool", "stickWood", "treeLeaves" 
		}));

		//Trough
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Animania.blockTrough, 1), new Object[] {
			"p p","pip","s s",'p', "plankWood", 'i', "ingotIron", 's', "stickWood"
		}));

		//Cheese Mold
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Animania.cheeseMold, 1), new Object[] {
			"   ","pip"," p ",'p', "plankWood", 'i', "ingotIron"
		}));

		//Slop Normal
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Animania.bucketSlop, 1), new Object[] {
			"listBucketmilk", "cropCarrot", "cropPotato" 
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Animania.bucketSlop, 1), new Object[] {
			"listBucketmilk", "cropCarrot", "cropBeet"
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Animania.bucketSlop, 1), new Object[] {
			"listBucketmilk", "cropCarrot", "bread"
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Animania.bucketSlop, 1), new Object[] {
			"listBucketmilk", "cropBeet", "cropPotato"
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Animania.bucketSlop, 1), new Object[] {
			"listBucketmilk", "cropBeet", "bread"
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Animania.bucketSlop, 1), new Object[] {
			"listBucketmilk", "cropPotato", "bread"
		}));

		//Slop OreDict
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Animania.bucketSlop, 1), new Object[] {
			"listAllmilk", "listAllmilk", "listAllmilk", "listAllmilk", "cropCarrot", "cropPotato", Items.BUCKET 
		}));


		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Animania.bucketSlop, 1), new Object[] {
			"listAllmilk", "listAllmilk", "listAllmilk", "listAllmilk", "cropCarrot", "bread", Items.BUCKET
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Animania.bucketSlop, 1), new Object[] {
			"listAllmilk", "listAllmilk", "listAllmilk", "listAllmilk", "cropBeet", "cropPotato", Items.BUCKET
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Animania.bucketSlop, 1), new Object[] {
			"listAllmilk", "listAllmilk", "listAllmilk", "listAllmilk", "cropBeet", "bread", Items.BUCKET
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Animania.bucketSlop, 1), new Object[] {
			"listAllmilk", "listAllmilk", "listAllmilk", "listAllmilk", "cropPotato", "bread", Items.BUCKET
		}));

		//Cheese Mold Recipes
		GameRegistry.addRecipe(new CheeseRecipe1());
		RecipeSorter.register("animania:cheesewheelholstein", CheeseRecipe1.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");

		GameRegistry.addRecipe(new CheeseRecipe2());
		RecipeSorter.register("animania:cheesewheelfriesian", CheeseRecipe2.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");

		/*
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Animania.cheeseWheelHolstein, 1), new Object[] {
			Animania.milkBucketHolstein, new ItemStack(Animania.cheeseMold, 1, OreDictionary.WILDCARD_VALUE)
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Animania.cheeseWheelFriesian, 1), new Object[] {
			Animania.milkBucketFriesian, new ItemStack(Animania.cheeseMold, 1, OreDictionary.WILDCARD_VALUE)
		}));
		 */


		//Carving Knife Recipes
		GameRegistry.addRecipe(new CheeseRecipe3());
		RecipeSorter.register("animania:cheeseWheelHolstein", CheeseRecipe3.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");

		GameRegistry.addRecipe(new CheeseRecipe4());
		RecipeSorter.register("animania:cheeseWheelFriesian", CheeseRecipe4.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Animania.cheeseWedgeHolstein, 4), new Object[] {
			Animania.cheeseWheelHolstein, new ItemStack(Animania.carvingKnife, 1, OreDictionary.WILDCARD_VALUE)
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Animania.cheeseWedgeFriesian, 4), new Object[] {
			Animania.cheeseWheelFriesian, new ItemStack(Animania.carvingKnife, 1, OreDictionary.WILDCARD_VALUE)
		}));


		//Slop Bucket Special Recipe
		GameRegistry.addRecipe(new SlopBucketRecipe1());
		GameRegistry.addRecipe(new SlopBucketRecipe2());
		GameRegistry.addRecipe(new SlopBucketRecipe3());
		GameRegistry.addRecipe(new SlopBucketRecipe4());
		GameRegistry.addRecipe(new SlopBucketRecipe5());
		GameRegistry.addRecipe(new SlopBucketRecipe6());
		RecipeSorter.register("animania:bucketslop1", SlopBucketRecipe1.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");
		RecipeSorter.register("animania:bucketslop2", SlopBucketRecipe2.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");
		RecipeSorter.register("animania:bucketslop3", SlopBucketRecipe3.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");
		RecipeSorter.register("animania:bucketslop4", SlopBucketRecipe4.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");
		RecipeSorter.register("animania:bucketslop5", SlopBucketRecipe5.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");
		RecipeSorter.register("animania:bucketslop6", SlopBucketRecipe6.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Animania.bucketSlop, 1), new Object[] {
			"listAllmilk", "listAllmilk", "listAllmilk", "listAllmilk", "cropCarrot", "cropBeet", Items.BUCKET
		}));


		//Blocks
		GameRegistry.addShapelessRecipe(new ItemStack(Animania.blockMud, 2), new Object[]{
			Items.WATER_BUCKET, Blocks.DIRT});


		//Vanilla transfer meats recipes
		if (Animania.enableVanillaMeatRecipes) {
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.BEEF), new Object[] {
				Animania.rawAngusBeef
			}));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.BEEF), new Object[] {
				Animania.rawLonghornBeef
			}));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.BEEF), new Object[] {
				Animania.rawHerefordBeef
			}));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.BEEF), new Object[] {
				Animania.rawAngusSteak
			}));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.BEEF), new Object[] {
				Animania.rawLonghornSteak
			}));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.BEEF), new Object[] {
				Animania.rawHerefordSteak
			}));


			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.PORKCHOP), new Object[] {
				Animania.rawLargeBlackPork
			}));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.PORKCHOP), new Object[] {
				Animania.rawLargeBlackBacon
			}));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.PORKCHOP), new Object[] {
				Animania.rawDurocPork
			}));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.PORKCHOP), new Object[] {
				Animania.rawDurocBacon
			}));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.PORKCHOP), new Object[] {
				Animania.rawHampshirePork
			}));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.PORKCHOP), new Object[] {
				Animania.rawHampshireBacon
			}));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.PORKCHOP), new Object[] {
				Animania.rawOldSpotPork
			}));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.PORKCHOP), new Object[] {
				Animania.rawOldSpotBacon
			}));

			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.CHICKEN), new Object[] {
				Animania.rawOrpingtonChicken
			}));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.CHICKEN), new Object[] {
				Animania.rawPlymouthRockChicken
			}));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.CHICKEN), new Object[] {
				Animania.rawWyandotteChicken
			}));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.CHICKEN), new Object[] {
				Animania.rawRhodeIslandRedChicken
			}));

		}

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.MILK_BUCKET), new Object[] {
			Animania.milkBucketFriesian
		}));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.MILK_BUCKET), new Object[] {
			Animania.milkBucketHolstein
		}));


		//Smelting Recipes
		GameRegistry.addSmelting(Animania.rawAngusBeef, new ItemStack(Animania.cookedAngusRoast, 1), .3F);
		GameRegistry.addSmelting(Animania.rawLonghornBeef, new ItemStack(Animania.cookedLonghornRoast, 1), .3F);
		GameRegistry.addSmelting(Animania.rawHerefordBeef, new ItemStack(Animania.cookedHerefordRoast, 1), .3F);
		GameRegistry.addSmelting(Animania.rawAngusSteak, new ItemStack(Animania.cookedAngusSteak, 1), .2F);
		GameRegistry.addSmelting(Animania.rawLonghornSteak, new ItemStack(Animania.cookedLonghornSteak, 1), .2F);
		GameRegistry.addSmelting(Animania.rawHerefordSteak, new ItemStack(Animania.cookedHerefordSteak, 1), .2F);

		GameRegistry.addSmelting(Animania.rawLargeBlackPork, new ItemStack(Animania.cookedLargeBlackRoast, 1), .3F);
		GameRegistry.addSmelting(Animania.rawDurocPork, new ItemStack(Animania.cookedDurocRoast, 1), .3F);
		GameRegistry.addSmelting(Animania.rawOldSpotPork, new ItemStack(Animania.cookedOldSpotRoast, 1), .3F);
		GameRegistry.addSmelting(Animania.rawHampshirePork, new ItemStack(Animania.cookedHampshireRoast, 1), .3F);
		GameRegistry.addSmelting(Animania.rawLargeBlackBacon, new ItemStack(Animania.cookedLargeBlackBacon, 1), .2F);
		GameRegistry.addSmelting(Animania.rawDurocBacon, new ItemStack(Animania.cookedDurocBacon, 1), .2F);
		GameRegistry.addSmelting(Animania.rawHampshireBacon, new ItemStack(Animania.cookedHampshireBacon, 1), .2F);
		GameRegistry.addSmelting(Animania.rawOldSpotBacon, new ItemStack(Animania.cookedOldSpotBacon, 1), .2F);

		GameRegistry.addSmelting(Animania.rawOrpingtonChicken, new ItemStack(Animania.cookedOrpingtonChicken, 1), .3F);
		GameRegistry.addSmelting(Animania.rawPlymouthRockChicken, new ItemStack(Animania.cookedPlymouthRockChicken, 1), .3F);
		GameRegistry.addSmelting(Animania.rawWyandotteChicken, new ItemStack(Animania.cookedWyandotteChicken, 1), .3F);
		GameRegistry.addSmelting(Animania.rawRhodeIslandRedChicken, new ItemStack(Animania.cookedRhodeIslandRedChicken, 1), .3F);

		GameRegistry.addSmelting(Animania.rawPrimeBeef, new ItemStack(Animania.cookedPrimeBeef, 1), .3F);
		GameRegistry.addSmelting(Animania.rawPrimeSteak, new ItemStack(Animania.cookedPrimeSteak, 1), .3F);
		GameRegistry.addSmelting(Animania.rawPrimePork, new ItemStack(Animania.cookedPrimePork, 1), .3F);
		GameRegistry.addSmelting(Animania.rawPrimeBacon, new ItemStack(Animania.cookedPrimeBacon, 1), .3F);
		GameRegistry.addSmelting(Animania.rawPrimeChicken, new ItemStack(Animania.cookedPrimeChicken, 1), .3F);

		GameRegistry.addSmelting(Items.EGG, new ItemStack(Animania.plainOmelette, 1), .3F);
		GameRegistry.addSmelting(Animania.brownEgg, new ItemStack(Animania.plainOmelette, 1), .3F);

		//OreDictionary
		oreRegistration();

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
	}

}