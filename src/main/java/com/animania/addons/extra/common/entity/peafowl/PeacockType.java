package com.animania.addons.extra.common.entity.peafowl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.animania.Animania;
import com.animania.addons.extra.common.entity.peafowl.PeafowlBlue.EntityPeachickBlue;
import com.animania.addons.extra.common.entity.peafowl.PeafowlBlue.EntityPeacockBlue;
import com.animania.addons.extra.common.entity.peafowl.PeafowlBlue.EntityPeafowlBlue;
import com.animania.addons.extra.common.entity.peafowl.PeafowlCharcoal.EntityPeachickCharcoal;
import com.animania.addons.extra.common.entity.peafowl.PeafowlCharcoal.EntityPeacockCharcoal;
import com.animania.addons.extra.common.entity.peafowl.PeafowlCharcoal.EntityPeafowlCharcoal;
import com.animania.addons.extra.common.entity.peafowl.PeafowlOpal.EntityPeachickOpal;
import com.animania.addons.extra.common.entity.peafowl.PeafowlOpal.EntityPeacockOpal;
import com.animania.addons.extra.common.entity.peafowl.PeafowlOpal.EntityPeafowlOpal;
import com.animania.addons.extra.common.entity.peafowl.PeafowlPeach.EntityPeachickPeach;
import com.animania.addons.extra.common.entity.peafowl.PeafowlPeach.EntityPeacockPeach;
import com.animania.addons.extra.common.entity.peafowl.PeafowlPeach.EntityPeafowlPeach;
import com.animania.addons.extra.common.entity.peafowl.PeafowlPurple.EntityPeachickPurple;
import com.animania.addons.extra.common.entity.peafowl.PeafowlPurple.EntityPeacockPurple;
import com.animania.addons.extra.common.entity.peafowl.PeafowlPurple.EntityPeafowlPurple;
import com.animania.addons.extra.common.entity.peafowl.PeafowlTaupe.EntityPeachickTaupe;
import com.animania.addons.extra.common.entity.peafowl.PeafowlTaupe.EntityPeacockTaupe;
import com.animania.addons.extra.common.entity.peafowl.PeafowlTaupe.EntityPeafowlTaupe;
import com.animania.addons.extra.common.entity.peafowl.PeafowlWhite.EntityPeachickWhite;
import com.animania.addons.extra.common.entity.peafowl.PeafowlWhite.EntityPeacockWhite;
import com.animania.addons.extra.common.entity.peafowl.PeafowlWhite.EntityPeafowlWhite;
import com.animania.api.interfaces.AnimaniaType;

import net.minecraft.world.level.Level;

public enum PeacockType implements AnimaniaType
{
	BLUE(EntityPeacockBlue.class, EntityPeafowlBlue.class, EntityPeachickBlue.class),
	WHITE(EntityPeacockWhite.class, EntityPeafowlWhite.class, EntityPeachickWhite.class),
	TAUPE(EntityPeacockTaupe.class, EntityPeafowlTaupe.class, EntityPeachickTaupe.class),
	PURPLE(EntityPeacockPurple.class, EntityPeafowlPurple.class, EntityPeachickPurple.class),
	PEACH(EntityPeacockPeach.class, EntityPeafowlPeach.class, EntityPeachickPeach.class),
	OPAL(EntityPeacockOpal.class, EntityPeafowlOpal.class, EntityPeachickOpal.class),
	CHARCOAL(EntityPeacockCharcoal.class, EntityPeafowlCharcoal.class, EntityPeachickCharcoal.class);
	
	private Class male;
	private Class female;
	private Class child;
	
	private PeacockType(Class male, Class female, Class child)
	{
		this.male = male;
		this.female = female;
		this.child = child;
	}
	
	@Override
	public EntityPeacockBase getMale(Level world)
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
		EntityPeacockBase male = null;
		try
		{
			male = (EntityPeacockBase) constructor.newInstance(world);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return male;
	}

	@Override
	public EntityPeafowlBase getFemale(Level world)
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
		EntityPeafowlBase female = null;
		try
		{
			female = (EntityPeafowlBase) constructor.newInstance(world);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return female;	
	}

	@Override
	public EntityPeachickBase getChild(Level world)
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
		EntityPeachickBase child = null;
		try
		{
			child = (EntityPeachickBase) constructor.newInstance(world);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return child;	
	}

	public static PeacockType breed(PeacockType male, PeacockType female)
	{
		return Animania.RANDOM.nextBoolean() ? male : female;
	}
	
	@Override
	public String getTypeName()
	{
		return Animania.MODID + ":peacock";
	}

}
