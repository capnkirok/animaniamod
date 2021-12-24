package com.animania.addons.farm.common.entity.pigs;

import net.minecraft.world.level.Level;

public class PigDuroc
{

	public static class EntitySowDuroc extends EntitySowBase
	{
	
		public EntitySowDuroc(Level world)
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
	
		public PigEntityletDuroc(Level world)
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
	
		public EntityHogDuroc(Level world)
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
