package com.animania.addons.catsdogs.common.entity.canids;

import net.minecraft.world.level.Level;

public class DogGreyhound
{

	public static class EntityPuppyGreyhound extends EntityPuppyBase
	{
	
		public EntityPuppyGreyhound(Level world)
		{
			super(world);
			this.type = DogType.GREYHOUND;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return -7578572;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return -15987708;
		}
	}

	public static class EntityMaleGreyhound extends EntityMaleDogBase
	{
	
		public EntityMaleGreyhound(Level world)
		{
			super(world);
			this.type = DogType.GREYHOUND;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return -7578572;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return -15987708;
		}
	}

	public static class EntityFemaleGreyhound extends EntityFemaleDogBase
	{
	
		public EntityFemaleGreyhound(Level world)
		{
			super(world);
			this.type = DogType.GREYHOUND;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return -7578572;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return -15987708;
		}
	}

}
