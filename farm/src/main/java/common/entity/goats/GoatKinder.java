package common.entity.goats;

import net.minecraft.world.level.Level;

public class GoatKinder
{

	public static class EntityBuckKinder extends EntityBuckBase
	{

		public EntityBuckKinder(Level levelIn)
		{
			super(levelIn);
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

		public EntityDoeKinder(Level levelIn)
		{
			super(levelIn);
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

		public EntityKidKinder(Level levelIn)
		{
			super(levelIn);
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
