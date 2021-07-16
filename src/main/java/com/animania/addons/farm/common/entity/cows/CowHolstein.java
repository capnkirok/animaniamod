package com.animania.addons.farm.common.entity.cows;

import com.animania.addons.farm.common.handler.FarmAddonBlockHandler;

import net.minecraft.world.World;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.UniversalBucket;

public class CowHolstein
{

	public static class EntityBullHolstein extends EntityBullBase
	{

		public EntityBullHolstein(World world)
		{
			super(world);
			this.cowType = CowType.HOLSTEIN;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 15987699;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 2236962;
		}

	}

	public static class EntityCalfHolstein extends EntityCalfBase
	{

		public EntityCalfHolstein(World world)
		{
			super(world);
			this.cowType = CowType.HOLSTEIN;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 15987699;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 2236962;
		}

	}

	public static class CowEntityHolstein extends CowEntityBase
	{

		public CowEntityHolstein(World world)
		{
			super(world);
			this.cowType = CowType.HOLSTEIN;
			this.milk = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, FarmAddonBlockHandler.fluidMilkHolstein);
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 15987699;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 2236962;
		}

	}
}