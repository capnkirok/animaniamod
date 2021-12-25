package com.animania.addons.farm.common.block;

import java.util.Random;

import com.animania.Animania;
import com.animania.addons.farm.common.handler.FarmAddonBlockHandler;
import com.animania.common.handler.BlockHandler;
import com.animania.config.AnimaniaConfig;

import PropertyInteger;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.PotionEffect;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockCheese extends Block
{

	public static final PropertyInteger BITES = PropertyInteger.create("bites", 0, 3);
	private static final AxisAlignedBB[] AABB = new AxisAlignedBB[] { new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.5D, 0.9375D), new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.5D, 0.9375D), new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.5D, 0.5D), new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.5D, 0.5D, 0.5D) };

	public BlockCheese(String name)
	{
		super(Material.CAKE, MaterialColor.YELLOW);
		this.setDefaultState(this.blockState.getBaseState().withProperty(BITES, Integer.valueOf(0)));
		this.setRegistryName(Animania.MODID + ":" + name);
		this.setUnlocalizedName(Animania.MODID + "_" + name);
		this.setCreativeTab(Animania.TabAnimaniaResources);
		this.setSoundType(SoundType.CLOTH);
		this.setHardness(0.6f);
		BlockHandler.blocks.add(this);
		Item item = new BlockItem(this);
		item.setRegistryName(new ResourceLocation(Animania.MODID, name.substring(7) + "_cheese_wheel"));

		ForgeRegistries.ITEMS.register(item);
		this.setTickRandomly(true);

	}

	@Override
	public boolean isFullCube(BlockState state)
	{
		return false;
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
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, Level level, BlockPos pos, PlayerEntity player)
	{
		return new ItemStack(this);
	}

	@Override
	public Item getItemDropped(BlockState state, Random rand, int fortune)
	{
		return Item.getItemFromBlock(this);
	}

	@Override
	public int quantityDropped(BlockState state, int fortune, Random random)
	{
		return state == this.defaultBlockState() ? 1 : 0;
	}

	@Override
	public AxisAlignedBB getBoundingBox(BlockState state, IBlockAccess source, BlockPos pos)
	{
		return AABB[state.getValue(BITES)];
	}

	@Override
	public boolean onBlockActivated(Level levelIn, BlockPos pos, BlockState state, PlayerEntity playerIn, EnumHand hand, Direction facing, float hitX, float hitY, float hitZ)
	{
		if (!levelIn.isRemote)
		{
			return this.eatCheese(levelIn, pos, state, playerIn);
		}
		else
		{
			ItemStack itemstack = playerIn.getHeldItem(hand);
			return this.eatCheese(levelIn, pos, state, playerIn) || itemstack.isEmpty();
		}
	}

	private boolean eatCheese(Level levelIn, BlockPos pos, BlockState state, PlayerEntity player)
	{
		if (!player.canEat(false))
		{
			return false;
		}
		else
		{
			if (!levelIn.isRemote && AnimaniaConfig.gameRules.foodsGiveBonusEffects)
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
			int i = (Integer) state.getValue(BITES);

			if (i < 3)
			{
				levelIn.setBlock(pos, state.withProperty(BITES, Integer.valueOf(i + 1)), 3);
			}
			else
			{
				levelIn.setBlockToAir(pos);
			}

			return true;
		}
	}

	@Override
	public int getMetaFromState(BlockState state)
	{
		return (Integer) state.getValue(BITES);
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] { BITES });
	}

	@Override
	public int getComparatorInputOverride(BlockState blockState, Level levelIn, BlockPos pos)
	{
		return 4 - ((Integer) blockState.getValue(BITES)).intValue();
	}

	@Override
	public boolean hasComparatorInputOverride(BlockState state)
	{
		return true;
	}

	@Override
	public boolean canPlaceBlockAt(Level levelIn, BlockPos pos)
	{
		return super.canPlaceBlockAt(levelIn, pos) ? this.canBlockStay(levelIn, pos) : false;
	}

	@Override
	public void neighborChanged(BlockState state, Level levelIn, BlockPos pos, Block blockIn, BlockPos fromPos)
	{
		if (!this.canBlockStay(levelIn, pos))
		{
			levelIn.setBlockToAir(pos);
		}
	}

	private boolean canBlockStay(Level levelIn, BlockPos pos)
	{
		return levelIn.getBlockState(pos.down()).getMaterial().isSolid();
	}

	@Override
	public int quantityDropped(Random random)
	{
		return 0;
	}

	@Override
	public ItemStack getItem(Level levelIn, BlockPos pos, BlockState state)
	{
		return new ItemStack(this);
	}

	/**
	 * Convert the given metadata into a BlockState for this Block
	 */
	@Override
	public BlockState getStateFromMeta(int meta)
	{
		return this.defaultBlockState().withProperty(BITES, Integer.valueOf(meta));
	}

	@SideOnly(Dist.CLIENT)
	@Override
	public BlockRenderLayer getBlockLayer()
	{
		return BlockRenderLayer.CUTOUT;
	}
}
