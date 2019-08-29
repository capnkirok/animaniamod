package com.animania.addons.catsdogs.common.entity.canids;

import net.minecraft.world.World;

public class DogFox
{

	public static class EntityPuppyFox extends EntityPuppyBase
	{
	
		public EntityPuppyFox(World world)
		{
			super(world);
			this.type = DogType.FOX;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return -5415620;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return -13946603;
		}
	}

	public static class EntityMaleFox extends EntityMaleDogBase
	{
	
		public EntityMaleFox(World world)
		{
			super(world);
			this.type = DogType.FOX;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return -5415620;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return -13946603;
		}
	}

	public static class EntityFemaleFox extends EntityFemaleDogBase
	{
	
		public EntityFemaleFox(World world)
		{
			super(world);
			this.type = DogType.FOX;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return -5415620;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return -13946603;
		}
	}

}
