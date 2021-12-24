package com.animania.common.entities.generic.ai;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.animania.common.helper.ReflectionUtil;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.pathfinder.PathFinder;

public abstract class GenericAISearchBlock extends Goal
{
	private static final boolean DEBUG_MODE = false;

	protected final PathfinderMob creature;
	protected final double movementSpeed;
	protected BlockPos destinationBlock = NO_POS;
	private boolean isAtDestination;
	protected final int searchRange;
	protected Level level;
	protected List<Direction> destinationOffset;
	protected BlockPos seekingBlockPos = NO_POS;

	protected BlockPos oldBlockPos = NO_POS;
	private boolean hasSecondary;
	private int walkTries = 0;
	private boolean isDone = false;
	private Set<BlockPos> nonValidPositions = new HashSet<BlockPos>();
	private int blacklistTimer = 0;

	public static final BlockPos NO_POS = new BlockPos(-1, -1, -1);

	public GenericAISearchBlock(PathfinderMob creature, double speedIn, int range, boolean hasSecondary, Direction... destinationOffset)
	{
		this.creature = creature;
		this.movementSpeed = speedIn;
		this.searchRange = range;
		this.destinationOffset = new ArrayList<Direction>();
		for (Direction f : destinationOffset)
			this.destinationOffset.add(f);
		this.level = creature.level;
		this.hasSecondary = hasSecondary;
		// this.setMutexBits(5);
	}

	public GenericAISearchBlock(CreatureEntity creature, double speedIn, int range, Direction... destinationOffset)
	{
		this(creature, speedIn, range, false, destinationOffset);
	}

	/**
	 * Returns whether the Goal should begin execution.
	 */
	@Override
	public boolean shouldExecute()
	{
		blacklistTimer++;

		if (blacklistTimer > 10)
		{
			this.nonValidPositions.clear();
			this.blacklistTimer = 0;
			this.seekingBlockPos = NO_POS;
		}

		if (this.seekingBlockPos == NO_POS)
			return this.searchForDestination();

		return false;
	}

	/**
	 * Returns whether an in-progress Goal should continue executing
	 */
	@Override
	public boolean shouldContinueExecuting()
	{
		return destinationBlock != NO_POS && seekingBlockPos != NO_POS && !isDone && (this.shouldMoveTo(this.creature.level, this.seekingBlockPos) || (this.hasSecondary ? this.shouldMoveToSecondary(level, seekingBlockPos) : false));
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	@Override
	public void startExecuting()
	{
		this.creature.getNavigation().tryMoveToXYZ((this.destinationBlock.getX()) + 0.5D, (this.destinationBlock.getY()), (this.destinationBlock.getZ()) + 0.5D, this.movementSpeed);
		this.walkTries = 0;
	}

	@Override
	public void resetTask()
	{
		this.isAtDestination = false;
		this.destinationBlock = NO_POS;
		this.seekingBlockPos = NO_POS;
		this.walkTries = 0;
		this.isDone = false;
	}

	/**
	 * Keep ticking a continuous task that has already been started
	 */
	@Override
	public void updateTask()
	{
		if (!shouldContinueExecuting())
			this.resetTask();

		if (!this.destinationBlock.equals(NO_POS))
		{
			double distance = this.creature.getDistanceSqToCenter(this.destinationBlock);

			if (distance > 2.5D)
			{
				this.isAtDestination = false;
				this.walkTries++;

				boolean isStandingStill = this.creature.prevgetX() == this.creature.getX() && this.creature.prevgetY() == this.creature.getY() && this.creature.prevgetZ() == this.creature.getZ();

				if (isStandingStill && this.walkTries % 40 == 0)
				{
					this.creature.getNavigation().tryMoveToXYZ((this.destinationBlock.getX()) + 0.5D, (this.destinationBlock.getY()), (this.destinationBlock.getZ()) + 0.5D, this.movementSpeed);
					this.creature.getLookHelper().setLookPosition(this.seekingBlockPos.getX() + 0.5D, (this.seekingBlockPos.getY()), this.seekingBlockPos.getZ() + 0.5D, 10.0F, this.creature.getVerticalFaceSpeed());
				}

				if (isStandingStill && this.walkTries > 100)
				{
					this.nonValidPositions.add(seekingBlockPos);

					this.resetTask();
					this.searchForDestination();
				}
			} else
			{
				this.isAtDestination = true;
				this.blacklistTimer = 0;
			}

			if (this.isAtDestination)
			{
				this.nonValidPositions.clear();
				this.blacklistTimer = 0;
				this.isDone = true;
			}
		}
	}

	protected boolean isAtDestination()
	{
		return this.isAtDestination;
	}

	public void onArriveAtDestination()
	{
		this.nonValidPositions.clear();
	}

	@Override
	public boolean isInterruptible()
	{
		return true;
	}

	/**
	 * Searches and sets new destination block and returns true if a suitable
	 * block (specified in
	 * {@link net.minecraft.entity.ai.EntityAIMoveToBlock#shouldMoveTo(Level, BlockPos)
	 * MoveToBlockGoal#shouldMoveTo(Level, BlockPos)}) can be found.
	 */
	protected boolean searchForDestination()
	{
		BlockPos blockpos = new BlockPos(this.creature);

		if (blockpos.equals(oldBlockPos))
			return false;
		oldBlockPos = blockpos;

		BlockPos secondaryDest = null;
		BlockPos secondarySeek = null;
		int ySearchRange = searchRange / 2;
		if (ySearchRange < 1)
			ySearchRange = 1;

		Collections.shuffle(destinationOffset);

		for (int range = 0; range < this.searchRange; ++range)
		{
			for (int y = 0; y <= ySearchRange; y = y > 0 ? -y : 1 - y)
			{
				for (int x = 0; x <= range; x = x > 0 ? -x : 1 - x)
				{
					for (int z = x < range && x > -range ? range : 0; z <= range; z = z > 0 ? -z : 1 - z)
					{
						BlockPos blockpos1 = blockpos.add(x, y - 1, z);

						if (!this.nonValidPositions.contains(blockpos1))
						{
							boolean shouldMoveToPrimary = this.shouldMoveTo(this.creature.level, blockpos1);
							if (shouldMoveToPrimary || (this.hasSecondary && secondarySeek == null && this.shouldMoveToSecondary(this.creature.level, blockpos1)))
							{
								if (destinationOffset.isEmpty())
								{
									if (this.creature.getNavigation().getPathToXYZ(blockpos1.getX() + 0.5, blockpos1.getY(), blockpos1.getZ() + 0.5) != null)
									{
										if (shouldMoveToPrimary)
										{
											this.destinationBlock = blockpos1;
											this.seekingBlockPos = blockpos1;
											return true;
										}
										secondaryDest = blockpos1;
										secondarySeek = blockpos1;
									}
								} else
								{

									for (Direction facing : destinationOffset)
									{
										AxisAlignedBB aabb = level.getBlockState(blockpos1).getCollisionBoundingBox(level, blockpos1);

										BlockPos offsetPos = aabb == Block.NULL_AABB ? blockpos1 : blockpos1.offset(facing);

										if (this.creature.getNavigation().getPathToXYZ(offsetPos.getX() + 0.5, offsetPos.getY(), offsetPos.getZ() + 0.5) != null)
										{
											if (shouldMoveToPrimary)
											{
												this.destinationBlock = offsetPos;
												this.seekingBlockPos = blockpos1;
												return true;
											}
											secondaryDest = offsetPos;
											secondarySeek = blockpos1;
										}
									}
								}
							}
						}
					}
				}
			}
		}

		if (secondarySeek != null)
		{
			this.destinationBlock = secondaryDest;
			this.seekingBlockPos = secondarySeek;
			return true;
		}

		return false;
	}

	protected boolean pathExists(BlockPos start, BlockPos end)
	{
		try
		{
			PathPoint startPoint = new PathPoint(start.getX(), start.getY(), start.getZ());
			PathPoint endPoint = new PathPoint(end.getX(), end.getY(), end.getZ());

			PathNavigate navigate = this.creature.getNavigation();
			Method getPathFinder = ReflectionUtil.findMethod(PathNavigate.class, "getPathFinder", "createPathFinder");
			Method getPath = ReflectionUtil.findMethod(PathFinder.class, "findPath", "func_186336_a", PathPoint.class, PathPoint.class, float.class);

			PathFinder finder = (PathFinder) getPathFinder.invoke(navigate);
			Path p = (Path) getPath.invoke(finder, startPoint, endPoint, (float) searchRange);

			return p != null;
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Return true to set given position as destination
	 */
	protected abstract boolean shouldMoveTo(Level levelIn, BlockPos pos);

	protected boolean shouldMoveToSecondary(Level levelIn, BlockPos pos)
	{
		return false;
	}

}