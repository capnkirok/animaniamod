package com.animania.common.blockentities;

import javax.annotation.Nullable;

import com.animania.common.helper.AnimaniaHelper;
import com.animania.config.AnimaniaConfig;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateBlockEntity;
import net.minecraft.world.level.block.Blocks;
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
		return new SPacketUpdateBlockEntity(this.getBlockPos(), 1, this.getUpdateTag());
	}

	@Override
	public CompoundTag getUpdateTag()
	{
		return this.save(new CompoundTag());
	}

	@Override
	public void markDirty()
	{
		super.markDirty();

		AnimaniaHelper.sendBlockEntityUpdate(this);
	}

	@Override
	public CompoundTag save(CompoundTag compound)
	{
		CompoundTag tag = super.save(compound);
		tag.putInt("usesLeft", this.usesLeft);

		return tag;

	}

	@Override
	public void load(CompoundTag compound)
	{
		super.load(compound);
		this.usesLeft = compound.getInt("usesLeft");
	}

}
