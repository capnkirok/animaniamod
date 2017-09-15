package com.animania.common.entities;

import java.util.Random;

import com.animania.common.entities.amphibians.AmphibianType;
import com.animania.common.entities.chickens.ChickenType;
import com.animania.common.entities.cows.CowType;
import com.animania.common.entities.goats.GoatType;
import com.animania.common.entities.horses.HorseType;
import com.animania.common.entities.peacocks.PeacockType;
import com.animania.common.entities.pigs.PigType;
import com.animania.common.entities.rodents.FerretType;
import com.animania.common.entities.rodents.HamsterType;
import com.animania.common.entities.rodents.HedgehogType;
import com.animania.common.entities.rodents.rabbits.RabbitType;
import com.animania.common.entities.sheep.SheepType;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class RandomAnimalType implements AnimaniaType
{

	@Override
	public EntityLivingBase getMale(World world)
	{
		Random rand = new Random();
		switch(rand.nextInt(12))
		{
		case 0:
			return AmphibianType.values()[rand.nextInt(AmphibianType.values().length)].getMale(world);
		case 1:
			return ChickenType.values()[rand.nextInt(ChickenType.values().length)].getMale(world);
		case 2:
			return CowType.values()[rand.nextInt(CowType.values().length)].getMale(world);
		case 3:
			return GoatType.values()[rand.nextInt(GoatType.values().length)].getMale(world);
		case 4:
			return HorseType.values()[rand.nextInt(HorseType.values().length)].getMale(world);
		case 5:
			return PeacockType.values()[rand.nextInt(PeacockType.values().length)].getMale(world);
		case 6:
			return PigType.values()[rand.nextInt(PigType.values().length)].getMale(world);
		case 7:
			return FerretType.values()[rand.nextInt(FerretType.values().length)].getMale(world);
		case 8:
			return HamsterType.values()[rand.nextInt(HamsterType.values().length)].getMale(world);
		case 9:
			return HedgehogType.values()[rand.nextInt(HedgehogType.values().length)].getMale(world);
		case 10:
			return RabbitType.values()[rand.nextInt(RabbitType.values().length)].getMale(world);
		case 11:
			return SheepType.values()[rand.nextInt(SheepType.values().length)].getMale(world);
			}
		return null;
	}

	@Override
	public EntityLivingBase getFemale(World world)
	{
		Random rand = new Random();
		switch(rand.nextInt(12))
		{
		case 0:
			return AmphibianType.values()[rand.nextInt(AmphibianType.values().length)].getFemale(world);
		case 1:
			return ChickenType.values()[rand.nextInt(ChickenType.values().length)].getFemale(world);
		case 2:
			return CowType.values()[rand.nextInt(CowType.values().length)].getFemale(world);
		case 3:
			return GoatType.values()[rand.nextInt(GoatType.values().length)].getFemale(world);
		case 4:
			return HorseType.values()[rand.nextInt(HorseType.values().length)].getFemale(world);
		case 5:
			return PeacockType.values()[rand.nextInt(PeacockType.values().length)].getFemale(world);
		case 6:
			return PigType.values()[rand.nextInt(PigType.values().length)].getFemale(world);
		case 7:
		case 8:
		case 9:
		case 10:
			return RabbitType.values()[rand.nextInt(RabbitType.values().length)].getFemale(world);
		case 11:
			return SheepType.values()[rand.nextInt(SheepType.values().length)].getFemale(world);
			}
		return null;
	}

	@Override
	public EntityLivingBase getChild(World world)
	{
		Random rand = new Random();
		switch(rand.nextInt(12))
		{
		case 0:
			return AmphibianType.values()[rand.nextInt(AmphibianType.values().length)].getChild(world);
		case 1:
			return ChickenType.values()[rand.nextInt(ChickenType.values().length)].getChild(world);
		case 2:
			return CowType.values()[rand.nextInt(CowType.values().length)].getChild(world);
		case 3:
			return GoatType.values()[rand.nextInt(GoatType.values().length)].getChild(world);
		case 4:
			return HorseType.values()[rand.nextInt(HorseType.values().length)].getChild(world);
		case 5:
			return PeacockType.values()[rand.nextInt(PeacockType.values().length)].getChild(world);
		case 6:
			return PigType.values()[rand.nextInt(PigType.values().length)].getChild(world);
		case 7:
		case 8:
		case 9:
		case 10:
			return RabbitType.values()[rand.nextInt(RabbitType.values().length)].getChild(world);
		case 11:
			return SheepType.values()[rand.nextInt(SheepType.values().length)].getChild(world);
			}
		return null;
	}

}
