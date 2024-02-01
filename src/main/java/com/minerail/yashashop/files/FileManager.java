package com.minerail.yashashop.files;


import com.minerail.yashashop.main.ColorUtils;
import com.minerail.yashashop.main.YashaShop;
import com.minerail.yashashop.menus.GuiManager;
import dev.triumphteam.gui.guis.Gui;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class FileManager {
    final static Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("Yashashop");
    private static FileConfiguration config = plugin.getConfig();
    private static ConfigurationSection categoriesSections;
    private static ConfigurationSection categorySection;
    private static Path categoryDirectory;
    private static Path categoryFilePath;




//config
    public static void setupConfigFiles(YashaShop yashaShop) {
        plugin.saveDefaultConfig();
        System.out.println("Config.yml file successfully loaded.");
        createCategoryFiles();
        GuiManager.itemBuilderForCategoriesGui();
    }

    public static void reloadConfigFiles() {
        File file = new File(Bukkit.getServer().getPluginManager().getPlugin("YashaShop").getDataFolder(), "config.yml");
        config = YamlConfiguration.loadConfiguration(file);
        createCategoryFiles();
        GuiManager.itemBuilderForCategoriesGui();
    }

    //Readers for config file
    public static String getCategoryTitle() {
        return ColorUtils.translateColorCodes(config.getString("CategoryMenus.CategoryMenu.title"));
    }
    public static String getConfigString(String stringpath) {
        return config.getString(stringpath);
    }

    public static Integer getConfigInt(String intPath) {
        return config.getInt(intPath);
    }



//Creating or loading files from string "filename"
public static Set<String> getFileNamesFromConfig() {
        Set<String> fileNamesList = new HashSet<>();
        categoriesSections = config.getConfigurationSection("CategoryMenus.CategoryMenu.categories");
        if (categoriesSections != null) {
            for (String key : categoriesSections.getKeys(false)) {
                ConfigurationSection categorySection = categoriesSections.getConfigurationSection(key);
                if (categorySection != null && categorySection.contains("filename")) {
                    String fileName = categorySection.getString("filename");
                    fileNamesList.add(fileName);
                }
            }
        } return fileNamesList;
    }


    public static void createCategoryFiles() {
        categoryDirectory = plugin.getDataFolder().toPath().resolve("Categories");
        if (!Files.exists(categoryDirectory)) {
            try {
                Files.createDirectories(categoryDirectory);
                plugin.getLogger().info("Created directory: Categories");
            } catch (IOException e) {
                e.printStackTrace();}
        } else {plugin.getLogger().info("Loaded directory: Categories");}
        for (String categoryFileName : getFileNamesFromConfig()) {
            categoryFilePath = categoryDirectory.resolve(categoryFileName);
            if (!Files.exists(categoryFilePath)) {
                try {
                    InputStream inputStream = plugin.getResource("defaultyamlstructureforsection.yml");
                    Files.copy(inputStream, categoryFilePath);
                    plugin.getLogger().info("Created file: " + categoryFileName);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            } else {
                plugin.getLogger().info("Loaded File: " + categoryFileName);
                YamlConfiguration load = new YamlConfiguration();
                try {
                    load.load("plugins/YashaShop/Categories/"+categoryFileName);
                } catch (IOException | InvalidConfigurationException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }
}
