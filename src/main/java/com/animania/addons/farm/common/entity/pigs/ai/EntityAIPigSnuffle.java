package com.animania.addons.farm.common.entity.pigs.ai;

import java.util.List;

import com.animania.Animania;
import com.animania.addons.farm.common.entity.pigs.EntityAnimaniaPig;
import com.animania.addons.farm.common.handler.FarmAddonItemHandler;
import com.animania.common.entities.generic.ai.GenericAIEatGrass;
import com.animania.common.handler.BlockHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.helper.ItemHelper;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

public class EntityAIPigSnuffle extends GenericAIEatGrass<EntityAnimaniaPig>
{
	private boolean hasSpawned = false;
	private boolean hasEaten = false;

	public EntityAIPigSnuffle(EntityAnimaniaPig grassEaterEntityIn)
	{
		super(grassEaterEntityIn, false);
	}

	@Override
	public boolean shouldExecute()
	{

		Block poschk = this.grassEaterentity.level.getBlockState(this.grassEaterEntity.getPosition().down()).getBlock();
		if (poschk == BlockHandler.blockMud || poschk.getUnlocalizedName().equals("tile.mud") || this.grassEaterEntity.getSleeping() || this.grassEaterEntity.getFed())
		{
			return false;
		}

		if (Animania.RANDOM.nextInt(120) == 50)
			return true;

		return false;
	}

	@Override
	public void resetTask()
	{
		this.eatingGrassTimer = 0;
		this.hasSpawned = false;
		this.hasEaten = false;
		super.resetTask();
	}

	@Override
	public boolean shouldContinueExecuting()
	{
		return this.eatingGrassTimer > 0;
	}

	@Override
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

		if (this.shouldMoveTo(world, blockpos1))
		{
			if (this.eatingGrassTimer > 80 && BiomeDictionary.hasType(biomegenbase, Type.FOREST) && !this.grassEaterEntity.isChild() && this.grassEaterEntity.getLeashed() && this.grassEaterEntity.getLeashHolder() instanceof PlayerEntity)
			{
				this.entityWorld.playEvent(2001, blockpos1, Block.getIdFromBlock(chkblock));
				if (!hasSpawned)
				{
					ItemHelper.spawnItem(entityWorld, blockpos1.up(), FarmAddonItemHandler.truffle, Animania.RANDOM.nextInt(2) + 1);
					hasSpawned = true;
				}
			}

			if (this.eatingGrassTimer < 100 && !hasEaten)
			{
				List<EntityItem> items = AnimaniaHelper.getEntitiesInRangeGeneric(EntityItem.class, 3, world, this.grassEaterEntity);
				for (EntityItem ei : items)
				{
					if (ei.getItem().getItem() == FarmAddonItemHandler.truffle)
					{
						ei.getItem().shrink(64);
						this.grassEaterEntity.setFed(true);
						hasEaten = true;
					}
				}
			}
		} else
			this.resetTask();
	}
}
