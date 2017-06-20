package com.animania.common.entities.peacocks;

import java.util.Random;
import java.util.Set;

import com.animania.common.ModSoundEvents;
import com.google.common.collect.Sets;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityPeacockBase extends EntityAnimaniaPeacock
{

	public EntityPeacockBase(World worldIn)
	{
		super(worldIn);
		this.setSize(0.8F, 1.6F);

	}
	

	@Override
	public void playLivingSound()
	{
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null)
			this.playSound(soundevent, this.getSoundVolume() - .8F, this.getSoundPitch());
	}
	
	
	
}
