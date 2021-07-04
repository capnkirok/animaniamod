package com.animania.addons.farm.common.event;

import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventMudDamageCanceller
{
	@SubscribeEvent
	public static void notifyAttack(LivingAttackEvent event)
	{
		// if ((event.getLivingEntity() instanceof EntityHamster ||
		// event.getLivingEntity() instanceof EntityFerretWhite
		// || event.getLivingEntity() instanceof EntityFerretGrey ||
		// event.getLivingEntity() instanceof EntityChickLeghorn
		// || event.getLivingEntity() instanceof EntityChickPlymouthRock ||
		// event.getLivingEntity() instanceof EntityChickOrpington
		// || event.getLivingEntity() instanceof EntityChickRhodeIslandRed ||
		// event.getLivingEntity() instanceof EntityChickWyandotte)
		// && event.getSource().damageType.contains("Wall"))
		// if (!event.getLivingEntity().world.isRemote) {
		//
		// BlockPos poschk = new BlockPos(event.getLivingEntity().posX +
		// event.getLivingEntity().motionX / 1.5,
		// event.getLivingEntity().posY + .1F, event.getLivingEntity().posZ +
		// event.getLivingEntity().motionZ / 1.5);
		// Block blockchk =
		// event.getLivingEntity().world.getBlockState(poschk).getBlock();
		//
		// boolean isInMud = false;
		// if (blockchk == BlockHandler.blockMud)
		// event.setCanceled(true);
		//
		// }

	}

}