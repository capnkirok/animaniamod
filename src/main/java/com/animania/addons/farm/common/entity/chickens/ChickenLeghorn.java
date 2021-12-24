package com.animania.addons.farm.common.entity.chickens;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

public class ChickenLeghorn
{
	public static class EntityChickLeghorn extends EntityChickBase
	{

		public EntityChickLeghorn(Level worldIn)
		{
			super(worldIn);
			this.type = ChickenType.LEGHORN;
			this.resourceLocation = new ResourceLocation("animania:textures/entity/chickens/chick_white.png");
			this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/chickens/chick_blink.png");
			this.lidCol = 0xFACA65;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 15724527;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 14869218;
		}
	}

	public static class EntityRoosterLeghorn extends EntityRoosterBase
	{

		public EntityRoosterLeghorn(Level worldIn)
		{
			super(worldIn);
			this.type = ChickenType.LEGHORN;
			this.resourceLocation = new ResourceLocation("animania:textures/entity/chickens/rooster_white.png");
			this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/chickens/chicken_blink.png");
			this.lidCol = 0xF2F2F2;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 15724527;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 14869218;
		}
	}

	public static class EntityHenLeghorn extends EntityHenBase
	{

		public EntityHenLeghorn(Level worldIn)
		{
			super(worldIn);
			this.type = ChickenType.LEGHORN;
			this.resourceLocation = new ResourceLocation("animania:textures/entity/chickens/hen_white.png");
			this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/chickens/chicken_blink.png");
			this.lidCol = 0xF2F2F2;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 15724527;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 14869218;
		}
	}
}