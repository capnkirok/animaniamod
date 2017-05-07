package com.animania.common.entities.cows.ai;

import java.util.Random;

import com.animania.common.entities.cows.EntityBullAngus;
import com.animania.common.entities.cows.EntityBullFriesian;
import com.animania.common.entities.cows.EntityBullHereford;
import com.animania.common.entities.cows.EntityBullHolstein;
import com.animania.common.entities.cows.EntityBullLonghorn;
import com.animania.common.entities.cows.EntityCowAngus;
import com.animania.common.entities.cows.EntityCowFriesian;
import com.animania.common.entities.cows.EntityCowHereford;
import com.animania.common.entities.cows.EntityCowHolstein;
import com.animania.common.entities.cows.EntityCowLonghorn;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityAIMateCows extends EntityAIBase
{
	private final EntityAnimal theAnimal;
	World theWorld;
	private EntityAnimal targetMate;
	int courtshipTimer;
	double moveSpeed;
	private int delayCounter;


	public EntityAIMateCows(EntityAnimal animal, double speedIn)
	{
		this.theAnimal = animal;
		this.theWorld = animal.world;
		this.moveSpeed = speedIn;
		this.setMutexBits(3);
		this.courtshipTimer = 20;
		this.delayCounter = 0;

	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	@Override
	public boolean shouldExecute()
	{
		delayCounter++;
		if (delayCounter > 60) {

			if (this.theAnimal instanceof EntityCowAngus) {
				EntityCowAngus ec = (EntityCowAngus)this.theAnimal;
				if (ec.getMateUniqueId() != null) {
					return false;
				}
			} else if (this.theAnimal instanceof EntityBullAngus) {
				EntityBullAngus ec = (EntityBullAngus)this.theAnimal;
				if (ec.getMateUniqueId() != null) {
					return false;
				}
			} else if (this.theAnimal instanceof EntityCowFriesian) {
				EntityCowFriesian ec = (EntityCowFriesian)this.theAnimal;
				if (ec.getMateUniqueId() != null) {
					return false;
				}
			} else if (this.theAnimal instanceof EntityBullFriesian) {
				EntityBullFriesian ec = (EntityBullFriesian)this.theAnimal;
				if (ec.getMateUniqueId() != null) {
					return false;
				}
			} else if (this.theAnimal instanceof EntityCowHereford) {
				EntityCowHereford ec = (EntityCowHereford)this.theAnimal;
				if (ec.getMateUniqueId() != null) {
					return false;
				}
			} else if (this.theAnimal instanceof EntityBullHereford) {
				EntityBullHereford ec = (EntityBullHereford)this.theAnimal;
				if (ec.getMateUniqueId() != null) {
					return false;
				}
			} else if (this.theAnimal instanceof EntityCowHolstein) {
				EntityCowHolstein ec = (EntityCowHolstein)this.theAnimal;
				if (ec.getMateUniqueId() != null) {
					return false;
				}
			} else if (this.theAnimal instanceof EntityBullHolstein) {
				EntityBullHolstein ec = (EntityBullHolstein)this.theAnimal;
				if (ec.getMateUniqueId() != null) {
					return false;
				}
			} else if (this.theAnimal instanceof EntityCowLonghorn) {
				EntityCowLonghorn ec = (EntityCowLonghorn)this.theAnimal;
				if (ec.getMateUniqueId() != null) {
					return false;
				}
			} else if (this.theAnimal instanceof EntityBullLonghorn) {
				EntityBullLonghorn ec = (EntityBullLonghorn)this.theAnimal;
				if (ec.getMateUniqueId() != null) {
					return false;
				}
			}

			this.targetMate = this.getNearbyMate();
			
			Random rand = new Random();
			if (this.targetMate != null && rand.nextInt(20) == 0) {
				this.delayCounter = 0;
				this.resetTask();
				return false;
			}
			
			return this.targetMate != null;
		} else {
			return false;
		}
	}

	/**
	 * Returns whether an in-progress EntityAIBase should continue executing
	 */
	@Override
	public boolean continueExecuting()
	{
		return this.targetMate.isEntityAlive();
	}

	/**
	 * Resets the task
	 */
	@Override
	public void resetTask()
	{
		this.targetMate = null;
	}

	/**
	 * Updates the task
	 */
	@Override
	public void updateTask()
	{
		this.theAnimal.getLookHelper().setLookPositionWithEntity(this.targetMate, 10.0F, this.theAnimal.getVerticalFaceSpeed());
		this.theAnimal.getNavigator().tryMoveToEntityLiving(this.targetMate, this.moveSpeed);

	}


	private EntityAnimal getNearbyMate()
	{

		if (this.theAnimal instanceof EntityCowAngus || this.theAnimal instanceof EntityCowFriesian || this.theAnimal instanceof EntityCowHereford || this.theAnimal instanceof EntityCowHolstein || this.theAnimal instanceof EntityCowLonghorn) {


			String mateID = "";
			if (this.theAnimal instanceof EntityCowHolstein) {
				EntityCowHolstein entity2 = (EntityCowHolstein)this.theAnimal;
				if (entity2.getMateUniqueId() != null) {
					mateID = entity2.getMateUniqueId().toString();
				}
			} else if (this.theAnimal instanceof EntityCowFriesian) {
				EntityCowFriesian entity2 = (EntityCowFriesian)this.theAnimal;	
				if (entity2.getMateUniqueId() != null) {
					mateID = entity2.getMateUniqueId().toString();
				}
			} else if (this.theAnimal instanceof EntityCowHereford) {
				EntityCowHereford entity2 = (EntityCowHereford)this.theAnimal;
				if (entity2.getMateUniqueId() != null) {
					mateID = entity2.getMateUniqueId().toString();
				}
			} else if (this.theAnimal instanceof EntityCowLonghorn) {
				EntityCowLonghorn entity2 = (EntityCowLonghorn)this.theAnimal;
				if (entity2.getMateUniqueId() != null) {
					mateID = entity2.getMateUniqueId().toString();
				}
			} else  {
				EntityCowAngus entity2 = (EntityCowAngus)this.theAnimal;
				if (entity2.getMateUniqueId() != null) {
					mateID = entity2.getMateUniqueId().toString();
				}
			}

			int esize = this.theWorld.loadedEntityList.size();
			for (int k = 0; k <= esize - 1; k++) {
				Entity entity = this.theWorld.loadedEntityList.get(k);

				double xt = entity.posX;
				double yt = entity.posY;
				double zt = entity.posZ;
				int x1 = MathHelper.floor(this.theAnimal.posX);
				int y1 = MathHelper.floor(this.theAnimal.posY);
				int z1 = MathHelper.floor(this.theAnimal.posZ);
				double x2 = Math.abs(xt - x1);
				double y2 = Math.abs(yt - y1);
				double z2 = Math.abs(zt - z1);


				if (entity !=null && (entity instanceof EntityBullAngus || entity instanceof EntityBullFriesian || entity instanceof EntityBullHereford || entity instanceof EntityBullHolstein || entity instanceof EntityBullLonghorn) && x2 <= 4 && y2 <=2 && z2 <=4) {

					this.courtshipTimer--;
					if (entity instanceof EntityBullAngus) {
						EntityBullAngus entity3 = (EntityBullAngus) entity;
						if (entity3.getMateUniqueId() == null && this.courtshipTimer < 0) {
							if (this.theAnimal instanceof EntityCowAngus) {
								((EntityCowAngus) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityCowFriesian) {
								((EntityCowFriesian) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityCowHereford) {
								((EntityCowHereford) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityCowHolstein) {
								((EntityCowHolstein) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityCowLonghorn) {
								((EntityCowLonghorn) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							}
							this.theAnimal.setInLove(null);
							this.courtshipTimer = 20;
							k = esize;
							return (EntityAnimal) entity;
						} else if (entity3.getMateUniqueId() == null) {
							k = esize;
							this.theAnimal.setInLove(null);
							this.theAnimal.getLookHelper().setLookPositionWithEntity(entity, 10.0F, this.theAnimal.getVerticalFaceSpeed());
							this.theAnimal.getNavigator().tryMoveToEntityLiving(entity, this.moveSpeed);
							return null;
						}
					} else if (entity instanceof EntityBullFriesian) {
						EntityBullFriesian entity3 = (EntityBullFriesian) entity;
						if (entity3.getMateUniqueId() == null && this.courtshipTimer < 0) {
							if (this.theAnimal instanceof EntityCowAngus) {
								((EntityCowAngus) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityCowFriesian) {
								((EntityCowFriesian) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityCowHereford) {
								((EntityCowHereford) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityCowHolstein) {
								((EntityCowHolstein) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityCowLonghorn) {
								((EntityCowLonghorn) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							}
							this.theAnimal.setInLove(null);
							this.courtshipTimer = 20;
							k = esize;
							return (EntityAnimal) entity;
						} else if (entity3.getMateUniqueId() == null) {
							k = esize;
							this.theAnimal.setInLove(null);
							this.theAnimal.getLookHelper().setLookPositionWithEntity(entity, 10.0F, this.theAnimal.getVerticalFaceSpeed());
							this.theAnimal.getNavigator().tryMoveToEntityLiving(entity, this.moveSpeed);
							return null;
						}
					} else if (entity instanceof EntityBullHereford) {
						EntityBullHereford entity3 = (EntityBullHereford) entity;
						if (entity3.getMateUniqueId() == null && this.courtshipTimer < 0) {
							if (this.theAnimal instanceof EntityCowAngus) {
								((EntityCowAngus) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityCowFriesian) {
								((EntityCowFriesian) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityCowHereford) {
								((EntityCowHereford) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityCowHolstein) {
								((EntityCowHolstein) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityCowLonghorn) {
								((EntityCowLonghorn) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							}
							this.theAnimal.setInLove(null);
							this.courtshipTimer = 20;
							k = esize;
							return (EntityAnimal) entity;
						} else if (entity3.getMateUniqueId() == null) {
							k = esize;
							this.theAnimal.setInLove(null);
							this.theAnimal.getLookHelper().setLookPositionWithEntity(entity, 10.0F, this.theAnimal.getVerticalFaceSpeed());
							this.theAnimal.getNavigator().tryMoveToEntityLiving(entity, this.moveSpeed);
							return null;
						}
					} else if (entity instanceof EntityBullHolstein) {
						EntityBullHolstein entity3 = (EntityBullHolstein) entity;
						if (entity3.getMateUniqueId() == null && this.courtshipTimer < 0) {
							if (this.theAnimal instanceof EntityCowAngus) {
								((EntityCowAngus) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityCowFriesian) {
								((EntityCowFriesian) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityCowHereford) {
								((EntityCowHereford) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityCowHolstein) {
								((EntityCowHolstein) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityCowLonghorn) {
								((EntityCowLonghorn) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							}
							this.theAnimal.setInLove(null);
							this.courtshipTimer = 20;
							k = esize;
							return (EntityAnimal) entity;
						} else if (entity3.getMateUniqueId() == null) {
							k = esize;
							this.theAnimal.setInLove(null);
							this.theAnimal.getLookHelper().setLookPositionWithEntity(entity, 10.0F, this.theAnimal.getVerticalFaceSpeed());
							this.theAnimal.getNavigator().tryMoveToEntityLiving(entity, this.moveSpeed);
							return null;
						}
					} else if (entity instanceof EntityBullLonghorn) {
						EntityBullLonghorn entity3 = (EntityBullLonghorn) entity;
						if (entity3.getMateUniqueId() == null && this.courtshipTimer < 0) {
							if (this.theAnimal instanceof EntityCowAngus) {
								((EntityCowAngus) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityCowFriesian) {
								((EntityCowFriesian) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityCowHereford) {
								((EntityCowHereford) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityCowHolstein) {
								((EntityCowHolstein) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityCowLonghorn) {
								((EntityCowLonghorn) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							}
							this.theAnimal.setInLove(null);
							this.courtshipTimer = 20;
							k = esize;
							return (EntityAnimal) entity;
						} else if (entity3.getMateUniqueId() == null) {
							k = esize;
							this.theAnimal.setInLove(null);
							this.theAnimal.getLookHelper().setLookPositionWithEntity(entity, 10.0F, this.theAnimal.getVerticalFaceSpeed());
							this.theAnimal.getNavigator().tryMoveToEntityLiving(entity, this.moveSpeed);
							return null;
						}
					}
				} 
			}
		} else if (this.theAnimal instanceof EntityBullAngus || this.theAnimal instanceof EntityBullAngus || this.theAnimal instanceof EntityBullFriesian || this.theAnimal instanceof EntityBullHereford || this.theAnimal instanceof EntityBullHolstein || this.theAnimal instanceof EntityBullLonghorn) {


			String mateID = "";
			if (this.theAnimal instanceof EntityBullHolstein) {
				EntityBullHolstein entity2 = (EntityBullHolstein)this.theAnimal;
				if (entity2.getMateUniqueId() != null) {
					mateID = entity2.getMateUniqueId().toString();
				}
			} else if (this.theAnimal instanceof EntityBullFriesian) {
				EntityBullFriesian entity2 = (EntityBullFriesian)this.theAnimal;	
				if (entity2.getMateUniqueId() != null) {
					mateID = entity2.getMateUniqueId().toString();
				}
			} else if (this.theAnimal instanceof EntityBullHereford) {
				EntityBullHereford entity2 = (EntityBullHereford)this.theAnimal;
				if (entity2.getMateUniqueId() != null) {
					mateID = entity2.getMateUniqueId().toString();
				}
			} else if (this.theAnimal instanceof EntityBullLonghorn) {
				EntityBullLonghorn entity2 = (EntityBullLonghorn)this.theAnimal;
				if (entity2.getMateUniqueId() != null) {
					mateID = entity2.getMateUniqueId().toString();
				}
			} else  {
				EntityBullAngus entity2 = (EntityBullAngus)this.theAnimal;
				if (entity2.getMateUniqueId() != null) {
					mateID = entity2.getMateUniqueId().toString();
				}
			}


			int esize = this.theWorld.loadedEntityList.size();
			for (int k = 0; k <= esize - 1; k++) {
				Entity entity = this.theWorld.loadedEntityList.get(k);

				double xt = entity.posX;
				double yt = entity.posY;
				double zt = entity.posZ;
				int x1 = MathHelper.floor(this.theAnimal.posX);
				int y1 = MathHelper.floor(this.theAnimal.posY);
				int z1 = MathHelper.floor(this.theAnimal.posZ);
				double x2 = Math.abs(xt - x1);
				double y2 = Math.abs(yt - y1);
				double z2 = Math.abs(zt - z1);


				if (entity !=null && (entity instanceof EntityCowAngus || entity instanceof EntityCowFriesian || entity instanceof EntityCowHereford || entity instanceof EntityCowHolstein || entity instanceof EntityCowLonghorn) && x2 <= 4 && y2 <=2 && z2 <=4) {

					this.courtshipTimer--;

					if (entity instanceof EntityCowAngus) {
						EntityCowAngus entity3 = (EntityCowAngus) entity;
						if (entity3.getMateUniqueId() == null && this.courtshipTimer < 0 )  {
							if (this.theAnimal instanceof EntityBullAngus) {
								((EntityBullAngus) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityBullFriesian) {
								((EntityBullFriesian) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityBullHereford) {
								((EntityBullHereford) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityBullHolstein) {
								((EntityBullHolstein) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityBullLonghorn) {
								((EntityBullLonghorn) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							}
							this.theAnimal.setInLove(null);
							this.courtshipTimer = 20;
							k = esize;
							return (EntityAnimal) entity;
						} else if (entity3.getMateUniqueId() == null) {
							k = esize;
							this.theAnimal.setInLove(null);
							this.theAnimal.getLookHelper().setLookPositionWithEntity(entity, 10.0F, this.theAnimal.getVerticalFaceSpeed());
							this.theAnimal.getNavigator().tryMoveToEntityLiving(entity, this.moveSpeed);
							return null;
						}
					} else if (entity instanceof EntityCowFriesian) {
						EntityCowFriesian entity3 = (EntityCowFriesian) entity;
						if (entity3.getMateUniqueId() == null && this.courtshipTimer < 0 )  {
							if (this.theAnimal instanceof EntityBullAngus) {
								((EntityBullAngus) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityBullFriesian) {
								((EntityBullFriesian) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityBullHereford) {
								((EntityBullHereford) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityBullHolstein) {
								((EntityBullHolstein) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityBullLonghorn) {
								((EntityBullLonghorn) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							}
							this.theAnimal.setInLove(null);
							this.courtshipTimer = 20;
							k = esize;
							return (EntityAnimal) entity;
						} else if (entity3.getMateUniqueId() == null) {
							k = esize;
							this.theAnimal.setInLove(null);
							this.theAnimal.getLookHelper().setLookPositionWithEntity(entity, 10.0F, this.theAnimal.getVerticalFaceSpeed());
							this.theAnimal.getNavigator().tryMoveToEntityLiving(entity, this.moveSpeed);
							return null;
						}
					} else if (entity instanceof EntityCowHereford) {
						EntityCowHereford entity3 = (EntityCowHereford) entity;
						if (entity3.getMateUniqueId() == null && this.courtshipTimer < 0 )  {
							if (this.theAnimal instanceof EntityBullAngus) {
								((EntityBullAngus) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityBullFriesian) {
								((EntityBullFriesian) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityBullHereford) {
								((EntityBullHereford) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityBullHolstein) {
								((EntityBullHolstein) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityBullLonghorn) {
								((EntityBullLonghorn) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							}
							this.theAnimal.setInLove(null);
							this.courtshipTimer = 20;
							k = esize;
							return (EntityAnimal) entity;
						} else if (entity3.getMateUniqueId() == null) {
							k = esize;
							this.theAnimal.setInLove(null);
							this.theAnimal.getLookHelper().setLookPositionWithEntity(entity, 10.0F, this.theAnimal.getVerticalFaceSpeed());
							this.theAnimal.getNavigator().tryMoveToEntityLiving(entity, this.moveSpeed);
							return null;
						}
					} else if (entity instanceof EntityCowHolstein) {
						EntityCowHolstein entity3 = (EntityCowHolstein) entity;
						if (entity3.getMateUniqueId() == null && this.courtshipTimer < 0 )  {
							if (this.theAnimal instanceof EntityBullAngus) {
								((EntityBullAngus) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityBullFriesian) {
								((EntityBullFriesian) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityBullHereford) {
								((EntityBullHereford) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityBullHolstein) {
								((EntityBullHolstein) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityBullLonghorn) {
								((EntityBullLonghorn) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							}
							this.theAnimal.setInLove(null);
							this.courtshipTimer = 20;
							return (EntityAnimal) entity;
						} else if (entity3.getMateUniqueId() == null) {
							k = esize;
							this.theAnimal.setInLove(null);
							this.theAnimal.getLookHelper().setLookPositionWithEntity(entity, 10.0F, this.theAnimal.getVerticalFaceSpeed());
							this.theAnimal.getNavigator().tryMoveToEntityLiving(entity, this.moveSpeed);
							return null;
						}
					} else if (entity instanceof EntityCowLonghorn) {
						EntityCowLonghorn entity3 = (EntityCowLonghorn) entity;
						if (entity3.getMateUniqueId() == null && this.courtshipTimer < 0 )  {
							if (this.theAnimal instanceof EntityBullAngus) {
								((EntityBullAngus) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityBullFriesian) {
								((EntityBullFriesian) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityBullHereford) {
								((EntityBullHereford) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityBullHolstein) {
								((EntityBullHolstein) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityBullLonghorn) {
								((EntityBullLonghorn) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							}
							this.theAnimal.setInLove(null);
							this.courtshipTimer = 20;
							k = esize;
							return (EntityAnimal) entity;
						} else if (entity3.getMateUniqueId() == null) {
							k = esize;
							this.theAnimal.setInLove(null);
							this.theAnimal.getLookHelper().setLookPositionWithEntity(entity, 10.0F, this.theAnimal.getVerticalFaceSpeed());
							this.theAnimal.getNavigator().tryMoveToEntityLiving(entity, this.moveSpeed);
							return null;
						}
					}


				} 
			}
		} 

		return null;
	}


}