package com.animania.common.entities.chickens;

import java.util.Random;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityChickPlymouthRock extends EntityChickBase
{

	public EntityChickPlymouthRock(World worldIn)
	{
		super(worldIn);
		this.type = ChickenType.PLYMOUTH_ROCK;
		this.resourceLocation = new ResourceLocation("animania:textures/entity/chickens/chick_specked.png");
		this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/chickens/chick_specked_blink.png");

	}
}