package com.animania.common.entities.horses;

import java.util.Set;
import java.util.UUID;

import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.IChild;
import com.animania.common.entities.generic.GenericBehavior;
import com.animania.common.entities.generic.ai.GenericAIFollowParents;
import com.animania.compat.top.providers.entity.TOPInfoProviderBase;
import com.google.common.base.Optional;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityFoalBase extends EntityAnimaniaHorse implements TOPInfoProviderBase, IChild
{
	private static final DataParameter<Integer> COLOR_NUM = EntityDataManager.<Integer>createKey(EntityFoalBase.class, DataSerializers.VARINT);
	private static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(new Item[] { Items.WHEAT, Items.APPLE, Items.CARROT });
	private static final DataParameter<Optional<UUID>> PARENT_UNIQUE_ID = EntityDataManager.<Optional<UUID>>createKey(EntityFoalBase.class, DataSerializers.OPTIONAL_UNIQUE_ID);
	private static final DataParameter<Float> AGE = EntityDataManager.<Float>createKey(EntityFoalBase.class, DataSerializers.FLOAT);
	protected int ageTimer;
	private ResourceLocation resourceLocation;
	private ResourceLocation resourceLocationBlink;
	private static final String[] HORSE_TEXTURES = new String[] { "black", "bw1", "bw2", "grey", "red", "white" };

	public EntityFoalBase(World worldIn)
	{
		super(worldIn);
		this.setSize(2.2F, 3.0F);
		this.width = 2.2F;
		this.height = 3.0F;
		this.stepHeight = 1.1F;
		this.tasks.addTask(1, new GenericAIFollowParents<EntityFoalBase, EntityMareBase>(this, 1.1, EntityMareBase.class));
		this.ageTimer = 0;
		this.horseType = HorseType.DRAFT;
		this.gender = EntityGender.CHILD;
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(EntityFoalBase.AGE, Float.valueOf(0));
		this.dataManager.register(EntityFoalBase.PARENT_UNIQUE_ID, Optional.<UUID>absent());
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(12.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
	}

	@Override
	public void setInLove(EntityPlayer player)
	{
		this.world.setEntityState(this, (byte) 18);
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn)
	{
		this.playSound(SoundEvents.ENTITY_HORSE_STEP, 0.05F, 1.1F);
	}

	@Override
	public void playLivingSound()
	{
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null && !this.getSleeping())
		{
			this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch() + .2F - (this.getEntityAge() * 2));
		}
	}

	@SideOnly(Side.CLIENT)
	public float getHeadRotationPointY(float p_70894_1_)
	{
		if (this.isBeingRidden())
		{
			return 0;
		}

		return this.eatTimer <= 0 ? 0.0F : (this.eatTimer >= 4 && this.eatTimer <= 156 ? 1.0F : (this.eatTimer < 4 ? ((float) this.eatTimer - p_70894_1_) / 4.0F : -((float) (this.eatTimer - 160) - p_70894_1_) / 4.0F));
	}

	@SideOnly(Side.CLIENT)
	public float getHeadRotationAngleX(float p_70890_1_)
	{

		if (this.isBeingRidden())
		{
			return 0;
		}

		if (this.eatTimer > 4 && this.eatTimer <= 156)
		{
			float f = ((float) (this.eatTimer - 4) - p_70890_1_) / 80.0F;
			return ((float) Math.PI / 5F) + ((float) Math.PI * 7F / 500F) * MathHelper.sin(f * 28.7F);
		}
		else
		{
			return this.eatTimer > 0 ? ((float) Math.PI / 5F) : this.rotationPitch * 0.017453292F;
		}
	}

	@Override
	public DataParameter<Optional<UUID>> getParentUniqueIdParam()
	{
		return EntityFoalBase.PARENT_UNIQUE_ID;
	}

	@Override
	public int getAgeTimer()
	{
		return ageTimer;
	}

	@Override
	public void setAgeTimer(int i)
	{
		ageTimer = i;
	}

	public ResourceLocation getResourceLocation()
	{
		return resourceLocation;
	}

	public ResourceLocation getResourceLocationBlink()
	{
		return resourceLocationBlink;
	}

	public void onLivingUpdate()
	{
		if (this.getColorNumber() > 5)
		{
			this.setColorNumber(0);
		}

		GenericBehavior.livingUpdateChild(this, EntityMareBase.class);

		super.onLivingUpdate();
	}

	@Override
	public EntityFoalBase createChild(EntityAgeable p_90011_1_)
	{
		return null;
	}

	@Override
	public DataParameter<Float> getEntityAgeParam()
	{
		return AGE;
	}
	
	@Override
	public void ageUp(int growthSeconds, boolean updateForcedAge)
	{
		float entityAge = this.getEntityAge();
		entityAge += 0.05f;
		this.setEntityAge(entityAge);
	}

}
