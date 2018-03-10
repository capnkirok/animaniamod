package com.animania.common.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.common.items.ItemSaltLick;
import com.animania.common.tileentities.TileEntitySaltLick;
import com.animania.config.AnimaniaConfig;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class BlockSaltLick extends AnimaniaBlock implements ITileEntityProvider
{

	private static final AxisAlignedBB AABB = new AxisAlignedBB(0.1875,0,0.1875,0.8125,0.25,0.8125);
	
	public BlockSaltLick()
	{
		super("salt_lick", Material.ROCK, MapColor.SNOW, false);
		this.setCreativeTab(Animania.TabAnimaniaResources);
		
		this.setHardness(1.2f);
		this.setResistance(1.7f);
		this.hasTileEntity = true;
		Item item = new ItemSaltLick(this);
		item.setRegistryName(new ResourceLocation(Animania.MODID, "salt_lick"));
		
		ForgeRegistries.ITEMS.register(item);
	}
	
	public void useSaltLick(World world, BlockPos pos, @Nullable EntityLivingBase entity)
	{
		TileEntity te = world.getTileEntity(pos);
		if(te != null && te instanceof TileEntitySaltLick)
		{
			((TileEntitySaltLick) te).useSaltLick();
			if(entity != null)
			{
				entity.heal(2f);
			}
		}
	}
	
	
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess p_193383_1_, IBlockState p_193383_2_, BlockPos p_193383_3_, EnumFacing p_193383_4_)
	{
		return BlockFaceShape.UNDEFINED;
	}
	
	@Override
	public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te, ItemStack stack)
	{
		if(te != null && te instanceof TileEntitySaltLick)
		{
			int left = ((TileEntitySaltLick)te).usesLeft;
			int damage = AnimaniaConfig.careAndFeeding.saltLickMaxUses - left;
			
			Block.spawnAsEntity(worldIn, pos, new ItemStack(this, 1, damage));
		}
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Items.AIR;
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		int left = AnimaniaConfig.careAndFeeding.saltLickMaxUses - stack.getItemDamage();
		
		TileEntity te = worldIn.getTileEntity(pos);
		if(te != null && te instanceof TileEntitySaltLick)
		{
			((TileEntitySaltLick)te).usesLeft = left;
		}
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		if(world != null)
		{
			TileEntity te = world.getTileEntity(pos);
			if(te != null && te instanceof TileEntitySaltLick)
			{
				double usesLeft = (double)((TileEntitySaltLick)te).usesLeft / (double)AnimaniaConfig.careAndFeeding.saltLickMaxUses;
				return new AxisAlignedBB(0.1875, 0, 0.1875, 0.8125, 0.625 * usesLeft , 0.8125);
			}
		}
		return AABB;
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess world, BlockPos pos)
	{
		if(world != null)
		{
			TileEntity te = world.getTileEntity(pos);
			if(te != null && te instanceof TileEntitySaltLick)
			{
				double usesLeft = (double)((TileEntitySaltLick)te).usesLeft / (double)AnimaniaConfig.careAndFeeding.saltLickMaxUses;
				return new AxisAlignedBB(0.1875, 0, 0.1875, 0.8125, 0.625 * usesLeft , 0.8125);
			}
		}
		return AABB;
	}

	@Override
	public boolean isFullBlock(IBlockState state)
	{
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}
		
//	@Override
//	public boolean isFullyOpaque(IBlockState state)
//	{
//		return false;
//	}
	
	@Override
	public boolean hasTileEntity()
	{
		return true;
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntitySaltLick();
	}
	
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state)
	{
		return EnumBlockRenderType.INVISIBLE;
	}
	
	

}
