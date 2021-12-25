package com.animania.addons.farm.common.entity.goats;

public class GoatNigerianDwarf
{

	public static class EntityBuckNigerianDwarf extends EntityBuckBase
	{

		public EntityBuckNigerianDwarf(Level levelIn)
		{
			super(levelIn);
			this.goatType = GoatType.NIGERIAN_DWARF;
			this.setSize(1.2F, 1.2F);
			this.width = 1.2F;
			this.height = 1.2F;
			this.width = 1.2F;

		}

		@Override
		public int getPrimaryEggColor()
		{
			return 2697513;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 8343350;
		}

	}

	public static class EntityDoeNigerianDwarf extends EntityDoeBase
	{

		public EntityDoeNigerianDwarf(Level levelIn)
		{
			super(levelIn);
			this.goatType = GoatType.NIGERIAN_DWARF;
			this.setSize(1.1F, 1.2F);
			this.width = 1.1F;
			this.height = 1.2F;

		}

		@Override
		public int getPrimaryEggColor()
		{
			return 2697513;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 8343350;
		}

	}

	public static class EntityKidNigerianDwarf extends EntityKidBase
	{

		public EntityKidNigerianDwarf(Level levelIn)
		{
			super(levelIn);
			this.goatType = GoatType.NIGERIAN_DWARF;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 2697513;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 8343350;
		}

	}

}
