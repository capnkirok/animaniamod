package com.animania.common.events;

import java.util.Random;

import com.animania.config.AnimaniaConfig;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventBeehiveDecorator {

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void onWorldDecoration(DecorateBiomeEvent.Decorate event) {

		if (AnimaniaConfig.gameRules.beehiveSpawning) {
			int frequencyBeehives = AnimaniaConfig.gameRules.beehiveSpawningFrequency;
			if (frequencyBeehives < 0) {
				frequencyBeehives = 0;
			} else if (frequencyBeehives > 10) {
				frequencyBeehives = 10;
			}

			if((event.getResult() == Result.ALLOW || event.getResult() == Result.DEFAULT) && (event.getType() == EventType.TREE) && event.getRand().nextInt(10) < frequencyBeehives) { 

				int x = event.getPos().getX() + 8;
				int z = event.getPos().getZ() + 8;
				int y = event.getWorld().getTopSolidOrLiquidBlock(event.getPos()).getY() + 2;

				BlockPos pos = new BlockPos(x, y, z);
				for (int i = -7; i < 7; i++) {
					for (int j = 1; j < 4; j++) {
						for (int k = -7; k < 7; k++) {
							pos = new BlockPos(x + i, y + j, z + k);
							Block blockchk = event.getWorld().getBlockState(pos).getBlock();
							if (blockchk instanceof BlockLog) {

								int side = event.getRand().nextInt(4);

								if (side == 0) {
									event.getWorld().setBlockState(pos.east(),Blocks.GLOWSTONE.getDefaultState());
									i = 7; j = 3; k =7;
									break;
								} else if (side == 1) {
									event.getWorld().setBlockState(pos.west(),Blocks.GLOWSTONE.getDefaultState());
									i = 7; j = 3; k =7;
									break;
								} else if (side == 2) { 
									event.getWorld().setBlockState(pos.north(),Blocks.GLOWSTONE.getDefaultState());
									i = 7; j = 3; k =7;
									break;
								} else if (side == 3) {
									event.getWorld().setBlockState(pos.south(),Blocks.GLOWSTONE.getDefaultState());
									i = 7; j = 3; k =7;
									break;
								}
							}
						}
					}
				}
			}
		}
	}
}