package com.animania.addons.farm.compat.waila;

import java.util.List;
import java.util.UUID;

import com.animania.addons.farm.common.entity.goats.EntityDoeBase;
import com.animania.addons.farm.common.entity.goats.GoatAngora.EntityBuckAngora;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.compat.waila.provider.WailaAnimalEntityProviderBase;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaEntityAccessor;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.entity.player.ServerPlayer;
import net.minecraft.nbt.CompoundTag;

public class WailaEntityBuckProvider extends WailaAnimalEntityProviderBase
{

	@Override
	public List<String> getWailaBody(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config)
	{
		currenttip = super.getWailaBody(entity, currenttip, accessor, config);

		if (accessor.getPlayer().isSneaking())
		{
			String mate = accessor.getNBTData().getString("MateUUID");
			Level level = entity.level;

			if (!mate.equals(""))
			{
				for (Entity e : AnimaniaHelper.getEntitiesInRange(EntityDoeBase.class, 20, level, entity))
				{
					UUID id = e.getUUID();
					if (id.toString().equals(mate))
					{
						String name = e.getCustomNameTag();
						if (!name.equals(""))
							currenttip.add(I18n.translateToLocal("text.waila.mated") + " (" + name + ")");
						else
							currenttip.add(I18n.translateToLocal("text.waila.mated"));

						return currenttip;
					}
				}

				// currenttip.add(I18n.translateToLocal("text.waila.matemissing"));

			}

			if (entity instanceof EntityBuckAngora thisEntity)
			{

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
			}
		}
		return currenttip;

	}

	@Override
	public CompoundTag getNBTData(ServerPlayer player, Entity ent, CompoundTag tag, Level level)
	{
		CompoundTag comp = ent.getEntityData();

		String mate = comp.getString("MateUUID");
		if (!mate.equals(""))
			tag.setString("MateUUID", mate);

		return super.getNBTData(player, ent, tag, level);
	}

}
