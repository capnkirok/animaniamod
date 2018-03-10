package com.animania.common.entities.genericAi;

import com.animania.common.blocks.BlockSaltLick;
import com.animania.common.handler.BlockHandler;
import com.animania.common.tileentities.TileEntitySaltLick;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityAIFindSaltLick extends EntityAIBase
{
	private final EntityCreature temptedEntity;
	private final double speed;
	private double targetX;
	private double targetY;
	private double targetZ;
	private double pitch;
	private double yaw;
	private boolean isRunning;
	private int delayTemptCounter;
	private World world;

	public EntityAIFindSaltLick(EntityCreature temptedEntityIn, double speedIn)
	{
		this.temptedEntity = temptedEntityIn;
		this.speed = speedIn;
		this.setMutexBits(3);
		this.delayTemptCounter = 0;
		this.world = temptedEntity.world;
	}

	public boolean shouldExecute()
	{

		delayTemptCounter++;
		
		if (delayTemptCounter >= 40)
		{
			BlockPos pos = new BlockPos(temptedEntity.posX, temptedEntity.posY, temptedEntity.posZ);
			Block block = temptedEntity.world.getBlockState(pos).getBlock();

			if (block == BlockHandler.blockSaltLick)
			{
				
				((BlockSaltLick)BlockHandler.blockSaltLick).useSaltLick(world, pos, temptedEntity);
				
				return true;
			}

		}

		return false;
	}

	public boolean shouldContinueExecuting()
	{
		return this.shouldExecute();
	}

	public void startExecuting()
	{
		this.isRunning = true;
	}

	public void resetTask()
	{
		this.temptedEntity.getNavigator().clearPath();
		this.isRunning = false;
	}

	public void updateTask()
	{
		double x = this.temptedEntity.posX;
		double y = this.temptedEntity.posY;
		double z = this.temptedEntity.posZ;

		BlockPos currentpos = new BlockPos(x, y, z);
		Block block = world.getBlockState(currentpos).getBlock();
		if (block != BlockHandler.blockSaltLick)
		{
			boolean found = false;
			int loc = 24;
			int newloc = 24;
			BlockPos pos = new BlockPos(x, y, z);
			BlockPos saltPos = new BlockPos(x, y, z);

			for (int i = -10; i < 10; i++)
			{
				for (int j = -3; j < 3; j++)
				{
					for (int k = -10; k < 10; k++)
					{

						pos = new BlockPos(x + i, y + j, z + k);
						Block blockchk = temptedEntity.world.getBlockState(pos).getBlock();

						if (blockchk == BlockHandler.blockSaltLick && !temptedEntity.hasPath())
						{
							
							TileEntitySaltLick te = (TileEntitySaltLick) temptedEntity.world.getTileEntity(pos);
							found = true;

							if (found == true)
							{

								newloc = Math.abs(i) + Math.abs(j) + Math.abs(k);

								if (newloc < loc)
								{

									loc = newloc;

									if (temptedEntity.posX < saltPos.getX())
									{
										BlockPos saltPoschk = new BlockPos(x + i + 1, y + j, z + k);
										Block saltBlockchk = temptedEntity.world.getBlockState(saltPoschk).getBlock();
										if (saltBlockchk == BlockHandler.blockSaltLick)
										{
											i = i + 1;
										}
									}

									if (temptedEntity.posZ < saltPos.getZ())
									{
										BlockPos saltPoschk = new BlockPos(x + i, y + j, z + k + 1);
										Block saltBlockchk = temptedEntity.world.getBlockState(saltPoschk).getBlock();
										if (saltBlockchk == BlockHandler.blockSaltLick)
										{
											k = k + 1;
										}
									}

									saltPos = new BlockPos(x + i, y + j, z + k);

								}

							}

						}
					}

				}

			}

			if (found)
			{

				Block saltBlockchk = temptedEntity.world.getBlockState(saltPos).getBlock();

				if (saltBlockchk == BlockHandler.blockSaltLick)
				{
					this.temptedEntity.getNavigator().tryMoveToXYZ(saltPos.getX() + .50, saltPos.getY(), saltPos.getZ() + .50, this.speed);
				}
				else
				{
					

				}

			}
		}

	}

	public boolean isRunning()
	{
		return this.isRunning;
	}
}