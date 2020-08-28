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
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.common.Loader;

public class DispenserHandler
{

	public static void init()
	{
		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(Items.WHEAT_SEEDS, SEEDS_DISPENSER_BEHAVIOUR);
		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(Items.PUMPKIN_SEEDS, SEEDS_DISPENSER_BEHAVIOUR);
		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(Items.MELON_SEEDS, SEEDS_DISPENSER_BEHAVIOUR);
		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(Items.BEETROOT_SEEDS, SEEDS_DISPENSER_BEHAVIOUR);

		for (ResourceLocation path : Item.REGISTRY.getKeys())
		{
			Item item = Item.getByNameOrId(path.toString());
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
			EnumFacing enumfacing = source.getBlockState().getValue(BlockDispenser.FACING);
			World world = source.getWorld();
			BlockPos pos = source.getBlockPos().offset(enumfacing);
			BlockPos below = pos.offset(EnumFacing.DOWN);
			Item item = stack.getItem();

			if (world.getBlockState(pos).getBlock() != BlockHandler.blockSeeds && world.getBlockState(below).isFullBlock() && world.getBlockState(below).isOpaqueCube() && !(world.getBlockState(below).getBlock() instanceof BlockFarmland) && !(world.getBlockState(below).getBlock() instanceof IPlantable) && AnimaniaConfig.gameRules.allowSeedDispenserPlacement)
			{
				if (world.getBlockState(pos).getBlock().isReplaceable(world, pos))
				{
					if (item == Items.WHEAT_SEEDS)
						world.setBlockState(pos, BlockHandler.blockSeeds.getDefaultState());
					else if (item == Items.PUMPKIN_SEEDS)
						world.setBlockState(pos, BlockHandler.blockSeeds.getDefaultState().withProperty(BlockSeeds.VARIANT, BlockSeeds.EnumType.PUMPKIN));
					else if (item == Items.MELON_SEEDS)
						world.setBlockState(pos, BlockHandler.blockSeeds.getDefaultState().withProperty(BlockSeeds.VARIANT, BlockSeeds.EnumType.MELON));
					else if (item == Items.BEETROOT_SEEDS)
						world.setBlockState(pos, BlockHandler.blockSeeds.getDefaultState().withProperty(BlockSeeds.VARIANT, BlockSeeds.EnumType.BEETROOT));
					stack.shrink(1);
					return stack;
				}
			} else if ((Loader.isModLoaded("quark") || Loader.isModLoaded("botania")) && world.getBlockState(below).getBlock() instanceof BlockFarmland)
			{
				if (world.getBlockState(pos).getBlock().isReplaceable(world, pos))
				{
					if (item == Items.WHEAT_SEEDS)
						world.setBlockState(pos, Blocks.WHEAT.getDefaultState());
					else if (item == Items.PUMPKIN_SEEDS)
						world.setBlockState(pos, Blocks.PUMPKIN_STEM.getDefaultState());
					else if (item == Items.MELON_SEEDS)
						world.setBlockState(pos, Blocks.MELON_STEM.getDefaultState());
					else if (item == Items.BEETROOT_SEEDS)
						world.setBlockState(pos, Blocks.BEETROOTS.getDefaultState());
					return stack;
				}
			} else if (world.getBlockState(pos).getBlock() == BlockHandler.blockSeeds)
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
			EnumFacing enumfacing = source.getBlockState().getValue(BlockDispenser.FACING);
			World world = source.getWorld();
			BlockPos pos = source.getBlockPos().offset(enumfacing);
			ItemEntityEgg item = (ItemEntityEgg) stack.getItem();

			EntityLivingBase entity = null;

			if (item.gender == EntityGender.RANDOM)
			{
				AnimaniaType[] types = item.type.getClass().getEnumConstants();
				if (types != null)
					entity = EntityGender.getEntity(types[Animania.RANDOM.nextInt(types.length)], item.gender, world);
				else
				{
					double d = Animania.RANDOM.nextDouble();
					if (d <= 0.33)
						entity = item.type.getMale(world);
					else if (d > 0.33 && d <= 0.66)
						entity = item.type.getFemale(world);
					else
						entity = item.type.getChild(world);

					if (entity == null)
						entity = item.type.getMale(world);
				}
			} else
			{
				entity = EntityGender.getEntity(item.type, item.gender, world);
			}
			if (entity != null)
			{
				entity.setPosition(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);

				if (stack.hasDisplayName())
					entity.setCustomNameTag(stack.getDisplayName());

				stack.shrink(1);

				AnimaniaHelper.spawnEntity(world, entity);
			}

			return stack;

		}

	};

}
