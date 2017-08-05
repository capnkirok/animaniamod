package com.animania.common.entities.pigs;

import com.animania.common.handler.ItemHandler;

import net.minecraft.init.Items;
import net.minecraft.world.World;

public class EntitySowYorkshire extends EntitySowBase
{

	public EntitySowYorkshire(World world)
	{
		super(world);
		this.pigType = PigType.YORKSHIRE;
		this.dropRaw = Items.PORKCHOP;
		this.dropCooked = Items.COOKED_PORKCHOP;
	}

}