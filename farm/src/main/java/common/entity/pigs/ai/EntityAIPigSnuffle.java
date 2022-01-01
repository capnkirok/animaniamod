package common.entity.pigs.ai;

import com.animania.Animania;
import com.animania.common.entities.generic.ai.GenericAIEatGrass;
import com.animania.common.handler.BlockHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.helper.ItemHelper;
import common.entity.pigs.EntityAnimaniaPig;
import common.handler.FarmAddonItemHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

import java.util.List;

public class PigSnuffleGoal extends GenericAIEatGrass<EntityAnimaniaPig>
{
	private boolean hasSpawned = false;
	private boolean hasEaten = false;

	public PigSnuffleGoal(EntityAnimaniaPig grassEaterEntityIn)
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
		Block chkblock = this.entityLevel.getBlockState(blockpos1).getBlock();

		Biome biomegenbase = this.entityLevel.getBiome(blockpos1);

		if (this.shouldMoveTo(this.level, blockpos1))
		{
			if (this.eatingGrassTimer > 80 && BiomeDictionary.hasType(biomegenbase, Type.FOREST) && !this.grassEaterEntity.isChild() && this.grassEaterEntity.getLeashed() && this.grassEaterEntity.getLeashHolder() instanceof Player)
			{
				this.entityLevel.playEvent(2001, blockpos1, Block.getIdFromBlock(chkblock));
				if (!this.hasSpawned)
				{
					ItemHelper.spawnItem(this.entityLevel, blockpos1.up(), FarmAddonItemHandler.truffle, Animania.RANDOM.nextInt(2) + 1);
					this.hasSpawned = true;
				}
			}

			if (this.eatingGrassTimer < 100 && !this.hasEaten)
			{
				List<EntityItem> items = AnimaniaHelper.getEntitiesInRangeGeneric(EntityItem.class, 3, this.level, this.grassEaterEntity);
				for (EntityItem ei : items)
				{
					if (ei.getItem().getItem() == FarmAddonItemHandler.truffle)
					{
						ei.getItem().shrink(64);
						this.grassEaterEntity.setFed(true);
						this.hasEaten = true;
					}
				}
			}
		}
		else
			this.resetTask();
	}
}
