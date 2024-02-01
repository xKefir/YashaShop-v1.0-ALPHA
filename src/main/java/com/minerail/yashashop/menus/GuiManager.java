package com.minerail.yashashop.menus;


import com.minerail.yashashop.files.FileManager;
import com.minerail.yashashop.main.ColorUtils;
import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.Set;


public class GuiManager {
    //Main
    final static Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("Yashashop");
    private static Set<String> fileNamesOfCategories = FileManager.getFileNamesFromConfig();
    private static Set<String> listOfCategories;
    private static FileConfiguration config = plugin.getConfig();
    private static ConfigurationSection categoriesSections;

    //For categories items
    private static Material materialOfCategory;
    private static String displaynameOfCategory;
    private static Integer slotOfCategory;
    private static String npcOfCategory;
    private static List<String> loreOfCategory;
    private static GuiItem items;
    //For section files

    //For section gui

    //For section items

    public static void itemBuilderForCategoriesGui() {
        categoriesSections = config.getConfigurationSection("CategoryMenus.CategoryMenu.categories");
        for (String section : categoriesSections.getKeys(false)) {
            ConfigurationSection sec = categoriesSections.getConfigurationSection(section);
            //Material
            if (sec.contains("material")) {
                materialOfCategory = Material.valueOf(sec.getString("material"));
            }
            //DisplayName
            if (sec.contains("displayname")) {
                displaynameOfCategory = ColorUtils.translateColorCodes(sec.getString("displayname"));
            }
            //Slot
            if (sec.contains("slot")) {
                slotOfCategory = sec.getInt("slot");
            }
            //NPC
            if (sec.contains("NPC")) {
                npcOfCategory = sec.getString("NPC");
            }
            //Lore
            if (sec.contains("lore")) {
                loreOfCategory = sec.getStringList("lore");
            }
            //Sum
            if (materialOfCategory != null && displaynameOfCategory != null && slotOfCategory != null && loreOfCategory != null || npcOfCategory != null) {
                items = ItemBuilder.from(materialOfCategory).name(Component.text(displaynameOfCategory)).asGuiItem();
                categoriesGui.setItem(slotOfCategory, items);
            }
        }
        plugin.getLogger().info("Successfully loaded itemss for Categories GUI!");
    }
    public static void showCategoriesGui(Player player) {
        categoriesGui.setDefaultClickAction(event -> event.setCancelled(true));
        categoriesGui.open(player);
    }

    private static void showSectionGui(Player player) {
        sectionGui.open(player);
    }

    static Gui categoriesGui = Gui.gui()
            .title(Component.text(FileManager.getCategoryTitle()))
            .rows(FileManager.getConfigInt("CategoryMenus.CategoryMenu.rows"))
            .create();

    static Gui sectionGui = Gui.gui()
            .title(Component.text("dla spokoju"))
            .rows(3)
            .create();

}


