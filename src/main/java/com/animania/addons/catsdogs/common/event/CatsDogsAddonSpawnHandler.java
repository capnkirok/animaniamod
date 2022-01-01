package com.animania.addons.catsdogs.common.event;

import java.util.List;

import com.animania.addons.catsdogs.common.entity.canids.EntityAnimaniaDog;
import com.animania.addons.catsdogs.common.entity.felids.EntityAnimaniaCat;
import com.animania.addons.catsdogs.config.CatsDogsConfig;
import com.animania.common.helper.AnimaniaHelper;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.event.entity.living.LivingSpawnEvent.CheckSpawn;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CatsDogsAddonSpawnHandler
{
	@SubscribeEvent
	public static void removeSpawns(CheckSpawn event)
	{
		BlockPos pos = new BlockPos(event.getX(), event.getY(), event.getZ());
		Level levelIn = event.getEntity().getLevel();
		Biome biome = event.getEntity().getLevel().getBiome(pos);
		int chooser = 0;

		LivingEntity replacementEntity = null;

		if (CatsDogsConfig.catsdogs.replaceVanillaWolves && event.getEntity().getClass().equals(Wolf.class) && !levelIn.isClientSide)
		{
			event.setResult(Event.Result.DENY);
		}
		else if (CatsDogsConfig.catsdogs.replaceVanillaOcelots && event.getEntity().getClass().equals(Ocelot.class) && !levelIn.isClientSide)
		{
			event.setResult(Event.Result.DENY);
		}
	}

	@SubscribeEvent
	public static void limitAnimaniaSpawns(CheckSpawn event)
	{
		BlockPos pos = new BlockPos(event.getX(), event.getY() + 1, event.getZ());
		Level levelIn = event.getEntity().getLevel();
		Biome biome = event.getEntity().getLevel().getBiome(pos);

		if (event.getEntity() instanceof EntityAnimaniaCat && !levelIn.isClientSide)
		{
			List<EntityAnimaniaCat> others = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaCat.class, 100, event.getEntity().level, pos);
			if (others.size() > CatsDogsConfig.catsdogs.spawnLimitCats)
			{
				event.setResult(Event.Result.DENY);
			}
		}
		else if (event.getEntity() instanceof EntityAnimaniaDog && !levelIn.isClientSide)
		{
			List<EntityAnimaniaDog> others = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaDog.class, 100, event.getEntity().level, pos);
			if (others.size() > CatsDogsConfig.catsdogs.spawnLimitDogs)
			{
				event.setResult(Event.Result.DENY);
			}
		}
	}

}
