package com.animania.common.entities.chickens;

import com.animania.common.AnimaniaAchievements;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityHenLeghorn extends EntityHenBase
{

	public EntityHenLeghorn(World worldIn)
	{
		super(worldIn);
		this.type = ChickenType.LEGHORN;
		this.resourceLocation = new ResourceLocation("animania:textures/entity/chickens/hen_white.png");
		this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/chickens/hen_white_blink.png");
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 15724527;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 14869218;
	}
	
	@Override
	protected void dropFewItems(boolean hit, int lootlevel) {

		int happyDrops = 0;

		if (this.getWatered())
			happyDrops++;
		if (this.getFed())
			happyDrops++;

		int j;
		int k;

		j = happyDrops + lootlevel;

		for (k = 0; k < j; ++k)
			if (this.isBurning()) {
				this.dropItem(Items.COOKED_CHICKEN, 1);
				this.dropItem(Items.FEATHER, 1);
			}
			else {
				this.dropItem(Items.CHICKEN, 1);
				this.dropItem(Items.FEATHER, 1);
			}
	}
	
}