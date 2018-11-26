package com.animania.api.interfaces;

import java.util.UUID;

import com.animania.common.entities.pigs.EntityPigletBase;

public interface IChild
{
	public UUID getParentUniqueId();
	
	public void setParentUniqueId(UUID id);
	
	public float getEntityAge();

	public void setEntityAge(float age);
	

}
