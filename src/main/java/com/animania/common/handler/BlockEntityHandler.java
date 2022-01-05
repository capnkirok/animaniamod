package com.animania.common.handler;

import com.animania.Animania;
import com.animania.common.blockentities.BlockEntityInvisiblock;
import com.animania.common.blockentities.BlockEntityNest;
import com.animania.common.blockentities.BlockEntitySaltLick;
import com.animania.common.blockentities.BlockEntityTrough;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityHandler
{
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Animania.MODID);

	public static final RegistryObject<BlockEntityType<?>> TROUGH_BLOCK_ENTITY = BLOCK_ENTITIES.register("BlockEntityTrough", () -> BlockEntityType.Builder.of(BlockEntityTrough::new, validBlocks).build(null));

	public static void preInit()
	{
		GameRegistry.registerBlockEntity(BlockEntityTrough.class, "BlockEntityTrough");
		GameRegistry.registerBlockEntity(BlockEntityNest.class, "BlockEntityNest");
		GameRegistry.registerBlockEntity(BlockEntityInvisiblock.class, "BlockEntityInvisiblock");
		GameRegistry.registerBlockEntity(BlockEntitySaltLick.class, "BlockEntitySaltLick");
	}
}
