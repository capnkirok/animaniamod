package com.animania.compat.jei;

import javax.annotation.Nonnull;

import com.animania.common.handler.BlockHandler;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;

@JeiPlugin
public class JEICompat implements IModPlugin {

	@Override
	public void register(@Nonnull IModRegistry registry)
	{
		ItemStack slopBucket = FluidUtil.getFilledBucket(new FluidStack(BlockHandler.fluidSlop, 9));

		registry.addDescription(new ItemStack(BlockHandler.blockTrough), "text.jei.trough");
		registry.addDescription(new ItemStack(BlockHandler.blockMud), "text.jei.mud");
		registry.addDescription(new ItemStack(Items.WHEAT_SEEDS), "text.jei.seeds");
		registry.addDescription(new ItemStack(BlockHandler.blockNest), "text.jei.nest");
		registry.addDescription(slopBucket, "text.jei.slop");
	}

}
