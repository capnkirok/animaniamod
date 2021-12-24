package com.animania.addons.catsdogs.common.entity.canids;

public class DogPug
{

	public static class EntityPuppyPug extends EntityPuppyBase
	{
	
		public EntityPuppyPug(Level level)
		{
			super(level);
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
	
		public EntityMalePug(Level level)
		{
			super(level);
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
	
		public EntityFemalePug(Level level)
		{
			super(level);
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
