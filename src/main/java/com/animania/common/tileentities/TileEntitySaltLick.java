package com.animania.common.tileentities;

import javax.annotation.Nullable;

import com.animania.common.helper.AnimaniaHelper;
import com.animania.config.AnimaniaConfig;

import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntitySaltLick extends TileEntity
{

	public int usesLeft = AnimaniaConfig.careAndFeeding.saltLickMaxUses;

	public void useSaltLick()
	{
		usesLeft--;

		if (usesLeft <= 0)
			world.setBlockState(pos, Blocks.AIR.getDefaultState());
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt)
	{
		this.readFromNBT(pkt.getNbtCompound());

	}

	@Override
	@Nullable
	public SPacketUpdateTileEntity getUpdatePacket()
	{
		return new SPacketUpdateTileEntity(this.pos, 1, this.getUpdateTag());
	}

	@Override
	public NBTTagCompound getUpdateTag()
	{
		return this.writeToNBT(new NBTTagCompound());
	}
	
	@Override
	public void markDirty()
	{
		super.markDirty();
		
		AnimaniaHelper.sendTileEntityUpdate(this);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		NBTTagCompound tag = super.writeToNBT(compound);
		tag.setInteger("usesLeft", usesLeft);

		return tag;

	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.usesLeft = compound.getInteger("usesLeft");
	}

}
