package com.animania.common.entities.rodents.ai;

import com.animania.Animania;
import com.animania.common.entities.rodents.EntityHedgehogBase;
import com.animania.common.handler.BlockHandler;
import com.animania.common.tileentities.TileEntityNest;
import com.animania.common.tileentities.TileEntityNest.NestContent;
import com.animania.config.AnimaniaConfig;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;

public class EntityAIHedgehogFindNests extends EntityAIBase
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

	public EntityAIHedgehogFindNests(EntityCreature temptedEntityIn, double speedIn)
	{
		this.temptedEntity = temptedEntityIn;
		this.speed = speedIn;
		this.setMutexBits(3);
		this.delayTemptCounter = 0;
	}

	@Override
	public boolean shouldExecute()
	{
		delayTemptCounter++;
		if (this.delayTemptCounter < 60)
		{
			return false;
		}
		else if (delayTemptCounter > 60)
		{

			if (this.temptedEntity instanceof EntityHedgehogBase)
			{
				EntityHedgehogBase entity = (EntityHedgehogBase) this.temptedEntity;
				if (entity.getFed())
				{
					this.delayTemptCounter = 0;
					return false;
				}
				
				if (entity.getSleeping())
				{
					this.delayTemptCounter = 0;
					return false;
				}
			}
			
			

			BlockPos currentpos = new BlockPos(this.temptedEntity.posX, this.temptedEntity.posY, this.temptedEntity.posZ);
			Block poschk = this.temptedEntity.world.getBlockState(currentpos).getBlock();

			if (poschk == BlockHandler.blockNest)
			{
				TileEntityNest te = (TileEntityNest) this.temptedEntity.world.getTileEntity(currentpos);

				if (te == null ? true : te.getNestContent() == NestContent.EMPTY) {
					this.delayTemptCounter = 0;
					return false;
				}

				if ((te.getNestContent() == NestContent.CHICKEN_BROWN || te.getNestContent() == NestContent.CHICKEN_WHITE) && te.itemHandler.getStackInSlot(0).getCount() > 0)
				{
					te.itemHandler.extractItem(0, 1, false);
					te.markDirty();
					if (this.temptedEntity instanceof EntityHedgehogBase)
					{
						EntityHedgehogBase ech = (EntityHedgehogBase) this.temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
						ech.setWatered(true);
					}
					this.delayTemptCounter = 0;
					return false;

				}
			}

			if (poschk == Blocks.CARROTS || poschk == Blocks.BEETROOTS || poschk == Blocks.POTATOES)
			{

				if (this.temptedEntity instanceof EntityHedgehogBase)
				{
					EntityHedgehogBase ech = (EntityHedgehogBase) this.temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setFed(true);
				}

				if (AnimaniaConfig.gameRules.plantsRemovedAfterEating) {
					temptedEntity.world.destroyBlock(currentpos, false);
				}
				this.delayTemptCounter = 0;
				return false;
			}

			double x = this.temptedEntity.posX;
			double y = this.temptedEntity.posY;
			double z = this.temptedEntity.posZ;

			boolean foodFound = false;

			BlockPos pos = new BlockPos(x, y, z);

			for (int i = -16; i < 16; i++)
				for (int j = -3; j < 3; j++)
					for (int k = -16; k < 16; k++)
					{

						pos = new BlockPos(x + i, y + j, z + k);

						Block blockchk = this.temptedEntity.world.getBlockState(pos).getBlock();

						if (blockchk == BlockHandler.blockNest)
						{
							TileEntityNest te = (TileEntityNest) this.temptedEntity.world.getTileEntity(pos);

							if (te != null && (te.getNestContent() == NestContent.CHICKEN_BROWN || te.getNestContent() == NestContent.CHICKEN_WHITE) )
							{
								foodFound = true;
								if (Animania.RANDOM.nextInt(200) == 0)
								{
									this.delayTemptCounter = 0;
									return false;
								}
								else if (this.temptedEntity.collidedHorizontally && this.temptedEntity.motionX == 0 && this.temptedEntity.motionZ == 0)
								{
									this.delayTemptCounter = 0;
									return false;
								}
								else
									return true;
							}
						}

						if (blockchk == Blocks.CARROTS || blockchk == Blocks.BEETROOTS || blockchk == Blocks.POTATOES)
						{

							foodFound = true;
							if (Animania.RANDOM.nextInt(200) == 0)
							{
								this.delayTemptCounter = 0;
								return false;
							}
							else if (this.temptedEntity.collidedHorizontally && this.temptedEntity.motionX == 0 && this.temptedEntity.motionZ == 0)
							{
								this.delayTemptCounter = 0;
								return false;
							}
							else
								return true;
						}
					}

			if (!foodFound) {
				this.delayTemptCounter = 0; 
				return false;
			}
		}

		return false;

	}

	public boolean shouldContinueExecuting()
	{
		return !this.temptedEntity.getNavigator().noPath();
	}

	@Override
	public void resetTask()
	{
		this.temptingPlayer = null;
		this.temptedEntity.getNavigator().clearPath();
		this.isRunning = false;

	}

	@Override
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
			for (int j = -3; j < 3; j++)
				for (int k = -16; k < 16; k++)
				{

					pos = new BlockPos(x + i, y + j, z + k);
					Block blockchk = this.temptedEntity.world.getBlockState(pos).getBlock();

					if (blockchk == BlockHandler.blockNest)
					{

						TileEntityNest te = (TileEntityNest) this.temptedEntity.world.getTileEntity(pos);

						if (te != null && (te.getNestContent() == NestContent.CHICKEN_BROWN || te.getNestContent() == NestContent.CHICKEN_WHITE) )
						{

							foodFound = true;
							newloc = Math.abs(i) + Math.abs(j) + Math.abs(k);

							if (newloc < loc)
							{

								loc = newloc;

								if (this.temptedEntity.posX < foodPos.getX())
								{
									BlockPos foodPoschk = new BlockPos(x + i + 1, y + j, z + k);
									Block foodBlockchk = this.temptedEntity.world.getBlockState(foodPoschk).getBlock();
									i = i + 1;
								}

								if (this.temptedEntity.posZ < foodPos.getZ())
								{
									BlockPos foodPoschk = new BlockPos(x + i, y + j, z + k + 1);
									Block foodBlockchk = this.temptedEntity.world.getBlockState(foodPoschk).getBlock();
									k = k + 1;
								}

								foodPos = new BlockPos(x + i, y + j, z + k);

							}
						}
					}
					else if (blockchk == Blocks.CARROTS || blockchk == Blocks.BEETROOTS || blockchk == Blocks.POTATOES)
					{
						foodFound = true;
						newloc = Math.abs(i) + Math.abs(j) + Math.abs(k);

						if (newloc < loc)
						{

							loc = newloc;

							if (this.temptedEntity.posX < foodPos.getX())
							{
								BlockPos foodPoschk = new BlockPos(x + i + 1, y + j, z + k);
								Block foodBlockchk = this.temptedEntity.world.getBlockState(foodPoschk).getBlock();
								if (foodBlockchk == Blocks.CARROTS || foodBlockchk == Blocks.BEETROOTS || foodBlockchk == Blocks.POTATOES)
									i = i + 1;
							}

							if (this.temptedEntity.posZ < foodPos.getZ())
							{
								BlockPos foodPoschk = new BlockPos(x + i, y + j, z + k + 1);
								Block foodBlockchk = this.temptedEntity.world.getBlockState(foodPoschk).getBlock();
								if (foodBlockchk == Blocks.CARROTS || foodBlockchk == Blocks.BEETROOTS || foodBlockchk == Blocks.POTATOES)
									k = k + 1;
							}

							foodPos = new BlockPos(x + i, y + j, z + k);

						}
					}
				}

		if (foodFound)
		{

			Block foodBlockchk = this.temptedEntity.world.getBlockState(foodPos).getBlock();

			if (foodBlockchk == BlockHandler.blockNest)
			{
				TileEntityNest te = (TileEntityNest) this.temptedEntity.world.getTileEntity(foodPos);

				if (te != null && (te.getNestContent() == NestContent.CHICKEN_BROWN || te.getNestContent() == NestContent.CHICKEN_WHITE) )
					if (this.temptedEntity.getNavigator().tryMoveToXYZ(foodPos.getX() + .7, foodPos.getY(), foodPos.getZ(), this.speed) == false)
						this.delayTemptCounter = 0;
					else
						this.temptedEntity.getNavigator().tryMoveToXYZ(foodPos.getX() + .7, foodPos.getY(), foodPos.getZ(), this.speed);

			}
			else if (foodBlockchk == Blocks.CARROTS || foodBlockchk == Blocks.BEETROOTS || foodBlockchk == Blocks.POTATOES)
				if (this.temptedEntity.getNavigator().tryMoveToXYZ(foodPos.getX(), foodPos.getY(), foodPos.getZ(), this.speed) == false)
					this.delayTemptCounter = 0;
				else
					this.temptedEntity.getNavigator().tryMoveToXYZ(foodPos.getX(), foodPos.getY(), foodPos.getZ(), this.speed);
		}

	}

	public boolean isRunning()
	{
		return this.isRunning;
	}
}