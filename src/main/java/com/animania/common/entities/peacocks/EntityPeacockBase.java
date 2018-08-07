package com.animania.common.entities.peacocks;

import com.animania.common.entities.EntityGender;
import com.animania.common.handler.ItemHandler;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityPeacockBase extends EntityAnimaniaPeacock
{

	
	
	public EntityPeacockBase(World worldIn)
	{
		super(worldIn);
		this.setSize(0.8F, 1.6F);
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
