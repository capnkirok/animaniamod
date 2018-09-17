package com.animania.common.entities;

import net.minecraft.util.math.BlockPos;

public interface ISleeping
{
	public void setSleeping(boolean sleeping);
	
	public boolean getSleeping();
	
	public void setSleepingPos(BlockPos pos);
	
	public BlockPos getSleepingPos();
}
