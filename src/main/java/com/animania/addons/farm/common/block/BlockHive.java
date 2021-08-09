package com.animania.addons.farm.common.block;

import com.animania.Animania;
import com.animania.addons.farm.common.handler.FarmAddonItemHandler;
import com.animania.addons.farm.common.tileentity.TileEntityHive;
import com.animania.common.handler.BlockHandler;

import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockHive extends ContainerBlock
{
	private String name = "block_hive";
	public static final PropertyDirection FACING = BlockDirectional.FACING;

	public BlockHive()
	{
		super(Material.WOOD, MaterialColor.YELLOW);
		this.setSoundType(SoundType.WOOD);
		this.setRegistryName(new ResourceLocation(Animania.MODID, this.name));
		this.setUnlocalizedName(Animania.MODID + "_" + this.name);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		this.setCreativeTab(Animania.TabAnimaniaResources);
		this.setHardness(1.3f);
		this.setResistance(0.3f);
		BlockHandler.blocks.add(this);
		Item item = new BlockItem(this);
		item.setRegistryName(new ResourceLocation(Animania.MODID, "bee_hive"));
		ForgeRegistries.ITEMS.register(item);
	}

	public BlockHive(Material mat, MaterialColor color)
	{
		super(mat, color);
	}

	@Override
	public boolean isOpaqueCube(BlockState state)
	{
		return false;
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess p_193383_1_, BlockState p_193383_2_, BlockPos p_193383_3_, EnumFacing p_193383_4_)
	{
		return BlockFaceShape.UNDEFINED;
	}

	@Override
	public boolean isFullCube(BlockState state)
	{
		return false;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, BlockState state, PlayerEntity player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{

		ItemStack heldItem = player.getHeldItem(hand);
		TileEntityHive hive = (TileEntityHive) world.getTileEntity(pos);

		if (hive != null)
		{
			// EMPTY LIQUIDS
			if (!heldItem.isEmpty() && FluidUtil.getFluidHandler(heldItem) != null && FluidUtil.getFluidContained(heldItem) == null && hive.fluidHandler.getFluid() != null && hive.fluidHandler.getFluid().amount >= 1000)
			{
				FluidStack fluidStack = hive.fluidHandler.drain(1000, true);
				if (!player.isCreative())
				{
					IFluidHandlerItem handler;
					if (heldItem.getCount() >= 1)
					{
						ItemStack heldItem1 = heldItem.copy();
						heldItem1.setCount(1);
						handler = FluidUtil.getFluidHandler(heldItem1);

						handler.fill(fluidStack, true);
						ItemStack newstack = handler.getContainer();

						if (heldItem.getCount() > 1)
						{
							heldItem.shrink(1);
							player.inventory.addItemStackToInventory(newstack);
						} else
							player.setHeldItem(hand, newstack);

						return true;
					}

				}

				world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ITEM_BUCKET_FILL, SoundCategory.PLAYERS, 0.6F, 0.8F);
				return true;

			} else if (!heldItem.isEmpty() && heldItem.getItem() == Items.GLASS_BOTTLE && hive.fluidHandler.getFluid() != null && hive.fluidHandler.getFluid().amount >= 1000)
			{
				FluidStack fluidStack = hive.fluidHandler.drain(1000, true);
				ItemStack honeyBottle = new ItemStack(FarmAddonItemHandler.honeyJar);

				if (!player.isCreative())
				{
					IFluidHandlerItem handler;
					if (heldItem.getCount() >= 1)
					{
						ItemStack heldItem1 = honeyBottle.copy();
						heldItem1.setCount(1);
						handler = FluidUtil.getFluidHandler(heldItem1);

						handler.fill(fluidStack, true);
						ItemStack newstack = handler.getContainer();

						if (heldItem.getCount() > 1)
						{
							heldItem.shrink(1);
							player.inventory.addItemStackToInventory(newstack);
						} else
							player.setHeldItem(hand, newstack);

						return true;
					}

				}

				world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ITEM_BUCKET_FILL, SoundCategory.PLAYERS, 0.6F, 0.8F);
				return true;
			}

			if (!world.isRemote && player.isSneaking() && hand == EnumHand.MAIN_HAND)
			{
				int honey = hive.fluidHandler.getFluidAmount();

				player.sendStatusMessage(new TextComponentString("Honey stored: " + honey + "mB"), true);
			}

		}

		return true;
	}

	public BlockState getStateFromMeta(int meta)
	{
		EnumFacing enumfacing = EnumFacing.getFront(meta);

		if (enumfacing.getAxis() == EnumFacing.Axis.Y)
		{
			enumfacing = EnumFacing.NORTH;
		}

		return this.getDefaultState().withProperty(FACING, enumfacing);
	}

	public int getMetaFromState(BlockState state)
	{
		return ((EnumFacing) state.getValue(FACING)).getIndex();
	}

	@Override
	public BlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, LivingEntity placer)
	{
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] { FACING });
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityHive();
	}

}
