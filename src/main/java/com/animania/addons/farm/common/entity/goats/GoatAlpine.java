package com.animania.addons.farm.common.entity.goats;

import net.minecraft.world.World;

public class GoatAlpine
{

	public static class EntityBuckAlpine extends EntityBuckBase
	{

		public EntityBuckAlpine(World worldIn)
		{
			super(worldIn);
			this.goatType = GoatType.ALPINE;
			this.setSize(1.6F, 1.4F);
			this.width = 1.6F;
			this.height = 1.4F;
			this.width = 1.6F;

		}

		@Override
		public int getPrimaryEggColor()
		{
			return 14867928;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 8281676;
		}

	}

	public static class EntityKidAlpine extends EntityKidBase
	{

		public EntityKidAlpine(World worldIn)
		{
			super(worldIn);
			this.goatType = GoatType.ALPINE;
			this.setSize(1.0F, 1.0F);
			this.width = 1.0F;
			this.height = 1.0F;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 14867928;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 8281676;
		}

	}

	public static class EntityDoeAlpine extends EntityDoeBase
	{

		public EntityDoeAlpine(World worldIn)
		{
			super(worldIn);
			this.goatType = GoatType.ALPINE;
			this.setSize(1.6F, 1.3F);
			this.width = 1.6F;
			this.height = 1.3F;

		}

		@Override
		public int getPrimaryEggColor()
		{
			return 14867928;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 8281676;
		}

	}

}