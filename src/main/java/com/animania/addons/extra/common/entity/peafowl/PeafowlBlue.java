package com.animania.addons.extra.common.entity.peafowl;

import net.minecraft.resources.ResourceLocation;

public class PeafowlBlue
{

	public static class EntityPeachickBlue extends EntityPeachickBase
	{

		public EntityPeachickBlue(Level levelIn)
		{
			super(levelIn);
			this.type = PeacockType.BLUE;
			this.resourceLocation = new ResourceLocation("animania:textures/entity/peacocks/peachick_blue.png");
			this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/peacocks/peachick_blink.png");
			this.lidCol = 0x6F5B2D;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 2446225;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 4361491;
		}
	}

	public static class EntityPeafowlBlue extends EntityPeafowlBase
	{

		public EntityPeafowlBlue(Level levelIn)
		{
			super(levelIn);
			this.type = PeacockType.BLUE;
			this.resourceLocation = new ResourceLocation("animania:textures/entity/peacocks/peafowl_blue.png");
			this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/peacocks/peafowl_blue_blink.png");
			this.lidCol = 0x846F75;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 2446225;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 4361491;
		}
	}

	public static class EntityPeacockBlue extends EntityPeacockBase
	{

		public EntityPeacockBlue(Level levelIn)
		{
			super(levelIn);
			this.type = PeacockType.BLUE;
			this.resourceLocation = new ResourceLocation("animania:textures/entity/peacocks/peacock_blue.png");
			this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/peacocks/peacock_blue_blink.png");
			this.lidCol = 0x8E8670;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 2446225;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 4361491;
		}
	}

}