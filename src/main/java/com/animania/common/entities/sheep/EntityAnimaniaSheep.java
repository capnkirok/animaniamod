package com.animania.common.entities.sheep;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Nullable;

import com.animania.common.entities.AnimalContainer;
import com.animania.common.entities.AnimaniaAnimal;
import com.animania.common.entities.EntityGender;
import com.animania.common.entities.ISpawnable;
import com.animania.common.entities.pigs.EntityAnimaniaPig;
import com.animania.common.entities.sheep.ai.EntityAIFindFoodSheep;
import com.animania.common.entities.sheep.ai.EntityAIFindSaltLickSheep;
import com.animania.common.entities.sheep.ai.EntityAIFindWater;
import com.animania.common.entities.sheep.ai.EntityAIPanicSheep;
import com.animania.common.entities.sheep.ai.EntityAISheepEatGrass;
import com.animania.common.entities.sheep.ai.EntityAISwimmingSheep;
import com.animania.common.handler.BlockHandler;
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
import net.minecraft.entity.ai.EntityAIWatchClosest;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityAnimaniaSheep extends EntitySheep implements ISpawnable, IShearable, AnimaniaAnimal
{

	public static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(AnimaniaHelper.getItemArray(AnimaniaConfig.careAndFeeding.sheepFood));
	protected static final DataParameter<Boolean> WATERED = EntityDataManager.<Boolean>createKey(EntityAnimaniaSheep.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> FED = EntityDataManager.<Boolean>createKey(EntityAnimaniaSheep.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Optional<UUID>> MATE_UNIQUE_ID = EntityDataManager.<Optional<UUID>>createKey(EntityAnimaniaSheep.class, DataSerializers.OPTIONAL_UNIQUE_ID);
	protected static final DataParameter<Optional<UUID>> RIVAL_UNIQUE_ID = EntityDataManager.<Optional<UUID>>createKey(EntityAnimaniaSheep.class, DataSerializers.OPTIONAL_UNIQUE_ID);
	protected static final DataParameter<Boolean> SHEARED = EntityDataManager.<Boolean>createKey(EntityAnimaniaSheep.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Integer> SHEARED_TIMER = EntityDataManager.<Integer>createKey(EntityAnimaniaSheep.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> COLOR_NUM = EntityDataManager.<Integer>createKey(EntityAnimaniaSheep.class, DataSerializers.VARINT);
	protected static final DataParameter<Integer> AGE = EntityDataManager.<Integer>createKey(EntityAnimaniaSheep.class, DataSerializers.VARINT);
	
	private static final String[] SHEEP_TEXTURES = new String[] {"black", "white", "brown"};


	protected int happyTimer;
	public int blinkTimer;
	public int eatTimer;
	protected int fedTimer;
	protected int wateredTimer;
	protected int damageTimer;
	public SheepType sheepType;
	protected Item dropRaw = Items.MUTTON;
	protected Item dropCooked = Items.COOKED_MUTTON;
	public EntityAISheepEatGrass entityAIEatGrass;
	protected boolean mateable = false;
	protected boolean headbutting = false;
	protected EntityGender gender;

	public EntityAnimaniaSheep(World worldIn)
	{
		super(worldIn);
		this.tasks.taskEntries.clear();
		this.entityAIEatGrass = new EntityAISheepEatGrass(this);
		if (!AnimaniaConfig.gameRules.ambianceMode) {
			this.tasks.addTask(2, new EntityAIFindWater(this, 1.0D));
			this.tasks.addTask(3, new EntityAIFindFoodSheep(this, 1.0D));
		}
		this.tasks.addTask(4, new EntityAIWanderAvoidWater(this, 1.0D));
		this.tasks.addTask(5, new EntityAISwimmingSheep(this));
		this.tasks.addTask(6, new EntityAIPanicSheep(this, 1.8D));
		this.tasks.addTask(7, new EntityAITempt(this, 1.25D, false, EntityAnimaniaSheep.TEMPTATION_ITEMS));
		this.tasks.addTask(6, new EntityAITempt(this, 1.25D, Item.getItemFromBlock(Blocks.YELLOW_FLOWER), false));
		this.tasks.addTask(6, new EntityAITempt(this, 1.25D, Item.getItemFromBlock(Blocks.RED_FLOWER), false));
		this.tasks.addTask(8, this.entityAIEatGrass);
		this.tasks.addTask(9, new EntityAIAvoidEntity(this, EntityWolf.class, 10.0F, 2.0D, 2.2D));
		this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(11, new EntityAILookIdle(this));
		this.tasks.addTask(12, new EntityAIFindSaltLickSheep(this, 1.0));
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
		this.dataManager.register(EntityAnimaniaSheep.FED, Boolean.valueOf(true));
		this.dataManager.register(EntityAnimaniaSheep.WATERED, Boolean.valueOf(true));
		this.dataManager.register(EntityAnimaniaSheep.MATE_UNIQUE_ID, Optional.<UUID>absent());
		this.dataManager.register(EntityAnimaniaSheep.RIVAL_UNIQUE_ID, Optional.<UUID>absent());
		this.dataManager.register(EntityAnimaniaSheep.SHEARED, Boolean.valueOf(false));
		this.dataManager.register(EntityAnimaniaSheep.SHEARED_TIMER, Integer.valueOf(AnimaniaConfig.careAndFeeding.woolRegrowthTimer + this.rand.nextInt(500)));

		if (this instanceof EntityRamFriesian || this instanceof EntityEweFriesian || this instanceof EntityLambFriesian) {
			this.dataManager.register(EntityAnimaniaSheep.COLOR_NUM, Integer.valueOf(rand.nextInt(3)));
		} else if (this instanceof EntityRamDorset || this instanceof EntityEweDorset || this instanceof EntityLambDorset) {
			this.dataManager.register(EntityAnimaniaSheep.COLOR_NUM, Integer.valueOf(rand.nextInt(2)));
		} else if (this instanceof EntityRamMerino || this instanceof EntityEweMerino || this instanceof EntityLambMerino) {
			this.dataManager.register(EntityAnimaniaSheep.COLOR_NUM, Integer.valueOf(rand.nextInt(2)));
		} else if (this instanceof EntityRamSuffolk || this instanceof EntityEweSuffolk || this instanceof EntityLambSuffolk) {
			this.dataManager.register(EntityAnimaniaSheep.COLOR_NUM, Integer.valueOf(rand.nextInt(2)));
		} else {
			this.dataManager.register(EntityAnimaniaSheep.COLOR_NUM, 0);
		}
		this.dataManager.register(EntityAnimaniaSheep.AGE, Integer.valueOf(0));


	}

	@Override
	public EntityAnimaniaSheep createChild(EntityAgeable ageable)
	{
		return null;
	}

	@Override
	protected ResourceLocation getLootTable()
	{
		return null;
	}


	@Override
	protected void consumeItemFromStack(EntityPlayer player, ItemStack stack)
	{
		this.setFed(true);
		this.entityAIEatGrass.startExecuting();
		this.eatTimer = 80;
		player.addStat(sheepType.getAchievement(), 1);

		if (!player.isCreative())
			stack.shrink(1);
	}


	public boolean getSheared()
	{
		return this.dataManager.get(EntityAnimaniaSheep.SHEARED).booleanValue();
	}

	public void setSheared(boolean sheared)
	{
		if (sheared)
		{
			this.dataManager.set(EntityAnimaniaSheep.SHEARED, Boolean.valueOf(true));
			this.setWoolRegrowthTimer(AnimaniaConfig.careAndFeeding.woolRegrowthTimer + this.rand.nextInt(500));
		} else
			this.dataManager.set(EntityAnimaniaSheep.SHEARED, Boolean.valueOf(false));
	}

	public int getWoolRegrowthTimer()
	{
		return this.dataManager.get(EntityAnimaniaSheep.SHEARED_TIMER).intValue();
	}

	public void setWoolRegrowthTimer(int time)
	{
		this.dataManager.set(EntityAnimaniaSheep.SHEARED_TIMER, Integer.valueOf(time));
	}

	public boolean getFed()
	{
		return this.dataManager.get(EntityAnimaniaSheep.FED).booleanValue();
	}

	public void setFed(boolean fed)
	{
		if (fed)
		{
			this.dataManager.set(EntityAnimaniaSheep.FED, Boolean.valueOf(true));
			this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer + this.rand.nextInt(100);
			this.setHealth(this.getHealth() + 1.0F);
		} else
			this.dataManager.set(EntityAnimaniaSheep.FED, Boolean.valueOf(false));
	}

	public boolean getWatered()
	{
		return this.dataManager.get(EntityAnimaniaSheep.WATERED).booleanValue();
	}

	public void setWatered(boolean watered)
	{
		if (watered)
		{
			this.dataManager.set(EntityAnimaniaSheep.WATERED, Boolean.valueOf(true));
			this.wateredTimer = AnimaniaConfig.careAndFeeding.waterTimer + this.rand.nextInt(100);
		} else
			this.dataManager.set(EntityAnimaniaSheep.WATERED, Boolean.valueOf(false));
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
		if (this.getAge() == 0) {
			this.setAge(1);
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

		if (stack.getItem() instanceof ItemShears && !this.getSheared() && !this.isChild())  
		{
			if (!this.world.isRemote) {
				this.playSound(SoundEvents.ENTITY_SHEEP_SHEAR, 1.0F, 1.0F);
			}
			player.swingArm(hand);
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

	@Nullable
	public UUID getMateUniqueId()
	{
		if(mateable)
		{
			try
			{
				UUID id = (UUID) ((Optional) this.dataManager.get(EntityAnimaniaSheep.MATE_UNIQUE_ID)).orNull();
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
		this.dataManager.set(EntityAnimaniaSheep.MATE_UNIQUE_ID, Optional.fromNullable(uniqueId));
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
		compound.setInteger("ColorNumber", getColorNumber());
		compound.setInteger("Age", this.getAge());

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
		this.setColorNumber(compound.getInteger("ColorNumber"));
		this.setFed(compound.getBoolean("Fed"));
		this.setWatered(compound.getBoolean("Watered"));
		this.setSheared(compound.getBoolean("Sheared"));
		this.setAge(compound.getInteger("Age"));

	}

	public int getAge()
	{
		return this.dataManager.get(EntityAnimaniaSheep.AGE).intValue();
	}

	public void setAge(int age)
	{
		this.dataManager.set(EntityAnimaniaSheep.AGE, Integer.valueOf(age));
	}
	
	public int getColorNumber()
	{
		return ((Integer)this.dataManager.get(COLOR_NUM)).intValue();
	}

	public void setColorNumber(int color)
	{
		this.dataManager.set(COLOR_NUM, Integer.valueOf(color));
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
		if (AnimaniaConfig.drops.customMobDrops && dropRaw != Items.MUTTON && dropCooked != Items.COOKED_MUTTON)
		{
			String drop = AnimaniaConfig.drops.sheepDrop;
			dropItem = AnimaniaHelper.getItem(drop);
			if (this.isBurning() && drop.equals(this.dropRaw.getRegistryName().toString()))
			{
				drop = this.dropCooked.getRegistryName().toString();
				dropItem = AnimaniaHelper.getItem(drop);
			}
		} else
		{
			dropItem = new ItemStack(this.dropRaw, 1);
			if (this.isBurning())
				dropItem = new ItemStack(this.dropCooked, 1);
		}

		ItemStack woolItem = new ItemStack(Blocks.WOOL, 1);
		if (this.getSheared()) {
			woolItem = ItemStack.EMPTY;
		} else if (this instanceof EntityRamFriesian || this instanceof EntityEweFriesian) {
			switch (this.getColorNumber()) {
			case 0:
				woolItem = new ItemStack(BlockHandler.blockAnimaniaWool, 1, 1);
				break;
			case 1:
				woolItem = new ItemStack(Item.getItemFromBlock(Blocks.WOOL), 1);
				break;
			case 2:
				woolItem = new ItemStack(BlockHandler.blockAnimaniaWool, 1, 2);
				break;
			}	
		} else if (this instanceof EntityRamSuffolk || this instanceof EntityEweSuffolk) {
			switch (this.getColorNumber()) {
			case 0:
				woolItem = new ItemStack(Item.getItemFromBlock(Blocks.WOOL), 1);
				break;
			case 1:
				woolItem = new ItemStack(Item.getItemFromBlock(BlockHandler.blockAnimaniaWool), 1, 6);
				break;
			}
		} else if (this instanceof EntityRamDorset || this instanceof EntityEweDorset) {
			switch (this.getColorNumber()) {
			case 0:
				woolItem = new ItemStack(Item.getItemFromBlock(Blocks.WOOL), 1);
				break;
			case 1:
				woolItem = new ItemStack(Item.getItemFromBlock(BlockHandler.blockAnimaniaWool), 1, 0);
				break;
			}
		} else if (this instanceof EntityRamMerino || this instanceof EntityEweMerino ) {
			switch (this.getColorNumber()) {
			case 0:
				woolItem = new ItemStack(Item.getItemFromBlock(BlockHandler.blockAnimaniaWool), 1, 5);
				break;
			case 1:
				woolItem = new ItemStack(Item.getItemFromBlock(BlockHandler.blockAnimaniaWool), 1, 4);
				break;
			}	
		} else if (this instanceof EntityRamJacob || this instanceof EntityEweJacob) {
			switch (this.getColorNumber()) {
			case 0:
				woolItem = new ItemStack(Item.getItemFromBlock(BlockHandler.blockAnimaniaWool), 1, 3);
				break;
			}	

		} else {
			woolItem = new ItemStack(Item.getItemFromBlock(Blocks.WOOL));
		}
		
		if (happyDrops == 2)
		{
			dropItem.setCount(1 + lootlevel);
			woolItem.setCount(1 + lootlevel);
			EntityItem entityitem = new EntityItem(this.world, this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D, dropItem);
			world.spawnEntity(entityitem);
			this.entityDropItem(woolItem, .5F);
		
			
		} else if (happyDrops == 1)
		{
			if (this.isBurning())
			{
				woolItem.setCount(1 + lootlevel);
				this.dropItem(Items.MUTTON, 1 + lootlevel);
				this.entityDropItem(woolItem, .5F);
			} else
			{
				woolItem.setCount(1 + lootlevel);
				this.dropItem(Items.MUTTON, 1 + lootlevel);
				this.entityDropItem(woolItem, .5F);
				
			}
		} else if (happyDrops == 0)
			this.entityDropItem(woolItem, .5F);

	}

	@Override
	public Item getSpawnEgg()
	{
		return ItemEntityEgg.ANIMAL_EGGS.get(new AnimalContainer(this.sheepType, this.gender));
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
	public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos) {
		if (!this.getSheared() && !this.isChild()) {
			return true;
		} else {
			return false;
		}
	}


	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public EntityGender getEntityGender()
	{
		return this.gender;
	}

}
