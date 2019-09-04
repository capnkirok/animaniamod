package com.animania.addons.catsdogs.common.entity.felids;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.addons.catsdogs.config.CatsDogsConfig;
import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.IImpregnable;
import com.animania.api.interfaces.IMateable;
import com.animania.common.ModSoundEvents;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.compat.top.providers.entity.TOPInfoProviderMateable;
import com.animania.config.AnimaniaConfig;
import com.google.common.base.Optional;

import mcjty.theoneprobe.api.IProbeHitEntityData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;

public class EntityQueenBase extends EntityAnimaniaCat implements TOPInfoProviderMateable, IMateable, IImpregnable
{

	protected static final DataParameter<Optional<UUID>> MATE_UNIQUE_ID = EntityDataManager.<Optional<UUID>>createKey(EntityQueenBase.class, DataSerializers.OPTIONAL_UNIQUE_ID);
	protected static final DataParameter<Boolean> PREGNANT = EntityDataManager.<Boolean>createKey(EntityQueenBase.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> HAS_KIDS = EntityDataManager.<Boolean>createKey(EntityQueenBase.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> FERTILE = EntityDataManager.<Boolean>createKey(EntityQueenBase.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Integer> GESTATION_TIMER = EntityDataManager.<Integer>createKey(EntityQueenBase.class, DataSerializers.VARINT);
	public int dryTimer;

	public EntityQueenBase(World worldIn)
	{
		super(worldIn);
		this.setSize(0.8F, 0.8F); 
		this.width = 0.8F;
		this.height = 0.8F;
		this.stepHeight = 1.1F;
		this.gender = EntityGender.FEMALE;
	}

	@Override
	@Nullable
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
	{
		if (this.world.isRemote)
			return null;

		List list = this.world.loadedEntityList;

		int currentCount = 0;
		int num = 0;
		for (int i = 0; i < list.size(); i++)
		{
			if (list.get(i) instanceof EntityAnimaniaCat)
			{
				num++;
			}
		}
		currentCount = num;

		if (currentCount <= CatsDogsConfig.catsdogs.spawnLimitCats)
		{

			int chooser = this.rand.nextInt(5);

			if (chooser == 0)
			{
				EntityTomBase entityPig = this.type.getMale(world);
				entityPig.setPosition(this.posX, this.posY, this.posZ);
				this.world.spawnEntity(entityPig);
				entityPig.setMateUniqueId(this.entityUniqueID);
				this.setMateUniqueId(entityPig.getPersistentID());
			}
			else if (chooser == 1 && !AnimaniaConfig.careAndFeeding.feedToBreed)
			{
				EntityKittenBase entityPig = this.type.getChild(world);
				entityPig.setPosition(this.posX, this.posY, this.posZ);
				this.world.spawnEntity(entityPig);
				entityPig.setParentUniqueId(this.entityUniqueID);
			}
			else if (chooser > 2)
			{
				EntityTomBase entityPig = this.type.getMale(world);
				entityPig.setPosition(this.posX, this.posY, this.posZ);
				this.world.spawnEntity(entityPig);
				entityPig.setMateUniqueId(this.entityUniqueID);
				this.setMateUniqueId(entityPig.getPersistentID());
				if (!AnimaniaConfig.careAndFeeding.feedToBreed)
				{
					EntityKittenBase entityPiglet = this.type.getChild(world);
					entityPiglet.setPosition(this.posX, this.posY, this.posZ);
					this.world.spawnEntity(entityPiglet);
					entityPiglet.setParentUniqueId(this.entityUniqueID);
				}
			}
		}

		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).applyModifier(new AttributeModifier("Random spawn bonus", this.rand.nextGaussian() * 0.05D, 1));

		if (this.rand.nextFloat() < 0.05F)
			this.setLeftHanded(true);
		else
			this.setLeftHanded(false);

		return livingdata;
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(MATE_UNIQUE_ID, Optional.<UUID>absent());
		this.dataManager.register(PREGNANT, false);
		this.dataManager.register(HAS_KIDS, false);
		this.dataManager.register(FERTILE, true);
		this.dataManager.register(GESTATION_TIMER, Integer.valueOf(AnimaniaConfig.careAndFeeding.gestationTimer + this.rand.nextInt(200)));

	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound)
	{
		super.writeEntityToNBT(compound);
		



	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound)
	{
		super.readEntityFromNBT(compound);

		

		

	}

	@Override
	public DataParameter<Integer> getGestationParam()
	{
		return GESTATION_TIMER;
	}

	@Override
	public DataParameter<Boolean> getPregnantParam()
	{
		return PREGNANT;
	}

	@Override
	public DataParameter<Boolean> getFertileParam()
	{
		return FERTILE;
	}

	@Override
	public DataParameter<Boolean> getHasKidsParam()
	{
		return HAS_KIDS;
	}

	@Override
	public DataParameter<Optional<UUID>> getMateUniqueIdParam()
	{
		return MATE_UNIQUE_ID;
	}

	// TODO: SOUNDS
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
			num = 10;
		else if (happy == 1)
			num = 20;
		else
			num = 40;

		int chooser = Animania.RANDOM.nextInt(num);

		if (chooser == 0)
			return ModSoundEvents.pig1;
		else if (chooser == 1)
			return ModSoundEvents.pig2;
		else if (chooser == 2)
			return ModSoundEvents.pig4;
		else if (chooser == 3)
			return ModSoundEvents.pig5;
		else if (chooser == 4)
			return ModSoundEvents.pig6;
		else if (chooser == 5)
			return ModSoundEvents.pig7;
		else
			return null;

	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source)
	{
		int chooser = Animania.RANDOM.nextInt(3);

		if (chooser == 0)
			return ModSoundEvents.pigHurt1;
		else if (chooser == 1)
			return ModSoundEvents.pigHurt2;
		else
			return ModSoundEvents.pig3;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		int chooser = Animania.RANDOM.nextInt(3);

		if (chooser == 0)
			return ModSoundEvents.pigHurt1;
		else if (chooser == 1)
			return ModSoundEvents.pigHurt2;
		else
			return ModSoundEvents.pig3;
	}

	@Override
	public void onLivingUpdate()
	{
		if (!this.getFertile() && this.dryTimer > -1)
		{
			this.dryTimer--;
		}
		else
		{
			this.setFertile(true);
			this.dryTimer = AnimaniaConfig.careAndFeeding.gestationTimer / 5 + rand.nextInt(50);
		}

		if (this.isBeingRidden() && this.getSleeping())
			this.setSleeping(false);

		if (this.blinkTimer > -1)
		{
			this.blinkTimer--;
			if (this.blinkTimer == 0)
			{
				this.blinkTimer = 200 + this.rand.nextInt(200);

				// Check for Mate
				if (this.getMateUniqueId() != null)
				{
					UUID mate = this.getMateUniqueId();
					boolean mateReset = true;

					List<EntityTomBase> entities = AnimaniaHelper.getEntitiesInRange(EntityTomBase.class, 20, world, this);
					for (int k = 0; k <= entities.size() - 1; k++)
					{
						EntityTomBase male = entities.get(k);
						if (male != null)
						{
							UUID id = male.getPersistentID();
							if (id.equals(this.getMateUniqueId()) && !male.isDead)
							{
								mateReset = false;
								break;
							}
						}
					}

					if (mateReset)
						this.setMateUniqueId(null);

				}
			}
		}

		boolean fed = this.getFed();
		boolean watered = this.getWatered();
		int gestationTimer = this.getGestation();

		if (gestationTimer > -1 && this.getPregnant())
		{
			gestationTimer--;
			this.setGestation(gestationTimer);

			if (gestationTimer < 200 && this.getSleeping())
			{
				this.setSleeping(false);
			}

			if (gestationTimer == 0)
			{

				List list = this.world.loadedEntityList;
				int pigCount = 0;
				int num = 0;
				for (int i = 0; i < list.size(); i++)
				{
					if (list.get(i) instanceof EntityAnimaniaCat)
					{
						num++;
					}
				}
				pigCount = num;

				UUID MateID = this.getMateUniqueId();
				List<EntityTomBase> entities = AnimaniaHelper.getEntitiesInRange(EntityTomBase.class, 30, this.world, this);
				int esize = entities.size();
				Boolean mateFound = false;
				for (int k = 0; k <= esize - 1; k++)
				{
					EntityTomBase male = entities.get(k);
					if (male != null && this.getFed() && this.getWatered() && male.getPersistentID().equals(MateID))
					{

						this.setInLove(null);
						CatType maleType = male.type;
						CatType babyType = CatType.breed(maleType, this.type);
						EntityKittenBase child = babyType.getChild(world);
						child.setPosition(this.posX, this.posY + .2, this.posZ);

						BabyEntitySpawnEvent event = new BabyEntitySpawnEvent(this, (EntityLiving) male, child);
						MinecraftForge.EVENT_BUS.post(event);
						if (!event.isCanceled())
						{
							if (!world.isRemote)
							{
								this.world.spawnEntity(child);
							}
							child.setParentUniqueId(this.getPersistentID());
							this.playSound(ModSoundEvents.piglet1, 0.50F, 1.1F);

							this.setPregnant(false);
							this.setFertile(false);
							this.setHasKids(true);

							k = esize;
							mateFound = true;
							break;

						}
					}
				}

				if (!mateFound && this.getFed() && this.getWatered())
				{

					this.setInLove(null);
					CatType babyType = type.breed(this.type, this.type);
					EntityKittenBase entityKid = babyType.getChild(world);
					entityKid.setPosition(this.posX, this.posY + .2, this.posZ);
					BabyEntitySpawnEvent event = new BabyEntitySpawnEvent(this, (EntityLiving) entityKid, entityKid);
					MinecraftForge.EVENT_BUS.post(event);
					if (!event.isCanceled())
					{
						if (!world.isRemote)
						{
							this.world.spawnEntity(entityKid);
						}

						entityKid.setParentUniqueId(this.getPersistentID());
						this.playSound(ModSoundEvents.piglet1, 0.50F, 1.1F);

						this.setPregnant(false);
						this.setFertile(false);
						this.setHasKids(true);

						mateFound = true;

					}
				}
			}
		}
		else if (gestationTimer < 0)
		{
			this.setGestation(1);
		}

		super.onLivingUpdate();
	}
	
	@Override
	public boolean isBreedingItem(@Nullable ItemStack stack)
	{
		return stack != ItemStack.EMPTY && (TEMPTATION_ITEMS.contains(stack.getItem()));
	}
	
	@Override
	public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, Entity entity, IProbeHitEntityData data)
	{

		if (player.isSneaking())
		{

			if (this.getMateUniqueId() != null) 
				probeInfo.text(I18n.translateToLocal("text.waila.mated"));
			
			if (this.getFertile() && !this.getPregnant())
			{
				probeInfo.text(I18n.translateToLocal("text.waila.fertile1"));
			} 

			if (this.getPregnant())
			{
				if (this.getGestation() > 1) {
					int bob = this.getGestation();
					probeInfo.text(I18n.translateToLocal("text.waila.pregnant1") + " (" + bob + " " + I18n.translateToLocal("text.waila.pregnant2") + ")" );
				} else {
					probeInfo.text(I18n.translateToLocal("text.waila.pregnant1"));
				}
			} 
		}
		TOPInfoProviderMateable.super.addProbeInfo(mode, probeInfo, player, world, entity, data);
	}

}
