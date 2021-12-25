package com.animania.common.helper;

import com.animania.Animania;

public class InvalidConfigException extends Exception
{

	public InvalidConfigException(String cause)
	{
		super(cause);
	}

	public void printException()
	{
		Animania.LOGGER.error(this.getMessage());
		for (int i = 0; i < this.getStackTrace().length; i++)
		{
			StackTraceElement element = this.getStackTrace()[i];
			Animania.LOGGER.error(element.toString());

			if (i >= 10)
			{
				Animania.LOGGER.error(this.getStackTrace().length - 10 + " more...");
				break;
			}
		}

		Animania.LOGGER.info("");
	}

}
