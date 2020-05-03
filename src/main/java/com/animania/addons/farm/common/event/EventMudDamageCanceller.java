package com.animania.addons.farm.common.event;

import com.animania.Animania;

import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = Animania.MODID)
public class EventMudDamageCanceller
{
	@SubscribeEvent
	public static void notifyAttack(LivingAttackEvent event)
	{
		// if ((event.getEntityLiving() instanceof EntityHamster ||
		// event.getEntityLiving() instanceof EntityFerretWhite
		// || event.getEntityLiving() instanceof EntityFerretGrey ||
		// event.getEntityLiving() instanceof EntityChickLeghorn
		// || event.getEntityLiving() instanceof EntityChickPlymouthRock ||
		// event.getEntityLiving() instanceof EntityChickOrpington
		// || event.getEntityLiving() instanceof EntityChickRhodeIslandRed ||
		// event.getEntityLiving() instanceof EntityChickWyandotte)
		// && event.getSource().damageType.contains("Wall"))
		// if (!event.getEntityLiving().world.isRemote) {
		//
		// BlockPos poschk = new BlockPos(event.getEntityLiving().posX +
		// event.getEntityLiving().motionX / 1.5,
		// event.getEntityLiving().posY + .1F, event.getEntityLiving().posZ +
		// event.getEntityLiving().motionZ / 1.5);
		// Block blockchk =
		// event.getEntityLiving().world.getBlockState(poschk).getBlock();
		//
		// boolean isInMud = false;
		// if (blockchk == BlockHandler.blockMud)
		// event.setCanceled(true);
		//
		// }

	}

}