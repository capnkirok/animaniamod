package com.animania.addons.farm.common.entity.cows;

import com.animania.Animania;
import com.animania.common.helper.AnimaniaHelper;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class CowMooshroom
{

	public static class EntityBullMooshroom extends EntityBullBase
	{

		public EntityBullMooshroom(World world)
		{
			super(world);
			this.cowType = CowType.MOOSHROOM;
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

		@Override
		protected ResourceLocation getLootTable()
		{
			return new ResourceLocation(Animania.MODID, "cow_mooshroom");
		}

	}

	public static class CowEntityMooshroom extends CowEntityBase
	{

		public CowEntityMooshroom(World world)
		{
			super(world);
			this.cowType = CowType.MOOSHROOM;
		}

		@Override
		public boolean processInteract(PlayerEntity player, EnumHand hand)
		{
			ItemStack stack = player.getHeldItem(hand);

			if (this.getFed() && this.getWatered() && stack != ItemStack.EMPTY && AnimaniaHelper.isEmptyFluidContainer(stack) && this.getHasKids())
			{
				return true;
			}
			if (this.getFed() && this.getWatered() && stack != ItemStack.EMPTY && stack.getItem() == Items.BOWL && this.getHasKids())
			{
				if (!player.isCreative())
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

		@Override
		protected ResourceLocation getLootTable()
		{
			return new ResourceLocation(Animania.MODID, "cow_mooshroom");
		}

	}

	public static class EntityCalfMooshroom extends EntityCalfBase
	{

		public EntityCalfMooshroom(World world)
		{
			super(world);
			this.cowType = CowType.MOOSHROOM;
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

}
