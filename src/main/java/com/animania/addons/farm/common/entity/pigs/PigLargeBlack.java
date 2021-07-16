package com.animania.addons.farm.common.entity.pigs;

import net.minecraft.world.World;

public class PigLargeBlack
{

	public static class EntitySowLargeBlack extends EntitySowBase
	{
	
		public EntitySowLargeBlack(World world)
		{
			super(world);
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
	
		public PigEntityletLargeBlack(World world)
		{
			super(world);
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
	
		public EntityHogLargeBlack(World world)
		{
			super(world);
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
