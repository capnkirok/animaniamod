package com.animania.addons.farm.common.handler;

import java.util.List;
import java.util.Random;

import com.animania.addons.farm.client.model.horse.ModelDraftHorseFoal;
import com.animania.addons.farm.client.model.horse.ModelDraftHorseMare;
import com.animania.addons.farm.client.model.horse.ModelDraftHorseStallion;
import com.animania.addons.farm.client.model.pig.ModelPiglet;
import com.animania.addons.farm.client.model.pig.ModelPigletHampshire;
import com.animania.addons.farm.common.entity.chickens.ChickenType;
import com.animania.addons.farm.common.entity.chickens.EntityAnimaniaChicken;
import com.animania.addons.farm.common.entity.chickens.EntityChickBase;
import com.animania.addons.farm.common.entity.chickens.EntityRoosterBase;
import com.animania.addons.farm.common.entity.cows.CowEntityBase;
import com.animania.addons.farm.common.entity.cows.CowType;
import com.animania.addons.farm.common.entity.cows.EntityAnimaniaCow;
import com.animania.addons.farm.common.entity.cows.EntityBullBase;
import com.animania.addons.farm.common.entity.pigs.EntityAnimaniaPig;
import com.animania.addons.farm.common.entity.sheep.EntityAnimaniaSheep;
import com.animania.addons.farm.common.entity.sheep.SheepJacob.EntityEweJacob;
import com.animania.addons.farm.common.entity.sheep.SheepJacob.EntityLambJacob;
import com.animania.addons.farm.common.entity.sheep.SheepJacob.EntityRamJacob;
import com.animania.addons.farm.common.entity.sheep.SheepMerino.EntityRamMerino;
import com.animania.addons.farm.common.entity.sheep.SheepSuffolk.EntityRamSuffolk;
import com.animania.common.entities.generic.ai.GenericAINearestAttackableTarget;
import com.animania.common.entities.generic.ai.GenericAITargetNonTamed;
import com.animania.common.handler.AddonInjectionHandler;
import com.animania.common.handler.BlockHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.tileentities.TileEntityNest;
import com.animania.config.AnimaniaConfig;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.core.BlockPos;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.level.block.state.BlockState;

public class FarmAddonInjectionHandler
{
	public static String ID = "farm";

	public static void preInit()
	{
		// Mud block particles
		AddonInjectionHandler.addInjection(ID, "mudParticleDisplay", args -> {
			BlockState stateIn = (BlockState) args[0];
			Level levelIn = (Level) args[1];
			BlockPos pos = (BlockPos) args[2];
			Random rand = (Random) args[3];

			List<EntityAnimaniaPig> pigs = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaPig.class, 10, levelIn, pos);
			for (EntityAnimaniaPig pig : pigs)
			{
				boolean isMuddy;
				float splashTimer;

				splashTimer = pig.getSplashTimer();
				isMuddy = pig.getMuddy();

				if (isMuddy && splashTimer > 0.0)
				{
					double xt = pig.getX();
					double yt = pig.getY();
					double zt = pig.getZ();
					int x1 = pos.getX();
					int y1 = pos.getY();
					int z1 = pos.getZ();
					double x2 = xt - x1;
					double y2 = yt - y1;
					double z2 = zt - z1;

					if (Mth.abs((int) x2) < 1 && Mth.abs((int) z2) < 1 && Mth.abs((int) y2) < 1)
						for (int kk = 0; kk < 8; kk++)
							levelIn.spawnParticle(EnumParticleTypes.BLOCK_CRACK, pig.getX() + (rand.nextFloat() - 0.5D) * pig.width, pig.getEntityBoundingBox().minY + 0.5D, pig.getZ() + (rand.nextFloat() - 0.5D) * pig.width, 4.0D * (rand.nextFloat() - 0.5D), 0.5D, (rand.nextFloat() - 0.5D) * 4.0D, new int[] { Block.getStateId(stateIn) });
				}

			}

			return null;

		});

		// Nest chicken hatching
		AddonInjectionHandler.addInjection(ID, "nestHatchChickens", args -> {
			TileEntityNest te = (TileEntityNest) args[0];
			Level levelIn = (Level) args[1];
			BlockPos pos = (BlockPos) args[2];
			BlockState state = (BlockState) args[3];
			Random rand = (Random) args[4];

			if (te.getBirdType() instanceof ChickenType)
			{
				List<EntityRoosterBase> roosters = AnimaniaHelper.getEntitiesInRange(EntityRoosterBase.class, 3, levelIn, pos);
				List<EntityAnimaniaChicken> nearbyChickens = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaChicken.class, 15, levelIn, pos);
				if (nearbyChickens.size() < AnimaniaConfig.careAndFeeding.entityBreedingLimit)
				{
					ChickenType birdType = (ChickenType) te.getBirdType();
					for (EntityRoosterBase rooster : roosters)
					{
						if (rand.nextInt(AnimaniaConfig.careAndFeeding.eggHatchChance) < 1)
						{
							ChickenType chickType = ChickenType.breed(rooster.type, birdType);
							EntityChickBase chick = chickType.getChild(levelIn);
							chick.setPosition(pos.getX() + .5, pos.getY() + .2, pos.getZ() + .5);
							AnimaniaHelper.spawnEntity(levelIn, chick);
							chick.playSound(FarmAddonSoundHandler.chickenCluck1, 0.50F, 1.4F);
							te.removeItem();
							te.markDirty();
							break;
						}
					}
				}
			}

			return null;
		});

		// Pig mud test
		AddonInjectionHandler.addInjection(ID, "pigMudTest", args -> {
			LivingEntity entity = (LivingEntity) args[0];
			return Boolean.valueOf(entity instanceof EntityAnimaniaPig && entity.level.getBlockState(entity.getPosition().down()).getBlock() == BlockHandler.blockMud);
		});

		// Is cow
		AddonInjectionHandler.addInjection(ID, "isCow", args -> {
			LivingEntity creature = (LivingEntity) args[0];
			return Boolean.valueOf(creature instanceof CowEntityBase || creature instanceof EntityBullBase);
		});

		// Is mooshroom
		AddonInjectionHandler.addInjection(ID, "isMooshroom", args -> {
			LivingEntity grassEaterEntity = (LivingEntity) args[0];
			Block block = (Block) args[1];
			return Boolean.valueOf(grassEaterEntity instanceof EntityAnimaniaCow && ((EntityAnimaniaCow) grassEaterEntity).cowType == CowType.MOOSHROOM ? block == Blocks.MYCELIUM : false);
		});

		// Avoid chickens
		AddonInjectionHandler.addInjection(ID, "avoidChicken", args -> {
			EntityAITasks tasks = (EntityAITasks) args[0];
			CreatureEntity entity = (CreatureEntity) args[1];
			tasks.addTask(6, new AvoidEntityGoal<EntityAnimaniaChicken>(entity, EntityAnimaniaChicken.class, 10.0F, 3.0D, 3.5D));
			return null;
		});

		// Avoid Roosters
		AddonInjectionHandler.addInjection(ID, "avoidRooster", args -> {
			EntityAITasks tasks = (EntityAITasks) args[0];
			CreatureEntity entity = (CreatureEntity) args[1];
			tasks.addTask(6, new AvoidEntityGoal<EntityRoosterBase>(entity, EntityRoosterBase.class, 10.0F, 3.0D, 3.5D));
			return null;
		});

		// Attack chicks
		AddonInjectionHandler.addInjection(ID, "attackChicks", args -> {
			CreatureEntity entity = (CreatureEntity) args[0];
			if (entity instanceof TameableEntity)
				entity.targetTasks.addTask(2, new GenericAITargetNonTamed((TameableEntity) entity, AnimalEntity.class, false, target -> target instanceof EntityChickBase));
			else
				entity.targetTasks.addTask(2, new GenericAINearestAttackableTarget(entity, AnimalEntity.class, 10, false, false, target -> target instanceof EntityChickBase));

			return null;
		});

		// blink1
		AddonInjectionHandler.addInjection(ID, "blink1", args -> {
			RenderLiving render = (RenderLiving) args[0];
			LivingEntity entity = (LivingEntity) args[1];

			if (render.getMainModel() instanceof ModelPiglet || render.getMainModel() instanceof ModelPigletHampshire)
			{
				GlStateManager.translate(0.0, -0.038, 0.1499);
			}
			else
				GlStateManager.scale(1.0002, 1.0002, 1.0002);

			GlStateManager.pushMatrix();

			if (render.getMainModel() instanceof ModelDraftHorseStallion)
			{
				GlStateManager.translate(0.0, 0, 0.0001);
			}

			if (entity instanceof EntityAnimaniaSheep)
			{
				GlStateManager.translate(0.0, 0, 0.0001);
			}

			if (entity instanceof EntityRamSuffolk || entity instanceof EntityRamMerino || entity instanceof EntityRamJacob || entity instanceof EntityEweJacob || entity instanceof EntityLambJacob)
			{
				GlStateManager.translate(0.0, 0, -0.0001);
			}

			return null;
		});

		// blink2
		AddonInjectionHandler.addInjection(ID, "blink2", args -> {
			RenderLiving render = (RenderLiving) args[0];
			LivingEntity entity = (LivingEntity) args[1];

			if (render.getMainModel() instanceof ModelDraftHorseMare || render.getMainModel() instanceof ModelDraftHorseFoal)
			{
				GlStateManager.translate(0.0, 0, 0.0001);
			}

			if (entity instanceof EntityRamSuffolk || entity instanceof EntityRamMerino || entity instanceof EntityRamJacob || entity instanceof EntityEweJacob || entity instanceof EntityLambJacob)
			{
				GlStateManager.translate(0.0, 0, 0.0001);
			}

			return null;
		});
	}

}
