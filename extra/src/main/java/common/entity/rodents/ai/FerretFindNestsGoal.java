package common.entity.rodents.ai;

import com.animania.Animania;
import com.animania.common.blockentities.BlockEntityNest;
import com.animania.common.blockentities.BlockEntityNest.NestContent;
import com.animania.common.handler.BlockHandler;
import com.animania.config.AnimaniaConfig;
import common.entity.rodents.EntityFerretBase;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;

public class FerretFindNestsGoal extends Goal
{
	private final PathfinderMob temptedEntity;
	private final double speed;
	private double targetX;
	private double targetY;
	private double targetZ;
	private double pitch;
	private double yaw;
	private Player temptingPlayer;
	private boolean isRunning;
	private int delayTemptCounter;

	public FerretFindNestsGoal(PathfinderMob temptedEntityIn, double speedIn)
	{
		this.temptedEntity = temptedEntityIn;
		this.speed = speedIn;
		this.setMutexBits(3);
		this.delayTemptCounter = 0;
	}

	public boolean shouldExecute()
	{
		this.delayTemptCounter++;

		if (this.delayTemptCounter < AnimaniaConfig.gameRules.ticksBetweenAIFirings)
		{
		}
		else if (this.delayTemptCounter >= AnimaniaConfig.gameRules.ticksBetweenAIFirings)
		{
			if (this.temptedEntity instanceof EntityFerretBase entity && (entity.getSleeping() || entity.getFed()))
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
					this.temptedEntity.getNavigation().moveTo(vec3d.x, vec3d.y, vec3d.z, this.speed);
				}
				return false;
			}

			BlockPos currentpos = new BlockPos(this.temptedEntity.getX(), this.temptedEntity.getY(), this.temptedEntity.getZ());
			Block poschk = temptedEntity.level.getBlockState(currentpos).getBlock();

			if (poschk == BlockHandler.blockNest)
			{
				BlockEntityNest te = (BlockEntityNest) temptedEntity.level.getBlockEntity(currentpos);

				if (te == null || te.getNestContent() == NestContent.EMPTY)
				{
					this.delayTemptCounter = 0;
					return false;
				}

				if ((te.getNestContent() == NestContent.CHICKEN_BROWN || te.getNestContent() == NestContent.CHICKEN_WHITE) && te.itemHandler.getStackInSlot(0).getCount() > 0)
				{
					te.itemHandler.extractItem(0, 1, false);
					te.markDirty();

					if (this.temptedEntity instanceof EntityFerretBase ech)
					{
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
						ech.setWatered(true);
					}
					this.delayTemptCounter = 0;
					return false;

				}
			}

			double x = this.temptedEntity.getX();
			double y = this.temptedEntity.getY();
			double z = this.temptedEntity.getZ();

			boolean foodFound = false;

			BlockPos pos = new BlockPos(x, y, z);

			for (int i = -16; i < 16; i++)
			{
				for (int j = -3; j < 3; j++)
				{
					for (int k = -16; k < 16; k++)
					{

						pos = new BlockPos(x + i, y + j, z + k);

						Block blockchk = temptedEntity.level.getBlockState(pos).getBlock();

						if (blockchk == BlockHandler.blockNest)
						{
							BlockEntityNest te = (BlockEntityNest) temptedEntity.level.getBlockEntity(pos);

							if (te != null && (te.getNestContent() == NestContent.CHICKEN_BROWN || te.getNestContent() == NestContent.CHICKEN_WHITE))
							{
								foodFound = true;
								if (Animania.RANDOM.nextInt(200) == 0 || this.temptedEntity.horizontalCollision && this.temptedEntity.getDeltaMovement().x == 0 && this.temptedEntity.getDeltaMovement().z == 0)
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
			}
		}
		return false;

	}

	public boolean shouldContinueExecuting()
	{
		return !this.temptedEntity.getNavigation().isDone();
	}

	public void resetTask()
	{
		this.temptingPlayer = null;
		this.temptedEntity.getNavigation().stop();
		this.isRunning = false;
	}

	public void startExecuting()
	{
		double x = this.temptedEntity.getX();
		double y = this.temptedEntity.getY();
		double z = this.temptedEntity.getZ();

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
					Block blockchk = temptedEntity.level.getBlockState(pos).getBlock();
					if (blockchk == BlockHandler.blockNest)
					{
						BlockEntityNest be = (BlockEntityNest) temptedEntity.level.getBlockEntity(pos);

						if (be != null && (be.getNestContent() == NestContent.CHICKEN_BROWN || be.getNestContent() == NestContent.CHICKEN_WHITE))
						{

							foodFound = true;
							newloc = Math.abs(i) + Math.abs(j) + Math.abs(k);

							if (newloc < loc)
							{

								loc = newloc;

								if (this.temptedEntity.getX() < foodPos.getX())
								{
									BlockPos foodPoschk = new BlockPos(x + i + 1, y + j, z + k);
									Block foodBlockchk = temptedEntity.level.getBlockState(foodPoschk).getBlock();
									i = i + 1;
								}

								if (this.temptedEntity.getZ() < foodPos.getZ())
								{
									BlockPos foodPoschk = new BlockPos(x + i, y + j, z + k + 1);
									Block foodBlockchk = temptedEntity.level.getBlockState(foodPoschk).getBlock();
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

			Block foodBlockchk = temptedEntity.level.getBlockState(foodPos).getBlock();

			if (foodBlockchk == BlockHandler.blockNest)
			{
				BlockEntityNest te = (BlockEntityNest) temptedEntity.level.getBlockEntity(foodPos);

				if (te != null && (te.getNestContent() == NestContent.CHICKEN_BROWN || te.getNestContent() == NestContent.CHICKEN_WHITE))
				{
					if (!this.temptedEntity.getNavigation().moveTo(foodPos.getX() + .7, foodPos.getY(), foodPos.getZ(), this.speed))
					{
						this.delayTemptCounter = 0;
					}
					else
					{
						this.temptedEntity.getNavigation().moveTo(foodPos.getX() + .7, foodPos.getY(), foodPos.getZ(), this.speed);
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