package com.animania.common.entities.chickens;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ChickenWyandotte
{

	public static class EntityChickWyandotte extends EntityChickBase
	{

		public EntityChickWyandotte(World worldIn)
		{
			super(worldIn);
			this.type = ChickenType.WYANDOTTE;
			this.resourceLocation = new ResourceLocation("animania:textures/entity/chickens/chick_brown.png");
			this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/chickens/chick_blink.png");
			this.lidCol = 0x492A1E;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 8219743;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 5129532;
		}
	}

	public static class EntityRoosterWyandotte extends EntityRoosterBase
	{

		public EntityRoosterWyandotte(World worldIn)
		{
			super(worldIn);
			this.type = ChickenType.WYANDOTTE;
			this.resourceLocation = new ResourceLocation("animania:textures/entity/chickens/rooster_brown.png");
			this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/chickens/chicken_blink.png");
			this.lidCol = 0x362018;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 8219743;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 5129532;
		}
	}

	public static class EntityHenWyandotte extends EntityHenBase
	{

		public EntityHenWyandotte(World worldIn)
		{
			super(worldIn);
			this.type = ChickenType.WYANDOTTE;
			this.resourceLocation = new ResourceLocation("animania:textures/entity/chickens/hen_brown.png");
			this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/chickens/chicken_blink.png");
			this.lidCol = 0x362018;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 8219743;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 5129532;
		}
	}

}