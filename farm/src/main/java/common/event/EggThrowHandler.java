package common.event;

import com.animania.common.handler.AddonInjectionHandler;
import common.handler.FarmAddonItemHandler;
import config.FarmConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EggThrowHandler
{

	@SubscribeEvent
	public static void notify(PlayerInteractEvent.RightClickItem event)
	{

		ItemStack stack = event.getItemStack();
		Player player = event.getPlayer();
		BlockPos pos = event.getPos();
		Level level = event.getLevel();

		if (stack != ItemStack.EMPTY && (stack.getItem() == Items.EGG || stack.getItem() == FarmAddonItemHandler.brownEgg))
		{

			if (FarmConfig.settings.allowEggThrowing)
			{
				int esize = level.loadedEntityList.size();
				for (int k = 0; k <= esize - 1; k++)
				{
					Entity entity = level.loadedEntityList.get(k);

					double xt = entity.getX();
					double yt = entity.getY();
					double zt = entity.getZ();
					int x1 = MathHelper.floor(player.getX());
					int y1 = MathHelper.floor(player.getY());
					int z1 = MathHelper.floor(player.getZ());
					int x2 = (int) Math.abs(xt - x1);
					int y2 = (int) Math.abs(yt - y1);
					int z2 = (int) Math.abs(zt - z1);

					AddonInjectionHandler.runInjection("extra", "rodentEggEvent", null, entity, x2, y2, z2, event);
				}

			}
			else
			{
				// event.getPlayer().swingArm(event.getHand());
				event.isCanceled();
				event.setCanceled(true);
			}

		}
	}

}
