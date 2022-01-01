package common.entity.rodents;

import com.animania.Animania;
import com.animania.api.interfaces.AnimaniaType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public enum HamsterType implements AnimaniaType
{
	STANDARD(EntityHamster.class);

	private Class male;

	private HamsterType(Class male)
	{
		this.male = male;
	}

	@Override
	public EntityHamster getMale(Level level)
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
		EntityHamster hamster = null;
		try
		{
			hamster = (EntityHamster) constructor.newInstance(level);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return hamster;
	}

	@Override
	public LivingEntity getFemale(Level level)
	{
		return null;
	}

	@Override
	public LivingEntity getChild(Level level)
	{
		return null;
	}

	@Override
	public String getTypeName()
	{
		return Animania.MODID + ":hamster";
	}
}
