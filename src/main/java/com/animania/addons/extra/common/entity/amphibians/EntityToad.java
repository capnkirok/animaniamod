package com.animania.addons.extra.common.entity.amphibians;

import com.animania.Animania;
import com.animania.addons.extra.common.handler.ExtraAddonSoundHandler;
import com.animania.api.data.AnimalContainer;
import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.AnimaniaType;
import com.animania.common.items.ItemEntityEgg;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class EntityToad extends EntityAmphibian
{

	public EntityToad(Level levelIn)
	{
		super(levelIn, true);
	}

	@Override
	protected SoundEvent getAmbientSound()
	{

		int chooser = Animania.RANDOM.nextInt(5);

		switch (chooser)
		{
		case 0:
			return ExtraAddonSoundHandler.toadLiving1;
		case 1:
			return ExtraAddonSoundHandler.toadLiving2;
		case 2:
			return ExtraAddonSoundHandler.toadLiving3;
		case 3:
			return ExtraAddonSoundHandler.toadLiving4;
		default:
			return null;
		}
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source)
	{
		return null;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return null;
	}

	@Override
	protected ResourceLocation getLootTable()
	{
		return new ResourceLocation("extra/" + Animania.MODID, "toad");
	}

	@Override
	public void playLivingSound()
	{
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null)
			this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch() - this.getGrowingAge() * 2);
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn)
	{
		this.playSound(SoundEvents.ENTITY_CHICKEN_STEP, 0.05F, 1.1F);
	}

	@Override
	protected float getSoundVolume()
	{
		return 0.4F;
	}

	private ItemStack getItem(String moditem)
	{

		ItemStack foundStack = null;
		String item = "";
		String mod;
		int sepLoc;
		int metaLoc;
		boolean metaFlag = false;
		String metaVal = "";

		sepLoc = moditem.indexOf(":");
		metaLoc = moditem.indexOf("#");

		if (!moditem.contains(":"))
		{
			return new ItemStack(Blocks.AIR, 1);
		}

		mod = moditem.substring(0, sepLoc);

		if (metaLoc > 0)
		{
			item = moditem.substring(sepLoc + 1, metaLoc);
		}
		else
		{
			item = moditem.substring(sepLoc + 1);
		}
		if (metaLoc > 0)
		{
			metaFlag = true;
			metaVal = moditem.substring(metaLoc + 1);
		}

		Item bob = Item.getByNameOrId(item);

		if (bob != null)
		{

			if (metaFlag)
			{
				foundStack = new ItemStack(bob, 1, Integer.parseInt(metaVal));
			}
			else
			{
				foundStack = new ItemStack(bob, 1);
			}
		}
		else
		{
			foundStack = new ItemStack(Blocks.AIR, 1);
		}

		return foundStack;
	}

	@Override
	public Item getSpawnEgg()
	{
		return ItemEntityEgg.ANIMAL_EGGS.get(new AnimalContainer(AmphibianType.TOAD, EntityGender.NONE));
	}

	@Override
	public int getPrimaryEggColor()
	{
		return 13868916;
	}

	@Override
	public int getSecondaryEggColor()
	{
		return 5650205;
	}

	@Override
	public AnimaniaType getAnimalType()
	{
		return AmphibianType.TOAD;
	}

}
