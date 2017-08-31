package com.animania.common.entities.peacocks;

import com.animania.common.handler.ItemHandler;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityPeafowlOpal extends EntityPeafowlBase
{

	public EntityPeafowlOpal(World worldIn)
	{
		super(worldIn);
		this.type = PeacockType.OPAL;
		this.resourceLocation = new ResourceLocation("animania:textures/entity/peacocks/peafowl_opal.png");
		this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/peacocks/peafowl_opal_blink.png");
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