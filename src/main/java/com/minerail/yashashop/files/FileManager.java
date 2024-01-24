package com.minerail.yashashop.files;


import com.minerail.yashashop.main.YashaShop;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.Set;


public class FileManager {
    final static Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("Yashashop");

    public static void readingConfigFile() {
        ConfigurationSection categoriesSections = plugin.getConfig().getConfigurationSection("CategoryMenus.CategoryMenu.categories");
        if (categoriesSections != null) {
            Set<String> keys = categoriesSections.getKeys(false);
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
                        System.out.println("filename: '' for"+ key + "is empty in config.yml file!");
                    }
                }
            }
        } else {
        }

    }

}