package com.animania.api.interfaces;

import java.util.UUID;

public interface IChild
{
	public UUID getParentUniqueId();
	
	public void setParentUniqueId(UUID id);
	
	public float getEntityAge();

	public void setEntityAge(float age);
	

}
