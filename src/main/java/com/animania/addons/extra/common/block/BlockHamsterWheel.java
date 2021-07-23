package com.animania.addons.extra.common.block;

import com.animania.Animania;
import com.animania.addons.extra.common.capabilities.CapabilityRefs;
import com.animania.addons.extra.common.capabilities.ICapabilityPlayer;
import com.animania.addons.extra.common.entity.rodents.EntityHamster;
import com.animania.addons.extra.common.handler.ExtraAddonItemHandler;
import com.animania.addons.extra.common.tileentity.TileEntityHamsterWheel;
import com.animania.common.handler.BlockHandler;
import com.animania.common.handler.CompatHandler;
import com.animania.compat.top.providers.TOPInfoProvider;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.ContainerBlock;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockHamsterWheel extends ContainerBlock implements TOPInfoProvider
{

	private String name = "block_hamster_wheel";
	public static final PropertyDirection FACING = BlockHorizontal.FACING;

	public BlockHamsterWheel()
	{
		super(Material.IRON, MaterialColor.GRAY);
		this.setRegistryName(new ResourceLocation(Animania.MODID, this.name));
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		BlockHandler.blocks.add(this);
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
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, PlayerEntity player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		TileEntityHamsterWheel te = (TileEntityHamsterWheel) world.getTileEntity(pos);

		if (!te.isRunning())
		{

			if (player.hasCapability(CapabilityRefs.CAPS, null))
			{
				ICapabilityPlayer cap = player.getCapability(CapabilityRefs.CAPS, null);

				CompoundNBT hamsternbt = cap.getAnimal();

				if (!hamsternbt.hasNoTags() && cap.isCarrying() && cap.getType().equals("hamster"))

				{
					EntityHamster hamster = (EntityHamster) EntityList.createEntityByIDFromName(new ResourceLocation(Animania.MODID, "hamster"), world);
					hamster.readFromNBT(hamsternbt);
					if (hamster.getFed() && !hamster.isInBall())
					{
						te.setHamster(hamster);
						te.markDirty();
						cap.setAnimal(new CompoundNBT());
						cap.setCarrying(false);
						cap.setType("");
						player.swingArm(EnumHand.MAIN_HAND);
						player.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 1.0F, (Animania.RANDOM.nextFloat() - Animania.RANDOM.nextFloat()) * 0.2F + 1.0F);

						return true;
					}
				}
			}

		}

		if (!player.getHeldItem(hand).isEmpty() && player.getHeldItem(hand).getItem() == ExtraAddonItemHandler.hamsterFood)
		{
			ItemStack held = player.getHeldItem(hand);
			ItemStack remainder = te.getItemHandler().insertItem(0, new ItemStack(ExtraAddonItemHandler.hamsterFood), false);

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
	@net.minecraftforge.fml.common.Optional.Method(modid = CompatHandler.THEONEPROBE_ID)
	public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, PlayerEntity player, World world, IBlockState blockState, IProbeHitData data)
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
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, LivingEntity placer)
	{
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, LivingEntity placer, ItemStack stack)
	{
		worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
	}

	@Override
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
	@Override
	public int getMetaFromState(IBlockState state)
	{
		return state.getValue(FACING).getIndex();
	}

	/**
	 * Returns the blockstate with the given rotation from the passed
	 * blockstate. If inapplicable, returns the passed blockstate.
	 */
	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot)
	{
		return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
	}

	/**
	 * Returns the blockstate with the given mirror of the passed blockstate. If
	 * inapplicable, returns the passed blockstate.
	 */
	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
	{
		return state.withRotation(mirrorIn.toRotation(state.getValue(FACING)));
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] { FACING });
	}

	@Override
	public boolean hasComparatorInputOverride(IBlockState state)
	{
		return true;
	}

	@Override
	public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos)
	{

		TileEntityHamsterWheel te = (TileEntityHamsterWheel) worldIn.getTileEntity(pos);
		if (te.getHamster() != null)
			return 15;
		else
			return 0;
	}

}
