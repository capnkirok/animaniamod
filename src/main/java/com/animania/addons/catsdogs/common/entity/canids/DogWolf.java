package com.animania.addons.catsdogs.common.entity.canids;

import com.animania.common.entities.generic.ai.GenericAITargetNonTamed;
import com.animania.common.handler.AddonInjectionHandler;
import com.animania.config.AnimaniaConfig;

import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.world.level.Level;

public class DogWolf
{

	public static class EntityPuppyWolf extends EntityPuppyBase
	{

		public EntityPuppyWolf(Level level)
		{
			super(level);
			this.type = DogType.WOLF;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return -4409680;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return -13488852;
		}

		@Override
		public int getVariantCount()
		{
			return 8;
		}

		@Override
		public int getEyeColorForVariant(int variant)
		{
			switch (variant)
			{
			case 0:
				return 0x524E48;
			case 1:
				return 0xD9D9D9;
			case 2:
				return 0x2A2725;
			case 3:
				return 0x2A2725;
			case 4:
				return 0x71533F;
			case 5:
				return 0x9D8C76;
			case 6:
				return 0x9A9389;
			default:
				return 0xCECECD;
			}
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

	public static class EntityMaleWolf extends EntityMaleDogBase
	{

		public EntityMaleWolf(Level level)
		{
			super(level);
			this.type = DogType.WOLF;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return -4409680;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return -13488852;
		}

		@Override
		public int getVariantCount()
		{
			return 8;
		}

		@Override
		public int getEyeColorForVariant(int variant)
		{
			switch (variant)
			{
			case 0:
				return 0x524E48;
			case 1:
				return 0xD9D9D9;
			case 2:
				return 0x2A2725;
			case 3:
				return 0x2A2725;
			case 4:
				return 0x71533F;
			case 5:
				return 0x9D8C76;
			case 6:
				return 0x9A9389;
			default:
				return 0xCECECD;
			}
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

	public static class EntityFemaleWolf extends EntityFemaleDogBase
	{

		public EntityFemaleWolf(Level level)
		{
			super(level);
			this.type = DogType.WOLF;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return -4409680;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return -13488852;
		}

		@Override
		public int getVariantCount()
		{
			return 8;
		}

		@Override
		public int getEyeColorForVariant(int variant)
		{
			switch (variant)
			{
			case 0:
				return 0x524E48;
			case 1:
				return 0xD9D9D9;
			case 2:
				return 0x2A2725;
			case 3:
				return 0x2A2725;
			case 4:
				return 0x71533F;
			case 5:
				return 0x9D8C76;
			case 6:
				return 0x9A9389;
			default:
				return 0xCECECD;
			}
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
