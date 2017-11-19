package com.animania.common.blocks;

import java.util.List;
import java.util.Random;

import com.animania.Animania;
import com.animania.common.capabilities.CapabilityRefs;
import com.animania.common.capabilities.ICapabilityPlayer;
import com.animania.common.entities.rodents.EntityHamster;
import com.animania.common.handler.ItemHandler;
import com.animania.common.tileentities.TileEntityHamsterWheel;
import com.animania.compat.top.providers.TOPInfoProvider;

import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class BlockHamsterWheel extends BlockContainer implements TOPInfoProvider
{

	private String name = "block_hamster_wheel";
	public static final PropertyDirection FACING = BlockHorizontal.FACING;

	public BlockHamsterWheel()
	{
		super(Material.IRON, MapColor.GRAY);
		this.setRegistryName(new ResourceLocation(Animania.MODID, this.name));
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		ForgeRegistries.BLOCKS.register(this);
		this.setUnlocalizedName(Animania.MODID + "_" + this.name);
		this.setCreativeTab(Animania.TabAnimaniaResources);
		this.setTickRandomly(true);
		this.setHardness(1.4f);
		this.setResistance(3.4F);
		this.useNeighborBrightness = true;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityHamsterWheel();
	}

	// @Override
	// public boolean isFullyOpaque(IBlockState state)
	// {
	// return false;
	// }

	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}
	
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess p_193383_1_, IBlockState p_193383_2_, BlockPos p_193383_3_, EnumFacing p_193383_4_)
	{
		return BlockFaceShape.UNDEFINED;
	}

	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		TileEntityHamsterWheel te = (TileEntityHamsterWheel) world.getTileEntity(pos);

		if (!te.isRunning())
		{

			if (player.hasCapability(CapabilityRefs.CAPS, null))
			{
				ICapabilityPlayer cap = player.getCapability(CapabilityRefs.CAPS, null);

				NBTTagCompound hamsternbt = cap.getAnimal();

				if (!hamsternbt.hasNoTags() && cap.isCarrying())

				{
					EntityHamster hamster = (EntityHamster) EntityList.createEntityByIDFromName(new ResourceLocation(Animania.MODID, "hamster"), world);
					hamster.readEntityFromNBT(hamsternbt);
					if (hamster.getFed() && !hamster.isInBall())
					{
						te.setHamster(hamster);
						te.markDirty();
						cap.setAnimal(new NBTTagCompound());
						cap.setCarrying(false);
						cap.setType("");
						player.swingArm(EnumHand.MAIN_HAND);
						Random rand = new Random();
						player.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);

						return true;
					}
				}
			}

		}
		
		if (!player.getHeldItem(hand).isEmpty() && player.getHeldItem(hand).getItem() == ItemHandler.hamsterFood)
		{
			ItemStack held = player.getHeldItem(hand);
			ItemStack remainder = te.getItemHandler().insertItem(0, new ItemStack(ItemHandler.hamsterFood), false);

			if (!player.isCreative() && remainder.isEmpty())
				held.shrink(1);

			return true;
		}

		if (!world.isRemote && player.isSneaking() && hand == EnumHand.MAIN_HAND)
		{
			ItemStack food = te.getItemHandler().getStackInSlot(0);
			if (food.isEmpty())
				player.sendStatusMessage(new TextComponentString(te.getEnergy() + "/" + te.getPower().getMaxEnergyStored() + " RF"), true);
			else
				player.sendStatusMessage(new TextComponentString(te.getEnergy() + "/" + te.getPower().getMaxEnergyStored() + " RF, " + food.getCount() + " " + food.getDisplayName()), true);

		}

		return false;
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	{
		TileEntityHamsterWheel te = (TileEntityHamsterWheel) worldIn.getTileEntity(pos);
		if (te != null)
		{
			te.ejectHamster();
			InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), te.getItemHandler().getStackInSlot(0));
		}
		super.breakBlock(worldIn, pos, state);
	}

	@Override
	public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, IBlockState blockState, IProbeHitData data)
	{

		TileEntity te = world.getTileEntity(data.getPos());
		if (te instanceof TileEntityHamsterWheel)
		{
			if (mode == ProbeMode.NORMAL)
			{
				TileEntityHamsterWheel wheel = (TileEntityHamsterWheel) te;
				ItemStack food = wheel.getItemHandler().getStackInSlot(0);

				if (!food.isEmpty())
					probeInfo.item(food);
			}
		}
	}

	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	{
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}

	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
	}

	public IBlockState getStateFromMeta(int meta)
	{
		EnumFacing enumfacing = EnumFacing.getFront(meta);

		if (enumfacing.getAxis() == EnumFacing.Axis.Y)
		{
			enumfacing = EnumFacing.NORTH;
		}

		return this.getDefaultState().withProperty(FACING, enumfacing);
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	public int getMetaFromState(IBlockState state)
	{
		return ((EnumFacing) state.getValue(FACING)).getIndex();
	}

	/**
	 * Returns the blockstate with the given rotation from the passed
	 * blockstate. If inapplicable, returns the passed blockstate.
	 */
	public IBlockState withRotation(IBlockState state, Rotation rot)
	{
		return state.withProperty(FACING, rot.rotate((EnumFacing) state.getValue(FACING)));
	}

	/**
	 * Returns the blockstate with the given mirror of the passed blockstate. If
	 * inapplicable, returns the passed blockstate.
	 */
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
	{
		return state.withRotation(mirrorIn.toRotation((EnumFacing) state.getValue(FACING)));
	}

	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] { FACING });
	}

}
