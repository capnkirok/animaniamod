package common.entity.rodents;

import net.minecraft.world.level.Level;

public class EntityFerretGrey extends EntityFerretBase
{

	public EntityFerretGrey(Level levelIn)
	{
		super(levelIn);
		this.type = FerretType.GREY;
	}

	@Override
	public int getPrimaryEggColor()
	{
		return 13948116;
	}

	@Override
	public int getSecondaryEggColor()
	{
		return 8741209;
	}

}