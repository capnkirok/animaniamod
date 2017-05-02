package com.animania.entities.pigs;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityAIMatePigs extends EntityAIBase
{
	private final EntityAnimal theAnimal;
	World theWorld;
	private EntityAnimal targetMate;
	int courtshipTimer;
	double moveSpeed;
	private int delayCounter;


	public EntityAIMatePigs(EntityAnimal animal, double speedIn)
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
	public boolean shouldExecute()
	{

		delayCounter++;
		if (delayCounter > 40) {

			if (this.theAnimal instanceof EntitySowDuroc) {
				EntitySowDuroc ec = (EntitySowDuroc)this.theAnimal;
				if (ec.getMateUniqueId() != null) {
					return false;
				}
			} else if (this.theAnimal instanceof EntityHogDuroc) {
				EntityHogDuroc ec = (EntityHogDuroc)this.theAnimal;
				if (ec.getMateUniqueId() != null) {
					return false;
				}
			} else if (this.theAnimal instanceof EntitySowHampshire) {
				EntitySowHampshire ec = (EntitySowHampshire)this.theAnimal;
				if (ec.getMateUniqueId() != null) {
					return false;
				}
			} else if (this.theAnimal instanceof EntityHogHampshire) {
				EntityHogHampshire ec = (EntityHogHampshire)this.theAnimal;
				if (ec.getMateUniqueId() != null) {
					return false;
				}
			} else if (this.theAnimal instanceof EntitySowLargeBlack) {
				EntitySowLargeBlack ec = (EntitySowLargeBlack)this.theAnimal;
				if (ec.getMateUniqueId() != null) {
					return false;
				}
			} else if (this.theAnimal instanceof EntityHogLargeBlack) {
				EntityHogLargeBlack ec = (EntityHogLargeBlack)this.theAnimal;
				if (ec.getMateUniqueId() != null) {
					return false;
				}
			} else if (this.theAnimal instanceof EntitySowLargeWhite) {
				EntitySowLargeWhite ec = (EntitySowLargeWhite)this.theAnimal;
				if (ec.getMateUniqueId() != null) {
					return false;
				}
			} else if (this.theAnimal instanceof EntityHogLargeWhite) {
				EntityHogLargeWhite ec = (EntityHogLargeWhite)this.theAnimal;
				if (ec.getMateUniqueId() != null) {
					return false;
				}
			} else if (this.theAnimal instanceof EntitySowOldSpot) {
				EntitySowOldSpot ec = (EntitySowOldSpot)this.theAnimal;
				if (ec.getMateUniqueId() != null) {
					return false;
				}
			} else if (this.theAnimal instanceof EntityHogOldSpot) {
				EntityHogOldSpot ec = (EntityHogOldSpot)this.theAnimal;
				if (ec.getMateUniqueId() != null) {
					return false;
				}
			} else if (this.theAnimal instanceof EntitySowYorkshire) {
				EntitySowYorkshire ec = (EntitySowYorkshire)this.theAnimal;
				if (ec.getMateUniqueId() != null) {
					return false;
				}
			} else if (this.theAnimal instanceof EntityHogYorkshire) {
				EntityHogYorkshire ec = (EntityHogYorkshire)this.theAnimal;
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
	public boolean continueExecuting()
	{
		return this.targetMate.isEntityAlive();
	}

	/**
	 * Resets the task
	 */
	public void resetTask()
	{
		this.targetMate = null;
	}

	/**
	 * Updates the task
	 */
	public void updateTask()
	{
		this.theAnimal.getLookHelper().setLookPositionWithEntity(this.targetMate, 10.0F, (float)this.theAnimal.getVerticalFaceSpeed());
		this.theAnimal.getNavigator().tryMoveToEntityLiving(this.targetMate, this.moveSpeed);

	}


	private EntityAnimal getNearbyMate()
	{

		if (this.theAnimal instanceof EntitySowDuroc || this.theAnimal instanceof EntitySowDuroc || this.theAnimal instanceof EntitySowHampshire || this.theAnimal instanceof EntitySowLargeBlack || this.theAnimal instanceof EntitySowLargeWhite || this.theAnimal instanceof EntitySowOldSpot | this.theAnimal instanceof EntitySowYorkshire) {


			String mateID = "";
			if (this.theAnimal instanceof EntitySowLargeWhite) {
				EntitySowLargeWhite entity2 = (EntitySowLargeWhite)this.theAnimal;
				if (entity2.getMateUniqueId() != null) {
					mateID = entity2.getMateUniqueId().toString();
				}
			} else if (this.theAnimal instanceof EntitySowHampshire) {
				EntitySowHampshire entity2 = (EntitySowHampshire)this.theAnimal;	
				if (entity2.getMateUniqueId() != null) {
					mateID = entity2.getMateUniqueId().toString();
				}
			} else if (this.theAnimal instanceof EntitySowLargeBlack) {
				EntitySowLargeBlack entity2 = (EntitySowLargeBlack)this.theAnimal;
				if (entity2.getMateUniqueId() != null) {
					mateID = entity2.getMateUniqueId().toString();
				}
			} else if (this.theAnimal instanceof EntitySowOldSpot) {
				EntitySowOldSpot entity2 = (EntitySowOldSpot)this.theAnimal;
				if (entity2.getMateUniqueId() != null) {
					mateID = entity2.getMateUniqueId().toString();
				}
			} else if (this.theAnimal instanceof EntitySowYorkshire) {
				EntitySowYorkshire entity2 = (EntitySowYorkshire)this.theAnimal;
				if (entity2.getMateUniqueId() != null) {
					mateID = entity2.getMateUniqueId().toString();
				}
			} else {
				EntitySowDuroc entity2 = (EntitySowDuroc)this.theAnimal;
				if (entity2.getMateUniqueId() != null) {
					mateID = entity2.getMateUniqueId().toString();
				}
			}

			int esize = this.theWorld.loadedEntityList.size();
			for (int k = 0; k <= esize - 1; k++) {
				Entity entity = (Entity) this.theWorld.loadedEntityList.get(k);

				double xt = entity.posX;
				double yt = entity.posY;
				double zt = entity.posZ;
				int x1 = MathHelper.floor(this.theAnimal.posX);
				int y1 = MathHelper.floor(this.theAnimal.posY);
				int z1 = MathHelper.floor(this.theAnimal.posZ);
				double x2 = Math.abs(xt - x1);
				double y2 = Math.abs(yt - y1);
				double z2 = Math.abs(zt - z1);


				if (entity !=null && (entity instanceof EntityHogDuroc || entity instanceof EntityHogHampshire || entity instanceof EntityHogLargeBlack || entity instanceof EntityHogLargeWhite || entity instanceof EntityHogOldSpot || entity instanceof EntityHogYorkshire) && x2 <= 4 && y2 <=2 && z2 <=4) {

					this.courtshipTimer--;
					if (entity instanceof EntityHogDuroc) {
						EntityHogDuroc entity3 = (EntityHogDuroc) entity;
						if (entity3.getMateUniqueId() == null && this.courtshipTimer < 0) {
							if (this.theAnimal instanceof EntitySowDuroc) {
								((EntitySowDuroc) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntitySowHampshire) {
								((EntitySowHampshire) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntitySowLargeBlack) {
								((EntitySowLargeBlack) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntitySowLargeWhite) {
								((EntitySowLargeWhite) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntitySowOldSpot) {
								((EntitySowOldSpot) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntitySowYorkshire) {
								((EntitySowYorkshire) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							}
							this.theAnimal.setInLove(null);
							this.courtshipTimer = 20;
							k = esize;
							return (EntityAnimal) entity;
						} else if (entity3.getMateUniqueId() == null) {
							k = esize;
							this.theAnimal.setInLove(null);
							this.theAnimal.getLookHelper().setLookPositionWithEntity(entity, 10.0F, (float)this.theAnimal.getVerticalFaceSpeed());
							this.theAnimal.getNavigator().tryMoveToEntityLiving(entity, this.moveSpeed);
							return null;
						}
					} else if (entity instanceof EntityHogHampshire) {
						EntityHogHampshire entity3 = (EntityHogHampshire) entity;
						if (entity3.getMateUniqueId() == null && this.courtshipTimer < 0) {
							if (this.theAnimal instanceof EntitySowDuroc) {
								((EntitySowDuroc) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntitySowHampshire) {
								((EntitySowHampshire) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntitySowLargeBlack) {
								((EntitySowLargeBlack) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntitySowLargeWhite) {
								((EntitySowLargeWhite) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntitySowOldSpot) {
								((EntitySowOldSpot) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntitySowYorkshire) {
								((EntitySowYorkshire) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							}
							this.theAnimal.setInLove(null);
							this.courtshipTimer = 20;
							k = esize;
							return (EntityAnimal) entity;
						} else if (entity3.getMateUniqueId() == null) {
							k = esize;
							this.theAnimal.setInLove(null);
							this.theAnimal.getLookHelper().setLookPositionWithEntity(entity, 10.0F, (float)this.theAnimal.getVerticalFaceSpeed());
							this.theAnimal.getNavigator().tryMoveToEntityLiving(entity, this.moveSpeed);
							return null;
						}
					} else if (entity instanceof EntityHogLargeBlack) {
						EntityHogLargeBlack entity3 = (EntityHogLargeBlack) entity;
						if (entity3.getMateUniqueId() == null && this.courtshipTimer < 0) {
							if (this.theAnimal instanceof EntitySowDuroc) {
								((EntitySowDuroc) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntitySowHampshire) {
								((EntitySowHampshire) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntitySowLargeBlack) {
								((EntitySowLargeBlack) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntitySowLargeWhite) {
								((EntitySowLargeWhite) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntitySowOldSpot) {
								((EntitySowOldSpot) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntitySowYorkshire) {
								((EntitySowYorkshire) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							}
							this.theAnimal.setInLove(null);
							this.courtshipTimer = 20;
							k = esize;
							return (EntityAnimal) entity;
						} else if (entity3.getMateUniqueId() == null) {
							k = esize;
							this.theAnimal.setInLove(null);
							this.theAnimal.getLookHelper().setLookPositionWithEntity(entity, 10.0F, (float)this.theAnimal.getVerticalFaceSpeed());
							this.theAnimal.getNavigator().tryMoveToEntityLiving(entity, this.moveSpeed);
							return null;
						}
					} else if (entity instanceof EntityHogLargeWhite) {
						EntityHogLargeWhite entity3 = (EntityHogLargeWhite) entity;
						if (entity3.getMateUniqueId() == null && this.courtshipTimer < 0) {
							if (this.theAnimal instanceof EntitySowDuroc) {
								((EntitySowDuroc) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntitySowHampshire) {
								((EntitySowHampshire) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntitySowLargeBlack) {
								((EntitySowLargeBlack) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntitySowLargeWhite) {
								((EntitySowLargeWhite) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntitySowOldSpot) {
								((EntitySowOldSpot) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntitySowYorkshire) {
								((EntitySowYorkshire) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							}
							this.theAnimal.setInLove(null);
							this.courtshipTimer = 20;
							k = esize;
							return (EntityAnimal) entity;
						} else if (entity3.getMateUniqueId() == null) {
							k = esize;
							this.theAnimal.setInLove(null);
							this.theAnimal.getLookHelper().setLookPositionWithEntity(entity, 10.0F, (float)this.theAnimal.getVerticalFaceSpeed());
							this.theAnimal.getNavigator().tryMoveToEntityLiving(entity, this.moveSpeed);
							return null;
						}
					} else if (entity instanceof EntityHogOldSpot) {
						EntityHogOldSpot entity3 = (EntityHogOldSpot) entity;
						if (entity3.getMateUniqueId() == null && this.courtshipTimer < 0) {
							if (this.theAnimal instanceof EntitySowDuroc) {
								((EntitySowDuroc) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntitySowHampshire) {
								((EntitySowHampshire) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntitySowLargeBlack) {
								((EntitySowLargeBlack) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntitySowLargeWhite) {
								((EntitySowLargeWhite) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntitySowOldSpot) {
								((EntitySowOldSpot) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntitySowYorkshire) {
								((EntitySowYorkshire) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							}
							this.theAnimal.setInLove(null);
							this.courtshipTimer = 20;
							k = esize;
							return (EntityAnimal) entity;
						} else if (entity3.getMateUniqueId() == null) {
							k = esize;
							this.theAnimal.setInLove(null);
							this.theAnimal.getLookHelper().setLookPositionWithEntity(entity, 10.0F, (float)this.theAnimal.getVerticalFaceSpeed());
							this.theAnimal.getNavigator().tryMoveToEntityLiving(entity, this.moveSpeed);
							return null;
						}
					} else if (entity instanceof EntityHogYorkshire) {
						EntityHogYorkshire entity3 = (EntityHogYorkshire) entity;
						if (entity3.getMateUniqueId() == null && this.courtshipTimer < 0) {
							if (this.theAnimal instanceof EntitySowDuroc) {
								((EntitySowDuroc) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntitySowHampshire) {
								((EntitySowHampshire) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntitySowLargeBlack) {
								((EntitySowLargeBlack) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntitySowLargeWhite) {
								((EntitySowLargeWhite) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntitySowOldSpot) {
								((EntitySowOldSpot) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntitySowYorkshire) {
								((EntitySowYorkshire) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							}
							this.theAnimal.setInLove(null);
							this.courtshipTimer = 20;
							k = esize;
							return (EntityAnimal) entity;
						} else if (entity3.getMateUniqueId() == null) {
							k = esize;
							this.theAnimal.setInLove(null);
							this.theAnimal.getLookHelper().setLookPositionWithEntity(entity, 10.0F, (float)this.theAnimal.getVerticalFaceSpeed());
							this.theAnimal.getNavigator().tryMoveToEntityLiving(entity, this.moveSpeed);
							return null;
						}
					}
				} 
			}
		} else if (this.theAnimal instanceof EntityHogDuroc || this.theAnimal instanceof EntityHogHampshire || this.theAnimal instanceof EntityHogLargeBlack || this.theAnimal instanceof EntityHogLargeWhite || this.theAnimal instanceof EntityHogOldSpot || this.theAnimal instanceof EntityHogYorkshire) {


			String mateID = "";
			if (this.theAnimal instanceof EntityHogLargeWhite) {
				EntityHogLargeWhite entity2 = (EntityHogLargeWhite)this.theAnimal;
				if (entity2.getMateUniqueId() != null) {
					mateID = entity2.getMateUniqueId().toString();
				}
			} else if (this.theAnimal instanceof EntityHogHampshire) {
				EntityHogHampshire entity2 = (EntityHogHampshire)this.theAnimal;	
				if (entity2.getMateUniqueId() != null) {
					mateID = entity2.getMateUniqueId().toString();
				}
			} else if (this.theAnimal instanceof EntityHogLargeBlack) {
				EntityHogLargeBlack entity2 = (EntityHogLargeBlack)this.theAnimal;
				if (entity2.getMateUniqueId() != null) {
					mateID = entity2.getMateUniqueId().toString();
				}
			} else if (this.theAnimal instanceof EntityHogOldSpot) {
				EntityHogOldSpot entity2 = (EntityHogOldSpot)this.theAnimal;
				if (entity2.getMateUniqueId() != null) {
					mateID = entity2.getMateUniqueId().toString();
				}
			} else if (this.theAnimal instanceof EntityHogYorkshire) {
				EntityHogYorkshire entity2 = (EntityHogYorkshire)this.theAnimal;
				if (entity2.getMateUniqueId() != null) {
					mateID = entity2.getMateUniqueId().toString();
				}
			} else  {
				EntityHogDuroc entity2 = (EntityHogDuroc)this.theAnimal;
				if (entity2.getMateUniqueId() != null) {
					mateID = entity2.getMateUniqueId().toString();
				}
			}


			int esize = this.theWorld.loadedEntityList.size();
			for (int k = 0; k <= esize - 1; k++) {
				Entity entity = (Entity) this.theWorld.loadedEntityList.get(k);

				double xt = entity.posX;
				double yt = entity.posY;
				double zt = entity.posZ;
				int x1 = MathHelper.floor(this.theAnimal.posX);
				int y1 = MathHelper.floor(this.theAnimal.posY);
				int z1 = MathHelper.floor(this.theAnimal.posZ);
				double x2 = Math.abs(xt - x1);
				double y2 = Math.abs(yt - y1);
				double z2 = Math.abs(zt - z1);

				if (entity !=null && (entity instanceof EntitySowDuroc || entity instanceof EntitySowHampshire || entity instanceof EntitySowLargeBlack || entity instanceof EntitySowLargeWhite || entity instanceof EntitySowOldSpot || entity instanceof EntitySowYorkshire) && x2 <= 4 && y2 <=2 && z2 <=4) {

					this.courtshipTimer--;

					if (entity instanceof EntitySowDuroc) {
						EntitySowDuroc entity3 = (EntitySowDuroc) entity;
						if (entity3.getMateUniqueId() == null && this.courtshipTimer < 0 )  {
							if (this.theAnimal instanceof EntityHogDuroc) {
								((EntityHogDuroc) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityHogHampshire) {
								((EntityHogHampshire) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityHogLargeBlack) {
								((EntityHogLargeBlack) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityHogLargeWhite) {
								((EntityHogLargeWhite) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityHogOldSpot) {
								((EntityHogOldSpot) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityHogYorkshire) {
								((EntityHogYorkshire) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							}
							this.theAnimal.setInLove(null);
							this.courtshipTimer = 20;
							k = esize;
							return (EntityAnimal) entity;
						} else if (entity3.getMateUniqueId() == null) {
							k = esize;
							this.theAnimal.setInLove(null);
							this.theAnimal.getLookHelper().setLookPositionWithEntity(entity, 10.0F, (float)this.theAnimal.getVerticalFaceSpeed());
							this.theAnimal.getNavigator().tryMoveToEntityLiving(entity, this.moveSpeed);
							return null;
						}
					} else if (entity instanceof EntitySowHampshire) {
						EntitySowHampshire entity3 = (EntitySowHampshire) entity;
						if (entity3.getMateUniqueId() == null && this.courtshipTimer < 0 )  {
							if (this.theAnimal instanceof EntityHogDuroc) {
								((EntityHogDuroc) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityHogHampshire) {
								((EntityHogHampshire) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityHogLargeBlack) {
								((EntityHogLargeBlack) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityHogLargeWhite) {
								((EntityHogLargeWhite) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityHogOldSpot) {
								((EntityHogOldSpot) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityHogYorkshire) {
								((EntityHogYorkshire) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							}
							this.theAnimal.setInLove(null);
							this.courtshipTimer = 20;
							k = esize;
							return (EntityAnimal) entity;
						} else if (entity3.getMateUniqueId() == null) {
							k = esize;
							this.theAnimal.setInLove(null);
							this.theAnimal.getLookHelper().setLookPositionWithEntity(entity, 10.0F, (float)this.theAnimal.getVerticalFaceSpeed());
							this.theAnimal.getNavigator().tryMoveToEntityLiving(entity, this.moveSpeed);
							return null;
						}
					} else if (entity instanceof EntitySowLargeBlack) {
						EntitySowLargeBlack entity3 = (EntitySowLargeBlack) entity;
						if (entity3.getMateUniqueId() == null && this.courtshipTimer < 0 )  {
							if (this.theAnimal instanceof EntityHogDuroc) {
								((EntityHogDuroc) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityHogHampshire) {
								((EntityHogHampshire) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityHogLargeBlack) {
								((EntityHogLargeBlack) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityHogLargeWhite) {
								((EntityHogLargeWhite) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityHogOldSpot) {
								((EntityHogOldSpot) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityHogYorkshire) {
								((EntityHogYorkshire) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							}
							this.theAnimal.setInLove(null);
							this.courtshipTimer = 20;
							k = esize;
							return (EntityAnimal) entity;
						} else if (entity3.getMateUniqueId() == null) {
							k = esize;
							this.theAnimal.setInLove(null);
							this.theAnimal.getLookHelper().setLookPositionWithEntity(entity, 10.0F, (float)this.theAnimal.getVerticalFaceSpeed());
							this.theAnimal.getNavigator().tryMoveToEntityLiving(entity, this.moveSpeed);
							return null;
						}
					} else if (entity instanceof EntitySowLargeWhite) {
						EntitySowLargeWhite entity3 = (EntitySowLargeWhite) entity;
						if (entity3.getMateUniqueId() == null && this.courtshipTimer < 0 )  {
							if (this.theAnimal instanceof EntityHogDuroc) {
								((EntityHogDuroc) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityHogHampshire) {
								((EntityHogHampshire) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityHogLargeBlack) {
								((EntityHogLargeBlack) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityHogLargeWhite) {
								((EntityHogLargeWhite) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityHogOldSpot) {
								((EntityHogOldSpot) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityHogYorkshire) {
								((EntityHogYorkshire) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							}
							this.theAnimal.setInLove(null);
							this.courtshipTimer = 20;
							return (EntityAnimal) entity;
						} else if (entity3.getMateUniqueId() == null) {
							k = esize;
							this.theAnimal.setInLove(null);
							this.theAnimal.getLookHelper().setLookPositionWithEntity(entity, 10.0F, (float)this.theAnimal.getVerticalFaceSpeed());
							this.theAnimal.getNavigator().tryMoveToEntityLiving(entity, this.moveSpeed);
							return null;
						}
					} else if (entity instanceof EntitySowOldSpot) {
						EntitySowOldSpot entity3 = (EntitySowOldSpot) entity;
						if (entity3.getMateUniqueId() == null && this.courtshipTimer < 0 )  {
							if (this.theAnimal instanceof EntityHogDuroc) {
								((EntityHogDuroc) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityHogHampshire) {
								((EntityHogHampshire) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityHogLargeBlack) {
								((EntityHogLargeBlack) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityHogLargeWhite) {
								((EntityHogLargeWhite) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityHogOldSpot) {
								((EntityHogOldSpot) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityHogYorkshire) {
								((EntityHogYorkshire) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							}
							this.theAnimal.setInLove(null);
							this.courtshipTimer = 20;
							k = esize;
							return (EntityAnimal) entity;
						} else if (entity3.getMateUniqueId() == null) {
							k = esize;
							this.theAnimal.setInLove(null);
							this.theAnimal.getLookHelper().setLookPositionWithEntity(entity, 10.0F, (float)this.theAnimal.getVerticalFaceSpeed());
							this.theAnimal.getNavigator().tryMoveToEntityLiving(entity, this.moveSpeed);
							return null;
						}
					} else if (entity instanceof EntitySowYorkshire) {
						EntitySowYorkshire entity3 = (EntitySowYorkshire) entity;
						if (entity3.getMateUniqueId() == null && this.courtshipTimer < 0 )  {
							if (this.theAnimal instanceof EntityHogDuroc) {
								((EntityHogDuroc) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityHogHampshire) {
								((EntityHogHampshire) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityHogLargeBlack) {
								((EntityHogLargeBlack) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityHogLargeWhite) {
								((EntityHogLargeWhite) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityHogOldSpot) {
								((EntityHogOldSpot) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} else if (this.theAnimal instanceof EntityHogYorkshire) {
								((EntityHogYorkshire) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							}
							this.theAnimal.setInLove(null);
							this.courtshipTimer = 20;
							k = esize;
							return (EntityAnimal) entity;
						} else if (entity3.getMateUniqueId() == null) {
							k = esize;
							this.theAnimal.setInLove(null);
							this.theAnimal.getLookHelper().setLookPositionWithEntity(entity, 10.0F, (float)this.theAnimal.getVerticalFaceSpeed());
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