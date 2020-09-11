package com.animania.common.events;

import com.animania.Animania;
import com.animania.config.AnimaniaConfig;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

@EventBusSubscriber(modid = Animania.MODID)
public class LoginEventHandler
{

	@SubscribeEvent
	public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event)
	{
		if (UpdateHandler.show && AnimaniaConfig.gameRules.showModUpdateNotification == true)
		{
			event.player.sendMessage(new TextComponentString(UpdateHandler.updateStatus));

			ITextComponent download = new TextComponentString(I18n.translateToLocal("animania.update.download"));
			download.getStyle().setColor(TextFormatting.GOLD).setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponentString(I18n.translateToLocal("animania.update.show_latest_files")))).setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "http://minecraft.curseforge.com/projects/animania/files"));
			ITextComponent component = new TextComponentString(I18n.translateToLocal("animania.update.click_to")).appendSibling(download);
			event.player.sendMessage(component);
		}
	}
}
