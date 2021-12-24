package com.animania.addons.farm.common.entity.goats;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.level.Level;

public class GoatFainting
{

	public static class EntityBuckFainting extends EntityBuckBase
	{

		public EntityBuckFainting(Level worldIn)
		{
			super(worldIn);
			this.goatType = GoatType.FAINTING;
			this.setSize(1.1F, 1.0F);
			this.width = 1.1F;
			this.height = 1.0F;
			this.width = 1.1F;

		}

		@Override
		public int getPrimaryEggColor()
		{
			return 1250067;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 14803425;
		}

		@Override
		protected void collideWithEntity(Entity entityIn)
		{

			if (entityIn instanceof PlayerEntity)
			{
				PlayerEntity player = (PlayerEntity) entityIn;
				if (player.isSprinting())
				{
					this.setSpooked(true);
					this.setSpookedTimer(1.0F);

				}
				entityIn.applyEntityCollision(this);
			}
		}

	}

	public static class EntityKidFainting extends EntityKidBase
	{

		public EntityKidFainting(World worldIn)
		{
			super(worldIn);
			this.goatType = GoatType.FAINTING;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 1250067;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 14803425;
		}

		@Override
		protected void collideWithEntity(Entity entityIn)
		{

			if (entityIn instanceof PlayerEntity)
			{
				PlayerEntity player = (PlayerEntity) entityIn;
				if (player.isSprinting())
				{
					this.setSpooked(true);
					this.setSpookedTimer(1.0F);

				}
				entityIn.applyEntityCollision(this);
			}
		}

	}

	public static class EntityDoeFainting extends EntityDoeBase
	{

		public EntityDoeFainting(World worldIn)
		{
			super(worldIn);
			this.setSize(1.1F, 1.0F);
			this.width = 1.1F;
			this.height = 1.0F;
			this.goatType = GoatType.FAINTING;

		}

		@Override
		public int getPrimaryEggColor()
		{
			return 1250067;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 14803425;
		}

		@Override
		protected void collideWithEntity(Entity entityIn)
		{

			if (entityIn instanceof PlayerEntity)
			{
				PlayerEntity player = (PlayerEntity) entityIn;
				if (player.isSprinting())
				{
					this.setSpooked(true);
					this.setSpookedTimer(1.0F);

				}
				entityIn.applyEntityCollision(this);
			}
		}

	}

}
