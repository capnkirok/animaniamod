package com.animania.catsdogs.common.entity.canids;

import net.minecraft.world.level.Level;

public class DogHusky
{

	public static class EntityPuppyHusky extends EntityPuppyBase
	{

		public EntityPuppyHusky(Level level)
		{
			super(level);
			this.type = DogType.HUSKY;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return -14606304;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return -1118482;
		}
	}

	public static class EntityMaleHusky extends EntityMaleDogBase
	{

		public EntityMaleHusky(Level level)
		{
			super(level);
			this.type = DogType.HUSKY;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return -14606304;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return -1118482;
		}
	}

	public static class EntityFemaleHusky extends EntityFemaleDogBase
	{

		public EntityFemaleHusky(Level level)
		{
			super(level);
			this.type = DogType.HUSKY;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return -14606304;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return -1118482;
		}
	}

}
