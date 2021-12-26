package com.animania.addons.catsdogs.common.entity.canids;

import net.minecraft.world.level.Level;

public class DogPomeranian
{

	public static class EntityFemalePomeranian extends EntityFemaleDogBase
	{

		public EntityFemalePomeranian(Level level)
		{
			super(level);
			this.type = DogType.POMERANIAN;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return -197380;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return -13884380;
		}
	}

	public static class EntityMalePomeranian extends EntityMaleDogBase
	{

		public EntityMalePomeranian(Level level)
		{
			super(level);
			this.type = DogType.POMERANIAN;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return -197380;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return -13884380;
		}
	}

	public static class EntityPuppyPomeranian extends EntityPuppyBase
	{

		public EntityPuppyPomeranian(Level level)
		{
			super(level);
			this.type = DogType.POMERANIAN;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return -197380;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return -13884380;
		}
	}

}
