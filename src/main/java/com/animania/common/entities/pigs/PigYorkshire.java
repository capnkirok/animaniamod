package com.animania.common.entities.pigs;

import net.minecraft.world.World;

public class PigYorkshire
{

	public static class EntityHogYorkshire extends EntityHogBase
	{
	
		public EntityHogYorkshire(World world)
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

	public static class EntityPigletYorkshire extends EntityPigletBase
	{
	
		public EntityPigletYorkshire(World world)
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
	
		public EntitySowYorkshire(World world)
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
