package com.animania.addons.catsdogs.common.entity.canids;

import net.minecraft.world.World;

public class DogLabrador
{

	public static class EntityFemaleLabrador extends EntityFemaleDogBase
	{
	
		public EntityFemaleLabrador(World world)
		{
			super(world);
			this.type = DogType.LABRADOR;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return -4153993;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return -12506848;
		}
		
		@Override
		public int getVariantCount()
		{
			return 3;
		}
		
		@Override
		public int getEyeColorForVariant(int variant)
		{
			switch(variant)
			{
			case 0: 
				return 0xAA7A4C;
			case 1:
				return 0x1B1B1B;
			default:
				return 0x39211A;
			}
		}
	}

	public static class EntityPuppyLabrador extends EntityPuppyBase
	{
	
		public EntityPuppyLabrador(World world)
		{
			super(world);
			this.type = DogType.LABRADOR;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return -4153993;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return -12506848;
		}
		
		@Override
		public int getVariantCount()
		{
			return 3;
		}
		
		@Override
		public int getEyeColorForVariant(int variant)
		{
			switch(variant)
			{
			case 0: 
				return 0xAA7A4C;
			case 1:
				return 0x1B1B1B;
			default:
				return 0x39211A;
			}
		}
	}

	public static class EntityMaleLabrador extends EntityMaleDogBase
	{
	
		public EntityMaleLabrador(World world)
		{
			super(world);
			this.type = DogType.LABRADOR;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return -4153993;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return -12506848;
		}
		
		@Override
		public int getVariantCount()
		{
			return 3;
		}
		
		@Override
		public int getEyeColorForVariant(int variant)
		{
			switch(variant)
			{
			case 0: 
				return 0xAA7A4C;
			case 1:
				return 0x1B1B1B;
			default:
				return 0x39211A;
			}
		}
	}

}
