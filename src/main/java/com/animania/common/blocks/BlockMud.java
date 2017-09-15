package com.animania.common.blocks;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.common.entities.pigs.EntityAnimaniaPig;
import com.animania.common.helper.AnimaniaHelper;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMud extends Block
{

	protected static final AxisAlignedBB MUD_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.88D, 1.0D);

	private String name = "block_mud";

	public BlockMud()
	{
		super(Material.SAND, MapColor.BROWN);
		this.setCreativeTab(Animania.TabAnimaniaResources);
		this.setRegistryName(new ResourceLocation(Animania.MODID, this.name));
		ForgeRegistries.BLOCKS.register(this);
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
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}

	public int damageDropped(int par1)
	{
		return par1;
	}

	@Override
	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
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
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
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
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
	{

		List<EntityAnimaniaPig> pigs = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaPig.class, 10, worldIn, pos);

		for (EntityAnimaniaPig pig : pigs)
		{

			boolean isMuddy = false;
			Float splashTimer = 0.0F;

			splashTimer = pig.getSplashTimer();
			isMuddy = pig.getMuddy();

			if (isMuddy && splashTimer > 0.0)
			{
				double xt = pig.posX;
				double yt = pig.posY;
				double zt = pig.posZ;
				int x1 = pos.getX();
				int y1 = pos.getY();
				int z1 = pos.getZ();
				double x2 = xt - x1;
				double y2 = yt - y1;
				double z2 = zt - z1;

				if (MathHelper.abs((int) x2) < 1 && MathHelper.abs((int) z2) < 1 && MathHelper.abs((int) y2) < 1)
					for (int kk = 0; kk < 8; kk++)
						worldIn.spawnParticle(EnumParticleTypes.BLOCK_CRACK, pig.posX + (rand.nextFloat() - 0.5D) * pig.width, pig.getEntityBoundingBox().minY + 0.5D, pig.posZ + (rand.nextFloat() - 0.5D) * pig.width, 4.0D * (rand.nextFloat() - 0.5D), 0.5D, (rand.nextFloat() - 0.5D) * 4.0D, new int[] { Block.getStateId(stateIn) });

			}

		}
	}

}
