package com.animania.common.blockentities;

import java.util.Set;

import com.animania.Animania;
import com.animania.api.interfaces.IFoodProviderBE;
import com.animania.common.handler.BlockHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.config.AnimaniaConfig;

import net.minecraft.client.renderer.texture.Tickable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;

public class BlockEntityInvisiblock extends BlockEntity implements Tickable, IFoodProviderBE
{
	public BlockEntityInvisiblock()
	{

	}

	@Override
	public boolean hasCapability(Capability<?> capability, Direction facing)
	{

		if (AnimaniaConfig.gameRules.allowTroughAutomation)
		{

			if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && this.getPlacedTrough() != null && this.getPlacedTrough().fluidHandler.getFluid() == null)
				return true;
			if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY && this.getPlacedTrough() != null && this.getPlacedTrough().itemHandler.getStackInSlot(0).isEmpty())
				return true;
		}

		return super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, Direction facing)
	{
		if (AnimaniaConfig.gameRules.allowTroughAutomation)
		{

			if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
				return (T) this.getPlacedTrough().itemHandler;

			if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
				return (T) this.getPlacedTrough().fluidHandler;

		}

		return super.getCapability(capability, facing);

	}

	private BlockEntityTrough getPlacedTrough()
	{
		BlockPos pos1 = new BlockPos(this.pos.getX() + 1, this.pos.getY(), this.pos.getZ());
		BlockPos pos2 = new BlockPos(this.pos.getX() - 1, this.pos.getY(), this.pos.getZ());
		BlockPos pos3 = new BlockPos(this.pos.getX(), this.pos.getY(), this.pos.getZ() + 1);
		BlockPos pos4 = new BlockPos(this.pos.getX(), this.pos.getY(), this.pos.getZ() - 1);

		Block block1 = this.level.getBlockState(pos1).getBlock();
		Block block2 = this.level.getBlockState(pos2).getBlock();
		Block block3 = this.level.getBlockState(pos3).getBlock();
		Block block4 = this.level.getBlockState(pos4).getBlock();

		if (block1 == BlockHandler.blockTrough)
		{
			BlockEntityTrough te = (BlockEntityTrough) this.level.getBlockEntity(pos1);
			if (te != null && te.getTroughRotation() == 1)
				return te;
		}

		if (block2 == BlockHandler.blockTrough)
		{
			BlockEntityTrough te = (BlockEntityTrough) this.level.getBlockEntity(pos2);
			if (te != null && te.getTroughRotation() == 0)
				return te;
		}

		if (block3 == BlockHandler.blockTrough)
		{
			BlockEntityTrough te = (BlockEntityTrough) this.level.getBlockEntity(pos3);
			if (te != null && te.getTroughRotation() == 2)
				return te;

		}

		if (block4 == BlockHandler.blockTrough)
		{
			BlockEntityTrough te = (BlockEntityTrough) this.level.getBlockEntity(pos4);
			if (te != null && te.getTroughRotation() == 3)
				return te;

		}

		Animania.LOGGER.warn("Removing invalid trough at [" + this.pos.getX() + ", " + this.pos.getY() + ", " + this.pos.getZ() + "], dimension " + this.level.provider.getDimension());

		this.level.setBlockToAir(this.pos);

		return null;
	}

	public BlockEntityTrough getTrough()
	{
		return this.getPlacedTrough();
	}

	@Override
	public void update()
	{

	}

	@Override
	public void markDirty()
	{
		super.markDirty();

		BlockEntityTrough trough = this.getPlacedTrough();

		if (trough != null)
		{
			AnimaniaHelper.sendBlockEntityUpdate(this.getPlacedTrough());
		}
	}

	@Override
	public boolean canConsume(Set<ItemStack> fooditems, Fluid[] fluid)
	{
		BlockEntityTrough trough = this.getTrough();

		if (trough != null)
		{
			return this.getTrough().canConsume(fooditems, fluid);
		}

		return false;
	}

	@Override
	public boolean canConsume(FluidStack fluid, Set<ItemStack> fooditems)
	{
		BlockEntityTrough trough = this.getTrough();

		if (trough != null)
		{
			return this.getTrough().canConsume(fluid, fooditems);
		}

		return false;
	}

	@Override
	public void consumeSolidOrLiquid(int liquidAmount, int itemAmount)
	{
		BlockEntityTrough trough = this.getTrough();

		if (trough != null)
		{
			this.getTrough().consumeSolidOrLiquid(liquidAmount, itemAmount);
		}
	}

	@Override
	public void consumeSolid(int amount)
	{
		BlockEntityTrough trough = this.getTrough();

		if (trough != null)
		{
			this.getTrough().consumeSolid(amount);
		}
	}

	@Override
	public void consumeLiquid(int amount)
	{
		BlockEntityTrough trough = this.getTrough();

		if (trough != null)
		{
			this.getTrough().consumeLiquid(amount);
		}
	}
}
