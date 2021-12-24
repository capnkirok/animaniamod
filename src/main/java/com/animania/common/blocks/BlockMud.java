package com.animania.common.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.common.handler.AddonInjectionHandler;
import com.animania.common.handler.BlockHandler;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMud extends Block
{

	protected static final AABB MUD_AABB = new AABB(0.0D, 0.0D, 0.0D, 1.0D, 0.88D, 1.0D);

	private String name = "block_mud";

	public BlockMud()
	{
		super(Material.SAND, MaterialColor.BROWN);
		this.setCreativeTab(Animania.TabAnimaniaResources);
		this.setRegistryName(new ResourceLocation(Animania.MODID, this.name));
		BlockHandler.blocks.add(this);
		this.setUnlocalizedName(Animania.MODID + "_" + this.name);
		this.slipperiness = 0.6F;
		this.setSoundType(SoundType.SLIME);
		this.setTickRandomly(true);
	}

	@Override
	public int tickRate(World worldIn)
	{
		return 20;
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
	public AxisAlignedBB getCollisionBoundingBox(BlockState blockState, IBlockAccess worldIn, BlockPos pos)
	{
		return BlockMud.MUD_AABB;
	}

	@Override
	public int quantityDropped(Random random)
	{
		return 1;
	}

	public String getName()
	{
		return this.name;
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, BlockState state, Entity entityIn)
	{
		entityIn.motionX *= 0.2;
		entityIn.motionZ *= 0.2;
	}

	@Override
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn)
	{
		entityIn.motionX *= 0.2D;
		entityIn.motionZ *= 0.2D;
	}

	@Override
	@SideOnly(Dist.CLIENT)
	public void randomDisplayTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand)
	{
		AddonInjectionHandler.runInjection("farm", "mudParticleDisplay", Void.class, stateIn, worldIn, pos, rand);
	}

}
