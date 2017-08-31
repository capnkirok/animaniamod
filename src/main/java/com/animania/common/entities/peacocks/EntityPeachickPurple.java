package com.animania.common.entities.peacocks;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityPeachickPurple extends EntityPeachickBase
{

	public EntityPeachickPurple(World worldIn)
	{
		super(worldIn);
		this.type = PeacockType.PURPLE;
		this.resourceLocation = new ResourceLocation("animania:textures/entity/peacocks/peachick_purple.png");
		this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/peacocks/peachick_purple_blink.png");
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 2373476;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 3569227;
	}
}