package com.animania.addons.farm.common.entity.chickens.ai;

import java.util.List;

import com.animania.addons.farm.common.entity.chickens.ChickenLeghorn.EntityHenLeghorn;
import com.animania.addons.farm.common.entity.chickens.ChickenOrpington.EntityHenOrpington;
import com.animania.addons.farm.common.entity.chickens.ChickenPlymouthRock.EntityHenPlymouthRock;
import com.animania.addons.farm.common.entity.chickens.ChickenRhodeIslandRed.EntityHenRhodeIslandRed;
import com.animania.addons.farm.common.entity.chickens.ChickenWyandotte.EntityHenWyandotte;
import com.animania.addons.farm.common.entity.chickens.EntityAnimaniaChicken;
import com.animania.addons.farm.common.entity.chickens.EntityHenBase;
import com.animania.addons.farm.common.handler.FarmAddonItemHandler;
import com.animania.common.handler.BlockHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.tileentities.TileEntityNest;
import com.animania.common.tileentities.TileEntityNest.NestContent;
import com.animania.config.AnimaniaConfig;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class EntityAIFindNest extends EntityAIBase
{
	private final EntityAnimaniaChicken temptedEntity;
	private final double speed;
	private double targetX;
	private double targetY;
	private double targetZ;
	private double pitch;
	private double yaw;
	private PlayerEntity temptingPlayer;
	private boolean isRunning;
	private int delayTemptCounter;

	public EntityAIFindNest(EntityAnimaniaChicken temptedEntityIn, double speedIn)
	{
		this.temptedEntity = temptedEntityIn;
		this.speed = speedIn;
		this.setMutexBits(3);
		this.delayTemptCounter = 0;
	}

	public boolean shouldExecute()
	{
		
		delayTemptCounter++;
		
		if (this.delayTemptCounter < AnimaniaConfig.gameRules.ticksBetweenAIFirings) 
		{
			return false;
		}
		else if (delayTemptCounter > AnimaniaConfig.gameRules.ticksBetweenAIFirings) 
		{
			
			if (!temptedentity.level.isDaytime() || temptedEntity.getSleeping()) {
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

			if (this.temptedEntity.getRNG().nextInt(100) == 0)
			{
				Vec3d vec3d = RandomPositionGenerator.findRandomTarget(this.temptedEntity, 20, 4);
				if (vec3d != null) {
					this.delayTemptCounter = 0;
					this.resetTask();
					this.temptedEntity.getNavigator().tryMoveToXYZ(vec3d.x, vec3d.y, vec3d.z, this.speed);
					this.temptedEntity.getLookHelper().setLookPosition(vec3d.x, vec3d.y, vec3d.z, 0.0F, 0.0F);
				}
				return false;
			}
			
			BlockPos currentpos = new BlockPos(temptedEntity.getX(), temptedEntity.getY(), temptedEntity.getZ());
			Block poschk = temptedentity.level.getBlockState(currentpos).getBlock();

			if (poschk == BlockHandler.blockNest)
			{
				TileEntityNest te = (TileEntityNest) temptedentity.level.getTileEntity(currentpos);

				if (te.itemHandler.getStackInSlot(0).getCount() >= 3)
				{
					this.delayTemptCounter = 0;
					return false;
				}

				if (temptedEntity instanceof EntityHenLeghorn || temptedEntity instanceof EntityHenOrpington || temptedEntity instanceof EntityHenPlymouthRock)
				{
					EntityHenBase entity = (EntityHenBase) temptedEntity;
					if (te != null && (te.getNestContent() == NestContent.EMPTY || te.getNestContent() == NestContent.CHICKEN_WHITE) && !entity.getLaid())
					{
						if (te.getNestContent() == NestContent.CHICKEN_WHITE ? entity.type == te.birdType : true)
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
						if (te.getNestContent() == NestContent.CHICKEN_BROWN ? hen.type == te.birdType : true)
							if (te.insertItem(new ItemStack(FarmAddonItemHandler.brownEgg)))
							{
								hen.setLaid(true);
								te.birdType = hen.type;
								this.resetTask();
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
						
						List<EntityAnimaniaChicken> others = AnimaniaHelper.getEntitiesInRange(EntityHenBase.class, 3, temptedentity.level, pos);

						if (blockchk == BlockHandler.blockNest && others.size() == 0)
						{

							TileEntityNest te = (TileEntityNest) temptedentity.level.getTileEntity(pos);
							NestContent nestType = te.getNestContent();

							if (nestType == NestContent.CHICKEN_BROWN || nestType == NestContent.CHICKEN_WHITE || nestType == NestContent.EMPTY)
							{
								if (temptedEntity instanceof EntityHenLeghorn && (nestType == NestContent.CHICKEN_WHITE ? ((EntityHenBase)temptedEntity).type == te.birdType: nestType == NestContent.EMPTY))
								{
									nestFound = true;
									return true;
								}
								else if (temptedEntity instanceof EntityHenOrpington && (nestType == NestContent.CHICKEN_WHITE ? ((EntityHenBase)temptedEntity).type == te.birdType: nestType == NestContent.EMPTY))
								{
									nestFound = true;
									return true;
								}
								else if (temptedEntity instanceof EntityHenPlymouthRock && (nestType == NestContent.CHICKEN_WHITE ? ((EntityHenBase)temptedEntity).type == te.birdType: nestType == NestContent.EMPTY))
								{
									nestFound = true;
									return true;
								}
								else if (temptedEntity instanceof EntityHenRhodeIslandRed && (nestType == NestContent.CHICKEN_BROWN ? ((EntityHenBase)temptedEntity).type == te.birdType: nestType == NestContent.EMPTY))
								{
									nestFound = true;
									return true;
								}
								else if (temptedEntity instanceof EntityHenWyandotte && (nestType == NestContent.CHICKEN_BROWN ? ((EntityHenBase)temptedEntity).type == te.birdType: nestType == NestContent.EMPTY))
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
				return false;
			}
		}

		
		return false;
	}

	public boolean shouldContinueExecuting()
	{
		return !this.temptedEntity.getNavigator().noPath();
	}
	
	public void resetTask()
	{
		this.temptingPlayer = null;
		this.temptedEntity.getNavigator().clearPath();
		this.isRunning = false;
	}

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

						if (blockchk == BlockHandler.blockNest && !temptedEntity.hasPath())
						{

							TileEntityNest te = (TileEntityNest) temptedentity.level.getTileEntity(pos);
							NestContent nestType = te.getNestContent();

							if (nestType == NestContent.CHICKEN_BROWN || nestType == NestContent.CHICKEN_WHITE || nestType == NestContent.EMPTY)
							{
								if (temptedEntity instanceof EntityHenLeghorn && (nestType == NestContent.CHICKEN_WHITE ? ((EntityHenBase)temptedEntity).type == te.birdType: nestType == NestContent.EMPTY))
								{
									nestFound = true;
								}
								else if (temptedEntity instanceof EntityHenOrpington && (nestType == NestContent.CHICKEN_WHITE ? ((EntityHenBase)temptedEntity).type == te.birdType: nestType == NestContent.EMPTY))
								{
									nestFound = true;
								}
								else if (temptedEntity instanceof EntityHenPlymouthRock && (nestType == NestContent.CHICKEN_WHITE ? ((EntityHenBase)temptedEntity).type == te.birdType: nestType == NestContent.EMPTY))
								{
									nestFound = true;
								}
								else if (temptedEntity instanceof EntityHenRhodeIslandRed && (nestType == NestContent.CHICKEN_BROWN ? ((EntityHenBase)temptedEntity).type == te.birdType: nestType == NestContent.EMPTY))
								{
									nestFound = true;
								}
								else if (temptedEntity instanceof EntityHenWyandotte && (nestType == NestContent.CHICKEN_BROWN ? ((EntityHenBase)temptedEntity).type == te.birdType: nestType == NestContent.EMPTY))
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

									if (temptedEntity.getX() < nestPos.getX())
									{
										BlockPos nestPoschk = new BlockPos(x + i + 1, y + j, z + k);
										Block nestBlockchk = temptedentity.level.getBlockState(nestPoschk).getBlock();
										if (nestBlockchk == BlockHandler.blockNest)
										{
											i = i + 1;
										}
									}

									if (temptedEntity.getZ() < nestPos.getZ())
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

				List<Entity> nestClear = temptedentity.level.getEntitiesWithinAABBExcludingEntity(temptedEntity, temptedEntity.getEntityBoundingBox().expand(1, 1, 1));

				if (nestBlockchk == BlockHandler.blockNest && nestClear.isEmpty())
				{
					this.temptedEntity.getNavigator().tryMoveToXYZ(nestPos.getX() + .50, nestPos.getY(), nestPos.getZ() + .50, this.speed);
					this.temptedEntity.getLookHelper().setLookPosition(nestPos.getX(), nestPos.getY(), nestPos.getZ(), 10.0F, 10.0F);
					
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