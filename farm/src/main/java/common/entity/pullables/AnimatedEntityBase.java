package common.entity.pullables;

import com.animania.client.handler.AnimationHandler;
import com.leviathanstudio.craftstudio.CraftStudioApi;
import com.leviathanstudio.craftstudio.common.animation.IAnimated;
import com.leviathanstudio.craftstudio.common.animation.simpleImpl.AnimatedEntity;
import net.minecraft.world.level.Level;

public abstract class AnimatedEntityBase extends Entity implements IAnimated
{
	/** The animation handler of this type of entity. */
	// It should be different for every entity class, unless child classes have
	// the same models.
	// You should declare a new one in your extended classes.
	protected static AnimationHandler animHandler = CraftStudioApi.getNewAnimationHandler(AnimatedEntity.class);

	// Here you should add all the needed animations in the animationHandler.
	static
	{
		// AnimatedEntity.animHandler.addAnim("yourModId", "yourAnimation",
		// "yourModel",
		// false);
	}

	/** The constructor of the entity. */
	public AnimatedEntityBase(Level levelIn)
	{
		super(levelIn);
	}

	// You must call super.onLivingUpdate(), in your entity, or call the
	// animationsUpdate() method like here.
	@Override
	public void onEntityUpdate()
	{
		super.onEntityUpdate();
		this.getAnimationHandler().animationsUpdate(this);
	}

	@Override
	public <T extends IAnimated> AnimationHandler<T> getAnimationHandler()
	{
		// Be careful to return the right animation handler.
		return AnimatedEntityBase.animHandler;
	}

	@Override
	public int getDimension()
	{
		return this.dimension;
	}

	@Override
	public double getX()
	{
		return this.getX();
	}

	@Override
	public double getY()
	{
		return this.getY();
	}

	@Override
	public double getZ()
	{
		return this.getZ();
	}

	@Override
	public boolean isLevelRemote()
	{
		return this.level.isClientSide;
	}
}
