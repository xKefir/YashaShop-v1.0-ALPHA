package com.minerail.yashashop.menus;


import com.minerail.yashashop.files.ConfigManager;
import com.minerail.yashashop.files.FileManager;
import com.minerail.yashashop.main.ColorUtils;
import mc.obliviate.inventory.Gui;
import mc.obliviate.inventory.Icon;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class CategoriesMenu extends Gui {
    final Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("Yashashop");


    public CategoriesMenu(Player player) {
        super(player,
                "category",
                ConfigManager.getCategoryTitle(),
                ConfigManager.getConfigInt("CategoryMenus.CategoryMenu.rows"));
    }
    public void onOpen(InventoryOpenEvent event) {
        fillGui(new ItemStack(Material.valueOf(ConfigManager.getConfigString("CategoryMenus.CategoryMenu.filler-item.material"))));
        ConfigurationSection categoriesSections = plugin.getConfig().getConfigurationSection("CategoryMenus.CategoryMenu.categories.");
        if (categoriesSections != null) {
            for (String key : categoriesSections.getKeys(false)) {
                ConfigurationSection categorySection = categoriesSections.getConfigurationSection(key);
                if (categorySection != null
                        && categorySection.contains("displayname")
                        && categorySection.contains("material")
                        && categorySection.contains("lore")
                        && categorySection.contains("slot")) {
                    String materialFromConfig = categorySection.getString("material");
                    String displayNameOfItem = ColorUtils.translateColorCodes(categorySection.getString("displayname"));
                    List<String> loreOfItem = FileManager.getItemLoreList(categorySection);
                    Integer slotOfitem = categorySection.getInt("slot");
                    Icon items = new Icon(Material.valueOf(materialFromConfig)).setName(displayNameOfItem).setLore(loreOfItem).hideFlags().setAmount(1);
                    addItem(slotOfitem, items);

                }
            }
        }
    }



}
