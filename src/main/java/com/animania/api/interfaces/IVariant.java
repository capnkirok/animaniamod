package com.animania.api.interfaces;

public interface IVariant
{
	public int getVariant();

	public void setVariant(int i);
	
	default int getVariantCount()
	{
		return 0;
	}
	
	default int getEyeColorForVariant(int variant)
	{
		return 0;
	}
	
}

