package com.animania.addons.catsdogs.common.entity.canids;

import net.minecraft.world.World;

public class DogGreatDane
{

	public static class EntityPuppyGreatDane extends EntityPuppyBase
	{
	
		public EntityPuppyGreatDane(World world)
		{
			super(world);
			this.type = DogType.GREAT_DANE;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return -8300224;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return -14412785;
		}
	}

	public static class EntityMaleGreatDane extends EntityMaleDogBase
	{
	
		public EntityMaleGreatDane(World world)
		{
			super(world);
			this.type = DogType.GREAT_DANE;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return -8300224;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return -14412785;
		}
	}

	public static class EntityFemaleGreatDane extends EntityFemaleDogBase
	{
	
		public EntityFemaleGreatDane(World world)
		{
			super(world);
			this.type = DogType.GREAT_DANE;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return -8300224;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return -14412785;
		}
	}

}
