package com.minerail.yashashop.files;

import com.minerail.yashashop.main.YashaShop;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
public class LangManager {
    private static YamlConfiguration lang;
    final static File file = new File(Bukkit.getServer().getPluginManager().getPlugin("YashaShop").getDataFolder(), "messages.yml");
    public static void setupLangFile(YashaShop yashaShop) {
        if (!file.exists()) {
            yashaShop.saveResource("messages.yml", false);
            System.out.println("messages.yml successfully created!");
        } else {
            lang = YamlConfiguration.loadConfiguration(file);
            System.out.println("File messages.yml successfully loaded!");
        }
    }
    public static void reloadLangFile() {
    lang = YamlConfiguration.loadConfiguration(file);
    }
    public static String getReloadSuccessMsg() {
        return lang.getString("reload-message");
    }
    public static String getNoPermMsg() {
        return lang.getString("player-no-permission");
    }
    public static String getPluginPrefix() {
        return lang.getString("prefix");
    }

}

