package common.entity.sheep;

import com.animania.Animania;
import com.animania.addons.farm.common.entity.sheep.SheepDorper.EntityEweDorper;
import com.animania.addons.farm.common.entity.sheep.SheepDorper.EntityLambDorper;
import com.animania.addons.farm.common.entity.sheep.SheepDorper.EntityRamDorper;
import com.animania.addons.farm.common.entity.sheep.SheepDorset.EntityEweDorset;
import com.animania.addons.farm.common.entity.sheep.SheepDorset.EntityLambDorset;
import com.animania.addons.farm.common.entity.sheep.SheepDorset.EntityRamDorset;
import com.animania.addons.farm.common.entity.sheep.SheepFriesian.EntityEweFriesian;
import com.animania.addons.farm.common.entity.sheep.SheepFriesian.EntityLambFriesian;
import com.animania.addons.farm.common.entity.sheep.SheepFriesian.EntityRamFriesian;
import com.animania.addons.farm.common.entity.sheep.SheepJacob.EntityEweJacob;
import com.animania.addons.farm.common.entity.sheep.SheepJacob.EntityLambJacob;
import com.animania.addons.farm.common.entity.sheep.SheepJacob.EntityRamJacob;
import com.animania.addons.farm.common.entity.sheep.SheepMerino.EntityEweMerino;
import com.animania.addons.farm.common.entity.sheep.SheepMerino.EntityLambMerino;
import com.animania.addons.farm.common.entity.sheep.SheepMerino.EntityRamMerino;
import com.animania.addons.farm.common.entity.sheep.SheepSuffolk.EntityEweSuffolk;
import com.animania.addons.farm.common.entity.sheep.SheepSuffolk.EntityLambSuffolk;
import com.animania.addons.farm.common.entity.sheep.SheepSuffolk.EntityRamSuffolk;
import com.animania.api.interfaces.AnimaniaType;
import net.minecraft.stats.Stat;
import net.minecraft.world.level.Level;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public enum SheepType implements AnimaniaType
{
	DORPER(EntityRamDorper.class, EntityEweDorper.class, EntityLambDorper.class, null, true), DORSET(EntityRamDorset.class, EntityEweDorset.class, EntityLambDorset.class, null, true), FRIESIAN(EntityRamFriesian.class, EntityEweFriesian.class, EntityLambFriesian.class, null, false), JACOB(EntityRamJacob.class, EntityEweJacob.class, EntityLambJacob.class, null, false), MERINO(EntityRamMerino.class, EntityEweMerino.class, EntityLambMerino.class, null, false), SUFFOLK(EntityRamSuffolk.class, EntityEweSuffolk.class, EntityLambSuffolk.class, null, true);

	private Class<?> male;
	private Class<?> female;
	private Class<?> child;
	private Stat<?> achievement;
	public boolean isPrime;

	SheepType(Class<?> male, Class<?> female, Class<?> child, Stat<?> achievement, boolean prime)
	{
		this.male = male;
		this.female = female;
		this.child = child;
		this.achievement = achievement;
		this.isPrime = prime;
	}

	@Override
	public EntityRamBase getMale(Level level)
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
		EntityRamBase male = null;
		try
		{
			male = (EntityRamBase) constructor.newInstance(level);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return male;
	}

	@Override
	public EntityEweBase getFemale(Level level)
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
		EntityEweBase female = null;
		try
		{
			female = (EntityEweBase) constructor.newInstance(level);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return female;
	}

	@Override
	public EntityLambBase getChild(Level level)
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
		EntityLambBase child = null;
		try
		{
			child = (EntityLambBase) constructor.newInstance(level);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return child;
	}

	public static SheepType breed(SheepType male, SheepType female)
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
		return Animania.MODID + ":sheep";
	}

}
