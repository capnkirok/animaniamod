package com.animania.addons.farm.common.entity.pigs;

import net.minecraft.world.level.Level;

public class PigOldSpot
{

	public static class EntityHogOldSpot extends EntityHogBase
	{

		public EntityHogOldSpot(Level level)
		{
			super(level);
			this.pigType = PigType.OLD_SPOT;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 15845576;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 9859698;
		}

	}

	public static class PigEntityletOldSpot extends PigEntityletBase
	{

		public PigEntityletOldSpot(Level level)
		{
			super(level);
			this.pigType = PigType.OLD_SPOT;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 15845576;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 9859698;
		}

	}

	public static class EntitySowOldSpot extends EntitySowBase
	{

		public EntitySowOldSpot(Level level)
		{
			super(level);
			this.pigType = PigType.OLD_SPOT;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 15845576;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 9859698;
		}

	}

}
