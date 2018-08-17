package com.animania.common.items;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.animania.common.handler.BlockHandler;
import com.animania.common.items.handler.FluidHandlerJar;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fluids.capability.wrappers.FluidBucketWrapper;

public class ItemJar extends AnimaniaItem
{

	public ItemJar()
	{
		super("honey_jar");
	}

	@Override
	public ICapabilityProvider initCapabilities(@Nonnull ItemStack stack, NBTTagCompound nbt)
	{
		return new FluidHandlerJar(stack);
	}

	@Override
	public void getSubItems(@Nullable CreativeTabs tab, @Nonnull NonNullList<ItemStack> subItems)
	{
		if (!this.isInCreativeTab(tab))
			return;

		subItems.add(new ItemStack(this));
		
		FluidStack fs = new FluidStack(BlockHandler.fluidHoney, 1000);
		ItemStack stack = new ItemStack(this);
		IFluidHandlerItem fluidHandler = new FluidHandlerJar(stack);
		if (fluidHandler.fill(fs, true) == fs.amount)
		{
			ItemStack filled = fluidHandler.getContainer();
			subItems.add(filled);
		}


	}

}
