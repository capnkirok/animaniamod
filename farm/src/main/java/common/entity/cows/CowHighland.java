package common.entity.cows;

import net.minecraft.world.level.Level;

public class CowHighland
{

	public static class EntityBullHighland extends EntityBullBase
	{

		public EntityBullHighland(Level level)
		{
			super(level);
			this.cowType = CowType.HIGHLAND;

		}

		@Override
		public int getPrimaryEggColor()
		{
			return 8340777;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 2760475;
		}

	}

	public static class CowEntityHighland extends CowEntityBase
	{

		public CowEntityHighland(Level level)
		{
			super(level);
			this.cowType = CowType.HIGHLAND;

		}

		@Override
		public int getPrimaryEggColor()
		{
			return 8340777;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 2760475;
		}

	}

	public static class EntityCalfHighland extends EntityCalfBase
	{

		public EntityCalfHighland(Level level)
		{
			super(level);
			this.cowType = CowType.HIGHLAND;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 8340777;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 2760475;
		}

	}

}