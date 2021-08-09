package com.animania.addons.farm.compat.waila;

import java.util.List;
import java.util.UUID;

import com.animania.addons.farm.common.entity.goats.EntityBuckBase;
import com.animania.addons.farm.common.entity.sheep.EntityRamBase;
import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.IGendered;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.compat.waila.provider.WailaAnimalEntityProviderBase;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaEntityAccessor;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class WailaEntityRamProvider extends WailaAnimalEntityProviderBase
{

	@Override
	public List<String> getWailaBody(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config) {
		currenttip = super.getWailaBody(entity, currenttip, accessor, config);

		if (accessor.getPlayer().isSneaking()) {
			
			if (entity instanceof IGendered)
    		{
				IGendered igendered = (IGendered) entity;
    			if (igendered.getEntityGender() == EntityGender.MALE || igendered.getEntityGender() == EntityGender.FEMALE)
    				currenttip.add(igendered.getEntityGender() == EntityGender.MALE ? TextFormatting.AQUA + "\u2642" : TextFormatting.LIGHT_PURPLE + "\u2640");
    		}
			
			String mate = accessor.getNBTData().getString("MateUUID");
			World world = entity.level;

			if (!mate.equals("")) {
				for (Entity e : AnimaniaHelper.getEntitiesInRange(EntityBuckBase.class, 20, world, entity)) {
					UUID id = e.getUUID();
					if (id.toString().equals(mate)) {
						String name = e.getCustomNameTag();
						if (!name.equals(""))
							currenttip.add(I18n.translateToLocal("text.waila.mated") + " (" + name + ")");
						else
							currenttip.add(I18n.translateToLocal("text.waila.mated"));

						return currenttip;
					}
				}

				//currenttip.add(I18n.translateToLocal("text.waila.matemissing"));

			}

			if (entity instanceof EntityRamBase) {
				
				EntityRamBase thisEntity = (EntityRamBase)entity;
				if (thisEntity.getSheared()) {
					if (thisEntity.getWoolRegrowthTimer() > 0) {
						int bob = thisEntity.getWoolRegrowthTimer();
						currenttip.add(I18n.translateToLocal("text.waila.wool1") + " (" + bob + " " + I18n.translateToLocal("text.waila.wool2") + ")" );
					}

				} else if (!thisEntity.getSheared()) {
					currenttip.add(I18n.translateToLocal("text.waila.wool3"));
				}
			}

		}
		return currenttip;

	}

	@Override
	public CompoundNBT getNBTData(ServerPlayerEntity player, Entity ent, CompoundNBT tag, World world) {
		CompoundNBT comp = ent.getEntityData();

		String mate = comp.getString("MateUUID");
		if (!mate.equals(""))
			tag.setString("MateUUID", mate);

		return super.getNBTData(player, ent, tag, world);
	}

}
