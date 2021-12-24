package com.animania.addons.extra.common.entity.peafowl;

import com.animania.api.data.EntityGender;

public class EntityPeacockBase extends EntityAnimaniaPeacock
{

	
	
	public EntityPeacockBase(Level levelIn)
	{
		super(levelIn);
		this.setSize(0.8F, 1.6F); 
		this.width = 0.8F;
		this.height = 1.6F;
		this.gender = EntityGender.MALE;
		
	}
	
	@Override
	public void playLivingSound()
	{
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null && !this.getSleeping())
			this.playSound(soundevent, this.getSoundVolume() - .8F, this.getSoundPitch());
	}
	
	
	
}
