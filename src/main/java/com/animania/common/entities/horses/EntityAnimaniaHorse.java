package com.animania.common.entities.horses;

import java.util.Set;
import java.util.UUID;

import javax.annotation.Nullable;

import com.animania.common.AnimaniaAchievements;
import com.animania.common.entities.horses.ai.EntityAIFindFood;
import com.animania.common.entities.horses.ai.EntityAIFindWater;
import com.animania.common.entities.horses.ai.EntityAIFollowMateHorses;
import com.animania.common.entities.horses.ai.EntityAIMateHorses;
import com.animania.common.entities.horses.ai.EntityAIPanicHorses;
import com.animania.common.entities.horses.ai.EntityAISwimmingHorse;
import com.animania.common.entities.horses.ai.EntityAITemptHorses;
import com.animania.common.entities.horses.ai.EntityAIWanderHorse;
import com.animania.common.entities.horses.ai.EntityHorseEatGrass;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.config.AnimaniaConfig;
import com.google.common.base.Optional;
import com.google.common.collect.Sets;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityAnimaniaHorse extends EntityAnimal
{
	private static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(new Item[] {Items.WHEAT, Items.APPLE, Items.CARROT});
	protected static final DataParameter<Boolean> WATERED = EntityDataManager.<Boolean>createKey(EntityAnimaniaHorse.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> FED = EntityDataManager.<Boolean>createKey(EntityAnimaniaHorse.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Optional<UUID>> MATE_UNIQUE_ID = EntityDataManager.<Optional<UUID>>createKey(EntityAnimaniaHorse.class, DataSerializers.OPTIONAL_UNIQUE_ID);
	private static final DataParameter<Integer> COLOR_NUM = EntityDataManager.<Integer>createKey(EntityStallionDraftHorse.class, DataSerializers.VARINT);
	private static final String[] HORSE_TEXTURES = new String[] {"black", "bw1", "bw2", "grey", "red", "white"};

	protected int happyTimer;
	public int blinkTimer;
	public int eatTimer;
	protected int fedTimer;
	protected int wateredTimer;
	protected int damageTimer;
	public EntityHorseEatGrass entityAIEatGrass;
	public HorseType horseType;
	protected boolean mateable = false;
	private ResourceLocation resourceLocation;
	private ResourceLocation resourceLocationBlink;

	public EntityAnimaniaHorse(World worldIn)
	{
		super(worldIn);
		this.setSize(1.6F, 2.2F);
		this.stepHeight = 1.1F;
		this.tasks.taskEntries.clear();
		this.entityAIEatGrass = new EntityHorseEatGrass(this);
		this.tasks.addTask(1, new EntityAIFindFood(this, 1.1D));
		this.tasks.addTask(1, new EntityAIPanicHorses(this, 2.0D));
		this.tasks.addTask(5, new EntityAIMateHorses(this, 1.0D));
		this.tasks.addTask(1, new EntityAIFollowMateHorses(this, 1.1D));
		this.tasks.addTask(3, new EntityAIFindWater(this, 1.0D));
		this.tasks.addTask(4, new EntityAIWanderHorse(this, 1.0D));
		this.tasks.addTask(5, new EntityAISwimmingHorse(this));
		this.tasks.addTask(7, new EntityAITemptHorses(this, 1.25D, false, TEMPTATION_ITEMS));
		this.tasks.addTask(8, this.entityAIEatGrass);
		this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(11, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, EntityPlayer.class));
		this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer + rand.nextInt(100);
		this.wateredTimer = AnimaniaConfig.careAndFeeding.waterTimer + rand.nextInt(100);
		this.happyTimer = 60;
		this.blinkTimer = 80 + rand.nextInt(80);
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
		this.dataManager.register(EntityAnimaniaHorse.COLOR_NUM, Integer.valueOf(0));
		this.dataManager.register(EntityAnimaniaHorse.FED, Boolean.valueOf(true));
		this.dataManager.register(EntityAnimaniaHorse.WATERED, Boolean.valueOf(true));
		this.dataManager.register(EntityAnimaniaHorse.MATE_UNIQUE_ID, Optional.<UUID>absent());
	}

	@Override
	protected ResourceLocation getLootTable()
	{
		return null;
	}

	@Nullable
	public UUID getMateUniqueId()
	{
		if(mateable)
		{
			try
			{
				UUID id = (UUID) ((Optional) this.dataManager.get(EntityAnimaniaHorse.MATE_UNIQUE_ID)).orNull();
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
		this.dataManager.set(EntityAnimaniaHorse.MATE_UNIQUE_ID, Optional.fromNullable(uniqueId));
	}

	@Override
	protected void consumeItemFromStack(EntityPlayer player, ItemStack stack)
	{
		this.setFed(true);
		this.entityAIEatGrass.startExecuting();
		this.eatTimer = 80;
		player.addStat(horseType.getAchievement(), 1);

		player.addStat(AnimaniaAchievements.Horses, 1);

		if (!player.capabilities.isCreativeMode)
			stack.setCount(stack.getCount() - 1);
	}

	public boolean getFed()
	{
		return this.dataManager.get(EntityAnimaniaHorse.FED).booleanValue();
	}

	public void setFed(boolean fed)
	{
		if (fed)
		{
			this.dataManager.set(EntityAnimaniaHorse.FED, Boolean.valueOf(true));
			this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer + this.rand.nextInt(100);
			this.setHealth(this.getHealth() + 1.0F);
		} else
			this.dataManager.set(EntityAnimaniaHorse.FED, Boolean.valueOf(false));
	}

	public boolean getWatered()
	{
		return this.dataManager.get(EntityAnimaniaHorse.WATERED).booleanValue();
	}

	public void setWatered(boolean watered)
	{
		if (watered)
		{
			this.dataManager.set(EntityAnimaniaHorse.WATERED, Boolean.valueOf(true));
			this.wateredTimer = AnimaniaConfig.careAndFeeding.waterTimer + this.rand.nextInt(100);
		} else
			this.dataManager.set(EntityAnimaniaHorse.WATERED, Boolean.valueOf(false));
	}


	@Override
	protected void updateAITasks()
	{
		this.eatTimer = this.entityAIEatGrass.getEatingGrassTimer();
		super.updateAITasks();
	}

	@Override
	public void playLivingSound()
	{
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null)
			this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch() - .2F);
	}

	/**
	 * Returns the volume for the sounds this mob makes.
	 */
	@Override
	protected float getSoundVolume()
	{
		return 0.4F;
	}

	public int getColorNumber()
	{
		return ((Integer)this.dataManager.get(COLOR_NUM)).intValue();
	}

	public void setColorNumber(int color)
	{
		this.dataManager.set(COLOR_NUM, Integer.valueOf(color));
	}

	public ResourceLocation getResourceLocation()
	{
		return resourceLocation;
	}

	public ResourceLocation getResourceLocationBlink()
	{
		return resourceLocationBlink;
	}


	@Override
	protected Item getDropItem()
	{
		return Items.LEATHER;
	}

	protected ItemStack getItem(String moditem) {

		ItemStack foundStack = null;
		String item = "";
		String mod = "";
		int sepLoc = 0;
		int metaLoc = 0;
		boolean metaFlag = false;
		String metaVal = "";

		sepLoc = moditem.indexOf(":");
		metaLoc = moditem.indexOf("#");

		if (!moditem.contains(":")) {
			return new ItemStack(Blocks.AIR, 1);
		}

		mod = moditem.substring(0, sepLoc);

		if (metaLoc > 0) {
			item = moditem.substring(sepLoc+1, metaLoc);
		} else {
			item = moditem;
		}
		if (metaLoc > 0) {
			metaFlag = true;
			metaVal = moditem.substring(metaLoc+1, moditem.length());
		}

		Item bob = Item.getByNameOrId(item);

		if (bob != null) {

			if (metaFlag) {
				foundStack = new ItemStack(bob, 1, Integer.parseInt(metaVal));
			} else {
				foundStack = new ItemStack(bob, 1);
			}
		} else {
			foundStack = new ItemStack(Blocks.AIR, 1);
		}

		return foundStack;
	}


	

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand)
	{
		ItemStack stack = player.getHeldItem(hand);
		EntityPlayer entityplayer = player;

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


	public boolean isBreedingItem(@Nullable ItemStack stack)
	{
		return stack != null && this.isHorseBreedingItem(stack.getItem());
	}

	private boolean isHorseBreedingItem(Item itemIn)
	{
		return itemIn == Items.WHEAT || itemIn == Items.APPLE || itemIn == Items.CARROT; 
	}


	public void writeEntityToNBT(NBTTagCompound compound)
	{
		super.writeEntityToNBT(compound);

		if (this.getMateUniqueId() != null) {
			compound.setString("MateUUID", this.getMateUniqueId().toString());
		}
		compound.setBoolean("Fed", this.getFed());
		compound.setBoolean("Watered", this.getWatered());
		compound.setInteger("ColorNumber", getColorNumber());


	}

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

		this.setColorNumber(compound.getInteger("ColorNumber"));
		this.setFed(compound.getBoolean("Fed"));
		this.setWatered(compound.getBoolean("Watered"));

	}


	@Override
	protected void dropFewItems(boolean hit, int lootlevel)
	{
		int happyDrops = 0;

		if (this.getWatered()) {
			happyDrops++;
		} 
		if (this.getFed()) {
			happyDrops++;
		} 

		ItemStack dropItem;
		if (AnimaniaConfig.drops.customMobDrops)
		{
			String drop = AnimaniaConfig.drops.horseDrop;
			dropItem = AnimaniaHelper.getItem(drop);
		} else {
			dropItem = ItemStack.EMPTY;
		}

		if (happyDrops == 2) {
			dropItem.setCount(1 + lootlevel);
			EntityItem entityitem = new EntityItem(this.world, this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D, dropItem);
			world.spawnEntity(entityitem);
			this.dropItem(Items.LEATHER, 1);
		} else if (happyDrops == 1) {
			if (this.isBurning())
			{
				this.dropItem(Items.LEATHER, 1 + lootlevel);
			}
			else
			{
				this.dropItem(Items.LEATHER, 1 + lootlevel);
			}
		} else if (happyDrops == 0) {
			this.dropItem(Items.LEATHER, 1 + lootlevel);
		}

	}

	@Override
	public EntityAgeable createChild(EntityAgeable ageable) {
		return null;
	}


}
