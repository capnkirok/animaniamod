package com.animania.common.entities.chickens;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityChickOrpington extends EntityChickBase
{

	public EntityChickOrpington(World worldIn)
	{
		super(worldIn);
		this.type = ChickenType.ORPINGTON;
		this.resourceLocation = new ResourceLocation("animania:textures/entity/chickens/chick_golden.png");
		this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/chickens/chick_golden_blink.png");
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 15980429;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 13270563;
	}
}