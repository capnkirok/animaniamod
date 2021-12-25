package com.animania.addons.extra.common.entity.rodents.rabbits;

public class RabbitLop
{

	public static class RabbitEntityKitLop extends RabbitEntityKitBase
	{

		public RabbitEntityKitLop(Level levelIn)
		{
			super(levelIn);
			this.rabbitType = RabbitType.LOP;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 16513763;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 12883817;
		}

	}

	public static class RabbitEntityDoeLop extends RabbitEntityDoeBase
	{

		public RabbitEntityDoeLop(Level levelIn)
		{
			super(levelIn);
			this.rabbitType = RabbitType.LOP;

		}

		@Override
		public int getPrimaryEggColor()
		{
			return 16513763;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 12883817;
		}

	}

	public static class RabbitEntityBuckLop extends RabbitEntityBuckBase
	{

		public RabbitEntityBuckLop(Level levelIn)
		{
			super(levelIn);
			this.rabbitType = RabbitType.LOP;

		}

		@Override
		public int getPrimaryEggColor()
		{
			return 16513763;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 12883817;
		}
	}

}
