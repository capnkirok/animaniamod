package com.animania.common.entities.interfaces;

import java.util.UUID;

public interface IChild
{
	public UUID getParentUniqueId();
	
	public void setParentUniqueId(UUID id);

}
