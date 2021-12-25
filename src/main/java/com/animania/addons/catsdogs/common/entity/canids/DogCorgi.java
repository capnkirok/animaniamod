package com.animania.addons.catsdogs.common.entity.canids;

public class DogCorgi
{

	public static class EntityFemaleCorgi extends EntityFemaleDogBase
	{

		public EntityFemaleCorgi(Level level)
		{
			super(level);
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

		public EntityMaleCorgi(Level level)
		{
			super(level);
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

		public EntityPuppyCorgi(Level level)
		{
			super(level);
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
