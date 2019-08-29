package com.animania.common.entities.pigs;

import net.minecraft.world.World;

public class PigHampshire
{

	public static class EntitySowHampshire extends EntitySowBase
	{
	
		public EntitySowHampshire(World world)
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

	public static class EntityPigletHampshire extends EntityPigletBase
	{
	
		public EntityPigletHampshire(World world)
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
	
		public EntityHogHampshire(World world)
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
