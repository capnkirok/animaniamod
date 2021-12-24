package com.animania.addons.farm.common.entity.pigs;

import net.minecraft.world.level.Level;

public class PigHampshire
{

	public static class EntitySowHampshire extends EntitySowBase
	{
	
		public EntitySowHampshire(Level world)
		{
			super(world);
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
	
		public PigEntityletHampshire(Level world)
		{
			super(world);
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
	
		public EntityHogHampshire(Level world)
		{
			super(world);
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
