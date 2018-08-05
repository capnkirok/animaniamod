package com.animania.common.entities.horses.ai;

import java.util.List;

import com.animania.common.entities.horses.EntityAnimaniaHorse;
import com.animania.common.handler.BlockHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.config.AnimaniaConfig;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class EntityAISleep extends EntityAIBase 
{
	private final EntityCreature entityIn;
	private final double speed;
	private double targetX;
	private double targetY;
	private double targetZ;
	private double pitch;
	private double yaw;
	private EntityPlayer temptingPlayer;
	private int delayTemptCounter;
	private boolean isRunning;

	public EntityAISleep(EntityCreature entityInIn, double speedIn)
	{
		this.entityIn = entityInIn;
		this.speed = speedIn;
		this.setMutexBits(3);
		this.delayTemptCounter = 0;
	}

	public boolean shouldExecute()
	{

		delayTemptCounter++;

		if (this.delayTemptCounter <= AnimaniaConfig.gameRules.ticksBetweenAIFirings + entityIn.getRNG().nextInt(100)) {
			return false;
		} else if (delayTemptCounter > AnimaniaConfig.gameRules.ticksBetweenAIFirings) {

			if (entityIn.isBeingRidden()) {
				return false;
			}
			
			if (entityIn.world.isDaytime()) {
				if (entityIn instanceof EntityAnimaniaHorse) {
					EntityAnimaniaHorse ech = (EntityAnimaniaHorse)entityIn;
					if (ech.getSleeping()) {
						this.delayTemptCounter = 0;
						ech.setSleeping(false);
					}
				}
				return false;
			}

			if (entityIn instanceof EntityAnimaniaHorse) {
				EntityAnimaniaHorse ech = (EntityAnimaniaHorse)entityIn;
				if (ech.getSleeping()) {
					this.delayTemptCounter = 0;
					return false;
				}
			}

			if (this.entityIn.getRNG().nextInt(100) == 0)
			{
				this.delayTemptCounter = 0;
				return false;
			}

			BlockPos currentpos = new BlockPos(entityIn.posX, entityIn.posY, entityIn.posZ);
			BlockPos trypos1 = new BlockPos(entityIn.posX + 1, entityIn.posY, entityIn.posZ);
			BlockPos trypos2 = new BlockPos(entityIn.posX - 1, entityIn.posY, entityIn.posZ);
			BlockPos trypos3 = new BlockPos(entityIn.posX, entityIn.posY, entityIn.posZ + 1);
			BlockPos trypos4 = new BlockPos(entityIn.posX, entityIn.posY, entityIn.posZ - 1);

			Block poschk = entityIn.world.getBlockState(currentpos).getBlock();
			Block poschk1 = entityIn.world.getBlockState(trypos1).getBlock();
			Block poschk2 = entityIn.world.getBlockState(trypos2).getBlock();
			Block poschk3 = entityIn.world.getBlockState(trypos3).getBlock();
			Block poschk4 = entityIn.world.getBlockState(trypos4).getBlock();

			String bedBlockString = AnimaniaConfig.careAndFeeding.horseBed;
			ItemStack bedBlockStack = AnimaniaHelper.getItem(bedBlockString);
			Block bedBlock = Block.getBlockFromItem(bedBlockStack.getItem());

			String bedBlockString2 = AnimaniaConfig.careAndFeeding.horseBed2;
			ItemStack bedBlockStack2 = AnimaniaHelper.getItem(bedBlockString2);
			Block bedBlock2 = Block.getBlockFromItem(bedBlockStack2.getItem());

			if (bedBlock != Blocks.AIR) {

				if (poschk == bedBlock) {
					//do nothing
				} else if (poschk1 == bedBlock) {
					currentpos = trypos1;
				} else if (poschk2 == bedBlock) {
					currentpos = trypos2;
				} else if (poschk3 == bedBlock) {
					currentpos = trypos3;
				} else if (poschk4 == bedBlock) {
					currentpos = trypos4;
				}

				poschk = entityIn.world.getBlockState(currentpos).getBlock(); 

				if (entityIn instanceof EntityAnimaniaHorse && poschk == bedBlock) {
					EntityAnimaniaHorse ech = (EntityAnimaniaHorse)entityIn;

					this.resetTask();
					this.delayTemptCounter = 0;
					ech.setSleeping(true);
					return false;
				} 

				poschk = entityIn.world.getBlockState(currentpos.down()).getBlock(); 

				if (entityIn instanceof EntityAnimaniaHorse && poschk == bedBlock) {
					EntityAnimaniaHorse ech = (EntityAnimaniaHorse)entityIn;

					this.resetTask();
					this.delayTemptCounter = 0;
					ech.setSleeping(true);
					return false;
				} 

				if (bedBlock == BlockHandler.blockStraw) {

					poschk = entityIn.world.getBlockState(currentpos.up()).getBlock(); 

					if (entityIn instanceof EntityAnimaniaHorse && poschk == bedBlock) {

						EntityAnimaniaHorse ech = (EntityAnimaniaHorse)entityIn;

						this.resetTask();
						this.delayTemptCounter = 0;
						ech.setSleeping(true);
						return false;
					} 

				}
			}

			if (bedBlock2 != Blocks.AIR) {

				poschk = entityIn.world.getBlockState(currentpos).getBlock(); 

				if (entityIn instanceof EntityAnimaniaHorse && poschk == bedBlock2) {
					EntityAnimaniaHorse ech = (EntityAnimaniaHorse)entityIn;

					this.resetTask();
					this.delayTemptCounter = 0;
					ech.setSleeping(true);
					return false;
				} 

				poschk = entityIn.world.getBlockState(currentpos.down()).getBlock(); 

				if (entityIn instanceof EntityAnimaniaHorse && poschk == bedBlock2) {
					EntityAnimaniaHorse ech = (EntityAnimaniaHorse)entityIn;

					this.resetTask();
					this.delayTemptCounter = 0;
					ech.setSleeping(true);
					return false;
				} 
			}

			double x = this.entityIn.posX;
			double y = this.entityIn.posY;
			double z = this.entityIn.posZ;

			boolean bedFound = false;
			boolean prefBedFound = false;

			BlockPos pos = new BlockPos(x, y, z);

			for (int i = -16; i < 16; i++) {
				for (int j = -3; j < 3; j++) {
					for (int k = -16; k < 16; k++) {

						pos = new BlockPos(x + i, y + j, z + k);
						Block blockchk = entityIn.world.getBlockState(pos).getBlock();

						List<EntityAnimaniaHorse> others = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaHorse.class, 2, entityIn.world, pos);


						if (blockchk == bedBlock && others.size() < 2) {

							prefBedFound = true;
							bedFound = true;
							return true;


						}

						if (!prefBedFound && blockchk instanceof BlockGrass && others.size() < 2) {

							bedFound = true;
							return true;

						}

					}
				}
			}


			if (!bedFound) {
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

		this.delayTemptCounter = 0;
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

		BlockPos currentpos = new BlockPos(x, y - 1, z);
		Block poschk = entityIn.world.getBlockState(currentpos).getBlock();

		boolean bedFound = false;
		boolean prefBedFound = false;
		int loc = 24;
		int newloc = 24;
		BlockPos pos = new BlockPos(x, y - 1, z);
		BlockPos bedPos = new BlockPos(x, y - 1, z);

		String bedBlockString = AnimaniaConfig.careAndFeeding.horseBed;
		ItemStack bedBlockStack = AnimaniaHelper.getItem(bedBlockString);
		Block bedBlock = Block.getBlockFromItem(bedBlockStack.getItem());

		String bedBlockString2 = AnimaniaConfig.careAndFeeding.horseBed2;
		ItemStack bedBlockStack2 = AnimaniaHelper.getItem(bedBlockString2);
		Block bedBlock2 = Block.getBlockFromItem(bedBlockStack2.getItem());

		for (int i = -16; i < 16; i++) {
			for (int j = -3; j < 3; j++) {
				for (int k = -16; k < 16; k++) {

					pos = new BlockPos(x + i, y + j, z + k);
					Block blockchk = entityIn.world.getBlockState(pos).getBlock();

					List<EntityAnimaniaHorse> others = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaHorse.class, 2, entityIn.world, pos);

					if (blockchk == bedBlock && others.size() < 2) {
						bedFound = true;
						prefBedFound = true;
						newloc = Math.abs(i)  +  Math.abs(j) +  Math.abs(k);

						if (newloc < loc) {

							loc = newloc;

							if (entityIn.posX < bedPos.getX()) {
								BlockPos blockPoschk = new BlockPos(x + i + 1, y + j, z + k);
								Block bedBlockchk = entityIn.world.getBlockState(blockPoschk).getBlock();
								if (bedBlockchk == bedBlock ) {
									i = i + 1;
								}
							} 

							if (entityIn.posZ < bedPos.getZ()) {
								BlockPos blockPoschk = new BlockPos(x + i, y + j, z + k + 1);
								Block bedBlockchk = entityIn.world.getBlockState(blockPoschk).getBlock();
								if (bedBlockchk == bedBlock ) {
									k = k + 1;
								} 
							}

							bedPos = new BlockPos(x + i, y + j, z + k);

						}
					} 
				}
			}
		}


		if (!bedFound && !prefBedFound && !this.entityIn.hasPath()) {

			for (int i = -16; i < 16; i++) {
				for (int j = -3; j < 3; j++) {
					for (int k = -16; k < 16; k++) {	

						pos = new BlockPos(x + i, y + j, z + k);
						Block blockchk = entityIn.world.getBlockState(pos).getBlock();

						List<EntityAnimaniaHorse> others = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaHorse.class, 2, entityIn.world, pos);

						if (blockchk == bedBlock2 && others.size() < 2) {
							bedFound = true;
							newloc = Math.abs(i)  +  Math.abs(j) +  Math.abs(k);

							if (newloc < loc) {

								loc = newloc;

								if (entityIn.posX < bedPos.getX()) {
									BlockPos blockPoschk = new BlockPos(x + i + 1, y + j, z + k);
									Block bedBlockchk = entityIn.world.getBlockState(blockPoschk).getBlock();
									if (bedBlockchk == bedBlock2 ) {
										i = i + 1;
									}
								} 

								if (entityIn.posZ < bedPos.getZ()) {
									BlockPos blockPoschk = new BlockPos(x + i, y + j, z + k + 1);
									Block bedBlockchk = entityIn.world.getBlockState(blockPoschk).getBlock();
									if (bedBlockchk == bedBlock2 ) {
										k = k + 1;
									} 
								}

								bedPos = new BlockPos(x + i, y + j, z + k);

							}

						} 

					}
				}
			}
		}


		if (bedFound) {

			Block bedBlockchk = entityIn.world.getBlockState(bedPos).getBlock();

			if ((bedBlockchk == bedBlock || bedBlockchk == bedBlock2) && !this.entityIn.hasPath()) {
				if(this.entityIn.getNavigator().tryMoveToXYZ(bedPos.getX(), bedPos.getY(), bedPos.getZ(), this.speed) == false) {
					this.entityIn.getLookHelper().setLookPosition(bedPos.getX(), bedPos.getY(), bedPos.getZ(), 0.0F, 0.0F);
					this.delayTemptCounter = 0;
					this.resetTask();
				} else {
					this.entityIn.getLookHelper().setLookPosition(bedPos.getX(), bedPos.getY(), bedPos.getZ(), 0.0F, 0.0F);
					this.entityIn.getNavigator().tryMoveToXYZ(bedPos.getX(), bedPos.getY(), bedPos.getZ(), this.speed);
				}

			}
		} else {
			this.delayTemptCounter = 0;
			this.resetTask();
		}
	}

	public boolean isRunning()
	{
		return this.isRunning;
	}
}