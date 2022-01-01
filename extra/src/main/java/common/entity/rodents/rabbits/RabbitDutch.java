package common.entity.rodents.rabbits;

import net.minecraft.world.level.Level;

public class RabbitDutch
{

	public static class RabbitEntityKitDutch extends RabbitEntityKitBase
	{

		public RabbitEntityKitDutch(Level levelIn)
		{
			super(levelIn);
			this.rabbitType = RabbitType.DUTCH;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 0;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 16777215;
		}

	}

	public static class RabbitEntityDoeDutch extends RabbitEntityDoeBase
	{

		public RabbitEntityDoeDutch(Level levelIn)
		{
			super(levelIn);
			this.rabbitType = RabbitType.DUTCH;

		}

		@Override
		public int getPrimaryEggColor()
		{
			return 0;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 16777215;
		}

	}

	public static class RabbitEntityBuckDutch extends RabbitEntityBuckBase
	{

		public RabbitEntityBuckDutch(Level levelIn)
		{
			super(levelIn);
			this.rabbitType = RabbitType.DUTCH;

		}

		@Override
		public int getPrimaryEggColor()
		{
			return 0;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 16777215;
		}
	}

}
