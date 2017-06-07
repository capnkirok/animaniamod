package com.animania.common.tileentities.handler;

import cofh.api.energy.EnergyStorage;

public class EnergyHandlerHamsterWheel extends EnergyStorage
{
	public EnergyHandlerHamsterWheel()
	{
		super(100000, 0, 100000);
	}

	public void insert(int energy)
	{
		if (this.energy < this.capacity)
		{
			int toBe = this.energy + energy;
			if (toBe > this.capacity)
				this.energy = this.capacity;
			else
				this.energy += energy;
		}
	}

}
