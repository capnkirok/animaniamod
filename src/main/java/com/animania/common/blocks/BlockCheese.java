package com.animania.common.blocks;

import java.util.Random;

import com.animania.Animania;

import net.minecraft.block.BlockCake;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockCheese extends BlockCake
{

	public BlockCheese(String name)
	{
		super();
		this.setRegistryName(Animania.MODID + ":" + name);
		this.setUnlocalizedName(Animania.MODID + "_" + name);
		this.setCreativeTab(Animania.TabAnimaniaResources);
		this.setSoundType(SoundType.CLOTH);
		this.setHardness(0.6f);
		GameRegistry.register(this);
		GameRegistry.register(new ItemBlock(this), new ResourceLocation(Animania.MODID, name.substring(7) + "_cheese_wheel"));
		this.setTickRandomly(true);

	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
	{
		return new ItemStack(this);
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Item.getItemFromBlock(this);
	}
	
	@Override
	public int quantityDropped(IBlockState state, int fortune, Random random)
	{
		return state == this.getDefaultState() ? 1 : 0;
	}
	
	
	
	
	
}
