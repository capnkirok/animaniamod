package com.animania.addons.farm.common.entity.pigs;

public class PigHampshire
{

	public static class EntitySowHampshire extends EntitySowBase
	{
	
		public EntitySowHampshire(Level level)
		{
			super(level);
			this.pigType = PigType.HAMPSHIRE;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 5327691;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 13684944;
		}
	
	
	}

	public static class PigEntityletHampshire extends PigEntityletBase
	{
	
		public PigEntityletHampshire(Level level)
		{
			super(level);
			this.pigType = PigType.HAMPSHIRE;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 5327691;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 13684944;
		}
	
	
	}

	public static class EntityHogHampshire extends EntityHogBase
	{
	
		public EntityHogHampshire(Level level)
		{
			super(level);
			this.pigType = PigType.HAMPSHIRE;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 5327691;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 13684944;
		}
	
	}

}
