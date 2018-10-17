package com.animania.common.entities.peacocks;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityPeachickPeach extends EntityPeachickBase
{

	public EntityPeachickPeach(World worldIn)
	{
		super(worldIn);
		this.type = PeacockType.PEACH;
		this.resourceLocation = new ResourceLocation("animania:textures/entity/peacocks/peachick_peach.png");
		this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/peacocks/peachick_blink.png");
		this.lidCol = 0xB49B7F;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 12419159;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 6111535;
	}
}