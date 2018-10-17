package com.animania.client.models;

public interface IColoredModel
{
	default void setWoolColor(float r, float g, float b)
	{
		
	}
	
	default void setColor(float r, float g, float b)
	{
		setWoolColor(r, g, b);
	}

}
