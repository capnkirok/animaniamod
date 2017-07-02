package com.animania.common.events;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import com.animania.Animania;

import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;

/*
 * Original Code VozValden, updated by Purplicious_Cow
 */

public class UpdateHandler
{

    private static String currentVersion = Animania.VERSION;
    private static String newestVersion;
    public static String  updateStatus   = "NULL";
    public static boolean show           = false;

    public static void init() {

        getNewestVersion();

        if (UpdateHandler.newestVersion != null) {
            if (UpdateHandler.newestVersion.equalsIgnoreCase(UpdateHandler.currentVersion)) {
                // show = true;
            }
            else {
                UpdateHandler.show = true;
                UpdateHandler.updateStatus = TextFormatting.WHITE + I18n.translateToLocal("animania.updatetext.1") + UpdateHandler.newestVersion + " "
                        + I18n.translateToLocal("animania.updatetext.2");
            }
        }
        else {
            //UpdateHandler.show = false;
            UpdateHandler.show = true;
            UpdateHandler.updateStatus = TextFormatting.WHITE + I18n.translateToLocal("animania.updatetext.3");
        }
    }

    private static void getNewestVersion() {

        try {
            URL url = new URL("http://www.creeptech.net/animania/1.11/versionchecker.txt");
            Scanner s = new Scanner(url.openStream());
            UpdateHandler.newestVersion = s.next();
            s.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
