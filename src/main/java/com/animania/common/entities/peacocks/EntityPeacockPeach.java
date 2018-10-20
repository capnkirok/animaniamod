package com.animania.common.entities.peacocks;

import com.animania.common.handler.ItemHandler;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityPeacockPeach extends EntityPeacockBase
{

	public EntityPeacockPeach(World worldIn)
	{
		super(worldIn);
		this.type = PeacockType.PEACH;
		this.resourceLocation = new ResourceLocation("animania:textures/entity/peacocks/peacock_peach.png");
		this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/peacocks/peacock_peach_blink.png");
		this.lidCol = 0x7F5A41;
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