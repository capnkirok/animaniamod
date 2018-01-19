package com.animania.common.blocks;

import com.animania.Animania;
import com.animania.common.handler.BlockHandler;
import com.animania.common.handler.ItemHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.tileentities.TileEntityCheeseMold;
import com.animania.common.tileentities.TileEntityTrough;
import com.animania.compat.top.providers.TOPInfoProvider;
import com.animania.config.AnimaniaConfig;

import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelFluid.FluidLoader;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockCheeseMold extends BlockContainer implements TOPInfoProvider
{

	public static final PropertyEnum<BlockCheeseMold.EnumType> VARIANT = PropertyEnum.<BlockCheeseMold.EnumType>create("variant", BlockCheeseMold.EnumType.class);
	protected static final AxisAlignedBB AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.625D, 1.0D);

	public BlockCheeseMold()
	{
		super(Material.WOOD, MapColor.WOOD);
		this.setRegistryName(Animania.MODID + ":" + "cheese_mold");
		this.setUnlocalizedName(Animania.MODID + "_" + "cheese_mold");
		this.setCreativeTab(Animania.TabAnimaniaResources);
		this.setSoundType(SoundType.WOOD);
		this.setHardness(0.9f);
		this.setResistance(1.2f);
		GameRegistry.register(this);
		GameRegistry.register(new ItemBlock(this), new ResourceLocation(Animania.MODID, "cheese_mold"));
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumType.EMPTY));

	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state)
	{
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityCheeseMold();
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
	{
		return new ItemStack(ItemHandler.cheeseMold);
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(VARIANT, EnumType.byMetadata(meta));
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	@Override
	public int getMetaFromState(IBlockState state)
	{
		return ((EnumType) state.getValue(VARIANT)).getMetadata();
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] { VARIANT });
	}

	@Override
	public boolean isFullyOpaque(IBlockState state)
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

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return AABB;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
	{
		return AABB;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{

		ItemStack stack = player.getHeldItem(hand);
		TileEntityCheeseMold te = (TileEntityCheeseMold) world.getTileEntity(pos);
		if (!world.isRemote)
		{
			if (!stack.isEmpty() && te != null)
			{
				if (AnimaniaHelper.hasFluid(stack, BlockHandler.fluidMilkFriesian) || AnimaniaHelper.hasFluid(stack, BlockHandler.fluidMilkHolstein) || AnimaniaHelper.hasFluid(stack, BlockHandler.fluidMilkGoat) || AnimaniaHelper.hasFluid(stack, BlockHandler.fluidMilkSheep) || AnimaniaHelper.hasFluid(stack, FluidRegistry.WATER))
				{
					if (te.getFluidHandler().getFluidAmount() == 0 && te.getItemHandler().getStackInSlot(0).isEmpty())
					{
						ItemStack subStack = stack.copy().splitStack(1);
						IFluidHandlerItem handler = FluidUtil.getFluidHandler(subStack);
						te.getFluidHandler().fill(FluidUtil.getFluidContained(subStack), true);

						if (!player.isCreative())
						{
							handler.drain(1000, true);
							stack.shrink(1);
							ItemStack newStack = handler.getContainer();
							AnimaniaHelper.addItem(player, newStack);
							return true;
						}
					}
				}
			}

			if (te != null)
			{
				if (!te.getItemHandler().getStackInSlot(0).isEmpty() && !player.isSneaking())
				{
					player.inventory.addItemStackToInventory(te.getItemHandler().getStackInSlot(0));
					return true;
				}

				if (player.isSneaking())
				{
					int progress = te.getProgress();
					if (te.getItemHandler().getStackInSlot(0).isEmpty())
						player.sendStatusMessage(new TextComponentString((int) (((float) progress / (float) AnimaniaConfig.gameRules.cheeseMaturityTime) * 100) + "%"), true);
					else
						player.sendStatusMessage(new TextComponentString("100%"), true);
					return true;
				}
			}
		}

		return true;
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	{
		TileEntityCheeseMold te = (TileEntityCheeseMold) worldIn.getTileEntity(pos);
		if (te != null)
			InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), te.getItemHandler().getStackInSlot(0));
		super.breakBlock(worldIn, pos, state);
	}

	public static enum EnumType implements IStringSerializable
	{
		EMPTY(0, "empty"),
		HOLSTEIN_MILK(1, "holstein_milk"),
		HOLSTEIN_CHEESE(2, "holstein_cheese"),
		FRIESIAN_MILK(3, "friesian_milk"),
		FRIESIAN_CHEESE(4, "friesian_cheese"),
		GOAT_MILK(5, "goat_milk"),
		GOAT_CHEESE(6, "goat_cheese"),
		SHEEP_MILK(7, "sheep_milk"),
		SHEEP_CHEESE(8, "sheep_cheese"),
		WATER(9, "water"),
		SALT(10, "salt");


		/** Array of the Block's BlockStates */
		private static final BlockCheeseMold.EnumType[] META = new BlockCheeseMold.EnumType[values().length];
		private final int meta;
		private final String name;

		private EnumType(int meta, String name)
		{
			this.meta = meta;
			this.name = name;
		}

		/**
		 * Returns the EnumType's metadata value.
		 */
		public int getMetadata()
		{
			return this.meta;
		}

		public String toString()
		{
			return this.name;
		}

		/**
		 * Returns an EnumType for the BlockState from a metadata value.
		 */
		public static BlockCheeseMold.EnumType byMetadata(int meta)
		{
			if (meta < 0 || meta >= META.length)
			{
				meta = 0;
			}

			return META[meta];
		}

		public String getName()
		{
			return this.name;
		}

		static
		{
			for (BlockCheeseMold.EnumType blockstone$enumtype : values())
			{
				META[blockstone$enumtype.getMetadata()] = blockstone$enumtype;
			}
		}

	}

	@Override
	public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, IBlockState blockState, IProbeHitData data)
	{
		TileEntity te = world.getTileEntity(data.getPos());
		if (te instanceof TileEntityCheeseMold)
		{
			TileEntityCheeseMold mold = (TileEntityCheeseMold) te;
			ItemStack stack = mold.getItemHandler().getStackInSlot(0);
			FluidStack fluid = mold.getFluidHandler().getFluid();
			int progress = mold.getProgress();

			probeInfo.text(I18n.translateToLocal("text.waila.aging") + ":");
			if (stack.isEmpty())
				probeInfo.progress((int) (((float) progress / (float) AnimaniaConfig.gameRules.cheeseMaturityTime) * 100), 100, probeInfo.defaultProgressStyle().suffix("%"));
			else
				probeInfo.progress(100, 100, probeInfo.defaultProgressStyle().suffix("%"));

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
