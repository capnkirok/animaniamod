package com.animania.addons.extra.common.entity.rodents.rabbits;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.animania.Animania;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitChinchilla.RabbitEntityBuckChinchilla;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitChinchilla.RabbitEntityDoeChinchilla;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitChinchilla.RabbitEntityKitChinchilla;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitCottonail.RabbitEntityBuckCottontail;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitCottonail.RabbitEntityDoeCottontail;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitCottonail.RabbitEntityKitCottontail;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitDutch.RabbitEntityBuckDutch;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitDutch.RabbitEntityDoeDutch;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitDutch.RabbitEntityKitDutch;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitHavana.RabbitEntityBuckHavana;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitHavana.RabbitEntityDoeHavana;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitHavana.RabbitEntityKitHavana;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitJack.RabbitEntityBuckJack;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitJack.RabbitEntityDoeJack;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitJack.RabbitEntityKitJack;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitLop.RabbitEntityBuckLop;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitLop.RabbitEntityDoeLop;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitLop.RabbitEntityKitLop;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitNewZealand.RabbitEntityBuckNewZealand;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitNewZealand.RabbitEntityDoeNewZealand;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitNewZealand.RabbitEntityKitNewZealand;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitRex.RabbitEntityBuckRex;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitRex.RabbitEntityDoeRex;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitRex.RabbitEntityKitRex;
import com.animania.api.interfaces.AnimaniaType;

import net.minecraft.stats.Stat;
import net.minecraft.world.level.Level;

public enum RabbitType implements AnimaniaType
{

	LOP(RabbitEntityBuckLop.class, RabbitEntityDoeLop.class, RabbitEntityKitLop.class, null, false), REX(RabbitEntityBuckRex.class, RabbitEntityDoeRex.class, RabbitEntityKitRex.class, null, true), DUTCH(RabbitEntityBuckDutch.class, RabbitEntityDoeDutch.class, RabbitEntityKitDutch.class, null, false), HAVANA(RabbitEntityBuckHavana.class, RabbitEntityDoeHavana.class, RabbitEntityKitHavana.class, null, false), NEW_ZEALAND(RabbitEntityBuckNewZealand.class, RabbitEntityDoeNewZealand.class, RabbitEntityKitNewZealand.class, null, true), JACK(RabbitEntityBuckJack.class, RabbitEntityDoeJack.class, RabbitEntityKitJack.class, null, false), COTTONTAIL(RabbitEntityBuckCottontail.class, RabbitEntityDoeCottontail.class, RabbitEntityKitCottontail.class, null, false), CHINCHILLA(RabbitEntityBuckChinchilla.class, RabbitEntityDoeChinchilla.class, RabbitEntityKitChinchilla.class, null, true);

	private Class<?> male;
	private Class<?> female;
	private Class<?> child;
	private Stat<?> achievement;
	public boolean isPrime;

	RabbitType(Class<?> male, Class<?> female, Class<?> child, Stat<?> achievement, boolean prime)
	{
		this.male = male;
		this.female = female;
		this.child = child;
		this.achievement = achievement;
		this.isPrime = prime;
	}

	@Override
	public RabbitEntityBuckBase getMale(Level level)
	{
		Constructor<?> constructor = null;
		try
		{
			constructor = this.male.getConstructor(Level.class);
		}
		catch (NoSuchMethodException | SecurityException e)
		{
			e.printStackTrace();
		}
		RabbitEntityBuckBase male = null;
		try
		{
			male = (RabbitEntityBuckBase) constructor.newInstance(level);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return male;
	}

	@Override
	public RabbitEntityDoeBase getFemale(Level level)
	{
		Constructor<?> constructor = null;
		try
		{
			constructor = this.female.getConstructor(Level.class);
		}
		catch (NoSuchMethodException | SecurityException e)
		{
			e.printStackTrace();
		}
		RabbitEntityDoeBase female = null;
		try
		{
			female = (RabbitEntityDoeBase) constructor.newInstance(level);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return female;
	}

	@Override
	public RabbitEntityKitBase getChild(Level level)
	{
		Constructor<?> constructor = null;
		try
		{
			constructor = this.child.getConstructor(Level.class);
		}
		catch (NoSuchMethodException | SecurityException e)
		{
			e.printStackTrace();
		}
		RabbitEntityKitBase child = null;
		try
		{
			child = (RabbitEntityKitBase) constructor.newInstance(level);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return child;
	}

	public static RabbitType breed(RabbitType male, RabbitType female)
	{
		return Animania.RANDOM.nextBoolean() ? male : female;
	}

	public Stat<?> getAchievement()
	{
		return this.achievement;
	}

	@Override
	public String getTypeName()
	{
		return Animania.MODID + ":rabbit";
	}

}
