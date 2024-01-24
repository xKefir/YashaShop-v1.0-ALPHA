package com.minerail.yashashop.files;

import com.minerail.yashashop.main.ColorUtils;
import com.minerail.yashashop.main.YashaShop;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ConfigManager {
    final static Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("Yashashop");
    private static FileConfiguration config = plugin.getConfig();
    public static ConfigurationSection sec = config.getConfigurationSection("CategoryMenus.CategoryMenu.categories");
    final static File file = new File(Bukkit.getServer().getPluginManager().getPlugin("YashaShop").getDataFolder(), "config.yml");

    public static List<String> getItemLoreList(String path) {
        List<String> loreConfig = new ArrayList<>();
        for (String lore : config.getStringList(path)) {
            loreConfig.add(ColorUtils.translateColorCodes(lore));
        }

        return loreConfig;
    }
    public static String getItemDisplayName(String path) {
        return ColorUtils.translateColorCodes(config.getString(path));
    }


    //Startup method
    public static void setupConfigFile(YashaShop yashaShop) {
        ConfigManager.config = yashaShop.getConfig();
        yashaShop.saveDefaultConfig();
        System.out.println("Config.yml file successfully loaded.");
    }

    public static void reloadConfigFile() {
        config = YamlConfiguration.loadConfiguration(file);
    }

    //Readers for config file
    public static String getCategoryTitle() {
        return ColorUtils.translateColorCodes(config.getString("guiMenus.categories.title"));
    }
    public static String getConfigString(String stringpath) {
        return config.getString(stringpath);
    }

    public static Integer getConfigInt(String intPath) {
        return config.getInt(intPath);
    }

    public static String getInventoryName() {
       return ConfigManager.config.getString("guiMenus.categories.title");
    }
}