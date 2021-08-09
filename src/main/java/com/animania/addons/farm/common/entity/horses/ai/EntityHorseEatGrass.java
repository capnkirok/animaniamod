package com.animania.addons.farm.common.entity.horses.ai;

import java.util.List;

import com.animania.addons.farm.common.entity.horses.EntityAnimaniaHorse;
import com.animania.addons.farm.common.entity.pullables.EntityCart;
import com.animania.addons.farm.common.entity.pullables.EntityWagon;
import com.animania.common.entities.generic.ai.GenericAIEatGrass;
import com.animania.common.helper.AnimaniaHelper;

public class HorseEntityEatGrass extends GenericAIEatGrass<EntityAnimaniaHorse>
{
	public HorseEntityEatGrass(EntityAnimaniaHorse grassEaterEntityIn)
	{
		super(grassEaterEntityIn);
	}

	@Override
	public boolean shouldExecute()
	{
		if (this.grassEaterEntity.isBeingRidden())
		{
			return false;
		}

		List carts = AnimaniaHelper.getEntitiesInRangeGeneric(EntityCart.class, 3, entityWorld, this.grassEaterEntity);
		if (!carts.isEmpty())
		{
			EntityCart cart = (EntityCart) carts.get(0);
			if (cart.pulled && cart.puller == this.grassEaterEntity)
			{
				return false;
			}
		}

		List wagons = AnimaniaHelper.getEntitiesInRangeGeneric(EntityWagon.class, 3, entityWorld, this.grassEaterEntity);
		if (!wagons.isEmpty())
		{
			EntityWagon wagon = (EntityWagon) wagons.get(0);
			if (wagon.pulled && wagon.puller == this.grassEaterEntity)
			{
				return false;
			}
		}

		return super.shouldExecute();
	}
}