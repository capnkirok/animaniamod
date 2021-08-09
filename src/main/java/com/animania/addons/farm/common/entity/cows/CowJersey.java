package com.animania.addons.farm.common.entity.cows;

import com.animania.addons.farm.common.handler.FarmAddonBlockHandler;

import net.minecraft.world.World;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.UniversalBucket;

public class CowJersey
{

	public static class EntityBullJersey extends EntityBullBase
	{

		public EntityBullJersey(World world)
		{
			super(world);
			this.cowType = CowType.JERSEY;

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

	public static class CowEntityJersey extends CowEntityBase
	{

		public CowEntityJersey(World world)
		{
			super(world);
			this.cowType = CowType.JERSEY;
			this.milk = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, FarmAddonBlockHandler.fluidMilkJersey);

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

	public static class EntityCalfJersey extends EntityCalfBase
	{

		public EntityCalfJersey(World world)
		{
			super(world);
			this.cowType = CowType.JERSEY;
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
}
