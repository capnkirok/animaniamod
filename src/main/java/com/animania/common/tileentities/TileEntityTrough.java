package com.animania.common.tileentities;

import javax.annotation.Nullable;

import com.animania.common.handler.BlockHandler;
import com.animania.common.tileentities.handler.FluidHandlerTrough;
import com.animania.common.tileentities.handler.ItemHandlerTrough;
import com.animania.config.AnimaniaConfig;
import com.google.common.collect.Iterables;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.properties.Property;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.server.management.PlayerProfileCache;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.StringUtils;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;

public class TileEntityTrough extends TileEntity implements ITickable
{
	private int troughType;
	private int troughRotation;
	private GameProfile playerProfile;
	private int dragonAnimatedTicks;
	private boolean dragonAnimated;
	private static PlayerProfileCache profileCache;
	private static MinecraftSessionService sessionService;

	public ItemHandlerTrough itemHandler;
	public FluidHandlerTrough fluidHandler;

	public TileEntityTrough()
	{

		this.itemHandler = new ItemHandlerTrough();
		this.fluidHandler = new FluidHandlerTrough(1000);
	}

	public static void setProfileCache(PlayerProfileCache profileCacheIn)
	{
		TileEntityTrough.profileCache = profileCacheIn;
	}

	public static void setSessionService(MinecraftSessionService sessionServiceIn)
	{
		TileEntityTrough.sessionService = sessionServiceIn;
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt)
	{
		this.readFromNBT(pkt.getNbtCompound());
		if (this.blockType != null && this.pos != null)
			this.world.notifyBlockUpdate(this.pos, this.blockType.getDefaultState(), this.blockType.getDefaultState(), 1);

	}

	@Override
	@Nullable
	public SPacketUpdateTileEntity getUpdatePacket()
	{
		NBTTagCompound tagCompound = new NBTTagCompound();
		this.writeToNBT(tagCompound);
		return new SPacketUpdateTileEntity(this.pos, 1, this.getUpdateTag());
	}

	@Override
	public NBTTagCompound getUpdateTag()
	{
		return this.writeToNBT(new NBTTagCompound());
	}

	@Override
	public void update()
	{

		ItemStack stack = this.itemHandler.getStackInSlot(0);
		FluidStack fluid = this.fluidHandler.getFluid();

		if (!stack.isEmpty())
		{
			int count = stack.getCount();
			if (count == 0 && fluid == null && this.troughType != 0)
				this.setType(0);
			else if (count == 1 && this.troughType != 4)
				this.setType(4);
			else if (count == 2 && this.troughType != 5)
				this.setType(5);
			else if (count == 3 && this.troughType != 6)
				this.setType(6);
		} else if (fluid != null)
		{
			if (fluid.getFluid() == FluidRegistry.WATER && fluid.amount >= 666 && this.troughType != 1)
				this.setType(1);
			else if (fluid.getFluid() == FluidRegistry.WATER && fluid.amount >= 333 && fluid.amount < 666 && this.troughType != 2)
				this.setType(2);
			else if (fluid.getFluid() == FluidRegistry.WATER && fluid.amount > 0 && fluid.amount < 333 && this.troughType != 3)
				this.setType(3);
			else if (fluid.getFluid() == BlockHandler.fluidSlop && fluid.amount >= 666 && this.troughType != 7)
				this.setType(7);
			else if (fluid.getFluid() == BlockHandler.fluidSlop && fluid.amount >= 333 && fluid.amount < 666 && this.troughType != 8)
				this.setType(8);
			else if (fluid.getFluid() == BlockHandler.fluidSlop && fluid.amount > 0 && fluid.amount < 333 && this.troughType != 9)
				this.setType(9);
		} else if (this.troughType != 0)
			this.setType(0);

	}

	@SideOnly(Side.CLIENT)
	public float getAnimationProgress(float p_184295_1_)
	{
		return this.dragonAnimated ? this.dragonAnimatedTicks + p_184295_1_ : (float) this.dragonAnimatedTicks;
	}

	@Nullable
	public GameProfile getPlayerProfile()
	{
		return this.playerProfile;
	}

	/**
	 * TROUGH TYPES
	 *
	 * <pre>
	 * 0 = Empty
	 * 1 = Water, full
	 * 2 = Water, 2/3
	 * 3 = Water, 1/3
	 * 4 = Wheat, 1 layer
	 * 5 = Wheat, 2 layers
	 * 6 = Wheat, 3 layers
	 * 7 = Slop, full
	 * 8 = Slop, 2/3
	 * 9 = Slop, 1/3
	 * </pre>
	 */
	@Deprecated
	public void setType(int type)
	{
		this.troughType = type;
		this.playerProfile = null;
		this.markDirty();
		this.world.notifyBlockUpdate(this.pos, this.world.getBlockState(this.pos), this.world.getBlockState(this.pos), 2);
	}

	public static GameProfile updateGameprofile(GameProfile input)
	{
		if (input != null && !StringUtils.isNullOrEmpty(input.getName()))
		{
			if (input.isComplete() && input.getProperties().containsKey("textures"))
				return input;
			else if (TileEntityTrough.profileCache != null && TileEntityTrough.sessionService != null)
			{
				GameProfile gameprofile = TileEntityTrough.profileCache.getGameProfileForUsername(input.getName());

				if (gameprofile == null)
					return input;
				else
				{
					Property property = Iterables.getFirst(gameprofile.getProperties().get("textures"), null);

					if (property == null)
						gameprofile = TileEntityTrough.sessionService.fillProfileProperties(gameprofile, true);

					return gameprofile;
				}
			} else
				return input;
		} else
			return input;
	}

	/**
	 * TROUGH TYPES
	 *
	 * <pre>
	 * 0 = Empty
	 * 1 = Water, full
	 * 2 = Water, 2/3
	 * 3 = Water, 1/3
	 * 4 = Wheat, 1 layer
	 * 5 = Wheat, 2 layers
	 * 6 = Wheat, 3 layers
	 * 7 = Slop, full
	 * 8 = Slop, 2/3
	 * 9 = Slop, 1/3
	 * </pre>
	 */
	public int getTroughType()
	{
		return this.troughType;
	}

	public int getTroughRotation()
	{
		return this.troughRotation;
	}

	public void setTroughRotation(int rotation)
	{
		this.troughRotation = rotation;
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		NBTTagCompound tag = super.writeToNBT(compound);
		NBTTagCompound items = this.itemHandler.serializeNBT();
		NBTTagCompound fluid = new NBTTagCompound();
		fluid = this.fluidHandler.writeToNBT(fluid);
		tag.setTag("items", items);
		tag.setTag("fluid", fluid);
		tag.setByte("Rot", (byte) (this.troughRotation & 255));

		return tag;

	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.troughRotation = compound.getByte("Rot");
		this.itemHandler = new ItemHandlerTrough();
		this.fluidHandler = new FluidHandlerTrough(1000);
		this.fluidHandler.readFromNBT(compound.getCompoundTag("fluid"));
		this.itemHandler.deserializeNBT(compound.getCompoundTag("items"));
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{

		if (AnimaniaConfig.gameRules.allowTroughAutomation)
		{
			if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && this.fluidHandler.getFluid() == null)
				return true;
			if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY && this.itemHandler.getStackInSlot(0).isEmpty())
				return true;
		}

		return super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		if (AnimaniaConfig.gameRules.allowTroughAutomation)
		{

			if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && this.fluidHandler.getFluid() == null)
				return (T) this.itemHandler;

			if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY && this.itemHandler.getStackInSlot(0).isEmpty())
				return (T) this.fluidHandler;

		}

		return super.getCapability(capability, facing);

	}

}