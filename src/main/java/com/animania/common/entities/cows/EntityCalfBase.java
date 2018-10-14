package com.animania.common.entities.cows;

import java.util.Random;
import java.util.UUID;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.animania.common.ModSoundEvents;
import com.animania.common.entities.EntityGender;
import com.animania.common.entities.IChild;
import com.animania.common.entities.cows.ai.EntityAIFollowParentCows;
import com.animania.compat.top.providers.entity.TOPInfoProviderChild;
import com.animania.config.AnimaniaConfig;
import com.google.common.base.Optional;

public class EntityCalfBase extends EntityAnimaniaCow implements TOPInfoProviderChild, IChild
{

	protected static final DataParameter<Optional<UUID>> PARENT_UNIQUE_ID = EntityDataManager.<Optional<UUID>>createKey(EntityCalfBase.class, DataSerializers.OPTIONAL_UNIQUE_ID);
	protected static final DataParameter<Float> AGE = EntityDataManager.<Float>createKey(EntityCalfBase.class, DataSerializers.FLOAT);
	protected int ageTimer;

	public EntityCalfBase(World worldIn)
	{
		super(worldIn);
		this.setSize(1.6F, 3.6F);
		this.stepHeight = 1.1F;
		this.tasks.addTask(1, new EntityAIFollowParentCows(this, 1.1D));
		this.ageTimer = 0;
		this.cowType = CowType.FRIESIAN;
		this.gender = EntityGender.CHILD;

	}

	@Override
	public boolean isChild()
	{
		return true;
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(EntityCalfBase.AGE, Float.valueOf(0));
		this.dataManager.register(EntityCalfBase.PARENT_UNIQUE_ID, Optional.<UUID>absent());
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.26000000298023224D);
	}

	@Override
	public void setInLove(EntityPlayer player)
	{
		this.world.setEntityState(this, (byte) 18);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound)
	{
		super.writeEntityToNBT(compound);
		compound.setFloat("Age", this.getEntityAge());
		if (this.getParentUniqueId() != null)
			if (this.getParentUniqueId() != null)
				compound.setString("ParentUUID", this.getParentUniqueId().toString());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound)
	{
		super.readEntityFromNBT(compound);
		this.setEntityAge(compound.getFloat("Age"));
		String s;

		if (compound.hasKey("ParentUUID", 8))
			s = compound.getString("ParentUUID");
		else
		{
			String s1 = compound.getString("Parent");
			s = PreYggdrasilConverter.convertMobOwnerIfNeeded(this.getServer(), s1);
		}

		if (!s.isEmpty())
			this.setParentUniqueId(UUID.fromString(s));

	}

	@Nullable
	public UUID getParentUniqueId()
	{
		try
		{
			UUID id = (UUID) ((Optional) this.dataManager.get(EntityCalfBase.PARENT_UNIQUE_ID)).orNull();
			return id;
		}
		catch(Exception e)
		{
			return null;
		}
	}

	public void setParentUniqueId(@Nullable UUID uniqueId)
	{
		this.dataManager.set(EntityCalfBase.PARENT_UNIQUE_ID, Optional.fromNullable(uniqueId));
	}

	public float getEntityAge()
	{
		try {
			return (this.getFloatFromDataManager(AGE));
		}
		catch (Exception e) {
			return 0;
		}
	}

	public void setEntityAge(float age)
	{
		this.dataManager.set(EntityCalfBase.AGE, Float.valueOf(age));
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		int happy = 0;
		int num = 1;

		if (this.getWatered())
			happy++;
		if (this.getFed())
			happy++;

		if (happy == 2)
			num = 6;
		else if (happy == 1)
			num = 12;
		else
			num = 24;

		Random rand = new Random();
		int chooser = rand.nextInt(num);

		if (chooser == 0)
			return ModSoundEvents.mooCalf1;
		else if (chooser == 1)
			return ModSoundEvents.mooCalf2;
		else if (chooser == 2)
			return ModSoundEvents.mooCalf3;
		else
			return null;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source)
	{
		Random rand = new Random();
		int chooser = rand.nextInt(3);

		if (chooser == 0)
			return ModSoundEvents.mooCalf1;
		else if (chooser == 1)
			return ModSoundEvents.mooCalf2;
		else
			return ModSoundEvents.mooCalf3;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		Random rand = new Random();
		int chooser = rand.nextInt(3);

		if (chooser == 0)
			return ModSoundEvents.mooCalf1;
		else if (chooser == 1)
			return ModSoundEvents.mooCalf2;
		else
			return ModSoundEvents.mooCalf3;
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn)
	{
		this.playSound(SoundEvents.ENTITY_COW_STEP, 0.05F, 1.1F);
	}

	@Override
	protected Item getDropItem()
	{
		return null;
	}

	@Override
	public void onLivingUpdate()
	{
		
		this.growingAge = -24000;
		
		if (this.world.isRemote)
			this.eatTimer = Math.max(0, this.eatTimer - 1);

		if (this.blinkTimer > -1)
		{
			this.blinkTimer--;
			if (this.blinkTimer == 0)
				this.blinkTimer = 100 + this.rand.nextInt(100);
		}

		if (this.fedTimer > -1)
		{
			this.fedTimer--;

			if (this.fedTimer == 0)
				this.setFed(false);
		}

		if (this.wateredTimer > -1)
		{
			this.wateredTimer--;

			if (this.wateredTimer == 0)
				this.setWatered(false);
		}

		boolean fed = this.getFed();
		boolean watered = this.getWatered();

		this.ageTimer++;
		
		if (this.ageTimer >= AnimaniaConfig.careAndFeeding.childGrowthTick)
			if (fed && watered)
			{
				this.ageTimer = 0;
				float age = this.getEntityAge();
				age = age + .01F;
				this.setEntityAge(age);
				this.setSize(1.2F + age, 1.8F + age);

				if (age >= 0.85 && !this.world.isRemote)
				{
					this.setDead();

					if (this.rand.nextInt(2) < 1)
					{
						EntityCowBase entityCow = this.cowType.getFemale(world);
						if (entityCow != null)
						{
							entityCow.setPosition(this.posX, this.posY + .5, this.posZ);
							String name = this.getCustomNameTag();
							if (name != "")
								entityCow.setCustomNameTag(name);
							
							entityCow.setAge(1);
							this.world.spawnEntity(entityCow);
							this.playSound(ModSoundEvents.moo1, 0.50F, 1.1F);
						}
					}
					else
					{
						EntityBullBase entityBull = this.cowType.getMale(world);
						if (entityBull != null)
						{
							entityBull.setPosition(this.posX, this.posY + .5, this.posZ);
							String name = this.getCustomNameTag();
							if (name != "")
								entityBull.setCustomNameTag(name);
							
							entityBull.setAge(1);
							this.world.spawnEntity(entityBull);
							this.playSound(ModSoundEvents.bullMoo1, 0.50F, 1.1F);
						}
					}

				}
			}

		super.onLivingUpdate();
	}

	@Override
	protected void dropFewItems(boolean damaged, int looting)
	{
		return;
	}

	@Override
	public void playLivingSound()
	{
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null)
		{
			this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch() + .2F - (this.getEntityAge() * 2));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void handleStatusUpdate(byte id)
	{
		if (id == 10)
			this.eatTimer = 160;
		else
			super.handleStatusUpdate(id);
	}

	@SideOnly(Side.CLIENT)
	public float getHeadRotationPointY(float p_70894_1_)
	{
		return this.eatTimer <= 0 ? 0.0F : this.eatTimer >= 4 && this.eatTimer <= 156 ? 1.0F : this.eatTimer < 4 ? (this.eatTimer - p_70894_1_) / 4.0F : -(this.eatTimer - 160 - p_70894_1_) / 4.0F;
	}

	@SideOnly(Side.CLIENT)
	public float getHeadRotationAngleX(float p_70890_1_)
	{
		if (this.eatTimer > 4 && this.eatTimer <= 156)
		{
			float f = (this.eatTimer - 4 - p_70890_1_) / 64.0F;
			return (float) Math.PI / 5F + (float) Math.PI * 7F / 100F * MathHelper.sin(f * 28.7F);
		}
		else
			return this.eatTimer > 0 ? (float) Math.PI / 5F : this.rotationPitch * 0.017453292F;
	}

	@Override
	public boolean isBreedingItem(@Nullable ItemStack stack)
	{
		return stack != ItemStack.EMPTY && this.isCowBreedingItem(stack.getItem());
	}

	private boolean isCowBreedingItem(Item itemIn)
	{
		return TEMPTATION_ITEMS.contains(itemIn) || itemIn == Item.getItemFromBlock(Blocks.YELLOW_FLOWER) || itemIn == Item.getItemFromBlock(Blocks.RED_FLOWER);
	}

	@Override
	public EntityCalfBase createChild(EntityAgeable e)
	{
		return null;
	}
}
