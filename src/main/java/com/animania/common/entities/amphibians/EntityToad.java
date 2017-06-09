package com.animania.common.entities.amphibians;

import java.util.Random;

import com.animania.common.ModSoundEvents;
import com.animania.config.AnimaniaConfig;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityToad extends EntityAmphibian
{

	public EntityToad(World worldIn) {
		super(worldIn, true);
	}

	@Override
	protected SoundEvent getAmbientSound() {

		Random rand = new Random();
		int chooser = rand.nextInt(5);

		if (chooser == 0)
			return ModSoundEvents.toadLiving1;
		else if (chooser == 1)
			return ModSoundEvents.toadLiving2;
		else if (chooser == 2)
			return ModSoundEvents.toadLiving3;
		else if (chooser == 3)
			return ModSoundEvents.toadLiving4;
		else
			return null;
	}

	@Override
	protected SoundEvent getHurtSound() {
		return null;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return null;
	}

	@Override
	public void playLivingSound() {
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null)
			this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch() - this.getAge() * 2);
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn) {
		this.playSound(SoundEvents.ENTITY_CHICKEN_STEP, 0.05F, 1.1F);
	}

	@Override
	protected float getSoundVolume() {
		return 0.4F;
	}

	@Override
	protected void dropFewItems(boolean hit, int lootlevel) {

		ItemStack dropItem;
		String drop = AnimaniaConfig.drops.toadDrop;
		dropItem = getItem(drop);
		dropItem.setCount(1 + lootlevel);
		EntityItem entityitem = new EntityItem(this.world, this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D, dropItem);
		world.spawnEntity(entityitem);

	}

	private ItemStack getItem(String moditem) {

		ItemStack foundStack = null;
		String item = "";
		String mod = "";
		int sepLoc = 0;
		int metaLoc = 0;
		boolean metaFlag = false;
		String metaVal = "";

		sepLoc = moditem.indexOf(":");
		metaLoc = moditem.indexOf("#");

		if (!moditem.contains(":")) {
			return new ItemStack(Blocks.AIR, 1);
		}

		mod = moditem.substring(0, sepLoc);

		if (metaLoc > 0) {
			item = moditem.substring(sepLoc+1, metaLoc);
		} else {
			item = moditem.substring(sepLoc+1, moditem.length());
		}
		if (metaLoc > 0) {
			metaFlag = true;
			metaVal = moditem.substring(metaLoc+1, moditem.length());
		}

		Item bob = Item.getByNameOrId(item);

		if (bob != null) {

			if (metaFlag) {
				foundStack = new ItemStack(bob, 1, Integer.parseInt(metaVal));
			} else {
				foundStack = new ItemStack(bob, 1);
			}
		} else {
			foundStack = new ItemStack(Blocks.AIR, 1);
		}

		return foundStack;
	}

}
