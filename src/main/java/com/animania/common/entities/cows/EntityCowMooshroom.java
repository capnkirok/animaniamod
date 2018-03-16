package com.animania.common.entities.cows;

import com.animania.common.helper.AnimaniaHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class EntityCowMooshroom extends EntityCowBase
{

	public EntityCowMooshroom(World world)
	{
		super(world);
		this.cowType = CowType.MOOSHROOM;
		this.dropRaw = Items.BEEF;
		this.dropCooked = Items.COOKED_BEEF;
	}
	
	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand)
	{
		ItemStack stack = player.getHeldItem(hand);

		if (this.getFed() && this.getWatered() && stack != ItemStack.EMPTY && AnimaniaHelper.isEmptyFluidContainer(stack) && this.getHasKids())
		{
			return true;
		}
		if (this.getFed() && this.getWatered() && stack != ItemStack.EMPTY && stack.getItem() == Items.BOWL && this.getHasKids())
		{
			if(!player.isCreative())
			{
				stack.shrink(1);
			}
			AnimaniaHelper.addItem(player, new ItemStack(Items.MUSHROOM_STEW));

			this.setWatered(false);
			return true;
		}
		else
			return super.processInteract(player, hand);
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