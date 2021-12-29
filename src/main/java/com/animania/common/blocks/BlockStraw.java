package com.animania.common.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.common.handler.BlockHandler;

import PropertyEnum;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.StringRepresentable;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockStraw extends Block
{

	protected static final AABB STRAW_AABB = new AABB(0.0D, 0.0D, 0.0D, 1.0D, 0.002D, 1.0D);

	private final String name = "block_straw";

	public static final EnumProperty<EnumType> VARIANT = EnumProperty.create("variant", BlockStraw.EnumType.class);

	public BlockStraw()
	{
		super(Material.CIRCUITS);
		this.setCreativeTab(null);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockStraw.EnumType.STRAW));
		this.setRegistryName(new ResourceLocation(Animania.MODID, this.name));
		this.setCreativeTab(Animania.TabAnimaniaResources);
		BlockHandler.blocks.add(this);
		this.setUnlocalizedName(Animania.MODID + "_" + this.name);
		this.setSoundType(SoundType.PLANT);
		this.setTickRandomly(true);

		Item item = new BlockItem(this);
		item.setRegistryName(new ResourceLocation(Animania.MODID, this.name));

		ForgeRegistries.ITEMS.register(item);

	}

	public boolean isFlammable(IBlockAccess level, BlockPos pos, Direction face)
	{
		return true;
	}

	@Override
	public boolean isOpaqueCube(BlockState state)
	{
		return false;
	}

	@Override
	public boolean isFullCube(BlockState state)
	{
		return false;
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess p_193383_1_, BlockState p_193383_2_, BlockPos p_193383_3_, Direction p_193383_4_)
	{
		return BlockFaceShape.UNDEFINED;
	}

	@Override
	@SideOnly(Dist.CLIENT)
	public BlockRenderLayer getBlockLayer()
	{
		return BlockRenderLayer.CUTOUT;
	}

	public BlockState onBlockPlaced(Level levelIn, BlockPos pos, Direction facing, float hitX, float hitY, float hitZ, int meta, LivingEntity placer)
	{

		for (int i = 0; i < 10; i++)
		{
			double d0 = pos.getX() + 0.25D;
			double d1 = pos.getY() + Animania.RANDOM.nextDouble() * 6.0D / 16.0D;
			double d2 = pos.getZ() + 0.25D;
			double d3 = 0.52D;
			double d4 = Animania.RANDOM.nextDouble() * 0.6D - 0.3D;
			levelIn.spawnParticle(EnumParticleTypes.BLOCK_CRACK, d0, d1 + .75D, d2 + d4, 0.0D, 0.0D, 0.0D, new int[] { Block.getStateId(BlockHandler.blockSeeds.defaultBlockState()) });

		}

		return this.defaultBlockState();
	}

	@Override
	@Nullable
	public Item getItemDropped(BlockState state, Random rand, int fortune)
	{
		return Item.getItemFromBlock(BlockHandler.blockStraw);
	}

	@Override
	public AABB getCollisionBoundingBox(BlockState blockState, IBlockAccess levelIn, BlockPos pos)
	{
		return Block.NULL_AABB;
	}

	@Override
	public AABB getBoundingBox(BlockState state, IBlockAccess source, BlockPos pos)
	{
		return BlockStraw.STRAW_AABB;
	}

	@Override
	public int quantityDropped(Random random)
	{
		return 1;
	}

	@Override
	public boolean canPlaceBlockAt(Level levelIn, BlockPos pos)
	{
		BlockPos blockposlower = pos.below();

		return levelIn.getBlockState(blockposlower).getBlock() != BlockHandler.blockStraw && levelIn.getBlockState(blockposlower).getBlock().isFullBlock(levelIn.getBlockState(blockposlower)) && levelIn.getBlockState(blockposlower).getBlock().isOpaqueCube(levelIn.getBlockState(blockposlower));

	}

	public String getName()
	{
		return this.name;
	}

	@Override
	public void observedNeighborChange(BlockState observerState, Level level, BlockPos observerPos, Block changedBlock, BlockPos changedBlockPos)
	{
		this.checkForDrop(level, observerPos, observerState);

	}

	private boolean checkForDrop(Level levelIn, BlockPos pos, BlockState state)
	{
		if (!this.canBlockStay(levelIn, pos))
		{
			this.dropBlockAsItem(levelIn, pos, state, 0);
			levelIn.setBlockToAir(pos);
			return false;
		}
		else
			return true;
	}

	private boolean canBlockStay(Level levelIn, BlockPos pos)
	{
		return !levelIn.getBlockState(pos.below()).isAir();
	}

	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, Level level, BlockPos pos, Player player)
	{
		switch (state.getValue(VARIANT))
		{
		case STRAW:
			return new ItemStack(BlockHandler.blockStraw);
		default:
			return new ItemStack(BlockHandler.blockStraw);

		}

	}

	@Override
	public BlockState getStateFromMeta(int meta)
	{
		return this.defaultBlockState().setValue(VARIANT, BlockStraw.EnumType.byMetadata(meta));
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	@Override
	public int getMetaFromState(BlockState state)
	{
		return state.getValue(VARIANT).getMetadata();
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> arg) {
		return new StateDefinition.Builder<>(this, new Property[] {VARIANT});
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] { VARIANT });
	}

	@Override
	public MaterialColor getMaterialColor(BlockState state, IBlockAccess levelIn, BlockPos pos)
	{
		return state.getValue(VARIANT).getMaterialColor();
	}

	@Override
	public boolean shouldSideBeRendered(BlockState blockState, IBlockAccess blockAccess, BlockPos pos, Direction side)
	{
		return side == Direction.UP;
	}

	public enum EnumType implements StringRepresentable
	{
		STRAW(0, MaterialColor.COLOR_YELLOW, "straw");

		/** Array of the Block's BlockStates */
		private static final BlockStraw.EnumType[] META = new BlockStraw.EnumType[values().length];
		/** The BlockState's metadata. */
		private final int meta;
		/** The EnumType's name. */
		private final String name;
		private final String unlocalizedName;
		private final MaterialColor materialColor;

		EnumType(int i, MaterialColor color, String name)
		{
			this(i, color, name, name);
		}

		EnumType(int meta, MaterialColor color, String name, String name2)
		{
			this.meta = meta;
			this.name = name;
			this.unlocalizedName = name2;
			this.materialColor = color;
		}

		/**
		 * Returns the EnumType's metadata value.
		 */
		public int getMetadata()
		{
			return this.meta;
		}

		public MaterialColor getMaterialColor()
		{
			return this.materialColor;
		}

		@Override
		public String toString()
		{
			return this.name;
		}

		/**
		 * Returns an EnumType for the BlockState from a metadata value.
		 */
		public static BlockStraw.EnumType byMetadata(int meta)
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

		public String getUnlocalizedName()
		{
			return this.unlocalizedName;
		}

		static
		{
			for (BlockStraw.EnumType blockstone$enumtype : values())
			{
				META[blockstone$enumtype.getMetadata()] = blockstone$enumtype;
			}
		}

		@Override
		public String getSerializedName() {
			return this.unlocalizedName;
		}
	}

}
