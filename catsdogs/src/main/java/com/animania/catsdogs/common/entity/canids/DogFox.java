package com.animania.catsdogs.common.entity.canids;

import com.animania.common.entities.generic.ai.GenericAITargetNonTamed;
import com.animania.common.handler.AddonInjectionHandler;
import com.animania.config.AnimaniaConfig;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.world.level.Level;

public class DogFox
{

	public static class EntityPuppyFox extends EntityPuppyBase
	{

		public EntityPuppyFox(Level level)
		{
			super(level);
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
				this.targetSelector.addTask(4, new GenericAITargetNonTamed(this, AnimalEntity.class, false, entity -> entity instanceof ChickenEntity));
				AddonInjectionHandler.runInjection("extra", "attackPeachicks", null, this);
				AddonInjectionHandler.runInjection("extra", "attackRodents", null, this);
			}
		}
	}

	public static class EntityMaleFox extends EntityMaleDogBase
	{

		public EntityMaleFox(Level level)
		{
			super(level);
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
				this.targetSelector.addTask(4, new GenericAITargetNonTamed(this, AnimalEntity.class, false, entity -> entity instanceof ChickenEntity));
				AddonInjectionHandler.runInjection("extra", "attackPeachicks", null, this);
				AddonInjectionHandler.runInjection("extra", "attackRodents", null, this);
			}
		}
	}

	public static class EntityFemaleFox extends EntityFemaleDogBase
	{

		public EntityFemaleFox(Level level)
		{
			super(level);
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
				this.targetSelector.addTask(4, new GenericAITargetNonTamed(this, AnimalEntity.class, false, entity -> entity instanceof ChickenEntity));
				AddonInjectionHandler.runInjection("extra", "attackPeachicks", null, this);
				AddonInjectionHandler.runInjection("extra", "attackRodents", null, this);
			}
		}
	}

}
