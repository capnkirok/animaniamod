package common.entity.sheep;

import common.handler.FarmAddonBlockHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public class SheepFriesian
{

	public static class EntityRamFriesian extends EntityRamBase
	{

		public EntityRamFriesian(Level levelIn)
		{
			super(levelIn);
			this.sheepType = SheepType.FRIESIAN;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 2039583;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 4013373;
		}

		@Override
		public List<ItemStack> onSheared(ItemStack item, IBlockAccess level, BlockPos pos, int fortune)
		{

			int i = 1 + this.rand.nextInt(2);

			List<ItemStack> woolDrops = new ArrayList<>();

			switch (this.getColorNumber())
			{
			case 0:
				woolDrops.add(new ItemStack(FarmAddonBlockHandler.blockAnimaniaWool, i, 1));
				break;
			case 1:
				woolDrops.add(new ItemStack(Blocks.WOOL, i, this.getDyeColor().getMetadata()));
				break;
			case 2:
				woolDrops.add(new ItemStack(FarmAddonBlockHandler.blockAnimaniaWool, i, 2));
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
			case 1:
				return true;
			default:
				return false;
			}
		}

	}

	public static class EntityLambFriesian extends EntityLambBase
	{

		public EntityLambFriesian(Level levelIn)
		{
			super(levelIn);
			this.sheepType = SheepType.FRIESIAN;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 2039583;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 4013373;
		}

	}

	public static class EntityEweFriesian extends EntityEweBase
	{

		public EntityEweFriesian(Level levelIn)
		{
			super(levelIn);
			this.sheepType = SheepType.FRIESIAN;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 2039583;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 4013373;
		}

		@Override
		public List<ItemStack> onSheared(ItemStack item, IBlockAccess level, BlockPos pos, int fortune)
		{

			int i = 1 + this.rand.nextInt(2);

			List<ItemStack> woolDrops = new ArrayList<>();

			switch (this.getColorNumber())
			{
			case 0:
				woolDrops.add(new ItemStack(FarmAddonBlockHandler.blockAnimaniaWool, i, 1));
				break;
			case 1:
				woolDrops.add(new ItemStack(Blocks.WOOL, i, this.getDyeColor().getMetadata()));
				break;
			case 2:
				woolDrops.add(new ItemStack(FarmAddonBlockHandler.blockAnimaniaWool, i, 2));
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
			case 1:
				return true;
			default:
				return false;
			}
		}

	}

}
