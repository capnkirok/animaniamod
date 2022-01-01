package common.entity.sheep;

import common.handler.FarmAddonBlockHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public class SheepDorset
{

	public static class EntityRamDorset extends EntityRamBase
	{

		public EntityRamDorset(Level levelIn)
		{
			super(levelIn);
			this.sheepType = SheepType.DORSET;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 4863280;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 15790320;
		}

		@Override
		public List<ItemStack> onSheared(ItemStack item, IBlockAccess level, BlockPos pos, int fortune)
		{

			int i = 1 + this.rand.nextInt(2);

			List<ItemStack> woolDrops = new ArrayList<>();

			switch (this.getColorNumber())
			{
			case 0:
				woolDrops.add(new ItemStack(Blocks.WOOL, i, this.getDyeColor().getMetadata()));
				break;
			case 1:
				woolDrops.add(new ItemStack(FarmAddonBlockHandler.blockAnimaniaWool, i, 0));
				break;
			}

			this.setSheared(true);

			return woolDrops;
		}

		@Override
		public boolean isDyeable()
		{
			switch (this.getColorNumber())
			{
			case 0:
				return true;
			default:
				return false;
			}
		}

	}

	public static class EntityLambDorset extends EntityLambBase
	{

		public EntityLambDorset(Level levelIn)
		{
			super(levelIn);
			this.sheepType = SheepType.DORSET;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 4863280;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 15790320;
		}
	}

	public static class EntityEweDorset extends EntityEweBase
	{

		public EntityEweDorset(Level levelIn)
		{
			super(levelIn);
			this.sheepType = SheepType.DORSET;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 4863280;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 15790320;
		}

		@Override
		public List<ItemStack> onSheared(ItemStack item, IBlockAccess level, BlockPos pos, int fortune)
		{

			int i = 1 + this.rand.nextInt(2);

			List<ItemStack> woolDrops = new ArrayList<>();

			switch (this.getColorNumber())
			{
			case 0:
				woolDrops.add(new ItemStack(Blocks.WOOL, i, this.getDyeColor().getMetadata()));
				break;
			case 1:
				woolDrops.add(new ItemStack(FarmAddonBlockHandler.blockAnimaniaWool, i, 0));
				break;
			}

			this.setSheared(true);

			return woolDrops;
		}

		@Override
		public boolean isDyeable()
		{
			switch (this.getColorNumber())
			{
			case 0:
				return true;
			default:
				return false;
			}
		}
	}

}
