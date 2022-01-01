package common.entity.goats;

import net.minecraft.world.level.Level;

public class GoatKiko
{

	public static class EntityBuckKiko extends EntityBuckBase
	{

		public EntityBuckKiko(Level levelIn)
		{
			super(levelIn);
			this.goatType = GoatType.KIKO;
			this.setSize(1.2F, 1.0F);
			this.width = 1.2F;
			this.height = 1.0F;

		}

		@Override
		public int getPrimaryEggColor()
		{
			return 8802872;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 3549475;
		}

	}

	public static class EntityKidKiko extends EntityKidBase
	{

		public EntityKidKiko(Level levelIn)
		{
			super(levelIn);
			this.goatType = GoatType.KIKO;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 8802872;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 3549475;
		}

	}

	public static class EntityDoeKiko extends EntityDoeBase
	{

		public EntityDoeKiko(Level levelIn)
		{
			super(levelIn);
			this.goatType = GoatType.KIKO;
			this.setSize(1.3F, 1.0F);
			this.width = 1.3F;
			this.height = 1.0F;

		}

		@Override
		public int getPrimaryEggColor()
		{
			return 8802872;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 3549475;
		}

	}

}
