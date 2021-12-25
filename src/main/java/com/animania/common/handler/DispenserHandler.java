package com.animania.common.handler;

import com.animania.Animania;
import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.AnimaniaType;
import com.animania.common.blocks.BlockSeeds;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.items.ItemEntityEgg;
import com.animania.config.AnimaniaConfig;

import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockFarmland;
import net.minecraft.core.BlockPos;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.ModList;

public class DispenserHandler
{

	public static void init()
	{
		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(Items.WHEAT_SEEDS, SEEDS_DISPENSER_BEHAVIOUR);
		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(Items.PUMPKIN_SEEDS, SEEDS_DISPENSER_BEHAVIOUR);
		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(Items.MELON_SEEDS, SEEDS_DISPENSER_BEHAVIOUR);
		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(Items.BEETROOT_SEEDS, SEEDS_DISPENSER_BEHAVIOUR);

		for (ResourceLocation path : RItem.REGISTRY.getKeys())
		{
			RItem item = RItem.getByNameOrId(path.toString());
			if (item instanceof ItemEntityEgg)
			{
				BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(item, SPAWNEGG_DISPENSER_BEHAVIOUR);
			}
		}
	}

	public static final IBehaviorDispenseItem SEEDS_DISPENSER_BEHAVIOUR = new BehaviorDefaultDispenseItem() {
		private final BehaviorDefaultDispenseItem behaviourDefaultDispenseItem = new BehaviorDefaultDispenseItem();

		@Override
		public ItemStack dispenseStack(IBlockSource source, ItemStack stack)
		{
			Direction enumfacing = source.getBlockState().getValue(BlockDispenser.FACING);
			Level level = source.getLevel();
			BlockPos pos = source.getBlockPos().offset(enumfacing);
			BlockPos below = pos.offset(Direction.DOWN);
			RItem item = stack.getItem();

			if (level.getBlockState(pos).getBlock() != BlockHandler.blockSeeds && level.getBlockState(below).isFullBlock() && level.getBlockState(below).isOpaqueCube() && !(level.getBlockState(below).getBlock() instanceof BlockFarmland) && !(level.getBlockState(below).getBlock() instanceof IPlantable) && AnimaniaConfig.gameRules.allowSeedDispenserPlacement)
			{
				if (level.getBlockState(pos).getBlock().isReplaceable(level, pos))
				{
					if (item == Items.WHEAT_SEEDS)
						level.setBlock(pos, BlockHandler.blockSeeds.defaultBlockState());
					else if (item == Items.PUMPKIN_SEEDS)
						level.setBlock(pos, BlockHandler.blockSeeds.defaultBlockState().withProperty(BlockSeeds.VARIANT, BlockSeeds.EnumType.PUMPKIN));
					else if (item == Items.MELON_SEEDS)
						level.setBlock(pos, BlockHandler.blockSeeds.defaultBlockState().withProperty(BlockSeeds.VARIANT, BlockSeeds.EnumType.MELON));
					else if (item == Items.BEETROOT_SEEDS)
						level.setBlock(pos, BlockHandler.blockSeeds.defaultBlockState().withProperty(BlockSeeds.VARIANT, BlockSeeds.EnumType.BEETROOT));
					stack.shrink(1);
					return stack;
				}
			}
			else if ((ModList.get().isLoaded("quark") || ModList.get().isLoaded("botania")) && level.getBlockState(below).getBlock() instanceof BlockFarmland)
			{
				if (level.getBlockState(pos).getBlock().isReplaceable(level, pos))
				{
					if (item == Items.WHEAT_SEEDS)
						level.setBlock(pos, Blocks.WHEAT.defaultBlockState());
					else if (item == Items.PUMPKIN_SEEDS)
						level.setBlock(pos, Blocks.PUMPKIN_STEM.defaultBlockState());
					else if (item == Items.MELON_SEEDS)
						level.setBlock(pos, Blocks.MELON_STEM.defaultBlockState());
					else if (item == Items.BEETROOT_SEEDS)
						level.setBlock(pos, Blocks.BEETROOTS.defaultBlockState());
					return stack;
				}
			}
			else if (level.getBlockState(pos).getBlock() == BlockHandler.blockSeeds)
			{
				return stack;
			}

			return super.dispenseStack(source, stack);

		}

	};

	public static final IBehaviorDispenseItem SPAWNEGG_DISPENSER_BEHAVIOUR = new BehaviorDefaultDispenseItem() {
		private final BehaviorDefaultDispenseItem behaviourDefaultDispenseItem = new BehaviorDefaultDispenseItem();

		@Override
		public ItemStack dispenseStack(IBlockSource source, ItemStack stack)
		{
			Direction enumfacing = source.getBlockState().getValue(BlockDispenser.FACING);
			Level level = source.getLevel();
			BlockPos pos = source.getBlockPos().offset(enumfacing);
			ItemEntityEgg item = (ItemEntityEgg) stack.getItem();

			LivingEntity entity = null;

			if (item.gender == EntityGender.RANDOM)
			{
				AnimaniaType[] types = item.type.getClass().getEnumConstants();
				if (types != null)
					entity = EntityGender.getEntity(types[Animania.RANDOM.nextInt(types.length)], item.gender, level);
				else
				{
					double d = Animania.RANDOM.nextDouble();
					if (d <= 0.33)
						entity = item.type.getMale(level);
					else if (d > 0.33 && d <= 0.66)
						entity = item.type.getFemale(level);
					else
						entity = item.type.getChild(level);

					if (entity == null)
						entity = item.type.getMale(level);
				}
			}
			else
			{
				entity = EntityGender.getEntity(item.type, item.gender, level);
			}
			if (entity != null)
			{
				entity.setPosition(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);

				if (stack.hasDisplayName())
					entity.setCustomNameTag(stack.getDisplayName());

				stack.shrink(1);

				AnimaniaHelper.spawnEntity(level, entity);
			}

			return stack;

		}

	};

}
