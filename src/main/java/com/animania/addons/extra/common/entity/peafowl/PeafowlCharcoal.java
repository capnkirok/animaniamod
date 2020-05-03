package com.animania.addons.extra.common.entity.peafowl;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class PeafowlCharcoal
{

	public static class EntityPeachickCharcoal extends EntityPeachickBase
	{

		public EntityPeachickCharcoal(World worldIn)
		{
			super(worldIn);
			this.type = PeacockType.CHARCOAL;
			this.resourceLocation = new ResourceLocation("animania:textures/entity/peacocks/peachick_charcoal.png");
			this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/peacocks/peachick_blink.png");
			this.lidCol = 0x646464;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 3815994;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 3092271;
		}
	}

	public static class EntityPeafowlCharcoal extends EntityPeafowlBase
	{

		public EntityPeafowlCharcoal(World worldIn)
		{
			super(worldIn);
			this.type = PeacockType.CHARCOAL;
			this.resourceLocation = new ResourceLocation("animania:textures/entity/peacocks/peafowl_charcoal.png");
			this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/peacocks/peafowl_charcoal_blink.png");
			this.lidCol = 0x9E9792;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 3815994;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 3092271;
		}
	}

	public static class EntityPeacockCharcoal extends EntityPeacockBase
	{

		public EntityPeacockCharcoal(World worldIn)
		{
			super(worldIn);
			this.type = PeacockType.CHARCOAL;
			this.resourceLocation = new ResourceLocation("animania:textures/entity/peacocks/peacock_charcoal.png");
			this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/peacocks/peacock_charcoal_blink.png");
			this.lidCol = 0x9E9792;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 3815994;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 3092271;
		}
	}

}