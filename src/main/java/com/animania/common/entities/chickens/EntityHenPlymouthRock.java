package com.animania.common.entities.chickens;

import com.animania.common.handler.ItemHandler;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityHenPlymouthRock extends EntityHenBase
{

	public EntityHenPlymouthRock(World worldIn)
	{
		super(worldIn);
		this.type = ChickenType.PLYMOUTH_ROCK;
		this.resourceLocation = new ResourceLocation("animania:textures/entity/chickens/hen_specked.png");
		this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/chickens/hen_specked_blink.png");
		this.oldDropRaw = ItemHandler.rawPlymouthRockChicken;
		this.oldDropCooked = ItemHandler.cookedPlymouthRockChicken;
		this.dropRaw = ItemHandler.rawPrimeChicken;
		this.dropCooked = ItemHandler.cookedPrimeChicken;
	}
}