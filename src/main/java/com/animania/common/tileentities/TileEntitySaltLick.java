package com.animania.common.tileentities;

import javax.annotation.Nullable;

import com.animania.common.helper.AnimaniaHelper;
import com.animania.config.AnimaniaConfig;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.world.level.block.entity.BlockEntity;

public class TileEntitySaltLick extends BlockEntity
{

	public int usesLeft = AnimaniaConfig.careAndFeeding.saltLickMaxUses;

	public void useSaltLick()
	{
		usesLeft--;

		if (usesLeft <= 0)
			level.setBlockAndUpdate(worldPosition, Blocks.AIR.defaultBlockState());
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
	public CompoundNBT getUpdateTag()
	{
		return this.writeToNBT(new CompoundNBT());
	}
	
	@Override
	public void markDirty()
	{
		super.markDirty();
		
		AnimaniaHelper.sendTileEntityUpdate(this);
	}

	@Override
	public CompoundNBT writeToNBT(CompoundNBT compound)
	{
		CompoundNBT tag = super.writeToNBT(compound);
		tag.putInteger("usesLeft", usesLeft);

		return tag;

	}

	@Override
	public void readFromNBT(CompoundNBT compound)
	{
		super.readFromNBT(compound);
		this.usesLeft = compound.getInteger("usesLeft");
	}

}
