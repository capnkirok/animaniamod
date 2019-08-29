package com.animania.addons.catsdogs.common.entity.canids;

import net.minecraft.world.World;

public class DogCollie
{

	public static class EntityPuppyCollie extends EntityPuppyBase
	{
	
		public EntityPuppyCollie(World world)
		{
			super(world);
			this.type = DogType.COLLIE;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return -12570587;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return -197380;
		}
		
		@Override
		public int getVariantCount()
		{
			return 2;
		}
		
		@Override
		public int getEyeColorForVariant(int variant)
		{
			switch(variant)
			{
			case 0: 
				return 0x433227;
			default:
				return 0x1B1B1B;
			}
		}
	}

	public static class EntityMaleCollie extends EntityMaleDogBase
	{
	
		public EntityMaleCollie(World world)
		{
			super(world);
			this.type = DogType.COLLIE;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return -12570587;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return -197380;
		}
		
		@Override
		public int getVariantCount()
		{
			return 2;
		}
		
		@Override
		public int getEyeColorForVariant(int variant)
		{
			switch(variant)
			{
			case 0: 
				return 0x433227;
			default:
				return 0x1B1B1B;
			}
		}
	}

	public static class EntityFemaleCollie extends EntityFemaleDogBase
	{
	
		public EntityFemaleCollie(World world)
		{
			super(world);
			this.type = DogType.COLLIE;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return -12570587;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return -197380;
		}
		
		@Override
		public int getVariantCount()
		{
			return 2;
		}
		
		@Override
		public int getEyeColorForVariant(int variant)
		{
			switch(variant)
			{
			case 0: 
				return 0x433227;
			default:
				return 0x1B1B1B;
			}
		}
	}

}
