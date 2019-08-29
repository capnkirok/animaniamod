package com.animania.common.entities.goats;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.IImpregnable;
import com.animania.api.interfaces.IMateable;
import com.animania.common.ModSoundEvents;
import com.animania.common.entities.goats.GoatAngora.EntityDoeAngora;
import com.animania.common.handler.BlockHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.compat.top.providers.entity.TOPInfoProviderMateable;
import com.animania.config.AnimaniaConfig;
import com.google.common.base.Optional;

import mcjty.theoneprobe.api.IProbeHitEntityData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.fluids.FluidActionResult;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityDoeBase extends EntityAnimaniaGoat implements TOPInfoProviderMateable, IMateable, IImpregnable
{
	protected ItemStack milk = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, BlockHandler.fluidMilkGoat);
	public int dryTimerDoe;
	protected static final DataParameter<Boolean> PREGNANT = EntityDataManager.<Boolean>createKey(EntityDoeBase.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> HAS_KIDS = EntityDataManager.<Boolean>createKey(EntityDoeBase.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> FERTILE = EntityDataManager.<Boolean>createKey(EntityDoeBase.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Integer> GESTATION_TIMER = EntityDataManager.<Integer>createKey(EntityDoeBase.class, DataSerializers.VARINT);
	protected static final DataParameter<Optional<UUID>> MATE_UNIQUE_ID = EntityDataManager.<Optional<UUID>>createKey(EntityDoeBase.class, DataSerializers.OPTIONAL_UNIQUE_ID);

	public EntityDoeBase(World worldIn)
	{
		super(worldIn);
		this.setSize(1.0F, 1.0F); 
		this.width = 1.0F;
		this.height = 1.0F;
		this.stepHeight = 1.1F;
		this.mateable = true;
		this.gender = EntityGender.FEMALE;
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(EntityDoeBase.PREGNANT, false);
		this.dataManager.register(EntityDoeBase.HAS_KIDS, false);
		this.dataManager.register(EntityDoeBase.FERTILE, true);
		this.dataManager.register(EntityDoeBase.GESTATION_TIMER, Integer.valueOf(AnimaniaConfig.careAndFeeding.gestationTimer + this.rand.nextInt(200)));
		this.dataManager.register(EntityDoeBase.MATE_UNIQUE_ID, Optional.<UUID>absent());

	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.265D);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound)
	{
		super.writeEntityToNBT(compound);
		compound.setBoolean("Pregnant", this.getPregnant());
		compound.setBoolean("HasKids", this.getHasKids());
		compound.setBoolean("Fertile", this.getFertile());
		compound.setInteger("Gestation", this.getGestation());
		if (this.getMateUniqueId() != null)
		{
			compound.setString("MateUUID", this.getMateUniqueId().toString());
		}

	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound)
	{
		super.readEntityFromNBT(compound);

		this.setPregnant(compound.getBoolean("Pregnant"));
		this.setHasKids(compound.getBoolean("HasKids"));
		this.setFertile(compound.getBoolean("Fertile"));
		this.setGestation(compound.getInteger("Gestation"));

		String s;

		if (compound.hasKey("MateUUID", 8))
		{
			s = compound.getString("MateUUID");
		}
		else
		{
			String s1 = compound.getString("Mate");
			s = PreYggdrasilConverter.convertMobOwnerIfNeeded(this.getServer(), s1);
		}

		if (!s.isEmpty())
		{
			this.setMateUniqueId(UUID.fromString(s));
		}
	}

	@Override
	@Nullable
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
	{
		if (this.world.isRemote)
			return null;

		List<EntityAnimaniaGoat> others = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaGoat.class, 64, this.world, this.getPosition());
		
		if (others.size() <= 4 ) {

			int chooser = this.rand.nextInt(3);

			if (chooser == 0)
			{
				EntityBuckBase entityGoat = this.goatType.getMale(world);
				entityGoat.setPosition(this.posX, this.posY, this.posZ);
				this.world.spawnEntity(entityGoat);
				entityGoat.setMateUniqueId(this.entityUniqueID);
				this.setMateUniqueId(entityGoat.getPersistentID());
			}
			else if (chooser == 1)
			{
				EntityKidBase entityKid = this.goatType.getChild(world);
				entityKid.setPosition(this.posX, this.posY, this.posZ);
				this.world.spawnEntity(entityKid);
				entityKid.setParentUniqueId(this.entityUniqueID);
				this.setHasKids(true);
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
			return ModSoundEvents.goatLiving1;
		else if (chooser == 1)
			return ModSoundEvents.goatLiving2;
		else if (chooser == 2)
			return ModSoundEvents.goatLiving3;
		else if (chooser == 3)
			return ModSoundEvents.goatLiving4;
		else if (chooser == 4)
			return ModSoundEvents.goatLiving5;
		else
			return null;

	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source)
	{
		int chooser = Animania.RANDOM.nextInt(3);

		if (chooser == 0)
			return ModSoundEvents.goatHurt1;
		else if (chooser == 1)
			return ModSoundEvents.goatHurt2;
		else
			return ModSoundEvents.goatLiving3;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		int chooser = Animania.RANDOM.nextInt(3);

		if (chooser == 0)
			return ModSoundEvents.goatHurt1;
		else if (chooser == 1)
			return ModSoundEvents.goatHurt2;
		else
			return ModSoundEvents.goatLiving3;
	}

	@Override
	public void playLivingSound()
	{
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null && !this.getSleeping())
			this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch());
	}

	@Override
	public void onLivingUpdate()
	{
		if (!this.getFertile() && this.dryTimerDoe > -1)
		{
			this.dryTimerDoe--;
		}
		else
		{
			this.setFertile(true);
			this.dryTimerDoe = AnimaniaConfig.careAndFeeding.gestationTimer / 5 + rand.nextInt(50);
		}

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

					List<EntityLivingBase> entities = AnimaniaHelper.getEntitiesInRange(EntityBuckBase.class, 20, world, this);
					for (int k = 0; k <= entities.size() - 1; k++)
					{
						Entity entity = entities.get(k);
						if (entity != null)
						{
							UUID id = entity.getPersistentID();
							if (id.equals(this.getMateUniqueId()) && !entity.isDead)
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
			
			if (gestationTimer < 200 && this.getSleeping()) {
				this.setSleeping(false);
			}

			if (gestationTimer == 0)
			{

				List list = this.world.loadedEntityList;	
				int goatCount = 0;
				int num = 0;
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i) instanceof EntityAnimaniaGoat) {
						num++;
					}
				}
				goatCount = num;

				UUID MateID = this.getMateUniqueId();
				List entities = AnimaniaHelper.getEntitiesInRange(EntityBuckBase.class, 30, this.world, this);
				int esize = entities.size();
				Boolean mateFound = false;
				for (int k = 0; k <= esize - 1; k++)
				{
					EntityBuckBase entity = (EntityBuckBase) entities.get(k);
					if (entity != null && this.getFed() && this.getWatered() && entity.getPersistentID().equals(MateID)) 
					{

						this.setInLove(null);
						GoatType maleType = ((EntityAnimaniaGoat) entity).goatType;
						GoatType babyType = GoatType.breed(maleType, this.goatType);
						EntityKidBase entityKid = babyType.getChild(world);
						entityKid.setPosition(this.posX, this.posY + .2, this.posZ);
						if (!world.isRemote)
						{
							this.world.spawnEntity(entityKid);
						}
						entityKid.setParentUniqueId(this.getPersistentID());
						this.playSound(ModSoundEvents.mooCalf1, 0.50F, 1.1F);

						this.setPregnant(false);
						this.setFertile(false);
						this.setHasKids(true);

						BabyEntitySpawnEvent event = new BabyEntitySpawnEvent(this, (EntityLiving) entity, entityKid);
						MinecraftForge.EVENT_BUS.post(event);
						k = esize;
						mateFound = true;
						break;

					}
				}

				if (!mateFound && this.getFed() && this.getWatered()) { 

					this.setInLove(null);
					GoatType babyType = GoatType.breed(this.goatType, this.goatType);
					EntityKidBase entityKid = babyType.getChild(world);
					entityKid.setPosition(this.posX, this.posY + .2, this.posZ);
					if (!world.isRemote) {
						this.world.spawnEntity(entityKid);
					}

					entityKid.setParentUniqueId(this.getPersistentID());
					this.playSound(ModSoundEvents.kidLiving1, 0.50F, 1.1F);

					this.setPregnant(false);
					this.setFertile(false);
					this.setHasKids(true);

					BabyEntitySpawnEvent event = new BabyEntitySpawnEvent(this, (EntityLiving) entityKid, entityKid);
					MinecraftForge.EVENT_BUS.post(event);
					mateFound = true;

				}
			}
		} else if (gestationTimer < 0){
			this.setGestation(100);
		}

		super.onLivingUpdate();
	}

	
	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand)
	{
		ItemStack stack = player.getHeldItem(hand);
		EntityPlayer entityplayer = player;

		if (this.getFed() && this.getWatered() && stack != ItemStack.EMPTY && AnimaniaHelper.isEmptyFluidContainer(stack) && this.getHasKids())
		{
			player.playSound(SoundEvents.ENTITY_COW_MILK, 1.0F, 1.0F);

			ItemStack one = stack.copy();
			one.setCount(1);
			FluidActionResult result;
			result = FluidUtil.tryFillContainer(one, FluidUtil.getFluidHandler(milk.copy()), 1000, player, true);

			ItemStack filled;;
			if (!result.success)
			{
				Item item = stack.getItem();
				if (item == Items.BUCKET)
					filled = milk.copy();
				else if(Loader.isModLoaded("ceramics") && item == Item.getByNameOrId("ceramics:clay_bucket"))
					filled = new ItemStack(Item.getByNameOrId("ceramics:clay_bucket"), 1, 1);
				else
					return false;
			}
			else
				filled = result.result;
			stack.shrink(1);
			AnimaniaHelper.addItem(player, filled);
			this.setWatered(false);

			return true;
		}
		else
			return super.processInteract(player, hand);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void handleStatusUpdate(byte id)
	{
		if (id == 10)
			this.eatTimer = 80;
		else
			super.handleStatusUpdate(id);
	}

	@SideOnly(Side.CLIENT)
	public float getHeadRotationPointY(float p_70894_1_)
	{
		return this.eatTimer <= 0 ? 0.0F : this.eatTimer >= 4 && this.eatTimer <= 76 ? 1.0F : this.eatTimer < 4 ? (this.eatTimer - p_70894_1_) / 4.0F : -(this.eatTimer - 80 - p_70894_1_) / 4.0F;
	}

	@SideOnly(Side.CLIENT)
	public float getHeadRotationAngleX(float p_70890_1_)
	{
		if (this.eatTimer > 4 && this.eatTimer <= 76)
		{
			float f = (this.eatTimer - 4 - p_70890_1_) / 24.0F;
			return (float) Math.PI / 5F + (float) Math.PI * 7F / 150F * MathHelper.sin(f * 28.7F);
		}
		else
			return this.eatTimer > 0 ? (float) Math.PI / 5F : this.rotationPitch * 0.017453292F;
	}

	@Override
	public boolean isBreedingItem(@Nullable ItemStack stack)
	{
		return stack != ItemStack.EMPTY && (EntityAnimaniaGoat.TEMPTATION_ITEMS.contains(stack.getItem()));
	}

	@Override
	public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, Entity entity, IProbeHitEntityData data)
	{
		if (player.isSneaking())
		{

			if (this.getMateUniqueId() != null)
				probeInfo.text(I18n.translateToLocal("text.waila.mated"));

			if (this.getHasKids())
				probeInfo.text(I18n.translateToLocal("text.waila.milkable"));

			if (this.getFertile() && !this.getPregnant())
			{
				probeInfo.text(I18n.translateToLocal("text.waila.fertile1"));
			}

			if (this.getPregnant())
			{
				if (this.getGestation() > 0)
				{
					int bob = this.getGestation();
					probeInfo.text(I18n.translateToLocal("text.waila.pregnant1") + " (" + bob + " " + I18n.translateToLocal("text.waila.pregnant2") + ")");
				}
				else
				{
					probeInfo.text(I18n.translateToLocal("text.waila.pregnant1"));
				}
			}

			if (this.getSheared() && this instanceof EntityDoeAngora)
			{
				if (this.getWoolRegrowthTimer() > 1)
				{
					int bob = this.getWoolRegrowthTimer();
					probeInfo.text(I18n.translateToLocal("text.waila.wool1") + " (" + bob + " " + I18n.translateToLocal("text.waila.wool2") + ")");
				}
			}
			else if (this instanceof EntityDoeAngora)
			{
				probeInfo.text(I18n.translateToLocal("text.waila.wool3"));
			}
		}
		TOPInfoProviderMateable.super.addProbeInfo(mode, probeInfo, player, world, entity, data);
	}

	@Override
	public DataParameter<Optional<UUID>> getMateUniqueIdParam()
	{
		return MATE_UNIQUE_ID;
	}
}
