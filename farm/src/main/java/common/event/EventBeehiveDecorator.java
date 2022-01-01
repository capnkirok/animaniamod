package common.event;

import com.animania.common.helper.AnimaniaHelper;
import common.block.BlockHive;
import common.block.BlockWildHive;
import common.handler.FarmAddonBlockHandler;
import config.FarmConfig;
import net.minecraft.block.BlockLog;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EventBeehiveDecorator
{

	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void onLevelDecoration(DecorateBiomeEvent.Decorate event)
	{

		if (FarmConfig.settings.hiveSpawning)
		{
			int frequencyBeehives = FarmConfig.settings.hiveSpawningFrequency;
			if (frequencyBeehives < 0)
			{
				frequencyBeehives = 0;
			}
			else if (frequencyBeehives > 10)
			{
				frequencyBeehives = 10;
			}

			Biome b = event.getLevel().getBiome(event.getPos());
			boolean isCorrectBiome = false;
			for (Type t : AnimaniaHelper.getBiomeTypes(FarmConfig.settings.hiveValidBiomeTypes))
			{
				if (BiomeDictionary.hasType(b, t))
				{
					isCorrectBiome = true;
				}
			}

			if ((event.getResult() == Result.ALLOW || event.getResult() == Result.DEFAULT) && event.getType() == EventType.TREE && event.getRand().nextInt(200) < frequencyBeehives && isCorrectBiome)
			{

				int x = event.getPos().getX() + 8;
				int z = event.getPos().getZ() + 8;
				int y = event.getLevel().getTopSolidOrLiquidBlock(event.getPos()).getY() + 2;

				BlockPos pos = new BlockPos(x, y, z);
				BlockPos pos2 = new BlockPos(x, y, z);
				for (int i = -7; i < 7; i++)
				{
					for (int j = 1; j < 4; j++)
					{
						for (int k = -7; k < 7; k++)
						{
							pos = new BlockPos(x + i, y + j, z + k);
							Block blockchk = event.getLevel().getBlockState(pos).getBlock();

							if (blockchk instanceof BlockLog)
							{
								for (int i2 = -3; i2 < 3; i2++)
								{
									for (int j2 = 1; j2 < 4; j2++)
									{
										for (int k2 = -3; k2 < 3; k2++)
										{
											pos2 = new BlockPos(pos.getX() + i2, pos.getY() + j2, pos.getZ() + k2);
											Block blockchk2 = event.getLevel().getBlockState(pos2).getBlock();
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
									event.getLevel().setBlock(pos.east(), FarmAddonBlockHandler.blockWildHive.defaultBlockState().withProperty(FACING, Direction.EAST));
									i = 7;
									j = 3;
									k = 7;
									break;
								}
								else if (side == 1)
								{
									event.getLevel().setBlock(pos.west(), FarmAddonBlockHandler.blockWildHive.defaultBlockState().withProperty(FACING, Direction.WEST));
									i = 7;
									j = 3;
									k = 7;
									break;
								}
								else if (side == 2)
								{
									event.getLevel().setBlock(pos.north(), FarmAddonBlockHandler.blockWildHive.defaultBlockState().withProperty(FACING, Direction.NORTH));
									i = 7;
									j = 3;
									k = 7;
									break;
								}
								else if (side == 3)
								{
									event.getLevel().setBlock(pos.south(), FarmAddonBlockHandler.blockWildHive.defaultBlockState().withProperty(FACING, Direction.SOUTH));
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