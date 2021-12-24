package com.animania.addons.catsdogs.common.entity.canids;

public class DogChihuahua
{

	public static class EntityPuppyChihuahua extends EntityPuppyBase
	{

		public EntityPuppyChihuahua(Level level)
		{
			super(level);
			this.type = DogType.CHIHUAHUA;
			this.setSize(0.5f, 0.5f);
		}

		@Override
		public int getPrimaryEggColor()
		{
			return -593428;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return -16382716;
		}

		@Override
		public int getVariantCount()
		{
			return 2;
		}

		@Override
		public int getEyeColorForVariant(int variant)
		{
			switch (variant)
			{
			case 0:
				return 0x8E5C2B;
			default:
				return 0xE5DAD3;
			}
		}
	}

	public static class EntityMaleChihuahua extends EntityMaleDogBase
	{

		public EntityMaleChihuahua(Level level)
		{
			super(level);
			this.type = DogType.CHIHUAHUA;
			this.setSize(0.8f, 0.8f);
		}

		@Override
		public int getPrimaryEggColor()
		{
			return -593428;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return -16382716;
		}

		@Override
		public int getVariantCount()
		{
			return 2;
		}

		@Override
		public int getEyeColorForVariant(int variant)
		{
			switch (variant)
			{
			case 0:
				return 0x8E5C2B;
			default:
				return 0xE5DAD3;
			}
		}
	}

	public static class EntityFemaleChihuahua extends EntityFemaleDogBase
	{

		public EntityFemaleChihuahua(Level level)
		{
			super(level);
			this.type = DogType.CHIHUAHUA;
			this.setSize(0.8f, 0.8f);
		}

		@Override
		public int getPrimaryEggColor()
		{
			return -593428;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return -16382716;
		}

		@Override
		public int getVariantCount()
		{
			return 2;
		}

		@Override
		public int getEyeColorForVariant(int variant)
		{
			switch (variant)
			{
			case 0:
				return 0x8E5C2B;
			default:
				return 0xE5DAD3;
			}
		}
	}

}
