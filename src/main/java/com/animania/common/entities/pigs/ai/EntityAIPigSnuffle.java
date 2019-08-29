package com.animania.common.entities.pigs.ai;

import com.animania.common.entities.generic.ai.GenericAIEatGrass;
import com.animania.common.entities.pigs.EntityAnimaniaPig;
import com.animania.common.handler.BlockHandler;
import com.animania.common.handler.ItemHandler;
import com.animania.common.helper.ItemHelper;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

public class EntityAIPigSnuffle extends GenericAIEatGrass<EntityAnimaniaPig>
{
	public EntityAIPigSnuffle(EntityAnimaniaPig grassEaterEntityIn)
	{
		super(grassEaterEntityIn, false);
	}

	@Override
	public boolean shouldExecute()
	{

		Block poschk = this.grassEaterEntity.world.getBlockState(this.grassEaterEntity.getPosition().down()).getBlock();
		boolean isMuddy = false;
		if (poschk == BlockHandler.blockMud || poschk.getUnlocalizedName().equals("tile.mud"))
		{
			isMuddy = true;
		}
		if (isMuddy)
		{
			return false;
		}

		return super.shouldExecute();
	}

	@Override
	public void resetTask()
	{
		this.eatingGrassTimer = 0;
	}

	@Override
	public boolean shouldContinueExecuting()
	{
		return this.eatingGrassTimer > 0;
	}

	public int getEatingGrassTimer()
	{
		return this.eatingGrassTimer;
	}

	@Override
	public void updateTask()
	{
		super.updateTask();
		
		BlockPos blockpos1 = this.grassEaterEntity.getPosition().down();
		Block chkblock = this.entityWorld.getBlockState(blockpos1).getBlock();

		Biome biomegenbase = this.entityWorld.getBiome(blockpos1);

		if (BiomeDictionary.hasType(biomegenbase, Type.FOREST) && !this.grassEaterEntity.isChild() && this.grassEaterEntity.getLeashed() && this.grassEaterEntity.getLeashHolder() instanceof EntityPlayer)
		{
			this.entityWorld.playEvent(2001, blockpos1, Block.getIdFromBlock(chkblock));
			ItemHelper.spawnItem(entityWorld, blockpos1.up(), ItemHandler.truffle);
		}
	}
}
