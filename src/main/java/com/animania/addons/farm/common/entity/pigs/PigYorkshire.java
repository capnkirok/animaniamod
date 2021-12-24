package com.animania.addons.farm.common.entity.pigs;

public class PigYorkshire
{

	public static class EntityHogYorkshire extends EntityHogBase
	{
	
		public EntityHogYorkshire(Level level)
		{
			super(level);
			this.pigType = PigType.YORKSHIRE;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 15845576;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 15117998;
		}
	
	}

	public static class PigEntityletYorkshire extends PigEntityletBase
	{
	
		public PigEntityletYorkshire(Level level)
		{
			super(level);
			this.pigType = PigType.YORKSHIRE;
		}
	
		@Override
		public int getPrimaryEggColor()
		{
			return 15845576;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 15117998;
		}
	}

	public static class EntitySowYorkshire extends EntitySowBase
	{
	
		public EntitySowYorkshire(Level level)
		{
			super(level);
			this.pigType = PigType.YORKSHIRE;
		}
	
		@Override
		public int getPrimaryEggColor()
		{
			return 15845576;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 15117998;
		}
	}

}
