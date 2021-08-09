package com.animania.addons.catsdogs.common.block;

import com.animania.addons.catsdogs.common.tileentity.TileEntityPetBowl;
import com.animania.addons.catsdogs.config.CatsDogsConfig;
import com.animania.api.interfaces.IFoodProviderBlock;
import com.animania.common.blocks.AnimaniaContainer;
import com.animania.common.handler.CompatHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.compat.top.providers.TOPInfoProvider;

import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.items.ItemHandlerHelper;

public class BlockPetBowl extends AnimaniaContainer implements IFoodProviderBlock, TOPInfoProvider
{

	public static final AxisAlignedBB AABB = new AxisAlignedBB(0.25, 0, 0.25, 0.75, 0.25, 0.75);

	public BlockPetBowl()
	{
		super("pet_bowl", Material.WOOD, MaterialColor.BROWN);
		this.setHardness(1.2f);
		this.setResistance(1.5f);
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state)
	{
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityPetBowl();
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
	{

		TileEntityPetBowl te = (TileEntityPetBowl) worldIn.getTileEntity(pos);

		if (entityIn != null && entityIn instanceof EntityItem && !worldIn.isRemote)
		{

			EntityItem entityitem = (EntityItem) entityIn;
			ItemStack stack = entityitem.getItem();

			// SOLIDS
			if (!stack.isEmpty() && isFoodItem(stack))
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

			}
		}
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, PlayerEntity playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{

		ItemStack heldItem = playerIn.getHeldItem(hand);
		TileEntityPetBowl te = (TileEntityPetBowl) worldIn.getTileEntity(pos);

		// SOLIDS
		if (!heldItem.isEmpty() && isFoodItem(heldItem))
		{
			if ((!te.itemHandler.getStackInSlot(0).isEmpty() && te.itemHandler.getStackInSlot(0).getCount() < 3) || te.itemHandler.getStackInSlot(0).isEmpty() && te.fluidHandler.getFluid() == null)
			{
				ItemStack held = heldItem.copy().splitStack(1);
				ItemStack remaining = te.itemHandler.insertItem(0, held, false);
				if (!playerIn.isCreative() && !ItemStack.areItemStacksEqual(remaining, held))
					heldItem.shrink(1);

				return true;
			}

		}
		// LIQUIDS
		else if (AnimaniaHelper.hasFluid(heldItem, FluidRegistry.WATER) && te.itemHandler.getStackInSlot(0).isEmpty() && (te.fluidHandler.getFluid() == null || (te.fluidHandler.getFluid().getFluid() == FluidRegistry.WATER && te.fluidHandler.getFluid().amount < 1000)))
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
			return true;
		}

		// EMPTY SOLIDS
		else if (playerIn.getHeldItemOffhand().isEmpty() && playerIn.getHeldItemMainhand().isEmpty() && !te.itemHandler.getStackInSlot(0).isEmpty())
		{
			ItemStack extract = te.itemHandler.extractItem(0, 1, false);
			playerIn.inventory.addItemStackToInventory(extract);
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
					}
					else
						playerIn.setHeldItem(hand, newstack);

					return true;
				}

			}

			worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ITEM_BUCKET_FILL, SoundCategory.PLAYERS, 0.6F, 0.8F);

			return true;

		}
		return false;
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	{
		TileEntityPetBowl te = (TileEntityPetBowl) worldIn.getTileEntity(pos);
		if (te != null)
		{
			InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), te.itemHandler.getStackInSlot(0));
		}

		super.breakBlock(worldIn, pos, state);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return AABB;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
	{
		return AABB;
	}

	@Override
	public boolean hasComparatorInputOverride(IBlockState state)
	{
		return true;
	}

	@Override
	public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos)
	{

		TileEntityPetBowl te = (TileEntityPetBowl) worldIn.getTileEntity(pos);
		if (!te.itemHandler.getStackInSlot(0).isEmpty())
			return ItemHandlerHelper.calcRedstoneFromInventory(te.itemHandler);

		if (te.fluidHandler.getFluid() != null)
		{
			int fluid = te.fluidHandler.getFluidAmount();
			return fluid / 66;
		}

		return 0;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess p_193383_1_, IBlockState p_193383_2_, BlockPos p_193383_3_, EnumFacing p_193383_4_)
	{
		return BlockFaceShape.UNDEFINED;
	}

	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}

	public static boolean isFoodItem(ItemStack stack)
	{
		if (stack.isEmpty())
			return false;

		String[] foods = CatsDogsConfig.catsdogs.petBowlFood;
		ItemStack[] items = AnimaniaHelper.getItemStackArray(foods);

		for (int i = 0; i < items.length; i++)
		{
			if (ItemStack.areItemsEqual(items[i], stack))
				return true;
		}

		return false;
	}

	@Override
	@net.minecraftforge.fml.common.Optional.Method(modid=CompatHandler.THEONEPROBE_ID)
	public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, PlayerEntity player, World world, IBlockState blockState, IProbeHitData data)
	{
		TileEntity te = world.getTileEntity(data.getPos());
		if (te instanceof TileEntityPetBowl)
		{
			TileEntityPetBowl bowl = (TileEntityPetBowl) te;
			ItemStack stack = bowl.itemHandler.getStackInSlot(0);
			FluidStack fluid = bowl.fluidHandler.getFluid();

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
