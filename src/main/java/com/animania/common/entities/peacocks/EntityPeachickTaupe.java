package com.animania.common.entities.peacocks;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityPeachickTaupe extends EntityPeachickBase
{

	public EntityPeachickTaupe(World worldIn)
	{
		super(worldIn);
		this.type = PeacockType.TAUPE;
		this.resourceLocation = new ResourceLocation("animania:textures/entity/peacocks/peachick_taupe.png");
		this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/peacocks/peachick_taupe_blink.png");
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