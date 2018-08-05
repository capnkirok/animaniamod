package com.animania.common.entities.horses.ai;

import java.util.List;
import java.util.Random;

import com.animania.common.entities.horses.EntityAnimaniaHorse;
import com.animania.common.handler.BlockHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.tileentities.TileEntityTrough;
import com.animania.config.AnimaniaConfig;

import net.minecraft.block.Block;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fluids.FluidRegistry;

public class EntityAIFindWater extends EntityAIBase 
{
	private final EntityAnimaniaHorse entityIn;
	private final double speed;
	private double targetX;
	private double targetY;
	private double targetZ;
	private double pitch;
	private double yaw;
	private EntityPlayer temptingPlayer;
	private int delayTemptCounter;
	private boolean isRunning;

	public EntityAIFindWater(EntityAnimaniaHorse entityInIn, double speedIn)
	{
		this.entityIn = entityInIn;
		this.speed = speedIn;
		this.setMutexBits(3);
		this.delayTemptCounter = 0;
	}

	public boolean shouldExecute()
	{

		delayTemptCounter++;
		
		if (this.delayTemptCounter <= AnimaniaConfig.gameRules.ticksBetweenAIFirings) {
			return false;
		} else if (delayTemptCounter > AnimaniaConfig.gameRules.ticksBetweenAIFirings) {

			if (entityIn.getWatered()) {
				this.delayTemptCounter = 0;
				return false;		
			}
			
			if (entityIn.getSleeping()) {
				this.delayTemptCounter = 0;
				return false;
			}
			
			if (entityIn.isBeingRidden()) {
				this.delayTemptCounter = 0;
				return false;
			}
			
			if (this.entityIn.getRNG().nextInt(100) <= 5)
			{
				Vec3d vec3d = RandomPositionGenerator.findRandomTarget(this.entityIn, 20, 4);
				if (vec3d != null) {
					this.delayTemptCounter = 0;
					this.resetTask();
					this.entityIn.getNavigator().tryMoveToXYZ(vec3d.x, vec3d.y, vec3d.z, this.speed);
					this.entityIn.getLookHelper().setLookPosition(vec3d.x, vec3d.y, vec3d.z, 0.0F, 0.0F);
				}
				return false;
			}

			Random rand = new Random();

			Biome biomegenbase = entityIn.world.getBiome(new BlockPos(entityIn.posX, entityIn.posY, entityIn.posZ)); 

			BlockPos currentpos = new BlockPos(entityIn.posX, entityIn.posY, entityIn.posZ);
			BlockPos currentposlower = new BlockPos(entityIn.posX, entityIn.posY - 1, entityIn.posZ);
			BlockPos trypos1 = new BlockPos(entityIn.posX + 1, entityIn.posY, entityIn.posZ);
			BlockPos trypos2 = new BlockPos(entityIn.posX - 1, entityIn.posY, entityIn.posZ);
			BlockPos trypos3 = new BlockPos(entityIn.posX, entityIn.posY, entityIn.posZ + 1);
			BlockPos trypos4 = new BlockPos(entityIn.posX, entityIn.posY, entityIn.posZ - 1);
			BlockPos trypos5 = new BlockPos(entityIn.posX + 1, entityIn.posY, entityIn.posZ + 1);
			BlockPos trypos6 = new BlockPos(entityIn.posX - 1, entityIn.posY, entityIn.posZ - 1);
			BlockPos trypos7 = new BlockPos(entityIn.posX - 1, entityIn.posY, entityIn.posZ + 1);
			BlockPos trypos8 = new BlockPos(entityIn.posX + 1, entityIn.posY, entityIn.posZ - 1);
			Block poschk = entityIn.world.getBlockState(currentpos).getBlock();
			Block poschk1 = entityIn.world.getBlockState(trypos1).getBlock();
			Block poschk2 = entityIn.world.getBlockState(trypos2).getBlock();
			Block poschk3 = entityIn.world.getBlockState(trypos3).getBlock();
			Block poschk4 = entityIn.world.getBlockState(trypos4).getBlock();
			Block poschk5 = entityIn.world.getBlockState(trypos5).getBlock();
			Block poschk6 = entityIn.world.getBlockState(trypos6).getBlock();
			Block poschk7 = entityIn.world.getBlockState(trypos7).getBlock();
			Block poschk8 = entityIn.world.getBlockState(trypos8).getBlock();
			Block poschk9 = entityIn.world.getBlockState(currentposlower).getBlock();

			if (poschk == BlockHandler.blockTrough) {
				//do nothing
			} else if (poschk1 == BlockHandler.blockTrough) {
				currentpos = trypos1;
			} else if (poschk2 == BlockHandler.blockTrough) {
				currentpos = trypos2;
			} else if (poschk3 == BlockHandler.blockTrough) {
				currentpos = trypos3;
			} else if (poschk4 == BlockHandler.blockTrough) {
				currentpos = trypos4;
			} else if (poschk5 == BlockHandler.blockTrough) {
				currentpos = trypos5;
			} else if (poschk6 == BlockHandler.blockTrough) {
				currentpos = trypos6;
			} else if (poschk7 == BlockHandler.blockTrough) {
				currentpos = trypos7;
			} else if (poschk8 == BlockHandler.blockTrough) {
				currentpos = trypos8;
			}


			if (poschk == BlockHandler.blockTrough || poschk1 == BlockHandler.blockTrough || poschk2 == BlockHandler.blockTrough || poschk3 == BlockHandler.blockTrough || poschk4 == BlockHandler.blockTrough || poschk5 == BlockHandler.blockTrough || poschk6 == BlockHandler.blockTrough || poschk7 == BlockHandler.blockTrough || poschk8 == BlockHandler.blockTrough) {

				TileEntityTrough te = (TileEntityTrough) this.entityIn.world.getTileEntity(currentpos);
				if (te != null && te.fluidHandler.getFluid() != null && te.fluidHandler.getFluid().getFluid() == FluidRegistry.WATER) {
					te.consumeLiquid(100);

					entityIn.world.notifyBlockUpdate(currentpos, poschk.getDefaultState(), poschk.getDefaultState(), 0);
					entityIn.world.updateComparatorOutputLevel(currentpos, poschk);

					if (entityIn instanceof EntityAnimaniaHorse) {
						EntityAnimaniaHorse ech = (EntityAnimaniaHorse)entityIn;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					} 

					this.delayTemptCounter = 0;
					return false;

				}

			} else if ((poschk == Blocks.WATER || poschk9 == Blocks.WATER) && !BiomeDictionary.hasType(biomegenbase, Type.OCEAN) && !BiomeDictionary.hasType(biomegenbase, Type.BEACH)) {

				if (entityIn instanceof EntityAnimaniaHorse) {
					EntityAnimaniaHorse ech = (EntityAnimaniaHorse)entityIn;
					ech.entityAIEatGrass.startExecuting();
					ech.setWatered(true);
				} 

				if (this.entityIn.world.getBlockState(currentposlower).getBlock() == Blocks.WATER && AnimaniaConfig.gameRules.waterRemovedAfterDrinking) {
					this.entityIn.world.setBlockToAir(currentposlower);
				}

				this.delayTemptCounter = 0;
				return false;
			}

			double x = this.entityIn.posX;
			double y = this.entityIn.posY;
			double z = this.entityIn.posZ;

			boolean waterFound = false;

			BlockPos pos = new BlockPos(x, y, z);

			for (int i = -16; i < 16; i++) {
				for (int j = -3; j < 3; j++) {
					for (int k = -16; k < 16; k++) {

						pos = new BlockPos(x + i, y + j, z + k);
						Block blockchk = entityIn.world.getBlockState(pos).getBlock();

						if (blockchk == Blocks.WATER && entityIn.getNavigator().tryMoveToXYZ(pos.getX(), pos.getY(), pos.getZ(), 1.0)) {
							waterFound = true;
							if (rand.nextInt(200) == 0) {
								this.delayTemptCounter = 0;
								return false;
							} else if (this.entityIn.collidedHorizontally && this.entityIn.motionX == 0 && this.entityIn.motionZ == 0 ) {
								return false;
							} else {
								return true;
							}
						} else if (blockchk == BlockHandler.blockTrough) {
							TileEntityTrough te = (TileEntityTrough) this.entityIn.world.getTileEntity(pos);
							if (te != null && te.fluidHandler.getFluid() != null && te.fluidHandler.getFluid().getFluid() == FluidRegistry.WATER && entityIn.getNavigator().tryMoveToXYZ(pos.getX(), pos.getY(), pos.getZ(), 1.0)) {
								waterFound = true;
								if (rand.nextInt(200) == 0) {
									this.delayTemptCounter = 0;
									return false;
								} else if (this.entityIn.collidedHorizontally && this.entityIn.motionX == 0 && this.entityIn.motionZ == 0) {
									this.delayTemptCounter = 0;
									return false;
								} else {
									return true;
								}
							}
						}
					}
				}
			}

			if (!waterFound) {
				this.delayTemptCounter = 0;
				Vec3d vec3d = RandomPositionGenerator.findRandomTarget(this.entityIn, 20, 4);
				if (vec3d != null) {
					this.delayTemptCounter = 0;
					this.entityIn.getNavigator().tryMoveToXYZ(vec3d.x, vec3d.y, vec3d.z, this.speed);
					this.resetTask();
				}
				return false;
			}
		}

		return false;
	}

	public boolean shouldContinueExecuting()
	{
		return !this.entityIn.getNavigator().noPath();
	}

	public void resetTask()
	{
		this.temptingPlayer = null;
		this.entityIn.getNavigator().clearPath();
		this.isRunning = false;
	}

	public void startExecuting()
	{

		double x = this.entityIn.posX;
		double y = this.entityIn.posY;
		double z = this.entityIn.posZ;

		BlockPos currentpos = new BlockPos(x, y, z);
		Block poschk = entityIn.world.getBlockState(currentpos).getBlock();

		if (poschk != Blocks.WATER) {

			boolean waterFound = false;
			int loc = 24;
			int newloc = 24;
			BlockPos pos = new BlockPos(x, y, z);
			BlockPos waterPos = new BlockPos(x, y, z);
			
			for (int i = -16; i < 16; i++) {
				for (int j = -3; j < 3; j++) {
					for (int k = -16; k < 16; k++) {

						pos = new BlockPos(x + i, y + j, z + k);
						Block blockchk = entityIn.world.getBlockState(pos).getBlock();
						Biome biomegenbase = entityIn.world.getBiome(pos); 

						List<EntityAnimaniaHorse> others = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaHorse.class, 2, entityIn.world, pos);

						if (blockchk == BlockHandler.blockTrough && others.size() < 2) {
							TileEntityTrough te = (TileEntityTrough) this.entityIn.world.getTileEntity(pos);
							if (te != null && te.fluidHandler.getFluid() != null && te.fluidHandler.getFluid().getFluid() == FluidRegistry.WATER) {
								waterFound = true;
								newloc = Math.abs(i)  +  Math.abs(j) +  Math.abs(k);

								if (newloc < loc) {

									loc = newloc;

									if (entityIn.posX < waterPos.getX()) {
										BlockPos waterPoschk = new BlockPos(x + i + 1, y + j, z + k);
										Block waterBlockchk = entityIn.world.getBlockState(waterPoschk).getBlock();
										if (waterBlockchk == BlockHandler.blockTrough ) {
											i = i + 1;
										}
									} 

									if (entityIn.posZ < waterPos.getZ()) {
										BlockPos waterPoschk = new BlockPos(x + i, y + j, z + k + 1);
										Block waterBlockchk = entityIn.world.getBlockState(waterPoschk).getBlock();
										if (waterBlockchk == BlockHandler.blockTrough ) {
											k = k + 1;
										} 
									}

									waterPos = new BlockPos(x + i, y + j, z + k);

								}
							}
						}

						if (blockchk == Blocks.WATER && others.size() < 2 && !BiomeDictionary.hasType(biomegenbase, Type.OCEAN) && !BiomeDictionary.hasType(biomegenbase, Type.BEACH)) {
							waterFound = true;
							newloc = Math.abs(i)  +  Math.abs(j) +  Math.abs(k);

							if (newloc < loc) {

								loc = newloc;

								if (entityIn.posX < waterPos.getX()) {
									BlockPos waterPoschk = new BlockPos(x + i + 1, y + j, z + k);
									Block waterBlockchk = entityIn.world.getBlockState(waterPoschk).getBlock();
									if (waterBlockchk == Blocks.WATER ) {
										i = i + 1;
									}
								} 

								if (entityIn.posZ < waterPos.getZ()) {
									BlockPos waterPoschk = new BlockPos(x + i, y + j, z + k + 1);
									Block waterBlockchk = entityIn.world.getBlockState(waterPoschk).getBlock();
									if (waterBlockchk == Blocks.WATER ) {
										k = k + 1;
									} 
								}

								waterPos = new BlockPos(x + i, y + j, z + k);

							}
						} 
					}
				}
			}

			if (waterFound) {

				Block waterBlockchk = entityIn.world.getBlockState(waterPos).getBlock();
				Biome biomegenbase = entityIn.world.getBiome(waterPos); 

				if (waterBlockchk == BlockHandler.blockTrough) {

					if(this.entityIn.getNavigator().tryMoveToXYZ(waterPos.getX() + .5, waterPos.getY(), waterPos.getZ() + .5, this.speed) == false) {
						this.delayTemptCounter = 0;
						this.entityIn.getLookHelper().setLookPosition(waterPos.getX(), waterPos.getY(), waterPos.getZ(), 0.0F, 0.0F);
						this.resetTask();
					} else {
						this.entityIn.getNavigator().tryMoveToXYZ(waterPos.getX() + .5, waterPos.getY(), waterPos.getZ() + .5, this.speed);
						this.entityIn.getLookHelper().setLookPosition(waterPos.getX(), waterPos.getY(), waterPos.getZ(), 0.0F, 0.0F);
					}


				} else if (waterBlockchk == Blocks.WATER && !BiomeDictionary.hasType(biomegenbase, Type.OCEAN) && !BiomeDictionary.hasType(biomegenbase, Type.BEACH)) {
					if(this.entityIn.getNavigator().tryMoveToXYZ(waterPos.getX(), waterPos.getY(), waterPos.getZ(), this.speed) == false) {
						this.entityIn.getLookHelper().setLookPosition(waterPos.getX(), waterPos.getY(), waterPos.getZ(), 0.0F, 0.0F);
						this.delayTemptCounter = 0;
						this.resetTask();
					} else {
						this.entityIn.getNavigator().tryMoveToXYZ(waterPos.getX(), waterPos.getY(), waterPos.getZ(), this.speed);
						this.entityIn.getLookHelper().setLookPosition(waterPos.getX(), waterPos.getY(), waterPos.getZ(), 0.0F, 0.0F);
					}
				} else {
					this.delayTemptCounter = 0;
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