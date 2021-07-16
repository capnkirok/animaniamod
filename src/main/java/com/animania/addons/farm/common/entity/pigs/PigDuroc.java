package com.animania.addons.farm.common.entity.pigs;

import net.minecraft.world.World;

public class PigDuroc
{

	public static class EntitySowDuroc extends EntitySowBase
	{
	
		public EntitySowDuroc(World world)
		{
			super(world);
			this.pigType = PigType.DUROC;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 9399147;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 6896443;
		}
	
	}

	public static class PigEntityletDuroc extends PigEntityletBase
	{
	
		public PigEntityletDuroc(World world)
		{
			super(world);
			this.pigType = PigType.DUROC;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 9399147;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 6896443;
		}
	
	}

	public static class EntityHogDuroc extends EntityHogBase
	{
	
		public EntityHogDuroc(World world)
		{
			super(world);
			this.pigType = PigType.DUROC;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 9399147;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 6896443;
		}
	
	}

}
