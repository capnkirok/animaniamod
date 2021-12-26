package com.animania.addons.extra.common.handler;

import java.util.List;
import java.util.Random;

import com.animania.addons.extra.common.entity.amphibians.EntityAmphibian;
import com.animania.addons.extra.common.entity.amphibians.EntityFrogs;
import com.animania.addons.extra.common.entity.amphibians.EntityToad;
import com.animania.addons.extra.common.entity.peafowl.EntityAnimaniaPeacock;
import com.animania.addons.extra.common.entity.peafowl.EntityPeachickBase;
import com.animania.addons.extra.common.entity.peafowl.EntityPeacockBase;
import com.animania.addons.extra.common.entity.peafowl.PeacockType;
import com.animania.addons.extra.common.entity.rodents.EntityFerretBase;
import com.animania.addons.extra.common.entity.rodents.EntityFerretGrey;
import com.animania.addons.extra.common.entity.rodents.EntityFerretWhite;
import com.animania.addons.extra.common.entity.rodents.EntityHamster;
import com.animania.addons.extra.common.entity.rodents.EntityHedgehog;
import com.animania.addons.extra.common.entity.rodents.EntityHedgehogBase;
import com.animania.api.interfaces.IFoodEating;
import com.animania.common.entities.generic.ai.GenericAINearestAttackableTarget;
import com.animania.common.entities.generic.ai.GenericAITargetNonTamed;
import com.animania.common.handler.AddonInjectionHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.tileentities.BlockEntityNest;
import com.animania.config.AnimaniaConfig;

import net.minecraft.core.BlockPos;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickItem;

public class ExtraAddonInjectionHandler
{
	public static String ID = "extra";

	public static void preInit()
	{
		AddonInjectionHandler.addInjection(ID, "nestHatchPeafowl", args -> {

			BlockEntityNest te = (BlockEntityNest) args[0];
			Level levelIn = (Level) args[1];
			BlockPos pos = (BlockPos) args[2];
			BlockState state = (BlockState) args[3];
			Random rand = (Random) args[4];

			if (te.getBirdType() instanceof PeacockType)
			{
				List<EntityPeacockBase> peacocks = AnimaniaHelper.getEntitiesInRange(EntityPeacockBase.class, 3, levelIn, pos);
				List<EntityAnimaniaPeacock> nearbyPeacocks = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaPeacock.class, 15, levelIn, pos);
				if (nearbyPeacocks.size() < AnimaniaConfig.careAndFeeding.entityBreedingLimit)
				{
					PeacockType birdType = (PeacockType) te.getBirdType();
					for (EntityPeacockBase peacock : peacocks)
					{
						if (rand.nextInt(AnimaniaConfig.careAndFeeding.eggHatchChance) < 1)
						{
							PeacockType chickType = PeacockType.breed(peacock.type, birdType);
							EntityPeachickBase chick = chickType.getChild(levelIn);
							chick.setPosition(pos.getX() + .5, pos.getY() + .2, pos.getZ() + .5);
							AnimaniaHelper.spawnEntity(levelIn, chick);
							chick.playSound(ExtraAddonSoundHandler.peacock1, 0.50F, 1.4F);
							te.removeItem();
							te.markDirty();
							break;
						}
					}
				}
			}

			return null;
		});

		// Attack frogs
		AddonInjectionHandler.addInjection(ID, "attackFrogs", args -> {
			PathfinderMob entity = (PathfinderMob) args[0];
			if (entity instanceof TamableAnimal)
				entity.targetTasks.addTask(2, new GenericAITargetNonTamed((TameableEntity) entity, AnimalEntity.class, false, target -> target instanceof EntityFrogs || target instanceof EntityToad));
			else
				entity.targetTasks.addTask(2, new GenericAINearestAttackableTarget(entity, AnimalEntity.class, 80, false, false, target -> target instanceof EntityFrogs || target instanceof EntityToad));
			return null;
		});

		// Attack peachicks
		AddonInjectionHandler.addInjection(ID, "attackPeachicks", args -> {
			CreatureEntity entity = (CreatureEntity) args[0];
			if (entity instanceof TameableEntity)
				entity.targetTasks.addTask(2, new GenericAITargetNonTamed((TameableEntity) entity, AnimalEntity.class, false, target -> target instanceof EntityPeachickBase));
			else
				entity.targetTasks.addTask(2, new GenericAINearestAttackableTarget(entity, AnimalEntity.class, 80, false, false, target -> target instanceof EntityPeachickBase));
			return null;
		});

		// Attack rodents
		AddonInjectionHandler.addInjection(ID, "attackRodents", args -> {
			CreatureEntity entity = (CreatureEntity) args[0];
			if (entity instanceof TameableEntity)
				entity.targetTasks.addTask(2, new GenericAITargetNonTamed((TameableEntity) entity, AnimalEntity.class, false, target -> target instanceof EntityHamster || target instanceof EntityFerretBase || target instanceof EntityHedgehogBase));
			else
				entity.targetTasks.addTask(2, new GenericAINearestAttackableTarget(entity, AnimalEntity.class, 80, false, false, target -> target instanceof EntityHamster || target instanceof EntityFerretBase || target instanceof EntityHedgehogBase));
			return null;
		});

		// Rodent egg event
		AddonInjectionHandler.addInjection(ID, "rodentEggEvent", args -> {
			Entity entity = (Entity) args[0];
			int x2 = (int) args[1];
			int y2 = (int) args[2];
			int z2 = (int) args[3];
			RightClickItem event = (RightClickItem) args[4];
			if (entity != null && x2 <= 3 && y2 <= 2 && z2 <= 3 && (entity instanceof EntityFerretWhite || entity instanceof EntityFerretGrey || entity instanceof EntityHedgehog))
			{
				event.getPlayer().swingArm(event.getHand());
				event.isCanceled();
				event.setCanceled(true);
			}
			return null;
		});

		// Frog feeding
		AddonInjectionHandler.addInjection(ID, "eatFrogs", args -> {
			Entity entity = (Entity) args[0];
			IFoodEating animal = (IFoodEating) args[1];
			if (entity instanceof EntityAmphibian)
			{
				animal.setFed(true);
			}
			return null;
		});
	}
}
