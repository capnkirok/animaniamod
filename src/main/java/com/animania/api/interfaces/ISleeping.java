package com.animania.api.interfaces;

import net.minecraft.util.math.BlockPos;

public interface ISleeping
{
	public void setSleeping(boolean sleeping);
	
	public boolean getSleeping();
	
	public void setSleepingPos(BlockPos pos);
	
	public BlockPos getSleepingPos();
	
	public Float getSleepTimer();
	
	public void setSleepTimer(Float timer);

}
