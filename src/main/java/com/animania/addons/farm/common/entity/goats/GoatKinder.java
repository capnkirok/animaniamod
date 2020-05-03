package com.animania.addons.farm.common.entity.goats;

import net.minecraft.world.World;

public class GoatKinder
{

	public static class EntityBuckKinder extends EntityBuckBase
	{

		public EntityBuckKinder(World worldIn)
		{
			super(worldIn);
			this.goatType = GoatType.KINDER;
			this.setSize(1.3F, 1.2F);
			this.width = 1.3F;
			this.height = 1.2F;
			this.width = 1.3F;

		}

		@Override
		public int getPrimaryEggColor()
		{
			return 9263679;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 13811120;
		}

	}

	public static class EntityDoeKinder extends EntityDoeBase
	{

		public EntityDoeKinder(World worldIn)
		{
			super(worldIn);
			this.setSize(1.4F, 1.2F);
			this.width = 1.4F;
			this.height = 1.2F;
			this.goatType = GoatType.KINDER;

		}

		@Override
		public int getPrimaryEggColor()
		{
			return 9263679;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 13811120;
		}

	}

	public static class EntityKidKinder extends EntityKidBase
	{

		public EntityKidKinder(World worldIn)
		{
			super(worldIn);
			this.goatType = GoatType.KINDER;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 9263679;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 13811120;
		}

	}

}
