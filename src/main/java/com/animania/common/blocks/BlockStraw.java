package com.animania.common.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.common.handler.BlockHandler;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

public class BlockStraw extends Block
{

	protected static final AABB STRAW_AABB = new AABB(0.0D, 0.0D, 0.0D, 1.0D, 0.002D, 1.0D);

	private final String name = "block_straw";

	public static final EnumProperty<EnumType> VARIANT = EnumProperty.create("variant", BlockStraw.EnumType.class);

	public BlockStraw()
	{
		super(BlockBehaviour.Properties.of(Material.EXPLOSIVE));
		this.registerDefaultState(this.defaultBlockState().setValue(VARIANT, BlockStraw.EnumType.STRAW));
		this.setRegistryName(new ResourceLocation(Animania.MODID, this.name));
		this.setCreativeTab(Animania.TabAnimaniaResources);
		BlockHandler.blocks.add(this);

		Item item = new BlockItem(this);
		item.setRegistryName(new ResourceLocation(Animania.MODID, this.name));

		ForgeRegistries.ITEMS.register(item);

	}

	@Override
	public @NotNull SoundType getSoundType(@NotNull BlockState blockState) {
		return SoundType.GRASS;
	}

	@Override
	public boolean isRandomlyTicking(@NotNull BlockState blockState) {
		return true;
	}

	@Override
	public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return true;
	}

	@Override
	public InteractionResult use(BlockState blockState, Level level, BlockPos pos, Player placer, InteractionHand hand, BlockHitResult result) {

		for (int i = 0; i < 10; i++)
		{
			double d0 = pos.getX() + 0.25D;
			double d1 = pos.getY() + Animania.RANDOM.nextDouble() * 6.0D / 16.0D;
			double d2 = pos.getZ() + 0.25D;
			double d3 = 0.52D;
			double d4 = Animania.RANDOM.nextDouble() * 0.6D - 0.3D;
			level.addParticle(ParticleTypes.BLOCK, d0, d1 + .75D, d2 + d4, 0.0D, 0.0D, 0.0D);

		}

		return InteractionResult.PASS;
	}

	@Override
	public VoxelShape getCollisionShape(BlockState p_60572_, BlockGetter p_60573_, BlockPos p_60574_, CollisionContext p_60575_) {
		return Shapes.empty();
	}

	@Override
	public VoxelShape getShape(@NotNull BlockState blockState, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
		return BlockStraw.STRAW_AABB;
	}

	@Override
	public boolean canSurvive(@NotNull BlockState blockState, @NotNull LevelReader levelReader, BlockPos pos) {
		BlockPos lowerBlockPos = pos.below();

		return levelReader.getBlockState(lowerBlockPos).getBlock() != BlockHandler.blockStraw && levelReader.getBlockState(lowerBlockPos).getBlock().isFullBlock(levelReader.getBlockState(lowerBlockPos)) && levelReader.getBlockState(lowerBlockPos).getBlock().isOpaqueCube(levelReader.getBlockState(lowerBlockPos));
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
		return new ItemStack(BlockHandler.blockStraw);

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
