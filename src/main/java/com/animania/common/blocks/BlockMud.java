package com.animania.common.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.common.handler.AddonInjectionHandler;
import com.animania.common.handler.BlockHandler;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

public class BlockMud extends Block
{

	protected static final AABB MUD_AABB = new AABB(0.0D, 0.0D, 0.0D, 1.0D, 0.88D, 1.0D);

	private String name = "block_mud";

	public BlockMud()
	{
		super(BlockBehaviour.Properties.of(Material.SAND, MaterialColor.COLOR_BROWN));
		this.setCreativeTab(Animania.TabAnimaniaResources);
		this.setRegistryName(new ResourceLocation(Animania.MODID, this.name));
		BlockHandler.blocks.add(this);
		this.setUnlocalizedName(Animania.MODID + "_" + this.name);
		this.slipperiness = 0.6F;
		this.setSoundType(SoundType.SLIME);
		this.setTickRandomly(true);
	}

	@Override
	public boolean isFullCube(BlockState state)
	{
		return false;
	}

	public int damageDropped(int par1)
	{
		return par1;
	}

	@Override
	@Nullable
	public AABB getCollisionBoundingBox(BlockState blockState, IBlockAccess levelIn, BlockPos pos)
	{
		return BlockMud.MUD_AABB;
	}

	/* TODO: Done in loot tables
	@Override
	public int quantityDropped(Random random)
	{
		return 1;
	}

	TODO: Done in lang
	public String getName()
	{
		return this.name;
	}*/

	@Override
	public void onEntityCollidedWithBlock(Level levelIn, BlockPos pos, BlockState state, Entity entityIn)
	{
		entityIn.motionX *= 0.2;
		entityIn.motionZ *= 0.2;
	}

	@Override
	public void onEntityWalk(Level levelIn, BlockPos pos, Entity entityIn)
	{
		entityIn.motionX *= 0.2D;
		entityIn.motionZ *= 0.2D;
	}

	@Override
	// @SideOnly(Dist.CLIENT)
	public void randomTick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull Random random) {
		AddonInjectionHandler.runInjection("farm", "mudParticleDisplay", Void.class, state, level, pos, random);
	}
}
