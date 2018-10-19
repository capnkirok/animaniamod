package com.animania.common.entities.generic.ai;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.animania.common.entities.interfaces.IFoodEating;
import com.animania.common.entities.interfaces.ISleeping;
import com.animania.common.handler.BlockHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.tileentities.TileEntityTrough;
import com.animania.config.AnimaniaConfig;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fluids.FluidRegistry;

@Deprecated
public class GenericAIFindWater_old<T extends EntityCreature & IFoodEating> extends EntityAIBase
{
	private final T entity;
	private final double speed;
	private int waterFindTimer;
	private boolean isRunning;
	private EntityAIBase eatAI;
	private Class parentClass;
	private boolean halfAmount;

	public GenericAIFindWater_old(T entity, double speedIn, @Nullable EntityAIBase eatAI, Class parentClass, boolean halfAmount)
	{
		this.entity = entity;
		this.speed = speedIn;
		this.setMutexBits(3);
		this.waterFindTimer = 0;
		this.eatAI = eatAI;
		this.parentClass = parentClass;
		this.halfAmount = halfAmount;
	}

	public GenericAIFindWater_old(T entity, double speedIn, @Nullable EntityAIBase eatAI, Class parentClass)
	{
		this(entity, speedIn, eatAI, parentClass, false);
	}

	public boolean shouldExecute()
	{

		waterFindTimer++;

		if (this.waterFindTimer <= AnimaniaConfig.gameRules.ticksBetweenAIFirings)
		{
			return false;
		}
		else if (waterFindTimer > AnimaniaConfig.gameRules.ticksBetweenAIFirings)
		{

			if (entity.getWatered())
			{
				this.waterFindTimer = 0;
				return false;
			}

			if (entity instanceof ISleeping)
			{
				if (((ISleeping) entity).getSleeping())
				{
					this.waterFindTimer = 0;
					return false;
				}
			}

			if (this.entity.getRNG().nextInt(100) <= 5)
			{
				Vec3d vec3d = RandomPositionGenerator.findRandomTarget(this.entity, 20, 4);
				if (vec3d != null)
				{
					this.waterFindTimer = 0;
					this.resetTask();
					this.entity.getNavigator().tryMoveToXYZ(vec3d.x, vec3d.y, vec3d.z, this.speed);
					this.entity.getLookHelper().setLookPosition(vec3d.x, vec3d.y, vec3d.z, 0.0F, 0.0F);
				}
				return false;
			}

			Random rand = new Random();

			Biome biomegenbase = entity.world.getBiome(new BlockPos(entity.posX, entity.posY, entity.posZ));

			BlockPos currentpos = new BlockPos(entity.posX, entity.posY, entity.posZ);
			BlockPos currentposlower = new BlockPos(entity.posX, entity.posY - 1, entity.posZ);
			BlockPos trypos1 = new BlockPos(entity.posX + 1, entity.posY, entity.posZ);
			BlockPos trypos2 = new BlockPos(entity.posX - 1, entity.posY, entity.posZ);
			BlockPos trypos3 = new BlockPos(entity.posX, entity.posY, entity.posZ + 1);
			BlockPos trypos4 = new BlockPos(entity.posX, entity.posY, entity.posZ - 1);
			BlockPos trypos5 = new BlockPos(entity.posX + 1, entity.posY, entity.posZ + 1);
			BlockPos trypos6 = new BlockPos(entity.posX - 1, entity.posY, entity.posZ - 1);
			BlockPos trypos7 = new BlockPos(entity.posX - 1, entity.posY, entity.posZ + 1);
			BlockPos trypos8 = new BlockPos(entity.posX + 1, entity.posY, entity.posZ - 1);
			Block poschk = entity.world.getBlockState(currentpos).getBlock();
			Block poschk1 = entity.world.getBlockState(trypos1).getBlock();
			Block poschk2 = entity.world.getBlockState(trypos2).getBlock();
			Block poschk3 = entity.world.getBlockState(trypos3).getBlock();
			Block poschk4 = entity.world.getBlockState(trypos4).getBlock();
			Block poschk5 = entity.world.getBlockState(trypos5).getBlock();
			Block poschk6 = entity.world.getBlockState(trypos6).getBlock();
			Block poschk7 = entity.world.getBlockState(trypos7).getBlock();
			Block poschk8 = entity.world.getBlockState(trypos8).getBlock();
			Block poschk9 = entity.world.getBlockState(currentposlower).getBlock();

			if (poschk == BlockHandler.blockTrough)
			{
				// do nothing
			}
			else if (poschk1 == BlockHandler.blockTrough)
			{
				currentpos = trypos1;
			}
			else if (poschk2 == BlockHandler.blockTrough)
			{
				currentpos = trypos2;
			}
			else if (poschk3 == BlockHandler.blockTrough)
			{
				currentpos = trypos3;
			}
			else if (poschk4 == BlockHandler.blockTrough)
			{
				currentpos = trypos4;
			}
			else if (poschk5 == BlockHandler.blockTrough)
			{
				currentpos = trypos5;
			}
			else if (poschk6 == BlockHandler.blockTrough)
			{
				currentpos = trypos6;
			}
			else if (poschk7 == BlockHandler.blockTrough)
			{
				currentpos = trypos7;
			}
			else if (poschk8 == BlockHandler.blockTrough)
			{
				currentpos = trypos8;
			}

			if (poschk == BlockHandler.blockTrough || poschk1 == BlockHandler.blockTrough || poschk2 == BlockHandler.blockTrough || poschk3 == BlockHandler.blockTrough || poschk4 == BlockHandler.blockTrough || poschk5 == BlockHandler.blockTrough || poschk6 == BlockHandler.blockTrough || poschk7 == BlockHandler.blockTrough || poschk8 == BlockHandler.blockTrough)
			{

				TileEntityTrough te = (TileEntityTrough) this.entity.world.getTileEntity(currentpos);
				if (te != null && te.fluidHandler.getFluid() != null && te.fluidHandler.getFluid().getFluid() == FluidRegistry.WATER)
				{
					if (halfAmount)
						te.consumeLiquid(50);
					else
						te.consumeLiquid(100);

					entity.world.notifyBlockUpdate(currentpos, poschk.getDefaultState(), poschk.getDefaultState(), 0);
					entity.world.updateComparatorOutputLevel(currentpos, poschk);

					if (eatAI != null)
						eatAI.startExecuting();
					entity.setWatered(true);

					this.waterFindTimer = 0;
					return false;

				}

			}
			else if ((poschk == Blocks.WATER || poschk9 == Blocks.WATER) && !BiomeDictionary.hasType(biomegenbase, Type.OCEAN) && !BiomeDictionary.hasType(biomegenbase, Type.BEACH))
			{

				if (eatAI != null)
					eatAI.startExecuting();
				entity.setWatered(true);

				if (this.entity.world.getBlockState(currentposlower).getBlock() == Blocks.WATER && AnimaniaConfig.gameRules.waterRemovedAfterDrinking)
				{
					if (!halfAmount)
						this.entity.world.setBlockToAir(currentposlower);
				}

				this.waterFindTimer = 0;
				return false;
			}

			double x = this.entity.posX;
			double y = this.entity.posY;
			double z = this.entity.posZ;

			boolean waterFound = false;

			BlockPos pos = new BlockPos(x, y, z);

			for (int i = -16; i < 16; i++)
			{
				for (int j = -3; j < 3; j++)
				{
					for (int k = -16; k < 16; k++)
					{

						pos = new BlockPos(x + i, y + j, z + k);
						Block blockchk = entity.world.getBlockState(pos).getBlock();

						if (blockchk == Blocks.WATER && entity.getNavigator().tryMoveToXYZ(pos.getX(), pos.getY(), pos.getZ(), 1.0))
						{
							waterFound = true;
							if (rand.nextInt(200) == 0)
							{
								this.waterFindTimer = 0;
								return false;
							}
							else if (this.entity.collidedHorizontally && this.entity.motionX == 0 && this.entity.motionZ == 0)
							{
								return false;
							}
							else
							{
								return true;
							}
						}
						else if (blockchk == BlockHandler.blockTrough)
						{
							TileEntityTrough te = (TileEntityTrough) this.entity.world.getTileEntity(pos);
							if (te != null && te.fluidHandler.getFluid() != null && te.fluidHandler.getFluid().getFluid() == FluidRegistry.WATER && entity.getNavigator().tryMoveToXYZ(pos.getX(), pos.getY(), pos.getZ(), 1.0))
							{
								waterFound = true;
								if (rand.nextInt(200) == 0)
								{
									this.waterFindTimer = 0;
									return false;
								}
								else if (this.entity.collidedHorizontally && this.entity.motionX == 0 && this.entity.motionZ == 0)
								{
									this.waterFindTimer = 0;
									return false;
								}
								else
								{
									return true;
								}
							}
						}
					}
				}
			}

			if (!waterFound)
			{
				this.waterFindTimer = 0;
				Vec3d vec3d = RandomPositionGenerator.findRandomTarget(this.entity, 20, 4);
				if (vec3d != null)
				{
					this.waterFindTimer = 0;
					this.entity.getNavigator().tryMoveToXYZ(vec3d.x, vec3d.y, vec3d.z, this.speed);
					this.resetTask();
				}
				return false;
			}
		}

		return false;
	}

	public boolean shouldContinueExecuting()
	{
		return !this.entity.getNavigator().noPath();
	}

	public void resetTask()
	{
		this.entity.getNavigator().clearPath();
		this.isRunning = false;
	}

	public void startExecuting()
	{

		double x = this.entity.posX;
		double y = this.entity.posY;
		double z = this.entity.posZ;

		BlockPos currentpos = new BlockPos(x, y, z);
		Block poschk = entity.world.getBlockState(currentpos).getBlock();

		if (poschk != Blocks.WATER)
		{

			boolean waterFound = false;
			int loc = 24;
			int newloc = 24;
			BlockPos pos = new BlockPos(x, y, z);
			BlockPos waterPos = new BlockPos(x, y, z);

			for (int i = -16; i < 16; i++)
			{
				for (int j = -3; j < 3; j++)
				{
					for (int k = -16; k < 16; k++)
					{

						pos = new BlockPos(x + i, y + j, z + k);
						Block blockchk = entity.world.getBlockState(pos).getBlock();
						Biome biomegenbase = entity.world.getBiome(pos);

						List others = AnimaniaHelper.getEntitiesInRange(parentClass, 2, entity.world, pos);

						if (blockchk == BlockHandler.blockTrough && others.size() < 2)
						{
							TileEntityTrough te = (TileEntityTrough) this.entity.world.getTileEntity(pos);
							if (te != null && te.fluidHandler.getFluid() != null && te.fluidHandler.getFluid().getFluid() == FluidRegistry.WATER)
							{
								waterFound = true;
								newloc = Math.abs(i) + Math.abs(j) + Math.abs(k);

								if (newloc < loc)
								{

									loc = newloc;

									if (entity.posX < waterPos.getX())
									{
										BlockPos waterPoschk = new BlockPos(x + i + 1, y + j, z + k);
										Block waterBlockchk = entity.world.getBlockState(waterPoschk).getBlock();
										if (waterBlockchk == BlockHandler.blockTrough)
										{
											i = i + 1;
										}
									}

									if (entity.posZ < waterPos.getZ())
									{
										BlockPos waterPoschk = new BlockPos(x + i, y + j, z + k + 1);
										Block waterBlockchk = entity.world.getBlockState(waterPoschk).getBlock();
										if (waterBlockchk == BlockHandler.blockTrough)
										{
											k = k + 1;
										}
									}

									waterPos = new BlockPos(x + i, y + j, z + k);

								}
							}
						}

						if (blockchk == Blocks.WATER && others.size() < 2 && !BiomeDictionary.hasType(biomegenbase, Type.OCEAN) && !BiomeDictionary.hasType(biomegenbase, Type.BEACH))
						{
							waterFound = true;
							newloc = Math.abs(i) + Math.abs(j) + Math.abs(k);

							if (newloc < loc)
							{

								loc = newloc;

								if (entity.posX < waterPos.getX())
								{
									BlockPos waterPoschk = new BlockPos(x + i + 1, y + j, z + k);
									Block waterBlockchk = entity.world.getBlockState(waterPoschk).getBlock();
									if (waterBlockchk == Blocks.WATER)
									{
										i = i + 1;
									}
								}

								if (entity.posZ < waterPos.getZ())
								{
									BlockPos waterPoschk = new BlockPos(x + i, y + j, z + k + 1);
									Block waterBlockchk = entity.world.getBlockState(waterPoschk).getBlock();
									if (waterBlockchk == Blocks.WATER)
									{
										k = k + 1;
									}
								}

								waterPos = new BlockPos(x + i, y + j, z + k);

							}
						}
					}
				}
			}

			if (waterFound)
			{

				Block waterBlockchk = entity.world.getBlockState(waterPos).getBlock();
				Biome biomegenbase = entity.world.getBiome(waterPos);

				if (waterBlockchk == BlockHandler.blockTrough)
				{

					if (this.entity.getNavigator().tryMoveToXYZ(waterPos.getX() + .5, waterPos.getY(), waterPos.getZ() + .5, this.speed) == false)
					{
						this.waterFindTimer = 0;
						this.entity.getLookHelper().setLookPosition(waterPos.getX(), waterPos.getY(), waterPos.getZ(), 0.0F, 0.0F);
						this.resetTask();
					}
					else
					{
						this.entity.getNavigator().tryMoveToXYZ(waterPos.getX() + .5, waterPos.getY(), waterPos.getZ() + .5, this.speed);
						this.entity.getLookHelper().setLookPosition(waterPos.getX(), waterPos.getY(), waterPos.getZ(), 0.0F, 0.0F);
					}

				}
				else if (waterBlockchk == Blocks.WATER && !BiomeDictionary.hasType(biomegenbase, Type.OCEAN) && !BiomeDictionary.hasType(biomegenbase, Type.BEACH))
				{
					if (this.entity.getNavigator().tryMoveToXYZ(waterPos.getX(), waterPos.getY(), waterPos.getZ(), this.speed) == false)
					{
						this.entity.getLookHelper().setLookPosition(waterPos.getX(), waterPos.getY(), waterPos.getZ(), 0.0F, 0.0F);
						this.waterFindTimer = 0;
						this.resetTask();
					}
					else
					{
						this.entity.getNavigator().tryMoveToXYZ(waterPos.getX(), waterPos.getY(), waterPos.getZ(), this.speed);
						this.entity.getLookHelper().setLookPosition(waterPos.getX(), waterPos.getY(), waterPos.getZ(), 0.0F, 0.0F);
					}
				}
				else
				{
					this.waterFindTimer = 0;
					this.resetTask();
				}
			}
		}
	}

	public boolean isRunning()
	{
		return this.isRunning;
	}
}
