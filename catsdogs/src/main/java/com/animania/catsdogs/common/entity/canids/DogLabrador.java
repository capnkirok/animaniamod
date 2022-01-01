package com.animania.catsdogs.common.entity.canids;

import net.minecraft.world.level.Level;

public class DogLabrador
{

	public static class EntityFemaleLabrador extends EntityFemaleDogBase
	{

		public EntityFemaleLabrador(Level level)
		{
			super(level);
			this.type = DogType.LABRADOR;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return -4153993;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return -12506848;
		}

		@Override
		public int getVariantCount()
		{
			return 3;
		}

		@Override
		public int getEyeColorForVariant(int variant)
		{
			switch (variant)
			{
			case 0:
				return 0xAA7A4C;
			case 1:
				return 0x1B1B1B;
			default:
				return 0x39211A;
			}
		}
	}

	public static class EntityPuppyLabrador extends EntityPuppyBase
	{

		public EntityPuppyLabrador(Level level)
		{
			super(level);
			this.type = DogType.LABRADOR;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return -4153993;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return -12506848;
		}

		@Override
		public int getVariantCount()
		{
			return 3;
		}

		@Override
		public int getEyeColorForVariant(int variant)
		{
			switch (variant)
			{
			case 0:
				return 0xAA7A4C;
			case 1:
				return 0x1B1B1B;
			default:
				return 0x39211A;
			}
		}
	}

	public static class EntityMaleLabrador extends EntityMaleDogBase
	{

		public EntityMaleLabrador(Level level)
		{
			super(level);
			this.type = DogType.LABRADOR;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return -4153993;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return -12506848;
		}

		@Override
		public int getVariantCount()
		{
			return 3;
		}

		@Override
		public int getEyeColorForVariant(int variant)
		{
			switch (variant)
			{
			case 0:
				return 0xAA7A4C;
			case 1:
				return 0x1B1B1B;
			default:
				return 0x39211A;
			}
		}
	}

}
