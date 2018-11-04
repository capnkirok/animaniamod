package com.animania.common.entities.pigs.ai;

import java.util.List;

import com.animania.Animania;
import com.animania.common.entities.pigs.EntityAnimaniaPig;
import com.animania.common.entities.pigs.EntityHogBase;
import com.animania.common.entities.pigs.EntityPigletBase;
import com.animania.common.entities.pigs.EntitySowBase;
import com.animania.common.handler.BlockHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.config.AnimaniaConfig;

import net.minecraft.block.Block;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class EntityAIFindMud extends EntityAIBase
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

	public EntityAIFindMud(EntityAnimaniaPig temptedEntityIn, double speedIn) {
		this.entityIn = temptedEntityIn;
		this.speed = speedIn;
		this.setMutexBits(3);
		this.delayTemptCounter = 0;
	}

	@Override
	public boolean shouldExecute() {

		delayTemptCounter++;
		if (this.delayTemptCounter < AnimaniaConfig.gameRules.ticksBetweenAIFirings) {
			return false;
		} else if (delayTemptCounter > AnimaniaConfig.gameRules.ticksBetweenAIFirings) {

			if (entityIn.getPlayed()) {
				this.delayTemptCounter = 0;
				return false;
			}

			if (!entityIn.world.isDaytime() || entityIn.getSleeping()) {
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

			BlockPos currentpos = new BlockPos(this.entityIn.posX, this.entityIn.posY, this.entityIn.posZ);
			Block poschk = this.entityIn.world.getBlockState(currentpos).getBlock();
			if (poschk == BlockHandler.blockMud || poschk.getUnlocalizedName().equals("tile.mud")) {
				EntityAnimaniaPig pig = (EntityAnimaniaPig) entityIn;
				pig.setPlayed(true);
				this.delayTemptCounter = 0;
				return false;
			}

			double x = this.entityIn.posX;
			double y = this.entityIn.posY;
			double z = this.entityIn.posZ;

			boolean mudFound = false;

			BlockPos pos = new BlockPos(x, y, z);

			for (int i = -10; i < 10; i++)
				for (int j = -2; j < 2; j++)
					for (int k = -10; k < 10; k++) {

						pos = new BlockPos(x + i, y + j, z + k);
						Block blockchk = this.entityIn.world.getBlockState(pos).getBlock();

						List<EntityAnimaniaPig> others = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaPig.class, 2, entityIn.world, pos);

						if (blockchk != null && (blockchk == BlockHandler.blockMud || blockchk.getUnlocalizedName().equals("tile.mud")) && others.size() < 2) {
							mudFound = true;
							if (Animania.RANDOM.nextInt(200) == 0) {
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

			if (!mudFound) {
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
		if (poschk != BlockHandler.blockMud || !poschk.getUnlocalizedName().equals("tile.mud")) {

			boolean mudFound = false;
			boolean spcFlag = false;
			int loc = 24;
			int newloc = 24;
			BlockPos pos = new BlockPos(x, y, z);
			BlockPos mudPos = new BlockPos(x, y, z);

			for (int i = -10; i < 10; i++)
				for (int j = -2; j < 2; j++)
					for (int k = -10; k < 10; k++) {

						pos = new BlockPos(x + i, y + j, z + k);
						Block blockchk = this.entityIn.world.getBlockState(pos).getBlock();
						if (blockchk == BlockHandler.blockMud || blockchk.getUnlocalizedName().equals("tile.mud")) {
							mudFound = true;
							newloc = Math.abs(i) + Math.abs(j) + Math.abs(k);

							if (newloc < loc) {

								loc = newloc;

								if (this.entityIn.posX > mudPos.getX()) {
									BlockPos mudPoschk = new BlockPos(x + i + 1, y + j, z + k);
									Block mudBlockchk = this.entityIn.world.getBlockState(mudPoschk).getBlock();
									if (mudBlockchk == BlockHandler.blockMud || mudBlockchk.getUnlocalizedName().equals("tile.mud")) {
										spcFlag = true;
										i = i + 1;
									}
								}

								if (this.entityIn.posZ > mudPos.getZ()) {
									BlockPos mudPoschk = new BlockPos(x + i, y + j, z + k + 1);
									Block mudBlockchk = this.entityIn.world.getBlockState(mudPoschk).getBlock();
									if (mudBlockchk == BlockHandler.blockMud || mudBlockchk.getUnlocalizedName().equals("tile.mud")) {
										spcFlag = true;
										k = k + 1;
									}
								}

								mudPos = new BlockPos(x + i, y + j, z + k);

								if (spcFlag) {
									i = 10;
									j = 3;
									k = 10;
								}

							}

						}

					}

			if (mudFound) {

				Block mudBlockchk = this.entityIn.world.getBlockState(mudPos).getBlock();
				if (mudBlockchk == BlockHandler.blockMud || mudBlockchk.getUnlocalizedName().equals("tile.mud")) 
					if (this.entityIn instanceof EntitySowBase) {
						EntitySowBase te = (EntitySowBase) this.entityIn;

						if (te.getMuddy() == false) {
							if (te.posX - mudPos.getX() < 0 && te.posZ - mudPos.getZ() < 0)
								this.entityIn.getNavigator().tryMoveToXYZ(mudPos.getX() + 2, mudPos.getY(), mudPos.getZ() + 2, this.speed);
							else
								this.entityIn.getNavigator().tryMoveToXYZ(mudPos.getX(), mudPos.getY(), mudPos.getZ(), this.speed);

							this.entityIn.getNavigator().tryMoveToXYZ(mudPos.getX(), mudPos.getY(), mudPos.getZ(), this.speed);

						}
					}
					else if (this.entityIn instanceof EntityPigletBase) {
						EntityPigletBase te = (EntityPigletBase) this.entityIn;
						if (te.getMuddy() == false)
							if (te.posX - mudPos.getX() < 0 && te.posZ - mudPos.getZ() < 0)
								this.entityIn.getNavigator().tryMoveToXYZ(mudPos.getX() + 2, mudPos.getY(), mudPos.getZ() + 2, this.speed);
							else
								this.entityIn.getNavigator().tryMoveToXYZ(mudPos.getX() - 1, mudPos.getY(), mudPos.getZ() - 1, this.speed);
					}
					else if (this.entityIn instanceof EntityHogBase) {
						EntityHogBase te = (EntityHogBase) this.entityIn;
						if (te.getMuddy() == false)
							if (te.posX - mudPos.getX() < 0 && te.posZ - mudPos.getZ() < 0)
								this.entityIn.getNavigator().tryMoveToXYZ(mudPos.getX() + 3, mudPos.getY(), mudPos.getZ() + 3, this.speed);
							else if (te.posX - mudPos.getX() > 0 && te.posZ - mudPos.getZ() > 0)
								this.entityIn.getNavigator().tryMoveToXYZ(mudPos.getX() - 1, mudPos.getY(), mudPos.getZ() - 1, this.speed);
							else
								this.entityIn.getNavigator().tryMoveToXYZ(mudPos.getX(), mudPos.getY(), mudPos.getZ(), this.speed);
					}
			}
		}
	}

	public boolean isRunning() {
		return this.isRunning;
	}
}