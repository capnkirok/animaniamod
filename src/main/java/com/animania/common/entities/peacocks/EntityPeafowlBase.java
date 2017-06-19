package com.animania.common.entities.peacocks;

import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityPeafowlBase extends EntityAnimaniaPeacock
{

	public EntityPeafowlBase(World worldIn)
	{
		super(worldIn);
		this.setSize(0.6F, 1.2F);
		
	}
	
	@Override
	public void playLivingSound()
	{
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null)
			this.playSound(soundevent, this.getSoundVolume() - .8F, this.getSoundPitch() + .2F);
	}
	
	@Override
	protected void dropFewItems(boolean hit, int lootlevel)
	{
		return;
	}

}
