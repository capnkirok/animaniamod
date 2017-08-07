package com.animania.common.items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ItemDolly extends AnimaniaItem
{

	public ItemDolly()
	{
		super("dolly");
		this.setMaxStackSize(1);
		this.setMaxDamage(100);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		Block block = world.getBlockState(pos).getBlock();
		ItemStack stack = player.getHeldItem(hand);
		if (hasChest(stack))
		{
			Vec3d vec = player.getLookVec();
			EnumFacing facing2 = EnumFacing.getFacingFromVector((float) vec.xCoord, 0f, (float) vec.zCoord);
			BlockPos pos2 = pos;
			if (!world.getBlockState(pos2).getBlock().isReplaceable(world, pos2))
			{
				pos2 = pos.offset(facing);
			}

			if (world.getBlockState(pos2).getBlock().isReplaceable(world, pos2))
			{
				boolean canPlace = Blocks.CHEST.canPlaceBlockAt(world, pos2);

				if (canPlace)
				{
					world.setBlockState(pos2, Blocks.CHEST.getDefaultState().withProperty(BlockChest.FACING, facing2.getOpposite()));
					TileEntity tile = world.getTileEntity(pos2);
					tile.readFromNBT(getChest(stack));
					tile.setPos(pos2);
					clearChest(stack);
					player.playSound(SoundEvents.BLOCK_CHEST_CLOSE, 1.0f, 0.5f);
					stack.damageItem(1, player);
					return EnumActionResult.SUCCESS;
				}
			}

		}

		if (block == Blocks.CHEST)
		{
			TileEntity te = world.getTileEntity(pos);
			if (te instanceof TileEntityChest)
			{
				if (storeChest((TileEntityChest) te, stack))
				{
					world.removeTileEntity(pos);
					world.setBlockToAir(pos);
					return EnumActionResult.SUCCESS;
				}

				return EnumActionResult.FAIL;
			}
		}

		return EnumActionResult.FAIL;
	}

	public static boolean hasChest(ItemStack stack)
	{
		if (stack.hasTagCompound())
		{
			NBTTagCompound tag = stack.getTagCompound();
			return tag.hasKey("chest");
		}
		return false;
	}

	private boolean storeChest(TileEntityChest tile, ItemStack stack)
	{
		if (tile == null)
			return false;

		if (stack.isEmpty())
			return false;

		NBTTagCompound chest = new NBTTagCompound();
		chest = tile.writeToNBT(chest);

		NBTTagCompound tag = stack.hasTagCompound() ? stack.getTagCompound() : new NBTTagCompound();
		if (tag.hasKey("chest"))
			return false;

		tag.setTag("chest", chest);
		stack.setTagCompound(tag);
		return true;
	}

	private void clearChest(ItemStack stack)
	{
		if (stack.hasTagCompound())
		{
			NBTTagCompound tag = stack.getTagCompound();
			tag.removeTag("chest");
		}
	}

	private NBTTagCompound getChest(ItemStack stack)
	{
		if (stack.hasTagCompound())
		{
			NBTTagCompound tag = stack.getTagCompound();
			return tag.getCompoundTag("chest");
		}
		return null;
	}

}
