package com.animania.addons.extra.common.entity.peafowl.ai;

import java.util.List;

import com.animania.addons.extra.common.entity.peafowl.EntityAnimaniaPeacock;
import com.animania.addons.extra.common.entity.peafowl.EntityPeafowlBase;
import com.animania.addons.extra.common.entity.peafowl.PeafowlBlue.EntityPeafowlBlue;
import com.animania.addons.extra.common.handler.ExtraAddonItemHandler;
import com.animania.common.handler.BlockHandler;
import com.animania.common.blockentities.BlockEntityNest;
import com.animania.common.blockentities.BlockEntityNest.NestContent;
import com.animania.config.AnimaniaConfig;

import net.minecraft.core.BlockPos;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class FindPeacockNestGoal extends Goal
{
	private final EntityAnimaniaPeacock temptedEntity;
	private final double speed;
	private double targetX;
	private double targetY;
	private double targetZ;
	private double pitch;
	private double yaw;
	private Player temptingPlayer;
	private boolean isRunning;
	private int delayTemptCounter;

	public FindPeacockNestGoal(EntityAnimaniaPeacock temptedEntityIn, double speedIn)
	{
		this.temptedEntity = temptedEntityIn;
		this.speed = speedIn;
		this.setMutexBits(3);
		this.delayTemptCounter = 0;
	}

	@Override
	public boolean shouldExecute()
	{

		this.delayTemptCounter++;
		if (this.delayTemptCounter <= AnimaniaConfig.gameRules.ticksBetweenAIFirings)
		{
		}
		else if (this.delayTemptCounter > AnimaniaConfig.gameRules.ticksBetweenAIFirings)
		{

			if (!temptedentity.level.isDay() || this.temptedEntity.getSleeping())
			{
				this.delayTemptCounter = 0;
				return false;
			}

			if (this.temptedEntity instanceof EntityPeafowlBase entity && (!entity.getWatered() || !entity.getFed()))
			{
				this.delayTemptCounter = 0;
				return false;
			}

			if (this.temptedEntity.getRandom().nextInt(100) == 0)
			{
				Vec3d vec3d = RandomPositionGenerator.findRandomTarget(this.temptedEntity, 20, 4);
				if (vec3d != null)
				{
					this.delayTemptCounter = 0;
					this.resetTask();
					this.temptedEntity.getNavigation().tryMoveToXYZ(vec3d.x, vec3d.y, vec3d.z, this.speed);
				}
				return false;
			}

			BlockPos currentpos = new BlockPos(this.temptedEntity.getX(), this.temptedEntity.getY(), this.temptedEntity.getZ());
			Block poschk = temptedentity.level.getBlockState(currentpos).getBlock();

			if (poschk == BlockHandler.blockNest)
			{
				BlockEntityNest te = (BlockEntityNest) temptedentity.level.getBlockEntity(currentpos);

				if (te.itemHandler.getStackInSlot(0).getCount() >= 3)
				{
					return false;
				}

				if (this.temptedEntity instanceof EntityPeafowlBlue entity)
				{
					if (te != null && (te.getNestContent() == NestContent.EMPTY || te.getNestContent() == NestContent.PEACOCK_BLUE) && !entity.getLaid())
					{
						if ((te.getNestContent() == NestContent.PEACOCK_BLUE ? entity.type == te.birdType : true) && te.insertItem(new ItemStack(ExtraAddonItemHandler.peacockEggBlue)))
						{
							entity.setLaid(true);
							te.birdType = entity.type;
							this.delayTemptCounter = 0;
							te.markDirty();
						}
					}
				}
				else if (this.temptedEntity instanceof EntityPeafowlBase entity)
				{
					if (te != null && (te.getNestContent() == NestContent.EMPTY || te.getNestContent() == NestContent.PEACOCK_WHITE) && !entity.getLaid())
					{
						if ((te.getNestContent() == NestContent.PEACOCK_WHITE ? entity.type == te.birdType : true) && te.insertItem(new ItemStack(ExtraAddonItemHandler.peacockEggWhite)))
						{
							entity.setLaid(true);
							te.birdType = entity.type;
							this.delayTemptCounter = 0;
							te.markDirty();
						}
					}
				}
				this.delayTemptCounter = 0;
				return false;
			}

			double x = this.temptedEntity.getX();
			double y = this.temptedEntity.getY();
			double z = this.temptedEntity.getZ();

			boolean nestFound = false;

			BlockPos pos = new BlockPos(x, y, z);

			for (int i = -10; i < 10; i++)
			{
				for (int j = -3; j < 3; j++)
				{
					for (int k = -10; k < 10; k++)
					{

						pos = new BlockPos(x + i, y + j, z + k);
						Block blockchk = temptedentity.level.getBlockState(pos).getBlock();

						if (blockchk == BlockHandler.blockNest)
						{

							BlockEntityNest te = (BlockEntityNest) temptedentity.level.getBlockEntity(pos);
							NestContent nestType = te.getNestContent();

							if (nestType == NestContent.PEACOCK_BLUE || nestType == NestContent.PEACOCK_WHITE || nestType == NestContent.EMPTY)
							{
								if (this.temptedEntity instanceof EntityPeafowlBlue && (nestType == NestContent.PEACOCK_BLUE || nestType == NestContent.EMPTY))
								{
									nestFound = true;
									return true;
								}
								else if (this.temptedEntity instanceof EntityPeafowlBase && (nestType == NestContent.PEACOCK_WHITE ? ((EntityPeafowlBase) this.temptedEntity).type == te.birdType : nestType == NestContent.EMPTY))
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
				this.delayTemptCounter = 0;
			}
		}

		return false;
	}

	@Override
	public boolean shouldContinueExecuting()
	{
		return !this.temptedEntity.getNavigation().noPath();
	}

	@Override
	public void resetTask()
	{
		this.temptingPlayer = null;
		this.temptedEntity.getNavigation().stop();
		this.isRunning = false;
	}

	@Override
	public void startExecuting()
	{

		double x = this.temptedEntity.getX();
		double y = this.temptedEntity.getY();
		double z = this.temptedEntity.getZ();

		BlockPos currentpos = new BlockPos(x, y, z);
		Block poschk = temptedentity.level.getBlockState(currentpos).getBlock();
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
						Block blockchk = temptedentity.level.getBlockState(pos).getBlock();

						if (blockchk == BlockHandler.blockNest && !this.temptedEntity.hasPath())
						{

							BlockEntityNest te = (BlockEntityNest) temptedentity.level.getBlockEntity(pos);
							NestContent nestType = te.getNestContent();

							if (nestType == NestContent.PEACOCK_BLUE || nestType == NestContent.PEACOCK_WHITE || nestType == NestContent.EMPTY)
							{
								if (this.temptedEntity instanceof EntityPeafowlBlue && (nestType == NestContent.PEACOCK_BLUE || nestType == NestContent.EMPTY))
								{
									nestFound = true;
								}
								else if (this.temptedEntity instanceof EntityPeafowlBase && (nestType == NestContent.PEACOCK_WHITE ? ((EntityPeafowlBase) this.temptedEntity).type == te.birdType : nestType == NestContent.EMPTY))
								{
									nestFound = true;
								}
							}

							if (nestFound)
							{

								newloc = Math.abs(i) + Math.abs(j) + Math.abs(k);

								if (newloc < loc)
								{

									loc = newloc;

									if (this.temptedEntity.getX() < nestPos.getX())
									{
										BlockPos nestPoschk = new BlockPos(x + i + 1, y + j, z + k);
										Block nestBlockchk = temptedentity.level.getBlockState(nestPoschk).getBlock();
										if (nestBlockchk == BlockHandler.blockNest)
										{
											i = i + 1;
										}
									}

									if (this.temptedEntity.getZ() < nestPos.getZ())
									{
										BlockPos nestPoschk = new BlockPos(x + i, y + j, z + k + 1);
										Block nestBlockchk = temptedentity.level.getBlockState(nestPoschk).getBlock();
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

				Block nestBlockchk = temptedentity.level.getBlockState(nestPos).getBlock();
				List<Entity> nestClear = temptedentity.level.getEntitiesWithinAABBExcludingEntity(this.temptedEntity, this.temptedEntity.getEntityBoundingBox().expand(1, 1, 1));

				if (nestBlockchk == BlockHandler.blockNest && nestClear.isEmpty())
				{
					this.temptedEntity.getNavigation().tryMoveToXYZ(nestPos.getX() + .50, nestPos.getY(), nestPos.getZ() + .50, this.speed);
				}
				else
				{
					// this.temptedEntity.getNavigation().tryMoveToXYZ(nestPos.getX(),
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