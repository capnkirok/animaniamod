package com.animania.common.entities.chickens;

import com.animania.common.handler.ItemHandler;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityRoosterPlymouthRock extends EntityRoosterBase
{

	public EntityRoosterPlymouthRock(World worldIn)
	{
		super(worldIn);
		this.type = ChickenType.PLYMOUTH_ROCK;
		this.resourceLocation = new ResourceLocation("animania:textures/entity/chickens/rooster_specked.png");
		this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/chickens/rooster_specked_blink.png");
		this.oldDropRaw = ItemHandler.rawPlymouthRockChicken;
		this.oldDropCooked = ItemHandler.cookedPlymouthRockChicken;
		this.dropRaw = ItemHandler.rawPrimeChicken;
		this.dropCooked = ItemHandler.cookedPrimeChicken;
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