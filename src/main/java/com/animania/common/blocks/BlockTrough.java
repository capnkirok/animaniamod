package com.animania.common.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.api.interfaces.IFoodProviderBlock;
import com.animania.common.handler.BlockHandler;
import com.animania.common.handler.CompatHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.tileentities.TileEntityTrough;
import com.animania.compat.top.providers.TOPInfoProvider;
import com.animania.config.AnimaniaConfig;

import PropertyDirection;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.items.ItemHandlerHelper;

public class BlockTrough extends BaseEntityBlock implements TOPInfoProvider, IFoodProviderBlock
{
	private String name = "block_trough";
	public static final PropertyDirection FACING = BlockDirectional.FACING;
	public static final PropertyInteger TYPE = PropertyInteger.create("type", 0, 3);
	protected static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.25D, 2.0D, 0.3D, 0.75D);
	protected static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(-1.0D, 0.0D, 0.25D, 1.0D, 0.3D, 0.75D);
	protected static final AxisAlignedBB WEST_AABB = new AxisAlignedBB(0.25D, 0.0D, -1.0D, 0.75D, 0.3D, 1.0D);
	protected static final AxisAlignedBB EAST_AABB = new AxisAlignedBB(0.25D, 0.0D, 0.0D, 0.75D, 0.3D, 2.0D);

	public BlockTrough()
	{
		super(Material.WOOD);
		this.setRegistryName(new ResourceLocation(Animania.MODID, this.name));
		this.setDefaultState(this.blockState.getBaseState().withProperty(BlockTrough.FACING, Direction.NORTH));
		BlockHandler.blocks.add(this);
		this.setUnlocalizedName(Animania.MODID + "_" + this.name);
		this.setCreativeTab(Animania.TabAnimaniaResources);
		this.setTickRandomly(true);
	}

	@Override
	public int tickRate(World worldIn)
	{
		return 6;
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, BlockState state, Random rand)
	{

		float f = worldIn.getBiome(pos).getTemperature(pos);

		if (worldIn.getBiomeProvider().getTemperatureAtHeight(f, pos.getY()) >= 0.15F && worldIn.isRaining() && worldIn.getTopSolidOrLiquidBlock(pos).getY() == pos.getY() + 1)
		{
			TileEntityTrough te = (TileEntityTrough) worldIn.getTileEntity(pos);

			if (te.itemHandler.getStackInSlot(0).isEmpty() && te.fluidHandler.getFluid() == null)
			{
				te.fluidHandler.fill(new FluidStack(FluidRegistry.WATER, 100), true);
			} else if (te.itemHandler.getStackInSlot(0).isEmpty() && te.fluidHandler.getFluid().getFluid() == FluidRegistry.WATER && te.fluidHandler.getFluid().amount < 1000)
			{
				te.fluidHandler.fill(new FluidStack(FluidRegistry.WATER, 100), true);
			}

		}

	}

	@Override
	@Deprecated
	public BlockFaceShape getBlockFaceShape(IBlockAccess p_193383_1_, BlockState p_193383_2_, BlockPos p_193383_3_, Direction p_193383_4_)
	{
		return BlockFaceShape.UNDEFINED;
	}

	@Override
	public boolean isTopSolid(BlockState state)
	{
		return false;
	}

	@Override
	public String getLocalizedName()
	{
		return I18n.translateToLocal("tile.animania_block_trough.name");
	}

	/**
	 * Used to determine ambient occlusion and culling when rebuilding chunks
	 * for render
	 */
	@Override
	public boolean isOpaqueCube(BlockState state)
	{
		return false;
	}

	@Override
	public boolean isFullBlock(BlockState state)
	{
		return false;
	}

	@Override
	public boolean isFullCube(BlockState state)
	{
		return false;
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, BlockState state, Entity entityIn)
	{

		TileEntity tile = worldIn.getTileEntity(pos);
		if (!(tile instanceof TileEntityTrough))
			return;

		TileEntityTrough te = (TileEntityTrough) tile;

		if (entityIn != null && entityIn instanceof EntityItem && !worldIn.isRemote)
		{

			EntityItem entityitem = (EntityItem) entityIn;
			ItemStack stack = entityitem.getItem();

			// SOLIDS
			if (!stack.isEmpty() && stack.getItem() == Items.WHEAT || isModdedFoodItem(stack))
			{
				if (!te.itemHandler.getStackInSlot(0).isEmpty() && te.itemHandler.getStackInSlot(0).getCount() < 3 || te.itemHandler.getStackInSlot(0).isEmpty() && te.fluidHandler.getFluid() == null)
				{
					ItemStack held = stack.copy().splitStack(1);
					ItemStack remaining = te.itemHandler.insertItem(0, held, false);
					if (!ItemStack.areItemStacksEqual(remaining, held))
						stack.shrink(1);

				}

			}
			// LIQUIDS
			else if (AnimaniaHelper.hasFluid(stack, FluidRegistry.WATER) && te.itemHandler.getStackInSlot(0).isEmpty() && te.fluidHandler.getFluid() == null)
			{
				te.fluidHandler.fill(new FluidStack(FluidRegistry.WATER, 1000), true);
				worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 0.6F, 0.8F);
				IFluidHandlerItem handler = FluidUtil.getFluidHandler(stack);
				handler.drain(1000, true);
				ItemStack newStack = handler.getContainer();
				entityitem.setItem(newStack);

			} else if (AnimaniaHelper.hasFluid(stack, BlockHandler.fluidSlop) && te.itemHandler.getStackInSlot(0).isEmpty() && te.fluidHandler.getFluid() == null)
			{
				te.fluidHandler.fill(new FluidStack(BlockHandler.fluidSlop, 1000), true);
				worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_SLIME_PLACE, SoundCategory.BLOCKS, 0.6F, 0.8F);

				IFluidHandlerItem handler = FluidUtil.getFluidHandler(stack);
				handler.drain(1000, true);
				ItemStack newStack = handler.getContainer();
				entityitem.setItem(newStack);

			}

			worldIn.updateComparatorOutputLevel(findInvisiblock(worldIn, pos), BlockHandler.blockInvisiblock);
			worldIn.updateComparatorOutputLevel(pos, this);
		}
	}

	@Override
	public AxisAlignedBB getBoundingBox(BlockState state, IBlockAccess source, BlockPos pos)
	{

		switch (state.getValue(BlockTrough.FACING))
		{
		case NORTH:
		default:
			return BlockTrough.NORTH_AABB;
		case SOUTH:
			return BlockTrough.SOUTH_AABB;
		case WEST:
			return BlockTrough.WEST_AABB;
		case EAST:
			return BlockTrough.EAST_AABB;
		}
	}

	/**
	 * Called by BlockItems just before a block is actually set in the world, to
	 * allow for adjustments to the BlockState
	 */

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack)
	{

		TileEntityTrough teChk = (TileEntityTrough) worldIn.getTileEntity(pos);

		if (teChk == null || true)
		{

			Direction enumfacing = Direction.getHorizontal(MathHelper.floor(placer.rotationYaw * 4.0F / 360.0F + 0.5D) & 3).getOpposite();
			state = state.withProperty(BlockTrough.FACING, enumfacing);
			BlockPos blockpos = pos.north();
			boolean flag = this.blockState == worldIn.getBlockState(blockpos);

			if (!flag)
				worldIn.setBlock(pos, state, 3);

			// System.out.println(placer.getHorizontalFacing().toString());

			if (placer.getHorizontalFacing().toString() == "south")
			{
				BlockPos invisipos = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ());
				worldIn.setBlock(invisipos, BlockHandler.blockInvisiblock.defaultBlockState());
				TileEntityTrough te = (TileEntityTrough) worldIn.getTileEntity(pos);
				te.setTroughRotation(0);
			} else if (placer.getHorizontalFacing().toString() == "north")
			{
				BlockPos invisipos = new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ());
				worldIn.setBlock(invisipos, BlockHandler.blockInvisiblock.defaultBlockState());
				TileEntityTrough te = (TileEntityTrough) worldIn.getTileEntity(pos);
				te.setTroughRotation(1);
			} else if (placer.getHorizontalFacing().toString() == "east")
			{
				BlockPos invisipos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1);
				worldIn.setBlock(invisipos, BlockHandler.blockInvisiblock.defaultBlockState());
				TileEntityTrough te = (TileEntityTrough) worldIn.getTileEntity(pos);
				te.setTroughRotation(2);
			} else if (placer.getHorizontalFacing().toString() == "west")
			{
				BlockPos invisipos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1);
				worldIn.setBlock(invisipos, BlockHandler.blockInvisiblock.defaultBlockState());
				TileEntityTrough te = (TileEntityTrough) worldIn.getTileEntity(pos);
				te.setTroughRotation(3);
			}
		}

	}

	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
	{
		PlayerEntity PlayerEntity = worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 5, false);

		String dir = "";

		if (PlayerEntity != null)
			dir = PlayerEntity.getHorizontalFacing().toString().trim();

		BlockPos blockpos = pos.west();
		BlockPos blockpos1 = pos.east();
		BlockPos blockpos2 = pos.north();
		BlockPos blockpos3 = pos.south();

		if (dir.equals("north") && !worldIn.getBlockState(blockpos).getBlock().isReplaceable(worldIn, blockpos))
			return false;

		if (dir.equals("east") && !worldIn.getBlockState(blockpos2).getBlock().isReplaceable(worldIn, blockpos2))
			return false;

		if (dir.equals("south") && !worldIn.getBlockState(blockpos1).getBlock().isReplaceable(worldIn, blockpos1))
			return false;

		if (dir.equals("west") && !worldIn.getBlockState(blockpos3).getBlock().isReplaceable(worldIn, blockpos3))
			return false;

		return true;

	}

	/**
	 * Returns a new instance of a block's tile entity class. Called on placing
	 * the block.
	 */
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityTrough();
	}

	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, BlockState state)
	{
		return new ItemStack(BlockHandler.blockTrough, 1);
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, BlockState state)
	{

		String dir = state.getValue(BlockTrough.FACING).toString();

		if (dir == "south")
		{
			BlockPos invisipos = new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ());
			worldIn.setBlockToAir(invisipos);
		} else if (dir == "north")
		{
			BlockPos invisipos = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ());
			worldIn.setBlockToAir(invisipos);
		} else if (dir == "east")
		{
			BlockPos invisipos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1);
			worldIn.setBlockToAir(invisipos);
		} else if (dir == "west")
		{
			BlockPos invisipos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1);
			worldIn.setBlockToAir(invisipos);
		}

		TileEntityTrough te = (TileEntityTrough) worldIn.getTileEntity(pos);
		if (te != null)
		{
			InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), te.itemHandler.getStackInSlot(0));
		}

		super.breakBlock(worldIn, pos, state);
	}

	@Override
	@Nullable
	public Item getItemDropped(BlockState state, Random rand, int fortune)
	{
		return Item.getItemFromBlock(BlockHandler.blockTrough);
	}

	public boolean canDispenserPlace(World worldIn, BlockPos pos, ItemStack stack)
	{
		return stack.getMetadata() == 1 && pos.getY() >= 2 && worldIn.getDifficulty() != EnumDifficulty.PEACEFUL;
	}

	@Override
	public BlockState getStateFromMeta(int meta)
	{
		return this.defaultBlockState().withProperty(BlockTrough.FACING, Direction.getHorizontal(meta & 3));
	}

	@Override
	public int getMetaFromState(BlockState state)
	{
		int i = 0;
		i = i | state.getValue(BlockTrough.FACING).getIndex();
		return i;
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, BlockState state, PlayerEntity playerIn, EnumHand hand, Direction facing, float hitX, float hitY, float hitZ)
	{

		ItemStack heldItem = playerIn.getHeldItem(hand);
		TileEntityTrough te = (TileEntityTrough) worldIn.getTileEntity(pos);

		// SOLIDS
		if (!heldItem.isEmpty() && heldItem.getItem() == Items.WHEAT || isModdedFoodItem(heldItem))
		{
			if ((!te.itemHandler.getStackInSlot(0).isEmpty() && te.itemHandler.getStackInSlot(0).getCount() < 3) || te.itemHandler.getStackInSlot(0).isEmpty() && te.fluidHandler.getFluid() == null)
			{
				ItemStack held = heldItem.copy().splitStack(1);
				ItemStack remaining = te.itemHandler.insertItem(0, held, false);
				if (!playerIn.isCreative() && !ItemStack.areItemStacksEqual(remaining, held))
					heldItem.shrink(1);

				worldIn.updateComparatorOutputLevel(findInvisiblock(worldIn, pos), BlockHandler.blockInvisiblock);
				worldIn.updateComparatorOutputLevel(pos, this);
				return true;
			}

		}
		// LIQUIDS
		else if (AnimaniaHelper.hasFluid(heldItem, FluidRegistry.WATER) && te.itemHandler.getStackInSlot(0).isEmpty() && te.fluidHandler.getFluid() == null)
		{
			te.fluidHandler.fill(new FluidStack(FluidRegistry.WATER, 1000), true);
			worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.PLAYERS, 0.6F, 0.8F);
			if (!playerIn.isCreative())
			{
				IFluidHandlerItem handler = FluidUtil.getFluidHandler(heldItem);
				handler.drain(1000, true);
				ItemStack newStack = handler.getContainer();
				playerIn.setHeldItem(hand, newStack);
			}
			worldIn.updateComparatorOutputLevel(findInvisiblock(worldIn, pos), BlockHandler.blockInvisiblock);
			worldIn.updateComparatorOutputLevel(pos, this);
			return true;

		} else if (AnimaniaHelper.hasFluid(heldItem, FluidRegistry.WATER) && te.fluidHandler.getFluid() != null && te.fluidHandler.getFluid().getFluid() == FluidRegistry.WATER && te.fluidHandler.getFluid().amount < 1000)
		{
			te.fluidHandler.fill(new FluidStack(FluidRegistry.WATER, 1000), true);
			worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.PLAYERS, 0.6F, 0.8F);
			if (!playerIn.isCreative())
			{
				IFluidHandlerItem handler = FluidUtil.getFluidHandler(heldItem);
				handler.drain(1000, true);
				ItemStack newStack = handler.getContainer();
				playerIn.setHeldItem(hand, newStack);
			}
			worldIn.updateComparatorOutputLevel(findInvisiblock(worldIn, pos), BlockHandler.blockInvisiblock);
			worldIn.updateComparatorOutputLevel(pos, this);
			return true;

		} else if (AnimaniaHelper.hasFluid(heldItem, BlockHandler.fluidSlop) && te.itemHandler.getStackInSlot(0).isEmpty() && te.fluidHandler.getFluid() == null)
		{
			te.fluidHandler.fill(new FluidStack(BlockHandler.fluidSlop, 1000), true);
			worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_SLIME_PLACE, SoundCategory.PLAYERS, 0.6F, 0.8F);
			if (!playerIn.isCreative())
			{
				IFluidHandlerItem handler = FluidUtil.getFluidHandler(heldItem);
				handler.drain(1000, true);
				ItemStack newStack = handler.getContainer();
				playerIn.setHeldItem(hand, newStack);
			}
			worldIn.updateComparatorOutputLevel(findInvisiblock(worldIn, pos), BlockHandler.blockInvisiblock);
			worldIn.updateComparatorOutputLevel(pos, this);
			return true;

		} else if (AnimaniaHelper.hasFluid(heldItem, BlockHandler.fluidSlop) && te.fluidHandler.getFluid() != null && te.fluidHandler.getFluid().getFluid() == BlockHandler.fluidSlop && te.fluidHandler.getFluid().amount < 1000)
		{
			te.fluidHandler.fill(new FluidStack(BlockHandler.fluidSlop, 1000), true);
			worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_SLIME_PLACE, SoundCategory.PLAYERS, 0.6F, 0.8F);
			if (!playerIn.isCreative())
			{
				IFluidHandlerItem handler = FluidUtil.getFluidHandler(heldItem);
				handler.drain(1000, true);
				ItemStack newStack = handler.getContainer();
				playerIn.setHeldItem(hand, newStack);
			}
			worldIn.updateComparatorOutputLevel(findInvisiblock(worldIn, pos), BlockHandler.blockInvisiblock);
			worldIn.updateComparatorOutputLevel(pos, this);
			return true;

		}
		// EMPTY SOLIDS
		else if (playerIn.getHeldItemOffhand().isEmpty() && playerIn.getMainHandItem().isEmpty() && !te.itemHandler.getStackInSlot(0).isEmpty())
		{
			ItemStack extract = te.itemHandler.extractItem(0, 1, false);
			playerIn.inventory.addItemStackToInventory(extract);
			worldIn.updateComparatorOutputLevel(findInvisiblock(worldIn, pos), BlockHandler.blockInvisiblock);
			return true;
		}
		// EMPTY LIQUIDS
		else if (!heldItem.isEmpty() && FluidUtil.getFluidHandler(heldItem) != null && FluidUtil.getFluidContained(heldItem) == null && te.fluidHandler.getFluid() != null && te.fluidHandler.getFluid().amount >= 1000)
		{
			FluidStack fluidStack = te.fluidHandler.drain(1000, true);
			if (!playerIn.isCreative())
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
						playerIn.inventory.addItemStackToInventory(newstack);
					} else
						playerIn.setHeldItem(hand, newstack);

					return true;
				}

			}

			worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ITEM_BUCKET_FILL, SoundCategory.PLAYERS, 0.6F, 0.8F);
			worldIn.updateComparatorOutputLevel(findInvisiblock(worldIn, pos), BlockHandler.blockInvisiblock);
			worldIn.updateComparatorOutputLevel(pos, this);

			return true;

		}
		return false;
	}

	@Override
	public BlockState withRotation(BlockState state, Rotation rot)
	{
		return state.withProperty(BlockTrough.FACING, rot.rotate(state.getValue(BlockTrough.FACING)));
	}

	@Override
	public BlockState withMirror(BlockState state, Mirror mirrorIn)
	{
		return state.withRotation(mirrorIn.toRotation(state.getValue(BlockTrough.FACING)));
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] { BlockTrough.FACING });
	}

	@Override
	public boolean hasComparatorInputOverride(BlockState state)
	{
		return true;
	}

	@Override
	public int getComparatorInputOverride(BlockState blockState, World worldIn, BlockPos pos)
	{

		TileEntityTrough te = (TileEntityTrough) worldIn.getTileEntity(pos);
		if (!te.itemHandler.getStackInSlot(0).isEmpty())
			return ItemHandlerHelper.calcRedstoneFromInventory(te.itemHandler);

		if (te.fluidHandler.getFluid() != null)
		{
			int fluid = te.fluidHandler.getFluidAmount();
			return fluid / 66;
		}

		return 0;
	}

	public String getName()
	{
		return this.name;
	}

	public BlockPos findInvisiblock(World world, BlockPos pos)
	{
		Direction facing = world.getBlockState(pos).getValue(FACING);
		facing = facing.rotateAround(Axis.Y);

		return pos.offset(facing);
	}

	public static boolean isModdedFoodItem(ItemStack stack)
	{
		if (stack.isEmpty())
			return false;

		String[] foods = AnimaniaConfig.gameRules.troughFood;

		ItemStack[] items = AnimaniaHelper.getItemStackArray(foods);

		for (int i = 0; i < items.length; i++)
		{
			if (ItemStack.areItemsEqual(items[i], stack))
				return true;
		}

		return false;
	}

	@Override
	public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
	{
		BlockPos invisi = findInvisiblock(worldIn, pos);
		worldIn.updateComparatorOutputLevel(invisi, BlockHandler.blockInvisiblock);

		super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
	}

	@Override
	@net.minecraftforge.fml.common.Optional.Method(modid = CompatHandler.THEONEPROBE_ID)
	public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, PlayerEntity player, World world, BlockState blockState, IProbeHitData data)
	{
		TileEntity te = world.getTileEntity(data.getPos());
		if (te instanceof TileEntityTrough)
		{
			TileEntityTrough trough = (TileEntityTrough) te;
			ItemStack stack = trough.itemHandler.getStackInSlot(0);
			FluidStack fluid = trough.fluidHandler.getFluid();

			if (mode == ProbeMode.NORMAL)
			{

				if (!stack.isEmpty())
				{
					probeInfo.horizontal();
					probeInfo.item(stack);
				}
				if (fluid != null)
				{
					ItemStack bucket = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, fluid.getFluid());
					probeInfo.horizontal().item(bucket).text(fluid.getLocalizedName() + ", " + fluid.amount + "mB");

				}
			}

		}

	}

}