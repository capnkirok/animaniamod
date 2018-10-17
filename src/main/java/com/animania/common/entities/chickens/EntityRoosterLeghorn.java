package com.animania.common.entities.chickens;

import com.animania.common.helper.AnimaniaHelper;
import com.animania.config.AnimaniaConfig;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityRoosterLeghorn extends EntityRoosterBase
{

	public EntityRoosterLeghorn(World worldIn)
	{
		super(worldIn);
		this.type = ChickenType.LEGHORN;
		this.resourceLocation = new ResourceLocation("animania:textures/entity/chickens/rooster_white.png");
		this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/chickens/chicken_blink.png");
		this.lidCol = 0xF2F2F2;
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

		ItemStack dropItem2;
		String drop2 = AnimaniaConfig.drops.chickenDrop2;
		dropItem2 = AnimaniaHelper.getItem(drop2);
		
		for (k = 0; k < j; ++k)
			if (this.isBurning()) {
				this.dropItem(Items.COOKED_CHICKEN, 1);
				this.dropItem(dropItem2.getItem(), AnimaniaConfig.drops.chickenDrop2Amount + lootlevel);
			}
			else {
				this.dropItem(Items.CHICKEN, 1);
				this.dropItem(dropItem2.getItem(), AnimaniaConfig.drops.chickenDrop2Amount + lootlevel);
			}
	}
}