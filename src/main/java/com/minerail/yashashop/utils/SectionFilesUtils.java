package com.minerail.yashashop.utils;


import com.minerail.yashashop.menus.Guis;
import org.bukkit.Bukkit;
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
    private static YamlConfiguration sectionFIle;
    private Map<String, YamlConfiguration> loader = new LinkedHashMap<>();
    private static Guis guis;

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
    public void buildShops() {
        this.guis = new Guis(this);
        for (Map.Entry<String, YamlConfiguration> entry : loader.entrySet()) {
            String id = entry.getKey();
            YamlConfiguration file = entry.getValue();
            guis.buildItemsForCategoryGui(file.getBoolean("Item.enabled"),
                    file.getString("Item.displayname"),
                    file.getString("Item.material"),
                    id,
                    file.getInt("Item.slot")
            );

            guis.createSectionGuis(id,
                    file.getString("Section.GuiSettings.displayName"),
                    file.getInt("Section.GuiSettings.rows"),
                    file.getInt("Section.GuiSettings.pageSize")
            );
            for (String it : file.getConfigurationSection("Section.GuiSettings.elements").getKeys(false)) {
                guis.buildItemsForSection(file.getString("Section.GuiSettings.elements." + it + ".material"),
                        file.getDouble("Section.GuiSettings.elements." + it + ".buy"),
                        file.getDouble("Section.GuiSettings.elements." + it + ".sell")
                );
            }

        }
    }

}
