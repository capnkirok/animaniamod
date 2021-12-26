package com.animania.common.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.common.helper.RegistryHelper.RItem;
import com.animania.common.items.ItemSaltLick;
import com.animania.common.tileentities.BlockEntitySaltLick;
import com.animania.config.AnimaniaConfig;

import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockSaltLick extends AnimaniaBlock implements EntityBlock
{

	private static final AABB AABB = new AABB(0.1875, 0, 0.1875, 0.8125, 0.25, 0.8125);

	public BlockSaltLick()
	{
		super("salt_lick", Material.STONE, MaterialColor.SNOW, false);
		this.setCreativeTab(Animania.TabAnimaniaResources);

		this.setHardness(1.2f);
		this.setResistance(1.7f);
		this.hasBlockEntity = true;
		RItem item = new ItemSaltLick(this);
		item.setRegistryName(new ResourceLocation(Animania.MODID, "salt_lick"));

		ForgeRegistries.ITEMS.register(item);
	}

	public void useSaltLick(Level level, BlockPos pos, @Nullable LivingEntity entity)
	{
		BlockEntity te = level.getBlockEntity(pos);
		if (te instanceof BlockEntitySaltLick)
		{
			((BlockEntitySaltLick) te).useSaltLick();
			if (entity != null)
			{
				entity.heal(2f);
				te.markDirty();
			}
		}
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess p_193383_1_, BlockState p_193383_2_, BlockPos p_193383_3_, Direction p_193383_4_)
	{
		return BlockFaceShape.UNDEFINED;
	}

	@Override
	public void harvestBlock(Level levelIn, Player player, BlockPos pos, BlockState state, BlockEntity te, ItemStack stack)
	{
		if (te instanceof BlockEntitySaltLick)
		{
			int left = ((BlockEntitySaltLick) te).usesLeft;
			int damage = AnimaniaConfig.careAndFeeding.saltLickMaxUses - left;

			Block.spawnAsEntity(levelIn, pos, new ItemStack(this, 1, damage));
		}
	}

	@Override
	public RItem getItemDropped(BlockState state, Random rand, int fortune)
	{
		return Items.AIR;
	}

	@Override
	public void onBlockPlacedBy(Level levelIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack)
	{
		int left = AnimaniaConfig.careAndFeeding.saltLickMaxUses - stack.getItemDamage();

		BlockEntity te = levelIn.getBlockEntity(pos);
		if (te instanceof BlockEntitySaltLick)
		{
			((BlockEntitySaltLick) te).usesLeft = left;
		}
	}

	@Override
	public AxisAlignedBB getBoundingBox(BlockState state, IBlockAccess level, BlockPos pos)
	{
		if (level != null)
		{
			BlockEntity te = level.getBlockEntity(pos);
			if (te instanceof BlockEntitySaltLick)
			{
				double usesLeft = (double) ((BlockEntitySaltLick) te).usesLeft / (double) AnimaniaConfig.careAndFeeding.saltLickMaxUses;
				return new AxisAlignedBB(0.1875, 0, 0.1875, 0.8125, 0.625 * usesLeft, 0.8125);
			}
		}
		return AABB;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(BlockState blockState, IBlockAccess level, BlockPos pos)
	{
		if (level != null)
		{
			BlockEntity te = level.getBlockEntity(pos);
			if (te instanceof BlockEntitySaltLick)
			{
				double usesLeft = (double) ((BlockEntitySaltLick) te).usesLeft / (double) AnimaniaConfig.careAndFeeding.saltLickMaxUses;
				return new AxisAlignedBB(0.1875, 0, 0.1875, 0.8125, 0.625 * usesLeft, 0.8125);
			}
		}
		return AABB;
	}

	@Override
	public boolean isFullBlock(BlockState state)
	{
		return false;
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

	// @Override
	// public boolean isFullyOpaque(BlockState state)
	// {
	// return false;
	// }

	@Override
	public boolean hasBlockEntity()
	{
		return true;
	}

	@Override
	public BlockEntity createNewBlockEntity(Level levelIn, int meta)
	{
		return new BlockEntitySaltLick();
	}

	@Override
	public EnumBlockRenderType getRenderType(BlockState state)
	{
		return EnumBlockRenderType.INVISIBLE;
	}

}
