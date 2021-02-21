package com.animania.addons.catsdogs.common.entity.canids;

import com.animania.common.entities.generic.ai.GenericAITargetNonTamed;
import com.animania.common.handler.AddonInjectionHandler;
import com.animania.config.AnimaniaConfig;

import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.world.World;

public class DogFox
{

	public static class EntityPuppyFox extends EntityPuppyBase
	{

		public EntityPuppyFox(World world)
		{
			super(world);
			this.type = DogType.FOX;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return -5415620;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return -13946603;
		}

		@Override
		protected void initAI()
		{
			super.initAI();

			if (AnimaniaConfig.gameRules.animalsCanAttackOthers && !this.isTamed())
			{
				this.targetTasks.addTask(4, new GenericAITargetNonTamed(this, EntityAnimal.class, false, (entity) -> entity instanceof EntityChicken));
				AddonInjectionHandler.runInjection("extra", "attackPeachicks", null, this);
				AddonInjectionHandler.runInjection("extra", "attackRodents", null, this);
			}
		}
	}

	public static class EntityMaleFox extends EntityMaleDogBase
	{

		public EntityMaleFox(World world)
		{
			super(world);
			this.type = DogType.FOX;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return -5415620;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return -13946603;
		}

		@Override
		protected void initAI()
		{
			super.initAI();

			if (AnimaniaConfig.gameRules.animalsCanAttackOthers && !this.isTamed())
			{
				this.targetTasks.addTask(4, new GenericAITargetNonTamed(this, EntityAnimal.class, false, (entity) -> entity instanceof EntityChicken));
				AddonInjectionHandler.runInjection("extra", "attackPeachicks", null, this);
				AddonInjectionHandler.runInjection("extra", "attackRodents", null, this);
			}
		}
	}

	public static class EntityFemaleFox extends EntityFemaleDogBase
	{

		public EntityFemaleFox(World world)
		{
			super(world);
			this.type = DogType.FOX;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return -5415620;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return -13946603;
		}

		@Override
		protected void initAI()
		{
			super.initAI();

			if (AnimaniaConfig.gameRules.animalsCanAttackOthers && !this.isTamed())
			{
				this.targetTasks.addTask(4, new GenericAITargetNonTamed(this, EntityAnimal.class, false, (entity) -> entity instanceof EntityChicken));
				AddonInjectionHandler.runInjection("extra", "attackPeachicks", null, this);
				AddonInjectionHandler.runInjection("extra", "attackRodents", null, this);
			}
		}
	}

}
