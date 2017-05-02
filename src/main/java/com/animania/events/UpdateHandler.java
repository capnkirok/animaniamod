package com.animania.events;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;

import com.animania.Animania;

/*
 * Original Code VozValden, updated by Purplicious_Cow
 */


public class UpdateHandler {

	private static String currentVersion = Animania.version;
	private static String newestVersion;
	public static String updateStatus = "NULL";
	public static boolean show = false;

	public static void init() {
		
		getNewestVersion();
	
		if(newestVersion != null) {
			if(newestVersion.equalsIgnoreCase(currentVersion)) {
				//show = true;
			} else {
				show = true;
				updateStatus = TextFormatting.WHITE + I18n.translateToLocal("animania.updatetext.1") + newestVersion + " " + I18n.translateToLocal("animania.updatetext.2");
			}
		}else {
			show = false;
			updateStatus = TextFormatting.WHITE + I18n.translateToLocal("animania.updatetext.3");
		}
	}

	private static void getNewestVersion() {
		
		try {
			URL url = new URL("http://www.creeptech.net/animania/1.11/versionchecker.txt");
			Scanner s = new Scanner(url.openStream());
			newestVersion = s.next();
			s.close();
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
	}
}

