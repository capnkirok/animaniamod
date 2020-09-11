package com.animania.addons.farm.common.event;

import com.animania.addons.farm.common.block.BlockHive;
import com.animania.addons.farm.common.block.BlockWildHive;
import com.animania.addons.farm.common.handler.FarmAddonBlockHandler;
import com.animania.addons.farm.config.FarmConfig;
import com.animania.common.helper.AnimaniaHelper;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockLog;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventBeehiveDecorator
{

	public static final PropertyDirection FACING = BlockDirectional.FACING;

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void onWorldDecoration(DecorateBiomeEvent.Decorate event)
	{

		if (FarmConfig.settings.hiveSpawning)
		{
			int frequencyBeehives = FarmConfig.settings.hiveSpawningFrequency;
			if (frequencyBeehives < 0)
			{
				frequencyBeehives = 0;
			} else if (frequencyBeehives > 10)
			{
				frequencyBeehives = 10;
			}

			Biome b = event.getWorld().getBiome(event.getPos());
			boolean isCorrectBiome = false;
			for (Type t : AnimaniaHelper.getBiomeTypes(FarmConfig.settings.hiveValidBiomeTypes))
			{
				if (BiomeDictionary.hasType(b, t))
				{
					isCorrectBiome = true;
				}
			}

			if ((event.getResult() == Result.ALLOW || event.getResult() == Result.DEFAULT) && (event.getType() == EventType.TREE) && event.getRand().nextInt(200) < frequencyBeehives && isCorrectBiome)
			{

				int x = event.getPos().getX() + 8;
				int z = event.getPos().getZ() + 8;
				int y = event.getWorld().getTopSolidOrLiquidBlock(event.getPos()).getY() + 2;

				BlockPos pos = new BlockPos(x, y, z);
				BlockPos pos2 = new BlockPos(x, y, z);
				for (int i = -7; i < 7; i++)
				{
					for (int j = 1; j < 4; j++)
					{
						for (int k = -7; k < 7; k++)
						{
							pos = new BlockPos(x + i, y + j, z + k);
							Block blockchk = event.getWorld().getBlockState(pos).getBlock();

							if (blockchk instanceof BlockLog)
							{
								for (int i2 = -3; i2 < 3; i2++)
								{
									for (int j2 = 1; j2 < 4; j2++)
									{
										for (int k2 = -3; k2 < 3; k2++)
										{
											pos2 = new BlockPos(pos.getX() + i2, pos.getY() + j2, pos.getZ() + k2);
											Block blockchk2 = event.getWorld().getBlockState(pos2).getBlock();
											if (blockchk2 instanceof BlockHive || blockchk2 instanceof BlockWildHive)
											{
												i2 = 7;
												j2 = 3;
												k2 = 7;
												i = 7;
												j = 3;
												k = 7;
												break;
											}
										}
									}
								}

								int side = event.getRand().nextInt(4);

								if (side == 0)
								{
									event.getWorld().setBlockState(pos.east(), FarmAddonBlockHandler.blockWildHive.getDefaultState().withProperty(FACING, EnumFacing.EAST));
									i = 7;
									j = 3;
									k = 7;
									break;
								} else if (side == 1)
								{
									event.getWorld().setBlockState(pos.west(), FarmAddonBlockHandler.blockWildHive.getDefaultState().withProperty(FACING, EnumFacing.WEST));
									i = 7;
									j = 3;
									k = 7;
									break;
								} else if (side == 2)
								{
									event.getWorld().setBlockState(pos.north(), FarmAddonBlockHandler.blockWildHive.getDefaultState().withProperty(FACING, EnumFacing.NORTH));
									i = 7;
									j = 3;
									k = 7;
									break;
								} else if (side == 3)
								{
									event.getWorld().setBlockState(pos.south(), FarmAddonBlockHandler.blockWildHive.getDefaultState().withProperty(FACING, EnumFacing.SOUTH));
									i = 7;
									j = 3;
									k = 7;
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