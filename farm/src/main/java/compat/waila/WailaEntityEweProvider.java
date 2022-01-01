package compat.waila;

import com.animania.compat.waila.provider.WailaAnimalEntityProviderMateable;
import common.entity.sheep.EntityEweBase;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaEntityAccessor;
import net.minecraft.client.resources.language.I18n;

import java.util.List;

public class WailaEntityEweProvider extends WailaAnimalEntityProviderMateable
{

	@Override
	public List<String> getWailaBody(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config)
	{
		currenttip = super.getWailaBody(entity, currenttip, accessor, config);
		if (accessor.getPlayer().isSneaking())
		{

			EntityEweBase thisEntity = (EntityEweBase) entity;

			if (thisEntity.getHasKids())
				currenttip.add(I18n.translateToLocal("text.waila.milkable"));

			if (thisEntity.getFertile() && !thisEntity.getPregnant())
			{
				currenttip.add(I18n.translateToLocal("text.waila.fertile1"));
			}

			if (thisEntity.getPregnant())
			{
				if (thisEntity.getGestation() > 1)
				{
					int bob = thisEntity.getGestation();
					currenttip.add(I18n.translateToLocal("text.waila.pregnant1") + " (" + bob + " " + I18n.translateToLocal("text.waila.pregnant2") + ")");
				}
				else
				{
					currenttip.add(I18n.translateToLocal("text.waila.pregnant1"));
				}
			}

			if (thisEntity.getSheared())
			{
				if (thisEntity.getWoolRegrowthTimer() > 0)
				{
					int bob = thisEntity.getWoolRegrowthTimer();
					currenttip.add(I18n.translateToLocal("text.waila.wool1") + " (" + bob + " " + I18n.translateToLocal("text.waila.wool2") + ")");
				}

			}
			else if (!thisEntity.getSheared())
			{
				currenttip.add(I18n.translateToLocal("text.waila.wool3"));
			}

			if (thisEntity.getSleeping())
			{
				currenttip.add(I18n.translateToLocal("text.waila.sleeping"));
			}

		}
		return currenttip;
	}

}
