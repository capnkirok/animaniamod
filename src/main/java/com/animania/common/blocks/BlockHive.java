package com.animania.common.blocks;

import com.animania.Animania;
import com.animania.common.handler.BlockHandler;
import com.animania.common.handler.ItemHandler;
import com.animania.common.tileentities.TileEntityHive;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class BlockHive extends BlockContainer
{
	private String name = "block_hive";
	public static final PropertyDirection FACING = BlockDirectional.FACING;
	
	public BlockHive()
	{
		super(Material.WOOD, MapColor.YELLOW);
		this.setSoundType(SoundType.WOOD);
		this.setRegistryName(new ResourceLocation(Animania.MODID, this.name));
		this.setUnlocalizedName(Animania.MODID + "_" + this.name);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		this.setCreativeTab(Animania.TabAnimaniaResources);
		this.setHardness(1.3f);
		this.setResistance(0.3f);
		BlockHandler.blocks.add(this);
		Item item = new ItemBlock(this);
		item.setRegistryName(new ResourceLocation(Animania.MODID, "bee_hive"));
		ForgeRegistries.ITEMS.register(item);
	}
	
	public BlockHive(Material mat, MapColor color)
	{
		super(mat, color);
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
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{

		ItemStack heldItem = player.getHeldItem(hand);
		TileEntityHive hive = (TileEntityHive) world.getTileEntity(pos);

		if (hive != null)
		{
			// EMPTY LIQUIDS
			if (!heldItem.isEmpty() && FluidUtil.getFluidHandler(heldItem) != null && FluidUtil.getFluidContained(heldItem) == null && hive.fluidHandler.getFluid() != null && hive.fluidHandler.getFluid().amount >= 1000)
			{
				FluidStack fluidStack = hive.fluidHandler.drain(1000, true);
				if (!player.isCreative())
				{
					IFluidHandlerItem handler;
					if (heldItem.getCount() >= 1)
					{
						ItemStack heldItem1 = heldItem.copy();
						heldItem1.setCount(1);
						handler = FluidUtil.getFluidHandler(heldItem1);

						handler.fill(fluidStack, true);
						ItemStack newstack = handler.getContainer();

						if (heldItem.getCount() > 1)
						{
							heldItem.shrink(1);
							player.inventory.addItemStackToInventory(newstack);
						}
						else
							player.setHeldItem(hand, newstack);

						return true;
					}

				}

				world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ITEM_BUCKET_FILL, SoundCategory.PLAYERS, 0.6F, 0.8F);
				return true;

			}
			else if(!heldItem.isEmpty() && heldItem.getItem() == Items.GLASS_BOTTLE && hive.fluidHandler.getFluid() != null && hive.fluidHandler.getFluid().amount >= 1000)
			{
				FluidStack fluidStack = hive.fluidHandler.drain(1000, true);
				ItemStack honeyBottle = new ItemStack(ItemHandler.honeyJar);
				
				if (!player.isCreative())
				{
					IFluidHandlerItem handler;
					if (heldItem.getCount() >= 1)
					{
						ItemStack heldItem1 = honeyBottle.copy();
						heldItem1.setCount(1);
						handler = FluidUtil.getFluidHandler(heldItem1);

						handler.fill(fluidStack, true);
						ItemStack newstack = handler.getContainer();

						if (heldItem.getCount() > 1)
						{
							heldItem.shrink(1);
							player.inventory.addItemStackToInventory(newstack);
						}
						else
							player.setHeldItem(hand, newstack);

						return true;
					}

				}

				world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ITEM_BUCKET_FILL, SoundCategory.PLAYERS, 0.6F, 0.8F);
				return true;
			}
			
			if (!world.isRemote && player.isSneaking() && hand == EnumHand.MAIN_HAND)
			{
				int honey = hive.fluidHandler.getFluidAmount();

				player.sendStatusMessage(new TextComponentString("Honey stored: " + honey + "mB"), true);
			}

		}
		
		return true;
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

    public int getMetaFromState(IBlockState state)
    {
        return ((EnumFacing)state.getValue(FACING)).getIndex();
    }

	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	{
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] {FACING });
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityHive();
	}

}
