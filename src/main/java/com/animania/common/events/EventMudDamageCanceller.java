package com.animania.common.events;

import com.animania.common.entities.chickens.ChickenLeghorn.EntityChickLeghorn;
import com.animania.common.entities.chickens.ChickenOrpington.EntityChickOrpington;
import com.animania.common.entities.chickens.ChickenPlymouthRock.EntityChickPlymouthRock;
import com.animania.common.entities.chickens.ChickenRhodeIslandRed.EntityChickRhodeIslandRed;
import com.animania.common.entities.chickens.ChickenWyandotte.EntityChickWyandotte;
import com.animania.common.entities.rodents.EntityFerretGrey;
import com.animania.common.entities.rodents.EntityFerretWhite;
import com.animania.common.entities.rodents.EntityHamster;
import com.animania.common.handler.BlockHandler;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventMudDamageCanceller
{
    @SubscribeEvent
    public void notifyAttack(LivingAttackEvent event) {
        if ((event.getEntityLiving() instanceof EntityHamster || event.getEntityLiving() instanceof EntityFerretWhite
                || event.getEntityLiving() instanceof EntityFerretGrey || event.getEntityLiving() instanceof EntityChickLeghorn
                || event.getEntityLiving() instanceof EntityChickPlymouthRock || event.getEntityLiving() instanceof EntityChickOrpington
                || event.getEntityLiving() instanceof EntityChickRhodeIslandRed || event.getEntityLiving() instanceof EntityChickWyandotte)
                && event.getSource().damageType.contains("Wall"))
            if (!event.getEntityLiving().world.isRemote) {

                BlockPos poschk = new BlockPos(event.getEntityLiving().posX + event.getEntityLiving().motionX / 1.5,
                        event.getEntityLiving().posY + .1F, event.getEntityLiving().posZ + event.getEntityLiving().motionZ / 1.5);
                Block blockchk = event.getEntityLiving().world.getBlockState(poschk).getBlock();

                boolean isInMud = false;
                if (blockchk == BlockHandler.blockMud)
                    event.setCanceled(true);

            }

    }

}