package com.minerail.yashashop.files;


import com.minerail.yashashop.main.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class FileManager {
    final static Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("Yashashop");
    public static ConfigurationSection categoriesSections = plugin.getConfig().getConfigurationSection("CategoryMenus.CategoryMenu.categories.");
    public static Set<String> keys = categoriesSections.getKeys(false);
    public static String key;

    public static void readingConfigFile() {
        if (categoriesSections != null) {
            for (String key : keys) {
                ConfigurationSection path = categoriesSections.getConfigurationSection(key);
                if (path != null) {
                    String fileName = path.getString("filename");
                    if (fileName != null) {
                        File directoryCategories = new File("plugins/YashaShop/Categories");

                        if (directoryCategories.exists()) {
                            File categoryFiles = new File("plugins/YashaShop/Categories/"+fileName);
                            if (!categoryFiles.exists()) {
                                try {
                                    System.out.println("File not found, creating file: " + fileName);
                                    categoryFiles.createNewFile();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            directoryCategories.mkdirs();
                            System.out.println("Directory not found.. New Directory just created.");
                        }
                    } else {
                        System.out.println("filename: " + fileName + " for "+ key + " is empty in config.yml file!");
                    }
                }
            }
        }

    }
    public static List<String> getItemLoreList(ConfigurationSection categorySection) {
        List<String> loreConfig = new ArrayList<>();
        if (categoriesSections != null) {
            for (String key : keys) {
                for (String lore : plugin.getConfig().getStringList(categorySection + ".lore")) {
                    loreConfig.add(ColorUtils.translateColorCodes(lore));
                }
            }
        }
        return loreConfig;
    }
}