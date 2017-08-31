package com.animania.common.entities.cows;

import net.minecraft.init.Items;
import net.minecraft.world.World;

public class EntityBullFriesian extends EntityBullBase
{

	public EntityBullFriesian(World world)
	{
		super(world);
		this.cowType = CowType.FRIESIAN;
		this.dropRaw = Items.BEEF;
		this.dropCooked = Items.COOKED_BEEF;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 15987699;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 3944229;
	}

}