package com.animania.common.entities.peacocks;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityPeachickOpal extends EntityPeachickBase
{

	public EntityPeachickOpal(World worldIn)
	{
		super(worldIn);
		this.type = PeacockType.OPAL;
		this.resourceLocation = new ResourceLocation("animania:textures/entity/peacocks/peachick_opal.png");
		this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/peacocks/peachick_blink.png");
		this.lidCol = 0xA1A1A1;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 5265772;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 7174504;
	}
}