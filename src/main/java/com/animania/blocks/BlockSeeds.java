package com.animania.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.animania.Animania;
import com.animania.entities.pigs.EntityHogDuroc;
import com.animania.entities.pigs.EntityHogLargeBlack;
import com.animania.entities.pigs.EntityHogLargeWhite;
import com.animania.entities.pigs.EntityHogOldSpot;
import com.animania.entities.pigs.EntityHogYorkshire;
import com.animania.entities.pigs.EntityPigletDuroc;
import com.animania.entities.pigs.EntityPigletLargeBlack;
import com.animania.entities.pigs.EntityPigletLargeWhite;
import com.animania.entities.pigs.EntityPigletOldSpot;
import com.animania.entities.pigs.EntityPigletYorkshire;
import com.animania.entities.pigs.EntitySowDuroc;
import com.animania.entities.pigs.EntitySowLargeBlack;
import com.animania.entities.pigs.EntitySowLargeWhite;
import com.animania.entities.pigs.EntitySowOldSpot;
import com.animania.entities.pigs.EntitySowYorkshire;
import com.animania.tileentities.TileEntityTrough;


public class BlockSeeds extends Block {

	protected static final AxisAlignedBB SEEDS_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.0002D, 1.0D);

	private String name = "block_seeds";
	
	public BlockSeeds() {
		super(Material.LEAVES);
		setCreativeTab(null); 
		this.setRegistryName(new ResourceLocation(Animania.modid, name));
		GameRegistry.register(this);
		setUnlocalizedName(Animania.modid + "_" + name);
		setSoundType(SoundType.PLANT);
		this.setTickRandomly(true);
	}

	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}

	public boolean isFullCube(IBlockState state)
	{
		return false;
	}
	
	@Override
	public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos)
	{
		return true;
	}


	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer()
	{
		return BlockRenderLayer.CUTOUT;
	}

	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	{


		Random rand = new Random();

		for (int i = 0; i < 10; i++) {
			double d0 = (double)pos.getX() + 0.25D;
			double d1 = (double)pos.getY() + rand.nextDouble() * 6.0D / 16.0D;
			double d2 = (double)pos.getZ() + 0.25D;
			double d3 = 0.52D;
			double d4 = rand.nextDouble() * 0.6D - 0.3D;
			worldIn.spawnParticle(EnumParticleTypes.BLOCK_CRACK, d0, d1 + .75D, d2 + d4, 0.0D, 0.0D, 0.0D, new int[] {Block.getStateId(Animania.blockSeeds.getDefaultState())});

		}

		return this.getDefaultState();
	}

	

	public int damageDropped(int par1)
	{
		return par1;
	}

	@Nullable
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Items.WHEAT_SEEDS;
	}

	
	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos)
	{
		return NULL_AABB;
	}
	

	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return SEEDS_AABB;
	}

	public int quantityDropped(Random random)
	{
		return 1;
	}

	public String getName()
	{
		return name;
	}

	@Override
	public void observedNeighborChange(IBlockState observerState, World world, BlockPos observerPos, Block changedBlock, BlockPos changedBlockPos)
	{
		IBlockState state = world.getBlockState(changedBlockPos);
    	this.checkForDrop(world, observerPos, state);
		
	}
	
    private boolean checkForDrop(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!this.canBlockStay(worldIn, pos))
        {
            this.dropBlockAsItem(worldIn, pos, state, 0);
            worldIn.setBlockToAir(pos);
            return false;
        }
        else
        {
            return true;
        }
    }
   
    private boolean canBlockStay(World worldIn, BlockPos pos)
    {
        return !worldIn.isAirBlock(pos.down());
    }


}




