package com.animania;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import com.animania.blocks.BlockInvisiblock;
import com.animania.blocks.BlockMud;
import com.animania.blocks.BlockNest;
import com.animania.blocks.BlockSeeds;
import com.animania.blocks.BlockTrough;
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
import com.animania.events.ConfigChangeEventHandler;
import com.animania.events.EggThrowHandler;
import com.animania.events.EventMudDamageCanceller;
import com.animania.events.EventReplaceSpawnAnimals;
import com.animania.events.ItemSeedHandler;
import com.animania.events.LoginEventHandler;
import com.animania.events.RemoveVanillaSpawns;
import com.animania.events.UpdateHandler;
import com.animania.items.ItemBrownEgg;
import com.animania.items.ItemBucketMilk;
import com.animania.items.ItemBucketSlop;
import com.animania.items.ItemCarvingKnife;
import com.animania.items.ItemCheeseMold;
import com.animania.items.ItemCheeseWedge;
import com.animania.items.ItemCheeseWheel;
import com.animania.items.ItemChocolateTruffle;
import com.animania.items.ItemCookedAngusRoast;
import com.animania.items.ItemCookedAngusSteak;
import com.animania.items.ItemCookedDurocBacon;
import com.animania.items.ItemCookedDurocRoast;
import com.animania.items.ItemCookedHampshireBacon;
import com.animania.items.ItemCookedHampshireRoast;
import com.animania.items.ItemCookedHerefordRoast;
import com.animania.items.ItemCookedHerefordSteak;
import com.animania.items.ItemCookedLargeBlackBacon;
import com.animania.items.ItemCookedLargeBlackRoast;
import com.animania.items.ItemCookedLonghornRoast;
import com.animania.items.ItemCookedLonghornSteak;
import com.animania.items.ItemCookedOldSpotBacon;
import com.animania.items.ItemCookedOldSpotRoast;
import com.animania.items.ItemCookedOrpingtonChicken;
import com.animania.items.ItemCookedPlymouthRockChicken;
import com.animania.items.ItemCookedPrimeBacon;
import com.animania.items.ItemCookedPrimeBeef;
import com.animania.items.ItemCookedPrimeChicken;
import com.animania.items.ItemCookedPrimePork;
import com.animania.items.ItemCookedPrimeSteak;
import com.animania.items.ItemCookedRhodeIslandRedChicken;
import com.animania.items.ItemCookedWyandotteChicken;
import com.animania.items.ItemEntityEgg;
import com.animania.items.ItemHamsterFood;
import com.animania.items.ItemOmelette;
import com.animania.items.ItemPeacockFeather;
import com.animania.items.ItemRawAngusBeef;
import com.animania.items.ItemRawAngusSteak;
import com.animania.items.ItemRawDurocBacon;
import com.animania.items.ItemRawDurocPork;
import com.animania.items.ItemRawHampshireBacon;
import com.animania.items.ItemRawHampshirePork;
import com.animania.items.ItemRawHerefordBeef;
import com.animania.items.ItemRawHerefordSteak;
import com.animania.items.ItemRawLargeBlackBacon;
import com.animania.items.ItemRawLargeBlackPork;
import com.animania.items.ItemRawLonghornBeef;
import com.animania.items.ItemRawLonghornSteak;
import com.animania.items.ItemRawOldSpotBacon;
import com.animania.items.ItemRawOldSpotPork;
import com.animania.items.ItemRawOrpingtonChicken;
import com.animania.items.ItemRawPlymouthRockChicken;
import com.animania.items.ItemRawPrimeBacon;
import com.animania.items.ItemRawPrimeBeef;
import com.animania.items.ItemRawPrimeChicken;
import com.animania.items.ItemRawPrimePork;
import com.animania.items.ItemRawPrimeSteak;
import com.animania.items.ItemRawRhodeIslandRedChicken;
import com.animania.items.ItemRawWyandotteChicken;
import com.animania.items.ItemTruffle;
import com.animania.items.ItemTruffleSoup;
import com.animania.recipes.CheeseRecipe1;
import com.animania.recipes.CheeseRecipe2;
import com.animania.recipes.CheeseRecipe3;
import com.animania.recipes.CheeseRecipe4;
import com.animania.recipes.SlopBucketRecipe1;
import com.animania.recipes.SlopBucketRecipe2;
import com.animania.recipes.SlopBucketRecipe3;
import com.animania.recipes.SlopBucketRecipe4;
import com.animania.recipes.SlopBucketRecipe5;
import com.animania.recipes.SlopBucketRecipe6;
import com.animania.tileentities.TileEntityNest;
import com.animania.tileentities.TileEntityTrough;


//Forge loads
@Mod(modid = "animania", name = "Animania", version="1.0.4.7",  guiFactory = "com.animania.gui.GuiFactoryAnimania")

public class Animania {

	public static final String modid = "animania";
	public static final String version = "1.0.4.7";
	public static final String name = "Animania";
	public static Configuration config;

	@SidedProxy(clientSide="com.animania.ClientProxy", serverSide="com.animania.CommonProxy")
	public static CommonProxy proxy;

	//Configs

	//COWS
	public static int CowHolsteinID;
	public static int BullHolsteinID;
	public static int CalfHolsteinID;
	public static int CowFriesianID;
	public static int BullFriesianID;
	public static int CalfFriesianID;
	public static int CowAngusID;
	public static int BullAngusID;
	public static int CalfAngusID;
	public static int CowLonghornID;
	public static int BullLonghornID;
	public static int CalfLonghornID;
	public static int CowHerefordID;
	public static int BullHerefordID;
	public static int CalfHerefordID;

	//RODENTS
	public static int HamsterMaleID;
	public static int FerretMaleGreyID;
	public static int FerretMaleWhiteID;
	public static int HedgehogMaleID;
	public static int HedgehogMaleAlbinoID;

	//CHICKENS
	public static int HenPlymouthRockID;
	public static int RoosterPlymouthRockID;
	public static int ChickPlymouthRockID;
	public static int HenLeghornID;
	public static int RoosterLeghornID;
	public static int ChickLeghornID;
	public static int HenOrpingtonID;
	public static int RoosterOrpingtonID;
	public static int ChickOrpingtonID;
	public static int HenRhodeIslandRedID;
	public static int RoosterRhodeIslandRedID;
	public static int ChickRhodeIslandRedID;
	public static int HenWyandotteID;
	public static int RoosterWyandotteID;
	public static int ChickWyandotteID;

	//PEAFOWL
	public static int PeacockBlueID;
	public static int PeafowlBlueID;
	public static int PeachickBlueID;
	public static int PeacockWhiteID;
	public static int PeafowlWhiteID;
	public static int PeachickWhiteID;

	//PIGS
	public static int SowYorkshireID;
	public static int HogYorkshireID;
	public static int PigletYorkshireID;
	public static int SowOldSpotID;
	public static int HogOldSpotID;
	public static int PigletOldSpotID;
	public static int SowLargeBlackID;
	public static int HogLargeBlackID;
	public static int PigletLargeBlackID;
	public static int SowDurocID;
	public static int HogDurocID;
	public static int PigletDurocID;
	public static int SowLargeWhiteID;
	public static int HogLargeWhiteID;
	public static int PigletLargeWhiteID;
	public static int SowHampshireID;
	public static int HogHampshireID;
	public static int PigletHampshireID;

	//HORSES
	//public static int StallionDraftHorseID;
	//public static int MareDraftHorseID;
	//public static int FoalDraftHorseID;

	//GAME RULES
	public static boolean foodsGiveBonusEffects;
	public static boolean showModUpdateNotification;
	public static boolean showParts;
	public static boolean replaceVanillaCows;
	public static boolean replaceVanillaPigs;
	public static boolean replaceVanillaChickens;
	public static boolean enableVanillaMeatRecipes;
	public static boolean allowEggThrowing;
	public static boolean shiftSeedPlacement;
	public static boolean animalsStarve;

	public static boolean customMobDrops;
	public static String pigDrop;
	public static String cowDrop;
	public static String chickenDrop;
	public static String horseDrop;
	public static String peacockBlueDrop;
	public static String peacockWhiteDrop;
	public static String hamsterDrop;
	public static String ferretDrop;
	public static String hedgehogDrop;

	public static boolean spawnAnimaniaChickens;
	public static boolean spawnAnimaniaCows;
	public static boolean spawnAnimaniaPigs;
	public static boolean spawnAnimaniaRodents;
	public static boolean spawnAnimaniaPeacocks;
	public static boolean spawnAnimaniaHorses;

	public static int spawnLimitCows;
	public static int spawnLimitPigs;
	public static int spawnLimitChickens;
	public static int spawnLimitHedgehogs;
	public static int spawnLimitHamsters;
	public static int spawnLimitFerrets;
	public static int spawnLimitPeacocks;

	public static int spawnProbabilityCows;
	public static int spawnProbabilitySows;
	public static int spawnProbabilityHens;
	public static int spawnProbabilityHedgehogs;
	public static int spawnProbabilityFerrets;
	public static int spawnProbabilityHamsters;
	public static int spawnProbabilityPeacocks;

	public static int numberCowFamilies;
	public static int numberPigFamilies;
	public static int numberChickenFamilies;

	public static boolean showUnhappyParticles;
	public static int childGrowthTick;
	public static int feedTimer;
	public static int waterTimer;
	public static int playTimer;
	public static int laidTimer;
	public static int gestationTimer;
	public static int starvationTimer;
	public static int eggHatchChance;

	//Items
	public static Item hamsterBall;
	public static Item hamsterFood;
	public static Item truffle;
	public static Item brownEgg;
	public static Item bucketSlop;
	public static Item carvingKnife;
	public static Item cheeseMold;

	public static Item peacockFeatherBlue;
	public static Item peacockFeatherWhite;

	//Beef
	public static Item rawHerefordBeef;
	public static Item rawLonghornBeef;
	public static Item rawAngusBeef;
	public static Item rawHerefordSteak;
	public static Item rawLonghornSteak;
	public static Item rawAngusSteak;
	public static Item cookedHerefordRoast;
	public static Item cookedLonghornRoast;
	public static Item cookedAngusRoast;
	public static Item cookedHerefordSteak;
	public static Item cookedLonghornSteak;
	public static Item cookedAngusSteak;

	public static Item rawPrimeSteak;
	public static Item rawPrimeBeef;
	public static Item cookedPrimeSteak;
	public static Item cookedPrimeBeef;

	//Pork
	public static Item rawLargeBlackPork;
	public static Item rawDurocPork;
	public static Item rawOldSpotPork;
	public static Item rawHampshirePork;
	public static Item rawLargeBlackBacon;
	public static Item rawDurocBacon;
	public static Item rawOldSpotBacon;
	public static Item rawHampshireBacon;
	public static Item cookedLargeBlackRoast;
	public static Item cookedDurocRoast;
	public static Item cookedOldSpotRoast;
	public static Item cookedHampshireRoast;
	public static Item cookedLargeBlackBacon;
	public static Item cookedDurocBacon;
	public static Item cookedOldSpotBacon;
	public static Item cookedHampshireBacon;

	public static Item rawPrimePork;
	public static Item rawPrimeBacon;
	public static Item cookedPrimePork;
	public static Item cookedPrimeBacon;

	//Chicken
	public static Item rawOrpingtonChicken;
	public static Item rawPlymouthRockChicken;
	public static Item rawWyandotteChicken;
	public static Item rawRhodeIslandRedChicken;
	public static Item cookedOrpingtonChicken;
	public static Item cookedPlymouthRockChicken;
	public static Item cookedWyandotteChicken;
	public static Item cookedRhodeIslandRedChicken;

	public static Item rawPrimeChicken;
	public static Item cookedPrimeChicken;

	//Other Foods
	public static Item plainOmelette;
	public static Item cheeseOmelette;
	public static Item baconOmelette;
	public static Item truffleOmelette;
	public static Item ultimateOmelette;
	public static Item cheeseWheelFriesian;
	public static Item cheeseWedgeFriesian;
	public static Item cheeseWheelHolstein;
	public static Item cheeseWedgeHolstein;
	public static Item truffleSoup;
	public static Item chocolateTruffle;
	public static Item milkBucketHolstein;
	public static Item milkBucketFriesian;

	//Eggs - Cows

	public static Item entityeggcalfholstein;
	public static Item entityeggcowholstein;
	public static Item entityeggbullholstein;

	public static Item entityeggcalffriesian;
	public static Item entityeggcowfriesian;
	public static Item entityeggbullfriesian;

	public static Item entityeggcalfangus;
	public static Item entityeggcowangus;
	public static Item entityeggbullangus;

	public static Item entityeggcalflonghorn;
	public static Item entityeggcowlonghorn;
	public static Item entityeggbulllonghorn;

	public static Item entityeggcalfhereford;
	public static Item entityeggcowhereford;
	public static Item entityeggbullhereford;

	public static Item entityeggrandomcow;

	//Eggs - Chickens
	public static Item entityeggchickplymouth;
	public static Item entityegghenplymouth;
	public static Item entityeggroosterplymouth;

	public static Item entityeggchickleghorn;
	public static Item entityegghenleghorn;
	public static Item entityeggroosterleghorn;

	public static Item entityeggchickorpington;
	public static Item entityegghenorpington;
	public static Item entityeggroosterorpington;

	public static Item entityeggchickred;
	public static Item entityegghenred;
	public static Item entityeggroosterred;

	public static Item entityeggchickwyandotte;
	public static Item entityegghenwyandotte;
	public static Item entityeggroosterwyandotte;

	public static Item entityeggrandomchicken;


	//Eggs - Peacocks
	public static Item entityeggpeachickblue;
	public static Item entityeggpeafowlblue;
	public static Item entityeggpeacockblue;

	public static Item entityeggpeachickwhite;
	public static Item entityeggpeafowlwhite;
	public static Item entityeggpeacockwhite;

	//Eggs - Pigs
	public static Item entityeggpigletyorkshire;
	public static Item entityeggsowyorkshire;
	public static Item entityegghogyorkshire;

	public static Item entityeggpigletoldspot;
	public static Item entityeggsowoldspot;
	public static Item entityegghogoldspot;

	public static Item entityeggpigletlargeblack;
	public static Item entityeggsowlargeblack;
	public static Item entityegghoglargeblack;

	public static Item entityeggpigletlargewhite;
	public static Item entityeggsowlargewhite;
	public static Item entityegghoglargewhite;

	public static Item entityeggpigletduroc;
	public static Item entityeggsowduroc;
	public static Item entityegghogduroc;

	public static Item entityeggpiglethampshire;
	public static Item entityeggsowhampshire;
	public static Item entityegghoghampshire;

	public static Item entityeggrandompig;

	//Eggs - Rodents
	public static Item entityegghamster;
	public static Item entityeggferretgrey;
	public static Item entityeggferretwhite;
	public static Item entityegghedgehog;
	public static Item entityegghedgehogalbino;

	//Eggs - Horses
	//public static Item entityeggdrafthorsestallion;
	//public static Item entityeggdrafthorsemare;
	//public static Item entityeggdrafthorsefoal;


	//Blocks
	public static Block blockMud;
	public static Block blockInvisiblock;
	public static Block blockSeeds;

	//Tile Entities
	public static Block blockTrough;
	public static Block blockNest;

	//Item Blocks
	public static ItemBlock itemBlockMud;
	public static ItemBlock itemBlockSeeds;
	public static ItemBlock itemBlockTrough;
	public static ItemBlock itemBlockNest;
	public static ItemBlock itemInvisiblock;

	//DamageSource
	public static DamageSource bullDamage;

	//Tabs
	public static CreativeTabs TabAnimaniaEggs = new TabAnimaniaEntities(CreativeTabs.getNextID(), "Animania"); 
	public static CreativeTabs TabAnimaniaResources = new TabAnimaniaResources(CreativeTabs.getNextID(), "Animania"); 

	//Instance
	@Instance("Animania")
	public static Animania instance;

	//Network
	@Mod.EventHandler
	public void preload(FMLPreInitializationEvent event)
	{

		config = new Configuration(event.getSuggestedConfigurationFile());
		syncConfig();

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

	}

	//Defining Variables
	@EventHandler
	public void load(FMLInitializationEvent event){

		proxy.registerRenderers();
		proxy.registerTextures();

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

	//Event Handler
	@EventHandler
	public void postInit(FMLPostInitializationEvent e){

	}

	public static void syncConfig() {

		//CONFIG

		//Cows
		CowHolsteinID = config.get("Entities", "Holstein Cow Entity ID", 500).getInt();
		BullHolsteinID = config.get("Entities", "Holstein Bull Entity ID", 501).getInt();
		CalfHolsteinID = config.get("Entities", "Holstein Calf Entity ID", 502).getInt();

		CowFriesianID = config.get("Entities", "Friesian Cow Entity ID", 503).getInt();
		BullFriesianID = config.get("Entities", "Friesian Bull Entity ID", 504).getInt();
		CalfFriesianID = config.get("Entities", "Friesian Calf Entity ID", 505).getInt();

		CowAngusID = config.get("Entities", "Angus Cow Entity ID", 506).getInt();
		BullAngusID = config.get("Entities", "Angus Bull Entity ID", 507).getInt();
		CalfAngusID = config.get("Entities", "Angus Calf Entity ID", 508).getInt();

		CowLonghornID = config.get("Entities", "Longhorn Cow Entity ID", 509).getInt();
		BullLonghornID = config.get("Entities", "Longhorn Bull Entity ID", 510).getInt();
		CalfLonghornID = config.get("Entities", "Longhorn Calf Entity ID", 511).getInt();

		CowHerefordID = config.get("Entities", "Hereford Cow Entity ID", 512).getInt();
		BullHerefordID = config.get("Entities", "Hereford Bull Entity ID", 513).getInt();
		CalfHerefordID = config.get("Entities", "Hereford Calf Entity ID", 514).getInt();

		//Chickens
		HenPlymouthRockID = config.get("Entities", "Plymouth Rock Hen Entity ID", 530).getInt();
		RoosterPlymouthRockID = config.get("Entities", "Plymouth Rock Rooster Entity ID", 531).getInt();
		ChickPlymouthRockID = config.get("Entities", "Plymouth Rock Chick Entity ID", 532).getInt();

		HenLeghornID = config.get("Entities", "Leghorn Hen Entity ID", 533).getInt();
		RoosterLeghornID = config.get("Entities", "Leghorn Entity ID", 534).getInt();
		ChickLeghornID = config.get("Entities", "Leghorn Chick Entity ID", 535).getInt();

		HenOrpingtonID = config.get("Entities", "Orpington Hen Entity ID", 536).getInt();
		RoosterOrpingtonID = config.get("Entities", "Orpington Entity ID", 537).getInt();
		ChickOrpingtonID = config.get("Entities", "Orpington Chick Entity ID", 538).getInt();

		HenWyandotteID = config.get("Entities", "Wyandotte Hen Entity ID", 539).getInt();
		RoosterWyandotteID = config.get("Entities", "Wyandotte Entity ID", 540).getInt();
		ChickWyandotteID = config.get("Entities", "Wyandotte Chick Entity ID", 541).getInt();

		HenRhodeIslandRedID = config.get("Entities", "Rhode Island Red Hen Entity ID", 542).getInt();
		RoosterRhodeIslandRedID = config.get("Entities", "Rhode Island Red Entity ID", 543).getInt();
		ChickRhodeIslandRedID = config.get("Entities", "Rhode Island Red Chick Entity ID", 544).getInt();

		//Peacocks
		PeacockBlueID = config.get("Entities", "India Blue Peacock Entity ID", 580).getInt();
		PeafowlBlueID = config.get("Entities", "India Blue Peafowl Entity ID", 581).getInt();
		PeachickBlueID = config.get("Entities", "India Blue Peachick Entity ID", 582).getInt();

		PeacockWhiteID = config.get("Entities", "India White Peacock Entity ID", 583).getInt();
		PeafowlWhiteID = config.get("Entities", "India White Peafowl Entity ID", 584).getInt();
		PeachickWhiteID = config.get("Entities", "India White Peachick Entity ID", 585).getInt();

		//Rodents
		HamsterMaleID = config.get("Entities", "Hamster Male Entity ID", 520).getInt();
		FerretMaleGreyID = config.get("Entities", "Grey Ferret Male Entity ID", 523).getInt();
		FerretMaleWhiteID = config.get("Entities", "White Ferret Male Entity ID", 526).getInt();
		HedgehogMaleID = config.get("Entities", "Hedgehog Male Entity ID", 529).getInt();
		HedgehogMaleAlbinoID = config.get("Entities", "Albino Hedgehog Male Entity ID", 550).getInt();

		//Pigs
		SowYorkshireID = config.get("Entities", "Yorkshire Sow Entity ID", 600).getInt();
		HogYorkshireID = config.get("Entities", "Yorkshire Hog Entity ID", 601).getInt();
		PigletYorkshireID = config.get("Entities", "Yorkshire Piglet Entity ID", 602).getInt();

		SowOldSpotID = config.get("Entities", "Old Spot Sow Entity ID", 603).getInt();
		HogOldSpotID = config.get("Entities", "Old Spot Hog Entity ID", 604).getInt();
		PigletOldSpotID = config.get("Entities", "Old Spot Piglet Entity ID", 605).getInt();

		SowLargeBlackID = config.get("Entities", "Large Black Sow Entity ID", 606).getInt();
		HogLargeBlackID = config.get("Entities", "Large Black Hog Entity ID", 607).getInt();
		PigletLargeBlackID = config.get("Entities", "Large Black Piglet Entity ID", 608).getInt();

		SowLargeWhiteID = config.get("Entities", "Large White Sow Entity ID", 609).getInt();
		HogLargeWhiteID = config.get("Entities", "Large White Hog Entity ID", 610).getInt();
		PigletLargeWhiteID = config.get("Entities", "Large White Piglet Entity ID", 611).getInt();

		SowDurocID = config.get("Entities", "Duroc Sow Entity ID", 612).getInt();
		HogDurocID = config.get("Entities", "Duroc Hog Entity ID", 613).getInt();
		PigletDurocID = config.get("Entities", "Duroc Piglet Entity ID", 614).getInt();

		SowHampshireID = config.get("Entities", "Hampshire Sow Entity ID", 615).getInt();
		HogHampshireID = config.get("Entities", "Hampshire Hog Entity ID", 616).getInt();
		PigletHampshireID = config.get("Entities", "Hampshire Piglet Entity ID", 617).getInt();

		//Horses
		//MareDraftHorseID = config.get("Entities", "Draft Horse Mare Entity ID", 701).getInt();
		//StallionDraftHorseID = config.get("Entities", "Draft Horse Stallion Entity ID", 702).getInt();
		//FoalDraftHorseID = config.get("Entities", "Draft Horse Foal Entity ID", 703).getInt();

		//GAME RULES
		foodsGiveBonusEffects = config.get("Game Rules", "Foods give bonus effects", true).getBoolean();
		showModUpdateNotification = config.get("Game Rules", "Show mod update notification at startup", true).getBoolean();
		showParts = config.get("Game Rules", "Show male parts (modesty flag)", true).getBoolean();
		showUnhappyParticles = config.get("Game Rules", "Show particles when animals are unhappy", true).getBoolean();
		enableVanillaMeatRecipes = config.get("Game Rules", "Enable recipes to exchange special meats for vanilla", false).getBoolean();
		replaceVanillaCows = config.get("Game Rules", "Remove vanilla Cows", true).getBoolean();
		replaceVanillaPigs = config.get("Game Rules", "Remove vanilla Pigs", true).getBoolean();
		replaceVanillaChickens = config.get("Game Rules", "Remove vanilla Chickens", true).getBoolean();
		allowEggThrowing = config.get("Game Rules", "Allow eggs to be thrown", false).getBoolean();
		shiftSeedPlacement = config.get("Game Rules", "Shift-Right-Click for Seed Placement", false).getBoolean();
		animalsStarve = config.get("Game Rules", "Animals starve to death when not fed and watered", false).getBoolean();
		
		//CARE & FEEDING
		childGrowthTick = config.get("Care and Feeding", "Ticks before next incremental growth", 200).getInt();
		feedTimer = config.get("Care and Feeding", "Ticks between feedings", 12000).getInt();
		waterTimer = config.get("Care and Feeding", "Ticks between drinking water", 8000).getInt();
		playTimer = config.get("Care and Feeding", "Ticks between playing", 4000).getInt();
		laidTimer = config.get("Care and Feeding", "Ticks between laying eggs", 2000).getInt();
		gestationTimer = config.get("Care and Feeding", "Ticks between birthings", 20000).getInt();
		starvationTimer = config.get("Care and Feeding", "Ticks between animals taking starvation damage", 200).getInt();
		eggHatchChance = config.get("Care and Feeding", "Egg hatch chance (1/x)", 2).getInt();

		//SPAWN
		spawnAnimaniaCows = config.get("Spawn", "Spawn Animania Cows in world", true).getBoolean();
		spawnAnimaniaPigs = config.get("Spawn", "Spawn Animania Pigs in world", true).getBoolean();
		spawnAnimaniaChickens = config.get("Spawn", "Spawn Animania Chickens in world", true).getBoolean();
		spawnAnimaniaRodents = config.get("Spawn", "Spawn Animania Rodents in world", true).getBoolean();
		spawnAnimaniaPeacocks = config.get("Spawn", "Spawn Animania Peacocks in world", true).getBoolean();
		spawnAnimaniaHorses = config.get("Spawn", "Spawn Animania Horses in world", true).getBoolean();

		spawnLimitCows = config.get("Spawn", "Spawn limit for Cows in loaded chunks", 36).getInt();
		spawnLimitPigs = config.get("Spawn", "Spawn limit for Pigs in loaded chunks", 32).getInt();
		spawnLimitChickens = config.get("Spawn", "Spawn limit for Chickens in loaded chunks", 32).getInt();
		spawnLimitHedgehogs = config.get("Spawn", "Spawn limit for Hedgehogs in loaded chunks", 50).getInt();
		spawnLimitFerrets = config.get("Spawn", "Spawn limit for Ferrets in loaded chunks", 50).getInt();
		spawnLimitHamsters = config.get("Spawn", "Spawn limit for Hamsters in loaded chunks", 50).getInt();
		spawnLimitPeacocks = config.get("Spawn", "Spawn limit for Peacocks in loaded chunks", 50).getInt();

		spawnProbabilityCows = config.get("Spawn", "Spawn probability Cows", 12).getInt();
		spawnProbabilitySows = config.get("Spawn", "Spawn probability Pigs", 12).getInt();
		spawnProbabilityHens = config.get("Spawn", "Spawn probability Chickens", 16).getInt();
		spawnProbabilityHedgehogs = config.get("Spawn", "Spawn probability Hedgehogs", 10).getInt();
		spawnProbabilityFerrets = config.get("Spawn", "Spawn probability Ferrets", 10).getInt();
		spawnProbabilityHamsters = config.get("Spawn", "Spawn probability Hamsters", 12).getInt();
		spawnProbabilityPeacocks = config.get("Spawn", "Spawn probability Peacocks", 20).getInt();

		numberCowFamilies = config.get("Spawn", "Number of potential Cow families per chunk", 2).getInt();
		numberPigFamilies  = config.get("Spawn", "Number of potential Pig families per chunk", 1).getInt();
		numberChickenFamilies  = config.get("Spawn", "Number of potential Chicken families per chunk", 1).getInt();

		//DROPS
		customMobDrops = config.get("Drops", "Enable Animal Drops from Config", true).getBoolean();
		chickenDrop = config.get("Drops", "Set Custom Chicken Drop (if enabled)", "animania:raw_prime_chicken").getString();
		pigDrop = config.get("Drops", "Set Custom Pig Drop (if enabled)", "animania:raw_prime_pork").getString();
		cowDrop = config.get("Drops", "Set Custom Cow Drop (if enabled)", "animania:raw_prime_beef").getString();
		horseDrop = config.get("Drops", "Set Custom Horse Drop (if enabled)", "").getString();
		peacockBlueDrop = config.get("Drops", "Set Custom Blue Peacock Drop (if enabled)", "animania:blue_peacock_feather").getString();
		peacockWhiteDrop = config.get("Drops", "Set Custom White Peacock Drop (if enabled)", "animania:white_peacock_feather").getString();
		ferretDrop = config.get("Drops", "Set Custom Ferret Drop (if enabled)", "").getString();
		hamsterDrop = config.get("Drops", "Set Custom Hamster Drop (if enabled)", "animania:hamster_food").getString();
		hedgehogDrop = config.get("Drops", "Set Custom Hedgehog Drop (if enabled)", "").getString();

		if (config.hasChanged())
		{
			config.save();
		}

	}

	public static void oreRegistration()
	{
		OreDictionary.registerOre("egg", Animania.brownEgg);
		OreDictionary.registerOre("listAllegg", Animania.brownEgg);

		OreDictionary.registerOre("listAllmeatraw", Animania.rawAngusBeef);
		OreDictionary.registerOre("listAllmeatraw", Animania.rawAngusSteak);
		OreDictionary.registerOre("listAllmeatraw", Animania.rawDurocBacon);
		OreDictionary.registerOre("listAllmeatraw", Animania.rawDurocPork);
		OreDictionary.registerOre("listAllmeatraw", Animania.rawHampshireBacon);
		OreDictionary.registerOre("listAllmeatraw", Animania.rawHampshirePork);
		OreDictionary.registerOre("listAllmeatraw", Animania.rawHerefordBeef);
		OreDictionary.registerOre("listAllmeatraw", Animania.rawHerefordSteak);
		OreDictionary.registerOre("listAllmeatraw", Animania.rawLargeBlackBacon);
		OreDictionary.registerOre("listAllmeatraw", Animania.rawLargeBlackPork);
		OreDictionary.registerOre("listAllmeatraw", Animania.rawLonghornBeef);
		OreDictionary.registerOre("listAllmeatraw", Animania.rawLonghornSteak);
		OreDictionary.registerOre("listAllmeatraw", Animania.rawOldSpotBacon);
		OreDictionary.registerOre("listAllmeatraw", Animania.rawOldSpotPork);
		OreDictionary.registerOre("listAllmeatraw", Animania.rawOrpingtonChicken);
		OreDictionary.registerOre("listAllmeatraw", Animania.rawPlymouthRockChicken);
		OreDictionary.registerOre("listAllmeatraw", Animania.rawWyandotteChicken);
		OreDictionary.registerOre("listAllmeatraw", Animania.rawRhodeIslandRedChicken);
		OreDictionary.registerOre("listAllbeefraw", Animania.rawAngusBeef);
		OreDictionary.registerOre("listAllbeefraw", Animania.rawAngusSteak);
		OreDictionary.registerOre("listAllbeefraw", Animania.rawHerefordBeef);
		OreDictionary.registerOre("listAllbeefraw", Animania.rawHerefordSteak);
		OreDictionary.registerOre("listAllbeefraw", Animania.rawLonghornBeef);
		OreDictionary.registerOre("listAllbeefraw", Animania.rawLonghornSteak);
		OreDictionary.registerOre("listAllporkraw", Animania.rawDurocBacon);
		OreDictionary.registerOre("listAllporkraw", Animania.rawDurocPork);
		OreDictionary.registerOre("listAllporkraw", Animania.rawHampshireBacon);
		OreDictionary.registerOre("listAllporkraw", Animania.rawHampshirePork);
		OreDictionary.registerOre("listAllporkraw", Animania.rawLargeBlackBacon);
		OreDictionary.registerOre("listAllporkraw", Animania.rawLargeBlackPork);
		OreDictionary.registerOre("listAllporkraw", Animania.rawOldSpotBacon);
		OreDictionary.registerOre("listAllporkraw", Animania.rawOldSpotPork);
		OreDictionary.registerOre("listAllchickenraw", Animania.rawOrpingtonChicken);
		OreDictionary.registerOre("listAllchickenraw", Animania.rawPlymouthRockChicken);
		OreDictionary.registerOre("listAllchickenraw", Animania.rawRhodeIslandRedChicken);
		OreDictionary.registerOre("listAllchickenraw", Animania.rawWyandotteChicken);

		OreDictionary.registerOre("listAllmeatcooked", Animania.cookedAngusRoast);
		OreDictionary.registerOre("listAllmeatcooked", Animania.cookedAngusSteak);
		OreDictionary.registerOre("listAllmeatcooked", Animania.cookedDurocBacon);
		OreDictionary.registerOre("listAllmeatcooked", Animania.cookedDurocRoast);
		OreDictionary.registerOre("listAllmeatcooked", Animania.cookedHampshireBacon);
		OreDictionary.registerOre("listAllmeatcooked", Animania.cookedHampshireRoast);
		OreDictionary.registerOre("listAllmeatcooked", Animania.cookedHerefordRoast);
		OreDictionary.registerOre("listAllmeatcooked", Animania.cookedHerefordSteak);
		OreDictionary.registerOre("listAllmeatcooked", Animania.cookedLargeBlackBacon);
		OreDictionary.registerOre("listAllmeatcooked", Animania.cookedLargeBlackRoast);
		OreDictionary.registerOre("listAllmeatcooked", Animania.cookedLonghornRoast);
		OreDictionary.registerOre("listAllmeatcooked", Animania.cookedLonghornSteak);
		OreDictionary.registerOre("listAllmeatcooked", Animania.cookedOldSpotBacon);
		OreDictionary.registerOre("listAllmeatcooked", Animania.cookedOldSpotRoast);
		OreDictionary.registerOre("listAllmeatcooked", Animania.cookedOrpingtonChicken);
		OreDictionary.registerOre("listAllmeatcooked", Animania.cookedPlymouthRockChicken);
		OreDictionary.registerOre("listAllmeatcooked", Animania.cookedRhodeIslandRedChicken);
		OreDictionary.registerOre("listAllmeatcooked", Animania.cookedWyandotteChicken);
		OreDictionary.registerOre("listAllbeefcooked", Animania.cookedAngusRoast);
		OreDictionary.registerOre("listAllbeefcooked", Animania.cookedAngusSteak);
		OreDictionary.registerOre("listAllbeefcooked", Animania.cookedHerefordRoast);
		OreDictionary.registerOre("listAllbeefcooked", Animania.cookedHerefordSteak);
		OreDictionary.registerOre("listAllbeefcooked", Animania.cookedLonghornRoast);
		OreDictionary.registerOre("listAllbeefcooked", Animania.cookedLonghornSteak);
		OreDictionary.registerOre("listAllporkcooked", Animania.cookedDurocBacon);
		OreDictionary.registerOre("listAllporkcooked", Animania.cookedDurocRoast);
		OreDictionary.registerOre("listAllporkcooked", Animania.cookedHampshireBacon);
		OreDictionary.registerOre("listAllporkcooked", Animania.cookedHampshireRoast);
		OreDictionary.registerOre("listAllporkcooked", Animania.cookedLargeBlackBacon);
		OreDictionary.registerOre("listAllporkcooked", Animania.cookedLargeBlackRoast);
		OreDictionary.registerOre("listAllporkcooked", Animania.cookedOldSpotBacon);
		OreDictionary.registerOre("listAllporkcooked", Animania.cookedOldSpotRoast);
		OreDictionary.registerOre("listAllchickencooked", Animania.cookedOrpingtonChicken);
		OreDictionary.registerOre("listAllchickencooked", Animania.cookedRhodeIslandRedChicken);
		OreDictionary.registerOre("listAllchickencooked", Animania.cookedPlymouthRockChicken);
		OreDictionary.registerOre("listAllchickencooked", Animania.cookedWyandotteChicken);

		//Cooked Prime Meats
		OreDictionary.registerOre("listAllmeatcooked", Animania.cookedPrimeChicken);
		OreDictionary.registerOre("listAllmeatcooked", Animania.cookedPrimePork);
		OreDictionary.registerOre("listAllmeatcooked", Animania.cookedPrimeBeef);
		OreDictionary.registerOre("listAllbeefcooked", Animania.cookedPrimeBeef);
		OreDictionary.registerOre("listAllbeefcooked", Animania.cookedPrimeSteak);
		OreDictionary.registerOre("listAllporkcooked", Animania.cookedPrimePork);
		OreDictionary.registerOre("listAllporkcooked", Animania.cookedPrimeBacon);
		OreDictionary.registerOre("listAllchickencooked", Animania.cookedPrimeChicken);

		OreDictionary.registerOre("listBucketmilk", Animania.milkBucketFriesian);
		OreDictionary.registerOre("listBucketmilk", Animania.milkBucketHolstein);

		OreDictionary.registerOre("foodCheese", Animania.cheeseWedgeFriesian);
		OreDictionary.registerOre("foodCheese", Animania.cheeseWedgeHolstein);

		OreDictionary.registerOre("cropCarrot", Items.CARROT);
		OreDictionary.registerOre("cropPotato", Items.POTATO);
		OreDictionary.registerOre("cropBeet", Items.BEETROOT);
		OreDictionary.registerOre("bread", Items.BREAD);
		OreDictionary.registerOre("listAllsugar", Items.SUGAR);
		OreDictionary.registerOre("listAllseed", Items.WHEAT_SEEDS);

		//Additions 1.0.4.8
		OreDictionary.registerOre("foodMeats", Animania.rawAngusBeef);
		OreDictionary.registerOre("foodMeats", Animania.rawAngusSteak);
		OreDictionary.registerOre("foodMeats", Animania.rawDurocBacon);
		OreDictionary.registerOre("foodMeats", Animania.rawDurocPork);
		OreDictionary.registerOre("foodMeats", Animania.rawHampshireBacon);
		OreDictionary.registerOre("foodMeats", Animania.rawHampshirePork);
		OreDictionary.registerOre("foodMeats", Animania.rawHerefordBeef);
		OreDictionary.registerOre("foodMeats", Animania.rawHerefordSteak);
		OreDictionary.registerOre("foodMeats", Animania.rawLargeBlackBacon);
		OreDictionary.registerOre("foodMeats", Animania.rawLargeBlackPork);
		OreDictionary.registerOre("foodMeats", Animania.rawLonghornBeef);
		OreDictionary.registerOre("foodMeats", Animania.rawLonghornSteak);
		OreDictionary.registerOre("foodMeats", Animania.rawOldSpotBacon);
		OreDictionary.registerOre("foodMeats", Animania.rawOldSpotPork);
		OreDictionary.registerOre("foodMeats", Animania.rawOrpingtonChicken);
		OreDictionary.registerOre("foodMeats", Animania.rawPlymouthRockChicken);
		OreDictionary.registerOre("foodMeats", Animania.rawWyandotteChicken);
		OreDictionary.registerOre("foodMeats", Animania.rawRhodeIslandRedChicken);
		OreDictionary.registerOre("foodMeats", Animania.cookedAngusRoast);
		OreDictionary.registerOre("foodMeats", Animania.cookedAngusSteak);
		OreDictionary.registerOre("foodMeats", Animania.cookedDurocBacon);
		OreDictionary.registerOre("foodMeats", Animania.cookedDurocRoast);
		OreDictionary.registerOre("foodMeats", Animania.cookedHampshireBacon);
		OreDictionary.registerOre("foodMeats", Animania.cookedHampshireRoast);
		OreDictionary.registerOre("foodMeats", Animania.cookedHerefordRoast);
		OreDictionary.registerOre("foodMeats", Animania.cookedHerefordSteak);
		OreDictionary.registerOre("foodMeats", Animania.cookedLargeBlackBacon);
		OreDictionary.registerOre("foodMeats", Animania.cookedLargeBlackRoast);
		OreDictionary.registerOre("foodMeats", Animania.cookedLonghornRoast);
		OreDictionary.registerOre("foodMeats", Animania.cookedLonghornSteak);
		OreDictionary.registerOre("foodMeats", Animania.cookedOldSpotBacon);
		OreDictionary.registerOre("foodMeats", Animania.cookedOldSpotRoast);
		OreDictionary.registerOre("foodMeats", Animania.cookedOrpingtonChicken);
		OreDictionary.registerOre("foodMeats", Animania.cookedPlymouthRockChicken);
		OreDictionary.registerOre("foodMeats", Animania.cookedRhodeIslandRedChicken);
		OreDictionary.registerOre("foodMeats", Animania.cookedWyandotteChicken);
		OreDictionary.registerOre("foodMeats", Animania.rawPrimeChicken);
		OreDictionary.registerOre("foodMeats", Animania.rawPrimePork);
		OreDictionary.registerOre("foodMeats", Animania.rawPrimeBeef);
		OreDictionary.registerOre("foodMeats", Animania.cookedPrimeChicken);
		OreDictionary.registerOre("foodMeats", Animania.cookedPrimePork);
		OreDictionary.registerOre("foodMeats", Animania.cookedPrimeBeef);
		OreDictionary.registerOre("listAllchickenraw", Items.CHICKEN);
		OreDictionary.registerOre("listAllbeefraw", Items.BEEF);
		OreDictionary.registerOre("listAllporkraw", Items.PORKCHOP);
		OreDictionary.registerOre("listAllchickencooked", Items.COOKED_CHICKEN);
		OreDictionary.registerOre("listAllbeefcooked", Items.COOKED_BEEF);
		OreDictionary.registerOre("listAllporkcooked", Items.COOKED_PORKCHOP);
		OreDictionary.registerOre("feather", Animania.peacockFeatherBlue);
		OreDictionary.registerOre("feather", Animania.peacockFeatherWhite);

		OreDictionary.registerOre("wool", new ItemStack(Blocks.WOOL, 1, Short.MAX_VALUE));

		//TODO
		//add ferret eating meats ore dict


	}


}
