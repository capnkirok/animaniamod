package com.animania.common.entities.peacocks.ai;

import java.util.List;

import com.animania.common.entities.peacocks.EntityPeafowlBase;
import com.animania.common.entities.peacocks.EntityPeafowlBlue;
import com.animania.common.entities.peacocks.EntityPeafowlWhite;
import com.animania.common.handler.BlockHandler;
import com.animania.common.tileentities.TileEntityNest;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;

public class EntityAIFindPeacockNest extends EntityAIBase
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

	public EntityAIFindPeacockNest(EntityCreature temptedEntityIn, double speedIn)
	{
		this.temptedEntity = temptedEntityIn;
		this.speed = speedIn;
		this.setMutexBits(3);
		this.delayTemptCounter = 0;
	}

	public boolean shouldExecute()
	{

		delayTemptCounter++;
		if (this.delayTemptCounter <= 40) {
			return false;
		} else if (delayTemptCounter > 40) {

			if (!this.temptedEntity.world.isDaytime()) {
				this.delayTemptCounter = 0;
				return false;
			}

			if (temptedEntity instanceof EntityPeafowlBase) {
				 EntityPeafowlBase entity = (EntityPeafowlBase)temptedEntity;
				if (!entity.getWatered() || !entity.getFed()) {
					this.delayTemptCounter = 0;
					return false;
				}
			} else if (temptedEntity instanceof EntityPeafowlWhite) {
				EntityPeafowlWhite entity = (EntityPeafowlWhite)temptedEntity;
				if (!entity.getWatered() || !entity.getFed()) {
					this.delayTemptCounter = 0;
					return false;
				}
			}

			BlockPos currentpos = new BlockPos(temptedEntity.posX, temptedEntity.posY, temptedEntity.posZ);
			Block poschk = temptedEntity.world.getBlockState(currentpos).getBlock();

			if (poschk == BlockHandler.blockNest) {
				TileEntityNest te = (TileEntityNest) temptedEntity.world.getTileEntity(currentpos);

				if (te.getNestType() == 3 || te.getNestType() == 6 || te.getNestType() == 9 || te.getNestType() == 12 || te.getNestType() == 15 || te.getNestType() == 18 || te.getNestType() == 21) {
					return false;
				}
				
				if (temptedEntity instanceof EntityPeafowlBlue) {
					EntityPeafowlBlue entity = (EntityPeafowlBlue)temptedEntity;

					if (te !=null && te.getNestType() == 0 && !entity.getLaid() ) {
						te.setType(16);
						te.markDirty();
						entity.setLaid(true);
						this.temptedEntity.world.notifyBlockUpdate(currentpos, poschk.getDefaultState(), poschk.getDefaultState(), 1);
						this.temptedEntity.world.updateComparatorOutputLevel(currentpos, poschk);
						this.resetTask();
					} else if (te !=null && te.getNestType() == 16 && !entity.getLaid() ) {
						te.setType(17);
						te.markDirty();
						entity.setLaid(true);
						this.temptedEntity.world.notifyBlockUpdate(currentpos, poschk.getDefaultState(), poschk.getDefaultState(), 1);
						this.temptedEntity.world.updateComparatorOutputLevel(currentpos, poschk);
						this.resetTask();
					} else if (te !=null && te.getNestType() == 17 && !entity.getLaid() ) {
						te.setType(18);
						te.markDirty();
						entity.setLaid(true);
						this.temptedEntity.world.notifyBlockUpdate(currentpos, poschk.getDefaultState(), poschk.getDefaultState(), 1);
						this.temptedEntity.world.updateComparatorOutputLevel(currentpos, poschk);
						this.resetTask();
					}
				} else if (temptedEntity instanceof EntityPeafowlWhite) {
					EntityPeafowlWhite entity = (EntityPeafowlWhite)temptedEntity;
					if (te !=null && te.getNestType() == 0 && !entity.getLaid() ) {
						te.setType(19);
						te.markDirty();
						entity.setLaid(true);
						this.temptedEntity.world.notifyBlockUpdate(currentpos, poschk.getDefaultState(), poschk.getDefaultState(), 1);
						this.temptedEntity.world.updateComparatorOutputLevel(currentpos, poschk);
						this.resetTask();
					} else if (te !=null && te.getNestType() == 19 && !entity.getLaid() ) {
						te.setType(20);
						te.markDirty();
						entity.setLaid(true);
						this.temptedEntity.world.notifyBlockUpdate(currentpos, poschk.getDefaultState(), poschk.getDefaultState(), 1);
						this.temptedEntity.world.updateComparatorOutputLevel(currentpos, poschk);
						this.resetTask();
					} else if (te !=null && te.getNestType() == 20 && !entity.getLaid() ) {
						te.setType(21);
						te.markDirty();
						entity.setLaid(true);
						this.temptedEntity.world.notifyBlockUpdate(currentpos, poschk.getDefaultState(), poschk.getDefaultState(), 1);
						this.temptedEntity.world.updateComparatorOutputLevel(currentpos, poschk);
						this.resetTask();
					}
				}
				return false;
			}

			double x = this.temptedEntity.posX;
			double y = this.temptedEntity.posY;
			double z = this.temptedEntity.posZ;

			boolean nestFound = false;

			BlockPos pos = new BlockPos(x, y, z);

			for (int i = -10; i < 10; i++) {
				for (int j = -3; j < 3; j++) {
					for (int k = -10; k < 10; k++) {

						pos = new BlockPos(x + i, y + j, z + k);
						Block blockchk = temptedEntity.world.getBlockState(pos).getBlock();

						if (blockchk == BlockHandler.blockNest) {

							TileEntityNest te = (TileEntityNest) temptedEntity.world.getTileEntity(pos);
							int nestType = te.getNestType();

							if (nestType == 30) { 
								//do nothing
							} else {
								if (temptedEntity instanceof EntityPeafowlBlue && (nestType == 0 || nestType == 16 || nestType == 17 || nestType == 18)) {
									nestFound = true;
									return true;
								} else if (temptedEntity instanceof EntityPeafowlWhite && (nestType == 0 || nestType == 19 || nestType == 20  || nestType == 21)) {
									nestFound = true;
									return true;
								} 
							}
						}
					}
				}
			}

			if (!nestFound) {
				return false;
			}
		}

		return false;
	}

	public boolean continueExecuting()
	{

		return this.shouldExecute();
	}
	
	public void startExecuting()
	{	
		this.isRunning = true;
	}

	public void resetTask()
	{
		this.temptingPlayer = null;
		this.temptedEntity.getNavigator().clearPathEntity();
		this.isRunning = false;
	}

	public void updateTask()
	{

		double x = this.temptedEntity.posX;
		double y = this.temptedEntity.posY;
		double z = this.temptedEntity.posZ;

		BlockPos currentpos = new BlockPos(x, y, z);
		Block poschk = temptedEntity.world.getBlockState(currentpos).getBlock();
		if (poschk != BlockHandler.blockNest) {

			boolean nestFound = false;
			int loc = 24;
			int newloc = 24;
			BlockPos pos = new BlockPos(x, y, z);
			BlockPos nestPos = new BlockPos(x, y, z);

			for (int i = -10; i < 10; i++) {
				for (int j = -3; j < 3; j++) {
					for (int k = -10; k < 10; k++) {

						pos = new BlockPos(x + i, y + j, z + k);
						Block blockchk = temptedEntity.world.getBlockState(pos).getBlock();

						if (blockchk == BlockHandler.blockNest && !temptedEntity.hasPath()) {

							TileEntityNest te = (TileEntityNest) temptedEntity.world.getTileEntity(pos);
							int nestType = te.getNestType();
							if (nestType == 20) { 
								//do nothing
							} else {
								if (temptedEntity instanceof EntityPeafowlBlue && (nestType == 0 || nestType == 1 || nestType == 2 || nestType == 3)) {
									nestFound = true;
								} else if (temptedEntity instanceof EntityPeafowlWhite && (nestType == 0 || nestType == 4 || nestType == 5 || nestType == 6)) {
									nestFound = true;
								} 
							}

							if (nestFound == true) {

								newloc = Math.abs(i)  +  Math.abs(j) +  Math.abs(k);

								if (newloc < loc) {

									loc = newloc;

									if (temptedEntity.posX < nestPos.getX()) {
										BlockPos nestPoschk = new BlockPos(x + i + 1, y + j, z + k);
										Block nestBlockchk = temptedEntity.world.getBlockState(nestPoschk).getBlock();
										if (nestBlockchk == BlockHandler.blockNest) {
											i = i + 1;
										}
									} 

									if (temptedEntity.posZ < nestPos.getZ()) {
										BlockPos nestPoschk = new BlockPos(x + i, y + j, z + k + 1);
										Block nestBlockchk = temptedEntity.world.getBlockState(nestPoschk).getBlock();
										if (nestBlockchk == BlockHandler.blockNest) {
											k = k + 1;
										} 
									}

									nestPos = new BlockPos(x + i, y + j, z + k);

								}
							}
						}
					}
				}
			}

			if (nestFound) {

				Block nestBlockchk = temptedEntity.world.getBlockState(nestPos).getBlock();
				List<Entity> nestClear = temptedEntity.world.getEntitiesWithinAABBExcludingEntity(temptedEntity, temptedEntity.getEntityBoundingBox().expandXyz(1));

				if (nestBlockchk == BlockHandler.blockNest && nestClear.isEmpty()) {
					this.temptedEntity.getNavigator().tryMoveToXYZ(nestPos.getX() + .50, nestPos.getY(), nestPos.getZ() + .50, this.speed);
				} else {
					//this.temptedEntity.getNavigator().tryMoveToXYZ(nestPos.getX(), nestPos.getY(), nestPos.getZ(), this.speed);

				}
			}
		}
	}

	public boolean isRunning()
	{
		return this.isRunning;
	}
}