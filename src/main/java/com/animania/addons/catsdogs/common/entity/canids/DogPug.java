package com.animania.addons.catsdogs.common.entity.canids;

import net.minecraft.world.World;

public class DogPug
{

	public static class EntityPuppyPug extends EntityPuppyBase
	{
	
		public EntityPuppyPug(World world)
		{
			super(world);
			this.type = DogType.PUG;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return -1514529;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return -13026238;
		}
	}

	public static class EntityMalePug extends EntityMaleDogBase
	{
	
		public EntityMalePug(World world)
		{
			super(world);
			this.type = DogType.PUG;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return -1514529;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return -13026238;
		}
	}

	public static class EntityFemalePug extends EntityFemaleDogBase
	{
	
		public EntityFemalePug(World world)
		{
			super(world);
			this.type = DogType.PUG;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return -1514529;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return -13026238;
		}
	}

}
