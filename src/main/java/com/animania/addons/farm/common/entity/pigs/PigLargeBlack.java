package com.animania.addons.farm.common.entity.pigs;

import net.minecraft.world.level.Level;

public class PigLargeBlack
{

	public static class EntitySowLargeBlack extends EntitySowBase
	{

		public EntitySowLargeBlack(Level level)
		{
			super(level);
			this.pigType = PigType.LARGE_BLACK;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 8417906;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 5326149;
		}

	}

	public static class PigEntityletLargeBlack extends PigEntityletBase
	{

		public PigEntityletLargeBlack(Level level)
		{
			super(level);
			this.pigType = PigType.LARGE_BLACK;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 8417906;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 5326149;
		}

	}

	public static class EntityHogLargeBlack extends EntityHogBase
	{

		public EntityHogLargeBlack(Level level)
		{
			super(level);
			this.pigType = PigType.LARGE_BLACK;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 8417906;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 5326149;
		}

	}

}
