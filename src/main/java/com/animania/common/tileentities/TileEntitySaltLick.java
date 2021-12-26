package com.animania.common.tileentities;

import javax.annotation.Nullable;

import com.animania.common.helper.AnimaniaHelper;
import com.animania.config.AnimaniaConfig;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;

public class BlockEntitySaltLick extends BlockEntity
{

	public int usesLeft = AnimaniaConfig.careAndFeeding.saltLickMaxUses;

	public void useSaltLick()
	{
		this.usesLeft--;

		if (this.usesLeft <= 0)
			this.level.setBlockAndUpdate(levelPosition, Blocks.AIR.defaultBlockState());
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateBlockEntity pkt)
	{
		this.readFromNBT(pkt.getNbtCompound());

	}

	@Override
	@Nullable
	public SPacketUpdateBlockEntity getUpdatePacket()
	{
		return new SPacketUpdateBlockEntity(this.pos, 1, this.getUpdateTag());
	}

	@Override
	public CompoundTag getUpdateTag()
	{
		return this.writeToNBT(new CompoundTag());
	}

	@Override
	public void markDirty()
	{
		super.markDirty();

		AnimaniaHelper.sendBlockEntityUpdate(this);
	}

	@Override
	public CompoundTag writeToNBT(CompoundTag compound)
	{
		CompoundTag tag = super.writeToNBT(compound);
		tag.putInteger("usesLeft", this.usesLeft);

		return tag;

	}

	@Override
	public void readFromNBT(CompoundTag compound)
	{
		super.readFromNBT(compound);
		this.usesLeft = compound.getInteger("usesLeft");
	}

}
