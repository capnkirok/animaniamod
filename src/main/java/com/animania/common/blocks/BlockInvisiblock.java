package com.animania.common.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.api.interfaces.IFoodProviderBlock;
import com.animania.common.handler.BlockHandler;
import com.animania.common.handler.CompatHandler;
import com.animania.common.tileentities.TileEntityInvisiblock;
import com.animania.common.tileentities.TileEntityTrough;
import com.animania.compat.top.providers.TOPInfoProvider;

import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.core.BlockPos;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.InteractionHand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemHandlerHelper;

public class BlockInvisiblock extends BaseEntityBlock implements TOPInfoProvider, IFoodProviderBlock
{

	protected static final AABB Invisiblock_AABB = new AABB(0.1D, 0.0D, 0.1D, 0.8D, 0.3D, 0.8D);

	private String name = "invisiblock";

	public BlockInvisiblock()
	{
		super(Material.WOOD);
		this.setRegistryName(new ResourceLocation(Animania.MODID, this.name));
		this.setBlockUnbreakable();
		BlockHandler.blocks.add(this);
		this.setUnlocalizedName(Animania.MODID + "_" + this.name);
		this.setCreativeTab(null);
	}

	@Override
	public void onEntityCollidedWithBlock(Level levelIn, BlockPos pos, BlockState state, Entity entityIn)
	{
		if (entityIn instanceof EntityItem)
		{

			BlockPos pos1 = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ());
			BlockPos pos2 = new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ());
			BlockPos pos3 = new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1);
			BlockPos pos4 = new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1);

			Block chk1 = levelIn.getBlockState(pos1).getBlock();
			Block chk2 = levelIn.getBlockState(pos2).getBlock();
			Block chk3 = levelIn.getBlockState(pos3).getBlock();
			Block chk4 = levelIn.getBlockState(pos4).getBlock();

			if (chk1 != null && chk1 == BlockHandler.blockTrough)
			{
				String state1 = levelIn.getBlockState(pos1).toString().substring(29);
				state1 = state1.substring(0, state1.length() - 1);
				if (state1.contains("south"))
				{
					levelIn.updateComparatorOutputLevel(pos, chk1);
					pos = pos1;
					state = levelIn.getBlockState(pos1);
					chk1.onEntityCollidedWithBlock(levelIn, pos1, state, entityIn);
				}
			}

			if (chk2 != null && chk2 == BlockHandler.blockTrough)
			{
				String state2 = levelIn.getBlockState(pos2).toString().substring(29);
				state2 = state2.substring(0, state2.length() - 1);
				if (state2.contains("north"))
				{
					levelIn.updateComparatorOutputLevel(pos, chk2);
					pos = pos2;
					state = levelIn.getBlockState(pos2);
					chk2.onEntityCollidedWithBlock(levelIn, pos2, state, entityIn);

				}
			}

			if (chk3 != null && chk3 == BlockHandler.blockTrough)
			{
				String state3 = levelIn.getBlockState(pos3).toString().substring(29);
				state3 = state3.substring(0, state3.length() - 1);
				if (state3.contains("west"))
				{
					levelIn.updateComparatorOutputLevel(pos, chk3);
					pos = pos3;
					state = levelIn.getBlockState(pos3);
					chk3.onEntityCollidedWithBlock(levelIn, pos3, state, entityIn);

				}
			}

			if (chk4 != null && chk4 == BlockHandler.blockTrough)
			{
				String state4 = levelIn.getBlockState(pos4).toString().substring(29);
				state4 = state4.substring(0, state4.length() - 1);
				if (state4.contains("east"))
				{
					levelIn.updateComparatorOutputLevel(pos, chk4);
					pos = pos4;
					state = levelIn.getBlockState(pos4);
					chk4.onEntityCollidedWithBlock(levelIn, pos4, state, entityIn);
				}
			}

		}

	}

	@Override
	@SideOnly(Dist.CLIENT)
	public BlockRenderLayer getBlockLayer()
	{
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public boolean isTopSolid(BlockState state)
	{
		return false;
	}

	@Deprecated
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
	public boolean isFullBlock(BlockState state)
	{
		return false;
	}

	@Override
	protected boolean canSilkHarvest()
	{
		return false;
	}

	@Override
	public boolean isOpaqueCube(BlockState state)
	{
		return false;
	}

	@Override
	public AxisAlignedBB getBoundingBox(BlockState state, IBlockAccess source, BlockPos pos)
	{
		return BlockInvisiblock.Invisiblock_AABB;
	}

	@Override
	@SideOnly(Dist.CLIENT)
	public boolean shouldSideBeRendered(BlockState blockState, IBlockAccess blockAccess, BlockPos pos, Direction side)
	{
		BlockState BlockState = blockAccess.getBlockState(pos.offset(side));
		Block block = BlockState.getBlock();

		return block == this ? false : super.shouldSideBeRendered(blockState, blockAccess, pos, side);
	}

	@Override
	public void breakBlock(Level levelIn, BlockPos pos, BlockState state)
	{

		BlockPos pos1 = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ());
		BlockPos pos2 = new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ());
		BlockPos pos3 = new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1);
		BlockPos pos4 = new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1);

		Block block1 = levelIn.getBlockState(pos1).getBlock();
		Block block2 = levelIn.getBlockState(pos2).getBlock();
		Block block3 = levelIn.getBlockState(pos3).getBlock();
		Block block4 = levelIn.getBlockState(pos4).getBlock();

		if (block1 == BlockHandler.blockTrough)
		{
			TileEntityTrough te = (TileEntityTrough) levelIn.getTileEntity(pos1);
			if (te != null && te.getTroughRotation() == 1 && !levelIn.isClientSide)
			{
				levelIn.setBlockToAir(pos1);
				super.breakBlock(levelIn, pos1, state);
			}
		}

		if (block2 == BlockHandler.blockTrough)
		{
			TileEntityTrough te = (TileEntityTrough) levelIn.getTileEntity(pos2);
			if (te != null && te.getTroughRotation() == 0 && !levelIn.isClientSide)
			{
				levelIn.setBlockToAir(pos2);
				super.breakBlock(levelIn, pos2, state);
			}
		}

		if (block3 == BlockHandler.blockTrough)
		{
			TileEntityTrough te = (TileEntityTrough) levelIn.getTileEntity(pos3);
			if (te != null && te.getTroughRotation() == 2 && !levelIn.isClientSide)
			{
				levelIn.setBlockToAir(pos3);
				super.breakBlock(levelIn, pos3, state);
			}

		}

		if (block4 == BlockHandler.blockTrough)
		{
			TileEntityTrough te = (TileEntityTrough) levelIn.getTileEntity(pos4);
			if (te != null && te.getTroughRotation() == 3 && !levelIn.isClientSide)
			{
				levelIn.setBlockToAir(pos4);
				super.breakBlock(levelIn, pos4, state);
			}

		}

		super.breakBlock(levelIn, pos, state);
	}

	@Override
	public int quantityDropped(Random random)
	{
		return 1;
	}

	@Override
	public boolean onBlockActivated(Level levelIn, BlockPos pos, BlockState state, Player playerIn, InteractionHand hand, Direction facing, float hitX, float hitY, float hitZ)
	{

		BlockPos pos1 = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ());
		BlockPos pos2 = new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ());
		BlockPos pos3 = new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1);
		BlockPos pos4 = new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1);

		Block chk1 = levelIn.getBlockState(pos1).getBlock();
		Block chk2 = levelIn.getBlockState(pos2).getBlock();
		Block chk3 = levelIn.getBlockState(pos3).getBlock();
		Block chk4 = levelIn.getBlockState(pos4).getBlock();

		if (chk1 != null && chk1 == BlockHandler.blockTrough)
		{
			String state1 = levelIn.getBlockState(pos1).toString().substring(29);
			state1 = state1.substring(0, state1.length() - 1);
			if (state1.contains("south"))
			{
				levelIn.updateComparatorOutputLevel(pos, chk1);
				pos = pos1;
				state = levelIn.getBlockState(pos1);
				return chk1.onBlockActivated(levelIn, pos1, state, playerIn, hand, facing, hitX, hitY, hitZ);

			}
		}

		if (chk2 != null && chk2 == BlockHandler.blockTrough)
		{
			String state2 = levelIn.getBlockState(pos2).toString().substring(29);
			state2 = state2.substring(0, state2.length() - 1);
			if (state2.contains("north"))
			{
				levelIn.updateComparatorOutputLevel(pos, chk2);
				pos = pos2;
				state = levelIn.getBlockState(pos2);
				return chk2.onBlockActivated(levelIn, pos2, state, playerIn, hand, facing, hitX, hitY, hitZ);

			}
		}

		if (chk3 != null && chk3 == BlockHandler.blockTrough)
		{
			String state3 = levelIn.getBlockState(pos3).toString().substring(29);
			state3 = state3.substring(0, state3.length() - 1);
			if (state3.contains("west"))
			{
				levelIn.updateComparatorOutputLevel(pos, chk3);
				pos = pos3;
				state = levelIn.getBlockState(pos3);
				return chk3.onBlockActivated(levelIn, pos3, state, playerIn, hand, facing, hitX, hitY, hitZ);

			}
		}

		if (chk4 != null && chk4 == BlockHandler.blockTrough)
		{
			String state4 = levelIn.getBlockState(pos4).toString().substring(29);
			state4 = state4.substring(0, state4.length() - 1);
			if (state4.contains("east"))
			{
				levelIn.updateComparatorOutputLevel(pos, chk4);
				pos = pos4;
				state = levelIn.getBlockState(pos4);
				return chk4.onBlockActivated(levelIn, pos4, state, playerIn, hand, facing, hitX, hitY, hitZ);
			}
		}

		return false;
	}

	@Override
	@Nullable
	public RItem getItemDropped(BlockState state, Random rand, int fortune)
	{
		return RItem.getItemFromBlock(BlockHandler.blockTrough);
	}

	@Override
	public boolean hasComparatorInputOverride(BlockState state)
	{
		return true;
	}

	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, Level level, BlockPos pos, Player player)
	{
		return new ItemStack(BlockHandler.blockTrough);
	}

	@Override
	public TileEntity createNewTileEntity(Level levelIn, int meta)
	{
		return new TileEntityInvisiblock();
	}

	@Override
	public int getComparatorInputOverride(BlockState blockState, Level levelIn, BlockPos pos)
	{

		TileEntityInvisiblock te = (TileEntityInvisiblock) levelIn.getTileEntity(pos);
		if (te.getTrough() != null)
		{
			if (!te.getTrough().itemHandler.getStackInSlot(0).isEmpty())
				return ItemHandlerHelper.calcRedstoneFromInventory(te.getTrough().itemHandler);

			if (te.getTrough().fluidHandler.getFluid() != null)
			{
				int fluid = te.getTrough().fluidHandler.getFluidAmount();
				return fluid / 66;
			}
		}
		return 0;
	}

	@Override
	@net.minecraftforge.fml.common.Optional.Method(modid = CompatHandler.THEONEPROBE_ID)
	public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, Player player, Level level, BlockState blockState, IProbeHitData data)
	{
		TileEntity te = level.getTileEntity(data.getPos());
		if ((te instanceof TileEntityInvisiblock invis) && (invis.getTrough() != null))
		{
			TileEntityTrough trough = invis.getTrough();
			ItemStack stack = trough.itemHandler.getStackInSlot(0);
			FluidStack fluid = trough.fluidHandler.getFluid();

			if (mode == ProbeMode.NORMAL)
			{

				if (!stack.isEmpty())
				{
					probeInfo.horizontal();
					probeInfo.item(stack);
				}
				if (fluid != null)
				{
					ItemStack bucket = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, fluid.getFluid());
					probeInfo.horizontal().item(bucket).text(fluid.getLocalizedName() + ", " + fluid.amount + "mB");

				}
			}
		}

	}

}
