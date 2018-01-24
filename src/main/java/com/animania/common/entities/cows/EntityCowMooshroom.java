package com.animania.common.entities.cows;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityCowMooshroom extends EntityCowBase
{

	public EntityCowMooshroom(World world)
	{
		super(world);
		this.cowType = CowType.MOOSHROOM;
		this.milk = new ItemStack(Items.MUSHROOM_STEW, 1);
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