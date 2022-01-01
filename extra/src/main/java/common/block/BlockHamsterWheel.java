package common.block;

import com.animania.Animania;
import com.animania.common.handler.BlockHandler;
import com.animania.common.handler.CompatHandler;
import com.animania.compat.top.providers.TOPInfoProvider;
import common.blockentity.BlockEntityHamsterWheel;
import common.capabilities.CapabilityRefs;
import common.capabilities.ICapabilityPlayer;
import common.entity.rodents.EntityHamster;
import common.handler.ExtraAddonItemHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.Nullable;

public class BlockHamsterWheel extends BaseEntityBlock implements TOPInfoProvider
{

	private String name = "block_hamster_wheel";
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

	public BlockHamsterWheel()
	{
		super(Properties.of(Material.METAL, MaterialColor.COLOR_GRAY));
		this.setRegistryName(new ResourceLocation(Animania.MODID, this.name));
		this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH));
		BlockHandler.blocks.add(this);
		// TODO: Move to lang, and item
		//  this.setUnlocalizedName(Animania.MODID + "_" + this.name);
		//  this.setCreativeTab(Animania.TabAnimaniaResources);
		this.setHardness(1.4f);
		this.setResistance(3.4F);
		this.useNeighborBrightness = true;
	}

	@Override
	public boolean isRandomlyTicking(BlockState arg) {
		return true;
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos arg, BlockState arg2) {
		return new BlockEntityHamsterWheel();
	}

	/* TODO: Err...
	@Override
	public boolean isOpaqueCube(BlockState state)
	{
		return false;
	}*/

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
	public InteractionResult use(BlockState arg, Level level, BlockPos pos, Player player, InteractionHand arg5, BlockHitResult arg6) {
		BlockEntityHamsterWheel te = (BlockEntityHamsterWheel) level.getBlockEntity(pos);

		if (!te.isRunning() && player.hasCapability(CapabilityRefs.CAPS, null))
		{
			LazyOptional<ICapabilityPlayer> cap = player.getCapability(CapabilityRefs.CAPS, null);

			CompoundTag hamsternbt = cap.ifPresent(ICapabilityPlayer::getAnimal);

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
					player.swingArm(InteractionHand.MAIN_HAND);
					player.playSound(SoundEvents.ITEM_PICKUP, 1.0F, (Animania.RANDOM.nextFloat() - Animania.RANDOM.nextFloat()) * 0.2F + 1.0F);

					return true;
				}
			}
		}

		if (!player.getItemInHand(hand).isEmpty() && player.getItemInHand(hand).getItem() == ExtraAddonItemHandler.hamsterFood)
		{
			ItemStack held = player.getItemInHand(hand);
			ItemStack remainder = te.getItemHandler().insertItem(0, new ItemStack(ExtraAddonItemHandler.hamsterFood), false);

			if (!player.isCreative() && remainder.isEmpty())
				held.shrink(1);

			return true;
		}

		if (!level.isClientSide && player.isSneaking() && hand == InteractionHand.MAIN_HAND)
		{
			ItemStack food = te.getItemHandler().getStackInSlot(0);
			if (food.isEmpty())
				player.displayClientMessage(new TextComponent(te.getEnergy() + "/" + te.getPower().getMaxEnergyStored() + " RF"), true);
			else
				player.displayClientMessage(new TextComponent(te.getEnergy() + "/" + te.getPower().getMaxEnergyStored() + " RF, " + food.getCount() + " " + food.getDisplayName()), true);

		}

		return false;
	}

	@Override
	public void playerDestroy(Level level, Player player, BlockPos pos, BlockState blockState, @Nullable BlockEntity blockEntity, ItemStack itemStack) {
		BlockEntityHamsterWheel te = (BlockEntityHamsterWheel) level.getBlockEntity(pos);
		if (te != null)
		{
			te.ejectHamster();
			Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), te.getItemHandler().getStackInSlot(0));
		}
		super.playerDestroy(level, player, pos, blockState, blockEntity, itemStack);
	}

	@Override
	@net.minecraftforge.fml.common.Optional.Method(modid = CompatHandler.THEONEPROBE_ID)
	public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, Player player, Level level, BlockState blockState, IProbeHitData data)
	{

		BlockEntity te = level.getBlockEntity(data.getPos());
		if (te instanceof BlockEntityHamsterWheel wheel && mode == ProbeMode.NORMAL)
		{
			ItemStack food = wheel.getItemHandler().getStackInSlot(0);

			if (!food.isEmpty())
				probeInfo.item(food);
		}
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext ctx) {
		return this.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite());
	}

	@Override
	public void setPlacedBy(Level level, BlockPos pos, BlockState blockState, @Nullable LivingEntity livingEntity, ItemStack itemStack) {
		level.setBlock(pos, blockState.setValue(FACING, livingEntity.getDirection().getOpposite()), 2);
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

		BlockEntityHamsterWheel te = (BlockEntityHamsterWheel) levelIn.getBlockEntity(pos);
		if (te.getHamster() != null)
			return 15;
		else
			return 0;
	}

}
