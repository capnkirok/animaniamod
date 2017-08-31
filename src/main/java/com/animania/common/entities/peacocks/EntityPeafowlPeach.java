package com.animania.common.entities.peacocks;

import com.animania.common.handler.ItemHandler;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityPeafowlPeach extends EntityPeafowlBase
{

	public EntityPeafowlPeach(World worldIn)
	{
		super(worldIn);
		this.type = PeacockType.PEACH;
		this.resourceLocation = new ResourceLocation("animania:textures/entity/peacocks/peafowl_peach.png");
		this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/peacocks/peafowl_peach_blink.png");
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