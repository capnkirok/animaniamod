package com.animania.compat.jei;

import javax.annotation.Nonnull;

import com.animania.common.handler.BlockHandler;
import com.animania.common.handler.ItemHandler;

import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.UniversalBucket;

@JEIPlugin
public class JEICompat extends BlankModPlugin
{
	
	private ItemStack slopBucket = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, BlockHandler.fluidSlop);
	private ItemStack milkHolstein = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, BlockHandler.fluidMilkHolstein);
	private ItemStack milkFriesian = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, BlockHandler.fluidMilkFriesian);
	

	@Override
	public void register(@Nonnull IModRegistry registry)
	{
		registry.addDescription(new ItemStack(BlockHandler.blockTrough), "text.jei.trough");
		registry.addDescription(new ItemStack(BlockHandler.blockMud), "text.jei.mud");
		registry.addDescription(new ItemStack(Items.WHEAT_SEEDS), "text.jei.seeds");
		registry.addDescription(new ItemStack(ItemHandler.truffle), "text.jei.truffle");
		registry.addDescription(new ItemStack(BlockHandler.blockNest), "text.jei.nest");
		registry.addDescription(slopBucket, "text.jei.slop", "text.jei.slop.craft");
		registry.addDescription(milkHolstein, "text.jei.milkholstein");
		registry.addDescription(milkFriesian, "text.jei.milkfriesian");
		
		
	}
	
}
