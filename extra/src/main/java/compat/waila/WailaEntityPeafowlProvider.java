package compat.waila;

import com.animania.compat.waila.provider.WailaAnimalEntityProviderBase;
import common.entity.peafowl.EntityPeafowlBase;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaEntityAccessor;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

import java.util.List;

public class WailaEntityPeafowlProvider extends WailaAnimalEntityProviderBase
{
	@Override
	public List<String> getWailaBody(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config)
	{
		currenttip = super.getWailaBody(entity, currenttip, accessor, config);

		EntityPeafowlBase ehb = (EntityPeafowlBase) entity;
		int timer = ehb.getLaidTimer();

		if (accessor.getPlayer().isSneaking())

			if (timer >= 0)
			{
				currenttip.add(I18n.translateToLocal("text.waila.egglay") + ": " + timer);
			}
			else
			{
				currenttip.add(I18n.translateToLocal("text.waila.egglay2"));
			}
		return currenttip;
	}

	@Override
	public CompoundTag getNBTData(ServerPlayer player, Entity ent, CompoundTag tag, Level level)
	{
		int laytime = ent.getEntityData().getInteger("EggLayTime");
		tag.putInt("EggLayTime", laytime);

		return super.getNBTData(player, ent, tag, level);
	}

}
