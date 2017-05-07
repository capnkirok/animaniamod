package com.animania.common.entities.pigs.ai;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;

import com.animania.Animania;
import com.animania.common.blocks.BlockTrough;
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
import com.animania.common.tileentities.TileEntityTrough;

public class EntityAIFindFood extends EntityAIBase 
{
	private final EntityCreature temptedEntity;
	private final double speed;
	private double targetX;
	private double targetY;
	private double targetZ;
	private double pitch;
	private double yaw;
	private EntityPlayer temptingPlayer;
	private boolean isRunning;
	private int delayTemptCounter;

	public EntityAIFindFood(EntityCreature temptedEntityIn, double speedIn)
	{
		this.temptedEntity = temptedEntityIn;
		this.speed = speedIn;
		this.setMutexBits(3);
		this.delayTemptCounter = 0;
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	@Override
	public boolean shouldExecute()
	{
		delayTemptCounter++;
		if (delayTemptCounter > 20) {

			if (this.temptedEntity instanceof EntitySowYorkshire) {
				EntitySowYorkshire sow = (EntitySowYorkshire) temptedEntity;
				if (sow.getFed()) {
					return false;
				}
			} else if (temptedEntity instanceof EntitySowDuroc) {
				EntitySowDuroc sow = (EntitySowDuroc)temptedEntity;
				if (sow.getFed()) {
					return false;
				}
			} else if (temptedEntity instanceof EntitySowHampshire) {
				EntitySowHampshire sow = (EntitySowHampshire)temptedEntity;
				if (sow.getFed()) {
					return false;
				}
			} else if (temptedEntity instanceof EntitySowYorkshire) {
				EntitySowYorkshire sow = (EntitySowYorkshire)temptedEntity;
				sow.entityAIEatGrass.startExecuting();
				if (sow.getFed()) {
					return false;
				}
			} else if (temptedEntity instanceof EntitySowLargeBlack) {
				EntitySowLargeBlack sow = (EntitySowLargeBlack)temptedEntity;
				if (sow.getFed()) {
					return false;
				}
			} else if (temptedEntity instanceof EntitySowLargeWhite) {
				EntitySowLargeWhite sow = (EntitySowLargeWhite)temptedEntity;
				if (sow.getFed()) {
					return false;
				}
			} else if (temptedEntity instanceof EntitySowOldSpot) {
				EntitySowOldSpot sow = (EntitySowOldSpot)temptedEntity;
				if (sow.getFed()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityHogOldSpot) {
				EntityHogOldSpot sow = (EntityHogOldSpot)temptedEntity;
				if (sow.getFed()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityHogDuroc) {
				EntityHogDuroc sow = (EntityHogDuroc)temptedEntity;
				if (sow.getFed()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityHogHampshire) {
				EntityHogHampshire sow = (EntityHogHampshire)temptedEntity;
				if (sow.getFed()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityHogYorkshire) {
				EntityHogYorkshire sow = (EntityHogYorkshire)temptedEntity;
				if (sow.getFed()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityHogLargeBlack) {
				EntityHogLargeBlack sow = (EntityHogLargeBlack)temptedEntity;
				if (sow.getFed()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityHogLargeWhite) {
				EntityHogLargeWhite sow = (EntityHogLargeWhite)temptedEntity;
				if (sow.getFed()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityPigletOldSpot) {
				EntityPigletOldSpot sow = (EntityPigletOldSpot)temptedEntity;
				if (sow.getFed()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityPigletDuroc) {
				EntityPigletDuroc sow = (EntityPigletDuroc)temptedEntity;
				if (sow.getFed()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityPigletHampshire) {
				EntityPigletHampshire sow = (EntityPigletHampshire)temptedEntity;
				if (sow.getFed()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityPigletYorkshire) {
				EntityPigletYorkshire sow = (EntityPigletYorkshire)temptedEntity;
				if (sow.getFed()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityPigletLargeBlack) {
				EntityPigletLargeBlack sow = (EntityPigletLargeBlack)temptedEntity;
				if (sow.getFed()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityPigletLargeWhite) {
				EntityPigletLargeWhite sow = (EntityPigletLargeWhite)temptedEntity;
				if (sow.getFed()) {
					return false;
				}
			}

			BlockPos currentpos = new BlockPos(temptedEntity.posX, temptedEntity.posY, temptedEntity.posZ);
			BlockPos trypos1 = new BlockPos(temptedEntity.posX + 1, temptedEntity.posY, temptedEntity.posZ);
			BlockPos trypos2 = new BlockPos(temptedEntity.posX - 1, temptedEntity.posY, temptedEntity.posZ);
			BlockPos trypos3 = new BlockPos(temptedEntity.posX, temptedEntity.posY, temptedEntity.posZ + 1);
			BlockPos trypos4 = new BlockPos(temptedEntity.posX, temptedEntity.posY, temptedEntity.posZ - 1);
			BlockPos trypos5 = new BlockPos(temptedEntity.posX + 1, temptedEntity.posY, temptedEntity.posZ + 1);
			BlockPos trypos6 = new BlockPos(temptedEntity.posX - 1, temptedEntity.posY, temptedEntity.posZ - 1);
			BlockPos trypos7 = new BlockPos(temptedEntity.posX - 1, temptedEntity.posY, temptedEntity.posZ + 1);
			BlockPos trypos8 = new BlockPos(temptedEntity.posX + 1, temptedEntity.posY, temptedEntity.posZ - 1);
			Block poschk = temptedEntity.world.getBlockState(currentpos).getBlock();
			Block poschk1 = temptedEntity.world.getBlockState(trypos1).getBlock();
			Block poschk2 = temptedEntity.world.getBlockState(trypos2).getBlock();
			Block poschk3 = temptedEntity.world.getBlockState(trypos3).getBlock();
			Block poschk4 = temptedEntity.world.getBlockState(trypos4).getBlock();
			Block poschk5 = temptedEntity.world.getBlockState(trypos5).getBlock();
			Block poschk6 = temptedEntity.world.getBlockState(trypos6).getBlock();
			Block poschk7 = temptedEntity.world.getBlockState(trypos7).getBlock();
			Block poschk8 = temptedEntity.world.getBlockState(trypos8).getBlock();

			if (poschk == Animania.blockTrough) {
				//do nothing
			} else if (poschk1 == Animania.blockTrough) {
				currentpos = trypos1;
			} else if (poschk2 == Animania.blockTrough) {
				currentpos = trypos2;
			} else if (poschk3 == Animania.blockTrough) {
				currentpos = trypos3;
			} else if (poschk4 == Animania.blockTrough) {
				currentpos = trypos4;
			} else if (poschk5 == Animania.blockTrough) {
				currentpos = trypos5;
			} else if (poschk6 == Animania.blockTrough) {
				currentpos = trypos6;
			} else if (poschk7 == Animania.blockTrough) {
				currentpos = trypos7;
			} else if (poschk8 == Animania.blockTrough) {
				currentpos = trypos8;
			}

			if (poschk1 == Animania.blockTrough || poschk2 == Animania.blockTrough || poschk3 == Animania.blockTrough || poschk4 == Animania.blockTrough || poschk5 == Animania.blockTrough || poschk6 == Animania.blockTrough || poschk7 == Animania.blockTrough || poschk8 == Animania.blockTrough) {
				TileEntityTrough te = (TileEntityTrough) temptedEntity.world.getTileEntity(currentpos);
				if (te !=null && te.getTroughType() == 9) {
					te.setType(0);
					te.markDirty();
					temptedEntity.world.notifyBlockUpdate(currentpos, poschk.getDefaultState(), poschk.getDefaultState(), 0);
					temptedEntity.world.updateComparatorOutputLevel(currentpos, poschk);

					if (temptedEntity instanceof EntitySowDuroc) {
						EntitySowDuroc ech = (EntitySowDuroc)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setSlopFed(true);
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntitySowHampshire) {
						EntitySowHampshire ech = (EntitySowHampshire)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setSlopFed(true);
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntitySowLargeBlack) {
						EntitySowLargeBlack ech = (EntitySowLargeBlack)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setSlopFed(true);
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntitySowLargeWhite) {
						EntitySowLargeWhite ech = (EntitySowLargeWhite)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setSlopFed(true);
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntitySowOldSpot) {
						EntitySowOldSpot ech = (EntitySowOldSpot)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setSlopFed(true);
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntitySowYorkshire) {
						EntitySowYorkshire ech = (EntitySowYorkshire)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setSlopFed(true);
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntityHogDuroc) {
						EntityHogDuroc ech = (EntityHogDuroc)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setSlopFed(true);
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntityHogHampshire) {
						EntityHogHampshire ech = (EntityHogHampshire)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setSlopFed(true);
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntityHogLargeBlack) {
						EntityHogLargeBlack ech = (EntityHogLargeBlack)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setSlopFed(true);
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntityHogLargeWhite) {
						EntityHogLargeWhite ech = (EntityHogLargeWhite)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setSlopFed(true);
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntityHogOldSpot) {
						EntityHogOldSpot ech = (EntityHogOldSpot)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setSlopFed(true);
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntityHogYorkshire) {
						EntityHogYorkshire ech = (EntityHogYorkshire)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setSlopFed(true);
					} else if (temptedEntity instanceof EntityPigletDuroc) {
						EntityPigletDuroc ech = (EntityPigletDuroc)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setSlopFed(true);
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntityPigletHampshire) {
						EntityPigletHampshire ech = (EntityPigletHampshire)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setSlopFed(true);
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntityPigletLargeBlack) {
						EntityPigletLargeBlack ech = (EntityPigletLargeBlack)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setSlopFed(true);
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntityPigletLargeWhite) {
						EntityPigletLargeWhite ech = (EntityPigletLargeWhite)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setSlopFed(true);
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntityPigletOldSpot) {
						EntityPigletOldSpot ech = (EntityPigletOldSpot)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setSlopFed(true);
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntityPigletOldSpot) {
						EntityPigletYorkshire ech = (EntityPigletYorkshire)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setSlopFed(true);
						ech.setWatered(true);
					}

					//TODO ech.world.playSound(ech.posX, ech.posY, ech.posZ, SoundEvents.ENTITY_HORSE_EAT, SoundCategory.AMBIENT, .2F, .7F, false);

					return false;

				} else if (te !=null && te.getTroughType() == 8) {
					te.setType(9);
					te.markDirty();
					temptedEntity.world.notifyBlockUpdate(currentpos, poschk.getDefaultState(), poschk.getDefaultState(), 9);
					temptedEntity.world.updateComparatorOutputLevel(currentpos, poschk);
					if (temptedEntity instanceof EntitySowDuroc) {
						EntitySowDuroc ech = (EntitySowDuroc)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntitySowHampshire) {
						EntitySowHampshire ech = (EntitySowHampshire)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntitySowLargeBlack) {
						EntitySowLargeBlack ech = (EntitySowLargeBlack)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntitySowLargeWhite) {
						EntitySowLargeWhite ech = (EntitySowLargeWhite)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntitySowOldSpot) {
						EntitySowOldSpot ech = (EntitySowOldSpot)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntitySowYorkshire) {
						EntitySowYorkshire ech = (EntitySowYorkshire)temptedEntity;
						ech.setFed(true);
						ech.entityAIEatGrass.startExecuting();
					} else if (temptedEntity instanceof EntityHogDuroc) {
						EntityHogDuroc ech = (EntityHogDuroc)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityHogHampshire) {
						EntityHogHampshire ech = (EntityHogHampshire)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityHogLargeBlack) {
						EntityHogLargeBlack ech = (EntityHogLargeBlack)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityHogLargeWhite) {
						EntityHogLargeWhite ech = (EntityHogLargeWhite)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityHogOldSpot) {
						EntityHogOldSpot ech = (EntityHogOldSpot)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityHogYorkshire) {
						EntityHogYorkshire ech = (EntityHogYorkshire)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityPigletDuroc) {
						EntityPigletDuroc ech = (EntityPigletDuroc)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityPigletHampshire) {
						EntityPigletHampshire ech = (EntityPigletHampshire)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityPigletLargeBlack) {
						EntityPigletLargeBlack ech = (EntityPigletLargeBlack)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityPigletLargeWhite) {
						EntityPigletLargeWhite ech = (EntityPigletLargeWhite)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityPigletOldSpot) {
						EntityPigletOldSpot ech = (EntityPigletOldSpot)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityPigletYorkshire) {
						EntityPigletYorkshire ech = (EntityPigletYorkshire)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					}
					return false;
				} else if (te !=null && te.getTroughType() == 7) {
					te.setType(8);
					te.markDirty();
					temptedEntity.world.notifyBlockUpdate(currentpos, poschk.getDefaultState(), poschk.getDefaultState(), 8);
					temptedEntity.world.updateComparatorOutputLevel(currentpos, poschk);
					if (temptedEntity instanceof EntitySowDuroc) {
						EntitySowDuroc ech = (EntitySowDuroc)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntitySowHampshire) {
						EntitySowHampshire ech = (EntitySowHampshire)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntitySowLargeBlack) {
						EntitySowLargeBlack ech = (EntitySowLargeBlack)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntitySowLargeWhite) {
						EntitySowLargeWhite ech = (EntitySowLargeWhite)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntitySowOldSpot) {
						EntitySowOldSpot ech = (EntitySowOldSpot)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntitySowYorkshire) {
						EntitySowYorkshire ech = (EntitySowYorkshire)temptedEntity;
						ech.setFed(true);
						ech.entityAIEatGrass.startExecuting();
					} else if (temptedEntity instanceof EntityHogDuroc) {
						EntityHogDuroc ech = (EntityHogDuroc)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityHogHampshire) {
						EntityHogHampshire ech = (EntityHogHampshire)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityHogLargeBlack) {
						EntityHogLargeBlack ech = (EntityHogLargeBlack)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityHogLargeWhite) {
						EntityHogLargeWhite ech = (EntityHogLargeWhite)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityHogOldSpot) {
						EntityHogOldSpot ech = (EntityHogOldSpot)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
					} else if (temptedEntity instanceof EntityHogYorkshire) {
						EntityHogYorkshire ech = (EntityHogYorkshire)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityPigletDuroc) {
						EntityPigletDuroc ech = (EntityPigletDuroc)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityPigletHampshire) {
						EntityPigletHampshire ech = (EntityPigletHampshire)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityPigletLargeBlack) {
						EntityPigletLargeBlack ech = (EntityPigletLargeBlack)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityPigletLargeWhite) {
						EntityPigletLargeWhite ech = (EntityPigletLargeWhite)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityPigletOldSpot) {
						EntityPigletOldSpot ech = (EntityPigletOldSpot)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityPigletYorkshire) {
						EntityPigletYorkshire ech = (EntityPigletYorkshire)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					}
					return false;
				}
			} 

			if (poschk == Blocks.CARROTS || poschk == Blocks.BEETROOTS || poschk == Blocks.POTATOES || poschk == Blocks.WHEAT) {

				if (temptedEntity instanceof EntitySowDuroc) {
					EntitySowDuroc ech = (EntitySowDuroc)temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setFed(true);
				} else if (temptedEntity instanceof EntitySowHampshire) {
					EntitySowHampshire ech = (EntitySowHampshire)temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setFed(true);
				} else if (temptedEntity instanceof EntitySowLargeBlack) {
					EntitySowLargeBlack ech = (EntitySowLargeBlack)temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setFed(true);
				} else if (temptedEntity instanceof EntitySowLargeWhite) {
					EntitySowLargeWhite ech = (EntitySowLargeWhite)temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setFed(true);
				} else if (temptedEntity instanceof EntitySowOldSpot) {
					EntitySowOldSpot ech = (EntitySowOldSpot)temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setFed(true);
				} else if (temptedEntity instanceof EntitySowYorkshire) {
					EntitySowYorkshire ech = (EntitySowYorkshire)temptedEntity;
					ech.setFed(true);
					ech.entityAIEatGrass.startExecuting();
				} else if (temptedEntity instanceof EntityHogDuroc) {
					EntityHogDuroc ech = (EntityHogDuroc)temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setFed(true);
				} else if (temptedEntity instanceof EntityHogHampshire) {
					EntityHogHampshire ech = (EntityHogHampshire)temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setFed(true);
				} else if (temptedEntity instanceof EntityHogLargeBlack) {
					EntityHogLargeBlack ech = (EntityHogLargeBlack)temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setFed(true);
				} else if (temptedEntity instanceof EntityHogLargeWhite) {
					EntityHogLargeWhite ech = (EntityHogLargeWhite)temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setFed(true);
				} else if (temptedEntity instanceof EntityHogOldSpot) {
					EntityHogOldSpot ech = (EntityHogOldSpot)temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setFed(true);
				} else if (temptedEntity instanceof EntityHogYorkshire) {
					EntityHogYorkshire ech = (EntityHogYorkshire)temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setFed(true);
				} else if (temptedEntity instanceof EntityPigletDuroc) {
					EntityPigletDuroc ech = (EntityPigletDuroc)temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setFed(true);
				} else if (temptedEntity instanceof EntityPigletHampshire) {
					EntityPigletHampshire ech = (EntityPigletHampshire)temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setFed(true);
				} else if (temptedEntity instanceof EntityPigletLargeBlack) {
					EntityPigletLargeBlack ech = (EntityPigletLargeBlack)temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setFed(true);
				} else if (temptedEntity instanceof EntityPigletLargeWhite) {
					EntityPigletLargeWhite ech = (EntityPigletLargeWhite)temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setFed(true);
				} else if (temptedEntity instanceof EntityPigletOldSpot) {
					EntityPigletOldSpot ech = (EntityPigletOldSpot)temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setFed(true);
				} else if (temptedEntity instanceof EntityPigletYorkshire) {
					EntityPigletYorkshire ech = (EntityPigletYorkshire)temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setFed(true);
				}

				if (temptedEntity.world.getGameRules().getBoolean("mobGriefing"))
				{
					temptedEntity.world.destroyBlock(currentpos, false);
				}


				return false;
			}

			double x = this.temptedEntity.posX;
			double y = this.temptedEntity.posY;
			double z = this.temptedEntity.posZ;

			boolean foodFound = false;
			Random rand = new Random();

			BlockPos pos = new BlockPos(x, y, z);

			for (int i = -16; i < 16; i++) {
				for (int j = -3; j < 3; j++) {
					for (int k = -16; k < 16; k++) {

						pos = new BlockPos(x + i, y + j, z + k);

						Block blockchk = temptedEntity.world.getBlockState(pos).getBlock();

						if (blockchk == Animania.blockTrough) {
							TileEntityTrough te = (TileEntityTrough) temptedEntity.world.getTileEntity(pos);

							if (te !=null && (te.getTroughType() == 7 || te.getTroughType() == 8 || te.getTroughType() == 9)) {
								foodFound = true;
								if (rand.nextInt(50) == 0) {
									this.delayTemptCounter = 0;
									this.resetTask();
									return false;
								} else if (this.temptedEntity.isCollidedHorizontally && this.temptedEntity.motionX == 0 && this.temptedEntity.motionZ == 0) {
									this.delayTemptCounter = 0;
									this.resetTask();
									return false;
								} else {
									return true;
								}
							}
						}

						if (blockchk == Blocks.CARROTS || blockchk == Blocks.BEETROOTS || blockchk == Blocks.POTATOES || blockchk == Blocks.WHEAT) {

							foodFound = true;
							if (rand.nextInt(20) == 0) {
								this.delayTemptCounter = 0;
								this.resetTask();
								return false;
							} else if (this.temptedEntity.isCollidedHorizontally && this.temptedEntity.motionX == 0 && this.temptedEntity.motionZ == 0) {
								this.delayTemptCounter = 0;
								this.resetTask();
								return false;
							} else {
								return true;
							}
						}

					}

				}
			}

			if (!foodFound) {
				return false;
			}
		}

			return false;
		
	}



	@Override
	public boolean continueExecuting()
	{

		return this.shouldExecute();
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	@Override
	public void startExecuting()
	{	
		this.isRunning = true;
	}

	/**
	 * Resets the task
	 */
	@Override
	public void resetTask()
	{
		this.temptingPlayer = null;
		this.temptedEntity.getNavigator().clearPathEntity();
		this.isRunning = false;

	}


	@Override
	public void updateTask()
	{

		double x = this.temptedEntity.posX;
		double y = this.temptedEntity.posY;
		double z = this.temptedEntity.posZ;

		boolean foodFound = false;
		int loc = 24;
		int newloc = 24;
		BlockPos pos = new BlockPos(x, y, z);
		BlockPos foodPos = new BlockPos(x, y, z);

		for (int i = -16; i < 16; i++) {
			for (int j = -3; j < 3; j++) {
				for (int k = -16; k < 16; k++) {

					pos = new BlockPos(x + i, y + j, z + k);
					Block blockchk = temptedEntity.world.getBlockState(pos).getBlock();


					if (blockchk == Animania.blockTrough) {


						TileEntityTrough te = (TileEntityTrough) temptedEntity.world.getTileEntity(pos);

						if (te !=null && (te.getTroughType() == 7 || te.getTroughType() == 8 || te.getTroughType() == 9)) {

							foodFound = true;
							newloc = Math.abs(i)  +  Math.abs(j) +  Math.abs(k);

							if (newloc < loc) {

								loc = newloc;

								if (temptedEntity.posX < foodPos.getX()) {
									BlockPos foodPoschk = new BlockPos(x + i + 1, y + j, z + k);
									Block foodBlockchk = temptedEntity.world.getBlockState(foodPoschk).getBlock();
									i = i + 1;
								} 

								if (temptedEntity.posZ < foodPos.getZ()) {
									BlockPos foodPoschk = new BlockPos(x + i, y + j, z + k + 1);
									Block foodBlockchk = temptedEntity.world.getBlockState(foodPoschk).getBlock();
									k = k + 1;
								}

								foodPos = new BlockPos(x + i, y + j, z + k);

							}
						}
					} else if (blockchk == Blocks.CARROTS || blockchk == Blocks.WHEAT  || blockchk == Blocks.BEETROOTS || blockchk == Blocks.POTATOES) {
						foodFound = true;
						newloc = Math.abs(i)  +  Math.abs(j) +  Math.abs(k);

						if (newloc < loc) {

							loc = newloc;

							if (temptedEntity.posX < foodPos.getX()) {
								BlockPos foodPoschk = new BlockPos(x + i + 1, y + j, z + k);
								Block foodBlockchk = temptedEntity.world.getBlockState(foodPoschk).getBlock();
								if (foodBlockchk == Blocks.CARROTS || foodBlockchk == Blocks.WHEAT || foodBlockchk == Blocks.BEETROOTS || foodBlockchk == Blocks.POTATOES) {
									i = i + 1;
								}
							} 

							if (temptedEntity.posZ < foodPos.getZ()) {
								BlockPos foodPoschk = new BlockPos(x + i, y + j, z + k + 1);
								Block foodBlockchk = temptedEntity.world.getBlockState(foodPoschk).getBlock();
								if (foodBlockchk == Blocks.CARROTS || foodBlockchk == Blocks.WHEAT || foodBlockchk == Blocks.BEETROOTS || foodBlockchk == Blocks.POTATOES) {
									k = k + 1;
								} 
							}

							foodPos = new BlockPos(x + i, y + j, z + k);

						}
					}
				}
			}
		}

		if (foodFound) {

			Block foodBlockchk = temptedEntity.world.getBlockState(foodPos).getBlock();

			if (foodBlockchk == Animania.blockTrough) {
				TileEntityTrough te = (TileEntityTrough) temptedEntity.world.getTileEntity(foodPos);

				if (te.getTroughType() == 7 || te.getTroughType() == 8 || te.getTroughType() == 9) {
					if(this.temptedEntity.getNavigator().tryMoveToXYZ(foodPos.getX() + .7, foodPos.getY(), foodPos.getZ(), this.speed) == false) {
						this.resetTask();
					} else {
						this.temptedEntity.getNavigator().tryMoveToXYZ(foodPos.getX() + .7, foodPos.getY(), foodPos.getZ(), this.speed);
					}

				}

			} else if (foodBlockchk == Blocks.CARROTS || foodBlockchk == Blocks.WHEAT || foodBlockchk == Blocks.BEETROOTS || foodBlockchk == Blocks.POTATOES) {
				if(this.temptedEntity.getNavigator().tryMoveToXYZ(foodPos.getX(), foodPos.getY(), foodPos.getZ(), this.speed) == false) {
					this.resetTask();
				} else {
					this.temptedEntity.getNavigator().tryMoveToXYZ(foodPos.getX(), foodPos.getY(), foodPos.getZ(), this.speed);
				}
			}
		}

	}


	public boolean isRunning()
	{
		return this.isRunning;
	}
}