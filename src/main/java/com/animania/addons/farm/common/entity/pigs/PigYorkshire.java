package com.animania.addons.farm.common.entity.pigs;

import net.minecraft.world.level.Level;

public class PigYorkshire
{

	public static class EntityHogYorkshire extends EntityHogBase
	{
	
		public EntityHogYorkshire(Level world)
		{
			super(world);
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
	
		public PigEntityletYorkshire(Level world)
		{
			super(world);
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
	
		public EntitySowYorkshire(Level world)
		{
			super(world);
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
