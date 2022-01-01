package common.entity.cows;

import com.animania.Animania;
import com.animania.common.helper.AnimaniaHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CowMooshroom
{

	public static class EntityBullMooshroom extends EntityBullBase
	{

		public EntityBullMooshroom(Level level)
		{
			super(level);
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

		public CowEntityMooshroom(Level level)
		{
			super(level);
			this.cowType = CowType.MOOSHROOM;
		}

		@Override
		public boolean processInteract(Player player, InteractionHand hand)
		{
			ItemStack stack = player.getItemInHand(hand);

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

		public EntityCalfMooshroom(Level level)
		{
			super(level);
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
