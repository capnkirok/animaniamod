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

import PropertyDirection;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.level.IBlockAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MaterialColor;

public class BlockHamsterWheel extends BaseEntityBlock implements TOPInfoProvider
{

	private String name = "block_hamster_wheel";
	public static final PropertyDirection FACING = BlockHorizontal.FACING;

	public BlockHamsterWheel()
	{
		super(Material.IRON, MaterialColor.GRAY);
		this.setRegistryName(new ResourceLocation(Animania.MODID, this.name));
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, Direction.NORTH));
		BlockHandler.blocks.add(this);
		this.setUnlocalizedName(Animania.MODID + "_" + this.name);
		this.setCreativeTab(Animania.TabAnimaniaResources);
		this.setTickRandomly(true);
		this.setHardness(1.4f);
		this.setResistance(3.4F);
		this.useNeighborBrightness = true;
	}

	@Override
	public TileEntity createNewTileEntity(Level levelIn, int meta)
	{
		return new TileEntityHamsterWheel();
	}

	@Override
	public boolean isOpaqueCube(BlockState state)
	{
		return false;
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess p_193383_1_, BlockState p_193383_2_, BlockPos p_193383_3_, Direction p_193383_4_)
	{
		return BlockFaceShape.UNDEFINED;
	}

	@Override
	public boolean isFullCube(BlockState state)
	{
		return false;
	}

	@Override
	public boolean onBlockActivated(Level level, BlockPos pos, BlockState state, PlayerEntity player, EnumHand hand, Direction facing, float hitX, float hitY, float hitZ)
	{
		TileEntityHamsterWheel te = (TileEntityHamsterWheel) level.getTileEntity(pos);

		if (!te.isRunning())
		{

			if (player.hasCapability(CapabilityRefs.CAPS, null))
			{
				ICapabilityPlayer cap = player.getCapability(CapabilityRefs.CAPS, null);

				CompoundTag hamsternbt = cap.getAnimal();

				if (!hamsternbt.hasNoTags() && cap.isCarrying() && cap.getType().equals("hamster"))

				{
					EntityHamster hamster = (EntityHamster) EntityList.createEntityByIDFromName(new ResourceLocation(Animania.MODID, "hamster"), level);
					hamster.readFromNBT(hamsternbt);
					if (hamster.getFed() && !hamster.isInBall())
					{
						te.setHamster(hamster);
						te.markDirty();
						cap.setAnimal(new CompoundTag());
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

		if (!level.isRemote && player.isSneaking() && hand == EnumHand.MAIN_HAND)
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
	public void breakBlock(Level levelIn, BlockPos pos, BlockState state)
	{
		TileEntityHamsterWheel te = (TileEntityHamsterWheel) levelIn.getTileEntity(pos);
		if (te != null)
		{
			te.ejectHamster();
			InventoryHelper.spawnItemStack(levelIn, pos.getX(), pos.getY(), pos.getZ(), te.getItemHandler().getStackInSlot(0));
		}
		super.breakBlock(levelIn, pos, state);
	}

	@Override
	@net.minecraftforge.fml.common.Optional.Method(modid = CompatHandler.THEONEPROBE_ID)
	public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, PlayerEntity player, Level level, BlockState blockState, IProbeHitData data)
	{

		TileEntity te = level.getTileEntity(data.getPos());
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
	public BlockState getStateForPlacement(Level levelIn, BlockPos pos, Direction facing, float hitX, float hitY, float hitZ, int meta, LivingEntity placer)
	{
		return this.defaultBlockState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}

	@Override
	public void onBlockPlacedBy(Level levelIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack)
	{
		levelIn.setBlock(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
	}

	@Override
	public BlockState getStateFromMeta(int meta)
	{
		Direction enumfacing = Direction.getFront(meta);

		if (enumfacing.getAxis() == Direction.Axis.Y)
		{
			enumfacing = Direction.NORTH;
		}

		return this.defaultBlockState().withProperty(FACING, enumfacing);
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	@Override
	public int getMetaFromState(BlockState state)
	{
		return state.getValue(FACING).getIndex();
	}

	/**
	 * Returns the blockstate with the given rotation from the passed
	 * blockstate. If inapplicable, returns the passed blockstate.
	 */
	@Override
	public BlockState withRotation(BlockState state, Rotation rot)
	{
		return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
	}

	/**
	 * Returns the blockstate with the given mirror of the passed blockstate. If
	 * inapplicable, returns the passed blockstate.
	 */
	@Override
	public BlockState withMirror(BlockState state, Mirror mirrorIn)
	{
		return state.withRotation(mirrorIn.toRotation(state.getValue(FACING)));
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] { FACING });
	}

	@Override
	public boolean hasComparatorInputOverride(BlockState state)
	{
		return true;
	}

	@Override
	public int getComparatorInputOverride(BlockState blockState, Level levelIn, BlockPos pos)
	{

		TileEntityHamsterWheel te = (TileEntityHamsterWheel) levelIn.getTileEntity(pos);
		if (te.getHamster() != null)
			return 15;
		else
			return 0;
	}

}
