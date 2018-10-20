package com.animania.common.entities.peacocks;

import com.animania.common.handler.ItemHandler;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityPeacockOpal extends EntityPeacockBase
{

	public EntityPeacockOpal(World worldIn)
	{
		super(worldIn);
		this.type = PeacockType.OPAL;
		this.resourceLocation = new ResourceLocation("animania:textures/entity/peacocks/peacock_opal.png");
		this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/peacocks/peacock_opal_blink.png");
		this.lidCol = 0x495163;
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