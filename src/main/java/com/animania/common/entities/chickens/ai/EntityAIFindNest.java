package com.animania.common.entities.chickens.ai;

import java.util.List;

import com.animania.common.entities.chickens.EntityHenBase;
import com.animania.common.entities.chickens.EntityHenLeghorn;
import com.animania.common.entities.chickens.EntityHenOrpington;
import com.animania.common.entities.chickens.EntityHenPlymouthRock;
import com.animania.common.entities.chickens.EntityHenRhodeIslandRed;
import com.animania.common.entities.chickens.EntityHenWyandotte;
import com.animania.common.handler.BlockHandler;
import com.animania.common.handler.ItemHandler;
import com.animania.common.tileentities.TileEntityNest;
import com.animania.common.tileentities.TileEntityNest.NestContent;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

public class EntityAIFindNest extends EntityAIBase
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

	public EntityAIFindNest(EntityCreature temptedEntityIn, double speedIn)
	{
		this.temptedEntity = temptedEntityIn;
		this.speed = speedIn;
		this.setMutexBits(3);
		this.delayTemptCounter = 0;
	}

	public boolean shouldExecute()
	{

		delayTemptCounter++;
		if (this.delayTemptCounter < 40)
		{
			return false;
		}
		else if (delayTemptCounter > 40)
		{

			if (!this.temptedEntity.world.isDaytime())
			{
				this.delayTemptCounter = 0;
				return false;
			}

			if (temptedEntity instanceof EntityHenBase)
			{
				EntityHenBase entity = (EntityHenBase) temptedEntity;
				if (!entity.getWatered() || !entity.getFed())
				{
					this.delayTemptCounter = 0;
					return false;
				}
			}

			BlockPos currentpos = new BlockPos(temptedEntity.posX, temptedEntity.posY, temptedEntity.posZ);
			Block poschk = temptedEntity.world.getBlockState(currentpos).getBlock();

			if (poschk == BlockHandler.blockNest)
			{
				TileEntityNest te = (TileEntityNest) temptedEntity.world.getTileEntity(currentpos);

				if (te.itemHandler.getStackInSlot(0).getCount() >= 3)
				{
					return false;
				}

				if (temptedEntity instanceof EntityHenLeghorn || temptedEntity instanceof EntityHenOrpington || temptedEntity instanceof EntityHenPlymouthRock)
				{
					EntityHenBase entity = (EntityHenBase) temptedEntity;
					if (te != null && (te.getNestContent() == NestContent.EMPTY || te.getNestContent() == NestContent.CHICKEN_WHITE) && !entity.getLaid())
					{
						if (te.insertItem(new ItemStack(Items.EGG)))
						{
							entity.setLaid(true);
							te.birdType = entity.type;
							this.resetTask();
							te.markDirty();
						}
					}
				}
				else if (temptedEntity instanceof EntityHenRhodeIslandRed || temptedEntity instanceof EntityHenWyandotte)
				{
					EntityHenBase hen = (EntityHenBase) temptedEntity;
					if (te != null && (te.getNestContent() == NestContent.EMPTY || te.getNestContent() == NestContent.CHICKEN_BROWN) && !hen.getLaid())
					{
						if (te.insertItem(new ItemStack(ItemHandler.brownEgg)))
						{
							hen.setLaid(true);
							te.birdType = hen.type;
							this.resetTask();
							te.markDirty();
						}
					}
				}

				return false;
			}

			double x = this.temptedEntity.posX;
			double y = this.temptedEntity.posY;
			double z = this.temptedEntity.posZ;

			boolean nestFound = false;

			BlockPos pos = new BlockPos(x, y, z);

			for (int i = -10; i < 10; i++)
			{
				for (int j = -3; j < 3; j++)
				{
					for (int k = -10; k < 10; k++)
					{

						pos = new BlockPos(x + i, y + j, z + k);
						Block blockchk = temptedEntity.world.getBlockState(pos).getBlock();

						if (blockchk == BlockHandler.blockNest)
						{

							TileEntityNest te = (TileEntityNest) temptedEntity.world.getTileEntity(pos);
							NestContent nestType = te.getNestContent();

							if(nestType == NestContent.CHICKEN_BROWN || nestType == NestContent.CHICKEN_WHITE || nestType == NestContent.EMPTY)
							{
								if (temptedEntity instanceof EntityHenLeghorn && (nestType == NestContent.CHICKEN_WHITE || nestType == NestContent.EMPTY))
								{
									nestFound = true;
									return true;
								}
								else if (temptedEntity instanceof EntityHenOrpington && (nestType == NestContent.CHICKEN_WHITE || nestType == NestContent.EMPTY))
								{
									nestFound = true;
									return true;
								}
								else if (temptedEntity instanceof EntityHenPlymouthRock && (nestType == NestContent.CHICKEN_WHITE || nestType == NestContent.EMPTY))
								{
									nestFound = true;
									return true;
								}
								else if (temptedEntity instanceof EntityHenRhodeIslandRed && (nestType == NestContent.CHICKEN_BROWN || nestType == NestContent.EMPTY))
								{
									nestFound = true;
									return true;
								}
								else if (temptedEntity instanceof EntityHenWyandotte && (nestType == NestContent.CHICKEN_BROWN || nestType == NestContent.EMPTY))
								{
									nestFound = true;
									return true;
								}
							}
						}

					}

				}
			}

			if (!nestFound)
			{
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
		if (poschk != BlockHandler.blockNest)
		{

			boolean nestFound = false;
			int loc = 24;
			int newloc = 24;
			BlockPos pos = new BlockPos(x, y, z);
			BlockPos nestPos = new BlockPos(x, y, z);

			for (int i = -10; i < 10; i++)
			{
				for (int j = -3; j < 3; j++)
				{
					for (int k = -10; k < 10; k++)
					{

						pos = new BlockPos(x + i, y + j, z + k);
						Block blockchk = temptedEntity.world.getBlockState(pos).getBlock();

						if (blockchk == BlockHandler.blockNest && !temptedEntity.hasPath())
						{

							TileEntityNest te = (TileEntityNest) temptedEntity.world.getTileEntity(pos);
							NestContent nestType = te.getNestContent();

							if(nestType == NestContent.CHICKEN_BROWN || nestType == NestContent.CHICKEN_WHITE || nestType == NestContent.EMPTY)
							{
								if (temptedEntity instanceof EntityHenLeghorn && (nestType == NestContent.CHICKEN_WHITE || nestType == NestContent.EMPTY))
								{
									nestFound = true;
								}
								else if (temptedEntity instanceof EntityHenOrpington && (nestType == NestContent.CHICKEN_WHITE || nestType == NestContent.EMPTY))
								{
									nestFound = true;
								}
								else if (temptedEntity instanceof EntityHenPlymouthRock && (nestType == NestContent.CHICKEN_WHITE || nestType == NestContent.EMPTY))
								{
									nestFound = true;
								}
								else if (temptedEntity instanceof EntityHenRhodeIslandRed && (nestType == NestContent.CHICKEN_BROWN || nestType == NestContent.EMPTY))
								{
									nestFound = true;
								}
								else if (temptedEntity instanceof EntityHenWyandotte && (nestType == NestContent.CHICKEN_BROWN || nestType == NestContent.EMPTY))
								{
									nestFound = true;
								}
							}

							if (nestFound == true)
							{

								newloc = Math.abs(i) + Math.abs(j) + Math.abs(k);

								if (newloc < loc)
								{

									loc = newloc;

									if (temptedEntity.posX < nestPos.getX())
									{
										BlockPos nestPoschk = new BlockPos(x + i + 1, y + j, z + k);
										Block nestBlockchk = temptedEntity.world.getBlockState(nestPoschk).getBlock();
										if (nestBlockchk == BlockHandler.blockNest)
										{
											i = i + 1;
										}
									}

									if (temptedEntity.posZ < nestPos.getZ())
									{
										BlockPos nestPoschk = new BlockPos(x + i, y + j, z + k + 1);
										Block nestBlockchk = temptedEntity.world.getBlockState(nestPoschk).getBlock();
										if (nestBlockchk == BlockHandler.blockNest)
										{
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

			if (nestFound)
			{

				Block nestBlockchk = temptedEntity.world.getBlockState(nestPos).getBlock();

				List<Entity> nestClear = temptedEntity.world.getEntitiesWithinAABBExcludingEntity(temptedEntity, temptedEntity.getEntityBoundingBox().expandXyz(1));

				if (nestBlockchk == BlockHandler.blockNest && nestClear.isEmpty())
				{
					this.temptedEntity.getNavigator().tryMoveToXYZ(nestPos.getX() + .50, nestPos.getY(), nestPos.getZ() + .50, this.speed);
				}
				else
				{
					// this.temptedEntity.getNavigator().tryMoveToXYZ(nestPos.getX(),
					// nestPos.getY(), nestPos.getZ(), this.speed);

				}

			}
		}

	}

	public boolean isRunning()
	{
		return this.isRunning;
	}
}