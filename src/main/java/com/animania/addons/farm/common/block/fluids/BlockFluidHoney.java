package com.animania.addons.farm.common.block.fluids;

import com.animania.addons.farm.common.handler.FarmAddonBlockHandler;
import com.animania.common.blocks.fluids.BlockFluidBase;

import net.minecraft.core.BlockPos;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MaterialColor;

public class BlockFluidHoney extends BlockFluidBase
{

	public BlockFluidHoney()
	{
		super(FarmAddonBlockHandler.fluidHoney, Material.WATER, "honey");
		this.quantaPerBlock = 3;
		this.renderLayer = BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	public void onEntityCollidedWithBlock(Level level, BlockPos pos, BlockState state, Entity entity)
	{
		Vec3d vec = this.getFlowVector(level, pos);
		entity.addVelocity(vec.x / 2000, vec.y / 2000, vec.z / 2000);
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addMobEffectInstance(new MobEffectInstance(MobEffects.REGENERATION, 1, 0, false, false));
	}

	@Override
	public MaterialColor getMaterialColor(BlockState state, IBlockAccess levelIn, BlockPos pos)
	{
		return MaterialColor.YELLOW;
	}

	@Override
	public boolean shouldSideBeRendered(BlockState state, IBlockAccess level, BlockPos pos, Direction side)
	{
		return true;
	}

}
