package com.animania.common.entities.amphibians;

import java.util.Random;

import com.animania.common.ModSoundEvents;

import net.minecraft.block.Block;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityToad extends EntityAmphibian {

	public EntityToad(World worldIn) {
		super(worldIn, true);
	}

	protected SoundEvent getAmbientSound()
	{

		Random rand = new Random();
		int chooser = rand.nextInt(5);

		if (chooser == 0) {
			return ModSoundEvents.toadLiving1;
		} else if (chooser == 1){
			return ModSoundEvents.toadLiving2;
		} else if (chooser == 2){
			return ModSoundEvents.toadLiving3;
		} else if (chooser == 3){
			return ModSoundEvents.toadLiving4;
		} else {
			return null;
		}
	}

	protected SoundEvent getHurtSound()
	{
		return null;
	}

	protected SoundEvent getDeathSound()
	{
		return null;
	}

	@Override
	public void playLivingSound()
	{
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null)
		{
			this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch() - (this.getAge() * 2));
		}
	}

	protected void playStepSound(BlockPos pos, Block blockIn)
	{
		this.playSound(SoundEvents.ENTITY_CHICKEN_STEP, 0.05F, 1.1F);
	}

	protected float getSoundVolume()
	{
		return 0.4F;
	}

}
