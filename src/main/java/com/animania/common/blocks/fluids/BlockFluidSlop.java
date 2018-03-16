package com.animania.common.blocks.fluids;

import com.animania.common.handler.BlockHandler;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockFluidSlop extends BlockFluidBase
{

    public BlockFluidSlop() {
        super(BlockHandler.fluidSlop, Material.WATER, "slop");

        this.quantaPerBlock = 3;
        this.renderLayer = BlockRenderLayer.TRANSLUCENT;

    }

    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
        entity.setInWeb();
    }

    @Override
    public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
        return true;
    }
    
    @Override
    public MapColor getMapColor(IBlockState state)
    {
    	return MapColor.BROWN;
    }

}
