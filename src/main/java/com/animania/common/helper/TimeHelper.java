package com.animania.common.helper;

public class TimeHelper
{

	public static final int SECOND = 20;
	public static final int MINUTE = SECOND * 60;
	public static final int HOUR = MINUTE * 60;
	public static final int DAY = HOUR * 24;

	public static String getTime(int ticks)
	{
		int days = (ticks - ticks % DAY) / DAY;
		ticks -= days * DAY;
		int hours = (ticks - ticks % HOUR) / HOUR;
		ticks -= hours * HOUR;
		int minutes = (ticks - ticks % MINUTE) / MINUTE;
		ticks -= minutes * MINUTE;
		int seconds = (ticks - ticks % SECOND) / SECOND;

		return (days > 0 ? days + " Day" + (days > 1 ? "s" : "") + (hours > 0 || minutes > 0 || seconds > 0 ? ", " : "") : "") + (hours > 0 ? hours + " Hour" + (hours > 1 ? "s" : "") + (minutes > 0 || seconds > 0 ? ", " : "") : "") + (minutes > 0 ? minutes + " Minute" + (minutes > 1 ? "s" : "") + (seconds > 0 ? ", " : "") : "") + (seconds > 0 ? seconds + " Second" + (seconds > 1 ? "s" : "") : "");
	}

}
