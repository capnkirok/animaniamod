package common.entity.pigs.ai;

import com.animania.config.AnimaniaConfig;
import common.entity.pigs.EntityAnimaniaPig;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class TemptItemStackGoal extends Goal
{
	private final EntityAnimaniaPig temptedEntity;
	private final double speed;
	private double pitch;
	private double yaw;
	private Player temptingPlayer;
	private int delayTemptCounter;
	private boolean isRunning;
	private final ItemStack temptItem;

	public TemptItemStackGoal(EntityAnimaniaPig temptedEntityIn, double speedIn, ItemStack temptItemIn)
	{
		this.temptedEntity = temptedEntityIn;
		this.speed = speedIn;
		this.temptItem = temptItemIn;
		this.setMutexBits(3);

		if (!(temptedEntityIn.getNavigation() instanceof PathNavigateGround))
			throw new IllegalArgumentException("Unsupported mob type for TemptGoal");
	}

	@Override
	public boolean shouldExecute()
	{
		if (this.delayTemptCounter > 0)
		{
			--this.delayTemptCounter;
			return false;
		}
		else if (this.temptedEntity.getSleeping())
		{
			this.delayTemptCounter = AnimaniaConfig.gameRules.ticksBetweenAIFirings;
			return false;
		}
		else
		{

			this.temptingPlayer = this.temptedentity.level.getClosestPlayerToEntity(this.temptedEntity, 10.0D);
			return this.temptingPlayer == null ? false : this.isTempting(this.temptingPlayer.getMainHandItem()) || this.isTempting(this.temptingPlayer.getItemInHandOffhand());
		}
	}

	protected boolean isTempting(ItemStack stack)
	{
		return ItemStack.areItemStacksEqual(stack, this.temptItem);
	}

	@Override
	public boolean shouldContinueExecuting()
	{
		return this.shouldExecute();
	}

	@Override
	public void startExecuting()
	{
		double targetX = this.temptingPlayer.getX();
		double targetY = this.temptingPlayer.getY();
		double targetZ = this.temptingPlayer.getZ();
		this.isRunning = true;
	}

	@Override
	public void resetTask()
	{
		this.temptingPlayer = null;
		this.temptedEntity.getNavigation().stop();
		this.delayTemptCounter = AnimaniaConfig.gameRules.ticksBetweenAIFirings;
		this.isRunning = false;
	}

	@Override
	public void updateTask()
	{
		if (this.temptedEntity != null && this.temptingPlayer != null)
			this.temptedEntity.getLookHelper().setLookPositionWithEntity(this.temptingPlayer, this.temptedEntity.getHorizontalFaceSpeed() + 20, this.temptedEntity.getVerticalFaceSpeed());

		if (this.temptedEntity.getDistanceSq(this.temptingPlayer) < 6.25D)
			this.temptedEntity.getNavigation().stop();
		else
			this.temptedEntity.getNavigation().tryMoveToLivingEntity(this.temptingPlayer, this.speed);
	}

	public boolean isRunning()
	{
		return this.isRunning;
	}

}
