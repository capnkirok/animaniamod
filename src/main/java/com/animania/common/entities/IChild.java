package com.animania.common.entities;

import java.util.UUID;

public interface IChild
{
	public UUID getParentUniqueId();
	
	public void setParentUniqueId(UUID id);

}
