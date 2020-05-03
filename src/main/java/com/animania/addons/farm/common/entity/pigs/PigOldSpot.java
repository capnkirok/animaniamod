package com.animania.addons.farm.common.entity.pigs;

import net.minecraft.world.World;

public class PigOldSpot
{

	public static class EntityHogOldSpot extends EntityHogBase
	{
	
		public EntityHogOldSpot(World world)
		{
			super(world);
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

	public static class EntityPigletOldSpot extends EntityPigletBase
	{
	
		public EntityPigletOldSpot(World world)
		{
			super(world);
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
	
		public EntitySowOldSpot(World world)
		{
			super(world);
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
