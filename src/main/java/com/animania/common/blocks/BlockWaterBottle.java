package com.animania.common.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockWaterBottle extends BlockContainer
{

    public BlockWaterBottle() {
        super(Material.ROCK);
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderType() {
        return -1;
    }

    /*
     * public void onBlockPlacedBy(World par1World, int x, int y, int z,
     * EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
     *
     * TileEntityWaterBottle tile = (TileEntityWaterBottle)
     * par1World.getTileEntity(x, y, z); tile.rotation =
     * MathHelper.floor((double)(par5EntityLivingBase.rotationYaw * 16.0F /
     * 360.0F) + 0.5D) & 15; }
     */

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        // TODO Auto-generated method stub
        return null;
    }
}
