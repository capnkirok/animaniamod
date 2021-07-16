package com.animania.addons.farm.common.block;

import java.util.Random;

import com.animania.Animania;
import com.animania.addons.farm.common.handler.FarmAddonBlockHandler;
import com.animania.common.handler.BlockHandler;
import com.animania.config.AnimaniaConfig;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import PropertyInteger;

public class BlockCheese extends Block
{

	public static final PropertyInteger BITES = PropertyInteger.create("bites", 0, 3);
	private static final AxisAlignedBB[] AABB = new AxisAlignedBB[] { new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.5D, 0.9375D), new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.5D, 0.9375D), new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.5D, 0.5D), new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.5D, 0.5D, 0.5D) };

	public BlockCheese(String name)
	{
		super(Material.CAKE, MapColor.YELLOW);
		this.setDefaultState(this.blockState.getBaseState().withProperty(BITES, Integer.valueOf(0)));
		this.setRegistryName(Animania.MODID + ":" + name);
		this.setUnlocalizedName(Animania.MODID + "_" + name);
		this.setCreativeTab(Animania.TabAnimaniaResources);
		this.setSoundType(SoundType.CLOTH);
		this.setHardness(0.6f);
		BlockHandler.blocks.add(this);
		Item item = new ItemBlock(this);
		item.setRegistryName(new ResourceLocation(Animania.MODID, name.substring(7) + "_cheese_wheel"));

		ForgeRegistries.ITEMS.register(item);
		this.setTickRandomly(true);

	}

	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
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
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, PlayerEntity player)
	{
		return new ItemStack(this);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Item.getItemFromBlock(this);
	}

	@Override
	public int quantityDropped(IBlockState state, int fortune, Random random)
	{
		return state == this.getDefaultState() ? 1 : 0;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return AABB[state.getValue(BITES)];
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, PlayerEntity playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if (!worldIn.isRemote)
		{
			return this.eatCheese(worldIn, pos, state, playerIn);
		} else
		{
			ItemStack itemstack = playerIn.getHeldItem(hand);
			return this.eatCheese(worldIn, pos, state, playerIn) || itemstack.isEmpty();
		}
	}

	private boolean eatCheese(World worldIn, BlockPos pos, IBlockState state, PlayerEntity player)
	{
		if (!player.canEat(false))
		{
			return false;
		} else
		{
			if (!worldIn.isRemote && AnimaniaConfig.gameRules.foodsGiveBonusEffects)
			{
				if (this == FarmAddonBlockHandler.blockCheeseFriesian)
					player.addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 6, 2, false, false));
				else if (this == FarmAddonBlockHandler.blockCheeseGoat)
					player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 1200, 0, false, false));
				else if (this == FarmAddonBlockHandler.blockCheeseSheep)
					player.addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 10, 0, false, false));
				else
					player.addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 12, 2, false, false));

			}
			player.getFoodStats().addStats(2, 1.2F);
			int i = ((Integer) state.getValue(BITES)).intValue();

			if (i < 3)
			{
				worldIn.setBlockState(pos, state.withProperty(BITES, Integer.valueOf(i + 1)), 3);
			} else
			{
				worldIn.setBlockToAir(pos);
			}

			return true;
		}
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return ((Integer) state.getValue(BITES)).intValue();
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] { BITES });
	}

	@Override
	public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos)
	{
		return 4 - ((Integer) blockState.getValue(BITES)).intValue();
	}

	@Override
	public boolean hasComparatorInputOverride(IBlockState state)
	{
		return true;
	}

	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
	{
		return super.canPlaceBlockAt(worldIn, pos) ? this.canBlockStay(worldIn, pos) : false;
	}

	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
	{
		if (!this.canBlockStay(worldIn, pos))
		{
			worldIn.setBlockToAir(pos);
		}
	}

	private boolean canBlockStay(World worldIn, BlockPos pos)
	{
		return worldIn.getBlockState(pos.down()).getMaterial().isSolid();
	}

	@Override
	public int quantityDropped(Random random)
	{
		return 0;
	}

	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
	{
		return new ItemStack(this);
	}

	/**
	 * Convert the given metadata into a BlockState for this Block
	 */
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(BITES, Integer.valueOf(meta));
	}

	@SideOnly(Dist.CLIENT)
	@Override
	public BlockRenderLayer getBlockLayer()
	{
		return BlockRenderLayer.CUTOUT;
	}
}
