package com.animania.common.entities.cows.ai;

import com.animania.common.entities.cows.EntityBullAngus;
import com.animania.common.entities.cows.EntityBullFriesian;
import com.animania.common.entities.cows.EntityBullHereford;
import com.animania.common.entities.cows.EntityBullHolstein;
import com.animania.common.entities.cows.EntityBullLonghorn;
import com.animania.common.entities.cows.EntityCalfAngus;
import com.animania.common.entities.cows.EntityCalfFriesian;
import com.animania.common.entities.cows.EntityCalfHereford;
import com.animania.common.entities.cows.EntityCalfHolstein;
import com.animania.common.entities.cows.EntityCalfLonghorn;
import com.animania.common.entities.cows.EntityCowAngus;
import com.animania.common.entities.cows.EntityCowFriesian;
import com.animania.common.entities.cows.EntityCowHereford;
import com.animania.common.entities.cows.EntityCowHolstein;
import com.animania.common.entities.cows.EntityCowLonghorn;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockStateMatcher;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityAICowEatGrass extends EntityAIBase
{
    private static final Predicate<IBlockState> IS_TALL_GRASS = BlockStateMatcher.forBlock(Blocks.TALLGRASS).where(BlockTallGrass.TYPE,
            Predicates.equalTo(BlockTallGrass.EnumType.GRASS));
    /** The entity owner of this AITask */
    private final EntityLiving                  grassEaterEntity;
    /** The world the grass eater entity is eating from */
    private final World                         entityWorld;
    /** Number of ticks since the entity started to eat grass */
    public int                                  eatingGrassTimer;

    public EntityAICowEatGrass(EntityLiving grassEaterEntityIn) {
        this.grassEaterEntity = grassEaterEntityIn;
        this.entityWorld = grassEaterEntityIn.world;
        this.setMutexBits(7);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute() {

        if (this.grassEaterEntity.getRNG().nextInt(this.grassEaterEntity.isChild() ? 50 : 1000) != 0)
            return false;
        else {
            BlockPos blockpos = new BlockPos(this.grassEaterEntity.posX, this.grassEaterEntity.posY, this.grassEaterEntity.posZ);
            return EntityAICowEatGrass.IS_TALL_GRASS.apply(this.entityWorld.getBlockState(blockpos)) ? true
                    : this.entityWorld.getBlockState(blockpos.down()).getBlock() == Blocks.GRASS;
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void startExecuting() {
        this.eatingGrassTimer = 100;
        this.entityWorld.setEntityState(this.grassEaterEntity, (byte) 10);
        this.grassEaterEntity.getNavigator().clearPathEntity();
    }

    /**
     * Resets the task
     */
    @Override
    public void resetTask() {
        this.eatingGrassTimer = 0;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
    public boolean continueExecuting() {

        return this.eatingGrassTimer > 0;
    }

    /**
     * Number of ticks since the entity started to eat grass
     */
    public int getEatingGrassTimer() {
        return this.eatingGrassTimer;
    }

    /**
     * Updates the task
     */
    @Override
    public void updateTask() {
        this.eatingGrassTimer = Math.max(0, this.eatingGrassTimer - 1);

        if (this.eatingGrassTimer == 4) {
            BlockPos blockpos = new BlockPos(this.grassEaterEntity.posX, this.grassEaterEntity.posY, this.grassEaterEntity.posZ);

            if (EntityAICowEatGrass.IS_TALL_GRASS.apply(this.entityWorld.getBlockState(blockpos))) {

                if (this.grassEaterEntity instanceof EntityCowHolstein) {
                    EntityCowHolstein ech = (EntityCowHolstein) this.grassEaterEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setFed(true);
                }
                else if (this.grassEaterEntity instanceof EntityCowFriesian) {
                    EntityCowFriesian ech = (EntityCowFriesian) this.grassEaterEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setFed(true);
                }
                else if (this.grassEaterEntity instanceof EntityCowHereford) {
                    EntityCowHereford ech = (EntityCowHereford) this.grassEaterEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setFed(true);
                }
                else if (this.grassEaterEntity instanceof EntityCowLonghorn) {
                    EntityCowLonghorn ech = (EntityCowLonghorn) this.grassEaterEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setFed(true);
                }
                else if (this.grassEaterEntity instanceof EntityCowAngus) {
                    EntityCowAngus ech = (EntityCowAngus) this.grassEaterEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setFed(true);
                }
                else if (this.grassEaterEntity instanceof EntityBullHolstein) {
                    EntityBullHolstein ech = (EntityBullHolstein) this.grassEaterEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setFed(true);
                }
                else if (this.grassEaterEntity instanceof EntityBullFriesian) {
                    EntityBullFriesian ech = (EntityBullFriesian) this.grassEaterEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setFed(true);
                }
                else if (this.grassEaterEntity instanceof EntityBullHereford) {
                    EntityBullHereford ech = (EntityBullHereford) this.grassEaterEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setFed(true);
                }
                else if (this.grassEaterEntity instanceof EntityBullLonghorn) {
                    EntityBullLonghorn ech = (EntityBullLonghorn) this.grassEaterEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setFed(true);
                }
                else if (this.grassEaterEntity instanceof EntityBullAngus) {
                    EntityBullAngus ech = (EntityBullAngus) this.grassEaterEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setFed(true);
                }
                else if (this.grassEaterEntity instanceof EntityCalfHolstein) {
                    EntityCalfHolstein ech = (EntityCalfHolstein) this.grassEaterEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setFed(true);
                }
                else if (this.grassEaterEntity instanceof EntityCalfFriesian) {
                    EntityCalfFriesian ech = (EntityCalfFriesian) this.grassEaterEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setFed(true);
                }
                else if (this.grassEaterEntity instanceof EntityCalfHereford) {
                    EntityCalfHereford ech = (EntityCalfHereford) this.grassEaterEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setFed(true);
                }
                else if (this.grassEaterEntity instanceof EntityCalfLonghorn) {
                    EntityCalfLonghorn ech = (EntityCalfLonghorn) this.grassEaterEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setFed(true);
                }
                else if (this.grassEaterEntity instanceof EntityCalfAngus) {
                    EntityCalfAngus ech = (EntityCalfAngus) this.grassEaterEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setFed(true);
                }

                this.grassEaterEntity.eatGrassBonus();
            }

            else

            {
                BlockPos blockpos1 = blockpos.down();

                if (this.entityWorld.getBlockState(blockpos1).getBlock() == Blocks.GRASS) {

                    this.entityWorld.playEvent(2001, blockpos1, Block.getIdFromBlock(Blocks.GRASS));
                    this.entityWorld.setBlockState(blockpos1, Blocks.DIRT.getDefaultState(), 2);

                    if (this.grassEaterEntity instanceof EntityCowHolstein) {
                        EntityCowHolstein ech = (EntityCowHolstein) this.grassEaterEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setFed(true);
                    }
                    else if (this.grassEaterEntity instanceof EntityCowFriesian) {
                        EntityCowFriesian ech = (EntityCowFriesian) this.grassEaterEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setFed(true);
                    }
                    else if (this.grassEaterEntity instanceof EntityCowHereford) {
                        EntityCowHereford ech = (EntityCowHereford) this.grassEaterEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setFed(true);
                    }
                    else if (this.grassEaterEntity instanceof EntityCowLonghorn) {
                        EntityCowLonghorn ech = (EntityCowLonghorn) this.grassEaterEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setFed(true);
                    }
                    else if (this.grassEaterEntity instanceof EntityCowAngus) {
                        EntityCowAngus ech = (EntityCowAngus) this.grassEaterEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setFed(true);
                    }
                    else if (this.grassEaterEntity instanceof EntityBullHolstein) {
                        EntityBullHolstein ech = (EntityBullHolstein) this.grassEaterEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setFed(true);
                    }
                    else if (this.grassEaterEntity instanceof EntityBullFriesian) {
                        EntityBullFriesian ech = (EntityBullFriesian) this.grassEaterEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setFed(true);
                    }
                    else if (this.grassEaterEntity instanceof EntityBullHereford) {
                        EntityBullHereford ech = (EntityBullHereford) this.grassEaterEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setFed(true);
                    }
                    else if (this.grassEaterEntity instanceof EntityBullLonghorn) {
                        EntityBullLonghorn ech = (EntityBullLonghorn) this.grassEaterEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setFed(true);
                    }
                    else if (this.grassEaterEntity instanceof EntityBullAngus) {
                        EntityBullAngus ech = (EntityBullAngus) this.grassEaterEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setFed(true);
                    }
                    else if (this.grassEaterEntity instanceof EntityCalfHolstein) {
                        EntityCalfHolstein ech = (EntityCalfHolstein) this.grassEaterEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setFed(true);
                    }
                    else if (this.grassEaterEntity instanceof EntityCalfFriesian) {
                        EntityCalfFriesian ech = (EntityCalfFriesian) this.grassEaterEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setFed(true);
                    }
                    else if (this.grassEaterEntity instanceof EntityCalfHereford) {
                        EntityCalfHereford ech = (EntityCalfHereford) this.grassEaterEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setFed(true);
                    }
                    else if (this.grassEaterEntity instanceof EntityCalfLonghorn) {
                        EntityCalfLonghorn ech = (EntityCalfLonghorn) this.grassEaterEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setFed(true);
                    }
                    else if (this.grassEaterEntity instanceof EntityCalfAngus) {
                        EntityCalfAngus ech = (EntityCalfAngus) this.grassEaterEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setFed(true);
                    }

                    this.grassEaterEntity.eatGrassBonus();
                }
            }
        }
    }
}