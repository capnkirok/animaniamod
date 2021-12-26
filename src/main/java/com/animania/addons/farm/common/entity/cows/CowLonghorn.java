package com.animania.addons.farm.common.entity.cows;

import net.minecraft.world.level.Level;

public class CowLonghorn
{

	public static class EntityBullLonghorn extends EntityBullBase
	{

		public EntityBullLonghorn(Level level)
		{
			super(level);
			this.cowType = CowType.LONGHORN;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 16763795;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 11227168;
		}

	}

	public static class CowEntityLonghorn extends CowEntityBase
	{

		public CowEntityLonghorn(Level level)
		{
			super(level);
			this.cowType = CowType.LONGHORN;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 16763795;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 11227168;
		}

	}

	public static class EntityCalfLonghorn extends EntityCalfBase
	{

		public EntityCalfLonghorn(Level level)
		{
			super(level);
			this.cowType = CowType.LONGHORN;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 16763795;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 11227168;
		}

	}

}