package com.minerail.yashashop.utils;

import com.minerail.yashashop.main.YashaShop;
import com.minerail.yashashop.menus.Guis;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashSet;

public class ConfigUtils {
    public ConfigUtils(YashaShop yashaShop) {
    }
    public ConfigUtils(Guis Guis) {
    }
    public ConfigUtils(SectionFilesUtils sectionFilesUtils) {
    }
    private static Plugin plugin;

    public static YamlConfiguration config;
    private static File c1;


    private SectionFilesUtils sections;
    private LinkedHashSet<String> elements = new LinkedHashSet<String>();


    public void createOrLoadConfig() throws IOException, InvalidConfigurationException {
        plugin = Bukkit.getPluginManager().getPlugin("YashaShop");
        c1 = new File(plugin.getDataFolder(), "config.yml");
        if (!c1.exists()) {
            plugin.saveResource("config.yml", false);
            config = YamlConfiguration.loadConfiguration(c1);
        } else {
            config = YamlConfiguration.loadConfiguration(c1);
            getElements();
        }
    }
    public void getElements() throws IOException {
        elements.clear();
        if (config.getConfigurationSection("categoryGUI.elements") != null) {
            for (String el : config.getConfigurationSection("categoryGUI.elements").getKeys(false)) {
                elements.add(el);
            }
            createSectionFiles();
        }
    }
    public void createSectionFiles() throws IOException {
        sections = new SectionFilesUtils(this);
        for (String file : elements) {
            sections.createFiles(file);
        }
    }
    //Guis
    public boolean guiEnabled() {
        return config.getBoolean("categoryGUI.enabled", true);
    }
    public String getGuiName() {
        return config.getString("categoryGUI.displayName", "default");
    }
    public int getGuiRows() {
        return config.getInt("categoryGUI.rows", 9);
    }

}

