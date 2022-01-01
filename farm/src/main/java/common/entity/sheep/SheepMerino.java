package common.entity.sheep;

import common.handler.FarmAddonBlockHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public class SheepMerino
{

	public static class EntityEweMerino extends EntityEweBase
	{

		public EntityEweMerino(Level levelIn)
		{
			super(levelIn);
			this.sheepType = SheepType.MERINO;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 15526109;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 11904114;
		}

		@Override
		public List<ItemStack> onSheared(ItemStack item, IBlockAccess level, BlockPos pos, int fortune)
		{

			int i = 1 + this.rand.nextInt(2);

			List<ItemStack> woolDrops = new ArrayList<>();

			switch (this.getColorNumber())
			{
			case 0:
				if (this.getDyeColor() == EnumDyeColor.WHITE)
					woolDrops.add(new ItemStack(FarmAddonBlockHandler.blockAnimaniaWool, i, 5));
				else
					woolDrops.add(new ItemStack(Blocks.WOOL, i, this.getDyeColor().getMetadata()));
				break;
			case 1:
				woolDrops.add(new ItemStack(FarmAddonBlockHandler.blockAnimaniaWool, i, 4));
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

	public static class EntityRamMerino extends EntityRamBase
	{

		public EntityRamMerino(Level levelIn)
		{
			super(levelIn);
			this.sheepType = SheepType.MERINO;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 15526109;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 11904114;
		}

		@Override
		public List<ItemStack> onSheared(ItemStack item, IBlockAccess level, BlockPos pos, int fortune)
		{

			int i = 1 + this.rand.nextInt(2);

			List<ItemStack> woolDrops = new ArrayList<>();

			switch (this.getColorNumber())
			{
			case 0:
				if (this.getDyeColor() == EnumDyeColor.WHITE)
					woolDrops.add(new ItemStack(FarmAddonBlockHandler.blockAnimaniaWool, i, 5));
				else
					woolDrops.add(new ItemStack(Blocks.WOOL, i, this.getDyeColor().getMetadata()));
				break;
			case 1:
				woolDrops.add(new ItemStack(FarmAddonBlockHandler.blockAnimaniaWool, i, 4));
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

	public static class EntityLambMerino extends EntityLambBase
	{

		public EntityLambMerino(Level levelIn)
		{
			super(levelIn);
			this.sheepType = SheepType.MERINO;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 15526109;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 11904114;
		}
	}

}
