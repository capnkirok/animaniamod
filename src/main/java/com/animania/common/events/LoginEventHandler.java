package com.animania.common.events;

import com.animania.Animania;
import com.animania.config.AnimaniaConfig;

import net.minecraft.ChatFormatting;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Animania.MODID)
public class LoginEventHandler
{

	@SubscribeEvent
	public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event)
	{
		if (UpdateHandler.show && AnimaniaConfig.gameRules.showModUpdateNotification)
		{
			event.getPlayer().sendMessage(new TextComponent(UpdateHandler.updateStatus));

			ITextComponent download = new TextComponent(I18n.translateToLocal("animania.update.download"));
			download.getStyle().setColor(ChatFormatting.GOLD).setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent(I18n.translateToLocal("animania.update.show_latest_files")))).setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "http://minecraft.curseforge.com/projects/animania/files"));
			ITextComponent component = new TextComponent(I18n.translateToLocal("animania.update.click_to")).appendSibling(download);
			event.getPlayer().sendMessage(component);
		}
	}
}
