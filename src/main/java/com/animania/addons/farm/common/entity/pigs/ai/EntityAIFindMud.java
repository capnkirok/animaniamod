package com.animania.addons.farm.common.entity.pigs.ai;

import java.util.List;

import com.animania.Animania;
import com.animania.addons.farm.common.entity.pigs.EntityAnimaniaPig;
import com.animania.addons.farm.common.entity.pigs.EntityHogBase;
import com.animania.addons.farm.common.entity.pigs.EntitySowBase;
import com.animania.addons.farm.common.entity.pigs.PigEntityletBase;
import com.animania.common.handler.BlockHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.config.AnimaniaConfig;

import net.minecraft.core.BlockPos;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;

public class FindMudGoal extends Goal
{
	private final EntityAnimaniaPig entityIn;
	private final double speed;
	private double targetX;
	private double targetY;
	private double targetZ;
	private double pitch;
	private double yaw;
	private Player temptingPlayer;
	private int delayTemptCounter;
	private boolean isRunning;

	public FindMudGoal(EntityAnimaniaPig temptedEntityIn, double speedIn)
	{
		this.entityIn = temptedEntityIn;
		this.speed = speedIn;
		this.setMutexBits(3);
		this.delayTemptCounter = 0;
	}

	@Override
	public boolean shouldExecute()
	{

		this.delayTemptCounter++;
		if (this.delayTemptCounter < AnimaniaConfig.gameRules.ticksBetweenAIFirings)
		{
		}
		else if (this.delayTemptCounter > AnimaniaConfig.gameRules.ticksBetweenAIFirings)
		{

			if (this.entityIn.getPlayed() || !this.entityIn.level.isDay() || this.entityIn.getSleeping())
			{
				this.delayTemptCounter = 0;
				return false;
			}

			if (this.entityIn.getRandom().nextInt(100) == 0)
			{
				Vec3d vec3d = RandomPositionGenerator.findRandomTarget(this.entityIn, 20, 4);
				if (vec3d != null)
				{
					this.delayTemptCounter = 0;
					this.resetTask();
					this.entityIn.getNavigation().tryMoveToXYZ(vec3d.x, vec3d.y, vec3d.z, this.speed);
				}
				return false;
			}

			BlockPos currentpos = new BlockPos(this.entityIn.getX(), this.entityIn.getY(), this.entityIn.getZ());
			Block poschk = this.entityIn.level.getBlockState(currentpos).getBlock();
			if (poschk == BlockHandler.blockMud || poschk.getUnlocalizedName().equals("tile.mud"))
			{
				EntityAnimaniaPig pig = this.entityIn;
				pig.setPlayed(true);
				this.delayTemptCounter = 0;
				return false;
			}

			double x = this.entityIn.getX();
			double y = this.entityIn.getY();
			double z = this.entityIn.getZ();

			boolean mudFound = false;

			BlockPos pos = new BlockPos(x, y, z);

			for (int i = -10; i < 10; i++)
				for (int j = -2; j < 2; j++)
					for (int k = -10; k < 10; k++)
					{

						pos = new BlockPos(x + i, y + j, z + k);
						Block blockchk = this.entityIn.level.getBlockState(pos).getBlock();

						List<EntityAnimaniaPig> others = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaPig.class, 2, this.entityIn.level, pos);

						if (blockchk != null && (blockchk == BlockHandler.blockMud || blockchk.getUnlocalizedName().equals("tile.mud")) && others.size() < 2)
						{
							mudFound = true;
							if (Animania.RANDOM.nextInt(200) == 0 || this.entityIn.collidedHorizontally && this.entityIn.motionX == 0 && this.entityIn.motionZ == 0)
							{
								this.delayTemptCounter = 0;
								return false;
							}
							else
								return true;
						}

					}

			if (!mudFound)
			{
				this.delayTemptCounter = 0;
			}
		}

		return false;
	}

	public boolean shouldContinueExecuting()
	{
		return !this.entityIn.getNavigation().noPath();
	}

	@Override
	public void resetTask()
	{
		this.temptingPlayer = null;
		this.entityIn.getNavigation().stop();
		this.isRunning = false;
	}

	@Override
	public void startExecuting()
	{

		double x = this.entityIn.getX();
		double y = this.entityIn.getY();
		double z = this.entityIn.getZ();

		BlockPos currentpos = new BlockPos(x, y, z);
		Block poschk = this.entityIn.level.getBlockState(currentpos).getBlock();
		if (poschk != BlockHandler.blockMud || !poschk.getUnlocalizedName().equals("tile.mud"))
		{

			boolean mudFound = false;
			boolean spcFlag = false;
			int loc = 24;
			int newloc = 24;
			BlockPos pos = new BlockPos(x, y, z);
			BlockPos mudPos = new BlockPos(x, y, z);

			for (int i = -10; i < 10; i++)
				for (int j = -2; j < 2; j++)
					for (int k = -10; k < 10; k++)
					{

						pos = new BlockPos(x + i, y + j, z + k);
						Block blockchk = this.entityIn.level.getBlockState(pos).getBlock();
						if (blockchk == BlockHandler.blockMud || blockchk.getUnlocalizedName().equals("tile.mud"))
						{
							mudFound = true;
							newloc = Math.abs(i) + Math.abs(j) + Math.abs(k);

							if (newloc < loc)
							{

								loc = newloc;

								if (this.entityIn.getX() > mudPos.getX())
								{
									BlockPos mudPoschk = new BlockPos(x + i + 1, y + j, z + k);
									Block mudBlockchk = this.entityIn.level.getBlockState(mudPoschk).getBlock();
									if (mudBlockchk == BlockHandler.blockMud || mudBlockchk.getUnlocalizedName().equals("tile.mud"))
									{
										spcFlag = true;
										i = i + 1;
									}
								}

								if (this.entityIn.getZ() > mudPos.getZ())
								{
									BlockPos mudPoschk = new BlockPos(x + i, y + j, z + k + 1);
									Block mudBlockchk = this.entityIn.level.getBlockState(mudPoschk).getBlock();
									if (mudBlockchk == BlockHandler.blockMud || mudBlockchk.getUnlocalizedName().equals("tile.mud"))
									{
										spcFlag = true;
										k = k + 1;
									}
								}

								mudPos = new BlockPos(x + i, y + j, z + k);

								if (spcFlag)
								{
									i = 10;
									j = 3;
									k = 10;
								}

							}

						}

					}

			if (mudFound)
			{

				Block mudBlockchk = this.entityIn.level.getBlockState(mudPos).getBlock();
				if (mudBlockchk == BlockHandler.blockMud || mudBlockchk.getUnlocalizedName().equals("tile.mud"))
					if (this.entityIn instanceof EntitySowBase te)
					{
						if (!te.getMuddy())
						{
							if (te.getX() - mudPos.getX() < 0 && te.getZ() - mudPos.getZ() < 0)
								this.entityIn.getNavigation().tryMoveToXYZ(mudPos.getX() + 2, mudPos.getY(), mudPos.getZ() + 2, this.speed);
							else
								this.entityIn.getNavigation().tryMoveToXYZ(mudPos.getX(), mudPos.getY(), mudPos.getZ(), this.speed);

							this.entityIn.getNavigation().tryMoveToXYZ(mudPos.getX(), mudPos.getY(), mudPos.getZ(), this.speed);

						}
					}
					else if (this.entityIn instanceof PigEntityletBase te)
					{
						if (!te.getMuddy())
							if (te.getX() - mudPos.getX() < 0 && te.getZ() - mudPos.getZ() < 0)
								this.entityIn.getNavigation().tryMoveToXYZ(mudPos.getX() + 2, mudPos.getY(), mudPos.getZ() + 2, this.speed);
							else
								this.entityIn.getNavigation().tryMoveToXYZ(mudPos.getX() - 1, mudPos.getY(), mudPos.getZ() - 1, this.speed);
					}
					else if ((this.entityIn instanceof EntityHogBase te) && !te.getMuddy())
						if (te.getX() - mudPos.getX() < 0 && te.getZ() - mudPos.getZ() < 0)
							this.entityIn.getNavigation().tryMoveToXYZ(mudPos.getX() + 3, mudPos.getY(), mudPos.getZ() + 3, this.speed);
						else if (te.getX() - mudPos.getX() > 0 && te.getZ() - mudPos.getZ() > 0)
							this.entityIn.getNavigation().tryMoveToXYZ(mudPos.getX() - 1, mudPos.getY(), mudPos.getZ() - 1, this.speed);
						else
							this.entityIn.getNavigation().tryMoveToXYZ(mudPos.getX(), mudPos.getY(), mudPos.getZ(), this.speed);
			}
		}
	}

	public boolean isRunning()
	{
		return this.isRunning;
	}
}