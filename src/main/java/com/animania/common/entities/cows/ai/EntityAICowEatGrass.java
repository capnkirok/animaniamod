package com.animania.common.entities.cows.ai;

import com.animania.common.entities.cows.EntityAnimaniaCow;
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
    private final EntityLiving                  grassEaterEntity;
    private final World                         entityWorld;
    public int                                  eatingGrassTimer;

    public EntityAICowEatGrass(EntityLiving grassEaterEntityIn) {
        this.grassEaterEntity = grassEaterEntityIn;
        this.entityWorld = grassEaterEntityIn.world;
        this.setMutexBits(7);
    }

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

    @Override
    public void startExecuting() {
        this.eatingGrassTimer = 100;
        this.entityWorld.setEntityState(this.grassEaterEntity, (byte) 10);
        this.grassEaterEntity.getNavigator().clearPathEntity();
    }

    @Override
    public void resetTask() {
        this.eatingGrassTimer = 0;
    }

    @Override
    public boolean continueExecuting() {

        return this.eatingGrassTimer > 0;
    }

    public int getEatingGrassTimer() {
        return this.eatingGrassTimer;
    }

    @Override
    public void updateTask() {
        this.eatingGrassTimer = Math.max(0, this.eatingGrassTimer - 1);

        if (this.eatingGrassTimer == 4) {
            BlockPos blockpos = new BlockPos(this.grassEaterEntity.posX, this.grassEaterEntity.posY, this.grassEaterEntity.posZ);

            if (EntityAICowEatGrass.IS_TALL_GRASS.apply(this.entityWorld.getBlockState(blockpos))) {

                if (this.grassEaterEntity instanceof EntityAnimaniaCow) {
                    EntityAnimaniaCow ech = (EntityAnimaniaCow) this.grassEaterEntity;
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

                    if (this.grassEaterEntity instanceof EntityAnimaniaCow) {
                        EntityAnimaniaCow ech = (EntityAnimaniaCow) this.grassEaterEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setFed(true);
                    }
                   
                    this.grassEaterEntity.eatGrassBonus();
                }
            }
        }
    }
}