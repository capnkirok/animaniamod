package com.animania.common;

import com.animania.common.entities.chickens.ChickenType;
import com.animania.common.entities.cows.CowType;
import com.animania.common.entities.goats.GoatType;
import com.animania.common.entities.horses.HorseType;
import com.animania.common.entities.peacocks.PeacockType;
import com.animania.common.entities.pigs.PigType;
import com.animania.common.entities.rodents.FerretType;
import com.animania.common.entities.rodents.HedgehogType;
import com.animania.common.entities.rodents.rabbits.RabbitType;
import com.animania.common.entities.sheep.SheepType;
import com.animania.common.handler.BlockHandler;
import com.animania.common.handler.ItemHandler;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class AnimaniaAchievements
{

	public static AchievementPage page;

	public static Achievement Leghorn;
	public static Achievement Orpington;
	public static Achievement PlymouthRock;
	public static Achievement RhodeIslandRed;
	public static Achievement Wyandotte;
	public static Achievement Chickens;

	public static Achievement Angus;
	public static Achievement Friesian;
	public static Achievement Hereford;
	public static Achievement Holstein;
	public static Achievement Longhorn;
	public static Achievement Highland;
	public static Achievement Jersey;
	public static Achievement Mooshroom;
	
	public static Achievement Cows;

	public static Achievement IndiaBlue;
	public static Achievement White;
	public static Achievement Charcoal;
	public static Achievement Opal;
	public static Achievement Peach;
	public static Achievement Purple;
	public static Achievement Taupe;
	public static Achievement Peacocks;

	public static Achievement Duroc;
	public static Achievement Hampshire;
	public static Achievement LargeBlack;
	public static Achievement LargeWhite;
	public static Achievement OldSpot;
	public static Achievement Yorkshire;
	public static Achievement Pigs;

	public static Achievement Hamsters;

	public static Achievement WhiteFerret;
	public static Achievement GreyFerret;
	public static Achievement Ferrets;

	public static Achievement Hedgehog;
	public static Achievement AlbinoHedgehog;
	public static Achievement Hedgehogs;

	public static Achievement Sonic;
	public static Achievement Sanic;
	public static Achievement FeelsBadMan;

	public static Achievement Horses;
	public static Achievement Horseriding;
	
	public static Achievement Chinchilla;
	public static Achievement Cottontail;
	public static Achievement Dutch;
	public static Achievement Havana;
	public static Achievement Jack;
	public static Achievement Lop;
	public static Achievement NewZealand;
	public static Achievement Rex;
	public static Achievement Rabbits;
	
	public static Achievement Alpine;
	public static Achievement Angora;
	public static Achievement Fainting;
	public static Achievement Kiko;
	public static Achievement Kinder;
	public static Achievement NigerianDwarf;
	public static Achievement Pygmy;
	public static Achievement Goats;
	
	public static Achievement Dorper;
	public static Achievement Dorset;
	public static Achievement FriesianSheep;
	public static Achievement Jacob;
	public static Achievement Merino;
	public static Achievement Suffolk;
	public static Achievement Sheep;
	
	public static void init()
	{
		// Register Achievements
		AnimaniaAchievements.Angus = new Achievement("angus", "an.angus", -2, -2, ItemHandler.entityeggcalfangus, (Achievement) null).registerStat();
		AnimaniaAchievements.Friesian = new Achievement("friesian", "an.friesian", 0, -2, ItemHandler.entityeggcalffriesian, (Achievement) null).registerStat();
		AnimaniaAchievements.Hereford = new Achievement("hereford", "an.hereford", 2, -2, ItemHandler.entityeggcalfhereford, (Achievement) null).registerStat();
		AnimaniaAchievements.Holstein = new Achievement("holstein", "an.holstein", 4, -2, ItemHandler.entityeggcalfholstein, (Achievement) null).registerStat();
		AnimaniaAchievements.Longhorn = new Achievement("longhorn", "an.longhorn", 6, -2, ItemHandler.entityeggcalflonghorn, (Achievement) null).registerStat();
		AnimaniaAchievements.Jersey = new Achievement("jersey", "an.jersey", 6, -2, ItemHandler.entityeggcalfjersey, (Achievement) null).registerStat();
		AnimaniaAchievements.Highland = new Achievement("highland", "an.highland", 6, -2, ItemHandler.entityeggcalfhighland, (Achievement) null).registerStat();
		AnimaniaAchievements.Mooshroom = new Achievement("mooshroom", "an.mooshroom", 6, -2, ItemHandler.entityeggcalfmooshroom, (Achievement) null).registerStat();
		AnimaniaAchievements.Cows = new Achievement("cows", "an.cows", 8, -2, Items.MILK_BUCKET, AnimaniaAchievements.Longhorn).registerStat();

		AnimaniaAchievements.Leghorn = new Achievement("leghorn", "an.leghorn", -2, 0, ItemHandler.entityeggchickleghorn, (Achievement) null).registerStat();
		AnimaniaAchievements.Orpington = new Achievement("orpington", "an.orpington", 0, 0, ItemHandler.entityeggchickorpington, (Achievement) null).registerStat();
		AnimaniaAchievements.PlymouthRock = new Achievement("plymouthrock", "an.plymouthrock", 2, 0, ItemHandler.entityeggchickplymouth, (Achievement) null).registerStat();
		AnimaniaAchievements.RhodeIslandRed = new Achievement("rhodeislandred", "an.rhodeislandred", 4, 0, ItemHandler.entityeggchickred, (Achievement) null).registerStat();
		AnimaniaAchievements.Wyandotte = new Achievement("wyandotte", "an.wyandotte", 6, 0, ItemHandler.entityeggchickwyandotte, (Achievement) null).registerStat();
		AnimaniaAchievements.Chickens = new Achievement("chickens", "an.chickens", 8, 0, ItemHandler.brownEgg, AnimaniaAchievements.Wyandotte).registerStat();

		AnimaniaAchievements.Duroc = new Achievement("duroc", "an.duroc", -2, 2, ItemHandler.entityeggpigletduroc, (Achievement) null).registerStat();
		AnimaniaAchievements.Hampshire = new Achievement("hampshire", "an.hampshire", 0, 2, ItemHandler.entityeggpiglethampshire, (Achievement) null).registerStat();
		AnimaniaAchievements.LargeBlack = new Achievement("largeblack", "an.largeblack", 2, 2, ItemHandler.entityeggpigletlargeblack, (Achievement) null).registerStat();
		AnimaniaAchievements.LargeWhite = new Achievement("largewhite", "an.largewhite", 4, 2, ItemHandler.entityeggpigletlargewhite, (Achievement) null).registerStat();
		AnimaniaAchievements.OldSpot = new Achievement("oldspot", "an.oldspot", 6, 2, ItemHandler.entityeggpigletoldspot, (Achievement) null).registerStat();
		AnimaniaAchievements.Yorkshire = new Achievement("yorkshire", "an.yorkshire", 8, 2, ItemHandler.entityeggpigletyorkshire, (Achievement) null).registerStat();
		AnimaniaAchievements.Pigs = new Achievement("pigs", "an.pigs", 10, 2, ItemHandler.cookedPrimeBacon, AnimaniaAchievements.Yorkshire).registerStat();

		AnimaniaAchievements.IndiaBlue = new Achievement("indiablue", "an.indiablue", -2, 4, ItemHandler.entityeggpeachickblue, (Achievement) null).registerStat();
		AnimaniaAchievements.White = new Achievement("white", "an.white", 0, 4, ItemHandler.entityeggpeachickwhite, (Achievement) null).registerStat();
		AnimaniaAchievements.Charcoal = new Achievement("charcoal", "an.charcoal", 2, 4, ItemHandler.entityeggpeachickcharcoal, (Achievement) null).registerStat();
		AnimaniaAchievements.Opal = new Achievement("opal", "an.opal", 4, 4, ItemHandler.entityeggpeachickopal, (Achievement) null).registerStat();
		AnimaniaAchievements.Peach = new Achievement("peach", "an.peach", 6, 4, ItemHandler.entityeggpeachickpeach, (Achievement) null).registerStat();
		AnimaniaAchievements.Purple = new Achievement("purple", "an.purple", 8, 4, ItemHandler.entityeggpeachickpurple, (Achievement) null).registerStat();
		AnimaniaAchievements.Taupe = new Achievement("taupe", "an.taupe", 10, 4, ItemHandler.entityeggpeachicktaupe, (Achievement) null).registerStat();
		AnimaniaAchievements.Peacocks = new Achievement("Peacocks", "an.peacocks", 12, 4, ItemHandler.peacockFeatherBlue, AnimaniaAchievements.White).registerStat();

		AnimaniaAchievements.WhiteFerret = new Achievement("whiteferret", "an.whiteferret", -2, 6, ItemHandler.entityeggferretwhite, (Achievement) null).registerStat();
		AnimaniaAchievements.GreyFerret = new Achievement("greyferret", "an.greyferret", 0, 6, ItemHandler.entityeggferretgrey, (Achievement) null).registerStat();
		AnimaniaAchievements.Ferrets = new Achievement("ferrets", "an.ferrets", 2, 6, ItemHandler.entityeggferretwhite, AnimaniaAchievements.GreyFerret).registerStat();

		AnimaniaAchievements.Hamsters = new Achievement("hamsters", "an.hamsters", -2, 8, ItemHandler.hamsterBallClear, (Achievement) null).registerStat();

		AnimaniaAchievements.Hedgehog = new Achievement("hedgehog", "an.hedgehog", -2, 10, ItemHandler.entityegghedgehog, (Achievement) null).registerStat();
		AnimaniaAchievements.AlbinoHedgehog = new Achievement("albinohedgehog", "an.albinohedgehog", 0, 10, ItemHandler.entityegghedgehogalbino, (Achievement) null).registerStat();
		AnimaniaAchievements.Hedgehogs = new Achievement("hedgehogs", "an.hedgehogs", 2, 10, Items.MUTTON, (Achievement) null).registerStat();

		AnimaniaAchievements.Sonic = new Achievement("sonic", "an.hedgehogsonic", 4, 10, ItemHandler.entityegghedgehog, (Achievement) null).registerStat().setSpecial();
		AnimaniaAchievements.Sanic = new Achievement("sanic", "an.hedgehogsanic", 6, 10, ItemHandler.entityegghedgehog, (Achievement) null).registerStat().setSpecial();
		AnimaniaAchievements.FeelsBadMan = new Achievement("feelsbadman", "an.feelsbadman", -2, 20, ItemHandler.entityeggfrog, (Achievement) null).registerStat().setSpecial();

		AnimaniaAchievements.Horses = new Achievement("horses", "an.horses", -2, 12, ItemHandler.entityeggdrafthorsestallion, (Achievement) null).registerStat();
		AnimaniaAchievements.Horseriding = new Achievement("horseriding", "an.horseriding", 0, 12, ItemHandler.ridingCrop, (Achievement) null).registerStat();

		AnimaniaAchievements.Chinchilla = new Achievement("chinchilla", "an.chinchilla", -2, 14, ItemHandler.entityeggkitchinchilla, (Achievement) null).registerStat();
		AnimaniaAchievements.Cottontail = new Achievement("cottontail", "an.cottontail", 0, 14, ItemHandler.entityeggkitcottontail, (Achievement) null).registerStat();
		AnimaniaAchievements.Dutch = new Achievement("dutch", "an.dutch", 2, 14, ItemHandler.entityeggkitdutch, (Achievement) null).registerStat();
		AnimaniaAchievements.Havana = new Achievement("havana", "an.havana", 4, 14, ItemHandler.entityeggkithavana, (Achievement) null).registerStat();
		AnimaniaAchievements.Jack = new Achievement("jack", "an.jack", 6, 14, ItemHandler.entityeggkitjack, (Achievement) null).registerStat();
		AnimaniaAchievements.Lop = new Achievement("lop", "an.lop", 8, 14, ItemHandler.entityeggkitlop, (Achievement) null).registerStat();
		AnimaniaAchievements.NewZealand = new Achievement("newzealand", "an.newzealand", 10, 14, ItemHandler.entityeggkitnewzealand, (Achievement) null).registerStat();
		AnimaniaAchievements.Rex = new Achievement("rex", "an.rex", 12, 14, ItemHandler.entityeggkitrex, (Achievement) null).registerStat();
		AnimaniaAchievements.Rabbits = new Achievement("rabbits", "an.rabbits", 14, 14, Items.CARROT, (Achievement) null).registerStat();
		
		AnimaniaAchievements.Alpine = new Achievement("alpine", "an.alpine", -2, 16, ItemHandler.entityeggkidalpine, (Achievement) null).registerStat();
		AnimaniaAchievements.Angora = new Achievement("angora", "an.angora", 0, 16, ItemHandler.entityeggkidangora, (Achievement) null).registerStat();
		AnimaniaAchievements.Fainting = new Achievement("fainting", "an.fainting", 2, 16, ItemHandler.entityeggkidfainting, (Achievement) null).registerStat();
		AnimaniaAchievements.Kiko = new Achievement("kiko", "an.kiko", 4, 16, ItemHandler.entityeggkidkiko, (Achievement) null).registerStat();
		AnimaniaAchievements.Kinder = new Achievement("kinder", "an.kinder", 6, 16, ItemHandler.entityeggkidkinder, (Achievement) null).registerStat();
		AnimaniaAchievements.NigerianDwarf = new Achievement("nigeriandwarf", "an.nigeriandwarf", 8, 16, ItemHandler.entityeggkidnigeriandwarf, (Achievement) null).registerStat();
		AnimaniaAchievements.Pygmy = new Achievement("pygmy", "an.pygmy", 10, 16, ItemHandler.entityeggkidpygmy, (Achievement) null).registerStat();
		AnimaniaAchievements.Goats = new Achievement("goats", "an.goats", 12, 16, ItemHandler.cheeseWedgeGoat, (Achievement) null).registerStat();
		
		AnimaniaAchievements.Dorper = new Achievement("dorper", "an.dorper", -2, 18, ItemHandler.entityegglambdorper, (Achievement) null).registerStat();
		AnimaniaAchievements.Dorset = new Achievement("dorset", "an.dorset", 0, 18, ItemHandler.entityegglambdorset, (Achievement) null).registerStat();
		AnimaniaAchievements.FriesianSheep = new Achievement("friesiansheep", "an.friesiansheep", 2, 18, ItemHandler.entityegglambfriesian, (Achievement) null).registerStat();
		AnimaniaAchievements.Jacob = new Achievement("jacob", "an.jacob", 4, 18, ItemHandler.entityegglambjacob, (Achievement) null).registerStat();
		AnimaniaAchievements.Merino = new Achievement("merino", "an.merino", 6, 18, ItemHandler.entityegglambmerino, (Achievement) null).registerStat();
		AnimaniaAchievements.Suffolk = new Achievement("suffolk", "an.suffolk", 8, 18, ItemHandler.entityegglambsuffolk, (Achievement) null).registerStat();
		AnimaniaAchievements.Sheep = new Achievement("sheep", "an.sheep", 10, 18, Blocks.WOOL, (Achievement) null).registerStat();
		
		AchievementPage.registerAchievementPage(AnimaniaAchievements.page = new AchievementPage("Animania", new Achievement[] { AnimaniaAchievements.Leghorn, AnimaniaAchievements.Orpington, AnimaniaAchievements.PlymouthRock, AnimaniaAchievements.RhodeIslandRed, AnimaniaAchievements.Wyandotte, AnimaniaAchievements.Chickens, 
				AnimaniaAchievements.Angus, AnimaniaAchievements.Friesian, AnimaniaAchievements.Hereford, AnimaniaAchievements.Holstein, AnimaniaAchievements.Longhorn, AnimaniaAchievements.Jersey, AnimaniaAchievements.Mooshroom, AnimaniaAchievements.Cows, 
				AnimaniaAchievements.Duroc, AnimaniaAchievements.Hampshire, AnimaniaAchievements.LargeBlack, AnimaniaAchievements.LargeWhite, AnimaniaAchievements.OldSpot, AnimaniaAchievements.Yorkshire, AnimaniaAchievements.Pigs, 
				AnimaniaAchievements.IndiaBlue, AnimaniaAchievements.White, AnimaniaAchievements.Charcoal, AnimaniaAchievements.Opal, AnimaniaAchievements.Peach, AnimaniaAchievements.Purple, AnimaniaAchievements.Taupe, AnimaniaAchievements.Peacocks, 
				AnimaniaAchievements.WhiteFerret, AnimaniaAchievements.GreyFerret, AnimaniaAchievements.Ferrets, AnimaniaAchievements.Hamsters, 
				AnimaniaAchievements.Hedgehog, AnimaniaAchievements.AlbinoHedgehog, AnimaniaAchievements.Hedgehogs, AnimaniaAchievements.Sonic, AnimaniaAchievements.Sanic,
				AnimaniaAchievements.Horses, AnimaniaAchievements.Horseriding,
				AnimaniaAchievements.Chinchilla, AnimaniaAchievements.Cottontail, AnimaniaAchievements.Dutch, AnimaniaAchievements.Havana, AnimaniaAchievements.Jack, AnimaniaAchievements.Lop, AnimaniaAchievements.NewZealand, AnimaniaAchievements.Rex, AnimaniaAchievements.Rabbits, 
				AnimaniaAchievements.Alpine, AnimaniaAchievements.Angora, AnimaniaAchievements.Fainting, AnimaniaAchievements.Kiko, AnimaniaAchievements.Kinder, AnimaniaAchievements.NigerianDwarf, AnimaniaAchievements.Pygmy, AnimaniaAchievements.Goats, 
				AnimaniaAchievements.Dorper, AnimaniaAchievements.Dorset, AnimaniaAchievements.FriesianSheep, AnimaniaAchievements.Jacob, AnimaniaAchievements.Merino, AnimaniaAchievements.Suffolk, AnimaniaAchievements.Sheep,
				AnimaniaAchievements.FeelsBadMan
						
		}));

	}

	public static void postItemInit()
	{ 
		CowType.ANGUS.setAchievement(Angus);
		CowType.FRIESIAN.setAchievement(Friesian);
		CowType.HEREFORD.setAchievement(Hereford);
		CowType.LONGHORN.setAchievement(Longhorn);
		CowType.HOLSTEIN.setAchievement(Holstein);
		CowType.MOOSHROOM.setAchievement(Mooshroom);
		CowType.JERSEY.setAchievement(Jersey);
		
		ChickenType.LEGHORN.setAchievement(Leghorn);
		ChickenType.ORPINGTON.setAchievement(Orpington);
		ChickenType.PLYMOUTH_ROCK.setAchievement(PlymouthRock);
		ChickenType.RHODE_ISLAND_RED.setAchievement(RhodeIslandRed);
		ChickenType.WYANDOTTE.setAchievement(Wyandotte);

		HorseType.DRAFT.setAchievement(Horses);
		
		PeacockType.BLUE.setAchievement(IndiaBlue);
		PeacockType.WHITE.setAchievement(White);
		PeacockType.CHARCOAL.setAchievement(Charcoal);
		PeacockType.OPAL.setAchievement(Opal);
		PeacockType.PEACH.setAchievement(Peach);
		PeacockType.PURPLE.setAchievement(Purple);
		PeacockType.TAUPE.setAchievement(Taupe);
		
		PigType.DUROC.setAchievement(Duroc);
		PigType.HAMPSHIRE.setAchievement(Hampshire);
		PigType.LARGE_BLACK.setAchievement(LargeBlack);
		PigType.LARGE_WHITE.setAchievement(LargeWhite);
		PigType.OLD_SPOT.setAchievement(OldSpot);
		PigType.YORKSHIRE.setAchievement(Yorkshire);
		
		HedgehogType.ALBINO.setAchievement(AlbinoHedgehog);
		HedgehogType.NORMAL.setAchievement(Hedgehog);

		FerretType.GREY.setAchievement(GreyFerret);
		FerretType.WHITE.setAchievement(WhiteFerret);

		RabbitType.CHINCHILLA.setAchievement(Chinchilla);
		RabbitType.COTTONTAIL.setAchievement(Cottontail);
		RabbitType.DUTCH.setAchievement(Dutch);
		RabbitType.HAVANA.setAchievement(Havana);
		RabbitType.JACK.setAchievement(Jack);
		RabbitType.LOP.setAchievement(Lop);
		RabbitType.NEW_ZEALAND.setAchievement(NewZealand);
		RabbitType.REX.setAchievement(Rex);
		
		GoatType.ALPINE.setAchievement(Alpine);
		GoatType.ANGORA.setAchievement(Angora);
		GoatType.FAINTING.setAchievement(Fainting);
		GoatType.KIKO.setAchievement(Kiko);
		GoatType.KINDER.setAchievement(Kinder);
		GoatType.NIGERIAN_DWARF.setAchievement(NigerianDwarf);
		GoatType.PYGMY.setAchievement(Pygmy);
		
		SheepType.DORPER.setAchievement(Dorper);
		SheepType.DORSET.setAchievement(Dorset);
		SheepType.FRIESIAN.setAchievement(FriesianSheep);
		SheepType.JACOB.setAchievement(Jacob);
		SheepType.MERINO.setAchievement(Merino);
		SheepType.SUFFOLK.setAchievement(Suffolk);
		

	}

}
