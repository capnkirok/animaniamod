package com.animania.addons.catsdogs.common.entity.canids;

import net.minecraft.world.level.Level;

public class DogCorgi
{

	public static class EntityFemaleCorgi extends EntityFemaleDogBase
	{
	
		public EntityFemaleCorgi(Level world)
		{
			super(world);
			this.type = DogType.CORGI;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return -263173;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return -2987202;
		}
	}

	public static class EntityMaleCorgi extends EntityMaleDogBase
	{
	
		public EntityMaleCorgi(Level world)
		{
			super(world);
			this.type = DogType.CORGI;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return -263173;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return -2987202;
		}
	}

	public static class EntityPuppyCorgi extends EntityPuppyBase
	{
	
		public EntityPuppyCorgi(Level world)
		{
			super(world);
			this.type = DogType.CORGI;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return -263173;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return -2987202;
		}
	}

}
