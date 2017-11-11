package com.animania.common.entities.goats;

import java.util.Set;
import java.util.UUID;

import javax.annotation.Nullable;

import com.animania.common.AnimaniaAchievements;
import com.animania.common.entities.AnimalContainer;
import com.animania.common.entities.EntityGender;
import com.animania.common.entities.ISpawnable;
import com.animania.common.entities.cows.EntityAnimaniaCow;
import com.animania.common.entities.goats.ai.EntityAIFindFoodGoats;
import com.animania.common.entities.goats.ai.EntityAIFindSaltLickGoats;
import com.animania.common.entities.goats.ai.EntityAIFindWater;
import com.animania.common.entities.goats.ai.EntityAIGoatEatGrass;
import com.animania.common.entities.goats.ai.EntityAIPanicGoats;
import com.animania.common.entities.goats.ai.EntityAISwimmingGoats;
import com.animania.common.entities.goats.ai.EntityAIWatchClosestGoats;
import com.animania.common.handler.ItemHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.items.ItemEntityEgg;
import com.animania.config.AnimaniaConfig;
import com.google.common.base.Optional;
import com.google.common.collect.Sets;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityAnimaniaGoat extends EntitySheep implements ISpawnable
{

	public static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(AnimaniaHelper.getItemArray(AnimaniaConfig.careAndFeeding.goatFood));
	protected static final DataParameter<Boolean> WATERED = EntityDataManager.<Boolean>createKey(EntityAnimaniaGoat.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> FED = EntityDataManager.<Boolean>createKey(EntityAnimaniaGoat.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Optional<UUID>> MATE_UNIQUE_ID = EntityDataManager.<Optional<UUID>>createKey(EntityAnimaniaGoat.class, DataSerializers.OPTIONAL_UNIQUE_ID);
	protected static final DataParameter<Optional<UUID>> RIVAL_UNIQUE_ID = EntityDataManager.<Optional<UUID>>createKey(EntityAnimaniaGoat.class, DataSerializers.OPTIONAL_UNIQUE_ID);
	protected static final DataParameter<Boolean> SHEARED = EntityDataManager.<Boolean>createKey(EntityAnimaniaGoat.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Integer> SHEARED_TIMER = EntityDataManager.<Integer>createKey(EntityAnimaniaGoat.class, DataSerializers.VARINT);
	protected static final DataParameter<Boolean> SPOOKED = EntityDataManager.<Boolean>createKey(EntityAnimaniaGoat.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Float> SPOOKED_TIMER = EntityDataManager.<Float>createKey(EntityAnimaniaGoat.class, DataSerializers.FLOAT);
	protected static final DataParameter<Integer> AGE = EntityDataManager.<Integer>createKey(EntityAnimaniaGoat.class, DataSerializers.VARINT);

	protected int happyTimer;
	public int blinkTimer;
	public int eatTimer;
	protected int fedTimer;
	protected int wateredTimer;
	protected int damageTimer;
	public GoatType goatType;
	protected Item dropRaw = ItemHandler.rawChevon;
	protected Item dropCooked = ItemHandler.cookedChevon;
	public EntityAIGoatEatGrass entityAIEatGrass;
	protected boolean mateable = false;
	protected boolean headbutting = false;
	protected EntityGender gender;

	public EntityAnimaniaGoat(World worldIn)
	{
		super(worldIn);
		this.tasks.taskEntries.clear();
		this.entityAIEatGrass = new EntityAIGoatEatGrass(this);
		if (!AnimaniaConfig.gameRules.ambianceMode) {
			this.tasks.addTask(2, new EntityAIFindWater(this, 1.0D));
			this.tasks.addTask(3, new EntityAIFindFoodGoats(this, 1.0D));
		}
		this.tasks.addTask(3, new EntityAIPanicGoats(this, 1.8D));
		this.tasks.addTask(4, new EntityAIWanderAvoidWater(this, 1.0D));
		this.tasks.addTask(5, new EntityAISwimmingGoats(this));
		this.tasks.addTask(7, new EntityAITempt(this, 1.25D, false, EntityAnimaniaGoat.TEMPTATION_ITEMS));
		this.tasks.addTask(6, new EntityAITempt(this, 1.25D, Item.getItemFromBlock(Blocks.RED_FLOWER), false));
		this.tasks.addTask(8, this.entityAIEatGrass);
		this.tasks.addTask(9, new EntityAIAvoidEntity(this, EntityWolf.class, 10.0F, 2.2D, 2.2D));
		this.tasks.addTask(10, new EntityAIWatchClosestGoats(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(11, new EntityAILookIdle(this));
		this.tasks.addTask(12, new EntityAIFindSaltLickGoats(this, 1.0));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, EntityPlayer.class));
		this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer + this.rand.nextInt(100);
		this.wateredTimer = AnimaniaConfig.careAndFeeding.waterTimer + this.rand.nextInt(100);
		this.happyTimer = 60;
		this.blinkTimer = 100 + this.rand.nextInt(100);
		this.enablePersistence();
	}

	@Override
	protected boolean canDespawn()
	{
		return false;
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(EntityAnimaniaGoat.FED, Boolean.valueOf(true));
		this.dataManager.register(EntityAnimaniaGoat.WATERED, Boolean.valueOf(true));
		this.dataManager.register(EntityAnimaniaGoat.MATE_UNIQUE_ID, Optional.<UUID>absent());
		this.dataManager.register(EntityAnimaniaGoat.RIVAL_UNIQUE_ID, Optional.<UUID>absent());
		this.dataManager.register(EntityAnimaniaGoat.SHEARED, Boolean.valueOf(false));
		this.dataManager.register(EntityAnimaniaGoat.SHEARED_TIMER, Integer.valueOf(AnimaniaConfig.careAndFeeding.woolRegrowthTimer + this.rand.nextInt(500)));
		this.dataManager.register(EntityAnimaniaGoat.SPOOKED, Boolean.valueOf(false));
		this.dataManager.register(EntityAnimaniaGoat.SPOOKED_TIMER, 0.0F);
		this.dataManager.register(EntityAnimaniaGoat.AGE, Integer.valueOf(0));
	}

	@Override
	protected ResourceLocation getLootTable()
	{
		return null;
	}

	public int getAnimalAge()
	{
		return this.dataManager.get(EntityAnimaniaGoat.AGE).intValue();
	}

	public void setAnimalAge(int age)
	{
		this.dataManager.set(EntityAnimaniaGoat.AGE, Integer.valueOf(age));
	}

	@Override
	protected void consumeItemFromStack(EntityPlayer player, ItemStack stack)
	{
		this.setFed(true);
		this.entityAIEatGrass.startExecuting();
		this.eatTimer = 80;
		player.addStat(goatType.getAchievement(), 1);

		if (player.hasAchievement(AnimaniaAchievements.Alpine) && player.hasAchievement(AnimaniaAchievements.Angora) && player.hasAchievement(AnimaniaAchievements.Fainting) 
				&& player.hasAchievement(AnimaniaAchievements.Kiko) && player.hasAchievement(AnimaniaAchievements.Kinder) && player.hasAchievement(AnimaniaAchievements.NigerianDwarf) 
				&& player.hasAchievement(AnimaniaAchievements.Pygmy))
			player.addStat(AnimaniaAchievements.Goats, 1);

		if (!player.isCreative())
			stack.shrink(1);;
	}

	public void setSpooked(boolean spooked)
	{
		this.dataManager.set(EntityAnimaniaGoat.SPOOKED, Boolean.valueOf(spooked));
	} 

	public boolean getSpooked()
	{
		return this.dataManager.get(EntityAnimaniaGoat.SPOOKED).booleanValue();
	}

	public Float getSpookedTimer()
	{
		return this.dataManager.get(EntityAnimaniaGoat.SPOOKED_TIMER).floatValue();
	}

	public void setSpookedTimer(Float timer)
	{
		this.dataManager.set(EntityAnimaniaGoat.SPOOKED_TIMER, Float.valueOf(timer));
	}

	@Nullable
	public UUID getMateUniqueId()
	{
		if(mateable)
		{
			try
			{
				UUID id = (UUID) ((Optional) this.dataManager.get(EntityAnimaniaGoat.MATE_UNIQUE_ID)).orNull();
				return id;
			}
			catch(Exception e)
			{
				return null;
			}
		}
		return null;
	}

	public void setMateUniqueId(@Nullable UUID uniqueId)
	{
		this.dataManager.set(EntityAnimaniaGoat.MATE_UNIQUE_ID, Optional.fromNullable(uniqueId));
	}

	public boolean getFed()
	{
		return this.dataManager.get(EntityAnimaniaGoat.FED).booleanValue();
	}

	public void setFed(boolean fed)
	{
		if (fed)
		{
			this.dataManager.set(EntityAnimaniaGoat.FED, Boolean.valueOf(true));
			this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer + this.rand.nextInt(100);
			this.setHealth(this.getHealth() + 1.0F);
		} else
			this.dataManager.set(EntityAnimaniaGoat.FED, Boolean.valueOf(false));
	}

	public boolean getWatered()
	{
		return this.dataManager.get(EntityAnimaniaGoat.WATERED).booleanValue();
	}

	public void setWatered(boolean watered)
	{
		if (watered)
		{
			this.dataManager.set(EntityAnimaniaGoat.WATERED, Boolean.valueOf(true));
			this.wateredTimer = AnimaniaConfig.careAndFeeding.waterTimer + this.rand.nextInt(100);
		} else
			this.dataManager.set(EntityAnimaniaGoat.WATERED, Boolean.valueOf(false));
	}

	@Override
	protected void updateAITasks()
	{
		this.eatTimer = this.entityAIEatGrass.getEatingGrassTimer();
		super.updateAITasks();
	}

	@Override
	protected float getSoundVolume()
	{
		return 0.4F;
	}


	@Override
	protected Item getDropItem()
	{
		return Items.LEATHER;
	}

	@Override
	public void onLivingUpdate()
	{

		if (this.getAnimalAge() == 0) {
			this.setAnimalAge(1);
		}
		
		if (this.world.isRemote)
			this.eatTimer = Math.max(0, this.eatTimer - 1);

		if (this.blinkTimer > -1)
		{
			this.blinkTimer--;
			if (this.blinkTimer == 0)
			{
				this.blinkTimer = 100 + this.rand.nextInt(100);
			}
		}

		if (this.getSpooked()) {

			if (this.getSpookedTimer() == 1.0F) {
				this.setJumping(true);
			} else {
				this.setJumping(false);
			}

			this.getNavigator().clearPathEntity();
			this.setNoAI(true);

			this.setSpookedTimer(this.getSpookedTimer() - 0.01F);

			if (this.getSpookedTimer() <= 0.04F && this.getSpookedTimer() > 0.0F) {
				this.setJumping(true);
			} else if (this.getSpookedTimer() <= 0.0F) {
				this.setSpooked(false);
				this.setSpookedTimer(0.0F);
				this.setNoAI(false);
				this.setJumping(false);

			}
		}

		if (this.fedTimer > -1 && !AnimaniaConfig.gameRules.ambianceMode)
		{
			this.fedTimer--;

			if (this.fedTimer == 0)
				this.setFed(false);
		}

		if (this.wateredTimer > -1)
		{
			this.wateredTimer--;

			if (this.wateredTimer == 0 && !AnimaniaConfig.gameRules.ambianceMode)
				this.setWatered(false);
		}

		boolean fed = this.getFed();
		boolean watered = this.getWatered();


		if (!fed && !watered)
		{
			this.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 2, 1, false, false));
			if (AnimaniaConfig.gameRules.animalsStarve)
			{
				if (this.damageTimer >= AnimaniaConfig.careAndFeeding.starvationTimer)
				{
					this.attackEntityFrom(DamageSource.STARVE, 4f);
					this.damageTimer = 0;
				}
				this.damageTimer++;
			}

		} else if (!fed || !watered)
			this.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 2, 0, false, false));

		if (this.happyTimer > -1)
		{
			this.happyTimer--;
			if (this.happyTimer == 0)
			{
				this.happyTimer = 60;

				if (!this.getFed() && !this.getWatered() && AnimaniaConfig.gameRules.showUnhappyParticles)
				{
					double d = this.rand.nextGaussian() * 0.001D;
					double d1 = this.rand.nextGaussian() * 0.001D;
					double d2 = this.rand.nextGaussian() * 0.001D;
					this.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX + this.rand.nextFloat() * this.width - this.width, this.posY + 1.5D + this.rand.nextFloat() * this.height, this.posZ + this.rand.nextFloat() * this.width - this.width, d, d1, d2);
				}
			}
		}

		boolean sheared = this.getSheared();
		if (sheared) {
			int shearedTimer = this.getWoolRegrowthTimer();
			shearedTimer--;
			this.setWoolRegrowthTimer(shearedTimer);
			if (shearedTimer < 0) {
				this.setSheared(false);

			}
		}


		super.onLivingUpdate();
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand)
	{
		ItemStack stack = player.getHeldItem(hand);
		EntityPlayer entityplayer = player;

		if (stack.getItem() instanceof ItemShears && !this.getSheared() && !this.isChild() && (this instanceof EntityBuckAngora || this instanceof EntityDoeAngora))   //Forge: Moved to onSheared
		{
			if (!this.world.isRemote)
			{
				this.setSheared(true);
				int i = 1 + this.rand.nextInt(2);

				for (int j = 0; j < i; ++j)
				{
					//EntityItem entityitem = this.entityDropItem(new ItemStack(Item.getItemFromBlock(Blocks.WOOL), 1, this.getFleeceColor().getMetadata()), 1.0F);
					EntityItem entityitem = this.entityDropItem(new ItemStack(Item.getItemFromBlock(Blocks.WOOL), 1), 1.0F);
					entityitem.motionY += (double)(this.rand.nextFloat() * 0.05F);
					entityitem.motionX += (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F);
					entityitem.motionZ += (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F);
				}
			}

			stack.damageItem(1, player);
			this.playSound(SoundEvents.ENTITY_SHEEP_SHEAR, 1.0F, 1.0F);
		}

		if (stack != ItemStack.EMPTY && stack.getItem() == Items.WATER_BUCKET)
		{
			if (stack.getCount() == 1 && !player.capabilities.isCreativeMode)
				player.setHeldItem(hand, new ItemStack(Items.BUCKET));
			else if (!player.capabilities.isCreativeMode && !player.inventory.addItemStackToInventory(new ItemStack(Items.BUCKET)))
				player.dropItem(new ItemStack(Items.BUCKET), false);

			this.eatTimer = 40;
			this.entityAIEatGrass.startExecuting();
			this.setWatered(true);
			this.setInLove(player);
			return true;
		} 
		else if(stack != ItemStack.EMPTY && stack.getItem() == Items.BUCKET)
		{
			return false;
		}
		else
			return super.processInteract(player, hand);
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

	@Override
	public void writeEntityToNBT(NBTTagCompound compound)
	{
		super.writeEntityToNBT(compound);
		if (this.getMateUniqueId() != null) {
			compound.setString("MateUUID", this.getMateUniqueId().toString());
		}
		compound.setBoolean("Fed", this.getFed());
		compound.setBoolean("Watered", this.getWatered());
		compound.setBoolean("Sheared", this.getSheared());
		compound.setBoolean("Spooked", this.getSpooked());
		compound.setInteger("Age", this.getAnimalAge());

	}

	public boolean getSheared()
	{
		return this.dataManager.get(EntityAnimaniaGoat.SHEARED).booleanValue();
	}

	public void setSheared(boolean sheared)
	{
		if (sheared)
		{
			this.dataManager.set(EntityAnimaniaGoat.SHEARED, Boolean.valueOf(true));
			this.setWoolRegrowthTimer(AnimaniaConfig.careAndFeeding.woolRegrowthTimer + this.rand.nextInt(500));
		} else
			this.dataManager.set(EntityAnimaniaGoat.SHEARED, Boolean.valueOf(false));
	}

	public int getWoolRegrowthTimer()
	{
		return this.dataManager.get(EntityAnimaniaGoat.SHEARED_TIMER).intValue();
	}

	public void setWoolRegrowthTimer(int time)
	{
		this.dataManager.set(EntityAnimaniaGoat.SHEARED_TIMER, Integer.valueOf(time));
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound)
	{
		super.readEntityFromNBT(compound);
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

		this.setFed(compound.getBoolean("Fed"));
		this.setWatered(compound.getBoolean("Watered"));
		this.setSheared(compound.getBoolean("Sheared"));
		this.setSpooked(compound.getBoolean("Spooked"));
		this.setAnimalAge(compound.getInteger("Age"));

	}

	@Override
	protected void dropFewItems(boolean hit, int lootlevel)
	{
		int happyDrops = 0;

		if (this.getWatered())
			happyDrops++;
		if (this.getFed())
			happyDrops++;

		ItemStack dropItem;
		if (AnimaniaConfig.drops.customMobDrops) {
			String drop = AnimaniaConfig.drops.goatDrop;
			dropItem = AnimaniaHelper.getItem(drop);
			//TODO AIR
			if (this.isBurning() && drop.equals(this.dropRaw.getRegistryName().toString()))
			{
				drop = this.dropCooked.getRegistryName().toString();
				dropItem = AnimaniaHelper.getItem(drop);
			}
		} else {
			dropItem = new ItemStack(this.dropRaw, 1);
			if (this.isBurning())
				dropItem = new ItemStack(this.dropCooked, 1);
		}

		if (happyDrops >= 1)
		{
			dropItem.setCount(1 + lootlevel);
			EntityItem entityitem = new EntityItem(this.world, this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D, dropItem);
			world.spawnEntity(entityitem);
			if ((this instanceof EntityBuckAngora || this instanceof EntityDoeAngora) && !this.getSheared())  {
				this.dropItem(new ItemStack(Item.getItemFromBlock(Blocks.WOOL), 1).getItem(), 1 + lootlevel);
			}		
		} else if (happyDrops == 0)
			if ((this instanceof EntityBuckAngora || this instanceof EntityDoeAngora) && !this.getSheared())  {
				this.dropItem(new ItemStack(Item.getItemFromBlock(Blocks.WOOL), 1).getItem(), 1 + lootlevel);
			}

	}

	@Override
	public EntityAnimaniaGoat createChild(EntityAgeable ageable) {
		return null;
	}

	@Override
	public Item getSpawnEgg()
	{
		return ItemEntityEgg.ANIMAL_EGGS.get(new AnimalContainer(this.goatType, this.gender));
	}

	@Override
	public ItemStack getPickedResult(RayTraceResult target)
	{
		return new ItemStack(getSpawnEgg());
	}

	@Override
	public int getPrimaryEggColor()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSecondaryEggColor()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public EntityGender getEntityGender()
	{
		return this.gender;
	}

}
