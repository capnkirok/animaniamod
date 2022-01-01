package common.entity.chickens;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

public class ChickenPlymouthRock
{

	public static class EntityChickPlymouthRock extends EntityChickBase
	{

		public EntityChickPlymouthRock(Level levelIn)
		{
			super(levelIn);
			this.type = ChickenType.PLYMOUTH_ROCK;
			this.resourceLocation = new ResourceLocation("animania:textures/entity/chickens/chick_specked.png");
			this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/chickens/chick_blink.png");
			this.lidCol = 0xD2D7E2;

		}

		@Override
		public int getPrimaryEggColor()
		{
			return 13683925;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 9735826;
		}
	}

	public static class EntityRoosterPlymouthRock extends EntityRoosterBase
	{

		public EntityRoosterPlymouthRock(Level levelIn)
		{
			super(levelIn);
			this.type = ChickenType.PLYMOUTH_ROCK;
			this.resourceLocation = new ResourceLocation("animania:textures/entity/chickens/rooster_specked.png");
			this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/chickens/chicken_blink.png");
			this.lidCol = 0xA29497;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 13683925;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 9735826;
		}
	}

	public static class EntityHenPlymouthRock extends EntityHenBase
	{

		public EntityHenPlymouthRock(Level levelIn)
		{
			super(levelIn);
			this.type = ChickenType.PLYMOUTH_ROCK;
			this.resourceLocation = new ResourceLocation("animania:textures/entity/chickens/hen_specked.png");
			this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/chickens/chicken_blink.png");
			this.lidCol = 0xA29497;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 13683925;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 9735826;
		}
	}

}