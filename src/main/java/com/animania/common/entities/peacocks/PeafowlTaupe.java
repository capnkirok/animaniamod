package com.animania.common.entities.peacocks;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class PeafowlTaupe
{

	public static class EntityPeafowlTaupe extends EntityPeafowlBase
	{
	
		public EntityPeafowlTaupe(World worldIn)
		{
			super(worldIn);
			this.type = PeacockType.TAUPE;
			this.resourceLocation = new ResourceLocation("animania:textures/entity/peacocks/peafowl_taupe.png");
			this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/peacocks/peafowl_taupe_blink.png");
			this.lidCol = 0xA7988E;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 12427148;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 7102038;
		}
	}

	public static class EntityPeacockTaupe extends EntityPeacockBase
	{
	
		public EntityPeacockTaupe(World worldIn)
		{
			super(worldIn);
			this.type = PeacockType.TAUPE;
			this.resourceLocation = new ResourceLocation("animania:textures/entity/peacocks/peacock_taupe.png");
			this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/peacocks/peacock_taupe_blink.png");
			this.lidCol = 0xA7988E;
	
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 12427148;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 7102038;
		}
	}

	public static class EntityPeachickTaupe extends EntityPeachickBase
	{
	
		public EntityPeachickTaupe(World worldIn)
		{
			super(worldIn);
			this.type = PeacockType.TAUPE;
			this.resourceLocation = new ResourceLocation("animania:textures/entity/peacocks/peachick_taupe.png");
			this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/peacocks/peachick_blink.png");
			this.lidCol = 0xA7988E;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 12427148;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 7102038;
		}
	}

}
