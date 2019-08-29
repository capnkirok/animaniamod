package com.animania.common.entities.horses.ai;

import java.util.List;

import com.animania.common.entities.generic.ai.GenericAIEatGrass;
import com.animania.common.entities.horses.EntityAnimaniaHorse;
import com.animania.common.entities.props.EntityCart;
import com.animania.common.entities.props.EntityWagon;
import com.animania.common.helper.AnimaniaHelper;

public class EntityHorseEatGrass extends GenericAIEatGrass<EntityAnimaniaHorse>
{
	public EntityHorseEatGrass(EntityAnimaniaHorse grassEaterEntityIn)
	{
		super(grassEaterEntityIn);
	}

	public boolean shouldExecute()
	{
		if (this.grassEaterEntity.isBeingRidden()) {
			return false;
		}
		
		List carts = AnimaniaHelper.getCartsInRange(EntityCart.class, 3, entityWorld, this.grassEaterEntity);
		if (!carts.isEmpty()) {
			EntityCart cart = (EntityCart) carts.get(0);
			if (cart.pulled && cart.puller == this.grassEaterEntity) {
				return false;
			}
		}
		
		List wagons = AnimaniaHelper.getWagonsInRange(EntityWagon.class, 3, entityWorld, this.grassEaterEntity);
		if (!wagons.isEmpty()) {
			EntityWagon wagon = (EntityWagon) wagons.get(0);
			if (wagon.pulled && wagon.puller == this.grassEaterEntity) {
				return false;
			}
		}
			
		return super.shouldExecute();
	}
}