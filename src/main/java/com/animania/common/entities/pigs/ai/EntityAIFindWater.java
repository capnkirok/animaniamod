package com.animania.common.entities.pigs.ai;

import java.util.Random;

import com.animania.common.entities.pigs.EntityAnimaniaPig;
import com.animania.common.entities.pigs.EntityHogDuroc;
import com.animania.common.entities.pigs.EntityHogHampshire;
import com.animania.common.entities.pigs.EntityHogLargeBlack;
import com.animania.common.entities.pigs.EntityHogLargeWhite;
import com.animania.common.entities.pigs.EntityHogOldSpot;
import com.animania.common.entities.pigs.EntityHogYorkshire;
import com.animania.common.entities.pigs.EntityPigletDuroc;
import com.animania.common.entities.pigs.EntityPigletHampshire;
import com.animania.common.entities.pigs.EntityPigletLargeBlack;
import com.animania.common.entities.pigs.EntityPigletLargeWhite;
import com.animania.common.entities.pigs.EntityPigletOldSpot;
import com.animania.common.entities.pigs.EntityPigletYorkshire;
import com.animania.common.entities.pigs.EntitySowDuroc;
import com.animania.common.entities.pigs.EntitySowHampshire;
import com.animania.common.entities.pigs.EntitySowLargeBlack;
import com.animania.common.entities.pigs.EntitySowLargeWhite;
import com.animania.common.entities.pigs.EntitySowOldSpot;
import com.animania.common.entities.pigs.EntitySowYorkshire;
import com.animania.common.handler.BlockHandler;
import com.animania.common.tileentities.TileEntityTrough;
import com.animania.config.AnimaniaConfig;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
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
	private final EntityAnimaniaPig entityIn;
	private final double         speed;
	private double               targetX;
	private double               targetY;
	private double               targetZ;
	private double               pitch;
	private double               yaw;
	private EntityPlayer         temptingPlayer;
	private int                  delayTemptCounter;
	private boolean              isRunning;

	public EntityAIFindWater(EntityAnimaniaPig temptedEntityIn, double speedIn) {
		this.entityIn = temptedEntityIn;
		this.speed = speedIn;
		this.setMutexBits(3);
		this.delayTemptCounter = 0;
	}

	@Override
	public boolean shouldExecute() {

		delayTemptCounter++;
		if (this.delayTemptCounter <= AnimaniaConfig.gameRules.ticksBetweenAIFirings) {
			return false;
		} else if (delayTemptCounter > AnimaniaConfig.gameRules.ticksBetweenAIFirings) {

			if (!entityIn.world.isDaytime() || entityIn.getSleeping()) {
				this.delayTemptCounter = 0;
				return false;
			}

			if (entityIn.getWatered()) {
				this.delayTemptCounter = 0;
				return false;
			}

			if (this.entityIn.getRNG().nextInt(100) == 0)
			{
				Vec3d vec3d = RandomPositionGenerator.findRandomTarget(this.entityIn, 20, 4);
				if (vec3d != null) {
					this.delayTemptCounter = 0;
					this.resetTask();
					this.entityIn.getNavigator().tryMoveToXYZ(vec3d.x, vec3d.y, vec3d.z, this.speed);
				}
				return false;
			}

			Random rand = new Random();

			Biome biomegenbase = this.entityIn.world.getBiome(new BlockPos(this.entityIn.posX, this.entityIn.posY, this.entityIn.posZ));

			BlockPos currentpos = new BlockPos(this.entityIn.posX, this.entityIn.posY, this.entityIn.posZ);
			BlockPos currentposlower = new BlockPos(this.entityIn.posX, this.entityIn.posY - 1, this.entityIn.posZ);
			Block poschk = this.entityIn.world.getBlockState(currentpos).getBlock();
			Block poschk2 = this.entityIn.world.getBlockState(currentposlower).getBlock();

			if (poschk == BlockHandler.blockTrough) {
				TileEntityTrough te = (TileEntityTrough) this.entityIn.world.getTileEntity(currentpos);
				if (te != null && te.fluidHandler.getFluid() != null && te.fluidHandler.getFluid().getFluid() == FluidRegistry.WATER) {

					te.consumeLiquid(100);

					if (entityIn instanceof EntityAnimaniaPig) {
						EntityAnimaniaPig ech = (EntityAnimaniaPig)entityIn;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					} 
					this.delayTemptCounter = 0;
					return false;
				}
			}
			else if (poschk2 == Blocks.WATER && !BiomeDictionary.hasType(biomegenbase, Type.OCEAN) && !BiomeDictionary.hasType(biomegenbase, Type.BEACH)) {

				if (entityIn instanceof EntityAnimaniaPig) {
					EntityAnimaniaPig ech = (EntityAnimaniaPig)entityIn;
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

			for (int i = -16; i < 16; i++)
				for (int j = -3; j < 3; j++)
					for (int k = -16; k < 16; k++) {

						pos = new BlockPos(x + i, y + j, z + k);
						Block blockchk = this.entityIn.world.getBlockState(pos).getBlock();

						if (blockchk == Blocks.WATER) {
							waterFound = true;
							if (rand.nextInt(200) == 0) {
								this.delayTemptCounter = 0;
								return false;
							}
							else if (this.entityIn.collidedHorizontally && this.entityIn.motionX == 0
									&& this.entityIn.motionZ == 0) {
								this.delayTemptCounter = 0;
								return false;
							}
							else
								return true;
						}
						else if (blockchk == BlockHandler.blockTrough) {
							TileEntityTrough te = (TileEntityTrough) this.entityIn.world.getTileEntity(pos);
							if (te != null && te.fluidHandler.getFluid() != null && te.fluidHandler.getFluid().getFluid() == FluidRegistry.WATER) {
								waterFound = true;
								if (rand.nextInt(200) == 0) {
									this.delayTemptCounter = 0;
									return false;
								}
								else if (this.entityIn.collidedHorizontally && this.entityIn.motionX == 0
										&& this.entityIn.motionZ == 0) {
									this.delayTemptCounter = 0;
									return false;
								}
								else
									return true;
							}
						}
					}

			if (!waterFound) {
				this.delayTemptCounter = 0;
				return false;
			}
		}

		return false;
	}

	public boolean shouldContinueExecuting()
	{
		return !this.entityIn.getNavigator().noPath();
	}

	@Override
	public void resetTask() {
		this.temptingPlayer = null;
		this.entityIn.getNavigator().clearPath();
		this.isRunning = false;
	}

	@Override
	public void startExecuting() {

		double x = this.entityIn.posX;
		double y = this.entityIn.posY;
		double z = this.entityIn.posZ;

		BlockPos currentpos = new BlockPos(x, y, z);
		Block poschk = this.entityIn.world.getBlockState(currentpos).getBlock();

		if (poschk != Blocks.WATER) {

			boolean waterFound = false;
			boolean spcFlag = false;
			int loc = 24;
			int newloc = 24;
			BlockPos pos = new BlockPos(x, y, z);
			BlockPos waterPos = new BlockPos(x, y, z);

			for (int i = -16; i < 16; i++)
				for (int j = -3; j < 3; j++)
					for (int k = -16; k < 16; k++) {

						pos = new BlockPos(x + i, y + j, z + k);
						Block blockchk = this.entityIn.world.getBlockState(pos).getBlock();

						Biome biomegenbase = this.entityIn.world.getBiome(pos);

						if (blockchk == BlockHandler.blockTrough) {
							TileEntityTrough te = (TileEntityTrough) this.entityIn.world.getTileEntity(pos);
							if (te != null && te.fluidHandler.getFluid() != null && te.fluidHandler.getFluid().getFluid() == FluidRegistry.WATER) {
								waterFound = true;
								newloc = Math.abs(i) + Math.abs(j) + Math.abs(k);

								if (newloc < loc) {

									loc = newloc;

									if (this.entityIn.posX < waterPos.getX()) {
										BlockPos waterPoschk = new BlockPos(x + i + 1, y + j, z + k);
										Block waterBlockchk = this.entityIn.world.getBlockState(waterPoschk).getBlock();
										if (waterBlockchk == BlockHandler.blockTrough) {
											spcFlag = true;
											i = i + 1;
										}
									}

									if (this.entityIn.posZ < waterPos.getZ()) {
										BlockPos waterPoschk = new BlockPos(x + i, y + j, z + k + 1);
										Block waterBlockchk = this.entityIn.world.getBlockState(waterPoschk).getBlock();
										if (waterBlockchk == BlockHandler.blockTrough) {
											spcFlag = true;
											k = k + 1;
										}
									}

									waterPos = new BlockPos(x + i, y + j, z + k);

									if (spcFlag) {
										i = 10;
										j = 3;
										k = 10;
									}
								}
							}
						}

						if (blockchk == Blocks.WATER && !BiomeDictionary.hasType(biomegenbase, Type.OCEAN)
								&& !BiomeDictionary.hasType(biomegenbase, Type.BEACH)) {
							waterFound = true;
							newloc = Math.abs(i) + Math.abs(j) + Math.abs(k);

							if (newloc < loc) {

								loc = newloc;

								if (this.entityIn.posX < waterPos.getX()) {
									BlockPos waterPoschk = new BlockPos(x + i + 1, y + j, z + k);
									Block waterBlockchk = this.entityIn.world.getBlockState(waterPoschk).getBlock();
									if (waterBlockchk == Blocks.WATER) {
										spcFlag = true;
										i = i + 1;
									}
								}

								if (this.entityIn.posZ < waterPos.getZ()) {
									BlockPos waterPoschk = new BlockPos(x + i, y + j, z + k + 1);
									Block waterBlockchk = this.entityIn.world.getBlockState(waterPoschk).getBlock();
									if (waterBlockchk == Blocks.WATER) {
										spcFlag = true;
										k = k + 1;
									}
								}

								waterPos = new BlockPos(x + i, y + j, z + k);

								if (spcFlag) {
									i = 10;
									j = 3;
									k = 10;
								}
							}
						}
					}

			if (waterFound) {

				Block waterBlockchk = this.entityIn.world.getBlockState(waterPos).getBlock();
				Biome biomegenbase = this.entityIn.world.getBiome(waterPos);

				if (waterBlockchk == BlockHandler.blockTrough) {
					if (this.entityIn.getNavigator().tryMoveToXYZ(waterPos.getX(), waterPos.getY(), waterPos.getZ(), this.speed) == false)
						this.delayTemptCounter = 0;
					else
						this.entityIn.getNavigator().tryMoveToXYZ(waterPos.getX(), waterPos.getY(), waterPos.getZ(), this.speed);

				}
				else if (waterBlockchk == Blocks.WATER && !BiomeDictionary.hasType(biomegenbase, Type.OCEAN)
						&& !BiomeDictionary.hasType(biomegenbase, Type.BEACH))
					if (this.entityIn instanceof EntityPigletYorkshire || this.entityIn instanceof EntityPigletDuroc
							|| this.entityIn instanceof EntityPigletHampshire || this.entityIn instanceof EntityPigletLargeBlack
							|| this.entityIn instanceof EntityPigletLargeWhite || this.entityIn instanceof EntityPigletOldSpot) {
						if (this.entityIn.getNavigator().tryMoveToXYZ(waterPos.getX(), waterPos.getY(), waterPos.getZ(), this.speed) == false)
							this.delayTemptCounter = 0;
						else
							this.entityIn.getNavigator().tryMoveToXYZ(waterPos.getX(), waterPos.getY(), waterPos.getZ(), this.speed);
					}
					else if (this.entityIn.getNavigator().tryMoveToXYZ(waterPos.getX(), waterPos.getY(), waterPos.getZ(), this.speed) == false)
						this.delayTemptCounter = 0;
					else
						this.entityIn.getNavigator().tryMoveToXYZ(waterPos.getX(), waterPos.getY(), waterPos.getZ(), this.speed);
			}

		}

	}

	public boolean isRunning() {
		return this.isRunning;
	}
}