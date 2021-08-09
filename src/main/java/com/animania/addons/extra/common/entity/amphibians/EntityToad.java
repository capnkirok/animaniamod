package com.animania.addons.extra.common.entity.amphibians;

import com.animania.Animania;
import com.animania.addons.extra.common.handler.ExtraAddonSoundHandler;
import com.animania.api.data.AnimalContainer;
import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.AnimaniaType;
import com.animania.common.items.ItemEntityEgg;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityToad extends EntityAmphibian
{

	public EntityToad(World worldIn)
	{
		super(worldIn, true);
	}

	@Override
	protected SoundEvent getAmbientSound()
	{

		int chooser = Animania.RANDOM.nextInt(5);

		if (chooser == 0)
			return ExtraAddonSoundHandler.toadLiving1;
		else if (chooser == 1)
			return ExtraAddonSoundHandler.toadLiving2;
		else if (chooser == 2)
			return ExtraAddonSoundHandler.toadLiving3;
		else if (chooser == 3)
			return ExtraAddonSoundHandler.toadLiving4;
		else
			return null;
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
		String mod = "";
		int sepLoc = 0;
		int metaLoc = 0;
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
		} else
		{
			item = moditem.substring(sepLoc + 1, moditem.length());
		}
		if (metaLoc > 0)
		{
			metaFlag = true;
			metaVal = moditem.substring(metaLoc + 1, moditem.length());
		}

		Item bob = Item.getByNameOrId(item);

		if (bob != null)
		{

			if (metaFlag)
			{
				foundStack = new ItemStack(bob, 1, Integer.parseInt(metaVal));
			} else
			{
				foundStack = new ItemStack(bob, 1);
			}
		} else
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
