package com.animania.blocks;


import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.animania.Animania;
import com.animania.tileentities.TileEntityTrough;

public class BlockTrough extends BlockContainer
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
		this.setRegistryName(new ResourceLocation(Animania.modid, name));
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		GameRegistry.register(this);
		setUnlocalizedName(Animania.modid + "_" + name);
		setCreativeTab(Animania.TabAnimaniaResources); 
	}

	public String getLocalizedName()
	{
		return I18n.translateToLocal("tile.animania_block_trough.name");
	}

	/**
	 * Used to determine ambient occlusion and culling when rebuilding chunks for render
	 */
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}

	public boolean isFullCube(IBlockState state)
	{
		return false;
	}


	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
	{

		TileEntityTrough te = (TileEntityTrough) worldIn.getTileEntity(pos);

		if (entityIn != null && entityIn instanceof EntityItem) {

			EntityItem entityitem = (EntityItem) entityIn;
			Item item = entityitem.getEntityItem().getItem();

			if (te.getTroughType() == 4 && item !=null && item == Items.WHEAT) {
				te.setType(5);
				te.markDirty();
				worldIn.notifyBlockUpdate(pos, state, state, 5);
				worldIn.updateComparatorOutputLevel(pos, this);
				entityitem.setDead();

			} else if (te.getTroughType() == 5 && item !=null && item == Items.WHEAT) {
				te.setType(6);
				te.markDirty();
				worldIn.notifyBlockUpdate(pos, state, state, 6);
				worldIn.updateComparatorOutputLevel(pos, this);
				entityitem.setDead();
			} else if (te.getTroughType() == 0 && item !=null && item == Items.WHEAT) {
				te.setType(4);
				te.markDirty();
				worldIn.notifyBlockUpdate(pos, state, state, 4);
				worldIn.updateComparatorOutputLevel(pos, this);
				entityitem.setDead();
			} else if (te.getTroughType() == 0 && item !=null && item == Items.WATER_BUCKET) {
				te.setType(1);
				te.markDirty();
				worldIn.notifyBlockUpdate(pos, state, state, 1);
				worldIn.updateComparatorOutputLevel(pos, this);
				worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ITEM_BUCKET_FILL, SoundCategory.PLAYERS, 0.6F, 0.8F);
				entityitem.setEntityItemStack(new ItemStack(Items.BUCKET, 1));

			} else if (te.getTroughType() == 1 && item !=null && item == Items.WATER_BUCKET) {
				te.setType(1);
				te.markDirty();
				worldIn.notifyBlockUpdate(pos, state, state, 1);
				worldIn.updateComparatorOutputLevel(pos, this);
				worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ITEM_BUCKET_FILL, SoundCategory.PLAYERS, 0.6F, 0.8F);
				entityitem.setEntityItemStack(new ItemStack(Items.BUCKET, 1));
			} else if (te.getTroughType() == 2 && item !=null && item == Items.WATER_BUCKET) {
				te.setType(1);
				te.markDirty();
				worldIn.notifyBlockUpdate(pos, state, state, 1);
				worldIn.updateComparatorOutputLevel(pos, this);
				worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ITEM_BUCKET_FILL, SoundCategory.PLAYERS, 0.6F, 0.8F);
				entityitem.setEntityItemStack(new ItemStack(Items.BUCKET, 1));		
			} else if (te.getTroughType() == 0 && item !=null && item == Animania.bucketSlop) {
				te.setType(7);
				te.markDirty();
				worldIn.notifyBlockUpdate(pos, state, state, 7);
				worldIn.updateComparatorOutputLevel(pos, this);
				worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_SLIME_PLACE, SoundCategory.PLAYERS, 0.6F, 0.8F);
				entityitem.setEntityItemStack(new ItemStack(Items.BUCKET, 1));
			} else if (te.getTroughType() == 5 && item !=null && item == Animania.bucketSlop) {
				te.setType(7);
				te.markDirty();
				worldIn.notifyBlockUpdate(pos, state, state, 7);
				worldIn.updateComparatorOutputLevel(pos, this);
				worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_SLIME_PLACE, SoundCategory.PLAYERS, 0.6F, 0.8F);
				entityitem.setEntityItemStack(new ItemStack(Items.BUCKET, 1));
			} else if (te.getTroughType() == 0 && item !=null && item == Animania.bucketSlop) {
				te.setType(7);
				te.markDirty();
				worldIn.notifyBlockUpdate(pos, state, state, 7);
				worldIn.updateComparatorOutputLevel(pos, this);
				worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_SLIME_PLACE, SoundCategory.PLAYERS, 0.6F, 0.8F);
				entityitem.setEntityItemStack(new ItemStack(Items.BUCKET, 1));

			}
		}


	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{

		switch ((EnumFacing)state.getValue(FACING))
		{
		case NORTH:
		default:
			return NORTH_AABB;
		case SOUTH:
			return SOUTH_AABB;
		case WEST:
			return WEST_AABB;
		case EAST:
			return EAST_AABB;
		}
	}




	/**
	 * Called by ItemBlocks just before a block is actually set in the world, to allow for adjustments to the
	 * IBlockstate
	 */


	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		
		TileEntityTrough teChk = (TileEntityTrough) worldIn.getTileEntity(pos);

		if (teChk == null || teChk.getTroughType() == 0.0F) {

			EnumFacing enumfacing = EnumFacing.getHorizontal(MathHelper.floor((double)(placer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3).getOpposite();
			state = state.withProperty(FACING, enumfacing);
			BlockPos blockpos = pos.north();
			boolean flag = this == worldIn.getBlockState(blockpos).getBlock();
			
			if (!flag)
			{
				worldIn.setBlockState(pos, state, 3);
			}
			
			if (stack.hasDisplayName())
			{
				TileEntity tileentity = worldIn.getTileEntity(pos);

				if (tileentity instanceof TileEntityChest)
				{
					((TileEntityChest)tileentity).setCustomName(stack.getDisplayName());
				}
			}

			//System.out.println(placer.getHorizontalFacing().toString());

			if (placer.getHorizontalFacing().toString() == "south") {
				BlockPos invisipos = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ());
				worldIn.setBlockState(invisipos, Animania.blockInvisiblock.getDefaultState());
				TileEntityTrough te = (TileEntityTrough) worldIn.getTileEntity(pos);
				te.setTroughRotation(0);
			} else if (placer.getHorizontalFacing().toString() == "north") {
				BlockPos invisipos = new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ());
				worldIn.setBlockState(invisipos, Animania.blockInvisiblock.getDefaultState());
				TileEntityTrough te = (TileEntityTrough) worldIn.getTileEntity(pos);
				te.setTroughRotation(1);
			} else if (placer.getHorizontalFacing().toString() == "east") {
				BlockPos invisipos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1);
				worldIn.setBlockState(invisipos, Animania.blockInvisiblock.getDefaultState());
				TileEntityTrough te = (TileEntityTrough) worldIn.getTileEntity(pos);
				te.setTroughRotation(2);
			} else if (placer.getHorizontalFacing().toString() == "west") {
				BlockPos invisipos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1);
				worldIn.setBlockState(invisipos, Animania.blockInvisiblock.getDefaultState());
				TileEntityTrough te = (TileEntityTrough) worldIn.getTileEntity(pos);
				te.setTroughRotation(3);
			}
		}
		
		

		
	}


	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
	{
		EntityPlayer entityplayer = worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 5, false);

		String dir = "";

		if (entityplayer != null) {
			dir = entityplayer.getHorizontalFacing().toString().trim();
		}

		BlockPos blockpos = pos.west();
		BlockPos blockpos1 = pos.east();
		BlockPos blockpos2 = pos.north();
		BlockPos blockpos3 = pos.south();

		if (dir.equals("north") && !worldIn.getBlockState(blockpos).getBlock().isReplaceable(worldIn, blockpos))
		{
			return false;
		}

		if (dir.equals("east") && !worldIn.getBlockState(blockpos2).getBlock().isReplaceable(worldIn, blockpos2))
		{
			return false;
		}

		if (dir.equals("south") && !worldIn.getBlockState(blockpos1).getBlock().isReplaceable(worldIn, blockpos1))
		{
			return false;
		}

		if (dir.equals("west") && !worldIn.getBlockState(blockpos3).getBlock().isReplaceable(worldIn, blockpos3))
		{
			return false;
		}

		return true;

	}

	/**
	 * Returns a new instance of a block's tile entity class. Called on placing the block.
	 */
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityTrough();
	}

	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
	{
		return new ItemStack(Animania.blockTrough, 1);
	}

	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	{

		String dir = state.getValue(FACING).toString();


		if (dir == "south") {
			BlockPos invisipos = new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ());
			worldIn.setBlockToAir(invisipos);	
		} else if (dir == "north") {
			BlockPos invisipos = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ());
			worldIn.setBlockToAir(invisipos);	
		} else if (dir == "east") {
			BlockPos invisipos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1);
			worldIn.setBlockToAir(invisipos);	
		} else if (dir == "west") {
			BlockPos invisipos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1);
			worldIn.setBlockToAir(invisipos);	
		}

		TileEntityTrough te = (TileEntityTrough) worldIn.getTileEntity(pos);
		Random rand = new Random();

		super.breakBlock(worldIn, pos, state);
	}


	@Nullable
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Item.getItemFromBlock(Animania.blockTrough);
	}

	public boolean canDispenserPlace(World worldIn, BlockPos pos, ItemStack stack)
	{
		return stack.getMetadata() == 1 && pos.getY() >= 2 && worldIn.getDifficulty() != EnumDifficulty.PEACEFUL;
	}

	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta & 3));
	}

	public int getMetaFromState(IBlockState state)
	{
		int i = 0;
		i = i | ((EnumFacing)state.getValue(FACING)).getIndex();
		return i;
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{

		ItemStack heldItem = playerIn.getHeldItem(hand);

		TileEntityTrough te = (TileEntityTrough) worldIn.getTileEntity(pos);

		if (worldIn.isRemote) {
			return true;

		} else if (heldItem != ItemStack.EMPTY && heldItem.getItem() == Items.WATER_BUCKET && te.getTroughType() == 0) {
			playerIn.setHeldItem(hand, new ItemStack(Items.BUCKET));
			te.setType(1);
			te.markDirty();
			worldIn.notifyBlockUpdate(pos, state, state, 1);
			worldIn.updateComparatorOutputLevel(pos, this);
			worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ITEM_BUCKET_FILL, SoundCategory.PLAYERS, 0.6F, 0.8F);
			return true;
		} else if (heldItem != ItemStack.EMPTY && heldItem.getItem() == Items.WHEAT && te.getTroughType() == 0) {
			if (heldItem.getCount() == 1) {
				playerIn.setHeldItem(hand, ItemStack.EMPTY);
			} else {
				heldItem.shrink(1);
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
			if (heldItem.getCount() < 2) {
				playerIn.setHeldItem(hand, new ItemStack(Items.WATER_BUCKET));
			} else {
				heldItem.shrink(1);
				EntityItem entityitem = new EntityItem(playerIn.world, playerIn.posX + 0.2D, playerIn.posY + 0.2D, playerIn.posZ + 0.2D, new ItemStack(Items.WATER_BUCKET));
				worldIn.spawnEntity(entityitem);
			}
			te.setType(0);
			te.markDirty();
			worldIn.notifyBlockUpdate(pos, state, state, 0);
			worldIn.updateComparatorOutputLevel(pos, this);
			return true;
		} else if (heldItem != ItemStack.EMPTY && heldItem.getItem() == Items.BUCKET && te.getTroughType() == 7) {
			if (heldItem.getCount() < 2) {
				playerIn.setHeldItem(hand, new ItemStack(Animania.bucketSlop));
			} else {
				heldItem.shrink(1);
				EntityItem entityitem = new EntityItem(playerIn.world, playerIn.posX + 0.2D, playerIn.posY + 0.2D, playerIn.posZ + 0.2D, new ItemStack(Animania.bucketSlop));
				worldIn.spawnEntity(entityitem);
			}
			te.setType(0);
			te.markDirty();
			worldIn.notifyBlockUpdate(pos, state, state, 0);
			worldIn.updateComparatorOutputLevel(pos, this);
			return true;
		} else if (heldItem == ItemStack.EMPTY && te.getTroughType() == 4) {
			ItemStack bob = new ItemStack(Items.WHEAT, 1);
			EntityItem entityitem = new EntityItem(playerIn.world, playerIn.posX + 0.2D, playerIn.posY + 0.2D, playerIn.posZ + 0.2D, bob);
			worldIn.spawnEntity(entityitem);
			te.setType(0);
			te.markDirty();
			worldIn.notifyBlockUpdate(pos, state, state, 0);
			worldIn.updateComparatorOutputLevel(pos, this);
			return true;
		} else if (heldItem == ItemStack.EMPTY && te.getTroughType() == 5) {
			ItemStack bob = new ItemStack(Items.WHEAT, 1);
			EntityItem entityitem = new EntityItem(playerIn.world, playerIn.posX + 0.2D, playerIn.posY + 0.2D, playerIn.posZ + 0.2D, bob);
			worldIn.spawnEntity(entityitem);
			te.setType(4);
			te.markDirty();
			worldIn.notifyBlockUpdate(pos, state, state, 4);
			worldIn.updateComparatorOutputLevel(pos, this);
			return true;
		} else if (heldItem == ItemStack.EMPTY && te.getTroughType() == 6) {
			ItemStack bob = new ItemStack(Items.WHEAT, 1);
			EntityItem entityitem = new EntityItem(playerIn.world, playerIn.posX + 0.2D, playerIn.posY + 0.2D, playerIn.posZ + 0.2D, bob);
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


	public IBlockState withRotation(IBlockState state, Rotation rot)
	{
		return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
	}


	public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
	{
		return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
	}

	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] {FACING});
	}

	@Override
	public boolean hasComparatorInputOverride(IBlockState state)
	{
		return true;
	}

	@Override
	public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos)
	{

		TileEntityTrough te = (TileEntityTrough) worldIn.getTileEntity(pos);
		if (te.getTroughType() == 0) {
			return 0;
		} else {
			return 1;
		}
	}

	public String getName()
	{
		return name;
	}
}