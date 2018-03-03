package com.animania.common.entities.cows;

import com.animania.common.handler.BlockHandler;
import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.UniversalBucket;

public class EntityCowJersey extends EntityCowBase
{

	public EntityCowJersey(World world)
	{
		super(world);
		this.cowType = CowType.JERSEY;
		this.milk = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, BlockHandler.fluidMilkJersey);
		this.dropRaw = ItemHandler.rawPrimeBeef;
		this.dropCooked = ItemHandler.cookedPrimeBeef;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 12089918;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 16775643;
	}
}