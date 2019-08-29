package com.animania.addons.catsdogs.common.entity.canids;

import net.minecraft.world.World;

public class DogGreyhound
{

	public static class EntityPuppyGreyhound extends EntityPuppyBase
	{
	
		public EntityPuppyGreyhound(World world)
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
	
		public EntityMaleGreyhound(World world)
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
	
		public EntityFemaleGreyhound(World world)
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
