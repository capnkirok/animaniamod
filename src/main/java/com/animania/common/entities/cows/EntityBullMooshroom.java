package com.animania.common.entities.cows;

import net.minecraft.init.Items;
import net.minecraft.world.World;

public class EntityBullMooshroom extends EntityBullBase
{

	public EntityBullMooshroom(World world)
	{
		super(world);
		this.cowType = CowType.MOOSHROOM;
		this.dropRaw = Items.BEEF;
		this.dropCooked = Items.COOKED_BEEF;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 12325394;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 12627887;
	}

}