package com.animania.common.entities.goats;

import com.animania.common.handler.ItemHandler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityDoeFainting extends EntityDoeBase
{

	public EntityDoeFainting(World worldIn)
	{
		super(worldIn);
		this.setSize(1.1F, 1.0F); 
		this.width = 1.1F;
		this.height = 1.0F;
		this.goatType = GoatType.FAINTING;

	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 1250067;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 14803425;
	}

	@Override
	protected void collideWithEntity(Entity entityIn)
	{

		if (entityIn instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityIn;
			if (player.isSprinting())
			{			
				this.setSpooked(true);
				this.setSpookedTimer(1.0F);
				
			}
			entityIn.applyEntityCollision(this);
		}
	}

	
}
