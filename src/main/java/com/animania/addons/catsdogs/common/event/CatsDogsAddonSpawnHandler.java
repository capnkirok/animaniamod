package com.animania.addons.catsdogs.common.event;

import java.util.List;

import com.animania.addons.catsdogs.common.entity.canids.EntityAnimaniaDog;
import com.animania.addons.catsdogs.common.entity.felids.EntityAnimaniaCat;
import com.animania.addons.catsdogs.config.CatsDogsConfig;
import com.animania.common.helper.AnimaniaHelper;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.entity.living.LivingSpawnEvent.CheckSpawn;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CatsDogsAddonSpawnHandler
{
	@SubscribeEvent
	public static void removeSpawns(CheckSpawn event)
	{
		BlockPos pos = new BlockPos(event.getX(), event.getY(), event.getZ());
		World worldIn = event.getWorld();
		Biome biome = event.getWorld().getBiome(pos);
		int chooser = 0;

		EntityLiving replacementEntity = null;

		if (CatsDogsConfig.catsdogs.replaceVanillaWolves && (event.getEntity().getClass().equals(EntityWolf.class)) && !worldIn.isRemote)
		{
			event.setResult(Result.DENY);
		} else if (CatsDogsConfig.catsdogs.replaceVanillaOcelots && event.getEntity().getClass().equals(EntityOcelot.class) && !worldIn.isRemote)
		{
			event.setResult(Result.DENY);
		}
	}

	@SubscribeEvent
	public static void limitAnimaniaSpawns(CheckSpawn event)
	{
		BlockPos pos = new BlockPos(event.getX(), event.getY() + 1, event.getZ());
		World worldIn = event.getWorld();
		Biome biome = event.getWorld().getBiome(pos);

		if ((event.getEntity() instanceof EntityAnimaniaCat && !worldIn.isRemote))
		{
			List<EntityAnimaniaCat> others = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaCat.class, 100, event.getEntity().world, pos);
			if (others.size() > CatsDogsConfig.catsdogs.spawnLimitCats)
			{
				event.setResult(Result.DENY);
			}
		} else if ((event.getEntity() instanceof EntityAnimaniaDog && !worldIn.isRemote))
		{
			List<EntityAnimaniaDog> others = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaDog.class, 100, event.getEntity().world, pos);
			if (others.size() > CatsDogsConfig.catsdogs.spawnLimitDogs)
			{
				event.setResult(Result.DENY);
			}
		}
	}

}
