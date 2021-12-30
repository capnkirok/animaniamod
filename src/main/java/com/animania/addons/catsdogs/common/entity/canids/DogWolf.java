package com.animania.addons.catsdogs.common.entity.canids;

import com.animania.common.entities.generic.ai.GenericAITargetNonTamed;
import com.animania.common.handler.AddonInjectionHandler;
import com.animania.config.AnimaniaConfig;

import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Chicken;
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
			return switch (variant) {
				case 0 -> 0x524E48;
				case 1 -> 0xD9D9D9;
				case 2 -> 0x2A2725;
				case 3 -> 0x2A2725;
				case 4 -> 0x71533F;
				case 5 -> 0x9D8C76;
				case 6 -> 0x9A9389;
				default -> 0xCECECD;
			};
		}

		@Override
		protected void initAI()
		{
			super.initAI();

			if (AnimaniaConfig.gameRules.animalsCanAttackOthers && !this.isTame())
			{
				this.targetSelector.addGoal(4, new GenericAITargetNonTamed(this, Animal.class, false, entity -> entity instanceof Chicken));
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
			return switch (variant) {
				case 0 -> 0x524E48;
				case 1 -> 0xD9D9D9;
				case 2 -> 0x2A2725;
				case 3 -> 0x2A2725;
				case 4 -> 0x71533F;
				case 5 -> 0x9D8C76;
				case 6 -> 0x9A9389;
				default -> 0xCECECD;
			};
		}

		@Override
		protected void initAI()
		{
			super.initAI();

			if (AnimaniaConfig.gameRules.animalsCanAttackOthers && !this.isTame())
			{
				this.targetSelector.addGoal(4, new GenericAITargetNonTamed(this, Animal.class, false, entity -> entity instanceof Chicken));
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
			return switch (variant) {
				case 0 -> 0x524E48;
				case 1 -> 0xD9D9D9;
				case 2 -> 0x2A2725;
				case 3 -> 0x2A2725;
				case 4 -> 0x71533F;
				case 5 -> 0x9D8C76;
				case 6 -> 0x9A9389;
				default -> 0xCECECD;
			};
		}

		@Override
		protected void initAI()
		{
			super.initAI();

			if (AnimaniaConfig.gameRules.animalsCanAttackOthers && !this.isTame())
			{
				this.targetSelector.addGoal(4, new GenericAITargetNonTamed(this, Animal.class, false, entity -> entity instanceof Chicken));
				AddonInjectionHandler.runInjection("extra", "attackPeachicks", null, this);
				AddonInjectionHandler.runInjection("extra", "attackRodents", null, this);
			}
		}
	}

}
