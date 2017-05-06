package com.animania.common.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.animania.Animania;
import com.animania.common.tileentities.TileEntityTrough;

public class BlockInvisiblock extends BlockContainer {

	protected static final AxisAlignedBB Invisiblock_AABB = new AxisAlignedBB(0.1D, 0.0D, 0.1D, 0.8D, 0.3D, 0.8D);

	private String name = "invisiblock";

	public BlockInvisiblock() {
		super(Material.WOOD);
		this.setRegistryName(new ResourceLocation(Animania.modid, name));
		this.setBlockUnbreakable();
		GameRegistry.register(this);
		setUnlocalizedName(Animania.modid + "_" + name);
		this.setCreativeTab(null); 
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer()
	{
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}

	@Override
	protected boolean canSilkHarvest()
	{
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}

	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return Invisiblock_AABB;
	}

	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
	{
		IBlockState iblockstate = blockAccess.getBlockState(pos.offset(side));
		Block block = iblockstate.getBlock();

		return block == this ? false : super.shouldSideBeRendered(blockState, blockAccess, pos, side);
	}

	public boolean isOpaqueCube()
	{
		return false;
	}

	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	{

		BlockPos pos1 = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ());
		BlockPos pos2 = new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ());
		BlockPos pos3 = new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1);
		BlockPos pos4 = new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1);

		Block block1 = worldIn.getBlockState(pos1).getBlock();
		Block block2 = worldIn.getBlockState(pos2).getBlock();
		Block block3 = worldIn.getBlockState(pos3).getBlock();
		Block block4 = worldIn.getBlockState(pos4).getBlock();

		if (block1 == Animania.blockTrough) {
			TileEntityTrough te = (TileEntityTrough) worldIn.getTileEntity(pos1);
			if (te != null && te.getTroughRotation() == 1 && !worldIn.isRemote) {
				worldIn.setBlockToAir(pos1);
				super.breakBlock(worldIn, pos1, state);
			}
		} 

		if (block2 == Animania.blockTrough) {
			TileEntityTrough te = (TileEntityTrough) worldIn.getTileEntity(pos2);
			if (te != null && te.getTroughRotation() == 0 && !worldIn.isRemote) {
				worldIn.setBlockToAir(pos2);
				super.breakBlock(worldIn, pos2, state);
			}
		} 

		if (block3 == Animania.blockTrough) {
			TileEntityTrough te = (TileEntityTrough) worldIn.getTileEntity(pos3);
			if (te != null && te.getTroughRotation() == 2 && !worldIn.isRemote) {
				worldIn.setBlockToAir(pos3);
				super.breakBlock(worldIn, pos3, state);
			}

		} 

		if (block4 == Animania.blockTrough) {
			TileEntityTrough te = (TileEntityTrough) worldIn.getTileEntity(pos4);
			if (te != null && te.getTroughRotation() == 3 && !worldIn.isRemote) {
					worldIn.setBlockToAir(pos4);
				super.breakBlock(worldIn, pos4, state);
			}

		}

		super.breakBlock(worldIn, pos, state);
	}


	public int quantityDropped(Random random)
	{
		return 1;
	}


	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{

		ItemStack heldItem = playerIn.getHeldItem(hand);

		BlockPos pos1 = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ());
		BlockPos pos2 = new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ());
		BlockPos pos3 = new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1);
		BlockPos pos4 = new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1);

		Block chk1 = worldIn.getBlockState(pos1).getBlock();
		Block chk2 = worldIn.getBlockState(pos2).getBlock();
		Block chk3 = worldIn.getBlockState(pos3).getBlock();
		Block chk4 = worldIn.getBlockState(pos4).getBlock();

		if (chk1 != null && chk1 == Animania.blockTrough) {
			String state1 = worldIn.getBlockState(pos1).toString().substring(29);
			state1 = state1.substring(0, state1.length()-1);
			if (state1.contains("south")) {
				pos = pos1;
				state = worldIn.getBlockState(pos1);
			}
		}

		if (chk2 != null && chk2 == Animania.blockTrough) {
			String state2 = worldIn.getBlockState(pos2).toString().substring(29);
			state2 = state2.substring(0, state2.length()-1);
			if (state2.contains("north")) {
				pos = pos2;
				state = worldIn.getBlockState(pos2);
			}
		}

		if (chk3 != null && chk3 == Animania.blockTrough) {
			String state3 = worldIn.getBlockState(pos3).toString().substring(29);
			state3 = state3.substring(0, state3.length()-1);

			if (state3.contains("west")) {
				pos = pos3;
				state = worldIn.getBlockState(pos3);
			}
		}

		if (chk4 != null && chk4 == Animania.blockTrough) {
			String state4 = worldIn.getBlockState(pos4).toString().substring(29);
			state4 = state4.substring(0, state4.length()-1);
			if (state4.contains("east")) {
				pos = pos4;
				state = worldIn.getBlockState(pos4);
			}
		}

		TileEntityTrough te = (TileEntityTrough) worldIn.getTileEntity(pos);


		if (worldIn.isRemote) {
			return true;

		} else if (heldItem != ItemStack.EMPTY && heldItem.getItem() == Items.WATER_BUCKET && te.getTroughType() == 0) {
			playerIn.setHeldItem(hand, new ItemStack(Items.BUCKET));
			te.setType(1);
			te.markDirty();
			worldIn.notifyBlockUpdate(pos, state, state, 1);
			worldIn.updateComparatorOutputLevel(pos, this);
			return true;
		} else if (heldItem != ItemStack.EMPTY && heldItem.getItem() == Items.WHEAT && te.getTroughType() == 0) {
			if (heldItem.getCount() == 1) {
				playerIn.setHeldItem(hand, ItemStack.EMPTY);
			} else {
				heldItem.shrink(1);;
			}
			te.setType(4);
			te.markDirty();
			worldIn.notifyBlockUpdate(pos, state, state, 4);
			worldIn.updateComparatorOutputLevel(pos, this);
			return true;
		} else if (heldItem != ItemStack.EMPTY && heldItem.getItem() == Items.WHEAT && te.getTroughType() == 4) {
			if (heldItem.getCount() == 1) {
				playerIn.setHeldItem(hand, ItemStack.EMPTY);
			} else {
				heldItem.shrink(1);
			}
			te.setType(5);
			te.markDirty();
			worldIn.notifyBlockUpdate(pos, state, state, 5);
			worldIn.updateComparatorOutputLevel(pos, this);
			return true;
		} else if (heldItem != ItemStack.EMPTY && heldItem.getItem() == Items.WHEAT && te.getTroughType() == 5) {
			if (heldItem.getCount() == 1) {
				playerIn.setHeldItem(hand, ItemStack.EMPTY);
			} else {
				heldItem.shrink(1);
			}
			te.setType(6);
			te.markDirty();
			worldIn.notifyBlockUpdate(pos, state, state, 6);
			worldIn.updateComparatorOutputLevel(pos, this);
			return true;
		} else if (heldItem != ItemStack.EMPTY && heldItem.getItem() == Animania.bucketSlop && te.getTroughType() == 0) {
			playerIn.setHeldItem(hand, new ItemStack(Items.BUCKET));
			te.setType(7);
			te.markDirty();
			worldIn.notifyBlockUpdate(pos, state, state, 7);
			worldIn.updateComparatorOutputLevel(pos, this);
			worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.BLOCK_SLIME_PLACE, SoundCategory.PLAYERS, 0.6F, 0.8F);
			return true;
		} else if (heldItem != ItemStack.EMPTY && heldItem.getItem() == Items.BUCKET && te.getTroughType() == 1) {
			playerIn.setHeldItem(hand, new ItemStack(Items.WATER_BUCKET));
			te.setType(0);
			te.markDirty();
			worldIn.notifyBlockUpdate(pos, state, state, 0);
			worldIn.updateComparatorOutputLevel(pos, this);
			return true;
		} else if (heldItem != ItemStack.EMPTY && heldItem.getItem() == Items.BUCKET && te.getTroughType() == 7) {
			playerIn.setHeldItem(hand, new ItemStack(Animania.bucketSlop));
			te.setType(0);
			te.markDirty();
			worldIn.notifyBlockUpdate(pos, state, state, 0);
			worldIn.updateComparatorOutputLevel(pos, this);
			return true;
		} else if (heldItem == ItemStack.EMPTY && te.getTroughType() == 4) {
			ItemStack bob = new ItemStack(Items.WHEAT, 1);
			EntityItem entityitem = new EntityItem(playerIn.world, playerIn.posX + 0.5D, playerIn.posY + 0.5D, playerIn.posZ + 0.5D, bob);
			worldIn.spawnEntity(entityitem);
			te.setType(0);
			te.markDirty();
			worldIn.notifyBlockUpdate(pos, state, state, 0);
			worldIn.updateComparatorOutputLevel(pos, this);
			return true;
		} else if (heldItem == ItemStack.EMPTY && te.getTroughType() == 5) {
			ItemStack bob = new ItemStack(Items.WHEAT, 1);
			EntityItem entityitem = new EntityItem(playerIn.world, playerIn.posX + 0.5D, playerIn.posY + 0.5D, playerIn.posZ + 0.5D, bob);
			worldIn.spawnEntity(entityitem);
			te.setType(4);
			te.markDirty();
			worldIn.notifyBlockUpdate(pos, state, state, 4);
			worldIn.updateComparatorOutputLevel(pos, this);
			return true;
		} else if (heldItem == ItemStack.EMPTY && te.getTroughType() == 6) {
			ItemStack bob = new ItemStack(Items.WHEAT, 1);
			EntityItem entityitem = new EntityItem(playerIn.world, playerIn.posX + 0.5D, playerIn.posY + 0.5D, playerIn.posZ + 0.5D, bob);
			worldIn.spawnEntity(entityitem);
			te.setType(5);
			te.markDirty();
			worldIn.notifyBlockUpdate(pos, state, state, 5);
			worldIn.updateComparatorOutputLevel(pos, this);
			return true;
		} else {
			return false;
		}

	}

	@Nullable
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Item.getItemFromBlock(Animania.blockTrough);
	}

	public String getName()
	{
		return name;
	}

	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
	{
		return new ItemStack(Animania.blockTrough);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return null;
	}
}
