package com.animania.addons.farm.common.block.fluids;

import com.animania.addons.farm.common.handler.FarmAddonBlockHandler;
import com.animania.common.blocks.fluids.BlockFluidBase;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockFluidHoney extends BlockFluidBase
{

	public BlockFluidHoney()
	{
		super(FarmAddonBlockHandler.fluidHoney, Material.WATER, "honey");
		this.quantaPerBlock = 3;
		this.renderLayer = BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, BlockState state, Entity entity)
	{
		Vec3d vec = this.getFlowVector(world, pos);
		entity.addVelocity(vec.x / 2000, vec.y / 2000, vec.z / 2000);
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 1, 0, false, false));
	}

	@Override
	public MaterialColor getMaterialColor(BlockState state, IBlockAccess worldIn, BlockPos pos)
	{
		return MaterialColor.YELLOW;
	}

	@Override
	public boolean shouldSideBeRendered(BlockState state, IBlockAccess world, BlockPos pos, EnumFacing side)
	{
		return true;
	}

}
