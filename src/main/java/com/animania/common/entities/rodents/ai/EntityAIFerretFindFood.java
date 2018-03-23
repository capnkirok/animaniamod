package com.animania.common.entities.rodents.ai;

import java.util.Random;

import com.animania.common.entities.rodents.EntityFerretBase;
import com.animania.common.handler.BlockHandler;
import com.animania.common.tileentities.TileEntityNest;
import com.animania.common.tileentities.TileEntityNest.NestContent;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class EntityAIFerretFindFood extends EntityAIBase
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

	public EntityAIFerretFindFood(EntityCreature temptedEntityIn, double speedIn)
	{
		this.temptedEntity = temptedEntityIn;
		this.speed = speedIn;
		this.setMutexBits(3);
		this.delayTemptCounter = 0;
	}

	public boolean shouldExecute()
	{
		this.delayTemptCounter++;

		if (this.delayTemptCounter < 60)
		{
			return false;
		}
		else if (delayTemptCounter >= 60)
		{
			if (this.temptedEntity instanceof EntityFerretBase)
			{
				EntityFerretBase entity = (EntityFerretBase) this.temptedEntity;
				if (entity.getFed())
				{
					this.delayTemptCounter = 0;
					return false;
				}
			}
			
			if (this.temptedEntity.getRNG().nextInt(100) == 0)
			{
				Vec3d vec3d = RandomPositionGenerator.findRandomTarget(this.temptedEntity, 20, 4);
				if (vec3d != null) {
					this.delayTemptCounter = 0;
					this.resetTask();
					this.temptedEntity.getNavigator().tryMoveToXYZ(vec3d.xCoord, vec3d.yCoord, vec3d.zCoord, this.speed);
				}
				return false;
			}

			BlockPos currentpos = new BlockPos(temptedEntity.posX, temptedEntity.posY, temptedEntity.posZ);
			Block poschk = temptedEntity.world.getBlockState(currentpos).getBlock();

			if (poschk == BlockHandler.blockNest)
			{
				TileEntityNest te = (TileEntityNest) temptedEntity.world.getTileEntity(currentpos);

				if (te == null ? true : te.getNestContent() == NestContent.EMPTY)
					return false;

				if ((te.getNestContent() == NestContent.CHICKEN_BROWN || te.getNestContent() == NestContent.CHICKEN_WHITE || te.getNestContent() == NestContent.PEACOCK_BLUE || te.getNestContent() == NestContent.PEACOCK_WHITE)  && te.itemHandler.getStackInSlot(0).getCount() > 0)
				{
					te.itemHandler.extractItem(0, 1, false);
					te.markDirty();

					if (temptedEntity instanceof EntityFerretBase)
					{
						EntityFerretBase ech = (EntityFerretBase) temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
						ech.setWatered(true);
					}
					this.delayTemptCounter = 0;
					return false;

				}
			}

			double x = this.temptedEntity.posX;
			double y = this.temptedEntity.posY;
			double z = this.temptedEntity.posZ;

			boolean foodFound = false;
			Random rand = new Random();

			BlockPos pos = new BlockPos(x, y, z);

			for (int i = -16; i < 16; i++)
			{
				for (int j = -3; j < 3; j++)
				{
					for (int k = -16; k < 16; k++)
					{

						pos = new BlockPos(x + i, y + j, z + k);

						Block blockchk = temptedEntity.world.getBlockState(pos).getBlock();

						if (blockchk == BlockHandler.blockNest)
						{
							TileEntityNest te = (TileEntityNest) temptedEntity.world.getTileEntity(pos);

							if (te != null && (te.getNestContent() == NestContent.CHICKEN_BROWN || te.getNestContent() == NestContent.CHICKEN_WHITE || te.getNestContent() == NestContent.PEACOCK_BLUE || te.getNestContent() == NestContent.PEACOCK_WHITE) )
							{
								foodFound = true;
								if (rand.nextInt(200) == 0)
								{
									this.delayTemptCounter = 0;
									return false;
								}
								else if (this.temptedEntity.isCollidedHorizontally && this.temptedEntity.motionX == 0 && this.temptedEntity.motionZ == 0)
								{
									this.delayTemptCounter = 0;
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

			if (!foodFound)
			{
				this.delayTemptCounter = 0;
				return false;
			}
		}
		
		return false;

	}

	public boolean continueExecuting()
    {
        return !this.temptedEntity.getNavigator().noPath();
    }
	
	public void resetTask()
	{
		this.temptingPlayer = null;
		this.temptedEntity.getNavigator().clearPathEntity();
		this.isRunning = false;
	}

	public void startExecuting()
	{
		double x = this.temptedEntity.posX;
		double y = this.temptedEntity.posY;
		double z = this.temptedEntity.posZ;

		boolean foodFound = false;
		int loc = 24;
		int newloc = 24;
		BlockPos pos = new BlockPos(x, y, z);
		BlockPos foodPos = new BlockPos(x, y, z);

		for (int i = -16; i < 16; i++)
		{
			for (int j = -3; j < 3; j++)
			{
				for (int k = -16; k < 16; k++)
				{

					pos = new BlockPos(x + i, y + j, z + k);
					Block blockchk = temptedEntity.world.getBlockState(pos).getBlock();
					if (blockchk == BlockHandler.blockNest)
					{
						TileEntityNest te = (TileEntityNest) temptedEntity.world.getTileEntity(pos);

						if (te != null && (te.getNestContent() == NestContent.CHICKEN_BROWN || te.getNestContent() == NestContent.CHICKEN_WHITE || te.getNestContent() == NestContent.PEACOCK_BLUE || te.getNestContent() == NestContent.PEACOCK_WHITE) )
						{

							foodFound = true;
							newloc = Math.abs(i) + Math.abs(j) + Math.abs(k);

							if (newloc < loc)
							{

								loc = newloc;

								if (temptedEntity.posX < foodPos.getX())
								{
									BlockPos foodPoschk = new BlockPos(x + i + 1, y + j, z + k);
									Block foodBlockchk = temptedEntity.world.getBlockState(foodPoschk).getBlock();
									i = i + 1;
								}

								if (temptedEntity.posZ < foodPos.getZ())
								{
									BlockPos foodPoschk = new BlockPos(x + i, y + j, z + k + 1);
									Block foodBlockchk = temptedEntity.world.getBlockState(foodPoschk).getBlock();
									k = k + 1;
								}

								foodPos = new BlockPos(x + i, y + j, z + k);

							}
						}
					}
				}
			}
		}

		if (foodFound)
		{

			System.out.println("pathing");

			Block foodBlockchk = temptedEntity.world.getBlockState(foodPos).getBlock();

			if (foodBlockchk == BlockHandler.blockNest)
			{
				TileEntityNest te = (TileEntityNest) temptedEntity.world.getTileEntity(foodPos);

				if (te != null && (te.getNestContent() == NestContent.CHICKEN_BROWN || te.getNestContent() == NestContent.CHICKEN_WHITE || te.getNestContent() == NestContent.PEACOCK_BLUE || te.getNestContent() == NestContent.PEACOCK_WHITE) )
				{
					if (this.temptedEntity.getNavigator().tryMoveToXYZ(foodPos.getX() + .7, foodPos.getY(), foodPos.getZ(), this.speed) == false)
					{
						this.delayTemptCounter = 0;
					}
					else
					{
						this.temptedEntity.getNavigator().tryMoveToXYZ(foodPos.getX() + .7, foodPos.getY(), foodPos.getZ(), this.speed);
					}
				}
			}
		}
	}

	public boolean isRunning()
	{
		return this.isRunning;
	}
}