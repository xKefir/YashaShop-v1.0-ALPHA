package com.minerail.yashashop.utils;


import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;

public class SectionFilesUtils {
    private static Plugin pl;
    private ConfigUtils configUtils = new ConfigUtils(this);
    private static InputStream inputS;
    private Path categoriesDirectory;
    private Path categoryFilePath;
    private YamlConfiguration sectionFIle;
    public Map<String, YamlConfiguration> loader = new LinkedHashMap<>();

    public SectionFilesUtils(ConfigUtils configUtils) {
    }

    public void createFiles(String file) throws IOException {
        pl = Bukkit.getPluginManager().getPlugin("YashaShop");
        inputS = pl.getResource("defaultsectionpattern.yml");
        categoriesDirectory = pl.getDataFolder().toPath().resolve("Shops");
        loader.clear();
        if (!Files.exists(categoriesDirectory)) {
            Files.createDirectories(categoriesDirectory);
        } else {
            if (this.configUtils.config.getBoolean("categoryGUI.elements." + file, true)) {
                categoryFilePath = categoriesDirectory.resolve(file + ".yml");
                if (!Files.exists(categoryFilePath)) {
                    Files.copy(inputS, categoryFilePath);
                    sectionFIle = YamlConfiguration.loadConfiguration(new File("plugins/YashaShop/Shops/" + file + ".yml"));
                } else {
                    sectionFIle = YamlConfiguration.loadConfiguration(new File("plugins/YashaShop/Shops/" + file + ".yml"));
                    loader.put(file, sectionFIle);
                }
            }
        }
    }
    public void createGuisAndItems() {

    }

}
