package com.animania.catsdogs.common.block;

import com.animania.addons.catsdogs.common.blockentity.BlockEntityPetBowl;
import com.animania.api.interfaces.IFoodProviderBlock;
import com.animania.catsdogs.config.CatsDogsConfig;
import com.animania.common.blocks.AnimaniaContainer;
import com.animania.common.handler.CompatHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.compat.top.providers.TOPInfoProvider;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.core.BlockPos;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.items.ItemHandlerHelper;

public class BlockPetBowl extends AnimaniaContainer implements IFoodProviderBlock, TOPInfoProvider
{

	public static final AABB AABB = new AABB(0.25, 0, 0.25, 0.75, 0.25, 0.75);

	public BlockPetBowl()
	{
		super("pet_bowl", Material.WOOD, MaterialColor.COLOR_BROWN);
		this.setHardness(1.2f);
		this.setResistance(1.5f);
	}

	@Override
	public boolean hasBlockEntity(BlockState state)
	{
		return true;
	}

	@Override
	public BlockEntity createNewBlockEntity(Level levelIn, int meta)
	{
		return new BlockEntityPetBowl();
	}

	@Override
	public void onEntityCollidedWithBlock(Level levelIn, BlockPos pos, BlockState state, Entity entityIn)
	{

		BlockEntityPetBowl te = (BlockEntityPetBowl) levelIn.getBlockEntity(pos);

		if (entityIn instanceof EntityItem && !levelIn.isClientSide)
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
				levelIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 0.6F, 0.8F);
				IFluidHandlerItem handler = FluidUtil.getFluidHandler(stack);
				handler.drain(1000, true);
				ItemStack newStack = handler.getContainer();
				entityitem.setItem(newStack);

			}
		}
	}

	@Override
	public boolean onBlockActivated(Level levelIn, BlockPos pos, BlockState state, Player playerIn, InteractionHand hand, Direction facing, float hitX, float hitY, float hitZ)
	{

		ItemStack heldItem = playerIn.getItemInHand(hand);
		BlockEntityPetBowl te = (BlockEntityPetBowl) levelIn.getBlockEntity(pos);

		// SOLIDS
		if (!heldItem.isEmpty() && isFoodItem(heldItem))
		{
			if (!te.itemHandler.getStackInSlot(0).isEmpty() && te.itemHandler.getStackInSlot(0).getCount() < 3 || te.itemHandler.getStackInSlot(0).isEmpty() && te.fluidHandler.getFluid() == null)
			{
				ItemStack held = heldItem.copy().splitStack(1);
				ItemStack remaining = te.itemHandler.insertItem(0, held, false);
				if (!playerIn.isCreative() && !ItemStack.areItemStacksEqual(remaining, held))
					heldItem.shrink(1);

				return true;
			}

		}
		// LIQUIDS
		else if (AnimaniaHelper.hasFluid(heldItem, FluidRegistry.WATER) && te.itemHandler.getStackInSlot(0).isEmpty() && (te.fluidHandler.getFluid() == null || te.fluidHandler.getFluid().getFluid() == FluidRegistry.WATER && te.fluidHandler.getFluid().amount < 1000))
		{
			te.fluidHandler.fill(new FluidStack(FluidRegistry.WATER, 1000), true);
			levelIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.PLAYERS, 0.6F, 0.8F);
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
		else if (playerIn.getItemInHandOffhand().isEmpty() && playerIn.getMainHandItem().isEmpty() && !te.itemHandler.getStackInSlot(0).isEmpty())
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

			levelIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ITEM_BUCKET_FILL, SoundCategory.PLAYERS, 0.6F, 0.8F);

			return true;

		}
		return false;
	}

	@Override
	public void breakBlock(Level levelIn, BlockPos pos, BlockState state)
	{
		BlockEntityPetBowl te = (BlockEntityPetBowl) levelIn.getBlockEntity(pos);
		if (te != null)
		{
			InventoryHelper.spawnItemStack(levelIn, pos.getX(), pos.getY(), pos.getZ(), te.itemHandler.getStackInSlot(0));
		}

		super.breakBlock(levelIn, pos, state);
	}

	@Override
	public AABB getBoundingBox(BlockState state, IBlockAccess source, BlockPos pos)
	{
		return AABB;
	}

	@Override
	public AABB getCollisionBoundingBox(BlockState blockState, IBlockAccess levelIn, BlockPos pos)
	{
		return AABB;
	}

	@Override
	public boolean hasComparatorInputOverride(BlockState state)
	{
		return true;
	}

	@Override
	public int getComparatorInputOverride(BlockState blockState, Level levelIn, BlockPos pos)
	{

		BlockEntityPetBowl te = (BlockEntityPetBowl) levelIn.getBlockEntity(pos);
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
	public boolean isOpaqueCube(BlockState state)
	{
		return false;
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess p_193383_1_, BlockState p_193383_2_, BlockPos p_193383_3_, Direction p_193383_4_)
	{
		return BlockFaceShape.UNDEFINED;
	}

	@Override
	public boolean isFullCube(BlockState state)
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
	@net.minecraftforge.fml.common.Optional.Method(modid = CompatHandler.THEONEPROBE_ID)
	public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, Player player, Level level, BlockState blockState, IProbeHitData data)
	{
		BlockEntity te = level.getBlockEntity(data.getPos());
		if (te instanceof BlockEntityPetBowl bowl)
		{
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
