package com.animania.common.events;

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
            if (UpdateHandler.newestVersion.equals(UpdateHandler.currentVersion)) {
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
            URL url = new URL("https://gist.githubusercontent.com/Tschipp/150bdce860b9240c9cc363f5e7b95a6d/raw");
            Scanner s = new Scanner(url.openStream());
            UpdateHandler.newestVersion = s.next();
            s.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
