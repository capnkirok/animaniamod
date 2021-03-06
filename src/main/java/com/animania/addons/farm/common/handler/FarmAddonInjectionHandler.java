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
import com.animania.addons.farm.common.entity.cows.CowType;
import com.animania.addons.farm.common.entity.cows.EntityAnimaniaCow;
import com.animania.addons.farm.common.entity.cows.EntityBullBase;
import com.animania.addons.farm.common.entity.cows.EntityCowBase;
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

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class FarmAddonInjectionHandler
{
	public static String ID = "farm";

	public static void preInit()
	{
		// Mud block particles
		AddonInjectionHandler.addInjection(ID, "mudParticleDisplay", args -> {
			IBlockState stateIn = (IBlockState) args[0];
			World worldIn = (World) args[1];
			BlockPos pos = (BlockPos) args[2];
			Random rand = (Random) args[3];

			List<EntityAnimaniaPig> pigs = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaPig.class, 10, worldIn, pos);
			for (EntityAnimaniaPig pig : pigs)
			{
				boolean isMuddy = false;
				float splashTimer = 0.0F;

				splashTimer = pig.getSplashTimer();
				isMuddy = pig.getMuddy();

				if (isMuddy && splashTimer > 0.0)
				{
					double xt = pig.posX;
					double yt = pig.posY;
					double zt = pig.posZ;
					int x1 = pos.getX();
					int y1 = pos.getY();
					int z1 = pos.getZ();
					double x2 = xt - x1;
					double y2 = yt - y1;
					double z2 = zt - z1;

					if (MathHelper.abs((int) x2) < 1 && MathHelper.abs((int) z2) < 1 && MathHelper.abs((int) y2) < 1)
						for (int kk = 0; kk < 8; kk++)
							worldIn.spawnParticle(EnumParticleTypes.BLOCK_CRACK, pig.posX + (rand.nextFloat() - 0.5D) * pig.width, pig.getEntityBoundingBox().minY + 0.5D, pig.posZ + (rand.nextFloat() - 0.5D) * pig.width, 4.0D * (rand.nextFloat() - 0.5D), 0.5D, (rand.nextFloat() - 0.5D) * 4.0D, new int[] { Block.getStateId(stateIn) });
				}

			}

			return null;

		});

		// Nest chicken hatching
		AddonInjectionHandler.addInjection(ID, "nestHatchChickens", args -> {
			TileEntityNest te = (TileEntityNest) args[0];
			World worldIn = (World) args[1];
			BlockPos pos = (BlockPos) args[2];
			IBlockState state = (IBlockState) args[3];
			Random rand = (Random) args[4];

			if (te.getBirdType() instanceof ChickenType)
			{
				List<EntityRoosterBase> roosters = AnimaniaHelper.getEntitiesInRange(EntityRoosterBase.class, 3, worldIn, pos);
				List<EntityAnimaniaChicken> nearbyChickens = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaChicken.class, 15, worldIn, pos);
				if (nearbyChickens.size() < AnimaniaConfig.careAndFeeding.entityBreedingLimit)
				{
					ChickenType birdType = (ChickenType) te.getBirdType();
					for (EntityRoosterBase rooster : roosters)
					{
						if (rand.nextInt(AnimaniaConfig.careAndFeeding.eggHatchChance) < 1)
						{
							ChickenType chickType = ChickenType.breed(rooster.type, birdType);
							EntityChickBase chick = chickType.getChild(worldIn);
							chick.setPosition(pos.getX() + .5, pos.getY() + .2, pos.getZ() + .5);
							AnimaniaHelper.spawnEntity(worldIn, chick);
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
			EntityLivingBase entity = (EntityLivingBase) args[0];
			return Boolean.valueOf((entity instanceof EntityAnimaniaPig && entity.world.getBlockState(entity.getPosition().down()).getBlock() == BlockHandler.blockMud));
		});

		// Is cow
		AddonInjectionHandler.addInjection(ID, "isCow", args -> {
			EntityLivingBase creature = (EntityLivingBase) args[0];
			return Boolean.valueOf(creature instanceof EntityCowBase || creature instanceof EntityBullBase);
		});

		// Is mooshroom
		AddonInjectionHandler.addInjection(ID, "isMooshroom", args -> {
			EntityLivingBase grassEaterEntity = (EntityLivingBase) args[0];
			Block block = (Block) args[1];
			return Boolean.valueOf(((grassEaterEntity instanceof EntityAnimaniaCow && ((EntityAnimaniaCow) grassEaterEntity).cowType == CowType.MOOSHROOM) ? block == Blocks.MYCELIUM : false));
		});

		// Avoid chickens
		AddonInjectionHandler.addInjection(ID, "avoidChicken", args -> {
			EntityAITasks tasks = (EntityAITasks) args[0];
			EntityCreature entity = (EntityCreature) args[1];
			tasks.addTask(6, new EntityAIAvoidEntity<EntityAnimaniaChicken>(entity, EntityAnimaniaChicken.class, 10.0F, 3.0D, 3.5D));
			return null;
		});

		// Avoid Roosters
		AddonInjectionHandler.addInjection(ID, "avoidRooster", args -> {
			EntityAITasks tasks = (EntityAITasks) args[0];
			EntityCreature entity = (EntityCreature) args[1];
			tasks.addTask(6, new EntityAIAvoidEntity<EntityRoosterBase>(entity, EntityRoosterBase.class, 10.0F, 3.0D, 3.5D));
			return null;
		});

		// Attack chicks
		AddonInjectionHandler.addInjection(ID, "attackChicks", args -> {
			EntityCreature entity = (EntityCreature) args[0];
			if (entity instanceof EntityTameable)
				entity.targetTasks.addTask(2, new GenericAITargetNonTamed((EntityTameable) entity, EntityAnimal.class, false, target -> target instanceof EntityChickBase));
			else
				entity.targetTasks.addTask(2, new GenericAINearestAttackableTarget(entity, EntityAnimal.class, 10, false, false, target -> target instanceof EntityChickBase));

			return null;
		});

		// blink1
		AddonInjectionHandler.addInjection(ID, "blink1", args -> {
			RenderLiving render = (RenderLiving) args[0];
			EntityLivingBase entity = (EntityLivingBase) args[1];

			if (render.getMainModel() instanceof ModelPiglet || render.getMainModel() instanceof ModelPigletHampshire)
			{
				GlStateManager.translate(0.0, -0.038, 0.1499);
			} else
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
			EntityLivingBase entity = (EntityLivingBase) args[1];

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
